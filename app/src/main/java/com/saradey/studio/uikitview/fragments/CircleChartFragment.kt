package com.saradey.studio.uikitview.fragments

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.saradey.studio.circlechart.CircleChartView
import com.saradey.studio.uikitview.R

class CircleChartFragment : Fragment(R.layout.fragment_circle_chart) {

    private lateinit var crcExample1: CircleChartView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        crcExample1 = requireView().findViewById(R.id.crcExample1)
        crcExample1.Builder()
            .addDataValue("Android", 100, Color.DKGRAY)
            .addDataValue("Ios", 100, Color.RED)
            .addDataValue("Fuchsia", 50, Color.GREEN)
            .addDataValue("Harmonia", 96, Color.BLUE)
            .create()
    }
}