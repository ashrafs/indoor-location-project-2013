package com.brian.android.mymap;

import java.lang.ref.WeakReference;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.GestureDetector.OnGestureListener;
import android.widget.RelativeLayout;

import com.brian.android.util.ImageUtil;
/**
 * Map view class.
 * <p>
 * A map view contains the map image and the locations on it. 
 * <p>
 * The map view has the following function:
 * <ul>
 *   <li>Zoom in, zoom out.</li>
 *   <li>Scrolling.</li>
 *   <li>Place location.</li>
 * </ul>
 */
public class MapView extends RelativeLayout {

	private static final String TAG = "MapView"; // Log tag.

	/**
	 * ImageView that displays the map image.
	 */
	private MapImage mapImage;

	/**
	 * Reference to MapImageDisplay.
	 * This is used to reduce the times of creating MapImageDisplay object.
	 */
	private WeakReference<MapImageDisplay> mapImageDisplayRef;

	private MapLocation[] locations = new MapLocation[0];
	
	/**
	 * Map width.
	 */
	private int maxWidth;
	
	/**
	 * Map height.
	 */
	private int maxHeight;
	
	/**
	 * Minimum zoom ratio.
	 */
	private float zoomMinRatio = 1;
	
	/**
	 * Current zoom ratio.
	 * Value -1 mean it has not been set.
	 */
	private float zoomRatio = -1;

	/**
	 * Paint used to clear the canvas.
	 */
	private Paint clearCanvasPaint;

	/**
	 * GestureDetector to process scrolling.
	 */
	private GestureDetector gestureScanner;

	/**
	 * Minimum moving distance to detect the scrolling.
	 */
	private int minScrollRate = 3;
	
