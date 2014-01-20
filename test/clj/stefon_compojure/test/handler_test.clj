(ns stefon-compojure.test.handler-test
  (:require [clojure.test :refer :all]
            [stefon.shell :as shell]
            [stefon-compojure.handler :as ch]
            [ring.mock.request :as r]
            [taoensso.timbre :as timbre]))


(require '[clojure.pprint :refer :all])

(def request-body {:tags []
                   :assets []
                   :modified-date "09/01/2013"
                   :created-date "09/01/2013"
                   :content-type "txt"
                   :title "Latest In Biotech"
                   :content "Lorem ipsum."
                   })

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

  (testing "Retrieve Post"

    (let [x (shell/stop-system)
          x (shell/start-system)
          x (shell/load-plugin 'stefon-compojure.plugin)
          test-date (-> (java.text.SimpleDateFormat. "MM/DD/yyyy") (.parse "09/01/2013"))

          r1 (ch/app (r/request :put "/post" request-body))
          r2 (ch/app (r/request :get "/post" {:id (-> r1 :result :id)}))]

      (is (= (:status r2) 200))
      (is (= (-> r2 :result :title) "Latest In Biotech"))
      (is (= (-> r2 :result :content) "Lorem ipsum."))
      (is (= (-> r2 :result :content-type) "txt"))
      (is (= (-> r2 :result :created-date) test-date))
      (is (= (-> r2 :result :modified-date) test-date))
      (is (= (-> r2 :result :assets) []))
      (is (= (-> r2 :result :tags) []))))

  (testing "Update Post"

    (let [x (shell/stop-system)
          x (shell/start-system)
          x (shell/load-plugin 'stefon-compojure.plugin)

          r1 (ch/app (r/request :put "/post" request-body))
          r2 (ch/app (r/request :get "/post" {:id (-> r1 :result :id)}))]

      (is (= (:status r1) 200))
      (is (= (:status r2) 200))

      (let [new-content "New content"
            u1 (ch/app (r/request :post "/post" {:id (-> r1 :result :id) :content new-content}))
            u2 (ch/app (r/request :get "/post" {:id (-> r1 :result :id)}))]

        (is (= (:status u2) 200))
        (is (= (-> u2 :result :content) new-content)))))

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
