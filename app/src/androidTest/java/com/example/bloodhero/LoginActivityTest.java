package com.example.bloodhero;

import static org.junit.Assert.*;

import android.support.test.rule.ActivityTestRule;
import android.view.View;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

/**
 * Unit Testing for Login Activity
 * @author Abrar Karim
 * @version 0.1
 */


public class LoginActivityTest {

    @Rule
    public ActivityTestRule<LoginActivity> mActivityTestRule = new ActivityTestRule<>(LoginActivity.class);

    private LoginActivity mActivity = null;
    /**
     * Opening Logging page
     * @author Abrar Karim
     * @version 0.1
     */

    @Before
    public void setUp() {

        mActivity = mActivityTestRule.getActivity();
    }
    @Test
    public void testLaunch(){
        View view = mActivity.findViewById(R.id.card);
        assertNotNull(view);
    }

    @After
    public void tearDown() {
        mActivity = null;
    }
}