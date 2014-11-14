package com.ark.fancysmiley.accessibility;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.widget.Toast;

import com.ark.fancysmiley.common.CommonUtils;

public class AccessService extends AccessibilityService {
	private static final String[] PACKAGES = new String[] { "com.whatsapp", "jp.naver.line.android", "com.kakao.talk",
			"com.facebook.orca" };
	public static final String TAG = AccessService.class.getName();

	private static final String ACTIVITY_WHATSAPP_CONVERSATION = "com.whatsapp.Conversation";

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
	public void onAccessibilityEvent(AccessibilityEvent event) {
		System.out.println("AccessService.onAccessibilityEvent()");

		String packageName = event.getPackageName().toString();
		int eventType = event.getEventType();

		System.err.println(packageName);
		System.out.println("Event type: " + eventType);
		String className = event.getClassName().toString();
		System.err.println("Class Name" + className);

		switch (eventType) {
		case AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED:
			if (className.equals(ACTIVITY_WHATSAPP_CONVERSATION)) {
				if (CommonUtils.isActivityInForeground(getApplicationContext(), ACTIVITY_WHATSAPP_CONVERSATION)) {
					Toast.makeText(getApplicationContext(), packageName + " Coversation Started", Toast.LENGTH_SHORT)
							.show();
				}
			} else {
				Toast.makeText(getApplicationContext(), packageName + " Coversation Stopped", Toast.LENGTH_SHORT)
						.show();
			}
			break;

		case AccessibilityEvent.TYPE_VIEW_CLICKED:
			if (className.equals(ACTIVITY_WHATSAPP_CONVERSATION)) {
				Toast.makeText(getApplicationContext(), packageName + " >> " + event.getText(), Toast.LENGTH_SHORT)
						.show();
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

}
