package com.nomath4u.values;

import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class MainActivity extends Activity {
	
	private SensorManager mSensorManager;
	private Sensor mAccelerometer;
	private Sensor mLightSensor;
	private Sensor mOrientationSensor;
	private SensorEventListener mAccelerometerListener;
	private SensorEventListener mLightSensorListener;
	private SensorEventListener mOrientationSensorListener;
	private TextView mTextViewAccelerometer;
	private TextView mTextViewLight;
	private TextView mTextViewOrientation;
	private TextView mTextViewSensors;
	private List<Sensor> deviceSensors;
	private String listOfSensors = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        mTextViewAccelerometer = (TextView) findViewById(R.id.text_accelerometer);
        mTextViewLight = (TextView) findViewById(R.id.text_light);
        mTextViewOrientation = (TextView) findViewById(R.id.text_orientation);
        mTextViewSensors = (TextView) findViewById(R.id.text_sensors);
        
        initListeners();
        
       
        	 
        
        // Get an instance of the SensorManager
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mLightSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        mOrientationSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
        mSensorManager.registerListener(mAccelerometerListener, mAccelerometer,SensorManager.SENSOR_DELAY_UI);
        mSensorManager.registerListener(mLightSensorListener, mLightSensor, SensorManager.SENSOR_DELAY_UI);
        mSensorManager.registerListener(mOrientationSensorListener, mOrientationSensor, SensorManager.SENSOR_DELAY_UI);
        
        //Get all Sensors and print them out
        deviceSensors = mSensorManager.getSensorList(Sensor.TYPE_ALL);
        for (Sensor sensor : deviceSensors){
        	listOfSensors += (sensor.getName() + "\n");
        }
        mTextViewSensors.setText(listOfSensors);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    private void initListeners(){
    	mAccelerometerListener = new SensorEventListener(){
    		@Override
    		public void onSensorChanged(SensorEvent event){
    			float[] values = event.values;
    			mTextViewAccelerometer.setText("Accelerometer: " + values[0] + ", " + values [1] + ", " + values[2]);
    		}
    		
    		@Override
    		public void onAccuracyChanged(Sensor sensor, int accuracy){
    			
    		}
    	};
    	mLightSensorListener = new SensorEventListener(){
    		@Override
    		public void onSensorChanged(SensorEvent event){
    			float[] values2 = event.values;
    			mTextViewLight.setText("Light Sensor: " + values2[0]);
    		}
    		
    		@Override
    		public void onAccuracyChanged(Sensor sensor, int accuracy){
    		}
    	};
    	mOrientationSensorListener = new SensorEventListener(){
    		@Override
    		public void onSensorChanged(SensorEvent event){
    			float[] value = event.values;
    			//TO-DO add and describe other orientation sensor values
    			mTextViewOrientation.setText("Orientation: " + value[0]);
    		}
    		
    		@Override
    		public void onAccuracyChanged(Sensor sensor, int accuracy){
    		}
    	};
    		    	
    }
}
