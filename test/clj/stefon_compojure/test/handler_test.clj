(ns stefon-compojure.test.handler-test
  (:require [clojure.test :refer :all]
            [stefon-compojure.handler :as ch]
            [ring.mock.request :as r]))


(def request-body {:title "Latest In Biotech"
                   :content "Lorem ipsum."
                   :content-type "txt"
                   :created-date "09/01/2013"
                   :modified-date "09/01/2013"
                   :assets []
                   :tags []})

(deftest test-app

  (testing "main route"

    (let [response (ch/app (r/request :get "/helloworld"))]
      (is (= (:status response) 200))
      (is (= (:body response) "Hello World"))))

  (testing "not-found route"

    (let [response (ch/app (r/request :get "/invalid"))]
      (is (= (:status response) 404))))


  ;; ===> POST
  #_(testing "Create Post"

    (let [response (ch/app (r/request :put "/post" request-body))]
      (is (= (:status response) 200))
      (is (= (:body response) request-body))))

  #_(testing "Retrieve Post"

    (let [r1 (ch/app (r/request :put "/post" request-body))
          r2 (ch/app (r/request :get "/post" {:id (:id r1)}))]
      (is (= (:status r1) 200))
      (is (= (:body r2) (:body r1)))))

  #_(testing "Update Post"

    (let [r1 (ch/app (r/request :put "/post" request-body))
          r2 (ch/app (r/request :get "/post" {:id (:id r1)}))]

      (is (= (:status r1) 200))
      (is (= (:body r2) (:body r1)))

      (let [new-content "New content"
            u1 (ch/app (r/request :update "/post" {:content new-content}))
            u2 (ch/app (r/request :get "/post" {:id (-> u1 :body :id)}))]

        (is (= (:status u2) 200))
        (is (= (-> u2 :body :content) new-content)))))

  #_(testing "Delete Post"

    (let [r1 (ch/app (r/request :put "/post" request-body))
          r2 (ch/app (r/request :get "/post" {:id (:id r1)}))]

      (is (= (:status r1) 200))
      (is (= (:body r2) (:body r1)))

      (let [u1 (ch/app (r/request :delete "/post" {:id (-> r2 :body :id)}))
            u2 (ch/app (r/request :get "/post" {:id (-> r2 :body :id)}))]

        (is (= (:status u2) 200))
        (is (= (-> u2 :body) nil)))))

  #_(testing "List Posts"

    (let [r1 (ch/app (r/request :put "/post" request-body))
          r2 (ch/app (r/request :put "/post" request-body))
          r3 (ch/app (r/request :get "/posts"))]
      (is (= (:status r1) 200))
      #_(is (= (:body r2) (:body r1))))))
