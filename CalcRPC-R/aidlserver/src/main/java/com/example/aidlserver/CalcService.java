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
            int MY_UID = Binder.getCallingUid();
            Log.d(TAG, "UID: " + MY_UID + " CalcService: addCallback: called");

            mCallback = callback;
            return true;
        }

        @Override
        public boolean removeCallback() throws RemoteException {
            int MY_UID = Binder.getCallingUid();
            Log.d(TAG, "UID: " + MY_UID + " CalcService: removeCallback: called");

            mCallback = null;
            return true;
        }

        @Override
        public String getResult(String mode) throws RemoteException {
            int MY_UID = Binder.getCallingUid();
            Log.d(TAG, "UID: " + MY_UID + " CalcService: getResult: Called");

            float[] inputs = mCallback.get_inputs();
            if (inputs == null) {
                return "Check Inputs";
            }
            if ("ADD".equals(mode)) {
                return Float.toString(inputs[0] + inputs[1]);
            } else if ("SUB".equals(mode)) {
                return Float.toString(inputs[0] - inputs[1]);
            } else if ("MUL".equals(mode)) {
                return Float.toString(inputs[0] * inputs[1]);
            } else if ("DIV".equals(mode)) {
                return Float.toString(inputs[0] / inputs[1]);
            }
            return "Error!";
        }
    };

    @Override
    public IBinder onBind(Intent intent) {
        int MY_UID = Binder.getCallingUid();
        Log.d(TAG, "UID: " + MY_UID + " CalcService: onBind: MY_UID: " + MY_UID);

        return binder;
    }
}
