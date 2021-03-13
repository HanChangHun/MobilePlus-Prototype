package android.hardware.gnss.V1_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public final class GnssData {
  public IGnssMeasurementCallback.GnssClock clock = new IGnssMeasurementCallback.GnssClock();
  
  public int measurementCount = 0;
  
  public IGnssMeasurementCallback.GnssMeasurement[] measurements = new IGnssMeasurementCallback.GnssMeasurement[64];
  
  public static final ArrayList<GnssData> readVectorFromParcel(HwParcel paramHwParcel) {
    ArrayList<GnssData> arrayList = new ArrayList();
    HwBlob hwBlob1 = paramHwParcel.readBuffer(16L);
    int i = hwBlob1.getInt32(8L);
    HwBlob hwBlob2 = paramHwParcel.readEmbeddedBuffer((i * 9296), hwBlob1.handle(), 0L, true);
    arrayList.clear();
    for (byte b = 0; b < i; b++) {
      GnssData gnssData = new GnssData();
      gnssData.readEmbeddedFromParcel(paramHwParcel, hwBlob2, (b * 9296));
      arrayList.add(gnssData);
    } 
    return arrayList;
  }
  
  public static final void writeVectorToParcel(HwParcel paramHwParcel, ArrayList<GnssData> paramArrayList) {
    HwBlob hwBlob1 = new HwBlob(16);
    int i = paramArrayList.size();
    hwBlob1.putInt32(8L, i);
    hwBlob1.putBool(12L, false);
    HwBlob hwBlob2 = new HwBlob(i * 9296);
    for (byte b = 0; b < i; b++)
      ((GnssData)paramArrayList.get(b)).writeEmbeddedToBlob(hwBlob2, (b * 9296)); 
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
    return (this.measurementCount != ((GnssData)paramObject).measurementCount) ? false : (!HidlSupport.deepEquals(this.measurements, ((GnssData)paramObject).measurements) ? false : (!!HidlSupport.deepEquals(this.clock, ((GnssData)paramObject).clock)));
  }
  
  public final int hashCode() {
    return Objects.hash(new Object[] { Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.measurementCount))), Integer.valueOf(HidlSupport.deepHashCode(this.measurements)), Integer.valueOf(HidlSupport.deepHashCode(this.clock)) });
  }
  
  public final void readEmbeddedFromParcel(HwParcel paramHwParcel, HwBlob paramHwBlob, long paramLong) {
    this.measurementCount = paramHwBlob.getInt32(0L + paramLong);
    long l = 8L + paramLong;
    for (byte b = 0; b < 64; b++) {
      this.measurements[b] = new IGnssMeasurementCallback.GnssMeasurement();
      this.measurements[b].readEmbeddedFromParcel(paramHwParcel, paramHwBlob, l);
      l += 144L;
    } 
    this.clock.readEmbeddedFromParcel(paramHwParcel, paramHwBlob, 9224L + paramLong);
  }
  
  public final void readFromParcel(HwParcel paramHwParcel) {
    readEmbeddedFromParcel(paramHwParcel, paramHwParcel.readBuffer(9296L), 0L);
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
  
  public final void writeEmbeddedToBlob(HwBlob paramHwBlob, long paramLong) {
    paramHwBlob.putInt32(0L + paramLong, this.measurementCount);
    long l = 8L + paramLong;
    for (byte b = 0; b < 64; b++) {
      this.measurements[b].writeEmbeddedToBlob(paramHwBlob, l);
      l += 144L;
    } 
    this.clock.writeEmbeddedToBlob(paramHwBlob, 9224L + paramLong);
  }
  
  public final void writeToParcel(HwParcel paramHwParcel) {
    HwBlob hwBlob = new HwBlob(9296);
    writeEmbeddedToBlob(hwBlob, 0L);
    paramHwParcel.writeBuffer(hwBlob);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/gnss/V1_0/IGnssMeasurementCallback$GnssData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */