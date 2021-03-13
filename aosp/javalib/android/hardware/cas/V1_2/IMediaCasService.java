package android.hardware.cas.V1_2;

import android.hardware.cas.V1_0.HidlCasPluginDescriptor;
import android.hardware.cas.V1_0.ICas;
import android.hardware.cas.V1_0.ICasListener;
import android.hardware.cas.V1_0.IDescramblerBase;
import android.hardware.cas.V1_1.ICas;
import android.hardware.cas.V1_1.ICasListener;
import android.hardware.cas.V1_1.IMediaCasService;
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

public interface IMediaCasService extends IMediaCasService {
  public static final String kInterfaceName = "android.hardware.cas@1.2::IMediaCasService";
  
  static IMediaCasService asInterface(IHwBinder paramIHwBinder) {
    if (paramIHwBinder == null)
      return null; 
    IHwInterface iHwInterface = paramIHwBinder.queryLocalInterface("android.hardware.cas@1.2::IMediaCasService");
    if (iHwInterface != null && iHwInterface instanceof IMediaCasService)
      return (IMediaCasService)iHwInterface; 
    Proxy proxy = new Proxy(paramIHwBinder);
    try {
      Iterator<String> iterator = proxy.interfaceChain().iterator();
      while (iterator.hasNext()) {
        boolean bool = ((String)iterator.next()).equals("android.hardware.cas@1.2::IMediaCasService");
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
    return asInterface(HwBinder.getService("android.hardware.cas@1.2::IMediaCasService", paramString));
  }
  
  static IMediaCasService getService(String paramString, boolean paramBoolean) throws RemoteException {
    return asInterface(HwBinder.getService("android.hardware.cas@1.2::IMediaCasService", paramString, paramBoolean));
  }
  
  static IMediaCasService getService(boolean paramBoolean) throws RemoteException {
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
    
    public ICas createPluginExt(int param1Int, ICasListener param1ICasListener) throws RemoteException {
      IHwBinder iHwBinder;
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.cas@1.1::IMediaCasService");
      null.writeInt32(param1Int);
      if (param1ICasListener == null) {
        param1ICasListener = null;
      } else {
        iHwBinder = param1ICasListener.asBinder();
      } 
      null.writeStrongBinder(iHwBinder);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(6, null, hwParcel, 0);
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
        return "[class or subclass of android.hardware.cas@1.2::IMediaCasService]@Proxy";
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
                -15, -122, -107, -35, 54, -18, 32, 86, 64, -72, 
                50, 106, 23, 69, 56, 88, -89, -76, 89, 102, 
                83, -86, -90, -17, 0, 22, -80, -82, -15, -67, 
                77, -84 }, { 
                -33, -6, -51, -66, 11, -49, -124, 67, 1, 61, 
                -27, -67, -59, 106, -125, 71, -102, -39, 121, -44, 
                -111, -98, -47, 90, 85, -123, 83, -97, 70, 9, 
                31, 7 }, { 
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
      return new ArrayList<>(Arrays.asList(new String[] { "android.hardware.cas@1.2::IMediaCasService", "android.hardware.cas@1.1::IMediaCasService", "android.hardware.cas@1.0::IMediaCasService", "android.hidl.base@1.0::IBase" }));
    }
    
    public final String interfaceDescriptor() {
      return "android.hardware.cas@1.2::IMediaCasService";
    }
    
    public final boolean linkToDeath(IHwBinder.DeathRecipient param1DeathRecipient, long param1Long) {
      return true;
    }
    
    public final void notifySyspropsChanged() {
      HwBinder.enableInstrumentation();
    }
    
    public void onTransact(int param1Int1, HwParcel param1HwParcel1, HwParcel param1HwParcel2, int param1Int2) throws RemoteException {
      DebugInfo debugInfo;
      HwBlob hwBlob2;
      String str;
      ArrayList<String> arrayList2;
      ICas iCas1;
      HwBlob hwBlob1;
      IHwBinder iHwBinder3;
      IDescramblerBase iDescramblerBase;
      ArrayList<byte[]> arrayList1;
      IHwBinder iHwBinder2;
      ICas iCas;
      byte[] arrayOfByte1;
      IHwBinder iHwBinder1;
      boolean bool;
      ArrayList<byte[]> arrayList3 = null;
      byte[] arrayOfByte2 = null;
      HwBlob hwBlob3 = null;
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
              arrayList3 = getHashChain();
              param1HwParcel2.writeStatus(0);
              hwBlob2 = new HwBlob(16);
              param1Int2 = arrayList3.size();
              hwBlob2.putInt32(8L, param1Int2);
              hwBlob2.putBool(12L, false);
              hwBlob3 = new HwBlob(param1Int2 * 32);
              param1Int1 = 0;
              while (param1Int1 < param1Int2) {
                long l = (param1Int1 * 32);
                arrayOfByte2 = arrayList3.get(param1Int1);
                if (arrayOfByte2 != null && arrayOfByte2.length == 32) {
                  hwBlob3.putInt8Array(l, arrayOfByte2);
                  param1Int1++;
                  continue;
                } 
                throw new IllegalArgumentException("Array element is not of the expected length");
              } 
              hwBlob2.putBlob(0L, hwBlob3);
              param1HwParcel2.writeBuffer(hwBlob2);
              param1HwParcel2.send();
            case 256136003:
              hwBlob2.enforceInterface("android.hidl.base@1.0::IBase");
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
          arrayList2 = interfaceChain();
          param1HwParcel2.writeStatus(0);
          param1HwParcel2.writeStringVector(arrayList2);
          param1HwParcel2.send();
        case 6:
          arrayList2.enforceInterface("android.hardware.cas@1.1::IMediaCasService");
          iCas1 = createPluginExt(arrayList2.readInt32(), ICasListener.asInterface(arrayList2.readStrongBinder()));
          param1HwParcel2.writeStatus(0);
          if (iCas1 == null) {
            hwBlob1 = hwBlob3;
          } else {
            iHwBinder3 = hwBlob1.asBinder();
          } 
          param1HwParcel2.writeStrongBinder(iHwBinder3);
          param1HwParcel2.send();
        case 5:
          iHwBinder3.enforceInterface("android.hardware.cas@1.0::IMediaCasService");
          iDescramblerBase = createDescrambler(iHwBinder3.readInt32());
          param1HwParcel2.writeStatus(0);
          if (iDescramblerBase == null) {
            arrayList1 = arrayList3;
          } else {
            iHwBinder2 = arrayList1.asBinder();
          } 
          param1HwParcel2.writeStrongBinder(iHwBinder2);
          param1HwParcel2.send();
        case 4:
          iHwBinder2.enforceInterface("android.hardware.cas@1.0::IMediaCasService");
          bool = isDescramblerSupported(iHwBinder2.readInt32());
          param1HwParcel2.writeStatus(0);
          param1HwParcel2.writeBool(bool);
          param1HwParcel2.send();
        case 3:
          iHwBinder2.enforceInterface("android.hardware.cas@1.0::IMediaCasService");
          iCas = createPlugin(iHwBinder2.readInt32(), ICasListener.asInterface(iHwBinder2.readStrongBinder()));
          param1HwParcel2.writeStatus(0);
          if (iCas == null) {
            arrayOfByte1 = arrayOfByte2;
          } else {
            iHwBinder1 = arrayOfByte1.asBinder();
          } 
          param1HwParcel2.writeStrongBinder(iHwBinder1);
          param1HwParcel2.send();
        case 2:
          iHwBinder1.enforceInterface("android.hardware.cas@1.0::IMediaCasService");
          bool = isSystemIdSupported(iHwBinder1.readInt32());
          param1HwParcel2.writeStatus(0);
          param1HwParcel2.writeBool(bool);
          param1HwParcel2.send();
        case 1:
          break;
      } 
      iHwBinder1.enforceInterface("android.hardware.cas@1.0::IMediaCasService");
      ArrayList arrayList = enumeratePlugins();
      param1HwParcel2.writeStatus(0);
      HidlCasPluginDescriptor.writeVectorToParcel(param1HwParcel2, arrayList);
      param1HwParcel2.send();
    }
    
    public final void ping() {}
    
    public IHwInterface queryLocalInterface(String param1String) {
      return (IHwInterface)("android.hardware.cas@1.2::IMediaCasService".equals(param1String) ? this : null);
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


/* Location:              /home/chun/Desktop/temp/!/android/hardware/cas/V1_2/IMediaCasService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */