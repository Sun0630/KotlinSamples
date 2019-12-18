package com.sx.kotlinfirst.chapter9.create

/**
 * @author sunxin
 * @date 2019-12-18 10:50
 * @desc Builder 模式
 */
class Robot private constructor(
    val code: String,
    val battery: String?,
    val height: Int?,
    val weight: Int?
) {
    // 嵌套类，负责创建Robot
    class Builder(val code: String) {
        private var battery: String? = null
        private var height: Int? = null
        private var weight: Int? = null


        fun setBattery(battery: String?): Builder {
            this.battery = battery
            return this
        }


        fun setHeight(height: Int?): Builder {
            this.height = height
            return this
        }

        fun setWeight(weight: Int?): Builder {
            this.weight = weight
            return this
        }

        fun build(): Robot {
            // 添加约束条件
            if (weight != null && battery == null) {
                throw IllegalArgumentException("Battery should determined when setting weight.")
            }
            return Robot(
                code,
                battery,
                height,
                weight
            )
        }

    }

}


// 替代Builder，使用具名的可选参数
class RobotA(
    val code: String,
    val battery: String? = null,
    val height: Int? = null,
    val weight: Int? = null
) {
    init {
        // 添加以上的校验代码，使用require 关键字进行函数参数限制
        require(weight == null || battery != null) {
            throw IllegalArgumentException("Battery should determined when setting weight.")
        }
    }
}


fun main() {
    // 调用
    val robot = Robot.Builder("007")
        .setBattery("R6")
        .setHeight(100)
        .setWeight(78)
        .build()

    //具名的可选参数
    RobotA(
        code = "008",
        battery = "A8",
        height = 232,
        weight = 12
    )
    RobotA(code = "010")
    RobotA(code = "110", battery = "V8")


}