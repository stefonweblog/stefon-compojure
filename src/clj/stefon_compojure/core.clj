(ns stefon-compojure.core
  (:require [stefon-compojure.plugin :as plugin]))

(defn create-post [input]

  (plugin/pair {:id (:id @(plugin/get-plugin-state))
                :message {:stefon.post.create {:parameters input}}}))

(defn retrieve-post [input]

  (plugin/pair {:id (:id @(plugin/get-plugin-state))
                :message {:stefon.post.retrieve {:parameters input}}}))
