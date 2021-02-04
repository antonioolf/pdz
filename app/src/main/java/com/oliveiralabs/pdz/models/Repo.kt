package com.oliveiralabs.pdz.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "repo")
data class Repo(
    @PrimaryKey val id: Int?,
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "url") val url: String?
)