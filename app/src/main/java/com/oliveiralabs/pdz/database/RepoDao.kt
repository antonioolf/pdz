package com.oliveiralabs.pdz.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.oliveiralabs.pdz.models.Repo

@Dao
interface RepoDao {
    @Query("SELECT * FROM repo")
    suspend fun getAll(): List<Repo>

    @Query("SELECT * FROM repo WHERE id = :id")
    suspend fun findById(id: Int): Repo

    @Insert
    suspend fun insert(repo: Repo)

    @Delete
    suspend fun delete(repo: Repo)
}