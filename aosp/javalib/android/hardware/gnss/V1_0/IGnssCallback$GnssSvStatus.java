package android.hardware.gnss.V1_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public final class GnssSvStatus {
  public IGnssCallback.GnssSvInfo[] gnssSvList = new IGnssCallback.GnssSvInfo[64];
  
  public int numSvs = 0;
  
  public static final ArrayList<GnssSvStatus> readVectorFromParcel(HwParcel paramHwParcel) {
    ArrayList<GnssSvStatus> arrayList = new ArrayList();
    HwBlob hwBlob1 = paramHwParcel.readBuffer(16L);
    int i = hwBlob1.getInt32(8L);
    HwBlob hwBlob2 = paramHwParcel.readEmbeddedBuffer((i * 1540), hwBlob1.handle(), 0L, true);
    arrayList.clear();
    for (byte b = 0; b < i; b++) {
      GnssSvStatus gnssSvStatus = new GnssSvStatus();
      gnssSvStatus.readEmbeddedFromParcel(paramHwParcel, hwBlob2, (b * 1540));
      arrayList.add(gnssSvStatus);
    } 
    return arrayList;
  }
  
  public static final void writeVectorToParcel(HwParcel paramHwParcel, ArrayList<GnssSvStatus> paramArrayList) {
    HwBlob hwBlob1 = new HwBlob(16);
    int i = paramArrayList.size();
    hwBlob1.putInt32(8L, i);
    hwBlob1.putBool(12L, false);
    HwBlob hwBlob2 = new HwBlob(i * 1540);
    for (byte b = 0; b < i; b++)
      ((GnssSvStatus)paramArrayList.get(b)).writeEmbeddedToBlob(hwBlob2, (b * 1540)); 
    hwBlob1.putBlob(0L, hwBlob2);
    paramHwParcel.writeBuffer(hwBlob1);
  }
  
  public final boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (paramObject == null)
      return false; 
    if (paramObject.getClass() != GnssSvStatus.class)
      return false; 
    paramObject = paramObject;
    return (this.numSvs != ((GnssSvStatus)paramObject).numSvs) ? false : (!!HidlSupport.deepEquals(this.gnssSvList, ((GnssSvStatus)paramObject).gnssSvList));
  }
  
  public final int hashCode() {
    return Objects.hash(new Object[] { Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.numSvs))), Integer.valueOf(HidlSupport.deepHashCode(this.gnssSvList)) });
  }
  
  public final void readEmbeddedFromParcel(HwParcel paramHwParcel, HwBlob paramHwBlob, long paramLong) {
    this.numSvs = paramHwBlob.getInt32(0L + paramLong);
    paramLong = 4L + paramLong;
    for (byte b = 0; b < 64; b++) {
      this.gnssSvList[b] = new IGnssCallback.GnssSvInfo();
      this.gnssSvList[b].readEmbeddedFromParcel(paramHwParcel, paramHwBlob, paramLong);
      paramLong += 24L;
    } 
  }
  
  public final void readFromParcel(HwParcel paramHwParcel) {
    readEmbeddedFromParcel(paramHwParcel, paramHwParcel.readBuffer(1540L), 0L);
  }
  
  public final String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("{");
    stringBuilder.append(".numSvs = ");
    stringBuilder.append(this.numSvs);
    stringBuilder.append(", .gnssSvList = ");
    stringBuilder.append(Arrays.toString((Object[])this.gnssSvList));
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public final void writeEmbeddedToBlob(HwBlob paramHwBlob, long paramLong) {
    paramHwBlob.putInt32(0L + paramLong, this.numSvs);
    paramLong = 4L + paramLong;
    for (byte b = 0; b < 64; b++) {
      this.gnssSvList[b].writeEmbeddedToBlob(paramHwBlob, paramLong);
      paramLong += 24L;
    } 
  }
  
  public final void writeToParcel(HwParcel paramHwParcel) {
    HwBlob hwBlob = new HwBlob(1540);
    writeEmbeddedToBlob(hwBlob, 0L);
    paramHwParcel.writeBuffer(hwBlob);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/gnss/V1_0/IGnssCallback$GnssSvStatus.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */