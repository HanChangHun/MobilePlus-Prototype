package android.hardware.contexthub.V1_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

public final class MemRange {
  public int flags;
  
  public int freeBytes = 0;
  
  public int totalBytes = 0;
  
  public int type = 0;
  
  public static final ArrayList<MemRange> readVectorFromParcel(HwParcel paramHwParcel) {
    ArrayList<MemRange> arrayList = new ArrayList();
    HwBlob hwBlob1 = paramHwParcel.readBuffer(16L);
    int i = hwBlob1.getInt32(8L);
    HwBlob hwBlob2 = paramHwParcel.readEmbeddedBuffer((i * 16), hwBlob1.handle(), 0L, true);
    arrayList.clear();
    for (byte b = 0; b < i; b++) {
      MemRange memRange = new MemRange();
      memRange.readEmbeddedFromParcel(paramHwParcel, hwBlob2, (b * 16));
      arrayList.add(memRange);
    } 
    return arrayList;
  }
  
  public static final void writeVectorToParcel(HwParcel paramHwParcel, ArrayList<MemRange> paramArrayList) {
    HwBlob hwBlob1 = new HwBlob(16);
    int i = paramArrayList.size();
    hwBlob1.putInt32(8L, i);
    hwBlob1.putBool(12L, false);
    HwBlob hwBlob2 = new HwBlob(i * 16);
    for (byte b = 0; b < i; b++)
      ((MemRange)paramArrayList.get(b)).writeEmbeddedToBlob(hwBlob2, (b * 16)); 
    hwBlob1.putBlob(0L, hwBlob2);
    paramHwParcel.writeBuffer(hwBlob1);
  }
  
  public final boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (paramObject == null)
      return false; 
    if (paramObject.getClass() != MemRange.class)
      return false; 
    paramObject = paramObject;
    return (this.totalBytes != ((MemRange)paramObject).totalBytes) ? false : ((this.freeBytes != ((MemRange)paramObject).freeBytes) ? false : ((this.type != ((MemRange)paramObject).type) ? false : (!!HidlSupport.deepEquals(Integer.valueOf(this.flags), Integer.valueOf(((MemRange)paramObject).flags)))));
  }
  
  public final int hashCode() {
    return Objects.hash(new Object[] { Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.totalBytes))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.freeBytes))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.type))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.flags))) });
  }
  
  public final void readEmbeddedFromParcel(HwParcel paramHwParcel, HwBlob paramHwBlob, long paramLong) {
    this.totalBytes = paramHwBlob.getInt32(0L + paramLong);
    this.freeBytes = paramHwBlob.getInt32(4L + paramLong);
    this.type = paramHwBlob.getInt32(8L + paramLong);
    this.flags = paramHwBlob.getInt32(12L + paramLong);
  }
  
  public final void readFromParcel(HwParcel paramHwParcel) {
    readEmbeddedFromParcel(paramHwParcel, paramHwParcel.readBuffer(16L), 0L);
  }
  
  public final String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("{");
    stringBuilder.append(".totalBytes = ");
    stringBuilder.append(this.totalBytes);
    stringBuilder.append(", .freeBytes = ");
    stringBuilder.append(this.freeBytes);
    stringBuilder.append(", .type = ");
    stringBuilder.append(HubMemoryType.toString(this.type));
    stringBuilder.append(", .flags = ");
    stringBuilder.append(HubMemoryFlag.dumpBitfield(this.flags));
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public final void writeEmbeddedToBlob(HwBlob paramHwBlob, long paramLong) {
    paramHwBlob.putInt32(0L + paramLong, this.totalBytes);
    paramHwBlob.putInt32(4L + paramLong, this.freeBytes);
    paramHwBlob.putInt32(8L + paramLong, this.type);
    paramHwBlob.putInt32(12L + paramLong, this.flags);
  }
  
  public final void writeToParcel(HwParcel paramHwParcel) {
    HwBlob hwBlob = new HwBlob(16);
    writeEmbeddedToBlob(hwBlob, 0L);
    paramHwParcel.writeBuffer(hwBlob);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/contexthub/V1_0/MemRange.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */