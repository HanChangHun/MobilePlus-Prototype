package android.hardware.gnss.V2_1;

import android.hardware.gnss.V2_0.GnssConstellationType;
import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

public final class GnssSignalType {
  public double carrierFrequencyHz = 0.0D;
  
  public String codeType = new String();
  
  public byte constellation = (byte)0;
  
  public static final ArrayList<GnssSignalType> readVectorFromParcel(HwParcel paramHwParcel) {
    ArrayList<GnssSignalType> arrayList = new ArrayList();
    HwBlob hwBlob1 = paramHwParcel.readBuffer(16L);
    int i = hwBlob1.getInt32(8L);
    HwBlob hwBlob2 = paramHwParcel.readEmbeddedBuffer((i * 32), hwBlob1.handle(), 0L, true);
    arrayList.clear();
    for (byte b = 0; b < i; b++) {
      GnssSignalType gnssSignalType = new GnssSignalType();
      gnssSignalType.readEmbeddedFromParcel(paramHwParcel, hwBlob2, (b * 32));
      arrayList.add(gnssSignalType);
    } 
    return arrayList;
  }
  
  public static final void writeVectorToParcel(HwParcel paramHwParcel, ArrayList<GnssSignalType> paramArrayList) {
    HwBlob hwBlob1 = new HwBlob(16);
    int i = paramArrayList.size();
    hwBlob1.putInt32(8L, i);
    hwBlob1.putBool(12L, false);
    HwBlob hwBlob2 = new HwBlob(i * 32);
    for (byte b = 0; b < i; b++)
      ((GnssSignalType)paramArrayList.get(b)).writeEmbeddedToBlob(hwBlob2, (b * 32)); 
    hwBlob1.putBlob(0L, hwBlob2);
    paramHwParcel.writeBuffer(hwBlob1);
  }
  
  public final boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (paramObject == null)
      return false; 
    if (paramObject.getClass() != GnssSignalType.class)
      return false; 
    paramObject = paramObject;
    return (this.constellation != ((GnssSignalType)paramObject).constellation) ? false : ((this.carrierFrequencyHz != ((GnssSignalType)paramObject).carrierFrequencyHz) ? false : (!!HidlSupport.deepEquals(this.codeType, ((GnssSignalType)paramObject).codeType)));
  }
  
  public final int hashCode() {
    return Objects.hash(new Object[] { Integer.valueOf(HidlSupport.deepHashCode(Byte.valueOf(this.constellation))), Integer.valueOf(HidlSupport.deepHashCode(Double.valueOf(this.carrierFrequencyHz))), Integer.valueOf(HidlSupport.deepHashCode(this.codeType)) });
  }
  
  public final void readEmbeddedFromParcel(HwParcel paramHwParcel, HwBlob paramHwBlob, long paramLong) {
    this.constellation = paramHwBlob.getInt8(paramLong + 0L);
    this.carrierFrequencyHz = paramHwBlob.getDouble(paramLong + 8L);
    String str = paramHwBlob.getString(paramLong + 16L);
    this.codeType = str;
    paramHwParcel.readEmbeddedBuffer(((str.getBytes()).length + 1), paramHwBlob.handle(), paramLong + 16L + 0L, false);
  }
  
  public final void readFromParcel(HwParcel paramHwParcel) {
    readEmbeddedFromParcel(paramHwParcel, paramHwParcel.readBuffer(32L), 0L);
  }
  
  public final String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("{");
    stringBuilder.append(".constellation = ");
    stringBuilder.append(GnssConstellationType.toString(this.constellation));
    stringBuilder.append(", .carrierFrequencyHz = ");
    stringBuilder.append(this.carrierFrequencyHz);
    stringBuilder.append(", .codeType = ");
    stringBuilder.append(this.codeType);
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public final void writeEmbeddedToBlob(HwBlob paramHwBlob, long paramLong) {
    paramHwBlob.putInt8(0L + paramLong, this.constellation);
    paramHwBlob.putDouble(8L + paramLong, this.carrierFrequencyHz);
    paramHwBlob.putString(16L + paramLong, this.codeType);
  }
  
  public final void writeToParcel(HwParcel paramHwParcel) {
    HwBlob hwBlob = new HwBlob(32);
    writeEmbeddedToBlob(hwBlob, 0L);
    paramHwParcel.writeBuffer(hwBlob);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/gnss/V2_1/GnssSignalType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */