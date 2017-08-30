(ns five-algo.clock-degree
  (:require [five-algo.util :as utl])
  )

(use '[clojure.string :only (join split)])

(defprotocol ClockProtocol
  "a clock protocol"
  (hour [this])
  (minute [this])
  (long-hand-degree [this])
  (short-hand-degree [this])
  )



(defrecord Clock [hour min] ClockProtocol
  (hour [this] hour)
  (minute [this] min)
  (long-hand-degree [this] (* min (/ 360 60)))
  (short-hand-degree [this]
    (+ (*
         (mod hour 12)
         (/ 360 12) )
       (*
         min
         (/ (/ 360 12) 60))
       ))
  )

(defn make-clock
  [s]
  (let [col (split s #":")]
    (let
      [hour (utl/parse-int (first col))
       minute (utl/parse-int (second col))]
      (Clock. hour minute)))
  )

(defn abs [n] (max n (- n)))

(defn calc-clock-degree
  [s]
  (let [clock (make-clock s)]
    (let [degree (abs (- (long-hand-degree clock) (short-hand-degree clock)))]
      (int (if (> degree 180)
             (- 360 degree)
             degree))
      )
    )
  )

(defn clock-parse-str
  [s]
  (let [clock (make-clock s)]
    (str (hour clock) " hour " (minute clock) " minute"))
  )

