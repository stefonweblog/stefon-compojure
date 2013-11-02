(ns stefon-compojure.core
  (:require jig
            [stefon-compojure.handler :refer (app-routes)]
            [jig.web.ring :refer (add-handler)])
  (:import (jig Lifecycle)))


;; A Jig Component
(deftype Component [config]
  Lifecycle

  (init [_ system]

    (add-handler app-routes system config)
    system)

  (start [_ system]

    system)

  (stop [_ system]

    system))
