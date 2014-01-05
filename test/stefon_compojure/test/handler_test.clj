(ns stefon-compojure.test.handler-test
  (:require [clojure.test :refer :all]
            [stefon-compojure.handler :refer :all]
            [ring.mock.request :as r]))

(deftest test-app

  (testing "main route"

    (let [response (app (r/request :get "/helloworld"))]
      (is (= (:status response) 200))
      (is (= (:body response) "Hello World"))))

  (testing "not-found route"

    (let [response (app (r/request :get "/invalid"))]
      (is (= (:status response) 404)))))
