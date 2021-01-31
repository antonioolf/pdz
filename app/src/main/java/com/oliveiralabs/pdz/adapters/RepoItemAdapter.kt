package com.oliveiralabs.pdz.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.oliveiralabs.pdz.R
import com.oliveiralabs.pdz.models.RepoItem

class RepoItemAdapter(private val items: ArrayList<RepoItem>) : RecyclerView.Adapter<RepoItemAdapter.ViewHolder>() {
    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val name :TextView = view.findViewById(R.id.name)

        fun bind(repoItem: RepoItem) {
            name.text = repoItem.name
        }
    }

    fun update(repoItems: List<RepoItem>) {
        items.clear()
        items.addAll(repoItems)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.repo_item, parent, false)
    )
}