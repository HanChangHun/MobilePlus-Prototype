package android.hardware.gnss.V2_1;

import android.hardware.gnss.V1_0.IGnssMeasurementCallback;
import android.hardware.gnss.V1_1.IGnssMeasurementCallback;
import android.hardware.gnss.V2_0.IGnssMeasurement;
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
import java.util.Iterator;
import java.util.Objects;

public interface IGnssMeasurement extends IGnssMeasurement {
  public static final String kInterfaceName = "android.hardware.gnss@2.1::IGnssMeasurement";
  
  static IGnssMeasurement asInterface(IHwBinder paramIHwBinder) {
    if (paramIHwBinder == null)
      return null; 
    IHwInterface iHwInterface = paramIHwBinder.queryLocalInterface("android.hardware.gnss@2.1::IGnssMeasurement");
    if (iHwInterface != null && iHwInterface instanceof IGnssMeasurement)
      return (IGnssMeasurement)iHwInterface; 
    Proxy proxy = new Proxy(paramIHwBinder);
    try {
      Iterator<String> iterator = proxy.interfaceChain().iterator();
      while (iterator.hasNext()) {
        boolean bool = ((String)iterator.next()).equals("android.hardware.gnss@2.1::IGnssMeasurement");
        if (bool)
          return proxy; 
      } 
    } catch (RemoteException remoteException) {}
    return null;
  }
  
  static IGnssMeasurement castFrom(IHwInterface paramIHwInterface) {
    IGnssMeasurement iGnssMeasurement;
    if (paramIHwInterface == null) {
      paramIHwInterface = null;
    } else {
      iGnssMeasurement = asInterface(paramIHwInterface.asBinder());
    } 
    return iGnssMeasurement;
  }
  
  static IGnssMeasurement getService() throws RemoteException {
    return getService("default");
  }
  
  static IGnssMeasurement getService(String paramString) throws RemoteException {
    return asInterface(HwBinder.getService("android.hardware.gnss@2.1::IGnssMeasurement", paramString));
  }
  
  static IGnssMeasurement getService(String paramString, boolean paramBoolean) throws RemoteException {
    return asInterface(HwBinder.getService("android.hardware.gnss@2.1::IGnssMeasurement", paramString, paramBoolean));
  }
  
  static IGnssMeasurement getService(boolean paramBoolean) throws RemoteException {
    return getService("default", paramBoolean);
  }
  
  IHwBinder asBinder();
  
  void debug(NativeHandle paramNativeHandle, ArrayList<String> paramArrayList) throws RemoteException;
  
  DebugInfo getDebugInfo() throws RemoteException;
  
  ArrayList<byte[]> getHashChain() throws RemoteException;
  
  ArrayList<String> interfaceChain() throws RemoteException;
  
  String interfaceDescriptor() throws RemoteException;
  
  boolean linkToDeath(IHwBinder.DeathRecipient paramDeathRecipient, long paramLong) throws RemoteException;
  
  void notifySyspropsChanged() throws RemoteException;
  
  void ping() throws RemoteException;
  
  int setCallback_2_1(IGnssMeasurementCallback paramIGnssMeasurementCallback, boolean paramBoolean) throws RemoteException;
  
  void setHALInstrumentation() throws RemoteException;
  
  boolean unlinkToDeath(IHwBinder.DeathRecipient paramDeathRecipient) throws RemoteException;
  
  public static final class Proxy implements IGnssMeasurement {
    private IHwBinder mRemote;
    
    public Proxy(IHwBinder param1IHwBinder) {
      Objects.requireNonNull(param1IHwBinder);
      this.mRemote = param1IHwBinder;
    }
    
    public IHwBinder asBinder() {
      return this.mRemote;
    }
    
    public void close() throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.gnss@1.0::IGnssMeasurement");
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(2, null, hwParcel, 0);
        hwParcel.verifySuccess();
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void debug(NativeHandle param1NativeHandle, ArrayList<String> param1ArrayList) throws RemoteException {
      HwParcel hwParcel2 = new HwParcel();
      hwParcel2.writeInterfaceToken("android.hidl.base@1.0::IBase");
      hwParcel2.writeNativeHandle(param1NativeHandle);
      hwParcel2.writeStringVector(param1ArrayList);
      HwParcel hwParcel1 = new HwParcel();
      try {
        this.mRemote.transact(256131655, hwParcel2, hwParcel1, 0);
        hwParcel1.verifySuccess();
        hwParcel2.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel1.release();
      } 
    }
    
