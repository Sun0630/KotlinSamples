package com.sx.kotlinfirst.chapter6

/**
 * @author sunxin
 * @date 2019-12-14 13:56
 * @desc Kotlin 中的集合
 */


fun main() {
    // List,Set,Map
    //可变集合（MutableList）  只读集合(List)

    // 可变集合
    val mutableList = mutableListOf(1, 2, 3, 4, 5)
    mutableList[0] = 0
    println("可变集合:$mutableList") // [0, 2, 3, 4, 5]

    // 只读集合 kotlin将可变集合中的修改，添加，删除等方法移出之后就变成了只读集合
    val list = listOf(1, 2, 3, 4, 5)
//    list[0] = 0 // 报错

    // 只读集合能够被修改的两种情况
    // 只读列表在某些情况下是安全的，但并不总是安全的
    //1.
    val writeList: MutableList<Int> = mutableListOf(1, 2, 3, 4)
    // 直接将一个可变集合复制给只读集合
    val readList: List<Int> = writeList
    println("readList1:$readList")//[1, 2, 3, 4]
    writeList[0] = 0
    println("readList2:$readList")//[0, 2, 3, 4]

    // 2. 与java互操作的时候
    bar(list)  //[2, 4, 6, 8, 10]


    // 惰性集合
    // 惰性求值：表示一种在被需要时才进行求值的计算
    // 优点：优化性能  能构造出无限的数据类型
    // 序列的操作方式分为中间操作和末端操作
    // 通过序列提高效率
    val seqList = list
        .asSequence() // 将一个列表转化成序列
        // 中间操作
        .filter {
            println("filter:$it")
            it > 2
        }
        .map {
            println("map:$it")
            it * 2
        }
        // 末端操作，用来触发
        .toList()
    // 序列在执行链式操作的时候，会将所有的操作都应用在一个元素上，也就是说，第一个元素执行完所有操作之后，开始执行第二个元素
    // 不满足filter条件的元素也不会去执行map操作，这一步可以减少一部分性能开销

    println("seqList:$seqList")

    // 构造一个无限的自然数数列
    val naturalNumList = generateSequence(0) {
        it + 1
    }.takeWhile {
        it < 9
    }.toList()

    println("naturalNumList:$naturalNumList") //[0, 1, 2, 3, 4, 5, 6, 7, 8]


}

fun bar(list: List<Int>) = println(Util.foo(list))




