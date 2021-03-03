package com.oliveiralabs.pdz.activities


import androidx.test.espresso.IdlingRegistry
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.oliveiralabs.pdz.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class AddAndAccessFormula {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun addAndAccessFormula() {
        TestActions.clickFabAddRepo()
        TestActions.fillNewRepoDialogField(R.id.etUsername, "ZupIT")
        TestActions.fillNewRepoDialogField(R.id.etRepository, "ritchie-formulas")
        TestActions.clickButtonWithTextOk()

        IdlingRegistry.getInstance().register(mActivityTestRule.activity.getCountingIdlingResource());

        TestActions.clickRecyclerViewAtPosition(R.id.rvGroup, 3)
        TestActions.clickRecyclerViewAtPosition(R.id.rvFormulas, 0)
    }
}
