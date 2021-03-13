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

public interface IGnssMeasurementCallback extends IBase {
  public static final String kInterfaceName = "android.hardware.gnss@1.0::IGnssMeasurementCallback";
  
  static IGnssMeasurementCallback asInterface(IHwBinder paramIHwBinder) {
    if (paramIHwBinder == null)
      return null; 
    IHwInterface iHwInterface = paramIHwBinder.queryLocalInterface("android.hardware.gnss@1.0::IGnssMeasurementCallback");
    if (iHwInterface != null && iHwInterface instanceof IGnssMeasurementCallback)
      return (IGnssMeasurementCallback)iHwInterface; 
    Proxy proxy = new Proxy(paramIHwBinder);
    try {
      Iterator<String> iterator = proxy.interfaceChain().iterator();
      while (iterator.hasNext()) {
        boolean bool = ((String)iterator.next()).equals("android.hardware.gnss@1.0::IGnssMeasurementCallback");
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
    return asInterface(HwBinder.getService("android.hardware.gnss@1.0::IGnssMeasurementCallback", paramString));
  }
  
  static IGnssMeasurementCallback getService(String paramString, boolean paramBoolean) throws RemoteException {
    return asInterface(HwBinder.getService("android.hardware.gnss@1.0::IGnssMeasurementCallback", paramString, paramBoolean));
  }
  
  static IGnssMeasurementCallback getService(boolean paramBoolean) throws RemoteException {
    return getService("default", paramBoolean);
  }
  
  void GnssMeasurementCb(GnssData paramGnssData) throws RemoteException;
  
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
  
  public static final class GnssAccumulatedDeltaRangeState {
    public static final short ADR_STATE_CYCLE_SLIP = 4;
    
    public static final short ADR_STATE_RESET = 2;
    
    public static final short ADR_STATE_UNKNOWN = 0;
    
    public static final short ADR_STATE_VALID = 1;
    
    public static final String dumpBitfield(short param1Short) {
      ArrayList<String> arrayList = new ArrayList();
      short s1 = 0;
      arrayList.add("ADR_STATE_UNKNOWN");
      if ((param1Short & 0x1) == 1) {
        arrayList.add("ADR_STATE_VALID");
        s1 = (short)(false | true);
      } 
      short s2 = s1;
      if ((param1Short & 0x2) == 2) {
        arrayList.add("ADR_STATE_RESET");
        s2 = (short)(s1 | 0x2);
      } 
      s1 = s2;
      if ((param1Short & 0x4) == 4) {
        arrayList.add("ADR_STATE_CYCLE_SLIP");
        s1 = (short)(s2 | 0x4);
      } 
      if (param1Short != s1) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("0x");
        stringBuilder.append(Integer.toHexString(Short.toUnsignedInt((short)(s1 & param1Short))));
        arrayList.add(stringBuilder.toString());
      } 
      return String.join(" | ", (Iterable)arrayList);
    }
    
    public static final String toString(short param1Short) {
      if (param1Short == 0)
        return "ADR_STATE_UNKNOWN"; 
      if (param1Short == 1)
        return "ADR_STATE_VALID"; 
      if (param1Short == 2)
        return "ADR_STATE_RESET"; 
      if (param1Short == 4)
        return "ADR_STATE_CYCLE_SLIP"; 
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("0x");
      stringBuilder.append(Integer.toHexString(Short.toUnsignedInt(param1Short)));
      return stringBuilder.toString();
    }
  }
  
  public static final class GnssClock {
    public double biasNs = 0.0D;
    
    public double biasUncertaintyNs = 0.0D;
    
    public double driftNsps = 0.0D;
    
    public double driftUncertaintyNsps = 0.0D;
    
    public long fullBiasNs = 0L;
    
    public short gnssClockFlags;
    
    public int hwClockDiscontinuityCount = 0;
    
    public short leapSecond = (short)0;
    
    public long timeNs = 0L;
    
    public double timeUncertaintyNs = 0.0D;
    
    public static final ArrayList<GnssClock> readVectorFromParcel(HwParcel param1HwParcel) {
      ArrayList<GnssClock> arrayList = new ArrayList();
      HwBlob hwBlob1 = param1HwParcel.readBuffer(16L);
      int i = hwBlob1.getInt32(8L);
      HwBlob hwBlob2 = param1HwParcel.readEmbeddedBuffer((i * 72), hwBlob1.handle(), 0L, true);
      arrayList.clear();
      for (byte b = 0; b < i; b++) {
        GnssClock gnssClock = new GnssClock();
        gnssClock.readEmbeddedFromParcel(param1HwParcel, hwBlob2, (b * 72));
        arrayList.add(gnssClock);
      } 
      return arrayList;
    }
    
    public static final void writeVectorToParcel(HwParcel param1HwParcel, ArrayList<GnssClock> param1ArrayList) {
      HwBlob hwBlob1 = new HwBlob(16);
      int i = param1ArrayList.size();
      hwBlob1.putInt32(8L, i);
      hwBlob1.putBool(12L, false);
      HwBlob hwBlob2 = new HwBlob(i * 72);
      for (byte b = 0; b < i; b++)
        ((GnssClock)param1ArrayList.get(b)).writeEmbeddedToBlob(hwBlob2, (b * 72)); 
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
      return !HidlSupport.deepEquals(Short.valueOf(this.gnssClockFlags), Short.valueOf(((GnssClock)param1Object).gnssClockFlags)) ? false : ((this.leapSecond != ((GnssClock)param1Object).leapSecond) ? false : ((this.timeNs != ((GnssClock)param1Object).timeNs) ? false : ((this.timeUncertaintyNs != ((GnssClock)param1Object).timeUncertaintyNs) ? false : ((this.fullBiasNs != ((GnssClock)param1Object).fullBiasNs) ? false : ((this.biasNs != ((GnssClock)param1Object).biasNs) ? false : ((this.biasUncertaintyNs != ((GnssClock)param1Object).biasUncertaintyNs) ? false : ((this.driftNsps != ((GnssClock)param1Object).driftNsps) ? false : ((this.driftUncertaintyNsps != ((GnssClock)param1Object).driftUncertaintyNsps) ? false : (!(this.hwClockDiscontinuityCount != ((GnssClock)param1Object).hwClockDiscontinuityCount))))))))));
    }
    
    public final int hashCode() {
      return Objects.hash(new Object[] { Integer.valueOf(HidlSupport.deepHashCode(Short.valueOf(this.gnssClockFlags))), Integer.valueOf(HidlSupport.deepHashCode(Short.valueOf(this.leapSecond))), Integer.valueOf(HidlSupport.deepHashCode(Long.valueOf(this.timeNs))), Integer.valueOf(HidlSupport.deepHashCode(Double.valueOf(this.timeUncertaintyNs))), Integer.valueOf(HidlSupport.deepHashCode(Long.valueOf(this.fullBiasNs))), Integer.valueOf(HidlSupport.deepHashCode(Double.valueOf(this.biasNs))), Integer.valueOf(HidlSupport.deepHashCode(Double.valueOf(this.biasUncertaintyNs))), Integer.valueOf(HidlSupport.deepHashCode(Double.valueOf(this.driftNsps))), Integer.valueOf(HidlSupport.deepHashCode(Double.valueOf(this.driftUncertaintyNsps))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.hwClockDiscontinuityCount))) });
    }
    
