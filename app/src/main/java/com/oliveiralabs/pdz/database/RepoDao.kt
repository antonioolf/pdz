package com.oliveiralabs.pdz.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.oliveiralabs.pdz.models.Repo

@Dao
interface RepoDao {
    @Query("SELECT * FROM repo")
    suspend fun getAll(): List<Repo>

    @Insert
    suspend fun insert(vararg repo: Repo)
}