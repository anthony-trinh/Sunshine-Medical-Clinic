package com.example.sunshinemedicalclinic;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class ContactScreenTest {
    @Rule
    public ActivityScenarioRule<ContactUs> mContactActivityTestRule = new ActivityScenarioRule<ContactUs>(ContactUs.class);
    @Test
    public void callClinic(){
        onView(withId(R.id.clinicSpinner)).perform(click());
        pressBack();
        onView(withId(R.id.callButton)).perform(click());
    }
}
