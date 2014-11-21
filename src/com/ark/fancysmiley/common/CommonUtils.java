package com.ark.fancysmiley.common;

import java.util.List;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.ComponentName;
import android.content.Context;
import android.util.Log;

public class CommonUtils {
	public static final String TAG = CommonUtils.class.getName();

	public static boolean isApplicationIsInForeground(Context context, String packageName) {
		ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
		List<RunningAppProcessInfo> appProcesses = activityManager.getRunningAppProcesses();
		if (appProcesses == null) {
			return false;
		}

		for (RunningAppProcessInfo appProcess : appProcesses) {
			if (appProcess.importance == RunningAppProcessInfo.IMPORTANCE_FOREGROUND
					&& appProcess.processName.equals(packageName)) {
				return true;
			}
		}

		return false;
	}

	public static boolean isApplicationIsInBackground(Context context, String packageName) {
		ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
		List<RunningAppProcessInfo> appProcesses = activityManager.getRunningAppProcesses();
		if (appProcesses == null) {
			return false;
		}

		for (RunningAppProcessInfo appProcess : appProcesses) {
			if (appProcess.importance == RunningAppProcessInfo.IMPORTANCE_BACKGROUND
					&& appProcess.processName.equals(packageName)) {
				return true;
			}
		}

		return false;
	}

	@SuppressWarnings("deprecation")
	public static boolean isActivityInForeground(Context context, String packageName) {
		try {
			ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
			List<ActivityManager.RunningTaskInfo> runningTasks = manager.getRunningTasks(1);
			
			for (int i = 0; i < runningTasks.size(); i++) {
				ComponentName topActivity = runningTasks.get(i).topActivity;

				System.out.println(topActivity);

				if (topActivity.getClassName().equals(packageName)) {
					return true;
				}
			}

		} catch (Exception e) {
			Log.e(TAG, "isActivityForeground", e);
		}

		return false;
	}
}
