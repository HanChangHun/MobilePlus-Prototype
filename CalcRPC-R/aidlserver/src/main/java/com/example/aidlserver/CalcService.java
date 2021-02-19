package com.example.aidlserver;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

public class CalcService extends Service {
    String TAG = "201521037";
    ICalcServiceCallback mCallback;
    private Binder binder = new ICalcService.Stub() {
        @Override
        public boolean addCallback(ICalcServiceCallback callback) throws RemoteException {
            Log.d(TAG, "addCallback: called");
            mCallback = callback;
            return true;
        }

        @Override
        public boolean removeCallback() throws RemoteException {
            Log.d(TAG, "removeCallback: called");
            mCallback = null;
            return true;
        }

        @Override
        public String getResult(String mode) throws RemoteException {
            Log.d(TAG, "getResult: Called");
            float[] inputs = mCallback.get_inputs();
            if (inputs == null) {
                return "Check Inputs";
            }
            if (mode.equals("ADD")) {
                return Float.toString(inputs[0] + inputs[1]);
            } else if (mode.equals("SUB")) {
                return Float.toString(inputs[0] - inputs[1]);
            } else if (mode.equals("MUL")) {
                return Float.toString(inputs[0] * inputs[1]);
            } else if (mode.equals("DIV")) {
                return Float.toString(inputs[0] / inputs[1]);
            } else {
                return "Error!";
            }
        }
    };

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind: called");
        return binder;
    }
}