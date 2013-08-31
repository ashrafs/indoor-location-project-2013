package com.brian.android.mymap_aghort;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.wifi.ScanResult;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ZoomButtonsController;

//import com.google.android.maps.GeoPoint;
//import com.google.android.maps.MapActivity;
//import com.google.android.maps.MapController;
//import com.google.android.maps.MapView;

import com.brian.android.mymap.MapView;
import com.brian.android.util.ImageUtil;
import com.brian.android.mymap_aghort.WifiAdmin;
import com.brian.android.mymap_aghort.R;
import com.imagezoom.ImageAttacher;
import com.imagezoom.ImageAttacher.OnMatrixChangedListener;
import com.imagezoom.ImageAttacher.OnPhotoTapListener;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.webkit.WebSettings;

public class MainActivity extends Activity implements SensorEventListener {

	// protected MapView map;
	protected ImageView map;
	// private ImageView image;
	private StringBuffer sb = new StringBuffer();
	private Button ScanButton, TakeButton;
	private TextView allNetWork;
	private WifiAdmin mWifiAdmin;
	private List<ScanResult> list;
	private ScanResult mScanResult, mScanResult1, mScanResult2, HScanResult1,
			HScanResult2;
	boolean Rcheck, Rcheck1, Rcheck2, Hcheck1, Hcheck2;

	private float currentDegree = 0f;
	private SensorManager mSensorManager;
	public ZoomButtonsController zoomy;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// setContentView(R.layout.testlayout);

		// compass
		// image = (ImageView) findViewById(R.id.main_iv);
		mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
		mSensorManager.unregisterListener(this);
		mWifiAdmin = new WifiAdmin(MainActivity.this);

		// map
		// map = (MapView) findViewById(R.id.map);
		// map.setMapImage(ImageUtil.loadBitmapFromResource(getResources(),R.drawable.map3));
		map = (ImageView) findViewById(R.id.mapTest);

		Bitmap bimtBitmap = BitmapFactory.decodeResource(getResources(),
				R.drawable.homemap);
		map.setImageBitmap(bimtBitmap);

		/**
		 * Use Simple ImageView
		 */
		usingSimpleImage(map);

		// Zoom
		// map.setBuiltInZoomControls(true);

		// location
		allNetWork = (TextView) findViewById(R.id.allNetWork);
		addListenerOnButton();
		addListenerOnButton1();

	}

	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {

		// Handle item selection
		switch (item.getItemId()) {
		case R.id.AgHort_1:
			// map = (MapView) findViewById(R.id.map);
			// map.setMapImage(ImageUtil.loadBitmapFromResource(getResources(),R.drawable.map1));

			map = (ImageView) findViewById(R.id.mapTest);

			Bitmap bimtBitmap = BitmapFactory.decodeResource(getResources(),
					R.drawable.map1);
			map.setImageBitmap(bimtBitmap);
			return true;

		case R.id.AgHort_2:
			// map = (MapView) findViewById(R.id.map);
			// map.setMapImage(ImageUtil.loadBitmapFromResource(getResources(),R.drawable.map2));

			map = (ImageView) findViewById(R.id.mapTest);
			Bitmap bimtBitmap1 = BitmapFactory.decodeResource(getResources(),
					R.drawable.map2);
			map.setImageBitmap(bimtBitmap1);
			return true;

		case R.id.AgHort_3:
			// map = (MapView) findViewById(R.id.map);
			// map.setMapImage(ImageUtil.loadBitmapFromResource(getResources(),R.drawable.map3));

			map = (ImageView) findViewById(R.id.mapTest);
			Bitmap bimtBitmap3 = BitmapFactory.decodeResource(getResources(),
					R.drawable.map3);
			map.setImageBitmap(bimtBitmap3);
			return true;

		case R.id.Sensor_On:
			mSensorManager.registerListener(this,
					mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION),
					SensorManager.SENSOR_DELAY_GAME);
			return true;

		case R.id.Sensor_Off:
			mSensorManager.unregisterListener(this);
			return true;
			/*
			 * case R.id.settings: startActivity(new Intent(MainActivity.this,
			 * SettingPActivity.class)); return true;
			 */
		default:
			return super.onOptionsItemSelected(item);
		}
		/*
		 * if (item.getItemId() == R.id.AgHort_1) { map = (MapView)
		 * findViewById(R.id.map);
		 * map.setMapImage(ImageUtil.loadBitmapFromResource(getResources(),
		 * R.drawable.map1)); return true; } else if (item.getItemId() ==
		 * R.id.AgHort_2) { map = (MapView) findViewById(R.id.map);
		 * map.setMapImage(ImageUtil.loadBitmapFromResource(getResources(),
		 * R.drawable.map2)); return true; } else if (item.getItemId() ==
		 * R.id.AgHort_3) { map = (MapView) findViewById(R.id.map);
		 * map.setMapImage(ImageUtil.loadBitmapFromResource(getResources(),
		 * R.drawable.map3)); return true; } else if (item.getItemId() ==
		 * R.id.SCLocation){
		 * 
		 * } else if (item.getItemId() == R.id.SDestination){
		 * 
		 * }
		 * 
		 * return super.onOptionsItemSelected(item);
		 */
		/*
		 * if(item.getItemId()==R.id.AgHort_1){
		 * Toast.makeText(getApplicationContext(), "1",
		 * Toast.LENGTH_SHORT).show(); }else
		 * if(item.getItemId()==R.id.AgHort_2){
		 * Toast.makeText(getApplicationContext(), "2",
		 * Toast.LENGTH_SHORT).show(); }else
		 * if(item.getItemId()==R.id.AgHort_3){
		 * Toast.makeText(getApplicationContext(), "3",
		 * Toast.LENGTH_SHORT).show(); }
		 */

	}

	/*
	 * private MapLocation[] createLocations() { MapLocation[] locations = new
	 * MapLocation[] { new StateLocation(this, 720, 685, "Pond")}; return
	 * locations; }
	 */

	/*
	 * @Override public void onCreateContextMenu(ContextMenu menu, View v,
	 * ContextMenu.ContextMenuInfo menuInfo) { super.onCreateContextMenu(menu,
	 * v, menuInfo); getMenuInflater().inflate(R.menu.contextmenu, menu);
	 * menu.setHeaderTitle("Select the Level"); }
	 * 
	 * @Override public boolean onContextItemSelected(MenuItem item) { switch
	 * (item.getItemId()) { case R.id.AgHort_1: map = (MapView)
	 * findViewById(R.id.map);
	 * map.setMapImage(ImageUtil.loadBitmapFromResource(getResources(),
	 * R.drawable.map1)); return true; case R.id.AgHort_2: map = (MapView)
	 * findViewById(R.id.map);
	 * map.setMapImage(ImageUtil.loadBitmapFromResource(getResources(),
	 * R.drawable.map2)); return true; case R.id.AgHort_3: map = (MapView)
	 * findViewById(R.id.map);
	 * map.setMapImage(ImageUtil.loadBitmapFromResource(getResources(),
	 * R.drawable.map3)); return true; } return
	 * super.onContextItemSelected(item); }
	 */
	private void addListenerOnButton() {

		ScanButton = (Button) findViewById(R.id.ScanButton);

		ScanButton.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {

				getAllNetWorkList();
			}
		});
	}

	private void addListenerOnButton1() {

		final Context context = this;

		TakeButton = (Button) findViewById(R.id.takeMe);

		TakeButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(context, SettingDActivity.class);
				startActivity(intent);
			}
		});
	}

	/**
	 * Called when user clicks Zoom out button.
	 * 
	 * @param v
	 */
	/*
	 * public void zoomOut(View v) { float oldRatio = map.getZoomRatio(); float
	 * newRatio = oldRatio - .4f; if (newRatio < 0.4f) { newRatio = 0.4f; }
	 * map.zoom(newRatio); }
	 */
	/**
	 * Called when user clicks Zoom in button.
	 * 
	 * @param v
	 */
	/*
	 * public void zoomIn(View v) { float oldRatio = map.getZoomRatio(); float
	 * newRatio = oldRatio + .4f; if (newRatio > 0.8) { newRatio = 0.8f; }
	 * map.zoom(newRatio); }
	 */
	public void usingSimpleImage(ImageView imageView) {
		ImageAttacher mAttacher = new ImageAttacher(imageView);
		ImageAttacher.MAX_ZOOM = 2.0f; // Double the current Size
		ImageAttacher.MIN_ZOOM = 1.0f; // Half the current Size
		MatrixChangeListener mMaListener = new MatrixChangeListener();
		mAttacher.setOnMatrixChangeListener(mMaListener);
		PhotoTapListener mPhotoTap = new PhotoTapListener();
		mAttacher.setOnPhotoTapListener(mPhotoTap);
	}

	private class PhotoTapListener implements OnPhotoTapListener {

		@Override
		public void onPhotoTap(View view, float x, float y) {
		}
	}

	private class MatrixChangeListener implements OnMatrixChangedListener {

		@Override
		public void onMatrixChanged(RectF rect) {

		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		// 为系统的方向传感器注册监听器
		mSensorManager.registerListener(this,
				mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION),
				SensorManager.SENSOR_DELAY_GAME);
	}

	@Override
	protected void onPause() {
		super.onPause();
		// 取消注册
		mSensorManager.unregisterListener(this);
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {

	}

	@Override
	public void onSensorChanged(SensorEvent event) {

		// 如果真机上触发event的传感器类型为水平传感器类型
		if (event.sensor.getType() == Sensor.TYPE_ORIENTATION) {
			// 获取绕Z轴转过的角度
			float degree = event.values[0];

			if (currentDegree <= 150 && currentDegree >= 100) {

				currentDegree = 360;
				// 创建旋转动画（反向转过degree度）
				RotateAnimation ra = new RotateAnimation(currentDegree,
						-degree, Animation.RELATIVE_TO_SELF, 0.5f,
						Animation.RELATIVE_TO_SELF, 0.5f);
				// 设置动画的持续时间
				ra.setDuration(200);
				// 设置动画结束后的保留状态
				ra.setFillAfter(true);
				// 启动动画
				// compass rotate
				// image.startAnimation(ra);

				// map rotate
				// map.startAnimation(ra);
				currentDegree = -degree;
			} else {

				// 创建旋转动画（反向转过degree度）
				RotateAnimation ra = new RotateAnimation(currentDegree,
						-degree, Animation.RELATIVE_TO_SELF, 0.5f,
						Animation.RELATIVE_TO_SELF, 0.5f);
				// 设置动画的持续时间
				ra.setDuration(200);
				// 设置动画结束后的保留状态
				ra.setFillAfter(true);
				// 启动动画
				// compass rotate
				// image.startAnimation(ra);

				// map rotate

				// need to check rather the sensor is on or not -- to do

				// map.startAnimation(ra);
				currentDegree = -degree;
			}
		}
	}

	/**
	 * Called when user clicks Scan in button.
	 * 
	 * @param v
	 */

	public void getAllNetWorkList() {

		// Clear previous Scan record
		if (sb != null) {
			sb = new StringBuffer();
			allNetWork.setText("");

		}

		// Start Scan Network
		mWifiAdmin.startScan();
		list = mWifiAdmin.getWifiList();

		if (list != null) {

			for (int i = 0; i < list.size(); i++) {

				RelativeLayout rlMain = (RelativeLayout) findViewById(R.id.relativelayout);
				RelativeLayout.LayoutParams params1 = new RelativeLayout.LayoutParams(
						50, 50);
				RelativeLayout.LayoutParams params2 = new RelativeLayout.LayoutParams(
						50, 50);
				ImageView iv1 = new ImageView(this);
				ImageView iv2 = new ImageView(this);
				iv1.setBackgroundColor(Color.WHITE);
				iv2.setBackgroundColor(Color.WHITE);

				// Get Scan Result
				mScanResult = list.get(0);
				mScanResult1 = list.get(i);
				mScanResult2 = list.get(i);
				HScanResult1 = list.get(0);
				HScanResult2 = list.get(i);
				// sb = sb.append(mScanResult.level +"dBm" + "\n\n");

				// sb = sb.append(mWifiAdmin.getWifiInfo());

				sb = sb.append(mScanResult.BSSID + "   ")
						.append(mScanResult.SSID + "   ")
						.append(mScanResult.capabilities + "   ")
						.append(mScanResult.frequency + "Hz" + "   ")
						.append(mScanResult.level + "dBm" + "\n\n");

				/*
				 * if (mScanResult.level < -65) {
				 * //------------------------------
				 * ------------------------------
				 * ------------------------------------------------------//
				 * 
				 * if (mScanResult.SSID.toString().equals("MUStudents")) {
				 * 
				 * if (mScanResult.BSSID.toString().equals( "ac:16:2d:e7:f4:02")
				 * || mScanResult.BSSID.toString().equals( "ac:16:2d:e7:e4:e2")
				 * || mScanResult.BSSID.toString().equals( "ac:16:2d:e7:e4:41"))
				 * { Rcheck = true; if
				 * (mScanResult1.SSID.toString().equals("MUStaff")) { if
				 * (mScanResult1.BSSID.toString().equals( "ac:16:2d:e7:e4:40"))
				 * { Rcheck1 = true; if (mScanResult2.SSID.toString().equals(
				 * "EduRoam")) { if
				 * (mScanResult2.BSSID.toString().equals("ac:16:2d:e7:e4:43")) {
				 * Rcheck2 = true; } } } } } }
				 * 
				 * if (mScanResult.SSID.toString().equals("MUStaff")) { if
				 * (mScanResult.BSSID.toString().equals( "ac:16:2d:e7:e4:40")) {
				 * Rcheck = true; if
				 * (mScanResult1.SSID.toString().equals("EduRoam")) { if
				 * (mScanResult1.BSSID.toString().equals( "ac:16:2d:e7:e4:43"))
				 * { Rcheck1 = true; if (mScanResult2.SSID.toString().equals(
				 * "MUStudents")) { if
				 * (mScanResult2.BSSID.toString().equals("ac:16:2d:e7:f4:02") ||
				 * mScanResult2.BSSID.toString().equals("ac:16:2d:e7:e4:e2") ||
				 * mScanResult2.BSSID.toString().equals("ac:16:2d:e7:e4:41")) {
				 * Rcheck2 = true; } } } } } }
				 * 
				 * if (mScanResult.SSID.toString().equals("EduRoam")) { if
				 * (mScanResult.BSSID.toString().equals( "ac:16:2d:e7:e4:43")) {
				 * Rcheck = true; if (mScanResult1.SSID.toString().equals(
				 * "MUStudents")) { if (mScanResult1.BSSID.toString().equals(
				 * "ac:16:2d:e7:f4:02") ||
				 * mScanResult1.BSSID.toString().equals("ac:16:2d:e7:e4:e2") ||
				 * mScanResult1.BSSID.toString().equals("ac:16:2d:e7:e4:41")) {
				 * Rcheck1 = true; if (mScanResult2.SSID.toString().equals(
				 * "MUStaff")) { if
				 * (mScanResult2.BSSID.toString().equals("ac:16:2d:e7:e4:40")) {
				 * Rcheck2 = true; } } } } } } /*
				 * if(mScanResult1.SSID.toString().equals("MUStaff")) { if
				 * (mScanResult1 .BSSID.toString().equals("ac:16:2d:e7:e4:40"))
				 * { Rcheck1 = true; } }
				 * 
				 * if (mScanResult2.SSID.toString().equals("EduRoam")) { if
				 * (mScanResult2.BSSID.toString().equals( "ac:16:2d:e7:e4:43"))
				 * { Rcheck2 = true; } }
				 * 
				 * 
				 * if (Rcheck == true || Rcheck1 == true || Rcheck2 == true) {
				 * // check MUStudent found or MUStaff found or EduRoam // found
				 * allNetWork.setText("You are around Room 3.65"); // map =
				 * (MapView) findViewById(R.id.map); //
				 * map.setMapImage(ImageUtil
				 * .loadBitmapFromResource(getResources(), // R.drawable.map4));
				 * Toast.makeText(getApplicationContext(),
				 * "Locate by either MUStudent or MUStaff or EduRoam",
				 * Toast.LENGTH_SHORT).show(); }
				 * 
				 * else { if (Rcheck == true && Rcheck1 == true || Rcheck2 ==
				 * true) { // check MUStudent found and MUStaff found or //
				 * EduRoam found allNetWork.setText("You are around Room 3.65");
				 * map = (MapView) findViewById(R.id.map);
				 * map.setMapImage(ImageUtil.loadBitmapFromResource(
				 * getResources(), R.drawable.map4)); Toast.makeText(
				 * getApplicationContext(),
				 * "Locate by MUStudent and MUStaff or EduRoam",
				 * Toast.LENGTH_SHORT).show(); } else { if (Rcheck == true ||
				 * Rcheck1 == true && Rcheck2 == true) { // check MUStudent
				 * found or MUStaff found and // EduRoam found
				 * allNetWork.setText("You are around Room 3.65"); map =
				 * (MapView) findViewById(R.id.map);
				 * map.setMapImage(ImageUtil.loadBitmapFromResource
				 * (getResources(), R.drawable.map4)); Toast.makeText(
				 * getApplicationContext(),
				 * "Locate by MUStudent or MUStaff and EduRoam",
				 * Toast.LENGTH_SHORT).show(); } else { if (Rcheck == true &&
				 * Rcheck1 == true && Rcheck2 == true) { // check MUStudent //
				 * found and // MUStaff found // and EduRoam // found
				 * allNetWork.setText("You are around Room 3.65"); map =
				 * (MapView) findViewById(R.id.map);
				 * map.setMapImage(ImageUtil.loadBitmapFromResource
				 * (getResources(), R.drawable.map4)); Toast.makeText(
				 * getApplicationContext(),
				 * "Locate by MUStudent, MUStaff and EduRoam",
				 * Toast.LENGTH_SHORT).show(); } else {
				 * allNetWork.setText("You are not around Room 3.65 "); // end
				 * // of // scan // check } } } }
				 * 
				 * } else { // if signal strength is greater than -85dB
				 * Toast.makeText(getApplicationContext(),
				 * "No Wifi for 3.65",Toast.LENGTH_SHORT).show();// end of Room
				 * check 3.65 }
				 */
				// -------------------------------------------------------------------------------------------------------------------------------------//



				if (mScanResult.level >= -78) {

					// For Home Use
					if (HScanResult1.SSID.toString().equals("Vodafone0BF3")
							|| HScanResult2.SSID.toString()
									.equals("NETGEAR_18")) // Check Vodafone
					{
						Hcheck1 = true;

						if (HScanResult1.SSID.toString().equals("NETGEAR_18")
								|| HScanResult2.SSID.toString().equals(
										"Vodafone0BF3")) // Check NETGEAR
						{
							Hcheck2 = true;
						}
					}

					if (Hcheck1 == true && Hcheck2 == true) {
						allNetWork.setText("You are around home" + " "
								+ mScanResult.level);
						Toast.makeText(getApplicationContext(),
								"Locate by NETGEAR_18 and Vodafone",
								Toast.LENGTH_SHORT).show();

						// iv.setImageResource(R.drawable.location_icon);
						iv2.setBackgroundColor(Color.WHITE);
						params2.topMargin = 100;
						params2.leftMargin = 100;
						rlMain.addView(iv2, params2);
						
						
						iv1.setBackgroundColor(Color.RED);
						params1.topMargin = 150;
						params1.leftMargin = 230;
						rlMain.addView(iv1, params1);

					} else {

						if (Hcheck1 == true || Hcheck2 == true) {
							allNetWork.setText("You are around home" + " "
									+ mScanResult.level);
							Toast.makeText(getApplicationContext(),
									"Locate by NETGEAR_18 or Vodafone",
									Toast.LENGTH_SHORT).show();
						} else {
							allNetWork.setText("You are not around Home ");
						}
					}
				}
				if (mScanResult.level <= -79) {
					// For Home Use
					if (HScanResult1.SSID.toString().equals("Vodafone0BF3")
							|| HScanResult2.SSID.toString()
									.equals("NETGEAR_18")) // Check Vodafone
					{
						Hcheck1 = true;

						if (HScanResult1.SSID.toString().equals("NETGEAR_18")
								|| HScanResult2.SSID.toString().equals(
										"Vodafone0BF3")) // Check NETGEAR
						{
							Hcheck2 = true;
						}
					}

					if (Hcheck1 == true && Hcheck2 == true) {
						allNetWork.setText("You are around home" + " "
								+ mScanResult.level);
						Toast.makeText(getApplicationContext(),
								"Locate by NETGEAR_18 and Vodafone",
								Toast.LENGTH_SHORT).show();

						// iv.setImageResource(R.drawable.location_icon);
						iv1.setBackgroundColor(Color.WHITE);
						params1.topMargin = 150;
						params1.leftMargin = 230;
						rlMain.addView(iv1, params1);
						
						iv2.setBackgroundColor(Color.BLUE);
						params2.topMargin = 100;
						params2.leftMargin = 100;
						rlMain.addView(iv2, params2);
						
						
					}
				}
			}
		}
	}
}
/*
 * private void DisplayWifiState() {
 * 
 * ConnectivityManager myConnManager = (ConnectivityManager)
 * getSystemService(CONNECTIVITY_SERVICE);
 * myConnManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI); WifiManager
 * myWifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
 * WifiInfo myWifiInfo = myWifiManager.getConnectionInfo();
 * textBssid.setText(myWifiInfo.getBSSID()); String CurrentMac =
 * "a0:21:b7:70:b4:cc"; String CurrentMac1 = "ac:16:2d:e7:e4:f2"; String
 * SearchedMac = textBssid.getText().toString();
 * 
 * if (SearchedMac.equals(CurrentMac)) { Toast toast =
 * Toast.makeText(getApplicationContext(), "Matches", Toast.LENGTH_SHORT);
 * toast.show(); } else { Toast toast = Toast.makeText(getApplicationContext(),
 * "Noooo", Toast.LENGTH_SHORT); toast.show(); }
 * 
 * if (SearchedMac.equals(CurrentMac1)) { Toast toast =
 * Toast.makeText(getApplicationContext(), "Matches", Toast.LENGTH_SHORT);
 * toast.show(); } else { Toast toast = Toast.makeText(getApplicationContext(),
 * "Noooo", Toast.LENGTH_SHORT); toast.show(); } }
 */