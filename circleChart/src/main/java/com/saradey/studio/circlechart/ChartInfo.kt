package com.saradey.studio.circlechart

import androidx.annotation.ColorInt

data class ChartInfo(
    val chartName: String,
    val chartValue: Int,
    val startAngle: Float,
    val currentAngle: Float,
    val endAngle: Float,
    @ColorInt val chartColor: Int
)
