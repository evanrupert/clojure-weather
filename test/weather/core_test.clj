(ns weather.core-test
  (:require [clojure.test :refer :all]
            [weather.core :refer :all]))

;; TODO: Test exceptions for parse-json

(def test-response {:request-time 361, :repeatable? false, :protocol-version {:name "HTTP", :major 1, :minor 1}, :streaming? true, :chunked? false, :reason-phrase "OK", :headers {"Server" "openresty", "Date" "Wed, 24 Jan 2018 04:15:24 GMT", "Content-Type" "application/json; charset=utf-8", "Content-Length" "444", "Connection" "close", "X-Cache-Key" "/data/2.5/weather?APPID=74e8cbce503233f5a167f2c7ec502e12&q=orlando", "Access-Control-Allow-Origin" "*", "Access-Control-Allow-Credentials" "true", "Access-Control-Allow-Methods" "GET, POST"}, :orig-content-encoding nil, :status 200, :length 444, :body "{\"coord\":{\"lon\":-81.38,\"lat\":28.54},\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"clear sky\",\"icon\":\"01n\"}],\"base\":\"stations\",\"main\":{\"temp\":291.29,\"pressure\":1019,\"humidity\":82,\"temp_min\":290.15,\"temp_max\":293.15},\"visibility\":16093,\"wind\":{\"speed\":1.5,\"deg\":230},\"clouds\":{\"all\":1},\"dt\":1516762680,\"sys\":{\"type\":1,\"id\":703,\"message\":0.0047,\"country\":\"US\",\"sunrise\":1516796219,\"sunset\":1516834720},\"id\":4167147,\"name\":\"Orlando\",\"cod\":200}", :trace-redirects []})

(def parse-result {:coord {:lon -81.38, :lat 28.54}, :cod 200, :name "Orlando", :dt 1516762680, :wind {:speed 1.5, :deg 230}, :id 4167147, :weather [{:id 800, :main "Clear", :description "clear sky", :icon "01n"}], :clouds {:all 1}, :sys {:type 1, :id 703, :message 0.0047, :country "US", :sunrise 1516796219, :sunset 1516834720}, :base "stations", :main {:temp 291.29, :pressure 1019, :humidity 82, :temp_min 290.15, :temp_max 293.15}, :visibility 16093} )

(deftest parse-json-test
  (testing "parse-json properly parses http response"
    (is (= parse-result (parse-json test-response)))))


(deftest get-temp-test
  (testing "get-temp returns the proper value from a map"
    (is (= 291.29 (get-temp parse-result)))))

(deftest kelvin->fahrenheit-test
  (testing "kelvin->fahrenheit test"
    (is (= -100 (Math/round (kelvin->fahrenheit 200))))))