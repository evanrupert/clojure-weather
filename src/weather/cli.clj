(ns weather.cli
  (:gen-class))

(require '[clojure.tools.cli :refer [parse-opts]])

(def cli-specs [["-l" "--location" :default false]
                ["-c" "--city"     :default false]
                ["-i" "--id"       :default false]
                ["-z" "--zip-code" :default false] 
                ["-h" "--help"]])

(defn parse-args
  [args]
  (let [parsed (parse-opts args cli-specs)
        opts (:options parsed)
        arguments (:arguments parsed)]
    [(reduce (fn [acc [key val]] 
              (if val
                key
                acc)) 
      nil 
      opts)
     arguments]))

(def help-msg 
  "Usage:\n
  -l | --location   Get weather at given latitude and longitude\n
  -c | --city       Get weather at given city name\n
  -i | --id         Get weather at given city id\n
  -z | --zip-code   Get weather at given zip code\n
  -h | --help       Display this help message\n")

(defn raise-error
  [err]
  (println err)
  (println help-msg)
  (System/exit 0))

(defn handle-cli-errors
  [parsed]
  (let [[cmd args] parsed]
    (case cmd
      :location 
        (if (= (count args) 2)
          parsed
          (raise-error "Improper argument number, -l should be given two numbers"))
      :help 
        (do
          (println help-msg)
          (System/exit 0))
      nil
        (raise-error "Valid specifier not given")
      "default"
        parsed)))