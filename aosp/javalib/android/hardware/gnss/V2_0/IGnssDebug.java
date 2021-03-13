package android.hardware.gnss.V2_0;

import android.hardware.gnss.V1_0.IGnssDebug;
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

public interface IGnssDebug extends IGnssDebug {
  public static final String kInterfaceName = "android.hardware.gnss@2.0::IGnssDebug";
  
  static IGnssDebug asInterface(IHwBinder paramIHwBinder) {
    if (paramIHwBinder == null)
      return null; 
    IHwInterface iHwInterface = paramIHwBinder.queryLocalInterface("android.hardware.gnss@2.0::IGnssDebug");
    if (iHwInterface != null && iHwInterface instanceof IGnssDebug)
      return (IGnssDebug)iHwInterface; 
    Proxy proxy = new Proxy(paramIHwBinder);
    try {
      Iterator<String> iterator = proxy.interfaceChain().iterator();
      while (iterator.hasNext()) {
        boolean bool = ((String)iterator.next()).equals("android.hardware.gnss@2.0::IGnssDebug");
        if (bool)
          return proxy; 
      } 
    } catch (RemoteException remoteException) {}
    return null;
  }
  
  static IGnssDebug castFrom(IHwInterface paramIHwInterface) {
    IGnssDebug iGnssDebug;
    if (paramIHwInterface == null) {
      paramIHwInterface = null;
    } else {
      iGnssDebug = asInterface(paramIHwInterface.asBinder());
    } 
    return iGnssDebug;
  }
  
  static IGnssDebug getService() throws RemoteException {
    return getService("default");
  }
  
  static IGnssDebug getService(String paramString) throws RemoteException {
    return asInterface(HwBinder.getService("android.hardware.gnss@2.0::IGnssDebug", paramString));
  }
  
  static IGnssDebug getService(String paramString, boolean paramBoolean) throws RemoteException {
    return asInterface(HwBinder.getService("android.hardware.gnss@2.0::IGnssDebug", paramString, paramBoolean));
  }
  
  static IGnssDebug getService(boolean paramBoolean) throws RemoteException {
    return getService("default", paramBoolean);
  }
  
  IHwBinder asBinder();
  
  void debug(NativeHandle paramNativeHandle, ArrayList<String> paramArrayList) throws RemoteException;
  
  DebugData getDebugData_2_0() throws RemoteException;
  
  DebugInfo getDebugInfo() throws RemoteException;
  
  ArrayList<byte[]> getHashChain() throws RemoteException;
  
  ArrayList<String> interfaceChain() throws RemoteException;
  
  String interfaceDescriptor() throws RemoteException;
  
  boolean linkToDeath(IHwBinder.DeathRecipient paramDeathRecipient, long paramLong) throws RemoteException;
  
  void notifySyspropsChanged() throws RemoteException;
  
  void ping() throws RemoteException;
  
  void setHALInstrumentation() throws RemoteException;
  
  boolean unlinkToDeath(IHwBinder.DeathRecipient paramDeathRecipient) throws RemoteException;
  
  public static final class DebugData {
    public IGnssDebug.PositionDebug position = new IGnssDebug.PositionDebug();
    
    public ArrayList<IGnssDebug.SatelliteData> satelliteDataArray = new ArrayList<>();
    
    public IGnssDebug.TimeDebug time = new IGnssDebug.TimeDebug();
    
    public static final ArrayList<DebugData> readVectorFromParcel(HwParcel param1HwParcel) {
      ArrayList<DebugData> arrayList = new ArrayList();
      HwBlob hwBlob = param1HwParcel.readBuffer(16L);
      int i = hwBlob.getInt32(8L);
      hwBlob = param1HwParcel.readEmbeddedBuffer((i * 112), hwBlob.handle(), 0L, true);
      arrayList.clear();
      for (byte b = 0; b < i; b++) {
        DebugData debugData = new DebugData();
        debugData.readEmbeddedFromParcel(param1HwParcel, hwBlob, (b * 112));
        arrayList.add(debugData);
      } 
      return arrayList;
    }
    
