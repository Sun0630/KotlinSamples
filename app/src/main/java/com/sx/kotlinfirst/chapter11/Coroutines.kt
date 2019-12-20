package com.sx.kotlinfirst.chapter11

import android.graphics.DiscretePathEffect
import kotlinx.coroutines.*
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.system.measureTimeMillis

/**
 * @author sunxin
 * @date 2019-12-19 19:54
 * @desc 协程：轻量级的线程   Kotlin 的协程目前还处在实验阶段
 */

fun main1() {
    // 创建协程的3中方法
    // 方法一：使用runBlocking顶层函数
    // 通常用于单元测试场景，不会用到业务开发当中，因为他是线程阻塞的
    runBlocking {
        // ....
    }

    // 方法二：在后台启动一个协程
    // 不会阻塞线程，但是Android开发中不推荐，因为他的生命周期和App一致，且不能取消
    GlobalScope.launch {
        // 延迟1秒，挂起协程但是不会阻塞线程，所以在延迟1s之后先输出了 Hello ，后输出了 World
        // delay 只能用于协程内部，用于挂起协程，不会阻塞线程
        delay(1000L)
        // 延迟之后输出
        println("World")
    }

    // 方法三：自行通过 CoroutineContext 创建一个 CoroutineScope对象
    // 推荐使用这种方法，
    val context = EmptyCoroutineContext
    val coroutineScope = CoroutineScope(context)
    // 切换到IO线程
    coroutineScope.launch(Dispatchers.IO) {
        println("当前线程name：${Thread().name}")
    }

    // 实现：在子线程请求数据，切换到主线程更新UI
    // 一般写法，不优雅，还是在嵌套
    coroutineScope.launch(Dispatchers.IO){
        // ... 子线程获取数据
        launch(Dispatchers.Main){
            // 切换到主线程更新UI
        }
    }

    // 二般写法，使用 withContext函数。这个函数可以切换到指定的线程，并在闭包内的逻辑执行结束之后，自动把线程切换回去继续执行
    coroutineScope.launch(Dispatchers.Main){
        // 从UI线程开始
        val image = withContext(Dispatchers.IO){
            // 运行在IO线程获取图片，并在结束之后切回UI线程
        }
        // IO线程执行结束之后回到UI线程继续执行更新UI
        // 这个方式的优势体现在频繁的进行线程切换，"自动切回来" 消除了并发代码在协作时的嵌套
        // 把withContext放进一个单独的函数里面
        getImage(1)
    }






    // 协程被延迟了1s，但是主线程继续执行
    println("Hello")
    // 为了使JVM报货，阻塞主线程2s
    // 如果没有，那么程序智慧输出 Hello，因为主线程没有阻塞，程序回立即执行，不会等协程执行完之后执行
    Thread.sleep(2000L)


}

// 把withContext放进一个单独的函数里面
suspend fun getImage(imageId: Int) = withContext(Dispatchers.IO){

}

/**
 * 1. launch 与 runBlocking
 *
 * runBlocking：最高级的协程，也就是主协程。仍阻塞当前执行的线程
 * launch 创建的协程能运行在 runBlocking 中，反过来不行
 *
 */
fun main2() = runBlocking {
    val job = launch {
        delay(3000L)
        println("World")
    }

    // 会等到协程结束，非阻塞式
    job.join()
    println("Hello")
    // 在这里延时的意思是不让程序过早的结束，或者说让程序在这2s内保活
    delay(2000L)
}


//suspend:挂起。 用其修饰的方法在协程内部使用的时候和普通方法没有什么区别，
// 不同的是在suspend修饰的方法内部还可以条用其他suspend方法
suspend fun search() {
    // delay也是用suspend修饰，这些方法只能在协程内部或者其他suspend方法中执行，不能在普通方法中执行
    delay(1000)

}


// 用同步方式写异步代码：用协程来优雅的处理异步逻辑

// 先来看看代码在协程中的执行顺序
suspend fun searchItemOne(): String {
    delay(1000)
    return "item-one"
}

suspend fun searchItemTwo(): String {
    delay(1000)
    return "item-two"
}

fun main() = runBlocking {
    val one = searchItemOne()
    val two = searchItemTwo()

    //the items is item-one and item-two
    // 在协程内部，其实这两个方法使顺序执行的。
    // 顺序执行其实不是很合理，因为这两个操作不会相互依赖
    // 他们的关系应该使并行的
    println("the items is $one and $two")


    // 改进：让上面两个程序并行执行  使用 async and  await

    // async: 创建了一个协程，会与其他子协程并行执行
    // 与Launch不通的事，async 返回了一个 Deferred(延迟的) 对象
    // Deferred: 非阻塞可取消的future，一个带有结果的job
    // future: 将来会返回一个结果，利用await可以等待这个值查询到之后，然后将他获取
    val twoItem = async {
        searchItemTwo()
    }


    val oneItem = async {
        searchItemOne()
    }


    println("the items is ${oneItem.await()} and ${twoItem.await()}")


    // 测试有无 async 运行的时间

    val time = measureTimeMillis {
        val one = searchItemOne()
        val two = searchItemTwo()
        println("The items is $one and $two")
    }

    println("Cost time is $time ms") //Cost time is 2006 ms


    val asyncTime = measureTimeMillis {
        val twoItem = async {
            searchItemTwo()
        }


        val oneItem = async {
            searchItemOne()
        }


        println("the items is ${oneItem.await()} and ${twoItem.await()}")
    }

    println("Cost async time is $asyncTime ms")//Cost async time is 1007 ms

    // 时间几乎缩短了一半
}
