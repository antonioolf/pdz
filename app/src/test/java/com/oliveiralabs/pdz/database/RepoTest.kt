package com.oliveiralabs.pdz.database

import android.os.Build
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

    @Test
    @Throws(Exception::class)
    fun `test insert and getAll methods`() {
        CoroutineScope(Dispatchers.IO).launch {

            val operation = async {
                val db = DbTestUtil.getDatabase()
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
}