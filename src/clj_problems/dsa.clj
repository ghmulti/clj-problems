(ns clj-problems.dsa
  (:refer-clojure))

(defn get-inversions [{inv :inversions}] (if inv inv 0))

(defn- merge-cols [col1 col2]
  ;(println "Merging" col1 col2)
  (loop [[fl & rl :as l] col1
         [fr & rr :as r] col2
         un (with-meta [] {:inversions (+ (-> col1 meta get-inversions) (-> col2 meta get-inversions))})]
    ;(println "Iterate " fl fr un)
    (if (or fl fr)
      (if (or (and fl fr (< fl fr)) (not fr))
        (recur rl r (conj un fl))
        (recur l rr (-> (conj un fr)
                        (with-meta {:inversions (-> un meta get-inversions (+ (count l)))} ))))
      un)))

(defn merge-sort [col]
  (let [sz (count col)]
    (if (= 1 sz)
      col
      (merge-cols (merge-sort (take (/ sz 2) col)) (merge-sort (drop (/ sz 2) col))))))


(defn number-of-inversions [col]
  (-> (merge-sort col)
      meta
      get-inversions))