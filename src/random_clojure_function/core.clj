(ns random-clojure-function.core
  (:gen-class))

(def standard-library-functions
  "Fully qualified function names from clojure.core"
  (vals (ns-publics 'clojure.core)))

(def available-namespace
  "Returns a list of all available namespaces"
  (mapcat #(vals (ns-publics %)) (all-ns)))

(defn function-list
  "Gets a list of functions from a inputted namespace"
  [namespace]
  (vals (ns-publics namespace)))

(defn random-function
  "Returns a function name and description from the Clojure Standard Library"
  [function-list]
  (let [function-details (meta (rand-nth function-list))]
    (str "\n\nName:\n  "
         (function-details :name) 
         "\n\nDescription:\n  " 
         (function-details :doc)
         "\n\nSignature:\n "
         (function-details :arglists) "\n\n"))
  )

(defn -main
  "Calls the random-function function from above and prints the output to the user"
  [& args]
  (if (seq args)
    (println (random-function (mapcat #(function-list (symbol %)) args)))
    (println (random-function standard-library-functions))))

