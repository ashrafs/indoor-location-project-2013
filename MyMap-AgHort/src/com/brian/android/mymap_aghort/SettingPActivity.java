package com.brian.android.mymap_aghort;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ToggleButton;

public class SettingPActivity extends Activity implements OnClickListener {
	
	private Button oksetting_button, return_button;
	ToggleButton pointN_toggleButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.program_main);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

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

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if((pointN_toggleButton.isChecked()))
		{
			
		}
		
	}
	
}
