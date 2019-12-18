package com.sx.kotlinfirst.chapter3

/**
 * @author sunxin
 * @date 2019-12-11 11:39
 * @desc kotlin 中定义内部类 使用 inner 关键字修饰，与java中的不通，如果不加 inner,则表示一个 嵌套类
 */


class OuterKotlin {
    val name = "this is outer class"


    // 这是一个嵌套类，不包含对其外部类的引用，所以无法调用其外部类的属性
    class ErrorInnerKotlin {
        fun printName(): Unit {
            //
//            println("this name is $name")
        }
    }


    // 内部类，包含对其外部类实例的引用
    inner class InnerKotlin {
        fun printName() {
            println("this name is $name")
        }
    }
}


///////////使用内部类解决多继承的问题---骡子的困惑////////////

open class Horse { // 马
    fun runfast(): Unit {
        println("I can run fast")
    }
}


open class Donkey { // 驴
    fun doLongTimeThing(): Unit {
        println("I can do some thing long time")
    }
}


class Mule { // 骡子

    // 在一个类内部定义多个内部类
    private inner class HorseM : Horse()
    private inner class DonkeyM : Donkey()


    fun runfast() {
        HorseM().runfast()
    }


    fun doLongTimeThing(): Unit {
        DonkeyM().doLongTimeThing()
    }

}














