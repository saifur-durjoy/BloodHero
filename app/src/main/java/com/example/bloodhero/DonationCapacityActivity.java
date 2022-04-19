package com.example.bloodhero;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class DonationCapacityActivity extends AppCompatActivity
{
    EditText userBloodVolume;
    TextView resultCapacity;
    Button calculateButtonCapacity,buttonRedirectBmi ;

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