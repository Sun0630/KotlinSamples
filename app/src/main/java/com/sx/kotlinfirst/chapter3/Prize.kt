package com.sx.kotlinfirst.chapter3

/**
 * @author sunxin
 * @date 2019-12-11 14:55
 * @desc 伴生对象
 */
fun main() {
    val prize = Prize("红包", 10, Prize.TYPE_REDPACK)
    println(Prize.isRedPack(prize))
    DatabaseConfig.host
    DatabaseConfig.password
}


class Prize(val name: String, val count: Int, val type: Int) {

    // 伴生对象是替代JAVA中的static的一种方式
    // 伴生对象包裹了所有的静态属性和方法
    companion object {
        val TYPE_REDPACK = 0
        val TYPE_COUPON = 1


        fun isRedPack(prize: Prize): Boolean {
            return prize.type == TYPE_REDPACK
        }
    }







}


// 天生的单例模式
object DatabaseConfig{
    var host:String = "127.0.0.1"
    var port:Int = 3306
    var username:String = "root"
    var password :String = "root"
}






