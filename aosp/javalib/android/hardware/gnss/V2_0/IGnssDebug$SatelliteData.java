package android.hardware.gnss.V2_0;

import android.hardware.gnss.V1_0.IGnssDebug;
import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

public final class SatelliteData {
  public byte constellation = (byte)0;
  
  public IGnssDebug.SatelliteData v1_0 = new IGnssDebug.SatelliteData();
  
  public static final ArrayList<SatelliteData> readVectorFromParcel(HwParcel paramHwParcel) {
    ArrayList<SatelliteData> arrayList = new ArrayList();
    HwBlob hwBlob1 = paramHwParcel.readBuffer(16L);
    int i = hwBlob1.getInt32(8L);
    HwBlob hwBlob2 = paramHwParcel.readEmbeddedBuffer((i * 24), hwBlob1.handle(), 0L, true);
    arrayList.clear();
    for (byte b = 0; b < i; b++) {
      SatelliteData satelliteData = new SatelliteData();
      satelliteData.readEmbeddedFromParcel(paramHwParcel, hwBlob2, (b * 24));
      arrayList.add(satelliteData);
    } 
    return arrayList;
  }
  
  public static final void writeVectorToParcel(HwParcel paramHwParcel, ArrayList<SatelliteData> paramArrayList) {
    HwBlob hwBlob1 = new HwBlob(16);
    int i = paramArrayList.size();
    hwBlob1.putInt32(8L, i);
    hwBlob1.putBool(12L, false);
    HwBlob hwBlob2 = new HwBlob(i * 24);
    for (byte b = 0; b < i; b++)
      ((SatelliteData)paramArrayList.get(b)).writeEmbeddedToBlob(hwBlob2, (b * 24)); 
    hwBlob1.putBlob(0L, hwBlob2);
    paramHwParcel.writeBuffer(hwBlob1);
  }
  
  public final boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (paramObject == null)
      return false; 
    if (paramObject.getClass() != SatelliteData.class)
      return false; 
    paramObject = paramObject;
    return !HidlSupport.deepEquals(this.v1_0, ((SatelliteData)paramObject).v1_0) ? false : (!(this.constellation != ((SatelliteData)paramObject).constellation));
  }
  
  public final int hashCode() {
    return Objects.hash(new Object[] { Integer.valueOf(HidlSupport.deepHashCode(this.v1_0)), Integer.valueOf(HidlSupport.deepHashCode(Byte.valueOf(this.constellation))) });
  }
  
  public final void readEmbeddedFromParcel(HwParcel paramHwParcel, HwBlob paramHwBlob, long paramLong) {
    this.v1_0.readEmbeddedFromParcel(paramHwParcel, paramHwBlob, 0L + paramLong);
    this.constellation = paramHwBlob.getInt8(20L + paramLong);
  }
  
  public final void readFromParcel(HwParcel paramHwParcel) {
    readEmbeddedFromParcel(paramHwParcel, paramHwParcel.readBuffer(24L), 0L);
  }
  
  public final String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("{");
    stringBuilder.append(".v1_0 = ");
    stringBuilder.append(this.v1_0);
    stringBuilder.append(", .constellation = ");
    stringBuilder.append(GnssConstellationType.toString(this.constellation));
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public final void writeEmbeddedToBlob(HwBlob paramHwBlob, long paramLong) {
    this.v1_0.writeEmbeddedToBlob(paramHwBlob, 0L + paramLong);
    paramHwBlob.putInt8(20L + paramLong, this.constellation);
  }
  
  public final void writeToParcel(HwParcel paramHwParcel) {
    HwBlob hwBlob = new HwBlob(24);
    writeEmbeddedToBlob(hwBlob, 0L);
    paramHwParcel.writeBuffer(hwBlob);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/gnss/V2_0/IGnssDebug$SatelliteData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */