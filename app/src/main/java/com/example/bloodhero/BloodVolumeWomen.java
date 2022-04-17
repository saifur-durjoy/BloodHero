package com.example.bloodhero;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toolbar;

import de.hdodenhof.circleimageview.CircleImageView;

public class BloodVolumeWomen extends AppCompatActivity {

    Toolbar toolbarWomen;
    CircleImageView profileImage;
    TextView idnumber, resultWomen;
    EditText weightWomen, heightWomen;
    Button calculateWomen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_volume_women);

        weightWomen = findViewById(R.id.weightWomen);
        heightWomen = findViewById(R.id.heightWomen);
        calculateWomen = findViewById(R.id.calculateButtonWomen);
        calculateWomen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculateBloodVolume();
            }
        });
        resultWomen = findViewById(R.id.resultWomen);

    }

    private void calculateBloodVolume() {
        String userProvidedWeight = weightWomen.getText().toString();
        String userProvidedHeight = heightWomen.getText().toString();

        float weightValue = (float) (Float.parseFloat(userProvidedWeight));
        float heightValue = Float.parseFloat(userProvidedHeight);

//       (0.006012 x Height{\displaystyle ^{3}}^{3})+(14.6 x Weight)+604
        float bloodVolume = (float) ((0.000356*heightValue*heightValue*heightValue)+(33*weightValue)+183);

        String calculation = "Blood Volume: " +bloodVolume +" ml";
        resultWomen.setText(calculation);
    }
}