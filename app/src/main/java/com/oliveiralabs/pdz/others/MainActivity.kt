package com.oliveiralabs.pdz.others

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.oliveiralabs.pdz.R
import com.oliveiralabs.pdz.adapters.RepoItemAdapter
import com.oliveiralabs.pdz.models.RepoItem
import org.json.JSONArray

/*
* O limite de arquivos para a listagem recursiva do Github é 100.000
* https://github.community/t/github-get-tree-api-limits-and-recursivity/1300/2
*
*
* requests em loop: https://stackoverflow.com/a/60881752
*
* */

class MainActivity : AppCompatActivity(), GithubApi.RequestCallback {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

/*        val api = GithubApi(this, this)
        api.getAllFiles("antonioolf/formulas-antonio", "master")
        api.getRepoContent("antonioolf/formulas-antonio", "master")*/

        val rvItemRit = findViewById<RecyclerView>(R.id.rvItemRit)
        rvItemRit.layoutManager = LinearLayoutManager(this)

        val data = arrayOf(RepoItem("Makefile", true), RepoItem("Dockerfile", true), RepoItem("formula", false))

        rvItemRit.adapter = RepoItemAdapter(data)

    }

    override fun getAllFilesResponse(response: String) {
        val files = JSONArray(response)
    }

    override fun getRepoContentResponse(response: String) {
        TODO("Not yet implemented")
    }
}