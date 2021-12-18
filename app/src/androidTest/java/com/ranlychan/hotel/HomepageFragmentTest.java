package com.ranlychan.hotel;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;


import com.ranlychan.hotel.activity.ViewPagerActivityMain;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static java.lang.Thread.sleep;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class HomepageFragmentTest {
    @Rule
    public ActivityScenarioRule<ViewPagerActivityMain> mViewPagerActivityMainRule = new ActivityScenarioRule(ViewPagerActivityMain.class);

    /*

    @Before
    public void setUp() throws Exception {
        //调用Activity中我们已经设置好的getIdlingresource()方法，获取Idlingresource对象
        idlingresource = mViewPagerActivityMainRule.getActivity().getIdlingresource();

        //去掉下行注释，只有异步结束后，才进行接下来的测试代码（tests passed）
        //注册异步监听，当该idlingresource中的counter标记值为0时才进行接下来的测试代码
        Espresso.registerIdlingResources(idlingresource);
    }

    @Test
    public void onLoadingFinished() throws Exception {
        //  不再需要这样的代码
        //  Thread.sleep(5000);

        // 未注册idlingResource时，立即进行test，此时异步并未结束，报错（tests failed）
        onView(withId(R.id.text))
                .check(matches(withText("success!")));
    }

    @After
    public void release() throws Exception {
        //我们在测试结束后取消注册，释放资源
        Espresso.unregisterIdlingResources(idlingresource);
    }

     */

    @Test
    public void testRecyclerView(){
        /*
        //点击进入首页
        onView(withId(R.id.main_homepage)).perform(click());

        //定位到第3条并点击
        onView(ViewMatchers.withId(R.id.rv_homepage_rooms))
                .perform(RecyclerViewActions.actionOnItemAtPosition(3, click()));

        onView(withText("房型详情")).check(matches(isDisplayed()));


        try {
            sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        */

    }
}
