package com.example.bloodhero;

public class BloodVolumeCalculaor {

    public float calculateBloodVolume(float userProviderWeight, float userProvidedHeight){


        float weightValue = userProviderWeight;
        float heightValue = userProvidedHeight;

        float bloodVolume;


        if(weightValue<=0 || heightValue <=0)
        {
            bloodVolume = 0;
            return bloodVolume;
        }
        else
        {
           bloodVolume =  (float) ((0.0003668 * heightValue * heightValue * heightValue) + (32.2 * weightValue) + 604);
           return bloodVolume;
        }
    }
}


