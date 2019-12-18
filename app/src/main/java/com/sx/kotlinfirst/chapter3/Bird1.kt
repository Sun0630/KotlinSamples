package com.sx.kotlinfirst.chapter3

/**
 * @author sunxin
 * @date 2019-12-10 22:44
 * @desc 鸟类 普通
 */
// Kotlin 中类和方法默认是不可以被重写的，必须加上 open 修饰符
open class Bird1 {
    // 重量
    // val: 不可变属性成员  属性默认值 默认全局可见
    val weight: Double = 500.0
    // 颜色
    val color: String = "blue"
    // 年龄
    val age: Int = 1


    /**
     *子类应该尽量避免重写父类中的非抽象的方法，因为父类一旦变更方法，子类的调用很可能就出错了
     *  里氏替换原则：子类可以扩展父类的功能，但是不能改变父类已有的功能
     */
    // 全局可见
    open fun fly() {
        println("I can fly")
    }



}