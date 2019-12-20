package com.sx.kotlinfirst

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.sx.kotlinfirst.chapter6.ContentBean
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView.invisible()

        textView.setOnClickListener {

        }

        Snackbar.make(findViewById(android.R.id.content), "Hello Snackbar", Snackbar.LENGTH_SHORT)
            .show()
        //
        val isConnect = isMobileConnected()


        // 使用携程下载网络图片
        // 在UI线程开启一个协程
        CoroutineScope(Dispatchers.Main).launch {
            // 打印线程名称
            println("UI线程名称:${Thread.currentThread().name}") //main
            // 在IO线程开启一个协程进行下载图片
            val bitmap = downloadImage()
            // 在UI线程更新图片到界面
            imageView.setImageBitmap(bitmap)
        }
    }

    private suspend fun downloadImage(): Bitmap? = withContext(Dispatchers.IO) {
        val conn = URL("https://d47jbcq60tnr6.cloudfront.net/2019116/24878-1dcrrx9.tp0z.png").openConnection() as HttpURLConnection
        println("IO线程名称:${Thread.currentThread().name}") // DefaultDispatcher-worker-1
        conn.run {
            this.requestMethod="GET"
            this.connect()
            if (this.responseCode == HttpURLConnection.HTTP_OK){
                return@withContext BitmapFactory.decodeStream(conn.inputStream)
            }else{
                null
            }
        }

        null
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
    private fun Context.isMobileConnected(): Boolean {
        val connectivityManager: ConnectivityManager =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
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
