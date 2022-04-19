package com.example.bloodhero;

import static org.junit.Assert.*;

import org.junit.Test;

public class BloodVolumeCalculaorTest {

    @Test
    public void testCase1()
    {
        float inputWeight= 0,inputHeight =0;
        float output;
        float expected = (float) 0;
        double delta = .1;
        BloodVolumeCalculaor bloodVolumeCalculaor = new BloodVolumeCalculaor();
        output = bloodVolumeCalculaor.calculateBloodVolume(inputHeight,inputWeight);

        assertEquals(expected,output,delta);
    }

    @Test
    public void testCase2()
    {
        float inputWeight= 0,inputHeight =183;
        float output;
        float expected = (float) 0;
        double delta = .1;
        BloodVolumeCalculaor bloodVolumeCalculaor = new BloodVolumeCalculaor();
        output = bloodVolumeCalculaor.calculateBloodVolume(inputHeight,inputWeight);

        assertEquals(expected,output,delta);
    }

    @Test
    public void testCase3()
    {
        float inputWeight= 65,inputHeight =0;
        float output;
        float expected = (float) 0;
        double delta = .1;
        BloodVolumeCalculaor bloodVolumeCalculaor = new BloodVolumeCalculaor();
        output = bloodVolumeCalculaor.calculateBloodVolume(inputHeight,inputWeight);

        assertEquals(expected,output,delta);
    }

    @Test
    public void testCase4()
    {
        float inputWeight= 70,inputHeight =178;
        float output;
        float expected = (float) 4926.661;
        double delta = .1;
        BloodVolumeCalculaor bloodVolumeCalculaor = new BloodVolumeCalculaor();
        output = bloodVolumeCalculaor.calculateBloodVolume(inputWeight, inputHeight);

        assertEquals(expected,output,delta);
    }
}