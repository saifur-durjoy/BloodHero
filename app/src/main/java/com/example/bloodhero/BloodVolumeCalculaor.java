package com.example.bloodhero;

/**
 * This Class represent the task of Blood Volume Calculation
 * @author Saifur Rahman Durjoy (Saifur-Durjoy)
 * @since 2022
 */

public class BloodVolumeCalculaor {

    /**
     * This method returns the calculated Blood Volume
     * @param userProvidedHeight this takes input of height in CM
     * @param userProviderWeight this takes input of weight in KG
     * @return bloodVolume calculated blood volume
     *
     */

    public float calculateBloodVolume(float userProviderWeight, float userProvidedHeight)
    {
        /**
         * @param weightValue set equal to user weigt
         * @param heightValue set equal to user height
         */

        float weightValue = userProviderWeight;
        float heightValue = userProvidedHeight;

        float bloodVolume;

        /**
         * if weight or height is less than 0 than it sets  blood volume to 0
         */

        if(weightValue<=0 || heightValue <=0)
        {
            bloodVolume = 0;
            return bloodVolume;
        }
        /**
         * else blood volume gets calculated
         * using the formula (0.0003668*height^3 + 32.2 weight + 604)
         */

        else
        {
           bloodVolume =  (float) ((0.0003668 * heightValue * heightValue * heightValue) + (32.2 * weightValue) + 604);
           return bloodVolume;
        }
    }
}


