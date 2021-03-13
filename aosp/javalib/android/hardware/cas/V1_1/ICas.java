package android.hardware.cas.V1_1;

import android.hardware.cas.V1_0.ICas;
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

public interface ICas extends ICas {
  public static final String kInterfaceName = "android.hardware.cas@1.1::ICas";
  
  static ICas asInterface(IHwBinder paramIHwBinder) {
    if (paramIHwBinder == null)
      return null; 
    IHwInterface iHwInterface = paramIHwBinder.queryLocalInterface("android.hardware.cas@1.1::ICas");
    if (iHwInterface != null && iHwInterface instanceof ICas)
      return (ICas)iHwInterface; 
    Proxy proxy = new Proxy(paramIHwBinder);
    try {
      Iterator<String> iterator = proxy.interfaceChain().iterator();
      while (iterator.hasNext()) {
        boolean bool = ((String)iterator.next()).equals("android.hardware.cas@1.1::ICas");
        if (bool)
          return proxy; 
      } 
    } catch (RemoteException remoteException) {}
    return null;
  }
  
  static ICas castFrom(IHwInterface paramIHwInterface) {
    ICas iCas;
    if (paramIHwInterface == null) {
      paramIHwInterface = null;
    } else {
      iCas = asInterface(paramIHwInterface.asBinder());
    } 
    return iCas;
  }
  
  static ICas getService() throws RemoteException {
    return getService("default");
  }
  
  static ICas getService(String paramString) throws RemoteException {
    return asInterface(HwBinder.getService("android.hardware.cas@1.1::ICas", paramString));
  }
  
  static ICas getService(String paramString, boolean paramBoolean) throws RemoteException {
    return asInterface(HwBinder.getService("android.hardware.cas@1.1::ICas", paramString, paramBoolean));
  }
  
