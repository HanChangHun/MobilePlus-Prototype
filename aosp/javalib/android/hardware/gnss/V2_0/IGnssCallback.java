package android.hardware.gnss.V2_0;

import android.hardware.gnss.V1_0.GnssLocation;
import android.hardware.gnss.V1_1.IGnssCallback;
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

public interface IGnssCallback extends IGnssCallback {
  public static final String kInterfaceName = "android.hardware.gnss@2.0::IGnssCallback";
  
  static IGnssCallback asInterface(IHwBinder paramIHwBinder) {
    if (paramIHwBinder == null)
      return null; 
    IHwInterface iHwInterface = paramIHwBinder.queryLocalInterface("android.hardware.gnss@2.0::IGnssCallback");
    if (iHwInterface != null && iHwInterface instanceof IGnssCallback)
      return (IGnssCallback)iHwInterface; 
    Proxy proxy = new Proxy(paramIHwBinder);
    try {
      Iterator<String> iterator = proxy.interfaceChain().iterator();
      while (iterator.hasNext()) {
        boolean bool = ((String)iterator.next()).equals("android.hardware.gnss@2.0::IGnssCallback");
        if (bool)
          return proxy; 
      } 
    } catch (RemoteException remoteException) {}
    return null;
  }
  
  static IGnssCallback castFrom(IHwInterface paramIHwInterface) {
    IGnssCallback iGnssCallback;
    if (paramIHwInterface == null) {
      paramIHwInterface = null;
    } else {
      iGnssCallback = asInterface(paramIHwInterface.asBinder());
    } 
    return iGnssCallback;
  }
  
  static IGnssCallback getService() throws RemoteException {
    return getService("default");
  }
  
  static IGnssCallback getService(String paramString) throws RemoteException {
    return asInterface(HwBinder.getService("android.hardware.gnss@2.0::IGnssCallback", paramString));
  }
  
  static IGnssCallback getService(String paramString, boolean paramBoolean) throws RemoteException {
    return asInterface(HwBinder.getService("android.hardware.gnss@2.0::IGnssCallback", paramString, paramBoolean));
  }
  
  static IGnssCallback getService(boolean paramBoolean) throws RemoteException {
    return getService("default", paramBoolean);
  }
  
  IHwBinder asBinder();
  
  void debug(NativeHandle paramNativeHandle, ArrayList<String> paramArrayList) throws RemoteException;
  
  DebugInfo getDebugInfo() throws RemoteException;
  
  ArrayList<byte[]> getHashChain() throws RemoteException;
  
  void gnssLocationCb_2_0(GnssLocation paramGnssLocation) throws RemoteException;
  
  void gnssRequestLocationCb_2_0(boolean paramBoolean1, boolean paramBoolean2) throws RemoteException;
  
  void gnssSetCapabilitiesCb_2_0(int paramInt) throws RemoteException;
  
  void gnssSvStatusCb_2_0(ArrayList<GnssSvInfo> paramArrayList) throws RemoteException;
  
  ArrayList<String> interfaceChain() throws RemoteException;
  
  String interfaceDescriptor() throws RemoteException;
  
  boolean linkToDeath(IHwBinder.DeathRecipient paramDeathRecipient, long paramLong) throws RemoteException;
  
  void notifySyspropsChanged() throws RemoteException;
  
  void ping() throws RemoteException;
  
  void setHALInstrumentation() throws RemoteException;
  
  boolean unlinkToDeath(IHwBinder.DeathRecipient paramDeathRecipient) throws RemoteException;
  
  public static final class Capabilities {
    public static final int GEOFENCING = 32;
    
    public static final int LOW_POWER_MODE = 256;
    
    public static final int MEASUREMENTS = 64;
    
    public static final int MEASUREMENT_CORRECTIONS = 1024;
    
    public static final int MSA = 4;
    
    public static final int MSB = 2;
    
    public static final int NAV_MESSAGES = 128;
    
    public static final int ON_DEMAND_TIME = 16;
    
    public static final int SATELLITE_BLACKLIST = 512;
    
    public static final int SCHEDULING = 1;
    
    public static final int SINGLE_SHOT = 8;
    
