package android.hardware.gnss.V1_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

public final class SatelliteData {
  public byte constellation = (byte)0;
  
  public float ephemerisAgeSeconds = 0.0F;
  
  public byte ephemerisHealth = (byte)0;
  
  public byte ephemerisSource = (byte)0;
  
  public byte ephemerisType = (byte)0;
  
  public float serverPredictionAgeSeconds = 0.0F;
  
  public boolean serverPredictionIsAvailable = false;
  
  public short svid = (short)0;
  
  public static final ArrayList<SatelliteData> readVectorFromParcel(HwParcel paramHwParcel) {
    ArrayList<SatelliteData> arrayList = new ArrayList();
    HwBlob hwBlob1 = paramHwParcel.readBuffer(16L);
    int i = hwBlob1.getInt32(8L);
    HwBlob hwBlob2 = paramHwParcel.readEmbeddedBuffer((i * 20), hwBlob1.handle(), 0L, true);
    arrayList.clear();
    for (byte b = 0; b < i; b++) {
      SatelliteData satelliteData = new SatelliteData();
      satelliteData.readEmbeddedFromParcel(paramHwParcel, hwBlob2, (b * 20));
      arrayList.add(satelliteData);
    } 
    return arrayList;
  }
  
  public static final void writeVectorToParcel(HwParcel paramHwParcel, ArrayList<SatelliteData> paramArrayList) {
    HwBlob hwBlob1 = new HwBlob(16);
    int i = paramArrayList.size();
    hwBlob1.putInt32(8L, i);
    hwBlob1.putBool(12L, false);
    HwBlob hwBlob2 = new HwBlob(i * 20);
    for (byte b = 0; b < i; b++)
      ((SatelliteData)paramArrayList.get(b)).writeEmbeddedToBlob(hwBlob2, (b * 20)); 
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
    return (this.svid != ((SatelliteData)paramObject).svid) ? false : ((this.constellation != ((SatelliteData)paramObject).constellation) ? false : ((this.ephemerisType != ((SatelliteData)paramObject).ephemerisType) ? false : ((this.ephemerisSource != ((SatelliteData)paramObject).ephemerisSource) ? false : ((this.ephemerisHealth != ((SatelliteData)paramObject).ephemerisHealth) ? false : ((this.ephemerisAgeSeconds != ((SatelliteData)paramObject).ephemerisAgeSeconds) ? false : ((this.serverPredictionIsAvailable != ((SatelliteData)paramObject).serverPredictionIsAvailable) ? false : (!(this.serverPredictionAgeSeconds != ((SatelliteData)paramObject).serverPredictionAgeSeconds))))))));
  }
  
  public final int hashCode() {
    return Objects.hash(new Object[] { Integer.valueOf(HidlSupport.deepHashCode(Short.valueOf(this.svid))), Integer.valueOf(HidlSupport.deepHashCode(Byte.valueOf(this.constellation))), Integer.valueOf(HidlSupport.deepHashCode(Byte.valueOf(this.ephemerisType))), Integer.valueOf(HidlSupport.deepHashCode(Byte.valueOf(this.ephemerisSource))), Integer.valueOf(HidlSupport.deepHashCode(Byte.valueOf(this.ephemerisHealth))), Integer.valueOf(HidlSupport.deepHashCode(Float.valueOf(this.ephemerisAgeSeconds))), Integer.valueOf(HidlSupport.deepHashCode(Boolean.valueOf(this.serverPredictionIsAvailable))), Integer.valueOf(HidlSupport.deepHashCode(Float.valueOf(this.serverPredictionAgeSeconds))) });
  }
  
  public final void readEmbeddedFromParcel(HwParcel paramHwParcel, HwBlob paramHwBlob, long paramLong) {
    this.svid = paramHwBlob.getInt16(0L + paramLong);
    this.constellation = paramHwBlob.getInt8(2L + paramLong);
    this.ephemerisType = paramHwBlob.getInt8(3L + paramLong);
    this.ephemerisSource = paramHwBlob.getInt8(4L + paramLong);
    this.ephemerisHealth = paramHwBlob.getInt8(5L + paramLong);
    this.ephemerisAgeSeconds = paramHwBlob.getFloat(8L + paramLong);
    this.serverPredictionIsAvailable = paramHwBlob.getBool(12L + paramLong);
    this.serverPredictionAgeSeconds = paramHwBlob.getFloat(16L + paramLong);
  }
  
  public final void readFromParcel(HwParcel paramHwParcel) {
    readEmbeddedFromParcel(paramHwParcel, paramHwParcel.readBuffer(20L), 0L);
  }
  
  public final String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("{");
    stringBuilder.append(".svid = ");
    stringBuilder.append(this.svid);
    stringBuilder.append(", .constellation = ");
    stringBuilder.append(GnssConstellationType.toString(this.constellation));
    stringBuilder.append(", .ephemerisType = ");
    stringBuilder.append(IGnssDebug.SatelliteEphemerisType.toString(this.ephemerisType));
    stringBuilder.append(", .ephemerisSource = ");
    stringBuilder.append(IGnssDebug.SatelliteEphemerisSource.toString(this.ephemerisSource));
    stringBuilder.append(", .ephemerisHealth = ");
    stringBuilder.append(IGnssDebug.SatelliteEphemerisHealth.toString(this.ephemerisHealth));
    stringBuilder.append(", .ephemerisAgeSeconds = ");
    stringBuilder.append(this.ephemerisAgeSeconds);
    stringBuilder.append(", .serverPredictionIsAvailable = ");
    stringBuilder.append(this.serverPredictionIsAvailable);
    stringBuilder.append(", .serverPredictionAgeSeconds = ");
    stringBuilder.append(this.serverPredictionAgeSeconds);
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public final void writeEmbeddedToBlob(HwBlob paramHwBlob, long paramLong) {
    paramHwBlob.putInt16(0L + paramLong, this.svid);
    paramHwBlob.putInt8(2L + paramLong, this.constellation);
    paramHwBlob.putInt8(3L + paramLong, this.ephemerisType);
    paramHwBlob.putInt8(4L + paramLong, this.ephemerisSource);
    paramHwBlob.putInt8(5L + paramLong, this.ephemerisHealth);
    paramHwBlob.putFloat(8L + paramLong, this.ephemerisAgeSeconds);
    paramHwBlob.putBool(12L + paramLong, this.serverPredictionIsAvailable);
    paramHwBlob.putFloat(16L + paramLong, this.serverPredictionAgeSeconds);
  }
  
  public final void writeToParcel(HwParcel paramHwParcel) {
    HwBlob hwBlob = new HwBlob(20);
    writeEmbeddedToBlob(hwBlob, 0L);
    paramHwParcel.writeBuffer(hwBlob);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/gnss/V1_0/IGnssDebug$SatelliteData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */