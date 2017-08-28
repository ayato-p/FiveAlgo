(ns five-algo.test
  (:require [five-algo.clock-degree :as cd]))

(defn test-short-hand-degree
  []
  (= 10 (cd/short-hand-degree "10")))

(defn test-clock-parse-str
  []
  (= "10 hour 10 minute" (let [str (cd/clock-parse-str "10:10")]
                            (do (println str)
                                str)
                            ) )
  )

(defn start-test
  []
  (do (print "test-short-hand-degree:")
      (if (true? (test-short-hand-degree))
        (println "collect")
        (println "failed"))

      (print "test-make-clock:")
      (if (true? (test-clock-parse-str))
        (println "collect")
        (println "failed"))
     ))
