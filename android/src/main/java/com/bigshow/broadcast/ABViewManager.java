package com.bigshow.broadcast;


import android.opengl.GLSurfaceView;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;

import javax.microedition.khronos.opengles.GL10;
import javax.microedition.khronos.egl.EGLConfig;

public class ABViewManager extends SimpleViewManager<ABView> {
    private ABView surfaceView;
    private SurfaceHolder surfaceHolder;
    private ABModule abModule;

    public ABViewManager(ABModule abModule){
        super();
        this.abModule = abModule;

    }
    @Override
    public String getName() {
        return "ABView";
    }

    @Override
    protected ABView createViewInstance(ThemedReactContext reactContext) {
        surfaceView = new ABView(reactContext);
        this.abModule.setSurfaceView(surfaceView);
        return surfaceView;
    }


}
