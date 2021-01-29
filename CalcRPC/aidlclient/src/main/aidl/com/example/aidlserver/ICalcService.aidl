// ICalcService.aidl
package com.example.aidlserver;
import com.example.aidlserver.ICalcServiceCallback;

// Declare any non-default types here with import statements

interface ICalcService {
    boolean addCallback(ICalcServiceCallback callback);
    boolean removeCallback();
    String getResult(String mode);
}