    public static final String dumpBitfield(int param1Int) {
      ArrayList<String> arrayList = new ArrayList();
      int i = 0;
      if ((param1Int & 0x1) == 1) {
        arrayList.add("SCHEDULING");
        i = false | true;
      } 
      int j = i;
      if ((param1Int & 0x2) == 2) {
        arrayList.add("MSB");
        j = i | 0x2;
      } 
      i = j;
      if ((param1Int & 0x4) == 4) {
        arrayList.add("MSA");
        i = j | 0x4;
      } 
      j = i;
      if ((param1Int & 0x8) == 8) {
        arrayList.add("SINGLE_SHOT");
        j = i | 0x8;
      } 
      i = j;
      if ((param1Int & 0x10) == 16) {
        arrayList.add("ON_DEMAND_TIME");
        i = j | 0x10;
      } 
      int k = i;
      if ((param1Int & 0x20) == 32) {
        arrayList.add("GEOFENCING");
        k = i | 0x20;
      } 
      j = k;
      if ((param1Int & 0x40) == 64) {
        arrayList.add("MEASUREMENTS");
        j = k | 0x40;
      } 
      i = j;
      if ((param1Int & 0x80) == 128) {
        arrayList.add("NAV_MESSAGES");
        i = j | 0x80;
      } 
      j = i;
      if ((param1Int & 0x100) == 256) {
        arrayList.add("LOW_POWER_MODE");
        j = i | 0x100;
      } 
      i = j;
      if ((param1Int & 0x200) == 512) {
        arrayList.add("SATELLITE_BLACKLIST");
        i = j | 0x200;
      } 
      j = i;
      if ((param1Int & 0x400) == 1024) {
        arrayList.add("MEASUREMENT_CORRECTIONS");
        j = i | 0x400;
      } 
      if (param1Int != j) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("0x");
        stringBuilder.append(Integer.toHexString(j & param1Int));
        arrayList.add(stringBuilder.toString());
      } 
      return String.join(" | ", (Iterable)arrayList);
    }
    
