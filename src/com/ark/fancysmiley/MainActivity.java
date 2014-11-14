package com.ark.fancysmiley;

import java.util.Locale;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Locale default1 = Locale.getDefault();
		System.err.println(default1);
	}
}
