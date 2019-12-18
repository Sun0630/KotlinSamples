package com.sx.kotlinfirst.chapter2

/**
 * @author sunxin
 * @date 2019-12-10 22:05
 * @desc 自定义中缀表达式
 */
class Person {

    /**
     * 自定义中缀表达式called，也能按照正常方法调用
     */
    infix fun called(name: String) {
        println("My name is $name")
    }

}