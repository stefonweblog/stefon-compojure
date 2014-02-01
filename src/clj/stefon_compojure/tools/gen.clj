(ns stefon-compojure.tools.gen
  (:require [hiccup.page :as hpage]
            [garden.core :as garden]))

(defn gen-html []

  ;; i. read all files
  ;; ii. from an input directory
  (def idx (slurp "src/templ/document/index.edn"))
  (def idx-form (read-string idx))

  (def result-page (hpage/html5 {} idx-form))

  ;; iii. spit out result page(s)
  ;; iv. to a configured location
  (spit "public/index.html" result-page))


(defn gen-css []

  (def cdx (slurp "src/templ/style/index.edn"))
  (def cdx-form (read-string cdx))

  (def result-css (garden/css cdx-form))
  (spit "public/include/css/index.css" result-css))
