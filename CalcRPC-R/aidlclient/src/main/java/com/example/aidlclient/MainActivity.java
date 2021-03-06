package com.example.aidlclient;

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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aidlserver.ICalcService;
import com.example.aidlserver.ICalcServiceCallback;

public class MainActivity extends AppCompatActivity {
    String TAG = "201521037";

    String no_service = "No Service.";
    TextView result_id;

    private ICalcService iCalcService;
    private boolean bound = false;

    private final ICalcServiceCallback callback = new ICalcServiceCallback.Stub() {
        @Override
        public float[] get_inputs() {
            int MY_UID = Binder.getCallingUid();
            Log.d(TAG, "UID: " + MY_UID + " MainActivity: callback: get_inputs: Called");
            float[] inputs = new float[2];

            EditText input1_id = findViewById(R.id.input1_id);
            EditText input2_id = findViewById(R.id.input2_id);
            try {
                inputs[0] = Float.parseFloat(String.valueOf(input1_id.getText()));
                inputs[1] = Float.parseFloat(String.valueOf(input2_id.getText()));
                return inputs;
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "Fill in all the blanks.", Toast.LENGTH_SHORT).show();
                return null;
            }
        }
    };

    private final ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            int MY_UID = Binder.getCallingUid();
            Log.d(TAG, "UID: " + MY_UID + " MainActivity: onServiceConnected: Called");
            iCalcService = ICalcService.Stub.asInterface(service);
            try {
                iCalcService.addCallback(callback);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            bound = true;
            Log.d(TAG, "UID: " + MY_UID + " MainActivity: onServiceConnected: iCalcService: " + iCalcService);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            int MY_UID = Binder.getCallingUid();
            Log.d(TAG, "UID: " + MY_UID + " MainActivity: onServiceDisconnected: Called");
            iCalcService = null;
            bound = false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        int MY_UID = Binder.getCallingUid();
        Log.d(TAG, "UID: " + MY_UID + " MainActivity: onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        result_id = findViewById(R.id.result_id);

        Button bindButton = (Button) findViewById(R.id.bind_btn);
        bindButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int MY_UID = Binder.getCallingUid();
                Log.d(TAG, "UID: " + MY_UID + " MainActivity: onCreate: onClick: bindButton");
                if (!bound) {
                    Intent intent = new Intent().setClassName("com.example.aidlserver",
                            "com.example.aidlserver.CalcService");
                    boolean isBind = bindService(intent, connection, Context.BIND_AUTO_CREATE);
                    Log.d(TAG, "UID: " + MY_UID + " MainActivity: onCreate: isBind: " + isBind);
                }
            }
        });

        Button unbindButton = (Button) findViewById(R.id.unbind_btn);
        unbindButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int MY_UID = Binder.getCallingUid();
                Log.d(TAG, "UID: " + MY_UID + " MainActivity: onCreate: onClick: unbindButton");
                if (bound) {
                    unbindService(connection);
                    bound = false;
                    try {
                        iCalcService.removeCallback();
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    Log.d(TAG, "UID: " + MY_UID + " MainActivity: onCreate: bound: " + bound);
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        int MY_UID = Binder.getCallingUid();
        Log.d(TAG, "UID: " + MY_UID + " MainActivity: onStart: MY_UID: " + MY_UID);

        if (!bound) {
            Intent intent = new Intent().setClassName("com.example.aidlserver",
                    "com.example.aidlserver.CalcService");
            boolean isBind = bindService(intent, connection, Context.BIND_AUTO_CREATE);
            Log.d(TAG, "UID: " + MY_UID + " MainActivity: onStart: isBind: " + isBind);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        int MY_UID = Binder.getCallingUid();
        Log.d(TAG, "UID: " + MY_UID + " MainActivity: onStop");
        if (bound) {
            try {
                iCalcService.removeCallback();
                bound = false;
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            unbindService(connection);
            Log.d(TAG, "UID: " + MY_UID + " MainActivity: onStop: bound: " + bound);
        }
    }


    public void getAddResult(View view) throws RemoteException {
        int MY_UID = Binder.getCallingUid();
        Log.d(TAG, "UID: " + MY_UID + " MainActivity: getAddResult: Called");
        if (bound) {
            result_id.setText(iCalcService.getResult("ADD"));
        } else result_id.setText(no_service);
    }

    public void getSubResult(View view) throws RemoteException {
        int MY_UID = Binder.getCallingUid();
        Log.d(TAG, "UID: " + MY_UID + " MainActivity: getSubResult: Called");
        if (bound) {
            result_id.setText(iCalcService.getResult("SUB"));
        } else result_id.setText(no_service);
    }

    public void getMulResult(View view) throws RemoteException {
        int MY_UID = Binder.getCallingUid();
        Log.d(TAG, "UID: " + MY_UID + " MainActivity: getMulResult: Called");
        if (bound) {
            result_id.setText(iCalcService.getResult("MUL"));
        } else result_id.setText(no_service);
    }

    public void getDivResult(View view) throws RemoteException {
        int MY_UID = Binder.getCallingUid();
        Log.d(TAG, "UID: " + MY_UID + " MainActivity: getDivResult: Called");
        if (bound) {
            result_id.setText(iCalcService.getResult("DIV"));
        } else result_id.setText(no_service);
    }
}
