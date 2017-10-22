(ns lice.core
  (:require [net.cgrand.enlive-html :as html]
            [ring.middleware.params :refer [wrap-params]]
            [ring.middleware.json :refer [wrap-json-response]]
            [compojure.core :refer :all]
            [compojure.handler :as handler]
            [compojure.route :as route]))

(defn extract-text [license-response]
  (html/text (first (html/select  license-response [:#license-text]))))

(defn request-license [license-name]
  (html/html-resource
    (java.net.URL. (str "https://choosealicense.com/licenses/" license-name "/"))))

(defn fetch-license [license-name]
  {:body { :text (extract-text (request-license license-name)) }})

(defn exception-handler
  [handler]
  (fn [request]
    (try
      (handler request)
      (catch Exception e
        (println "err" e)
        {:status 400 :body "Whoopsie. That didn't work"}))))

(defroutes app-routes
  (GET "/" [] {:body { :message "Hello" }})
  (GET "/licenses/:name" [name] (fetch-license name))
  (route/not-found "That was not found...."))

(def app
  (-> (handler/api app-routes)
      wrap-json-response
      exception-handler))
