package com.example.navvisapp.ui

import android.app.Activity
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import com.example.navvisapp.MainActivity
import com.example.navvisapp.R
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class MainFragmentTest {
    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java)

    private var mMainActivity: Activity? = null
    private var mRecyclerView: RecyclerView? = null
    private val res_ID: Int = R.id.number_list
    private var itemCount = 0

    @Before
    fun setup(){
        this.mMainActivity = activityRule.activity
        this.mRecyclerView =  (this.mMainActivity as MainActivity)?.findViewById(this.res_ID)
        this.itemCount = this.mRecyclerView?.adapter!!.itemCount;
    }

    @Test
    fun test_isRecycleViewVisible_onAppLaunch() {
        onView(withId(com.example.navvisapp.R.id.number_list)).check(matches(isDisplayed()))
    }

    @Test
    fun testItemCount() {
        onView(withId(res_ID)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(itemCount.minus(1)))
    }







}