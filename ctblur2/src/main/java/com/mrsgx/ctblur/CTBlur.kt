package com.mrsgx.ctblur

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.support.annotation.RequiresApi
import android.support.v4.view.ViewCompat
import android.util.Log
import java.util.concurrent.atomic.AtomicBoolean
import java.util.concurrent.atomic.AtomicInteger

/**
 * Created by Marshall  on 2018/2/23.
 *
 */
class CTBlur constructor(data: CTBlurData) {
    companion object {
        private const val BLUR_ROUNDS_PER_UPDATE = 10
        var BLUR_INTERCEPTOR = 75L
    }

    private var mData = data
    private var dest: Bitmap? = null
    private val handler: Handler = Handler(Looper.getMainLooper())
    private val blurRunnable: Runnable = BlurRunnable()

    //为了在多线程模式下保证其原子性所以采用Atomic变量
    private val isWorking = AtomicBoolean(false)
    private val isBluring = AtomicBoolean(true)
    private val blursLeft = AtomicInteger(0)

    fun updateBlurView(): Boolean {
        return update()
    }

    fun updateBlurView(radius: Int) {
        if (isBluring.get())
            Thread {
                kotlin.run {
                    while (mData.blurRadius++ < radius) {
                        update()
                        Thread.sleep(BLUR_INTERCEPTOR)
                    }
                    isBluring.compareAndSet(true, false)

                }
            }.start()

    }
    fun restoreBlurView() {
        if (!isBluring.get())
            Thread {
                kotlin.run {
                    while (mData.blurRadius-- > 1) {
                        update()
                        Thread.sleep(BLUR_INTERCEPTOR)
                    }
                    isBluring.compareAndSet(false, true)
                }
            }.start()


    }

    private fun update(): Boolean {
        try {
            if (blursLeft.get() < BLUR_ROUNDS_PER_UPDATE)
                blursLeft.addAndGet(BLUR_ROUNDS_PER_UPDATE)
            //如果线程不在渲染状态则进行渲染
            if (!isWorking.get()) {
                handler.post(blurRunnable)
                return true
            }
        } catch (e: Exception) {
            Log.e("marshall", e.toString())
        }
        return false
    }

    //渲染线程
    inner class BlurRunnable : Runnable {
        @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
        override fun run() {
            isWorking.compareAndSet(false, true)
            //创建待渲染位图

            dest = CTBlurUtils.drawViewToBitmap(dest, mData.rootView, mData.downSampleSize, mData.config)
            //渲染并赋值给控件
            for (view in mData.viewsToBlurOnto) {
                val d = BitmapDrawable(mData.contextWrapper!!.resources, mData.blurAlgorithm!!.blur(mData.blurRadius,
                        CTBlurUtils.crop(dest!!.copy(dest!!.config, true), view, mData.downSampleSize)))
                ViewCompat.setBackground(view, d)
            }
            if (mData.blurRadius <= 1) {
                for (view in mData.viewsToBlurOnto) {
                    handler.post({ view.background = BitmapDrawable(Bitmap.createBitmap(view.width, view.height, mData.config)) })
                }
            }

            //判断线程状态
            val left = blursLeft.decrementAndGet()
            if (left > 0)
                handler.post(this)
            else
                isWorking.compareAndSet(true, false)
        }

    }
}


