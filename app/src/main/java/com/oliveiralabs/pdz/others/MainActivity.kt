package com.oliveiralabs.pdz.others

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.oliveiralabs.pdz.R
import com.oliveiralabs.pdz.adapters.RepoItemAdapter
import com.oliveiralabs.pdz.database.AppDatabase
import com.oliveiralabs.pdz.models.Repo
import com.oliveiralabs.pdz.models.RepoItem
import kotlinx.coroutines.*
import org.json.JSONObject


class MainActivity : AppCompatActivity(), NewRepoDialog.NewRepoDialogListener {

    val typeFile = 100644
    private val baseUrl = "https://api.github.com"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = RepoItemAdapter(arrayListOf())

        val layoutManager = LinearLayoutManager(this)

        val rvRepoItem = findViewById<RecyclerView>(R.id.rvRepoItem)
        rvRepoItem.adapter = adapter
        rvRepoItem.layoutManager = layoutManager

        val queue = Volley.newRequestQueue(this)

        val stringRequest = StringRequest(Request.Method.GET, "${baseUrl}/repos/antonioolf/cdi/git/trees/master?recursive=1",
                { response ->
                    val repoItemList = responseToRepoItemList(response)
                    adapter.update(repoItemList)
                    adapter.notifyDataSetChanged()
                },
                {
                    print("erro")
                }
        )

        queue.add(stringRequest)


        /* ------------------------------------- */
        val fabAddRepo = findViewById<FloatingActionButton>(R.id.fabAddRepo)
        fabAddRepo.setOnClickListener {
            val newRepoDialog = NewRepoDialog()
            newRepoDialog.show(supportFragmentManager, "teste")
        }
    }

    private fun responseToRepoItemList(response :String): ArrayList<RepoItem> {
        val jsonObj = JSONObject(response)
        val jsonArray = jsonObj.getJSONArray("tree")
        val result :ArrayList<RepoItem> = arrayListOf()

        for (i in 0 until jsonArray.length()) {
            val file :JSONObject = jsonArray.get(i) as JSONObject
            val fileName :String = file.get("path") as String
            val mode :String = file.get("mode") as String

            result.add(RepoItem(fileName, true))
        }

        return result
    }

    override fun onDialogPositiveClick(repoName :String, repoURL :String) {
        CoroutineScope(Dispatchers.IO).launch {
            val operation = async {

                val db = Room.databaseBuilder(
                    applicationContext,
                    AppDatabase::class.java,
                    resources.getString(R.string.database_name)
                ).build()

                val repo = Repo(null, repoName, repoURL)
                db.repoDao().insert(repo)
            }

            val result = operation.await()
            withContext(Dispatchers.Main) {
                println("Operações da main thread!")
            }
        }
    }
}