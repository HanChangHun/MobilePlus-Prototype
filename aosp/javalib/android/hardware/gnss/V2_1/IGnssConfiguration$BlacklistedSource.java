package android.hardware.gnss.V2_1;

import android.hardware.gnss.V2_0.GnssConstellationType;
import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

public final class BlacklistedSource {
  public byte constellation = (byte)0;
  
  public short svid = (short)0;
  
  public static final ArrayList<BlacklistedSource> readVectorFromParcel(HwParcel paramHwParcel) {
    ArrayList<BlacklistedSource> arrayList = new ArrayList();
    HwBlob hwBlob = paramHwParcel.readBuffer(16L);
    int i = hwBlob.getInt32(8L);
    hwBlob = paramHwParcel.readEmbeddedBuffer((i * 4), hwBlob.handle(), 0L, true);
    arrayList.clear();
    for (byte b = 0; b < i; b++) {
      BlacklistedSource blacklistedSource = new BlacklistedSource();
      blacklistedSource.readEmbeddedFromParcel(paramHwParcel, hwBlob, (b * 4));
      arrayList.add(blacklistedSource);
    } 
    return arrayList;
  }
  
  public static final void writeVectorToParcel(HwParcel paramHwParcel, ArrayList<BlacklistedSource> paramArrayList) {
    HwBlob hwBlob1 = new HwBlob(16);
    int i = paramArrayList.size();
    hwBlob1.putInt32(8L, i);
    hwBlob1.putBool(12L, false);
    HwBlob hwBlob2 = new HwBlob(i * 4);
    for (byte b = 0; b < i; b++)
      ((BlacklistedSource)paramArrayList.get(b)).writeEmbeddedToBlob(hwBlob2, (b * 4)); 
    hwBlob1.putBlob(0L, hwBlob2);
    paramHwParcel.writeBuffer(hwBlob1);
  }
  
  public final boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (paramObject == null)
      return false; 
    if (paramObject.getClass() != BlacklistedSource.class)
      return false; 
    paramObject = paramObject;
    return (this.constellation != ((BlacklistedSource)paramObject).constellation) ? false : (!(this.svid != ((BlacklistedSource)paramObject).svid));
  }
  
  public final int hashCode() {
    return Objects.hash(new Object[] { Integer.valueOf(HidlSupport.deepHashCode(Byte.valueOf(this.constellation))), Integer.valueOf(HidlSupport.deepHashCode(Short.valueOf(this.svid))) });
  }
  
  public final void readEmbeddedFromParcel(HwParcel paramHwParcel, HwBlob paramHwBlob, long paramLong) {
    this.constellation = paramHwBlob.getInt8(0L + paramLong);
    this.svid = paramHwBlob.getInt16(2L + paramLong);
  }
  
  public final void readFromParcel(HwParcel paramHwParcel) {
    readEmbeddedFromParcel(paramHwParcel, paramHwParcel.readBuffer(4L), 0L);
  }
  
  public final String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("{");
    stringBuilder.append(".constellation = ");
    stringBuilder.append(GnssConstellationType.toString(this.constellation));
    stringBuilder.append(", .svid = ");
    stringBuilder.append(this.svid);
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public final void writeEmbeddedToBlob(HwBlob paramHwBlob, long paramLong) {
    paramHwBlob.putInt8(0L + paramLong, this.constellation);
    paramHwBlob.putInt16(2L + paramLong, this.svid);
  }
  
  public final void writeToParcel(HwParcel paramHwParcel) {
    HwBlob hwBlob = new HwBlob(4);
    writeEmbeddedToBlob(hwBlob, 0L);
    paramHwParcel.writeBuffer(hwBlob);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/gnss/V2_1/IGnssConfiguration$BlacklistedSource.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */