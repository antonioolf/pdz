package com.oliveiralabs.pdz.others

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.oliveiralabs.pdz.R


class NewRepoDialog :DialogFragment() {

    private lateinit var listener: NewRepoDialogListener

    interface NewRepoDialogListener {
        fun onDialogPositiveClick(dialog: AlertDialog, username: String, repository: String)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            listener = context as NewRepoDialogListener
        } catch (e: ClassCastException) {
            throw ClassCastException(("$context must implement NoticeDialogListener"))
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            val inflater = requireActivity().layoutInflater

            builder.setView(inflater.inflate(R.layout.new_repo_dialog, null))
                .setTitle(getString(R.string.new_repo))
                .setPositiveButton(R.string.ok) { _, _ -> }
                .setNegativeButton(R.string.cancel) { _, _ ->
                    dialog?.cancel()
                }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    override fun onResume() {
        super.onResume()
        val d = dialog as AlertDialog
        val positiveButton: Button = d.getButton(Dialog.BUTTON_POSITIVE) as Button
        positiveButton.setOnClickListener {

            val username = (d.findViewById<View>(R.id.etUsername) as EditText).text.toString()
            val repository = (d.findViewById<View>(R.id.etRepository) as EditText).text.toString()

            if (validateInput(username, d.context) && validateInput(repository, d.context)) {
                listener.onDialogPositiveClick(d, username, repository)
            }
        }
    }

    private fun validateInput(input: String, ctx :Context) :Boolean {
        return if (input.isEmpty()) {
            Toast.makeText(ctx, getString(R.string.fill_all_inputs), Toast.LENGTH_SHORT).show()
            false
        } else {
            true
        }
    }
}