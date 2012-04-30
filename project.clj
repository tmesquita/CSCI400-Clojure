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

(defn move "Performs the player's move" [[locx locy] board]
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

(defn harvestText "Retrieves the value on each button into a 2D vector" [buttons]
  (map (fn [row]
         (map (fn [button]
                (.getText button)) row)) buttons))

(defn addButtonAction! "Adds an action listener to the buttons" [buttons]
  (let [actionListener
        (proxy [ActionListener] []
          (actionPerformed [evt]
            (let [coordinates (map (fn [c] (- (int c) (int \0))) (.getActionCommand evt)),
                  board (harvestText buttons)]
            (if (validateMove coordinates board)
              (move coordinates board)
              (JOptionPane/showMessageDialog nil "You're an Idiot.")))))]
              (dorun (map-indexed (fn [x row]
                          (dorun (map-indexed (fn [y button]
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
