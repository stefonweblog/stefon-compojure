(ns stefon-compojure.service
  (:require [ajax.core :refer [PUT GET POST DELETE]]))

(defn fubar [] "fubar")

(defn handler [response]
  (.log js/console (str response)))

(defn error-handler [{:keys [status status-text]}]
  (.log js/console
        (str "something bad happened: " status " " status-text)))


(defn create-post [message]

  (PUT "/post"
       {:params message
        :handler handler
        :error-handler error-handler}))

(defn retrieve-post [pid]

  (GET "/post"
       {:params {:id pid}
        :handler handler
        :error-handler error-handler}))

(defn update-post [message]

  (UPDATE "/post"
          {:params message
           :handler handler
           :error-handler error-handler}))

(defn delete-post [pid]

  (DELETE "/post"
        {:params {:id    "123asdf"}
         :handler handler
         :error-handler error-handler}))

(defn retrieve-posts []

  (GET "/posts"
       {:handler handler
        :error-handler error-handler}))
