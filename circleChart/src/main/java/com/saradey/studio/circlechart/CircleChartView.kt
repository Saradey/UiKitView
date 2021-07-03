package com.saradey.studio.circlechart

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import androidx.annotation.ColorInt
import kotlin.math.min

class CircleChartView : View {

    constructor(context: Context) : super(context)
    constructor(context: Context, attr: AttributeSet?) : super(context, attr) {
        initAttrs(attr)
    }

    constructor(context: Context, attr: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attr,
        defStyleAttr
    ) {
        initAttrs(attr)
    }

    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes) {
        initAttrs(attrs)
    }

    /** Пространство в котором будет отрисовываться круг */
    private val rectF = RectF()

    /** Модель которая содержит информацию для отрисовки */
    private val chartsInfo = mutableListOf<ChartInfo>()

    /** Кисточка для покраски и толщины */
    private val paint = Paint()

    /** Пустое расстояние между паями */
    private var arcPieSpacePixels = 0

    /** Толщина наших паев */
    private var arcPieStrokeSize = 0f

    /** Закрашивается круг или нет */
    private var arcPieMode = 0

    /** Флаг который позволяет закрашивать круг из центра */
    private var useCenter = false

    init {
        paint.color = Color.TRANSPARENT
        paint.isAntiAlias = true
    }

    private fun initAttrs(attrs: AttributeSet?) {
        val typed = context.obtainStyledAttributes(attrs, R.styleable.CircleChartView)
        arcPieSpacePixels = typed.getInt(R.styleable.CircleChartView_arcPieSpacePixels, 0)
        arcPieStrokeSize = typed.getFloat(R.styleable.CircleChartView_arcPieStrokeSize, 10f)
        arcPieMode = typed.getInt(R.styleable.CircleChartView_arcPieMode, 0)
        typed.recycle()
    }

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
        height =
            if (heightMode == MeasureSpec.AT_MOST || (heightMode == MeasureSpec.UNSPECIFIED && heightSize == 0)) {
                resources.getDimensionPixelSize(R.dimen.min_chart_size)
            } else {
                heightSize
            }
        setMeasuredDimension(width, height)
        initValues(width.toFloat(), height.toFloat())
    }

    private fun initValues(width: Float, height: Float) {
        paint.strokeWidth = arcPieStrokeSize
        rectF.set(
            0f + arcPieStrokeSize / 2,
            0f + arcPieStrokeSize / 2,
            width - arcPieStrokeSize / 2,
            height - arcPieStrokeSize / 2
        )
        paint.style = if (arcPieMode == ARC_MODE_STROKE)
            Paint.Style.STROKE
        else Paint.Style.FILL
        useCenter = arcPieMode == ARC_MODE_FILL
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawColor(Color.TRANSPARENT)
        chartsInfo.forEach { model ->
            paint.color = model.chartColor
            canvas.drawArc(rectF, model.startAngle, model.endAngle, useCenter, paint)
        }
        super.onDraw(canvas)
    }

    inner class Builder {

        fun addDataValue(
            chartName: String,
            data: Int,
            @ColorInt arcColor: Int
        ): Builder {
            chartsInfo.add(
                ChartInfo(
                    chartName = chartName,
                    chartValue = data,
                    chartColor = arcColor
                )
            )
            return this
        }

        fun create() {
            val totalScore = chartsInfo.sumOf {
                it.chartValue
            }
            chartsInfo.forEachIndexed { index, model ->
                model.currentAngle = (model.chartValue.toFloat() / totalScore.toFloat()) * 360f
                if (index > 0) {
                    model.startAngle =
                        chartsInfo[index - 1].startAngle + chartsInfo[index - 1].currentAngle
                }
                model.endAngle = model.currentAngle - arcPieSpacePixels
            }
            requestLayout()
        }
    }

    companion object {

        private const val ARC_MODE_STROKE = 0

        private const val ARC_MODE_FILL = 1
    }
}