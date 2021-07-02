package com.saradey.studio.uikitview.fragments.main.list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.recyclerview.widget.RecyclerView
import com.saradey.studio.uikitview.R
import com.saradey.studio.uikitview.fragments.CircleChartFragment
import com.saradey.studio.uikitview.models.KeyCostumeView
import com.saradey.studio.uikitview.models.MainListModel

class MainListFragment : Fragment(R.layout.fragment_main_list) {

    private lateinit var rcvListCostumeView: RecyclerView
    private val mainListAdapter = MainListAdapter(::clickHolderListener)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        rcvListCostumeView = requireView() as RecyclerView
        rcvListCostumeView.adapter = mainListAdapter
        mainListAdapter.models = generateCostumeListView()
    }

    private fun generateCostumeListView() = listOf(
        MainListModel(
            title = "Circle Chart",
            key = KeyCostumeView.CircleChart
        )
    )

    private fun clickHolderListener(key: KeyCostumeView) {
        when (key) {
            KeyCostumeView.CircleChart -> goToTheCircleChart()
        }
    }

    private fun goToTheCircleChart() {
        parentFragmentManager.commit {
            replace(R.id.rcvFieldContent, CircleChartFragment())
            addToBackStack(CircleChartFragment::class.java.canonicalName)
        }
    }
}