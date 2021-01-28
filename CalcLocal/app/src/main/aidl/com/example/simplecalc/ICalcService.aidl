package com.example.simplecalc;
import com.example.simplecalc.ICalcServiceCallback;

interface ICalcService {
    boolean addCallback(ICalcServiceCallback callback);
    boolean removeCallback();
    String getResult(String mode);
}