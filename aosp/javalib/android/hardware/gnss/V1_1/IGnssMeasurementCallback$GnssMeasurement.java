package android.hardware.gnss.V1_1;

import android.hardware.gnss.V1_0.IGnssMeasurementCallback;
import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

public final class GnssMeasurement {
  public short accumulatedDeltaRangeState;
  
  public IGnssMeasurementCallback.GnssMeasurement v1_0 = new IGnssMeasurementCallback.GnssMeasurement();
  
  public static final ArrayList<GnssMeasurement> readVectorFromParcel(HwParcel paramHwParcel) {
    ArrayList<GnssMeasurement> arrayList = new ArrayList();
    HwBlob hwBlob1 = paramHwParcel.readBuffer(16L);
    int i = hwBlob1.getInt32(8L);
    HwBlob hwBlob2 = paramHwParcel.readEmbeddedBuffer((i * 152), hwBlob1.handle(), 0L, true);
    arrayList.clear();
    for (byte b = 0; b < i; b++) {
      GnssMeasurement gnssMeasurement = new GnssMeasurement();
      gnssMeasurement.readEmbeddedFromParcel(paramHwParcel, hwBlob2, (b * 152));
      arrayList.add(gnssMeasurement);
    } 
    return arrayList;
  }
  
  public static final void writeVectorToParcel(HwParcel paramHwParcel, ArrayList<GnssMeasurement> paramArrayList) {
    HwBlob hwBlob1 = new HwBlob(16);
    int i = paramArrayList.size();
    hwBlob1.putInt32(8L, i);
    hwBlob1.putBool(12L, false);
    HwBlob hwBlob2 = new HwBlob(i * 152);
    for (byte b = 0; b < i; b++)
      ((GnssMeasurement)paramArrayList.get(b)).writeEmbeddedToBlob(hwBlob2, (b * 152)); 
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
    return !HidlSupport.deepEquals(this.v1_0, ((GnssMeasurement)paramObject).v1_0) ? false : (!!HidlSupport.deepEquals(Short.valueOf(this.accumulatedDeltaRangeState), Short.valueOf(((GnssMeasurement)paramObject).accumulatedDeltaRangeState)));
  }
  
  public final int hashCode() {
    return Objects.hash(new Object[] { Integer.valueOf(HidlSupport.deepHashCode(this.v1_0)), Integer.valueOf(HidlSupport.deepHashCode(Short.valueOf(this.accumulatedDeltaRangeState))) });
  }
  
  public final void readEmbeddedFromParcel(HwParcel paramHwParcel, HwBlob paramHwBlob, long paramLong) {
    this.v1_0.readEmbeddedFromParcel(paramHwParcel, paramHwBlob, 0L + paramLong);
    this.accumulatedDeltaRangeState = paramHwBlob.getInt16(144L + paramLong);
  }
  
  public final void readFromParcel(HwParcel paramHwParcel) {
    readEmbeddedFromParcel(paramHwParcel, paramHwParcel.readBuffer(152L), 0L);
  }
  
  public final String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("{");
    stringBuilder.append(".v1_0 = ");
    stringBuilder.append(this.v1_0);
    stringBuilder.append(", .accumulatedDeltaRangeState = ");
    stringBuilder.append(IGnssMeasurementCallback.GnssAccumulatedDeltaRangeState.dumpBitfield(this.accumulatedDeltaRangeState));
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public final void writeEmbeddedToBlob(HwBlob paramHwBlob, long paramLong) {
    this.v1_0.writeEmbeddedToBlob(paramHwBlob, 0L + paramLong);
    paramHwBlob.putInt16(144L + paramLong, this.accumulatedDeltaRangeState);
  }
  
  public final void writeToParcel(HwParcel paramHwParcel) {
    HwBlob hwBlob = new HwBlob(152);
    writeEmbeddedToBlob(hwBlob, 0L);
    paramHwParcel.writeBuffer(hwBlob);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/gnss/V1_1/IGnssMeasurementCallback$GnssMeasurement.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */