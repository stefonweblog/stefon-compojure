(ns stefon-compojure.handler
  (:use compojure.core)
  (:require [compojure.handler :as handler]
            [compojure.route :as route]
            [stefon-webui-common.core :as common]))

(defroutes app-routes
  (GET "/" [] (common/handle-hello-world))
  (route/resources "/")
  (route/not-found "Not Found"))

(def app
  (handler/site app-routes))
