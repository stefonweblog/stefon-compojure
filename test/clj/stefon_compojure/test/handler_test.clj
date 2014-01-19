(ns stefon-compojure.test.handler-test
  (:require [clojure.test :refer :all]
            [stefon.shell :as shell]
            [stefon-compojure.handler :as ch]
            [ring.mock.request :as r]
            [taoensso.timbre :as timbre]))


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
  (testing "Create Post"

    (let [x (shell/stop-system)
          x (shell/start-system)
          x (shell/load-plugin 'stefon-compojure.plugin)
          test-date (-> (java.text.SimpleDateFormat. "MM/DD/yyyy") (.parse "09/01/2013"))

          response (ch/app (r/request :put "/post" request-body))]

      (is (= stefon.domain.Post (type (:result response))))
      (is (= (:status response) 200))

      (is (= (-> response :result :title) "Latest In Biotech"))
      (is (= (-> response :result :content) "Lorem ipsum."))
      (is (= (-> response :result :content-type) "txt"))
      (is (= (-> response :result :created-date) test-date))
      (is (= (-> response :result :modified-date) test-date))
      (is (= (-> response :result :assets) []))
      (is (= (-> response :result :tags) []))))

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
