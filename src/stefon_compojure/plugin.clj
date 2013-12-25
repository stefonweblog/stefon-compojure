(ns stefon-compojure.plugin
  (:require [clojure.core.async :as async :refer :all]
            [stefon-compojure.config :as config]))


(defn generic-handler [env message]
  (println ">> generic-handler CALLED > " message))


(def ^:dynamic *plugin-state* (atom {}))
(defn get-plugin-state [] *plugin-state*)


(defn plugin
  ([]
     (plugin :dev))
  ([env]
     (fn [msg] ">> test-handler > " msg)
     #_(partial generic-handler env)))

(defn plugin-ack
  "We're going to expect an acknowledgement with the following keys:
   '(:id :sendfn :recievefn :channel)"
  ([result-map]
     (plugin-ack result-map (config/get-config)))

  ([result-map config]

     (swap! (get-plugin-state) (fn [inp]
                                 (clojure.core/merge inp result-map)))))
