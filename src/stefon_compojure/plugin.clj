(ns stefon-compojure.plugin
  (:require [clojure.core.async :as async]
            [stefon-compojure.config :as config]))


(defn generic-handler [env system-atom message]
  (println ">> generic-handler CALLED > system-atom[" system-atom "] > message[" message "]"))


(def ^:dynamic *plugin-state* (atom {}))
(defn get-plugin-state [] *plugin-state*)


(defn plugin
  ([]
     (plugin :dev))
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
