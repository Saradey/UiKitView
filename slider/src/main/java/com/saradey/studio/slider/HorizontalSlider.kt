package com.saradey.studio.slider

import android.content.Context
import android.graphics.Bitmap
import android.util.AttributeSet
import android.view.View

class HorizontalSlider : View {

    constructor(context: Context) : super(context)
    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet) {

    }

    private var max = 10
    private var progress = 3
    var listener: HorizontalSliderProgressListener? = null
    var iconHead: Bitmap? = null
    var cornerRadius = dpToPx(DEFAULT_CORNER_DP_RADIUS).toFloat()


    interface HorizontalSliderProgressListener {
        fun onChanged(progress: Int, max: Int)
    }

    private fun dpToPx(dp: Int) = (dp * resources.displayMetrics.density).toInt()

    companion object {

        private const val DEFAULT_CORNER_DP_RADIUS = 5
    }
}