package android.hardware.gnss.V1_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

public final class GnssClock {
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
  
  public static final ArrayList<GnssClock> readVectorFromParcel(HwParcel paramHwParcel) {
    ArrayList<GnssClock> arrayList = new ArrayList();
    HwBlob hwBlob1 = paramHwParcel.readBuffer(16L);
    int i = hwBlob1.getInt32(8L);
    HwBlob hwBlob2 = paramHwParcel.readEmbeddedBuffer((i * 72), hwBlob1.handle(), 0L, true);
    arrayList.clear();
    for (byte b = 0; b < i; b++) {
      GnssClock gnssClock = new GnssClock();
      gnssClock.readEmbeddedFromParcel(paramHwParcel, hwBlob2, (b * 72));
      arrayList.add(gnssClock);
    } 
    return arrayList;
  }
  
  public static final void writeVectorToParcel(HwParcel paramHwParcel, ArrayList<GnssClock> paramArrayList) {
    HwBlob hwBlob1 = new HwBlob(16);
    int i = paramArrayList.size();
    hwBlob1.putInt32(8L, i);
    hwBlob1.putBool(12L, false);
    HwBlob hwBlob2 = new HwBlob(i * 72);
    for (byte b = 0; b < i; b++)
      ((GnssClock)paramArrayList.get(b)).writeEmbeddedToBlob(hwBlob2, (b * 72)); 
    hwBlob1.putBlob(0L, hwBlob2);
    paramHwParcel.writeBuffer(hwBlob1);
  }
  
  public final boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (paramObject == null)
      return false; 
    if (paramObject.getClass() != GnssClock.class)
      return false; 
    paramObject = paramObject;
    return !HidlSupport.deepEquals(Short.valueOf(this.gnssClockFlags), Short.valueOf(((GnssClock)paramObject).gnssClockFlags)) ? false : ((this.leapSecond != ((GnssClock)paramObject).leapSecond) ? false : ((this.timeNs != ((GnssClock)paramObject).timeNs) ? false : ((this.timeUncertaintyNs != ((GnssClock)paramObject).timeUncertaintyNs) ? false : ((this.fullBiasNs != ((GnssClock)paramObject).fullBiasNs) ? false : ((this.biasNs != ((GnssClock)paramObject).biasNs) ? false : ((this.biasUncertaintyNs != ((GnssClock)paramObject).biasUncertaintyNs) ? false : ((this.driftNsps != ((GnssClock)paramObject).driftNsps) ? false : ((this.driftUncertaintyNsps != ((GnssClock)paramObject).driftUncertaintyNsps) ? false : (!(this.hwClockDiscontinuityCount != ((GnssClock)paramObject).hwClockDiscontinuityCount))))))))));
  }
  
  public final int hashCode() {
    return Objects.hash(new Object[] { Integer.valueOf(HidlSupport.deepHashCode(Short.valueOf(this.gnssClockFlags))), Integer.valueOf(HidlSupport.deepHashCode(Short.valueOf(this.leapSecond))), Integer.valueOf(HidlSupport.deepHashCode(Long.valueOf(this.timeNs))), Integer.valueOf(HidlSupport.deepHashCode(Double.valueOf(this.timeUncertaintyNs))), Integer.valueOf(HidlSupport.deepHashCode(Long.valueOf(this.fullBiasNs))), Integer.valueOf(HidlSupport.deepHashCode(Double.valueOf(this.biasNs))), Integer.valueOf(HidlSupport.deepHashCode(Double.valueOf(this.biasUncertaintyNs))), Integer.valueOf(HidlSupport.deepHashCode(Double.valueOf(this.driftNsps))), Integer.valueOf(HidlSupport.deepHashCode(Double.valueOf(this.driftUncertaintyNsps))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.hwClockDiscontinuityCount))) });
  }
  
  public final void readEmbeddedFromParcel(HwParcel paramHwParcel, HwBlob paramHwBlob, long paramLong) {
    this.gnssClockFlags = paramHwBlob.getInt16(0L + paramLong);
    this.leapSecond = paramHwBlob.getInt16(2L + paramLong);
    this.timeNs = paramHwBlob.getInt64(8L + paramLong);
    this.timeUncertaintyNs = paramHwBlob.getDouble(16L + paramLong);
    this.fullBiasNs = paramHwBlob.getInt64(24L + paramLong);
    this.biasNs = paramHwBlob.getDouble(32L + paramLong);
    this.biasUncertaintyNs = paramHwBlob.getDouble(40L + paramLong);
    this.driftNsps = paramHwBlob.getDouble(48L + paramLong);
    this.driftUncertaintyNsps = paramHwBlob.getDouble(56L + paramLong);
    this.hwClockDiscontinuityCount = paramHwBlob.getInt32(64L + paramLong);
  }
  
  public final void readFromParcel(HwParcel paramHwParcel) {
    readEmbeddedFromParcel(paramHwParcel, paramHwParcel.readBuffer(72L), 0L);
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
  
  public final void writeEmbeddedToBlob(HwBlob paramHwBlob, long paramLong) {
    paramHwBlob.putInt16(0L + paramLong, this.gnssClockFlags);
    paramHwBlob.putInt16(2L + paramLong, this.leapSecond);
    paramHwBlob.putInt64(8L + paramLong, this.timeNs);
    paramHwBlob.putDouble(16L + paramLong, this.timeUncertaintyNs);
    paramHwBlob.putInt64(24L + paramLong, this.fullBiasNs);
    paramHwBlob.putDouble(32L + paramLong, this.biasNs);
    paramHwBlob.putDouble(40L + paramLong, this.biasUncertaintyNs);
    paramHwBlob.putDouble(48L + paramLong, this.driftNsps);
    paramHwBlob.putDouble(56L + paramLong, this.driftUncertaintyNsps);
    paramHwBlob.putInt32(64L + paramLong, this.hwClockDiscontinuityCount);
  }
  
  public final void writeToParcel(HwParcel paramHwParcel) {
    HwBlob hwBlob = new HwBlob(72);
    writeEmbeddedToBlob(hwBlob, 0L);
    paramHwParcel.writeBuffer(hwBlob);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/gnss/V1_0/IGnssMeasurementCallback$GnssClock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */