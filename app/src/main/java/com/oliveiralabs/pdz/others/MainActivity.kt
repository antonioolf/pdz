package com.oliveiralabs.pdz.others

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.oliveiralabs.pdz.R
import com.oliveiralabs.pdz.adapters.RepoItemAdapter
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = RepoItemAdapter(arrayListOf())

        val layoutManager = LinearLayoutManager(this)
        rvRepoItem.adapter = adapter
        rvRepoItem.layoutManager = layoutManager

        val queue = Volley.newRequestQueue(this)

        val stringRequest = StringRequest(Request.Method.GET, url,
            { response ->
                print(response.toString())
            },
            {
                print("erro")
            }
        )

        queue.add(stringRequest)
    }


}