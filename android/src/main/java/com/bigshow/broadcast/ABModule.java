package com.bigshow.broadcast;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.Log;
import android.widget.Toast;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.hardware.Camera;
import android.os.IBinder;



import java.util.HashMap;
import java.util.Map;

import com.facebook.react.bridge.BaseActivityEventListener;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;


import io.antmedia.android.broadcaster.ILiveVideoBroadcaster;
import io.antmedia.android.broadcaster.LiveVideoBroadcaster;
import io.antmedia.android.broadcaster.utils.Resolution;

public class ABModule extends ReactContextBaseJavaModule implements  ServiceConnection, LifecycleEventListener {
    private ABView surfaceView;
    private boolean isSurfaceCreated;
    private ILiveVideoBroadcaster mLiveVideoBroadcaster;
    private Intent mLiveVideoBroadcasterServiceIntent;
    private Resolution defaultResolution = new Resolution(180,320);
    private static final String TAG = "bigshow-broadcast";

    @Override
    public void onServiceConnected(ComponentName componentName, IBinder service) {

        // We've bound to LocalService, cast the IBinder and get LocalService instance
        LiveVideoBroadcaster.LocalBinder binder = (LiveVideoBroadcaster.LocalBinder) service;
        if (mLiveVideoBroadcaster == null) {
            mLiveVideoBroadcaster = binder.getService();
        }

    }

    @Override
    public void onServiceDisconnected(ComponentName componentName) {

    }



    public ABModule(ReactApplicationContext reactContext) {
        super(reactContext);
       // this.surfaceView = surfaceView;
        mLiveVideoBroadcasterServiceIntent = new Intent(getReactApplicationContext(), LiveVideoBroadcaster.class);
        getReactApplicationContext().bindService(mLiveVideoBroadcasterServiceIntent, this, Context.BIND_AUTO_CREATE);
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
        promise.resolve(mLiveVideoBroadcaster.startBroadcasting(rtmpUrl));
    }

    @ReactMethod
    public void stopStream(Promise promise) {
        mLiveVideoBroadcaster.stopBroadcasting();
        promise.resolve(true);
    }


    @ReactMethod
    public void startPreview() {

    }

    @ReactMethod
    public void stopPreview() {
        if (mLiveVideoBroadcaster !=null){
            mLiveVideoBroadcaster.pause();
        }
    }

    @ReactMethod
    public void setResolution(int width, int height) {
        Resolution resolution = new Resolution(width,height);
        mLiveVideoBroadcaster.setResolution(resolution);
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
            mLiveVideoBroadcaster.changeCamera();
            promise.resolve(true);
    }

    public  void setSurfaceView(ABView surface) {

      this.surfaceView = surface;
      mLiveVideoBroadcaster.init(getCurrentActivity(), surfaceView);
      mLiveVideoBroadcaster.setAdaptiveStreaming(true);
      mLiveVideoBroadcaster.openCamera(Camera.CameraInfo.CAMERA_FACING_FRONT);
    }

    public  void destroySurfaceView() {

    }


    @Override
    public void onHostResume() {
        Log.i(TAG,"onHostResume");
    }

    @Override
    public void onHostPause() {
        Log.i(TAG,"onHostPause");
     if (mLiveVideoBroadcaster != null){
         mLiveVideoBroadcaster.pause();
     }
    }

    @Override
    public void onHostDestroy() {
        Log.i(TAG,"onHostDestroy");
    }
}
