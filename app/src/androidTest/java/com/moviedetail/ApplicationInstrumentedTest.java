package com.moviedetail;

import android.content.Context;
import android.support.annotation.UiThread;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.moviedetail.view.MainActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertEquals;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ApplicationInstrumentedTest {
    @Rule
    public ActivityTestRule<MainActivity> activityActivityTestRule = new ActivityTestRule<>(MainActivity.class);
    private MainActivity mActivity;

    @Test
    public void useAppContext() {
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.moviedetail", appContext.getPackageName());
    }

    @Before
    public void setUp() {
        mActivity = activityActivityTestRule.getActivity();
        assertThat(mActivity, notNullValue());
    }

    @Test
    public void swipePage() {
        onView(withId(R.id.view_pager))
                .check(matches(isDisplayed()));

        onView(withId(R.id.view_pager))
                .perform(swipeLeft());
    }

    @Test
    public void checkTabLayoutDisplayed() {
        onView(withId(R.id.tabs))
                .perform(click())
                .check(matches(isDisplayed()));
    }

    @Test
    @UiThread
    public void checkTabSwitch() {
        onView(allOf(withText("Top Rated Movies"), isDescendantOfA(withId(R.id.tabs))))
                .perform(click())
                .check(matches(isDisplayed()));


    }

    @Test
    public void searchMovie() {
        onView(withId(R.id.action_search)).perform(click());
        onView(withId(android.support.design.R.id.search_src_text)).perform(typeText("avengers"));


    }
}
