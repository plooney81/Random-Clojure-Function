(ns random-clojure-function.core
  (:gen-class))

(def standard-library-functions
  "Fully qualified function names from clojure.core"
  (vals (ns-publics 'clojure.core)))

(defn random-function
  "Returns a function name and description from the Clojure Standard Library"
  [function-list]
  (let [function-details (meta (rand-nth function-list))]
    (str "\n\nName:\n  "
         (function-details :name) 
         "\n\nDescription:\n  " 
         (function-details :doc) "\n\n"
         "\n\nSignature:\n "
         (function-details :arglists)))
  )

(defn -main
  "Calls the random-function function from above and prints the output to the user"
  [& args]
  (println (random-function standard-library-functions))
  )

