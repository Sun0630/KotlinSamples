package com.sx.kotlinfirst.chapter2

import java.util.*

/**
 * @author sunxin
 * @date 2019-12-10 16:20
 * @desc 第二章：基础语法
 */


class Book(val name: String)


fun main() {
    val book = ::Book
    println(book("Kotlin In Action").name)

    val countryTest = CountryTest()

    val countries = listOf<Country>(
        Country("ESP", "EU", 100000),
        Country("UK", "EU", 1000),
        Country("SEI", "EU", 1000)
    )


    // 使用 ::  实现对于某个类的方法进行引用
    countryTest.filterCountries(countries, countryTest::isBigEuCountry).map {
        println(it.name)
    }


    // 传入一个匿名函数
    countryTest.filterCountries(countries, fun(country: Country): Boolean {
        return country.continent == "EU" && country.population > 10000
    }).map {
        println("使用匿名函数：${it.name}")
    }


    // 上面进一步简化,直接使用lambda表达式
    countryTest.filterCountries(countries) { country ->
        country.continent == "EU" && country.population > 10000
    }.map {
        println("使用lambda表达式：${it.name}")
    }

    // 如果lambda 表达式返回的不是Unit，那么默认的最后一行表达式的值类型就是返回值类型
    val foo = { x: Int ->
        val y = x + 1
        y //返回值的类型
    }

    println(foo(2))


    // 遍历集合
    listOf(1, 2, 3, 4).forEach {
        // it: 学名叫做 单个参数的隐式名称
        println(foo(it))
    }



    // lambda 表达式函数体，必须通过 Invoke 或者 () 来调用lambda
    fun fooo(int: Int) = {
        println("fooo:$int")
    }

    listOf(1, 2, 3, 4).forEach {
        // it: 学名叫做 单个参数的隐式名称
        fooo(it).invoke()
    }


    // 访问外部环境变量的函数


    var sum = 0

    listOf(1, 2, 3, 4).filter {
        it > 0
    }.forEach {
        sum += it
    }

    println(sum)


    // kotlin 支持自运行的lambda
//    {x:Int-> println(x)}(1)

    // if-else 表达式
    ifExpression(true)

    // when 表达式
    println(schedule(true, DayOfWeek.THU))

    //for 循环   .. 范围表达式  rangeTo  step: 步长
    for (i in 1..10 step 2) {
        print("$i ")
    }

    println("-------------")


    // downTo 倒序
    for (i in 10 downTo 1 step 2) {
        print("$i ")
    }

    println("-------------")
    //until 半开区间 不包含10
    for (i in 1 until 10) {
        print("$i ")
    }

    println("-------------")

    // in 检查成员关系

    val isIn = "a" in listOf("1", "2", "a")
    println(isIn)


    val isInA = "kot" in "abc".."xyz"
    println(isInA)


    // 中缀表达式
    mapOf(
        1 to "one",
        2 to "two",
        3 to "three"
    )
    // 自定义中缀表达式
    val person = Person()
    person called "sunxin"
    person.called("Lix")


    // 可变参数  vararg
    printLetters("a", "b", "c", count = 3)
    val letters = arrayOf("a", "b", "c", "d")
    // 使用 * 传入外部的变量作为可变参数的变量
    printLetters(*letters, count = letters.size)


    // 字符串的定义和操作
    val str = "Hello World"

    println(str.length)
    println(str.substring(0, 5))
    println("$str Hello Kotlin")
    println(str.replace("World", "Kotlin"))

    for (c in str.toUpperCase(locale = Locale.CHINA)) {
        print(c)
    }

    println()
    println("--------")

    // 原生字符串
    val rawString = """
        \n Kotlin is awesome,
        \n Kotlin is a better Java.
    """.trimIndent()

    println(rawString)

}

/**
 * if-else 表达式
 */
fun ifExpression(flag: Boolean) {

    val a = if (flag) "dive into kotlin" else ""

    println(a.toUpperCase(locale = Locale.CHINA))

}

/**
 * when 表达式
 */
fun schedule(sunny: Boolean, day: DayOfWeek) = when (day) {
    DayOfWeek.MON -> "打篮球"
    DayOfWeek.TUE -> "吃饭"
    DayOfWeek.WEN -> "去约会"
    DayOfWeek.THU -> "看书"
    else -> when {
        sunny -> "去图书馆"
        else -> "学习"
    }
}


/**
 * 可变参数，使用 vararg 修饰，类似 java中的 ...
 */
fun printLetters(vararg letters: String, count: Int): Unit {
    print("$count letters are ")
    for (letter in letters) {
        print(letter)
    }
    println()
}