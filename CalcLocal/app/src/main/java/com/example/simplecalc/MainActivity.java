package com.example.simplecalc;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    TextView result_id;

    public static final String TAG = "MainActivity";

    private boolean bound = false;
    private ICalcService iCalcService = null;

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
            Log.d(TAG, "onServiceConnected: " + name);
            iCalcService = ICalcService.Stub.asInterface(service);
            try {
                iCalcService.addCallback(callback);
                bound = true;
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d(TAG, "onServiceDisconnected: " + name);
            iCalcService = null;
            bound = false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "MainActivity: onCreate: callingUid: " + Binder.getCallingUid());

        setContentView(R.layout.activity_main);

        result_id = findViewById(R.id.result_id);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!bound) {
            Intent intent = new Intent(this, CalcService.class);
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