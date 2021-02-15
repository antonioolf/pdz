package com.oliveiralabs.pdz.activities

import android.content.Intent
import android.os.Build
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.oliveiralabs.pdz.R
import junit.framework.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.Shadows.shadowOf
import org.robolectric.annotation.Config

@Config(sdk = [Build.VERSION_CODES.O_MR1])
@RunWith(RobolectricTestRunner::class)
class MainActivityTest {

    @Test
    fun clickingLogin_shouldStartLoginActivity() {
        val activity :MainActivity = Robolectric.setupActivity(MainActivity::class.java)
        activity.findViewById<FloatingActionButton>(R.id.fabAddRepo).performClick()

        val expectedIntent = Intent(activity, FormulaActivity::class.java)
        val actual :Intent = shadowOf(RuntimeEnvironment.application).nextStartedActivity
        assertEquals(expectedIntent.component, actual.component)
    }
}