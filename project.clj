;Project file for clojure
;Thomas Mesquita
;Alan Nguyen
;Aaron Lebahn

(import '(javax.swing JFrame JButton JOptionPane))
(import '(java.awt.event ActionListener))
(import '(java.awt GridLayout))

(defn validateMove "Checks for valid move at the given location on the given board"
	[[locx locy] board]
	(let [value (nth (nth board locx) locy)]
		(= value " "))
)

(defn makeMove "Places Move at given location for given player on given board"
	[[locx locy] player board]
)

(defn makeRow "Makes a row of buttons" []
	(loop [col 0, buttons nil]
	(if (= col 3)
		buttons
		(let [button (JButton. "X")]
			(recur (inc col), (cons button buttons))))))

(defn makeButtons "returns a 3x3 vector of buttons" []
	(let [a 1]
		(loop [row 0, buttons nil]
		(if (= row 3)
			buttons
			(let [bRow (makeRow)]
			(recur (inc row), (cons bRow buttons)))))))

(defn showWindow "Initializes and shows the main Window" []
	(let [mainWindow (JFrame. "Tic-Tac-Toe"), buttons (makeButtons)]
		(.setDefaultCloseOperation mainWindow JFrame/EXIT_ON_CLOSE)
		(.setSize mainWindow 300 300)
		(.setLayout mainWindow (GridLayout. 3 3))
		(println buttons)
		(.setVisible mainWindow true)
	)
)

(defn startGame "Starts the Game" []
	(showWindow)
)

(startGame)
