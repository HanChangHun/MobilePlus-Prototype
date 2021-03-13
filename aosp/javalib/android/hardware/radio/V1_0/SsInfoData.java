package android.hardware.radio.V1_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

public final class SsInfoData {
  public ArrayList<Integer> ssInfo = new ArrayList<>();
  
  public static final ArrayList<SsInfoData> readVectorFromParcel(HwParcel paramHwParcel) {
    ArrayList<SsInfoData> arrayList = new ArrayList();
    HwBlob hwBlob1 = paramHwParcel.readBuffer(16L);
    int i = hwBlob1.getInt32(8L);
    HwBlob hwBlob2 = paramHwParcel.readEmbeddedBuffer((i * 16), hwBlob1.handle(), 0L, true);
    arrayList.clear();
    for (byte b = 0; b < i; b++) {
      SsInfoData ssInfoData = new SsInfoData();
      ssInfoData.readEmbeddedFromParcel(paramHwParcel, hwBlob2, (b * 16));
      arrayList.add(ssInfoData);
    } 
    return arrayList;
  }
  
  public static final void writeVectorToParcel(HwParcel paramHwParcel, ArrayList<SsInfoData> paramArrayList) {
    HwBlob hwBlob1 = new HwBlob(16);
    int i = paramArrayList.size();
    hwBlob1.putInt32(8L, i);
    hwBlob1.putBool(12L, false);
    HwBlob hwBlob2 = new HwBlob(i * 16);
    for (byte b = 0; b < i; b++)
      ((SsInfoData)paramArrayList.get(b)).writeEmbeddedToBlob(hwBlob2, (b * 16)); 
    hwBlob1.putBlob(0L, hwBlob2);
    paramHwParcel.writeBuffer(hwBlob1);
  }
  
  public final boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (paramObject == null)
      return false; 
    if (paramObject.getClass() != SsInfoData.class)
      return false; 
    paramObject = paramObject;
    return !!HidlSupport.deepEquals(this.ssInfo, ((SsInfoData)paramObject).ssInfo);
  }
  
  public final int hashCode() {
    return Objects.hash(new Object[] { Integer.valueOf(HidlSupport.deepHashCode(this.ssInfo)) });
  }
  
  public final void readEmbeddedFromParcel(HwParcel paramHwParcel, HwBlob paramHwBlob, long paramLong) {
    int i = paramHwBlob.getInt32(paramLong + 0L + 8L);
    HwBlob hwBlob = paramHwParcel.readEmbeddedBuffer((i * 4), paramHwBlob.handle(), paramLong + 0L + 0L, true);
    this.ssInfo.clear();
    for (byte b = 0; b < i; b++) {
      int j = hwBlob.getInt32((b * 4));
      this.ssInfo.add(Integer.valueOf(j));
    } 
  }
  
  public final void readFromParcel(HwParcel paramHwParcel) {
    readEmbeddedFromParcel(paramHwParcel, paramHwParcel.readBuffer(16L), 0L);
  }
  
  public final String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("{");
    stringBuilder.append(".ssInfo = ");
    stringBuilder.append(this.ssInfo);
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public final void writeEmbeddedToBlob(HwBlob paramHwBlob, long paramLong) {
    int i = this.ssInfo.size();
    paramHwBlob.putInt32(paramLong + 0L + 8L, i);
    paramHwBlob.putBool(paramLong + 0L + 12L, false);
    HwBlob hwBlob = new HwBlob(i * 4);
    for (byte b = 0; b < i; b++)
      hwBlob.putInt32((b * 4), ((Integer)this.ssInfo.get(b)).intValue()); 
    paramHwBlob.putBlob(paramLong + 0L + 0L, hwBlob);
  }
  
  public final void writeToParcel(HwParcel paramHwParcel) {
    HwBlob hwBlob = new HwBlob(16);
    writeEmbeddedToBlob(hwBlob, 0L);
    paramHwParcel.writeBuffer(hwBlob);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/V1_0/SsInfoData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */