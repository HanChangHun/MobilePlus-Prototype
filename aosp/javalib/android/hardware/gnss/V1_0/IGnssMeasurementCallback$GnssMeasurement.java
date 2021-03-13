package android.hardware.gnss.V1_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

public final class GnssMeasurement {
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
  
  public static final ArrayList<GnssMeasurement> readVectorFromParcel(HwParcel paramHwParcel) {
    ArrayList<GnssMeasurement> arrayList = new ArrayList();
    HwBlob hwBlob = paramHwParcel.readBuffer(16L);
    int i = hwBlob.getInt32(8L);
    hwBlob = paramHwParcel.readEmbeddedBuffer((i * 144), hwBlob.handle(), 0L, true);
    arrayList.clear();
    for (byte b = 0; b < i; b++) {
      GnssMeasurement gnssMeasurement = new GnssMeasurement();
      gnssMeasurement.readEmbeddedFromParcel(paramHwParcel, hwBlob, (b * 144));
      arrayList.add(gnssMeasurement);
    } 
    return arrayList;
  }
  
  public static final void writeVectorToParcel(HwParcel paramHwParcel, ArrayList<GnssMeasurement> paramArrayList) {
    HwBlob hwBlob1 = new HwBlob(16);
    int i = paramArrayList.size();
    hwBlob1.putInt32(8L, i);
    hwBlob1.putBool(12L, false);
    HwBlob hwBlob2 = new HwBlob(i * 144);
    for (byte b = 0; b < i; b++)
      ((GnssMeasurement)paramArrayList.get(b)).writeEmbeddedToBlob(hwBlob2, (b * 144)); 
    hwBlob1.putBlob(0L, hwBlob2);
    paramHwParcel.writeBuffer(hwBlob1);
  }
  
