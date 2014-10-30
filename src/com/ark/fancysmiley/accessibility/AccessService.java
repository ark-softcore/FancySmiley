package com.ark.fancysmiley.accessibility;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;

import com.ark.fancysmiley.BuildConfig;

public class AccessService extends AccessibilityService {
	private static final String[] PACKAGES = new String[] { "com.whatsapp",
			"jp.naver.line.android", "com.kakao.talk", "com.facebook.orca",
			"com.golgorz.animatedsmileysfree, com.ark.dictionary" };
	public static final String TAG = AccessService.class.getName();

	@Override
	protected void onServiceConnected() {
		super.onServiceConnected();

		try {
			if (BuildConfig.DEBUG)
				Log.v(TAG, "onServiceConnected");

			AccessibilityServiceInfo accessibilityserviceinfo = new AccessibilityServiceInfo();
			accessibilityserviceinfo.eventTypes = -1;
			accessibilityserviceinfo.feedbackType = 16;
			accessibilityserviceinfo.packageNames = PACKAGES;
			setServiceInfo(accessibilityserviceinfo);
			
			return;
		} catch (Exception e) {
			Log.e(TAG, "onServiceConnected", e);
		}
	}

	@Override
	public void onAccessibilityEvent(AccessibilityEvent event) {
		System.out.println("AccessService.onAccessibilityEvent()");
	}

	@Override
	public void onInterrupt() {
	}

}
