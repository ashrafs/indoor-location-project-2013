package com.brian.android.util;

import java.io.IOException;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class ImageUtil {

	private static final String TAG = "ImageUtil";
	
	/**
	 * Load a picture from the resource.
	 * @param resources
	 * @param resourceId
	 * @return
	 */
	public static Bitmap loadBitmapFromResource(Resources resources, int resourceId) {
        Bitmap bitmap = BitmapFactory.decodeResource(resources, resourceId);
        return bitmap;
	}

	/**
	 * Load a picture from assets, unscale.
	 * @param context
	 * @param assetPath
	 * @return
	 * @throws IOException
	 */
	public static Bitmap loadBitmapFromAsset(Context context, String assetPath) throws IOException {
		Bitmap bitmap = BitmapFactory.decodeStream(context.getAssets().open(assetPath));
        return bitmap;
	}


	/**
	 * Calculate ratio to scale an object to fit the max size (width or height).
	 * <br/>
	 * Usage example:
	 * <br/>
	 * <pre>
	 *   float ratio = sizeFitRatio(width, height, maxWidth, maxHeight);
	 *   // Calculate size to fit maximum width or height.
	 *   int newWidth = ratio * width;
	 *   int newHeight = ratio * height;
	 * </pre>
	 * @param objectWidth
	 * @param objectHeight
	 * @param maxWidth
	 * @param maxHeight
	 * @return
	 */
	public static float sizeFitRatio(int objectWidth, int objectHeight, int maxWidth, int maxHeight) {
		float ratio = 1f; // Default ratio to 1.
    	if (objectWidth != 0 && objectHeight != 0) {
	    	float ratioWidth = maxWidth / (float)objectWidth;
	    	float ratioHeight = maxHeight / (float)objectHeight;
	    	float minRatio = (ratioWidth < ratioHeight) ? ratioWidth : ratioHeight;
			if (minRatio > 0) {
				ratio = minRatio;
			}
    	}
    	return ratio;
	}

	/**
	 * Scale an bitmap to fit frame size.
	 * @param bitmap
	 * @param frameWidth
	 * @param frameHeight
	 * @return Scaled bitmap, or the bitmap itself if scaling is unnecessary.
	 */
    public static Bitmap scaleToFitFrame(Bitmap bitmap, int frameWidth, int frameHeight) {
    	int bitmapWidth = bitmap.getWidth();
    	int bitmapHeight = bitmap.getHeight();
    	float ratio = sizeFitRatio(bitmapWidth, bitmapHeight, frameWidth, frameHeight);
    	return scaleImage(bitmap, ratio);
    }
    
    /**
     * Scale a bitmap.
     * @param bitmap
     * @param ratio
     * @return
     */
    public static Bitmap scaleImage(Bitmap bitmap, float ratio) {
    	Bitmap result = bitmap;
    	if (ratio != 1f) {
			int newWidth = (int) (bitmap.getWidth() * ratio);			
			int newHeight = (int) (bitmap.getHeight() * ratio);		
			result = Bitmap.createScaledBitmap(bitmap, newWidth, newHeight, true);
    	}
    	return result;
    }
}
