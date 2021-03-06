package com.example.aidlserver;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;

import com.example.aidlserver.ICalcService;
import com.example.aidlserver.ICalcServiceCallback;

public class MyService extends Service {

    ICalcServiceCallback mCallback;
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
        return binder;
    }
}