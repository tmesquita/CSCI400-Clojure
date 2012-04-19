;Warm up one for clojure
;Thomas Mesquita
;Alan Nguyen
;Aaron Lebahn

;(big st n) that returns true if a string st is longer than n characters.
(defn big
	[st n]
	(> (count st) n)
)

(println "st = test, n = 4: " (big "test" 4))
(println "st = Clojure, n = 3: " (big "Clojure" 3))
(println "st = Brogrammer, n = 11: " (big "Brogrammer" 11))

;(collection-type col) that returns :list, :map,
;or :vector based on the type of collection col.
(defn collection-type
	[col]
	(cond
		(list? col) :list
		(map? col) :map
		(vector? col) :vector
	)
)

(println "'(1 2 3) =" (collection-type '(1 2 3)))
(println "{:vader \"sith\", :luke \"jedi\"} =" (collection-type {:vader "sith", :luke "jedi"}))
(println "[:hutt :wookie :ewok] =" (collection-type [:hutt :wookie :ewok]))