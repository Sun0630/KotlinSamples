package com.sx.kotlinfirst.chapter7

/**
 * @author sunxin
 * @date 2019-12-16 14:40
 * @desc
 */

object Test{
    @JvmStatic
    fun main(args: Array<String>) {
        // 在没有Son对象的情况下，调用他的扩展函数
        Son.foo()
    }
}