	/**
	 * Construct the object from an XML file. Valid Attributes:
	 * 
	 * @see android.view.View#View(android.content.Context, android.util.AttributeSet)
	 */
	public MapView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initObject();
	}
	
	/**
	 * Empty the reference to MapImageDisplay.
	 * @return
	 */
	private void emptyMapImageDisplayRef() {
		mapImageDisplayRef = new WeakReference<MapImageDisplay>(null);
	}
	
	/**
	 * Initiate the BaseMap object after creating it.
	 * This is called in the constructor.
	 */
	private void initObject() {
		emptyMapImageDisplayRef();
		
		// Create clearCanvasPaint.
		clearCanvasPaint = new Paint();
		clearCanvasPaint.setColor(Color.TRANSPARENT);
		
		createGestureListener();
	}
	
	/**
	 * Set an image as the map.
	 * @param mapImage
	 */
	public void setMapImage(Bitmap image) {
    	Log.d(TAG, "setMapImage");

    	// Set the map image.
    	mapImage = new MapImage(image);
	}

	/**
	 * Set the locations.
	 * @param locations
	 */
	public void setLocations(MapLocation[] locations) {
		// Remove old locations.
		for (MapLocation location : this.locations) {
			removeView(location);
		}

		// Add new locations.
		this.locations = locations;
		for (MapLocation location : this.locations) {
			addView(location);
		}
	}

	@Override
	protected void onDraw(Canvas canvas) {
    	Log.d(TAG, "onDraw");

    	canvas.save();

	    // Clear the canvas
	    canvas.drawRect(0, 0, getWidth(), getHeight(), clearCanvasPaint);
	    
	    // Draw the map image.
    	if (hasMapImage()) {
	    	if (zoomRatio < 0) {
	    		// mapImage has not zoomed yet.
	    		zoom(getZoomRatio());
	    	}
	
	    	// Draw map image.
	    	MapImageDisplay mapImageDisplay = getMapImageToDisplay();
	    	canvas.drawBitmap(mapImageDisplay.image,
	    			mapImageDisplay.leftTop.x, mapImageDisplay.leftTop.y, null);
    	} else {
    		Log.d(TAG, "There is no MapImage to draw.");
    	}

	    canvas.restore();
//	    super.onDraw(canvas);
	}
	
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		maxWidth = MeasureSpec.getSize(widthMeasureSpec);
		maxHeight = MeasureSpec.getSize(heightMeasureSpec);
    	Log.d(TAG, "onMeasure, width: " + maxWidth + ", height: " + maxHeight);
    	
    	if (hasMapImage()) {
    		initDisplayMapImage();
    	}
    	
    	// Call the super method.
    	super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
    
    /**
     * Check if the map image is set.
     * @return
     */
    private boolean hasMapImage() {
    	return mapImage != null;
    }
    
    /**
     * Calculate the minimum zoom ratio.
     */
    private void initDisplayMapImage() {
    	// Get the minimum zoom ratio.
    	zoomMinRatio = ImageUtil.sizeFitRatio(mapImage.getOriginalImageWidth(), mapImage.getOriginalImageHeight(),
    			maxWidth, maxHeight);
    	Log.d(TAG, "initDisplayMapImage. zoomMinRatio = " + zoomMinRatio);
    }
    
    /**
     * Zoom the map.
     * @param ratio The zooming ratio.
     */
    public void zoom(float ratio) {
    	Log.d(TAG, "zoom(" + ratio + ")");
    	if (ratio > 0) {
	    	zoomRatio = ratio;
	    	
	    	// Zoomed the image.
	    	mapImage.zoom(ratio);
	    	
	    	// Change image focus if zoomRatio <= zoomMinRatio.
	    	if (zoomRatio <= zoomMinRatio) {
	    		mapImage.resetFocus();
	    	}
	    	
	    	// Redraw map image and location.
	    	changeMapImage();
    	}
    }
    
    /**
     * Change the map image (zooming or changing focus).
     * This will change the position of locations, too.
     */
    private void changeMapImage() {
    	emptyMapImageDisplayRef();
    	
    	// Calculate the left-top position of the mapImage.
    	MapImageDisplay mapImageDisplay = getMapImageToDisplay();
    	Log.d(TAG, "mapImageDisplay position " + mapImageDisplay.leftTop);

    	// Resize and re-locate the locations
    	for (MapLocation location : locations) {
    		location.zoom(zoomRatio, mapImageDisplay.leftTop);
    	}

    	// Re-draw.
    	invalidate();
    }
    
    /**
     * Get zooming ratio.
     * @return
     */
	public float getZoomRatio() {
		if (zoomRatio < 0) {
			Log.d(TAG, "Set zoomRatio to minimum " + zoomMinRatio);
			zoomRatio = zoomMinRatio;
		}
		return zoomRatio;
	}
	
	/**
	 * Get the minimum zoom ratio that fit the image to the map.
	 * @return
	 */
	public float getZoomMinRatio() {
		return zoomMinRatio;
	}
	
	/**
	 * Get the map image to display.
	 * This will try to get the MapImageDisplay object from the weak reference
	 * before create new one.
	 * @return
	 */
	private MapImageDisplay getMapImageToDisplay() {
		// Return value. First get it from the weak reference.
		MapImageDisplay mapImageDisplay = mapImageDisplayRef.get();

		// Create new MapImageDisplay if cannot get it from weak reference.
		if (mapImageDisplay == null) {
			Log.d(TAG, "Create new MapImageDisplay and set to the weak reference");
	    	mapImageDisplay = mapImage.getImageToDisplay(getWidth(), getHeight());
	    	// Set the weak reference
			mapImageDisplayRef = new WeakReference<MapImageDisplay>(mapImageDisplay);
		}
		
		return mapImageDisplay;
	}
	
	/**
	 * Set the gesture listener for the map.
	 */
	protected void createGestureListener() {
		setGestureScanner(new GestureDetector(new OnGestureListener() {

			@Override
			public boolean onScroll(MotionEvent e1, MotionEvent e2,
					float distanceX, float distanceY) {
				handleScroll((int) distanceX, (int) distanceY);
				return true;
			}

			@Override
			public boolean onDown(MotionEvent arg0) {
				return true;
			}

			@Override
			public boolean onFling(MotionEvent e1, MotionEvent e2,
					float velocityX, float velocityY) {
				return true;
			}

			@Override
			public void onLongPress(MotionEvent e) {
				// Do nothing
			}

			@Override
			public void onShowPress(MotionEvent e) {
				// Do nothing
			}

			@Override
			public boolean onSingleTapUp(MotionEvent e) {
				return true;
			}}));
	}

	/**
	 * Handle when user scroll the map.
	 * This will change the focus of the mapImage.
	 * @param distX > 0 to left, < 0 to right.
	 * @param distY > 0 to up, < 0 to down.
	 */
    protected void handleScroll(int distX, int distY) {
    	Log.d(TAG, "handleScroll(" + distX + ", " + distY + ")");

    	// Get the current display image info.
    	MapImageDisplay mapImageDisplay = getMapImageToDisplay();
    	// Get right bottom position.
    	Position rightBottom = mapImageDisplay.getRightBottom();
    	Log.d(TAG, "mapImageDisplay " + mapImageDisplay.leftTop + ", " + rightBottom);

    	// Map scroll distance.
    	int scrollDistX = 0; // > 0: to right, < 0: to left.
    	int scrollDistY = 0; // > 0: to down, < 0: to up.
    	
    	// Scroll X-Axis
    	if (distX >= minScrollRate) {
    		// Scroll to left.
    		if (rightBottom.x > maxWidth) {
    			scrollDistX = rightBottom.x - maxWidth > distX ?
    					-distX : maxWidth - rightBottom.x;
    		}
    	} else if (distX <= -minScrollRate) {
    		// Scroll to right.
    		if (mapImageDisplay.leftTop.x < 0) {
    			scrollDistX = mapImageDisplay.leftTop.x < distX ?
    					-distX : -mapImageDisplay.leftTop.x;
    		}
    	}
    	// Scroll Y-Axis
    	if (distY >= minScrollRate) {
    		// Scroll up.
    		if (rightBottom.y > maxHeight) {
    			scrollDistY = rightBottom.y - maxHeight > distY ?
    					-distY : maxHeight - rightBottom.y;
    		}
    	} else if (distY <= -minScrollRate) {
    		// Scroll down.
    		if (mapImageDisplay.leftTop.y < 0) {
    			scrollDistY = mapImageDisplay.leftTop.y < distY ?
    					-distY : -mapImageDisplay.leftTop.y;
    		}
    	}
    	// Change the focus.
    	Log.d(TAG, "scrollDistX: " + scrollDistX + ", scrollDistY: " + scrollDistY);
    	if (scrollDistX != 0 || scrollDistY != 0) {
    		// Focus is moved conversely to the image moving direction.
    		mapImage.moveFocus(-scrollDistX, -scrollDistY);
	    	// Redraw map image and location.
	    	changeMapImage();
    	}
    }

    /**
     * Handle when the user touch on the map.
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return getGestureScanner().onTouchEvent(event);
    }

    /**
     * @return the gestureScanner
     */
    protected GestureDetector getGestureScanner() {
       return gestureScanner;
    }
    
    /**
     * @param gestureScanner the gestureScanner to set
     */
    protected void setGestureScanner(GestureDetector gestureScanner) {
       this.gestureScanner = gestureScanner;
    }

	protected void zoom(float ratio, float px, float py) {
		// TODO Auto-generated method stub
		
	}


}

