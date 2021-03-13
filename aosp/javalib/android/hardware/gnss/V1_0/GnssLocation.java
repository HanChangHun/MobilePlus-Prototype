package android.hardware.gnss.V1_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

public final class GnssLocation {
  public double altitudeMeters = 0.0D;
  
  public float bearingAccuracyDegrees = 0.0F;
  
  public float bearingDegrees = 0.0F;
  
  public short gnssLocationFlags;
  
  public float horizontalAccuracyMeters = 0.0F;
  
  public double latitudeDegrees = 0.0D;
  
  public double longitudeDegrees = 0.0D;
  
  public float speedAccuracyMetersPerSecond = 0.0F;
  
  public float speedMetersPerSec = 0.0F;
  
  public long timestamp = 0L;
  
  public float verticalAccuracyMeters = 0.0F;
  
  public static final ArrayList<GnssLocation> readVectorFromParcel(HwParcel paramHwParcel) {
    ArrayList<GnssLocation> arrayList = new ArrayList();
    HwBlob hwBlob1 = paramHwParcel.readBuffer(16L);
    int i = hwBlob1.getInt32(8L);
    HwBlob hwBlob2 = paramHwParcel.readEmbeddedBuffer((i * 64), hwBlob1.handle(), 0L, true);
    arrayList.clear();
    for (byte b = 0; b < i; b++) {
      GnssLocation gnssLocation = new GnssLocation();
      gnssLocation.readEmbeddedFromParcel(paramHwParcel, hwBlob2, (b * 64));
      arrayList.add(gnssLocation);
    } 
    return arrayList;
  }
  
  public static final void writeVectorToParcel(HwParcel paramHwParcel, ArrayList<GnssLocation> paramArrayList) {
    HwBlob hwBlob1 = new HwBlob(16);
    int i = paramArrayList.size();
    hwBlob1.putInt32(8L, i);
    hwBlob1.putBool(12L, false);
    HwBlob hwBlob2 = new HwBlob(i * 64);
    for (byte b = 0; b < i; b++)
      ((GnssLocation)paramArrayList.get(b)).writeEmbeddedToBlob(hwBlob2, (b * 64)); 
    hwBlob1.putBlob(0L, hwBlob2);
    paramHwParcel.writeBuffer(hwBlob1);
  }
  
