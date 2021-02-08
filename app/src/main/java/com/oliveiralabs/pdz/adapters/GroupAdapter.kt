package com.oliveiralabs.pdz.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.oliveiralabs.pdz.R
import com.oliveiralabs.pdz.activities.FormulasActivity

class GroupAdapter(private val items: ArrayList<String>) : RecyclerView.Adapter<GroupAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvGroupName :TextView = view.findViewById(R.id.tvGroupName)

        init {
            view.setOnClickListener {

                view.context.startActivity(Intent(view.context, FormulasActivity::class.java)
                    .putExtra("group", tvGroupName.text)
                )
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
        LayoutInflater.from(parent.context).inflate(R.layout.group_item, parent, false)
    )
}