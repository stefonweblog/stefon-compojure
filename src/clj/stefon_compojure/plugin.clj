(ns stefon-compojure.plugin
  (:require [clojure.core.async :as async]
            [stefon-compojure.config :as config]
            [taoensso.timbre :as timbre]))


(def ^:dynamic *plugin-state* (atom { :dispatch-channel (async/chan) }))
(defn get-plugin-state [] *plugin-state*)


(defn generic-handler [env system-atom message]

  (timbre/info "stefon-compojure.plugin/generic-handler CALLED > system-atom[" system-atom
               "] > message[" message "]")

  (let [dispatch-channel (-> @(get-plugin-state) :dispatch-channel)]
    (async/go (async/>! dispatch-channel message))))


(defn pair [message]

  (let [sendfn (:sendfn @(get-plugin-state))
        dispatch-channel (-> @(get-plugin-state) :dispatch-channel)

        p (promise)
        x (async/go (deliver p (async/<! dispatch-channel)))]

    (sendfn message)
    @p))

(defn plugin
  ([] (plugin :dev))
  ([env]
     (clojure.core/partial generic-handler env)))

(defn plugin-ack
  "We're going to expect an acknowledgement with the following keys:
   '(:id :sendfn :recievefn :channel)"
  ([result-map]
     (plugin-ack result-map (config/get-config)))
  ([result-map config]
     (swap! (get-plugin-state) (fn [inp]
                                 (clojure.core/merge inp result-map)))))
