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
public class SettingsScreenTest {
    @Rule
    public ActivityScenarioRule<settings> mSettingsActivityTestRule = new ActivityScenarioRule<settings>(settings.class);
    @Test
    public void changeSettings_logout(){
        onView(withId(R.id.darkModeButton)).perform(click());
        onView(withId(R.id.muteSoundButton)).perform(click());
        onView(withId(R.id.logoutButton)).perform(click());
    }
}
