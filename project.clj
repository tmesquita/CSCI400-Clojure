;Project file for clojure
;Thomas Mesquita
;Alan Nguyen
;Aaron Lebahn
(import '(javax.swing JFrame JButton JOptionPane))

(defn showWindow "Initializes and shows the main Window" []
	(let [mainWindow (JFrame. "Tic-Tac-Toe")]
		(.setDefaultCloseOperation mainWindow JFrame/EXIT_ON_CLOSE)
		(.setSize mainWindow 300 300)
		(.setVisible mainWindow true)
	)
)

(defn startGame "Starts the Game" []
	(showWindow)
)

(startGame)
