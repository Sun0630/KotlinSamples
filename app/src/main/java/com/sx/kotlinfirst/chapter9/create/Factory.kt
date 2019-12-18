package com.sx.kotlinfirst.chapter9.create

/**
 * @author sunxin
 * @date 2019-12-17 11:09
 * @desc 创建型模式-- 工厂模式
 */

// 1. 伴生对象增强工厂模式
// 业务描述：有一个电脑加工厂，同时生产个人电脑和服务器主机。


// 定义一个电脑接口
interface Computer {
    val cpu: String
}

// 个人电脑
class PC(override val cpu: String = "Core") :
    Computer

// 服务器主机
class Server(override val cpu: String = "Xeon") :
    Computer

enum class ComputerType {
    PC, Server
}

// 电脑加工厂类 （模仿JAVA中标准的工厂设计模式）
class ComputerFactory1 {
    fun produce(type: ComputerType): Computer {
        return when (type) {
            ComputerType.PC -> PC()
            ComputerType.Server -> Server()
        }
    }
}

// 1.1 使用单例替代工厂类,kotlin 支持用object实现JAVA中的单例模式
object ComputerFactory2 {
    fun produce(type: ComputerType): Computer {
        return when (type) {
            ComputerType.PC -> PC()
            ComputerType.Server -> Server()
        }
    }
}

// 1.1.2 produce的调用显得多余，使用运算符重载invoke 方法代替 produce，进一步简化表达
object ComputerFactory3 {
    operator fun invoke(type: ComputerType): Computer {
        return when (type) {
            ComputerType.PC -> PC()
            ComputerType.Server -> Server()
        }
    }
}


// 1.2 伴生对象创建静态工厂方法
// 原则：考虑用静态工厂方法替代构造器

// 个人电脑
class PCC(override val cpu: String = "Core") :
    ComputerC

// 服务器主机
class ServerC(override val cpu: String = "Xeon") :
    ComputerC

interface ComputerC {
    val cpu: String

    companion object {
        operator fun invoke(type: ComputerType): ComputerC {
            return when (type) {
                ComputerType.PC -> PCC()
                ComputerType.Server -> ServerC()
            }
        }
    }
}


//1.3 扩展伴生对象方法
// 需求：为ComputerC 增加一个功能，通过CPU型号来判断电脑类型
fun ComputerC.Companion.fromCPU(cpu: String): ComputerType? = when (cpu) {
    "Core" -> ComputerType.PC
    "Xeon" -> ComputerType.Server
    else -> null
}


fun main() {
    // 标准
    val comp = ComputerFactory1()
        .produce(ComputerType.Server)
    // object 代替，使用单例
    var produce = ComputerFactory2.produce(
        ComputerType.Server
    )
    // 重载produce 方法
    var factory3 =
        ComputerFactory3(ComputerType.Server)
    println("标准${comp.cpu}")
    println("单例工厂${produce.cpu}")
    println("运算符重载${factory3.cpu}")

    // 1.2
    var computerC =
        ComputerC(ComputerType.Server)
    println("伴生对象增强${computerC.cpu}")


    //1.3 扩展伴生对象方法
    val type = ComputerC.fromCPU("Core")
    println("电脑类型是$type")
}