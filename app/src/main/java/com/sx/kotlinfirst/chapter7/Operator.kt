package com.sx.kotlinfirst.chapter7

/**
 * @author sunxin
 * @date 2019-12-16 13:44
 * @desc
 */


data class Area(val value: Double)

// 运算符重载  operator：将一个函数标记为重载一个操作符或者实现一个约定
operator fun Area.plus(that: Area): Area {
    return Area(this.value + that.value)
}


fun main() {
    println(Area(1.0) + Area(2.3))

    val list = mutableListOf(1, 2, 3)
    list.exchange(0,1)
    println(list)

}


