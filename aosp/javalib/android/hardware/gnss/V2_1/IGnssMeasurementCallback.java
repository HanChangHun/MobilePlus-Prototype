package android.hardware.gnss.V2_1;

import android.hardware.gnss.V2_0.ElapsedRealtime;
import android.hardware.gnss.V2_0.IGnssMeasurementCallback;
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

public interface IGnssMeasurementCallback extends IGnssMeasurementCallback {
  public static final String kInterfaceName = "android.hardware.gnss@2.1::IGnssMeasurementCallback";
  
  static IGnssMeasurementCallback asInterface(IHwBinder paramIHwBinder) {
    if (paramIHwBinder == null)
      return null; 
    IHwInterface iHwInterface = paramIHwBinder.queryLocalInterface("android.hardware.gnss@2.1::IGnssMeasurementCallback");
    if (iHwInterface != null && iHwInterface instanceof IGnssMeasurementCallback)
      return (IGnssMeasurementCallback)iHwInterface; 
    Proxy proxy = new Proxy(paramIHwBinder);
    try {
      Iterator<String> iterator = proxy.interfaceChain().iterator();
      while (iterator.hasNext()) {
        boolean bool = ((String)iterator.next()).equals("android.hardware.gnss@2.1::IGnssMeasurementCallback");
        if (bool)
          return proxy; 
      } 
    } catch (RemoteException remoteException) {}
    return null;
  }
  
  static IGnssMeasurementCallback castFrom(IHwInterface paramIHwInterface) {
    IGnssMeasurementCallback iGnssMeasurementCallback;
    if (paramIHwInterface == null) {
      paramIHwInterface = null;
    } else {
      iGnssMeasurementCallback = asInterface(paramIHwInterface.asBinder());
    } 
    return iGnssMeasurementCallback;
  }
  
  static IGnssMeasurementCallback getService() throws RemoteException {
    return getService("default");
  }
  
  static IGnssMeasurementCallback getService(String paramString) throws RemoteException {
    return asInterface(HwBinder.getService("android.hardware.gnss@2.1::IGnssMeasurementCallback", paramString));
  }
  
  static IGnssMeasurementCallback getService(String paramString, boolean paramBoolean) throws RemoteException {
    return asInterface(HwBinder.getService("android.hardware.gnss@2.1::IGnssMeasurementCallback", paramString, paramBoolean));
  }
  
  static IGnssMeasurementCallback getService(boolean paramBoolean) throws RemoteException {
    return getService("default", paramBoolean);
  }
  
  IHwBinder asBinder();
  
  void debug(NativeHandle paramNativeHandle, ArrayList<String> paramArrayList) throws RemoteException;
  
  DebugInfo getDebugInfo() throws RemoteException;
  
  ArrayList<byte[]> getHashChain() throws RemoteException;
  
  void gnssMeasurementCb_2_1(GnssData paramGnssData) throws RemoteException;
  
  ArrayList<String> interfaceChain() throws RemoteException;
  
  String interfaceDescriptor() throws RemoteException;
  
  boolean linkToDeath(IHwBinder.DeathRecipient paramDeathRecipient, long paramLong) throws RemoteException;
  
  void notifySyspropsChanged() throws RemoteException;
  
  void ping() throws RemoteException;
  
  void setHALInstrumentation() throws RemoteException;
  
  boolean unlinkToDeath(IHwBinder.DeathRecipient paramDeathRecipient) throws RemoteException;
  
  public static final class GnssClock {
    public GnssSignalType referenceSignalTypeForIsb = new GnssSignalType();
    
    public android.hardware.gnss.V1_0.IGnssMeasurementCallback.GnssClock v1_0 = new android.hardware.gnss.V1_0.IGnssMeasurementCallback.GnssClock();
    
    public static final ArrayList<GnssClock> readVectorFromParcel(HwParcel param1HwParcel) {
      ArrayList<GnssClock> arrayList = new ArrayList();
      HwBlob hwBlob = param1HwParcel.readBuffer(16L);
      int i = hwBlob.getInt32(8L);
      hwBlob = param1HwParcel.readEmbeddedBuffer((i * 104), hwBlob.handle(), 0L, true);
      arrayList.clear();
      for (byte b = 0; b < i; b++) {
        GnssClock gnssClock = new GnssClock();
        gnssClock.readEmbeddedFromParcel(param1HwParcel, hwBlob, (b * 104));
        arrayList.add(gnssClock);
      } 
      return arrayList;
    }
    
