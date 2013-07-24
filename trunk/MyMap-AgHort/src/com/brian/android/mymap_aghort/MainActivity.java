package com.brian.android.mymap_aghort;

import java.util.List;

import android.app.Activity;
import android.net.wifi.ScanResult;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.brian.android.mymap.MapView;
import com.brian.android.util.ImageUtil;
import com.brian.android.mymap_aghort.WifiAdmin;
import com.brian.android.mymap.MapLocation;
import com.brian.android.mymap_aghort.R;
import com.brian.android.mymap_aghort.StateLocation;

public class MainActivity extends Activity {

	protected MapView map;
	private StringBuffer sb = new StringBuffer();
	private Button ScanButton;
	private Button btn;
	private TextView allNetWork;
	private WifiAdmin mWifiAdmin;
	private List<ScanResult> list;
	private ScanResult mScanResult;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		btn = (Button) findViewById(R.id.btnoptions);
		registerForContextMenu(btn);
		mWifiAdmin = new WifiAdmin(MainActivity.this);
		btn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				openContextMenu(v);
			}
		});

		map = (MapView) findViewById(R.id.map);
		map.setMapImage(ImageUtil.loadBitmapFromResource(getResources(),
				R.drawable.map3));

		allNetWork = (TextView) findViewById(R.id.allNetWork);
		addListenerOnButton();

	}

	/*
	 * private MapLocation[] createLocations() { MapLocation[] locations = new
	 * MapLocation[] { new StateLocation(this, 720, 685, "Pond")}; return
	 * locations; }
	 */

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenu.ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		getMenuInflater().inflate(R.menu.contextmenu, menu);
		menu.setHeaderTitle("Select the Level");
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		switch (item.getItemId()) {
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
		}
		return super.onContextItemSelected(item);
	}

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
				mScanResult = list.get(i);
				// sb = sb.append(mScanResult.level +"dBm" + "\n\n");

				// sb = sb.append(mWifiAdmin.getWifiInfo());

				sb = sb.append(mScanResult.BSSID + "   ")
						.append(mScanResult.SSID + "   ")
						.append(mScanResult.capabilities + "   ")
						.append(mScanResult.frequency + "Hz" + "   ")
						.append(mScanResult.level + "dBm" + "\n\n");

				if (mScanResult.level < -70
						&& mScanResult.SSID.toString().equals("MUStudents")
						) {
					// if (mScanResult.level < -70 &&
					// mScanResult.SSID.toString().equals("NETGEAR_18") ){
					// allNetWork.setText("Find you");
					if (mScanResult.BSSID.toString()
							.equals("ac:16:2d:e7:f4:02")
							|| mScanResult.BSSID.toString().equals(
									"ac:16:2d:e7:e4:e2")) {
						allNetWork.setText("You are using " + mScanResult.SSID);
						map = (MapView) findViewById(R.id.map);
						map.setMapImage(ImageUtil.loadBitmapFromResource(
								getResources(), R.drawable.map4));
					} else {
						allNetWork.setText("You are not around Room 3.65 ");
					}
				} else {
					Toast.makeText(getApplicationContext(), "No Wifi for 3.65",
							Toast.LENGTH_SHORT).show();
				}
				// allNetWork.setText("Scanned WiFi Network: \n" +
				// mWifiAdmin.getRssi());

				if (mScanResult.level < -70
						&& mScanResult.SSID.toString().equals("NETGEAR_18")) {
					allNetWork.setText("You are using " + mScanResult.SSID);
				}else {
					allNetWork.setText("You are not around Home ");
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