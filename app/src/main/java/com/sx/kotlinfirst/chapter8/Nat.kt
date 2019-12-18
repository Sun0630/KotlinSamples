package com.sx.kotlinfirst.chapter8

/**
 * @author sunxin
 * @date 2019-12-16 21:29
 * @desc Kotlin 的 KClass
 */

sealed class Nat {

    companion object {
        object Zero : Nat()
    }


    val Companion._0
        get() = Zero

    fun <A : Nat> Succ<A>.preceed(): A {
        return this.prev
    }


}


data class Succ<N : Nat>(val prev: N) : Nat()


fun <A : Nat> Nat.plus(other: A): Nat {
    return when (other) {
        is Succ<*> -> Succ(this.plus(other.prev))
        else -> this
    }
}

