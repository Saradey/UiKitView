package com.saradey.studio.uikitview.fragments.main.list

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.RecyclerView
import com.saradey.studio.uikitview.R
import com.saradey.studio.uikitview.models.KeyCostumeView
import com.saradey.studio.uikitview.models.MainListModel

class MainListAdapter(
    private val clickListener: (KeyCostumeView) -> Unit
) : RecyclerView.Adapter<MainListAdapter.MainListHolder>() {

    var models = listOf<MainListModel>()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MainListHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.holder_main_list, parent, false)
        )

    override fun onBindViewHolder(holder: MainListHolder, position: Int) {
        holder.bind(models[position])
    }

    override fun getItemCount() = models.size

    inner class MainListHolder(
        view: View
    ) : RecyclerView.ViewHolder(view) {

        fun bind(model: MainListModel) {
            (itemView as AppCompatButton).text = model.title
            itemView.setOnClickListener {
                clickListener(model.key)
            }
        }
    }
}