package android.hardware.cas.V1_2;

import android.hardware.cas.V1_1.ICasListener;
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

public interface ICasListener extends ICasListener {
  public static final String kInterfaceName = "android.hardware.cas@1.2::ICasListener";
  
  static ICasListener asInterface(IHwBinder paramIHwBinder) {
    if (paramIHwBinder == null)
      return null; 
    IHwInterface iHwInterface = paramIHwBinder.queryLocalInterface("android.hardware.cas@1.2::ICasListener");
    if (iHwInterface != null && iHwInterface instanceof ICasListener)
      return (ICasListener)iHwInterface; 
    Proxy proxy = new Proxy(paramIHwBinder);
    try {
      Iterator<String> iterator = proxy.interfaceChain().iterator();
      while (iterator.hasNext()) {
        boolean bool = ((String)iterator.next()).equals("android.hardware.cas@1.2::ICasListener");
        if (bool)
          return proxy; 
      } 
    } catch (RemoteException remoteException) {}
    return null;
  }
  
  static ICasListener castFrom(IHwInterface paramIHwInterface) {
    ICasListener iCasListener;
    if (paramIHwInterface == null) {
      paramIHwInterface = null;
    } else {
      iCasListener = asInterface(paramIHwInterface.asBinder());
    } 
    return iCasListener;
  }
  
  static ICasListener getService() throws RemoteException {
    return getService("default");
  }
  
  static ICasListener getService(String paramString) throws RemoteException {
    return asInterface(HwBinder.getService("android.hardware.cas@1.2::ICasListener", paramString));
  }
  
  static ICasListener getService(String paramString, boolean paramBoolean) throws RemoteException {
    return asInterface(HwBinder.getService("android.hardware.cas@1.2::ICasListener", paramString, paramBoolean));
  }
  
  static ICasListener getService(boolean paramBoolean) throws RemoteException {
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
  
  void onStatusUpdate(byte paramByte, int paramInt) throws RemoteException;
  
  void ping() throws RemoteException;
  
  void setHALInstrumentation() throws RemoteException;
  
  boolean unlinkToDeath(IHwBinder.DeathRecipient paramDeathRecipient) throws RemoteException;
  
  public static final class Proxy implements ICasListener {
    private IHwBinder mRemote;
    
    public Proxy(IHwBinder param1IHwBinder) {
      Objects.requireNonNull(param1IHwBinder);
      this.mRemote = param1IHwBinder;
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
    
    public void onEvent(int param1Int1, int param1Int2, ArrayList<Byte> param1ArrayList) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.cas@1.0::ICasListener");
      null.writeInt32(param1Int1);
      null.writeInt32(param1Int2);
      null.writeInt8Vector(param1ArrayList);
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
    
    public void onSessionEvent(ArrayList<Byte> param1ArrayList1, int param1Int1, int param1Int2, ArrayList<Byte> param1ArrayList2) throws RemoteException {
      HwParcel hwParcel2 = new HwParcel();
      hwParcel2.writeInterfaceToken("android.hardware.cas@1.1::ICasListener");
      hwParcel2.writeInt8Vector(param1ArrayList1);
      hwParcel2.writeInt32(param1Int1);
      hwParcel2.writeInt32(param1Int2);
      hwParcel2.writeInt8Vector(param1ArrayList2);
      HwParcel hwParcel1 = new HwParcel();
      try {
        this.mRemote.transact(2, hwParcel2, hwParcel1, 0);
        hwParcel1.verifySuccess();
        hwParcel2.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel1.release();
      } 
    }
    
    public void onStatusUpdate(byte param1Byte, int param1Int) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.cas@1.2::ICasListener");
      null.writeInt8(param1Byte);
      null.writeInt32(param1Int);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(3, null, hwParcel, 0);
        hwParcel.verifySuccess();
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
        return "[class or subclass of android.hardware.cas@1.2::ICasListener]@Proxy";
      } 
    }
    
    public boolean unlinkToDeath(IHwBinder.DeathRecipient param1DeathRecipient) throws RemoteException {
      return this.mRemote.unlinkToDeath(param1DeathRecipient);
    }
  }
  
  public static abstract class Stub extends HwBinder implements ICasListener {
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
          arrayList.enforceInterface("android.hardware.cas@1.2::ICasListener");
          onStatusUpdate(arrayList.readInt8(), arrayList.readInt32());
          param1HwParcel2.writeStatus(0);
          param1HwParcel2.send();
        } 
        arrayList.enforceInterface("android.hardware.cas@1.1::ICasListener");
        onSessionEvent(arrayList.readInt8Vector(), arrayList.readInt32(), arrayList.readInt32(), arrayList.readInt8Vector());
        param1HwParcel2.writeStatus(0);
        param1HwParcel2.send();
      } 
      arrayList.enforceInterface("android.hardware.cas@1.0::ICasListener");
      onEvent(arrayList.readInt32(), arrayList.readInt32(), arrayList.readInt8Vector());
      param1HwParcel2.writeStatus(0);
      param1HwParcel2.send();
    }
    
    public final void ping() {}
    
    public IHwInterface queryLocalInterface(String param1String) {
      return (IHwInterface)("android.hardware.cas@1.2::ICasListener".equals(param1String) ? this : null);
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


/* Location:              /home/chun/Desktop/temp/!/android/hardware/cas/V1_2/ICasListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */