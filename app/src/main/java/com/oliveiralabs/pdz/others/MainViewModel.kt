package com.oliveiralabs.pdz.others

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oliveiralabs.pdz.models.RepoItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class MainViewModel : ViewModel() {
    private val service = RepoItemService()
    private val _items = MutableLiveData<List<RepoItem>>()

    val items: LiveData<List<RepoItem>>
        get() = _items

    fun fetchRepoItems(userRepo :String, branch :String) {
        viewModelScope.launch(Dispatchers.IO) {
            val res = service.getRepoContent(userRepo, branch)

            if (res.isSuccessful) {
                withContext(Dispatchers.Main) {
                    _items.value = res.body()
                }
            } else {
                throw HttpException(res)
            }
        }
    }
}