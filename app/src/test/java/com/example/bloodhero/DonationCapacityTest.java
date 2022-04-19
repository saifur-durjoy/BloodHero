package com.example.bloodhero;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * This class is used for Unit Testing on Donation Capacity
 * @author Arfana Rahman_1831172042
 * @since 2022
 */
public class DonationCapacityTest {

    /**
     * This is the test case 1 for input = 0 (Valid Input)
     */

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
    /**
     * This is the test case 2 for invalid inputs (Less than 0)
     */

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

    /**
     * This is the test case 3 for valid inputs (For Positive Number)
     */

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