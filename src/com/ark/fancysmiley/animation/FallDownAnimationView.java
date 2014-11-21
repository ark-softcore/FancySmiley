package com.ark.fancysmiley.animation;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.View;

public class FallDownAnimationView extends View {
	public static final String TAG = FallDownAnimationView.class.getName();

	private Bitmap animtorObject;

	public FallDownAnimationView(Context context) {
		super(context);
	}

	public FallDownAnimationView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public FallDownAnimationView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	protected void onDraw(android.graphics.Canvas canvas) {
		super.onDraw(canvas);
	}

	public void setAnimtorObject(Bitmap animtorObject) {
		this.animtorObject = animtorObject;
	}

	public Bitmap getAnimtorObject() {
		return animtorObject;
	}

	public void startAnimation() {
		
	}
}
