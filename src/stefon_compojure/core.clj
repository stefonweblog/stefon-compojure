(ns stefon-compojure.core
  #_(:require jig
            [stefon-compojure.handler :refer (app-routes)]
            [jig.web.ring :refer (add-handler)])
  #_(:import (jig Lifecycle)))


;; A Jig Component
#_(deftype Component [config]
  Lifecycle

  (init [_ system]

    (add-handler app-routes system config))

  (start [_ system]

    system)

  (stop [_ system]

    system))
