(ns random-clojure-function.core
  (:gen-class)
  (:require [org.httpkit.server :as server] ;! httpkit setup addition
            [compojure.core :refer :all]   ;! Compojure setup addition
            [compojure.route :as route]))  ;! Compojure setup addition

;* *****************************************
;* Start Random Function Portion
(def standard-library-functions
  "Fully qualified function names from clojure.core"
  (vals (ns-publics 'clojure.core)))

(def available-namespace
  "Returns a list of all available namespaces"
  (mapcat #(vals (ns-publics %)) (all-ns)))

(def all-public-functions
  "Fully qualified function names from available"
  (mapcat #(vals (ns-publics %)) (all-ns)))

(defn function-list
  "Gets a list of functions from a inputted namespace"
  [namespace]
  (vals (ns-publics namespace)))

(defn random-function
  "Returns a function name and description from the Clojure Standard Library"
  [function-list]
  (let [function-details (meta (rand-nth function-list))]
    (str 
     "<!DOCTYPE html>
<html lang=\"en\">
<head>
    <meta charset=\"UTF-8\">
    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">
    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">
    <title>Clojure Random Function Generator</title>
</head>
<body>"     
     "<h1>Name:</h1>\n"
         "<p>" (function-details :name) "</p>\n" 
         "<h2>Description:</h2>\n" 
         "<p>" (function-details :doc) "</p>\n"
         "<h2>Signature:</h2>\n"
         "<p>" (function-details :arglists) "</p>\n"
         "<h2>Name Space:</h2>\n"
         "<p>" (function-details :ns) "</p>"
     "</body>
</html>"))
  )

(defn output-random-function-string
  "Calls the random-function function from abov and prints the output to the user"
  [& args]
  (if (seq args)
    (println (random-function (mapcat #(function-list (symbol %)) args)))
    (println (random-function all-public-functions))))
;* *****************************************

;* *****************************************
;* Start http-kit Portion and Compojure Portion

(defn fps-handler
  [req]
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body (random-function all-public-functions)})

(defn mail-man []
  "{\"\": \"5 years later...\"}")

(defn mail-handler [req]
  {:status 200
   :headers {"Content-Type" "text/json"}
   :body (mail-man)})

(defn general-handler [req]
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body "All hail General Zod!"})

(defroutes app-routes
  (GET "/" [] fps-handler)
  (POST "/postoffice" [] mail-handler)
  (ANY "/anything-goes" [] general-handler)
  (route/not-found "You Must Be New Here"))

(defn -main
  "App entry point"
  [& args]
  (let [port (Integer/parseInt (or (System/getenv "PORT") "8080"))]
    (server/run-server #'app-routes {:port port})
    (println (str "Running webserver at http:/127.0.0.1:" port "/"))))

