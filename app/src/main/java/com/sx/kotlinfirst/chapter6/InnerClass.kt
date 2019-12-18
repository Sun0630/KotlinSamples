package com.sx.kotlinfirst.chapter6

/**
 * @author sunxin
 * @date 2019-12-14 14:59
 * @desc 内联函数
 */

// 出现的原因：kotlin大量使用lambda，会带来一些额外的开销，内联函数就是用来解决这个问题
// 缺点：尽量避免对具有大量函数体的函数进行内联，会导致过多的字节码数量
fun main() {
    foo(
        {
            println("I am inline")
        },
        {
            println("I am noinline")
        }
    )

    // 具体化参数类型
    getType<Int>()

    // 非局部返回

    // 先看一个局部返回的例子

    foo()
    // 执行结果发现，return只在该函数的局部生效
//    end block
//    before local return
//    end local return
    // 把foo参数换成lambda
    foo {
        // lambda 不允许存在 return 关键字
//        return  // return is not allowed here
        // 加上 inline 关键字之后允许
        //SO89DF-89ETHG-86OKH5-8WET6
        return  //before local returning 执行完之后就退出了foo的执行
    }



    foo1 {
        // 使用标签来实现Lambda的非局部返回，不使用inline
        return@foo1
    }


}

inline fun <reified T> getType() {
    println(T::class)
}


fun localReturn() {
    return
}

fun foo1(returning: () -> Unit) {
    println("before local returning")
    returning()
    println("end local returning")
    return
}

//crossinline 避免带有return的Lambda参数产生破坏
inline fun foo(/*crossinline*/ returning: () -> Unit) {
    println("before local returning")
    returning()
    println("end local returning")
    return
}

fun foo() {
    println("before local return")
    localReturn()
    println("end local return")

}


// 函数开头加上 inline ，那么他的函数体及lambda参数都会被内联
// lambda参数开头加上noinline ，就不会被内联
inline fun foo(block1: () -> Unit, noinline block2: () -> Unit) {
    println("before block")
    block1()
    block2()
    println("end block")
}