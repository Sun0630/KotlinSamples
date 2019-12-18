package com.sx.kotlinfirst.chapter3

/**
 * @author sunxin
 * @date 2019-12-11 11:25
 * @desc Kotlin 中的接口多继承
 */

interface FlyerAnimals {
    fun fly()
    fun kind() = "flying animals xxxx"
}


interface Animal {
    val name: String
    fun eat()
    fun kind() = "flying animals"

}


class AngryBird(override val name: String) : FlyerAnimals, Animal {

    override fun eat() {
        println("I can eat")
    }

    override fun fly() {
        println("I can fly")
    }

    // 通过super 关键字来指定继承哪个父接口的方法，或者由自己去实现该方法
    override fun kind() = super<Animal>.kind()

}

fun main() {
    val angryBird = AngryBird("HBird")
    println(angryBird.kind())
}