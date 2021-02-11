(ns random-clojure-function.core-test
  (:require [clojure.test :refer [deftest is testing]]
            [random-clojure-function.core :as SUT]))

(deftest random-function-test
  (testing "Show random function from Clojure standard library"
    ;* Checks if the standard-library-functions contains entries 
    (is (seq SUT/standard-library-functions))
    ;* Checks if the -main function returns a string
    (is (string? (SUT/random-function SUT/standard-library-functions)))))