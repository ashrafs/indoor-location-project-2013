package com.brian.android.mymap;

import android.graphics.Bitmap;
import android.util.Log;

import com.brian.android.util.ImageUtil;

/**
 * Manipulate the map image.
 */
class MapImage {
	
	private static final String TAG = "MapImage"; // Log tag.

	/**
	 * Original map image.
	 */
	private Bitmap originalImage;

	/**
	 * Focus point (the point on the image that should be displayed at the center of the map).
	 */
	private Position focusOnOriginalImage;

	private float zoomRatio = 1;
	
	/**
	 * Constructor.
	 * @param image The map image.
	 */
	public MapImage(Bitmap image) {
		// Set the image data.
		originalImage = image;
		Log.d(TAG, "Original image size: " + imageSize(image));
		// Set the focus.
		resetFocus();
    	Log.d(TAG, "Default focus: " + focusOnOriginalImage);
	}
	
	/**
	 * Zoom the image.
	 * @param ratio
	 */
	public void zoom(float ratio) {
		Log.d(TAG, "zoom(" + ratio + ")");
		if (ratio > 0) {
		  this.zoomRatio = ratio;
		}
	}
	
	/**
	 * Set the focus to the center of the image.
	 */
	public void resetFocus() {
		focusOnOriginalImage = new Position(originalImage.getWidth() / 2, originalImage.getHeight() / 2);
	}
	
	/**
	 * Move the focus a specified distance.
	 * The distance is based on the zoomRatio.
	 * @param distX
	 * @param distY
	 */
	public void moveFocus(int distX, int distY) {
		Position originalDist = new Position(distX, distY).mult(1 / zoomRatio);
		Log.d(TAG, "moveFocus(" + distX + ", " + distY + ") ~= " + originalDist);
		Log.d(TAG, "old focus: " + focusOnOriginalImage);
		focusOnOriginalImage = focusOnOriginalImage.add(originalDist);
		Log.d(TAG, "new focus: " + focusOnOriginalImage);
	}
	
	public int getOriginalImageWidth() {
		return originalImage.getWidth();
	}
	
	public int getOriginalImageHeight() {
		return originalImage.getHeight();
	}

	/**
	 * Generate image info (left-top position and bitmap) to displayed in a frame.
	 * @param frameWidth
	 * @param frameHeight
	 * @return
	 */
	public MapImageDisplay getImageToDisplay(int frameWidth, int frameHeight) {
		MapImageDisplay mapImageDisplay = new MapImageDisplay(); // Return value.

		// Generate the bitmap info to display.
		// Zoom image.
		Bitmap zoomedImage = ImageUtil.scaleImage(originalImage, zoomRatio);
		// Zoom focus.
		Position zoomedFocus = focusOnOriginalImage.mult(zoomRatio);
		Log.d(TAG, "Zoomed image size: " + imageSize(zoomedImage) + ", focus: " + zoomedFocus);

		Position frameCenter = new Position(frameWidth / 2, frameHeight / 2);
		Position leftTop = frameCenter.sub(zoomedFocus); // Left/top position of the zoomedImage
		Log.d(TAG, "getImageToDisplay leftTop: " + leftTop);
		mapImageDisplay.leftTop = leftTop;
		mapImageDisplay.image = zoomedImage;

		Log.d(TAG, "Map image display: " + mapImageDisplay);
		return mapImageDisplay;
	}
	
	/**
	 * Return a string that describes the image size.
	 * @param bitmap
	 * @return
	 */
	private Position imageSize(Bitmap bitmap) {
		return new Position(bitmap.getWidth(), bitmap.getHeight());
	}
}
