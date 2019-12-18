package com.sx.kotlinfirst.chapter9.create

/**
 * @author sunxin
 * @date 2019-12-17 11:41
 * @desc 创建型模式 - 抽象工厂
 */

// 内联函数有一个很大的作用：具体化参数类型。利用这一特性，改进更为复杂的工厂模式，抽象工厂
// 抽象工厂模式：为创建一组相关或相互依赖的对象提供一个接口，而且无须指定他们的具体类

interface ComputerA

// 戴尔
class Dell : ComputerA

// 华硕
class Asus : ComputerA

// 宏基
class Acer : ComputerA


// 抽象工厂
abstract class AbstractFactory {

    abstract fun produce(): ComputerA

    companion object {
        inline operator fun <reified T : ComputerA> invoke(): AbstractFactory {
            return when (T::class) {
                Dell::class -> DellFactory()
                Asus::class -> AsusFactory()
                Acer::class -> AcerFactory()
                else -> throw IllegalArgumentException()
            }
        }
    }
}


class DellFactory : AbstractFactory() {
    override fun produce(): ComputerA =
        Dell()
}

class AsusFactory : AbstractFactory() {
    override fun produce(): ComputerA =
        Asus()
}

class AcerFactory : AbstractFactory() {
    override fun produce(): ComputerA =
        Acer()
}

fun main() {
//    val dellFactory = AbstractFactory(DellFactory())
////    val dellFactory = AbstractFactory<Dell>()

    val dellFactory =
        AbstractFactory<Dell>()
    val dell = dellFactory.produce()
    println(dell)
}