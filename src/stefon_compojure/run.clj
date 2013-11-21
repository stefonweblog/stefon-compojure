(ns stefon-compojure.run
  (:require [ring.adapter.jetty :refer :all]
            [stefon-compojure.handler :as handler]))


(defonce server (run-jetty #'handler/app {:port 8080 :join? false}))
