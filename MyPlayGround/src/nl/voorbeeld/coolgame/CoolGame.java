package nl.voorbeeld.coolgame;

import android.widget.Toast;
import nl.saxion.act.playground.model.Game;
import nl.saxion.act.playground.model.GameBoard;
import nl.voorbeeld.coolgame.objects.Leaf;
import nl.voorbeeld.coolgame.objects.Rock;
import nl.voorbeeld.coolgame.objects.Player;


/**
 * Awesome game for the Speelveld-project.
 * 
 * @author Paul de Groot
 */
public class CoolGame extends Game {
	
	
	
	private Player player= new Player();
	private boolean gameOver= false;
	
	/** Tag used for log messages */
	public static final String TAG = "CoolGame";

	/** Reference to the main activity, so some labels can be updated. */
	private MainActivity activity;
	
	/** The number of leafs eaten. */
	private int score;

	/**
	 * Constructor.
	 * 
	 * @param activity  The main activity
	 */
	public CoolGame(MainActivity activity) {
		// Create a new game board and couple it to this game
		super(new CoolGameBoard());
		
		// Store reference to the main activity
		this.activity = activity;

		// Reset the game
		initNewGame();

		// Tell the game board view which game board to show
		CoolGameBoardView gameView = activity.getGameBoardView();
		GameBoard gameBoard = getGameBoard();
		gameView.setGameBoard(gameBoard);
		
		// Set size of the view to that of the game board
		gameView.setFixedGridSize(gameBoard.getWidth(), gameBoard.getHeight());
	}
	
	public Player getPlayer(){
		return player;
	}

	/**
	 * Starts a new game.
	 * Resets the score and places all objects in the right place.
	 */
	 
	 final Runnable spawn = new Runnable() {
	 @Override
		public void run() {
		spawnEnemies();
		}
	}
	_stop = new Boolean;
	public void initNewGame() {
		// Set the score and update the label
		score = 0;
		activity.updateScoreLabel(score);
		gameOver=false;

		GameBoard board = getGameBoard();
		board.removeAllObjects();

		// Add a player object
		board.addGameObject(new Player(), 5, 0);
		// init delay plus first row  //import runnable java.lang.Runnable
		mHandler = new Handler(Looper.getMainLooper()) {
			while(!_stop){ postAtTime(Runnable enemy.run, long 1000);//maybe no delay for movement?
				postAtTime(Runnable spawn, long 1000);
			
    				} 
    			 removeCallbacks (Runnable enemy.run);
    			removeCallbacks (Runnable Coolgame.run);

		// Redraw the game view
		board.updateView();
	}

	/**
	 * Called by Wombat if it ate a leaf. Increases the score.
	 */
	public void changeScore(int punten) {
		score= score+punten;
		activity.updateScoreLabel(score);
	}
	
	public void GameOver(){
		getGameBoard().removeAllObjects();
		//TODO stop timer
		gameOver = true;
		activity.getGameBoardView().setBackgroundResource(R.drawable.gameoverscreen);
		
		Toast.makeText(activity.getApplicationContext(), "GAME OVER",
				Toast.LENGTH_LONG).show();
	}

	public boolean isGameOver() {
		return gameOver;
	}
}
//dimitri faalt
