package com.example.bloodhero;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * This class is used for Checking whether a person is eligible for Blood donation.
 * CSE327.6 Project java documentation
 * @author Arfana Rahman_1831172042
 * @since 2022
 */
public class DonationCapacityActivity extends AppCompatActivity
{
    EditText userBloodVolume;
    TextView resultCapacity;
    Button calculateButtonCapacity,buttonRedirectBmi ;

    /**
     * This method is used for Calculate Donation Capacity
     * @param savedInstanceState The Capacity of a person's donation
     */

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation_capacity);

        userBloodVolume = findViewById(R.id.bloodVolumeCapacity);
        resultCapacity = findViewById(R.id.resultCapacity);
        calculateButtonCapacity = findViewById(R.id.calculateButtonCapacity);
        calculateButtonCapacity.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                calculateDonationCapacity();
            }
        });
        buttonRedirectBmi = findViewById(R.id.buttonToBloodvolume);
        buttonRedirectBmi.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                openBloodVolumeTab();
            }
        });
    }

    /**
     * Open the Blood Volume Tab If a person don't know his/her Blood Volume.
     */
    private void openBloodVolumeTab()
    {
        Intent intent = new Intent(DonationCapacityActivity.this, BloodVolume.class);
        startActivity(intent);
    }

    private void calculateDonationCapacity()
    {
        String userProvidedBloodVolume = userBloodVolume.getText().toString();

        float bloodVolumeValue = Float.parseFloat(userProvidedBloodVolume);

        if(bloodVolumeValue<=0)
        {
            float capacity = 0;
            String calculation = +capacity +"\nInvalid Blood Volume input ";
        }
        else
        {
            float capacity = (float) (0.13 * bloodVolumeValue);
            String calculation = "Eligible Blood Volume you can Donate: " + capacity + " ml";
            resultCapacity.setText(calculation);
        }


    }
}