package com.brian.android.mymap_aghort;

import android.os.Bundle;

import com.brian.android.mymap.MapView;

public class NextMapActivity extends MainActivity {
   //Called when the activity is first created. 
  @Override
  public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);

      map = (MapView) findViewById(R.id.map);
  }
}