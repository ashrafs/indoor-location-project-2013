package com.brian.android.util;

import android.app.Activity;
import android.widget.Toast;

/**
 * Utility function on Activity.
 */
public class ActivityUtil {

	/**
	 * Display a Toast message and close the activity.
	 * @param activity
	 * @param errorMessage
	 */
	public static void closeOnError(Activity activity, String errorMessage) {
		Toast.makeText(activity, errorMessage, Toast.LENGTH_LONG).show();
		activity.finish();
	}
}
