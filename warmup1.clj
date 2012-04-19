;Warm up one for clojure
;Thomas Mesquita
;Alan Nguyen
;Aaron Lebahn

;(big st n) that returns true if a string st is longer than n characters.

(defn big
	[st n]
	(> (count st) n))

(println (big "test" 4))
(println (big "Clojure" 3))
(println (big "Brogrammer" 11))

;(collection-type col) that returns :list, :map,
;or :vector based on the type of collection col.
