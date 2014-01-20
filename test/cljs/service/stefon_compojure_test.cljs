(ns stefon-compojure-test
  (:require-macros [cemerick.cljs.test
                    :refer (is deftest with-test run-tests testing test-var)])
  (:require [cemerick.cljs.test :as t]
            [stefon-compojure.service :as cs]))


(deftest service
  (is (= (cs/fubar) "fubar")))