    public static final String toString(int param1Int) {
      if (param1Int == 1)
        return "SCHEDULING"; 
      if (param1Int == 2)
        return "MSB"; 
      if (param1Int == 4)
        return "MSA"; 
      if (param1Int == 8)
        return "SINGLE_SHOT"; 
      if (param1Int == 16)
        return "ON_DEMAND_TIME"; 
      if (param1Int == 32)
        return "GEOFENCING"; 
      if (param1Int == 64)
        return "MEASUREMENTS"; 
      if (param1Int == 128)
        return "NAV_MESSAGES"; 
      if (param1Int == 256)
        return "LOW_POWER_MODE"; 
      if (param1Int == 512)
        return "SATELLITE_BLACKLIST"; 
      if (param1Int == 1024)
        return "MEASUREMENT_CORRECTIONS"; 
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("0x");
      stringBuilder.append(Integer.toHexString(param1Int));
      return stringBuilder.toString();
    }
  }
  
  public static final class GnssSvInfo {
    public byte constellation = (byte)0;
    
    public android.hardware.gnss.V1_0.IGnssCallback.GnssSvInfo v1_0 = new android.hardware.gnss.V1_0.IGnssCallback.GnssSvInfo();
    
    public static final ArrayList<GnssSvInfo> readVectorFromParcel(HwParcel param1HwParcel) {
      ArrayList<GnssSvInfo> arrayList = new ArrayList();
      HwBlob hwBlob = param1HwParcel.readBuffer(16L);
      int i = hwBlob.getInt32(8L);
      hwBlob = param1HwParcel.readEmbeddedBuffer((i * 28), hwBlob.handle(), 0L, true);
      arrayList.clear();
      for (byte b = 0; b < i; b++) {
        GnssSvInfo gnssSvInfo = new GnssSvInfo();
        gnssSvInfo.readEmbeddedFromParcel(param1HwParcel, hwBlob, (b * 28));
        arrayList.add(gnssSvInfo);
      } 
      return arrayList;
    }
    
    public static final void writeVectorToParcel(HwParcel param1HwParcel, ArrayList<GnssSvInfo> param1ArrayList) {
      HwBlob hwBlob1 = new HwBlob(16);
      int i = param1ArrayList.size();
      hwBlob1.putInt32(8L, i);
      hwBlob1.putBool(12L, false);
      HwBlob hwBlob2 = new HwBlob(i * 28);
      for (byte b = 0; b < i; b++)
        ((GnssSvInfo)param1ArrayList.get(b)).writeEmbeddedToBlob(hwBlob2, (b * 28)); 
      hwBlob1.putBlob(0L, hwBlob2);
      param1HwParcel.writeBuffer(hwBlob1);
    }
    
    public final boolean equals(Object param1Object) {
      if (this == param1Object)
        return true; 
      if (param1Object == null)
        return false; 
      if (param1Object.getClass() != GnssSvInfo.class)
        return false; 
      param1Object = param1Object;
      return !HidlSupport.deepEquals(this.v1_0, ((GnssSvInfo)param1Object).v1_0) ? false : (!(this.constellation != ((GnssSvInfo)param1Object).constellation));
    }
    
    public final int hashCode() {
      return Objects.hash(new Object[] { Integer.valueOf(HidlSupport.deepHashCode(this.v1_0)), Integer.valueOf(HidlSupport.deepHashCode(Byte.valueOf(this.constellation))) });
    }
    
    public final void readEmbeddedFromParcel(HwParcel param1HwParcel, HwBlob param1HwBlob, long param1Long) {
      this.v1_0.readEmbeddedFromParcel(param1HwParcel, param1HwBlob, 0L + param1Long);
      this.constellation = param1HwBlob.getInt8(24L + param1Long);
    }
    
    public final void readFromParcel(HwParcel param1HwParcel) {
      readEmbeddedFromParcel(param1HwParcel, param1HwParcel.readBuffer(28L), 0L);
    }
    
    public final String toString() {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("{");
      stringBuilder.append(".v1_0 = ");
      stringBuilder.append(this.v1_0);
      stringBuilder.append(", .constellation = ");
      stringBuilder.append(GnssConstellationType.toString(this.constellation));
      stringBuilder.append("}");
      return stringBuilder.toString();
    }
    
    public final void writeEmbeddedToBlob(HwBlob param1HwBlob, long param1Long) {
      this.v1_0.writeEmbeddedToBlob(param1HwBlob, 0L + param1Long);
      param1HwBlob.putInt8(24L + param1Long, this.constellation);
    }
    
    public final void writeToParcel(HwParcel param1HwParcel) {
      HwBlob hwBlob = new HwBlob(28);
      writeEmbeddedToBlob(hwBlob, 0L);
      param1HwParcel.writeBuffer(hwBlob);
    }
  }
  
  public static final class Proxy implements IGnssCallback {
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
    
    public void gnssAcquireWakelockCb() throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.gnss@1.0::IGnssCallback");
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(6, null, hwParcel, 0);
        hwParcel.verifySuccess();
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void gnssLocationCb(GnssLocation param1GnssLocation) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.gnss@1.0::IGnssCallback");
      param1GnssLocation.writeToParcel(null);
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
    
    public void gnssLocationCb_2_0(GnssLocation param1GnssLocation) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.gnss@2.0::IGnssCallback");
      param1GnssLocation.writeToParcel(null);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(13, null, hwParcel, 0);
        hwParcel.verifySuccess();
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void gnssNameCb(String param1String) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.gnss@1.1::IGnssCallback");
      null.writeString(param1String);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(10, null, hwParcel, 0);
        hwParcel.verifySuccess();
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void gnssNmeaCb(long param1Long, String param1String) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.gnss@1.0::IGnssCallback");
      null.writeInt64(param1Long);
      null.writeString(param1String);
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
    
    public void gnssReleaseWakelockCb() throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.gnss@1.0::IGnssCallback");
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
    
    public void gnssRequestLocationCb(boolean param1Boolean) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.gnss@1.1::IGnssCallback");
      null.writeBool(param1Boolean);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(11, null, hwParcel, 0);
        hwParcel.verifySuccess();
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void gnssRequestLocationCb_2_0(boolean param1Boolean1, boolean param1Boolean2) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.gnss@2.0::IGnssCallback");
      null.writeBool(param1Boolean1);
      null.writeBool(param1Boolean2);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(14, null, hwParcel, 0);
        hwParcel.verifySuccess();
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void gnssRequestTimeCb() throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.gnss@1.0::IGnssCallback");
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(8, null, hwParcel, 0);
        hwParcel.verifySuccess();
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void gnssSetCapabilitesCb(int param1Int) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.gnss@1.0::IGnssCallback");
      null.writeInt32(param1Int);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(5, null, hwParcel, 0);
        hwParcel.verifySuccess();
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void gnssSetCapabilitiesCb_2_0(int param1Int) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.gnss@2.0::IGnssCallback");
      null.writeInt32(param1Int);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(12, null, hwParcel, 0);
        hwParcel.verifySuccess();
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void gnssSetSystemInfoCb(android.hardware.gnss.V1_0.IGnssCallback.GnssSystemInfo param1GnssSystemInfo) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.gnss@1.0::IGnssCallback");
      param1GnssSystemInfo.writeToParcel(null);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(9, null, hwParcel, 0);
        hwParcel.verifySuccess();
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void gnssStatusCb(byte param1Byte) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.gnss@1.0::IGnssCallback");
      null.writeInt8(param1Byte);
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
    
    public void gnssSvStatusCb(android.hardware.gnss.V1_0.IGnssCallback.GnssSvStatus param1GnssSvStatus) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.gnss@1.0::IGnssCallback");
      param1GnssSvStatus.writeToParcel(null);
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
    
    public void gnssSvStatusCb_2_0(ArrayList<IGnssCallback.GnssSvInfo> param1ArrayList) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.gnss@2.0::IGnssCallback");
      IGnssCallback.GnssSvInfo.writeVectorToParcel(null, param1ArrayList);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(15, null, hwParcel, 0);
        hwParcel.verifySuccess();
        null.releaseTemporaryStorage();
        return;
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
        return "[class or subclass of android.hardware.gnss@2.0::IGnssCallback]@Proxy";
      } 
    }
    
    public boolean unlinkToDeath(IHwBinder.DeathRecipient param1DeathRecipient) throws RemoteException {
      return this.mRemote.unlinkToDeath(param1DeathRecipient);
    }
  }
  
  public static abstract class Stub extends HwBinder implements IGnssCallback {
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
      byte[] arrayOfByte = { 
          -100, -77, -33, 43, -34, 44, 108, -43, -3, -106, 
          -73, -60, 21, 85, 66, 12, -84, -41, -30, 118, 
          -91, 86, -58, -124, -81, -111, -73, 70, 28, -122, 
          70, 15 };
      return (ArrayList)new ArrayList<>(Arrays.asList((byte[])new byte[][] { { 
                100, 35, 32, 55, 16, -102, 94, 95, 83, -85, 
                3, 119, -25, 85, -20, 73, 74, -23, 63, -53, 
                82, 121, -26, -18, -89, 29, -20, 46, 122, -58, 
                -5, -4 }, { 
                -118, -43, 91, -61, 91, -77, -88, 62, 101, -64, 
                24, -67, -3, -25, -82, 94, -68, 116, -97, -14, 
                -65, 107, 121, 65, 45, -19, 11, -58, -56, -101, 
                -105, -40 }, arrayOfByte, { 
                -20, Byte.MAX_VALUE, -41, -98, -48, 45, -6, -123, -68, 73, 
                -108, 38, -83, -82, 62, -66, 35, -17, 5, 36, 
                -13, -51, 105, 87, 19, -109, 36, -72, 59, 24, 
                -54, 76 } }));
    }
    
    public final ArrayList<String> interfaceChain() {
      return new ArrayList<>(Arrays.asList(new String[] { "android.hardware.gnss@2.0::IGnssCallback", "android.hardware.gnss@1.1::IGnssCallback", "android.hardware.gnss@1.0::IGnssCallback", "android.hidl.base@1.0::IBase" }));
    }
    
    public final String interfaceDescriptor() {
      return "android.hardware.gnss@2.0::IGnssCallback";
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
      GnssLocation gnssLocation1;
      android.hardware.gnss.V1_0.IGnssCallback.GnssSystemInfo gnssSystemInfo;
      android.hardware.gnss.V1_0.IGnssCallback.GnssSvStatus gnssSvStatus;
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
        case 15:
          arrayList.enforceInterface("android.hardware.gnss@2.0::IGnssCallback");
          gnssSvStatusCb_2_0(IGnssCallback.GnssSvInfo.readVectorFromParcel((HwParcel)arrayList));
          param1HwParcel2.writeStatus(0);
          param1HwParcel2.send();
        case 14:
          arrayList.enforceInterface("android.hardware.gnss@2.0::IGnssCallback");
          gnssRequestLocationCb_2_0(arrayList.readBool(), arrayList.readBool());
          param1HwParcel2.writeStatus(0);
          param1HwParcel2.send();
        case 13:
          arrayList.enforceInterface("android.hardware.gnss@2.0::IGnssCallback");
          gnssLocation1 = new GnssLocation();
          gnssLocation1.readFromParcel((HwParcel)arrayList);
          gnssLocationCb_2_0(gnssLocation1);
          param1HwParcel2.writeStatus(0);
          param1HwParcel2.send();
        case 12:
          arrayList.enforceInterface("android.hardware.gnss@2.0::IGnssCallback");
          gnssSetCapabilitiesCb_2_0(arrayList.readInt32());
          param1HwParcel2.writeStatus(0);
          param1HwParcel2.send();
        case 11:
          arrayList.enforceInterface("android.hardware.gnss@1.1::IGnssCallback");
          gnssRequestLocationCb(arrayList.readBool());
          param1HwParcel2.writeStatus(0);
          param1HwParcel2.send();
        case 10:
          arrayList.enforceInterface("android.hardware.gnss@1.1::IGnssCallback");
          gnssNameCb(arrayList.readString());
          param1HwParcel2.writeStatus(0);
          param1HwParcel2.send();
        case 9:
          arrayList.enforceInterface("android.hardware.gnss@1.0::IGnssCallback");
          gnssSystemInfo = new android.hardware.gnss.V1_0.IGnssCallback.GnssSystemInfo();
          gnssSystemInfo.readFromParcel((HwParcel)arrayList);
          gnssSetSystemInfoCb(gnssSystemInfo);
          param1HwParcel2.writeStatus(0);
          param1HwParcel2.send();
        case 8:
          arrayList.enforceInterface("android.hardware.gnss@1.0::IGnssCallback");
          gnssRequestTimeCb();
          param1HwParcel2.writeStatus(0);
          param1HwParcel2.send();
        case 7:
          arrayList.enforceInterface("android.hardware.gnss@1.0::IGnssCallback");
          gnssReleaseWakelockCb();
          param1HwParcel2.writeStatus(0);
          param1HwParcel2.send();
        case 6:
          arrayList.enforceInterface("android.hardware.gnss@1.0::IGnssCallback");
          gnssAcquireWakelockCb();
          param1HwParcel2.writeStatus(0);
          param1HwParcel2.send();
        case 5:
          arrayList.enforceInterface("android.hardware.gnss@1.0::IGnssCallback");
          gnssSetCapabilitesCb(arrayList.readInt32());
          param1HwParcel2.writeStatus(0);
          param1HwParcel2.send();
        case 4:
          arrayList.enforceInterface("android.hardware.gnss@1.0::IGnssCallback");
          gnssNmeaCb(arrayList.readInt64(), arrayList.readString());
          param1HwParcel2.writeStatus(0);
          param1HwParcel2.send();
        case 3:
          arrayList.enforceInterface("android.hardware.gnss@1.0::IGnssCallback");
          gnssSvStatus = new android.hardware.gnss.V1_0.IGnssCallback.GnssSvStatus();
          gnssSvStatus.readFromParcel((HwParcel)arrayList);
          gnssSvStatusCb(gnssSvStatus);
          param1HwParcel2.writeStatus(0);
          param1HwParcel2.send();
        case 2:
          arrayList.enforceInterface("android.hardware.gnss@1.0::IGnssCallback");
          gnssStatusCb(arrayList.readInt8());
          param1HwParcel2.writeStatus(0);
          param1HwParcel2.send();
        case 1:
          break;
      } 
      arrayList.enforceInterface("android.hardware.gnss@1.0::IGnssCallback");
      GnssLocation gnssLocation = new GnssLocation();
      gnssLocation.readFromParcel((HwParcel)arrayList);
      gnssLocationCb(gnssLocation);
      param1HwParcel2.writeStatus(0);
      param1HwParcel2.send();
    }
    
    public final void ping() {}
    
    public IHwInterface queryLocalInterface(String param1String) {
      return (IHwInterface)("android.hardware.gnss@2.0::IGnssCallback".equals(param1String) ? this : null);
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


/* Location:              /home/chun/Desktop/temp/!/android/hardware/gnss/V2_0/IGnssCallback.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */