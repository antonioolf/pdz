package com.oliveiralabs.pdz.database

import android.content.Context
import android.os.Build
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.google.common.truth.Truth.assertThat
import com.oliveiralabs.pdz.models.Repo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config


@Config(sdk = [Build.VERSION_CODES.O_MR1])
@RunWith(RobolectricTestRunner::class)
class RepoTest {

    private fun setupDatabase(): AppDatabase {
        val context = ApplicationProvider.getApplicationContext<Context>()
        return Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
    }

    @Test
    @Throws(Exception::class)
    fun `test insert and getAll methods`() {
        CoroutineScope(Dispatchers.IO).launch {

            val operation = async {
                val db = setupDatabase()
                val repoDao = db.repoDao()

                repoDao.insert(
                        Repo(null, "a", "1"),
                        Repo(null, "b", "2"),
                        Repo(null, "c", "3"),
                        Repo(null, "d", "4")
                )

                val repoList = repoDao.getAll()
                assertThat(repoList.size).isEqualTo(4)

                db.close()
            }

            operation.await()
        }
    }

    /*  @Test
  @Throws(Exception::class)
  fun `test repo insertion and selection`() {
      CoroutineScope(Dispatchers.IO).launch {

          val operation = async {
              val db = setupDatabase()
              val repoDao = db.repoDao()

              val repo = Repo(1, "antonioolf", "formulas-antonio")
              repoDao.insert(repo)
              val inserted = repoDao.findById(1)
              assertThat(inserted).isEqualTo(repo)

              db.close()
          }

          operation.await()
      }
  }*/
/*
    @Test
    @Throws(Exception::class)
    fun `test repo delete`() {
        CoroutineScope(Dispatchers.IO).launch {

            val operation = async {
                val db = setupDatabase()
                val repoDao = db.repoDao()

                val repo = Repo(1, "antonioolf", "formulas-antonio")
                repoDao.insert(repo)

                repoDao.delete(repo)

                val inserted = repoDao.findById(1)
                assertThat(inserted).isNull()

                db.close()
            }

            operation.await()
        }
    }*/
}