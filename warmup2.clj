;Warm up two for clojure
;Thomas Mesquita
;Alan Nguyen
;Aaron Lebahn

; 1. Implement an unless with else condition using macros
;Define the unless macro with an Else condition

(defmacro
	unless
	[test if-body else-body]
	'(if
		(not ~test)
		~if-body
		~else-body
	)
)