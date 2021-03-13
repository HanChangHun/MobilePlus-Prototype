package android.hardware.cas.V1_2;

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

public abstract class Stub extends HwBinder implements ICasListener {
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
              -104, 17, -8, 103, -34, -12, -101, 66, 13, -116, 
              112, Byte.MAX_VALUE, 126, 56, -45, -67, -42, 79, -125, 82, 
              68, -31, -46, -91, -23, 118, 42, -71, -125, 86, 
              114, -36 }, { 
              37, 1, 45, 23, 120, -9, 57, 111, -106, 123, 
              -68, 2, 49, 57, 125, 84, 75, -34, 66, 27, 
              -91, -71, -121, 6, -55, -28, -118, -57, -112, 97, 
              38, -125 }, { 
              -72, 14, 20, 86, -72, 31, Byte.MIN_VALUE, 3, 45, 13, 
              -25, -53, 69, 101, 42, -63, 90, -15, 30, 116, 
              116, -43, 32, -41, 87, 72, 30, -54, -83, 121, 
              109, -1 }, { 
              -20, Byte.MAX_VALUE, -41, -98, -48, 45, -6, -123, -68, 73, 
              -108, 38, -83, -82, 62, -66, 35, -17, 5, 36, 
              -13, -51, 105, 87, 19, -109, 36, -72, 59, 24, 
              -54, 76 } }));
  }
  
  public final ArrayList<String> interfaceChain() {
    return new ArrayList<>(Arrays.asList(new String[] { "android.hardware.cas@1.2::ICasListener", "android.hardware.cas@1.1::ICasListener", "android.hardware.cas@1.0::ICasListener", "android.hidl.base@1.0::IBase" }));
  }
  
  public final String interfaceDescriptor() {
    return "android.hardware.cas@1.2::ICasListener";
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
        arrayList.enforceInterface("android.hardware.cas@1.2::ICasListener");
        onStatusUpdate(arrayList.readInt8(), arrayList.readInt32());
        paramHwParcel2.writeStatus(0);
        paramHwParcel2.send();
      } 
      arrayList.enforceInterface("android.hardware.cas@1.1::ICasListener");
      onSessionEvent(arrayList.readInt8Vector(), arrayList.readInt32(), arrayList.readInt32(), arrayList.readInt8Vector());
      paramHwParcel2.writeStatus(0);
      paramHwParcel2.send();
    } 
    arrayList.enforceInterface("android.hardware.cas@1.0::ICasListener");
    onEvent(arrayList.readInt32(), arrayList.readInt32(), arrayList.readInt8Vector());
    paramHwParcel2.writeStatus(0);
    paramHwParcel2.send();
  }
  
  public final void ping() {}
  
  public IHwInterface queryLocalInterface(String paramString) {
    return (IHwInterface)("android.hardware.cas@1.2::ICasListener".equals(paramString) ? this : null);
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


/* Location:              /home/chun/Desktop/temp/!/android/hardware/cas/V1_2/ICasListener$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */