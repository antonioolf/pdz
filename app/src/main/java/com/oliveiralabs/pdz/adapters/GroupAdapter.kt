package com.oliveiralabs.pdz.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.oliveiralabs.pdz.R

class GroupAdapter(private val items: ArrayList<String>) : RecyclerView.Adapter<GroupAdapter.ViewHolder>() {
    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val name :TextView = view.findViewById(R.id.name)

        fun bind(group: String) {
            name.text = group
        }
    }

    fun update(groups: List<String>) {
        items.clear()
        items.addAll(groups)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.group_item, parent, false)
    )
}