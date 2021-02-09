package com.oliveiralabs.pdz.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.oliveiralabs.pdz.R
import com.oliveiralabs.pdz.others.RequestQueueSingleton
import io.noties.markwon.Markwon
import org.json.JSONObject

class FormulaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formula)

        val readmeURL = intent.getStringExtra("readmeURL").toString()
/*        intent.getStringExtra("command")
        intent.getStringExtra("helpJsonURL")*/

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