(ns stefon-compojure-test
  (:require [cemerick.cljs.test :as t]
            [stefon-compojure.service :as cs]))

#_(deftest somewhat-less-wat
  (is (= "{}[]" (+ {} []))))

#_(deftest javascript-allows-div0
  (is (= js/Infinity (/ 1 0) (/ (int 1) (int 0)))))

#_(deftest service
  (is (= (s/fubar) "fubar")))
