package android.hardware.gnss.V2_0;

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

public interface IAGnssCallback extends IBase {
  public static final String kInterfaceName = "android.hardware.gnss@2.0::IAGnssCallback";
  
  static IAGnssCallback asInterface(IHwBinder paramIHwBinder) {
    if (paramIHwBinder == null)
      return null; 
    IHwInterface iHwInterface = paramIHwBinder.queryLocalInterface("android.hardware.gnss@2.0::IAGnssCallback");
    if (iHwInterface != null && iHwInterface instanceof IAGnssCallback)
      return (IAGnssCallback)iHwInterface; 
    Proxy proxy = new Proxy(paramIHwBinder);
    try {
      Iterator<String> iterator = proxy.interfaceChain().iterator();
      while (iterator.hasNext()) {
        boolean bool = ((String)iterator.next()).equals("android.hardware.gnss@2.0::IAGnssCallback");
        if (bool)
          return proxy; 
      } 
    } catch (RemoteException remoteException) {}
    return null;
  }
  
  static IAGnssCallback castFrom(IHwInterface paramIHwInterface) {
    IAGnssCallback iAGnssCallback;
    if (paramIHwInterface == null) {
      paramIHwInterface = null;
    } else {
      iAGnssCallback = asInterface(paramIHwInterface.asBinder());
    } 
    return iAGnssCallback;
  }
  
  static IAGnssCallback getService() throws RemoteException {
    return getService("default");
  }
  
  static IAGnssCallback getService(String paramString) throws RemoteException {
    return asInterface(HwBinder.getService("android.hardware.gnss@2.0::IAGnssCallback", paramString));
  }
  
  static IAGnssCallback getService(String paramString, boolean paramBoolean) throws RemoteException {
    return asInterface(HwBinder.getService("android.hardware.gnss@2.0::IAGnssCallback", paramString, paramBoolean));
  }
  
  static IAGnssCallback getService(boolean paramBoolean) throws RemoteException {
    return getService("default", paramBoolean);
  }
  
  void agnssStatusCb(byte paramByte1, byte paramByte2) throws RemoteException;
  
  IHwBinder asBinder();
  
  void debug(NativeHandle paramNativeHandle, ArrayList<String> paramArrayList) throws RemoteException;
  
  DebugInfo getDebugInfo() throws RemoteException;
  
  ArrayList<byte[]> getHashChain() throws RemoteException;
  
  ArrayList<String> interfaceChain() throws RemoteException;
  
  String interfaceDescriptor() throws RemoteException;
  
  boolean linkToDeath(IHwBinder.DeathRecipient paramDeathRecipient, long paramLong) throws RemoteException;
  
  void notifySyspropsChanged() throws RemoteException;
  
  void ping() throws RemoteException;
  
  void setHALInstrumentation() throws RemoteException;
  
  boolean unlinkToDeath(IHwBinder.DeathRecipient paramDeathRecipient) throws RemoteException;
  
  public static final class AGnssStatusValue {
    public static final byte AGNSS_DATA_CONNECTED = 3;
    
    public static final byte AGNSS_DATA_CONN_DONE = 4;
    
    public static final byte AGNSS_DATA_CONN_FAILED = 5;
    
    public static final byte RELEASE_AGNSS_DATA_CONN = 2;
    
    public static final byte REQUEST_AGNSS_DATA_CONN = 1;
    
    public static final String dumpBitfield(byte param1Byte) {
      ArrayList<String> arrayList = new ArrayList();
      byte b1 = 0;
      if ((param1Byte & 0x1) == 1) {
        arrayList.add("REQUEST_AGNSS_DATA_CONN");
        b1 = (byte)(false | true);
      } 
      byte b2 = b1;
      if ((param1Byte & 0x2) == 2) {
        arrayList.add("RELEASE_AGNSS_DATA_CONN");
        b2 = (byte)(b1 | 0x2);
      } 
      b1 = b2;
      if ((param1Byte & 0x3) == 3) {
        arrayList.add("AGNSS_DATA_CONNECTED");
        b1 = (byte)(b2 | 0x3);
      } 
      b2 = b1;
      if ((param1Byte & 0x4) == 4) {
        arrayList.add("AGNSS_DATA_CONN_DONE");
        b2 = (byte)(b1 | 0x4);
      } 
      b1 = b2;
      if ((param1Byte & 0x5) == 5) {
        arrayList.add("AGNSS_DATA_CONN_FAILED");
        b1 = (byte)(b2 | 0x5);
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
      if (param1Byte == 1)
        return "REQUEST_AGNSS_DATA_CONN"; 
      if (param1Byte == 2)
        return "RELEASE_AGNSS_DATA_CONN"; 
      if (param1Byte == 3)
        return "AGNSS_DATA_CONNECTED"; 
      if (param1Byte == 4)
        return "AGNSS_DATA_CONN_DONE"; 
      if (param1Byte == 5)
        return "AGNSS_DATA_CONN_FAILED"; 
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("0x");
      stringBuilder.append(Integer.toHexString(Byte.toUnsignedInt(param1Byte)));
      return stringBuilder.toString();
    }
  }
  
