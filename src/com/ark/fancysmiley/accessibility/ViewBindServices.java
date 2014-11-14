package com.ark.fancysmiley.accessibility;

import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.ImageView;

import com.ark.fancysmiley.R;

public class ViewBindServices extends Service implements OnTouchListener,
		OnGestureListener {
	private GestureDetector detector;
	private LayoutParams param;
	private ImageView view;
	private WindowManager manager;
	private int inity;
	private int initx;

	@Override
	public void onCreate() {
		super.onCreate();
		detector = new GestureDetector(this, this);
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		manager = (WindowManager) getSystemService(WINDOW_SERVICE);
		view = new ImageView(this);
		view.setImageResource(R.drawable.ic_launcher);
		view.setOnTouchListener(this);
		param = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, LayoutParams.TYPE_PHONE,
				LayoutParams.FLAG_NOT_FOCUSABLE, PixelFormat.TRANSLUCENT);
		param.x = 0;
		param.y = 0;

		param.gravity = Gravity.LEFT | Gravity.TOP;
		manager.addView(view, param);
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		manager.removeView(view);
	}

	@Override
	public boolean onDown(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onShowPress(MotionEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onLongPress(MotionEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		return false;
	}
}