package android.hardware.radio.V1_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

public final class SimRefreshResult {
  public String aid = new String();
  
  public int efId = 0;
  
  public int type = 0;
  
  public static final ArrayList<SimRefreshResult> readVectorFromParcel(HwParcel paramHwParcel) {
    ArrayList<SimRefreshResult> arrayList = new ArrayList();
    HwBlob hwBlob1 = paramHwParcel.readBuffer(16L);
    int i = hwBlob1.getInt32(8L);
    HwBlob hwBlob2 = paramHwParcel.readEmbeddedBuffer((i * 24), hwBlob1.handle(), 0L, true);
    arrayList.clear();
    for (byte b = 0; b < i; b++) {
      SimRefreshResult simRefreshResult = new SimRefreshResult();
      simRefreshResult.readEmbeddedFromParcel(paramHwParcel, hwBlob2, (b * 24));
      arrayList.add(simRefreshResult);
    } 
    return arrayList;
  }
  
  public static final void writeVectorToParcel(HwParcel paramHwParcel, ArrayList<SimRefreshResult> paramArrayList) {
    HwBlob hwBlob1 = new HwBlob(16);
    int i = paramArrayList.size();
    hwBlob1.putInt32(8L, i);
    hwBlob1.putBool(12L, false);
    HwBlob hwBlob2 = new HwBlob(i * 24);
    for (byte b = 0; b < i; b++)
      ((SimRefreshResult)paramArrayList.get(b)).writeEmbeddedToBlob(hwBlob2, (b * 24)); 
    hwBlob1.putBlob(0L, hwBlob2);
    paramHwParcel.writeBuffer(hwBlob1);
  }
  
  public final boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (paramObject == null)
      return false; 
    if (paramObject.getClass() != SimRefreshResult.class)
      return false; 
    paramObject = paramObject;
    return (this.type != ((SimRefreshResult)paramObject).type) ? false : ((this.efId != ((SimRefreshResult)paramObject).efId) ? false : (!!HidlSupport.deepEquals(this.aid, ((SimRefreshResult)paramObject).aid)));
  }
  
  public final int hashCode() {
    return Objects.hash(new Object[] { Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.type))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.efId))), Integer.valueOf(HidlSupport.deepHashCode(this.aid)) });
  }
  
  public final void readEmbeddedFromParcel(HwParcel paramHwParcel, HwBlob paramHwBlob, long paramLong) {
    this.type = paramHwBlob.getInt32(paramLong + 0L);
    this.efId = paramHwBlob.getInt32(paramLong + 4L);
    String str = paramHwBlob.getString(paramLong + 8L);
    this.aid = str;
    paramHwParcel.readEmbeddedBuffer(((str.getBytes()).length + 1), paramHwBlob.handle(), paramLong + 8L + 0L, false);
  }
  
  public final void readFromParcel(HwParcel paramHwParcel) {
    readEmbeddedFromParcel(paramHwParcel, paramHwParcel.readBuffer(24L), 0L);
  }
  
  public final String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("{");
    stringBuilder.append(".type = ");
    stringBuilder.append(SimRefreshType.toString(this.type));
    stringBuilder.append(", .efId = ");
    stringBuilder.append(this.efId);
    stringBuilder.append(", .aid = ");
    stringBuilder.append(this.aid);
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public final void writeEmbeddedToBlob(HwBlob paramHwBlob, long paramLong) {
    paramHwBlob.putInt32(0L + paramLong, this.type);
    paramHwBlob.putInt32(4L + paramLong, this.efId);
    paramHwBlob.putString(8L + paramLong, this.aid);
  }
  
  public final void writeToParcel(HwParcel paramHwParcel) {
    HwBlob hwBlob = new HwBlob(24);
    writeEmbeddedToBlob(hwBlob, 0L);
    paramHwParcel.writeBuffer(hwBlob);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/V1_0/SimRefreshResult.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */