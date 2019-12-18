package com.sx.kotlinfirst.chapter3

import java.util.*
import kotlin.Comparator

/**
 * @author sunxin
 * @date 2019-12-10 22:34
 * @desc 第三章：面向对象
 */

fun main() {

    // 由于默认参数的存在，要按顺序写或指定参数名
//    val bird = Bird(weight = 100.00, age = 18, color = "Yellow")
//    bird.printSex()


    val comparable = Comparator<String> { s1, s2 ->
        if (s1 == null)
            return@Comparator -1
        else if (s2 == null)
            return@Comparator 1

        s1.compareTo(s2)
    }

    Collections.sort(listOf("1", "2"), comparable)

    for (i in 1..10) {
        print(i)
        for (j in 1..10) {
            print("$i---$j")
//            if (j > 3)
//
//            break
        }
    }

}