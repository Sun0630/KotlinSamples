package com.sx.kotlinfirst.chapter8

/**
 * @author sunxin
 * @date 2019-12-17 10:14
 * @desc 注解
 */

// 定义注解
annotation class Cache(val namespace:String,val expires:Int)

annotation class CacheKey(val keyName:String,val buckets:IntArray)


@Cache(namespace = "hero",expires = 3600)
data class Hero(
    // 作用于属性
    @property:CacheKey(keyName = "outman",buckets = [1, 2, 3])
    val name:String,
    // 作用域字段
    @field:CacheKey(keyName = "atk",buckets = [1, 2, 3])
    val attack:Int,
    // 作用于 Getter
    @get:CacheKey(keyName = "def",buckets = [1,2,3])
    val defense:Int,
    val initHp:Int
)


fun main() {
    // 通过反射获取注解信息
    // 运行时发生
    val cache = Hero::class.annotations.find {
        it is Cache
    } as Cache?

    println("namespace ${cache?.namespace}")
    println("expires ${cache?.expires}")

}


