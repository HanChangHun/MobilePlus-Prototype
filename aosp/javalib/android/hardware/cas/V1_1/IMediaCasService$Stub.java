package android.hardware.cas.V1_1;

import android.hardware.cas.V1_0.HidlCasPluginDescriptor;
import android.hardware.cas.V1_0.ICas;
import android.hardware.cas.V1_0.ICasListener;
import android.hardware.cas.V1_0.IDescramblerBase;
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

public abstract class Stub extends HwBinder implements IMediaCasService {
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
              -33, -6, -51, -66, 11, -49, -124, 67, 1, 61, 
              -27, -67, -59, 106, -125, 71, -102, -39, 121, -44, 
              -111, -98, -47, 90, 85, -123, 83, -97, 70, 9, 
              31, 7 }, { 
              -122, -70, -100, 3, -105, -117, 121, -89, 66, -23, 
              -112, 66, 11, -59, -50, -48, 103, 61, 37, -87, 
              57, -8, 37, 114, -103, 107, -17, -110, 98, 30, 
              32, 20 }, { 
              -20, Byte.MAX_VALUE, -41, -98, -48, 45, -6, -123, -68, 73, 
              -108, 38, -83, -82, 62, -66, 35, -17, 5, 36, 
              -13, -51, 105, 87, 19, -109, 36, -72, 59, 24, 
              -54, 76 } }));
  }
  
  public final ArrayList<String> interfaceChain() {
    return new ArrayList<>(Arrays.asList(new String[] { "android.hardware.cas@1.1::IMediaCasService", "android.hardware.cas@1.0::IMediaCasService", "android.hidl.base@1.0::IBase" }));
  }
  
  public final String interfaceDescriptor() {
    return "android.hardware.cas@1.1::IMediaCasService";
  }
  
  public final boolean linkToDeath(IHwBinder.DeathRecipient paramDeathRecipient, long paramLong) {
    return true;
  }
  
  public final void notifySyspropsChanged() {
    HwBinder.enableInstrumentation();
  }
  
  public void onTransact(int paramInt1, HwParcel paramHwParcel1, HwParcel paramHwParcel2, int paramInt2) throws RemoteException {
    DebugInfo debugInfo;
    ArrayList<byte[]> arrayList2;
    String str;
    ArrayList<String> arrayList1;
    ICas iCas1;
    HwBlob hwBlob2;
    IHwBinder iHwBinder3;
    IDescramblerBase iDescramblerBase;
    HwBlob hwBlob1;
    IHwBinder iHwBinder2;
    ICas iCas;
    byte[] arrayOfByte1;
    IHwBinder iHwBinder1;
    boolean bool;
    HwBlob hwBlob3 = null;
    byte[] arrayOfByte2 = null;
    HwBlob hwBlob4 = null;
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
            arrayList2 = getHashChain();
            paramHwParcel2.writeStatus(0);
            hwBlob4 = new HwBlob(16);
            paramInt2 = arrayList2.size();
            hwBlob4.putInt32(8L, paramInt2);
            hwBlob4.putBool(12L, false);
            hwBlob3 = new HwBlob(paramInt2 * 32);
            paramInt1 = 0;
            while (paramInt1 < paramInt2) {
              long l = (paramInt1 * 32);
              arrayOfByte2 = arrayList2.get(paramInt1);
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
            arrayList2.enforceInterface("android.hidl.base@1.0::IBase");
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
        arrayList1 = interfaceChain();
        paramHwParcel2.writeStatus(0);
        paramHwParcel2.writeStringVector(arrayList1);
        paramHwParcel2.send();
      case 6:
        arrayList1.enforceInterface("android.hardware.cas@1.1::IMediaCasService");
        iCas1 = createPluginExt(arrayList1.readInt32(), ICasListener.asInterface(arrayList1.readStrongBinder()));
        paramHwParcel2.writeStatus(0);
        if (iCas1 == null) {
          hwBlob2 = hwBlob4;
        } else {
          iHwBinder3 = hwBlob2.asBinder();
        } 
        paramHwParcel2.writeStrongBinder(iHwBinder3);
        paramHwParcel2.send();
      case 5:
        iHwBinder3.enforceInterface("android.hardware.cas@1.0::IMediaCasService");
        iDescramblerBase = createDescrambler(iHwBinder3.readInt32());
        paramHwParcel2.writeStatus(0);
        if (iDescramblerBase == null) {
          hwBlob1 = hwBlob3;
        } else {
          iHwBinder2 = hwBlob1.asBinder();
        } 
        paramHwParcel2.writeStrongBinder(iHwBinder2);
        paramHwParcel2.send();
      case 4:
        iHwBinder2.enforceInterface("android.hardware.cas@1.0::IMediaCasService");
        bool = isDescramblerSupported(iHwBinder2.readInt32());
        paramHwParcel2.writeStatus(0);
        paramHwParcel2.writeBool(bool);
        paramHwParcel2.send();
      case 3:
        iHwBinder2.enforceInterface("android.hardware.cas@1.0::IMediaCasService");
        iCas = createPlugin(iHwBinder2.readInt32(), ICasListener.asInterface(iHwBinder2.readStrongBinder()));
        paramHwParcel2.writeStatus(0);
        if (iCas == null) {
          arrayOfByte1 = arrayOfByte2;
        } else {
          iHwBinder1 = arrayOfByte1.asBinder();
        } 
        paramHwParcel2.writeStrongBinder(iHwBinder1);
        paramHwParcel2.send();
      case 2:
        iHwBinder1.enforceInterface("android.hardware.cas@1.0::IMediaCasService");
        bool = isSystemIdSupported(iHwBinder1.readInt32());
        paramHwParcel2.writeStatus(0);
        paramHwParcel2.writeBool(bool);
        paramHwParcel2.send();
      case 1:
        break;
    } 
    iHwBinder1.enforceInterface("android.hardware.cas@1.0::IMediaCasService");
    ArrayList arrayList = enumeratePlugins();
    paramHwParcel2.writeStatus(0);
    HidlCasPluginDescriptor.writeVectorToParcel(paramHwParcel2, arrayList);
    paramHwParcel2.send();
  }
  
  public final void ping() {}
  
  public IHwInterface queryLocalInterface(String paramString) {
    return (IHwInterface)("android.hardware.cas@1.1::IMediaCasService".equals(paramString) ? this : null);
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


/* Location:              /home/chun/Desktop/temp/!/android/hardware/cas/V1_1/IMediaCasService$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */