(defproject stefon-compojure "0.1.0-SNAPSHOT"
  :description "Compujure plugin for the Stefon Weblog system"
  :url "https://github.com/stefonweblog/stefon-compojure"

  :dependencies [[org.clojure/clojure "1.5.1"]
                 [org.clojure/clojurescript "0.0-2138"]
                 [compojure "1.1.5"]

                 ;; TODO - deleteme; checkout lib dependencies
                 [org.clojure/core.async "0.1.267.0-0d7780-alpha"]
                 [prismatic/schema "0.1.3"]
                 [cljs-uuid "0.0.4"]]
  :plugins [[lein-ring "0.8.5"]
            [ring/ring-jetty-adapter "1.2.0"]]
  :ring {:handler stefon-compojure.handler/app}
  :profiles {:dev {:dependencies [[ring-mock "0.1.5"]
                                  [midje "1.5.1"] ]
                   :plugins [[lein-cljsbuild "1.0.1"]
                             [com.cemerick/austin "0.1.3"]]}}

  :cljsbuild {
              :builds [{:source-paths ["public/templ/cljs"]
                        :compiler {
                                   :output-to "public/include/js/stefon-compojure.js"
                                   ;; :optimizations :whitespace
                                   :pretty-print true}}]}

  :eval-in-leiningen true)
