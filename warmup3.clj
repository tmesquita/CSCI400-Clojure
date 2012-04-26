;Warm up two for clojure
;Thomas Mesquita
;Alan Nguyen
;Aaron Lebahn

;Use refs to create a vector of accounts in memory. Create debit and credit functions to change the balance of an account.

(def accounts (ref []))
(defn credit
	[accounts accountNum ammount]
	(map 
		(fn
			[account]
 			(if
 				(= accountNum (:account account))
 				(assoc
					account
					:balance
					(+ (:balance account) ammount)
				)
				account
			)
		)
		accounts
	)
)

(defn debit
	[accounts account ammount]
	(credit accounts account (- ammount))
)

(dosync
	(alter accounts
		conj{ :account "1" :balance 100 }
			{ :account "2" :balance 200 }
	)
)

(println @accounts)
(println "Credit 50 to account 1, and debit 100 from account 2")
(dosync
	(alter accounts credit "1" 50)
	(alter accounts debit "2" 100)
)
(println @accounts)