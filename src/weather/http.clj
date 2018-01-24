(ns weather.http
  (:gen-class))

(require '[clj-http.client :as client])
(require '[clojure.data.json :as json])

(def base-url "api.openweathermap.org/data/2.5/weather?")

(defn request-data
  [parsed]
  (let [[cmd args] parsed]
    (case cmd
      :location
        (let [[lat lon] args]
          (client/get
            (str base-url "lat=" lat "lon=" lon)))
      :city
        (let [[name] args]
          (client/get
            (str base-url "q=" name)))
      :id
        (let [[id] args]
          (client/get
            (str base-url "id=" id)))
      :zip-code
        (let [[code] args]
          (client/get
            (str base-url "zip=" code)))
      "default"
        (do
          (println "Failed to parse arguments")
          (System/exit 0)))))
    
;; TODO: Finish
(defn get-temp
  [data]
  (-> data
      :main
      :temp))
