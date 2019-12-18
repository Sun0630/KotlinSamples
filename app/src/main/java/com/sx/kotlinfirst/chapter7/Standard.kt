package com.sx.kotlinfirst.chapter7

/**
 * @author sunxin
 * @date 2019-12-16 15:00
 * @desc 标准库中的扩展函数 run let  also  takeIf
 */


/**
 * 1. run
 */
fun testFoo() {
    val nickname = "Tom"

    // 拥有一个单独的作用域
    // 返回范围内最后一个对象
    run {
        val nickname = "Jerry"
        println(nickname)
    }
    println(nickname)
}

/**
 * 2. let 返回的是闭包的最后一行，同样限制了变量的作用域
 *    apply 返回的是原来的对象
 */
data class Student1(var age: Int)

class Kot {
    private val student: Student1 = Student1(10)
    fun dealStu() {
        val result = student.let {
            println(it.age)
            it.age
        }

        /**
         * 3. also 是let和apply的加强版
         */
        val alsoResult = student.also {
            this.student.age += it.age
            println(this.student.age)
            println(it.age)
        }


        /**
         * 4. takeIf: 带上一个条件判断，满足条件之后才会执行
         *    takeUnless  不满足特定条件才会执行
         */
        val takeIfResult = student.takeIf {
            it.age > 1
        }.let {

        }


    }
}


fun main() {

    testFoo()
}