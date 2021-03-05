package com.oliveiralabs.pdz.activities


import androidx.test.espresso.IdlingRegistry
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.oliveiralabs.pdz.R
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class AddAndAccessFormula {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Before
    fun init() {
        IdlingRegistry.getInstance().register(mActivityTestRule.activity.getCountingIdlingResource())
    }

    @Test
    fun addAndAccessFormula() {
        TestActions.clickFabAddRepo()
        TestActions.fillNewRepoDialogField(R.id.etUsername, "ZupIT")
        TestActions.fillNewRepoDialogField(R.id.etRepository, "ritchie-formulas")
        TestActions.clickButtonWithText("OK")

        TestActions.clickRecyclerViewAtPosition(R.id.rvGroup, 3)
        TestActions.clickRecyclerViewAtPosition(R.id.rvFormulas, 0)
    }

    @After
    fun end() {
        IdlingRegistry.getInstance().unregister(mActivityTestRule.activity.getCountingIdlingResource())
    }
}
