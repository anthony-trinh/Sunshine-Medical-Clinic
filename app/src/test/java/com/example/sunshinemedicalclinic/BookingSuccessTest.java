package com.example.sunshinemedicalclinic;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.Test;
import org.junit.runners.JUnit4;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
@RunWith(JUnit4.class)
public class BookingSuccessTest {
    BookingSuccess bookingSuccess = new BookingSuccess();
    @Before
    public void setUp() {
        BookingSuccess bookingSuccess = new BookingSuccess();
    }
    @Test
    public void setgetClinic_isCorrect(){
        bookingSuccess.setClinic("Hospital");
        String clinic = bookingSuccess.getClinic();
        assertThat(clinic, is(equalTo("Hospital")));
    }
    @Test
    public void setgetTime_isCorrect(){
        bookingSuccess.setClinic("Dec. 10");
        String clinic = bookingSuccess.getClinic();
        assertThat(clinic, is(equalTo("Dec. 10")));
    }
    @Test
    public void setgetType_isCorrect(){
        bookingSuccess.setClinic("Covid-19 Test");
        String clinic = bookingSuccess.getClinic();
        assertThat(clinic, is(equalTo("Covid-19 Test")));
    }
    @Test
    public void setgetAddress_isCorrect(){
        bookingSuccess.setClinic("120 Hardland St.");
        String clinic = bookingSuccess.getClinic();
        assertThat(clinic, is(equalTo("120 Hardland St.")));
    }
}
