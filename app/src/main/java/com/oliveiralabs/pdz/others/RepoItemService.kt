package com.oliveiralabs.pdz.others

import com.google.gson.JsonObject
import com.oliveiralabs.pdz.models.RepoItem
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RepoItemService {
    private val baseUrl :String = "https://api.github.com"

    private val api: GithubApi = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(GithubApi::class.java)

    suspend fun getRepoContent(user :String, repo :String, branch :String): Response<JsonObject> = api.getRepoContent(user, repo, branch)
}