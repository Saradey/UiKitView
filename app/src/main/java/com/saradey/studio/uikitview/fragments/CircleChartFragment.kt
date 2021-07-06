package com.saradey.studio.uikitview.fragments

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.fragment.app.Fragment
import com.saradey.studio.circlechart.CircleChartView
import com.saradey.studio.uikitview.R

class CircleChartFragment : Fragment(R.layout.fragment_circle_chart) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val crcExample1 = requireView().findViewById<CircleChartView>(R.id.crcExample1)
        crcExample1.Builder()
            .addDataValue("Android", 100, Color.DKGRAY)
            .addDataValue("Ios", 100, Color.RED)
            .addDataValue("Fuchsia", 50, Color.GREEN)
            .addDataValue("Harmonia", 96, Color.BLUE)
            .create()

        val crcExample2 = requireView().findViewById<CircleChartView>(R.id.crcExample2)
        crcExample2.Builder()
            .addDataValue("Android", 100, Color.DKGRAY)
            .addDataValue("Ios", 100, Color.RED)
            .addDataValue("Fuchsia", 50, Color.GREEN)
            .addDataValue("Harmonia", 96, Color.BLUE)
            .create()

        val crcExample3 = requireView().findViewById<CircleChartView>(R.id.crcExample3)
        crcExample3.Builder()
            .addDataValue("Android", 100, Color.DKGRAY)
            .addDataValue("Ios", 100, Color.RED)
            .addDataValue("Fuchsia", 50, Color.GREEN)
            .addDataValue("Harmonia", 96, Color.BLUE)
            .create()

        val crcExample4 = requireView().findViewById<CircleChartView>(R.id.crcExample4)
        crcExample4.Builder()
            .addDataValue("Android", 100, Color.DKGRAY)
            .addDataValue("Ios", 100, Color.RED)
            .addDataValue("Fuchsia", 50, Color.GREEN)
            .addDataValue("Harmonia", 96, Color.BLUE)
            .create()

        val crcExample5 = requireView().findViewById<CircleChartView>(R.id.crcExample5)
        crcExample5.Builder()
            .addDataValue("Android", 100, Color.DKGRAY)
            .addDataValue("Ios", 100, Color.RED)
            .addDataValue("Fuchsia", 50, Color.GREEN)
            .addDataValue("Harmonia", 96, Color.BLUE)
            .create()

        val crcExample6 = requireView().findViewById<CircleChartView>(R.id.crcExample6)
        crcExample6.Builder()
            .addDataValue("Android", 100, Color.DKGRAY)
            .addDataValue("Ios", 100, Color.RED)
            .addDataValue("Fuchsia", 50, Color.GREEN)
            .addDataValue("Harmonia", 96, Color.BLUE)
            .create()

        val llcContent = requireView().findViewById<LinearLayoutCompat>(R.id.llcContent)
        llcContent.setOnClickListener {
            crcExample1.Builder()
                .addDataValue("Android", 100, Color.DKGRAY)
                .addDataValue("Ios", 100, Color.RED)
                .create()
        }

        val crcExample7 = requireView().findViewById<CircleChartView>(R.id.crcExample7)
        crcExample7.Builder()
            .addDataValue("Android", 100, Color.DKGRAY)
            .addDataValue("Ios", 100, Color.RED)
            .addDataValue("Fuchsia", 50, Color.GREEN)
            .addDataValue("Harmonia", 96, Color.BLUE)
            .create()

        val crcExample8 = requireView().findViewById<CircleChartView>(R.id.crcExample8)
        crcExample8.Builder()
            .addDataValue("Android", 100, Color.DKGRAY)
            .addDataValue("Ios", 100, Color.RED)
            .addDataValue("Fuchsia", 50, Color.GREEN)
            .addDataValue("Harmonia", 96, Color.BLUE)
            .create()

        val crcExample9 = requireView().findViewById<CircleChartView>(R.id.crcExample9)
        crcExample9.Builder()
            .addDataValue("Android", 100, Color.DKGRAY)
            .addDataValue("Ios", 100, Color.RED)
            .addDataValue("Fuchsia", 50, Color.GREEN)
            .addDataValue("Harmonia", 96, Color.BLUE)
            .create()
    }
}