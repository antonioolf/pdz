package com.oliveiralabs.pdz.others

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.oliveiralabs.pdz.R
import com.oliveiralabs.pdz.adapters.FormulasAdapter
import com.oliveiralabs.pdz.models.Formula

class FormulasDialog :DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val activity = requireActivity()
        val inflater = activity.layoutInflater
        val view = inflater.inflate(R.layout.formulas_dialog, null)

        val alertDialog = activity.let {
            val builder = AlertDialog.Builder(it)


            builder.setView(view)
                .setNegativeButton(R.string.cancel) { _, _ ->
                    dialog?.cancel()
                }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")

        val mapping = RepoMapper.getMapping()
        val formulas = mapping[tag]

        val formulasAdapter = FormulasAdapter(formulas as ArrayList<Formula>)
        val rvFormulas = view.findViewById<RecyclerView>(R.id.rvFormulas)
        rvFormulas.adapter = formulasAdapter
        rvFormulas.layoutManager = LinearLayoutManager(view.context)

        return alertDialog
    }
}