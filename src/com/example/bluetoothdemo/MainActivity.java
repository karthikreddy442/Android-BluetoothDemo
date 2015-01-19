package com.example.bluetoothdemo;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

	private Button On,Off,Visible,list;
	   private BluetoothAdapter BA;
	   private Set<BluetoothDevice>pairedDevices;
	   private ListView lv;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	
		On = (Button)findViewById(R.id.button1);
	      Visible= (Button)findViewById(R.id.button2);
	      list= (Button)findViewById(R.id.button3);
Off	       = (Button)findViewById(R.id.button4);

	      lv = (ListView)findViewById(R.id.listView1);

	      BA = BluetoothAdapter.getDefaultAdapter();
	
	      On.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
		if (!BA.isEnabled()){
        Intent turnBluetoothOn=new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
		startActivityForResult(turnBluetoothOn, 0);
		 Toast.makeText(getApplicationContext(),"Bluetooth turned on" 
		         ,Toast.LENGTH_LONG).show();
		      
		}else{
	         Toast.makeText(getApplicationContext(),"Bluetooth already on",
	         Toast.LENGTH_LONG).show();
		}
			}
		});
	
	      Off.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(BA.isEnabled()){
				BA.disable();
				Toast.makeText(getApplicationContext(), "Bluetooth turned off", Toast.LENGTH_LONG).show();
				}
				else{
					Toast.makeText(getApplicationContext(), "Bluetooth already off", Toast.LENGTH_LONG).show();
}}
		});
	
	Visible.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			
			Intent setVisible=new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
			startActivityForResult(setVisible, 0);
			
		}
	});
	
	list.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
		pairedDevices=BA.getBondedDevices();	
		List list=new ArrayList();
		for(BluetoothDevice bd: pairedDevices){

			list.add(bd);	
		} 
		
		Toast.makeText(getApplication(), "Showing paired devices", Toast.LENGTH_SHORT).show();
		final ArrayAdapter ad= new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, list);
	lv.setAdapter(ad);
		}
		
	});
	      
	
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
