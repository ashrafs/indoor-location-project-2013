package com.brian.android.mymap_aghort;

import android.content.Context;

import com.brian.android.mymap.Position;

/**
 * Example of extending MapLocation.
 * <p>
 * The location size is changed when zooming the map.
 */
public class ZoomLocation extends StateLocation {

	/**
	 * Constructor.
	 * @param context
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param normalDrawable Image on normal status.
	 * @param pressedDrawable Image when pressing on the location.
	 * @param title
	 */
	public ZoomLocation(Context context, int x, int y, int width, int height,
			String normalDrawable, String pressedDrawable, String title) {
		super(context, x, y, normalDrawable, pressedDrawable, title);

		setZoomable(new Position(width, height));
	}
}
