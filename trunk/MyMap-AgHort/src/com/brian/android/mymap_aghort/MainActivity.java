package com.brian.android.mymap_aghort;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import com.brian.android.mymap.MapView;
import com.brian.android.util.ImageUtil;

public class MainActivity extends Activity {

	protected MapView map;
	private StringBuffer sb = new StringBuffer();
	private Button ScanButton;
	TextView textBssid;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		
        map = (MapView) findViewById(R.id.map);
        map.setMapImage(ImageUtil.loadBitmapFromResource(getResources(), R.drawable.map2));
       // mWifiAdmin = new WifiAdmin(MainActivity.this);
        textBssid = (TextView)findViewById(R.id.Bssid);
        addListenerOnButton();
	}
	
	private void addListenerOnButton() {
		
			ScanButton = (Button) findViewById(R.id.ScanButton);
			
			ScanButton.setOnClickListener(new OnClickListener(){
				
				@Override
				public void onClick(View view){
						//getAllNetWorkList();
						DisplayWifiState();
				}
			});
		}

	/**
     * Called when user clicks Zoom out button.
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
     * @param v
     */
    
/*    public void getAllNetWorkList() {
		
		//Clear previous Scan record
		if (sb != null)
		{
			sb = new StringBuffer();
		}
		
		//Start Scan Network
		mWifiAdmin.startScan();
		list = mWifiAdmin.getWifiList();
		
		if (list != null){
			for (int i = 0; i < list.size(); i++){
				
				//Get Scan Result
				mScanResult = list.get(i);
				//sb = sb.append(mScanResult.level +"dBm" + "\n\n");
				sb = sb.append(mScanResult.BSSID + "\n\n");
			}
			NetWorkRSSI.setMovementMethod(new ScrollingMovementMethod());
			NetWorkRSSI.setText("Current Connected Wi-Fi: \n" + sb.toString());
		}
		
	}
	
	*/
    
private void DisplayWifiState(){
	    
	    ConnectivityManager myConnManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
	    myConnManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
	    WifiManager myWifiManager = (WifiManager)getSystemService(Context.WIFI_SERVICE);
	  WifiInfo myWifiInfo = myWifiManager.getConnectionInfo();
	  textBssid.setText(myWifiInfo.getBSSID());
	  
}
}