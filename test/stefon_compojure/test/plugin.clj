(ns stefon-compojure.test.plugin

  ;;(:use )

  (:require [clojure.test :refer :all]
            [ring.mock.request :refer :all]
            [midje.sweet :refer :all]

            [stefon-compojure.plugin :as pluginC]
            [stefon.shell :as shell]))


#_(deftest basic-crud

  (testing "main route"

    (let [result (bootstrap-stefon)
          sendfn (:sendfn result)
          channel (:channel result)

          id (:id result)
          heartbeat (sendfn {:id id
                             :message {:stefon.domain.schema {:parameters nil}}})]

      (println heartbeat)

      (is (= 1 1))
      (is (= 2 2)))))



(deftest test-plugin-stefon

  (testing "plugging into stefon plugin framework"

    (shell/start-system)
    (shell/load-plugin 'stefon-compojure.plugin)

    (is (not (empty? @(pluginC/get-plugin-state))))))




;; basic CRUD for posts



;; TODO - hookup austin

;; index / landing page for weblog

;; create clojurescript that calls basic post CRUD

;; clojruescript for advanced post CRUD

;; clojurescript for CRUD on tags

;; clojurescript for CRUD on assets

;; clojurescript for CRUD on mixed content
;;   post & tags
;;   post & assets



;; TODO - UI for weblog

;; landing screen

;; paging

;; post replies
;;   list replies by time
;;   list replies by thread



;; TODO - split out a theme

;; TODO - documentation

;; TODO - [plugin] aauthentication & admin console

;; TODO - [plugin] payment gateway
