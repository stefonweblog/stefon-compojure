(ns stefon-compojure.plugin
  (:require [stefon-compojure.config :as config]))


(defn generic-handler [env message]
  (println ">> generic-handler CALLED > " message))


(def ^:dynamic *plugin-state* (atom {}))
(defn get-plugin-state [] *plugin-state*)


(defn plugin
  ([]
     (plugin :dev))
  ([env]
     (partial generic-handler env)))

(defn plugin-ack
  "We're going to expect an acknowledgement with the following keys:
   '(:id :sendfn :recievefn :channel)"
  ([result-map]

     (println "... plugin-ack: " result-map)
     (plugin-ack result-map (config/get-config)))

  ([result-map config]
     (swap! (get-plugin-state) (fn [inp] (merge inp result-map)))))
