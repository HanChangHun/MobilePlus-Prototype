package android.hardware.gnss.V1_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

public final class Options {
  public byte flags;
  
  public long periodNanos = 0L;
  
  public static final ArrayList<Options> readVectorFromParcel(HwParcel paramHwParcel) {
    ArrayList<Options> arrayList = new ArrayList();
    HwBlob hwBlob = paramHwParcel.readBuffer(16L);
    int i = hwBlob.getInt32(8L);
    hwBlob = paramHwParcel.readEmbeddedBuffer((i * 16), hwBlob.handle(), 0L, true);
    arrayList.clear();
    for (byte b = 0; b < i; b++) {
      Options options = new Options();
      options.readEmbeddedFromParcel(paramHwParcel, hwBlob, (b * 16));
      arrayList.add(options);
    } 
    return arrayList;
  }
  
  public static final void writeVectorToParcel(HwParcel paramHwParcel, ArrayList<Options> paramArrayList) {
    HwBlob hwBlob1 = new HwBlob(16);
    int i = paramArrayList.size();
    hwBlob1.putInt32(8L, i);
    hwBlob1.putBool(12L, false);
    HwBlob hwBlob2 = new HwBlob(i * 16);
    for (byte b = 0; b < i; b++)
      ((Options)paramArrayList.get(b)).writeEmbeddedToBlob(hwBlob2, (b * 16)); 
    hwBlob1.putBlob(0L, hwBlob2);
    paramHwParcel.writeBuffer(hwBlob1);
  }
  
  public final boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (paramObject == null)
      return false; 
    if (paramObject.getClass() != Options.class)
      return false; 
    paramObject = paramObject;
    return (this.periodNanos != ((Options)paramObject).periodNanos) ? false : (!!HidlSupport.deepEquals(Byte.valueOf(this.flags), Byte.valueOf(((Options)paramObject).flags)));
  }
  
  public final int hashCode() {
    return Objects.hash(new Object[] { Integer.valueOf(HidlSupport.deepHashCode(Long.valueOf(this.periodNanos))), Integer.valueOf(HidlSupport.deepHashCode(Byte.valueOf(this.flags))) });
  }
  
  public final void readEmbeddedFromParcel(HwParcel paramHwParcel, HwBlob paramHwBlob, long paramLong) {
    this.periodNanos = paramHwBlob.getInt64(0L + paramLong);
    this.flags = paramHwBlob.getInt8(8L + paramLong);
  }
  
  public final void readFromParcel(HwParcel paramHwParcel) {
    readEmbeddedFromParcel(paramHwParcel, paramHwParcel.readBuffer(16L), 0L);
  }
  
  public final String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("{");
    stringBuilder.append(".periodNanos = ");
    stringBuilder.append(this.periodNanos);
    stringBuilder.append(", .flags = ");
    stringBuilder.append(IGnssBatching.Flag.dumpBitfield(this.flags));
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public final void writeEmbeddedToBlob(HwBlob paramHwBlob, long paramLong) {
    paramHwBlob.putInt64(0L + paramLong, this.periodNanos);
    paramHwBlob.putInt8(8L + paramLong, this.flags);
  }
  
  public final void writeToParcel(HwParcel paramHwParcel) {
    HwBlob hwBlob = new HwBlob(16);
    writeEmbeddedToBlob(hwBlob, 0L);
    paramHwParcel.writeBuffer(hwBlob);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/gnss/V1_0/IGnssBatching$Options.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */