package com.example.sunshinemedicalclinic;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class BookScreenTest {
    @Rule
    public ActivityScenarioRule<BookAppointment> mBookActivityTestRule = new ActivityScenarioRule<BookAppointment>(BookAppointment.class);
    @Test
    public void scheduleAppointment(){
        onView(withId(R.id.rdBook)).perform(click());
        onView(withId(R.id.btnDate)).perform(click());
        pressBack();
    }
}
