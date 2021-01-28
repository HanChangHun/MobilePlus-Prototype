// ICalcService.aidl
package com.example.calcserver;
import com.example.calcserver.ICalcServiceCallback;

interface ICalcService {
    boolean addCallback(ICalcServiceCallback callback);
    boolean removeCallback(ICalcServiceCallback callback);
    String getResult(float input1, float input2, String mode);
}