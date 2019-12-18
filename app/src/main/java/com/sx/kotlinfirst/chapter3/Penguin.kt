package com.sx.kotlinfirst.chapter3

/**
 * @author sunxin
 * @date 2019-12-11 09:35
 * @desc 继承
 */
class Penguin : Bird1() {

    override fun fly() {
        super.fly()
        println("I can not fly")
    }

}

