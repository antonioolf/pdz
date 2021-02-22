package com.oliveiralabs.pdz.activities


import android.view.View
import android.view.ViewGroup
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher
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
/*        val floatingActionButton = onView(
                allOf(withId(R.id.fabAddRepo), withContentDescription("Add"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                3),
                        isDisplayed()))
        floatingActionButton.perform(click())*/

/*        val appCompatEditText = onView(
                allOf(withId(R.id.etUsername),
                        childAtPosition(
                                allOf(withId(R.id.newRepoDialog),
                                        childAtPosition(
                                                withId(android.R.id.custom),
                                                0)),
                                0),
                        isDisplayed()))
        appCompatEditText.perform(replaceText("ZupIT"), closeSoftKeyboard())

        val appCompatEditText2 = onView(
                allOf(withId(R.id.etRepository),
                        childAtPosition(
                                allOf(withId(R.id.newRepoDialog),
                                        childAtPosition(
                                                withId(android.R.id.custom),
                                                0)),
                                1),
                        isDisplayed()))
        appCompatEditText2.perform(replaceText("ritchie-formulas"), closeSoftKeyboard())

        val materialButton = onView(
                allOf(withId(android.R.id.button1), withText("OK"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(`is`("android.widget.ScrollView")),
                                        0),
                                3)))
        materialButton.perform(scrollTo(), click())

        Espresso.registerIdlingResources(mActivityTestRule.activity.getCountingIdlingResource())

        val recyclerView = onView(
                allOf(withId(R.id.rvGroup),
                        childAtPosition(
                                withClassName(`is`("android.widget.LinearLayout")),
                                1)))

        recyclerView.perform(actionOnItemAtPosition<ViewHolder>(3, click()))

        val recyclerView2 = onView(
                allOf(withId(R.id.rvFormulas),
                        childAtPosition(
                                withClassName(`is`("androidx.cardview.widget.CardView")),
                                0)))
        recyclerView2.perform(actionOnItemAtPosition<ViewHolder>(1, click()))*/
    }

    private fun childAtPosition(
            parentMatcher: Matcher<View>, position: Int): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}
