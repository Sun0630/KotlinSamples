package com.sx.kotlinfirst.chapter9.behavior

/**
 * @author sunxin
 * @date 2019-12-18 16:29
 * @desc 责任链模式
 */
// 目的：避免请求的发送者和接收者之间的耦合关系，将这个对象连成一条链，并沿着这个链传递该请求，直到有一个对象处理它为止
// 需求场景：希望使得多个对象都有机会处理某种类型的请求

/**
 * 案例：学生会基金管理
 *      当要发生一笔支出的时候
 *          金额 < 100   -> 分部长审批
 *          金额 > 100   -> 会长审批
 *          金额 > 500   -> 学院辅导员审批
 *              > 1000   -> 驳回重新申请
 */

// 实现一：使用面向对象的思路结合责任链模式

// 支出
data class ApplyEvent(val money: Int, val title: String)


interface ApplyHandler {
    // 接替者
    val successor: ApplyHandler?

    // 处理
    fun handleEvent(event: ApplyEvent)
}


// 分部长
class GroupLeader(override val successor: ApplyHandler?) : ApplyHandler {

    override fun handleEvent(event: ApplyEvent) {
        when {
            event.money <= 100 -> println("Group Leader handled application:${event.title}")
            else -> when (successor) {
                is ApplyHandler -> successor.handleEvent(event)
                else -> println("Group Leader: this application cannot be handled.")
            }
        }
    }
}


// 会长
class President(override val successor: ApplyHandler?) : ApplyHandler {

    override fun handleEvent(event: ApplyEvent) {
        when {
            event.money <= 500 -> println("President handled application:${event.title}")
            else -> when (successor) {
                is ApplyHandler -> successor.handleEvent(event)
                else -> println("President: this application cannot be handled.")
            }
        }
    }
}


// 学院
class College(override val successor: ApplyHandler?) : ApplyHandler {
    override fun handleEvent(event: ApplyEvent) {
        when {
            event.money > 1000 -> println("College:This application is refused")
            else -> println("College handled application: ${event.title}")
        }
    }
}


// 当输入参数处于某个责任链环节的有效接收范围之内，该环节才能对其做出正常的处理操作。在编程语言种有专门术语描述这个：偏函数

// 偏函数：在数学中，指的是定义域X中可能存在某些值在值域Y中没有对应的值

// 定义偏函数类型 PartialFunction：解决责任链模式中各个环节对于输入的校验及处理逻辑的问题
class PartialFunction<in P1, out R>(
    // 校验函数
    private val defineAt: (P1) -> Boolean,
    // 处理函数
    private val f: (P1) -> R
) : (P1) -> R {

    override fun invoke(p1: P1): R {
        if (defineAt(p1)) {
            return f(p1)
        } else {
            throw IllegalArgumentException("Value:($p1) isn't supported by this function")
        }
    }

    // 这个方法仅仅作为一个拷贝 defineAt 的方法
    fun isDefineAt(p1: P1) = defineAt(p1)

}


// PartialFunction 方法并没有处理如何将请求在整个链条中进行传递。我们需要给他扩展一个orElse 方法
// that 责任链后继者
// infix : 中缀 https://www.kotlincn.net/docs/reference/functions.html#%E4%B8%AD%E7%BC%80%E8%A1%A8%E7%A4%BA%E6%B3%95
// 中缀函数必须满足:1,必须是成员函数或扩展函数  2. 必须只有一个参数  3. 其参数不得接收可变数量的参数 且不能有默认值
infix fun <P1, R> PartialFunction<P1, R>.orElse(that: PartialFunction<P1, R>): PartialFunction<P1, R> {
    return PartialFunction(
        { this.isDefineAt(it) || that.isDefineAt(it) },
        {
            when {
                this.isDefineAt(it) -> this(it)
                else -> that(it)
            }
        }
    )
}


// 使用 orElse构建责任链
//data class ApplyEvent1(val money: Int, val title: String)

// 自运行的Lambda语法
// 分部长
val groupLeader = {
    val defineAt: (ApplyEvent) -> Boolean = { it.money <= 200 }
    val handler: (ApplyEvent) -> Unit = { println("Group Leader handled application:${it.title}") }

    PartialFunction(defineAt, handler)
}()


val president = {
    val defineAt: (ApplyEvent) -> Boolean = { it.money <= 500 }
    val handler: (ApplyEvent) -> Unit = { println("President handled application:${it.title}") }

    PartialFunction(defineAt, handler)
}()


val college = {
    val defineAt: (ApplyEvent) -> Boolean = { true }
    val handler: (ApplyEvent) -> Unit = {
        when {
            it.money > 1000 -> println("College:this application is refused")
            else -> println("College handled application:${it.title}")
        }
    }

    PartialFunction(defineAt, handler)
}()

// 使用orElse构建一个基于责任链模式和PartialFunction类型的中缀表达式
val applyChain = groupLeader orElse president orElse college


fun main() {
//    val college = College(null)
//    val president = President(college)
//    val groupLeader = GroupLeader(president)
//
//    groupLeader.handleEvent(
//        ApplyEvent(
//            10,
//            "buy a pen"
//        )
//    ) //Group Leader handled application:buy a pen
//    groupLeader.handleEvent(ApplyEvent(200, "team building"))
//    groupLeader.handleEvent(ApplyEvent(600, "hold a debate match"))
//    groupLeader.handleEvent(ApplyEvent(1200, "annual meeting of the college"))
//

    // 测试偏函数
    applyChain(ApplyEvent(600, "hold a debate match"))


}
