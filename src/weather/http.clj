(ns weather.http
  (:gen-class))

(require '[clj-http.client :as client])


(def api-key (or (System/getenv "OPENWEATHER_API_KEY") 
               (do
                 (println "Api Key not configured")
                 (System/exit 1))))
(def base-url (str "http://api.openweathermap.org/data/2.5/weather?APPID=" api-key "&"))


(defn request-data
  [parsed]
  (let [[cmd args] parsed]
    (case cmd
      :location
        (let [[lat lon] args]
          (client/get
            (str base-url "lat=" lat "&lon=" lon)))
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
