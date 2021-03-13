package android.hardware.gnss.V2_0;

import android.hardware.gnss.V1_0.IGnssCallback;
import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

public final class GnssSvInfo {
  public byte constellation = (byte)0;
  
  public IGnssCallback.GnssSvInfo v1_0 = new IGnssCallback.GnssSvInfo();
  
  public static final ArrayList<GnssSvInfo> readVectorFromParcel(HwParcel paramHwParcel) {
    ArrayList<GnssSvInfo> arrayList = new ArrayList();
    HwBlob hwBlob = paramHwParcel.readBuffer(16L);
    int i = hwBlob.getInt32(8L);
    hwBlob = paramHwParcel.readEmbeddedBuffer((i * 28), hwBlob.handle(), 0L, true);
    arrayList.clear();
    for (byte b = 0; b < i; b++) {
      GnssSvInfo gnssSvInfo = new GnssSvInfo();
      gnssSvInfo.readEmbeddedFromParcel(paramHwParcel, hwBlob, (b * 28));
      arrayList.add(gnssSvInfo);
    } 
    return arrayList;
  }
  
  public static final void writeVectorToParcel(HwParcel paramHwParcel, ArrayList<GnssSvInfo> paramArrayList) {
    HwBlob hwBlob1 = new HwBlob(16);
    int i = paramArrayList.size();
    hwBlob1.putInt32(8L, i);
    hwBlob1.putBool(12L, false);
    HwBlob hwBlob2 = new HwBlob(i * 28);
    for (byte b = 0; b < i; b++)
      ((GnssSvInfo)paramArrayList.get(b)).writeEmbeddedToBlob(hwBlob2, (b * 28)); 
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
    return !HidlSupport.deepEquals(this.v1_0, ((GnssSvInfo)paramObject).v1_0) ? false : (!(this.constellation != ((GnssSvInfo)paramObject).constellation));
  }
  
  public final int hashCode() {
    return Objects.hash(new Object[] { Integer.valueOf(HidlSupport.deepHashCode(this.v1_0)), Integer.valueOf(HidlSupport.deepHashCode(Byte.valueOf(this.constellation))) });
  }
  
  public final void readEmbeddedFromParcel(HwParcel paramHwParcel, HwBlob paramHwBlob, long paramLong) {
    this.v1_0.readEmbeddedFromParcel(paramHwParcel, paramHwBlob, 0L + paramLong);
    this.constellation = paramHwBlob.getInt8(24L + paramLong);
  }
  
  public final void readFromParcel(HwParcel paramHwParcel) {
    readEmbeddedFromParcel(paramHwParcel, paramHwParcel.readBuffer(28L), 0L);
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
    paramHwBlob.putInt8(24L + paramLong, this.constellation);
  }
  
  public final void writeToParcel(HwParcel paramHwParcel) {
    HwBlob hwBlob = new HwBlob(28);
    writeEmbeddedToBlob(hwBlob, 0L);
    paramHwParcel.writeBuffer(hwBlob);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/gnss/V2_0/IGnssCallback$GnssSvInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */