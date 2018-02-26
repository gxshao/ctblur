package com.mrsgx.graphic.ctblur

import android.content.Context
import android.content.res.Resources
import android.os.Build
import android.renderscript.RenderScript

/**
 * Created by Marshall  on 2018/2/23.
 *
 */
class ContextWrapper {
    var context: Context? = null
        private set
    private var renderScript: RenderScript? = null
    private var renderScriptContextType = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
        RenderScript.ContextType.NORMAL
    } else {
        TODO("VERSION.SDK_INT < JELLY_BEAN_MR2")
    }

    val resources: Resources
        get() = context!!.resources

    constructor(context: Context) {
        this.context = context
    }

    constructor(context: Context, renderScriptContextType: RenderScript.ContextType) {
        this.context = context
        this.renderScriptContextType = renderScriptContextType
    }

            /**
     * Syncronously creates a Renderscript context if none exists.
     * Creating a Renderscript context takes about 20 ms in Nexus 5
     *
     * @return
     */
    fun getRenderScript(): RenderScript {
        if (renderScript == null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
                renderScript = RenderScript.create(context, renderScriptContextType)
            }
        }
        return renderScript!!
    }
}
