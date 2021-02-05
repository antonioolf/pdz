package com.oliveiralabs.pdz.others

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import android.widget.*
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

    private lateinit var repoList: List<Repo>
    private lateinit var repoItemAdapter: RepoItemAdapter
    private lateinit var spinnerRepoAdapter: ArrayAdapter<String?>

    val typeFile = 100644
    private val baseUrl = "https://api.github.com"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setFabAddRepo()
        setSpinnerRepo()
        setRepoItemList()
    }

    private fun setRepoItemList() {
        repoItemAdapter = RepoItemAdapter(arrayListOf())
        val layoutManager = LinearLayoutManager(this)
        val rvRepoItem = findViewById<RecyclerView>(R.id.rvRepoItem)
        rvRepoItem.adapter = repoItemAdapter
        rvRepoItem.layoutManager = layoutManager
        loadRepoItems("antonioolf/cdi")
    }

    private fun setSpinnerRepo() {
        val spinner: Spinner = findViewById(R.id.spinnerRepo)
        getRepoList()

        repoList = arrayListOf()
        spinnerRepoAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, repoList.map { "${it.username} / ${it.repository}" })
        spinner.adapter = spinnerRepoAdapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val item :Repo = repoList[position]

                loadRepoItems("${item.username}/${item.repository}")
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
    }

    private fun setFabAddRepo() {
        val fabAddRepo = findViewById<FloatingActionButton>(R.id.fabAddRepo)
        fabAddRepo.setOnClickListener {
            val newRepoDialog = NewRepoDialog()
            newRepoDialog.show(supportFragmentManager, "teste")
        }
    }

    private fun getRepoList() {
        CoroutineScope(Dispatchers.IO).launch {
            val operation = async {

                val db = Room.databaseBuilder(
                        applicationContext,
                        AppDatabase::class.java,
                        resources.getString(R.string.database_name)
                ).build()

                repoList = db.repoDao().getAll()
            }

            operation.await()
            withContext(Dispatchers.Main) {
                spinnerRepoAdapter.clear()
                spinnerRepoAdapter.addAll(repoList.map { "${it.username} / ${it.repository}" })
                spinnerRepoAdapter.notifyDataSetChanged()
            }
        }
    }

    private fun loadRepoItems(userRepoSlug :String) {
        findViewById<ProgressBar>(R.id.pbRepoItem).visibility = View.VISIBLE
        val queue = Volley.newRequestQueue(this)
        val stringRequest = StringRequest(Request.Method.GET, "${baseUrl}/repos/${userRepoSlug}/git/trees/master?recursive=1",
                { response ->
                    val repoItemList = responseToRepoItemList(response)
                    repoItemAdapter.update(repoItemList)
                    repoItemAdapter.notifyDataSetChanged()

                    findViewById<ProgressBar>(R.id.pbRepoItem).visibility = View.GONE
                },
                {
                    Toast.makeText(this, "Erro ao carregar itens do repositório", Toast.LENGTH_SHORT).show()
                    findViewById<ProgressBar>(R.id.pbRepoItem).visibility = View.GONE
                }
        )

        queue.add(stringRequest)
    }

    private fun responseToRepoItemList(response: String): ArrayList<RepoItem> {
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

    override fun onDialogPositiveClick(dialog: AlertDialog, username: String, repository: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val operation = async {

                val db = Room.databaseBuilder(
                        applicationContext,
                        AppDatabase::class.java,
                        resources.getString(R.string.database_name)
                ).build()

                val repo = Repo(null, username, repository)
                db.repoDao().insert(repo)
            }

            /*val result = */operation.await()
            withContext(Dispatchers.Main) {
                dialog.dismiss()
                getRepoList()
            }
        }
    }
}