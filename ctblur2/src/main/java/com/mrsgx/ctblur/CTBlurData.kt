package com.mrsgx.ctblur

import android.graphics.Bitmap
import android.view.View

/**
 *
 * 此类是渲染图像所需的一部分参数。<BR/>
 * 1.缩放比例大小<BR/>
 * 2.未渲染原始View控件<BR/>
 * 3.要附加的控件列表<BR/>
 * 4.Bitmap位图配置<BR/>
 * 5.渲染半径<BR/>
 * 6.渲染算法<BR/>
 * 7.加载脚本<BR/>
 * Created by Marshall  on 2018/2/23.
 * <p>
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
