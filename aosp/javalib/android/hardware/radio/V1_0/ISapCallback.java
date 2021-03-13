package android.hardware.radio.V1_0;

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

public interface ISapCallback extends IBase {
  public static final String kInterfaceName = "android.hardware.radio@1.0::ISapCallback";
  
  static ISapCallback asInterface(IHwBinder paramIHwBinder) {
    if (paramIHwBinder == null)
      return null; 
    IHwInterface iHwInterface = paramIHwBinder.queryLocalInterface("android.hardware.radio@1.0::ISapCallback");
    if (iHwInterface != null && iHwInterface instanceof ISapCallback)
      return (ISapCallback)iHwInterface; 
    Proxy proxy = new Proxy(paramIHwBinder);
    try {
      Iterator<String> iterator = proxy.interfaceChain().iterator();
      while (iterator.hasNext()) {
        boolean bool = ((String)iterator.next()).equals("android.hardware.radio@1.0::ISapCallback");
        if (bool)
          return proxy; 
      } 
    } catch (RemoteException remoteException) {}
    return null;
  }
  
  static ISapCallback castFrom(IHwInterface paramIHwInterface) {
    ISapCallback iSapCallback;
    if (paramIHwInterface == null) {
      paramIHwInterface = null;
    } else {
      iSapCallback = asInterface(paramIHwInterface.asBinder());
    } 
    return iSapCallback;
  }
  
  static ISapCallback getService() throws RemoteException {
    return getService("default");
  }
  
  static ISapCallback getService(String paramString) throws RemoteException {
    return asInterface(HwBinder.getService("android.hardware.radio@1.0::ISapCallback", paramString));
  }
  
  static ISapCallback getService(String paramString, boolean paramBoolean) throws RemoteException {
    return asInterface(HwBinder.getService("android.hardware.radio@1.0::ISapCallback", paramString, paramBoolean));
  }
  
  static ISapCallback getService(boolean paramBoolean) throws RemoteException {
    return getService("default", paramBoolean);
  }
  
  void apduResponse(int paramInt1, int paramInt2, ArrayList<Byte> paramArrayList) throws RemoteException;
  
  IHwBinder asBinder();
  
  void connectResponse(int paramInt1, int paramInt2, int paramInt3) throws RemoteException;
  
  void debug(NativeHandle paramNativeHandle, ArrayList<String> paramArrayList) throws RemoteException;
  
  void disconnectIndication(int paramInt1, int paramInt2) throws RemoteException;
  
  void disconnectResponse(int paramInt) throws RemoteException;
  
  void errorResponse(int paramInt) throws RemoteException;
  
  DebugInfo getDebugInfo() throws RemoteException;
  
  ArrayList<byte[]> getHashChain() throws RemoteException;
  
  ArrayList<String> interfaceChain() throws RemoteException;
  
  String interfaceDescriptor() throws RemoteException;
  
  boolean linkToDeath(IHwBinder.DeathRecipient paramDeathRecipient, long paramLong) throws RemoteException;
  
  void notifySyspropsChanged() throws RemoteException;
  
  void ping() throws RemoteException;
  
  void powerResponse(int paramInt1, int paramInt2) throws RemoteException;
  
  void resetSimResponse(int paramInt1, int paramInt2) throws RemoteException;
  
  void setHALInstrumentation() throws RemoteException;
  
  void statusIndication(int paramInt1, int paramInt2) throws RemoteException;
  
  void transferAtrResponse(int paramInt1, int paramInt2, ArrayList<Byte> paramArrayList) throws RemoteException;
  
  void transferCardReaderStatusResponse(int paramInt1, int paramInt2, int paramInt3) throws RemoteException;
  
  void transferProtocolResponse(int paramInt1, int paramInt2) throws RemoteException;
  
  boolean unlinkToDeath(IHwBinder.DeathRecipient paramDeathRecipient) throws RemoteException;
  
  public static final class Proxy implements ISapCallback {
    private IHwBinder mRemote;
    
    public Proxy(IHwBinder param1IHwBinder) {
      Objects.requireNonNull(param1IHwBinder);
      this.mRemote = param1IHwBinder;
    }
    
    public void apduResponse(int param1Int1, int param1Int2, ArrayList<Byte> param1ArrayList) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::ISapCallback");
      null.writeInt32(param1Int1);
      null.writeInt32(param1Int2);
      null.writeInt8Vector(param1ArrayList);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(4, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public IHwBinder asBinder() {
      return this.mRemote;
    }
    
    public void connectResponse(int param1Int1, int param1Int2, int param1Int3) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::ISapCallback");
      null.writeInt32(param1Int1);
      null.writeInt32(param1Int2);
      null.writeInt32(param1Int3);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(1, null, hwParcel, 1);
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
    
    public void disconnectIndication(int param1Int1, int param1Int2) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::ISapCallback");
      null.writeInt32(param1Int1);
      null.writeInt32(param1Int2);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(3, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void disconnectResponse(int param1Int) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::ISapCallback");
      null.writeInt32(param1Int);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(2, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public final boolean equals(Object param1Object) {
      return HidlSupport.interfacesEqual((IHwInterface)this, param1Object);
    }
    
    public void errorResponse(int param1Int) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::ISapCallback");
      null.writeInt32(param1Int);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(10, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
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
    
    public void powerResponse(int param1Int1, int param1Int2) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::ISapCallback");
      null.writeInt32(param1Int1);
      null.writeInt32(param1Int2);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(6, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void resetSimResponse(int param1Int1, int param1Int2) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::ISapCallback");
      null.writeInt32(param1Int1);
      null.writeInt32(param1Int2);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(7, null, hwParcel, 1);
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
    
    public void statusIndication(int param1Int1, int param1Int2) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::ISapCallback");
      null.writeInt32(param1Int1);
      null.writeInt32(param1Int2);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(8, null, hwParcel, 1);
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
        return "[class or subclass of android.hardware.radio@1.0::ISapCallback]@Proxy";
      } 
    }
    
    public void transferAtrResponse(int param1Int1, int param1Int2, ArrayList<Byte> param1ArrayList) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::ISapCallback");
      null.writeInt32(param1Int1);
      null.writeInt32(param1Int2);
      null.writeInt8Vector(param1ArrayList);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(5, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void transferCardReaderStatusResponse(int param1Int1, int param1Int2, int param1Int3) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::ISapCallback");
      null.writeInt32(param1Int1);
      null.writeInt32(param1Int2);
      null.writeInt32(param1Int3);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(9, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void transferProtocolResponse(int param1Int1, int param1Int2) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::ISapCallback");
      null.writeInt32(param1Int1);
      null.writeInt32(param1Int2);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(11, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public boolean unlinkToDeath(IHwBinder.DeathRecipient param1DeathRecipient) throws RemoteException {
      return this.mRemote.unlinkToDeath(param1DeathRecipient);
    }
  }
  
  public static abstract class Stub extends HwBinder implements ISapCallback {
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
                -47, -125, -28, 6, -17, 8, -105, -33, 33, 23, 
                -87, -35, -29, -124, -24, -26, -22, 79, -90, -85, 
                28, Byte.MAX_VALUE, 111, 40, -26, 91, -121, 1, 18, 24, 
                -55, -22 }, { 
                -20, Byte.MAX_VALUE, -41, -98, -48, 45, -6, -123, -68, 73, 
                -108, 38, -83, -82, 62, -66, 35, -17, 5, 36, 
                -13, -51, 105, 87, 19, -109, 36, -72, 59, 24, 
                -54, 76 } }));
    }
    
    public final ArrayList<String> interfaceChain() {
      return new ArrayList<>(Arrays.asList(new String[] { "android.hardware.radio@1.0::ISapCallback", "android.hidl.base@1.0::IBase" }));
    }
    
    public final String interfaceDescriptor() {
      return "android.hardware.radio@1.0::ISapCallback";
    }
    
    public final boolean linkToDeath(IHwBinder.DeathRecipient param1DeathRecipient, long param1Long) {
      return true;
    }
    
    public final void notifySyspropsChanged() {
      HwBinder.enableInstrumentation();
    }
    
    public void onTransact(int param1Int1, HwParcel param1HwParcel1, HwParcel param1HwParcel2, int param1Int2) throws RemoteException {
      DebugInfo debugInfo;
      ArrayList<byte[]> arrayList1;
      String str;
      ArrayList<String> arrayList;
      HwBlob hwBlob1;
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
                byte[] arrayOfByte = arrayList1.get(param1Int1);
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
              arrayList1.enforceInterface("android.hidl.base@1.0::IBase");
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
        case 11:
          arrayList.enforceInterface("android.hardware.radio@1.0::ISapCallback");
          transferProtocolResponse(arrayList.readInt32(), arrayList.readInt32());
        case 10:
          arrayList.enforceInterface("android.hardware.radio@1.0::ISapCallback");
          errorResponse(arrayList.readInt32());
        case 9:
          arrayList.enforceInterface("android.hardware.radio@1.0::ISapCallback");
          transferCardReaderStatusResponse(arrayList.readInt32(), arrayList.readInt32(), arrayList.readInt32());
        case 8:
          arrayList.enforceInterface("android.hardware.radio@1.0::ISapCallback");
          statusIndication(arrayList.readInt32(), arrayList.readInt32());
        case 7:
          arrayList.enforceInterface("android.hardware.radio@1.0::ISapCallback");
          resetSimResponse(arrayList.readInt32(), arrayList.readInt32());
        case 6:
          arrayList.enforceInterface("android.hardware.radio@1.0::ISapCallback");
          powerResponse(arrayList.readInt32(), arrayList.readInt32());
        case 5:
          arrayList.enforceInterface("android.hardware.radio@1.0::ISapCallback");
          transferAtrResponse(arrayList.readInt32(), arrayList.readInt32(), arrayList.readInt8Vector());
        case 4:
          arrayList.enforceInterface("android.hardware.radio@1.0::ISapCallback");
          apduResponse(arrayList.readInt32(), arrayList.readInt32(), arrayList.readInt8Vector());
        case 3:
          arrayList.enforceInterface("android.hardware.radio@1.0::ISapCallback");
          disconnectIndication(arrayList.readInt32(), arrayList.readInt32());
        case 2:
          arrayList.enforceInterface("android.hardware.radio@1.0::ISapCallback");
          disconnectResponse(arrayList.readInt32());
        case 1:
          break;
      } 
      arrayList.enforceInterface("android.hardware.radio@1.0::ISapCallback");
      connectResponse(arrayList.readInt32(), arrayList.readInt32(), arrayList.readInt32());
    }
    
    public final void ping() {}
    
    public IHwInterface queryLocalInterface(String param1String) {
      return (IHwInterface)("android.hardware.radio@1.0::ISapCallback".equals(param1String) ? this : null);
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


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/V1_0/ISapCallback.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */