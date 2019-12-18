package com.sx.kotlinfirst.chapter4

/**
 * @author sunxin
 * @date 2019-12-11 16:34
 * @desc 密封类
 */


// 密封类的子类只能定义在父类或者与父类同一个文件内
// Shape 就是一个 和类型，是类型安全的
sealed class Shape {

    // Circle Rectangle Triangle 是通过将基本类型Double构造成类而组合成的积类型
    // 圆形
    class Circle(val radius: Double) : Shape()

    // 长方形
    class Rectangle(val width: Double, val height: Double) : Shape()

    // 三角形
    class Triangle(val base: Double, val height: Double) : Shape()
}

/**
 * 求面积
 * 密封类最大的好处就是使用when表达式的时候不用去考虑非法情况了，可以省略 else分支。
 */
fun getArea(shape: Shape): Double = when (shape) {
    is Shape.Circle -> shape.radius * shape.radius * Math.PI
    is Shape.Rectangle -> shape.height * shape.width
    is Shape.Triangle -> shape.base * shape.height / 2
}