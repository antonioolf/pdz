package com.oliveiralabs.pdz.activities

import android.app.AlertDialog
import android.os.Build
import android.widget.EditText
import android.widget.TextView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.common.truth.Truth
import com.oliveiralabs.pdz.R
import com.oliveiralabs.pdz.others.NewRepoDialog
import junit.framework.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@Config(sdk = [Build.VERSION_CODES.O_MR1])
@RunWith(RobolectricTestRunner::class)
class MainActivityTest {

    @Test
    fun `test empty repo list message`() {
        val controller = Robolectric.buildActivity(MainActivity::class.java)
        controller.create()

        val tvEmptyRepoList = controller.get().findViewById<TextView>(R.id.tvEmptyRepoList)
        val expectedStr = controller.get().getString(R.string.add_one_or_more_repos)
        assertEquals(tvEmptyRepoList.text, expectedStr)
    }


    @Test
    fun `test open formulas dialog on click in group`() {

    }

    @Test
    fun `test dialog add repo appears on button click`() {
        val controller = Robolectric.buildActivity(MainActivity::class.java)
        controller.create()
        controller.get().findViewById<FloatingActionButton>(R.id.fabAddRepo).performClick()
    }

    @Test
    fun `test modal add repo validation`() {
        val controller = Robolectric.buildActivity(MainActivity::class.java)
        controller.create()
        controller.get().findViewById<FloatingActionButton>(R.id.fabAddRepo).performClick()

        controller.resume()

        val newRepoDialog = NewRepoDialog()
        newRepoDialog.show(controller.get().supportFragmentManager, null)

        newRepoDialog.dialog?.findViewById<EditText>(R.id.etUsername)?.setText("fasdf")
        newRepoDialog.dialog?.findViewById<EditText>(R.id.etRepository)?.setText("fasdf")

        Truth.assertThat((newRepoDialog.dialog as AlertDialog).isShowing).isTrue()
    }
}