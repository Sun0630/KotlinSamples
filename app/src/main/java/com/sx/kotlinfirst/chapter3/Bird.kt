package com.sx.kotlinfirst.chapter3

/**
 * @author sunxin
 * @date 2019-12-10 23:33
 * @desc
 */
// 参数名前没有val
open class Bird(weight: Double = 0.00, age: Int = 11, private val color: String = "blue") {

    private val weight: Double = weight
    private val age: Int = age

    // 延迟加载 by lazy 首次调用时才会进行赋值操作，一旦被赋值，后续不能被更改。
    // 该变量必须是引用不可变的，不能通过var来声明。
    // 系统会默认给lazy 属性加上同步锁，同一时刻只允许一个线程对lazy属性进行初始化
    private val sex: String by lazy(LazyThreadSafetyMode.PUBLICATION) {
        // 并行模式
        if (this.color == "Yellow") "male" else "female"
    }

//    private val sex: String by lazy(LazyThreadSafetyMode.NONE) {
//        // 不做任何线程保证也不会有任何线程开销
//        if (this.color == "Yellow") "male" else "female"
//    }

    // lateinit ：延迟初始化 只能用于var变量声明，不能用于基本数据类型，需要用包装类型
    private lateinit var voice: String

    init {
        // 语句块，属于构造方法的一部分,没有val 或 var 修饰的时候可以在这里调用
        // 可以有多个init块，按从上到下的顺序执行。
        // 多个 init 块，更容易进行 职能分离
        // kotlin规定，类中的所有非抽象属性成员必须在对象创建时被初始化
        println("init....1")
        // 初始化属性
//        this.sex = if (this.color == "Yellow") "male" else "female"
        println("init do something")
        println("the weight is $weight")
    }

    init {
        println("init....2")
    }


    fun printSex() = print("鸟的性别是:$sex")


    open fun fly() {
        println("I can Fly")
    }

}