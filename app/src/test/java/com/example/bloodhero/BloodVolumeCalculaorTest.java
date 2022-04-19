package com.example.bloodhero;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * This class represents the unit testing for Blood Volume Calculate
 * @author Saifur Rahman Durjoy (Saifur-Durjoy)
 * @since 2022
 */

public class BloodVolumeCalculaorTest {

    /**
     * This is the method for test case 1 when both input height and weight are invalid
     */
    @Test
    public void testCase1()
    {
        /**
         * inputWeight set equal to 0
         * inputHeight set equal to 0
         * actual output to be calculated
         * expexted equal to 0
         */

        float inputWeight= 0,inputHeight =0;
        float output;
        float expected = (float) 0;
        double delta = .1;
        BloodVolumeCalculaor bloodVolumeCalculaor = new BloodVolumeCalculaor();
        output = bloodVolumeCalculaor.calculateBloodVolume(inputHeight,inputWeight);

        assertEquals(expected,output,delta);
    }

    /**
     * This is the method for test case 2 when one of the input as in weight is invalid
     */
    @Test
    public void testCase2()
    {
        /**
         * inputWeight set equal to 0
         * inputHeight set equal to 183
         * actual output to be calculated
         * output expexted equal to 0
         */

        float inputWeight= 0,inputHeight =183;
        float output;
        float expected = (float) 0;
        double delta = .1;
        BloodVolumeCalculaor bloodVolumeCalculaor = new BloodVolumeCalculaor();
        output = bloodVolumeCalculaor.calculateBloodVolume(inputHeight,inputWeight);

        assertEquals(expected,output,delta);
    }

    /**
     * This is the method for test case 3 when one of the input as in height is invalid
     */
    @Test
    public void testCase3()
    {
        /**
         * inputWeight set equal to 65
         * inputHeight set equal to -10
         * actual output to be calculated
         * output expexted equal to 0
         */

        float inputWeight= 65,inputHeight =-10;
        float output;
        float expected = (float) 0;
        double delta = .1;
        BloodVolumeCalculaor bloodVolumeCalculaor = new BloodVolumeCalculaor();
        output = bloodVolumeCalculaor.calculateBloodVolume(inputHeight,inputWeight);

        assertEquals(expected,output,delta);
    }

    /**
     * This is the method for test case 4 when the inputs are all valid
     */
    @Test
    public void testCase4()
    {
        /**
         * inputWeight set equal to 70
         * inputHeight set equal to 178
         * actual output to be calculated
         * output expexted equal to 4926.661
         */

        float inputWeight= 70,inputHeight =178;
        float output;
        float expected = (float) 4926.661;
        double delta = .1;
        BloodVolumeCalculaor bloodVolumeCalculaor = new BloodVolumeCalculaor();
        output = bloodVolumeCalculaor.calculateBloodVolume(inputWeight, inputHeight);

        assertEquals(expected,output,delta);
    }

}