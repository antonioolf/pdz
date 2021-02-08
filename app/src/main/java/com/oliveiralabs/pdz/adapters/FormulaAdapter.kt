package com.oliveiralabs.pdz.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.oliveiralabs.pdz.R
import com.oliveiralabs.pdz.models.Formula

class FormulaAdapter(private val items: ArrayList<Formula>) : RecyclerView.Adapter<FormulaAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var command :TextView = view.findViewById(R.id.tvGroupName)
        var readme :TextView = view.findViewById(R.id.tvGroupName)
        var shortDescription :TextView = view.findViewById(R.id.tvGroupName)

        init {
            view.setOnClickListener {
                print("hi")
                /*view.context.startActivity(Intent(view.context, FormulasActivity::class.java)
                .putExtra("group", name.text))*/
            }
        }
    }

    fun update(formulas: List<Formula>) {
        items.clear()
        items.addAll(formulas)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.command.text = item.command
    }

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.group_item, parent, false)
    )
}