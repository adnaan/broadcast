package com.bigshow.broadcast;

import android.content.Context;
import android.util.AttributeSet;
import android.view.SurfaceView;
import android.opengl.GLSurfaceView;

public class ABView extends GLSurfaceView {
    public ABView(Context context) {
        super(context);
        init();
    }

    public ABView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init(){
        setPreserveEGLContextOnPause(true);
        setEGLConfigChooser(false);
        setEGLContextClientVersion(2);
    }
}
