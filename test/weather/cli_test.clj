(ns weather.cli-test
  (:require [clojure.test :refer :all]
            [weather.cli :refer :all]))


(deftest parse-args-test
  (testing "parse-args properly parses arguments"
    (is (= [:city ["orlando"]]
          (parse-args ["-c" "orlando"])))
    (is (= [:location ["20" "20"]]
          (parse-args ["--location" "20" "20"])))
    (is (= [:id ["12345"]]
          (parse-args ["-i" "12345"])))
    (is )))