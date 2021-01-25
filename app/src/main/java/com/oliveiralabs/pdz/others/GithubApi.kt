package com.oliveiralabs.pdz.others

import com.oliveiralabs.pdz.models.RepoItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubApi {

    @GET("repos/{userRepo}/git/trees/{branch}?recursive=1")
    suspend fun getRepoContent(@Path("userRepo") userRepo: String, @Path("branch") branch :String): Response<List<RepoItem>>

    @GET
    suspend fun getInfo(): Response<String>
}