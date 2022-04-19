package com.example.bloodhero;

import static org.junit.Assert.*;

import org.junit.Test;

public class DonationCapacityTest {

    @Test
    public void testCaseOne() {

        float inputBloodvolume = 0;
        float output;
        float expected = (float) 0;
        double delta = .1;
        DonationCapacity donationCapacity = new DonationCapacity();
        output = donationCapacity.calculateDonationCapacity(inputBloodvolume);

        assertEquals(expected,output,delta);
    }

    @Test
    public void testCaseTwo() {

        float inputBloodvolume = -30;
        float output;
        float expected = (float) -1;
        double delta = .1;
        DonationCapacity donationCapacity = new DonationCapacity();
        output = donationCapacity.calculateDonationCapacity(inputBloodvolume);

        assertEquals(expected,output,delta);
    }

    @Test
    public void testCaseThree() {

        float inputBloodvolume = 30;
        float output;
        float expected = (float) 3.90;
        double delta = .1;
        DonationCapacity donationCapacity = new DonationCapacity();
        output = donationCapacity.calculateDonationCapacity(inputBloodvolume);

        assertEquals(expected,output,delta);
    }
}