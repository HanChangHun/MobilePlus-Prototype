package com.example.simplecalc;
import com.example.simplecalc.ICalcServiceCallback;

interface ICalcService {
    boolean addCallback(ICalcServiceCallback callback);
    boolean removeCallback(ICalcServiceCallback callback);
    String getResult(ICalcServiceCallback callback, String mode);
}