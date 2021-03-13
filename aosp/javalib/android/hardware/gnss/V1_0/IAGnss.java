package android.hardware.gnss.V1_0;

import android.internal.hidl.base.V1_0.DebugInfo;
import android.internal.hidl.base.V1_0.IBase;
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

public interface IAGnss extends IBase {
  public static final String kInterfaceName = "android.hardware.gnss@1.0::IAGnss";
  
  static IAGnss asInterface(IHwBinder paramIHwBinder) {
    if (paramIHwBinder == null)
      return null; 
    IHwInterface iHwInterface = paramIHwBinder.queryLocalInterface("android.hardware.gnss@1.0::IAGnss");
    if (iHwInterface != null && iHwInterface instanceof IAGnss)
      return (IAGnss)iHwInterface; 
    Proxy proxy = new Proxy(paramIHwBinder);
    try {
      Iterator<String> iterator = proxy.interfaceChain().iterator();
      while (iterator.hasNext()) {
        boolean bool = ((String)iterator.next()).equals("android.hardware.gnss@1.0::IAGnss");
        if (bool)
          return proxy; 
      } 
    } catch (RemoteException remoteException) {}
    return null;
  }
  
  static IAGnss castFrom(IHwInterface paramIHwInterface) {
    IAGnss iAGnss;
    if (paramIHwInterface == null) {
      paramIHwInterface = null;
    } else {
      iAGnss = asInterface(paramIHwInterface.asBinder());
    } 
    return iAGnss;
  }
  
  static IAGnss getService() throws RemoteException {
    return getService("default");
  }
  
  static IAGnss getService(String paramString) throws RemoteException {
    return asInterface(HwBinder.getService("android.hardware.gnss@1.0::IAGnss", paramString));
  }
  
  static IAGnss getService(String paramString, boolean paramBoolean) throws RemoteException {
    return asInterface(HwBinder.getService("android.hardware.gnss@1.0::IAGnss", paramString, paramBoolean));
  }
  
  static IAGnss getService(boolean paramBoolean) throws RemoteException {
    return getService("default", paramBoolean);
  }
  
  IHwBinder asBinder();
  
  boolean dataConnClosed() throws RemoteException;
  
  boolean dataConnFailed() throws RemoteException;
  
  boolean dataConnOpen(String paramString, byte paramByte) throws RemoteException;
  
  void debug(NativeHandle paramNativeHandle, ArrayList<String> paramArrayList) throws RemoteException;
  
  DebugInfo getDebugInfo() throws RemoteException;
  
  ArrayList<byte[]> getHashChain() throws RemoteException;
  
  ArrayList<String> interfaceChain() throws RemoteException;
  
  String interfaceDescriptor() throws RemoteException;
  
  boolean linkToDeath(IHwBinder.DeathRecipient paramDeathRecipient, long paramLong) throws RemoteException;
  
  void notifySyspropsChanged() throws RemoteException;
  
  void ping() throws RemoteException;
  
  void setCallback(IAGnssCallback paramIAGnssCallback) throws RemoteException;
  
  void setHALInstrumentation() throws RemoteException;
  
  boolean setServer(byte paramByte, String paramString, int paramInt) throws RemoteException;
  
  boolean unlinkToDeath(IHwBinder.DeathRecipient paramDeathRecipient) throws RemoteException;
  
  public static final class ApnIpType {
    public static final byte INVALID = 0;
    
    public static final byte IPV4 = 1;
    
    public static final byte IPV4V6 = 3;
    
    public static final byte IPV6 = 2;
    
