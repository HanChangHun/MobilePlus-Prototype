package android.hardware.radio.V1_2;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

public final class Call {
  public int audioQuality = 0;
  
  public android.hardware.radio.V1_0.Call base = new android.hardware.radio.V1_0.Call();
  
  public static final ArrayList<Call> readVectorFromParcel(HwParcel paramHwParcel) {
    ArrayList<Call> arrayList = new ArrayList();
    HwBlob hwBlob = paramHwParcel.readBuffer(16L);
    int i = hwBlob.getInt32(8L);
    hwBlob = paramHwParcel.readEmbeddedBuffer((i * 96), hwBlob.handle(), 0L, true);
    arrayList.clear();
    for (byte b = 0; b < i; b++) {
      Call call = new Call();
      call.readEmbeddedFromParcel(paramHwParcel, hwBlob, (b * 96));
      arrayList.add(call);
    } 
    return arrayList;
  }
  
  public static final void writeVectorToParcel(HwParcel paramHwParcel, ArrayList<Call> paramArrayList) {
    HwBlob hwBlob1 = new HwBlob(16);
    int i = paramArrayList.size();
    hwBlob1.putInt32(8L, i);
    hwBlob1.putBool(12L, false);
    HwBlob hwBlob2 = new HwBlob(i * 96);
    for (byte b = 0; b < i; b++)
      ((Call)paramArrayList.get(b)).writeEmbeddedToBlob(hwBlob2, (b * 96)); 
    hwBlob1.putBlob(0L, hwBlob2);
    paramHwParcel.writeBuffer(hwBlob1);
  }
  
  public final boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (paramObject == null)
      return false; 
    if (paramObject.getClass() != Call.class)
      return false; 
    paramObject = paramObject;
    return !HidlSupport.deepEquals(this.base, ((Call)paramObject).base) ? false : (!(this.audioQuality != ((Call)paramObject).audioQuality));
  }
  
  public final int hashCode() {
    return Objects.hash(new Object[] { Integer.valueOf(HidlSupport.deepHashCode(this.base)), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.audioQuality))) });
  }
  
  public final void readEmbeddedFromParcel(HwParcel paramHwParcel, HwBlob paramHwBlob, long paramLong) {
    this.base.readEmbeddedFromParcel(paramHwParcel, paramHwBlob, 0L + paramLong);
    this.audioQuality = paramHwBlob.getInt32(88L + paramLong);
  }
  
  public final void readFromParcel(HwParcel paramHwParcel) {
    readEmbeddedFromParcel(paramHwParcel, paramHwParcel.readBuffer(96L), 0L);
  }
  
  public final String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("{");
    stringBuilder.append(".base = ");
    stringBuilder.append(this.base);
    stringBuilder.append(", .audioQuality = ");
    stringBuilder.append(AudioQuality.toString(this.audioQuality));
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public final void writeEmbeddedToBlob(HwBlob paramHwBlob, long paramLong) {
    this.base.writeEmbeddedToBlob(paramHwBlob, 0L + paramLong);
    paramHwBlob.putInt32(88L + paramLong, this.audioQuality);
  }
  
  public final void writeToParcel(HwParcel paramHwParcel) {
    HwBlob hwBlob = new HwBlob(96);
    writeEmbeddedToBlob(hwBlob, 0L);
    paramHwParcel.writeBuffer(hwBlob);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/V1_2/Call.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */