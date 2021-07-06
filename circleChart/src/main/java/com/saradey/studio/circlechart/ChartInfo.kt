package com.saradey.studio.circlechart

import androidx.annotation.ColorInt

data class ChartInfo(
    val chartName: String,
    val chartValue: Int,
    var startAngle: Float = 0f,
    var currentAngle: Float = 0f,
    var endAngle: Float = 0f,
    @ColorInt val chartColor: Int
)