// ICalcService.aidl
package com.example.aidlserver;

// Declare any non-default types here with import statements

interface ICalcService {
    String getResult(float input1, float input2, String mode);
}