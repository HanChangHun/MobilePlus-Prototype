package com.example.simplecalc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public float[] get_inputs(EditText input1_id, EditText input2_id){
        float[] inputs = new float[2];
        inputs[0] = Float.parseFloat(String.valueOf(input1_id.getText()));
        inputs[1] = Float.parseFloat(String.valueOf(input2_id.getText()));
        return inputs;
    }

    TextView result_id;
    EditText input1_id;
    EditText input2_id;
    Button add_btn_id;
    Button sub_btn_id;
    Button mul_btn_id;
    Button div_btn_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result_id = findViewById(R.id.result_id);
        input1_id = findViewById(R.id.input1_id);
        input2_id = findViewById(R.id.input2_id);
        add_btn_id = findViewById(R.id.add_btn_id);
        sub_btn_id = findViewById(R.id.sub_btn_id);
        mul_btn_id = findViewById(R.id.mul_btn_id);
        div_btn_id = findViewById(R.id.div_btn_id);

        add_btn_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float[] inputs = get_inputs(input1_id, input2_id);
                float result = inputs[0] + inputs[1];
                result_id.setText(String.valueOf(result));
            }
        });
        sub_btn_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float[] inputs = get_inputs(input1_id, input2_id);
                float result = inputs[0] - inputs[1];
                result_id.setText(String.valueOf(result));
            }
        });
        mul_btn_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float[] inputs = get_inputs(input1_id, input2_id);
                float result = inputs[0] * inputs[1];
                result_id.setText(String.valueOf(result));
            }
        });
        div_btn_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float[] inputs = get_inputs(input1_id, input2_id);
                float result = inputs[0] / inputs[1];
                result_id.setText(String.valueOf(result));
            }
        });
    }
}