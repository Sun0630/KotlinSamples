package com.sx.kotlinfirst.chapter9.behavior

/**
 * @author sunxin
 * @date 2019-12-18 14:02
 * @desc 模板方法模式：高阶函数代替继承
 */

// 模板方法模式与策略模式的异同
// 相似的：他们都可以分离通用的算法和具体的上下文。
// 不同的：如果说策略模式才用的思路是将算法委托，那么传统的模板方法模式更多的是基于继承的方式实现的

// 模板方法模式定义：定义一个算法中的操作框架，而将一些步骤延迟到子类中，使得子类可以不改变算法的结构即可重定义该算法的某些特定步骤

/**
 * 需求：去市民中心办事
 *      步骤1：排队取号
 *      步骤2：根据自己的需求办理个性化的业务
 *      步骤3：评价
 */


// 办事中心
abstract class CivicCenterTask {

    private fun lineUp() {
        println("line up to take a number")
    }

    private fun evaluate() {
        println("evaluate service")
    }

    /**
     * 个性化方法定义成抽象函数交给子类实现
     */
    abstract fun askForHelp()


    fun execute() {
        this.lineUp()
        this.askForHelp()
        this.evaluate()
    }
}

// 我来办理社保
class PullSocialSecurity : CivicCenterTask() {

    override fun askForHelp() {
        println("ask for pull social security")
    }
}


// 他来办卡
class ApplyForCard : CivicCenterTask() {
    override fun askForHelp() {
        println("ask for apply card")
    }

}


// 上述代码复用性已经很高了

// 使用高阶函数再次简化模板方法模式代码
// 办事中心
class CivicCenterTask1 {

    private fun lineUp() {
        println("line up to take a number")
    }

    private fun evaluate() {
        println("evaluate service")
    }


    fun execute(askForHelp: () -> Unit) {
        this.lineUp()
        askForHelp()
        this.evaluate()
    }
}


fun pullSocialSecurity() =  println("ask for pull social security")

fun applyCard() = println("ask for pull apply card")


fun main() {
//    val pullSocialSecurity = PullSocialSecurity()
//    pullSocialSecurity.execute()
//    val applyForCard = ApplyForCard()
//    applyForCard.execute()

    // 使用高阶函数
    CivicCenterTask1().execute {
        pullSocialSecurity()
    }

    CivicCenterTask1().execute(::applyCard)

}