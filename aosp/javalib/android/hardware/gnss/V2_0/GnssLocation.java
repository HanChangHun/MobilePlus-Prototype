package android.hardware.gnss.V2_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

public final class GnssLocation {
  public ElapsedRealtime elapsedRealtime = new ElapsedRealtime();
  
  public android.hardware.gnss.V1_0.GnssLocation v1_0 = new android.hardware.gnss.V1_0.GnssLocation();
  
  public static final ArrayList<GnssLocation> readVectorFromParcel(HwParcel paramHwParcel) {
    ArrayList<GnssLocation> arrayList = new ArrayList();
    HwBlob hwBlob1 = paramHwParcel.readBuffer(16L);
    int i = hwBlob1.getInt32(8L);
    HwBlob hwBlob2 = paramHwParcel.readEmbeddedBuffer((i * 88), hwBlob1.handle(), 0L, true);
    arrayList.clear();
    for (byte b = 0; b < i; b++) {
      GnssLocation gnssLocation = new GnssLocation();
      gnssLocation.readEmbeddedFromParcel(paramHwParcel, hwBlob2, (b * 88));
      arrayList.add(gnssLocation);
    } 
    return arrayList;
  }
  
  public static final void writeVectorToParcel(HwParcel paramHwParcel, ArrayList<GnssLocation> paramArrayList) {
    HwBlob hwBlob1 = new HwBlob(16);
    int i = paramArrayList.size();
    hwBlob1.putInt32(8L, i);
    hwBlob1.putBool(12L, false);
    HwBlob hwBlob2 = new HwBlob(i * 88);
    for (byte b = 0; b < i; b++)
      ((GnssLocation)paramArrayList.get(b)).writeEmbeddedToBlob(hwBlob2, (b * 88)); 
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
    return !HidlSupport.deepEquals(this.v1_0, ((GnssLocation)paramObject).v1_0) ? false : (!!HidlSupport.deepEquals(this.elapsedRealtime, ((GnssLocation)paramObject).elapsedRealtime));
  }
  
  public final int hashCode() {
    return Objects.hash(new Object[] { Integer.valueOf(HidlSupport.deepHashCode(this.v1_0)), Integer.valueOf(HidlSupport.deepHashCode(this.elapsedRealtime)) });
  }
  
  public final void readEmbeddedFromParcel(HwParcel paramHwParcel, HwBlob paramHwBlob, long paramLong) {
    this.v1_0.readEmbeddedFromParcel(paramHwParcel, paramHwBlob, 0L + paramLong);
    this.elapsedRealtime.readEmbeddedFromParcel(paramHwParcel, paramHwBlob, 64L + paramLong);
  }
  
  public final void readFromParcel(HwParcel paramHwParcel) {
    readEmbeddedFromParcel(paramHwParcel, paramHwParcel.readBuffer(88L), 0L);
  }
  
  public final String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("{");
    stringBuilder.append(".v1_0 = ");
    stringBuilder.append(this.v1_0);
    stringBuilder.append(", .elapsedRealtime = ");
    stringBuilder.append(this.elapsedRealtime);
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public final void writeEmbeddedToBlob(HwBlob paramHwBlob, long paramLong) {
    this.v1_0.writeEmbeddedToBlob(paramHwBlob, 0L + paramLong);
    this.elapsedRealtime.writeEmbeddedToBlob(paramHwBlob, 64L + paramLong);
  }
  
  public final void writeToParcel(HwParcel paramHwParcel) {
    HwBlob hwBlob = new HwBlob(88);
    writeEmbeddedToBlob(hwBlob, 0L);
    paramHwParcel.writeBuffer(hwBlob);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/gnss/V2_0/GnssLocation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */