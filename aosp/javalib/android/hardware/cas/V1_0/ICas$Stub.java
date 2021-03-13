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

public abstract class Stub extends HwBinder implements ICas {
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
              14, 101, 107, -95, -70, -63, 20, 97, -95, 112, 
              -106, -17, 117, 43, 105, -46, 75, 0, 13, -126, 
              14, -11, 101, 47, 1, 80, -96, -7, 115, 29, 
              84, -62 }, { 
              -20, Byte.MAX_VALUE, -41, -98, -48, 45, -6, -123, -68, 73, 
              -108, 38, -83, -82, 62, -66, 35, -17, 5, 36, 
              -13, -51, 105, 87, 19, -109, 36, -72, 59, 24, 
              -54, 76 } }));
  }
  
  public final ArrayList<String> interfaceChain() {
    return new ArrayList<>(Arrays.asList(new String[] { "android.hardware.cas@1.0::ICas", "android.hidl.base@1.0::IBase" }));
  }
  
  public final String interfaceDescriptor() {
    return "android.hardware.cas@1.0::ICas";
  }
  
  public final boolean linkToDeath(IHwBinder.DeathRecipient paramDeathRecipient, long paramLong) {
    return true;
  }
  
  public final void notifySyspropsChanged() {
    HwBinder.enableInstrumentation();
  }
  
  public void onTransact(int paramInt1, HwParcel paramHwParcel1, final HwParcel _hidl_reply, int paramInt2) throws RemoteException {
    DebugInfo debugInfo;
    HwBlob hwBlob1;
    String str;
    ArrayList<String> arrayList;
    ArrayList<byte[]> arrayList1;
    HwBlob hwBlob2;
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
            _hidl_reply.writeStatus(0);
            debugInfo.writeToParcel(_hidl_reply);
            _hidl_reply.send();
          case 256921159:
            debugInfo.enforceInterface("android.hidl.base@1.0::IBase");
            ping();
            _hidl_reply.writeStatus(0);
            _hidl_reply.send();
          case 256462420:
            debugInfo.enforceInterface("android.hidl.base@1.0::IBase");
            setHALInstrumentation();
          case 256398152:
            debugInfo.enforceInterface("android.hidl.base@1.0::IBase");
            arrayList1 = getHashChain();
            _hidl_reply.writeStatus(0);
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
            _hidl_reply.writeBuffer(hwBlob2);
            _hidl_reply.send();
          case 256136003:
            hwBlob1.enforceInterface("android.hidl.base@1.0::IBase");
            str = interfaceDescriptor();
            _hidl_reply.writeStatus(0);
            _hidl_reply.writeString(str);
            _hidl_reply.send();
          case 256131655:
            str.enforceInterface("android.hidl.base@1.0::IBase");
            debug(str.readNativeHandle(), str.readStringVector());
            _hidl_reply.writeStatus(0);
            _hidl_reply.send();
          case 256067662:
            break;
        } 
        str.enforceInterface("android.hidl.base@1.0::IBase");
        arrayList = interfaceChain();
        _hidl_reply.writeStatus(0);
        _hidl_reply.writeStringVector(arrayList);
        _hidl_reply.send();
      case 10:
        arrayList.enforceInterface("android.hardware.cas@1.0::ICas");
        paramInt1 = release();
        _hidl_reply.writeStatus(0);
        _hidl_reply.writeInt32(paramInt1);
        _hidl_reply.send();
      case 9:
        arrayList.enforceInterface("android.hardware.cas@1.0::ICas");
        paramInt1 = refreshEntitlements(arrayList.readInt32(), arrayList.readInt8Vector());
        _hidl_reply.writeStatus(0);
        _hidl_reply.writeInt32(paramInt1);
        _hidl_reply.send();
      case 8:
        arrayList.enforceInterface("android.hardware.cas@1.0::ICas");
        paramInt1 = provision(arrayList.readString());
        _hidl_reply.writeStatus(0);
        _hidl_reply.writeInt32(paramInt1);
        _hidl_reply.send();
      case 7:
        arrayList.enforceInterface("android.hardware.cas@1.0::ICas");
        paramInt1 = sendEvent(arrayList.readInt32(), arrayList.readInt32(), arrayList.readInt8Vector());
        _hidl_reply.writeStatus(0);
        _hidl_reply.writeInt32(paramInt1);
        _hidl_reply.send();
      case 6:
        arrayList.enforceInterface("android.hardware.cas@1.0::ICas");
        paramInt1 = processEmm(arrayList.readInt8Vector());
        _hidl_reply.writeStatus(0);
        _hidl_reply.writeInt32(paramInt1);
        _hidl_reply.send();
      case 5:
        arrayList.enforceInterface("android.hardware.cas@1.0::ICas");
        paramInt1 = processEcm(arrayList.readInt8Vector(), arrayList.readInt8Vector());
        _hidl_reply.writeStatus(0);
        _hidl_reply.writeInt32(paramInt1);
        _hidl_reply.send();
      case 4:
        arrayList.enforceInterface("android.hardware.cas@1.0::ICas");
        paramInt1 = setSessionPrivateData(arrayList.readInt8Vector(), arrayList.readInt8Vector());
        _hidl_reply.writeStatus(0);
        _hidl_reply.writeInt32(paramInt1);
        _hidl_reply.send();
      case 3:
        arrayList.enforceInterface("android.hardware.cas@1.0::ICas");
        paramInt1 = closeSession(arrayList.readInt8Vector());
        _hidl_reply.writeStatus(0);
        _hidl_reply.writeInt32(paramInt1);
        _hidl_reply.send();
      case 2:
        arrayList.enforceInterface("android.hardware.cas@1.0::ICas");
        openSession(new ICas.openSessionCallback() {
              public void onValues(int param2Int, ArrayList<Byte> param2ArrayList) {
                _hidl_reply.writeStatus(0);
                _hidl_reply.writeInt32(param2Int);
                _hidl_reply.writeInt8Vector(param2ArrayList);
                _hidl_reply.send();
              }
            });
      case 1:
        break;
    } 
    arrayList.enforceInterface("android.hardware.cas@1.0::ICas");
    paramInt1 = setPrivateData(arrayList.readInt8Vector());
    _hidl_reply.writeStatus(0);
    _hidl_reply.writeInt32(paramInt1);
    _hidl_reply.send();
  }
  
  public final void ping() {}
  
  public IHwInterface queryLocalInterface(String paramString) {
    return (IHwInterface)("android.hardware.cas@1.0::ICas".equals(paramString) ? this : null);
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


/* Location:              /home/chun/Desktop/temp/!/android/hardware/cas/V1_0/ICas$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */