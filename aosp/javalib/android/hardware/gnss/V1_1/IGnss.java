package android.hardware.gnss.V1_1;

import android.hardware.gnss.V1_0.GnssLocation;
import android.hardware.gnss.V1_0.IAGnss;
import android.hardware.gnss.V1_0.IAGnssRil;
import android.hardware.gnss.V1_0.IGnss;
import android.hardware.gnss.V1_0.IGnssBatching;
import android.hardware.gnss.V1_0.IGnssCallback;
import android.hardware.gnss.V1_0.IGnssConfiguration;
import android.hardware.gnss.V1_0.IGnssDebug;
import android.hardware.gnss.V1_0.IGnssGeofencing;
import android.hardware.gnss.V1_0.IGnssMeasurement;
import android.hardware.gnss.V1_0.IGnssNavigationMessage;
import android.hardware.gnss.V1_0.IGnssNi;
import android.hardware.gnss.V1_0.IGnssXtra;
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

public interface IGnss extends IGnss {
  public static final String kInterfaceName = "android.hardware.gnss@1.1::IGnss";
  
  static IGnss asInterface(IHwBinder paramIHwBinder) {
    if (paramIHwBinder == null)
      return null; 
    IHwInterface iHwInterface = paramIHwBinder.queryLocalInterface("android.hardware.gnss@1.1::IGnss");
    if (iHwInterface != null && iHwInterface instanceof IGnss)
      return (IGnss)iHwInterface; 
    Proxy proxy = new Proxy(paramIHwBinder);
    try {
      Iterator<String> iterator = proxy.interfaceChain().iterator();
      while (iterator.hasNext()) {
        boolean bool = ((String)iterator.next()).equals("android.hardware.gnss@1.1::IGnss");
        if (bool)
          return proxy; 
      } 
    } catch (RemoteException remoteException) {}
    return null;
  }
  
  static IGnss castFrom(IHwInterface paramIHwInterface) {
    IGnss iGnss;
    if (paramIHwInterface == null) {
      paramIHwInterface = null;
    } else {
      iGnss = asInterface(paramIHwInterface.asBinder());
    } 
    return iGnss;
  }
  
  static IGnss getService() throws RemoteException {
    return getService("default");
  }
  
  static IGnss getService(String paramString) throws RemoteException {
    return asInterface(HwBinder.getService("android.hardware.gnss@1.1::IGnss", paramString));
  }
  
  static IGnss getService(String paramString, boolean paramBoolean) throws RemoteException {
    return asInterface(HwBinder.getService("android.hardware.gnss@1.1::IGnss", paramString, paramBoolean));
  }
  
  static IGnss getService(boolean paramBoolean) throws RemoteException {
    return getService("default", paramBoolean);
  }
  
  IHwBinder asBinder();
  
  void debug(NativeHandle paramNativeHandle, ArrayList<String> paramArrayList) throws RemoteException;
  
  DebugInfo getDebugInfo() throws RemoteException;
  
  IGnssConfiguration getExtensionGnssConfiguration_1_1() throws RemoteException;
  
  IGnssMeasurement getExtensionGnssMeasurement_1_1() throws RemoteException;
  
  ArrayList<byte[]> getHashChain() throws RemoteException;
  
  boolean injectBestLocation(GnssLocation paramGnssLocation) throws RemoteException;
  
  ArrayList<String> interfaceChain() throws RemoteException;
  
  String interfaceDescriptor() throws RemoteException;
  
  boolean linkToDeath(IHwBinder.DeathRecipient paramDeathRecipient, long paramLong) throws RemoteException;
  
  void notifySyspropsChanged() throws RemoteException;
  
  void ping() throws RemoteException;
  
  boolean setCallback_1_1(IGnssCallback paramIGnssCallback) throws RemoteException;
  
  void setHALInstrumentation() throws RemoteException;
  
  boolean setPositionMode_1_1(byte paramByte, int paramInt1, int paramInt2, int paramInt3, int paramInt4, boolean paramBoolean) throws RemoteException;
  
  boolean unlinkToDeath(IHwBinder.DeathRecipient paramDeathRecipient) throws RemoteException;
  
  public static final class Proxy implements IGnss {
    private IHwBinder mRemote;
    
    public Proxy(IHwBinder param1IHwBinder) {
      Objects.requireNonNull(param1IHwBinder);
      this.mRemote = param1IHwBinder;
    }
    
    public IHwBinder asBinder() {
      return this.mRemote;
    }
    
    public void cleanup() throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.gnss@1.0::IGnss");
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(4, null, hwParcel, 0);
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
    
    public void deleteAidingData(short param1Short) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.gnss@1.0::IGnss");
      null.writeInt16(param1Short);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(7, null, hwParcel, 0);
        hwParcel.verifySuccess();
        null.releaseTemporaryStorage();
        return;
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
    
    public IAGnss getExtensionAGnss() throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.gnss@1.0::IGnss");
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(11, null, hwParcel, 0);
        hwParcel.verifySuccess();
        null.releaseTemporaryStorage();
        return IAGnss.asInterface(hwParcel.readStrongBinder());
      } finally {
        hwParcel.release();
      } 
    }
    
    public IAGnssRil getExtensionAGnssRil() throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.gnss@1.0::IGnss");
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(9, null, hwParcel, 0);
        hwParcel.verifySuccess();
        null.releaseTemporaryStorage();
        return IAGnssRil.asInterface(hwParcel.readStrongBinder());
      } finally {
        hwParcel.release();
      } 
    }
    
    public IGnssBatching getExtensionGnssBatching() throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.gnss@1.0::IGnss");
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(18, null, hwParcel, 0);
        hwParcel.verifySuccess();
        null.releaseTemporaryStorage();
        return IGnssBatching.asInterface(hwParcel.readStrongBinder());
      } finally {
        hwParcel.release();
      } 
    }
    
    public IGnssConfiguration getExtensionGnssConfiguration() throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.gnss@1.0::IGnss");
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(16, null, hwParcel, 0);
        hwParcel.verifySuccess();
        null.releaseTemporaryStorage();
        return IGnssConfiguration.asInterface(hwParcel.readStrongBinder());
      } finally {
        hwParcel.release();
      } 
    }
    
    public IGnssConfiguration getExtensionGnssConfiguration_1_1() throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.gnss@1.1::IGnss");
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(21, null, hwParcel, 0);
        hwParcel.verifySuccess();
        null.releaseTemporaryStorage();
        return IGnssConfiguration.asInterface(hwParcel.readStrongBinder());
      } finally {
        hwParcel.release();
      } 
    }
    
    public IGnssDebug getExtensionGnssDebug() throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.gnss@1.0::IGnss");
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(17, null, hwParcel, 0);
        hwParcel.verifySuccess();
        null.releaseTemporaryStorage();
        return IGnssDebug.asInterface(hwParcel.readStrongBinder());
      } finally {
        hwParcel.release();
      } 
    }
    
    public IGnssGeofencing getExtensionGnssGeofencing() throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.gnss@1.0::IGnss");
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(10, null, hwParcel, 0);
        hwParcel.verifySuccess();
        null.releaseTemporaryStorage();
        return IGnssGeofencing.asInterface(hwParcel.readStrongBinder());
      } finally {
        hwParcel.release();
      } 
    }
    
    public IGnssMeasurement getExtensionGnssMeasurement() throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.gnss@1.0::IGnss");
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(13, null, hwParcel, 0);
        hwParcel.verifySuccess();
        null.releaseTemporaryStorage();
        return IGnssMeasurement.asInterface(hwParcel.readStrongBinder());
      } finally {
        hwParcel.release();
      } 
    }
    
    public IGnssMeasurement getExtensionGnssMeasurement_1_1() throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.gnss@1.1::IGnss");
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(22, null, hwParcel, 0);
        hwParcel.verifySuccess();
        null.releaseTemporaryStorage();
        return IGnssMeasurement.asInterface(hwParcel.readStrongBinder());
      } finally {
        hwParcel.release();
      } 
    }
    
    public IGnssNavigationMessage getExtensionGnssNavigationMessage() throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.gnss@1.0::IGnss");
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(14, null, hwParcel, 0);
        hwParcel.verifySuccess();
        null.releaseTemporaryStorage();
        return IGnssNavigationMessage.asInterface(hwParcel.readStrongBinder());
      } finally {
        hwParcel.release();
      } 
    }
    
    public IGnssNi getExtensionGnssNi() throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.gnss@1.0::IGnss");
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(12, null, hwParcel, 0);
        hwParcel.verifySuccess();
        null.releaseTemporaryStorage();
        return IGnssNi.asInterface(hwParcel.readStrongBinder());
      } finally {
        hwParcel.release();
      } 
    }
    
    public IGnssXtra getExtensionXtra() throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.gnss@1.0::IGnss");
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(15, null, hwParcel, 0);
        hwParcel.verifySuccess();
        null.releaseTemporaryStorage();
        return IGnssXtra.asInterface(hwParcel.readStrongBinder());
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
    
    public boolean injectBestLocation(GnssLocation param1GnssLocation) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.gnss@1.1::IGnss");
      param1GnssLocation.writeToParcel(null);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(23, null, hwParcel, 0);
        hwParcel.verifySuccess();
        null.releaseTemporaryStorage();
        return hwParcel.readBool();
      } finally {
        hwParcel.release();
      } 
    }
    
    public boolean injectLocation(double param1Double1, double param1Double2, float param1Float) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.gnss@1.0::IGnss");
      null.writeDouble(param1Double1);
      null.writeDouble(param1Double2);
      null.writeFloat(param1Float);
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
    
    public boolean injectTime(long param1Long1, long param1Long2, int param1Int) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.gnss@1.0::IGnss");
      null.writeInt64(param1Long1);
      null.writeInt64(param1Long2);
      null.writeInt32(param1Int);
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
    
    public boolean setCallback(IGnssCallback param1IGnssCallback) throws RemoteException {
      IHwBinder iHwBinder;
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.gnss@1.0::IGnss");
      if (param1IGnssCallback == null) {
        param1IGnssCallback = null;
      } else {
        iHwBinder = param1IGnssCallback.asBinder();
      } 
      null.writeStrongBinder(iHwBinder);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(1, null, hwParcel, 0);
        hwParcel.verifySuccess();
        null.releaseTemporaryStorage();
        return hwParcel.readBool();
      } finally {
        hwParcel.release();
      } 
    }
    
    public boolean setCallback_1_1(IGnssCallback param1IGnssCallback) throws RemoteException {
      IHwBinder iHwBinder;
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.gnss@1.1::IGnss");
      if (param1IGnssCallback == null) {
        param1IGnssCallback = null;
      } else {
        iHwBinder = param1IGnssCallback.asBinder();
      } 
      null.writeStrongBinder(iHwBinder);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(19, null, hwParcel, 0);
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
    
    public boolean setPositionMode(byte param1Byte, int param1Int1, int param1Int2, int param1Int3, int param1Int4) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.gnss@1.0::IGnss");
      null.writeInt8(param1Byte);
      null.writeInt32(param1Int1);
      null.writeInt32(param1Int2);
      null.writeInt32(param1Int3);
      null.writeInt32(param1Int4);
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
    
    public boolean setPositionMode_1_1(byte param1Byte, int param1Int1, int param1Int2, int param1Int3, int param1Int4, boolean param1Boolean) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.gnss@1.1::IGnss");
      null.writeInt8(param1Byte);
      null.writeInt32(param1Int1);
      null.writeInt32(param1Int2);
      null.writeInt32(param1Int3);
      null.writeInt32(param1Int4);
      null.writeBool(param1Boolean);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(20, null, hwParcel, 0);
        hwParcel.verifySuccess();
        null.releaseTemporaryStorage();
        param1Boolean = hwParcel.readBool();
        return param1Boolean;
      } finally {
        hwParcel.release();
      } 
    }
    
    public boolean start() throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.gnss@1.0::IGnss");
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
    
    public boolean stop() throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.gnss@1.0::IGnss");
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
    
    public String toString() {
      try {
        StringBuilder stringBuilder = new StringBuilder();
        this();
        stringBuilder.append(interfaceDescriptor());
        stringBuilder.append("@Proxy");
        return stringBuilder.toString();
      } catch (RemoteException remoteException) {
        return "[class or subclass of android.hardware.gnss@1.1::IGnss]@Proxy";
      } 
    }
    
    public boolean unlinkToDeath(IHwBinder.DeathRecipient param1DeathRecipient) throws RemoteException {
      return this.mRemote.unlinkToDeath(param1DeathRecipient);
    }
  }
  
  public static abstract class Stub extends HwBinder implements IGnss {
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
                -75, -15, -12, -63, -67, 109, -25, 26, -114, 113, 
                -41, 15, 87, -51, -85, -112, 74, -64, 36, -95, 
                47, 61, -18, 62, 33, 115, 119, 10, 69, -125, 
                -68, -62 }, { 
                -19, -26, -105, 16, -61, -87, 92, 44, -66, -127, 
                -114, 108, -117, -73, 44, 120, 22, -126, 63, -84, 
                -27, -4, 33, -63, 119, 49, -78, 111, 65, -39, 
                77, 101 }, { 
                -20, Byte.MAX_VALUE, -41, -98, -48, 45, -6, -123, -68, 73, 
                -108, 38, -83, -82, 62, -66, 35, -17, 5, 36, 
                -13, -51, 105, 87, 19, -109, 36, -72, 59, 24, 
                -54, 76 } }));
    }
    
    public final ArrayList<String> interfaceChain() {
      return new ArrayList<>(Arrays.asList(new String[] { "android.hardware.gnss@1.1::IGnss", "android.hardware.gnss@1.0::IGnss", "android.hidl.base@1.0::IBase" }));
    }
    
    public final String interfaceDescriptor() {
      return "android.hardware.gnss@1.1::IGnss";
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
      IGnssMeasurement iGnssMeasurement;
      GnssLocation gnssLocation1;
      IHwBinder iHwBinder12;
      IGnssConfiguration iGnssConfiguration;
      byte[] arrayOfByte1;
      IHwBinder iHwBinder11;
      IGnssBatching iGnssBatching;
      HwBlob hwBlob1;
      IHwBinder iHwBinder10;
      IGnssDebug iGnssDebug1;
      IHwBinder iHwBinder9;
      IGnssConfiguration iGnssConfiguration1;
      IHwBinder iHwBinder8;
      IGnssXtra iGnssXtra1;
      IHwBinder iHwBinder7;
      IGnssNavigationMessage iGnssNavigationMessage1;
      IHwBinder iHwBinder6;
      IGnssMeasurement iGnssMeasurement1;
      IHwBinder iHwBinder5;
      IGnssNi iGnssNi1;
      IHwBinder iHwBinder4;
      IAGnss iAGnss1;
      IHwBinder iHwBinder3;
      IGnssGeofencing iGnssGeofencing1;
      IHwBinder iHwBinder2;
      IAGnssRil iAGnssRil1;
      IHwBinder iHwBinder1;
      GnssLocation gnssLocation2;
      byte[] arrayOfByte2 = null;
      HwBlob hwBlob2 = null;
      IGnssDebug iGnssDebug2 = null;
      IGnssConfiguration iGnssConfiguration2 = null;
      IGnssXtra iGnssXtra2 = null;
      IGnssNavigationMessage iGnssNavigationMessage2 = null;
      IGnssMeasurement iGnssMeasurement2 = null;
      IGnssNi iGnssNi2 = null;
      IAGnss iAGnss2 = null;
      IGnssGeofencing iGnssGeofencing2 = null;
      IAGnssRil iAGnssRil2 = null;
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
              arrayList1 = getHashChain();
              param1HwParcel2.writeStatus(0);
              hwBlob2 = new HwBlob(16);
              param1Int2 = arrayList1.size();
              hwBlob2.putInt32(8L, param1Int2);
              hwBlob2.putBool(12L, false);
              hwBlob3 = new HwBlob(param1Int2 * 32);
              param1Int1 = 0;
              while (param1Int1 < param1Int2) {
                long l = (param1Int1 * 32);
                arrayOfByte2 = arrayList1.get(param1Int1);
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
        case 23:
          arrayList.enforceInterface("android.hardware.gnss@1.1::IGnss");
          gnssLocation2 = new GnssLocation();
          gnssLocation2.readFromParcel((HwParcel)arrayList);
          bool = injectBestLocation(gnssLocation2);
          param1HwParcel2.writeStatus(0);
          param1HwParcel2.writeBool(bool);
          param1HwParcel2.send();
        case 22:
          arrayList.enforceInterface("android.hardware.gnss@1.1::IGnss");
          iGnssMeasurement = getExtensionGnssMeasurement_1_1();
          param1HwParcel2.writeStatus(0);
          if (iGnssMeasurement == null) {
            gnssLocation1 = gnssLocation2;
          } else {
            iHwBinder12 = gnssLocation1.asBinder();
          } 
          param1HwParcel2.writeStrongBinder(iHwBinder12);
          param1HwParcel2.send();
        case 21:
          iHwBinder12.enforceInterface("android.hardware.gnss@1.1::IGnss");
          iGnssConfiguration = getExtensionGnssConfiguration_1_1();
          param1HwParcel2.writeStatus(0);
          if (iGnssConfiguration == null) {
            arrayOfByte1 = arrayOfByte2;
          } else {
            iHwBinder11 = arrayOfByte1.asBinder();
          } 
          param1HwParcel2.writeStrongBinder(iHwBinder11);
          param1HwParcel2.send();
        case 20:
          iHwBinder11.enforceInterface("android.hardware.gnss@1.1::IGnss");
          bool = setPositionMode_1_1(iHwBinder11.readInt8(), iHwBinder11.readInt32(), iHwBinder11.readInt32(), iHwBinder11.readInt32(), iHwBinder11.readInt32(), iHwBinder11.readBool());
          param1HwParcel2.writeStatus(0);
          param1HwParcel2.writeBool(bool);
          param1HwParcel2.send();
        case 19:
          iHwBinder11.enforceInterface("android.hardware.gnss@1.1::IGnss");
          bool = setCallback_1_1(IGnssCallback.asInterface(iHwBinder11.readStrongBinder()));
          param1HwParcel2.writeStatus(0);
          param1HwParcel2.writeBool(bool);
          param1HwParcel2.send();
        case 18:
          iHwBinder11.enforceInterface("android.hardware.gnss@1.0::IGnss");
          iGnssBatching = getExtensionGnssBatching();
          param1HwParcel2.writeStatus(0);
          if (iGnssBatching == null) {
            hwBlob1 = hwBlob2;
          } else {
            iHwBinder10 = hwBlob1.asBinder();
          } 
          param1HwParcel2.writeStrongBinder(iHwBinder10);
          param1HwParcel2.send();
        case 17:
          iHwBinder10.enforceInterface("android.hardware.gnss@1.0::IGnss");
          iGnssDebug1 = getExtensionGnssDebug();
          param1HwParcel2.writeStatus(0);
          if (iGnssDebug1 == null) {
            iGnssDebug1 = iGnssDebug2;
          } else {
            iHwBinder9 = iGnssDebug1.asBinder();
          } 
          param1HwParcel2.writeStrongBinder(iHwBinder9);
          param1HwParcel2.send();
        case 16:
          iHwBinder9.enforceInterface("android.hardware.gnss@1.0::IGnss");
          iGnssConfiguration1 = getExtensionGnssConfiguration();
          param1HwParcel2.writeStatus(0);
          if (iGnssConfiguration1 == null) {
            iGnssConfiguration1 = iGnssConfiguration2;
          } else {
            iHwBinder8 = iGnssConfiguration1.asBinder();
          } 
          param1HwParcel2.writeStrongBinder(iHwBinder8);
          param1HwParcel2.send();
        case 15:
          iHwBinder8.enforceInterface("android.hardware.gnss@1.0::IGnss");
          iGnssXtra1 = getExtensionXtra();
          param1HwParcel2.writeStatus(0);
          if (iGnssXtra1 == null) {
            iGnssXtra1 = iGnssXtra2;
          } else {
            iHwBinder7 = iGnssXtra1.asBinder();
          } 
          param1HwParcel2.writeStrongBinder(iHwBinder7);
          param1HwParcel2.send();
        case 14:
          iHwBinder7.enforceInterface("android.hardware.gnss@1.0::IGnss");
          iGnssNavigationMessage1 = getExtensionGnssNavigationMessage();
          param1HwParcel2.writeStatus(0);
          if (iGnssNavigationMessage1 == null) {
            iGnssNavigationMessage1 = iGnssNavigationMessage2;
          } else {
            iHwBinder6 = iGnssNavigationMessage1.asBinder();
          } 
          param1HwParcel2.writeStrongBinder(iHwBinder6);
          param1HwParcel2.send();
        case 13:
          iHwBinder6.enforceInterface("android.hardware.gnss@1.0::IGnss");
          iGnssMeasurement1 = getExtensionGnssMeasurement();
          param1HwParcel2.writeStatus(0);
          if (iGnssMeasurement1 == null) {
            iGnssMeasurement1 = iGnssMeasurement2;
          } else {
            iHwBinder5 = iGnssMeasurement1.asBinder();
          } 
          param1HwParcel2.writeStrongBinder(iHwBinder5);
          param1HwParcel2.send();
        case 12:
          iHwBinder5.enforceInterface("android.hardware.gnss@1.0::IGnss");
          iGnssNi1 = getExtensionGnssNi();
          param1HwParcel2.writeStatus(0);
          if (iGnssNi1 == null) {
            iGnssNi1 = iGnssNi2;
          } else {
            iHwBinder4 = iGnssNi1.asBinder();
          } 
          param1HwParcel2.writeStrongBinder(iHwBinder4);
          param1HwParcel2.send();
        case 11:
          iHwBinder4.enforceInterface("android.hardware.gnss@1.0::IGnss");
          iAGnss1 = getExtensionAGnss();
          param1HwParcel2.writeStatus(0);
          if (iAGnss1 == null) {
            iAGnss1 = iAGnss2;
          } else {
            iHwBinder3 = iAGnss1.asBinder();
          } 
          param1HwParcel2.writeStrongBinder(iHwBinder3);
          param1HwParcel2.send();
        case 10:
          iHwBinder3.enforceInterface("android.hardware.gnss@1.0::IGnss");
          iGnssGeofencing1 = getExtensionGnssGeofencing();
          param1HwParcel2.writeStatus(0);
          if (iGnssGeofencing1 == null) {
            iGnssGeofencing1 = iGnssGeofencing2;
          } else {
            iHwBinder2 = iGnssGeofencing1.asBinder();
          } 
          param1HwParcel2.writeStrongBinder(iHwBinder2);
          param1HwParcel2.send();
        case 9:
          iHwBinder2.enforceInterface("android.hardware.gnss@1.0::IGnss");
          iAGnssRil1 = getExtensionAGnssRil();
          param1HwParcel2.writeStatus(0);
          if (iAGnssRil1 == null) {
            iAGnssRil1 = iAGnssRil2;
          } else {
            iHwBinder1 = iAGnssRil1.asBinder();
          } 
          param1HwParcel2.writeStrongBinder(iHwBinder1);
          param1HwParcel2.send();
        case 8:
          iHwBinder1.enforceInterface("android.hardware.gnss@1.0::IGnss");
          bool = setPositionMode(iHwBinder1.readInt8(), iHwBinder1.readInt32(), iHwBinder1.readInt32(), iHwBinder1.readInt32(), iHwBinder1.readInt32());
          param1HwParcel2.writeStatus(0);
          param1HwParcel2.writeBool(bool);
          param1HwParcel2.send();
        case 7:
          iHwBinder1.enforceInterface("android.hardware.gnss@1.0::IGnss");
          deleteAidingData(iHwBinder1.readInt16());
          param1HwParcel2.writeStatus(0);
          param1HwParcel2.send();
        case 6:
          iHwBinder1.enforceInterface("android.hardware.gnss@1.0::IGnss");
          bool = injectLocation(iHwBinder1.readDouble(), iHwBinder1.readDouble(), iHwBinder1.readFloat());
          param1HwParcel2.writeStatus(0);
          param1HwParcel2.writeBool(bool);
          param1HwParcel2.send();
        case 5:
          iHwBinder1.enforceInterface("android.hardware.gnss@1.0::IGnss");
          bool = injectTime(iHwBinder1.readInt64(), iHwBinder1.readInt64(), iHwBinder1.readInt32());
          param1HwParcel2.writeStatus(0);
          param1HwParcel2.writeBool(bool);
          param1HwParcel2.send();
        case 4:
          iHwBinder1.enforceInterface("android.hardware.gnss@1.0::IGnss");
          cleanup();
          param1HwParcel2.writeStatus(0);
          param1HwParcel2.send();
        case 3:
          iHwBinder1.enforceInterface("android.hardware.gnss@1.0::IGnss");
          bool = stop();
          param1HwParcel2.writeStatus(0);
          param1HwParcel2.writeBool(bool);
          param1HwParcel2.send();
        case 2:
          iHwBinder1.enforceInterface("android.hardware.gnss@1.0::IGnss");
          bool = start();
          param1HwParcel2.writeStatus(0);
          param1HwParcel2.writeBool(bool);
          param1HwParcel2.send();
        case 1:
          break;
      } 
      iHwBinder1.enforceInterface("android.hardware.gnss@1.0::IGnss");
      boolean bool = setCallback(IGnssCallback.asInterface(iHwBinder1.readStrongBinder()));
      param1HwParcel2.writeStatus(0);
      param1HwParcel2.writeBool(bool);
      param1HwParcel2.send();
    }
    
    public final void ping() {}
    
    public IHwInterface queryLocalInterface(String param1String) {
      return (IHwInterface)("android.hardware.gnss@1.1::IGnss".equals(param1String) ? this : null);
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


/* Location:              /home/chun/Desktop/temp/!/android/hardware/gnss/V1_1/IGnss.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */