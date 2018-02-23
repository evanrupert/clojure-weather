(ns weather.core
  (:gen-class))

(require '[weather.cli :as cli])
(require '[weather.http :as http])
(require '[clojure.data.json :as json])

(defn parse-json
  [cmd result]
  [ cmd
    (json/read-str
      (:body result)
      :key-fn keyword)])

(defn kelvin->fahrenheit
  [kelvin]
  (- (* kelvin 9/5) 459.67))

(defn extract-data
  [cmd data]
  (case cmd
    "description" (-> :weather
                     :description)
    "temperature" (-> :main
                     :temp
                     kelvin->fahrenheit)
    "wind-speed"  (-> :wind
                     :speed)
    "humidity"    (-> :main
                     :humidity)))

(defn -main
  [& args]
  (if (not= (count args) 0)
    (-> args
        cli/parse-args
        cli/handle-cli-errors
        http/request-data
        parse-json
        extract-data
        println)
    (println "No arguments passed")))
