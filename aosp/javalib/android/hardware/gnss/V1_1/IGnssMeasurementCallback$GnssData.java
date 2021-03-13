package android.hardware.gnss.V1_1;

import android.hardware.gnss.V1_0.IGnssMeasurementCallback;
import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

public final class GnssData {
  public IGnssMeasurementCallback.GnssClock clock = new IGnssMeasurementCallback.GnssClock();
  
  public ArrayList<IGnssMeasurementCallback.GnssMeasurement> measurements = new ArrayList<>();
  
  public static final ArrayList<GnssData> readVectorFromParcel(HwParcel paramHwParcel) {
    ArrayList<GnssData> arrayList = new ArrayList();
    HwBlob hwBlob = paramHwParcel.readBuffer(16L);
    int i = hwBlob.getInt32(8L);
    hwBlob = paramHwParcel.readEmbeddedBuffer((i * 88), hwBlob.handle(), 0L, true);
    arrayList.clear();
    for (byte b = 0; b < i; b++) {
      GnssData gnssData = new GnssData();
      gnssData.readEmbeddedFromParcel(paramHwParcel, hwBlob, (b * 88));
      arrayList.add(gnssData);
    } 
    return arrayList;
  }
  
  public static final void writeVectorToParcel(HwParcel paramHwParcel, ArrayList<GnssData> paramArrayList) {
    HwBlob hwBlob1 = new HwBlob(16);
    int i = paramArrayList.size();
    hwBlob1.putInt32(8L, i);
    hwBlob1.putBool(12L, false);
    HwBlob hwBlob2 = new HwBlob(i * 88);
    for (byte b = 0; b < i; b++)
      ((GnssData)paramArrayList.get(b)).writeEmbeddedToBlob(hwBlob2, (b * 88)); 
    hwBlob1.putBlob(0L, hwBlob2);
    paramHwParcel.writeBuffer(hwBlob1);
  }
  
  public final boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (paramObject == null)
      return false; 
    if (paramObject.getClass() != GnssData.class)
      return false; 
    paramObject = paramObject;
    return !HidlSupport.deepEquals(this.measurements, ((GnssData)paramObject).measurements) ? false : (!!HidlSupport.deepEquals(this.clock, ((GnssData)paramObject).clock));
  }
  
  public final int hashCode() {
    return Objects.hash(new Object[] { Integer.valueOf(HidlSupport.deepHashCode(this.measurements)), Integer.valueOf(HidlSupport.deepHashCode(this.clock)) });
  }
  
  public final void readEmbeddedFromParcel(HwParcel paramHwParcel, HwBlob paramHwBlob, long paramLong) {
    int i = paramHwBlob.getInt32(paramLong + 0L + 8L);
    HwBlob hwBlob = paramHwParcel.readEmbeddedBuffer((i * 152), paramHwBlob.handle(), paramLong + 0L + 0L, true);
    this.measurements.clear();
    for (byte b = 0; b < i; b++) {
      IGnssMeasurementCallback.GnssMeasurement gnssMeasurement = new IGnssMeasurementCallback.GnssMeasurement();
      gnssMeasurement.readEmbeddedFromParcel(paramHwParcel, hwBlob, (b * 152));
      this.measurements.add(gnssMeasurement);
    } 
    this.clock.readEmbeddedFromParcel(paramHwParcel, paramHwBlob, paramLong + 16L);
  }
  
  public final void readFromParcel(HwParcel paramHwParcel) {
    readEmbeddedFromParcel(paramHwParcel, paramHwParcel.readBuffer(88L), 0L);
  }
  
  public final String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("{");
    stringBuilder.append(".measurements = ");
    stringBuilder.append(this.measurements);
    stringBuilder.append(", .clock = ");
    stringBuilder.append(this.clock);
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public final void writeEmbeddedToBlob(HwBlob paramHwBlob, long paramLong) {
    int i = this.measurements.size();
    paramHwBlob.putInt32(paramLong + 0L + 8L, i);
    paramHwBlob.putBool(paramLong + 0L + 12L, false);
    HwBlob hwBlob = new HwBlob(i * 152);
    for (byte b = 0; b < i; b++)
      ((IGnssMeasurementCallback.GnssMeasurement)this.measurements.get(b)).writeEmbeddedToBlob(hwBlob, (b * 152)); 
    paramHwBlob.putBlob(paramLong + 0L + 0L, hwBlob);
    this.clock.writeEmbeddedToBlob(paramHwBlob, 16L + paramLong);
  }
  
  public final void writeToParcel(HwParcel paramHwParcel) {
    HwBlob hwBlob = new HwBlob(88);
    writeEmbeddedToBlob(hwBlob, 0L);
    paramHwParcel.writeBuffer(hwBlob);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/gnss/V1_1/IGnssMeasurementCallback$GnssData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */