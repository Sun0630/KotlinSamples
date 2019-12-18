package com.sx.kotlinfirst

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.sx.kotlinfirst.chapter6.ContentBean
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView.invisible()

        textView.setOnClickListener {

        }

        Snackbar.make(findViewById(android.R.id.content),"Hello Snackbar",Snackbar.LENGTH_SHORT).show()
        //
        val isConnect = isMobileConnected()
    }


    fun bindData(content: ContentBean): Unit {
        with(content) {
            // this 可以省略
            textView.text = title
            textView.textSize = 18.0f
        }
    }

    fun bindData2(content: ContentBean): Unit {
        content.apply {
            textView.text = title
        }
    }

    /**
     * 作为Context的扩展函数，不用每次都传入Context了，摆脱了类的束缚
     */
    private fun Context.isMobileConnected():Boolean{
        val connectivityManager:ConnectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val mNetworkInfo = connectivityManager.activeNetworkInfo
        if (mNetworkInfo != null) {
            return mNetworkInfo.isAvailable
        }
        return false
    }


    // 扩展函数
    private fun View.invisible() {
        visibility = View.INVISIBLE
    }
}
