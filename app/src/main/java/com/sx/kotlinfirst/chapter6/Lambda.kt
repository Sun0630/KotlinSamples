package com.sx.kotlinfirst.chapter6

/**
 * @author sunxin
 * @date 2019-12-13 15:53
 * @desc lambda 表达式
 */

// 带接受者的lambda

// 返回值是一个函数类型： (Int) -> Int    左侧是函数的参数类型，右侧是返回值类型。必须用一个括号来包裹参数类型
val sum: Int.(Int) -> Int = { other -> plus(other) }

// 类型安全构造器
class HTML {
    fun body() {
        println("this is body")
    }
}

// 接收参数是一个函数类型
fun html(init: HTML.() -> Unit): HTML {
    val html = HTML()
    html.init()
    return html
}


fun main() {
    println(2.sum(3))

    html {
        body()
    }


    // 集合的高阶函数 API

    // 1. 遍历集合：map
    // 遍历集合中的每个元素并*2,并返回一个新集合
    val list = listOf(1, 2, 3, 4, 5)

    val map = list.map {
        it * 2
    }

    println("新集合为:${map}")

    // 2. 对集合进行筛选 : filter count

    val kayce = Student("Kayce", 26, "m", 100)
    val rip = Student("Rip", 29, "m", 130)
    val beth = Student("Beth", 35, "f", 200)
    val john = Student("John", 26, "m", 300)
    val jimmy = Student("Jimmy", 26, "m", 40)

    val students = listOf(kayce, rip, beth, john, jimmy)

    // 操作一：获取男生组成的列表
    val manList = students.filter {
        it.sex == "m"
    }


    val womanList = students.filterNot {
        it.sex == "m"
    }


    println("男生有：${manList}")
    println("女生有：${womanList}")


    // 操作二：统计满足条件的元素个数
    // 统计男女生的个数
    val manCount = students.count {
        it.sex == "m"
    }

    val womanCount = students.count {
        it.sex == "f"
    }

    println("男生个数:${manCount}")
    println("女生个数:${womanCount}")


    //3. 别样的求和方式：sumBy sum  fold  reduce
    // 求出所有学生的成绩总和 sumBy
    val totalScore = students.sumBy {
        it.score
    }

    println("所有学生的成绩总和是:${totalScore}")

    // sum: 对数值类型的列表进行求和
    val listTotal = list.sum()
    println("listTotal->sum:$listTotal")
    val listSumBy = list.sumBy {
        it
    }
    println("listTotal->sumBy:${listSumBy}")


    // fold 累计操作（递归），每次计算的结果作为参数提供给下一次调用
    // 这就是一个累加操作哦，可以指定初始值为 0
    val foldScore = students.fold(0) { acc, student ->
        acc + student.score
    }

    println("fold->sum:$foldScore")


    // reduce (减少) 与 fold很相似，唯一的区别是它没有初始值
//    val reduce = students.reduce { acc, student -> acc.score}
//    println(reduce)


    // 4. groupBy 分组
    // 根据学生性别进行分组,返回一个Map<String,List<Student>>
    val groupBy = students.groupBy {
        it.sex
    }

    val man = groupBy["m"]
    println("分组数据：${man}")


    // 5. 扁平化，处理嵌套集合:FlatMap flatten  (平)

    // 嵌套集合的扁平化，将多个集合的元素弄到一个新集合中
    val nestList = listOf(listOf("kayce","beth"), listOf("rip","lip"), listOf("jack"))

    val flattenList = nestList.flatten()

    println("扁平化集合:${flattenList}")

    // flatMap: 将集合中的元素进行加工，然后返回一个加工过的结合
    // flatMap 可以看作是由 flatten 和 map 进行组合之后的方法
    nestList.flatMap {
        it.map {
            println("FlatMap:$it")
        }
    }


}



