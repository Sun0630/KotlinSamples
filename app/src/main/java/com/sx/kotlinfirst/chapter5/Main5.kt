package com.sx.kotlinfirst.chapter5

/**
 * @author sunxin
 * @date 2019-12-12 09:43
 * @desc Kotlin 的类型系统
 */


// 座位 Student? 表示 Student可能为空
data class Seat(val student: Student?)

// 学生
data class Student(val glasses: Glasses?)

// 眼镜
data class Glasses(val degreeOfMyopia: Double)

//将任意不为空的类型转为目标类型T 这个会有问题 unchecked cast：Any to T   因为泛型擦除了
fun <T> castA(original: Any): T? = original as? T

// 内联函数  reified :具体化,利用它，我们就可以在方法体内访问泛型指定的jvm类对象，记得加 inline
inline fun <reified T> cast(original: Any): T? = original as? T

fun main() {

    val s = cast<String>("1DFFE")
    println(s)

    val glasses = Glasses(100.00)
    val student = Student(glasses)
    val seat = Seat(student)


    //1. ?. 表示安全调用，当对象存在的时候才会调用
    val degreeOfMyopiaKt = getDegreeOfMyopiaKt(seat)

    // todo:这里返回了一个Either<Error,Double> 对象，怎么才能拿到具体的度数呢？
    println("该位置上学生眼睛度数：${getDegreeOfMyopiaKt(seat)}")


    //2. Elvis 操作符  ?:  又称为合并操作符
    // 假设座位上有学生，如果不带眼镜，眼镜读数为 -1 ，如果带了眼镜，度数为眼镜度数。与JAVA中的三目操作符类似

    val result = student.glasses?.degreeOfMyopia ?: -1


    //3. 非空断言  !!.
    // 确保一个学生是戴眼镜的, 如果没戴眼镜，那么将会抛出NPE
    val res = student!!.glasses


    // 4. 类型检查   is
    val obj: Any = "dkdkdk"

    if (obj is String) {
        // 智能转换 smart casts
        println("---${obj.length}")
    }

    when (obj) {
        is String -> println("obj is a string")
        !is String -> println("obj is not a string")
    }

    // 5. Any 是 非空类型的根类型  Any? 是 所有类型的跟类型
    // Nothing and Nothing? 没有实例的类型。任何返回值为Nothing的表达式之后的语句都是无法执行的。
    // kotlin 中的 return throw 的返回值都是Nothing.Nothing? 是 Nothing 的父类
    // 本质上和 null 没有区别

    // 6.  在 kotlin 中， Int 类型等同于 int  Int? 等同于 Integer
    val x1: Int = 18
    val x2: Int? = 19


    //7. 泛型：让类型更加安全
    // 泛型的优点：1. 能在编译的时候就进行类型检查  在运行的时候会自动进行类型转换   2. 更加语义化，能写出更加通用的代码


    //8. 打破泛型不变  out：协变  in: 逆变
    // 在java中无法将List<String> 赋值给 List<Object> ,如果允许的话，将不在保证泛型安全
    // 但是在Kotlin中可以实现下面的赋值
    val stringList: List<String> = ArrayList<String>()
    val angList: List<Any> = stringList
    // 问题就在于 java 和 Kotlin 中的List 类型是不一样的
    // public interface List<out E> : Collection<E>
    // out：说明这个泛型类及泛型方法是协变的。 类型A是类型B的子类型，那么Generic<A> 也是 Generic<B> 的子类型
    // out: 出的意思，可以理解为List是一个只读列表
    // 将会出现类型不安全的问题

//    angList.add("kotlin") // 不允许 add

    // 如果一个List支持协变，那么它将无法添加元素，只能从里面读取内容，从List的源码中可以看出，没有添加，删除等方法
    // List 一旦创建将无法被修改


    // 一个方法的参数不允许传入参数的父类型对象（在Kotlin中可以使用 @UnsafeVariance 来解除这个限制）
    // 逆变
    // 定义一个比较器
    val numberComparator = Comparator<Number> { o1, o2 -> o1.toDouble().compareTo(o2.toDouble()) }

    val doubleList = mutableListOf<Double>(1.0,2.0,8.0,4.0)

    doubleList.sortWith(numberComparator)
    println(doubleList)

    val intList = mutableListOf<Int>(3,2,4,5,2,4,5)
    intList.sortWith(numberComparator)
    println(intList)

    // expect fun <T> MutableList<T>.sortWith(comparator: Comparator<in T>): Unit

    // Int， Double 是 Number 的子类，
    // 但是在sortWith 方法参数，本应接收一个Comparator<Double> 对象，现在却接收了Comparator<Number>
    // in 使泛型有了另一个特性，那就是 逆变。
    // 假设类型A 是 类型B的子类型，那么Generic<B> 反过来是 Generic<A> 的子类型
    // 所以，我们就可以讲一个 numberComparator 当做 doubleComparator 传入
    // 限制：out关键字声明的泛型参数类型不能作为方法的参数类型，但可以作为方法的返回值类型， in正好相反
    // in 关键字声明的泛型类型参数能作为方法的参数类型，但不能作为方法的返回值类型
    // in 入的意思，可以理解为消费内容，可以将列表看做一个可读可写功能受限的列表，获取值只能用Any类型


}


fun getDegreeOfMyopia(seat: Seat?): Double? {
    return seat?.student?.glasses?.degreeOfMyopia
}


fun getDegreeOfMyopiaKt(seat: Seat?): Either<Error, Double> {

    // let: 调用某对象的let函数，该对象会作为函数的参数，在函数块内可用 it 指代该对象。返回值微函数块的最后一行或指定的return表达式
    return seat?.student?.glasses?.let {
        Either.Right(it.degreeOfMyopia)
    } ?: Either.Left(Error("-1"))

}




