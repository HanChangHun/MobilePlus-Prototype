package android.hardware.radio.V1_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

public final class LastCallFailCauseInfo {
  public int causeCode = 0;
  
  public String vendorCause = new String();
  
  public static final ArrayList<LastCallFailCauseInfo> readVectorFromParcel(HwParcel paramHwParcel) {
    ArrayList<LastCallFailCauseInfo> arrayList = new ArrayList();
    HwBlob hwBlob = paramHwParcel.readBuffer(16L);
    int i = hwBlob.getInt32(8L);
    hwBlob = paramHwParcel.readEmbeddedBuffer((i * 24), hwBlob.handle(), 0L, true);
    arrayList.clear();
    for (byte b = 0; b < i; b++) {
      LastCallFailCauseInfo lastCallFailCauseInfo = new LastCallFailCauseInfo();
      lastCallFailCauseInfo.readEmbeddedFromParcel(paramHwParcel, hwBlob, (b * 24));
      arrayList.add(lastCallFailCauseInfo);
    } 
    return arrayList;
  }
  
  public static final void writeVectorToParcel(HwParcel paramHwParcel, ArrayList<LastCallFailCauseInfo> paramArrayList) {
    HwBlob hwBlob1 = new HwBlob(16);
    int i = paramArrayList.size();
    hwBlob1.putInt32(8L, i);
    hwBlob1.putBool(12L, false);
    HwBlob hwBlob2 = new HwBlob(i * 24);
    for (byte b = 0; b < i; b++)
      ((LastCallFailCauseInfo)paramArrayList.get(b)).writeEmbeddedToBlob(hwBlob2, (b * 24)); 
    hwBlob1.putBlob(0L, hwBlob2);
    paramHwParcel.writeBuffer(hwBlob1);
  }
  
  public final boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (paramObject == null)
      return false; 
    if (paramObject.getClass() != LastCallFailCauseInfo.class)
      return false; 
    paramObject = paramObject;
    return (this.causeCode != ((LastCallFailCauseInfo)paramObject).causeCode) ? false : (!!HidlSupport.deepEquals(this.vendorCause, ((LastCallFailCauseInfo)paramObject).vendorCause));
  }
  
  public final int hashCode() {
    return Objects.hash(new Object[] { Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.causeCode))), Integer.valueOf(HidlSupport.deepHashCode(this.vendorCause)) });
  }
  
  public final void readEmbeddedFromParcel(HwParcel paramHwParcel, HwBlob paramHwBlob, long paramLong) {
    this.causeCode = paramHwBlob.getInt32(paramLong + 0L);
    String str = paramHwBlob.getString(paramLong + 8L);
    this.vendorCause = str;
    paramHwParcel.readEmbeddedBuffer(((str.getBytes()).length + 1), paramHwBlob.handle(), paramLong + 8L + 0L, false);
  }
  
  public final void readFromParcel(HwParcel paramHwParcel) {
    readEmbeddedFromParcel(paramHwParcel, paramHwParcel.readBuffer(24L), 0L);
  }
  
  public final String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("{");
    stringBuilder.append(".causeCode = ");
    stringBuilder.append(LastCallFailCause.toString(this.causeCode));
    stringBuilder.append(", .vendorCause = ");
    stringBuilder.append(this.vendorCause);
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public final void writeEmbeddedToBlob(HwBlob paramHwBlob, long paramLong) {
    paramHwBlob.putInt32(0L + paramLong, this.causeCode);
    paramHwBlob.putString(8L + paramLong, this.vendorCause);
  }
  
  public final void writeToParcel(HwParcel paramHwParcel) {
    HwBlob hwBlob = new HwBlob(24);
    writeEmbeddedToBlob(hwBlob, 0L);
    paramHwParcel.writeBuffer(hwBlob);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/V1_0/LastCallFailCauseInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */