package com.sx.kotlinfirst.chapter11

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * @author sunxin
 * @date 2019-12-20 10:44
 * @desc 练习题
 */


fun main() {
    //1. 开启一个协程，打印当前线程名称
    CoroutineScope(Dispatchers.IO).launch {
        println("当前线程:${Thread.currentThread().name}")
    }

    //2. 下载一个网络图片
}