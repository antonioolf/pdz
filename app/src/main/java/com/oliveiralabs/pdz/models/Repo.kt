package com.oliveiralabs.pdz.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "repo")
data class Repo(
    @PrimaryKey val id: Int?,
    @ColumnInfo(name = "username") val username: String?,
    @ColumnInfo(name = "repository") val repository: String?
)