package com.sx.kotlinfirst.chapter5

import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

/**
 * @author sunxin
 * @date 2019-12-13 09:47
 * @desc 使用匿名内部类获取所有类型信息的泛型类
 */

open class GenericTypeToken<T>{
    var type:Type = Any::class.java

    init {
        // 匿名内部类在初始化的时候就会绑定父类或父类接口的相应信息
        val superClass = this.javaClass.genericSuperclass
        type = (superClass as ParameterizedType).actualTypeArguments[0]
    }
}


fun main() {
    val gt = object : GenericTypeToken<Map<String,String>>(){}
    println(gt.type)
}