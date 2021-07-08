package com.saradey.studio.circlechart

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import androidx.annotation.ColorInt
import androidx.core.content.ContextCompat

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
    private var arcPieSpacePixels = 0f

    /** Толщина наших паев */
    private var arcPieStrokeSize = 0f

    /** Закрашивается круг или нет */
    private var arcPieMode = 0

    /** Флаг который позволяет закрашивать круг из центра */
    private var useCenter = false

    /** Будет элевейшен или нет */
    private var arcPieElevation = false

    /** Размер elevation для круга */
    private var arcPieElevationF = 0f

    /** Для отрисовки elevation */
    private val paintElevation = Paint()

    /** Для внутренней отрисовки elevation */
    private var rectInsideElevation = RectF()

    /** Для внешней отрисовки elevation */
    private var rectOutsideElevation = RectF()

    /** Радиус круга в пикселях */
    private var radius = 0f

    init {
        paint.color = Color.TRANSPARENT
        paint.isAntiAlias = true
    }

    private fun initAttrs(attrs: AttributeSet?) {
        val typed = context.obtainStyledAttributes(attrs, R.styleable.CircleChartView)
        arcPieSpacePixels = typed.getDimension(R.styleable.CircleChartView_arcPieSpacePixels, 0f)
        arcPieStrokeSize = typed.getDimension(R.styleable.CircleChartView_arcPieStrokeSize, 10f)
        arcPieMode = typed.getInt(R.styleable.CircleChartView_arcPieMode, 0)
        arcPieElevation = typed.getBoolean(R.styleable.CircleChartView_arcPieElevation, false)
        typed.recycle()
        validateValues()
    }

    private fun validateValues() {
        if (arcPieElevation) {
            arcPieElevationF =
                resources.getDimensionPixelSize(R.dimen.max_size_elevation_circle).toFloat()
            paintElevation.isAntiAlias = true
            paintElevation.strokeWidth = arcPieElevationF
            paintElevation.style = Paint.Style.STROKE
            paintElevation.color = ContextCompat.getColor(
                context,
                R.color.elevation_color
            )
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)
        var width = 0
        var height = 0
        val dimensionMinSize = resources.getDimensionPixelSize(R.dimen.min_chart_size)
        //если у нас выставлено wrap_content для ширины
        //или у нас widthSize меньше минимальной ширины
        //то мы делаем ширины по умолчанию, иначе мы делаем ширины
        //как задано в dp
        width = if (widthMode == MeasureSpec.AT_MOST ||
            dimensionMinSize > widthSize
        ) {
            dimensionMinSize
        } else {
            widthSize
        }
        //если у нас выставлено wrap_content для высоты
        //или у нас heightSize меньше минимальной высоты
        //то мы делаем высоту по умолчанию, иначе мы делаем высоту
        //как задано в dp
        height = if (heightMode == MeasureSpec.AT_MOST ||
            dimensionMinSize > heightSize
        ) {
            dimensionMinSize
        } else {
            heightSize
        }
        //если размеры по ширине и высоте не пропорциональны
        //то мы делаем их пропорциональными то есть квадрат
        if (width > height) {
            height = width
        } else {
            width = height
        }
        setMeasuredDimension(width, height)
        initValues(width.toFloat(), height.toFloat())
    }

    private fun initValues(width: Float, height: Float) {
        paint.strokeWidth = arcPieStrokeSize
        //rectF это rect в пределах котрого будет отрисовываться круг
        //arcPieStrokeSize / 2 это нужно что бы не выходить за рамки нашего view
        rectF.set(
            0f + arcPieStrokeSize / 2 + arcPieElevationF,
            0f + arcPieStrokeSize / 2 + arcPieElevationF,
            width - arcPieStrokeSize / 2 - arcPieElevationF,
            height - arcPieStrokeSize / 2 - arcPieElevationF
        )
        //вычисляем координаты отрисовки elevation для внутреннего и внешнего
        //круга
        if (arcPieElevation) {
            rectInsideElevation.set(
                0f + arcPieStrokeSize + arcPieElevationF,
                0f + arcPieStrokeSize + arcPieElevationF,
                width - arcPieStrokeSize - arcPieElevationF,
                height - arcPieStrokeSize - arcPieElevationF
            )
            rectOutsideElevation.set(
                0f + arcPieElevationF,
                0f + arcPieElevationF,
                width - arcPieElevationF,
                height - arcPieElevationF
            )
        }
        //заливка или линии
        paint.style = if (arcPieMode == ARC_MODE_STROKE)
            Paint.Style.STROKE
        else Paint.Style.FILL
        //заливка или линии
        useCenter = arcPieMode == ARC_MODE_FILL
        radius = width / 2 - arcPieElevationF
    }

    override fun onDraw(canvas: Canvas) {
        drawPieChats(canvas)
        if (arcPieElevation)
            drawElevationCircle(canvas)
    }

    private fun drawElevationCircle(canvas: Canvas) {
        chartsInfo.forEach { model ->
            if (arcPieMode == ARC_MODE_STROKE) {
                canvas.drawArc(
                    rectInsideElevation,
                    model.startAngle,
                    model.endAngle,
                    useCenter,
                    paintElevation
                )
            }
            canvas.drawArc(
                rectOutsideElevation,
                model.startAngle,
                model.endAngle,
                useCenter,
                paintElevation
            )
        }
    }

    private fun drawPieChats(canvas: Canvas) {
        canvas.drawColor(Color.TRANSPARENT)
        chartsInfo.forEach { model ->
            paint.color = model.chartColor
            canvas.drawArc(rectF, model.startAngle, model.endAngle, useCenter, paint)
        }
    }

    inner class Builder {

        init {
            //заново формируем список
            chartsInfo.clear()
        }

        fun addDataValue(
            chartName: String,
            chartValue: Int,
            @ColorInt chartColor: Int
        ): Builder {
            chartsInfo.add(
                ChartInfo(
                    chartName = chartName,
                    chartValue = chartValue,
                    chartColor = chartColor
                )
            )
            return this
        }

        fun create() {
            //сумма всех chartValue
            val totalScore = chartsInfo.sumOf {
                it.chartValue
            }
            chartsInfo.forEachIndexed { index, model ->
                //размер пая на круге
                model.currentAngle = (model.chartValue.toFloat() / totalScore.toFloat()) * 360f
                //если у нас первый элемент, он отрисовывается с 0 градуса
                if (index > 0) {
                    //каждый следующий пай отрисовывается в зависимости от последнего
                    model.startAngle =
                        chartsInfo[index - 1].startAngle + chartsInfo[index - 1].currentAngle
                }
                //где пай заканчивается с учетом пространства между паями
                model.endAngle = model.currentAngle - arcPieSpacePixels
                invalidate()
            }
        }
    }

    companion object {

        private const val ARC_MODE_STROKE = 0

        private const val ARC_MODE_FILL = 1
    }
}