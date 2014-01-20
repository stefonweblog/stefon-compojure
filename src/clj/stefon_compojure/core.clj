(ns stefon-compojure.core
  (:require [stefon-compojure.plugin :as plugin]))

(defn create-post [input]

  (plugin/pair {:id (:id @(plugin/get-plugin-state))
                :message {:stefon.post.create {:parameters input}}}))

(defn retrieve-post [input]

  (plugin/pair {:id (:id @(plugin/get-plugin-state))
                :message {:stefon.post.retrieve {:parameters input}}}))

(defn update-post [input]

  (let [pid (:id input)
        update-map (dissoc input :id)]

    (plugin/pair {:id (:id @(plugin/get-plugin-state))
                  :message {:stefon.post.update {:parameters {:id pid :update-map update-map}}}})))

(defn delete-post [input]

  (plugin/pair {:id (:id @(plugin/get-plugin-state))
                :message {:stefon.post.delete {:parameters input}}}))

(defn list-posts []

  (plugin/pair {:id (:id @(plugin/get-plugin-state))
                :message {:stefon.post.list {:parameters []}}}))