  public static final class AGnssType {
    public static final byte C2K = 2;
    
    public static final byte SUPL = 1;
    
    public static final byte SUPL_EIMS = 3;
    
    public static final byte SUPL_IMS = 4;
    
    public static final String dumpBitfield(byte param1Byte) {
      ArrayList<String> arrayList = new ArrayList();
      byte b1 = 0;
      if ((param1Byte & 0x1) == 1) {
        arrayList.add("SUPL");
        b1 = (byte)(false | true);
      } 
      byte b2 = b1;
      if ((param1Byte & 0x2) == 2) {
        arrayList.add("C2K");
        b2 = (byte)(b1 | 0x2);
      } 
      b1 = b2;
      if ((param1Byte & 0x3) == 3) {
        arrayList.add("SUPL_EIMS");
        b1 = (byte)(b2 | 0x3);
      } 
      b2 = b1;
      if ((param1Byte & 0x4) == 4) {
        arrayList.add("SUPL_IMS");
        b2 = (byte)(b1 | 0x4);
      } 
      if (param1Byte != b2) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("0x");
        stringBuilder.append(Integer.toHexString(Byte.toUnsignedInt((byte)(b2 & param1Byte))));
        arrayList.add(stringBuilder.toString());
      } 
      return String.join(" | ", (Iterable)arrayList);
    }
    
    public static final String toString(byte param1Byte) {
      if (param1Byte == 1)
        return "SUPL"; 
      if (param1Byte == 2)
        return "C2K"; 
      if (param1Byte == 3)
        return "SUPL_EIMS"; 
      if (param1Byte == 4)
        return "SUPL_IMS"; 
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("0x");
      stringBuilder.append(Integer.toHexString(Byte.toUnsignedInt(param1Byte)));
      return stringBuilder.toString();
    }
  }
  
  public static final class Proxy implements IAGnssCallback {
    private IHwBinder mRemote;
    
    public Proxy(IHwBinder param1IHwBinder) {
      Objects.requireNonNull(param1IHwBinder);
      this.mRemote = param1IHwBinder;
    }
    
    public void agnssStatusCb(byte param1Byte1, byte param1Byte2) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.gnss@2.0::IAGnssCallback");
      null.writeInt8(param1Byte1);
      null.writeInt8(param1Byte2);
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
    
    public IHwBinder asBinder() {
      return this.mRemote;
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
        return "[class or subclass of android.hardware.gnss@2.0::IAGnssCallback]@Proxy";
      } 
    }
    
    public boolean unlinkToDeath(IHwBinder.DeathRecipient param1DeathRecipient) throws RemoteException {
      return this.mRemote.unlinkToDeath(param1DeathRecipient);
    }
  }
  
  public static abstract class Stub extends HwBinder implements IAGnssCallback {
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
                46, 90, -39, -125, 115, 64, 105, -24, 74, 118, 
                0, 4, -77, 45, -96, -48, -98, 65, 112, -64, 
                83, Byte.MIN_VALUE, -85, -30, 126, 110, -72, 14, 74, -89, 
                13, 90 }, { 
                -20, Byte.MAX_VALUE, -41, -98, -48, 45, -6, -123, -68, 73, 
                -108, 38, -83, -82, 62, -66, 35, -17, 5, 36, 
                -13, -51, 105, 87, 19, -109, 36, -72, 59, 24, 
                -54, 76 } }));
    }
    
    public final ArrayList<String> interfaceChain() {
      return new ArrayList<>(Arrays.asList(new String[] { "android.hardware.gnss@2.0::IAGnssCallback", "android.hidl.base@1.0::IBase" }));
    }
    
    public final String interfaceDescriptor() {
      return "android.hardware.gnss@2.0::IAGnssCallback";
    }
    
    public final boolean linkToDeath(IHwBinder.DeathRecipient param1DeathRecipient, long param1Long) {
      return true;
    }
    
    public final void notifySyspropsChanged() {
      HwBinder.enableInstrumentation();
    }
    
    public void onTransact(int param1Int1, HwParcel param1HwParcel1, HwParcel param1HwParcel2, int param1Int2) throws RemoteException {
      DebugInfo debugInfo;
      HwBlob hwBlob1;
      String str;
      ArrayList<String> arrayList;
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
          str.enforceInterface("android.hidl.base@1.0::IBase");
          arrayList = interfaceChain();
          param1HwParcel2.writeStatus(0);
          param1HwParcel2.writeStringVector(arrayList);
          param1HwParcel2.send();
        case 1:
          break;
      } 
      arrayList.enforceInterface("android.hardware.gnss@2.0::IAGnssCallback");
      agnssStatusCb(arrayList.readInt8(), arrayList.readInt8());
      param1HwParcel2.writeStatus(0);
      param1HwParcel2.send();
    }
    
    public final void ping() {}
    
    public IHwInterface queryLocalInterface(String param1String) {
      return (IHwInterface)("android.hardware.gnss@2.0::IAGnssCallback".equals(param1String) ? this : null);
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


/* Location:              /home/chun/Desktop/temp/!/android/hardware/gnss/V2_0/IAGnssCallback.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */