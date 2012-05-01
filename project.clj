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

(defn move "Performs the player's move" [[locx locy] board player]
  (map-indexed (fn [x row] (map-indexed (fn [y sq]
                                          (if (and (= x locx) (= y locy))
                                            player
                                            sq)) row)) board))

(defn chooseMove "Calculates the computer's move" [board]
  '[0 1])

(defn compareRow [row]
	(if(= row '("X" "X" "X"))
		:X
		(if (= row '("O" "O" "O"))
			:O
			false)))
(defn getDiagonals [board]
	(list (map-indexed (fn[i row] (nth row i)) board) (map-indexed (fn[i row] (nth row (- 2 i))) board)))

(defn findSpaces [board]
	(if(empty? board)
		false
		(if(not (nil? (some #{" "} (first board))))
			true
			(findSpaces (rest board)))))

(defn checkRowsAndCols [board fullBoard]
	(if(empty? board)
		(if(findSpaces fullBoard)
			nil
			:Tie)
		(if(false? (compareRow (first board)))
			(checkRowsAndCols (rest board) fullBoard)
			(compareRow (first board)))))

(defn checkWin [board]
	(println (getDiagonals board))
	(checkRowsAndCols (concat board (apply map list board) (getDiagonals board)) board))

(defn makeRow "Makes a row of buttons" []
	(loop [col 0, buttons nil]
	(if (= col 3)
		buttons
		(let [button (JButton. " ")]
			(recur (inc col), (cons button buttons))))))

(defn makeButtons "returns a 3x3 vector of buttons" []
	(let [a 1]
		(loop [row 0, buttons nil]
		(if (= row 3)
			buttons
			(let [bRow (makeRow)]
			(recur (inc row), (cons bRow buttons)))))))

(defn harvestText "Retrieves the value on each button into a 2D vector" [buttons]
  (map (fn [row]
         (map (fn [button]
                (.getText button)) row)) buttons))

(defn plantText! "Puts the board state onto buttons" [buttons board]
  (dorun (map (fn [butRow boardRow] (dorun (map (fn [button sq] (.setText button sq)) butRow boardRow))) buttons board)))

(defn declareWinAndRestart! [winState buttons]
  (JOptionPane/showMessageDialog
    nil
    (if (= winState :X)
      "X WINS!"
      (if (= winState :O)
        "O WINS!"
        "IT'S A TIE!")))
  (dorun (map (fn [row] (dorun (map (fn [button] (.setText button " ")) row))) buttons)))

(defn takeTurn! "Performs a move to coordinates, checks for a win, and calls next turn if neccesary"
  [buttons coordinates board turn]
  (let [newBoard (move coordinates board turn)
        winState (checkWin newBoard)]
    (plantText! buttons newBoard)
    (if (nil? winState)
      (if (= turn "X")
        (takeTurn! buttons (chooseMove newBoard) newBoard "O"))
      (declareWinAndRestart! winState buttons))))

(defn validateAndMove! "Validates the player's input then runs the turn"
  [buttons coordinates board]
  (if (validateMove coordinates board)
    (takeTurn! buttons coordinates board "X")
    (JOptionPane/showMessageDialog nil "You're an idiot.")))

(defn addButtonAction! "Adds an action listener to the buttons" [buttons]
  (let [actionListener
        (proxy [ActionListener] []
          (actionPerformed [evt]
            (validateAndMove! buttons
              (map (fn [c] (- (int c) (int \0))) (.getActionCommand evt))
              (harvestText buttons))))]
    (dorun (map-indexed
             (fn [x row]
               (dorun (map-indexed
                        (fn [y button]
                          (do (.addActionListener button actionListener)
                            (.setActionCommand button (str x y)))) row))) buttons))))

(defn addButtons! "Adds buttons to the window" [buttons frame]
	(dorun (map (fn [row]
		(dorun (map (fn [button]
			(.add frame button)) row))) buttons)))

(defn showWindow "Initializes and shows the main Window" []
	(let [mainWindow (JFrame. "Tic-Tac-Toe"), buttons (makeButtons)]
		(.setDefaultCloseOperation mainWindow JFrame/EXIT_ON_CLOSE)
		(.setSize mainWindow 300 300)
		(.setLayout mainWindow (GridLayout. 3 3))
		(addButtonAction! buttons)
		(addButtons! buttons mainWindow)
		(.setVisible mainWindow true)
	)
)

(defn startGame "Starts the Game" []
	(showWindow)
)

(startGame)
