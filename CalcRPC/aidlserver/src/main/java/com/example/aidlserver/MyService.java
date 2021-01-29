package com.example.aidlserver;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;

import com.example.aidlserver.ICalcService;

public class MyService extends Service {

    private Binder binder = new ICalcService.Stub(){
        @Override
        public String getResult(float inputs1, float inputs2, String mode) throws RemoteException {
            if (mode.equals("ADD")) {
                return Float.toString(inputs1 + inputs2);
            }
            else if (mode.equals("SUB")){
                return Float.toString(inputs1 - inputs2);
            }
            else if (mode.equals("MUL")){
                return Float.toString(inputs1 * inputs2);
            }
            else if (mode.equals("DIV")){
                return Float.toString(inputs1 / inputs2);
            }
            else {
                return null;
            }
        }
    };


    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }
}