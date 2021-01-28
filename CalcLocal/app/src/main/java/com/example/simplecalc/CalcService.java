package com.example.simplecalc;

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

    ICalcServiceCallback mCallback;
    List<ICalcServiceCallback> listeners = new ArrayList<>();
    private Binder binder = new ICalcService.Stub() {
        @Override
        public boolean addCallback(ICalcServiceCallback callback) throws RemoteException {
            mCallback = callback;
            return true;
        }

        @Override
        public boolean removeCallback() throws RemoteException {
            mCallback = null;
            return true;
        }

        @Override
        public String getResult(String mode) throws RemoteException {
            float[] inputs = mCallback.get_inputs();
            if (mode.equals("ADD")) {
                return Float.toString(inputs[0] + inputs[1]);
            }
            else if (mode.equals("SUB")){
                return Float.toString(inputs[0] - inputs[1]);
            }
            else if (mode.equals("MUL")){
                return Float.toString(inputs[0] * inputs[1]);
            }
            else if (mode.equals("DIV")){
                return Float.toString(inputs[0] / inputs[1]);
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