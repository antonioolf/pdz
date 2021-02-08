package com.oliveiralabs.pdz.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.oliveiralabs.pdz.R
import com.oliveiralabs.pdz.others.RepoMapper

class FormulasActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formulas)

        val group = intent.getStringExtra("group")

        Toast.makeText(this, group, Toast.LENGTH_SHORT).show()

        val mapping = RepoMapper.getMapping()
        print(mapping)
    }
}