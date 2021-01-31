package com.oliveiralabs.pdz.others

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.oliveiralabs.pdz.models.RepoItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import retrofit2.HttpException

class MainViewModel : ViewModel() {
    private val service = RepoItemService()
    private val _items = MutableLiveData<List<RepoItem>>()

    val items: LiveData<List<RepoItem>>
        get() = _items

    fun fetchRepoItems(user :String, repo :String, branch :String) {
        viewModelScope.launch(Dispatchers.IO) {
            val res = service.getRepoContent(user, repo, branch)

            if (res.isSuccessful) {
                withContext(Dispatchers.Main) {
                    val jsonArray = res.body()?.get("tree") as JsonArray

                    _items.value = jsonArray.map {
                        RepoItem(it.asJsonObject.get("path").asString, true)
                    }
                }
            } else {
                throw HttpException(res)
            }
        }
    }
}