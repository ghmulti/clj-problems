(ns clj-problems.core
  (:gen-class))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))



;; 166

(defn problem166 [mfn & args]
  (if (apply mfn args) :lt (if (apply mfn (reverse args)) :gt :eq)))

(defn run-166-tests [mfn]
  (and
    (= :gt (mfn < 5 1))
    (= :eq (mfn (fn [x y] (< (count x) (count y))) "pear" "plum"))
    (= :lt (mfn (fn [x y] (< (mod x 5) (mod y 5))) 21 3))
    (= :gt (mfn > 0 2))))

;;

;; 168

(defn build-lazy-row [f l r]
  (lazy-seq (cons (f l r) (build-lazy-row f l (inc r)))))

(defn build-lazy-columns [row-builder index]
  (lazy-seq (cons (row-builder index) (build-lazy-columns row-builder (inc index)))))

(defn problem168
  ([f] (build-lazy-columns #(build-lazy-row f % 0) 0))
  ([f m n] )
  ([f m n s t] ))

(defn run-168-tests [mfn]
  (= (take 5 (map #(take 6 %) (mfn str)))
     [["00" "01" "02" "03" "04" "05"]
      ["10" "11" "12" "13" "14" "15"]
      ["20" "21" "22" "23" "24" "25"]
      ["30" "31" "32" "33" "34" "35"]
      ["40" "41" "42" "43" "44" "45"]]))
;;