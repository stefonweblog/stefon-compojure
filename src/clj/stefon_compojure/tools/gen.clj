(ns stefon-compojure.tools.gen
  (:require [hiccup.page :as hpage]))


(defn gen-html []

  ;; i. read all files
  ;; ii. from an input directory
  (def idx (slurp "src/templ/document/index.edn"))
  (def idx-form (read-string idx))

  #_(require '[hiccup.core :as hiccup])
  #_(hiccup/html idx-form)

  (def result-page (hpage/html5 {} idx-form))

  ;; iii. spit out result page(s)
  ;; iv. to a configured location
  (spit "public/index.html" result-page))