    public static final void writeVectorToParcel(HwParcel param1HwParcel, ArrayList<DebugData> param1ArrayList) {
      HwBlob hwBlob1 = new HwBlob(16);
      int i = param1ArrayList.size();
      hwBlob1.putInt32(8L, i);
      hwBlob1.putBool(12L, false);
      HwBlob hwBlob2 = new HwBlob(i * 112);
      for (byte b = 0; b < i; b++)
        ((DebugData)param1ArrayList.get(b)).writeEmbeddedToBlob(hwBlob2, (b * 112)); 
      hwBlob1.putBlob(0L, hwBlob2);
      param1HwParcel.writeBuffer(hwBlob1);
    }
    
    public final boolean equals(Object param1Object) {
      if (this == param1Object)
        return true; 
      if (param1Object == null)
        return false; 
      if (param1Object.getClass() != DebugData.class)
        return false; 
      param1Object = param1Object;
      return !HidlSupport.deepEquals(this.position, ((DebugData)param1Object).position) ? false : (!HidlSupport.deepEquals(this.time, ((DebugData)param1Object).time) ? false : (!!HidlSupport.deepEquals(this.satelliteDataArray, ((DebugData)param1Object).satelliteDataArray)));
    }
    
    public final int hashCode() {
      return Objects.hash(new Object[] { Integer.valueOf(HidlSupport.deepHashCode(this.position)), Integer.valueOf(HidlSupport.deepHashCode(this.time)), Integer.valueOf(HidlSupport.deepHashCode(this.satelliteDataArray)) });
    }
    
    public final void readEmbeddedFromParcel(HwParcel param1HwParcel, HwBlob param1HwBlob, long param1Long) {
      this.position.readEmbeddedFromParcel(param1HwParcel, param1HwBlob, param1Long + 0L);
      this.time.readEmbeddedFromParcel(param1HwParcel, param1HwBlob, param1Long + 80L);
      int i = param1HwBlob.getInt32(param1Long + 96L + 8L);
      HwBlob hwBlob = param1HwParcel.readEmbeddedBuffer((i * 24), param1HwBlob.handle(), param1Long + 96L + 0L, true);
      this.satelliteDataArray.clear();
      for (byte b = 0; b < i; b++) {
        IGnssDebug.SatelliteData satelliteData = new IGnssDebug.SatelliteData();
        satelliteData.readEmbeddedFromParcel(param1HwParcel, hwBlob, (b * 24));
        this.satelliteDataArray.add(satelliteData);
      } 
    }
    
    public final void readFromParcel(HwParcel param1HwParcel) {
      readEmbeddedFromParcel(param1HwParcel, param1HwParcel.readBuffer(112L), 0L);
    }
    
    public final String toString() {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("{");
      stringBuilder.append(".position = ");
      stringBuilder.append(this.position);
      stringBuilder.append(", .time = ");
      stringBuilder.append(this.time);
      stringBuilder.append(", .satelliteDataArray = ");
      stringBuilder.append(this.satelliteDataArray);
      stringBuilder.append("}");
      return stringBuilder.toString();
    }
    
    public final void writeEmbeddedToBlob(HwBlob param1HwBlob, long param1Long) {
      this.position.writeEmbeddedToBlob(param1HwBlob, param1Long + 0L);
      this.time.writeEmbeddedToBlob(param1HwBlob, 80L + param1Long);
      int i = this.satelliteDataArray.size();
      param1HwBlob.putInt32(param1Long + 96L + 8L, i);
      param1HwBlob.putBool(param1Long + 96L + 12L, false);
      HwBlob hwBlob = new HwBlob(i * 24);
      for (byte b = 0; b < i; b++)
        ((IGnssDebug.SatelliteData)this.satelliteDataArray.get(b)).writeEmbeddedToBlob(hwBlob, (b * 24)); 
      param1HwBlob.putBlob(96L + param1Long + 0L, hwBlob);
    }
    
    public final void writeToParcel(HwParcel param1HwParcel) {
      HwBlob hwBlob = new HwBlob(112);
      writeEmbeddedToBlob(hwBlob, 0L);
      param1HwParcel.writeBuffer(hwBlob);
    }
  }
  
  public static final class Proxy implements IGnssDebug {
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
    
    public IGnssDebug.DebugData getDebugData() throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.gnss@1.0::IGnssDebug");
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(1, null, hwParcel, 0);
        hwParcel.verifySuccess();
        null.releaseTemporaryStorage();
        IGnssDebug.DebugData debugData = new IGnssDebug.DebugData();
        this();
        debugData.readFromParcel(hwParcel);
        return debugData;
      } finally {
        hwParcel.release();
      } 
    }
    
    public IGnssDebug.DebugData getDebugData_2_0() throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.gnss@2.0::IGnssDebug");
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(2, null, hwParcel, 0);
        hwParcel.verifySuccess();
        null.releaseTemporaryStorage();
        IGnssDebug.DebugData debugData = new IGnssDebug.DebugData();
        this();
        debugData.readFromParcel(hwParcel);
        return debugData;
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
        return "[class or subclass of android.hardware.gnss@2.0::IGnssDebug]@Proxy";
      } 
    }
    
    public boolean unlinkToDeath(IHwBinder.DeathRecipient param1DeathRecipient) throws RemoteException {
      return this.mRemote.unlinkToDeath(param1DeathRecipient);
    }
  }
  
  public static final class SatelliteData {
    public byte constellation = (byte)0;
    
    public IGnssDebug.SatelliteData v1_0 = new IGnssDebug.SatelliteData();
    
    public static final ArrayList<SatelliteData> readVectorFromParcel(HwParcel param1HwParcel) {
      ArrayList<SatelliteData> arrayList = new ArrayList();
      HwBlob hwBlob1 = param1HwParcel.readBuffer(16L);
      int i = hwBlob1.getInt32(8L);
      HwBlob hwBlob2 = param1HwParcel.readEmbeddedBuffer((i * 24), hwBlob1.handle(), 0L, true);
      arrayList.clear();
      for (byte b = 0; b < i; b++) {
        SatelliteData satelliteData = new SatelliteData();
        satelliteData.readEmbeddedFromParcel(param1HwParcel, hwBlob2, (b * 24));
        arrayList.add(satelliteData);
      } 
      return arrayList;
    }
    
    public static final void writeVectorToParcel(HwParcel param1HwParcel, ArrayList<SatelliteData> param1ArrayList) {
      HwBlob hwBlob1 = new HwBlob(16);
      int i = param1ArrayList.size();
      hwBlob1.putInt32(8L, i);
      hwBlob1.putBool(12L, false);
      HwBlob hwBlob2 = new HwBlob(i * 24);
      for (byte b = 0; b < i; b++)
        ((SatelliteData)param1ArrayList.get(b)).writeEmbeddedToBlob(hwBlob2, (b * 24)); 
      hwBlob1.putBlob(0L, hwBlob2);
      param1HwParcel.writeBuffer(hwBlob1);
    }
    
    public final boolean equals(Object param1Object) {
      if (this == param1Object)
        return true; 
      if (param1Object == null)
        return false; 
      if (param1Object.getClass() != SatelliteData.class)
        return false; 
      param1Object = param1Object;
      return !HidlSupport.deepEquals(this.v1_0, ((SatelliteData)param1Object).v1_0) ? false : (!(this.constellation != ((SatelliteData)param1Object).constellation));
    }
    
    public final int hashCode() {
      return Objects.hash(new Object[] { Integer.valueOf(HidlSupport.deepHashCode(this.v1_0)), Integer.valueOf(HidlSupport.deepHashCode(Byte.valueOf(this.constellation))) });
    }
    
    public final void readEmbeddedFromParcel(HwParcel param1HwParcel, HwBlob param1HwBlob, long param1Long) {
      this.v1_0.readEmbeddedFromParcel(param1HwParcel, param1HwBlob, 0L + param1Long);
      this.constellation = param1HwBlob.getInt8(20L + param1Long);
    }
    
    public final void readFromParcel(HwParcel param1HwParcel) {
      readEmbeddedFromParcel(param1HwParcel, param1HwParcel.readBuffer(24L), 0L);
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
      param1HwBlob.putInt8(20L + param1Long, this.constellation);
    }
    
    public final void writeToParcel(HwParcel param1HwParcel) {
      HwBlob hwBlob = new HwBlob(24);
      writeEmbeddedToBlob(hwBlob, 0L);
      param1HwParcel.writeBuffer(hwBlob);
    }
  }
  
  public static abstract class Stub extends HwBinder implements IGnssDebug {
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
                -74, 112, -70, -30, -85, -123, 23, 51, 98, -112, 
                83, 46, 54, 69, 2, -76, -37, -111, 32, 52, 
                13, 117, 71, 76, -52, -124, 66, -79, -79, 93, 
                106, -73 }, { 
                69, 66, 18, 43, -106, -5, -14, 113, 1, -53, 
                -126, 34, -70, -5, 118, -25, -56, -48, 50, -39, 
                119, -35, 16, 88, -19, -40, -27, -120, 28, -91, 
                117, 47 }, { 
                -20, Byte.MAX_VALUE, -41, -98, -48, 45, -6, -123, -68, 73, 
                -108, 38, -83, -82, 62, -66, 35, -17, 5, 36, 
                -13, -51, 105, 87, 19, -109, 36, -72, 59, 24, 
                -54, 76 } }));
    }
    
    public final ArrayList<String> interfaceChain() {
      return new ArrayList<>(Arrays.asList(new String[] { "android.hardware.gnss@2.0::IGnssDebug", "android.hardware.gnss@1.0::IGnssDebug", "android.hidl.base@1.0::IBase" }));
    }
    
    public final String interfaceDescriptor() {
      return "android.hardware.gnss@2.0::IGnssDebug";
    }
    
    public final boolean linkToDeath(IHwBinder.DeathRecipient param1DeathRecipient, long param1Long) {
      return true;
    }
    
    public final void notifySyspropsChanged() {
      HwBinder.enableInstrumentation();
    }
    
    public void onTransact(int param1Int1, HwParcel param1HwParcel1, HwParcel param1HwParcel2, int param1Int2) throws RemoteException {
      IGnssDebug.DebugData debugData1;
      if (param1Int1 != 1) {
        ArrayList<String> arrayList;
        if (param1Int1 != 2) {
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
              break;
          } 
          str.enforceInterface("android.hidl.base@1.0::IBase");
          arrayList = interfaceChain();
          param1HwParcel2.writeStatus(0);
          param1HwParcel2.writeStringVector(arrayList);
          param1HwParcel2.send();
        } 
        arrayList.enforceInterface("android.hardware.gnss@2.0::IGnssDebug");
        debugData1 = getDebugData_2_0();
        param1HwParcel2.writeStatus(0);
        debugData1.writeToParcel(param1HwParcel2);
        param1HwParcel2.send();
      } 
      debugData1.enforceInterface("android.hardware.gnss@1.0::IGnssDebug");
      IGnssDebug.DebugData debugData = getDebugData();
      param1HwParcel2.writeStatus(0);
      debugData.writeToParcel(param1HwParcel2);
      param1HwParcel2.send();
    }
    
    public final void ping() {}
    
    public IHwInterface queryLocalInterface(String param1String) {
      return (IHwInterface)("android.hardware.gnss@2.0::IGnssDebug".equals(param1String) ? this : null);
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


/* Location:              /home/chun/Desktop/temp/!/android/hardware/gnss/V2_0/IGnssDebug.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */