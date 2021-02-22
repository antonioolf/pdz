package com.oliveiralabs.pdz.activities


import android.view.View
import android.view.ViewGroup
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.oliveiralabs.pdz.R
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@LargeTest
@RunWith(AndroidJUnit4::class)
class GeneralNavigation {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun generalNavigation() {
        TestActions.clickFabAddRepo()
        TestActions.fillNewRepoDialogField(R.id.etUsername, "ZupIT")
        TestActions.fillNewRepoDialogField(R.id.etRepository, "ritchie-formulas")
        TestActions.clickButtonWithTextOk()

//        val materialButton3 = onView(
//                allOf(withId(android.R.id.button1), withText("OK"),
//                        childAtPosition(
//                                childAtPosition(
//                                        withClassName(`is`("android.widget.ScrollView")),
//                                        0),
//                                3)))
//        materialButton3.perform(scrollTo(), click())
//
//        val floatingActionButton3 = onView(
//                allOf(withId(R.id.fabAddRepo), withContentDescription("Add"),
//                        childAtPosition(
//                                childAtPosition(
//                                        withId(android.R.id.content),
//                                        0),
//                                3),
//                        isDisplayed()))
//        floatingActionButton3.perform(click())
//
//        val materialButton4 = onView(
//                allOf(withId(android.R.id.button2), withText("Cancel"),
//                        childAtPosition(
//                                childAtPosition(
//                                        withClassName(`is`("android.widget.ScrollView")),
//                                        0),
//                                2)))
//        materialButton4.perform(scrollTo(), click())
//
//        val appCompatSpinner = onView(
//                allOf(withId(R.id.spinnerRepo),
//                        childAtPosition(
//                                allOf(withId(R.id.spinnerRepoContainer),
//                                        childAtPosition(
//                                                withClassName(`is`("android.widget.RelativeLayout")),
//                                                0)),
//                                0),
//                        isDisplayed()))
//        appCompatSpinner.perform(click())
//
//        val materialTextView = onData(anything())
//                .inAdapterView(childAtPosition(
//                        withClassName(`is`("android.widget.PopupWindow\$PopupBackgroundView")),
//                        0))
//                .atPosition(1)
//        materialTextView.perform(click())
//
//        val recyclerView = onView(
//                allOf(withId(R.id.rvGroup),
//                        childAtPosition(
//                                withClassName(`is`("android.widget.LinearLayout")),
//                                1)))
//        recyclerView.perform(actionOnItemAtPosition<ViewHolder>(0, click()))
//
//        val recyclerView2 = onView(
//                allOf(withId(R.id.rvFormulas),
//                        childAtPosition(
//                                withClassName(`is`("androidx.cardview.widget.CardView")),
//                                0)))
//        recyclerView2.perform(actionOnItemAtPosition<ViewHolder>(8, click()))
//
//        val materialTextView2 = onView(
//                allOf(withId(R.id.formulaReadme), withText("Description\n\nThis formula will list your buckets on aws providing only one input:\n\nAWS region i.e., us-east-1, us-west-1, sa-east-1\n\nCommand\n\n \nrit aws list bucket\n \n\nRequirements\n\nGolang\nSet AWS credentials ($ rit set credentials) with ACCESS KEY ID and SECRET ACCESS KEY.\n\nDemonstration\n\n"),
//                        childAtPosition(
//                                childAtPosition(
//                                        withId(android.R.id.content),
//                                        0),
//                                2),
//                        isDisplayed()))
//        materialTextView2.perform(longClick())
//
//        val materialTextView3 = onView(
//                allOf(withId(R.id.formulaReadme), withText("Description\n\nThis formula will list your buckets on aws providing only one input:\n\nAWS region i.e., us-east-1, us-west-1, sa-east-1\n\nCommand\n\n \nrit aws list bucket\n \n\nRequirements\n\nGolang\nSet AWS credentials ($ rit set credentials) with ACCESS KEY ID and SECRET ACCESS KEY.\n\nDemonstration\n\n"),
//                        childAtPosition(
//                                childAtPosition(
//                                        withId(android.R.id.content),
//                                        0),
//                                2),
//                        isDisplayed()))
//        materialTextView3.perform(click())
//
//        val appCompatImageButton = onView(
//                allOf(withContentDescription("Navigate up"),
//                        childAtPosition(
//                                allOf(withId(R.id.action_bar),
//                                        childAtPosition(
//                                                withId(R.id.action_bar_container),
//                                                0)),
//                                1),
//                        isDisplayed()))
//        appCompatImageButton.perform(click())
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
