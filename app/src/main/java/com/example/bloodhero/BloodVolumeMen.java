package com.example.bloodhero;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toolbar;

import de.hdodenhof.circleimageview.CircleImageView;

public class BloodVolumeMen extends AppCompatActivity
{
    EditText weightMen, heightMen;
    TextView resultMen;
    Button calculateButtonMen;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_volume_men);

        weightMen = findViewById(R.id.weightMen);
        heightMen = findViewById(R.id.heightMen);
        resultMen = findViewById(R.id.resultMen);
        calculateButtonMen = findViewById(R.id.calculateButtonMen);
        calculateButtonMen.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                calculateBloodVolume();
            }
        });
    }

    private void calculateBloodVolume()
    {
        String userProvidedWeight = weightMen.getText().toString();
        String userProvidedHeight = heightMen.getText().toString();

        float weightValue = Float.parseFloat(userProvidedWeight);
        float heightValue = Float.parseFloat(userProvidedHeight);

        float bloodVolume;

        if(weightValue<=0 || heightValue <=0)
        {
            bloodVolume = 0;
            String calculation ="Blood Volume :"+bloodVolume+ " Invalid input!";
            resultMen.setText(calculation);
        }
        else
        {
            bloodVolume = (float) ((0.0003668 * heightValue * heightValue * heightValue) + (32.2 * weightValue) + 604);
            String calculation = "Blood volume:" +bloodVolume+ " ml";
            resultMen.setText(calculation);
        }
    }
}