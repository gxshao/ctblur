package com.mrsgx.graphic
import android.content.Context
import android.opengl.GLSurfaceView
import android.util.AttributeSet

class CTSurfaceView(context: Context?, Attr:AttributeSet) : GLSurfaceView(context,Attr) {
    init {
        setEGLContextClientVersion(3)
        setRenderer(CTRenderer())
        renderMode = GLSurfaceView.RENDERMODE_WHEN_DIRTY
    }

}