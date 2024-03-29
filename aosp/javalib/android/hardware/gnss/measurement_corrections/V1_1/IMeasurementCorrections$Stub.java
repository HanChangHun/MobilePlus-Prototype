package android.hardware.gnss.measurement_corrections.V1_1;

import android.hardware.gnss.measurement_corrections.V1_0.IMeasurementCorrectionsCallback;
import android.hardware.gnss.measurement_corrections.V1_0.MeasurementCorrections;
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

public abstract class Stub extends HwBinder implements IMeasurementCorrections {
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
        102, 112, -25, 120, 8, 3, -88, -58, -106, -58, 
        57, 31, -38, 85, -119, -93, 52, -79, -77, 125, 
        -57, -66, -109, -109, 121, 46, -45, 80, 53, 65, 
        54, 51 };
    byte[] arrayOfByte2 = { 
        -20, Byte.MAX_VALUE, -41, -98, -48, 45, -6, -123, -68, 73, 
        -108, 38, -83, -82, 62, -66, 35, -17, 5, 36, 
        -13, -51, 105, 87, 19, -109, 36, -72, 59, 24, 
        -54, 76 };
    return (ArrayList)new ArrayList<>(Arrays.asList((byte[])new byte[][] { arrayOfByte1, { 
              -44, -52, -115, -111, -109, 13, 90, 26, 98, -34, 
              -80, -39, 125, 57, -123, 16, -95, 21, -50, 62, 
              -34, 45, 41, 120, 115, -122, 81, -71, -48, 27, 
              17, -61 }, arrayOfByte2 }));
  }
  
  public final ArrayList<String> interfaceChain() {
    return new ArrayList<>(Arrays.asList(new String[] { "android.hardware.gnss.measurement_corrections@1.1::IMeasurementCorrections", "android.hardware.gnss.measurement_corrections@1.0::IMeasurementCorrections", "android.hidl.base@1.0::IBase" }));
  }
  
  public final String interfaceDescriptor() {
    return "android.hardware.gnss.measurement_corrections@1.1::IMeasurementCorrections";
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
              hwBlob1 = new HwBlob(16);
              paramInt2 = arrayList1.size();
              hwBlob1.putInt32(8L, paramInt2);
              hwBlob1.putBool(12L, false);
              hwBlob2 = new HwBlob(paramInt2 * 32);
              paramInt1 = 0;
              while (paramInt1 < paramInt2) {
                long l = (paramInt1 * 32);
                byte[] arrayOfByte = arrayList1.get(paramInt1);
                if (arrayOfByte != null && arrayOfByte.length == 32) {
                  hwBlob2.putInt8Array(l, arrayOfByte);
                  paramInt1++;
                  continue;
                } 
                throw new IllegalArgumentException("Array element is not of the expected length");
              } 
              hwBlob1.putBlob(0L, hwBlob2);
              paramHwParcel2.writeBuffer(hwBlob1);
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
        arrayList.enforceInterface("android.hardware.gnss.measurement_corrections@1.1::IMeasurementCorrections");
        MeasurementCorrections measurementCorrections1 = new MeasurementCorrections();
        measurementCorrections1.readFromParcel((HwParcel)arrayList);
        boolean bool2 = setCorrections_1_1(measurementCorrections1);
        paramHwParcel2.writeStatus(0);
        paramHwParcel2.writeBool(bool2);
        paramHwParcel2.send();
      } 
      arrayList.enforceInterface("android.hardware.gnss.measurement_corrections@1.0::IMeasurementCorrections");
      boolean bool1 = setCallback(IMeasurementCorrectionsCallback.asInterface(arrayList.readStrongBinder()));
      paramHwParcel2.writeStatus(0);
      paramHwParcel2.writeBool(bool1);
      paramHwParcel2.send();
    } 
    arrayList.enforceInterface("android.hardware.gnss.measurement_corrections@1.0::IMeasurementCorrections");
    MeasurementCorrections measurementCorrections = new MeasurementCorrections();
    measurementCorrections.readFromParcel((HwParcel)arrayList);
    boolean bool = setCorrections(measurementCorrections);
    paramHwParcel2.writeStatus(0);
    paramHwParcel2.writeBool(bool);
    paramHwParcel2.send();
  }
  
  public final void ping() {}
  
  public IHwInterface queryLocalInterface(String paramString) {
    return (IHwInterface)("android.hardware.gnss.measurement_corrections@1.1::IMeasurementCorrections".equals(paramString) ? this : null);
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


/* Location:              /home/chun/Desktop/temp/!/android/hardware/gnss/measurement_corrections/V1_1/IMeasurementCorrections$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */