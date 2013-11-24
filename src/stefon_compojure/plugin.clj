(ns stefon-compojure.plugin

  (:require [stefon-datomic.config :as config]))

(defn generic-handler [env message]

  (println ">> generic-handler CALLED > " message) )

(defn bootstrap-stefon
  "Start the system"

  ([function-map]
     (bootstrap-stefon function-map :dev))

  ([function-map env]
     (bootstrap-stefon function-map env (partial generic-handler env)))

  ([function-map env handlerfn]


     ;; START System
     (if-not ((:system-started? function-map))
       ((:start-system function-map)))


     ;; ATTACH Plugin
     (let [result ((:attach-plugin function-map) handlerfn)

           cid (:id result)
           sendfn (:sendfn result)
           recievefn (:recievefn result)]

       result)))

(defn plugin

  ([function-map]
     (plugin function-map :dev))
  ([function-map env]
     (plugin function-map env (config/get-config)))
  ([function-map env config]

     (let [result (bootstrap-stefon function-map env)]

       )))
