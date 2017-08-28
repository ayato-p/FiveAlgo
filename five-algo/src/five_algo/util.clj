(ns five-algo.util)

(defn parse-int [s]
  (Integer. (re-find  #"\d+" s )))