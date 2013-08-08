package com.brian.android.mymap_aghort;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

public class SettingDActivity extends Activity {

	private String[] Levelstring, Roomstring;
	Spinner Levelspinner, Roomspinner; // Assigning a name for spinner
	private TextView leveltext, roomtext;
	private Button oksetting_button, return_button;
	String SelectedLevel, SelectedRoom;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.destination_main);
		addListenerOnButton();

		// /////////////////SPINNER/////////////////////////

		ArrayAdapter leveladapter;// Assigning a name for ArrayAdapter
		ArrayAdapter roomadapter;// Assigning a name for ArrayAdapter

		Resources Levelres = getResources();// Assigning a name for Resources
		Resources Roomres = getResources();// Assigning a name for Resources

		Levelstring = Levelres.getStringArray(R.array.levelvalue);// getting the
																	// array
																	// items
		// to string named my string
		// mystring is an array which is defined on the top
		Roomstring = Levelres.getStringArray(R.array.roomvalue);// getting the
																// array
																// items
		// to string named my string
		// mystring is an array which is defined on the top

		Levelspinner = (Spinner) findViewById(R.id.levelspinner); // samplespinner
																	// is
																	// defined
																	// in
																	// the top
		// samplespinner is the name given to the spinner at the top

		Roomspinner = (Spinner) findViewById(R.id.roomspinner); // samplespinner
		// is
		// defined
		// in
		// the top
		// samplespinner is the name given to the spinner at the top

		leveladapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, Levelstring);
		Levelspinner.setAdapter(leveladapter);
		
		roomadapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, Roomstring);
		Roomspinner.setAdapter(roomadapter);

		Levelspinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// Toast.makeText(getBaseContext(),
				// spVIA.getSelectedItem().toString(),
				// Toast.LENGTH_LONG).show();

				SelectedLevel = Levelspinner.getSelectedItem().toString();
				leveltext = (TextView) findViewById(R.id.leveltext);

				leveltext.setText(SelectedLevel);

			}

			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
		});
		
		Roomspinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// Toast.makeText(getBaseContext(),
				// spVIA.getSelectedItem().toString(),
				// Toast.LENGTH_LONG).show();

				SelectedRoom = Roomspinner.getSelectedItem().toString();
				roomtext = (TextView) findViewById(R.id.roomtext);

				roomtext.setText(SelectedRoom);

			}

			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
		});
	}

	// ////////////////////////SPINNER
	// ENDS///////////////////////////////////////////////////
	/*
	 * Roomsp.setOnItemSelectedListener(new OnItemSelectedListener() {
	 * 
	 * @Override public void onItemSelected(AdapterView<?> arg0, View arg1, int
	 * arg2, long arg3) { String s=((TextView)arg1).getText().toString();
	 * if(s.equals("Level 1")) locattext.setText("You Have choose Room 1");
	 * if(s.equals("Level 2")) locattext.setText("You Have choose Room 2");
	 * if(s.equals("Level 3")) locattext.setText("You Have choose Room 3"); }
	 * 
	 * @Override public void onNothingSelected(AdapterView<?> arg0) { // TODO
	 * Auto-generated method stub
	 * 
	 * }
	 * 
	 * });
	 */

	private void addListenerOnButton() {

		final Context context = this;

		return_button = (Button) findViewById(R.id.return_button);

		return_button.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {

				Intent intent = new Intent(context, MainActivity.class);
				startActivity(intent);
			}
		});
	}
}
