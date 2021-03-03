package com.oliveiralabs.pdz.activities

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isRoot
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.oliveiralabs.pdz.R
import org.hamcrest.Matchers
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
        /* Adiciona um novo repo e verifica se ele apareceu na listagem do spinner */
        TestActions.clickFabAddRepo()
        TestActions.fillNewRepoDialogField(R.id.etUsername, "ZupIT")
        TestActions.fillNewRepoDialogField(R.id.etRepository, "ritchie-formulas")
        TestActions.clickButtonWithText("OK")
        TestActions.assertSpinnerHaveItem(R.id.spinnerRepo, "ZupIT / ritchie-formulas")
    }

    @Test
    fun testNewRepoFormValidation() {
        /* Abre dialog e clican em OK sem preencher os campos para validar */
        TestActions.clickFabAddRepo()
        TestActions.clickButtonWithText("OK")
        TestActions.assertNewRepoDialogOpen()
    }

    @Test
    fun cancelNewRepoRegister() {
        TestActions.clickFabAddRepo()
        TestActions.clickButtonWithText("CANCEL")
        TestActions.assertNewRepoDialogClosed()
    }

    @Test
    fun noRepoSelected() {
        TestActions.clickFabAddRepo()
        TestActions.fillNewRepoDialogField(R.id.etUsername, "ZupIT")
        TestActions.fillNewRepoDialogField(R.id.etRepository, "ritchie-formulas")
        TestActions.clickButtonWithText("OK")

        IdlingRegistry.getInstance().register(mActivityTestRule.activity.getCountingIdlingResource())

        onView(
            Matchers.allOf(
                ViewMatchers.withId(R.id.spinnerRepo),
                ViewMatchers.isDisplayed()
            )
        ).perform(ViewActions.click())

        onView(isRoot()).perform(ViewActions.pressBack())
    }
}