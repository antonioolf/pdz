package com.oliveiralabs.pdz.activities

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.oliveiralabs.pdz.R
import org.hamcrest.CoreMatchers.containsString
import org.hamcrest.Matchers


class TestActions {
    companion object {
        fun clickFabAddRepo() {
            onView(Matchers.allOf(withId(R.id.fabAddRepo), isDisplayed())).perform(click())
        }

        fun clickButtonWithTextOk() {
            onView(withText("OK")).perform(scrollTo(), click())
        }

        fun assertNewRepoDialogOpen() {
            onView(withText(R.string.new_repo)).check(matches(isDisplayed()))
        }

        fun fillNewRepoDialogField(resource: Int, value: String) {
            onView(withId(resource)).perform(typeText(value), closeSoftKeyboard())
        }

        fun assertSpinnerHaveItem(resSpinner: Int, itemTitle: String) {
            onView(withId(resSpinner)).check(matches(withSpinnerText(containsString(itemTitle))))
        }
    }
}