package com.example.simplecalc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private CalcService mService;
    private boolean mBound;
    TextView result_id;
    float[] inputs;
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

        result_id = findViewById(R.id.result_id);
    }

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            CalcService.MyBinder binder = (CalcService.MyBinder) service;
            mService = binder.getService();
            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onStart() {
        super.onStart();

        Intent intent = new Intent(this, CalcService.class);
        bindService(intent, mConnection, BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mBound) {
            unbindService(mConnection);
            mBound = false;
        }
    }

    public void getAddResult(View view) {
        if (mBound){
            inputs = get_inputs();
            mService.setNum1(inputs[0]);
            mService.setNum2(inputs[1]);
            result_id.setText(mService.getAddResult());
        }
    }
    public void getSubResult(View view) {
        if (mBound){
            inputs = get_inputs();
            mService.setNum1(inputs[0]);
            mService.setNum2(inputs[1]);
            result_id.setText(mService.getSubResult());
        }
    }
    public void getMulResult(View view) {
        if (mBound){
            inputs = get_inputs();
            mService.setNum1(inputs[0]);
            mService.setNum2(inputs[1]);
            result_id.setText(mService.getMulResult());
        }
    }
    public void getDivResult(View view) {
        if (mBound){
            inputs = get_inputs();
            mService.setNum1(inputs[0]);
            mService.setNum2(inputs[1]);
            result_id.setText(mService.getDivResult());
        }
    }
}