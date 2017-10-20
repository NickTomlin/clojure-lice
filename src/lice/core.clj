(ns lice.core)

(def names ["Evan", "Josh", "Hurley (we would never call him 'Sean')", "Paul", "Rohit"])

(defn handler [request]
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body (str "Hello " (rand-nth names))})
