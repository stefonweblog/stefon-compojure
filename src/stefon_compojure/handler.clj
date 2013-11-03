(ns stefon-compojure.handler
  (:use compojure.core)
  (:require [compojure.handler :as handler]
            [compojure.route :as route]
            [stefon-webui-common.core :as common]))

(defroutes app-routes
  (GET "/helloworld" [:as req] (common/handle-hello-world req))
  (route/resources "/")
  (route/not-found "Not Found"))

#_(def app
  (handler/site app-routes))
