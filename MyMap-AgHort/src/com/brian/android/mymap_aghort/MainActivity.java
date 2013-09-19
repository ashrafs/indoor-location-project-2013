package com.brian.android.mymap_aghort;

import java.util.Arrays;
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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ZoomButtonsController;

import com.brian.android.mymap.MapView;
import com.brian.android.util.ImageUtil;
import com.brian.android.mymap_aghort.WifiAdmin;
import com.brian.android.mymap_aghort.R;
import com.brian.android.mymap.MapLocation;
import com.brian.android.util.ActivityUtil;
import com.brian.android.mymap_aghort.StateLocation;
import com.brian.android.mymap_aghort.ZoomLocation;
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

	//protected ImageView map;
	protected MapView map;
	
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
	private StringBuffer Ahgsb1 = new StringBuffer();
	private StringBuffer Ahgsb2 = new StringBuffer();
	private StringBuffer Ahgsb3 = new StringBuffer();
	private StringBuffer Ahgsb4 = new StringBuffer();
	private StringBuffer Ahgsb5 = new StringBuffer();

	
	private Button TakeButton;
	private ImageButton ScanButton;
	private TextView allNetWork,destinationSet;
	private TextView locationtext;
	private WifiAdmin mWifiAdmin;
	private List<ScanResult> list;
	
	
	private ScanResult AgHScanResult1,AgHScanResult2, AgHScanResult3,AgHScanResult4,AgHScanResult5,
			HScanResult1,HScanResult2, HScanResult3, HScanResult4, HScanResult5,
			LScanResult1, LScanResult2, LScanResult3, LScanResult4,LScanResult5;
	/*
	boolean Aghortcheck1, Aghortcheck2, Aghortcheck3, Aghortcheck4, Aghortcheck5,
			Aghortcheck6,Aghortcheck7,Aghortcheck8,Aghortcheck9,Aghortcheck10,
			Aghortcheck11,Aghortcheck12,Aghortcheck13,Aghortcheck14,Aghortcheck15,
			Aghortcheck17,Aghortcheck18,Aghortcheck19,Aghortcheck20,Aghortcheck21;
			
	boolean HcheckL1, HcheckL2,HcheckL3, HcheckH1, HcheckH2, HcheckH3;
	boolean Lcheck1, Lcheck2, Lcheck3, Lcheck4, Lcheck5, Lcheck6, Lcheck7,
			Lcheck8, Lcheck9, Lcheck10, Lcheck11, Lcheck12;
	
	boolean HomePositionset1, HomePositionset2;
	boolean AgHPositionset1, AgHPositionset2, AgHPositionset3, AgHPositionset4;
	boolean	LPositionset1, LPositionset2, LPositionset3, LPositionset4;
	*/
	boolean[] Homecheck = new boolean[3];
	boolean[] Lcheck = new boolean[12];
	boolean[] Aghortcheck = new boolean[20];
	
	boolean[] HomePositionset = new boolean[2];
	boolean[] LPositionset = new boolean[4];
	boolean[] AgHPositionset = new boolean[4];

	private float currentDegree = 0f;
	private SensorManager mSensorManager;
	public ZoomButtonsController zoomy;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_main);
		setContentView(R.layout.oldlayout);

		// compass
		// image = (ImageView) findViewById(R.id.main_iv);
		//mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
		//mSensorManager.unregisterListener(this);
		mWifiAdmin = new WifiAdmin(MainActivity.this);
		
		//map = (ImageView) findViewById(R.id.mapTest);
		//map = (MapView) findViewById(R.id.map);

		// Bitmap bimtBitmap =
		// BitmapFactory.decodeResource(getResources(),R.drawable.librarymap);
		// map.setImageBitmap(bimtBitmap);

		/**
		 * Use Simple ImageView
		 */
		//usingSimpleImage(map);

		// Zoom
		// map.setBuiltInZoomControls(true);
		//map = (MapView) findViewById(R.id.map);
       //map.setMapImage(ImageUtil.loadBitmapFromResource(getResources(), R.drawable.homemap));
		
		allNetWork = (TextView) findViewById(R.id.allNetWork);
		destinationSet = (TextView) findViewById(R.id.destinationSet);
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
		case R.id.lposition1:
			map = (MapView) findViewById(R.id.map);
			map.setMapImage(ImageUtil.loadBitmapFromResource(getResources(), R.drawable.librarymap));
			MapLocation[] locations1 = new MapLocation[] {new StateLocation(this, 180, 740, R.drawable.destination_icon, R.drawable.destination_icon_selected, "Position 1")};
			map.setLocations(locations1);
			LPositionset[0] = true;
			destinationSet.setText("You have choose Position 1");
			return true;
		
		case R.id.lposition2:
			map = (MapView) findViewById(R.id.map);	
			map.setMapImage(ImageUtil.loadBitmapFromResource(getResources(), R.drawable.librarymap));
			MapLocation[] locations2 = new MapLocation[] {new StateLocation(this, 70, 450, R.drawable.destination_icon, R.drawable.destination_icon_selected, "Position 2")};
			map.setLocations(locations2);
			LPositionset[1] = true;
			destinationSet.setText("You have choose Position 2");
			return true;
			
		case R.id.lposition3:
			map = (MapView) findViewById(R.id.map);	
			map.setMapImage(ImageUtil.loadBitmapFromResource(getResources(), R.drawable.librarymap));
		  	MapLocation[] locations3 = new MapLocation[] {new StateLocation(this, 530, 10, R.drawable.destination_icon, R.drawable.destination_icon_selected, "Position 3")};
			map.setLocations(locations3);
			LPositionset[2] = true;
			destinationSet.setText("You have choose Position 3");
			return true;
			
		case R.id.Aghort1:
			map = (MapView) findViewById(R.id.map);	
			map.setMapImage(ImageUtil.loadBitmapFromResource(getResources(), R.drawable.map3));
			MapLocation[] locations4 = new MapLocation[] {new StateLocation(this, 270, 160, R.drawable.destination_icon, R.drawable.destination_icon_selected, "Outside Room 3.81")};
			map.setLocations(locations4);
			AgHPositionset[0] = true;
			destinationSet.setText("You have choose destination around room 3.81");
			return true;
			
		case R.id.Aghort2:
			map = (MapView) findViewById(R.id.map);	
			map.setMapImage(ImageUtil.loadBitmapFromResource(getResources(), R.drawable.map3));
			MapLocation[] locations5 = new MapLocation[] {new StateLocation(this, 700, 160, R.drawable.destination_icon, R.drawable.destination_icon_selected, "Outside Room 3.64")};
			map.setLocations(locations5);
			AgHPositionset[1] = true;
			destinationSet.setText("You have choose destination around room 3.64");
			return true;
			
		case R.id.Aghort3:
			map = (MapView) findViewById(R.id.map);	
			map.setMapImage(ImageUtil.loadBitmapFromResource(getResources(), R.drawable.map3));
			MapLocation[] locations6 = new MapLocation[] {new StateLocation(this, 920, 160, R.drawable.destination_icon, R.drawable.destination_icon_selected, "Outside Room 3.52")};
			map.setLocations(locations6);
			AgHPositionset[2] = true;
			destinationSet.setText("You have choose destination around room 3.52");
			return true;

		case R.id.Aghort4:
			map = (MapView) findViewById(R.id.map);	
			map.setMapImage(ImageUtil.loadBitmapFromResource(getResources(), R.drawable.map3));
			MapLocation[] locations7 = new MapLocation[] {new StateLocation(this, 1370, 200, R.drawable.destination_icon, R.drawable.destination_icon_selected, "Outside Room 3.42")};
			map.setLocations(locations7);
			AgHPositionset[3] = true;
			destinationSet.setText("You have choose destination around room 3.42");
			return true;
			
		case R.id.Hposition1:
			map = (MapView) findViewById(R.id.map);	
			map.setMapImage(ImageUtil.loadBitmapFromResource(getResources(), R.drawable.homemap));
			MapLocation[] locations8 = new MapLocation[] {new StateLocation(this, 700, 700, R.drawable.destination_icon, R.drawable.destination_icon_selected, "Home1")};
			map.setLocations(locations8);
			HomePositionset[0] = true;
			destinationSet.setText("You have choose Home Position 1");
			return true;

		case R.id.Hposition2:
			map = (MapView) findViewById(R.id.map);	
			map.setMapImage(ImageUtil.loadBitmapFromResource(getResources(), R.drawable.homemap));
			MapLocation[] locations9 = new MapLocation[] {new StateLocation(this, 200, 700, R.drawable.destination_icon, R.drawable.destination_icon_selected, "Home2")};
			map.setLocations(locations9);
			HomePositionset[1] = true;
			destinationSet.setText("You have choose Home Position 2");
			return true;

		

		case R.id.Sensor_On:
			mSensorManager.registerListener(this,mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION),SensorManager.SENSOR_DELAY_GAME);
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

		ScanButton = (ImageButton) findViewById(R.id.ScanButton);

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

		Button button=(Button) findViewById(R.id.changeMap); 
		 button.setOnClickListener(new OnClickListener() {       
		        @Override 
		        public void onClick(View v) { 
		            // TODO Auto-generated method stub 
		            //第二个参数是该popupmenu将要依附于哪个view上，如果该view下面有空间它就在下面显示，否则在其上面 
		            PopupMenu popupMenu=new PopupMenu(getApplicationContext(), allNetWork); 
		            popupMenu.getMenuInflater().inflate(R.menu.popupmenu,popupMenu.getMenu()); 
		            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() { 
		                 
		                @Override 
		                public boolean onMenuItemClick(MenuItem item) { 
		                    // TODO Auto-generated method stub 
		                    //Toast.makeText(getApplicationContext(), item.getTitle(),1000).show(); 
		                	switch(item.getItemId()){
		                		                	
		                	case R.id.AgHort_1:
		            			//map = (ImageView) findViewById(R.id.mapTest);
		            			map = (MapView) findViewById(R.id.map);

		            			//Bitmap bimtBitmap1 = BitmapFactory.decodeResource(getResources(),R.drawable.map1);
		            			//map.setImageBitmap(bimtBitmap1);
		            			map.setMapImage(ImageUtil.loadBitmapFromResource(getResources(), R.drawable.map1));
		            			return true;

		            		case R.id.AgHort_2:
		            			//map = (ImageView) findViewById(R.id.mapTest);
		            			map = (MapView) findViewById(R.id.map);
		            			
		            			//Bitmap bimtBitmap2 = BitmapFactory.decodeResource(getResources(),R.drawable.map2);
		            			//map.setImageBitmap(bimtBitmap2);
		            			map.setMapImage(ImageUtil.loadBitmapFromResource(getResources(), R.drawable.map2));
		            			return true;

		            		case R.id.AgHort_3:
		            			//map = (ImageView) findViewById(R.id.mapTest);
		            			map = (MapView) findViewById(R.id.map);
		            			
		            			//Bitmap bimtBitmap3 = BitmapFactory.decodeResource(getResources(),R.drawable.map3);
		            			//map.setImageBitmap(bimtBitmap3);
		            			map.setMapImage(ImageUtil.loadBitmapFromResource(getResources(), R.drawable.map3));
		            			
		            			return true;

		            		case R.id.homemap:
		            			//map = (ImageView) findViewById(R.id.mapTest);
		            			map = (MapView) findViewById(R.id.map);
		            			
		            			//Bitmap HomeBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.homemap);
		            			//map.setImageBitmap(HomeBitmap);
		            			map.setMapImage(ImageUtil.loadBitmapFromResource(getResources(), R.drawable.homemap));

		            			return true;
		            			
		            		case R.id.librarymap:
		            			//map = (ImageView) findViewById(R.id.mapTest);
		            			map = (MapView) findViewById(R.id.map);
		            			
		            			//Bitmap HomeBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.homemap);
		            			//map.setImageBitmap(HomeBitmap);
		            			map.setMapImage(ImageUtil.loadBitmapFromResource(getResources(), R.drawable.librarymap));

		            			return true;
		                }
							return false;
		                } 
		            }); 
		            //最后不要忘了调用show方法 
		            popupMenu.show(); 
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
	public void zoomOut(View v) {
		
		try{
			float oldRatio = map.getZoomRatio();
			float newRatio = oldRatio - .1f;
			if (newRatio < 0.3f) {
    		newRatio = 0.3f;
    		}
   			map.zoom(newRatio);
		}
		catch(IllegalStateException e)
			{	
				Toast.makeText(getApplicationContext(),"Cant Zoom",Toast.LENGTH_SHORT).show();
			}
    }
    
    /**
     * Called when user clicks Zoom in button.
     * @param v
     */
    public void zoomIn(View v) {
    	float oldRatio = map.getZoomRatio();
    	float newRatio = oldRatio + .1f;
    	if (newRatio > 0.9f) {
    		newRatio = 0.9f;
    	}
    	map.zoom(newRatio);
    }
    /**
     * 	
     * 
     *
     */
	/*
	public void usingSimpleImage(ImageView imageView) {
		ImageAttacher mAttacher = new ImageAttacher(imageView);
		ImageAttacher.MAX_ZOOM = 2.0f; // Double the current Size
		ImageAttacher.MIN_ZOOM = 1.0f; // Half the current Size
		MatrixChangeListener mMaListener = new MatrixChangeListener();
		mAttacher.setOnMatrixChangeListener(mMaListener);
		PhotoTapListener mPhotoTap = new PhotoTapListener();
		mAttacher.setOnPhotoTapListener(mPhotoTap);
	}

	*/

	@Override
	public void onAccuracyChanged(Sensor arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSensorChanged(SensorEvent arg0) {
		// TODO Auto-generated method stub
		
	}
    
    /*
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
				|| lsb1 != null || lsb2 != null || lsb3 != null || lsb4 != null || lsb5 != null 
				|| Ahgsb1 != null || Ahgsb2 != null || Ahgsb3 != null || Ahgsb4 != null || Ahgsb5 != null) {
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
			Ahgsb1 = new StringBuffer();
			Ahgsb2 = new StringBuffer();
			Ahgsb3 = new StringBuffer();
			Ahgsb4 = new StringBuffer();
			Ahgsb5 = new StringBuffer();
			//allNetWork.setText("");
			
		}

		// Start Scan Network
		mWifiAdmin.startScan();
		list = mWifiAdmin.getWifiList();

		if (list != null) {

			// for (int i = 0; i < list.size(); i++) {
/*
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
			Aghortcheck1 = false;
			Aghortcheck2 = false;
			Aghortcheck3 = false;
			Aghortcheck4 = false;
			Aghortcheck5 = false;
			*/
			
			Arrays.fill(Homecheck, Boolean.FALSE);
			Arrays.fill(Lcheck, Boolean.FALSE);
			Arrays.fill(Aghortcheck, Boolean.FALSE);
			
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
			Ahgsb1 = new StringBuffer();
			Ahgsb2 = new StringBuffer();
			Ahgsb3 = new StringBuffer();
			Ahgsb4 = new StringBuffer();
			Ahgsb5 = new StringBuffer();

			// Get Scan Result
			// mScanResult = list.get(0);
			// mScanResult1 = list.get(1);
			// mScanResult2 = list.get(2);
			HScanResult1 = list.get(0);
			LScanResult1 = list.get(0);
			AgHScanResult1 = list.get(0);

			if (list.size() > 1) {
				HScanResult2 = list.get(1);
				LScanResult2 = list.get(1);
				AgHScanResult2 = list.get(1);
			}
			if (list.size() > 2) {
				HScanResult3 = list.get(2);
				LScanResult3 = list.get(2);
				AgHScanResult3 = list.get(2);
			}
			if (list.size() > 3) {
				HScanResult4 = list.get(3);
				LScanResult4 = list.get(3);
				AgHScanResult4 = list.get(3);
			}
			if (list.size() > 4) {
				HScanResult5 = list.get(4);
				LScanResult5 = list.get(4);
				AgHScanResult5 = list.get(4);
			}

			// Home Use
			hsb1 = hsb1.append(HScanResult1.SSID);
			hsb2 = hsb2.append(HScanResult2.SSID);

			// Library Use
			lsb1 = lsb1.append(LScanResult1.BSSID);
			lsb2 = lsb2.append(LScanResult2.BSSID);
			
			// Aghort Use
			Ahgsb1 = Ahgsb1.append(AgHScanResult1.BSSID);
			Ahgsb2 = Ahgsb2.append(AgHScanResult2.BSSID);

			if (list.size() > 2) {
				hsb3 = hsb3.append(LScanResult3.SSID);
				lsb3 = lsb3.append(LScanResult3.BSSID);
				Ahgsb3 = Ahgsb3.append(AgHScanResult3.BSSID);
			}
			if (list.size() > 3) {
				hsb4 = hsb4.append(LScanResult4.SSID);
				lsb4 = lsb4.append(LScanResult4.BSSID);
				Ahgsb4 = Ahgsb4.append(AgHScanResult4.BSSID);
			}
			if (list.size() > 4) {
				hsb5 = hsb5.append(LScanResult5.SSID);
				lsb5 = lsb5.append(LScanResult5.BSSID);
				Ahgsb5 = Ahgsb5.append(AgHScanResult5.BSSID);
			}

			
// -------------------------------------------------------------------------------------------------------------------------------------//
/**
 * Home Use
 */
			if (HScanResult1.level >= -78) {// -- Less than or equal to -78dB	

				// usedHOMEMap = true;
				// For Home Use
				
				if (hsb1.toString().equals("NETGEAR_18")
						|| hsb2.toString().equals("vodafone0BF3")
						|| hsb3.toString().equals("AndroidAP")) {
					Homecheck[0] = true;
					//HcheckL1 = true;
					// allNetWork.setText(hsb1.toString());
					// Toast.makeText(getApplicationContext(),hsb1.toString(),Toast.LENGTH_SHORT).show();
				}
				if (hsb2.toString().equals("vodafone0BF3")
						|| hsb1.toString().equals("NETGEAR_18")
						|| hsb3.toString().equals("AndroidAP")) {
					Homecheck[1] = true;
					//HcheckL2 = true;
					// allNetWork.setText(hsb2.toString());
					// Toast.makeText(getApplicationContext(),hsb2.toString(),Toast.LENGTH_SHORT).show();
				}
				if (hsb3.toString().equals("vodafone0BF3")
						|| hsb3.toString().equals("NETGEAR_18")
						|| hsb3.toString().equals("AndroidAP")) {
					Homecheck[2] = true;
					//HcheckL3 = true;
					// allNetWork.setText(hsb2.toString());
					// Toast.makeText(getApplicationContext(),hsb2.toString(),Toast.LENGTH_SHORT).show();
				}
				

				if (Homecheck[0] == true && Homecheck[1] == true && Homecheck[2] == true) {
					locationtext.setText("");
					//map = (ImageView) findViewById(R.id.mapTest);
					map = (MapView) findViewById(R.id.map);
					
					//Bitmap HomeBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.homemap);
					//map.setImageBitmap(HomeBitmap);
					map.setMapImage(ImageUtil.loadBitmapFromResource(getResources(), R.drawable.homemap));
					
					allNetWork.setText("You are around home" + " "+ HScanResult1.level);
					
					MapLocation[] Hlocations = new MapLocation[] {new StateLocation(this, 700, 700, R.drawable.location_icon, R.drawable.location_icon_selected, "Addition Signals")};
					map.setLocations(Hlocations);

				}
				else
						if(Homecheck[0]== true && Homecheck[1]== true && Homecheck[2] == false||Homecheck[1]== true && Homecheck[2]== true && Homecheck[0] == false|| Homecheck[0]== true && Homecheck[2]== true && Homecheck[1] == false)
						map = (MapView) findViewById(R.id.map);
						map.setMapImage(ImageUtil.loadBitmapFromResource(getResources(), R.drawable.homemap));
						
						allNetWork.setText("You are around home" + " "+ HScanResult1.level);
						
						if(HomePositionset[0] == true){
							MapLocation[] locations8 = new MapLocation[] {
									new StateLocation(this, 700, 700, R.drawable.destination_icon, R.drawable.destination_icon_selected, "Your are destination at Home1"),
									new StateLocation(this, 700, 400, R.drawable.location_icon, R.drawable.location_icon_selected, "My_room")
							};
							map.setLocations(locations8);
						}
						else
							if(HomePositionset[1] == true){
								MapLocation[] locations8 = new MapLocation[] {
										new StateLocation(this, 200, 700, R.drawable.destination_icon, R.drawable.destination_icon_selected, "Your are destination at Home2"),
										new StateLocation(this, 700, 400, R.drawable.location_icon, R.drawable.location_icon_selected, "My_room")
								};
								map.setLocations(locations8);
						}
						else{
						MapLocation[] Hlocations = new MapLocation[] {new StateLocation(this, 700, 400, R.drawable.location_icon, R.drawable.location_icon_selected, "My_room")};
						map.setLocations(Hlocations);
						}
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
					Homecheck[0] = true;
				}

				if (hsb2.toString().equals("vodafone0BF3")
						|| hsb2.toString().equals("NETGEAR_18")
						|| hsb2.toString().equals("TNCAP5DC00F")
						|| hsb2.toString().equals("Sophie_Thomas")
						|| hsb2.toString().equals("T & P on Air 99.4")) // Check
																	// NETGEAR
				{
					Homecheck[1] = true;
				}
				
				if (hsb3.toString().equals("vodafone0BF3")
						|| hsb3.toString().equals("NETGEAR_18")
						|| hsb3.toString().equals("TNCAP5DC00F")
						|| hsb3.toString().equals("Sophie_Thomas")
						|| hsb3.toString().equals("T & P on Air 99.4")) // Check
																	// NETGEAR
				{
					Homecheck[2] = true;
				}

				if (Homecheck[0] = true && Homecheck[1] == true && Homecheck[2] == true  ) {
					allNetWork.setText("");
					map = (MapView) findViewById(R.id.map);
					map.setMapImage(ImageUtil.loadBitmapFromResource(getResources(), R.drawable.homemap));
					
					allNetWork.setText("You are around home" + " "+ HScanResult1.level);
					//Toast.makeText(getApplicationContext(),"Locate by NETGEAR_18 and Vodafone",Toast.LENGTH_SHORT).show();
					
					if(HomePositionset[0] == true){
						MapLocation[] locations8 = new MapLocation[] {
								new StateLocation(this, 700, 700, R.drawable.destination_icon, R.drawable.destination_icon_selected, "Your are destination at Home1"),
								new StateLocation(this, 200, 200, R.drawable.location_icon, R.drawable.location_icon_selected, "My position")
						};
						map.setLocations(locations8);
					}
					else
						if(HomePositionset[1] == true){
							MapLocation[] locations8 = new MapLocation[] {
									new StateLocation(this, 200, 700, R.drawable.destination_icon, R.drawable.destination_icon_selected, "Your are destination at Home2"),
									new StateLocation(this, 200, 200, R.drawable.location_icon, R.drawable.location_icon_selected, "My position")
							};
							map.setLocations(locations8);
						}
					
					else{
					MapLocation[] Hlocations = new MapLocation[] {new StateLocation(this, 200, 200, R.drawable.location_icon, R.drawable.location_icon_selected, "My position")};
					map.setLocations(Hlocations);
					}
				}

			}
			
			// -------------------------------------------------------------------------------------------------------------//

			// City Library test
			if (Homecheck[0] == false && Homecheck[1] == false && Homecheck[2] == false) {
				
				//Load and display Library Map
				//map = (ImageView) findViewById(R.id.mapTest);
				//Bitmap LibraryBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.librarymap);
				//map.setImageBitmap(LibraryBitmap);
				
				map = (MapView) findViewById(R.id.map);
				map.setMapImage(ImageUtil.loadBitmapFromResource(getResources(), R.drawable.librarymap));
				
				allNetWork.setText("");

				// For Library Use
				
//----------------- Position 1--------------------------------------------

				if (lsb1.toString().equals("c2:9f:db:67:5a:57")&& (LScanResult1.level <= -65)	//PN Library wi-fi greater(more) than -65db eg -66 -67...
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
					Lcheck[0] = true;
					Toast.makeText(getApplicationContext(), "Lcheck1 OK",
							Toast.LENGTH_SHORT).show();
				}

				if (lsb2.toString().equals("c2:9f:db:67:5a:57")&& (LScanResult2.level <= -65)	//PN Library wi-fi greater(more) than -65db eg -66 -67...
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
					Lcheck[1] = true;
					Toast.makeText(getApplicationContext(), "Lcheck2 OK",
							Toast.LENGTH_SHORT).show();
				}

				if (lsb3.toString().equals("c2:9f:db:67:5a:57")&& (LScanResult3.level <= -65)	//PN Library wi-fi greater(more) than -65db eg -66 -67...
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
					Lcheck[2] = true;
					Toast.makeText(getApplicationContext(), "Lcheck3 OK",
							Toast.LENGTH_SHORT).show();
				}

				if (Lcheck[0] == true && Lcheck[1] == true && Lcheck[2] == true) {
					allNetWork.setText("You are at city library Position 1"
							+ "  " + LScanResult1.level);
					Toast.makeText(getApplicationContext(), "P1", Toast.LENGTH_SHORT).show();	
					
					if (LPositionset[0] == true){
						MapLocation[] Llocations = new MapLocation[] {new StateLocation(this, 180, 740, R.drawable.destination_icon, R.drawable.destination_icon_selected, "Your are destination at Position 1"),
																		new StateLocation(this, 180, 740, R.drawable.location_icon, R.drawable.location_icon_selected, "You are at Position 1")
																	};
						map.setLocations(Llocations);
					}else
					if (LPositionset[1] == true){
						MapLocation[] Llocations = new MapLocation[] {new StateLocation(this, 70, 450, R.drawable.destination_icon, R.drawable.destination_icon_selected, "Your are destination at Position 2"),
																	new StateLocation(this, 180, 740, R.drawable.location_icon, R.drawable.location_icon_selected, "You are at Position 1")
																	};
						map.setLocations(Llocations);
					}else
					if (LPositionset[2] == true){
						MapLocation[] Llocations = new MapLocation[] {new StateLocation(this, 530, 10, R.drawable.destination_icon, R.drawable.destination_icon_selected, "Your are destination at Position 3"),
																new StateLocation(this, 180, 740, R.drawable.location_icon, R.drawable.location_icon_selected, "You are at Position 1")
																	};
						map.setLocations(Llocations);
					}else
					{
					MapLocation[] Llocations = new MapLocation[] {new StateLocation(this, 180, 740, R.drawable.location_icon, R.drawable.location_icon_selected, "You are at Position 1")};
					map.setLocations(Llocations);
					}
				}
/*
					// remove the previous position
					lv2.setBackgroundColor(Color.WHITE);
					lparams2.topMargin = 210; // 210
					lparams2.leftMargin = 10; // 10
					rlMain.addView(lv2, lparams2);

					lv1.setBackgroundColor(Color.BLUE);
					lparams1.topMargin = 335; // 335
					lparams1.leftMargin = 55; // 55
					rlMain.addView(lv1, lparams1);
			*/	
					else{
					Lcheck[0] = false;
					Lcheck[1] = false;
					Lcheck[2] = false;
					}
					
//----------------------- Position 2-----------------------------------------------------------
				 if (Lcheck[0] == false || Lcheck[1] == false || Lcheck[2] == false) {
					
				if (lsb1.toString().equals("c2:9f:db:67:5a:57") && (LScanResult1.level > -65)	// City library Wi-Fi less than -65db eg -64 -63...
							|| lsb2.toString().equals("00:13:c3:f1:37:b1")
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
						Lcheck[3] = true;
						Toast.makeText(getApplicationContext(),
								"Lcheck4 OK", Toast.LENGTH_SHORT).show();
					}
				
				if (lsb2.toString().equals("c2:9f:db:67:5a:57")&& (LScanResult1.level > -65)		// City library Wi-Fi less than -65db eg -64 -63...
							|| lsb2.toString().equals("00:13:c3:f1:37:b1")
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
						Lcheck[4] = true;
						Toast.makeText(getApplicationContext(),"Lcheck5 OK", Toast.LENGTH_SHORT).show();
					}

				if (lsb3.toString().equals("c2:9f:db:67:5a:57") && (LScanResult1.level > -65)// City library Wi-Fi less than -65db eg -64 -63...
						|| lsb3.toString().equals("00:13:c3:f1:37:b1")
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
						|| lsb3.toString().equals("58:98:35:96:74:d1")
						) 
					{
						Lcheck[5] = true;
						Toast.makeText(getApplicationContext(),	"Lcheck6 OK", Toast.LENGTH_SHORT).show();
					}

					if (Lcheck[3] == true && Lcheck[4] == true && Lcheck[5] == true) {
						allNetWork.setText("You are at city library Position 2"
								+ " " + LScanResult1.level);
						Toast.makeText(getApplicationContext(), "P2", Toast.LENGTH_SHORT).show();
						
						if (LPositionset[0] == true){
							MapLocation[] Llocations = new MapLocation[] {new StateLocation(this, 180, 740, R.drawable.destination_icon, R.drawable.destination_icon_selected, "Your are destination at Position 1"),
																		new StateLocation(this, 40, 440, R.drawable.location_icon, R.drawable.location_icon_selected, "You are at Position 2")
																		};
							map.setLocations(Llocations);
						}else
						if (LPositionset[1] == true){
							MapLocation[] Llocations = new MapLocation[] {new StateLocation(this, 40, 440, R.drawable.destination_icon, R.drawable.destination_icon_selected, "Your are destination at Position 2"),
																		new StateLocation(this, 40, 440, R.drawable.location_icon, R.drawable.location_icon_selected, "You are at Position 2")
																		};
							map.setLocations(Llocations);
						}else
						if (LPositionset[2] == true){
							MapLocation[] Llocations = new MapLocation[] {new StateLocation(this, 530, 10, R.drawable.destination_icon, R.drawable.destination_icon_selected, "Your are destination at Position 3"),
																			new StateLocation(this, 40, 440, R.drawable.location_icon, R.drawable.location_icon_selected, "You are at Position 2")
																		};
							map.setLocations(Llocations);
						}else
						{
						MapLocation[] Llocations = new MapLocation[] {new StateLocation(this, 40, 440, R.drawable.location_icon, R.drawable.location_icon_selected, "You are at Position 2")};
						map.setLocations(Llocations);
						}
						
						/*
						// remove the previous position
						lv1.setBackgroundColor(Color.WHITE);
						lparams1.topMargin = 335; // 335
						lparams1.leftMargin = 55; // 55
						rlMain.addView(lv1, lparams1);

						lv2.setBackgroundColor(Color.BLUE);
						lparams2.topMargin = 210; // 210
						lparams2.leftMargin = 10; // 10
						rlMain.addView(lv2, lparams2);
			*/			
					} else
						Lcheck[3] = false;
						Lcheck[4] = false;
						Lcheck[5] = false;
				 }
//------------------------Position 3--------------------------------------------------------------
				 if (Lcheck[3] == false || Lcheck[4] == false|| Lcheck[5] == false){
						
					if (lsb1.toString().equals("64:16:f0:ea:d7:35")					//Chokolato
								|| lsb1.toString().equals("c2:9f:db:67:5a:28")
								|| lsb1.toString().equals("00:12:44:ba:a6:90")
								|| lsb1.toString().equals("00:12:44:ba:a6:91")
								|| lsb1.toString().equals("c2:9f:db:67:5a:54") && LScanResult1.level < -65
								|| lsb1.toString().equals("00:12:44:b8:4e:80")		//inspirefreewifi
								|| lsb1.toString().equals("c2:9f:db:67:59:8a") && LScanResult1.level < -65		//less(more) than -65db eg -66, -67...
								|| lsb1.toString().equals("00:60:64:6a:e7:20")
								|| lsb1.toString().equals("00:12:44:b8:4e:81")		//studentcity
								|| lsb1.toString().equals("00:0c:c3:e6:29:90")
								|| lsb1.toString().equals("00:22:a4:bd:66:91")
								|| lsb1.toString().equals("00:13:c3:f1:37:b0")
								|| lsb1.toString().equals("a4:b1:e9:83:6e:29")
								|| lsb1.toString().equals("00:26:44:01:38:10")
								|| lsb1.toString().equals("c2:9f:db:67:5a:57"))
						{
							Lcheck[6] = true;
							// Toast.makeText(getApplicationContext(),lsb1.toString(),Toast.LENGTH_SHORT).show();
						}
					if (lsb2.toString().equals("64:16:f0:ea:d7:35")
							|| lsb2.toString().equals("c2:9f:db:67:5a:28")
							|| lsb2.toString().equals("00:12:44:ba:a6:90")
							|| lsb2.toString().equals("00:12:44:ba:a6:91")
							|| lsb2.toString().equals("c2:9f:db:67:5a:54") && LScanResult1.level < -65
							|| lsb2.toString().equals("00:12:44:b8:4e:80")		//inspirefreewifi
							|| lsb2.toString().equals("c2:9f:db:67:59:8a") && LScanResult1.level < -65		//less(more) than -65db eg -66, -67...
							|| lsb2.toString().equals("00:60:64:6a:e7:20")
							|| lsb2.toString().equals("00:12:44:b8:4e:81")
							|| lsb2.toString().equals("00:0c:c3:e6:29:90")
							|| lsb2.toString().equals("00:22:a4:bd:66:91")
							|| lsb2.toString().equals("00:13:c3:f1:37:b0")
							|| lsb2.toString().equals("a4:b1:e9:83:6e:29")
							|| lsb2.toString().equals("00:26:44:01:38:10")
							|| lsb2.toString().equals("c2:9f:db:67:5a:57")) 
					{
						Lcheck[7] = true;
						// Toast.makeText(getApplicationContext(),lsb2.toString(),Toast.LENGTH_SHORT).show();
					}

					if (lsb3.toString().equals("64:16:f0:ea:d7:35")
							|| lsb3.toString().equals("c2:9f:db:67:5a:28")
							|| lsb3.toString().equals("00:12:44:ba:a6:90")
							|| lsb3.toString().equals("00:12:44:ba:a6:91")
							|| lsb3.toString().equals("c2:9f:db:67:5a:54") && LScanResult1.level < -65
							|| lsb3.toString().equals("00:12:44:b8:4e:80")		//inspirefreewifi
							|| lsb3.toString().equals("c2:9f:db:67:59:8a") && LScanResult1.level < -65		//less(more) than -65db eg -66, -67...
							|| lsb3.toString().equals("00:60:64:6a:e7:20")
							|| lsb3.toString().equals("00:12:44:b8:4e:81")
							|| lsb3.toString().equals("00:0c:c3:e6:29:90")
							|| lsb3.toString().equals("00:22:a4:bd:66:91")
							|| lsb3.toString().equals("00:13:c3:f1:37:b0")
							|| lsb3.toString().equals("a4:b1:e9:83:6e:29")
							|| lsb3.toString().equals("00:26:44:01:38:10")
							|| lsb3.toString().equals("c2:9f:db:67:5a:57"))
					{
						Lcheck[8] = true;
						// Toast.makeText(getApplicationContext(),lsb4.toString(),Toast.LENGTH_SHORT).show();
					}

					if (Lcheck[6] == true && Lcheck[7] == true && Lcheck[8] == true) {
						allNetWork.setText("You are at city library Position 3"
								+ " " + LScanResult1.level);
						Toast.makeText(getApplicationContext(), "P3",
								Toast.LENGTH_SHORT).show();

						if (LPositionset[0] == true){
							MapLocation[] Llocations = new MapLocation[] {new StateLocation(this, 180, 740, R.drawable.destination_icon, R.drawable.destination_icon_selected, "Your are destination at Position 1"),
																			new StateLocation(this, 520, 42, R.drawable.location_icon, R.drawable.location_icon_selected, "You are at Position 3")
																		};
							map.setLocations(Llocations);
						}else
						if (LPositionset[1] == true){
							MapLocation[] Llocations = new MapLocation[] {new StateLocation(this, 40, 440, R.drawable.destination_icon, R.drawable.destination_icon_selected, "Your are destination at Position 2"),
																			new StateLocation(this, 520, 42, R.drawable.location_icon, R.drawable.location_icon_selected, "You are at Position 3")
																		};
							map.setLocations(Llocations);
						}else
						if (LPositionset[2] == true){
							MapLocation[] Llocations = new MapLocation[] {new StateLocation(this, 530, 10, R.drawable.destination_icon, R.drawable.destination_icon_selected, "Your are destination at Position 3"),
																			new StateLocation(this, 520, 42, R.drawable.location_icon, R.drawable.location_icon_selected, "You are at Position 3")
																		};
							map.setLocations(Llocations);
						}else
						{
						MapLocation[] Llocations = new MapLocation[] {new StateLocation(this, 520, 42, R.drawable.location_icon, R.drawable.location_icon_selected, "You are at Position 3")};
						map.setLocations(Llocations);
						}
						
						/*				// remove the previous position
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
			*/	
					}
					else 
						Lcheck[6] = false;
						Lcheck[7] = false;
						Lcheck[8] = false;
				 }
//--------------------------- Position 4-------------------------------------------------------------
					  if (Lcheck[6] == false && Lcheck[7] == false && Lcheck[8] == false) {
						
						  if (lsb1.toString().equals("00:22:75:60:28:b4")			//design_school_2
								  ||lsb1.toString().equals("c2:9f:db:67:59:8a") && LScanResult1.level > -65		//PN Library wi-fi	less than -65db
								  ||lsb1.toString().equals("c8:9c:1d:fd:7c:80")		//pavise
								  ||lsb1.toString().equals("00:12:44:b8:4e:80")		//inspirefreewifi
								  ||lsb1.toString().equals("00:12:44:b8:4e:81")		//studentcity
								  ||lsb1.toString().equals("00:0c:42:65:cf:58")		//studentcity
								  ||lsb1.toString().equals("00:1b:11:1c:68:08")		//Boardroom
								  ||lsb1.toString().equals("00:0e:8f:a3:84:ca")		//AFL
								  ||lsb1.toString().equals("c2:9f:db:67:5a:54")&& LScanResult2.level > -65		//PN Library wi-fi	
								  ||lsb1.toString().equals("00:60:64:6b:08:ea"))		//Bellas Cafe
								  {
							  			Lcheck[9] = true;
								  }

						  if (lsb2.toString().equals("00:22:75:60:28:b4")			//design_school_2
								  ||lsb2.toString().equals("c2:9f:db:67:59:8a") && LScanResult2.level > -65		//PN Library wi-fi less than -65db
								  ||lsb2.toString().equals("c8:9c:1d:fd:7c:80")		//pavise
								  ||lsb2.toString().equals("00:12:44:b8:4e:80")		//inspirefreewifi
								  ||lsb2.toString().equals("00:12:44:b8:4e:81")		//studentcity
								  ||lsb2.toString().equals("00:0c:42:65:cf:58")		//studentcity
								  ||lsb2.toString().equals("00:1b:11:1c:68:08")		//Boardroom
								  ||lsb2.toString().equals("00:0e:8f:a3:84:ca")		//AFL
								  ||lsb2.toString().equals("c2:9f:db:67:5a:54")	&& LScanResult2.level > -65	//PN Library wi-fi	
								  ||lsb2.toString().equals("00:60:64:6b:08:ea"))		//Bellas Cafe
								  {
							  			Lcheck[10] = true;
								  }
						  
						  if (lsb3.toString().equals("00:22:75:60:28:b4")			//design_school_2
								  ||lsb3.toString().equals("c2:9f:db:67:59:8a") && LScanResult3.level > -65		//PN Library wi-fi less than -65db
								  ||lsb3.toString().equals("c8:9c:1d:fd:7c:80")		//pavise
								  ||lsb3.toString().equals("00:12:44:b8:4e:80")		//inspirefreewifi
								  ||lsb3.toString().equals("00:12:44:b8:4e:81")		//studentcity
								  ||lsb3.toString().equals("00:0c:42:65:cf:58")		//studentcity
								  ||lsb3.toString().equals("00:1b:11:1c:68:08")		//Boardroom
								  ||lsb3.toString().equals("00:0e:8f:a3:84:ca")		//AFL
								  ||lsb3.toString().equals("c2:9f:db:67:5a:54")	&& LScanResult2.level > -65	//PN Library wi-fi	
								  ||lsb3.toString().equals("00:60:64:6b:08:ea"))		//Bellas Cafe
								  {
							  			Lcheck[11] = true;
								  }
						  
						  if(Lcheck[9] == true && Lcheck[10] == true && Lcheck[11] == true){
						  
							  	allNetWork.setText("You are at city library Position 4" +
							  				" " + LScanResult1.level);
							  	Toast.makeText(getApplicationContext(),"P4",Toast.LENGTH_SHORT).show();

								
								
								if (LPositionset[0] == true){
									MapLocation[] Llocations = new MapLocation[] {new StateLocation(this, 180, 740, R.drawable.destination_icon, R.drawable.destination_icon_selected, "Your are destination at Position 1"),
																					new StateLocation(this, 290, 42, R.drawable.location_icon, R.drawable.location_icon_selected, "You are at Position 4")
																				};
									map.setLocations(Llocations);
								}else
								if (LPositionset[1] == true){
									MapLocation[] Llocations = new MapLocation[] {new StateLocation(this, 40, 440, R.drawable.destination_icon, R.drawable.destination_icon_selected, "Your are destination at Position 2"),
																					new StateLocation(this, 290, 42, R.drawable.location_icon, R.drawable.location_icon_selected, "You are at Position 4")
																				};
									map.setLocations(Llocations);
								}else
								if (LPositionset[2] == true){
									MapLocation[] Llocations = new MapLocation[] {new StateLocation(this, 530, 10, R.drawable.destination_icon, R.drawable.destination_icon_selected, "Your are destination at Position 3"),
																					new StateLocation(this, 290, 42, R.drawable.location_icon, R.drawable.location_icon_selected, "You are at Position 4")
																				};
									map.setLocations(Llocations);
								}else
								{
									MapLocation[] Llocations = new MapLocation[] {new StateLocation(this, 290, 42, R.drawable.location_icon, R.drawable.location_icon_selected, "You are at Position 4")};
									map.setLocations(Llocations);
								}
								
								/*				  	// remove the previous position
							  	lv4.setBackgroundColor(Color.BLUE); 
							  	lparams4.topMargin = 42; //90 
							  	lparams4.leftMargin = 290; //280
							  	rlMain.addView(lv4, lparams4);
					  
							  	lv3.setBackgroundColor(Color.WHITE);
								lparams3.topMargin = 42; // 42
								lparams3.leftMargin = 200; // 200
								rlMain.addView(lv3, lparams3);
				*/
						  }	else
							  	Lcheck[9] = false;
						  		Lcheck[10] = false;
						  		Lcheck[11] = false;	  
					  }
			
/**
 *  Aghort Map			
 */

//-----------------Position 3.81-----------------------------------------------------------------------------
					  //if (Lcheck[0] == false || Lcheck[1] == false|| Lcheck[2] == false|| Lcheck[3] == false|| Lcheck[4] == false
					  	//	|| Lcheck[5] == false|| Lcheck[6] == false|| Lcheck[7] == false||Lcheck[8] == false || Lcheck[9] == false 
					  	//	|| Lcheck[10] == false|| Lcheck[11] == false){
					  			
						  if (Arrays.asList(Lcheck).contains(false)){
					  
						  if (Ahgsb1.toString().equals("ac:16:2d:e7:e4:f1")			//EduRoam
								  ||Ahgsb1.toString().equals("ac:16:2d:e7:e4:53")	//EduRoam
								  ||Ahgsb1.toString().equals("ac:16:2d:e7:f4:11")	//EduRoam
								  ||Ahgsb1.toString().equals("ac:16:2d:e7:e4:50")&& AgHScanResult1.level >= -70	//MUStaff
								  ||Ahgsb1.toString().equals("ac:16:2d:e7:e4:f0")&& AgHScanResult1.level <= -70//MUStaff
								  ||Ahgsb1.toString().equals("ac:16:2d:e7:f4:10")&& AgHScanResult1.level <= -70	//MUStaff
								  ||Ahgsb1.toString().equals("ac:16:2d:e7:e4:f4")	//MUStaffPrivateEquipment
								  ||Ahgsb1.toString().equals("ac:16:2d:e7:f4:14")	//MUStaffPrivateEquipment
								  ||Ahgsb1.toString().equals("ac:16:2d:e7:e4:52")	//MUStaffPrivateEquipment
								  ||Ahgsb1.toString().equals("c8:cb:b8:ec:5a:b4")	//MUStaffPrivateEquipment
								  ||Ahgsb1.toString().equals("ac:16:2d:e7:d4:b2")	//MUStaffPrivateEquipment
								  ||Ahgsb1.toString().equals("ac:16:2d:e7:e4:f2")&& AgHScanResult1.level <= -70	//MUStudents
								  ||Ahgsb1.toString().equals("ac:16:2d:e7:f4:12")&& AgHScanResult1.level <= -70	//MUStudents
								  ||Ahgsb1.toString().equals("ac:16:2d:e7:e4:51")&& AgHScanResult1.level >= -70	//MUStudents
								  ||Ahgsb1.toString().equals("ac:16:2d:e7:e4:f3")	//inspirefreewifi
								  ||Ahgsb1.toString().equals("ac:16:2d:e7:f4:13")	//inspirefreewifi
								  ||Ahgsb1.toString().equals("ac:16:2d:e7:e4:54")	//inspirefreewifi
								  ||Ahgsb1.toString().equals("ac:16:2d:e7:d4:b4")	//inspirefreewifi
								  ||Ahgsb1.toString().equals("ac:16:2d:e7:e4:f5")	//studentcity
								  ||Ahgsb1.toString().equals("ac:16:2d:e7:f4:15")	//studentcity
								  ||Ahgsb1.toString().equals("ac:16:2d:e7:e4:55")	//studentcity
								  ||Ahgsb1.toString().equals("ac:16:2d:e7:d4:b5")	//studentcity
								  )
								  {
							  		Aghortcheck[0] = true;
								  }

						  if (Ahgsb2.toString().equals("ac:16:2d:e7:e4:f1")			//EduRoam
								  ||Ahgsb2.toString().equals("ac:16:2d:e7:e4:53")	//EduRoam
								  ||Ahgsb2.toString().equals("ac:16:2d:e7:f4:11")	//EduRoam
								  ||Ahgsb2.toString().equals("ac:16:2d:e7:e4:50")&& AgHScanResult1.level >= -70	//MUStaff
								  ||Ahgsb2.toString().equals("ac:16:2d:e7:e4:f0")&& AgHScanResult1.level <= -70//MUStaff
								  ||Ahgsb2.toString().equals("ac:16:2d:e7:f4:10")&& AgHScanResult1.level <= -70	//MUStaff
								  ||Ahgsb2.toString().equals("ac:16:2d:e7:e4:f4")	//MUStaffPrivateEquipment
								  ||Ahgsb2.toString().equals("ac:16:2d:e7:f4:14")	//MUStaffPrivateEquipment
								  ||Ahgsb2.toString().equals("ac:16:2d:e7:e4:52")	//MUStaffPrivateEquipment
								  ||Ahgsb2.toString().equals("c8:cb:b8:ec:5a:b4")	//MUStaffPrivateEquipment
								  ||Ahgsb2.toString().equals("ac:16:2d:e7:d4:b2")	//MUStaffPrivateEquipment
								  ||Ahgsb2.toString().equals("ac:16:2d:e7:e4:f2")&& AgHScanResult1.level <= -70	//MUStudents
								  ||Ahgsb2.toString().equals("ac:16:2d:e7:f4:12")&& AgHScanResult1.level <= -70	//MUStudents
								  ||Ahgsb2.toString().equals("ac:16:2d:e7:e4:51")&& AgHScanResult1.level >= -70	//MUStudents
								  ||Ahgsb2.toString().equals("ac:16:2d:e7:e4:f3")	//inspirefreewifi
								  ||Ahgsb2.toString().equals("ac:16:2d:e7:f4:13")	//inspirefreewifi
								  ||Ahgsb2.toString().equals("ac:16:2d:e7:e4:54")	//inspirefreewifi
								  ||Ahgsb2.toString().equals("ac:16:2d:e7:d4:b4")	//inspirefreewifi
								  ||Ahgsb2.toString().equals("ac:16:2d:e7:e4:f5")	//studentcity
								  ||Ahgsb2.toString().equals("ac:16:2d:e7:f4:15")	//studentcity
								  ||Ahgsb2.toString().equals("ac:16:2d:e7:e4:55")	//studentcity
								  ||Ahgsb2.toString().equals("ac:16:2d:e7:d4:b5")	//studentcity
								  )
								  {
							  		Aghortcheck[1] = true;
								  }
						  
						  if (Ahgsb3.toString().equals("ac:16:2d:e7:e4:f1")			//EduRoam
								  ||Ahgsb3.toString().equals("ac:16:2d:e7:e4:53")	//EduRoam
								  ||Ahgsb3.toString().equals("ac:16:2d:e7:f4:11")	//EduRoam
								  ||Ahgsb3.toString().equals("ac:16:2d:e7:e4:50")&& AgHScanResult1.level >= -70	//MUStaff
								  ||Ahgsb3.toString().equals("ac:16:2d:e7:e4:f0")&& AgHScanResult1.level <= -70//MUStaff
								  ||Ahgsb3.toString().equals("ac:16:2d:e7:f4:10")&& AgHScanResult1.level <= -70	//MUStaff
								  ||Ahgsb3.toString().equals("ac:16:2d:e7:e4:f4")	//MUStaffPrivateEquipment
								  ||Ahgsb3.toString().equals("ac:16:2d:e7:f4:14")	//MUStaffPrivateEquipment
								  ||Ahgsb3.toString().equals("ac:16:2d:e7:e4:52")	//MUStaffPrivateEquipment
								  ||Ahgsb3.toString().equals("c8:cb:b8:ec:5a:b4")	//MUStaffPrivateEquipment
								  ||Ahgsb3.toString().equals("ac:16:2d:e7:d4:b2")	//MUStaffPrivateEquipment
								  ||Ahgsb3.toString().equals("ac:16:2d:e7:e4:f2")&& AgHScanResult1.level <= -70	//MUStudents
								  ||Ahgsb3.toString().equals("ac:16:2d:e7:f4:12")&& AgHScanResult1.level <= -70	//MUStudents
								  ||Ahgsb3.toString().equals("ac:16:2d:e7:e4:51")&& AgHScanResult1.level >= -70	//MUStudents
								  ||Ahgsb3.toString().equals("ac:16:2d:e7:e4:f3")	//inspirefreewifi
								  ||Ahgsb3.toString().equals("ac:16:2d:e7:f4:13")	//inspirefreewifi
								  ||Ahgsb3.toString().equals("ac:16:2d:e7:e4:54")	//inspirefreewifi
								  ||Ahgsb3.toString().equals("ac:16:2d:e7:d4:b4")	//inspirefreewifi
								  ||Ahgsb3.toString().equals("ac:16:2d:e7:e4:f5")	//studentcity
								  ||Ahgsb3.toString().equals("ac:16:2d:e7:f4:15")	//studentcity
								  ||Ahgsb3.toString().equals("ac:16:2d:e7:e4:55")	//studentcity
								  ||Ahgsb3.toString().equals("ac:16:2d:e7:d4:b5")	//studentcity
								  )
								  {
							  		Aghortcheck[2] = true;
								  }
						  
						  if (Ahgsb4.toString().equals("ac:16:2d:e7:e4:f1")			//EduRoam
								  ||Ahgsb4.toString().equals("ac:16:2d:e7:e4:53")	//EduRoam
								  ||Ahgsb4.toString().equals("ac:16:2d:e7:f4:11")	//EduRoam
								  ||Ahgsb4.toString().equals("ac:16:2d:e7:e4:50")&& AgHScanResult1.level >= -70	//MUStaff
								  ||Ahgsb4.toString().equals("ac:16:2d:e7:e4:f0")&& AgHScanResult1.level <= -70//MUStaff
								  ||Ahgsb4.toString().equals("ac:16:2d:e7:f4:10")&& AgHScanResult1.level <= -70	//MUStaff
								  ||Ahgsb4.toString().equals("ac:16:2d:e7:e4:f4")	//MUStaffPrivateEquipment
								  ||Ahgsb4.toString().equals("ac:16:2d:e7:f4:14")	//MUStaffPrivateEquipment
								  ||Ahgsb4.toString().equals("ac:16:2d:e7:e4:52")	//MUStaffPrivateEquipment
								  ||Ahgsb4.toString().equals("c8:cb:b8:ec:5a:b4")	//MUStaffPrivateEquipment
								  ||Ahgsb4.toString().equals("ac:16:2d:e7:d4:b2")	//MUStaffPrivateEquipment
								  ||Ahgsb4.toString().equals("ac:16:2d:e7:e4:f2")&& AgHScanResult1.level <= -70	//MUStudents
								  ||Ahgsb4.toString().equals("ac:16:2d:e7:f4:12")&& AgHScanResult1.level <= -70	//MUStudents
								  ||Ahgsb4.toString().equals("ac:16:2d:e7:e4:51")&& AgHScanResult1.level >= -70	//MUStudents
								  ||Ahgsb4.toString().equals("ac:16:2d:e7:e4:f3")	//inspirefreewifi
								  ||Ahgsb4.toString().equals("ac:16:2d:e7:f4:13")	//inspirefreewifi
								  ||Ahgsb4.toString().equals("ac:16:2d:e7:e4:54")	//inspirefreewifi
								  ||Ahgsb4.toString().equals("ac:16:2d:e7:d4:b4")	//inspirefreewifi
								  ||Ahgsb4.toString().equals("ac:16:2d:e7:e4:f5")	//studentcity
								  ||Ahgsb4.toString().equals("ac:16:2d:e7:f4:15")	//studentcity
								  ||Ahgsb4.toString().equals("ac:16:2d:e7:e4:55")	//studentcity
								  ||Ahgsb4.toString().equals("ac:16:2d:e7:d4:b5")	//studentcity
								  )
								  {
							  		Aghortcheck[3] = true;
								  }
						  
						  if (Ahgsb5.toString().equals("ac:16:2d:e7:e4:f1")			//EduRoam
								  ||Ahgsb5.toString().equals("ac:16:2d:e7:e4:53")	//EduRoam
								  ||Ahgsb5.toString().equals("ac:16:2d:e7:f4:11")	//EduRoam
								  ||Ahgsb5.toString().equals("ac:16:2d:e7:e4:50")&& AgHScanResult1.level >= -70	//MUStaff
								  ||Ahgsb5.toString().equals("ac:16:2d:e7:e4:f0")&& AgHScanResult1.level <= -70//MUStaff
								  ||Ahgsb5.toString().equals("ac:16:2d:e7:f4:10")&& AgHScanResult1.level <= -70	//MUStaff
								  ||Ahgsb5.toString().equals("ac:16:2d:e7:e4:f4")	//MUStaffPrivateEquipment
								  ||Ahgsb5.toString().equals("ac:16:2d:e7:f4:14")	//MUStaffPrivateEquipment
								  ||Ahgsb5.toString().equals("ac:16:2d:e7:e4:52")	//MUStaffPrivateEquipment
								  ||Ahgsb5.toString().equals("c8:cb:b8:ec:5a:b4")	//MUStaffPrivateEquipment
								  ||Ahgsb5.toString().equals("ac:16:2d:e7:d4:b2")	//MUStaffPrivateEquipment
								  ||Ahgsb5.toString().equals("ac:16:2d:e7:e4:f2")&& AgHScanResult1.level <= -70	//MUStudents
								  ||Ahgsb5.toString().equals("ac:16:2d:e7:f4:12")&& AgHScanResult1.level <= -70	//MUStudents
								  ||Ahgsb5.toString().equals("ac:16:2d:e7:e4:51")&& AgHScanResult1.level >= -70	//MUStudents
								  ||Ahgsb5.toString().equals("ac:16:2d:e7:e4:f3")	//inspirefreewifi
								  ||Ahgsb5.toString().equals("ac:16:2d:e7:f4:13")	//inspirefreewifi
								  ||Ahgsb5.toString().equals("ac:16:2d:e7:e4:54")	//inspirefreewifi
								  ||Ahgsb5.toString().equals("ac:16:2d:e7:d4:b4")	//inspirefreewifi
								  ||Ahgsb5.toString().equals("ac:16:2d:e7:e4:f5")	//studentcity
								  ||Ahgsb5.toString().equals("ac:16:2d:e7:f4:15")	//studentcity
								  ||Ahgsb5.toString().equals("ac:16:2d:e7:e4:55")	//studentcity
								  ||Ahgsb5.toString().equals("ac:16:2d:e7:d4:b5")	//studentcity
								  )
								  {
							  		Aghortcheck[4] = true;
								  }
						  
						  if(Aghortcheck[0] == true && Aghortcheck[1] == true && Aghortcheck[2] == true && Aghortcheck[3] == true && Aghortcheck[4] == true){
						  
							  	allNetWork.setText("You are close to Aghort Level 3 room 3.81" + " " + AgHScanResult1.level);
							  	//Toast.makeText(getApplicationContext(),"P1",Toast.LENGTH_SHORT).show();
								
								if (AgHPositionset[0] == true){
									MapLocation[] Llocations = new MapLocation[] {new StateLocation(this, 270, 160, R.drawable.destination_icon, R.drawable.destination_icon_selected, "Your are destination around Room 3.81"),
																					new StateLocation(this, 270, 160, R.drawable.location_icon, R.drawable.location_icon_selected, "You are around Room 3.81")
																				};
									map.setLocations(Llocations);
								}else
								if (AgHPositionset[1] == true){
									MapLocation[] Llocations = new MapLocation[] {new StateLocation(this, 700, 160, R.drawable.destination_icon, R.drawable.destination_icon_selected, "Your are destination around Room 3.64"),
																					new StateLocation(this, 270, 160, R.drawable.location_icon, R.drawable.location_icon_selected, "You are around Room 3.81")
																				};
									map.setLocations(Llocations);
								}else
								if (AgHPositionset[2] == true){
									MapLocation[] Llocations = new MapLocation[] {new StateLocation(this, 920, 160, R.drawable.destination_icon, R.drawable.destination_icon_selected, "Your are destination around Room 3.52"),
																					new StateLocation(this, 270, 160, R.drawable.location_icon, R.drawable.location_icon_selected, "You are around Room 3.81")
																				};
									map.setLocations(Llocations);
								}else
								if (AgHPositionset[3] == true){
									MapLocation[] Llocations = new MapLocation[] {new StateLocation(this, 1370, 200, R.drawable.destination_icon, R.drawable.destination_icon_selected, "Your are destination around Room 3.42"),
																					new StateLocation(this, 270, 160, R.drawable.location_icon, R.drawable.location_icon_selected, "You are around Room 3.81")
																					};
									map.setLocations(Llocations);
								}
								else
								{
									MapLocation[] Llocations = new MapLocation[] {new StateLocation(this, 270, 160, R.drawable.location_icon, R.drawable.location_icon_selected, "You are around Room 3.81")};
									map.setLocations(Llocations);
								}

						  }	else
							  	Aghortcheck[0] = false;
						  		Aghortcheck[1] = false;
						  		Aghortcheck[2] = false;
						  		Aghortcheck[3] = false;
						  		Aghortcheck[4] = false;
					  }
					  
//-----------------Position 3.64-----------------------------------------------------------------------------					  
						  if(Aghortcheck[0] == false && Aghortcheck[1] == false && Aghortcheck[2] == false && Aghortcheck[3] == false && Aghortcheck[4] == false){
							  
							  if (Ahgsb1.toString().equals("ac:16:2d:e7:f4:14")	//MUStaffPrivateEquipment			..-74 
									  ||Ahgsb1.toString().equals("ac:16:2d:e7:e4:f4")	//MUStaffPrivateEquipment	..-78
									  ||Ahgsb1.toString().equals("ac:16:2d:e7:e4:52")	//MUStaffPrivateEquipment	..-85
									  
									  ||Ahgsb1.toString().equals("ac:16:2d:e7:f4:10")	//MUStaff					..-74
									  ||Ahgsb1.toString().equals("ac:16:2d:e7:e4:50")	//MUStaff					..-80
									  
									  ||Ahgsb1.toString().equals("ac:16:2d:e7:f4:12")	//MUStudents				..-75
									  ||Ahgsb1.toString().equals("ac:16:2d:e7:e4:f2")	//MUStudents				..-78
									  ||Ahgsb1.toString().equals("ac:16:2d:e7:e4:51")	//MUStudents				..-85
									  
									  ||Ahgsb1.toString().equals("ac:16:2d:e7:f4:11")	//EduRoam					..-75
									  ||Ahgsb1.toString().equals("ac:16:2d:e7:e4:53")	//EduRoam					..-79

									  ||Ahgsb1.toString().equals("ac:16:2d:e7:f4:15")	//studentcity				..-74
									  ||Ahgsb1.toString().equals("ac:16:2d:e7:e4:f5")	//studentcity				..-78
									  ||Ahgsb1.toString().equals("ac:16:2d:e7:e4:55")	//studentcity				..-86
									  
									  ||Ahgsb1.toString().equals("ac:16:2d:e7:f4:13")	//inspirefreewifi			..-75
									  ||Ahgsb1.toString().equals("ac:16:2d:e7:e4:f3")	//inspirefreewifi			..-77
									  ||Ahgsb1.toString().equals("ac:16:2d:e7:e4:54")	//inspirefreewifi			..-80
									  )
									  {
								  		Aghortcheck[5] = true;
									  }

							  if (Ahgsb2.toString().equals("ac:16:2d:e7:f4:14")	//MUStaffPrivateEquipment			..-74 
									  ||Ahgsb2.toString().equals("ac:16:2d:e7:e4:f4")	//MUStaffPrivateEquipment	..-78
									  ||Ahgsb2.toString().equals("ac:16:2d:e7:e4:52")	//MUStaffPrivateEquipment	..-85
									  
									  ||Ahgsb2.toString().equals("ac:16:2d:e7:f4:10")	//MUStaff					..-74
									  ||Ahgsb2.toString().equals("ac:16:2d:e7:e4:50")	//MUStaff					..-80
									  
									  ||Ahgsb2.toString().equals("ac:16:2d:e7:f4:12")	//MUStudents				..-75
									  ||Ahgsb2.toString().equals("ac:16:2d:e7:e4:f2")	//MUStudents				..-78
									  ||Ahgsb2.toString().equals("ac:16:2d:e7:e4:51")	//MUStudents				..-85
									  
									  ||Ahgsb2.toString().equals("ac:16:2d:e7:f4:11")	//EduRoam					..-75
									  ||Ahgsb2.toString().equals("ac:16:2d:e7:e4:53")	//EduRoam					..-79

									  ||Ahgsb2.toString().equals("ac:16:2d:e7:f4:15")	//studentcity				..-74
									  ||Ahgsb2.toString().equals("ac:16:2d:e7:e4:f5")	//studentcity				..-78
									  ||Ahgsb2.toString().equals("ac:16:2d:e7:e4:55")	//studentcity				..-86
									  
									  ||Ahgsb2.toString().equals("ac:16:2d:e7:f4:13")	//inspirefreewifi			..-75
									  ||Ahgsb2.toString().equals("ac:16:2d:e7:e4:f3")	//inspirefreewifi			..-77
									  ||Ahgsb2.toString().equals("ac:16:2d:e7:e4:54")	//inspirefreewifi			..-80
									  )
									  {
								  		Aghortcheck[6] = true;
									  }
							  
							  if (Ahgsb3.toString().equals("ac:16:2d:e7:f4:14")	//MUStaffPrivateEquipment			..-74 
									  ||Ahgsb3.toString().equals("ac:16:2d:e7:e4:f4")	//MUStaffPrivateEquipment	..-78
									  ||Ahgsb3.toString().equals("ac:16:2d:e7:e4:52")	//MUStaffPrivateEquipment	..-85
									  
									  ||Ahgsb3.toString().equals("ac:16:2d:e7:f4:10")	//MUStaff					..-74
									  ||Ahgsb3.toString().equals("ac:16:2d:e7:e4:50")	//MUStaff					..-80
									  
									  ||Ahgsb3.toString().equals("ac:16:2d:e7:f4:12")	//MUStudents				..-75
									  ||Ahgsb3.toString().equals("ac:16:2d:e7:e4:f2")	//MUStudents				..-78
									  ||Ahgsb3.toString().equals("ac:16:2d:e7:e4:51")	//MUStudents				..-85
									  
									  ||Ahgsb3.toString().equals("ac:16:2d:e7:f4:11")	//EduRoam					..-75
									  ||Ahgsb3.toString().equals("ac:16:2d:e7:e4:53")	//EduRoam					..-79

									  ||Ahgsb3.toString().equals("ac:16:2d:e7:f4:15")	//studentcity				..-74
									  ||Ahgsb3.toString().equals("ac:16:2d:e7:e4:f5")	//studentcity				..-78
									  ||Ahgsb3.toString().equals("ac:16:2d:e7:e4:55")	//studentcity				..-86
									  
									  ||Ahgsb3.toString().equals("ac:16:2d:e7:f4:13")	//inspirefreewifi			..-75
									  ||Ahgsb3.toString().equals("ac:16:2d:e7:e4:f3")	//inspirefreewifi			..-77
									  ||Ahgsb3.toString().equals("ac:16:2d:e7:e4:54")	//inspirefreewifi			..-80
									  )
									  {
								  		Aghortcheck[7] = true;
									  }
							  
							  if (Ahgsb4.toString().equals("ac:16:2d:e7:f4:14")	//MUStaffPrivateEquipment			..-74 
									  ||Ahgsb4.toString().equals("ac:16:2d:e7:e4:f4")	//MUStaffPrivateEquipment	..-78
									  ||Ahgsb4.toString().equals("ac:16:2d:e7:e4:52")	//MUStaffPrivateEquipment	..-85
									  
									  ||Ahgsb4.toString().equals("ac:16:2d:e7:f4:10")	//MUStaff					..-74
									  ||Ahgsb4.toString().equals("ac:16:2d:e7:e4:50")	//MUStaff					..-80
									  
									  ||Ahgsb4.toString().equals("ac:16:2d:e7:f4:12")	//MUStudents				..-75
									  ||Ahgsb4.toString().equals("ac:16:2d:e7:e4:f2")	//MUStudents				..-78
									  ||Ahgsb4.toString().equals("ac:16:2d:e7:e4:51")	//MUStudents				..-85
									  
									  ||Ahgsb4.toString().equals("ac:16:2d:e7:f4:11")	//EduRoam					..-75
									  ||Ahgsb4.toString().equals("ac:16:2d:e7:e4:53")	//EduRoam					..-79

									  ||Ahgsb4.toString().equals("ac:16:2d:e7:f4:15")	//studentcity				..-74
									  ||Ahgsb4.toString().equals("ac:16:2d:e7:e4:f5")	//studentcity				..-78
									  ||Ahgsb4.toString().equals("ac:16:2d:e7:e4:55")	//studentcity				..-86
									  
									  ||Ahgsb4.toString().equals("ac:16:2d:e7:f4:13")	//inspirefreewifi			..-75
									  ||Ahgsb4.toString().equals("ac:16:2d:e7:e4:f3")	//inspirefreewifi			..-77
									  ||Ahgsb4.toString().equals("ac:16:2d:e7:e4:54")	//inspirefreewifi			..-80
									  )
									  {
								  		Aghortcheck[8] = true;
									  }
							  
							  if (Ahgsb5.toString().equals("ac:16:2d:e7:f4:14")	//MUStaffPrivateEquipment			..-74 
									  ||Ahgsb5.toString().equals("ac:16:2d:e7:e4:f4")	//MUStaffPrivateEquipment	..-78
									  ||Ahgsb5.toString().equals("ac:16:2d:e7:e4:52")	//MUStaffPrivateEquipment	..-85
									  
									  ||Ahgsb5.toString().equals("ac:16:2d:e7:f4:10")	//MUStaff					..-74
									  ||Ahgsb5.toString().equals("ac:16:2d:e7:e4:50")	//MUStaff					..-80
									  
									  ||Ahgsb5.toString().equals("ac:16:2d:e7:f4:12")	//MUStudents				..-75
									  ||Ahgsb5.toString().equals("ac:16:2d:e7:e4:f2")	//MUStudents				..-78
									  ||Ahgsb5.toString().equals("ac:16:2d:e7:e4:51")	//MUStudents				..-85
									  
									  ||Ahgsb5.toString().equals("ac:16:2d:e7:f4:11")	//EduRoam					..-75
									  ||Ahgsb5.toString().equals("ac:16:2d:e7:e4:53")	//EduRoam					..-79

									  ||Ahgsb5.toString().equals("ac:16:2d:e7:f4:15")	//studentcity				..-74
									  ||Ahgsb5.toString().equals("ac:16:2d:e7:e4:f5")	//studentcity				..-78
									  ||Ahgsb5.toString().equals("ac:16:2d:e7:e4:55")	//studentcity				..-86
									  
									  ||Ahgsb5.toString().equals("ac:16:2d:e7:f4:13")	//inspirefreewifi			..-75
									  ||Ahgsb5.toString().equals("ac:16:2d:e7:e4:f3")	//inspirefreewifi			..-77
									  ||Ahgsb5.toString().equals("ac:16:2d:e7:e4:54")	//inspirefreewifi			..-80
									  )
									  {
								  		Aghortcheck[9] = true;
									  }
							  
							  if(Aghortcheck[5] == true && Aghortcheck[6] == true && Aghortcheck[7] == true && Aghortcheck[8] == true && Aghortcheck[9] == true){
							  
								  	allNetWork.setText("You are close to Aghort Level 3 room 3.64" + " " + AgHScanResult1.level);
								  	//Toast.makeText(getApplicationContext(),"P1",Toast.LENGTH_SHORT).show();
									
									if (AgHPositionset[0] == true){
										MapLocation[] Llocations = new MapLocation[] {new StateLocation(this, 270, 160, R.drawable.destination_icon, R.drawable.destination_icon_selected, "Your are destination around Room 3.81"),
																						new StateLocation(this, 700, 160, R.drawable.location_icon, R.drawable.location_icon_selected, "You are around Room 3.64")
																					};
										map.setLocations(Llocations);
									}else
									if (AgHPositionset[1] == true){
										MapLocation[] Llocations = new MapLocation[] {new StateLocation(this, 700, 160, R.drawable.destination_icon, R.drawable.destination_icon_selected, "Your are destination around Room 3.64"),
																						new StateLocation(this, 700, 160, R.drawable.location_icon, R.drawable.location_icon_selected, "You are around Room 3.64")
																					};
										map.setLocations(Llocations);
									}else
									if (AgHPositionset[2] == true){
										MapLocation[] Llocations = new MapLocation[] {new StateLocation(this, 920, 160, R.drawable.destination_icon, R.drawable.destination_icon_selected, "Your are destination around Room 3.52"),
																						new StateLocation(this, 700, 160, R.drawable.location_icon, R.drawable.location_icon_selected, "You are around Room 3.64")
																					};
										map.setLocations(Llocations);
									}else
									if (AgHPositionset[3] == true){
										MapLocation[] Llocations = new MapLocation[] {new StateLocation(this, 1370, 200, R.drawable.destination_icon, R.drawable.destination_icon_selected, "Your are destination around Room 3.42"),
																							new StateLocation(this, 700, 160, R.drawable.location_icon, R.drawable.location_icon_selected, "You are around Room 3.64")
																						};
										map.setLocations(Llocations);
									}
									else
									{
										MapLocation[] Llocations = new MapLocation[] {new StateLocation(this, 700, 160, R.drawable.location_icon, R.drawable.location_icon_selected, "You are around Room 3.64")};
										map.setLocations(Llocations);
									}
							  }	else
								  	Aghortcheck[5] = false;
							  		Aghortcheck[6] = false;
							  		Aghortcheck[7] = false;
							  		Aghortcheck[8] = false;
							  		Aghortcheck[9] = false;
						  }
					  
					  
//-----------------Position 3.52-----------------------------------------------------------------------------
						  	if(Aghortcheck[5] == false && Aghortcheck[6] == false && Aghortcheck[7] == false && Aghortcheck[8] == false && Aghortcheck[9] == false){
							  
							  if (		Ahgsb1.toString().equals("ac:16:2d:e7:e4:f0")	//MUStaff					..-60
									  
									  ||Ahgsb1.toString().equals("ac:16:2d:e7:e4:f2")	//MUStudents				..-60
									  ||Ahgsb1.toString().equals("ac:16:2d:e7:d4:d2")	//MUStudents				..-91
									  
									  ||Ahgsb1.toString().equals("ac:16:2d:e7:e4:f1")	//EduRoam					..-60

									  ||Ahgsb1.toString().equals("ac:16:2d:e7:e4:f5")	//studentcity				..-78
									  
									  ||Ahgsb1.toString().equals("ac:16:2d:e7:e4:f3")	//inspirefreewifi			..-77
									  )
									  {
								  		Aghortcheck[10] = true;
									  }

							  if (Ahgsb2.toString().equals("ac:16:2d:e7:e4:f0")	//MUStaff					..-60
									  
									  ||Ahgsb2.toString().equals("ac:16:2d:e7:e4:f2")	//MUStudents				..-60
									  ||Ahgsb2.toString().equals("ac:16:2d:e7:d4:d2")	//MUStudents				..-91
									  
									  ||Ahgsb2.toString().equals("ac:16:2d:e7:e4:f1")	//EduRoam					..-60

									  ||Ahgsb2.toString().equals("ac:16:2d:e7:e4:f5")	//studentcity				..-78
									  
									  ||Ahgsb2.toString().equals("ac:16:2d:e7:e4:f3")	//inspirefreewifi			..-77
									  )
									  {
								  		Aghortcheck[11] = true;
									  }
							  
							  if (Ahgsb3.toString().equals("ac:16:2d:e7:e4:f0")	//MUStaff					..-60
									  
									  ||Ahgsb3.toString().equals("ac:16:2d:e7:e4:f2")	//MUStudents				..-60
									  ||Ahgsb3.toString().equals("ac:16:2d:e7:d4:d2")	//MUStudents				..-91
									  
									  ||Ahgsb3.toString().equals("ac:16:2d:e7:e4:f1")	//EduRoam					..-60

									  ||Ahgsb3.toString().equals("ac:16:2d:e7:e4:f5")	//studentcity				..-78
									  
									  ||Ahgsb3.toString().equals("ac:16:2d:e7:e4:f3")	//inspirefreewifi			..-77
									  )
									  {
								  		Aghortcheck[12] = true;
									  }
							  
							  if (Ahgsb4.toString().equals("ac:16:2d:e7:e4:f0")	//MUStaff					..-60
									  
									  ||Ahgsb4.toString().equals("ac:16:2d:e7:e4:f2")	//MUStudents				..-60
									  ||Ahgsb4.toString().equals("ac:16:2d:e7:d4:d2")	//MUStudents				..-91
									  
									  ||Ahgsb4.toString().equals("ac:16:2d:e7:e4:f1")	//EduRoam					..-60

									  ||Ahgsb4.toString().equals("ac:16:2d:e7:e4:f5")	//studentcity				..-78
									  
									  ||Ahgsb4.toString().equals("ac:16:2d:e7:e4:f3")	//inspirefreewifi			..-77
									  )
									  {
								  		Aghortcheck[13] = true;
									  }
							  
							  if (Ahgsb5.toString().equals("ac:16:2d:e7:e4:f0")	//MUStaff					..-60
									  
									  ||Ahgsb5.toString().equals("ac:16:2d:e7:e4:f2")	//MUStudents				..-60
									  ||Ahgsb5.toString().equals("ac:16:2d:e7:d4:d2")	//MUStudents				..-91
									  
									  ||Ahgsb5.toString().equals("ac:16:2d:e7:e4:f1")	//EduRoam					..-60

									  ||Ahgsb5.toString().equals("ac:16:2d:e7:e4:f5")	//studentcity				..-78
									  
									  ||Ahgsb5.toString().equals("ac:16:2d:e7:e4:f3")	//inspirefreewifi			..-77
									  )
									  {
								  		Aghortcheck[14] = true;
									  }
							  
							  if(Aghortcheck[10] == true && Aghortcheck[11] == true && Aghortcheck[12] == true && Aghortcheck[13] == true && Aghortcheck[14] == true){
							  
								  	allNetWork.setText("You are close to Aghort Level 3 room 3.52" + " " + AgHScanResult1.level);
								  	//Toast.makeText(getApplicationContext(),"P1",Toast.LENGTH_SHORT).show();
									
									if (AgHPositionset[0] == true){
										MapLocation[] Llocations = new MapLocation[] {new StateLocation(this, 270, 160, R.drawable.destination_icon, R.drawable.destination_icon_selected, "Your are destination around Room 3.81"),
																						new StateLocation(this, 920, 160, R.drawable.location_icon, R.drawable.location_icon_selected, "You are around Room 3.52")
																					};
										map.setLocations(Llocations);
									}else
									if (AgHPositionset[1] == true){
										MapLocation[] Llocations = new MapLocation[] {new StateLocation(this, 700, 160, R.drawable.destination_icon, R.drawable.destination_icon_selected, "Your are destination around Room 3.64"),
																						new StateLocation(this, 920, 160, R.drawable.location_icon, R.drawable.location_icon_selected, "You are around Room 3.52")
																					};
										map.setLocations(Llocations);
									}else
									if (AgHPositionset[2] == true){
										MapLocation[] Llocations = new MapLocation[] {new StateLocation(this, 920, 160, R.drawable.destination_icon, R.drawable.destination_icon_selected, "Your are destination around Room 3.52"),
																						new StateLocation(this, 920, 160, R.drawable.location_icon, R.drawable.location_icon_selected, "You are around Room 3.52")
																					};
										map.setLocations(Llocations);
									}else
									if (AgHPositionset[3] == true){
										MapLocation[] Llocations = new MapLocation[] {new StateLocation(this, 1370, 200, R.drawable.destination_icon, R.drawable.destination_icon_selected, "Your are destination around Room 3.42"),
																							new StateLocation(this, 920, 160, R.drawable.location_icon, R.drawable.location_icon_selected, "You are around Room 3.52")
																						};
										map.setLocations(Llocations);
									}
									else
									{
										MapLocation[] Llocations = new MapLocation[] {new StateLocation(this, 920, 160, R.drawable.location_icon, R.drawable.location_icon_selected, "You are around Room 3.52")};
										map.setLocations(Llocations);
									}
							  }	else
								  	Aghortcheck[10] = false;
							  		Aghortcheck[11] = false;
							  		Aghortcheck[12] = false;
							  		Aghortcheck[13] = false;
							  		Aghortcheck[14] = false;
						  }
					  
					  
//-----------------Position 3.42-----------------------------------------------------------------------------
						  	if(Aghortcheck[10] == false && Aghortcheck[11] == false && Aghortcheck[12] == false && Aghortcheck[13] == false && Aghortcheck[14] == false){
								  
								  if (   	Ahgsb1.toString().equals("ac:16:2d:e7:e4:d2")	//MUStaffPrivateEquipment	..-91 
										  ||Ahgsb1.toString().equals("ac:16:2d:e7:e4:f4")	//MUStaffPrivateEquipment	..-65
										  
										  ||Ahgsb1.toString().equals("ac:16:2d:e7:e4:f0")	//MUStaff					..-67
										  
										  ||Ahgsb1.toString().equals("ac:16:2d:e7:e4:d1")	//MUStudents				..-86
										  ||Ahgsb1.toString().equals("ac:16:2d:e7:e4:f2")	//MUStudents				..-66
										  
										  ||Ahgsb1.toString().equals("ac:16:2d:e7:e4:f1")	//EduRoam					..-65
										  ||Ahgsb1.toString().equals("ac:16:2d:e7:e4:d3")	//EduRoam					..-88

										  ||Ahgsb1.toString().equals("ac:16:2d:e7:d4:d3")	//studentcity				..-89
										  ||Ahgsb1.toString().equals("ac:16:2d:e7:e4:f5")	//studentcity				..-66

										  ||Ahgsb1.toString().equals("ac:16:2d:e7:e4:d4")	//inspirefreewifi			..-86
										  ||Ahgsb1.toString().equals("ac:16:2d:e7:e4:f3")	//inspirefreewifi			..-66
										  )
										  {
									  		Aghortcheck[15] = true;
										  }

								  if (   	Ahgsb2.toString().equals("ac:16:2d:e7:e4:d2")	//MUStaffPrivateEquipment	..-91 
										  ||Ahgsb2.toString().equals("ac:16:2d:e7:e4:f4")	//MUStaffPrivateEquipment	..-65
										  
										  ||Ahgsb2.toString().equals("ac:16:2d:e7:e4:f0")	//MUStaff					..-67
										  
										  ||Ahgsb2.toString().equals("ac:16:2d:e7:e4:d1")	//MUStudents				..-86
										  ||Ahgsb2.toString().equals("ac:16:2d:e7:e4:f2")	//MUStudents				..-66
										  
										  ||Ahgsb2.toString().equals("ac:16:2d:e7:e4:f1")	//EduRoam					..-65
										  ||Ahgsb2.toString().equals("ac:16:2d:e7:e4:d3")	//EduRoam					..-88

										  ||Ahgsb2.toString().equals("ac:16:2d:e7:d4:d3")	//studentcity				..-89
										  ||Ahgsb2.toString().equals("ac:16:2d:e7:e4:f5")	//studentcity				..-66

										  ||Ahgsb2.toString().equals("ac:16:2d:e7:e4:d4")	//inspirefreewifi			..-86
										  ||Ahgsb2.toString().equals("ac:16:2d:e7:e4:f3")	//inspirefreewifi			..-66
										  )
										  {
									  		Aghortcheck[16] = true;
										  }
								  
								  if (   	Ahgsb3.toString().equals("ac:16:2d:e7:e4:d2")	//MUStaffPrivateEquipment	..-91 
										  ||Ahgsb3.toString().equals("ac:16:2d:e7:e4:f4")	//MUStaffPrivateEquipment	..-65
										  
										  ||Ahgsb3.toString().equals("ac:16:2d:e7:e4:f0")	//MUStaff					..-67
										  
										  ||Ahgsb3.toString().equals("ac:16:2d:e7:e4:d1")	//MUStudents				..-86
										  ||Ahgsb3.toString().equals("ac:16:2d:e7:e4:f2")	//MUStudents				..-66
										  
										  ||Ahgsb3.toString().equals("ac:16:2d:e7:e4:f1")	//EduRoam					..-65
										  ||Ahgsb3.toString().equals("ac:16:2d:e7:e4:d3")	//EduRoam					..-88

										  ||Ahgsb3.toString().equals("ac:16:2d:e7:d4:d3")	//studentcity				..-89
										  ||Ahgsb3.toString().equals("ac:16:2d:e7:e4:f5")	//studentcity				..-66

										  ||Ahgsb3.toString().equals("ac:16:2d:e7:e4:d4")	//inspirefreewifi			..-86
										  ||Ahgsb3.toString().equals("ac:16:2d:e7:e4:f3")	//inspirefreewifi			..-66
										  )
										  {
									  		Aghortcheck[17] = true;
										  }
								  
								  if (   	Ahgsb4.toString().equals("ac:16:2d:e7:e4:d2")	//MUStaffPrivateEquipment	..-91 
										  ||Ahgsb4.toString().equals("ac:16:2d:e7:e4:f4")	//MUStaffPrivateEquipment	..-65
										  
										  ||Ahgsb4.toString().equals("ac:16:2d:e7:e4:f0")	//MUStaff					..-67
										  
										  ||Ahgsb4.toString().equals("ac:16:2d:e7:e4:d1")	//MUStudents				..-86
										  ||Ahgsb4.toString().equals("ac:16:2d:e7:e4:f2")	//MUStudents				..-66
										  
										  ||Ahgsb4.toString().equals("ac:16:2d:e7:e4:f1")	//EduRoam					..-65
										  ||Ahgsb4.toString().equals("ac:16:2d:e7:e4:d3")	//EduRoam					..-88

										  ||Ahgsb4.toString().equals("ac:16:2d:e7:d4:d3")	//studentcity				..-89
										  ||Ahgsb4.toString().equals("ac:16:2d:e7:e4:f5")	//studentcity				..-66

										  ||Ahgsb4.toString().equals("ac:16:2d:e7:e4:d4")	//inspirefreewifi			..-86
										  ||Ahgsb4.toString().equals("ac:16:2d:e7:e4:f3")	//inspirefreewifi			..-66
										  )
										  {
									  		Aghortcheck[18] = true;
										  }
								  
								  if (Ahgsb5.toString().equals("ac:16:2d:e7:e4:d2")			//MUStaffPrivateEquipment	..-91 
										  ||Ahgsb5.toString().equals("ac:16:2d:e7:e4:f4")	//MUStaffPrivateEquipment	..-65
										  
										  ||Ahgsb5.toString().equals("ac:16:2d:e7:e4:f0")	//MUStaff					..-67
										  
										  ||Ahgsb5.toString().equals("ac:16:2d:e7:e4:d1")	//MUStudents				..-86
										  ||Ahgsb5.toString().equals("ac:16:2d:e7:e4:f2")	//MUStudents				..-66
										  
										  ||Ahgsb5.toString().equals("ac:16:2d:e7:e4:f1")	//EduRoam					..-65
										  ||Ahgsb5.toString().equals("ac:16:2d:e7:e4:d3")	//EduRoam					..-88

										  ||Ahgsb5.toString().equals("ac:16:2d:e7:d4:d3")	//studentcity				..-89
										  ||Ahgsb5.toString().equals("ac:16:2d:e7:e4:f5")	//studentcity				..-66

										  ||Ahgsb5.toString().equals("ac:16:2d:e7:e4:d4")	//inspirefreewifi			..-86
										  ||Ahgsb5.toString().equals("ac:16:2d:e7:e4:f3")	//inspirefreewifi			..-66
										  )
										  {
									  		Aghortcheck[19] = true;
										  }
								  
								  if(Aghortcheck[15] == true && Aghortcheck[16] == true && Aghortcheck[17] == true && Aghortcheck[18] == true && Aghortcheck[19] == true){
								  
									  	allNetWork.setText("You are close to Aghort Level 3 room 3.64" + " " + AgHScanResult1.level);
									  	//Toast.makeText(getApplicationContext(),"P1",Toast.LENGTH_SHORT).show();
										
										if (AgHPositionset[0] == true){
											MapLocation[] Llocations = new MapLocation[] {new StateLocation(this, 270, 160, R.drawable.destination_icon, R.drawable.destination_icon_selected, "Your are destination Outside Room 3.81"),
																								new StateLocation(this, 1370, 200, R.drawable.location_icon, R.drawable.location_icon_selected, "You are around Room 3.42")
																						};
											map.setLocations(Llocations);
										}else
										if (AgHPositionset[1] == true){
											MapLocation[] Llocations = new MapLocation[] {new StateLocation(this, 700, 160, R.drawable.destination_icon, R.drawable.destination_icon_selected, "Your are destination Outside Room 3.64"),
																								new StateLocation(this, 1370, 200, R.drawable.location_icon, R.drawable.location_icon_selected, "You are around Room 3.42")
																						};
											map.setLocations(Llocations);
										}else
										if (AgHPositionset[2] == true){
											MapLocation[] Llocations = new MapLocation[] {new StateLocation(this, 920, 160, R.drawable.destination_icon, R.drawable.destination_icon_selected, "Your are destination Outside Room 3.52"),
																								new StateLocation(this, 1370, 200, R.drawable.location_icon, R.drawable.location_icon_selected, "You are around Room 3.42")
																						};
											map.setLocations(Llocations);
										}else
										if (AgHPositionset[3] == true){
											MapLocation[] Llocations = new MapLocation[] {new StateLocation(this, 1370, 200, R.drawable.destination_icon, R.drawable.destination_icon_selected, "Your are destination Outside Room 3.42"),
																								new StateLocation(this, 1370, 200, R.drawable.location_icon, R.drawable.location_icon_selected, "You are around Room 3.42")
																							};
											map.setLocations(Llocations);
										}
										else
										{
											MapLocation[] Llocations = new MapLocation[] {new StateLocation(this, 1370, 200, R.drawable.location_icon, R.drawable.location_icon_selected, "You are around Room 3.42")};
											map.setLocations(Llocations);
										}
								  }	else
									  	Aghortcheck[15] = false;
								  		Aghortcheck[16] = false;
								  		Aghortcheck[17] = false;
								  		Aghortcheck[18] = false;
								  		Aghortcheck[19] = false;
							  }
						  	else
						  	{
						  		Toast.makeText(getApplicationContext(),"Can't found your position",Toast.LENGTH_SHORT).show();
						  	}
			}
		}
	}
