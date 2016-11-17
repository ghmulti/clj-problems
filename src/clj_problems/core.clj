(ns clj-problems.core
  (:gen-class))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))



;; 166

(defn problem166 [mfn & args]
  (if (apply mfn args) :lt (if (apply mfn (reverse args)) :gt :eq)))

(defn run-166-tests []
  [
   (= :gt (problem166 < 5 1))
   (= :eq (problem166 (fn [x y] (< (count x) (count y))) "pear" "plum"))
   (= :lt (problem166 (fn [x y] (< (mod x 5) (mod y 5))) 21 3))
   (= :gt (problem166 > 0 2))])

;;

;; 168

(defn build-lazy-row [f l r]
  (lazy-seq (cons (f l r) (build-lazy-row f l (inc r)))))

(defn build-lazy-columns [row-builder index]
  (lazy-seq (cons (row-builder index) (build-lazy-columns row-builder (inc index)))))

(defn problem168
  ([f] (build-lazy-columns #(build-lazy-row f % 0) 0))
  ([f m n] (build-lazy-columns #(build-lazy-row f % n) m))
  ([f m n s t] (take s (map #(take t %) (problem168 f m n)))))

;(def result-168-fn
;  (let [build-lazy-row (fn build-lazy-row [f l r]
;                         (lazy-seq (cons (f l r) (build-lazy-row f l (inc r)))))
;        build-lazy-columns (fn build-lazy-columns [row-builder index]
;                             (lazy-seq (cons (row-builder index) (build-lazy-columns row-builder (inc index)))))]
;    (fn problem168
;      ([f] (build-lazy-columns #(build-lazy-row f % 0) 0))
;      ([f m n] (build-lazy-columns #(build-lazy-row f % n) m))
;      ([f m n s t] (take s (map #(take t %) (problem168 f m n)))))))

(defn run-168-tests []
  [
   (= (take 5 (map #(take 6 %) (problem168 str)))
      [["00" "01" "02" "03" "04" "05"]
       ["10" "11" "12" "13" "14" "15"]
       ["20" "21" "22" "23" "24" "25"]
       ["30" "31" "32" "33" "34" "35"]
       ["40" "41" "42" "43" "44" "45"]])

   (= (take 6 (map #(take 5 %) (problem168 str 3 2)))
      [["32" "33" "34" "35" "36"]
       ["42" "43" "44" "45" "46"]
       ["52" "53" "54" "55" "56"]
       ["62" "63" "64" "65" "66"]
       ["72" "73" "74" "75" "76"]
       ["82" "83" "84" "85" "86"]])

   (= (problem168 * 3 5 5 7)
      [[15 18 21 24 27 30 33]
       [20 24 28 32 36 40 44]
       [25 30 35 40 45 50 55]
       [30 36 42 48 54 60 66]
       [35 42 49 56 63 70 77]])
   ])
;;