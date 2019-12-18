package com.sx.kotlinfirst.chapter9.behavior

import kotlin.properties.Delegates

/**
 * @author sunxin
 * @date 2019-12-18 11:25
 * @desc kotlin 的标准库额外引入了可被观察的委托属性，使用委托来改造观察者模式
 */

interface StockUpdateListener {
    // 涨了
    fun onRise(price: Int)

    // 跌了
    fun onFall(price: Int)
}


class StockDisplayDelegate : StockUpdateListener {
    override fun onRise(price: Int) {
        println("The latest stock price has risen to $price")
    }

    override fun onFall(price: Int) {
        println("The latest stock price has fell to $price")
    }

}

class StockUpdateDelegate {
    var listener = mutableSetOf<StockUpdateListener>()
    // by 委托属性
    // @ https://www.kotlincn.net/docs/reference/delegated-properties.html
    var price: Int by Delegates.observable(0) { _, oldValue, newValue ->
        run {
            listener.forEach {
                if (newValue > oldValue) it.onRise(price) else it.onFall(price)
            }
        }
    }

    // Delegates.vetoable  在被赋新值生效之前提前进行捕获，然后判断是否接受他
    // 只接受正整数赋值
    var value: Int by Delegates.vetoable(0) { _, oldValue, newValue ->
        newValue > 0
    }


}

fun main() {
    val su = StockUpdateDelegate()
    val sd = StockDisplayDelegate()
    su.listener.add(sd)
    su.price = 100
    su.price = 78
    su.price = 89

    su.value = -1
    println(su.value) // 0
}