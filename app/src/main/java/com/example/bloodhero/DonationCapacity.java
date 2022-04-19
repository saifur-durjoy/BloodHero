package com.example.bloodhero;

public class DonationCapacity {

    public float calculateDonationCapacity(float userProvidedBloodVolume){


        float bloodVolumeValue = userProvidedBloodVolume;

        float capacity = (float) (0.13*bloodVolumeValue);


        if (bloodVolumeValue<0)
        {
            capacity = -1;
            return capacity;
        }

        else
        {
            return capacity;
        }


    }
}
