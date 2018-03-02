package com.mrsgx.ctblur

/**
 * Created by Marshall  on 2018/2/23.
 *
 */
import android.graphics.Bitmap
import android.graphics.Canvas

class BoxBlur : IBlur {
    override fun blur(radius: Int, original: Bitmap): Bitmap {

        val blurred = Bitmap.createBitmap(original.width, original.height,
                Bitmap.Config.ARGB_8888)
        if (radius and 1 == 0) return blurred
        val c = Canvas(blurred)
        val w = original.width
        val h = original.height
        val pixels = IntArray(original.width * original.height)
        original.getPixels(pixels, 0, w, 0, 0, w, h)
        c.drawBitmap(boxBlur(pixels,w,h,radius/2), 0, w, 0.0f, 0.0f, w, h, true, null)
        return blurred
    }
    private external fun boxBlur(pixels: IntArray,w:Int,h: Int,radius: Int):IntArray
}