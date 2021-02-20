package com.oliveiralabs.pdz.database

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider

class DbTestUtil {
    companion object {
        fun getDatabase(): AppDatabase {
            val context = ApplicationProvider.getApplicationContext<Context>()
            return Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        }
    }
}