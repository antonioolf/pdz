package com.oliveiralabs.pdz.activities

import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.oliveiralabs.pdz.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityTestUI {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun testAddNewRepo() {
        TestActions.clickFabAddRepo()
        TestActions.fillNewRepoDialogField(R.id.etUsername, "ZupIT")
        TestActions.fillNewRepoDialogField(R.id.etRepository, "ritchie-formulas")
        TestActions.clickButtonWithTextOk()
        TestActions.assertSpinnerHaveItem(R.id.spinnerRepo, "ZupIT / ritchie-formulas")
    }

    @Test
    fun testNewRepoFormValidation() {
        /* Abre dialog e clican em OK sem preencher os campos para validar */
        TestActions.clickFabAddRepo()
        TestActions.clickButtonWithTextOk()
        TestActions.assertNewRepoDialogOpen()
    }
}