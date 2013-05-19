(ns clojure-course-task02.core
  (:gen-class)
  (:import java.io.File))

(defn match-pattern? [pattern value]
  (-> (re-pattern pattern)
      (re-find value)))

(defn find-files [file-name path]
  "Searching for a file using file-name as a regexp."
  (->> (clojure.java.io/file path)
       (file-seq)
       (map #(.getName %))
       (filter #(match-pattern? file-name %))))

(defn usage []
  (println "Usage: $ run.sh file_name path"))

(defn -main [file-name path]
  (if (or (nil? file-name)
          (nil? path))
    (usage)
    (do
      (println "Searching for " file-name " in " path "...")
      (dorun (map println (find-files file-name path))))))
