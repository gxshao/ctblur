package com.mrsgx.graphic

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mrsgx.graphic.ctblur.CTBlur
import com.mrsgx.graphic.ctblur.CTBlurData
import com.mrsgx.graphic.ctblur.CTBlurUtils
import com.mrsgx.graphic.ctblur.ContextWrapper
import kotlinx.android.synthetic.main.glassfragment.view.*

@SuppressLint("ValidFragment")
/**
 * Created by Marshall  on 2018/2/23.
 *
 */
class Glass(private var rootview:View) : Fragment(),View.OnClickListener {
    var i=3
    override fun onClick(v: View?) {

        i+=2
        mBluror.updateBlurView(i)
    }

    private lateinit var mBluror:CTBlur

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view=inflater.inflate(R.layout.glassfragment,null)
        view.setOnClickListener(this)
        val mBlurData= CTBlurData()
        mBlurData.rootView=rootview  //要渲染的底部控件父控件
        val arr=ArrayList<View>()
        arr.add(view.glass)
        mBlurData.viewsToBlurOnto=arr
        mBlurData.contextWrapper= ContextWrapper(activity)
        mBlurData.blurAlgorithm=CTBlurUtils.getIBlurAlgorithm(1,mBlurData.contextWrapper!!)
        mBluror= CTBlur(mBlurData)
        return view
    }
}