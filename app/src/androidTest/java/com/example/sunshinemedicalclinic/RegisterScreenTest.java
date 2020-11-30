package com.example.sunshinemedicalclinic;

import androidx.test.core.app.ActivityScenario;
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
public class RegisterScreenTest {
    @Rule
    public ActivityScenarioRule<Register> mRegisterActivityTestRule = new ActivityScenarioRule<Register>(Register.class);
    @Rule
    public ActivityScenarioRule<BookAppointment> mBookActivityTestRule = new ActivityScenarioRule<BookAppointment>(BookAppointment.class);
    String typeHcard, typeFName, typeLName, typeSex, typePhone, typeDOB, typeEmail, typePassword;
    @Before
    public void initValidString() {
        typeHcard = "1234567890";
        typeFName = "John";
        typeLName = "Smith";
        typeSex = "M";
        typePhone = "123-456-7890";
        typeDOB = "03/12/2000";
        typeEmail = "john.smith12@gmail.com";
        typePassword = "123456";
    }
    @Test
    public void fillRegistration(){
        onView(withId(R.id.regHealthcardNo)).perform(typeText(typeHcard)).perform(closeSoftKeyboard());
        onView(withId(R.id.regFName)).perform(typeText(typeFName)).perform(closeSoftKeyboard());
        onView(withId(R.id.regLName)).perform(typeText(typeLName)).perform(closeSoftKeyboard());
        onView(withId(R.id.regSex)).perform(typeText(typeSex)).perform(closeSoftKeyboard());
        onView(withId(R.id.regPhoneNo)).perform(typeText(typePhone)).perform(closeSoftKeyboard());
        onView(withId(R.id.regDOB)).perform(typeText(typeDOB)).perform(closeSoftKeyboard());
        onView(withId(R.id.regEmail)).perform(typeText(typeEmail)).perform(closeSoftKeyboard());
        onView(withId(R.id.regPswd)).perform(typeText(typePassword)).perform(closeSoftKeyboard());
        onView(withId(R.id.regHealthcardNo)).check(matches(withText(typeHcard)));
        onView(withId(R.id.regFName)).check(matches(withText(typeFName)));
        onView(withId(R.id.regLName)).check(matches(withText(typeLName)));
        onView(withId(R.id.regSex)).check(matches(withText(typeSex)));
        onView(withId(R.id.regPhoneNo)).check(matches(withText(typePhone)));
        onView(withId(R.id.regDOB)).check(matches(withText(typeDOB)));
        onView(withId(R.id.regEmail)).check(matches(withText(typeEmail)));
        onView(withId(R.id.regPswd)).check(matches(withText(typePassword)));
    }
    @Test
    public void scheduleAppointment(){
        onView(withId(R.id.rdBook)).perform(click());
        onView(withId(R.id.btnDate)).perform(click());
        pressBack();
    }
}
