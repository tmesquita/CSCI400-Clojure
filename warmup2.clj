;Warm up two for clojure
;Thomas Mesquita
;Alan Nguyen
;Aaron Lebahn

; 1. Implement an unless with else condition using macros
;Define the unless macro with an Else condition

(defmacro
	unless
	[test if-body else-body]
	(list 'if (list 'not test) if-body else-body)
)

; 2. Write a type using defrecord that implements a protocol.

(defprotocol Counter
(value [c])
(increment [c])        
(decrement [c]))

(defrecord SimpleCounter [count]
Counter
(value [_] count)
(increment [_] (SimpleCounter. (+ count 1)))
(decrement [_] (SimpleCounter. (- count 1)))
)

