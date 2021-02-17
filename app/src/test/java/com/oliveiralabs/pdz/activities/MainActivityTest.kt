package com.oliveiralabs.pdz.activities

import android.os.Build
import android.widget.TextView
import com.oliveiralabs.pdz.R
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@Config(sdk = [Build.VERSION_CODES.O_MR1])
@RunWith(RobolectricTestRunner::class)
class MainActivityTest {

    private lateinit var activity: MainActivity

    @Before
    fun setup() {
        activity = Robolectric.setupActivity(MainActivity::class.java)
    }

    @Test
    fun `test empty repo list message`() {
        val tvEmptyRepoList = activity.findViewById<TextView>(R.id.tvEmptyRepoList)
        val expectedStr = activity.getString(R.string.add_one_or_more_repos)
        assertEquals(tvEmptyRepoList.text, expectedStr)
    }

    @Test
    fun `test dialog add repo appears on button click`() {

    }

    @Test
    fun `test modal add repo validation`() {

    }
}