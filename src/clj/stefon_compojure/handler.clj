(ns stefon-compojure.handler
  (:use compojure.core)
  (:require [compojure.handler :as handler]
            [compojure.route :as route]
            [taoensso.timbre :as timbre]
            ring.adapter.jetty

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

             ;; enforce key / value ordering
             finputs {:title (:title pinputs)
                      :content (:content pinputs)
                      :content-type (:content-type pinputs)
                      :created-date (.parse sdformat (:created-date pinputs))
                      :modified-date (.parse sdformat (:modified-date pinputs))
                      :assets (if (:assets pinputs) (:assets pinputs) [])
                      :tags (if (:tags pinputs) (:tags pinputs) [])}]

         (core/create-post finputs)))

  (GET "/post" [:as req]
       (core/retrieve-post (:params req)))

  (POST "/post" [:as req] "post post"

        (let [pinputs (:params req)
              finputs (-> pinputs
                          (dissoc pinputs :created-date :modified-date)
                          (assoc :modified-date (java.util.Date.)))]

         (core/update-post finputs)))

  (DELETE "/post" [:as req]
          (core/delete-post (:params req)))

  (GET "/posts" [:as req]
       (core/list-posts))


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

(defn run
  []
  (defonce ^:private server
    (ring.adapter.jetty/run-jetty #'app-routes {:port 8080 :join? false}))
  server)

