(ns stefon-compojure.test.plugin

  (:use clojure.test
        ring.mock.request
        midje.sweet)

  (:require [stefon-compojure.plugin :as pluginC]
            [stefon.shell :as shell]))



(defn bootstrap-stefon
  "Startup stefon & plugin (just memory-mode)"
  []

  (pluginC/plugin {:system-started? shell/system-started?
                   :start-system shell/start-system
                   :attach-plugin shell/attach-plugin}))

(deftest basic-crud

  (testing "main route"

    (let [result (bootstrap-stefon)
          sendfn (:sendfn result)
          id (:id result)
          heartbeat (sendfn {:id id
                             :message {:stefon.domain.schema {:parameters nil}}})]

      (println heartbeat)

      (is (= 1 1))
      (is (= 2 2)))))


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


(deftest test-app
  (testing "main route"
    (is (= 1 1)))

  (testing "not-found route"
    (is (= 2 2))
    (is (= 0 3))))

(facts "about migration"
       (fact "Migration produces a new left and right map"
             1 => 1)
       (fact "multiple keys can be moved at once"
             2 => 2))
