(require '[clojure.repl :refer :all])
(require '[clojure.pprint :refer :all])
(require '[missing-utils.core :refer :all])


#_(let [ch (chan)]
  (go (while true
        (let [v (<! ch)]
          (println "Read: " v))))
  (go (>! ch "hi")
      (<! (timeout 5000))
      (>! ch "there")))


#_(let [ch (chan)]
  (go-loop [v (<! ch)]
           (println "Read: " v)
           (recur (<! ch)))
  (go (>! ch "hi")
      (<! (timeout 5000))
      (>! ch "there")))


#_(require '[clojure.core.async :as async :refer :all])
#_(def ch (chan))
#_(go-loop [v (<! ch)]
         (println "Read: " v)
         (recur (<! ch)))
#_(go (>! ch "hi")
    (<! (timeout 5000))
    (>! ch "there"))
