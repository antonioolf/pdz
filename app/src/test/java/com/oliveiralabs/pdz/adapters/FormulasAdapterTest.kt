package com.oliveiralabs.pdz.adapters

import android.app.Activity
import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ApplicationProvider
import com.oliveiralabs.pdz.R
import com.oliveiralabs.pdz.models.Formula
import org.junit.Test

class FormulasAdapterTest {
    private val context: Context = ApplicationProvider.getApplicationContext()

    @Test
    fun `adapter works correctly`() {
        val formulas = arrayListOf(Formula("", "", ""))

        val formulasAdapter = FormulasAdapter(formulas)
        val rvFormulas = (context as Activity).findViewById<RecyclerView>(R.id.rvFormulas)
        rvFormulas.adapter = formulasAdapter
        rvFormulas.layoutManager = LinearLayoutManager(context)
    }
}