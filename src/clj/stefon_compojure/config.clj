(ns stefon-compojure.config
  (:require [clojure.java.io :as io] ))


(defn get-config-raw []
  (load-string (slurp (io/resource "stefon-compojure.edn"))))

(def get-config (memoize get-config-raw))
