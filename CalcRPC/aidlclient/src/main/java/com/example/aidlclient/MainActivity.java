package com.example.aidlclient;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.aidllib.ICalcService;

public class MainActivity extends AppCompatActivity {
    TextView result_id;

    private ICalcService iCalcService;
    private boolean bound = false;

    public float[] get_inputs() {
        float[] inputs = new float[2];

        EditText input1_id = findViewById(R.id.input1_id);
        EditText input2_id = findViewById(R.id.input2_id);
        inputs[0] = Float.parseFloat(String.valueOf(input1_id.getText()));
        inputs[1] = Float.parseFloat(String.valueOf(input2_id.getText()));

        return inputs;
    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            iCalcService = ICalcService.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            iCalcService = null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        result_id = findViewById(R.id.result_id);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (!bound) {
            Intent intent = new Intent("com.example.aidlserver.MY_SERVICE");
            intent.setPackage("com.example.aidlserver");
            bindService(intent, connection, Context.BIND_AUTO_CREATE);
            bound = true;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (bound) {
            bound = false;
            unbindService(connection);
        }
    }


    public void getAddResult(View view) throws RemoteException {
        if (bound) {
            float[] inputs = get_inputs();
            result_id.setText(iCalcService.getResult(inputs[0], inputs[1], "ADD"));
        }
    }
    public void getSubResult(View view) throws RemoteException {
        if (bound) {
            float[] inputs = get_inputs();
            result_id.setText(iCalcService.getResult(inputs[0], inputs[1], "SUB"));
        }
    }

    public void getMulResult(View view) throws RemoteException {
        if (bound) {
            float[] inputs = get_inputs();
            result_id.setText(iCalcService.getResult(inputs[0], inputs[1], "MUL"));
        }
    }

    public void getDivResult(View view) throws RemoteException {
        if (bound) {
            float[] inputs = get_inputs();
            result_id.setText(iCalcService.getResult(inputs[0], inputs[1], "DIV"));
        }
    }
}