package com.oliveiralabs.pdz.activities

import android.os.Bundle
import android.util.Base64
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.oliveiralabs.pdz.R
import com.oliveiralabs.pdz.others.RequestQueueSingleton
import io.noties.markwon.Markwon
import org.json.JSONObject

class FormulaActivity : AppCompatActivity() {
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formula)

        val readmeURL = intent.getStringExtra("readmeURL").toString()
        val command = intent.getStringExtra("command").toString()
        val ritCommand = "rit ${command.split("/").joinToString(" ") }"
        findViewById<TextView>(R.id.tvFormulaCommand).text = ritCommand
        /*intent.getStringExtra("helpJsonURL")*/

        loadMarkdown(readmeURL)
    }

    private fun loadMarkdown(readmeURL :String) {
        val pbFormula = findViewById<ProgressBar>(R.id.pbFormula)
        pbFormula.visibility = View.VISIBLE

        val queue = RequestQueueSingleton.getInstance(this.applicationContext).requestQueue
        val stringRequest = StringRequest(
          Request.Method.GET, readmeURL,
          { response ->
              val jsonObj = JSONObject(response)
              val base64Str = jsonObj.get("content").toString()
              val content = Base64.decode(base64Str, Base64.DEFAULT).decodeToString()

              val markdown : Markwon = Markwon.create(this)
              markdown.setMarkdown(findViewById(R.id.formulaReadme), content)

              pbFormula.visibility = View.GONE
          },
          {
              Toast.makeText(this, getString(R.string.error_load_repo_items), Toast.LENGTH_SHORT).show()
              pbFormula.visibility = View.GONE
          }
        )

        queue.add(stringRequest)
    }
}