package android.hardware.cas.V1_0;

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

public interface IMediaCasService extends IBase {
  public static final String kInterfaceName = "android.hardware.cas@1.0::IMediaCasService";
  
  static IMediaCasService asInterface(IHwBinder paramIHwBinder) {
    if (paramIHwBinder == null)
      return null; 
    IHwInterface iHwInterface = paramIHwBinder.queryLocalInterface("android.hardware.cas@1.0::IMediaCasService");
    if (iHwInterface != null && iHwInterface instanceof IMediaCasService)
      return (IMediaCasService)iHwInterface; 
    Proxy proxy = new Proxy(paramIHwBinder);
    try {
      Iterator<String> iterator = proxy.interfaceChain().iterator();
      while (iterator.hasNext()) {
        boolean bool = ((String)iterator.next()).equals("android.hardware.cas@1.0::IMediaCasService");
        if (bool)
          return proxy; 
      } 
    } catch (RemoteException remoteException) {}
    return null;
  }
  
  static IMediaCasService castFrom(IHwInterface paramIHwInterface) {
    IMediaCasService iMediaCasService;
    if (paramIHwInterface == null) {
      paramIHwInterface = null;
    } else {
      iMediaCasService = asInterface(paramIHwInterface.asBinder());
    } 
    return iMediaCasService;
  }
  
  static IMediaCasService getService() throws RemoteException {
    return getService("default");
  }
  
  static IMediaCasService getService(String paramString) throws RemoteException {
    return asInterface(HwBinder.getService("android.hardware.cas@1.0::IMediaCasService", paramString));
  }
  
  static IMediaCasService getService(String paramString, boolean paramBoolean) throws RemoteException {
    return asInterface(HwBinder.getService("android.hardware.cas@1.0::IMediaCasService", paramString, paramBoolean));
  }
  
  static IMediaCasService getService(boolean paramBoolean) throws RemoteException {
    return getService("default", paramBoolean);
  }
  
  IHwBinder asBinder();
  
  IDescramblerBase createDescrambler(int paramInt) throws RemoteException;
  
  ICas createPlugin(int paramInt, ICasListener paramICasListener) throws RemoteException;
  
  void debug(NativeHandle paramNativeHandle, ArrayList<String> paramArrayList) throws RemoteException;
  
  ArrayList<HidlCasPluginDescriptor> enumeratePlugins() throws RemoteException;
  
  DebugInfo getDebugInfo() throws RemoteException;
  
  ArrayList<byte[]> getHashChain() throws RemoteException;
  
  ArrayList<String> interfaceChain() throws RemoteException;
  
  String interfaceDescriptor() throws RemoteException;
  
  boolean isDescramblerSupported(int paramInt) throws RemoteException;
  
  boolean isSystemIdSupported(int paramInt) throws RemoteException;
  
  boolean linkToDeath(IHwBinder.DeathRecipient paramDeathRecipient, long paramLong) throws RemoteException;
  
  void notifySyspropsChanged() throws RemoteException;
  
  void ping() throws RemoteException;
  
  void setHALInstrumentation() throws RemoteException;
  
  boolean unlinkToDeath(IHwBinder.DeathRecipient paramDeathRecipient) throws RemoteException;
  
  public static final class Proxy implements IMediaCasService {
    private IHwBinder mRemote;
    
    public Proxy(IHwBinder param1IHwBinder) {
      Objects.requireNonNull(param1IHwBinder);
      this.mRemote = param1IHwBinder;
    }
    
    public IHwBinder asBinder() {
      return this.mRemote;
    }
    
    public IDescramblerBase createDescrambler(int param1Int) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.cas@1.0::IMediaCasService");
      null.writeInt32(param1Int);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(5, null, hwParcel, 0);
        hwParcel.verifySuccess();
        null.releaseTemporaryStorage();
        return IDescramblerBase.asInterface(hwParcel.readStrongBinder());
      } finally {
        hwParcel.release();
      } 
    }
    
    public ICas createPlugin(int param1Int, ICasListener param1ICasListener) throws RemoteException {
      IHwBinder iHwBinder;
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.cas@1.0::IMediaCasService");
      null.writeInt32(param1Int);
      if (param1ICasListener == null) {
        param1ICasListener = null;
      } else {
        iHwBinder = param1ICasListener.asBinder();
      } 
      null.writeStrongBinder(iHwBinder);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(3, null, hwParcel, 0);
        hwParcel.verifySuccess();
        null.releaseTemporaryStorage();
        return ICas.asInterface(hwParcel.readStrongBinder());
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
    
    public ArrayList<HidlCasPluginDescriptor> enumeratePlugins() throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.cas@1.0::IMediaCasService");
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(1, null, hwParcel, 0);
        hwParcel.verifySuccess();
        null.releaseTemporaryStorage();
        return HidlCasPluginDescriptor.readVectorFromParcel(hwParcel);
      } finally {
        hwParcel.release();
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
    
    public boolean isDescramblerSupported(int param1Int) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.cas@1.0::IMediaCasService");
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
    
    public boolean isSystemIdSupported(int param1Int) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.cas@1.0::IMediaCasService");
      null.writeInt32(param1Int);
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
        return "[class or subclass of android.hardware.cas@1.0::IMediaCasService]@Proxy";
      } 
    }
    
    public boolean unlinkToDeath(IHwBinder.DeathRecipient param1DeathRecipient) throws RemoteException {
      return this.mRemote.unlinkToDeath(param1DeathRecipient);
    }
  }
  
  public static abstract class Stub extends HwBinder implements IMediaCasService {
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
    
    public final boolean linkToDeath(IHwBinder.DeathRecipient param1DeathRecipient, long param1Long) {
      return true;
    }
    
    public final void notifySyspropsChanged() {
      HwBinder.enableInstrumentation();
    }
    
    public void onTransact(int param1Int1, HwParcel param1HwParcel1, HwParcel param1HwParcel2, int param1Int2) throws RemoteException {
      IHwBinder iHwBinder;
      if (param1Int1 != 1) {
        if (param1Int1 != 2) {
          IHwBinder iHwBinder1;
          HwBlob hwBlob1;
          HwBlob hwBlob2 = null;
          byte[] arrayOfByte = null;
          if (param1Int1 != 3) {
            if (param1Int1 != 4) {
              ArrayList<String> arrayList1;
              byte[] arrayOfByte1;
              if (param1Int1 != 5) {
                DebugInfo debugInfo;
                HwBlob hwBlob;
                String str;
                ArrayList<byte[]> arrayList2;
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
                    arrayList2 = getHashChain();
                    param1HwParcel2.writeStatus(0);
                    hwBlob2 = new HwBlob(16);
                    param1Int2 = arrayList2.size();
                    hwBlob2.putInt32(8L, param1Int2);
                    hwBlob2.putBool(12L, false);
                    hwBlob = new HwBlob(param1Int2 * 32);
                    param1Int1 = 0;
                    while (param1Int1 < param1Int2) {
                      long l = (param1Int1 * 32);
                      arrayOfByte = arrayList2.get(param1Int1);
                      if (arrayOfByte != null && arrayOfByte.length == 32) {
                        hwBlob.putInt8Array(l, arrayOfByte);
                        param1Int1++;
                        continue;
                      } 
                      throw new IllegalArgumentException("Array element is not of the expected length");
                    } 
                    hwBlob2.putBlob(0L, hwBlob);
                    param1HwParcel2.writeBuffer(hwBlob2);
                    param1HwParcel2.send();
                  case 256136003:
                    hwBlob.enforceInterface("android.hidl.base@1.0::IBase");
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
                arrayList1 = interfaceChain();
                param1HwParcel2.writeStatus(0);
                param1HwParcel2.writeStringVector(arrayList1);
                param1HwParcel2.send();
              } 
              arrayList1.enforceInterface("android.hardware.cas@1.0::IMediaCasService");
              IDescramblerBase iDescramblerBase = createDescrambler(arrayList1.readInt32());
              param1HwParcel2.writeStatus(0);
              if (iDescramblerBase == null) {
                arrayOfByte1 = arrayOfByte;
              } else {
                iHwBinder1 = arrayOfByte1.asBinder();
              } 
              param1HwParcel2.writeStrongBinder(iHwBinder1);
              param1HwParcel2.send();
            } 
            iHwBinder1.enforceInterface("android.hardware.cas@1.0::IMediaCasService");
            boolean bool1 = isDescramblerSupported(iHwBinder1.readInt32());
            param1HwParcel2.writeStatus(0);
            param1HwParcel2.writeBool(bool1);
            param1HwParcel2.send();
          } 
          iHwBinder1.enforceInterface("android.hardware.cas@1.0::IMediaCasService");
          ICas iCas = createPlugin(iHwBinder1.readInt32(), ICasListener.asInterface(iHwBinder1.readStrongBinder()));
          param1HwParcel2.writeStatus(0);
          if (iCas == null) {
            hwBlob1 = hwBlob2;
          } else {
            iHwBinder = hwBlob1.asBinder();
          } 
          param1HwParcel2.writeStrongBinder(iHwBinder);
          param1HwParcel2.send();
        } 
        iHwBinder.enforceInterface("android.hardware.cas@1.0::IMediaCasService");
        boolean bool = isSystemIdSupported(iHwBinder.readInt32());
        param1HwParcel2.writeStatus(0);
        param1HwParcel2.writeBool(bool);
        param1HwParcel2.send();
      } 
      iHwBinder.enforceInterface("android.hardware.cas@1.0::IMediaCasService");
      ArrayList<HidlCasPluginDescriptor> arrayList = enumeratePlugins();
      param1HwParcel2.writeStatus(0);
      HidlCasPluginDescriptor.writeVectorToParcel(param1HwParcel2, arrayList);
      param1HwParcel2.send();
    }
    
    public final void ping() {}
    
    public IHwInterface queryLocalInterface(String param1String) {
      return (IHwInterface)("android.hardware.cas@1.0::IMediaCasService".equals(param1String) ? this : null);
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


/* Location:              /home/chun/Desktop/temp/!/android/hardware/cas/V1_0/IMediaCasService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */