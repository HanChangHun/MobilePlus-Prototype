package android.hardware.radio.V1_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public final class ActivityStatsInfo {
  public int idleModeTimeMs = 0;
  
  public int rxModeTimeMs = 0;
  
  public int sleepModeTimeMs = 0;
  
  public int[] txmModetimeMs = new int[5];
  
  public static final ArrayList<ActivityStatsInfo> readVectorFromParcel(HwParcel paramHwParcel) {
    ArrayList<ActivityStatsInfo> arrayList = new ArrayList();
    HwBlob hwBlob1 = paramHwParcel.readBuffer(16L);
    int i = hwBlob1.getInt32(8L);
    HwBlob hwBlob2 = paramHwParcel.readEmbeddedBuffer((i * 32), hwBlob1.handle(), 0L, true);
    arrayList.clear();
    for (byte b = 0; b < i; b++) {
      ActivityStatsInfo activityStatsInfo = new ActivityStatsInfo();
      activityStatsInfo.readEmbeddedFromParcel(paramHwParcel, hwBlob2, (b * 32));
      arrayList.add(activityStatsInfo);
    } 
    return arrayList;
  }
  
  public static final void writeVectorToParcel(HwParcel paramHwParcel, ArrayList<ActivityStatsInfo> paramArrayList) {
    HwBlob hwBlob1 = new HwBlob(16);
    int i = paramArrayList.size();
    hwBlob1.putInt32(8L, i);
    hwBlob1.putBool(12L, false);
    HwBlob hwBlob2 = new HwBlob(i * 32);
    for (byte b = 0; b < i; b++)
      ((ActivityStatsInfo)paramArrayList.get(b)).writeEmbeddedToBlob(hwBlob2, (b * 32)); 
    hwBlob1.putBlob(0L, hwBlob2);
    paramHwParcel.writeBuffer(hwBlob1);
  }
  
  public final boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (paramObject == null)
      return false; 
    if (paramObject.getClass() != ActivityStatsInfo.class)
      return false; 
    paramObject = paramObject;
    return (this.sleepModeTimeMs != ((ActivityStatsInfo)paramObject).sleepModeTimeMs) ? false : ((this.idleModeTimeMs != ((ActivityStatsInfo)paramObject).idleModeTimeMs) ? false : (!HidlSupport.deepEquals(this.txmModetimeMs, ((ActivityStatsInfo)paramObject).txmModetimeMs) ? false : (!(this.rxModeTimeMs != ((ActivityStatsInfo)paramObject).rxModeTimeMs))));
  }
  
  public final int hashCode() {
    return Objects.hash(new Object[] { Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.sleepModeTimeMs))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.idleModeTimeMs))), Integer.valueOf(HidlSupport.deepHashCode(this.txmModetimeMs)), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.rxModeTimeMs))) });
  }
  
  public final void readEmbeddedFromParcel(HwParcel paramHwParcel, HwBlob paramHwBlob, long paramLong) {
    this.sleepModeTimeMs = paramHwBlob.getInt32(0L + paramLong);
    this.idleModeTimeMs = paramHwBlob.getInt32(4L + paramLong);
    paramHwBlob.copyToInt32Array(8L + paramLong, this.txmModetimeMs, 5);
    this.rxModeTimeMs = paramHwBlob.getInt32(28L + paramLong);
  }
  
  public final void readFromParcel(HwParcel paramHwParcel) {
    readEmbeddedFromParcel(paramHwParcel, paramHwParcel.readBuffer(32L), 0L);
  }
  
  public final String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("{");
    stringBuilder.append(".sleepModeTimeMs = ");
    stringBuilder.append(this.sleepModeTimeMs);
    stringBuilder.append(", .idleModeTimeMs = ");
    stringBuilder.append(this.idleModeTimeMs);
    stringBuilder.append(", .txmModetimeMs = ");
    stringBuilder.append(Arrays.toString(this.txmModetimeMs));
    stringBuilder.append(", .rxModeTimeMs = ");
    stringBuilder.append(this.rxModeTimeMs);
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public final void writeEmbeddedToBlob(HwBlob paramHwBlob, long paramLong) {
    paramHwBlob.putInt32(0L + paramLong, this.sleepModeTimeMs);
    paramHwBlob.putInt32(4L + paramLong, this.idleModeTimeMs);
    int[] arrayOfInt = this.txmModetimeMs;
    if (arrayOfInt != null && arrayOfInt.length == 5) {
      paramHwBlob.putInt32Array(8L + paramLong, arrayOfInt);
      paramHwBlob.putInt32(28L + paramLong, this.rxModeTimeMs);
      return;
    } 
    throw new IllegalArgumentException("Array element is not of the expected length");
  }
  
  public final void writeToParcel(HwParcel paramHwParcel) {
    HwBlob hwBlob = new HwBlob(32);
    writeEmbeddedToBlob(hwBlob, 0L);
    paramHwParcel.writeBuffer(hwBlob);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/V1_0/ActivityStatsInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */