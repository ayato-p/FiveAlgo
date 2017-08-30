(ns five-algo.test
  (:require [five-algo.clock-degree :as cd]))

(defn test-clock-parse-str
  []
  (= "10 hour 10 minute" (let [str (cd/clock-parse-str "10:10")]
                            (do (println str)
                                str)
                            ) )
  )
(defn test-calc-clock-degree
  []
  (= 115 (let [str (cd/calc-clock-degree "10:10")]
           (do (println str)
               str)
           ))
  )

(defn start-test
  []
  (do
      (print "test-make-clock:")
      (if (true? (test-clock-parse-str))
        (println "collect")
        (println "failed"))

      (print "test-calc-clock-degree:")
      (if (true? (test-calc-clock-degree))
        (println "collect")
        (println "failed"))
     ))