    public static final void writeVectorToParcel(HwParcel param1HwParcel, ArrayList<GnssClock> param1ArrayList) {
      HwBlob hwBlob1 = new HwBlob(16);
      int i = param1ArrayList.size();
      hwBlob1.putInt32(8L, i);
      hwBlob1.putBool(12L, false);
      HwBlob hwBlob2 = new HwBlob(i * 104);
      for (byte b = 0; b < i; b++)
        ((GnssClock)param1ArrayList.get(b)).writeEmbeddedToBlob(hwBlob2, (b * 104)); 
      hwBlob1.putBlob(0L, hwBlob2);
      param1HwParcel.writeBuffer(hwBlob1);
    }
    
    public final boolean equals(Object param1Object) {
      if (this == param1Object)
        return true; 
      if (param1Object == null)
        return false; 
      if (param1Object.getClass() != GnssClock.class)
        return false; 
      param1Object = param1Object;
      return !HidlSupport.deepEquals(this.v1_0, ((GnssClock)param1Object).v1_0) ? false : (!!HidlSupport.deepEquals(this.referenceSignalTypeForIsb, ((GnssClock)param1Object).referenceSignalTypeForIsb));
    }
    
    public final int hashCode() {
      return Objects.hash(new Object[] { Integer.valueOf(HidlSupport.deepHashCode(this.v1_0)), Integer.valueOf(HidlSupport.deepHashCode(this.referenceSignalTypeForIsb)) });
    }
    
    public final void readEmbeddedFromParcel(HwParcel param1HwParcel, HwBlob param1HwBlob, long param1Long) {
      this.v1_0.readEmbeddedFromParcel(param1HwParcel, param1HwBlob, 0L + param1Long);
      this.referenceSignalTypeForIsb.readEmbeddedFromParcel(param1HwParcel, param1HwBlob, 72L + param1Long);
    }
    
    public final void readFromParcel(HwParcel param1HwParcel) {
      readEmbeddedFromParcel(param1HwParcel, param1HwParcel.readBuffer(104L), 0L);
    }
    
    public final String toString() {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("{");
      stringBuilder.append(".v1_0 = ");
      stringBuilder.append(this.v1_0);
      stringBuilder.append(", .referenceSignalTypeForIsb = ");
      stringBuilder.append(this.referenceSignalTypeForIsb);
      stringBuilder.append("}");
      return stringBuilder.toString();
    }
    
    public final void writeEmbeddedToBlob(HwBlob param1HwBlob, long param1Long) {
      this.v1_0.writeEmbeddedToBlob(param1HwBlob, 0L + param1Long);
      this.referenceSignalTypeForIsb.writeEmbeddedToBlob(param1HwBlob, 72L + param1Long);
    }
    
    public final void writeToParcel(HwParcel param1HwParcel) {
      HwBlob hwBlob = new HwBlob(104);
      writeEmbeddedToBlob(hwBlob, 0L);
      param1HwParcel.writeBuffer(hwBlob);
    }
  }
  
  public static final class GnssData {
    public IGnssMeasurementCallback.GnssClock clock = new IGnssMeasurementCallback.GnssClock();
    
    public ElapsedRealtime elapsedRealtime = new ElapsedRealtime();
    
    public ArrayList<IGnssMeasurementCallback.GnssMeasurement> measurements = new ArrayList<>();
    
    public static final ArrayList<GnssData> readVectorFromParcel(HwParcel param1HwParcel) {
      ArrayList<GnssData> arrayList = new ArrayList();
      HwBlob hwBlob1 = param1HwParcel.readBuffer(16L);
      int i = hwBlob1.getInt32(8L);
      HwBlob hwBlob2 = param1HwParcel.readEmbeddedBuffer((i * 144), hwBlob1.handle(), 0L, true);
      arrayList.clear();
      for (byte b = 0; b < i; b++) {
        GnssData gnssData = new GnssData();
        gnssData.readEmbeddedFromParcel(param1HwParcel, hwBlob2, (b * 144));
        arrayList.add(gnssData);
      } 
      return arrayList;
    }
    
    public static final void writeVectorToParcel(HwParcel param1HwParcel, ArrayList<GnssData> param1ArrayList) {
      HwBlob hwBlob1 = new HwBlob(16);
      int i = param1ArrayList.size();
      hwBlob1.putInt32(8L, i);
      hwBlob1.putBool(12L, false);
      HwBlob hwBlob2 = new HwBlob(i * 144);
      for (byte b = 0; b < i; b++)
        ((GnssData)param1ArrayList.get(b)).writeEmbeddedToBlob(hwBlob2, (b * 144)); 
      hwBlob1.putBlob(0L, hwBlob2);
      param1HwParcel.writeBuffer(hwBlob1);
    }
    
    public final boolean equals(Object param1Object) {
      if (this == param1Object)
        return true; 
      if (param1Object == null)
        return false; 
      if (param1Object.getClass() != GnssData.class)
        return false; 
      param1Object = param1Object;
      return !HidlSupport.deepEquals(this.measurements, ((GnssData)param1Object).measurements) ? false : (!HidlSupport.deepEquals(this.clock, ((GnssData)param1Object).clock) ? false : (!!HidlSupport.deepEquals(this.elapsedRealtime, ((GnssData)param1Object).elapsedRealtime)));
    }
    
    public final int hashCode() {
      return Objects.hash(new Object[] { Integer.valueOf(HidlSupport.deepHashCode(this.measurements)), Integer.valueOf(HidlSupport.deepHashCode(this.clock)), Integer.valueOf(HidlSupport.deepHashCode(this.elapsedRealtime)) });
    }
    
    public final void readEmbeddedFromParcel(HwParcel param1HwParcel, HwBlob param1HwBlob, long param1Long) {
      int i = param1HwBlob.getInt32(param1Long + 0L + 8L);
      HwBlob hwBlob = param1HwParcel.readEmbeddedBuffer((i * 224), param1HwBlob.handle(), param1Long + 0L + 0L, true);
      this.measurements.clear();
      for (byte b = 0; b < i; b++) {
        IGnssMeasurementCallback.GnssMeasurement gnssMeasurement = new IGnssMeasurementCallback.GnssMeasurement();
        gnssMeasurement.readEmbeddedFromParcel(param1HwParcel, hwBlob, (b * 224));
        this.measurements.add(gnssMeasurement);
      } 
      this.clock.readEmbeddedFromParcel(param1HwParcel, param1HwBlob, param1Long + 16L);
      this.elapsedRealtime.readEmbeddedFromParcel(param1HwParcel, param1HwBlob, param1Long + 120L);
    }
    
    public final void readFromParcel(HwParcel param1HwParcel) {
      readEmbeddedFromParcel(param1HwParcel, param1HwParcel.readBuffer(144L), 0L);
    }
    
    public final String toString() {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("{");
      stringBuilder.append(".measurements = ");
      stringBuilder.append(this.measurements);
      stringBuilder.append(", .clock = ");
      stringBuilder.append(this.clock);
      stringBuilder.append(", .elapsedRealtime = ");
      stringBuilder.append(this.elapsedRealtime);
      stringBuilder.append("}");
      return stringBuilder.toString();
    }
    
    public final void writeEmbeddedToBlob(HwBlob param1HwBlob, long param1Long) {
      int i = this.measurements.size();
      param1HwBlob.putInt32(param1Long + 0L + 8L, i);
      param1HwBlob.putBool(param1Long + 0L + 12L, false);
      HwBlob hwBlob = new HwBlob(i * 224);
      for (byte b = 0; b < i; b++)
        ((IGnssMeasurementCallback.GnssMeasurement)this.measurements.get(b)).writeEmbeddedToBlob(hwBlob, (b * 224)); 
      param1HwBlob.putBlob(param1Long + 0L + 0L, hwBlob);
      this.clock.writeEmbeddedToBlob(param1HwBlob, 16L + param1Long);
      this.elapsedRealtime.writeEmbeddedToBlob(param1HwBlob, 120L + param1Long);
    }
    
    public final void writeToParcel(HwParcel param1HwParcel) {
      HwBlob hwBlob = new HwBlob(144);
      writeEmbeddedToBlob(hwBlob, 0L);
      param1HwParcel.writeBuffer(hwBlob);
    }
  }
  
  public static final class GnssMeasurement {
    public double basebandCN0DbHz = 0.0D;
    
    public int flags;
    
    public double fullInterSignalBiasNs = 0.0D;
    
    public double fullInterSignalBiasUncertaintyNs = 0.0D;
    
    public double satelliteInterSignalBiasNs = 0.0D;
    
    public double satelliteInterSignalBiasUncertaintyNs = 0.0D;
    
    public IGnssMeasurementCallback.GnssMeasurement v2_0 = new IGnssMeasurementCallback.GnssMeasurement();
    
    public static final ArrayList<GnssMeasurement> readVectorFromParcel(HwParcel param1HwParcel) {
      ArrayList<GnssMeasurement> arrayList = new ArrayList();
      HwBlob hwBlob = param1HwParcel.readBuffer(16L);
      int i = hwBlob.getInt32(8L);
      hwBlob = param1HwParcel.readEmbeddedBuffer((i * 224), hwBlob.handle(), 0L, true);
      arrayList.clear();
      for (byte b = 0; b < i; b++) {
        GnssMeasurement gnssMeasurement = new GnssMeasurement();
        gnssMeasurement.readEmbeddedFromParcel(param1HwParcel, hwBlob, (b * 224));
        arrayList.add(gnssMeasurement);
      } 
      return arrayList;
    }
    
    public static final void writeVectorToParcel(HwParcel param1HwParcel, ArrayList<GnssMeasurement> param1ArrayList) {
      HwBlob hwBlob1 = new HwBlob(16);
      int i = param1ArrayList.size();
      hwBlob1.putInt32(8L, i);
      hwBlob1.putBool(12L, false);
      HwBlob hwBlob2 = new HwBlob(i * 224);
      for (byte b = 0; b < i; b++)
        ((GnssMeasurement)param1ArrayList.get(b)).writeEmbeddedToBlob(hwBlob2, (b * 224)); 
      hwBlob1.putBlob(0L, hwBlob2);
      param1HwParcel.writeBuffer(hwBlob1);
    }
    
    public final boolean equals(Object param1Object) {
      if (this == param1Object)
        return true; 
      if (param1Object == null)
        return false; 
      if (param1Object.getClass() != GnssMeasurement.class)
        return false; 
      param1Object = param1Object;
      return !HidlSupport.deepEquals(this.v2_0, ((GnssMeasurement)param1Object).v2_0) ? false : (!HidlSupport.deepEquals(Integer.valueOf(this.flags), Integer.valueOf(((GnssMeasurement)param1Object).flags)) ? false : ((this.fullInterSignalBiasNs != ((GnssMeasurement)param1Object).fullInterSignalBiasNs) ? false : ((this.fullInterSignalBiasUncertaintyNs != ((GnssMeasurement)param1Object).fullInterSignalBiasUncertaintyNs) ? false : ((this.satelliteInterSignalBiasNs != ((GnssMeasurement)param1Object).satelliteInterSignalBiasNs) ? false : ((this.satelliteInterSignalBiasUncertaintyNs != ((GnssMeasurement)param1Object).satelliteInterSignalBiasUncertaintyNs) ? false : (!(this.basebandCN0DbHz != ((GnssMeasurement)param1Object).basebandCN0DbHz)))))));
    }
    
    public final int hashCode() {
      return Objects.hash(new Object[] { Integer.valueOf(HidlSupport.deepHashCode(this.v2_0)), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.flags))), Integer.valueOf(HidlSupport.deepHashCode(Double.valueOf(this.fullInterSignalBiasNs))), Integer.valueOf(HidlSupport.deepHashCode(Double.valueOf(this.fullInterSignalBiasUncertaintyNs))), Integer.valueOf(HidlSupport.deepHashCode(Double.valueOf(this.satelliteInterSignalBiasNs))), Integer.valueOf(HidlSupport.deepHashCode(Double.valueOf(this.satelliteInterSignalBiasUncertaintyNs))), Integer.valueOf(HidlSupport.deepHashCode(Double.valueOf(this.basebandCN0DbHz))) });
    }
    
    public final void readEmbeddedFromParcel(HwParcel param1HwParcel, HwBlob param1HwBlob, long param1Long) {
      this.v2_0.readEmbeddedFromParcel(param1HwParcel, param1HwBlob, 0L + param1Long);
      this.flags = param1HwBlob.getInt32(176L + param1Long);
      this.fullInterSignalBiasNs = param1HwBlob.getDouble(184L + param1Long);
      this.fullInterSignalBiasUncertaintyNs = param1HwBlob.getDouble(192L + param1Long);
      this.satelliteInterSignalBiasNs = param1HwBlob.getDouble(200L + param1Long);
      this.satelliteInterSignalBiasUncertaintyNs = param1HwBlob.getDouble(208L + param1Long);
      this.basebandCN0DbHz = param1HwBlob.getDouble(216L + param1Long);
    }
    
    public final void readFromParcel(HwParcel param1HwParcel) {
      readEmbeddedFromParcel(param1HwParcel, param1HwParcel.readBuffer(224L), 0L);
    }
    
    public final String toString() {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("{");
      stringBuilder.append(".v2_0 = ");
      stringBuilder.append(this.v2_0);
      stringBuilder.append(", .flags = ");
      stringBuilder.append(IGnssMeasurementCallback.GnssMeasurementFlags.dumpBitfield(this.flags));
      stringBuilder.append(", .fullInterSignalBiasNs = ");
      stringBuilder.append(this.fullInterSignalBiasNs);
      stringBuilder.append(", .fullInterSignalBiasUncertaintyNs = ");
      stringBuilder.append(this.fullInterSignalBiasUncertaintyNs);
      stringBuilder.append(", .satelliteInterSignalBiasNs = ");
      stringBuilder.append(this.satelliteInterSignalBiasNs);
      stringBuilder.append(", .satelliteInterSignalBiasUncertaintyNs = ");
      stringBuilder.append(this.satelliteInterSignalBiasUncertaintyNs);
      stringBuilder.append(", .basebandCN0DbHz = ");
      stringBuilder.append(this.basebandCN0DbHz);
      stringBuilder.append("}");
      return stringBuilder.toString();
    }
    
    public final void writeEmbeddedToBlob(HwBlob param1HwBlob, long param1Long) {
      this.v2_0.writeEmbeddedToBlob(param1HwBlob, 0L + param1Long);
      param1HwBlob.putInt32(176L + param1Long, this.flags);
      param1HwBlob.putDouble(184L + param1Long, this.fullInterSignalBiasNs);
      param1HwBlob.putDouble(192L + param1Long, this.fullInterSignalBiasUncertaintyNs);
      param1HwBlob.putDouble(200L + param1Long, this.satelliteInterSignalBiasNs);
      param1HwBlob.putDouble(208L + param1Long, this.satelliteInterSignalBiasUncertaintyNs);
      param1HwBlob.putDouble(216L + param1Long, this.basebandCN0DbHz);
    }
    
    public final void writeToParcel(HwParcel param1HwParcel) {
      HwBlob hwBlob = new HwBlob(224);
      writeEmbeddedToBlob(hwBlob, 0L);
      param1HwParcel.writeBuffer(hwBlob);
    }
  }
  
  public static final class GnssMeasurementFlags {
    public static final int HAS_AUTOMATIC_GAIN_CONTROL = 8192;
    
    public static final int HAS_CARRIER_CYCLES = 1024;
    
    public static final int HAS_CARRIER_FREQUENCY = 512;
    
    public static final int HAS_CARRIER_PHASE = 2048;
    
    public static final int HAS_CARRIER_PHASE_UNCERTAINTY = 4096;
    
    public static final int HAS_FULL_ISB = 65536;
    
    public static final int HAS_FULL_ISB_UNCERTAINTY = 131072;
    
    public static final int HAS_SATELLITE_ISB = 262144;
    
    public static final int HAS_SATELLITE_ISB_UNCERTAINTY = 524288;
    
    public static final int HAS_SNR = 1;
    
    public static final String dumpBitfield(int param1Int) {
      ArrayList<String> arrayList = new ArrayList();
      int i = 0;
      if ((param1Int & 0x1) == 1) {
        arrayList.add("HAS_SNR");
        i = false | true;
      } 
      int j = i;
      if ((param1Int & 0x200) == 512) {
        arrayList.add("HAS_CARRIER_FREQUENCY");
        j = i | 0x200;
      } 
      i = j;
      if ((param1Int & 0x400) == 1024) {
        arrayList.add("HAS_CARRIER_CYCLES");
        i = j | 0x400;
      } 
      j = i;
      if ((param1Int & 0x800) == 2048) {
        arrayList.add("HAS_CARRIER_PHASE");
        j = i | 0x800;
      } 
      i = j;
      if ((param1Int & 0x1000) == 4096) {
        arrayList.add("HAS_CARRIER_PHASE_UNCERTAINTY");
        i = j | 0x1000;
      } 
      j = i;
      if ((param1Int & 0x2000) == 8192) {
        arrayList.add("HAS_AUTOMATIC_GAIN_CONTROL");
        j = i | 0x2000;
      } 
      i = j;
      if ((param1Int & 0x10000) == 65536) {
        arrayList.add("HAS_FULL_ISB");
        i = j | 0x10000;
      } 
      int k = i;
      if ((param1Int & 0x20000) == 131072) {
        arrayList.add("HAS_FULL_ISB_UNCERTAINTY");
        k = i | 0x20000;
      } 
      j = k;
      if ((param1Int & 0x40000) == 262144) {
        arrayList.add("HAS_SATELLITE_ISB");
        j = k | 0x40000;
      } 
      i = j;
      if ((param1Int & 0x80000) == 524288) {
        arrayList.add("HAS_SATELLITE_ISB_UNCERTAINTY");
        i = j | 0x80000;
      } 
      if (param1Int != i) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("0x");
        stringBuilder.append(Integer.toHexString(i & param1Int));
        arrayList.add(stringBuilder.toString());
      } 
      return String.join(" | ", (Iterable)arrayList);
    }
    
    public static final String toString(int param1Int) {
      if (param1Int == 1)
        return "HAS_SNR"; 
      if (param1Int == 512)
        return "HAS_CARRIER_FREQUENCY"; 
      if (param1Int == 1024)
        return "HAS_CARRIER_CYCLES"; 
      if (param1Int == 2048)
        return "HAS_CARRIER_PHASE"; 
      if (param1Int == 4096)
        return "HAS_CARRIER_PHASE_UNCERTAINTY"; 
      if (param1Int == 8192)
        return "HAS_AUTOMATIC_GAIN_CONTROL"; 
      if (param1Int == 65536)
        return "HAS_FULL_ISB"; 
      if (param1Int == 131072)
        return "HAS_FULL_ISB_UNCERTAINTY"; 
      if (param1Int == 262144)
        return "HAS_SATELLITE_ISB"; 
      if (param1Int == 524288)
        return "HAS_SATELLITE_ISB_UNCERTAINTY"; 
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("0x");
      stringBuilder.append(Integer.toHexString(param1Int));
      return stringBuilder.toString();
    }
  }
  
  public static final class Proxy implements IGnssMeasurementCallback {
    private IHwBinder mRemote;
    
    public Proxy(IHwBinder param1IHwBinder) {
      Objects.requireNonNull(param1IHwBinder);
      this.mRemote = param1IHwBinder;
    }
    
    public void GnssMeasurementCb(android.hardware.gnss.V1_0.IGnssMeasurementCallback.GnssData param1GnssData) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.gnss@1.0::IGnssMeasurementCallback");
      param1GnssData.writeToParcel(null);
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
    
    public void gnssMeasurementCb(android.hardware.gnss.V1_1.IGnssMeasurementCallback.GnssData param1GnssData) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.gnss@1.1::IGnssMeasurementCallback");
      param1GnssData.writeToParcel(null);
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
    
    public void gnssMeasurementCb_2_0(IGnssMeasurementCallback.GnssData param1GnssData) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.gnss@2.0::IGnssMeasurementCallback");
      param1GnssData.writeToParcel(null);
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
    
    public void gnssMeasurementCb_2_1(IGnssMeasurementCallback.GnssData param1GnssData) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.gnss@2.1::IGnssMeasurementCallback");
      param1GnssData.writeToParcel(null);
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
        return "[class or subclass of android.hardware.gnss@2.1::IGnssMeasurementCallback]@Proxy";
      } 
    }
    
    public boolean unlinkToDeath(IHwBinder.DeathRecipient param1DeathRecipient) throws RemoteException {
      return this.mRemote.unlinkToDeath(param1DeathRecipient);
    }
  }
  
  public static abstract class Stub extends HwBinder implements IGnssMeasurementCallback {
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
                -33, 82, -30, -61, -98, -41, 1, -93, 85, -75, 
                -32, -3, -65, -125, -2, 95, -89, -48, 75, -2, 
                -51, 113, 81, 22, -77, -109, 115, -44, 109, -61, 
                -58, -126 }, { 
                -35, 108, -39, -37, -92, -3, -23, -102, 27, -61, 
                -53, 23, 40, -40, 35, 9, -11, 9, -90, -26, 
                -31, -103, 62, 80, 66, -33, -91, -1, -28, -81, 
                84, 66 }, { 
                -125, -25, -95, 15, -13, 112, 33, 71, -67, Byte.MAX_VALUE, 
                -6, 4, 86, 123, 32, -44, 7, -93, -79, 107, 
                -69, 119, 5, 100, 74, -12, 77, -111, -102, -2, 
                -111, 3 }, { 
                -41, 2, -5, 1, -36, 42, 7, 51, -86, -126, 
                11, 126, -74, 84, 53, -18, 51, 52, -9, 86, 
                50, -17, -120, 11, -81, -46, -5, -120, 3, -94, 
                10, 88 }, arrayOfByte }));
    }
    
    public final ArrayList<String> interfaceChain() {
      return new ArrayList<>(Arrays.asList(new String[] { "android.hardware.gnss@2.1::IGnssMeasurementCallback", "android.hardware.gnss@2.0::IGnssMeasurementCallback", "android.hardware.gnss@1.1::IGnssMeasurementCallback", "android.hardware.gnss@1.0::IGnssMeasurementCallback", "android.hidl.base@1.0::IBase" }));
    }
    
    public final String interfaceDescriptor() {
      return "android.hardware.gnss@2.1::IGnssMeasurementCallback";
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
            if (param1Int1 != 4) {
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
            arrayList.enforceInterface("android.hardware.gnss@2.1::IGnssMeasurementCallback");
            IGnssMeasurementCallback.GnssData gnssData3 = new IGnssMeasurementCallback.GnssData();
            gnssData3.readFromParcel((HwParcel)arrayList);
            gnssMeasurementCb_2_1(gnssData3);
            param1HwParcel2.writeStatus(0);
            param1HwParcel2.send();
          } 
          arrayList.enforceInterface("android.hardware.gnss@2.0::IGnssMeasurementCallback");
          IGnssMeasurementCallback.GnssData gnssData2 = new IGnssMeasurementCallback.GnssData();
          gnssData2.readFromParcel((HwParcel)arrayList);
          gnssMeasurementCb_2_0(gnssData2);
          param1HwParcel2.writeStatus(0);
          param1HwParcel2.send();
        } 
        arrayList.enforceInterface("android.hardware.gnss@1.1::IGnssMeasurementCallback");
        android.hardware.gnss.V1_1.IGnssMeasurementCallback.GnssData gnssData1 = new android.hardware.gnss.V1_1.IGnssMeasurementCallback.GnssData();
        gnssData1.readFromParcel((HwParcel)arrayList);
        gnssMeasurementCb(gnssData1);
        param1HwParcel2.writeStatus(0);
        param1HwParcel2.send();
      } 
      arrayList.enforceInterface("android.hardware.gnss@1.0::IGnssMeasurementCallback");
      android.hardware.gnss.V1_0.IGnssMeasurementCallback.GnssData gnssData = new android.hardware.gnss.V1_0.IGnssMeasurementCallback.GnssData();
      gnssData.readFromParcel((HwParcel)arrayList);
      GnssMeasurementCb(gnssData);
      param1HwParcel2.writeStatus(0);
      param1HwParcel2.send();
    }
    
    public final void ping() {}
    
    public IHwInterface queryLocalInterface(String param1String) {
      return (IHwInterface)("android.hardware.gnss@2.1::IGnssMeasurementCallback".equals(param1String) ? this : null);
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


/* Location:              /home/chun/Desktop/temp/!/android/hardware/gnss/V2_1/IGnssMeasurementCallback.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */