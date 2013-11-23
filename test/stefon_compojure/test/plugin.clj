(ns stefon-compojure.test.plugin
  (:use clojure.test
        ring.mock.request
        stefon-compojure.plugin
        midje.sweet))



;; TODO - hookup midje auto-test and auto-doc

;; startup stefon & plugin (just memory-mode)

;; basic CRUD for posts



;; TODO - hookup austin

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
    (let [response (app (request :get "/"))]
      (is (= (:status response) 200))
      (is (= (:body response) "Hello World"))))

  (testing "not-found route"
    (let [response (app (request :get "/invalid"))]
      (is (= (:status response) 404)))))

(facts "about migration"
       (fact "Migration produces a new left and right map"
             1 => 1)
       (fact "multiple keys can be moved at once"
             2 => 2))
