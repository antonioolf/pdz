package com.oliveiralabs.pdz.activities

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import com.oliveiralabs.pdz.R
import org.hamcrest.CoreMatchers.containsString
import org.hamcrest.Matchers


class TestActions {
    companion object {
        fun clickFabAddRepo() {
            onView(Matchers.allOf(withId(R.id.fabAddRepo), isDisplayed())).perform(click())
        }

        fun clickButtonWithText(text :String) {
            onView(withText(text)).perform(scrollTo(), click())
        }

        fun assertNewRepoDialogOpen() {
            onView(withText(R.string.new_repo)).check(matches(isDisplayed()))
        }

        fun assertNewRepoDialogClosed() {
            onView(withText(R.string.new_repo)).check(doesNotExist())
        }

        fun fillNewRepoDialogField(resource: Int, value: String) {
            onView(withId(resource)).perform(typeText(value), closeSoftKeyboard())
        }

        fun assertSpinnerHaveItem(resSpinner: Int, itemTitle: String) {
            onView(withId(resSpinner)).check(matches(withSpinnerText(containsString(itemTitle))))
        }

        fun clickRecyclerViewAtPosition(recyclerviewRes: Int, index: Int) {
            val recyclerView = onView(withId(recyclerviewRes))
            recyclerView.perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(index, click()))
        }
    }
}