  public final boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (paramObject == null)
      return false; 
    if (paramObject.getClass() != GnssLocation.class)
      return false; 
    paramObject = paramObject;
    return !HidlSupport.deepEquals(Short.valueOf(this.gnssLocationFlags), Short.valueOf(((GnssLocation)paramObject).gnssLocationFlags)) ? false : ((this.latitudeDegrees != ((GnssLocation)paramObject).latitudeDegrees) ? false : ((this.longitudeDegrees != ((GnssLocation)paramObject).longitudeDegrees) ? false : ((this.altitudeMeters != ((GnssLocation)paramObject).altitudeMeters) ? false : ((this.speedMetersPerSec != ((GnssLocation)paramObject).speedMetersPerSec) ? false : ((this.bearingDegrees != ((GnssLocation)paramObject).bearingDegrees) ? false : ((this.horizontalAccuracyMeters != ((GnssLocation)paramObject).horizontalAccuracyMeters) ? false : ((this.verticalAccuracyMeters != ((GnssLocation)paramObject).verticalAccuracyMeters) ? false : ((this.speedAccuracyMetersPerSecond != ((GnssLocation)paramObject).speedAccuracyMetersPerSecond) ? false : ((this.bearingAccuracyDegrees != ((GnssLocation)paramObject).bearingAccuracyDegrees) ? false : (!(this.timestamp != ((GnssLocation)paramObject).timestamp)))))))))));
  }
  
  public final int hashCode() {
    return Objects.hash(new Object[] { 
          Integer.valueOf(HidlSupport.deepHashCode(Short.valueOf(this.gnssLocationFlags))), Integer.valueOf(HidlSupport.deepHashCode(Double.valueOf(this.latitudeDegrees))), Integer.valueOf(HidlSupport.deepHashCode(Double.valueOf(this.longitudeDegrees))), Integer.valueOf(HidlSupport.deepHashCode(Double.valueOf(this.altitudeMeters))), Integer.valueOf(HidlSupport.deepHashCode(Float.valueOf(this.speedMetersPerSec))), Integer.valueOf(HidlSupport.deepHashCode(Float.valueOf(this.bearingDegrees))), Integer.valueOf(HidlSupport.deepHashCode(Float.valueOf(this.horizontalAccuracyMeters))), Integer.valueOf(HidlSupport.deepHashCode(Float.valueOf(this.verticalAccuracyMeters))), Integer.valueOf(HidlSupport.deepHashCode(Float.valueOf(this.speedAccuracyMetersPerSecond))), Integer.valueOf(HidlSupport.deepHashCode(Float.valueOf(this.bearingAccuracyDegrees))), 
          Integer.valueOf(HidlSupport.deepHashCode(Long.valueOf(this.timestamp))) });
  }
  
  public final void readEmbeddedFromParcel(HwParcel paramHwParcel, HwBlob paramHwBlob, long paramLong) {
    this.gnssLocationFlags = paramHwBlob.getInt16(0L + paramLong);
    this.latitudeDegrees = paramHwBlob.getDouble(8L + paramLong);
    this.longitudeDegrees = paramHwBlob.getDouble(16L + paramLong);
    this.altitudeMeters = paramHwBlob.getDouble(24L + paramLong);
    this.speedMetersPerSec = paramHwBlob.getFloat(32L + paramLong);
    this.bearingDegrees = paramHwBlob.getFloat(36L + paramLong);
    this.horizontalAccuracyMeters = paramHwBlob.getFloat(40L + paramLong);
    this.verticalAccuracyMeters = paramHwBlob.getFloat(44L + paramLong);
    this.speedAccuracyMetersPerSecond = paramHwBlob.getFloat(48L + paramLong);
    this.bearingAccuracyDegrees = paramHwBlob.getFloat(52L + paramLong);
    this.timestamp = paramHwBlob.getInt64(56L + paramLong);
  }
  
  public final void readFromParcel(HwParcel paramHwParcel) {
    readEmbeddedFromParcel(paramHwParcel, paramHwParcel.readBuffer(64L), 0L);
  }
  
  public final String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("{");
    stringBuilder.append(".gnssLocationFlags = ");
    stringBuilder.append(GnssLocationFlags.dumpBitfield(this.gnssLocationFlags));
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
    stringBuilder.append(", .timestamp = ");
    stringBuilder.append(this.timestamp);
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public final void writeEmbeddedToBlob(HwBlob paramHwBlob, long paramLong) {
    paramHwBlob.putInt16(0L + paramLong, this.gnssLocationFlags);
    paramHwBlob.putDouble(8L + paramLong, this.latitudeDegrees);
    paramHwBlob.putDouble(16L + paramLong, this.longitudeDegrees);
    paramHwBlob.putDouble(24L + paramLong, this.altitudeMeters);
    paramHwBlob.putFloat(32L + paramLong, this.speedMetersPerSec);
    paramHwBlob.putFloat(36L + paramLong, this.bearingDegrees);
    paramHwBlob.putFloat(40L + paramLong, this.horizontalAccuracyMeters);
    paramHwBlob.putFloat(44L + paramLong, this.verticalAccuracyMeters);
    paramHwBlob.putFloat(48L + paramLong, this.speedAccuracyMetersPerSecond);
    paramHwBlob.putFloat(52L + paramLong, this.bearingAccuracyDegrees);
    paramHwBlob.putInt64(56L + paramLong, this.timestamp);
  }
  
  public final void writeToParcel(HwParcel paramHwParcel) {
    HwBlob hwBlob = new HwBlob(64);
    writeEmbeddedToBlob(hwBlob, 0L);
    paramHwParcel.writeBuffer(hwBlob);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/gnss/V1_0/GnssLocation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */