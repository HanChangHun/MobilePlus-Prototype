package android.hardware.gnss.V2_0;

import android.hardware.gnss.V1_1.IGnssConfiguration;
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

public interface IGnssConfiguration extends IGnssConfiguration {
  public static final String kInterfaceName = "android.hardware.gnss@2.0::IGnssConfiguration";
  
  static IGnssConfiguration asInterface(IHwBinder paramIHwBinder) {
    if (paramIHwBinder == null)
      return null; 
    IHwInterface iHwInterface = paramIHwBinder.queryLocalInterface("android.hardware.gnss@2.0::IGnssConfiguration");
    if (iHwInterface != null && iHwInterface instanceof IGnssConfiguration)
      return (IGnssConfiguration)iHwInterface; 
    Proxy proxy = new Proxy(paramIHwBinder);
    try {
      Iterator<String> iterator = proxy.interfaceChain().iterator();
      while (iterator.hasNext()) {
        boolean bool = ((String)iterator.next()).equals("android.hardware.gnss@2.0::IGnssConfiguration");
        if (bool)
          return proxy; 
      } 
    } catch (RemoteException remoteException) {}
    return null;
  }
  
  static IGnssConfiguration castFrom(IHwInterface paramIHwInterface) {
    IGnssConfiguration iGnssConfiguration;
    if (paramIHwInterface == null) {
      paramIHwInterface = null;
    } else {
      iGnssConfiguration = asInterface(paramIHwInterface.asBinder());
    } 
    return iGnssConfiguration;
  }
  
  static IGnssConfiguration getService() throws RemoteException {
    return getService("default");
  }
  
  static IGnssConfiguration getService(String paramString) throws RemoteException {
    return asInterface(HwBinder.getService("android.hardware.gnss@2.0::IGnssConfiguration", paramString));
  }
  
  static IGnssConfiguration getService(String paramString, boolean paramBoolean) throws RemoteException {
    return asInterface(HwBinder.getService("android.hardware.gnss@2.0::IGnssConfiguration", paramString, paramBoolean));
  }
  
  static IGnssConfiguration getService(boolean paramBoolean) throws RemoteException {
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
  
  boolean setEsExtensionSec(int paramInt) throws RemoteException;
  
  void setHALInstrumentation() throws RemoteException;
  
  boolean unlinkToDeath(IHwBinder.DeathRecipient paramDeathRecipient) throws RemoteException;
  
  public static final class Proxy implements IGnssConfiguration {
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
    
    public boolean setBlacklist(ArrayList<IGnssConfiguration.BlacklistedSource> param1ArrayList) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.gnss@1.1::IGnssConfiguration");
      IGnssConfiguration.BlacklistedSource.writeVectorToParcel(null, param1ArrayList);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(8, null, hwParcel, 0);
        hwParcel.verifySuccess();
        null.releaseTemporaryStorage();
        return hwParcel.readBool();
      } finally {
        hwParcel.release();
      } 
    }
    
    public boolean setEmergencySuplPdn(boolean param1Boolean) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.gnss@1.0::IGnssConfiguration");
      null.writeBool(param1Boolean);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(7, null, hwParcel, 0);
        hwParcel.verifySuccess();
        null.releaseTemporaryStorage();
        param1Boolean = hwParcel.readBool();
        return param1Boolean;
      } finally {
        hwParcel.release();
      } 
    }
    
    public boolean setEsExtensionSec(int param1Int) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.gnss@2.0::IGnssConfiguration");
      null.writeInt32(param1Int);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(9, null, hwParcel, 0);
        hwParcel.verifySuccess();
        null.releaseTemporaryStorage();
        return hwParcel.readBool();
      } finally {
        hwParcel.release();
      } 
    }
    
    public boolean setGlonassPositioningProtocol(byte param1Byte) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.gnss@1.0::IGnssConfiguration");
      null.writeInt8(param1Byte);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(6, null, hwParcel, 0);
        hwParcel.verifySuccess();
        null.releaseTemporaryStorage();
        return hwParcel.readBool();
      } finally {
        hwParcel.release();
      } 
    }
    
    public boolean setGpsLock(byte param1Byte) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.gnss@1.0::IGnssConfiguration");
      null.writeInt8(param1Byte);
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
    
    public boolean setLppProfile(byte param1Byte) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.gnss@1.0::IGnssConfiguration");
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
    
    public boolean setSuplEs(boolean param1Boolean) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.gnss@1.0::IGnssConfiguration");
      null.writeBool(param1Boolean);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(1, null, hwParcel, 0);
        hwParcel.verifySuccess();
        null.releaseTemporaryStorage();
        param1Boolean = hwParcel.readBool();
        return param1Boolean;
      } finally {
        hwParcel.release();
      } 
    }
    
    public boolean setSuplMode(byte param1Byte) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.gnss@1.0::IGnssConfiguration");
      null.writeInt8(param1Byte);
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
    
    public boolean setSuplVersion(int param1Int) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.gnss@1.0::IGnssConfiguration");
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
    
    public String toString() {
      try {
        StringBuilder stringBuilder = new StringBuilder();
        this();
        stringBuilder.append(interfaceDescriptor());
        stringBuilder.append("@Proxy");
        return stringBuilder.toString();
      } catch (RemoteException remoteException) {
        return "[class or subclass of android.hardware.gnss@2.0::IGnssConfiguration]@Proxy";
      } 
    }
    
    public boolean unlinkToDeath(IHwBinder.DeathRecipient param1DeathRecipient) throws RemoteException {
      return this.mRemote.unlinkToDeath(param1DeathRecipient);
    }
  }
  
  public static abstract class Stub extends HwBinder implements IGnssConfiguration {
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
                -20, -55, 102, -58, -117, -35, -67, -107, -56, -38, 
                -25, -126, -72, 66, 4, -49, 1, -57, 87, 52, 
                103, 94, -121, 105, -106, 63, 59, 81, 6, -20, 
                18, -117 }, { 
                60, 81, -125, -41, 80, 96, 16, -66, 87, -32, 
                -9, 72, -29, 100, 15, -62, -34, -47, -70, -107, 
                87, -124, -74, 37, 107, -92, 39, -12, -61, -103, 
                89, 28 }, { 
                -5, -110, -30, -76, 15, -114, -99, 73, 78, -113, 
                -45, -76, -84, 24, 73, -102, 50, 22, 52, 46, 
                124, -1, 22, 7, 20, -61, -69, -13, 102, 11, 
                110, 121 }, { 
                -20, Byte.MAX_VALUE, -41, -98, -48, 45, -6, -123, -68, 73, 
                -108, 38, -83, -82, 62, -66, 35, -17, 5, 36, 
                -13, -51, 105, 87, 19, -109, 36, -72, 59, 24, 
                -54, 76 } }));
    }
    
    public final ArrayList<String> interfaceChain() {
      return new ArrayList<>(Arrays.asList(new String[] { "android.hardware.gnss@2.0::IGnssConfiguration", "android.hardware.gnss@1.1::IGnssConfiguration", "android.hardware.gnss@1.0::IGnssConfiguration", "android.hidl.base@1.0::IBase" }));
    }
    
    public final String interfaceDescriptor() {
      return "android.hardware.gnss@2.0::IGnssConfiguration";
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
        case 9:
          arrayList.enforceInterface("android.hardware.gnss@2.0::IGnssConfiguration");
          bool = setEsExtensionSec(arrayList.readInt32());
          param1HwParcel2.writeStatus(0);
          param1HwParcel2.writeBool(bool);
          param1HwParcel2.send();
        case 8:
          arrayList.enforceInterface("android.hardware.gnss@1.1::IGnssConfiguration");
          bool = setBlacklist(IGnssConfiguration.BlacklistedSource.readVectorFromParcel((HwParcel)arrayList));
          param1HwParcel2.writeStatus(0);
          param1HwParcel2.writeBool(bool);
          param1HwParcel2.send();
        case 7:
          arrayList.enforceInterface("android.hardware.gnss@1.0::IGnssConfiguration");
          bool = setEmergencySuplPdn(arrayList.readBool());
          param1HwParcel2.writeStatus(0);
          param1HwParcel2.writeBool(bool);
          param1HwParcel2.send();
        case 6:
          arrayList.enforceInterface("android.hardware.gnss@1.0::IGnssConfiguration");
          bool = setGlonassPositioningProtocol(arrayList.readInt8());
          param1HwParcel2.writeStatus(0);
          param1HwParcel2.writeBool(bool);
          param1HwParcel2.send();
        case 5:
          arrayList.enforceInterface("android.hardware.gnss@1.0::IGnssConfiguration");
          bool = setLppProfile(arrayList.readInt8());
          param1HwParcel2.writeStatus(0);
          param1HwParcel2.writeBool(bool);
          param1HwParcel2.send();
        case 4:
          arrayList.enforceInterface("android.hardware.gnss@1.0::IGnssConfiguration");
          bool = setGpsLock(arrayList.readInt8());
          param1HwParcel2.writeStatus(0);
          param1HwParcel2.writeBool(bool);
          param1HwParcel2.send();
        case 3:
          arrayList.enforceInterface("android.hardware.gnss@1.0::IGnssConfiguration");
          bool = setSuplMode(arrayList.readInt8());
          param1HwParcel2.writeStatus(0);
          param1HwParcel2.writeBool(bool);
          param1HwParcel2.send();
        case 2:
          arrayList.enforceInterface("android.hardware.gnss@1.0::IGnssConfiguration");
          bool = setSuplVersion(arrayList.readInt32());
          param1HwParcel2.writeStatus(0);
          param1HwParcel2.writeBool(bool);
          param1HwParcel2.send();
        case 1:
          break;
      } 
      arrayList.enforceInterface("android.hardware.gnss@1.0::IGnssConfiguration");
      boolean bool = setSuplEs(arrayList.readBool());
      param1HwParcel2.writeStatus(0);
      param1HwParcel2.writeBool(bool);
      param1HwParcel2.send();
    }
    
    public final void ping() {}
    
    public IHwInterface queryLocalInterface(String param1String) {
      return (IHwInterface)("android.hardware.gnss@2.0::IGnssConfiguration".equals(param1String) ? this : null);
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


/* Location:              /home/chun/Desktop/temp/!/android/hardware/gnss/V2_0/IGnssConfiguration.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */