package android.hardware.gnss.V1_0;

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

public abstract class Stub extends HwBinder implements IGnss {
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
              -19, -26, -105, 16, -61, -87, 92, 44, -66, -127, 
              -114, 108, -117, -73, 44, 120, 22, -126, 63, -84, 
              -27, -4, 33, -63, 119, 49, -78, 111, 65, -39, 
              77, 101 }, { 
              -20, Byte.MAX_VALUE, -41, -98, -48, 45, -6, -123, -68, 73, 
              -108, 38, -83, -82, 62, -66, 35, -17, 5, 36, 
              -13, -51, 105, 87, 19, -109, 36, -72, 59, 24, 
              -54, 76 } }));
  }
  
  public final ArrayList<String> interfaceChain() {
    return new ArrayList<>(Arrays.asList(new String[] { "android.hardware.gnss@1.0::IGnss", "android.hidl.base@1.0::IBase" }));
  }
  
  public final String interfaceDescriptor() {
    return "android.hardware.gnss@1.0::IGnss";
  }
  
  public final boolean linkToDeath(IHwBinder.DeathRecipient paramDeathRecipient, long paramLong) {
    return true;
  }
  
  public final void notifySyspropsChanged() {
    HwBinder.enableInstrumentation();
  }
  
  public void onTransact(int paramInt1, HwParcel paramHwParcel1, HwParcel paramHwParcel2, int paramInt2) throws RemoteException {
    DebugInfo debugInfo;
    ArrayList<byte[]> arrayList1;
    String str;
    ArrayList<String> arrayList;
    IGnssBatching iGnssBatching;
    byte[] arrayOfByte1;
    IHwBinder iHwBinder10;
    IGnssDebug iGnssDebug;
    HwBlob hwBlob2;
    IHwBinder iHwBinder9;
    IGnssConfiguration iGnssConfiguration;
    HwBlob hwBlob1;
    IHwBinder iHwBinder8;
    IGnssXtra iGnssXtra1;
    IHwBinder iHwBinder7;
    IGnssNavigationMessage iGnssNavigationMessage1;
    IHwBinder iHwBinder6;
    IGnssMeasurement iGnssMeasurement1;
    IHwBinder iHwBinder5;
    IGnssNi iGnssNi1;
    IHwBinder iHwBinder4;
    IAGnss iAGnss1;
    IHwBinder iHwBinder3;
    IGnssGeofencing iGnssGeofencing1;
    IHwBinder iHwBinder2;
    IAGnssRil iAGnssRil1;
    IHwBinder iHwBinder1;
    HwBlob hwBlob3 = null;
    HwBlob hwBlob4 = null;
    IGnssXtra iGnssXtra2 = null;
    IGnssNavigationMessage iGnssNavigationMessage2 = null;
    IGnssMeasurement iGnssMeasurement2 = null;
    IGnssNi iGnssNi2 = null;
    IAGnss iAGnss2 = null;
    IGnssGeofencing iGnssGeofencing2 = null;
    IAGnssRil iAGnssRil2 = null;
    byte[] arrayOfByte2 = null;
    switch (paramInt1) {
      default:
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
            hwBlob4 = new HwBlob(16);
            paramInt2 = arrayList1.size();
            hwBlob4.putInt32(8L, paramInt2);
            hwBlob4.putBool(12L, false);
            hwBlob3 = new HwBlob(paramInt2 * 32);
            paramInt1 = 0;
            while (paramInt1 < paramInt2) {
              long l = (paramInt1 * 32);
              arrayOfByte2 = arrayList1.get(paramInt1);
              if (arrayOfByte2 != null && arrayOfByte2.length == 32) {
                hwBlob3.putInt8Array(l, arrayOfByte2);
                paramInt1++;
                continue;
              } 
              throw new IllegalArgumentException("Array element is not of the expected length");
            } 
            hwBlob4.putBlob(0L, hwBlob3);
            paramHwParcel2.writeBuffer(hwBlob4);
            paramHwParcel2.send();
          case 256136003:
            arrayList1.enforceInterface("android.hidl.base@1.0::IBase");
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
      case 18:
        arrayList.enforceInterface("android.hardware.gnss@1.0::IGnss");
        iGnssBatching = getExtensionGnssBatching();
        paramHwParcel2.writeStatus(0);
        if (iGnssBatching == null) {
          arrayOfByte1 = arrayOfByte2;
        } else {
          iHwBinder10 = arrayOfByte1.asBinder();
        } 
        paramHwParcel2.writeStrongBinder(iHwBinder10);
        paramHwParcel2.send();
      case 17:
        iHwBinder10.enforceInterface("android.hardware.gnss@1.0::IGnss");
        iGnssDebug = getExtensionGnssDebug();
        paramHwParcel2.writeStatus(0);
        if (iGnssDebug == null) {
          hwBlob2 = hwBlob3;
        } else {
          iHwBinder9 = hwBlob2.asBinder();
        } 
        paramHwParcel2.writeStrongBinder(iHwBinder9);
        paramHwParcel2.send();
      case 16:
        iHwBinder9.enforceInterface("android.hardware.gnss@1.0::IGnss");
        iGnssConfiguration = getExtensionGnssConfiguration();
        paramHwParcel2.writeStatus(0);
        if (iGnssConfiguration == null) {
          hwBlob1 = hwBlob4;
        } else {
          iHwBinder8 = hwBlob1.asBinder();
        } 
        paramHwParcel2.writeStrongBinder(iHwBinder8);
        paramHwParcel2.send();
      case 15:
        iHwBinder8.enforceInterface("android.hardware.gnss@1.0::IGnss");
        iGnssXtra1 = getExtensionXtra();
        paramHwParcel2.writeStatus(0);
        if (iGnssXtra1 == null) {
          iGnssXtra1 = iGnssXtra2;
        } else {
          iHwBinder7 = iGnssXtra1.asBinder();
        } 
        paramHwParcel2.writeStrongBinder(iHwBinder7);
        paramHwParcel2.send();
      case 14:
        iHwBinder7.enforceInterface("android.hardware.gnss@1.0::IGnss");
        iGnssNavigationMessage1 = getExtensionGnssNavigationMessage();
        paramHwParcel2.writeStatus(0);
        if (iGnssNavigationMessage1 == null) {
          iGnssNavigationMessage1 = iGnssNavigationMessage2;
        } else {
          iHwBinder6 = iGnssNavigationMessage1.asBinder();
        } 
        paramHwParcel2.writeStrongBinder(iHwBinder6);
        paramHwParcel2.send();
      case 13:
        iHwBinder6.enforceInterface("android.hardware.gnss@1.0::IGnss");
        iGnssMeasurement1 = getExtensionGnssMeasurement();
        paramHwParcel2.writeStatus(0);
        if (iGnssMeasurement1 == null) {
          iGnssMeasurement1 = iGnssMeasurement2;
        } else {
          iHwBinder5 = iGnssMeasurement1.asBinder();
        } 
        paramHwParcel2.writeStrongBinder(iHwBinder5);
        paramHwParcel2.send();
      case 12:
        iHwBinder5.enforceInterface("android.hardware.gnss@1.0::IGnss");
        iGnssNi1 = getExtensionGnssNi();
        paramHwParcel2.writeStatus(0);
        if (iGnssNi1 == null) {
          iGnssNi1 = iGnssNi2;
        } else {
          iHwBinder4 = iGnssNi1.asBinder();
        } 
        paramHwParcel2.writeStrongBinder(iHwBinder4);
        paramHwParcel2.send();
      case 11:
        iHwBinder4.enforceInterface("android.hardware.gnss@1.0::IGnss");
        iAGnss1 = getExtensionAGnss();
        paramHwParcel2.writeStatus(0);
        if (iAGnss1 == null) {
          iAGnss1 = iAGnss2;
        } else {
          iHwBinder3 = iAGnss1.asBinder();
        } 
        paramHwParcel2.writeStrongBinder(iHwBinder3);
        paramHwParcel2.send();
      case 10:
        iHwBinder3.enforceInterface("android.hardware.gnss@1.0::IGnss");
        iGnssGeofencing1 = getExtensionGnssGeofencing();
        paramHwParcel2.writeStatus(0);
        if (iGnssGeofencing1 == null) {
          iGnssGeofencing1 = iGnssGeofencing2;
        } else {
          iHwBinder2 = iGnssGeofencing1.asBinder();
        } 
        paramHwParcel2.writeStrongBinder(iHwBinder2);
        paramHwParcel2.send();
      case 9:
        iHwBinder2.enforceInterface("android.hardware.gnss@1.0::IGnss");
        iAGnssRil1 = getExtensionAGnssRil();
        paramHwParcel2.writeStatus(0);
        if (iAGnssRil1 == null) {
          iAGnssRil1 = iAGnssRil2;
        } else {
          iHwBinder1 = iAGnssRil1.asBinder();
        } 
        paramHwParcel2.writeStrongBinder(iHwBinder1);
        paramHwParcel2.send();
      case 8:
        iHwBinder1.enforceInterface("android.hardware.gnss@1.0::IGnss");
        bool = setPositionMode(iHwBinder1.readInt8(), iHwBinder1.readInt32(), iHwBinder1.readInt32(), iHwBinder1.readInt32(), iHwBinder1.readInt32());
        paramHwParcel2.writeStatus(0);
        paramHwParcel2.writeBool(bool);
        paramHwParcel2.send();
      case 7:
        iHwBinder1.enforceInterface("android.hardware.gnss@1.0::IGnss");
        deleteAidingData(iHwBinder1.readInt16());
        paramHwParcel2.writeStatus(0);
        paramHwParcel2.send();
      case 6:
        iHwBinder1.enforceInterface("android.hardware.gnss@1.0::IGnss");
        bool = injectLocation(iHwBinder1.readDouble(), iHwBinder1.readDouble(), iHwBinder1.readFloat());
        paramHwParcel2.writeStatus(0);
        paramHwParcel2.writeBool(bool);
        paramHwParcel2.send();
      case 5:
        iHwBinder1.enforceInterface("android.hardware.gnss@1.0::IGnss");
        bool = injectTime(iHwBinder1.readInt64(), iHwBinder1.readInt64(), iHwBinder1.readInt32());
        paramHwParcel2.writeStatus(0);
        paramHwParcel2.writeBool(bool);
        paramHwParcel2.send();
      case 4:
        iHwBinder1.enforceInterface("android.hardware.gnss@1.0::IGnss");
        cleanup();
        paramHwParcel2.writeStatus(0);
        paramHwParcel2.send();
      case 3:
        iHwBinder1.enforceInterface("android.hardware.gnss@1.0::IGnss");
        bool = stop();
        paramHwParcel2.writeStatus(0);
        paramHwParcel2.writeBool(bool);
        paramHwParcel2.send();
      case 2:
        iHwBinder1.enforceInterface("android.hardware.gnss@1.0::IGnss");
        bool = start();
        paramHwParcel2.writeStatus(0);
        paramHwParcel2.writeBool(bool);
        paramHwParcel2.send();
      case 1:
        break;
    } 
    iHwBinder1.enforceInterface("android.hardware.gnss@1.0::IGnss");
    boolean bool = setCallback(IGnssCallback.asInterface(iHwBinder1.readStrongBinder()));
    paramHwParcel2.writeStatus(0);
    paramHwParcel2.writeBool(bool);
    paramHwParcel2.send();
  }
  
  public final void ping() {}
  
  public IHwInterface queryLocalInterface(String paramString) {
    return (IHwInterface)("android.hardware.gnss@1.0::IGnss".equals(paramString) ? this : null);
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


/* Location:              /home/chun/Desktop/temp/!/android/hardware/gnss/V1_0/IGnss$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */