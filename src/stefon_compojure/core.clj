(ns stefon-compojure.core
  (:require jig)
  (:import (jig Lifecycle)))


;; A Jig Component
(deftype Component [config]
  Lifecycle

  (init [_ system]

    ;;(println ">> init CALLED > " system)
    system)

  (start [_ system]

    ;;(println ">> start CALLED > " system)
    system)

  (stop [_ system]

    ;;(println ">> stop CALLED > " system)
    system))
