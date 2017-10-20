(defproject lice "0.1.0-SNAPSHOT"
  :main lice.core
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :plugins [[lein-ring "0.12.1"]]
  :dependencies [[org.clojure/clojure "1.8.0"]]
  :ring {:handler lice.core/handler :port 8000})
