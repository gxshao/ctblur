package com.mrsgx.ctblur

import android.graphics.Bitmap
import android.view.View

/**
 * 此类是渲染图像所需的一部分参数
 * 1.缩放比例大小
 * 2.未渲染原始View控件
 * 3.要附加的控件列表
 * 4.Bitmap位图配置
 * 5.渲染半径
 * 6.渲染算法
 * 7.加载脚本
 * Created by Marshall  on 2018/2/23.
 *
 */
class CTBlurData {
    var downSampleSize = 5
    lateinit var rootView: View
    lateinit var viewsToBlurOnto: List<View>
    val config = Bitmap.Config.ARGB_8888
    var blurRadius = 0
    var blurAlgorithm: IBlur? = null
    var contextWrapper: ContextWrapper? = null
}
