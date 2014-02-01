(ns stefon-compojure.run
  (:require [ring.adapter.jetty :refer :all]
            [stefon-compojure.handler :as handler]))

(defn create-server []
  (defonce server (run-jetty #'handler/app {:port 8080 :join? false})))

(defn start-server []
  (if-not server
    (create-server))
  (.stop server)
  (.start server))

(defn stop-server []
  (if server (.stop server)))
