package android.hardware.cas.V1_0;

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
    return new ArrayList<>(Arrays.asList(new String[] { "android.hardware.cas@1.0::IMediaCasService", "android.hidl.base@1.0::IBase" }));
  }
  
  public final String interfaceDescriptor() {
    return "android.hardware.cas@1.0::IMediaCasService";
  }
  
  public final boolean linkToDeath(IHwBinder.DeathRecipient paramDeathRecipient, long paramLong) {
    return true;
  }
  
  public final void notifySyspropsChanged() {
    HwBinder.enableInstrumentation();
  }
  
  public void onTransact(int paramInt1, HwParcel paramHwParcel1, HwParcel paramHwParcel2, int paramInt2) throws RemoteException {
    IHwBinder iHwBinder;
    if (paramInt1 != 1) {
      if (paramInt1 != 2) {
        IHwBinder iHwBinder1;
        HwBlob hwBlob1;
        HwBlob hwBlob2 = null;
        byte[] arrayOfByte = null;
        if (paramInt1 != 3) {
          if (paramInt1 != 4) {
            ArrayList<String> arrayList1;
            byte[] arrayOfByte1;
            if (paramInt1 != 5) {
              DebugInfo debugInfo;
              HwBlob hwBlob;
              String str;
              ArrayList<byte[]> arrayList2;
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
                  hwBlob2 = new HwBlob(16);
                  paramInt2 = arrayList2.size();
                  hwBlob2.putInt32(8L, paramInt2);
                  hwBlob2.putBool(12L, false);
                  hwBlob = new HwBlob(paramInt2 * 32);
                  paramInt1 = 0;
                  while (paramInt1 < paramInt2) {
                    long l = (paramInt1 * 32);
                    arrayOfByte = arrayList2.get(paramInt1);
                    if (arrayOfByte != null && arrayOfByte.length == 32) {
                      hwBlob.putInt8Array(l, arrayOfByte);
                      paramInt1++;
                      continue;
                    } 
                    throw new IllegalArgumentException("Array element is not of the expected length");
                  } 
                  hwBlob2.putBlob(0L, hwBlob);
                  paramHwParcel2.writeBuffer(hwBlob2);
                  paramHwParcel2.send();
                case 256136003:
                  hwBlob.enforceInterface("android.hidl.base@1.0::IBase");
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
            } 
            arrayList1.enforceInterface("android.hardware.cas@1.0::IMediaCasService");
            IDescramblerBase iDescramblerBase = createDescrambler(arrayList1.readInt32());
            paramHwParcel2.writeStatus(0);
            if (iDescramblerBase == null) {
              arrayOfByte1 = arrayOfByte;
            } else {
              iHwBinder1 = arrayOfByte1.asBinder();
            } 
            paramHwParcel2.writeStrongBinder(iHwBinder1);
            paramHwParcel2.send();
          } 
          iHwBinder1.enforceInterface("android.hardware.cas@1.0::IMediaCasService");
          boolean bool1 = isDescramblerSupported(iHwBinder1.readInt32());
          paramHwParcel2.writeStatus(0);
          paramHwParcel2.writeBool(bool1);
          paramHwParcel2.send();
        } 
        iHwBinder1.enforceInterface("android.hardware.cas@1.0::IMediaCasService");
        ICas iCas = createPlugin(iHwBinder1.readInt32(), ICasListener.asInterface(iHwBinder1.readStrongBinder()));
        paramHwParcel2.writeStatus(0);
        if (iCas == null) {
          hwBlob1 = hwBlob2;
        } else {
          iHwBinder = hwBlob1.asBinder();
        } 
        paramHwParcel2.writeStrongBinder(iHwBinder);
        paramHwParcel2.send();
      } 
      iHwBinder.enforceInterface("android.hardware.cas@1.0::IMediaCasService");
      boolean bool = isSystemIdSupported(iHwBinder.readInt32());
      paramHwParcel2.writeStatus(0);
      paramHwParcel2.writeBool(bool);
      paramHwParcel2.send();
    } 
    iHwBinder.enforceInterface("android.hardware.cas@1.0::IMediaCasService");
    ArrayList<HidlCasPluginDescriptor> arrayList = enumeratePlugins();
    paramHwParcel2.writeStatus(0);
    HidlCasPluginDescriptor.writeVectorToParcel(paramHwParcel2, arrayList);
    paramHwParcel2.send();
  }
  
  public final void ping() {}
  
  public IHwInterface queryLocalInterface(String paramString) {
    return (IHwInterface)("android.hardware.cas@1.0::IMediaCasService".equals(paramString) ? this : null);
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


/* Location:              /home/chun/Desktop/temp/!/android/hardware/cas/V1_0/IMediaCasService$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */