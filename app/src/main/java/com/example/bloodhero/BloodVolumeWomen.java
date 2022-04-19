package com.example.bloodhero;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toolbar;

import de.hdodenhof.circleimageview.CircleImageView;
/**
 * This Class provides with the calculation of female blood volume.
 * @author Saifur Rahman Durjoy (Saifur-Durjoy)
 * @since 2022
 */
public class BloodVolumeWomen extends AppCompatActivity
{
    /**
     * One Textview instance named resultWomen, to display the output of calculated blood volume
     */
    TextView resultWomen;
    /**
     * Two edittext instances named weightWomen, heightWomen to read input of height and weight from users
     */
    EditText weightWomen, heightWomen;
    /**
     * One Button instance named calculateButtonWomen to start the calculation of blood volume on click
     */
    Button calculateWomen;

    /**
     * Auto generated Java Class Built in method.
     * This method is used for establishing the connection between front end and back end.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_volume_women);

/**
 * connecting the instances declared above by with the front end (i.e. from layout file)
 */
        weightWomen = findViewById(R.id.weightWomen);
        heightWomen = findViewById(R.id.heightWomen);
        calculateWomen = findViewById(R.id.calculateButtonWomen);
        resultWomen = findViewById(R.id.resultWomen);

        /**
         * on clicking calculateButtonWomen button to start generating a new activity to calculate blood volume
         */
        calculateWomen.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                calculateBloodVolume();
            }
        });
    }

    /**
     * This method calculates Blood Volume and sends it to display
     */
    private void calculateBloodVolume()
    {
        /**
         * takes the user input values and assigns to thes variables
         */
        String userProvidedWeight = weightWomen.getText().toString();
        String userProvidedHeight = heightWomen.getText().toString();

        /**
         * parses the value to float number
         */
        float weightValue = (float) (Float.parseFloat(userProvidedWeight));
        float heightValue = Float.parseFloat(userProvidedHeight);

        float bloodVolume;

        /**
         * if weight or height is less than 0 than it sets  blood volume to 0
         * and gets displayed to the user
         */
        if(weightValue<=0 || heightValue <=0)
        {
            bloodVolume = 0;
            String calculation ="Blood Volume :"+bloodVolume+ " Invalid input!";
            resultWomen.setText(calculation);
        }

        /**
         * else blood volume gets calculated
         * using the formula (0.000356*height^3 + 33 weight + 183)
         * and gets displayed to the user
         */
        else
        {
            bloodVolume = (float) (float) ((0.000356*heightValue*heightValue*heightValue)+(33*weightValue)+183);
            String calculation = "Blood volume:" +bloodVolume+ " ml";
            resultWomen.setText(calculation);
        }
    }
}