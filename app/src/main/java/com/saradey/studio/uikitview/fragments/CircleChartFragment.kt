package com.saradey.studio.uikitview.fragments

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.saradey.studio.circlechart.CircleChartView
import com.saradey.studio.uikitview.R

class CircleChartFragment : Fragment(R.layout.fragment_circle_chart) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val colorTest1 = ContextCompat.getColor(requireContext(), R.color.test_color_1)
        val colorTest2 = ContextCompat.getColor(requireContext(), R.color.test_color_2)
        val colorTest3 = ContextCompat.getColor(requireContext(), R.color.test_color_3)
        val colorTest4 = ContextCompat.getColor(requireContext(), R.color.test_color_4)

        val crcExample1 = requireView().findViewById<CircleChartView>(R.id.crcExample1)
        crcExample1.Builder()
            .addDataValue("Android", 100, colorTest1)
            .addDataValue("Ios", 100, colorTest2)
            .addDataValue("Fuchsia", 50, colorTest3)
            .addDataValue("Harmonia", 96, colorTest4)
            .create()

        val crcExample2 = requireView().findViewById<CircleChartView>(R.id.crcExample2)
        crcExample2.Builder()
            .addDataValue("Android", 100, colorTest1)
            .addDataValue("Ios", 100, colorTest2)
            .addDataValue("Fuchsia", 50, colorTest3)
            .addDataValue("Harmonia", 96, colorTest4)
            .create()

        val crcExample3 = requireView().findViewById<CircleChartView>(R.id.crcExample3)
        crcExample3.Builder()
            .addDataValue("Android", 100, colorTest1)
            .addDataValue("Ios", 100, colorTest2)
            .addDataValue("Fuchsia", 50, colorTest3)
            .addDataValue("Harmonia", 96, colorTest4)
            .create()

        val crcExample4 = requireView().findViewById<CircleChartView>(R.id.crcExample4)
        crcExample4.Builder()
            .addDataValue("Android", 100, colorTest1)
            .addDataValue("Ios", 100, colorTest2)
            .addDataValue("Fuchsia", 50, colorTest3)
            .addDataValue("Harmonia", 96, colorTest4)
            .create()

        val crcExample5 = requireView().findViewById<CircleChartView>(R.id.crcExample5)
        crcExample5.Builder()
            .addDataValue("Android", 100, colorTest1)
            .addDataValue("Ios", 100, colorTest2)
            .addDataValue("Fuchsia", 50, colorTest3)
            .addDataValue("Harmonia", 96, colorTest4)
            .create()

        val crcExample6 = requireView().findViewById<CircleChartView>(R.id.crcExample6)
        crcExample6.Builder()
            .addDataValue("Android", 100, colorTest1)
            .addDataValue("Ios", 100, colorTest2)
            .addDataValue("Fuchsia", 50, colorTest3)
            .addDataValue("Harmonia", 96, colorTest4)
            .create()

        val llcContent = requireView().findViewById<LinearLayoutCompat>(R.id.llcContent)
        llcContent.setOnClickListener {
            crcExample1.Builder()
                .addDataValue("Android", 100, colorTest1)
                .addDataValue("Ios", 100, colorTest2)
                .create()
        }

        val crcExample7 = requireView().findViewById<CircleChartView>(R.id.crcExample7)
        crcExample7.Builder()
            .addDataValue("Android", 100, colorTest1)
            .addDataValue("Ios", 100, colorTest2)
            .addDataValue("Fuchsia", 50, colorTest3)
            .addDataValue("Harmonia", 96, colorTest4)
            .create()

        val crcExample8 = requireView().findViewById<CircleChartView>(R.id.crcExample8)
        crcExample8.Builder()
            .addDataValue("Android", 100, colorTest1)
            .addDataValue("Ios", 100, colorTest2)
            .addDataValue("Fuchsia", 50, colorTest3)
            .addDataValue("Harmonia", 96, colorTest4)
            .create()

        val crcExample9 = requireView().findViewById<CircleChartView>(R.id.crcExample9)
        crcExample9.Builder()
            .addDataValue("Android", 100, colorTest1)
            .addDataValue("Ios", 100, colorTest2)
            .addDataValue("Fuchsia", 50, colorTest3)
            .addDataValue("Harmonia", 96, colorTest4)
            .create()

        val crcExample10 = requireView().findViewById<CircleChartView>(R.id.crcExample10)
        crcExample10.Builder()
            .addDataValue("Android", 100, colorTest1)
            .addDataValue("Ios", 100, colorTest2)
            .addDataValue("Fuchsia", 50, colorTest3)
            .addDataValue("Harmonia", 96, colorTest4)
            .create()
    }
}