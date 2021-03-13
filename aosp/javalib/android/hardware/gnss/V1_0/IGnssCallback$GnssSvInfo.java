package android.hardware.gnss.V1_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

public final class GnssSvInfo {
  public float azimuthDegrees = 0.0F;
  
  public float cN0Dbhz = 0.0F;
  
  public float carrierFrequencyHz = 0.0F;
  
  public byte constellation = (byte)0;
  
  public float elevationDegrees = 0.0F;
  
  public byte svFlag;
  
  public short svid = (short)0;
  
  public static final ArrayList<GnssSvInfo> readVectorFromParcel(HwParcel paramHwParcel) {
    ArrayList<GnssSvInfo> arrayList = new ArrayList();
    HwBlob hwBlob1 = paramHwParcel.readBuffer(16L);
    int i = hwBlob1.getInt32(8L);
    HwBlob hwBlob2 = paramHwParcel.readEmbeddedBuffer((i * 24), hwBlob1.handle(), 0L, true);
    arrayList.clear();
    for (byte b = 0; b < i; b++) {
      GnssSvInfo gnssSvInfo = new GnssSvInfo();
      gnssSvInfo.readEmbeddedFromParcel(paramHwParcel, hwBlob2, (b * 24));
      arrayList.add(gnssSvInfo);
    } 
    return arrayList;
  }
  
  public static final void writeVectorToParcel(HwParcel paramHwParcel, ArrayList<GnssSvInfo> paramArrayList) {
    HwBlob hwBlob1 = new HwBlob(16);
    int i = paramArrayList.size();
    hwBlob1.putInt32(8L, i);
    hwBlob1.putBool(12L, false);
    HwBlob hwBlob2 = new HwBlob(i * 24);
    for (byte b = 0; b < i; b++)
      ((GnssSvInfo)paramArrayList.get(b)).writeEmbeddedToBlob(hwBlob2, (b * 24)); 
    hwBlob1.putBlob(0L, hwBlob2);
    paramHwParcel.writeBuffer(hwBlob1);
  }
  
  public final boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (paramObject == null)
      return false; 
    if (paramObject.getClass() != GnssSvInfo.class)
      return false; 
    paramObject = paramObject;
    return (this.svid != ((GnssSvInfo)paramObject).svid) ? false : ((this.constellation != ((GnssSvInfo)paramObject).constellation) ? false : ((this.cN0Dbhz != ((GnssSvInfo)paramObject).cN0Dbhz) ? false : ((this.elevationDegrees != ((GnssSvInfo)paramObject).elevationDegrees) ? false : ((this.azimuthDegrees != ((GnssSvInfo)paramObject).azimuthDegrees) ? false : ((this.carrierFrequencyHz != ((GnssSvInfo)paramObject).carrierFrequencyHz) ? false : (!!HidlSupport.deepEquals(Byte.valueOf(this.svFlag), Byte.valueOf(((GnssSvInfo)paramObject).svFlag))))))));
  }
  
  public final int hashCode() {
    return Objects.hash(new Object[] { Integer.valueOf(HidlSupport.deepHashCode(Short.valueOf(this.svid))), Integer.valueOf(HidlSupport.deepHashCode(Byte.valueOf(this.constellation))), Integer.valueOf(HidlSupport.deepHashCode(Float.valueOf(this.cN0Dbhz))), Integer.valueOf(HidlSupport.deepHashCode(Float.valueOf(this.elevationDegrees))), Integer.valueOf(HidlSupport.deepHashCode(Float.valueOf(this.azimuthDegrees))), Integer.valueOf(HidlSupport.deepHashCode(Float.valueOf(this.carrierFrequencyHz))), Integer.valueOf(HidlSupport.deepHashCode(Byte.valueOf(this.svFlag))) });
  }
  
  public final void readEmbeddedFromParcel(HwParcel paramHwParcel, HwBlob paramHwBlob, long paramLong) {
    this.svid = paramHwBlob.getInt16(0L + paramLong);
    this.constellation = paramHwBlob.getInt8(2L + paramLong);
    this.cN0Dbhz = paramHwBlob.getFloat(4L + paramLong);
    this.elevationDegrees = paramHwBlob.getFloat(8L + paramLong);
    this.azimuthDegrees = paramHwBlob.getFloat(12L + paramLong);
    this.carrierFrequencyHz = paramHwBlob.getFloat(16L + paramLong);
    this.svFlag = paramHwBlob.getInt8(20L + paramLong);
  }
  
  public final void readFromParcel(HwParcel paramHwParcel) {
    readEmbeddedFromParcel(paramHwParcel, paramHwParcel.readBuffer(24L), 0L);
  }
  
  public final String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("{");
    stringBuilder.append(".svid = ");
    stringBuilder.append(this.svid);
    stringBuilder.append(", .constellation = ");
    stringBuilder.append(GnssConstellationType.toString(this.constellation));
    stringBuilder.append(", .cN0Dbhz = ");
    stringBuilder.append(this.cN0Dbhz);
    stringBuilder.append(", .elevationDegrees = ");
    stringBuilder.append(this.elevationDegrees);
    stringBuilder.append(", .azimuthDegrees = ");
    stringBuilder.append(this.azimuthDegrees);
    stringBuilder.append(", .carrierFrequencyHz = ");
    stringBuilder.append(this.carrierFrequencyHz);
    stringBuilder.append(", .svFlag = ");
    stringBuilder.append(IGnssCallback.GnssSvFlags.dumpBitfield(this.svFlag));
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public final void writeEmbeddedToBlob(HwBlob paramHwBlob, long paramLong) {
    paramHwBlob.putInt16(0L + paramLong, this.svid);
    paramHwBlob.putInt8(2L + paramLong, this.constellation);
    paramHwBlob.putFloat(4L + paramLong, this.cN0Dbhz);
    paramHwBlob.putFloat(8L + paramLong, this.elevationDegrees);
    paramHwBlob.putFloat(12L + paramLong, this.azimuthDegrees);
    paramHwBlob.putFloat(16L + paramLong, this.carrierFrequencyHz);
    paramHwBlob.putInt8(20L + paramLong, this.svFlag);
  }
  
  public final void writeToParcel(HwParcel paramHwParcel) {
    HwBlob hwBlob = new HwBlob(24);
    writeEmbeddedToBlob(hwBlob, 0L);
    paramHwParcel.writeBuffer(hwBlob);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/gnss/V1_0/IGnssCallback$GnssSvInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */