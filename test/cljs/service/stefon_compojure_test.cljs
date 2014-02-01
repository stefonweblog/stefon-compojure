(ns stefon-compojure-test
  (:require-macros [cemerick.cljs.test
                    :refer (is deftest with-test run-tests testing test-var)])
  (:require [cemerick.cljs.test :as t]
            [stefon-compojure.service :as cs]
            [ajax.core :refer [get-default-format
                               normalize-method process-inputs
                               edn-format json-format raw-format
                               ajax-request]]
            [clojure.browser.repl :as repl]))


(def request-body {:title "Latest In Biotech"
                   :content "Lorem ipsum"
                   :content-type "txt"
                   :created-date "09/01/2013"
                   :modified-date "09/01/2013"
                   :assets []
                   :tags []})

(deftest service
  (is (= (cs/fubar) "fubar")))


(deftest create-post
  (cs/create-post request-body))
