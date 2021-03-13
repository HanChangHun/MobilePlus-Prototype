package android.hardware.gnss.V2_1;

import android.hardware.gnss.V1_0.IGnssMeasurementCallback;
import android.hardware.gnss.V1_1.IGnssMeasurementCallback;
import android.hardware.gnss.V2_0.IGnssMeasurementCallback;
import android.internal.hidl.base.V1_0.DebugInfo;
import android.os.HidlSupport;
import android.os.HwBinder;
import android.os.HwBlob;
import android.os.HwParcel;
import android.os.IHwBinder;
import android.os.IHwInterface;
import android.os.NativeHandle;
import android.os.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;

public abstract class Stub extends HwBinder implements IGnssMeasurement {
  public IHwBinder asBinder() {
    return (IHwBinder)this;
  }
  
  public void debug(NativeHandle paramNativeHandle, ArrayList<String> paramArrayList) {}
  
  public final DebugInfo getDebugInfo() {
    DebugInfo debugInfo = new DebugInfo();
    debugInfo.pid = HidlSupport.getPidIfSharable();
    debugInfo.ptr = 0L;
    debugInfo.arch = 0;
    return debugInfo;
  }
  
  public final ArrayList<byte[]> getHashChain() {
    byte[] arrayOfByte1 = { 
        -58, 119, 89, -11, -42, 56, 125, 39, 59, 102, 
        114, -111, Byte.MIN_VALUE, -48, 54, -112, -24, 39, -16, -74, 
        -72, -44, -31, 60, -30, -1, 66, -45, 27, 34, 
        64, 101 };
    byte[] arrayOfByte2 = { 
        26, 7, -47, 56, 62, -124, 124, 61, -21, 105, 
        110, -57, -94, -55, -29, 59, -106, -125, 119, 41, 
        69, 102, 4, 72, -96, 16, -79, Byte.MIN_VALUE, 99, -38, 
        103, -92 };
    byte[] arrayOfByte3 = { 
        -20, Byte.MAX_VALUE, -41, -98, -48, 45, -6, -123, -68, 73, 
        -108, 38, -83, -82, 62, -66, 35, -17, 5, 36, 
        -13, -51, 105, 87, 19, -109, 36, -72, 59, 24, 
        -54, 76 };
    return (ArrayList)new ArrayList<>(Arrays.asList((byte[])new byte[][] { { 
              121, 19, -95, 18, 6, -91, 119, -79, 42, -34, 
              -122, -89, -49, 63, -107, -62, 99, -100, -75, 20, 
              -48, -122, 103, 63, 39, -101, -7, -110, 56, -55, 
              -111, 126 }, arrayOfByte1, arrayOfByte2, { 
              -98, -88, -104, 123, -79, 8, -100, -116, 93, 123, 
              103, -122, 101, 117, -72, 102, -17, 81, 96, 69, 
              2, 29, -98, -4, -61, 124, 99, 82, -68, -32, 
              114, -93 }, arrayOfByte3 }));
  }
  
  public final ArrayList<String> interfaceChain() {
    return new ArrayList<>(Arrays.asList(new String[] { "android.hardware.gnss@2.1::IGnssMeasurement", "android.hardware.gnss@2.0::IGnssMeasurement", "android.hardware.gnss@1.1::IGnssMeasurement", "android.hardware.gnss@1.0::IGnssMeasurement", "android.hidl.base@1.0::IBase" }));
  }
  
  public final String interfaceDescriptor() {
    return "android.hardware.gnss@2.1::IGnssMeasurement";
  }
  
  public final boolean linkToDeath(IHwBinder.DeathRecipient paramDeathRecipient, long paramLong) {
    return true;
  }
  
  public final void notifySyspropsChanged() {
    HwBinder.enableInstrumentation();
  }
  
  public void onTransact(int paramInt1, HwParcel paramHwParcel1, HwParcel paramHwParcel2, int paramInt2) throws RemoteException {
    ArrayList<String> arrayList;
    if (paramInt1 != 1) {
      if (paramInt1 != 2) {
        if (paramInt1 != 3) {
          if (paramInt1 != 4) {
            if (paramInt1 != 5) {
              DebugInfo debugInfo;
              HwBlob hwBlob1;
              String str;
              ArrayList<byte[]> arrayList1;
              HwBlob hwBlob2;
              switch (paramInt1) {
                default:
                  return;
                case 257120595:
                  paramHwParcel1.enforceInterface("android.hidl.base@1.0::IBase");
                  notifySyspropsChanged();
                case 257049926:
                  paramHwParcel1.enforceInterface("android.hidl.base@1.0::IBase");
                  debugInfo = getDebugInfo();
                  paramHwParcel2.writeStatus(0);
                  debugInfo.writeToParcel(paramHwParcel2);
                  paramHwParcel2.send();
                case 256921159:
                  debugInfo.enforceInterface("android.hidl.base@1.0::IBase");
                  ping();
                  paramHwParcel2.writeStatus(0);
                  paramHwParcel2.send();
                case 256462420:
                  debugInfo.enforceInterface("android.hidl.base@1.0::IBase");
                  setHALInstrumentation();
                case 256398152:
                  debugInfo.enforceInterface("android.hidl.base@1.0::IBase");
                  arrayList1 = getHashChain();
                  paramHwParcel2.writeStatus(0);
                  hwBlob2 = new HwBlob(16);
                  paramInt2 = arrayList1.size();
                  hwBlob2.putInt32(8L, paramInt2);
                  hwBlob2.putBool(12L, false);
                  hwBlob1 = new HwBlob(paramInt2 * 32);
                  paramInt1 = 0;
                  while (paramInt1 < paramInt2) {
                    long l = (paramInt1 * 32);
                    byte[] arrayOfByte = arrayList1.get(paramInt1);
                    if (arrayOfByte != null && arrayOfByte.length == 32) {
                      hwBlob1.putInt8Array(l, arrayOfByte);
                      paramInt1++;
                      continue;
                    } 
                    throw new IllegalArgumentException("Array element is not of the expected length");
                  } 
                  hwBlob2.putBlob(0L, hwBlob1);
                  paramHwParcel2.writeBuffer(hwBlob2);
                  paramHwParcel2.send();
                case 256136003:
                  hwBlob1.enforceInterface("android.hidl.base@1.0::IBase");
                  str = interfaceDescriptor();
                  paramHwParcel2.writeStatus(0);
                  paramHwParcel2.writeString(str);
                  paramHwParcel2.send();
                case 256131655:
                  str.enforceInterface("android.hidl.base@1.0::IBase");
                  debug(str.readNativeHandle(), str.readStringVector());
                  paramHwParcel2.writeStatus(0);
                  paramHwParcel2.send();
                case 256067662:
                  break;
              } 
              str.enforceInterface("android.hidl.base@1.0::IBase");
              arrayList = interfaceChain();
              paramHwParcel2.writeStatus(0);
              paramHwParcel2.writeStringVector(arrayList);
              paramHwParcel2.send();
            } 
            arrayList.enforceInterface("android.hardware.gnss@2.1::IGnssMeasurement");
            paramInt1 = setCallback_2_1(IGnssMeasurementCallback.asInterface(arrayList.readStrongBinder()), arrayList.readBool());
            paramHwParcel2.writeStatus(0);
            paramHwParcel2.writeInt32(paramInt1);
            paramHwParcel2.send();
          } 
          arrayList.enforceInterface("android.hardware.gnss@2.0::IGnssMeasurement");
          paramInt1 = setCallback_2_0(IGnssMeasurementCallback.asInterface(arrayList.readStrongBinder()), arrayList.readBool());
          paramHwParcel2.writeStatus(0);
          paramHwParcel2.writeInt32(paramInt1);
          paramHwParcel2.send();
        } 
        arrayList.enforceInterface("android.hardware.gnss@1.1::IGnssMeasurement");
        paramInt1 = setCallback_1_1(IGnssMeasurementCallback.asInterface(arrayList.readStrongBinder()), arrayList.readBool());
        paramHwParcel2.writeStatus(0);
        paramHwParcel2.writeInt32(paramInt1);
        paramHwParcel2.send();
      } 
      arrayList.enforceInterface("android.hardware.gnss@1.0::IGnssMeasurement");
      close();
      paramHwParcel2.writeStatus(0);
      paramHwParcel2.send();
    } 
    arrayList.enforceInterface("android.hardware.gnss@1.0::IGnssMeasurement");
    paramInt1 = setCallback(IGnssMeasurementCallback.asInterface(arrayList.readStrongBinder()));
    paramHwParcel2.writeStatus(0);
    paramHwParcel2.writeInt32(paramInt1);
    paramHwParcel2.send();
  }
  
  public final void ping() {}
  
  public IHwInterface queryLocalInterface(String paramString) {
    return (IHwInterface)("android.hardware.gnss@2.1::IGnssMeasurement".equals(paramString) ? this : null);
  }
  
  public void registerAsService(String paramString) throws RemoteException {
    registerService(paramString);
  }
  
  public final void setHALInstrumentation() {}
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(interfaceDescriptor());
    stringBuilder.append("@Stub");
    return stringBuilder.toString();
  }
  
  public final boolean unlinkToDeath(IHwBinder.DeathRecipient paramDeathRecipient) {
    return true;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/gnss/V2_1/IGnssMeasurement$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */