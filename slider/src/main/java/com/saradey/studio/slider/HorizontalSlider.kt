package com.saradey.studio.slider

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.View
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat

class HorizontalSlider : View {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        val obtain = context.obtainStyledAttributes(attrs, R.styleable.HorizontalSlider)
        max = obtain.getInt(R.styleable.HorizontalSlider_hs_maxValue, max)
        progress = obtain.getInt(R.styleable.HorizontalSlider_hs_progressValue, progress)
        iconHead = gitBitmapFromVectorDrawable(
            obtain.getResourceId(R.styleable.HorizontalSlider_hs_iconHead, NOTHING_DRAWABLE_RES)
        )
        cornerRadius = obtain.getDimension(
            R.styleable.HorizontalSlider_hs_cornerRadius,
            NOTHING_CORNER_RADIUS
        )
        obtain.recycle()
    }

    private fun validateValues() {

    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

    }

    private var max = 10
    private var progress = 3
    var listener: HorizontalSliderProgressListener? = null
    var iconHead: Bitmap? = null
    var cornerRadius = 0f


    interface HorizontalSliderProgressListener {
        fun onChanged(progress: Int, max: Int)
    }

    private fun gitBitmapFromVectorDrawable(@DrawableRes drawableId: Int): Bitmap? {
        val drawable = ContextCompat.getDrawable(context, drawableId)
        drawable ?: return null
        val bitmap = Bitmap.createBitmap(
            drawable.intrinsicWidth,
            drawable.intrinsicHeight,
            Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        drawable.setBounds(0, 0, canvas.width, canvas.height)
        drawable.draw(canvas)
        return bitmap
    }

    companion object {

        private const val NOTHING_DRAWABLE_RES = -1

        private const val NOTHING_CORNER_RADIUS = 0f
    }
}