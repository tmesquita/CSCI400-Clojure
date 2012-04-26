;Project file for clojure
;Thomas Mesquita
;Alan Nguyen
;Aaron Lebahn

(import '(javax.swing JFrame JButton JOptionPane))
(import '(java.awt.event ActionListener))

(defn validateMove "Checks for valid move at the given location on the given board"
	[[locx locy] board]
	(let [value (nth (nth board locx) locy)]
		(= value " "))
)

(defn makeMove "Places Move at given location for given player on given board"
	[[locx locy] player board]
)

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
