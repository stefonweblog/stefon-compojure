(ns stefon-compojure.plugin
  )

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
           recievefn (:recievefn result)] )))

(defn plugin

  ([function-map])
  ([function-map env])
  ([function-map env config]))
