package com.sx.kotlinfirst.chapter7

/**
 * @author sunxin
 * @date 2019-12-16 14:05
 * @desc 扩展函数
 */


/**
 * 扩展函数：this代表的是接收者类型的对象
 * 实现机制：可以将扩展函数近似的理解为静态方法。独立于该类的任何对象，也不依赖类的特定实例，被该类的所有实例共享
 * 扩展函数不会带来额外的性能消耗
 * 成员方法优先级总高于扩展函数
 */
fun MutableList<Int>.exchange(fromIndex: Int, toIndex: Int) {
    val temp = this[fromIndex]
    this[fromIndex] = this[toIndex]
    this[toIndex] = temp
}


// 在kotlin中，如果需要声明一个静态的扩展函数，必须将其定义在伴生对象中
class Son {
    companion object {
        val age = 10
    }


    fun foo1(){
        println("foo in son class")
    }
}


object Parent{
    fun foo1(){
        println("foo in parent class")
    }

    @JvmStatic
    fun main(args: Array<String>) {
        fun Son.foo2(){
            this.foo1()
            // 使用 this@类名来强行指定调用的this，如果son扩展函数在Parent类内，我们将无法对其调用
            this@Parent.foo1()
        }
    }
}

// 在Test中调用
fun Son.Companion.foo() {
    println("age=$age")
}



