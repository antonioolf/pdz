package com.oliveiralabs.pdz.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.oliveiralabs.pdz.models.Repo

@Database(entities = [Repo::class], version = 1)

abstract class AppDatabase : RoomDatabase() {
    abstract fun repoDao(): RepoDao
}