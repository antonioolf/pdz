package com.oliveiralabs.pdz.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.oliveiralabs.pdz.R
import com.oliveiralabs.pdz.activities.FormulaActivity
import com.oliveiralabs.pdz.models.Formula

class FormulasAdapter(private val items: ArrayList<Formula>) : RecyclerView.Adapter<FormulasAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var command :TextView = view.findViewById(R.id.tvGroupName)
        lateinit var formula :Formula

        init {
            view.setOnClickListener {

                val intent = Intent(command.context, FormulaActivity::class.java).apply {
                    putExtra("command", formula.command)
                    putExtra("readmeURL", formula.readmeURL)
                    putExtra("helpJsonURL", formula.helpJsonURL)
                }

                command.context.startActivity(intent)
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
        holder.formula = item
        holder.command.text = item.command
    }

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.group_item, parent, false)
    )
}