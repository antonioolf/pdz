package com.oliveiralabs.pdz.activities

import android.content.Intent
import android.os.Build
import android.widget.TextView
import com.oliveiralabs.pdz.R
import junit.framework.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric.buildActivity
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@Config(sdk = [Build.VERSION_CODES.O_MR1])
@RunWith(RobolectricTestRunner::class)
class FormulaActivityTest {

    @Test
    fun `test formula command showing`() {
        // GIVEN
        val controller = buildActivity(FormulaActivity::class.java)

        val i = Intent()
        i.putExtra("command", "bitbucket/delete/repo")
        controller.get().intent = i

        // WHEN
        controller.create()
        controller.resume()

        // THEN
        assertEquals("rit bitbucket delete repo", controller.get().findViewById<TextView>(R.id.tvFormulaCommand).text)
    }
}