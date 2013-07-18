package com.brian.android.mymap_aghort;

import android.content.Context;
import android.util.AttributeSet;

import com.brian.android.mymap.MapView;

/**
 * Extend the MapView so that it can only be zoomed between
 * the ratio that fits it to the screen and the real size of
 * the map image (I assume that the image is larger than the
 * screen).
 *
 * Notice that the MapView.getZoomFitRatio() returns the ratio
 * that make the map fit to the screen.
 *
 * @author umbalaconmeogia
 *
 */
public class NextMap extends MapView {

  public NextMap(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  /**
   * Limit the zoom ratio so that the map can only be zoomed
   * between the ratio that fits it to the screen and the real
   * size of the map image
   */
  @Override
  protected void zoom(float ratio, float px, float py) {
    float zoomFitRatio = getZoomMinRatio();
    if (ratio < zoomFitRatio) {
      ratio = zoomFitRatio;
    } else if (ratio > 1f) {
      ratio = 1f;
    }
    super.zoom(ratio, px, py);
  }

//  /**
//   * Limit the zoom ratio so that the map cannot be zoomed
//   * larger than the screen.
//   */
//  @Override
//  protected void zoom(float ratio, float px, float py) {
//    float zoomFitRatio = getZoomFitRatio();
//    if (ratio > zoomFitRatio) {
//      ratio = zoomFitRatio;
//    }
//    super.zoom(ratio, px, py);
//  }

}
