package com.brian.android.mymap;

import java.io.IOException;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Bitmap.Config;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RelativeLayout.LayoutParams;

/**
 * Base class for the location displayed on the map.
 */
public class MapLocation extends ImageView {
	
	private static final String TAG = "MapLocation"; // Log tag.

	/**
	 * Default image to display the location on the map.
	 * This is a singleton variable.
	 */
	protected static Bitmap defaultImage;
	
	/**
	 * The position of the MapLocation on the map.
	 */
	protected Position position = new Position(0, 0);

	/**
	 * Set this to allow zoomable.
	 */
	private Position originalSize = null;
	
	/**
	 * Constructor.
	 * @param context
	 */
	public MapLocation(Context context) {
		super(context);
		
		// Set layout to wrap content.
		LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		setLayoutParams(params);
		
		// Set the default location image.
		setDefaultLocationImage();
	}
	
	/**
	 * Specify the position on the map.
	 * @param context
	 * @param x
	 * @param y
	 */
	public MapLocation(Context context, int x, int y) {
		this(context);
		setPosition(new Position(x, y));
	}

	/**
	 * Using image in resources to set state.
	 * @param context
	 * @param x
	 * @param y
	 * @param normalDrawableId
	 * @param pressedDrawableId
	 */
	public MapLocation(Context context, int x, int y,
			int normalDrawableId, int pressedDrawableId) {
		this(context, x, y);
		// Set state.
		setState(normalDrawableId, pressedDrawableId);
	}
	
	/**
	 * Using image in assets folder to set state.
	 * @param context
	 * @param x
	 * @param y
	 * @param normalImage
	 * @param pressedImage
	 */
	public MapLocation(Context context, int x, int y,
			String normalImage, String pressedImage) {
		this(context, x, y);
		// Set state.
		setState(normalImage, pressedImage);
	}

	/**
	 * Setter
	 * @param position
	 */
	protected void setPosition(Position position) {
		this.position = position;
	}

	/**
	 * Getter.
	 * @return
	 */
	public Position getPosition() {
		return position;
	}
	
	/**
	 * Allow this View resizable when zooming.
	 * @param originalSize
	 */
	public void setZoomable(Position originalSize) {
		this.originalSize = originalSize;
	}
	
	protected void setDefaultLocationImage() {
		Log.d(TAG, "setDefaultLocationImage");
		setImageBitmap(getDefaultImage());
	}
	
	/**
	 * Get the default image (a red circle).
	 * @return
	 */
	protected static Bitmap getDefaultImage() {
		if (defaultImage == null) {
			Bitmap bitmap = Bitmap.createBitmap(20, 20, Config.ARGB_8888);
			Paint paint = new Paint();
			paint.setColor(Color.RED);
			Canvas canvas = new Canvas(bitmap);
			canvas.drawCircle(5, 5, 5, paint);
			defaultImage = bitmap;
		}
		return defaultImage;
	}

//    @Override
//    protected void onDraw(Canvas canvas) {
//    	Log.d(TAG, "onDraw by " + this);
//       	super.onDraw(canvas);
//    }

//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//    	int width = MeasureSpec.getSize(widthMeasureSpec);
//    	int height = MeasureSpec.getSize(heightMeasureSpec);
//    	Log.d(TAG, "onMeasure(" + width + ", " + height + ") by " + this);
//    	super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//    }
 
    /**
     * Zoom the MapLocation according to the zooming of the map.
     * This will resize the size and adjust the position.
     * @param zoomRatio
     */
    public void zoom(float zoomRatio, Position mapImageLeftTop) {
    	Log.d(TAG, "zoom(" + zoomRatio + ", " + mapImageLeftTop + ") by " + this);
		if (zoomRatio > 0) {
			// Zoomed size.
			Position newSize = calcZoomedSize(zoomRatio);
			// New position depends on the zoom ratio and mapImage left-top.
			Position position = getPosition().mult(zoomRatio).add(mapImageLeftTop);
			// Reset size and left-top position.
	    	LayoutParams layoutParams = originalSize != null ?
	    			new LayoutParams(newSize.x, newSize.y) :
	    			(LayoutParams) getLayoutParams();
	    	layoutParams.topMargin = position.y - newSize.y / 2;
	    	layoutParams.leftMargin = position.x - newSize.x / 2;
	    	setLayoutParams(layoutParams);
		}
    }
	
	protected Position calcZoomedSize(float zoomRatio) {
		// Try to get original size.
		// If originalSize is not null, then it is used, else the current size is return.
		Position newSize = originalSize != null ?
				originalSize.mult(zoomRatio) :
				new Position(getWidth(), getHeight());
		return newSize;
	}

	/**
	 * Set state using drawables in resource.
	 * @param normalResId Drawable resource in normal.
	 * @param pressedResId Drawable resource when pressing.
	 */
	public void setState(int normalResId, int pressedResId) {
    	StateListDrawable drawable = new StateListDrawable();
    	drawable.addState(new int[] {android.R.attr.state_pressed}, getResources().getDrawable(pressedResId));
    	drawable.addState(new int[] {android.R.attr.state_enabled}, getResources().getDrawable(normalResId));
    	setImageDrawable(drawable);
	}
	
	/**
	 * Set state using images in assets folder.
	 * @param normalImage Image in normal.
	 * @param pressedImage Image in pressing.
	 */
	public void setState(String normalImage, String pressedImage) {
    	try {
        	StateListDrawable drawable = new StateListDrawable();
	    	drawable.addState(new int[] {android.R.attr.state_pressed}, getDrawable(pressedImage));
			drawable.addState(new int[] {android.R.attr.state_enabled}, getDrawable(normalImage));
	    	setImageDrawable(drawable);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create a Drawable from an image file in the assets folder.
	 * @param fileName
	 * @return
	 * @throws IOException 
	 */
	private Drawable getDrawable(String fileName) throws IOException {
		return Drawable.createFromStream(getContext().getAssets().open(fileName), null);
	}
}
