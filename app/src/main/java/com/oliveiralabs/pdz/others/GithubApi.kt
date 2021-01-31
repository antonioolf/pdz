package com.oliveiralabs.pdz.others

import com.google.gson.JsonObject
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubApi {

    @GET("repos/{user}/{repo}/git/trees/{branch}?recursive=1")
    suspend fun getRepoContent(
        @Path("user") user: String,
        @Path("repo") repo: String,
        @Path("branch") branch :String
    ): Response<JsonObject>

    @GET
    suspend fun getInfo(): Response<String>
}