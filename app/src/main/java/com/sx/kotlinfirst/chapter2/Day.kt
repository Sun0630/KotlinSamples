package com.sx.kotlinfirst.chapter2

/**
 * @author sunxin
 * @date 2019-12-10 21:22
 * @desc kotlin 的枚举类，可以拥有构造函数，定义额外的属性和方法
 */
enum class DayOfWeek(val day: Int) {
    MON(1),
    TUE(2),
    WEN(3),
    THU(4),
    FRI(5),
    SAT(7),
    SUN(7)

    ; // 用于隔离枚举值 和属性方法等


    fun getDayOfWeek(): Int {
        return day
    }
}