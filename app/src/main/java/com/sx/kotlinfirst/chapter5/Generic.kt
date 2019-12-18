package com.sx.kotlinfirst.chapter5

/**
 * @author sunxin
 * @date 2019-12-12 15:18
 * @desc 泛型的使用
 */


//泛型的优点：
//  1. 能在编译的时候就进行类型检查  在运行的时候会自动进行类型转换
//  2. 更加语义化，能写出更加通用的代码


// 需求：定义一个find方法，传入一个对象，若列表中存在该对象，则返回该对象，不存在则返回空

// 方法1：由于原来的集合不存在这样一个方法，所以我们可以自定义集合类继承 ArrayList
class SmartList<T> : ArrayList<T>() {

    fun find(t: T): T? {
        val index = super.indexOf(t)
        return if (index >= 0) super.get(index) else null
    }

}

// 方法2 使用扩展函数
fun <T> ArrayList<T>.find(t: T): T? {
    val index = indexOf(t)
    return if (index >= 0) get(index) else null
}


fun main() {
    val smartList = SmartList<String>()
    smartList.add("one")
    println(smartList.find("one")) // one
    println(smartList.find("two").isNullOrEmpty()) // true

    val arrayList = ArrayList<String>()
    arrayList.add("one")
    println(arrayList.find("one")) // one
    println(arrayList.find("two").isNullOrEmpty()) // true


    // 泛型类型上界约束
    val applePlate = FruitPlate<Apple>(Apple(100.0))

    val bananaPlate = FruitPlate(Banana(19.0))

    val nullFruitPlate = FruitPlate(null)

//    val noodlesPlate = FruitPlate(Noodles(10.0))  error


    // 获取泛型类型方式一：匿名内部类
    getGenericType()

}


// 泛型类型约束：设定类型上界

// 水果类
open class Fruit(val weight: Double)

class Apple(weight: Double) : Fruit(weight)

class Banana(weight: Double) : Fruit(weight)


// 水果盘子, 只能装水果,盘子也可以空着
class FruitPlate<T : Fruit?>(t: T)


// 来一个面条类
class Noodles(weight: Double)


// 多个泛型条件的情况
// 需求：假设有一把刀只能用来切张在地上的水果，西瓜

interface Ground

class WaterMelon(weight: Double) : Fruit(weight), Ground

// 使用where关键字实现对泛型参数类型添加多个的约束
fun <T> cut(t: T) where T : Fruit, T : Ground {
    println("You can cut me")
}


// 获取泛型类型的两种方法

// 1. 使用匿名内部类获取泛型类型
// 泛型的类型擦除并不是真的将全部的类型信息都擦除了，还是会将类型信息放在对应class的常量池中

val list1 = ArrayList<String>()


val list2 = object : ArrayList<String>() {} // 匿名内部类


fun getGenericType(): Unit {

    println(list1.javaClass.genericSuperclass)//    java.util.AbstractList<E>
    println("匿名内部类获取泛型类型：${list2.javaClass.genericSuperclass}")//    java.util.ArrayList<java.lang.String>


    val typeByInline = getTypeByInline<Map<String, String>>()
    println("内联函数获取泛型类型:${typeByInline}")
}


// 2. 使用内联函数获取泛型类型
// 内联函数在编译的时候编辑器就会将相应的函数的字节码插入调用的地方，参数类型也会被插入字节码之中
inline fun <reified T> getTypeByInline(): Class<T> {
    return T::class.java
}










