package com.ark.fancysmiley.accessibility;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.content.Intent;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.widget.Toast;

import com.ark.fancysmiley.common.CommonUtils;

public class AccessService extends AccessibilityService {
	public static final String TAG = AccessService.class.getName();

	private static final String[] PACKAGES = new String[] { "com.whatsapp", "jp.naver.line.android", "com.kakao.talk",
			"com.facebook.orca" };

	private static final String ACTIVITY_WHATSAPP_CONVERSATION = "com.whatsapp.Conversation";
	private boolean whatsAppRunning = false;

	private Thread availablityCheckingThread;

	@Override
	protected void onServiceConnected() {
		super.onServiceConnected();

		try {
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
	public void onAccessibilityEvent(final AccessibilityEvent event) {
		final String packageName = event.getPackageName().toString();
		final int eventType = event.getEventType();

		System.out.println("Event type: " + eventType);
		final String className = event.getClassName().toString();
		System.err.println("Class Name: " + className);
		final String text = event.getText().toString();

		switch (eventType) {
		case AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED:
			if (className.equals(ACTIVITY_WHATSAPP_CONVERSATION)) {
				if (CommonUtils.isActivityInForeground(getApplicationContext(), ACTIVITY_WHATSAPP_CONVERSATION)) {
					whatsAppRunning = true;

					startService(new Intent(getApplicationContext(), ViewBindServices.class));

					checkAvailablity();

					Toast.makeText(getApplicationContext(), packageName + " Coversation Started", Toast.LENGTH_SHORT)
							.show();
				}
			} else {
				whatsAppRunning = false;

				stopService(new Intent(getApplicationContext(), ViewBindServices.class));

				Toast.makeText(getApplicationContext(), packageName + " Coversation Stopped", Toast.LENGTH_SHORT)
						.show();
			}
			break;

		case AccessibilityEvent.TYPE_VIEW_CLICKED:
			if (whatsAppRunning && className.equals(ACTIVITY_WHATSAPP_CONVERSATION)) {
				Toast.makeText(getApplicationContext(), packageName + " >> " + text, Toast.LENGTH_SHORT).show();
			}
			break;

		default:
			break;
		}
	}

	@Override
	public void onInterrupt() {
		System.out.println("AccessService.onInterrupt()");
	}

	private void checkAvailablity() {
		if (availablityCheckingThread == null || !availablityCheckingThread.isAlive()) {
			availablityCheckingThread = new Thread() {
				@Override
				public void run() {
					super.run();

					boolean needed = false;
					do {
						for (int i = 0; i < PACKAGES.length; i++) {
							if (CommonUtils.isApplicationIsInForeground(getApplicationContext(), PACKAGES[i])) {
								needed = false;
							}
						}
					} while (needed);
				}
			};
		}
	}

}