  public final boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (paramObject == null)
      return false; 
    if (paramObject.getClass() != GnssMeasurement.class)
      return false; 
    paramObject = paramObject;
    return !HidlSupport.deepEquals(Integer.valueOf(this.flags), Integer.valueOf(((GnssMeasurement)paramObject).flags)) ? false : ((this.svid != ((GnssMeasurement)paramObject).svid) ? false : ((this.constellation != ((GnssMeasurement)paramObject).constellation) ? false : ((this.timeOffsetNs != ((GnssMeasurement)paramObject).timeOffsetNs) ? false : (!HidlSupport.deepEquals(Integer.valueOf(this.state), Integer.valueOf(((GnssMeasurement)paramObject).state)) ? false : ((this.receivedSvTimeInNs != ((GnssMeasurement)paramObject).receivedSvTimeInNs) ? false : ((this.receivedSvTimeUncertaintyInNs != ((GnssMeasurement)paramObject).receivedSvTimeUncertaintyInNs) ? false : ((this.cN0DbHz != ((GnssMeasurement)paramObject).cN0DbHz) ? false : ((this.pseudorangeRateMps != ((GnssMeasurement)paramObject).pseudorangeRateMps) ? false : ((this.pseudorangeRateUncertaintyMps != ((GnssMeasurement)paramObject).pseudorangeRateUncertaintyMps) ? false : (!HidlSupport.deepEquals(Short.valueOf(this.accumulatedDeltaRangeState), Short.valueOf(((GnssMeasurement)paramObject).accumulatedDeltaRangeState)) ? false : ((this.accumulatedDeltaRangeM != ((GnssMeasurement)paramObject).accumulatedDeltaRangeM) ? false : ((this.accumulatedDeltaRangeUncertaintyM != ((GnssMeasurement)paramObject).accumulatedDeltaRangeUncertaintyM) ? false : ((this.carrierFrequencyHz != ((GnssMeasurement)paramObject).carrierFrequencyHz) ? false : ((this.carrierCycles != ((GnssMeasurement)paramObject).carrierCycles) ? false : ((this.carrierPhase != ((GnssMeasurement)paramObject).carrierPhase) ? false : ((this.carrierPhaseUncertainty != ((GnssMeasurement)paramObject).carrierPhaseUncertainty) ? false : ((this.multipathIndicator != ((GnssMeasurement)paramObject).multipathIndicator) ? false : ((this.snrDb != ((GnssMeasurement)paramObject).snrDb) ? false : (!(this.agcLevelDb != ((GnssMeasurement)paramObject).agcLevelDb))))))))))))))))))));
  }
  
  public final int hashCode() {
    return Objects.hash(new Object[] { 
          Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.flags))), Integer.valueOf(HidlSupport.deepHashCode(Short.valueOf(this.svid))), Integer.valueOf(HidlSupport.deepHashCode(Byte.valueOf(this.constellation))), Integer.valueOf(HidlSupport.deepHashCode(Double.valueOf(this.timeOffsetNs))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.state))), Integer.valueOf(HidlSupport.deepHashCode(Long.valueOf(this.receivedSvTimeInNs))), Integer.valueOf(HidlSupport.deepHashCode(Long.valueOf(this.receivedSvTimeUncertaintyInNs))), Integer.valueOf(HidlSupport.deepHashCode(Double.valueOf(this.cN0DbHz))), Integer.valueOf(HidlSupport.deepHashCode(Double.valueOf(this.pseudorangeRateMps))), Integer.valueOf(HidlSupport.deepHashCode(Double.valueOf(this.pseudorangeRateUncertaintyMps))), 
          Integer.valueOf(HidlSupport.deepHashCode(Short.valueOf(this.accumulatedDeltaRangeState))), Integer.valueOf(HidlSupport.deepHashCode(Double.valueOf(this.accumulatedDeltaRangeM))), Integer.valueOf(HidlSupport.deepHashCode(Double.valueOf(this.accumulatedDeltaRangeUncertaintyM))), Integer.valueOf(HidlSupport.deepHashCode(Float.valueOf(this.carrierFrequencyHz))), Integer.valueOf(HidlSupport.deepHashCode(Long.valueOf(this.carrierCycles))), Integer.valueOf(HidlSupport.deepHashCode(Double.valueOf(this.carrierPhase))), Integer.valueOf(HidlSupport.deepHashCode(Double.valueOf(this.carrierPhaseUncertainty))), Integer.valueOf(HidlSupport.deepHashCode(Byte.valueOf(this.multipathIndicator))), Integer.valueOf(HidlSupport.deepHashCode(Double.valueOf(this.snrDb))), Integer.valueOf(HidlSupport.deepHashCode(Double.valueOf(this.agcLevelDb))) });
  }
  
  public final void readEmbeddedFromParcel(HwParcel paramHwParcel, HwBlob paramHwBlob, long paramLong) {
    this.flags = paramHwBlob.getInt32(0L + paramLong);
    this.svid = paramHwBlob.getInt16(4L + paramLong);
    this.constellation = paramHwBlob.getInt8(6L + paramLong);
    this.timeOffsetNs = paramHwBlob.getDouble(8L + paramLong);
    this.state = paramHwBlob.getInt32(16L + paramLong);
    this.receivedSvTimeInNs = paramHwBlob.getInt64(24L + paramLong);
    this.receivedSvTimeUncertaintyInNs = paramHwBlob.getInt64(32L + paramLong);
    this.cN0DbHz = paramHwBlob.getDouble(40L + paramLong);
    this.pseudorangeRateMps = paramHwBlob.getDouble(48L + paramLong);
    this.pseudorangeRateUncertaintyMps = paramHwBlob.getDouble(56L + paramLong);
    this.accumulatedDeltaRangeState = paramHwBlob.getInt16(64L + paramLong);
    this.accumulatedDeltaRangeM = paramHwBlob.getDouble(72L + paramLong);
    this.accumulatedDeltaRangeUncertaintyM = paramHwBlob.getDouble(80L + paramLong);
    this.carrierFrequencyHz = paramHwBlob.getFloat(88L + paramLong);
    this.carrierCycles = paramHwBlob.getInt64(96L + paramLong);
    this.carrierPhase = paramHwBlob.getDouble(104L + paramLong);
    this.carrierPhaseUncertainty = paramHwBlob.getDouble(112L + paramLong);
    this.multipathIndicator = paramHwBlob.getInt8(120L + paramLong);
    this.snrDb = paramHwBlob.getDouble(128L + paramLong);
    this.agcLevelDb = paramHwBlob.getDouble(136L + paramLong);
  }
  
  public final void readFromParcel(HwParcel paramHwParcel) {
    readEmbeddedFromParcel(paramHwParcel, paramHwParcel.readBuffer(144L), 0L);
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
  
  public final void writeEmbeddedToBlob(HwBlob paramHwBlob, long paramLong) {
    paramHwBlob.putInt32(0L + paramLong, this.flags);
    paramHwBlob.putInt16(4L + paramLong, this.svid);
    paramHwBlob.putInt8(6L + paramLong, this.constellation);
    paramHwBlob.putDouble(8L + paramLong, this.timeOffsetNs);
    paramHwBlob.putInt32(16L + paramLong, this.state);
    paramHwBlob.putInt64(24L + paramLong, this.receivedSvTimeInNs);
    paramHwBlob.putInt64(32L + paramLong, this.receivedSvTimeUncertaintyInNs);
    paramHwBlob.putDouble(40L + paramLong, this.cN0DbHz);
    paramHwBlob.putDouble(48L + paramLong, this.pseudorangeRateMps);
    paramHwBlob.putDouble(56L + paramLong, this.pseudorangeRateUncertaintyMps);
    paramHwBlob.putInt16(64L + paramLong, this.accumulatedDeltaRangeState);
    paramHwBlob.putDouble(72L + paramLong, this.accumulatedDeltaRangeM);
    paramHwBlob.putDouble(80L + paramLong, this.accumulatedDeltaRangeUncertaintyM);
    paramHwBlob.putFloat(88L + paramLong, this.carrierFrequencyHz);
    paramHwBlob.putInt64(96L + paramLong, this.carrierCycles);
    paramHwBlob.putDouble(104L + paramLong, this.carrierPhase);
    paramHwBlob.putDouble(112L + paramLong, this.carrierPhaseUncertainty);
    paramHwBlob.putInt8(120L + paramLong, this.multipathIndicator);
    paramHwBlob.putDouble(128L + paramLong, this.snrDb);
    paramHwBlob.putDouble(136L + paramLong, this.agcLevelDb);
  }
  
  public final void writeToParcel(HwParcel paramHwParcel) {
    HwBlob hwBlob = new HwBlob(144);
    writeEmbeddedToBlob(hwBlob, 0L);
    paramHwParcel.writeBuffer(hwBlob);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/gnss/V1_0/IGnssMeasurementCallback$GnssMeasurement.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */