package com.sx.kotlinfirst.chapter8

import kotlin.reflect.KMutableProperty
import kotlin.reflect.KProperty
import kotlin.reflect.full.memberProperties

/**
 * @author sunxin
 * @date 2019-12-16 20:48
 * @desc 元编程
 */
/**
 * 元数据：描述数据的数据称之为元数据
 * 元编程：操作元数据的编程称之为元编程
 * 程序即是数据，数据即是程序
 * 反射是获取描述程序信息的典型例子
 */

data class User(var name: String, val age: Int)

data class Person(var company: String, val age: Int, val address: String)

// 将一个data class 转换成map
// 违背DRY原则， Don`t Repeat Yourself
fun toMap(user: User): Map<String, Any> {
    return hashMapOf("name" to user.name, "age" to user.age)
}

// 使用反射来实现一下,将所有类型转化为map
// 不用再手动创建map
object Mapper {
    fun <A : Any> toMap(a: A) = run {
        // 获取A中所有属性
        a::class.memberProperties.map {
            val p = it as KProperty<*>
            // call 实际上是直接调用了 Getter
            p.name to p.call(a)
        }.toMap()
    }
}


fun main() {
//    Mapper.toMap(User(name = "sunxin",age = 18))
    val u = User(name = "sunxin", age = 18)
    println(toMap(u))
    println(Mapper.toMap(u))
    KmutablePropertyShow()
    println("------------------")
    KParameterShow()
    println("------------------")
    KTypeShow()

}

/**
 * 通过反射修改类成员属性
 */
fun KmutablePropertyShow() {
    val u = User("xixixi", 8)
    val props = u::class.memberProperties
    for (prop in props) {
        when (prop) {
            is KMutableProperty<*> -> {
                prop.setter.call(u, "sunxin")
            }
            else -> {
                prop.call(u)
            }
        }
    }
    println(u.name)
}

/**
 * 用KParameter的type属性获取KCallable 的参数类型
 */
fun KParameterShow() {
    val p = Person("信息科技", 18, "TaiAn")
    for (c in Person::class.members) {
        print("${c.name} ->") // 属性
        for (parameter in c.parameters) {
            print("${parameter.type} --") // 参数的类型
        }
        println()
    }
}

/**
 * 获取返回值类型，List<String>  -> List
 */
fun KTypeShow() {
    Person::class.members.forEach {
        println("${it.name}->${it.returnType.classifier}")
    }
}






