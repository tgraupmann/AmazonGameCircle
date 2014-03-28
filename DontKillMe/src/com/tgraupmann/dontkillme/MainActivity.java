package com.tgraupmann.dontkillme;

import android.os.Bundle;
import android.os.SystemClock;
import android.app.Activity;
import android.util.Log;
import android.util.SparseArray;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.MotionEvent.PointerCoords;
import android.view.MotionEvent.PointerProperties;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
	
	private static final String TAG = MainActivity.class.getSimpleName();
	
	private Button btnClickMe = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btnClickMe = (Button)findViewById(R.id.btnClickMe);
		btnClickMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	simulateClick();
            }
        });
		takeKeyEvents(true);
	}
	
	public void superDispatchKeyEvent(KeyEvent keyEvent) {
		super.dispatchKeyEvent(keyEvent);
	}
	
	private int mId = 0;
	
	public void simulateClick() {
		super.dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, mId));
    	super.dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_UP, mId));
    	++mId;
    	
    	SparseArray<String> names = new SparseArray<String>();
        names.append(MotionEvent.AXIS_X, "AXIS_X");
        names.append(MotionEvent.AXIS_Y, "AXIS_Y");
        names.append(MotionEvent.AXIS_PRESSURE, "AXIS_PRESSURE");
        names.append(MotionEvent.AXIS_SIZE, "AXIS_SIZE");
        names.append(MotionEvent.AXIS_TOUCH_MAJOR, "AXIS_TOUCH_MAJOR");
        names.append(MotionEvent.AXIS_TOUCH_MINOR, "AXIS_TOUCH_MINOR");
        names.append(MotionEvent.AXIS_TOOL_MAJOR, "AXIS_TOOL_MAJOR");
        names.append(MotionEvent.AXIS_TOOL_MINOR, "AXIS_TOOL_MINOR");
        names.append(MotionEvent.AXIS_ORIENTATION, "AXIS_ORIENTATION");
        names.append(MotionEvent.AXIS_VSCROLL, "AXIS_VSCROLL");
        names.append(MotionEvent.AXIS_HSCROLL, "AXIS_HSCROLL");
        names.append(MotionEvent.AXIS_Z, "AXIS_Z");
        names.append(MotionEvent.AXIS_RX, "AXIS_RX");
        names.append(MotionEvent.AXIS_RY, "AXIS_RY");
        names.append(MotionEvent.AXIS_RZ, "AXIS_RZ");
        names.append(MotionEvent.AXIS_HAT_X, "AXIS_HAT_X");
        names.append(MotionEvent.AXIS_HAT_Y, "AXIS_HAT_Y");
        names.append(MotionEvent.AXIS_LTRIGGER, "AXIS_LTRIGGER");
        names.append(MotionEvent.AXIS_RTRIGGER, "AXIS_RTRIGGER");
        names.append(MotionEvent.AXIS_THROTTLE, "AXIS_THROTTLE");
        names.append(MotionEvent.AXIS_RUDDER, "AXIS_RUDDER");
        names.append(MotionEvent.AXIS_WHEEL, "AXIS_WHEEL");
        names.append(MotionEvent.AXIS_GAS, "AXIS_GAS");
        names.append(MotionEvent.AXIS_BRAKE, "AXIS_BRAKE");
        names.append(MotionEvent.AXIS_DISTANCE, "AXIS_DISTANCE");
        names.append(MotionEvent.AXIS_TILT, "AXIS_TILT");
        names.append(MotionEvent.AXIS_GENERIC_1, "AXIS_GENERIC_1");
        names.append(MotionEvent.AXIS_GENERIC_2, "AXIS_GENERIC_2");
        names.append(MotionEvent.AXIS_GENERIC_3, "AXIS_GENERIC_3");
        names.append(MotionEvent.AXIS_GENERIC_4, "AXIS_GENERIC_4");
        names.append(MotionEvent.AXIS_GENERIC_5, "AXIS_GENERIC_5");
        names.append(MotionEvent.AXIS_GENERIC_6, "AXIS_GENERIC_6");
        names.append(MotionEvent.AXIS_GENERIC_7, "AXIS_GENERIC_7");
        names.append(MotionEvent.AXIS_GENERIC_8, "AXIS_GENERIC_8");
        names.append(MotionEvent.AXIS_GENERIC_9, "AXIS_GENERIC_9");
        names.append(MotionEvent.AXIS_GENERIC_10, "AXIS_GENERIC_10");
        names.append(MotionEvent.AXIS_GENERIC_11, "AXIS_GENERIC_11");
        names.append(MotionEvent.AXIS_GENERIC_12, "AXIS_GENERIC_12");
        names.append(MotionEvent.AXIS_GENERIC_13, "AXIS_GENERIC_13");
        names.append(MotionEvent.AXIS_GENERIC_14, "AXIS_GENERIC_14");
        names.append(MotionEvent.AXIS_GENERIC_15, "AXIS_GENERIC_15");
        names.append(MotionEvent.AXIS_GENERIC_16, "AXIS_GENERIC_16");
		
    	PointerProperties[] pointerProperties = new PointerProperties[1];
    	pointerProperties[0] = new PointerProperties();
    	PointerCoords[] pointerCoords = new PointerCoords[1];
    	pointerCoords[0] = new PointerCoords();
    	
    	final int count = names.size();
    	for (int i = 0; i < count; i++) {
            pointerCoords[0].setAxisValue(names.keyAt(i), 0);
        }	
	    
	    long downTime = SystemClock.uptimeMillis();
    	long eventTime = SystemClock.uptimeMillis() + 100;
    	int action = 0;
    	int metaState = 0;
    	int buttonState = 0;
    	float xPrecision = 1;
    	float yPrecision = 1;
    	int deviceId = 0;
    	int edgeFlags = 0;
    	int source = 0;
    	int flags = 0;
    	
    	int pointerCount = 1;
    	MotionEvent motionEvent = MotionEvent.obtain(downTime, eventTime, action,
    			pointerCount, pointerProperties, pointerCoords,
    			metaState, buttonState, xPrecision, yPrecision, deviceId, edgeFlags,
    			source, flags);
    	super.dispatchGenericMotionEvent(motionEvent);
    	motionEvent.recycle();
	}
	
	@Override
	public boolean dispatchGenericMotionEvent(MotionEvent motionEvent) {
		Log.i(TAG, "dispatchGenericMotionEvent");
		return true;
	}

	@Override
	public boolean dispatchKeyEvent(KeyEvent keyEvent) {
		Log.i(TAG, "dispatchKeyEvent keyCode="+keyEvent.getKeyCode());
		return true;
	}
	
	@Override
	public boolean onGenericMotionEvent(MotionEvent motionEvent) {
		Log.i(TAG, "onGenericMotionEvent");
		return true;
	}
	
	@Override
	public void onBackPressed() {
		Log.i(TAG, "onBackPressed");
		super.onBackPressed();
	}

	@Override
	protected void onDestroy() {
		Log.i(TAG, "onDestroy");
		super.onDestroy();
	}

	@Override
	public boolean onKeyLongPress(int keyCode, KeyEvent event) {
		Log.i(TAG, "onKeyLongPress keyCode="+event.getKeyCode());
		return super.onKeyLongPress(keyCode, event);
	}

	@Override
	public boolean onKeyMultiple(int keyCode, int repeatCount, KeyEvent event) {
		Log.i(TAG, "onKeyMultiple keyCode="+event.getKeyCode());
		return super.onKeyMultiple(keyCode, repeatCount, event);
	}

	@Override
	public boolean onKeyShortcut(int keyCode, KeyEvent event) {
		Log.i(TAG, "onKeyShortcut keyCode="+event.getKeyCode());
		return super.onKeyShortcut(keyCode, event);
	}
	
	@Override
	protected void onPause() {
		Log.i(TAG, "onPause");
		super.onPause();
	}

	@Override
	protected void onRestart() {
		Log.i(TAG, "onRestart");
		super.onRestart();
	}

	@Override
	protected void onResume() {
		Log.i(TAG, "onResume");
		super.onResume();
	}

	@Override
	protected void onStart() {
		Log.i(TAG, "onStart");
		super.onStart();
	}

	@Override
	protected void onStop() {
		Log.i(TAG, "onStop");
		super.onStop();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent keyEvent) {
		Log.i(TAG, "onKeyDown keyCode="+keyCode);
		return true;
	}
	
	@Override
	public boolean onKeyUp(int keyCode, KeyEvent keyEvent) {
		Log.i(TAG, "onKeyUp keyCode="+keyCode);
		return true;
	}
}