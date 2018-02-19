(ns weather.core
  (:gen-class))

(require '[weather.cli :as cli])
(require '[weather.http :as http])
(require '[clojure.data.json :as json])

(defn parse-json
  [result]
  (json/read-str 
    (:body result)
    :key-fn keyword))

(defn get-temp
  [data]
  (-> data
      :main
      :temp))

(defn kelvin->fahrenheit
  [kelvin]
  (- (* kelvin 9/5) 459.67))

(defn -main
  [& args]
  (if (not= (count args) 0)
    (-> args
        cli/parse-args
        cli-handle-cli-errors
        http/request-data
        parse-json
        get-temp
        kelvin->fahrenheit
        println)
    (println "No arguments passed")))
