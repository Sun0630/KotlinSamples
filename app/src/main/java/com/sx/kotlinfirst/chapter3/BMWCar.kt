package com.sx.kotlinfirst.chapter3

/**
 * @author sunxin
 * @date 2019-12-11 11:02
 * @desc
 */
class BMWCar(val name: String) {
    private val BMWEngine = Engine("BMW-V8")
    private val AUDIEngine  = AUDIEngine("AUDI-V10")
    fun getEngine(): String {

        return BMWEngine.engineType1()
//        return AUDIEngine.engineType()
    }
}

// kotlin 可以用private给单独的类进行修饰，作用域只在当前的Kotlin文件中
private open class Engine(val type: String) {
    // Kotlin 中的protected修饰符在Kotlin中的作用域只有类和子类
   protected open fun engineType(): String {
        return "V8"
    }

    fun engineType1(): String {
        return "V8"
    }
}


private class AUDIEngine(type: String): Engine(type) {

    // 子类可以访问父类的protected
    override fun engineType(): String {
        return super.engineType()
    }

}