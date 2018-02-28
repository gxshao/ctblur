package com.mrsgx.graphic

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var frg:Glass
    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    @SuppressLint("CommitTransaction")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Example of a call to a native method
         frg=Glass(basepic)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().add(R.id.base,frg).commit()
        }
        button.setOnClickListener{
            frg.onRestore()
        }
        blurs.setOnClickListener {
            frg.onBlur()
        }
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */

    companion object {

        // Used to load the 'native-lib' library on application startup.
        init {
            System.loadLibrary("ctblur")
        }
    }
}
