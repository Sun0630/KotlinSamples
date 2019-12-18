package com.sx.kotlinfirst.chapter3

import android.content.Context
import android.util.AttributeSet
import android.view.View

/**
 * @author sunxin
 * @date 2019-12-11 00:20
 * @desc
 */
class KotlinView : View {

    // 从构造函数
    constructor(context: Context?) : super(context)

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )
}