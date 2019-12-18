package com.sx.kotlinfirst.chapter9.behavior

import java.util.*

/**
 * @author sunxin
 * @date 2019-12-18 11:12
 * @desc 观察者模式
 */

// 需求：通过java标准累哭提供的类，实现观察者模式。动态更新股价

//股价更新被观察者
class StockUpdate : Observable() {
    // 定义观察者集合
    val observer = mutableListOf<Observer>()

    fun setStockChanged(price: Int) {
        this.observer.forEach {
            it.update(this, price)
        }
    }
}

// 观察者
class StockDisplay : Observer {

    override fun update(o: Observable?, price: Any?) {
        if (o is StockUpdate) {
            println("The latest stock price is $price")
        }
    }

}


fun main() {
    val stockUpdate = StockUpdate()
    val stockDisplay = StockDisplay()
    stockUpdate.observer.add(stockDisplay)
    stockUpdate.setStockChanged(100)
}