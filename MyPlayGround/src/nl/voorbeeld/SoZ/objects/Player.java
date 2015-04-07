package nl.voorbeeld.SoZ.objects;
import java.util.ArrayList;

import nl.saxion.act.playground.model.GameBoard;
import nl.saxion.act.playground.model.GameObject;
import nl.voorbeeld.SoZ.SoZGame;
import android.util.Log;
//import java.awt.Graphics;

public class Player extends GameObject {
	public static final String PLAYER_IMAGE = "playertemplate";

	/** Returns the ImageId of the image to show. */
	@Override
	public String getImageId() {
		return PLAYER_IMAGE;
	}

	/** Called when the user moves this player. */
	@Override
	public void onTouched(GameBoard gameBoard) {
		Log.d(SoZGame.TAG, "Touched player");

		// Player always move a square to the right
		int newPosX = getPositionX();
		int newPosY = getPositionY();

		// If new position is over the edge of the board, do nothing
		if (newPosX >= gameBoard.getWidth()) {
			return;
		}

		// Check if there is a object on the new position
		GameObject objectAtNewPos = gameBoard.getObject(newPosX, newPosY);
		if (objectAtNewPos != null) {

			// Player can't move through walls
			if (objectAtNewPos instanceof Enemy) {
				return;
			}

			// Killed a civilian? Score!
			if (objectAtNewPos instanceof Leaf) {
				gameBoard.removeObject(objectAtNewPos);
				((SoZGame) gameBoard.getGame()).changeScore();
			}
		}

		// Move player to the new position and redraw the app
		gameBoard.moveObject(this, newPosX, newPosY);
		gameBoard.updateView();
	}

	public void moveLeft(GameBoard gameBoard) {
		Log.d(SoZGame.TAG, "Moved Player");

		// player moves to the left
		int newPosX = getPositionX() - 1;
		int newPosY = getPositionY();

		// If new position is over the edge of the board, do nothing
		if (newPosX < 0|| ((SoZGame)(gameBoard.getGame())).isGameOver()) {
			return;
		}

		// Move player to the new position and redraw the app
		gameBoard.moveObject(this, newPosX, newPosY);
		gameBoard.updateView();
	}

	public void moveRight(GameBoard gameBoard) {
		Log.d(SoZGame.TAG, "Moved Player");

		// player moves to the right
		int newPosX = getPositionX() + 1;
		int newPosY = getPositionY();

		// If new position is over the edge of the board, do nothing
		if (newPosX > gameBoard.getWidth()-1 || ((SoZGame)(gameBoard.getGame())).isGameOver()) {
			return;
		}

		// Move player to the new position and redraw the app
		gameBoard.moveObject(this, newPosX, newPosY);
		gameBoard.updateView();
	}
	public void shoot(GameBoard gameBoard) {
		Log.d(SoZGame.TAG, "Fired Bullet");
		Projectile p = new MachinegunBullet();
		if (gameBoard.getGame().savegame.getEquiptWep().equals("ak")){
			p = new MachinegunBullet();
			if (gameBoard.getObject(getPositionX(), gameBoard.getHeight()-3)!=null){
				gameBoard.removeObject(gameBoard.getObject(getPositionX(), gameBoard.getHeight()-3));
			}else{
				gameBoard.addGameObject(p, getPositionX(), gameBoard.getHeight()-3);
				((SoZGame)gameBoard.getGame()).projectileFire(p);
			}
		}else if (gameBoard.getGame().savegame.getEquiptWep().equals("shotgun")){
			p = new ShotgunBullet();
			
			if (gameBoard.getObject(getPositionX(), gameBoard.getHeight()-3)!=null){
				gameBoard.removeObject(gameBoard.getObject(getPositionX(), gameBoard.getHeight()-3));
			}
			gameBoard.addGameObject(p, getPositionX(), gameBoard.getHeight()-3);
			((SoZGame)gameBoard.getGame()).projectileFire(p);
		}
		gameBoard.updateView();
	}
}
