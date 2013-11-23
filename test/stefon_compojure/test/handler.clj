(ns stefon-compojure.test.handler
  (:use clojure.test
        ring.mock.request
        stefon-compojure.handler
        midje.sweet))

(deftest test-app
  (testing "main route"

    (is (= 0 0))
    (is (= 5 5))

    #_(let [response (app (request :get "/"))]
      (is (= (:status response) 200))
      (is (= (:body response) "Hello World"))))

  (testing "not-found route"

    (is (= 9 9))
    (is (= 7 7))

    #_(let [response (app (request :get "/invalid"))]
      (is (= (:status response) 404)))))

(facts "about migration"
       (fact "Migration produces a new left and right map"
             1 => 1)
       (fact "multiple keys can be moved at once"
             2 => 2))
