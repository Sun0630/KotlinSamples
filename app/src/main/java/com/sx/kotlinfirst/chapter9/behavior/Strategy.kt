package com.sx.kotlinfirst.chapter9.behavior

import kotlin.reflect.jvm.javaMethod

/**
 * @author sunxin
 * @date 2019-12-18 13:22
 * @desc 高阶函数简化策略模式
 */

// 遵循开闭原则：策略模式，定义算法族，做的事情就是将不通的策略进行独立封装，与类在逻辑上解耦


// 案例：有一个表示游泳运动员的抽象类Swimmer，有一个游泳的方法swim

// 游泳运动员
class Swimmer {
    fun swim() {
        println("I am swimming...")
    }

    // shaw很快掌握了蛙泳，仰泳，自由泳等多种的泳姿，对swim方法进行改造
    // 这种设计不好，并不是所有的运动员都掌握这三种泳姿，违背了开闭原则
    // 改进：使用策略模式，将游泳这个行为封装成接口，根据不通的场景调用不同的游泳方法
    fun breaststroke() {
        println("I am breaststroke...")
    }

    fun backstroke() {
        println("I am backstroke...")
    }

    fun freestyle() {
        println("I am freestyle")
    }


}


// 使用策略模式改进
interface SwimmStrategy {
    fun swim()
}


// 蛙泳
class Breaststroke : SwimmStrategy {
    override fun swim() {
        println("I am breaststroke...")
    }
}

class Backstroke : SwimmStrategy {
    override fun swim() {
        println("I am backstroke...")
    }
}

class Freestyle : SwimmStrategy {
    override fun swim() {
        println("I am freestyle")
    }
}


// 游泳运动员，传入一个游泳策略
class SwimmerA(private val strategy: SwimmStrategy) {
    fun swim() {
        strategy.swim()
    }
}

// 策略模式完成，但是代码增加了很多

// 使用kotlin 的高阶函数抽象算法
// 将策略封装成一个函数，然后作为参数传递给

fun breaststroke() = println("I am breaststroke...")

fun backstroke() = println("I am backstroke...")

fun freestyle() = println("I am freestyle")

class SwimmerB(private val swimming:()->Unit){

    fun swim(){
        swimming()
    }
}



fun main() {
//    val shaw = Swimmer()
//    shaw.swim()
    val kFunction1 = Swimmer::swim
    // 策略设计模式
    // 比如，shaw周末想自由泳，工作日想仰泳
    val weekendShaw = SwimmerA(Freestyle())
    weekendShaw.swim()
    val weekdayShaw = SwimmerA(Breaststroke())
    weekdayShaw.swim()

    // 高阶函数再次改造
    val weekShaw = SwimmerB(::breaststroke)
    weekShaw.swim()
    val dayShaw = SwimmerB(::freestyle)
    dayShaw.swim()
}