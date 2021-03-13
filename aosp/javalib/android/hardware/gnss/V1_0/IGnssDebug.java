package android.hardware.gnss.V1_0;

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

public interface IGnssDebug extends IBase {
  public static final String kInterfaceName = "android.hardware.gnss@1.0::IGnssDebug";
  
  static IGnssDebug asInterface(IHwBinder paramIHwBinder) {
    if (paramIHwBinder == null)
      return null; 
    IHwInterface iHwInterface = paramIHwBinder.queryLocalInterface("android.hardware.gnss@1.0::IGnssDebug");
    if (iHwInterface != null && iHwInterface instanceof IGnssDebug)
      return (IGnssDebug)iHwInterface; 
    Proxy proxy = new Proxy(paramIHwBinder);
    try {
      Iterator<String> iterator = proxy.interfaceChain().iterator();
      while (iterator.hasNext()) {
        boolean bool = ((String)iterator.next()).equals("android.hardware.gnss@1.0::IGnssDebug");
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
    return asInterface(HwBinder.getService("android.hardware.gnss@1.0::IGnssDebug", paramString));
  }
  
  static IGnssDebug getService(String paramString, boolean paramBoolean) throws RemoteException {
    return asInterface(HwBinder.getService("android.hardware.gnss@1.0::IGnssDebug", paramString, paramBoolean));
  }
  
  static IGnssDebug getService(boolean paramBoolean) throws RemoteException {
    return getService("default", paramBoolean);
  }
  
  IHwBinder asBinder();
  
  void debug(NativeHandle paramNativeHandle, ArrayList<String> paramArrayList) throws RemoteException;
  
  DebugData getDebugData() throws RemoteException;
  
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
      HwBlob hwBlob1 = param1HwParcel.readBuffer(16L);
      int i = hwBlob1.getInt32(8L);
      HwBlob hwBlob2 = param1HwParcel.readEmbeddedBuffer((i * 112), hwBlob1.handle(), 0L, true);
      arrayList.clear();
      for (byte b = 0; b < i; b++) {
        DebugData debugData = new DebugData();
        debugData.readEmbeddedFromParcel(param1HwParcel, hwBlob2, (b * 112));
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
      HwBlob hwBlob = param1HwParcel.readEmbeddedBuffer((i * 20), param1HwBlob.handle(), param1Long + 96L + 0L, true);
      this.satelliteDataArray.clear();
      for (byte b = 0; b < i; b++) {
        IGnssDebug.SatelliteData satelliteData = new IGnssDebug.SatelliteData();
        satelliteData.readEmbeddedFromParcel(param1HwParcel, hwBlob, (b * 20));
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
      HwBlob hwBlob = new HwBlob(i * 20);
      for (byte b = 0; b < i; b++)
        ((IGnssDebug.SatelliteData)this.satelliteDataArray.get(b)).writeEmbeddedToBlob(hwBlob, (b * 20)); 
      param1HwBlob.putBlob(96L + param1Long + 0L, hwBlob);
    }
    
    public final void writeToParcel(HwParcel param1HwParcel) {
      HwBlob hwBlob = new HwBlob(112);
      writeEmbeddedToBlob(hwBlob, 0L);
      param1HwParcel.writeBuffer(hwBlob);
    }
  }
  
  public static final class PositionDebug {
    public float ageSeconds = 0.0F;
    
    public float altitudeMeters = 0.0F;
    
    public double bearingAccuracyDegrees = 0.0D;
    
    public float bearingDegrees = 0.0F;
    
    public double horizontalAccuracyMeters = 0.0D;
    
    public double latitudeDegrees = 0.0D;
    
    public double longitudeDegrees = 0.0D;
    
    public double speedAccuracyMetersPerSecond = 0.0D;
    
    public float speedMetersPerSec = 0.0F;
    
    public boolean valid = false;
    
    public double verticalAccuracyMeters = 0.0D;
    
    public static final ArrayList<PositionDebug> readVectorFromParcel(HwParcel param1HwParcel) {
      ArrayList<PositionDebug> arrayList = new ArrayList();
      HwBlob hwBlob1 = param1HwParcel.readBuffer(16L);
      int i = hwBlob1.getInt32(8L);
      HwBlob hwBlob2 = param1HwParcel.readEmbeddedBuffer((i * 80), hwBlob1.handle(), 0L, true);
      arrayList.clear();
      for (byte b = 0; b < i; b++) {
        PositionDebug positionDebug = new PositionDebug();
        positionDebug.readEmbeddedFromParcel(param1HwParcel, hwBlob2, (b * 80));
        arrayList.add(positionDebug);
      } 
      return arrayList;
    }
    
    public static final void writeVectorToParcel(HwParcel param1HwParcel, ArrayList<PositionDebug> param1ArrayList) {
      HwBlob hwBlob1 = new HwBlob(16);
      int i = param1ArrayList.size();
      hwBlob1.putInt32(8L, i);
      hwBlob1.putBool(12L, false);
      HwBlob hwBlob2 = new HwBlob(i * 80);
      for (byte b = 0; b < i; b++)
        ((PositionDebug)param1ArrayList.get(b)).writeEmbeddedToBlob(hwBlob2, (b * 80)); 
      hwBlob1.putBlob(0L, hwBlob2);
      param1HwParcel.writeBuffer(hwBlob1);
    }
    
    public final boolean equals(Object param1Object) {
      if (this == param1Object)
        return true; 
      if (param1Object == null)
        return false; 
      if (param1Object.getClass() != PositionDebug.class)
        return false; 
      param1Object = param1Object;
      return (this.valid != ((PositionDebug)param1Object).valid) ? false : ((this.latitudeDegrees != ((PositionDebug)param1Object).latitudeDegrees) ? false : ((this.longitudeDegrees != ((PositionDebug)param1Object).longitudeDegrees) ? false : ((this.altitudeMeters != ((PositionDebug)param1Object).altitudeMeters) ? false : ((this.speedMetersPerSec != ((PositionDebug)param1Object).speedMetersPerSec) ? false : ((this.bearingDegrees != ((PositionDebug)param1Object).bearingDegrees) ? false : ((this.horizontalAccuracyMeters != ((PositionDebug)param1Object).horizontalAccuracyMeters) ? false : ((this.verticalAccuracyMeters != ((PositionDebug)param1Object).verticalAccuracyMeters) ? false : ((this.speedAccuracyMetersPerSecond != ((PositionDebug)param1Object).speedAccuracyMetersPerSecond) ? false : ((this.bearingAccuracyDegrees != ((PositionDebug)param1Object).bearingAccuracyDegrees) ? false : (!(this.ageSeconds != ((PositionDebug)param1Object).ageSeconds)))))))))));
    }
    
    public final int hashCode() {
      return Objects.hash(new Object[] { 
            Integer.valueOf(HidlSupport.deepHashCode(Boolean.valueOf(this.valid))), Integer.valueOf(HidlSupport.deepHashCode(Double.valueOf(this.latitudeDegrees))), Integer.valueOf(HidlSupport.deepHashCode(Double.valueOf(this.longitudeDegrees))), Integer.valueOf(HidlSupport.deepHashCode(Float.valueOf(this.altitudeMeters))), Integer.valueOf(HidlSupport.deepHashCode(Float.valueOf(this.speedMetersPerSec))), Integer.valueOf(HidlSupport.deepHashCode(Float.valueOf(this.bearingDegrees))), Integer.valueOf(HidlSupport.deepHashCode(Double.valueOf(this.horizontalAccuracyMeters))), Integer.valueOf(HidlSupport.deepHashCode(Double.valueOf(this.verticalAccuracyMeters))), Integer.valueOf(HidlSupport.deepHashCode(Double.valueOf(this.speedAccuracyMetersPerSecond))), Integer.valueOf(HidlSupport.deepHashCode(Double.valueOf(this.bearingAccuracyDegrees))), 
            Integer.valueOf(HidlSupport.deepHashCode(Float.valueOf(this.ageSeconds))) });
    }
    
    public final void readEmbeddedFromParcel(HwParcel param1HwParcel, HwBlob param1HwBlob, long param1Long) {
      this.valid = param1HwBlob.getBool(0L + param1Long);
      this.latitudeDegrees = param1HwBlob.getDouble(8L + param1Long);
      this.longitudeDegrees = param1HwBlob.getDouble(16L + param1Long);
      this.altitudeMeters = param1HwBlob.getFloat(24L + param1Long);
      this.speedMetersPerSec = param1HwBlob.getFloat(28L + param1Long);
      this.bearingDegrees = param1HwBlob.getFloat(32L + param1Long);
      this.horizontalAccuracyMeters = param1HwBlob.getDouble(40L + param1Long);
      this.verticalAccuracyMeters = param1HwBlob.getDouble(48L + param1Long);
      this.speedAccuracyMetersPerSecond = param1HwBlob.getDouble(56L + param1Long);
      this.bearingAccuracyDegrees = param1HwBlob.getDouble(64L + param1Long);
      this.ageSeconds = param1HwBlob.getFloat(72L + param1Long);
    }
    
    public final void readFromParcel(HwParcel param1HwParcel) {
      readEmbeddedFromParcel(param1HwParcel, param1HwParcel.readBuffer(80L), 0L);
    }
    
    public final String toString() {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("{");
      stringBuilder.append(".valid = ");
      stringBuilder.append(this.valid);
      stringBuilder.append(", .latitudeDegrees = ");
      stringBuilder.append(this.latitudeDegrees);
      stringBuilder.append(", .longitudeDegrees = ");
      stringBuilder.append(this.longitudeDegrees);
      stringBuilder.append(", .altitudeMeters = ");
      stringBuilder.append(this.altitudeMeters);
      stringBuilder.append(", .speedMetersPerSec = ");
      stringBuilder.append(this.speedMetersPerSec);
      stringBuilder.append(", .bearingDegrees = ");
      stringBuilder.append(this.bearingDegrees);
      stringBuilder.append(", .horizontalAccuracyMeters = ");
      stringBuilder.append(this.horizontalAccuracyMeters);
      stringBuilder.append(", .verticalAccuracyMeters = ");
      stringBuilder.append(this.verticalAccuracyMeters);
      stringBuilder.append(", .speedAccuracyMetersPerSecond = ");
      stringBuilder.append(this.speedAccuracyMetersPerSecond);
      stringBuilder.append(", .bearingAccuracyDegrees = ");
      stringBuilder.append(this.bearingAccuracyDegrees);
      stringBuilder.append(", .ageSeconds = ");
      stringBuilder.append(this.ageSeconds);
      stringBuilder.append("}");
      return stringBuilder.toString();
    }
    
    public final void writeEmbeddedToBlob(HwBlob param1HwBlob, long param1Long) {
      param1HwBlob.putBool(0L + param1Long, this.valid);
      param1HwBlob.putDouble(8L + param1Long, this.latitudeDegrees);
      param1HwBlob.putDouble(16L + param1Long, this.longitudeDegrees);
      param1HwBlob.putFloat(24L + param1Long, this.altitudeMeters);
      param1HwBlob.putFloat(28L + param1Long, this.speedMetersPerSec);
      param1HwBlob.putFloat(32L + param1Long, this.bearingDegrees);
      param1HwBlob.putDouble(40L + param1Long, this.horizontalAccuracyMeters);
      param1HwBlob.putDouble(48L + param1Long, this.verticalAccuracyMeters);
      param1HwBlob.putDouble(56L + param1Long, this.speedAccuracyMetersPerSecond);
      param1HwBlob.putDouble(64L + param1Long, this.bearingAccuracyDegrees);
      param1HwBlob.putFloat(72L + param1Long, this.ageSeconds);
    }
    
    public final void writeToParcel(HwParcel param1HwParcel) {
      HwBlob hwBlob = new HwBlob(80);
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
        return "[class or subclass of android.hardware.gnss@1.0::IGnssDebug]@Proxy";
      } 
    }
    
    public boolean unlinkToDeath(IHwBinder.DeathRecipient param1DeathRecipient) throws RemoteException {
      return this.mRemote.unlinkToDeath(param1DeathRecipient);
    }
  }
  
  public static final class SatelliteData {
    public byte constellation = (byte)0;
    
    public float ephemerisAgeSeconds = 0.0F;
    
    public byte ephemerisHealth = (byte)0;
    
    public byte ephemerisSource = (byte)0;
    
    public byte ephemerisType = (byte)0;
    
    public float serverPredictionAgeSeconds = 0.0F;
    
    public boolean serverPredictionIsAvailable = false;
    
    public short svid = (short)0;
    
    public static final ArrayList<SatelliteData> readVectorFromParcel(HwParcel param1HwParcel) {
      ArrayList<SatelliteData> arrayList = new ArrayList();
      HwBlob hwBlob1 = param1HwParcel.readBuffer(16L);
      int i = hwBlob1.getInt32(8L);
      HwBlob hwBlob2 = param1HwParcel.readEmbeddedBuffer((i * 20), hwBlob1.handle(), 0L, true);
      arrayList.clear();
      for (byte b = 0; b < i; b++) {
        SatelliteData satelliteData = new SatelliteData();
        satelliteData.readEmbeddedFromParcel(param1HwParcel, hwBlob2, (b * 20));
        arrayList.add(satelliteData);
      } 
      return arrayList;
    }
    
    public static final void writeVectorToParcel(HwParcel param1HwParcel, ArrayList<SatelliteData> param1ArrayList) {
      HwBlob hwBlob1 = new HwBlob(16);
      int i = param1ArrayList.size();
      hwBlob1.putInt32(8L, i);
      hwBlob1.putBool(12L, false);
      HwBlob hwBlob2 = new HwBlob(i * 20);
      for (byte b = 0; b < i; b++)
        ((SatelliteData)param1ArrayList.get(b)).writeEmbeddedToBlob(hwBlob2, (b * 20)); 
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
      return (this.svid != ((SatelliteData)param1Object).svid) ? false : ((this.constellation != ((SatelliteData)param1Object).constellation) ? false : ((this.ephemerisType != ((SatelliteData)param1Object).ephemerisType) ? false : ((this.ephemerisSource != ((SatelliteData)param1Object).ephemerisSource) ? false : ((this.ephemerisHealth != ((SatelliteData)param1Object).ephemerisHealth) ? false : ((this.ephemerisAgeSeconds != ((SatelliteData)param1Object).ephemerisAgeSeconds) ? false : ((this.serverPredictionIsAvailable != ((SatelliteData)param1Object).serverPredictionIsAvailable) ? false : (!(this.serverPredictionAgeSeconds != ((SatelliteData)param1Object).serverPredictionAgeSeconds))))))));
    }
    
    public final int hashCode() {
      return Objects.hash(new Object[] { Integer.valueOf(HidlSupport.deepHashCode(Short.valueOf(this.svid))), Integer.valueOf(HidlSupport.deepHashCode(Byte.valueOf(this.constellation))), Integer.valueOf(HidlSupport.deepHashCode(Byte.valueOf(this.ephemerisType))), Integer.valueOf(HidlSupport.deepHashCode(Byte.valueOf(this.ephemerisSource))), Integer.valueOf(HidlSupport.deepHashCode(Byte.valueOf(this.ephemerisHealth))), Integer.valueOf(HidlSupport.deepHashCode(Float.valueOf(this.ephemerisAgeSeconds))), Integer.valueOf(HidlSupport.deepHashCode(Boolean.valueOf(this.serverPredictionIsAvailable))), Integer.valueOf(HidlSupport.deepHashCode(Float.valueOf(this.serverPredictionAgeSeconds))) });
    }
    
    public final void readEmbeddedFromParcel(HwParcel param1HwParcel, HwBlob param1HwBlob, long param1Long) {
      this.svid = param1HwBlob.getInt16(0L + param1Long);
      this.constellation = param1HwBlob.getInt8(2L + param1Long);
      this.ephemerisType = param1HwBlob.getInt8(3L + param1Long);
      this.ephemerisSource = param1HwBlob.getInt8(4L + param1Long);
      this.ephemerisHealth = param1HwBlob.getInt8(5L + param1Long);
      this.ephemerisAgeSeconds = param1HwBlob.getFloat(8L + param1Long);
      this.serverPredictionIsAvailable = param1HwBlob.getBool(12L + param1Long);
      this.serverPredictionAgeSeconds = param1HwBlob.getFloat(16L + param1Long);
    }
    
    public final void readFromParcel(HwParcel param1HwParcel) {
      readEmbeddedFromParcel(param1HwParcel, param1HwParcel.readBuffer(20L), 0L);
    }
    
    public final String toString() {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("{");
      stringBuilder.append(".svid = ");
      stringBuilder.append(this.svid);
      stringBuilder.append(", .constellation = ");
      stringBuilder.append(GnssConstellationType.toString(this.constellation));
      stringBuilder.append(", .ephemerisType = ");
      stringBuilder.append(IGnssDebug.SatelliteEphemerisType.toString(this.ephemerisType));
      stringBuilder.append(", .ephemerisSource = ");
      stringBuilder.append(IGnssDebug.SatelliteEphemerisSource.toString(this.ephemerisSource));
      stringBuilder.append(", .ephemerisHealth = ");
      stringBuilder.append(IGnssDebug.SatelliteEphemerisHealth.toString(this.ephemerisHealth));
      stringBuilder.append(", .ephemerisAgeSeconds = ");
      stringBuilder.append(this.ephemerisAgeSeconds);
      stringBuilder.append(", .serverPredictionIsAvailable = ");
      stringBuilder.append(this.serverPredictionIsAvailable);
      stringBuilder.append(", .serverPredictionAgeSeconds = ");
      stringBuilder.append(this.serverPredictionAgeSeconds);
      stringBuilder.append("}");
      return stringBuilder.toString();
    }
    
    public final void writeEmbeddedToBlob(HwBlob param1HwBlob, long param1Long) {
      param1HwBlob.putInt16(0L + param1Long, this.svid);
      param1HwBlob.putInt8(2L + param1Long, this.constellation);
      param1HwBlob.putInt8(3L + param1Long, this.ephemerisType);
      param1HwBlob.putInt8(4L + param1Long, this.ephemerisSource);
      param1HwBlob.putInt8(5L + param1Long, this.ephemerisHealth);
      param1HwBlob.putFloat(8L + param1Long, this.ephemerisAgeSeconds);
      param1HwBlob.putBool(12L + param1Long, this.serverPredictionIsAvailable);
      param1HwBlob.putFloat(16L + param1Long, this.serverPredictionAgeSeconds);
    }
    
    public final void writeToParcel(HwParcel param1HwParcel) {
      HwBlob hwBlob = new HwBlob(20);
      writeEmbeddedToBlob(hwBlob, 0L);
      param1HwParcel.writeBuffer(hwBlob);
    }
  }
  
  public static final class SatelliteEphemerisHealth {
    public static final byte BAD = 1;
    
    public static final byte GOOD = 0;
    
    public static final byte UNKNOWN = 2;
    
    public static final String dumpBitfield(byte param1Byte) {
      ArrayList<String> arrayList = new ArrayList();
      byte b1 = 0;
      arrayList.add("GOOD");
      if ((param1Byte & 0x1) == 1) {
        arrayList.add("BAD");
        b1 = (byte)(false | true);
      } 
      byte b2 = b1;
      if ((param1Byte & 0x2) == 2) {
        arrayList.add("UNKNOWN");
        b2 = (byte)(b1 | 0x2);
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
      if (param1Byte == 0)
        return "GOOD"; 
      if (param1Byte == 1)
        return "BAD"; 
      if (param1Byte == 2)
        return "UNKNOWN"; 
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("0x");
      stringBuilder.append(Integer.toHexString(Byte.toUnsignedInt(param1Byte)));
      return stringBuilder.toString();
    }
  }
  
  public static final class SatelliteEphemerisSource {
    public static final byte DEMODULATED = 0;
    
    public static final byte OTHER = 3;
    
    public static final byte OTHER_SERVER_PROVIDED = 2;
    
    public static final byte SUPL_PROVIDED = 1;
    
    public static final String dumpBitfield(byte param1Byte) {
      ArrayList<String> arrayList = new ArrayList();
      byte b1 = 0;
      arrayList.add("DEMODULATED");
      if ((param1Byte & 0x1) == 1) {
        arrayList.add("SUPL_PROVIDED");
        b1 = (byte)(false | true);
      } 
      byte b2 = b1;
      if ((param1Byte & 0x2) == 2) {
        arrayList.add("OTHER_SERVER_PROVIDED");
        b2 = (byte)(b1 | 0x2);
      } 
      b1 = b2;
      if ((param1Byte & 0x3) == 3) {
        arrayList.add("OTHER");
        b1 = (byte)(b2 | 0x3);
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
      if (param1Byte == 0)
        return "DEMODULATED"; 
      if (param1Byte == 1)
        return "SUPL_PROVIDED"; 
      if (param1Byte == 2)
        return "OTHER_SERVER_PROVIDED"; 
      if (param1Byte == 3)
        return "OTHER"; 
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("0x");
      stringBuilder.append(Integer.toHexString(Byte.toUnsignedInt(param1Byte)));
      return stringBuilder.toString();
    }
  }
  
  public static final class SatelliteEphemerisType {
    public static final byte ALMANAC_ONLY = 1;
    
    public static final byte EPHEMERIS = 0;
    
    public static final byte NOT_AVAILABLE = 2;
    
    public static final String dumpBitfield(byte param1Byte) {
      ArrayList<String> arrayList = new ArrayList();
      byte b1 = 0;
      arrayList.add("EPHEMERIS");
      if ((param1Byte & 0x1) == 1) {
        arrayList.add("ALMANAC_ONLY");
        b1 = (byte)(false | true);
      } 
      byte b2 = b1;
      if ((param1Byte & 0x2) == 2) {
        arrayList.add("NOT_AVAILABLE");
        b2 = (byte)(b1 | 0x2);
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
      if (param1Byte == 0)
        return "EPHEMERIS"; 
      if (param1Byte == 1)
        return "ALMANAC_ONLY"; 
      if (param1Byte == 2)
        return "NOT_AVAILABLE"; 
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("0x");
      stringBuilder.append(Integer.toHexString(Byte.toUnsignedInt(param1Byte)));
      return stringBuilder.toString();
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
      byte[] arrayOfByte = { 
          -20, Byte.MAX_VALUE, -41, -98, -48, 45, -6, -123, -68, 73, 
          -108, 38, -83, -82, 62, -66, 35, -17, 5, 36, 
          -13, -51, 105, 87, 19, -109, 36, -72, 59, 24, 
          -54, 76 };
      return (ArrayList)new ArrayList<>(Arrays.asList((byte[])new byte[][] { { 
                69, 66, 18, 43, -106, -5, -14, 113, 1, -53, 
                -126, 34, -70, -5, 118, -25, -56, -48, 50, -39, 
                119, -35, 16, 88, -19, -40, -27, -120, 28, -91, 
                117, 47 }, arrayOfByte }));
    }
    
    public final ArrayList<String> interfaceChain() {
      return new ArrayList<>(Arrays.asList(new String[] { "android.hardware.gnss@1.0::IGnssDebug", "android.hidl.base@1.0::IBase" }));
    }
    
    public final String interfaceDescriptor() {
      return "android.hardware.gnss@1.0::IGnssDebug";
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
          str.enforceInterface("android.hidl.base@1.0::IBase");
          arrayList = interfaceChain();
          param1HwParcel2.writeStatus(0);
          param1HwParcel2.writeStringVector(arrayList);
          param1HwParcel2.send();
        case 1:
          break;
      } 
      arrayList.enforceInterface("android.hardware.gnss@1.0::IGnssDebug");
      IGnssDebug.DebugData debugData = getDebugData();
      param1HwParcel2.writeStatus(0);
      debugData.writeToParcel(param1HwParcel2);
      param1HwParcel2.send();
    }
    
    public final void ping() {}
    
    public IHwInterface queryLocalInterface(String param1String) {
      return (IHwInterface)("android.hardware.gnss@1.0::IGnssDebug".equals(param1String) ? this : null);
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
  
  public static final class TimeDebug {
    public float frequencyUncertaintyNsPerSec = 0.0F;
    
    public long timeEstimate = 0L;
    
    public float timeUncertaintyNs = 0.0F;
    
    public static final ArrayList<TimeDebug> readVectorFromParcel(HwParcel param1HwParcel) {
      ArrayList<TimeDebug> arrayList = new ArrayList();
      HwBlob hwBlob1 = param1HwParcel.readBuffer(16L);
      int i = hwBlob1.getInt32(8L);
      HwBlob hwBlob2 = param1HwParcel.readEmbeddedBuffer((i * 16), hwBlob1.handle(), 0L, true);
      arrayList.clear();
      for (byte b = 0; b < i; b++) {
        TimeDebug timeDebug = new TimeDebug();
        timeDebug.readEmbeddedFromParcel(param1HwParcel, hwBlob2, (b * 16));
        arrayList.add(timeDebug);
      } 
      return arrayList;
    }
    
    public static final void writeVectorToParcel(HwParcel param1HwParcel, ArrayList<TimeDebug> param1ArrayList) {
      HwBlob hwBlob1 = new HwBlob(16);
      int i = param1ArrayList.size();
      hwBlob1.putInt32(8L, i);
      hwBlob1.putBool(12L, false);
      HwBlob hwBlob2 = new HwBlob(i * 16);
      for (byte b = 0; b < i; b++)
        ((TimeDebug)param1ArrayList.get(b)).writeEmbeddedToBlob(hwBlob2, (b * 16)); 
      hwBlob1.putBlob(0L, hwBlob2);
      param1HwParcel.writeBuffer(hwBlob1);
    }
    
    public final boolean equals(Object param1Object) {
      if (this == param1Object)
        return true; 
      if (param1Object == null)
        return false; 
      if (param1Object.getClass() != TimeDebug.class)
        return false; 
      param1Object = param1Object;
      return (this.timeEstimate != ((TimeDebug)param1Object).timeEstimate) ? false : ((this.timeUncertaintyNs != ((TimeDebug)param1Object).timeUncertaintyNs) ? false : (!(this.frequencyUncertaintyNsPerSec != ((TimeDebug)param1Object).frequencyUncertaintyNsPerSec)));
    }
    
    public final int hashCode() {
      return Objects.hash(new Object[] { Integer.valueOf(HidlSupport.deepHashCode(Long.valueOf(this.timeEstimate))), Integer.valueOf(HidlSupport.deepHashCode(Float.valueOf(this.timeUncertaintyNs))), Integer.valueOf(HidlSupport.deepHashCode(Float.valueOf(this.frequencyUncertaintyNsPerSec))) });
    }
    
    public final void readEmbeddedFromParcel(HwParcel param1HwParcel, HwBlob param1HwBlob, long param1Long) {
      this.timeEstimate = param1HwBlob.getInt64(0L + param1Long);
      this.timeUncertaintyNs = param1HwBlob.getFloat(8L + param1Long);
      this.frequencyUncertaintyNsPerSec = param1HwBlob.getFloat(12L + param1Long);
    }
    
    public final void readFromParcel(HwParcel param1HwParcel) {
      readEmbeddedFromParcel(param1HwParcel, param1HwParcel.readBuffer(16L), 0L);
    }
    
    public final String toString() {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("{");
      stringBuilder.append(".timeEstimate = ");
      stringBuilder.append(this.timeEstimate);
      stringBuilder.append(", .timeUncertaintyNs = ");
      stringBuilder.append(this.timeUncertaintyNs);
      stringBuilder.append(", .frequencyUncertaintyNsPerSec = ");
      stringBuilder.append(this.frequencyUncertaintyNsPerSec);
      stringBuilder.append("}");
      return stringBuilder.toString();
    }
    
    public final void writeEmbeddedToBlob(HwBlob param1HwBlob, long param1Long) {
      param1HwBlob.putInt64(0L + param1Long, this.timeEstimate);
      param1HwBlob.putFloat(8L + param1Long, this.timeUncertaintyNs);
      param1HwBlob.putFloat(12L + param1Long, this.frequencyUncertaintyNsPerSec);
    }
    
    public final void writeToParcel(HwParcel param1HwParcel) {
      HwBlob hwBlob = new HwBlob(16);
      writeEmbeddedToBlob(hwBlob, 0L);
      param1HwParcel.writeBuffer(hwBlob);
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/gnss/V1_0/IGnssDebug.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */