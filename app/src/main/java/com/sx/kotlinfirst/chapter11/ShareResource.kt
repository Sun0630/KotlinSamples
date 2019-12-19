package com.sx.kotlinfirst.chapter11

import java.util.concurrent.locks.Lock
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.withLock

/**
 * @author sunxin
 * @date 2019-12-19 21:00
 * @desc 共享资源控制
 */


// 1. 锁模式，对共享资源加锁
class Shop {

    val goods = hashMapOf<Long, Int>()

    init {
        goods[1] = 10
        goods[2] = 15
    }


    // @Synchronized: 声明一个同步方法
    @Synchronized
    fun buyGoods(id: Long) {
        val stock = goods.getValue(id)
        goods[id] = stock - 1
    }


    // synchronized: 声明一个同步代码块
    fun buyGoods2(id: Long) {
        synchronized(this) {
            val stock = goods.getValue(id)
            goods[id] = stock - 1
        }
    }


    fun buyGoods3(id: Long) {
        val stock = goods.getValue(id)
        goods[id] = stock - 1
    }


}


fun main() {

    val shop = Shop()

    // 使用Lock
    var lock: Lock = ReentrantLock()
    lock.withLock {
        shop.buyGoods3(1)
    }
}