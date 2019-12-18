package com.sx.kotlinfirst.chapter4

/**
 * @author sunxin
 * @date 2019-12-11 22:44
 * @desc 优惠券demo 代数数据类型与模式匹配
 */

// 优惠券
sealed class Coupon {


    companion object {
        // 优惠券类型
        // 现金券
        val CashType = "CASH"
        // 折扣券
        val DiscountType = "DISCOUNT"
        // 礼品券
        val GiftType = "GIFT"

        /////////////////////////////
        // 优惠券状态
//        // 未领取
//        val NotFetched = 1
//        // 已领取但未使用
//        val Fetched = 2
//        // 已使用
//        val Used = 3
//        // 已过期
//        val Expired = 4
//        // 已失效
//        val UnAvilable = 5
    }





    class CashCoupon(val id: Long, val type: String, val leastCost: Long, val reduceCost: Long) :
        Coupon()


    class DiscountCoupon(val id: Long, val type: String, val discount: Int) : Coupon()


    class GiftCoupon(val id: Long, val type: String, val gift: String) : Coupon()


}

// 用户
data class User(val name: String)

// 优惠券状态
// 将 Coupon 与 User 结合到状态里面来
sealed class CouponStatus {
    data class StatusNotFetched(val coupon: Coupon) : CouponStatus()
    data class StatusFetched(val coupon: Coupon, val user: User) : CouponStatus()
    data class StatusUsed(val coupon: Coupon, val user: User) : CouponStatus()
    data class StatusExpired(val coupon: Coupon) : CouponStatus()
    data class StatusUnAvilable(val coupon: Coupon) : CouponStatus()
}


fun main() {


}

fun fetched(c: Coupon, user: User): Boolean = true

fun used(c: Coupon, user: User): Boolean = true

fun isExpired(c: Coupon): Boolean = true

fun isUnAvailable(c: Coupon): Boolean = false


fun showUnAvilable(): Unit {

}

fun showExpired(): Unit {

}

fun showUsed() {}

fun showFetched() {}

fun showNotFetched() {}


fun getCouponStatus(coupon: Coupon, user: User): CouponStatus = when {
    isUnAvailable(coupon) -> CouponStatus.StatusUnAvilable(coupon)
    fetched(coupon, user) -> CouponStatus.StatusFetched(coupon, user)
    used(coupon, user) -> CouponStatus.StatusUsed(coupon, user)
    isExpired(coupon) -> CouponStatus.StatusExpired(coupon)

    else -> CouponStatus.StatusNotFetched(coupon)
}


fun showCouponStatus(status: CouponStatus) = when (status) {
    is CouponStatus.StatusNotFetched -> showNotFetched()
    is CouponStatus.StatusFetched -> showFetched()
    is CouponStatus.StatusUsed -> showUsed()
    is CouponStatus.StatusExpired -> showExpired()
    is CouponStatus.StatusUnAvilable -> showUnAvilable()
}
