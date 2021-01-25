package com.oliveiralabs.pdz.models

import com.google.gson.annotations.SerializedName

data class RepoItem(
        @SerializedName("name")
        val name :String,

        @SerializedName("isFile")
        val isFile :Boolean
)