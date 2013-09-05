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
	private StringBuffer hsb1 = new StringBuffer();
	private StringBuffer hsb2 = new StringBuffer();
	private StringBuffer hsb3 = new StringBuffer();
	private StringBuffer hsb4 = new StringBuffer();
	private StringBuffer hsb5 = new StringBuffer();
	private StringBuffer lsb1 = new StringBuffer();
	private StringBuffer lsb2 = new StringBuffer();
	private StringBuffer lsb3 = new StringBuffer();
	private StringBuffer lsb4 = new StringBuffer();
	private StringBuffer lsb5 = new StringBuffer();
	private Button ScanButton, TakeButton;
	private TextView allNetWork;
	private WifiAdmin mWifiAdmin;
	private List<ScanResult> list;
	private ScanResult mScanResult, mScanResult1, mScanResult2, HScanResult1,
			HScanResult2, HScanResult3, HScanResult4, HScanResult5,
			LScanResult1, LScanResult2, LScanResult3, LScanResult4,
			LScanResult5;
	boolean Rcheck, Rcheck1, Rcheck2;
	boolean HcheckL1, HcheckL2,HcheckL3, HcheckH1, HcheckH2, HcheckH3;
	boolean Lcheck1, Lcheck2, Lcheck3, Lcheck4, Lcheck5, Lcheck6, Lcheck7,
			Lcheck8, Lcheck9, Lcheck10, Lcheck11, Lcheck12;

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

		// Bitmap bimtBitmap =
		// BitmapFactory.decodeResource(getResources(),R.drawable.librarymap);
		// map.setImageBitmap(bimtBitmap);

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
			map = (ImageView) findViewById(R.id.mapTest);

			Bitmap bimtBitmap1 = BitmapFactory.decodeResource(getResources(),
					R.drawable.map1);
			map.setImageBitmap(bimtBitmap1);
			return true;

		case R.id.AgHort_2:
			map = (ImageView) findViewById(R.id.mapTest);
			Bitmap bimtBitmap2 = BitmapFactory.decodeResource(getResources(),
					R.drawable.map2);
			map.setImageBitmap(bimtBitmap2);
			return true;

		case R.id.AgHort_3:
			map = (ImageView) findViewById(R.id.mapTest);
			Bitmap bimtBitmap3 = BitmapFactory.decodeResource(getResources(),
					R.drawable.map3);
			map.setImageBitmap(bimtBitmap3);
			return true;

		case R.id.homemap:
			map = (ImageView) findViewById(R.id.mapTest);
			Bitmap HomeBitmap = BitmapFactory.decodeResource(getResources(),
					R.drawable.homemap);
			map.setImageBitmap(HomeBitmap);
			return true;

		case R.id.librarymap:
			map = (ImageView) findViewById(R.id.mapTest);
			@SuppressWarnings("deprecation")
			Bitmap LibraryBitmap = BitmapFactory.decodeResource(getResources(),
					R.drawable.librarymap);
			map.setImageBitmap(LibraryBitmap);
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

				// Display message that Wi-Fi is off
				if (!mWifiAdmin.mWifiManager.isWifiEnabled()){
					Toast.makeText(getApplicationContext(),"Please switch on Wi-Fi",Toast.LENGTH_SHORT).show();
					//finish();
				}
				
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
		if (sb != null || hsb1 != null || hsb2 != null || hsb3 != null || hsb4 != null || hsb5 != null
				|| lsb1 != null || lsb2 != null || lsb3 != null || lsb4 != null || lsb5 != null) {
			// sb = new StringBuffer();
			hsb1 = new StringBuffer();
			hsb2 = new StringBuffer();
			hsb3 = new StringBuffer();
			hsb4 = new StringBuffer();
			hsb5 = new StringBuffer(); 
			lsb1 = new StringBuffer();
			lsb2 = new StringBuffer();
			lsb3 = new StringBuffer();
			lsb4 = new StringBuffer();
			lsb5 = new StringBuffer();
			allNetWork.setText("");
		}

		// Start Scan Network
		mWifiAdmin.startScan();
		list = mWifiAdmin.getWifiList();

		if (list != null) {

			// for (int i = 0; i < list.size(); i++) {

			RelativeLayout rlMain = (RelativeLayout) findViewById(R.id.relativelayout);
			RelativeLayout.LayoutParams hparams1 = new RelativeLayout.LayoutParams(
					25, 25);
			RelativeLayout.LayoutParams hparams2 = new RelativeLayout.LayoutParams(
					25, 25);
			RelativeLayout.LayoutParams lparams1 = new RelativeLayout.LayoutParams(
					25, 25);
			RelativeLayout.LayoutParams lparams2 = new RelativeLayout.LayoutParams(
					25, 25);
			RelativeLayout.LayoutParams lparams3 = new RelativeLayout.LayoutParams(
					25, 25);
			RelativeLayout.LayoutParams lparams4 = new RelativeLayout.LayoutParams(
					25, 25);
			ImageView hv1 = new ImageView(this);
			ImageView hv2 = new ImageView(this);
			ImageView lv1 = new ImageView(this);
			ImageView lv2 = new ImageView(this);
			ImageView lv3 = new ImageView(this);
			ImageView lv4 = new ImageView(this);
			hv1.setBackgroundColor(Color.TRANSPARENT);
			hv2.setBackgroundColor(Color.TRANSPARENT);
			lv1.setBackgroundColor(Color.TRANSPARENT);
			lv2.setBackgroundColor(Color.TRANSPARENT);
			lv3.setBackgroundColor(Color.TRANSPARENT);
			lv4.setBackgroundColor(Color.TRANSPARENT);
			HcheckH1 = false;
			HcheckH2 = false;
			HcheckH3 = false;
			HcheckL1 = false;
			HcheckL2 = false;
			HcheckL3 = false;
			Lcheck1 = false;
			Lcheck2 = false;
			Lcheck3 = false;
			Lcheck4 = false;
			Lcheck5 = false;
			Lcheck6 = false;
			Lcheck7 = false;
			Lcheck8 = false;
			Lcheck9 = false;
			Lcheck10 = false;
			Lcheck11 = false;
			Lcheck12 = false;
			hsb1 = new StringBuffer();
			hsb2 = new StringBuffer();
			hsb3 = new StringBuffer();
			hsb4 = new StringBuffer();
			hsb5 = new StringBuffer();
			lsb1 = new StringBuffer();
			lsb2 = new StringBuffer();
			lsb3 = new StringBuffer();
			lsb4 = new StringBuffer();
			lsb5 = new StringBuffer();

			// Get Scan Result
			// mScanResult = list.get(0);
			// mScanResult1 = list.get(1);
			// mScanResult2 = list.get(2);
			HScanResult1 = list.get(0);
			LScanResult1 = list.get(0);

			if (list.size() > 1) {
				HScanResult2 = list.get(1);
				LScanResult2 = list.get(1);
			}
			if (list.size() > 2) {
				HScanResult3 = list.get(2);
				LScanResult3 = list.get(2);
			}
			if (list.size() > 3) {
				HScanResult4 = list.get(3);
				LScanResult4 = list.get(3);
			}
			if (list.size() > 4) {
				HScanResult5 = list.get(4);
				LScanResult5 = list.get(4);
			}

			// Home Use
			hsb1 = hsb1.append(HScanResult1.SSID);
			hsb2 = hsb2.append(HScanResult2.SSID);

			// Library Use
			lsb1 = lsb1.append(LScanResult1.BSSID);
			lsb2 = lsb2.append(LScanResult2.BSSID);

			if (list.size() > 2) {
				hsb3 = hsb3.append(LScanResult3.SSID);
				lsb3 = lsb3.append(LScanResult3.BSSID);
			}
			if (list.size() > 3) {
				hsb4 = hsb4.append(LScanResult4.SSID);
				lsb4 = lsb4.append(LScanResult4.BSSID);
			}
			if (list.size() > 4) {
				hsb5 = hsb5.append(LScanResult5.SSID);
				lsb5 = lsb5.append(LScanResult5.BSSID);
			}

			/*
			 * if (mScanResult.level < -65) { //------------------------------
			 * ------------------------------
			 * ------------------------------------------------------//
			 * 
			 * if (mScanResult.SSID.toString().equals("MUStudents")) {
			 * 
			 * if (mScanResult.BSSID.toString().equals( "ac:16:2d:e7:f4:02") ||
			 * mScanResult.BSSID.toString().equals( "ac:16:2d:e7:e4:e2") ||
			 * mScanResult.BSSID.toString().equals( "ac:16:2d:e7:e4:41")) {
			 * Rcheck = true; if
			 * (mScanResult1.SSID.toString().equals("MUStaff")) { if
			 * (mScanResult1.BSSID.toString().equals( "ac:16:2d:e7:e4:40")) {
			 * Rcheck1 = true; if (mScanResult2.SSID.toString().equals(
			 * "EduRoam")) { if
			 * (mScanResult2.BSSID.toString().equals("ac:16:2d:e7:e4:43")) {
			 * Rcheck2 = true; } } } } } }
			 * 
			 * if (mScanResult.SSID.toString().equals("MUStaff")) { if
			 * (mScanResult.BSSID.toString().equals( "ac:16:2d:e7:e4:40")) {
			 * Rcheck = true; if
			 * (mScanResult1.SSID.toString().equals("EduRoam")) { if
			 * (mScanResult1.BSSID.toString().equals( "ac:16:2d:e7:e4:43")) {
			 * Rcheck1 = true; if (mScanResult2.SSID.toString().equals(
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
			 * (mScanResult1 .BSSID.toString().equals("ac:16:2d:e7:e4:40")) {
			 * Rcheck1 = true; } }
			 * 
			 * if (mScanResult2.SSID.toString().equals("EduRoam")) { if
			 * (mScanResult2.BSSID.toString().equals( "ac:16:2d:e7:e4:43")) {
			 * Rcheck2 = true; } }
			 * 
			 * 
			 * if (Rcheck == true || Rcheck1 == true || Rcheck2 == true) { //
			 * check MUStudent found or MUStaff found or EduRoam // found
			 * allNetWork.setText("You are around Room 3.65"); // map =
			 * (MapView) findViewById(R.id.map); // map.setMapImage(ImageUtil
			 * .loadBitmapFromResource(getResources(), // R.drawable.map4));
			 * Toast.makeText(getApplicationContext(),
			 * "Locate by either MUStudent or MUStaff or EduRoam",
			 * Toast.LENGTH_SHORT).show(); }
			 * 
			 * else { if (Rcheck == true && Rcheck1 == true || Rcheck2 == true)
			 * { // check MUStudent found and MUStaff found or // EduRoam found
			 * allNetWork.setText("You are around Room 3.65"); map = (MapView)
			 * findViewById(R.id.map);
			 * map.setMapImage(ImageUtil.loadBitmapFromResource( getResources(),
			 * R.drawable.map4)); Toast.makeText( getApplicationContext(),
			 * "Locate by MUStudent and MUStaff or EduRoam",
			 * Toast.LENGTH_SHORT).show(); } else { if (Rcheck == true ||
			 * Rcheck1 == true && Rcheck2 == true) { // check MUStudent found or
			 * MUStaff found and // EduRoam found
			 * allNetWork.setText("You are around Room 3.65"); map = (MapView)
			 * findViewById(R.id.map);
			 * map.setMapImage(ImageUtil.loadBitmapFromResource (getResources(),
			 * R.drawable.map4)); Toast.makeText( getApplicationContext(),
			 * "Locate by MUStudent or MUStaff and EduRoam",
			 * Toast.LENGTH_SHORT).show(); } else { if (Rcheck == true &&
			 * Rcheck1 == true && Rcheck2 == true) { // check MUStudent // found
			 * and // MUStaff found // and EduRoam // found
			 * allNetWork.setText("You are around Room 3.65"); map = (MapView)
			 * findViewById(R.id.map);
			 * map.setMapImage(ImageUtil.loadBitmapFromResource (getResources(),
			 * R.drawable.map4)); Toast.makeText( getApplicationContext(),
			 * "Locate by MUStudent, MUStaff and EduRoam",
			 * Toast.LENGTH_SHORT).show(); } else {
			 * allNetWork.setText("You are not around Room 3.65 "); // end // of
			 * // scan // check } } } }
			 * 
			 * } else { // if signal strength is greater than -85dB
			 * Toast.makeText(getApplicationContext(),
			 * "No Wifi for 3.65",Toast.LENGTH_SHORT).show();// end of Room
			 * check 3.65 }
			 */
			// -------------------------------------------------------------------------------------------------------------------------------------//

			if (HScanResult1.level >= -78) {// -- Less than or equal to -78dB	

				// usedHOMEMap = true;
				// For Home Use
				
				if (hsb1.toString().equals("NETGEAR_18")
						|| hsb2.toString().equals("vodafone0BF3")
						|| hsb3.toString().equals("AndroidAP")) {
					HcheckL1 = true;
					// allNetWork.setText(hsb1.toString());
					// Toast.makeText(getApplicationContext(),hsb1.toString(),Toast.LENGTH_SHORT).show();
				}
				if (hsb2.toString().equals("vodafone0BF3")
						|| hsb1.toString().equals("NETGEAR_18")
						|| hsb3.toString().equals("AndroidAP")) {
					HcheckL2 = true;
					// allNetWork.setText(hsb2.toString());
					// Toast.makeText(getApplicationContext(),hsb2.toString(),Toast.LENGTH_SHORT).show();
				}
				if (hsb3.toString().equals("vodafone0BF3")
						|| hsb3.toString().equals("NETGEAR_18")
						|| hsb3.toString().equals("AndroidAP")) {
					HcheckL3 = true;
					// allNetWork.setText(hsb2.toString());
					// Toast.makeText(getApplicationContext(),hsb2.toString(),Toast.LENGTH_SHORT).show();
				}
				

				if (HcheckL1 == true && HcheckL2 == true && HcheckL3 == true) {
					map = (ImageView) findViewById(R.id.mapTest);
					Bitmap HomeBitmap = BitmapFactory.decodeResource(
							getResources(), R.drawable.homemap);
					map.setImageBitmap(HomeBitmap);
					allNetWork.setText("You are around home" + " "
							+ HScanResult1.level);
					// Toast.makeText(getApplicationContext(),"Locate by NETGEAR_18 and Vodafone",Toast.LENGTH_SHORT).show();

					// iv.setImageResource(R.drawable.location_icon);
					
					

					hv1.setBackgroundColor(Color.RED);
					hparams1.topMargin = 150;
					hparams1.leftMargin = 230;
					rlMain.addView(hv1, hparams1);
					
					hv2.setBackgroundColor(Color.WHITE);
					hparams2.topMargin = 100;
					hparams2.leftMargin = 100;
					rlMain.addView(hv2, hparams2);
				}
				else
					if(HcheckL1 == true && HcheckL2 == true && HcheckL3 == false|| HcheckL1 == true && HcheckL3 == true && HcheckL2 == false ||HcheckL3 == true && HcheckL2 == true && HcheckL1 == false){
						map = (ImageView) findViewById(R.id.mapTest);
						Bitmap HomeBitmap = BitmapFactory.decodeResource(
								getResources(), R.drawable.homemap);
						map.setImageBitmap(HomeBitmap);
						allNetWork.setText("You are around home" + " "
								+ HScanResult1.level);
						//Toast.makeText(getApplicationContext(),"Locate by NETGEAR_18 and Vodafone",Toast.LENGTH_SHORT).show();

						// iv.setImageResource(R.drawable.location_icon);						
						

						hv2.setBackgroundColor(Color.WHITE);
						hparams2.topMargin = 100;
						hparams2.leftMargin = 100;
						rlMain.addView(hv2, hparams2);	
						
						hv1.setBackgroundColor(Color.BLUE);
						hparams1.topMargin = 150;
						hparams1.leftMargin = 230;
						rlMain.addView(hv1, hparams1);
					}
			}

			// if (HScanResult1.level <= -78) //-- Greater than or equal to
			// -79dB
			if (HScanResult1.level <= -78) {
				// For Home Use
				if (hsb1.toString().equals("NETGEAR_18")
						|| hsb1.toString().equals("vodafone0BF3")
						|| hsb1.toString().equals("TNCAP5DC00F")
						|| hsb1.toString().equals("Sophie_Thomas")
						|| hsb1.toString().equals("T & P on Air 99.4")) // Check
																	// Vodafone
				{
					HcheckH1 = true;
				}

				if (hsb2.toString().equals("vodafone0BF3")
						|| hsb2.toString().equals("NETGEAR_18")
						|| hsb2.toString().equals("TNCAP5DC00F")
						|| hsb2.toString().equals("Sophie_Thomas")
						|| hsb2.toString().equals("T & P on Air 99.4")) // Check
																	// NETGEAR
				{
					HcheckH2 = true;
				}

				if (HcheckH1 == true && HcheckH2 == true ) {
					map = (ImageView) findViewById(R.id.mapTest);
					Bitmap HomeBitmap = BitmapFactory.decodeResource(
							getResources(), R.drawable.homemap);
					map.setImageBitmap(HomeBitmap);
					allNetWork.setText("You are around home" + " "
							+ HScanResult1.level);
					//Toast.makeText(getApplicationContext(),"Locate by NETGEAR_18 and Vodafone",Toast.LENGTH_SHORT).show();

					// iv.setImageResource(R.drawable.location_icon);
					hv2.setBackgroundColor(Color.WHITE);
					hparams2.topMargin = 150;
					hparams2.leftMargin = 230;
					rlMain.addView(hv2, hparams2);

					hv1.setBackgroundColor(Color.BLUE);
					hparams1.topMargin = 100;
					hparams1.leftMargin = 100;
					rlMain.addView(hv1, hparams1);

				}

			}
			// -------------------------------------------------------------------------------------------------------------//

			// City Library test
			if (HcheckL1 == false && HcheckL2 == false && HcheckL3 == false && HcheckH1 == false && HcheckH2 == false) {
				
				//Load and display Library Map
				map = (ImageView) findViewById(R.id.mapTest);
				Bitmap LibraryBitmap = BitmapFactory.decodeResource(
						getResources(), R.drawable.librarymap);
				map.setImageBitmap(LibraryBitmap);
				allNetWork.setText("");

				// For Library Use
				
//----------------- Position 1--------------------------------------------

				if (lsb1.toString().equals("c2:9f:db:67:5a:57")&& (LScanResult1.level > -70)	//PN Library wi-fi
						|| lsb1.toString().equals("00:13:c3:f1:37:b1")		//studentcity
						|| lsb1.toString().equals("00:13:c4:c3:9c:31")		//studentcity
						|| lsb1.toString().equals("00:13:c4:c3:9c:30")		//inspirefreewifi
						|| lsb1.toString().equals("00:13:c3:f1:37:b0")		//inspirefreewifi
						|| lsb1.toString().equals("62:f3:a3:ce:d8:9c")		//vodafoneD89F
						|| lsb1.toString().equals("f0:7d:68:f7:cb:9e")		//GCI
						|| lsb1.toString().equals("00:17:df:10:84:10")		//PPTA
						|| lsb1.toString().equals("00:18:4d:d3:d7:e8")		//PPTA1
						|| lsb1.toString().equals("24:db:ac:71:58:5b")		//VodafoneMobileWiFi-585B61
						|| lsb1.toString().equals("00:0c:c3:e5:a7:88")		//Telecom-6320
						|| lsb1.toString().equals("00:22:75:29:f0:99")		//Barista Cafe
						|| lsb1.toString().equals("58:98:35:96:74:d1")) 
				{
					Lcheck1 = true;
					Toast.makeText(getApplicationContext(), "Lcheck1 OK",
							Toast.LENGTH_SHORT).show();
				}

				if (lsb2.toString().equals("c2:9f:db:67:5a:57")&& (LScanResult2.level > -70)	//PN Library wi-fi
						|| lsb2.toString().equals("00:13:c3:f1:37:b1")		//studentcity
						|| lsb2.toString().equals("00:13:c4:c3:9c:31")		//studentcity
						|| lsb2.toString().equals("00:13:c4:c3:9c:30")		//inspirefreewifi
						|| lsb2.toString().equals("00:13:c3:f1:37:b0")		//inspirefreewifi
						|| lsb2.toString().equals("62:f3:a3:ce:d8:9c")		//vodafoneD89F
						|| lsb2.toString().equals("f0:7d:68:f7:cb:9e")		//GCI
						|| lsb2.toString().equals("00:17:df:10:84:10")		//PPTA
						|| lsb2.toString().equals("00:18:4d:d3:d7:e8")		//PPTA1
						|| lsb2.toString().equals("24:db:ac:71:58:5b")		//VodafoneMobileWiFi-585B61
						|| lsb2.toString().equals("00:0c:c3:e5:a7:88")		//Telecom-6320
						|| lsb2.toString().equals("00:22:75:29:f0:99")		//Barista Cafe
						|| lsb2.toString().equals("58:98:35:96:74:d1")) 
				{
					Lcheck2 = true;
					Toast.makeText(getApplicationContext(), "Lcheck2 OK",
							Toast.LENGTH_SHORT).show();
				}

				if (lsb3.toString().equals("c2:9f:db:67:5a:57")&& (LScanResult3.level > -70)	//PN Library wi-fi
						|| lsb3.toString().equals("00:13:c3:f1:37:b1")		//studentcity
						|| lsb3.toString().equals("00:13:c4:c3:9c:31")		//studentcity
						|| lsb3.toString().equals("00:13:c4:c3:9c:30")		//inspirefreewifi
						|| lsb3.toString().equals("00:13:c3:f1:37:b0")		//inspirefreewifi
						|| lsb3.toString().equals("62:f3:a3:ce:d8:9c")		//vodafoneD89F
						|| lsb3.toString().equals("f0:7d:68:f7:cb:9e")		//GCI
						|| lsb3.toString().equals("00:17:df:10:84:10")		//PPTA
						|| lsb3.toString().equals("00:18:4d:d3:d7:e8")		//PPTA1
						|| lsb3.toString().equals("24:db:ac:71:58:5b")		//VodafoneMobileWiFi-585B61
						|| lsb3.toString().equals("00:0c:c3:e5:a7:88")		//Telecom-6320
						|| lsb3.toString().equals("00:22:75:29:f0:99")		//Barista Cafe
						|| lsb3.toString().equals("58:98:35:96:74:d1")) 
				{
					Lcheck3 = true;
					Toast.makeText(getApplicationContext(), "Lcheck3 OK",
							Toast.LENGTH_SHORT).show();
				}

				if (Lcheck1 == true && Lcheck2 == true && Lcheck3) {
					allNetWork.setText("You are at city library Position 1"
							+ "  " + LScanResult1.level);
					Toast.makeText(getApplicationContext(), "Lcheck1, Lcheck2 and Lcheck3 Pass",
							Toast.LENGTH_SHORT).show();
					Toast.makeText(getApplicationContext(), "P1",
							Toast.LENGTH_SHORT).show();

					// remove the previous position
					lv2.setBackgroundColor(Color.WHITE);
					lparams2.topMargin = 210; // 210
					lparams2.leftMargin = 10; // 10
					rlMain.addView(lv2, lparams2);

					lv1.setBackgroundColor(Color.BLUE);
					lparams1.topMargin = 335; // 335
					lparams1.leftMargin = 55; // 55
					rlMain.addView(lv1, lparams1);

				} else
					Lcheck1 = false;
					Lcheck2 = false;
					Lcheck3 = false;
					
//----------------------- Position 2-----------------------------------------------------------
				 if (Lcheck1 == false || Lcheck2 == false
						|| Lcheck3 == false) {
					
				if (lsb1.toString().equals("c2:9f:db:67:5a:57")
							&& (LScanResult1.level > -70)
							|| lsb2.toString().equals("00:13:c3:f1:37:b1"))
					{
						Lcheck4 = true;
						Toast.makeText(getApplicationContext(),
								"Lcheck4 OK", Toast.LENGTH_SHORT).show();
					}
					if (lsb2.toString().equals("00:13:c3:f1:37:b1")
							|| lsb1.toString().equals("c2:9f:db:67:5a:57")
							&& (LScanResult1.level > -70)) 
					{
						Lcheck5 = true;
						Toast.makeText(getApplicationContext(),
								"Lcheck5 OK", Toast.LENGTH_SHORT).show();
					}

					if (lsb4.toString().equals("24:db:ac:71:58:5b")) //
					{
						Lcheck6 = true;
						Toast.makeText(getApplicationContext(),
								"Lcheck6 OK", Toast.LENGTH_SHORT).show();
					}

					if (Lcheck4 == true && Lcheck5 == true && Lcheck6 == true) {
						allNetWork.setText("You are at city library Position 2"
								+ " " + LScanResult1.level);
						Toast.makeText(getApplicationContext(), "P2",
								Toast.LENGTH_SHORT).show();

						// remove the previous position
						lv1.setBackgroundColor(Color.WHITE);
						lparams1.topMargin = 335; // 335
						lparams1.leftMargin = 55; // 55
						rlMain.addView(lv1, lparams1);

						lv2.setBackgroundColor(Color.BLUE);
						lparams2.topMargin = 210; // 210
						lparams2.leftMargin = 10; // 10
						rlMain.addView(lv2, lparams2);

					} else
						Lcheck4 = false;
						Lcheck5 = false;
						Lcheck6 = false;
				 }
//------------------------Position 3--------------------------------------------------------------
				 if (Lcheck4 == false || Lcheck5 == false
							|| Lcheck6 == false){
						
					if (lsb1.toString().equals("64:16:f0:ea:d7:35")
								|| lsb1.toString().equals("c2:9f:db:67:5a:28")
								|| lsb1.toString().equals("00:12:44:ba:a6:90")
								|| lsb1.toString().equals("00:12:44:ba:a6:91")
								|| lsb1.toString().equals("c2:9f:db:67:5a:54")
								|| lsb1.toString().equals("00:12:44:b8:4e:80")
								|| lsb1.toString().equals("c2:9f:db:67:59:8a") && LScanResult1.level > -70
								|| lsb1.toString().equals("00:60:64:6a:e7:20")
								|| lsb1.toString().equals("00:12:44:b8:4e:81")
								|| lsb1.toString().equals("00:0c:c3:e6:29:90")
								|| lsb1.toString().equals("00:22:a4:bd:66:91")
								|| lsb1.toString().equals("00:13:c3:f1:37:b0")
								|| lsb1.toString().equals("a4:b1:e9:83:6e:29")
								|| lsb1.toString().equals("00:26:44:01:38:10")
								|| lsb1.toString().equals("c2:9f:db:67:5a:57")) // Palmerston
																				// North
																				// Library
						{
							Lcheck7 = true;
							// Toast.makeText(getApplicationContext(),lsb1.toString(),Toast.LENGTH_SHORT).show();
						}
					if (lsb2.toString().equals("64:16:f0:ea:d7:35")
							|| lsb2.toString().equals("c2:9f:db:67:5a:28")
							|| lsb2.toString().equals("00:12:44:ba:a6:90")
							|| lsb2.toString().equals("00:12:44:ba:a6:91")
							|| lsb2.toString().equals("c2:9f:db:67:5a:54")
							|| lsb2.toString().equals("00:12:44:b8:4e:80")
							|| lsb2.toString().equals("c2:9f:db:67:59:8a") && LScanResult2.level > -70
							|| lsb2.toString().equals("00:60:64:6a:e7:20")
							|| lsb2.toString().equals("00:12:44:b8:4e:81")
							|| lsb2.toString().equals("00:0c:c3:e6:29:90")
							|| lsb2.toString().equals("00:22:a4:bd:66:91")
							|| lsb2.toString().equals("00:13:c3:f1:37:b0")
							|| lsb2.toString().equals("a4:b1:e9:83:6e:29")
							|| lsb2.toString().equals("00:26:44:01:38:10")
							|| lsb2.toString().equals("c2:9f:db:67:5a:57")) // Palmerston
																			// North
																			// Library
					{
						Lcheck8 = true;
						// Toast.makeText(getApplicationContext(),lsb2.toString(),Toast.LENGTH_SHORT).show();
					}

					if (lsb3.toString().equals("64:16:f0:ea:d7:35")
							|| lsb3.toString().equals("c2:9f:db:67:5a:28")
							|| lsb3.toString().equals("00:12:44:ba:a6:90")
							|| lsb3.toString().equals("00:12:44:ba:a6:91")
							|| lsb3.toString().equals("c2:9f:db:67:5a:54")
							|| lsb3.toString().equals("00:12:44:b8:4e:80")
							|| lsb3.toString().equals("c2:9f:db:67:59:8a") && LScanResult3.level > -70
							|| lsb3.toString().equals("00:60:64:6a:e7:20")
							|| lsb3.toString().equals("00:12:44:b8:4e:81")
							|| lsb3.toString().equals("00:0c:c3:e6:29:90")
							|| lsb3.toString().equals("00:22:a4:bd:66:91")
							|| lsb3.toString().equals("00:13:c3:f1:37:b0")
							|| lsb3.toString().equals("a4:b1:e9:83:6e:29")
							|| lsb3.toString().equals("00:26:44:01:38:10")
							|| lsb3.toString().equals("c2:9f:db:67:5a:57")) // Palmerston
																			// North
																			// Library
					{
						Lcheck9 = true;
						// Toast.makeText(getApplicationContext(),lsb4.toString(),Toast.LENGTH_SHORT).show();
					}

					if (Lcheck7 == true && Lcheck8 == true && Lcheck9 == true) {
						allNetWork.setText("You are at city library Position 3"
								+ " " + LScanResult1.level);
						Toast.makeText(getApplicationContext(), "P3",
								Toast.LENGTH_SHORT).show();

						// remove the previous position
						lv2.setBackgroundColor(Color.WHITE);
						lparams2.topMargin = 210; // 210
						lparams2.leftMargin = 10; // 10
						rlMain.addView(lv2, lparams2);

						lv1.setBackgroundColor(Color.WHITE);
						lparams1.topMargin = 335; // 335
						lparams1.leftMargin = 55; // 55
						rlMain.addView(lv1, lparams1);

						lv3.setBackgroundColor(Color.BLUE);
						lparams3.topMargin = 42; // 42
						lparams3.leftMargin = 200; // 200
						rlMain.addView(lv3, lparams3);
						
						lv4.setBackgroundColor(Color.WHITE); 
					  	lparams4.topMargin = 42; //90 
					  	lparams4.leftMargin = 290; //280
					  	rlMain.addView(lv4, lparams4);

					}
					else if(Lcheck7 == true || Lcheck8 == true || Lcheck9 == true)
						Lcheck7 = false;
						Lcheck8 = false;
						Lcheck9 = false;
				 }
//--------------------------- Position 4-------------------------------------------------------------
					  if (Lcheck7 == false && Lcheck8 == false && Lcheck9 == false) {
						
						  if (lsb1.toString().equals("00:22:75:60:28:b4")			//design_school_2
								  ||lsb1.toString().equals("c2:9f:db:67:59:8a") && LScanResult1.level < -70		//PN Library wi-fi
								  ||lsb1.toString().equals("c8:9c:1d:fd:7c:80")		//pavise
								  ||lsb1.toString().equals("00:12:44:b8:4e:80")		//inspirefreewifi
								  ||lsb1.toString().equals("00:12:44:b8:4e:81")		//studentcity
								  ||lsb1.toString().equals("00:0c:42:65:cf:58")		//studentcity
								  ||lsb1.toString().equals("02:0c:42:65:cf:58")		//inspirefreewifi
								  ||lsb1.toString().equals("00:1b:11:1c:68:08")		//Boardroom
								  ||lsb1.toString().equals("00:0e:8f:a3:84:ca")		//AFL
								  ||lsb1.toString().equals("c2:9f:db:67:5a:54")		//PN Library wi-fi	
								  ||lsb1.toString().equals("00:60:64:6b:08:ea"))		//Bellas Cafe
								  {
							  			Lcheck10 = true;
								  }

						  if (lsb2.toString().equals("00:22:75:60:28:b4")			//design_school_2
								  ||lsb2.toString().equals("c2:9f:db:67:59:8a") && LScanResult2.level < -70		//PN Library wi-fi
								  ||lsb2.toString().equals("c8:9c:1d:fd:7c:80")		//pavise
								  ||lsb2.toString().equals("00:12:44:b8:4e:80")		//inspirefreewifi
								  ||lsb2.toString().equals("00:12:44:b8:4e:81")		//studentcity
								  ||lsb2.toString().equals("00:0c:42:65:cf:58")		//studentcity
								  ||lsb2.toString().equals("02:0c:42:65:cf:58")		//inspirefreewifi
								  ||lsb2.toString().equals("00:1b:11:1c:68:08")		//Boardroom
								  ||lsb2.toString().equals("00:0e:8f:a3:84:ca")		//AFL
								  ||lsb2.toString().equals("c2:9f:db:67:5a:54")		//PN Library wi-fi	
								  ||lsb2.toString().equals("00:60:64:6b:08:ea"))		//Bellas Cafe
								  {
							  			Lcheck11 = true;
								  }
						  
						  if (lsb3.toString().equals("00:22:75:60:28:b4")			//design_school_2
								  ||lsb3.toString().equals("c2:9f:db:67:59:8a") && LScanResult3.level > -70		//PN Library wi-fi
								  ||lsb3.toString().equals("c8:9c:1d:fd:7c:80")		//pavise
								  ||lsb3.toString().equals("00:12:44:b8:4e:80")		//inspirefreewifi
								  ||lsb3.toString().equals("00:12:44:b8:4e:81")		//studentcity
								  ||lsb3.toString().equals("00:0c:42:65:cf:58")		//studentcity
								  ||lsb3.toString().equals("02:0c:42:65:cf:58")		//inspirefreewifi
								  ||lsb3.toString().equals("00:1b:11:1c:68:08")		//Boardroom
								  ||lsb3.toString().equals("00:0e:8f:a3:84:ca")		//AFL
								  ||lsb3.toString().equals("c2:9f:db:67:5a:54")		//PN Library wi-fi	
								  ||lsb3.toString().equals("00:60:64:6b:08:ea"))		//Bellas Cafe
								  {
							  		Lcheck12 = true;
								  }
						  
						  if(Lcheck10 == true && Lcheck11 == true && Lcheck12 == true){
						  
							  	allNetWork.setText("You are at city library Position 4" +
							  				" " + LScanResult1.level);
							  	Toast.makeText(getApplicationContext(),"P4",Toast.LENGTH_SHORT).show();
					  
					  // remove the previous position
					  
					  
							  	lv4.setBackgroundColor(Color.BLUE); 
							  	lparams4.topMargin = 42; //90 
							  	lparams4.leftMargin = 290; //280
							  	rlMain.addView(lv4, lparams4);
					  
							  	lv3.setBackgroundColor(Color.WHITE);
								lparams3.topMargin = 42; // 42
								lparams3.leftMargin = 200; // 200
								rlMain.addView(lv3, lparams3);
						  }		  
					  }
			/*		
					//Position 5
					 lv7.setBackgroundColor(Color.BLUE); lparams7.topMargin =
							  90; //90 
							  lparams7.leftMargin = 2880; //280
							  rlMain.addView(lv7, lparams7);
							  */
				}
			}
		}
	}