package com.example.unitconverter;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //Get Values and Button
        EditText editTextValue = findViewById(R.id.editTextValue);
        Button convert = findViewById(R.id.convert);


        //Spinner code
        Spinner to_spinner = findViewById(R.id.to_spinner);
        Spinner from_spinner = findViewById(R.id.from_spinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.units, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        to_spinner.setAdapter(adapter);
        from_spinner.setAdapter((adapter));

        // button click
        convert.setOnClickListener(v -> {
            //User selection
            String input = editTextValue.getText().toString();
            String from = from_spinner.getSelectedItem().toString();
            String to = to_spinner.getSelectedItem().toString();
            //Function call
            convertUnits(input, from, to);
        });
    }

    @SuppressLint("DefaultLocale")
    private void convertUnits(String input, String from, String to)
    {
        TextView viewResult = findViewById(R.id.viewResult);
        double value = Double.parseDouble(input);
        double result = 0.0;

        switch (from){
            case "Inch":
                switch(to){
                    case "Cm":
                        result = value * 2.54;
                        break;
                    case "Foot":
                        result = value / 12;
                        break;
                    case "Yard":
                        result = value / 36;
                        break;
                    case "Mile":
                        result = value / 63360;
                        break;
                } break;
            case "Foot":
                switch(to){
                    case "Inch":
                        result = value * 12;
                        break;
                    case "Cm":
                        result = value * 30.48;
                        break;
                    case "Yard":
                        result = value / 3;
                        break;
                    case "Mile":
                        result = value / 5280;
                        break;
                }break;
            case "Yard":
                switch(to){
                    case "Inch":
                        result = value * 36;
                        break;
                    case "Cm":
                        result = value * 91.44;
                        break;
                    case "Foot":
                        result = value * 3;
                        break;
                    case "Mile":
                        result = value / 1760;
                        break;
                }break;
            case "Mile":
                switch(to){
                    case "Inch":
                        result = value * 63360;
                        break;
                    case "Cm":
                        result = value * 160900;
                        break;
                    case "Foot":
                        result = value * 5280;
                        break;
                    case "Yard":
                        result = value * 1760;
                        break;
                    case "Km":
                        result = value * 1.609;
                        break;
                }break;
            case "Pound":
                switch(to){
                    case "Kg":
                        result = value / 2.205;
                        break;
                    case "Ounce":
                        result = value * 16;
                        break;
                    case "Ton":
                        result = value / 2000;
                        break;
                }break;
            case "Ounce":
                switch(to){
                    case "G":
                        result = value * 28.35;
                        break;
                    case "Pound":
                        result = value / 16;
                        break;
                    case "Ton":
                        result = value / 32000;
                        break;
                }break;
            case "Ton":
                switch(to){
                    case "Kg":
                        result = value * 1000;
                        break;
                    case "Pound":
                        result = value * 2205;
                        break;
                    case "Ounce":
                        result = value * 32000;
                        break;
                }break;
            case "Celsius":
                switch(to){
                    case "Fahrenheit":
                        result = (value * 1.8) + 32;
                        break;
                    case "Kelvin":
                        result = value + 273.15;
                        break;
                }break;
            case "Fahrenheit":
                if (to.equals("Celsius")) {
                    result = (value - 32) / 1.8;
                }
                break;
            case "Kelvin":
                if (to.equals("Celsius")) {
                    result = value - 273.15;
                }
                break;

        }


        viewResult.setText(String.format("Result: %s", result));

    }

}