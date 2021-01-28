package com.example.simplecalc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public float[] get_inputs(){
        float[] inputs = new float[2];

        EditText input1_id = findViewById(R.id.input1_id);
        EditText input2_id = findViewById(R.id.input2_id);
        inputs[0] = Float.parseFloat(String.valueOf(input1_id.getText()));
        inputs[1] = Float.parseFloat(String.valueOf(input2_id.getText()));

        return inputs;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView result_id;
        result_id = findViewById(R.id.result_id);
    }

    public void add_fun(View view) {
        float[] inputs = get_inputs();
        Intent intent = new Intent(this, CalcIntentService.class);
        intent.setAction("ADD");
        intent.putExtra("num1", inputs[0]);
        intent.putExtra("num2", inputs[1]);
        startService(intent);
    }
    public void sub_fun(View view) {
        float[] inputs = get_inputs();
        Intent intent = new Intent(this, CalcIntentService.class);
        intent.setAction("SUB");
        intent.putExtra("num1", inputs[0]);
        intent.putExtra("num2", inputs[1]);
        startService(intent);
    }
    public void mul_fun(View view) {
        float[] inputs = get_inputs();
        Intent intent = new Intent(this, CalcIntentService.class);
        intent.setAction("MUL");
        intent.putExtra("num1", inputs[0]);
        intent.putExtra("num2", inputs[1]);
        startService(intent);
    }
    public void div_fun(View view) {
        float[] inputs = get_inputs();
        Intent intent = new Intent(this, CalcIntentService.class);
        intent.setAction("DIV");
        intent.putExtra("num1", inputs[0]);
        intent.putExtra("num2", inputs[1]);
        startService(intent);
    }


}