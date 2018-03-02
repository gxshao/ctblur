package com.mrsgx.graphic

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var frg: Glass
    var changeBack=false
    @TargetApi(Build.VERSION_CODES.M)
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
        change.setOnClickListener {
            if(changeBack)
            basepic.setImageDrawable(getDrawable(R.mipmap.aa))
            else
                basepic.setImageDrawable(getDrawable(R.mipmap.photo3_med))
            changeBack=!changeBack
            frg.onBackgroundChanged()
        }
//        requestPermissions(arrayOf("android.permission.WRITE_EXTERNAL_STORAGE"), 0)
//        image.setOnClickListener {
//            val intent = Intent( Intent.ACTION_GET_CONTENT)
//            intent.setType("image/*");
//            startActivityForResult(intent, 0)
//        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 0) {
           // image.setImageBitmap(BitmapFactory.decodeFile(data!!.data.path))
            //tv_curSize.text = data.data.path
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
