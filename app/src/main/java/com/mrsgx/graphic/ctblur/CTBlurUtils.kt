package com.mrsgx.graphic.ctblur

import android.graphics.Bitmap
import android.graphics.Canvas
import android.support.v4.view.ViewCompat
import android.view.View

/**
 * 模糊实现工具
 * Created by Marshall  on 2018/2/23.
 *
 */
class CTBlurUtils {
    companion object {
        //根据缩放比例裁剪图片
        fun crop(srcBmp: Bitmap, canvasView: View, downsampling: Int): Bitmap {
            val scale = 1f / downsampling
            return Bitmap.createBitmap(
                    srcBmp,
                    Math.floor((ViewCompat.getX(canvasView) * scale).toDouble()).toInt(),
                    Math.floor((ViewCompat.getY(canvasView) * scale).toDouble()).toInt(),
                    Math.floor((canvasView.width * scale).toDouble()).toInt(),
                    Math.floor((canvasView.height * scale).toDouble()).toInt()
            )
        }
        const val ALGORITHM_GAUSSAINFASTBLUR=0
        const val ALGORITHM_BOXBLUR=1
        //获取渲染算法
         fun getIBlurAlgorithm(algorithm: Int, contextWrapper: ContextWrapper): IBlur {
            //只实现了两种 高斯模糊和 方框滤波
            return when (algorithm ) {
                ALGORITHM_GAUSSAINFASTBLUR -> GaussianFastBlur()
                ALGORITHM_BOXBLUR -> BoxBlur()
                else -> IgnoreBlur()
            }
        }


        //绘制图形
        fun drawViewToBitmap(src: Bitmap?, view: View, downSampling: Int, bitmapConfig: Bitmap.Config): Bitmap {
            var dest=src
            val scale = 1f / downSampling
            val viewWidth = view.width
            val viewHeight = view.height
            val bmpWidth = Math.round(viewWidth * scale)
            val bmpHeight = Math.round(viewHeight * scale)

            if (dest == null || dest.width != bmpWidth || dest.height != bmpHeight) {
                dest = Bitmap.createBitmap(bmpWidth, bmpHeight, bitmapConfig)
            }

            val c = Canvas(dest!!)
            if (downSampling > 1) {
                c.scale(scale, scale)
            }

            view.draw(c)
            return dest
        }

    }

    //不渲染返回给个默认值
    class IgnoreBlur : IBlur {
        override fun blur(radius: Int, original: Bitmap): Bitmap {
            return original
        }
    }
}
