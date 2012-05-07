package de.cs.android.sirene;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Sirene extends Activity {
	class SireneControl {
		private boolean playing = false;
		private final TextView infoLine = (TextView) findViewById(R.id.infoLine);
		private final Button playSireneButton = (Button) findViewById(R.id.playSirene);
		private final MediaPlayer mp;
		private AnimationDrawable sireneAnimation;
		private final ImageView sireneImg;

		public SireneControl(Context cont) {
			this.mp = MediaPlayer.create(cont, R.raw.whoop);
			sireneImg = (ImageView) findViewById(R.id.sireneImage);

			playSireneButton.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					toggle();
				}
			});
			mp.setOnCompletionListener(new OnCompletionListener() {

				@Override
				public void onCompletion(MediaPlayer mp) {
					stop();
				}
			});
		}

		private void start() {
			sireneImg.setImageResource(R.drawable.sirene_animation);
			sireneAnimation = (AnimationDrawable) sireneImg.getDrawable();
			sireneAnimation.start();
			playing = true;
			infoLine.setText(R.string.infoLineSireneStarted);
			playSireneButton.setText(R.string.SireneButtonStop);
			mp.start();

		}

		private void stop() {
			sireneAnimation.stop();
			playing = false;
			infoLine.setText(R.string.infoLineSireneStopped);
			playSireneButton.setText(R.string.SireneButtonPlay);
			mp.stop();
			sireneImg.setImageResource(R.drawable.bluelights_off);

			try {
				mp.prepare();
			} catch (Exception e) {
				infoLine.setText("ERR: " + e.getMessage());
			}

		}

		public void toggle() {
			if (playing) {
				stop();
			} else {
				start();
			}
		}
	}

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		SireneControl sc = new SireneControl(this);

	}
}

// public class Sirene extends Activity {
// class SireneControl {
// private boolean playing = false;
// private final Context cont;
// private final TextView infoLine = (TextView) findViewById(R.id.infoLine);
// private final Button playSireneButton = (Button)
// findViewById(R.id.playSirene);
// private MediaPlayer mp;
//
// public SireneControl(Context cont) {
// this.cont = cont;
//
// playSireneButton.setOnClickListener(new View.OnClickListener() {
// @Override
// public void onClick(View v) {
// toggle();
// }
// });
// }
//
// private void complete() {
// playing = false;
// infoLine.setText(R.string.infoLineSireneStopped);
// playSireneButton.setText(R.string.SireneButtonPlay);
// mp.release();
// }
//
// private void start() {
// mp = MediaPlayer.create(cont, R.raw.alarm);
//
// mp.setOnCompletionListener(new OnCompletionListener() {
//
// @Override
// public void onCompletion(MediaPlayer mp) {
// complete();
// }
// });
//
// playing = true;
// infoLine.setText(R.string.infoLineSireneStarted);
// playSireneButton.setText(R.string.SireneButtonStop);
// mp.start();
//
// }
//
// private void stop() {
// playing = false;
// infoLine.setText(R.string.infoLineSireneStopped);
// playSireneButton.setText(R.string.SireneButtonPlay);
// mp.stop();
// mp.release();
//
// }
//
// public void toggle() {
// if (playing) {
// stop();
// } else {
// start();
// }
// }
// }
//
// SireneControl sc;
//
// /** Called when the activity is first created. */
// @Override
// public void onCreate(Bundle savedInstanceState) {
// super.onCreate(savedInstanceState);
// setContentView(R.layout.main);
//
// sc = new SireneControl(this);
//
// }
// }