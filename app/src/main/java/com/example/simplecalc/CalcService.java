package com.example.simplecalc;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class CalcService extends Service {
    private IBinder mBinder = new MyBinder();
    private ServiceCallbacks serviceCallbacks;

    public class MyBinder extends Binder {
        public CalcService getService(){
            return CalcService.this;
        }
    }

    public CalcService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    public String getResult(String mode){
        float[] inputs = serviceCallbacks.get_inputs();
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

    public void setCallbacks(ServiceCallbacks callbacks) {
        serviceCallbacks = callbacks;
    }
}