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

;Write a multithreaded program to determine how many haircuts a barber can give in ten seconds
(def waitingRoom (java.util.concurrent.LinkedBlockingQueue. 3))
(def numHaircuts (atom 0))
(def continue? (atom true))

(defn open 
	[length] 
	(println "Start")
	(Thread/sleep length)
	(swap! continue? not)
	(Thread/sleep 35)
	(println "Finished")
	(println "Number of haircuts: " @numHaircuts)
	(System/exit 0)
)

(defn newCustomer
	[]
	(future
		(while @continue?
			(.put waitingRoom "customer")
			(println "New customer, waiting room size: " (.size waitingRoom))
			(Thread/sleep (+ 10 (rand-int 21)))
		)
	)
)

(defn cutHair
	[]
	(future
		(while @continue?
			(when-let [item (.poll waitingRoom)]
				(println "Hair cut complete")
				(Thread/sleep 20)
				(swap! numHaircuts inc)
			)
		)
	)
)

(defn start
	[]
	(dorun (repeatedly 1 newCustomer))
	(dorun (repeatedly 1 cutHair))
	(open 10000)
)

(start)