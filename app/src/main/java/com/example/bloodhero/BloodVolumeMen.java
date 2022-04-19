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
 * This Class provides with the calculation of male blood volume.
 * @author Saifur Rahman Durjoy (Saifur-Durjoy)
 * @since 2022
 */
public class BloodVolumeMen extends AppCompatActivity
{
    /**
     * Two edittext instances named weightMen, heightMen to read input of height and weight from users
     */
    EditText weightMen, heightMen;
    /**
     * One Textview instance named resultMen, to display the output of calculated blood volume
     */
    TextView resultMen;
    /**
     * One Button instance named calculateButtonMen to start the calculation of blood volume on click
     */
    Button calculateButtonMen;

    /**
     * Auto generated Java Class Built in method.
     * This method is used for establishing the connection between front end and back end.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_volume_men);
        /**
         * connecting the instances declared above by with the front end (i.e. from layout file)
         */
        weightMen = findViewById(R.id.weightMen);
        heightMen = findViewById(R.id.heightMen);
        resultMen = findViewById(R.id.resultMen);
        calculateButtonMen = findViewById(R.id.calculateButtonMen);
        /**
         * on clicking calculateButtonMen button to start generating a new activity to calculate blood volume
         */
        calculateButtonMen.setOnClickListener(new View.OnClickListener()
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
        String userProvidedWeight = weightMen.getText().toString();
        String userProvidedHeight = heightMen.getText().toString();

        /**
         * parses the value to float number
         */
        float weightValue = Float.parseFloat(userProvidedWeight);
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
            resultMen.setText(calculation);
        }

        /**
         * else blood volume gets calculated
         * using the formula (0.0003668*height^3 + 32.2 weight + 604)
         * and gets displayed to the user
         */
        else
        {
            bloodVolume = (float) ((0.0003668 * heightValue * heightValue * heightValue) + (32.2 * weightValue) + 604);
            String calculation = "Blood volume:" +bloodVolume+ " ml";
            resultMen.setText(calculation);
        }
    }
}