    public final void readEmbeddedFromParcel(HwParcel param1HwParcel, HwBlob param1HwBlob, long param1Long) {
      this.gnssClockFlags = param1HwBlob.getInt16(0L + param1Long);
      this.leapSecond = param1HwBlob.getInt16(2L + param1Long);
      this.timeNs = param1HwBlob.getInt64(8L + param1Long);
      this.timeUncertaintyNs = param1HwBlob.getDouble(16L + param1Long);
      this.fullBiasNs = param1HwBlob.getInt64(24L + param1Long);
      this.biasNs = param1HwBlob.getDouble(32L + param1Long);
      this.biasUncertaintyNs = param1HwBlob.getDouble(40L + param1Long);
      this.driftNsps = param1HwBlob.getDouble(48L + param1Long);
      this.driftUncertaintyNsps = param1HwBlob.getDouble(56L + param1Long);
      this.hwClockDiscontinuityCount = param1HwBlob.getInt32(64L + param1Long);
    }
    
    public final void readFromParcel(HwParcel param1HwParcel) {
      readEmbeddedFromParcel(param1HwParcel, param1HwParcel.readBuffer(72L), 0L);
    }
    
    public final String toString() {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("{");
      stringBuilder.append(".gnssClockFlags = ");
      stringBuilder.append(IGnssMeasurementCallback.GnssClockFlags.dumpBitfield(this.gnssClockFlags));
      stringBuilder.append(", .leapSecond = ");
      stringBuilder.append(this.leapSecond);
      stringBuilder.append(", .timeNs = ");
      stringBuilder.append(this.timeNs);
      stringBuilder.append(", .timeUncertaintyNs = ");
      stringBuilder.append(this.timeUncertaintyNs);
      stringBuilder.append(", .fullBiasNs = ");
      stringBuilder.append(this.fullBiasNs);
      stringBuilder.append(", .biasNs = ");
      stringBuilder.append(this.biasNs);
      stringBuilder.append(", .biasUncertaintyNs = ");
      stringBuilder.append(this.biasUncertaintyNs);
      stringBuilder.append(", .driftNsps = ");
      stringBuilder.append(this.driftNsps);
      stringBuilder.append(", .driftUncertaintyNsps = ");
      stringBuilder.append(this.driftUncertaintyNsps);
      stringBuilder.append(", .hwClockDiscontinuityCount = ");
      stringBuilder.append(this.hwClockDiscontinuityCount);
      stringBuilder.append("}");
      return stringBuilder.toString();
    }
    
    public final void writeEmbeddedToBlob(HwBlob param1HwBlob, long param1Long) {
      param1HwBlob.putInt16(0L + param1Long, this.gnssClockFlags);
      param1HwBlob.putInt16(2L + param1Long, this.leapSecond);
      param1HwBlob.putInt64(8L + param1Long, this.timeNs);
      param1HwBlob.putDouble(16L + param1Long, this.timeUncertaintyNs);
      param1HwBlob.putInt64(24L + param1Long, this.fullBiasNs);
      param1HwBlob.putDouble(32L + param1Long, this.biasNs);
      param1HwBlob.putDouble(40L + param1Long, this.biasUncertaintyNs);
      param1HwBlob.putDouble(48L + param1Long, this.driftNsps);
      param1HwBlob.putDouble(56L + param1Long, this.driftUncertaintyNsps);
      param1HwBlob.putInt32(64L + param1Long, this.hwClockDiscontinuityCount);
    }
    
    public final void writeToParcel(HwParcel param1HwParcel) {
      HwBlob hwBlob = new HwBlob(72);
      writeEmbeddedToBlob(hwBlob, 0L);
      param1HwParcel.writeBuffer(hwBlob);
    }
  }
  
  public static final class GnssClockFlags {
    public static final short HAS_BIAS = 8;
    
    public static final short HAS_BIAS_UNCERTAINTY = 16;
    
    public static final short HAS_DRIFT = 32;
    
    public static final short HAS_DRIFT_UNCERTAINTY = 64;
    
    public static final short HAS_FULL_BIAS = 4;
    
    public static final short HAS_LEAP_SECOND = 1;
    
    public static final short HAS_TIME_UNCERTAINTY = 2;
    
    public static final String dumpBitfield(short param1Short) {
      ArrayList<String> arrayList = new ArrayList();
      short s1 = 0;
      if ((param1Short & 0x1) == 1) {
        arrayList.add("HAS_LEAP_SECOND");
        s1 = (short)(false | true);
      } 
      short s2 = s1;
      if ((param1Short & 0x2) == 2) {
        arrayList.add("HAS_TIME_UNCERTAINTY");
        s2 = (short)(s1 | 0x2);
      } 
      short s3 = s2;
      if ((param1Short & 0x4) == 4) {
        arrayList.add("HAS_FULL_BIAS");
        s3 = (short)(s2 | 0x4);
      } 
      s1 = s3;
      if ((param1Short & 0x8) == 8) {
        arrayList.add("HAS_BIAS");
        s1 = (short)(s3 | 0x8);
      } 
      s2 = s1;
      if ((param1Short & 0x10) == 16) {
        arrayList.add("HAS_BIAS_UNCERTAINTY");
        s2 = (short)(s1 | 0x10);
      } 
      s1 = s2;
      if ((param1Short & 0x20) == 32) {
        arrayList.add("HAS_DRIFT");
        s1 = (short)(s2 | 0x20);
      } 
      s2 = s1;
      if ((param1Short & 0x40) == 64) {
        arrayList.add("HAS_DRIFT_UNCERTAINTY");
        s2 = (short)(s1 | 0x40);
      } 
      if (param1Short != s2) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("0x");
        stringBuilder.append(Integer.toHexString(Short.toUnsignedInt((short)(s2 & param1Short))));
        arrayList.add(stringBuilder.toString());
      } 
      return String.join(" | ", (Iterable)arrayList);
    }
    
    public static final String toString(short param1Short) {
      if (param1Short == 1)
        return "HAS_LEAP_SECOND"; 
      if (param1Short == 2)
        return "HAS_TIME_UNCERTAINTY"; 
      if (param1Short == 4)
        return "HAS_FULL_BIAS"; 
      if (param1Short == 8)
        return "HAS_BIAS"; 
      if (param1Short == 16)
        return "HAS_BIAS_UNCERTAINTY"; 
      if (param1Short == 32)
        return "HAS_DRIFT"; 
      if (param1Short == 64)
        return "HAS_DRIFT_UNCERTAINTY"; 
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("0x");
      stringBuilder.append(Integer.toHexString(Short.toUnsignedInt(param1Short)));
      return stringBuilder.toString();
    }
  }
  
  public static final class GnssData {
    public IGnssMeasurementCallback.GnssClock clock = new IGnssMeasurementCallback.GnssClock();
    
    public int measurementCount = 0;
    
    public IGnssMeasurementCallback.GnssMeasurement[] measurements = new IGnssMeasurementCallback.GnssMeasurement[64];
    
    public static final ArrayList<GnssData> readVectorFromParcel(HwParcel param1HwParcel) {
      ArrayList<GnssData> arrayList = new ArrayList();
      HwBlob hwBlob1 = param1HwParcel.readBuffer(16L);
      int i = hwBlob1.getInt32(8L);
      HwBlob hwBlob2 = param1HwParcel.readEmbeddedBuffer((i * 9296), hwBlob1.handle(), 0L, true);
      arrayList.clear();
      for (byte b = 0; b < i; b++) {
        GnssData gnssData = new GnssData();
        gnssData.readEmbeddedFromParcel(param1HwParcel, hwBlob2, (b * 9296));
        arrayList.add(gnssData);
      } 
      return arrayList;
    }
    
    public static final void writeVectorToParcel(HwParcel param1HwParcel, ArrayList<GnssData> param1ArrayList) {
      HwBlob hwBlob1 = new HwBlob(16);
      int i = param1ArrayList.size();
      hwBlob1.putInt32(8L, i);
      hwBlob1.putBool(12L, false);
      HwBlob hwBlob2 = new HwBlob(i * 9296);
      for (byte b = 0; b < i; b++)
        ((GnssData)param1ArrayList.get(b)).writeEmbeddedToBlob(hwBlob2, (b * 9296)); 
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
      return (this.measurementCount != ((GnssData)param1Object).measurementCount) ? false : (!HidlSupport.deepEquals(this.measurements, ((GnssData)param1Object).measurements) ? false : (!!HidlSupport.deepEquals(this.clock, ((GnssData)param1Object).clock)));
    }
    
    public final int hashCode() {
      return Objects.hash(new Object[] { Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.measurementCount))), Integer.valueOf(HidlSupport.deepHashCode(this.measurements)), Integer.valueOf(HidlSupport.deepHashCode(this.clock)) });
    }
    
    public final void readEmbeddedFromParcel(HwParcel param1HwParcel, HwBlob param1HwBlob, long param1Long) {
      this.measurementCount = param1HwBlob.getInt32(0L + param1Long);
      long l = 8L + param1Long;
      for (byte b = 0; b < 64; b++) {
        this.measurements[b] = new IGnssMeasurementCallback.GnssMeasurement();
        this.measurements[b].readEmbeddedFromParcel(param1HwParcel, param1HwBlob, l);
        l += 144L;
      } 
      this.clock.readEmbeddedFromParcel(param1HwParcel, param1HwBlob, 9224L + param1Long);
    }
    
    public final void readFromParcel(HwParcel param1HwParcel) {
      readEmbeddedFromParcel(param1HwParcel, param1HwParcel.readBuffer(9296L), 0L);
    }
    
    public final String toString() {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("{");
      stringBuilder.append(".measurementCount = ");
      stringBuilder.append(this.measurementCount);
      stringBuilder.append(", .measurements = ");
      stringBuilder.append(Arrays.toString((Object[])this.measurements));
      stringBuilder.append(", .clock = ");
      stringBuilder.append(this.clock);
      stringBuilder.append("}");
      return stringBuilder.toString();
    }
    
    public final void writeEmbeddedToBlob(HwBlob param1HwBlob, long param1Long) {
      param1HwBlob.putInt32(0L + param1Long, this.measurementCount);
      long l = 8L + param1Long;
      for (byte b = 0; b < 64; b++) {
        this.measurements[b].writeEmbeddedToBlob(param1HwBlob, l);
        l += 144L;
      } 
      this.clock.writeEmbeddedToBlob(param1HwBlob, 9224L + param1Long);
    }
    
    public final void writeToParcel(HwParcel param1HwParcel) {
      HwBlob hwBlob = new HwBlob(9296);
      writeEmbeddedToBlob(hwBlob, 0L);
      param1HwParcel.writeBuffer(hwBlob);
    }
  }
  
  public static final class GnssMeasurement {
    public double accumulatedDeltaRangeM = 0.0D;
    
    public short accumulatedDeltaRangeState;
    
    public double accumulatedDeltaRangeUncertaintyM = 0.0D;
    
    public double agcLevelDb = 0.0D;
    
    public double cN0DbHz = 0.0D;
    
    public long carrierCycles = 0L;
    
    public float carrierFrequencyHz = 0.0F;
    
    public double carrierPhase = 0.0D;
    
    public double carrierPhaseUncertainty = 0.0D;
    
    public byte constellation = (byte)0;
    
    public int flags;
    
    public byte multipathIndicator = (byte)0;
    
    public double pseudorangeRateMps = 0.0D;
    
    public double pseudorangeRateUncertaintyMps = 0.0D;
    
    public long receivedSvTimeInNs = 0L;
    
    public long receivedSvTimeUncertaintyInNs = 0L;
    
    public double snrDb = 0.0D;
    
    public int state;
    
    public short svid = (short)0;
    
    public double timeOffsetNs = 0.0D;
    
    public static final ArrayList<GnssMeasurement> readVectorFromParcel(HwParcel param1HwParcel) {
      ArrayList<GnssMeasurement> arrayList = new ArrayList();
      HwBlob hwBlob = param1HwParcel.readBuffer(16L);
      int i = hwBlob.getInt32(8L);
      hwBlob = param1HwParcel.readEmbeddedBuffer((i * 144), hwBlob.handle(), 0L, true);
      arrayList.clear();
      for (byte b = 0; b < i; b++) {
        GnssMeasurement gnssMeasurement = new GnssMeasurement();
        gnssMeasurement.readEmbeddedFromParcel(param1HwParcel, hwBlob, (b * 144));
        arrayList.add(gnssMeasurement);
      } 
      return arrayList;
    }
    
    public static final void writeVectorToParcel(HwParcel param1HwParcel, ArrayList<GnssMeasurement> param1ArrayList) {
      HwBlob hwBlob1 = new HwBlob(16);
      int i = param1ArrayList.size();
      hwBlob1.putInt32(8L, i);
      hwBlob1.putBool(12L, false);
      HwBlob hwBlob2 = new HwBlob(i * 144);
      for (byte b = 0; b < i; b++)
        ((GnssMeasurement)param1ArrayList.get(b)).writeEmbeddedToBlob(hwBlob2, (b * 144)); 
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
      return !HidlSupport.deepEquals(Integer.valueOf(this.flags), Integer.valueOf(((GnssMeasurement)param1Object).flags)) ? false : ((this.svid != ((GnssMeasurement)param1Object).svid) ? false : ((this.constellation != ((GnssMeasurement)param1Object).constellation) ? false : ((this.timeOffsetNs != ((GnssMeasurement)param1Object).timeOffsetNs) ? false : (!HidlSupport.deepEquals(Integer.valueOf(this.state), Integer.valueOf(((GnssMeasurement)param1Object).state)) ? false : ((this.receivedSvTimeInNs != ((GnssMeasurement)param1Object).receivedSvTimeInNs) ? false : ((this.receivedSvTimeUncertaintyInNs != ((GnssMeasurement)param1Object).receivedSvTimeUncertaintyInNs) ? false : ((this.cN0DbHz != ((GnssMeasurement)param1Object).cN0DbHz) ? false : ((this.pseudorangeRateMps != ((GnssMeasurement)param1Object).pseudorangeRateMps) ? false : ((this.pseudorangeRateUncertaintyMps != ((GnssMeasurement)param1Object).pseudorangeRateUncertaintyMps) ? false : (!HidlSupport.deepEquals(Short.valueOf(this.accumulatedDeltaRangeState), Short.valueOf(((GnssMeasurement)param1Object).accumulatedDeltaRangeState)) ? false : ((this.accumulatedDeltaRangeM != ((GnssMeasurement)param1Object).accumulatedDeltaRangeM) ? false : ((this.accumulatedDeltaRangeUncertaintyM != ((GnssMeasurement)param1Object).accumulatedDeltaRangeUncertaintyM) ? false : ((this.carrierFrequencyHz != ((GnssMeasurement)param1Object).carrierFrequencyHz) ? false : ((this.carrierCycles != ((GnssMeasurement)param1Object).carrierCycles) ? false : ((this.carrierPhase != ((GnssMeasurement)param1Object).carrierPhase) ? false : ((this.carrierPhaseUncertainty != ((GnssMeasurement)param1Object).carrierPhaseUncertainty) ? false : ((this.multipathIndicator != ((GnssMeasurement)param1Object).multipathIndicator) ? false : ((this.snrDb != ((GnssMeasurement)param1Object).snrDb) ? false : (!(this.agcLevelDb != ((GnssMeasurement)param1Object).agcLevelDb))))))))))))))))))));
    }
    
    public final int hashCode() {
      return Objects.hash(new Object[] { 
            Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.flags))), Integer.valueOf(HidlSupport.deepHashCode(Short.valueOf(this.svid))), Integer.valueOf(HidlSupport.deepHashCode(Byte.valueOf(this.constellation))), Integer.valueOf(HidlSupport.deepHashCode(Double.valueOf(this.timeOffsetNs))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.state))), Integer.valueOf(HidlSupport.deepHashCode(Long.valueOf(this.receivedSvTimeInNs))), Integer.valueOf(HidlSupport.deepHashCode(Long.valueOf(this.receivedSvTimeUncertaintyInNs))), Integer.valueOf(HidlSupport.deepHashCode(Double.valueOf(this.cN0DbHz))), Integer.valueOf(HidlSupport.deepHashCode(Double.valueOf(this.pseudorangeRateMps))), Integer.valueOf(HidlSupport.deepHashCode(Double.valueOf(this.pseudorangeRateUncertaintyMps))), 
            Integer.valueOf(HidlSupport.deepHashCode(Short.valueOf(this.accumulatedDeltaRangeState))), Integer.valueOf(HidlSupport.deepHashCode(Double.valueOf(this.accumulatedDeltaRangeM))), Integer.valueOf(HidlSupport.deepHashCode(Double.valueOf(this.accumulatedDeltaRangeUncertaintyM))), Integer.valueOf(HidlSupport.deepHashCode(Float.valueOf(this.carrierFrequencyHz))), Integer.valueOf(HidlSupport.deepHashCode(Long.valueOf(this.carrierCycles))), Integer.valueOf(HidlSupport.deepHashCode(Double.valueOf(this.carrierPhase))), Integer.valueOf(HidlSupport.deepHashCode(Double.valueOf(this.carrierPhaseUncertainty))), Integer.valueOf(HidlSupport.deepHashCode(Byte.valueOf(this.multipathIndicator))), Integer.valueOf(HidlSupport.deepHashCode(Double.valueOf(this.snrDb))), Integer.valueOf(HidlSupport.deepHashCode(Double.valueOf(this.agcLevelDb))) });
    }
    
    public final void readEmbeddedFromParcel(HwParcel param1HwParcel, HwBlob param1HwBlob, long param1Long) {
      this.flags = param1HwBlob.getInt32(0L + param1Long);
      this.svid = param1HwBlob.getInt16(4L + param1Long);
      this.constellation = param1HwBlob.getInt8(6L + param1Long);
      this.timeOffsetNs = param1HwBlob.getDouble(8L + param1Long);
      this.state = param1HwBlob.getInt32(16L + param1Long);
      this.receivedSvTimeInNs = param1HwBlob.getInt64(24L + param1Long);
      this.receivedSvTimeUncertaintyInNs = param1HwBlob.getInt64(32L + param1Long);
      this.cN0DbHz = param1HwBlob.getDouble(40L + param1Long);
      this.pseudorangeRateMps = param1HwBlob.getDouble(48L + param1Long);
      this.pseudorangeRateUncertaintyMps = param1HwBlob.getDouble(56L + param1Long);
      this.accumulatedDeltaRangeState = param1HwBlob.getInt16(64L + param1Long);
      this.accumulatedDeltaRangeM = param1HwBlob.getDouble(72L + param1Long);
      this.accumulatedDeltaRangeUncertaintyM = param1HwBlob.getDouble(80L + param1Long);
      this.carrierFrequencyHz = param1HwBlob.getFloat(88L + param1Long);
      this.carrierCycles = param1HwBlob.getInt64(96L + param1Long);
      this.carrierPhase = param1HwBlob.getDouble(104L + param1Long);
      this.carrierPhaseUncertainty = param1HwBlob.getDouble(112L + param1Long);
      this.multipathIndicator = param1HwBlob.getInt8(120L + param1Long);
      this.snrDb = param1HwBlob.getDouble(128L + param1Long);
      this.agcLevelDb = param1HwBlob.getDouble(136L + param1Long);
    }
    
    public final void readFromParcel(HwParcel param1HwParcel) {
      readEmbeddedFromParcel(param1HwParcel, param1HwParcel.readBuffer(144L), 0L);
    }
    
    public final String toString() {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("{");
      stringBuilder.append(".flags = ");
      stringBuilder.append(IGnssMeasurementCallback.GnssMeasurementFlags.dumpBitfield(this.flags));
      stringBuilder.append(", .svid = ");
      stringBuilder.append(this.svid);
      stringBuilder.append(", .constellation = ");
      stringBuilder.append(GnssConstellationType.toString(this.constellation));
      stringBuilder.append(", .timeOffsetNs = ");
      stringBuilder.append(this.timeOffsetNs);
      stringBuilder.append(", .state = ");
      stringBuilder.append(IGnssMeasurementCallback.GnssMeasurementState.dumpBitfield(this.state));
      stringBuilder.append(", .receivedSvTimeInNs = ");
      stringBuilder.append(this.receivedSvTimeInNs);
      stringBuilder.append(", .receivedSvTimeUncertaintyInNs = ");
      stringBuilder.append(this.receivedSvTimeUncertaintyInNs);
      stringBuilder.append(", .cN0DbHz = ");
      stringBuilder.append(this.cN0DbHz);
      stringBuilder.append(", .pseudorangeRateMps = ");
      stringBuilder.append(this.pseudorangeRateMps);
      stringBuilder.append(", .pseudorangeRateUncertaintyMps = ");
      stringBuilder.append(this.pseudorangeRateUncertaintyMps);
      stringBuilder.append(", .accumulatedDeltaRangeState = ");
      stringBuilder.append(IGnssMeasurementCallback.GnssAccumulatedDeltaRangeState.dumpBitfield(this.accumulatedDeltaRangeState));
      stringBuilder.append(", .accumulatedDeltaRangeM = ");
      stringBuilder.append(this.accumulatedDeltaRangeM);
      stringBuilder.append(", .accumulatedDeltaRangeUncertaintyM = ");
      stringBuilder.append(this.accumulatedDeltaRangeUncertaintyM);
      stringBuilder.append(", .carrierFrequencyHz = ");
      stringBuilder.append(this.carrierFrequencyHz);
      stringBuilder.append(", .carrierCycles = ");
      stringBuilder.append(this.carrierCycles);
      stringBuilder.append(", .carrierPhase = ");
      stringBuilder.append(this.carrierPhase);
      stringBuilder.append(", .carrierPhaseUncertainty = ");
      stringBuilder.append(this.carrierPhaseUncertainty);
      stringBuilder.append(", .multipathIndicator = ");
      stringBuilder.append(IGnssMeasurementCallback.GnssMultipathIndicator.toString(this.multipathIndicator));
      stringBuilder.append(", .snrDb = ");
      stringBuilder.append(this.snrDb);
      stringBuilder.append(", .agcLevelDb = ");
      stringBuilder.append(this.agcLevelDb);
      stringBuilder.append("}");
      return stringBuilder.toString();
    }
    
    public final void writeEmbeddedToBlob(HwBlob param1HwBlob, long param1Long) {
      param1HwBlob.putInt32(0L + param1Long, this.flags);
      param1HwBlob.putInt16(4L + param1Long, this.svid);
      param1HwBlob.putInt8(6L + param1Long, this.constellation);
      param1HwBlob.putDouble(8L + param1Long, this.timeOffsetNs);
      param1HwBlob.putInt32(16L + param1Long, this.state);
      param1HwBlob.putInt64(24L + param1Long, this.receivedSvTimeInNs);
      param1HwBlob.putInt64(32L + param1Long, this.receivedSvTimeUncertaintyInNs);
      param1HwBlob.putDouble(40L + param1Long, this.cN0DbHz);
      param1HwBlob.putDouble(48L + param1Long, this.pseudorangeRateMps);
      param1HwBlob.putDouble(56L + param1Long, this.pseudorangeRateUncertaintyMps);
      param1HwBlob.putInt16(64L + param1Long, this.accumulatedDeltaRangeState);
      param1HwBlob.putDouble(72L + param1Long, this.accumulatedDeltaRangeM);
      param1HwBlob.putDouble(80L + param1Long, this.accumulatedDeltaRangeUncertaintyM);
      param1HwBlob.putFloat(88L + param1Long, this.carrierFrequencyHz);
      param1HwBlob.putInt64(96L + param1Long, this.carrierCycles);
      param1HwBlob.putDouble(104L + param1Long, this.carrierPhase);
      param1HwBlob.putDouble(112L + param1Long, this.carrierPhaseUncertainty);
      param1HwBlob.putInt8(120L + param1Long, this.multipathIndicator);
      param1HwBlob.putDouble(128L + param1Long, this.snrDb);
      param1HwBlob.putDouble(136L + param1Long, this.agcLevelDb);
    }
    
    public final void writeToParcel(HwParcel param1HwParcel) {
      HwBlob hwBlob = new HwBlob(144);
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
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("0x");
      stringBuilder.append(Integer.toHexString(param1Int));
      return stringBuilder.toString();
    }
  }
  
  public static final class GnssMeasurementState {
    public static final int STATE_BDS_D2_BIT_SYNC = 256;
    
    public static final int STATE_BDS_D2_SUBFRAME_SYNC = 512;
    
    public static final int STATE_BIT_SYNC = 2;
    
    public static final int STATE_CODE_LOCK = 1;
    
    public static final int STATE_GAL_E1BC_CODE_LOCK = 1024;
    
    public static final int STATE_GAL_E1B_PAGE_SYNC = 4096;
    
    public static final int STATE_GAL_E1C_2ND_CODE_LOCK = 2048;
    
    public static final int STATE_GLO_STRING_SYNC = 64;
    
    public static final int STATE_GLO_TOD_DECODED = 128;
    
    public static final int STATE_GLO_TOD_KNOWN = 32768;
    
    public static final int STATE_MSEC_AMBIGUOUS = 16;
    
    public static final int STATE_SBAS_SYNC = 8192;
    
    public static final int STATE_SUBFRAME_SYNC = 4;
    
    public static final int STATE_SYMBOL_SYNC = 32;
    
    public static final int STATE_TOW_DECODED = 8;
    
    public static final int STATE_TOW_KNOWN = 16384;
    
    public static final int STATE_UNKNOWN = 0;
    
    public static final String dumpBitfield(int param1Int) {
      ArrayList<String> arrayList = new ArrayList();
      int i = 0;
      arrayList.add("STATE_UNKNOWN");
      if ((param1Int & 0x1) == 1) {
        arrayList.add("STATE_CODE_LOCK");
        i = false | true;
      } 
      int j = i;
      if ((param1Int & 0x2) == 2) {
        arrayList.add("STATE_BIT_SYNC");
        j = i | 0x2;
      } 
      i = j;
      if ((param1Int & 0x4) == 4) {
        arrayList.add("STATE_SUBFRAME_SYNC");
        i = j | 0x4;
      } 
      int k = i;
      if ((param1Int & 0x8) == 8) {
        arrayList.add("STATE_TOW_DECODED");
        k = i | 0x8;
      } 
      j = k;
      if ((param1Int & 0x10) == 16) {
        arrayList.add("STATE_MSEC_AMBIGUOUS");
        j = k | 0x10;
      } 
      i = j;
      if ((param1Int & 0x20) == 32) {
        arrayList.add("STATE_SYMBOL_SYNC");
        i = j | 0x20;
      } 
      j = i;
      if ((param1Int & 0x40) == 64) {
        arrayList.add("STATE_GLO_STRING_SYNC");
        j = i | 0x40;
      } 
      i = j;
      if ((param1Int & 0x80) == 128) {
        arrayList.add("STATE_GLO_TOD_DECODED");
        i = j | 0x80;
      } 
      j = i;
      if ((param1Int & 0x100) == 256) {
        arrayList.add("STATE_BDS_D2_BIT_SYNC");
        j = i | 0x100;
      } 
      k = j;
      if ((param1Int & 0x200) == 512) {
        arrayList.add("STATE_BDS_D2_SUBFRAME_SYNC");
        k = j | 0x200;
      } 
      i = k;
      if ((param1Int & 0x400) == 1024) {
        arrayList.add("STATE_GAL_E1BC_CODE_LOCK");
        i = k | 0x400;
      } 
      j = i;
      if ((param1Int & 0x800) == 2048) {
        arrayList.add("STATE_GAL_E1C_2ND_CODE_LOCK");
        j = i | 0x800;
      } 
      i = j;
      if ((param1Int & 0x1000) == 4096) {
        arrayList.add("STATE_GAL_E1B_PAGE_SYNC");
        i = j | 0x1000;
      } 
      j = i;
      if ((param1Int & 0x2000) == 8192) {
        arrayList.add("STATE_SBAS_SYNC");
        j = i | 0x2000;
      } 
      i = j;
      if ((param1Int & 0x4000) == 16384) {
        arrayList.add("STATE_TOW_KNOWN");
        i = j | 0x4000;
      } 
      j = i;
      if ((param1Int & 0x8000) == 32768) {
        arrayList.add("STATE_GLO_TOD_KNOWN");
        j = i | 0x8000;
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
      if (param1Int == 0)
        return "STATE_UNKNOWN"; 
      if (param1Int == 1)
        return "STATE_CODE_LOCK"; 
      if (param1Int == 2)
        return "STATE_BIT_SYNC"; 
      if (param1Int == 4)
        return "STATE_SUBFRAME_SYNC"; 
      if (param1Int == 8)
        return "STATE_TOW_DECODED"; 
      if (param1Int == 16)
        return "STATE_MSEC_AMBIGUOUS"; 
      if (param1Int == 32)
        return "STATE_SYMBOL_SYNC"; 
      if (param1Int == 64)
        return "STATE_GLO_STRING_SYNC"; 
      if (param1Int == 128)
        return "STATE_GLO_TOD_DECODED"; 
      if (param1Int == 256)
        return "STATE_BDS_D2_BIT_SYNC"; 
      if (param1Int == 512)
        return "STATE_BDS_D2_SUBFRAME_SYNC"; 
      if (param1Int == 1024)
        return "STATE_GAL_E1BC_CODE_LOCK"; 
      if (param1Int == 2048)
        return "STATE_GAL_E1C_2ND_CODE_LOCK"; 
      if (param1Int == 4096)
        return "STATE_GAL_E1B_PAGE_SYNC"; 
      if (param1Int == 8192)
        return "STATE_SBAS_SYNC"; 
      if (param1Int == 16384)
        return "STATE_TOW_KNOWN"; 
      if (param1Int == 32768)
        return "STATE_GLO_TOD_KNOWN"; 
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("0x");
      stringBuilder.append(Integer.toHexString(param1Int));
      return stringBuilder.toString();
    }
  }
  
  public static final class GnssMultipathIndicator {
    public static final byte INDICATIOR_NOT_PRESENT = 2;
    
    public static final byte INDICATOR_PRESENT = 1;
    
    public static final byte INDICATOR_UNKNOWN = 0;
    
    public static final String dumpBitfield(byte param1Byte) {
      ArrayList<String> arrayList = new ArrayList();
      byte b1 = 0;
      arrayList.add("INDICATOR_UNKNOWN");
      if ((param1Byte & 0x1) == 1) {
        arrayList.add("INDICATOR_PRESENT");
        b1 = (byte)(false | true);
      } 
      byte b2 = b1;
      if ((param1Byte & 0x2) == 2) {
        arrayList.add("INDICATIOR_NOT_PRESENT");
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
        return "INDICATOR_UNKNOWN"; 
      if (param1Byte == 1)
        return "INDICATOR_PRESENT"; 
      if (param1Byte == 2)
        return "INDICATIOR_NOT_PRESENT"; 
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("0x");
      stringBuilder.append(Integer.toHexString(Byte.toUnsignedInt(param1Byte)));
      return stringBuilder.toString();
    }
  }
  
  public static final class Proxy implements IGnssMeasurementCallback {
    private IHwBinder mRemote;
    
    public Proxy(IHwBinder param1IHwBinder) {
      Objects.requireNonNull(param1IHwBinder);
      this.mRemote = param1IHwBinder;
    }
    
    public void GnssMeasurementCb(IGnssMeasurementCallback.GnssData param1GnssData) throws RemoteException {
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
        return "[class or subclass of android.hardware.gnss@1.0::IGnssMeasurementCallback]@Proxy";
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
                -41, 2, -5, 1, -36, 42, 7, 51, -86, -126, 
                11, 126, -74, 84, 53, -18, 51, 52, -9, 86, 
                50, -17, -120, 11, -81, -46, -5, -120, 3, -94, 
                10, 88 }, arrayOfByte }));
    }
    
    public final ArrayList<String> interfaceChain() {
      return new ArrayList<>(Arrays.asList(new String[] { "android.hardware.gnss@1.0::IGnssMeasurementCallback", "android.hidl.base@1.0::IBase" }));
    }
    
    public final String interfaceDescriptor() {
      return "android.hardware.gnss@1.0::IGnssMeasurementCallback";
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
          str.enforceInterface("android.hidl.base@1.0::IBase");
          arrayList = interfaceChain();
          param1HwParcel2.writeStatus(0);
          param1HwParcel2.writeStringVector(arrayList);
          param1HwParcel2.send();
        case 1:
          break;
      } 
      arrayList.enforceInterface("android.hardware.gnss@1.0::IGnssMeasurementCallback");
      IGnssMeasurementCallback.GnssData gnssData = new IGnssMeasurementCallback.GnssData();
      gnssData.readFromParcel((HwParcel)arrayList);
      GnssMeasurementCb(gnssData);
      param1HwParcel2.writeStatus(0);
      param1HwParcel2.send();
    }
    
    public final void ping() {}
    
    public IHwInterface queryLocalInterface(String param1String) {
      return (IHwInterface)("android.hardware.gnss@1.0::IGnssMeasurementCallback".equals(param1String) ? this : null);
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


/* Location:              /home/chun/Desktop/temp/!/android/hardware/gnss/V1_0/IGnssMeasurementCallback.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */