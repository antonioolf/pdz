package com.oliveiralabs.pdz.others

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.oliveiralabs.pdz.R
import com.oliveiralabs.pdz.adapters.RepoItemAdapter

/*
 * O limite de arquivos para a listagem recursiva do Github é 100.000
 * https://github.community/t/github-get-tree-api-limits-and-recursivity/1300/2
 *
 *
 * requests em loop: https://stackoverflow.com/a/60881752
 *
 * Tutorial
 * https://medium.com/@tguizelini/android-mvvm-retrofit-coroutines-s2-7f09bce0ad0c
 *
 * Teste
 * https://medium.com/@q2ad/testing-retrofit-kotlin-coroutines-c7849e960ed8
 *
 * Problemas com o Retrofit
 *
 * - Lib bastante conhecida, a principio imaginei que fosse melhor pois é mais moderna
 *
 * - Dificuldade em passar URLs com barra (/), ele sempre encoda e dá errado, tem que passar separado pra funcionar
 * - Muito engessado: No meu entender, o fato de eu ter tido que especificar um model para receber os dados da API
 *   sugere que será trabalhoso lidar com APIs onde o retorno não vem exatamente da forma mais enxuta possível.
 *   Exemplo: No tutorial dava certo pois a API retornava uma lista e nada mais, porém a API do github retorna outros
 *   metadados e a lista está aninhada em outros níveis, fazendo com que desse erro. Não consegui encontrar como lidar
 *   com retornos desse tipo, que são bastante comuns.
 *
 */

class MainActivity : AppCompatActivity() {
    lateinit var viewModel: MainViewModel
    private val repoItemAdapter = RepoItemAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        findViewById<RecyclerView>(R.id.rvItemRit).apply {
            layoutManager = LinearLayoutManager(context)
            adapter = repoItemAdapter
        }

        viewModel.fetchRepoItems("antonioolf","cdi", "master")

        viewModel.items.observe(this, { repoItems ->
            repoItems?.let {
                repoItemAdapter.update(it)
            }
        })
    }
}