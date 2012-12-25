package com.nomath4u.values;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class MainActivity extends Activity {
	
	private SensorManager mSensorManager;
	private List<Sensor> deviceSensors;
	private ArrayList<SensorEventListener> mListOfSensorListeners = new ArrayList<SensorEventListener>();
	private LinearLayout linearLayout;
	private ArrayList<TextView> mTextViews = new ArrayList<TextView>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
       
        linearLayout = (LinearLayout)findViewById(R.id.stuffz);
        
        
       
        	 
        
        // Get an instance of the SensorManager
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
      
        
        //Get all Sensors
        deviceSensors = mSensorManager.getSensorList(Sensor.TYPE_ALL);
        
        
        initListeners();
        registerViews();
        
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    private void initListeners(){
    	//Fill List with Listeners
    	
    	for ( Sensor sensor : deviceSensors){
    		final TextView tView = new TextView(this);
    		 tView.setLayoutParams(new ViewGroup.LayoutParams(
                     ViewGroup.LayoutParams.FILL_PARENT,
                     ViewGroup.LayoutParams.WRAP_CONTENT));
    		final Sensor thisSensor = sensor;
    		SensorEventListener tmp = new SensorEventListener(){
    			@Override
    			public void onSensorChanged(SensorEvent event){
    				float[] values = event.values;
    				tView.setText(thisSensor.getName() + ": \n");
    				for (float number : values){
    					//Add each value to the text view
    					tView.setText(tView.getText() + String.format("%.5g%n", number) + " ");
    				}
    			}
    			@Override
    			public void onAccuracyChanged(Sensor sensor, int accuracy){
    				
    			}
    		};
    	mSensorManager.registerListener(tmp,sensor,SensorManager.SENSOR_DELAY_UI);	
    	mListOfSensorListeners.add(tmp);
    	mTextViews.add(tView);
    	
    	}
    }
    
    void registerViews(){
    	for(TextView view : mTextViews){
    		linearLayout.addView(view);
    	}
    }
}
