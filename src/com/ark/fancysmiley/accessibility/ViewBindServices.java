package com.ark.fancysmiley.accessibility;

import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.os.IBinder;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout.LayoutParams;

import com.ark.fancysmiley.R;

public class ViewBindServices extends Service implements OnTouchListener, OnGestureListener {
	private static final int MARGIN = 20;

	private GestureDetector detector;
	private LayoutParams param;
	private WindowManager manager;

	private ImageView view;
	private View iView;
	private int intrinsicWidth;
	private int intrinsicHeight;

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		detector = new GestureDetector(getApplicationContext(), this);

		manager = (WindowManager) getSystemService(WINDOW_SERVICE);

		LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
		iView = inflater.inflate(R.layout.acessor, null);

		manager.addView(iView, new WindowManager.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT,
				WindowManager.LayoutParams.TYPE_PHONE, WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
				PixelFormat.TRANSLUCENT));

		view = (ImageView) iView.findViewById(R.id.icon);
		iView.setOnTouchListener(this);

		param = (LayoutParams) view.getLayoutParams();
		intrinsicWidth = view.getDrawable().getIntrinsicWidth();
		intrinsicHeight = view.getDrawable().getIntrinsicHeight();

		param.leftMargin = MARGIN;
		param.topMargin = 50;

		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onDestroy() {
		manager.removeView(iView);

		super.onDestroy();
	}

	public void hideView() {
		view.setVisibility(View.GONE);
	}

	public void showView() {
		view.setVisibility(View.VISIBLE);
	}

	public boolean isViewHide() {
		return view.getVisibility() != View.VISIBLE;
	}

	private boolean down = false;

	@Override
	public boolean onDown(MotionEvent e) {
		int left = view.getLeft();
		int top = view.getTop();

		int width = view.getWidth();
		int height = view.getHeight();

		if (new Rect(left, top, left + width, top + height).contains((int) e.getX(), (int) e.getY())) {
			down = true;
		} else {
			down = false;
		}

		return down;
	}

	@Override
	public void onShowPress(MotionEvent e) {
	}

	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		return view.performClick();
	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
		if (!down) {
			return false;
		}

		int pointX = (int) e2.getX();
		int pointY = (int) e2.getY();

		switch (e2.getAction()) {
		case MotionEvent.ACTION_POINTER_UP:
			down = false;
			break;

		case MotionEvent.ACTION_MOVE:
			param = (LayoutParams) view.getLayoutParams();

			param.leftMargin = getScreenFormattedX(pointX);
			param.topMargin = getScreenFormattedY(pointY);

			view.setLayoutParams(param);
			break;
		}

		return down;
	}

	private int getScreenFormattedX(int pointX) {
		if (pointX < MARGIN) {
			return MARGIN;
		}

		if (pointX > iView.getWidth() - intrinsicWidth - MARGIN) {
			return iView.getWidth() - intrinsicWidth - MARGIN;
		}

		return pointX;
	}

	private int getScreenFormattedY(int pointY) {
		if (pointY < MARGIN) {
			return MARGIN;
		}

		if (pointY > iView.getHeight() - intrinsicHeight - MARGIN) {
			return iView.getHeight() - intrinsicHeight - MARGIN;
		}

		return pointY;
	}

	@Override
	public void onLongPress(MotionEvent e) {
	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
		return false;
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		return detector.onTouchEvent(event);
	}

}