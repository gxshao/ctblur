package com.mrsgx.graphic

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mrsgx.ctblur.CTBlur
import com.mrsgx.ctblur.CTBlurData
import com.mrsgx.ctblur.CTBlurUtils
import com.mrsgx.ctblur.ContextWrapper
import kotlinx.android.synthetic.main.glassfragment.view.*

@SuppressLint("ValidFragment")
/**
 * Created by Marshall  on 2018/2/23.
 *
 */
class Glass(private var rootview: View) : Fragment() {
    private lateinit var views: View
    fun onBlur() {
        mBluror.updateBlurView(5)
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    fun onRestore() {
        mBluror.restoreBlurView()

    }
    fun onBackgroundChanged(){
        mBluror.updateBlurView()
    }

    private lateinit var mBluror: com.mrsgx.ctblur.CTBlur

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        views = inflater.inflate(R.layout.glassfragment, null)
        val mBlurData = CTBlurData()
        mBlurData.rootView = rootview  //要渲染的底部控件父控件
        val arr = ArrayList<View>()
        arr.add(views.glass)
        mBlurData.viewsToBlurOnto = arr
        mBlurData.contextWrapper = ContextWrapper(activity)
        mBlurData.blurAlgorithm = CTBlurUtils.getIBlurAlgorithm(0, mBlurData.contextWrapper!!)
        mBluror = CTBlur(mBlurData)
        return views
    }
}

