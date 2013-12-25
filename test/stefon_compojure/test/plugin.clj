(ns stefon-compojure.test.plugin
  (:require [clojure.test :refer :all]
            [clojure.core.async :as async]
            [ring.mock.request :refer :all]
            [midje.sweet :refer :all]

            [stefon-compojure.plugin :as pluginC]
            [stefon.shell :as shell]
            [stefon.shell.kernel :as kernel]
            [stefon.shell.plugin :as plugin]))


#_(deftest plugin-stefon

  (testing "plugging into stefon plugin framework"

    (shell/stop-system)
    (shell/start-system)
    (shell/load-plugin 'stefon-compojure.plugin)
    (is (not (empty? @(pluginC/get-plugin-state))))
    (is (= (sort '(:id :sendfn :recievefn :channel))
           (sort (keys @(pluginC/get-plugin-state)))))))


#_(deftest basic-crud

  (testing "create"

    (let [x (shell/stop-system)
          x (shell/start-system)
          x (shell/load-plugin 'stefon-compojure.plugin)

          sendfn (:sendfn @(pluginC/get-plugin-state))
          channel (:channel @(pluginC/get-plugin-state))
          id (:id @(pluginC/get-plugin-state))]

      (def tchan (async/chan))

      (async/go (def msg (async/<! tchan))
          (println "4 .. " msg))
      (println "3 .. " (async/go (async/>! tchan {:fu :bar})))

      #_(async/go (let [msg (async/<! channel)]
                  (println ">> testing 123 > " msg)))

      #_(async/go (async/>! channel {:id "asdf"
                                   :message {:stefon.domain.schema {:parameters nil}}}))

      ;; ... TODO - easy send / receive workflow
      #_(sendfn {:id id
               :message {:stefon.domain.schema {:parameters nil}}}))))

#_(deftest basic-routes
  (testing "route:create"))


;; TODO - hookup austin
;; index / landing page for weblog
;; create clojurescript that calls basic post CRUD
;; clojurescript for advanced post CRUD
;; clojurescript for CRUD on tags
;; clojurescript for CRUD on assets
;; clojurescript for CRUD on mixed content
;;   post & tags
;;   post & assets


;; TODO - UI for weblog
;; landing screen
;; paging
;; post replies
;;   list replies by time
;;   list replies by thread


;; TODO - split out a theme
;; TODO - documentation
;; TODO - [plugin] aauthentication & admin console
;; TODO - [plugin] payment gateway
