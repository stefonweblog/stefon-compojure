(ns stefon-compojure-test
  (:require [cemerick.cljs.test :as t]
            [stefon-compojure :as s]))

(deftest somewhat-less-wat
  (is (= "{}[]" (+ {} []))))

(deftest javascript-allows-div0
  (is (= js/Infinity (/ 1 0) (/ (int 1) (int 0)))))

(deftest service
  (is (= (s/fubar) "fubar")))

(deftest thing
  (is false))
