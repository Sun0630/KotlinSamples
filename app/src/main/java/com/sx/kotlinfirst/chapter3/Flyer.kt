package com.sx.kotlinfirst.chapter3

/**
 * @author sunxin
 * @date 2019-12-10 23:16
 * @desc Kotlin 中的接口
 */
interface Flyer {

    val speed: Int

    // 赋值
    // val height = 1000  不能这么赋值
    val height
        get() = 1000

    fun kind()

    fun fly() {
        println("I can Fly")
    }
}