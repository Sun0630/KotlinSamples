package com.sx.kotlinfirst.chapter9.behavior

/**
 * @author sunxin
 * @date 2019-12-18 21:06
 * @desc 状态模式：允许一个对象在其内部状态改变时改变它的的行为，对象看起来似乎修改了它的类
 */


/**
 * 状态决定行为，对象的行为由他内部的状态决定
 * 对象的状态在运行期被改变时，它的行为也会因此而改变
 *
 * 需求:饮水机
 *      工作状态：1. 未启动 2. 制冷模式  3. 制热模式
 */

// 饮水机
class WaterMachine {

    var state: WaterMachineState
    val off = Off(this)
    val heating = Heating(this)
    val cooling = Cooling(this)

    init {
        // 默认初始值为关闭
        state = off
    }


    fun turnHeating() {
        this.state.turnHeating()
    }

    fun turnCooling() {
        this.state.turnCooling()
    }

    fun turnOff() {
        this.state.turnOff()
    }

}

// 饮水机3种不同的状态
class Off(override val machine: WaterMachine) : WaterMachineState(machine)

class Heating(override val machine: WaterMachine) : WaterMachineState(machine)

class Cooling(override val machine: WaterMachine) : WaterMachineState(machine)


//饮水机状态密封类
sealed class WaterMachineState(open val machine: WaterMachine) {

    fun turnHeating() {
        if (this !is Heating) {
            println("turn Heating")
            machine.state = machine.heating
        }
    }


    fun turnCooling() {
        if (this !is Cooling) {
            println("turn Cooling")
            machine.state = machine.cooling
        }
    }

    fun turnOff() {
        if (this !is Off) {
            println("turn Off")
            machine.state = machine.off
        }
    }

}



enum class Moment {
    EARLY_MORNING,// 早上上班
    DRINKING_WATER,// 日常饮水
    INSTANCE_NOODLES,// 泡面时间
    AFTER_WORK// 下班
}


// 不通角色在不通场景下，对饮水机执行不同的操作
fun waterMachineOps(machine: WaterMachine, moment: Moment) {
    when (moment) {
        Moment.EARLY_MORNING,
        Moment.DRINKING_WATER -> when (machine.state) {
            !is Cooling -> machine.turnCooling()
        }
        Moment.INSTANCE_NOODLES -> when (machine.state) {
            !is Heating -> machine.turnHeating()
        }
        Moment.AFTER_WORK -> when (machine.state) {
            !is Off -> machine.turnOff()
        }
    }
}


fun main() {

    val machine = WaterMachine()
    waterMachineOps(machine,Moment.EARLY_MORNING)
    waterMachineOps(machine,Moment.INSTANCE_NOODLES)
    waterMachineOps(machine,Moment.DRINKING_WATER)
    waterMachineOps(machine,Moment.AFTER_WORK)

}