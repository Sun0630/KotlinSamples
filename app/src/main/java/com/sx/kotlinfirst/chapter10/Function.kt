package com.sx.kotlinfirst.chapter10

/**
 * @author sunxin
 * @date 2019-12-19 10:29
 * @desc 函数式编程
 */

sealed class Format

data class Print(val text: String) : Format()

object NewLine : Format()

val str = listOf(Print("Hello"), NewLine, Print("Kotlin"))


/**
 * 副作用：修改了某处的某些东西，多次调用得到结果不一定相同。并发访问就是一个明显的例子
 *  1. 修改了外部变量的值
 *  2. IO操作，写入磁盘
 *  3. UI操作，修改按钮的操作状态
 */

// 将一个Format类对象的列表格式化为普通字符串
fun unsafeInterpreter(str: List<Format>) {

    str.forEach {
        when (it) {
            is Print -> print(it.text)
            is NewLine -> println()
        }
    }

}



// 纯函数：消除副作用只要传递给它的参数一致，每次获得的都是相同的返回结果值

/**
 * 基本法则:具有引用透明性，一个表达式在程序中可以被他等价的值替换，而不影响结果
 */


// fold 累计操作（递归），每次计算的结果作为参数提供给下一次调用
fun stringInterpreter(str: List<Format>) = str.fold("") { s: String, format: Format ->
    when (format) {
        is Print -> s + format.text
        is NewLine -> s + "\n"
    }
}


// 用扩展方法实现Typeclass



fun main() {
//    unsafeInterpreter(str)
    val s = stringInterpreter(str)
    println(s)
}