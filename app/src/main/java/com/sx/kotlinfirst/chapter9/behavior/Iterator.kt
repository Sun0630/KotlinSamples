package com.sx.kotlinfirst.chapter9.behavior

/**
 * @author sunxin
 * @date 2019-12-18 15:23
 * @desc 迭代器模式: 核心作用就是将遍历和实现分离开来，在遍历的同时不需要暴露对象的内部表示
 */

// 迭代器模式的实现
// 方案1：实现 Iterator 接口

data class Book(val name: String)

class Bookcase(books: List<Book>) : Iterator<Book> {

    private val iterator: Iterator<Book> = books.iterator()

    override fun hasNext(): Boolean = this.iterator.hasNext()

    override fun next(): Book = this.iterator.next()

}


// 方案2：重载 iterator 方法 ,重载Bookcase类的iterator方法，实现更精简的版本
data class Book1(val name: String)

class Bookcase1(val books: List<Book>) {
    operator fun iterator(): Iterator<Book> = this.books.iterator()
}


// 方案3：扩展函数
// 假设Book是一个引入的类，不能修改源码，使用扩展函数为Bookcase增加迭代的功能

data class Book2(val name: String)

class Bookcase2(val books: List<Book2>)

//operator fun Bookcase2.iterator(): Iterator<Book> = books.iterator()

// 如果想对迭代器的逻辑有更多控制权，可以用object表达式
operator fun Bookcase2.iterator(): Iterator<Book2> = object : Iterator<Book2> {
    override fun hasNext(): Boolean = books.iterator().hasNext()

    override fun next(): Book2 = books.iterator().next()
}


fun main() {
    val bookcase = Bookcase(
        listOf(Book("Dive into kotlin"), Book("Think in Java"))
    )

    while (bookcase.hasNext()) {
        println("the book name is ${bookcase.next().name}")
    }

    val bookcase1 = Bookcase1(
        listOf(Book("Gradle in Action"), Book("Flutter in Action"))
    )
}
