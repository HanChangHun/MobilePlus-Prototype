package com.example.simplecalc;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.util.Log;


public class CalcIntentService extends IntentService {

    public CalcIntentService() {
        super("CalcIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        float num1 = intent.getFloatExtra("num1",0);
        float num2 = intent.getFloatExtra("num2",0);
        if("ADD".equals(intent.getAction())){
            Log.d("My Intent Service", "intent service working " + (num1+num2));
        } else if("SUB".equals(intent.getAction())){
            Log.d("My Intent Service", "intent service working " + (num1-num2));
        } else if("MUL".equals(intent.getAction())){
            Log.d("My Intent Service", "intent service working " + (num1*num2));
        } else if("DIV".equals(intent.getAction())){
            Log.d("My Intent Service", "intent service working " + (num1/num2));
        }
    }
}