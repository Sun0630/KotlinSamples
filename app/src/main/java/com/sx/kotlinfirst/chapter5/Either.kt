package com.sx.kotlinfirst.chapter5

/**
 * @author sunxin
 * @date 2019-12-12 10:32
 * @desc 密封类封装 Either 只有两个子类型 Left  Right  如果 Either<A,B> 对象包含的是A的实例，那它就是Left的实例，否则是Right的实例
 */
sealed class Either<out L, out R> {
    class Left<out L>(val left: L) : Either<L, Nothing>()
    class Right<out R>(val right: R) : Either<Nothing, R>()
}





