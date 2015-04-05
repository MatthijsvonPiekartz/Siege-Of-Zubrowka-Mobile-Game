package nl.voorbeeld.SoZ;

import nl.saxion.act.playground.R;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MenuSchermActivity extends Activity {
	private Button continueButton;
	private Button newGameButton;
	private Button exitButton;
	private Context dit = this;
	public static final String START_GAME_NEW_OF_NIET = "nl.saxion.act.playground.newgameofniet";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menuscherm);
		continueButton = (Button) findViewById(R.id.continueButton);
		newGameButton = (Button) findViewById(R.id.newGameButton);
		exitButton = (Button) findViewById(R.id.exitButton);

		registerOnExit();
		registerOncontinueClick();
		registerOnNewGameClick();

	}

	private void registerOnExit() {

		exitButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();

			}
		});
	}

	private void registerOnNewGameClick() {

		newGameButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(dit, MainActivity.class);
				intent.putExtra(START_GAME_NEW_OF_NIET, true);
				Log.d("new game", "new game druk");
				startGame(intent);
				MediaPlayer mp_xmPlayer2 = new MediaPlayer();
                mp_xmPlayer2 = MediaPlayer(this, R.raw.music);
                mp_xmPlayer2.setLooping(true);
				
			}

			private MediaPlayer MediaPlayer(OnClickListener onClickListener,
					int music) {
				// TODO Auto-generated method stub
				return null;
			}
		});
	}

	private void startGame(Intent intent) {
		startActivity(intent);
	}

	private void registerOncontinueClick() {

		continueButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(dit, MainActivity.class);
				intent.putExtra(START_GAME_NEW_OF_NIET, false);
				startGame(intent);

			}
		});
	}
}
