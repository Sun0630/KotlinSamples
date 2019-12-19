package com.sx.kotlinfirst.chapter9.strcture

/**
 * @author sunxin
 * @date 2019-12-18 21:56
 * @desc 装饰者模式
 */

// 定义：在不必改变原类文件和使用继承的情况下，动态的扩展一个对象的功能。该模式通过创建一个包装对象，来包裹真实的对象

/** 组合优于继承
 * 主要做：
 * 1. 创建一个装饰类，包含一个需要被装饰的实例
 * 2. 装饰类重写所有被装饰类的方法
 * 3. 在装饰类中对需要增强的功能进行扩展
 */

// 1. 用类委托减少样本代码

interface MacBook {
    fun getCost(): Int
    fun getDesc(): String
    fun getProdDate(): String
}


class MacBookPro : MacBook {
    override fun getCost(): Int = 10000

    override fun getDesc(): String = "MacBook Pro"

    override fun getProdDate(): String = "Late 2019"
}


// 装饰类,类委托，把MacBook接口所有的方法都委托给了构造参数对象 macbook。只需要重写需要变更的方法就行
class ProcessorUpgradeMacbookPro(private val macBook: MacBook) : MacBook by macBook {

    override fun getCost(): Int = macBook.getCost() + 232

    override fun getDesc(): String = macBook.getDesc() + " 1G memory"
}


//2. 通过扩展代替装饰者
class Printer{
    fun drawLine(){
        println("-----------------")
    }

    fun drawDottedLine(){
        println("- - - - - - - - -")
    }

    fun drawStar(){
        println("* * * * * * * *")
    }
}


// 需求：每次绘图开始和结束之后有一段文字说明，来标记绘制的过程
// 使用扩展函数
fun Printer.startDraw(decorated:()->Unit){
    println("+++ start drawing +++")
    decorated()
    println("+++ end drawing +++")
}



fun main() {
//    val macBookPro = MacBookPro()
//    val processorUpgradeMacbookPro = ProcessorUpgradeMacbookPro(macBookPro)
//    println(processorUpgradeMacbookPro.getCost())
//    println(processorUpgradeMacbookPro.getDesc())

    Printer().run {
        startDraw {
            drawLine()
        }

        startDraw {
            drawDottedLine()
        }

        startDraw {
            drawStar()
        }
    }

}