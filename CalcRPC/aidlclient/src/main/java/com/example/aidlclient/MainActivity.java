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

import com.example.aidlserver.ICalcService;
import com.example.aidlserver.ICalcServiceCallback;

public class MainActivity extends AppCompatActivity {
    TextView result_id;

    private ICalcService iCalcService;
    private boolean bound = false;

    private ICalcServiceCallback callback = new ICalcServiceCallback.Stub() {
        @Override
        public float[] get_inputs() {
            float[] inputs = new float[2];

            EditText input1_id = findViewById(R.id.input1_id);
            EditText input2_id = findViewById(R.id.input2_id);
            inputs[0] = Float.parseFloat(String.valueOf(input1_id.getText()));
            inputs[1] = Float.parseFloat(String.valueOf(input2_id.getText()));

            return inputs;
        }
    };

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            iCalcService = ICalcService.Stub.asInterface(service);
            try {
                iCalcService.addCallback(callback);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            iCalcService = null;
            bound = false;
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
            try {
                iCalcService.removeCallback();
                bound = false;
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            unbindService(connection);
        }
    }


    public void getAddResult(View view) throws RemoteException {
        if (bound) {
            result_id.setText(iCalcService.getResult("ADD"));
        }
    }

    public void getSubResult(View view) throws RemoteException {
        if (bound) {
            result_id.setText(iCalcService.getResult("SUB"));
        }
    }

    public void getMulResult(View view) throws RemoteException {
        if (bound) {
            result_id.setText(iCalcService.getResult("MUL"));
        }
    }

    public void getDivResult(View view) throws RemoteException {
        if (bound) {
            result_id.setText(iCalcService.getResult("DIV"));
        }
    }
}