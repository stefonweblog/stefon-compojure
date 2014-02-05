(defproject stefon-compojure "0.1.0-SNAPSHOT"
  :description "Compujure plugin for the Stefon Weblog system"
  :url "https://github.com/stefonweblog/stefon-compojure"
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [org.clojure/clojurescript "0.0-2138"]
                 [compojure "1.1.6"]
                 [ring/ring-jetty-adapter "1.2.1"]
                 [com.taoensso/timbre "3.0.0-RC4"]
                 [cljs-ajax "0.2.3"]
                 [hiccup "1.0.5"]
                 [garden "1.1.5"]

                 ;; TODO - deleteme; checkout lib dependencies
                 [org.clojure/core.async "0.1.267.0-0d7780-alpha"]
                 [prismatic/schema "0.1.3"]
                 [cljs-uuid "0.0.4"]


                 ;; TODO - soon to be needed libs; use 'checkouts' in the interim
                 #_[stefon "0.1.1-SNAPSHOT"]
                 #_[stefon-webui-common "0.1.0-SNAPSHOT"]
                 #_[responsive-baseline "0.1.0-SNAPSHOT"]]

  :plugins [[lein-ring "0.8.5"]
            [ring/ring-jetty-adapter "1.2.0"]
            [com.cemerick/clojurescript.test "0.2.1"]]

  :profiles {:dev {:dependencies [[com.cemerick/clojurescript.test "0.2.1"]
                                  [ring-mock "0.1.5"]
                                  [midje "1.5.1"]
                                  [alembic "0.2.0"]]
                   :plugins [[lein-cljsbuild "1.0.1"]
                             [com.cemerick/austin "0.1.4-SNAPSHOT"]]}}

  :source-paths ["src/clj" "src/cljs"]
  :test-paths ["test/clj" "test/cljs"]

  :ring {:handler stefon-compojure.handler/app}
  :cljsbuild {:builds [{:source-paths ["src/cljs" "test/cljs"]
                        :compiler {:output-dir "public/include/js"
                                   :output-to "public/include/js/stefon_compojure.js"
                                   :optimizations :simple
                                   :pretty-print true}}]
              :test-commands {"unit-tests" ["phantomjs" :runner
                                            "window.literal_js_was_evaluated=true"
                                            "public/include/js/stefon_compojure.js"]}})
