/*
package com.oliveiralabs.pdz.database

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SmallTest
class RepoDaoTest {
    private lateinit var database: AppDatabase
    private lateinit var dao: RepoDao

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
                ApplicationProvider.getApplicationContext(),
                AppDatabase::class.java
        ).allowMainThreadQueries().build()
        dao = database.repoDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun insertRepoItem() = runBlockingTest {

    }
}*/
