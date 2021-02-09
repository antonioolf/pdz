package com.oliveiralabs.pdz.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.oliveiralabs.pdz.R
import com.oliveiralabs.pdz.others.FormulasDialog

class GroupAdapter(private val items: ArrayList<String>) : RecyclerView.Adapter<GroupAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvGroupName :TextView = view.findViewById(R.id.tvItemName)

        init {
            view.setOnClickListener {
                tvGroupName.text
                FormulasDialog()
                    .show((view.context as FragmentActivity).supportFragmentManager, tvGroupName.text.toString())
            }
        }
    }

    fun update(groups: List<String>) {
        items.clear()
        items.addAll(groups)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.tvGroupName.text = item
    }

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
    )
}