    public static final String dumpBitfield(byte param1Byte) {
      ArrayList<String> arrayList = new ArrayList();
      byte b1 = 0;
      arrayList.add("INVALID");
      if ((param1Byte & 0x1) == 1) {
        arrayList.add("IPV4");
        b1 = (byte)(false | true);
      } 
      byte b2 = b1;
      if ((param1Byte & 0x2) == 2) {
        arrayList.add("IPV6");
        b2 = (byte)(b1 | 0x2);
      } 
      b1 = b2;
      if ((param1Byte & 0x3) == 3) {
        arrayList.add("IPV4V6");
        b1 = (byte)(b2 | 0x3);
      } 
      if (param1Byte != b1) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("0x");
        stringBuilder.append(Integer.toHexString(Byte.toUnsignedInt((byte)(b1 & param1Byte))));
        arrayList.add(stringBuilder.toString());
      } 
      return String.join(" | ", (Iterable)arrayList);
    }
    
    public static final String toString(byte param1Byte) {
      if (param1Byte == 0)
        return "INVALID"; 
      if (param1Byte == 1)
        return "IPV4"; 
      if (param1Byte == 2)
        return "IPV6"; 
      if (param1Byte == 3)
        return "IPV4V6"; 
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("0x");
      stringBuilder.append(Integer.toHexString(Byte.toUnsignedInt(param1Byte)));
      return stringBuilder.toString();
    }
  }
  
  public static final class Proxy implements IAGnss {
    private IHwBinder mRemote;
    
    public Proxy(IHwBinder param1IHwBinder) {
      Objects.requireNonNull(param1IHwBinder);
      this.mRemote = param1IHwBinder;
    }
    
    public IHwBinder asBinder() {
      return this.mRemote;
    }
    
    public boolean dataConnClosed() throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.gnss@1.0::IAGnss");
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(2, null, hwParcel, 0);
        hwParcel.verifySuccess();
        null.releaseTemporaryStorage();
        return hwParcel.readBool();
      } finally {
        hwParcel.release();
      } 
    }
    
    public boolean dataConnFailed() throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.gnss@1.0::IAGnss");
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(3, null, hwParcel, 0);
        hwParcel.verifySuccess();
        null.releaseTemporaryStorage();
        return hwParcel.readBool();
      } finally {
        hwParcel.release();
      } 
    }
    
    public boolean dataConnOpen(String param1String, byte param1Byte) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.gnss@1.0::IAGnss");
      null.writeString(param1String);
      null.writeInt8(param1Byte);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(5, null, hwParcel, 0);
        hwParcel.verifySuccess();
        null.releaseTemporaryStorage();
        return hwParcel.readBool();
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
        HwBlob hwBlob = hwParcel.readBuffer(16L);
        int i = hwBlob.getInt32(8L);
        hwBlob = hwParcel.readEmbeddedBuffer((i * 32), hwBlob.handle(), 0L, true);
        arrayList.clear();
        for (byte b = 0; b < i; b++) {
          byte[] arrayOfByte = new byte[32];
          hwBlob.copyToInt8Array((b * 32), arrayOfByte, 32);
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
    
    public void setCallback(IAGnssCallback param1IAGnssCallback) throws RemoteException {
      IHwBinder iHwBinder;
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.gnss@1.0::IAGnss");
      if (param1IAGnssCallback == null) {
        param1IAGnssCallback = null;
      } else {
        iHwBinder = param1IAGnssCallback.asBinder();
      } 
      null.writeStrongBinder(iHwBinder);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(1, null, hwParcel, 0);
        hwParcel.verifySuccess();
        null.releaseTemporaryStorage();
        return;
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
    
    public boolean setServer(byte param1Byte, String param1String, int param1Int) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.gnss@1.0::IAGnss");
      null.writeInt8(param1Byte);
      null.writeString(param1String);
      null.writeInt32(param1Int);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(4, null, hwParcel, 0);
        hwParcel.verifySuccess();
        null.releaseTemporaryStorage();
        return hwParcel.readBool();
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
        return "[class or subclass of android.hardware.gnss@1.0::IAGnss]@Proxy";
      } 
    }
    
    public boolean unlinkToDeath(IHwBinder.DeathRecipient param1DeathRecipient) throws RemoteException {
      return this.mRemote.unlinkToDeath(param1DeathRecipient);
    }
  }
  
  public static abstract class Stub extends HwBinder implements IAGnss {
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
      return (ArrayList)new ArrayList<>(Arrays.asList((byte[])new byte[][] { { 
                55, -57, -38, 79, -126, 62, -55, 88, -33, -87, 
                -55, 96, -30, -45, 65, -60, -113, -121, 126, 11, 
                -6, 117, -113, 63, -87, -30, -39, -63, -31, -67, 
                102, -39 }, { 
                -20, Byte.MAX_VALUE, -41, -98, -48, 45, -6, -123, -68, 73, 
                -108, 38, -83, -82, 62, -66, 35, -17, 5, 36, 
                -13, -51, 105, 87, 19, -109, 36, -72, 59, 24, 
                -54, 76 } }));
    }
    
    public final ArrayList<String> interfaceChain() {
      return new ArrayList<>(Arrays.asList(new String[] { "android.hardware.gnss@1.0::IAGnss", "android.hidl.base@1.0::IBase" }));
    }
    
    public final String interfaceDescriptor() {
      return "android.hardware.gnss@1.0::IAGnss";
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
                byte[] arrayOfByte;
                String str;
                ArrayList<byte[]> arrayList1;
                HwBlob hwBlob1;
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
                    hwBlob1 = new HwBlob(16);
                    param1Int2 = arrayList1.size();
                    hwBlob1.putInt32(8L, param1Int2);
                    hwBlob1.putBool(12L, false);
                    hwBlob2 = new HwBlob(param1Int2 * 32);
                    param1Int1 = 0;
                    while (param1Int1 < param1Int2) {
                      long l = (param1Int1 * 32);
                      arrayOfByte = arrayList1.get(param1Int1);
                      if (arrayOfByte != null && arrayOfByte.length == 32) {
                        hwBlob2.putInt8Array(l, arrayOfByte);
                        param1Int1++;
                        continue;
                      } 
                      throw new IllegalArgumentException("Array element is not of the expected length");
                    } 
                    hwBlob1.putBlob(0L, hwBlob2);
                    param1HwParcel2.writeBuffer(hwBlob1);
                    param1HwParcel2.send();
                  case 256136003:
                    arrayOfByte.enforceInterface("android.hidl.base@1.0::IBase");
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
              arrayList.enforceInterface("android.hardware.gnss@1.0::IAGnss");
              boolean bool3 = dataConnOpen(arrayList.readString(), arrayList.readInt8());
              param1HwParcel2.writeStatus(0);
              param1HwParcel2.writeBool(bool3);
              param1HwParcel2.send();
            } 
            arrayList.enforceInterface("android.hardware.gnss@1.0::IAGnss");
            boolean bool2 = setServer(arrayList.readInt8(), arrayList.readString(), arrayList.readInt32());
            param1HwParcel2.writeStatus(0);
            param1HwParcel2.writeBool(bool2);
            param1HwParcel2.send();
          } 
          arrayList.enforceInterface("android.hardware.gnss@1.0::IAGnss");
          boolean bool1 = dataConnFailed();
          param1HwParcel2.writeStatus(0);
          param1HwParcel2.writeBool(bool1);
          param1HwParcel2.send();
        } 
        arrayList.enforceInterface("android.hardware.gnss@1.0::IAGnss");
        boolean bool = dataConnClosed();
        param1HwParcel2.writeStatus(0);
        param1HwParcel2.writeBool(bool);
        param1HwParcel2.send();
      } 
      arrayList.enforceInterface("android.hardware.gnss@1.0::IAGnss");
      setCallback(IAGnssCallback.asInterface(arrayList.readStrongBinder()));
      param1HwParcel2.writeStatus(0);
      param1HwParcel2.send();
    }
    
    public final void ping() {}
    
    public IHwInterface queryLocalInterface(String param1String) {
      return (IHwInterface)("android.hardware.gnss@1.0::IAGnss".equals(param1String) ? this : null);
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


/* Location:              /home/chun/Desktop/temp/!/android/hardware/gnss/V1_0/IAGnss.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */