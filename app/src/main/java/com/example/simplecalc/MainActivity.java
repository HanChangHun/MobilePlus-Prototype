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

public class MainActivity extends AppCompatActivity implements ServiceCallbacks {
    private CalcService mService;
    private boolean mBound;
    TextView result_id;
    @Override
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
            mService.setCallbacks(MainActivity.this);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mBound = false;
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
            mService.setCallbacks(null);
            unbindService(mConnection);
            mBound = false;
        }
    }

    public void getAddResult(View view) {
        if (mBound){
            result_id.setText(mService.getResult("ADD"));
        }
    }
    public void getSubResult(View view) {
        if (mBound){
            result_id.setText(mService.getResult("SUB"));
        }
    }
    public void getMulResult(View view) {
        if (mBound){
            result_id.setText(mService.getResult("MUL"));
        }
    }
    public void getDivResult(View view) {
        if (mBound){
            result_id.setText(mService.getResult("DIV"));
        }
    }
}