    public final boolean equals(Object param1Object) {
      return HidlSupport.interfacesEqual((IHwInterface)this, param1Object);
    }
    
    public DebugInfo getDebugInfo() throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hidl.base@1.0::IBase");
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(257049926, null, hwParcel, 0);
        hwParcel.verifySuccess();
        null.releaseTemporaryStorage();
        DebugInfo debugInfo = new DebugInfo();
        this();
        debugInfo.readFromParcel(hwParcel);
        return debugInfo;
      } finally {
        hwParcel.release();
      } 
    }
    
    public ArrayList<byte[]> getHashChain() throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hidl.base@1.0::IBase");
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(256398152, null, hwParcel, 0);
        hwParcel.verifySuccess();
        null.releaseTemporaryStorage();
        ArrayList<byte[]> arrayList = new ArrayList();
        this();
        HwBlob hwBlob1 = hwParcel.readBuffer(16L);
        int i = hwBlob1.getInt32(8L);
        HwBlob hwBlob2 = hwParcel.readEmbeddedBuffer((i * 32), hwBlob1.handle(), 0L, true);
        arrayList.clear();
        for (byte b = 0; b < i; b++) {
          byte[] arrayOfByte = new byte[32];
          hwBlob2.copyToInt8Array((b * 32), arrayOfByte, 32);
          arrayList.add(arrayOfByte);
        } 
        return arrayList;
      } finally {
        hwParcel.release();
      } 
    }
    
    public final int hashCode() {
      return asBinder().hashCode();
    }
    
    public ArrayList<String> interfaceChain() throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hidl.base@1.0::IBase");
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(256067662, null, hwParcel, 0);
        hwParcel.verifySuccess();
        null.releaseTemporaryStorage();
        return hwParcel.readStringVector();
      } finally {
        hwParcel.release();
      } 
    }
    
    public String interfaceDescriptor() throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hidl.base@1.0::IBase");
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(256136003, null, hwParcel, 0);
        hwParcel.verifySuccess();
        null.releaseTemporaryStorage();
        return hwParcel.readString();
      } finally {
        hwParcel.release();
      } 
    }
    
    public boolean linkToDeath(IHwBinder.DeathRecipient param1DeathRecipient, long param1Long) throws RemoteException {
      return this.mRemote.linkToDeath(param1DeathRecipient, param1Long);
    }
    
    public void notifySyspropsChanged() throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hidl.base@1.0::IBase");
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(257120595, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void ping() throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hidl.base@1.0::IBase");
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(256921159, null, hwParcel, 0);
        hwParcel.verifySuccess();
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public int setCallback(IGnssMeasurementCallback param1IGnssMeasurementCallback) throws RemoteException {
      IHwBinder iHwBinder;
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.gnss@1.0::IGnssMeasurement");
      if (param1IGnssMeasurementCallback == null) {
        param1IGnssMeasurementCallback = null;
      } else {
        iHwBinder = param1IGnssMeasurementCallback.asBinder();
      } 
      null.writeStrongBinder(iHwBinder);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(1, null, hwParcel, 0);
        hwParcel.verifySuccess();
        null.releaseTemporaryStorage();
        return hwParcel.readInt32();
      } finally {
        hwParcel.release();
      } 
    }
    
    public int setCallback_1_1(IGnssMeasurementCallback param1IGnssMeasurementCallback, boolean param1Boolean) throws RemoteException {
      IHwBinder iHwBinder;
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.gnss@1.1::IGnssMeasurement");
      if (param1IGnssMeasurementCallback == null) {
        param1IGnssMeasurementCallback = null;
      } else {
        iHwBinder = param1IGnssMeasurementCallback.asBinder();
      } 
      null.writeStrongBinder(iHwBinder);
      null.writeBool(param1Boolean);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(3, null, hwParcel, 0);
        hwParcel.verifySuccess();
        null.releaseTemporaryStorage();
        return hwParcel.readInt32();
      } finally {
        hwParcel.release();
      } 
    }
    
    public int setCallback_2_0(IGnssMeasurementCallback param1IGnssMeasurementCallback, boolean param1Boolean) throws RemoteException {
      IHwBinder iHwBinder;
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.gnss@2.0::IGnssMeasurement");
      if (param1IGnssMeasurementCallback == null) {
        param1IGnssMeasurementCallback = null;
      } else {
        iHwBinder = param1IGnssMeasurementCallback.asBinder();
      } 
      null.writeStrongBinder(iHwBinder);
      null.writeBool(param1Boolean);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(4, null, hwParcel, 0);
        hwParcel.verifySuccess();
        null.releaseTemporaryStorage();
        return hwParcel.readInt32();
      } finally {
        hwParcel.release();
      } 
    }
    
    public int setCallback_2_1(IGnssMeasurementCallback param1IGnssMeasurementCallback, boolean param1Boolean) throws RemoteException {
      IHwBinder iHwBinder;
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.gnss@2.1::IGnssMeasurement");
      if (param1IGnssMeasurementCallback == null) {
        param1IGnssMeasurementCallback = null;
      } else {
        iHwBinder = param1IGnssMeasurementCallback.asBinder();
      } 
      null.writeStrongBinder(iHwBinder);
      null.writeBool(param1Boolean);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(5, null, hwParcel, 0);
        hwParcel.verifySuccess();
        null.releaseTemporaryStorage();
        return hwParcel.readInt32();
      } finally {
        hwParcel.release();
      } 
    }
    
    public void setHALInstrumentation() throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hidl.base@1.0::IBase");
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(256462420, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public String toString() {
      try {
        StringBuilder stringBuilder = new StringBuilder();
        this();
        stringBuilder.append(interfaceDescriptor());
        stringBuilder.append("@Proxy");
        return stringBuilder.toString();
      } catch (RemoteException remoteException) {
        return "[class or subclass of android.hardware.gnss@2.1::IGnssMeasurement]@Proxy";
      } 
    }
    
    public boolean unlinkToDeath(IHwBinder.DeathRecipient param1DeathRecipient) throws RemoteException {
      return this.mRemote.unlinkToDeath(param1DeathRecipient);
    }
  }
  
  public static abstract class Stub extends HwBinder implements IGnssMeasurement {
    public IHwBinder asBinder() {
      return (IHwBinder)this;
    }
    
    public void debug(NativeHandle param1NativeHandle, ArrayList<String> param1ArrayList) {}
    
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
    
    public final boolean linkToDeath(IHwBinder.DeathRecipient param1DeathRecipient, long param1Long) {
      return true;
    }
    
    public final void notifySyspropsChanged() {
      HwBinder.enableInstrumentation();
    }
    
    public void onTransact(int param1Int1, HwParcel param1HwParcel1, HwParcel param1HwParcel2, int param1Int2) throws RemoteException {
      ArrayList<String> arrayList;
      if (param1Int1 != 1) {
        if (param1Int1 != 2) {
          if (param1Int1 != 3) {
            if (param1Int1 != 4) {
              if (param1Int1 != 5) {
                DebugInfo debugInfo;
                HwBlob hwBlob1;
                String str;
                ArrayList<byte[]> arrayList1;
                HwBlob hwBlob2;
                switch (param1Int1) {
                  default:
                    return;
                  case 257120595:
                    param1HwParcel1.enforceInterface("android.hidl.base@1.0::IBase");
                    notifySyspropsChanged();
                  case 257049926:
                    param1HwParcel1.enforceInterface("android.hidl.base@1.0::IBase");
                    debugInfo = getDebugInfo();
                    param1HwParcel2.writeStatus(0);
                    debugInfo.writeToParcel(param1HwParcel2);
                    param1HwParcel2.send();
                  case 256921159:
                    debugInfo.enforceInterface("android.hidl.base@1.0::IBase");
                    ping();
                    param1HwParcel2.writeStatus(0);
                    param1HwParcel2.send();
                  case 256462420:
                    debugInfo.enforceInterface("android.hidl.base@1.0::IBase");
                    setHALInstrumentation();
                  case 256398152:
                    debugInfo.enforceInterface("android.hidl.base@1.0::IBase");
                    arrayList1 = getHashChain();
                    param1HwParcel2.writeStatus(0);
                    hwBlob2 = new HwBlob(16);
                    param1Int2 = arrayList1.size();
                    hwBlob2.putInt32(8L, param1Int2);
                    hwBlob2.putBool(12L, false);
                    hwBlob1 = new HwBlob(param1Int2 * 32);
                    param1Int1 = 0;
                    while (param1Int1 < param1Int2) {
                      long l = (param1Int1 * 32);
                      byte[] arrayOfByte = arrayList1.get(param1Int1);
                      if (arrayOfByte != null && arrayOfByte.length == 32) {
                        hwBlob1.putInt8Array(l, arrayOfByte);
                        param1Int1++;
                        continue;
                      } 
                      throw new IllegalArgumentException("Array element is not of the expected length");
                    } 
                    hwBlob2.putBlob(0L, hwBlob1);
                    param1HwParcel2.writeBuffer(hwBlob2);
                    param1HwParcel2.send();
                  case 256136003:
                    hwBlob1.enforceInterface("android.hidl.base@1.0::IBase");
                    str = interfaceDescriptor();
                    param1HwParcel2.writeStatus(0);
                    param1HwParcel2.writeString(str);
                    param1HwParcel2.send();
                  case 256131655:
                    str.enforceInterface("android.hidl.base@1.0::IBase");
                    debug(str.readNativeHandle(), str.readStringVector());
                    param1HwParcel2.writeStatus(0);
                    param1HwParcel2.send();
                  case 256067662:
                    break;
                } 
                str.enforceInterface("android.hidl.base@1.0::IBase");
                arrayList = interfaceChain();
                param1HwParcel2.writeStatus(0);
                param1HwParcel2.writeStringVector(arrayList);
                param1HwParcel2.send();
              } 
              arrayList.enforceInterface("android.hardware.gnss@2.1::IGnssMeasurement");
              param1Int1 = setCallback_2_1(IGnssMeasurementCallback.asInterface(arrayList.readStrongBinder()), arrayList.readBool());
              param1HwParcel2.writeStatus(0);
              param1HwParcel2.writeInt32(param1Int1);
              param1HwParcel2.send();
            } 
            arrayList.enforceInterface("android.hardware.gnss@2.0::IGnssMeasurement");
            param1Int1 = setCallback_2_0(IGnssMeasurementCallback.asInterface(arrayList.readStrongBinder()), arrayList.readBool());
            param1HwParcel2.writeStatus(0);
            param1HwParcel2.writeInt32(param1Int1);
            param1HwParcel2.send();
          } 
          arrayList.enforceInterface("android.hardware.gnss@1.1::IGnssMeasurement");
          param1Int1 = setCallback_1_1(IGnssMeasurementCallback.asInterface(arrayList.readStrongBinder()), arrayList.readBool());
          param1HwParcel2.writeStatus(0);
          param1HwParcel2.writeInt32(param1Int1);
          param1HwParcel2.send();
        } 
        arrayList.enforceInterface("android.hardware.gnss@1.0::IGnssMeasurement");
        close();
        param1HwParcel2.writeStatus(0);
        param1HwParcel2.send();
      } 
      arrayList.enforceInterface("android.hardware.gnss@1.0::IGnssMeasurement");
      param1Int1 = setCallback(IGnssMeasurementCallback.asInterface(arrayList.readStrongBinder()));
      param1HwParcel2.writeStatus(0);
      param1HwParcel2.writeInt32(param1Int1);
      param1HwParcel2.send();
    }
    
    public final void ping() {}
    
    public IHwInterface queryLocalInterface(String param1String) {
      return (IHwInterface)("android.hardware.gnss@2.1::IGnssMeasurement".equals(param1String) ? this : null);
    }
    
    public void registerAsService(String param1String) throws RemoteException {
      registerService(param1String);
    }
    
    public final void setHALInstrumentation() {}
    
    public String toString() {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(interfaceDescriptor());
      stringBuilder.append("@Stub");
      return stringBuilder.toString();
    }
    
    public final boolean unlinkToDeath(IHwBinder.DeathRecipient param1DeathRecipient) {
      return true;
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/gnss/V2_1/IGnssMeasurement.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */