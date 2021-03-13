package android.hardware.gnss.V2_0;

import android.hardware.gnss.V1_0.IGnssDebug;
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

public abstract class Stub extends HwBinder implements IGnssDebug {
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
    return (ArrayList)new ArrayList<>(Arrays.asList((byte[])new byte[][] { { 
              -74, 112, -70, -30, -85, -123, 23, 51, 98, -112, 
              83, 46, 54, 69, 2, -76, -37, -111, 32, 52, 
              13, 117, 71, 76, -52, -124, 66, -79, -79, 93, 
              106, -73 }, { 
              69, 66, 18, 43, -106, -5, -14, 113, 1, -53, 
              -126, 34, -70, -5, 118, -25, -56, -48, 50, -39, 
              119, -35, 16, 88, -19, -40, -27, -120, 28, -91, 
              117, 47 }, { 
              -20, Byte.MAX_VALUE, -41, -98, -48, 45, -6, -123, -68, 73, 
              -108, 38, -83, -82, 62, -66, 35, -17, 5, 36, 
              -13, -51, 105, 87, 19, -109, 36, -72, 59, 24, 
              -54, 76 } }));
  }
  
  public final ArrayList<String> interfaceChain() {
    return new ArrayList<>(Arrays.asList(new String[] { "android.hardware.gnss@2.0::IGnssDebug", "android.hardware.gnss@1.0::IGnssDebug", "android.hidl.base@1.0::IBase" }));
  }
  
  public final String interfaceDescriptor() {
    return "android.hardware.gnss@2.0::IGnssDebug";
  }
  
  public final boolean linkToDeath(IHwBinder.DeathRecipient paramDeathRecipient, long paramLong) {
    return true;
  }
  
  public final void notifySyspropsChanged() {
    HwBinder.enableInstrumentation();
  }
  
  public void onTransact(int paramInt1, HwParcel paramHwParcel1, HwParcel paramHwParcel2, int paramInt2) throws RemoteException {
    IGnssDebug.DebugData debugData1;
    if (paramInt1 != 1) {
      ArrayList<String> arrayList;
      if (paramInt1 != 2) {
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
      arrayList.enforceInterface("android.hardware.gnss@2.0::IGnssDebug");
      debugData1 = getDebugData_2_0();
      paramHwParcel2.writeStatus(0);
      debugData1.writeToParcel(paramHwParcel2);
      paramHwParcel2.send();
    } 
    debugData1.enforceInterface("android.hardware.gnss@1.0::IGnssDebug");
    IGnssDebug.DebugData debugData = getDebugData();
    paramHwParcel2.writeStatus(0);
    debugData.writeToParcel(paramHwParcel2);
    paramHwParcel2.send();
  }
  
  public final void ping() {}
  
  public IHwInterface queryLocalInterface(String paramString) {
    return (IHwInterface)("android.hardware.gnss@2.0::IGnssDebug".equals(paramString) ? this : null);
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


/* Location:              /home/chun/Desktop/temp/!/android/hardware/gnss/V2_0/IGnssDebug$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */