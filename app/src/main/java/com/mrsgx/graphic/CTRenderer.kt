package com.mrsgx.graphic

import android.opengl.GLES30
import android.opengl.GLSurfaceView
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

/**
 * Created by Marshall  on 2018/2/23.
 *
 */
class CTRenderer : GLSurfaceView.Renderer {
    override fun onDrawFrame(gl: GL10?) {

    GLES30.glClear(GLES30.GL_COLOR_BUFFER_BIT)
    }

    override fun onSurfaceChanged(gl: GL10?, width: Int, height: Int) {
        GLES30.glViewport(0,0,width,height)
    }

    override fun onSurfaceCreated(gl: GL10?, config: EGLConfig?) {
        GLES30.glClearColor(10f,0.5f,0.5f,1.0f)
    }
}