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
import com.oliveiralabs.pdz.adapters.GroupAdapter
import com.oliveiralabs.pdz.database.AppDatabase
import com.oliveiralabs.pdz.models.Formula
import com.oliveiralabs.pdz.models.Repo
import kotlinx.coroutines.*


class MainActivity : AppCompatActivity(), NewRepoDialog.NewRepoDialogListener {

    private lateinit var repos: List<Repo>
    private lateinit var groupAdapter: GroupAdapter
    private lateinit var spinnerRepoAdapter: ArrayAdapter<String?>
    private lateinit var pbGroup: ProgressBar

    private val baseUrl = "https://api.github.com"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        pbGroup = findViewById(R.id.pbGroup)

        setFabAddRepo()
        setSpinnerRepos()
        setRepos()
        setGroups()
    }

    private fun setFabAddRepo() {
        val fabAddRepo = findViewById<FloatingActionButton>(R.id.fabAddRepo)
        fabAddRepo.setOnClickListener {
            val newRepoDialog = NewRepoDialog()
            newRepoDialog.show(supportFragmentManager, "teste")
        }
    }

    private fun setSpinnerRepos() {
        val spinner: Spinner = findViewById(R.id.spinnerRepo)

        spinnerRepoAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, arrayListOf())
        spinner.adapter = spinnerRepoAdapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val item :Repo = repos[position]
                loadRepoItems("${item.username}/${item.repository}")
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
    }

    private fun setRepos() {
        CoroutineScope(Dispatchers.IO).launch {
            val operation = async {

                val db = Room.databaseBuilder(
                        applicationContext,
                        AppDatabase::class.java,
                        resources.getString(R.string.database_name)
                ).build()

                repos = db.repoDao().getAll()
            }

            operation.await()
            withContext(Dispatchers.Main) {
                pbGroup.visibility = View.GONE
                if (repos.isEmpty()) {

                    findViewById<LinearLayout>(R.id.llEmptyRepoList).visibility = View.VISIBLE
                    findViewById<Spinner>(R.id.spinnerRepo).visibility = View.GONE
                } else {
                    spinnerRepoAdapter.clear()
                    spinnerRepoAdapter.addAll(repos.map { "${it.username} / ${it.repository}" })
                    spinnerRepoAdapter.notifyDataSetChanged()

                    findViewById<LinearLayout>(R.id.llEmptyRepoList).visibility = View.GONE
                    findViewById<Spinner>(R.id.spinnerRepo).visibility = View.VISIBLE
                }
            }
        }
    }

    private fun setGroups() {
        groupAdapter = GroupAdapter(arrayListOf())
        val layoutManager = LinearLayoutManager(this)
        val rvGroup = findViewById<RecyclerView>(R.id.rvGroup)
        rvGroup.adapter = groupAdapter
        rvGroup.layoutManager = layoutManager
    }

    private fun loadRepoItems(userRepoSlug :String) {
        pbGroup.visibility = View.VISIBLE
        val queue = Volley.newRequestQueue(this)
        val stringRequest = StringRequest(Request.Method.GET, "${baseUrl}/repos/${userRepoSlug}/git/trees/master?recursive=1",
                { response ->
                    val repoMapper = RepoMapper()
                    val repoMap :MutableMap<String, List<Formula>> = repoMapper.getRepoItemsMap(response)

                    groupAdapter.update(repoMap.keys.map { it })
                    groupAdapter.notifyDataSetChanged()

                    pbGroup.visibility = View.GONE
                },
                {
                    Toast.makeText(this, "Erro ao carregar itens do repositório", Toast.LENGTH_SHORT).show()
                    pbGroup.visibility = View.GONE
                }
        )

        queue.add(stringRequest)
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
                setRepos()
            }
        }
    }
}