  static ICas getService(boolean paramBoolean) throws RemoteException {
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
  
  int sendSessionEvent(ArrayList<Byte> paramArrayList1, int paramInt1, int paramInt2, ArrayList<Byte> paramArrayList2) throws RemoteException;
  
  void setHALInstrumentation() throws RemoteException;
  
  boolean unlinkToDeath(IHwBinder.DeathRecipient paramDeathRecipient) throws RemoteException;
  
  public static final class Proxy implements ICas {
    private IHwBinder mRemote;
    
    public Proxy(IHwBinder param1IHwBinder) {
      Objects.requireNonNull(param1IHwBinder);
      this.mRemote = param1IHwBinder;
    }
    
    public IHwBinder asBinder() {
      return this.mRemote;
    }
    
    public int closeSession(ArrayList<Byte> param1ArrayList) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.cas@1.0::ICas");
      null.writeInt8Vector(param1ArrayList);
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
    
    public void openSession(ICas.openSessionCallback param1openSessionCallback) throws RemoteException {
      HwParcel hwParcel1 = new HwParcel();
      hwParcel1.writeInterfaceToken("android.hardware.cas@1.0::ICas");
      HwParcel hwParcel2 = new HwParcel();
      try {
        this.mRemote.transact(2, hwParcel1, hwParcel2, 0);
        hwParcel2.verifySuccess();
        hwParcel1.releaseTemporaryStorage();
        param1openSessionCallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt8Vector());
        return;
      } finally {
        hwParcel2.release();
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
    
    public int processEcm(ArrayList<Byte> param1ArrayList1, ArrayList<Byte> param1ArrayList2) throws RemoteException {
      HwParcel hwParcel2 = new HwParcel();
      hwParcel2.writeInterfaceToken("android.hardware.cas@1.0::ICas");
      hwParcel2.writeInt8Vector(param1ArrayList1);
      hwParcel2.writeInt8Vector(param1ArrayList2);
      HwParcel hwParcel1 = new HwParcel();
      try {
        this.mRemote.transact(5, hwParcel2, hwParcel1, 0);
        hwParcel1.verifySuccess();
        hwParcel2.releaseTemporaryStorage();
        return hwParcel1.readInt32();
      } finally {
        hwParcel1.release();
      } 
    }
    
    public int processEmm(ArrayList<Byte> param1ArrayList) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.cas@1.0::ICas");
      null.writeInt8Vector(param1ArrayList);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(6, null, hwParcel, 0);
        hwParcel.verifySuccess();
        null.releaseTemporaryStorage();
        return hwParcel.readInt32();
      } finally {
        hwParcel.release();
      } 
    }
    
    public int provision(String param1String) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.cas@1.0::ICas");
      null.writeString(param1String);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(8, null, hwParcel, 0);
        hwParcel.verifySuccess();
        null.releaseTemporaryStorage();
        return hwParcel.readInt32();
      } finally {
        hwParcel.release();
      } 
    }
    
    public int refreshEntitlements(int param1Int, ArrayList<Byte> param1ArrayList) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.cas@1.0::ICas");
      null.writeInt32(param1Int);
      null.writeInt8Vector(param1ArrayList);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(9, null, hwParcel, 0);
        hwParcel.verifySuccess();
        null.releaseTemporaryStorage();
        param1Int = hwParcel.readInt32();
        return param1Int;
      } finally {
        hwParcel.release();
      } 
    }
    
    public int release() throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.cas@1.0::ICas");
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(10, null, hwParcel, 0);
        hwParcel.verifySuccess();
        null.releaseTemporaryStorage();
        return hwParcel.readInt32();
      } finally {
        hwParcel.release();
      } 
    }
    
    public int sendEvent(int param1Int1, int param1Int2, ArrayList<Byte> param1ArrayList) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.cas@1.0::ICas");
      null.writeInt32(param1Int1);
      null.writeInt32(param1Int2);
      null.writeInt8Vector(param1ArrayList);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(7, null, hwParcel, 0);
        hwParcel.verifySuccess();
        null.releaseTemporaryStorage();
        param1Int1 = hwParcel.readInt32();
        return param1Int1;
      } finally {
        hwParcel.release();
      } 
    }
    
    public int sendSessionEvent(ArrayList<Byte> param1ArrayList1, int param1Int1, int param1Int2, ArrayList<Byte> param1ArrayList2) throws RemoteException {
      HwParcel hwParcel2 = new HwParcel();
      hwParcel2.writeInterfaceToken("android.hardware.cas@1.1::ICas");
      hwParcel2.writeInt8Vector(param1ArrayList1);
      hwParcel2.writeInt32(param1Int1);
      hwParcel2.writeInt32(param1Int2);
      hwParcel2.writeInt8Vector(param1ArrayList2);
      HwParcel hwParcel1 = new HwParcel();
      try {
        this.mRemote.transact(11, hwParcel2, hwParcel1, 0);
        hwParcel1.verifySuccess();
        hwParcel2.releaseTemporaryStorage();
        param1Int1 = hwParcel1.readInt32();
        return param1Int1;
      } finally {
        hwParcel1.release();
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
    
    public int setPrivateData(ArrayList<Byte> param1ArrayList) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.cas@1.0::ICas");
      null.writeInt8Vector(param1ArrayList);
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
    
    public int setSessionPrivateData(ArrayList<Byte> param1ArrayList1, ArrayList<Byte> param1ArrayList2) throws RemoteException {
      HwParcel hwParcel2 = new HwParcel();
      hwParcel2.writeInterfaceToken("android.hardware.cas@1.0::ICas");
      hwParcel2.writeInt8Vector(param1ArrayList1);
      hwParcel2.writeInt8Vector(param1ArrayList2);
      HwParcel hwParcel1 = new HwParcel();
      try {
        this.mRemote.transact(4, hwParcel2, hwParcel1, 0);
        hwParcel1.verifySuccess();
        hwParcel2.releaseTemporaryStorage();
        return hwParcel1.readInt32();
      } finally {
        hwParcel1.release();
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
        return "[class or subclass of android.hardware.cas@1.1::ICas]@Proxy";
      } 
    }
    
    public boolean unlinkToDeath(IHwBinder.DeathRecipient param1DeathRecipient) throws RemoteException {
      return this.mRemote.unlinkToDeath(param1DeathRecipient);
    }
  }
  
  public static abstract class Stub extends HwBinder implements ICas {
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
          14, 101, 107, -95, -70, -63, 20, 97, -95, 112, 
          -106, -17, 117, 43, 105, -46, 75, 0, 13, -126, 
          14, -11, 101, 47, 1, 80, -96, -7, 115, 29, 
          84, -62 };
      byte[] arrayOfByte2 = { 
          -20, Byte.MAX_VALUE, -41, -98, -48, 45, -6, -123, -68, 73, 
          -108, 38, -83, -82, 62, -66, 35, -17, 5, 36, 
          -13, -51, 105, 87, 19, -109, 36, -72, 59, 24, 
          -54, 76 };
      return (ArrayList)new ArrayList<>(Arrays.asList((byte[])new byte[][] { { 
                68, -56, -119, 84, -77, -62, 1, -78, 111, 100, 
                -4, -37, 111, 39, Byte.MIN_VALUE, 36, -85, 58, -82, -122, 
                74, -98, 30, -57, 14, -118, 116, 39, 74, -23, 
                -42, -86 }, arrayOfByte1, arrayOfByte2 }));
    }
    
    public final ArrayList<String> interfaceChain() {
      return new ArrayList<>(Arrays.asList(new String[] { "android.hardware.cas@1.1::ICas", "android.hardware.cas@1.0::ICas", "android.hidl.base@1.0::IBase" }));
    }
    
    public final String interfaceDescriptor() {
      return "android.hardware.cas@1.1::ICas";
    }
    
    public final boolean linkToDeath(IHwBinder.DeathRecipient param1DeathRecipient, long param1Long) {
      return true;
    }
    
    public final void notifySyspropsChanged() {
      HwBinder.enableInstrumentation();
    }
    
    public void onTransact(int param1Int1, HwParcel param1HwParcel1, final HwParcel _hidl_reply, int param1Int2) throws RemoteException {
      DebugInfo debugInfo;
      HwBlob hwBlob1;
      String str;
      ArrayList<String> arrayList;
      ArrayList<byte[]> arrayList1;
      HwBlob hwBlob2;
      switch (param1Int1) {
        default:
          switch (param1Int1) {
            default:
              return;
            case 257120595:
              param1HwParcel1.enforceInterface("android.hidl.base@1.0::IBase");
              notifySyspropsChanged();
            case 257049926:
              param1HwParcel1.enforceInterface("android.hidl.base@1.0::IBase");
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
              hwBlob1 = new HwBlob(16);
              param1Int2 = arrayList1.size();
              hwBlob1.putInt32(8L, param1Int2);
              hwBlob1.putBool(12L, false);
              hwBlob2 = new HwBlob(param1Int2 * 32);
              param1Int1 = 0;
              while (param1Int1 < param1Int2) {
                long l = (param1Int1 * 32);
                byte[] arrayOfByte = arrayList1.get(param1Int1);
                if (arrayOfByte != null && arrayOfByte.length == 32) {
                  hwBlob2.putInt8Array(l, arrayOfByte);
                  param1Int1++;
                  continue;
                } 
                throw new IllegalArgumentException("Array element is not of the expected length");
              } 
              hwBlob1.putBlob(0L, hwBlob2);
              _hidl_reply.writeBuffer(hwBlob1);
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
        case 11:
          arrayList.enforceInterface("android.hardware.cas@1.1::ICas");
          param1Int1 = sendSessionEvent(arrayList.readInt8Vector(), arrayList.readInt32(), arrayList.readInt32(), arrayList.readInt8Vector());
          _hidl_reply.writeStatus(0);
          _hidl_reply.writeInt32(param1Int1);
          _hidl_reply.send();
        case 10:
          arrayList.enforceInterface("android.hardware.cas@1.0::ICas");
          param1Int1 = release();
          _hidl_reply.writeStatus(0);
          _hidl_reply.writeInt32(param1Int1);
          _hidl_reply.send();
        case 9:
          arrayList.enforceInterface("android.hardware.cas@1.0::ICas");
          param1Int1 = refreshEntitlements(arrayList.readInt32(), arrayList.readInt8Vector());
          _hidl_reply.writeStatus(0);
          _hidl_reply.writeInt32(param1Int1);
          _hidl_reply.send();
        case 8:
          arrayList.enforceInterface("android.hardware.cas@1.0::ICas");
          param1Int1 = provision(arrayList.readString());
          _hidl_reply.writeStatus(0);
          _hidl_reply.writeInt32(param1Int1);
          _hidl_reply.send();
        case 7:
          arrayList.enforceInterface("android.hardware.cas@1.0::ICas");
          param1Int1 = sendEvent(arrayList.readInt32(), arrayList.readInt32(), arrayList.readInt8Vector());
          _hidl_reply.writeStatus(0);
          _hidl_reply.writeInt32(param1Int1);
          _hidl_reply.send();
        case 6:
          arrayList.enforceInterface("android.hardware.cas@1.0::ICas");
          param1Int1 = processEmm(arrayList.readInt8Vector());
          _hidl_reply.writeStatus(0);
          _hidl_reply.writeInt32(param1Int1);
          _hidl_reply.send();
        case 5:
          arrayList.enforceInterface("android.hardware.cas@1.0::ICas");
          param1Int1 = processEcm(arrayList.readInt8Vector(), arrayList.readInt8Vector());
          _hidl_reply.writeStatus(0);
          _hidl_reply.writeInt32(param1Int1);
          _hidl_reply.send();
        case 4:
          arrayList.enforceInterface("android.hardware.cas@1.0::ICas");
          param1Int1 = setSessionPrivateData(arrayList.readInt8Vector(), arrayList.readInt8Vector());
          _hidl_reply.writeStatus(0);
          _hidl_reply.writeInt32(param1Int1);
          _hidl_reply.send();
        case 3:
          arrayList.enforceInterface("android.hardware.cas@1.0::ICas");
          param1Int1 = closeSession(arrayList.readInt8Vector());
          _hidl_reply.writeStatus(0);
          _hidl_reply.writeInt32(param1Int1);
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
      param1Int1 = setPrivateData(arrayList.readInt8Vector());
      _hidl_reply.writeStatus(0);
      _hidl_reply.writeInt32(param1Int1);
      _hidl_reply.send();
    }
    
    public final void ping() {}
    
    public IHwInterface queryLocalInterface(String param1String) {
      return (IHwInterface)("android.hardware.cas@1.1::ICas".equals(param1String) ? this : null);
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
  
  class null implements ICas.openSessionCallback {
    public void onValues(int param1Int, ArrayList<Byte> param1ArrayList) {
      _hidl_reply.writeStatus(0);
      _hidl_reply.writeInt32(param1Int);
      _hidl_reply.writeInt8Vector(param1ArrayList);
      _hidl_reply.send();
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/cas/V1_1/ICas.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */