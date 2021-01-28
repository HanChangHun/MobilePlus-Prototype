package com.example.simplecalc;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class CalcService extends Service {
    private float num1,num2;

    public void setNum1(float num1) {
        this.num1 = num1;
    }

    public void setNum2(float num2) {
        this.num2 = num2;
    }

    private IBinder mBinder = new MyBinder();

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

    public String getAddResult(){
        return Float.toString(num1+num2);
    }

    public String getSubResult() {
        return Float.toString(num1-num2);
    }

    public String getMulResult() {
        return Float.toString(num1*num2);
    }

    public String getDivResult() {
        return Float.toString(num1/num2);
    }
}