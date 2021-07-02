package com.saradey.studio.circlechart

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View

class CircleChart : View {

    constructor(context: Context) : super(context)
    constructor(context: Context, attr: AttributeSet?) : super(context, attr)
    constructor(context: Context, attr: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attr,
        defStyleAttr
    )

    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes)

    private val rectF = RectF()

    private val chartsInfo = mutableListOf<ChartInfo>()

    private val paint = Paint()

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)
        var width = 0
        var height = 0
        width = if (widthMode == MeasureSpec.AT_MOST) {
            resources.getDimensionPixelSize(R.dimen.min_chart_size)
        } else {
            widthSize
        }

        height = if (heightMode == MeasureSpec.AT_MOST) {
            resources.getDimensionPixelSize(R.dimen.min_chart_size)
        } else {
            heightSize
        }
        setMeasuredDimension(width, height)
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawColor(Color.RED)

        super.onDraw(canvas)
    }

    companion object {

        private const val DEFAULT_MAX_SWEEP_ANGLE = 75

        private const val DEFAULT_ARC_SPACE = 3
    }
}