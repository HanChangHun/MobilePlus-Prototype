package android.hardware.gnss.V1_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

public final class PositionDebug {
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
  
  public static final ArrayList<PositionDebug> readVectorFromParcel(HwParcel paramHwParcel) {
    ArrayList<PositionDebug> arrayList = new ArrayList();
    HwBlob hwBlob1 = paramHwParcel.readBuffer(16L);
    int i = hwBlob1.getInt32(8L);
    HwBlob hwBlob2 = paramHwParcel.readEmbeddedBuffer((i * 80), hwBlob1.handle(), 0L, true);
    arrayList.clear();
    for (byte b = 0; b < i; b++) {
      PositionDebug positionDebug = new PositionDebug();
      positionDebug.readEmbeddedFromParcel(paramHwParcel, hwBlob2, (b * 80));
      arrayList.add(positionDebug);
    } 
    return arrayList;
  }
  
  public static final void writeVectorToParcel(HwParcel paramHwParcel, ArrayList<PositionDebug> paramArrayList) {
    HwBlob hwBlob1 = new HwBlob(16);
    int i = paramArrayList.size();
    hwBlob1.putInt32(8L, i);
    hwBlob1.putBool(12L, false);
    HwBlob hwBlob2 = new HwBlob(i * 80);
    for (byte b = 0; b < i; b++)
      ((PositionDebug)paramArrayList.get(b)).writeEmbeddedToBlob(hwBlob2, (b * 80)); 
    hwBlob1.putBlob(0L, hwBlob2);
    paramHwParcel.writeBuffer(hwBlob1);
  }
  
  public final boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (paramObject == null)
      return false; 
    if (paramObject.getClass() != PositionDebug.class)
      return false; 
    paramObject = paramObject;
    return (this.valid != ((PositionDebug)paramObject).valid) ? false : ((this.latitudeDegrees != ((PositionDebug)paramObject).latitudeDegrees) ? false : ((this.longitudeDegrees != ((PositionDebug)paramObject).longitudeDegrees) ? false : ((this.altitudeMeters != ((PositionDebug)paramObject).altitudeMeters) ? false : ((this.speedMetersPerSec != ((PositionDebug)paramObject).speedMetersPerSec) ? false : ((this.bearingDegrees != ((PositionDebug)paramObject).bearingDegrees) ? false : ((this.horizontalAccuracyMeters != ((PositionDebug)paramObject).horizontalAccuracyMeters) ? false : ((this.verticalAccuracyMeters != ((PositionDebug)paramObject).verticalAccuracyMeters) ? false : ((this.speedAccuracyMetersPerSecond != ((PositionDebug)paramObject).speedAccuracyMetersPerSecond) ? false : ((this.bearingAccuracyDegrees != ((PositionDebug)paramObject).bearingAccuracyDegrees) ? false : (!(this.ageSeconds != ((PositionDebug)paramObject).ageSeconds)))))))))));
  }
  
  public final int hashCode() {
    return Objects.hash(new Object[] { 
          Integer.valueOf(HidlSupport.deepHashCode(Boolean.valueOf(this.valid))), Integer.valueOf(HidlSupport.deepHashCode(Double.valueOf(this.latitudeDegrees))), Integer.valueOf(HidlSupport.deepHashCode(Double.valueOf(this.longitudeDegrees))), Integer.valueOf(HidlSupport.deepHashCode(Float.valueOf(this.altitudeMeters))), Integer.valueOf(HidlSupport.deepHashCode(Float.valueOf(this.speedMetersPerSec))), Integer.valueOf(HidlSupport.deepHashCode(Float.valueOf(this.bearingDegrees))), Integer.valueOf(HidlSupport.deepHashCode(Double.valueOf(this.horizontalAccuracyMeters))), Integer.valueOf(HidlSupport.deepHashCode(Double.valueOf(this.verticalAccuracyMeters))), Integer.valueOf(HidlSupport.deepHashCode(Double.valueOf(this.speedAccuracyMetersPerSecond))), Integer.valueOf(HidlSupport.deepHashCode(Double.valueOf(this.bearingAccuracyDegrees))), 
          Integer.valueOf(HidlSupport.deepHashCode(Float.valueOf(this.ageSeconds))) });
  }
  
  public final void readEmbeddedFromParcel(HwParcel paramHwParcel, HwBlob paramHwBlob, long paramLong) {
    this.valid = paramHwBlob.getBool(0L + paramLong);
    this.latitudeDegrees = paramHwBlob.getDouble(8L + paramLong);
    this.longitudeDegrees = paramHwBlob.getDouble(16L + paramLong);
    this.altitudeMeters = paramHwBlob.getFloat(24L + paramLong);
    this.speedMetersPerSec = paramHwBlob.getFloat(28L + paramLong);
    this.bearingDegrees = paramHwBlob.getFloat(32L + paramLong);
    this.horizontalAccuracyMeters = paramHwBlob.getDouble(40L + paramLong);
    this.verticalAccuracyMeters = paramHwBlob.getDouble(48L + paramLong);
    this.speedAccuracyMetersPerSecond = paramHwBlob.getDouble(56L + paramLong);
    this.bearingAccuracyDegrees = paramHwBlob.getDouble(64L + paramLong);
    this.ageSeconds = paramHwBlob.getFloat(72L + paramLong);
  }
  
  public final void readFromParcel(HwParcel paramHwParcel) {
    readEmbeddedFromParcel(paramHwParcel, paramHwParcel.readBuffer(80L), 0L);
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
  
  public final void writeEmbeddedToBlob(HwBlob paramHwBlob, long paramLong) {
    paramHwBlob.putBool(0L + paramLong, this.valid);
    paramHwBlob.putDouble(8L + paramLong, this.latitudeDegrees);
    paramHwBlob.putDouble(16L + paramLong, this.longitudeDegrees);
    paramHwBlob.putFloat(24L + paramLong, this.altitudeMeters);
    paramHwBlob.putFloat(28L + paramLong, this.speedMetersPerSec);
    paramHwBlob.putFloat(32L + paramLong, this.bearingDegrees);
    paramHwBlob.putDouble(40L + paramLong, this.horizontalAccuracyMeters);
    paramHwBlob.putDouble(48L + paramLong, this.verticalAccuracyMeters);
    paramHwBlob.putDouble(56L + paramLong, this.speedAccuracyMetersPerSecond);
    paramHwBlob.putDouble(64L + paramLong, this.bearingAccuracyDegrees);
    paramHwBlob.putFloat(72L + paramLong, this.ageSeconds);
  }
  
  public final void writeToParcel(HwParcel paramHwParcel) {
    HwBlob hwBlob = new HwBlob(80);
    writeEmbeddedToBlob(hwBlob, 0L);
    paramHwParcel.writeBuffer(hwBlob);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/gnss/V1_0/IGnssDebug$PositionDebug.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */