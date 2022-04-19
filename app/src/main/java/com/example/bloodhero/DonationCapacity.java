package com.example.bloodhero;

/**
 * This class is used for finding the Donation Capacity
 * @author Arfana Rahman_1831172042
 * @since 2022
 */
public class DonationCapacity {

    /**
     * This method is used for calculate donation capacity by using this given formula
     * @param userProvidedBloodVolume users enter his/her Blood Volume
     * @return Tests passed or failed
     */
    public float calculateDonationCapacity(float userProvidedBloodVolume){


        float bloodVolumeValue = userProvidedBloodVolume;
/**
 *  This is the formula:
 *  0.13 * Blood Volume Value
 */
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
