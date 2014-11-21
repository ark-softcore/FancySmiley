package com.ark.fancysmiley.animation;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;

import com.ark.fancysmiley.R;

public class AnimationActivity extends Activity {
	public static final String TAG = AnimationActivity.class.getName();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_animation);

		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {
				finish();
			}
		}, 3000);

		FallDownAnimationView animator = (FallDownAnimationView) findViewById(R.id.object);
		animator.startAnimation();
	}

	@Override
	public void onBackPressed() {
	}
}
