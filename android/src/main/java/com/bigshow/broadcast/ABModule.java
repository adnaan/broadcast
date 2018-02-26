package com.bigshow.broadcast;

import android.widget.Toast;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;


import java.util.HashMap;
import java.util.Map;

public class ABModule extends ReactContextBaseJavaModule {
    private static ABView surfaceView;
    private static boolean isSurfaceCreated;

    public ABModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Override
    public String getName() {
        return "ABModule";
    }

    @Override
    public Map<String, Object> getConstants() {
        final Map<String, Object> constants = new HashMap<>();
        return constants;
    }

    @ReactMethod
    public void show(String message) {
        Toast.makeText(getReactApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    @ReactMethod
    public void startStream(String rtmpUrl, Promise promise) {
       
    }

    @ReactMethod
    public void stopStream(Promise promise) {
       
    }


    @ReactMethod
    public void startPreview() {
       
    }

    @ReactMethod
    public void stopPreview() {
      
    }

    @ReactMethod
    public void disableVideo() {
       
    }

    @ReactMethod
    public void disableAudio() {
       
    }

    @ReactMethod
    public void enableAudio() {
       
    }

    @ReactMethod
    public void enableVideo() {
       
    }

    @ReactMethod
    public void switchCamera(Promise promise) {
      
    }

    public static void setSurfaceView(ABView surface) {
      
    }

    public static void destroySurfaceView() {
      
    }
}
