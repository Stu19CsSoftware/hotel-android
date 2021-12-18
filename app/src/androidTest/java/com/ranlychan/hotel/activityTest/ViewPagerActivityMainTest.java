package com.ranlychan.hotel.activityTest;

import android.content.Context;

import androidx.test.espresso.ViewAction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.platform.app.InstrumentationRegistry;

import com.ranlychan.hotel.R;
import com.ranlychan.hotel.activity.ViewPagerActivityMain;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isSelected;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static java.lang.Thread.sleep;
import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class ViewPagerActivityMainTest {

    @Rule
    public ActivityScenarioRule<ViewPagerActivityMain> mViewPagerActivityMainRule = new ActivityScenarioRule(ViewPagerActivityMain.class);

    @Test
    public void useAppContext() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.ranlychan.hotel", appContext.getPackageName());
    }

    @Test
    public void viewPagerSlideTest(){
        onView(withId(R.id.main_homepage))
                .perform(click())
                .check(matches(isSelected()));

        onView(withId(R.id.main_message))
                .perform(click())
                .check(matches(isSelected()));

        onView(withId(R.id.main_me))
                .perform(click())
                .check(matches(isSelected()));

        try {
            sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
