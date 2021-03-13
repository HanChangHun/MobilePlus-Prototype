package android.hardware.gnss.V2_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

public final class ElapsedRealtime {
  public short flags;
  
  public long timeUncertaintyNs = 0L;
  
  public long timestampNs = 0L;
  
  public static final ArrayList<ElapsedRealtime> readVectorFromParcel(HwParcel paramHwParcel) {
    ArrayList<ElapsedRealtime> arrayList = new ArrayList();
    HwBlob hwBlob = paramHwParcel.readBuffer(16L);
    int i = hwBlob.getInt32(8L);
    hwBlob = paramHwParcel.readEmbeddedBuffer((i * 24), hwBlob.handle(), 0L, true);
    arrayList.clear();
    for (byte b = 0; b < i; b++) {
      ElapsedRealtime elapsedRealtime = new ElapsedRealtime();
      elapsedRealtime.readEmbeddedFromParcel(paramHwParcel, hwBlob, (b * 24));
      arrayList.add(elapsedRealtime);
    } 
    return arrayList;
  }
  
  public static final void writeVectorToParcel(HwParcel paramHwParcel, ArrayList<ElapsedRealtime> paramArrayList) {
    HwBlob hwBlob1 = new HwBlob(16);
    int i = paramArrayList.size();
    hwBlob1.putInt32(8L, i);
    hwBlob1.putBool(12L, false);
    HwBlob hwBlob2 = new HwBlob(i * 24);
    for (byte b = 0; b < i; b++)
      ((ElapsedRealtime)paramArrayList.get(b)).writeEmbeddedToBlob(hwBlob2, (b * 24)); 
    hwBlob1.putBlob(0L, hwBlob2);
    paramHwParcel.writeBuffer(hwBlob1);
  }
  
  public final boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (paramObject == null)
      return false; 
    if (paramObject.getClass() != ElapsedRealtime.class)
      return false; 
    paramObject = paramObject;
    return !HidlSupport.deepEquals(Short.valueOf(this.flags), Short.valueOf(((ElapsedRealtime)paramObject).flags)) ? false : ((this.timestampNs != ((ElapsedRealtime)paramObject).timestampNs) ? false : (!(this.timeUncertaintyNs != ((ElapsedRealtime)paramObject).timeUncertaintyNs)));
  }
  
  public final int hashCode() {
    return Objects.hash(new Object[] { Integer.valueOf(HidlSupport.deepHashCode(Short.valueOf(this.flags))), Integer.valueOf(HidlSupport.deepHashCode(Long.valueOf(this.timestampNs))), Integer.valueOf(HidlSupport.deepHashCode(Long.valueOf(this.timeUncertaintyNs))) });
  }
  
  public final void readEmbeddedFromParcel(HwParcel paramHwParcel, HwBlob paramHwBlob, long paramLong) {
    this.flags = paramHwBlob.getInt16(0L + paramLong);
    this.timestampNs = paramHwBlob.getInt64(8L + paramLong);
    this.timeUncertaintyNs = paramHwBlob.getInt64(16L + paramLong);
  }
  
  public final void readFromParcel(HwParcel paramHwParcel) {
    readEmbeddedFromParcel(paramHwParcel, paramHwParcel.readBuffer(24L), 0L);
  }
  
  public final String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("{");
    stringBuilder.append(".flags = ");
    stringBuilder.append(ElapsedRealtimeFlags.dumpBitfield(this.flags));
    stringBuilder.append(", .timestampNs = ");
    stringBuilder.append(this.timestampNs);
    stringBuilder.append(", .timeUncertaintyNs = ");
    stringBuilder.append(this.timeUncertaintyNs);
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public final void writeEmbeddedToBlob(HwBlob paramHwBlob, long paramLong) {
    paramHwBlob.putInt16(0L + paramLong, this.flags);
    paramHwBlob.putInt64(8L + paramLong, this.timestampNs);
    paramHwBlob.putInt64(16L + paramLong, this.timeUncertaintyNs);
  }
  
  public final void writeToParcel(HwParcel paramHwParcel) {
    HwBlob hwBlob = new HwBlob(24);
    writeEmbeddedToBlob(hwBlob, 0L);
    paramHwParcel.writeBuffer(hwBlob);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/gnss/V2_0/ElapsedRealtime.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */