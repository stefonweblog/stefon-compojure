(defproject stefon-compojure "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"

  :documentation
  {:files {
           "resources/doc/handler"
           {:input "test/stefon_compojure/test/handler.clj"
            :title "Handler Documentation"
            :sub-title "Describe the route handling functions in this plugin"
            :author "Timothy Washington"
            :email  "twashing@gmail.com"}

           "resources/doc/plugin"
           {:input "test/stefon_compojure/test/plugin.clj"
            :title "Plugin Documentation"
            :sub-title "Outlines the plugin lifecycle"
            :author "Timothy Washington"
            :email  "twashing@gmail.com"}}}


  :dependencies [[org.clojure/clojure "1.5.1"]
                 [compojure "1.1.5"]]
  :plugins [[lein-ring "0.8.5"]
            [ring/ring-jetty-adapter "1.2.0"]]
  :ring {:handler stefon-compojure.handler/app}
  :profiles {:dev {:dependencies [[ring-mock "0.1.5"]
                                  [midje "1.5.1"]]}}

  :eval-in-leiningen true)
