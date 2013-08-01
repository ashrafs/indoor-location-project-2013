package com.brian.android.mymap_aghort;

import java.util.List;

import android.app.Activity;
import android.net.wifi.ScanResult;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.brian.android.mymap.MapView;
import com.brian.android.util.ImageUtil;
import com.brian.android.mymap_aghort.WifiAdmin;
import com.brian.android.mymap_aghort.R;

public class MainActivity extends Activity {

	protected MapView map;
	private StringBuffer sb = new StringBuffer();
	private Button ScanButton;
	private TextView allNetWork;
	private WifiAdmin mWifiAdmin;
	private List<ScanResult> list;
	private ScanResult mScanResult, mScanResult1,mScanResult2,HScanResult1,HScanResult2;
	boolean Rcheck, Rcheck1, Rcheck2, Hcheck1, Hcheck2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mWifiAdmin = new WifiAdmin(MainActivity.this);
		map = (MapView) findViewById(R.id.map);
		map.setMapImage(ImageUtil.loadBitmapFromResource(getResources(),
				R.drawable.map3));

		allNetWork = (TextView) findViewById(R.id.allNetWork);
		addListenerOnButton();

	}

	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		
		//Handle item selection
		switch (item.getItemId()){
		case R.id.AgHort_1:
			map = (MapView) findViewById(R.id.map);
			map.setMapImage(ImageUtil.loadBitmapFromResource(getResources(),
					R.drawable.map1));
			return true;
			
		case R.id.AgHort_2:
			map = (MapView) findViewById(R.id.map);
			map.setMapImage(ImageUtil.loadBitmapFromResource(getResources(),
					R.drawable.map2));
			return true;
			
		case R.id.AgHort_3:
			map = (MapView) findViewById(R.id.map);
			map.setMapImage(ImageUtil.loadBitmapFromResource(getResources(),
					R.drawable.map3));
			return true;
			
		case R.id.SCLocation:
			
			return true;
		case R.id.SDestination:
			return true;
			
		default: return super.onOptionsItemSelected(item);
		}
		/*
		if (item.getItemId() == R.id.AgHort_1) {
			map = (MapView) findViewById(R.id.map);
			map.setMapImage(ImageUtil.loadBitmapFromResource(getResources(),
					R.drawable.map1));
			return true;
		} else if (item.getItemId() == R.id.AgHort_2) {
			map = (MapView) findViewById(R.id.map);
			map.setMapImage(ImageUtil.loadBitmapFromResource(getResources(),
					R.drawable.map2));
			return true;
		} else if (item.getItemId() == R.id.AgHort_3) {
			map = (MapView) findViewById(R.id.map);
			map.setMapImage(ImageUtil.loadBitmapFromResource(getResources(),
					R.drawable.map3));
			return true;
		} else if (item.getItemId() == R.id.SCLocation){
			
		} else if (item.getItemId() == R.id.SDestination){
			
		}
		
		return super.onOptionsItemSelected(item);
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

	/**
	 * Called when user clicks Zoom out button.
	 * 
	 * @param v
	 */
	public void zoomOut(View v) {
		float oldRatio = map.getZoomRatio();
		float newRatio = oldRatio - .1f;
		if (newRatio < 0.01f) {
			newRatio = 0.01f;
		}
		map.zoom(newRatio);
	}

	/**
	 * Called when user clicks Zoom in button.
	 * 
	 * @param v
	 */
	public void zoomIn(View v) {
		float oldRatio = map.getZoomRatio();
		float newRatio = oldRatio + .1f;
		if (newRatio > 1) {
			newRatio = 1;
		}
		map.zoom(newRatio);
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

				if (mScanResult.level < -65) {

					  if (mScanResult.SSID.toString().equals("MUStudents")) {	  
					  if (mScanResult.BSSID.toString().equals("ac:16:2d:e7:f4:02") 
					  || mScanResult.BSSID.toString().equals( "ac:16:2d:e7:e4:e2")
					  || mScanResult.BSSID.toString().equals( "ac:16:2d:e7:e4:41")) 
					  { 
						  Rcheck = true; 
						  if(mScanResult1.SSID.toString().equals("MUStaff")) {
							  if (mScanResult1.BSSID.toString().equals("ac:16:2d:e7:e4:40")) {
								  Rcheck1 = true; 
								  if (mScanResult2.SSID.toString().equals("EduRoam")) { 
									  if (mScanResult2.BSSID.toString().equals( "ac:16:2d:e7:e4:43")) { 
										  Rcheck2 = true; 
										  } 
									  }
								  } 
							  }
						  } 
					  } 
					  
					  if (mScanResult.SSID.toString().equals("MUStaff")) 
					  {	  
						  if (mScanResult.BSSID.toString().equals("ac:16:2d:e7:e4:40")) { 
							  Rcheck = true; 
							  if(mScanResult1.SSID.toString().equals("EduRoam")) {
								  if (mScanResult1.BSSID.toString().equals("ac:16:2d:e7:e4:43")) {
									  Rcheck1 = true; 
									  if (mScanResult2.SSID.toString().equals("MUStudents")) {	  
										  if (mScanResult2.BSSID.toString().equals("ac:16:2d:e7:f4:02") 
										  || mScanResult2.BSSID.toString().equals( "ac:16:2d:e7:e4:e2")
										  || mScanResult2.BSSID.toString().equals( "ac:16:2d:e7:e4:41")) 
										  { 
											  Rcheck2 = true; 
											  } 
										  }
									  } 
								  }
							  } 
						  } 
					  
					  if(mScanResult.SSID.toString().equals("EduRoam")) {
						  if (mScanResult.BSSID.toString().equals("ac:16:2d:e7:e4:43")) {
							  Rcheck = true; 
							  if (mScanResult1.SSID.toString().equals("MUStudents")) {	  
								  if (mScanResult1.BSSID.toString().equals("ac:16:2d:e7:f4:02") 
								  || mScanResult1.BSSID.toString().equals( "ac:16:2d:e7:e4:e2")
								  || mScanResult1.BSSID.toString().equals( "ac:16:2d:e7:e4:41")) 
								  {
									  Rcheck1 = true; 
									  if (mScanResult2.SSID.toString().equals("MUStaff")) 
									  {	  
										  if (mScanResult2.BSSID.toString().equals("ac:16:2d:e7:e4:40")) { 
											  Rcheck2 = true; 
											  } 
										  }
									  } 
								  }
							  } 
						  } 
					 /* if(mScanResult1.SSID.toString().equals("MUStaff")) {
						  if (mScanResult1.BSSID.toString().equals("ac:16:2d:e7:e4:40")) {
							  Rcheck1 = true; 
							  } 
						  }
					  
					  if (mScanResult2.SSID.toString().equals("EduRoam")) { 
						  if (mScanResult2.BSSID.toString().equals( "ac:16:2d:e7:e4:43")) { 
							  Rcheck2 = true; 
							  } 
						  }
						  
						  */
					  
					  if (Rcheck == true || Rcheck1 == true || Rcheck2 == true)
					  { 
						  // check MUStudent found or MUStaff found or EduRoam found
					  allNetWork.setText("You are around Room 3.65"); 
					  map = (MapView) findViewById(R.id.map);
					  map.setMapImage(ImageUtil.loadBitmapFromResource(getResources(), R.drawable.map4)); 
					  Toast.makeText(getApplicationContext(),"Locate by either MUStudent or MUStaff or EduRoam",Toast.LENGTH_SHORT).show();
					  } 
					  else{	
						  	if (Rcheck == true && Rcheck1 == true || Rcheck2 == true) { 
						  // check MUStudent found and MUStaff found or EduRoam found
						  		allNetWork.setText("You are around Room 3.65"); 
						  		map = (MapView) findViewById(R.id.map);
						  		map.setMapImage(ImageUtil.loadBitmapFromResource(getResources(), R.drawable.map4));
						  		Toast.makeText(getApplicationContext(), "Locate by MUStudent and MUStaff or EduRoam",Toast.LENGTH_SHORT).show();
						  		}
						  		else{					  
						  			if (Rcheck == true || Rcheck1 == true && Rcheck2 == true){ 
						  				// check MUStudent found or MUStaff found and EduRoam found
					  	  				allNetWork.setText("You are around Room 3.65"); 
					  	  				map = (MapView) findViewById(R.id.map);
					  	  				map.setMapImage(ImageUtil.loadBitmapFromResource(getResources(), R.drawable.map4));
					  	  				Toast.makeText(getApplicationContext(), "Locate by MUStudent or MUStaff and EduRoam",Toast.LENGTH_SHORT).show();
						  				} 
						  				else{
						  						if (Rcheck == true && Rcheck1 == true && Rcheck2 == true) { // check MUStudent found and MUStaff found and EduRoam found
						  							allNetWork.setText("You are around Room 3.65"); 
						  							map = (MapView) findViewById(R.id.map);
						  							map.setMapImage(ImageUtil.loadBitmapFromResource(getResources(), R.drawable.map4));
						  							Toast.makeText(getApplicationContext(), "Locate by MUStudent, MUStaff and EduRoam",Toast.LENGTH_SHORT).show(); 
						  							} else {
						  									allNetWork.setText("You are not around Room 3.65 "); //end  of  scan  check 
						  									}
						  					}
						  			}
					  		}
				}
				else { // if signal strength is greater than -85dB
				  Toast.makeText(getApplicationContext(),
				  "No Wifi for 3.65", Toast.LENGTH_SHORT).show();// end of Room check 3.65 
				  }
					 
					//For Home Use
					if (HScanResult1.SSID.toString().equals("Vodafone0BF3")
							|| HScanResult2.SSID.toString()
									.equals("NETGEAR_18")) // Check
					// Vodafone
					{
						Hcheck1 = true;

						if (HScanResult1.SSID.toString().equals("NETGEAR_18")
								|| HScanResult2.SSID.toString().equals(
										"Vodafone0BF3")) // Check
						// NETGEAR
						{
							Hcheck2 = true;
						}
					}

					if (Hcheck1 == true && Hcheck2 == true) {
						allNetWork.setText("You are around home");
						Toast.makeText(getApplicationContext(),
								"Locate by NETGEAR_18 and Vodafone",
								Toast.LENGTH_SHORT).show();
					} else {

						if (Hcheck1 == true || Hcheck2 == true) {
							allNetWork.setText("You are around home");
							Toast.makeText(getApplicationContext(),
									"Locate by NETGEAR_18 or Vodafone",
									Toast.LENGTH_SHORT).show();
						} else {
							allNetWork.setText("You are not around Home ");
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