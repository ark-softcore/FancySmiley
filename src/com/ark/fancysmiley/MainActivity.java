package com.ark.fancysmiley;

import com.ark.fancysmiley.animation.AnimationActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class MainActivity extends Activity implements OnClickListener {
	public static final String TAG = MainActivity.class.getName();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public void onClick(View v) {
		Intent intent = new Intent(getActivity(), AnimationActivity.class);
		startActivity(intent);
	}

	private Context getActivity() {
		return this;
	}
}
