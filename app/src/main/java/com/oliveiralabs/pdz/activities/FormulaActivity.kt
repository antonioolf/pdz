package com.oliveiralabs.pdz.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.oliveiralabs.pdz.R

class FormulaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formula)

        findViewById<TextView>(R.id.testCommandFormula).text = intent.getStringExtra("command")
    }
}