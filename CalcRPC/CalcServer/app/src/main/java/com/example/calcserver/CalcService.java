package com.example.calcserver;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class CalcService extends Service {
    float[] inputs;
    public static final String TAG = "CalcService";

    List<ICalcServiceCallback> listeners = new ArrayList<>();
    private Binder binder = new ICalcService.Stub() {
        @Override
        public boolean addCallback(ICalcServiceCallback callback) throws RemoteException {
            Log.d(TAG, "Add callback : " + callback);
            listeners.add(callback);
            return true;
        }

        @Override
        public boolean removeCallback(ICalcServiceCallback callback) throws RemoteException {
            listeners.remove(callback);
            return true;
        }

        @Override
        public String getResult(float input1, float input2, String mode) throws RemoteException {
            if (mode.equals("ADD")) {
                return Float.toString(input1 + input2);
            }
            else if (mode.equals("SUB")){
                return Float.toString(input1 + input2);
            }
            else if (mode.equals("MUL")){
                return Float.toString(input1 + input2);
            }
            else if (mode.equals("DIV")){
                return Float.toString(input1 + input2);
            }
            else {
                return null;
            }
        }
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }
}