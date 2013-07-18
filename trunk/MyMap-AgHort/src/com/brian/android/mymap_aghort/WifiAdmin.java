package com.brian.android.mymap_aghort;

import java.util.List;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.net.wifi.WifiManager.WifiLock;

public class WifiAdmin {

	//Define a WifiManager Object
	private WifiManager mWifiManager;
	
	//Define a WifiInfo Object
	private WifiInfo mWifiInfo;
	
	//List the scanned WiFi result
	private List<ScanResult> mWifiList;
	
	//List the WiFi network Configuration
	private List<WifiConfiguration> mWifiConfigurations;
	
	//WiFiLock use for avoid the WiFi been sleep after 2min in default.
	WifiLock mWifiLock;
	
	
	public WifiAdmin(Context context){
		
		//Get the WifiManager Object
		mWifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
		
		//Get the WifiInfo Object - Return dynamic information about the current Wi-Fi connection, if any is active.
		mWifiInfo = mWifiManager.getConnectionInfo();
	}
	
	
	//Open WiFi
	public void openWifi(){
		//Return whether Wi-Fi is enabled or disabled.
		if (!mWifiManager.isWifiEnabled()){
			
			//Enable or disable Wi-Fi.
			mWifiManager.setWifiEnabled(true);
		}
	}
	
	//Close WiFi
	public void closeWifi(){
		
		if (mWifiManager.isWifiEnabled()){
			
			//Enable or disable Wi-Fi.
			mWifiManager.setWifiEnabled(false);
		}
	}
	
	//Check the current WiFi status
	public int checkState(){
		
		//Gets the Wi-Fi enabled state.
		return mWifiManager.getWifiState();
	}
	
	//Lock WifiLock
	public void acquireWifiLock(){
		
		//each call to acquire will increment the reference count, and the radio will remain locked as long as the reference count is above zero.
		mWifiLock.acquire();
	}
	
	
	//UnLock WifiLock
	public void releaseWifiLock(){
		
		//Check is the WifiLock been Locked
		if(mWifiLock.isHeld()){
			
			mWifiLock.acquire();
		}
	}
	
	//Create a WifiLock
	public void createWifiLock(){
		mWifiLock = mWifiManager.createWifiLock("test");
	}
	
	//Get the WiFi Configuration
	public List<WifiConfiguration> getConfiguration(){
		return mWifiConfigurations;
	}
	
	//Connect with the Configurated WiFi
	public void connectionConfiguration (int index){
		if (index > mWifiConfigurations.size()){
			return;
		}
		
		//Connect with WiFi which given unique ID
		//Allow a previously configured network to be associated with. 
		//If disableOthers is true, then all other configured networks are disabled, and an attempt to connect to the selected network is initiated. 
		//This may result in the asynchronous delivery of state change events
		//networkID - The ID number that the supplicant uses to identify this network configuration entry. This must be passed as an argument to most calls into the supplicant. 
		mWifiManager.enableNetwork(mWifiConfigurations.get(index).networkId, true);
	}
	
	
	//Start Scan WiFi
	public void startScan(){
		
		//Request a scan for access points. Returns immediately. 
		//The availability of the results is made known later by means of an asynchronous event sent on completion of the scan.
		mWifiManager.startScan();
		
		//Get Scan Result
		//Return the results of the latest access point scan.
		mWifiList = mWifiManager.getScanResults();
		
		//Get the WiFi Configurations
		/*Return a list of all the networks configured in the supplicant. Not all fields of WifiConfiguration are returned. Only the following fields are filled in: 

			networkId 
			SSID 
			BSSID 
			priority 
			allowedProtocols 
			allowedKeyManagement 
			allowedAuthAlgorithms 
			allowedPairwiseCiphers 
			allowedGroupCiphers    
			*/
		mWifiConfigurations = mWifiManager.getConfiguredNetworks();
	}
	
	//Get WiFi List
	public List<ScanResult> getWifiList(){
		return mWifiList;
	}
	
	//Check Scan Result
	public StringBuffer lookUpScan(){
		StringBuffer sb = new StringBuffer();
		for (int i=0; i <mWifiList.size(); i++){
			sb.append("Index_" + new Integer (i+1).toString() + ":");
			
			//Transfer the ScanResult to String
			//Include BSSID, SSID, Capabilities, Frequency, Level
			sb.append((mWifiList.get(i)).toString()).append("\n");
		}
		
		return sb;
	}
	
	//Get Mac Address
	public String getMacAddress(){
		return (mWifiInfo == null)?"NULL":mWifiInfo.getMacAddress();
	}
	
	//Get BSSID
	public String getBSSID(){
		
		/*
		 * Return the basic service set identifier (BSSID) of the current access point. 
		 * The BSSID may be null if there is no network currently connected.
		 */
		return (mWifiInfo == null)?"NULL":mWifiInfo.getBSSID();
	}
	
	
	//Get IP Address
	public int getIpAddress(){
		return (mWifiInfo == null)?0:mWifiInfo.getIpAddress();
	}
	
	//Get Connected ID
	public int getNetWordID(){
		
		/*
		 * Each configured network has a unique small integer ID, 
		 * used to identify the network when performing operations on the supplicant. 
		 * This method returns the ID for the currently connected network.
		 */
		return (mWifiInfo == null)?0:mWifiInfo.getNetworkId();
	}
	
	//Get Wifi Info
	public String getWifiInfo(){
		return (mWifiInfo == null)?"NULL":mWifiInfo.toString();
	}
	
	//Add and Connect new WiFi Network
	public void addNetWork(WifiConfiguration configuration){
		
		/*
		 * Add a new network description to the set of configured networks. 
		 * The networkId field of the supplied configuration object is ignored. 
		 * The new network will be marked DISABLED by default. 
		 * To enable it, called enableNetwork(int, boolean).
		 */
		int wcgId = mWifiManager.addNetwork(configuration);
		mWifiManager.enableNetwork(wcgId, true);
	}
	
	//Disconnect Wifi with given ID
	public void disConnectionWifi (int netID){
		
		/*
		 * Disable a configured network. 
		 * The specified network will not be a candidate for associating. 
		 * This may result in the asynchronous delivery of state change events.
		 */
		mWifiManager.disableNetwork(netID);

		/*
		 * Disassociate from the currently active access point. 
		 * This may result in the asynchronous delivery of state change events.
		 */
		mWifiManager.disconnect();
	}
}
