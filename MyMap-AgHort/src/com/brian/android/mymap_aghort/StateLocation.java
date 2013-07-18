package com.brian.android.mymap_aghort;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.brian.android.mymap.MapLocation;

/**
 * Example of extending MapLocation.
 * <p>
 * StateLocation can change the image when the user pressing on it.
 */
public class StateLocation extends MapLocation {

	private String title;
	
	/**
	 * Constructor.
	 * @param context
	 * @param x
	 * @param y
	 * @param title
	 */
	public StateLocation(Context context, int x, int y, String title) {
		super(context, x, y);
		setTitleAndOnClick(title);
	}
	
	private void setTitleAndOnClick(String title) {
		this.title = title;

		// Set onclick listener.
		this.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(getContext(), StateLocation.this.title, Toast.LENGTH_SHORT).show();
			}
		});
	}
	
	/**
	 * Constructor, using image in resources.
	 * @param context
	 * @param x
	 * @param y
	 * @param normalDrawableId
	 * @param pressedDrawableId
	 * @param title
	 */
	public StateLocation(Context context, int x, int y,
			int normalDrawableId, int pressedDrawableId, String title) {
		super(context, x, y, normalDrawableId, pressedDrawableId);
		setTitleAndOnClick(title);
	}

	/**
	 * Constructor, using image in assets folder.
	 * @param context
	 * @param x
	 * @param y
	 * @param normalImage
	 * @param pressedImage
	 * @param title
	 */
	public StateLocation(Context context, int x, int y,
			String normalImage, String pressedImage, String title) {
		super(context, x, y, normalImage, pressedImage);
		setTitleAndOnClick(title);
	}
	
	public String toString() {
		return title;
	}
}

