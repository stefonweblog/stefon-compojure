(ns stefon-compojure.plugin
  (:require [clojure.core.async :as async]
            [taoensso.timbre :as timbre]
            [stefon-compojure.config :as config]
            [missing-utils.core :as missing]))


(def ^:dynamic *plugin-state* (atom { :response-messages [] }))
(defn get-plugin-state [] *plugin-state*)


(defn generic-handler
  "Default mechanism for receiving message responses from the kernel. Logs and sends responses to a dispatch channel"
  [env system-atom message]

  (timbre/warn "stefon-compojure.plugin/generic-handler CALLED > message[" message "]")

  (swap! (get-plugin-state)
         (fn [inp]
           (update-in inp [:response-messages] conj message))))


(defn response-check [message filter-check-fn]

  (and (not (empty? (-> @(get-plugin-state) :response-messages)))
       (filter filter-check-fn
               (-> @(get-plugin-state) :response-messages))))


;; TODO plugin/pair function is mixing up requests; untagle in order to run all tests concurrently
(defn pair
  "Binds an input message, to the asynchronous response from the kernel"
  [message]

  (let [message-id (missing/generate-id)
        new-message (update-in message [:message]
                               (fn [inp]
                                 (assoc inp :message-id message-id)))

        sendfn (:sendfn @(get-plugin-state))
        response-messages (-> @(get-plugin-state) :response-messages)
        response-check-fn (fn [inp]
                            (and (= "kernel" (:from inp))
                                 (= (-> message :message keys first)
                                    (:action inp))))
        p (promise)]

    (timbre/warn (str "INPUT: " new-message))
    (sendfn new-message)
    (deliver p
             (loop []

               (Thread/sleep 200)
               (if (response-check message response-check-fn)

                 (first (filter response-check-fn
                                (-> @(get-plugin-state) :response-messages)))
                 (recur))))

    @p))

(defn plugin
  "First step in plugin handshake. Kernel calls this function, and we should return our listener that accepts two function parameters: system-atom, message"
  ([] (plugin :dev))
  ([env]
     (clojure.core/partial generic-handler env)))

(defn plugin-ack
  "We're going to expect an acknowledgement with the following keys:
   '(:id :sendfn :recievefn :channel)"

  ([result-map]
     (plugin-ack result-map (config/get-config)))

  ([result-map config]
     (swap! (get-plugin-state)
            (fn [inp]
              (clojure.core/merge inp result-map)))))
