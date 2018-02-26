package com.mrsgx.graphic.ctblur

/**
 * Created by Marshall  on 2018/2/23.
 *
 */
import android.graphics.Bitmap

interface IBlur {

    /**
     * @param radius   模糊半径,取值范围再1-25
     * @param original
     * @return 模糊原bitmap后的结果
     */
    fun blur(radius: Int, original: Bitmap): Bitmap

}