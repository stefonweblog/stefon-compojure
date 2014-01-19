(ns stefon-compojure.handler
  (:use compojure.core)
  (:require [compojure.handler :as handler]
            [compojure.route :as route]
            [taoensso.timbre :as timbre]

            [stefon-compojure.core :as core]
            [stefon-webui-common.core :as common]))

(require '[clojure.pprint :refer :all])

(defroutes app-routes

  (GET "/helloworld" [:as req]
       (common/handle-hello-world req))

  ;; TODO
  ;; load resources from the responsive-baseline project
  ;; ex: (-> "public/apple-touch-icon.png" io/resource)


  ;; ===> POST
  (PUT "/post" [:as req]
       (let [pinputs (:params req)
             sdformat (java.text.SimpleDateFormat. "MM/DD/yyyy")
             cd (.parse sdformat (:created-date pinputs))
             md (.parse sdformat (:modified-date pinputs))

             finputs (assoc pinputs :created-date cd :modified-date md)
             finputs (if-not (:assets finputs) (assoc finputs :assets []))
             finputs (if-not (:tags finputs) (assoc finputs :tags []))]

         (core/create-post finputs)))

  (GET "/post" [:as req] "get post")
  (POST "/post" [:as req] "post post")
  (DELETE "/post" [:as req] "delete post")
  (GET "/posts" [:as req] "get posts")

  ;; ===> ASSET
  (PUT "/asset" [:as req] "put asset")
  (GET "/asset" [:as req] "get asset")
  (POST "/asset" [:as req] "post asset")
  (DELETE "/asset" [:as req] "delete asset")
  (GET "/assets" [:as req] "get assets")

  ;; ===> TAG
  (PUT "/tag" [:as req] "put tag")
  (GET "/tag" [:as req] "get tag")
  (POST "/tag" [:as req] "post tag")
  (DELETE "/tag" [:as req] "delete tag")
  (GET "/tags" [:as req] "get tags")


  (route/resources "/")
  (route/not-found "Not Found"))

(def app
  (handler/site app-routes))
