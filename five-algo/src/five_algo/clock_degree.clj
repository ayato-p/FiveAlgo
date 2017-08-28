(ns five-algo.clock-degree
  (:require [five-algo.util :as utl])
  )

(use '[clojure.string :only (join split)])

(defprotocol Clock
  "a clock protocol"
  (long-hand [this])
  (short-hand [this])
  )

(defrecord AClock [hour minute] Clock
  (long-hand [this] minute)
  (short-hand [this] hour)
  )

(defn make-clock
  [s]
  (let [col (split s #":")]
    (let
      [hour (first col)
       minute (second col)]
      (AClock. hour minute)))
  )

(defn clock-parse-str
  [s]
  (let [clock (make-clock s)]
    (str (short-hand clock) " hour " (long-hand clock) " minute"))
  )

; (let [clock (AClock. hour minute)]
;     (str (short-hand clock) " hour " (long-hand clock) " minute"))

(defn short-hand-degree
  [s]
  (let [x (utl/parse-int s)]
    x ))

(defn long-hand-degree
  [s s2]
  (let [x (utl/parse-int s)
        y (utl/parse-int s2)]
    x + y ))