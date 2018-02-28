package com.mrsgx.ctblur

/**
 * 高斯模糊算法
 * Created by Marshall  on 2018/2/23.
 *
 */

import android.graphics.Bitmap

/**
 * http://stackoverflow.com/a/13436737/774398
 * by gordi
 */
class GaussianFastBlur : IBlur {
    override fun blur(radius: Int, original: Bitmap): Bitmap {

        //参数 1：pix[] 2:r 3:width 4,height
        //返回: pix[]
        val w = original.width
        val h = original.height
        val pix = IntArray(w * h)
        original.getPixels(pix, 0, w, 0, 0, w, h)
        original.setPixels(ctGaussianFastBlur(radius,pix,w,h), 0, w, 0, 0, w, h)
        return original
    }
    private external fun ctGaussianFastBlur(radius: Int,pix:IntArray,w:Int,h:Int): IntArray
}