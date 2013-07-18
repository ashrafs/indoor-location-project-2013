package com.brian.android.mymap;

import android.graphics.Bitmap;

import com.brian.android.mymap.Position;

/**
 * Describe the information of the image to be display on the map
 * (left-top position and the bitmap).
 */
class MapImageDisplay {

	/**
	 * The left-top position.
	 */
	Position leftTop;
	
	/**
	 * The bitmap.
	 */
	Bitmap image;
	
	public String toString() {
		return "(x: " + leftTop.x + ", y: " + leftTop.y + ", w: " + image.getWidth() + ", h: " + image.getHeight() + ")";
	}
	
	public Position getRightBottom() {
		return new Position(leftTop.x + image.getWidth(), leftTop.y + image.getHeight());
	}
}
