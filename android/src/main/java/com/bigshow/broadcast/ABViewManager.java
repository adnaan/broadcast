package com.bigshow.broadcast;

import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;

public class ABViewManager extends SimpleViewManager<View> implements SurfaceHolder.Callback {
    private ABView surfaceView;
    private SurfaceHolder surfaceHolder;

    @Override
    public String getName() {
        return "ABView";
    }

    @Override
    protected View createViewInstance(ThemedReactContext reactContext) {
        surfaceView = new ABView(reactContext);
        surfaceHolder = surfaceView.getHolder();
        surfaceHolder.addCallback(this);
        return surfaceView;
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        ABModule.setSurfaceView(surfaceView);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        ABModule.destroySurfaceView();
    }
}
