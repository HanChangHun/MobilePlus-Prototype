package android.hardware.radio.V1_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

public final class CallForwardInfo {
  public String number = new String();
  
  public int reason = 0;
  
  public int serviceClass = 0;
  
  public int status = 0;
  
  public int timeSeconds = 0;
  
  public int toa = 0;
  
  public static final ArrayList<CallForwardInfo> readVectorFromParcel(HwParcel paramHwParcel) {
    ArrayList<CallForwardInfo> arrayList = new ArrayList();
    HwBlob hwBlob1 = paramHwParcel.readBuffer(16L);
    int i = hwBlob1.getInt32(8L);
    HwBlob hwBlob2 = paramHwParcel.readEmbeddedBuffer((i * 40), hwBlob1.handle(), 0L, true);
    arrayList.clear();
    for (byte b = 0; b < i; b++) {
      CallForwardInfo callForwardInfo = new CallForwardInfo();
      callForwardInfo.readEmbeddedFromParcel(paramHwParcel, hwBlob2, (b * 40));
      arrayList.add(callForwardInfo);
    } 
    return arrayList;
  }
  
  public static final void writeVectorToParcel(HwParcel paramHwParcel, ArrayList<CallForwardInfo> paramArrayList) {
    HwBlob hwBlob1 = new HwBlob(16);
    int i = paramArrayList.size();
    hwBlob1.putInt32(8L, i);
    hwBlob1.putBool(12L, false);
    HwBlob hwBlob2 = new HwBlob(i * 40);
    for (byte b = 0; b < i; b++)
      ((CallForwardInfo)paramArrayList.get(b)).writeEmbeddedToBlob(hwBlob2, (b * 40)); 
    hwBlob1.putBlob(0L, hwBlob2);
    paramHwParcel.writeBuffer(hwBlob1);
  }
  
  public final boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (paramObject == null)
      return false; 
    if (paramObject.getClass() != CallForwardInfo.class)
      return false; 
    paramObject = paramObject;
    return (this.status != ((CallForwardInfo)paramObject).status) ? false : ((this.reason != ((CallForwardInfo)paramObject).reason) ? false : ((this.serviceClass != ((CallForwardInfo)paramObject).serviceClass) ? false : ((this.toa != ((CallForwardInfo)paramObject).toa) ? false : (!HidlSupport.deepEquals(this.number, ((CallForwardInfo)paramObject).number) ? false : (!(this.timeSeconds != ((CallForwardInfo)paramObject).timeSeconds))))));
  }
  
  public final int hashCode() {
    return Objects.hash(new Object[] { Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.status))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.reason))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.serviceClass))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.toa))), Integer.valueOf(HidlSupport.deepHashCode(this.number)), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.timeSeconds))) });
  }
  
  public final void readEmbeddedFromParcel(HwParcel paramHwParcel, HwBlob paramHwBlob, long paramLong) {
    this.status = paramHwBlob.getInt32(paramLong + 0L);
    this.reason = paramHwBlob.getInt32(paramLong + 4L);
    this.serviceClass = paramHwBlob.getInt32(paramLong + 8L);
    this.toa = paramHwBlob.getInt32(paramLong + 12L);
    String str = paramHwBlob.getString(paramLong + 16L);
    this.number = str;
    paramHwParcel.readEmbeddedBuffer(((str.getBytes()).length + 1), paramHwBlob.handle(), paramLong + 16L + 0L, false);
    this.timeSeconds = paramHwBlob.getInt32(paramLong + 32L);
  }
  
  public final void readFromParcel(HwParcel paramHwParcel) {
    readEmbeddedFromParcel(paramHwParcel, paramHwParcel.readBuffer(40L), 0L);
  }
  
  public final String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("{");
    stringBuilder.append(".status = ");
    stringBuilder.append(CallForwardInfoStatus.toString(this.status));
    stringBuilder.append(", .reason = ");
    stringBuilder.append(this.reason);
    stringBuilder.append(", .serviceClass = ");
    stringBuilder.append(this.serviceClass);
    stringBuilder.append(", .toa = ");
    stringBuilder.append(this.toa);
    stringBuilder.append(", .number = ");
    stringBuilder.append(this.number);
    stringBuilder.append(", .timeSeconds = ");
    stringBuilder.append(this.timeSeconds);
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public final void writeEmbeddedToBlob(HwBlob paramHwBlob, long paramLong) {
    paramHwBlob.putInt32(0L + paramLong, this.status);
    paramHwBlob.putInt32(4L + paramLong, this.reason);
    paramHwBlob.putInt32(8L + paramLong, this.serviceClass);
    paramHwBlob.putInt32(12L + paramLong, this.toa);
    paramHwBlob.putString(16L + paramLong, this.number);
    paramHwBlob.putInt32(32L + paramLong, this.timeSeconds);
  }
  
  public final void writeToParcel(HwParcel paramHwParcel) {
    HwBlob hwBlob = new HwBlob(40);
    writeEmbeddedToBlob(hwBlob, 0L);
    paramHwParcel.writeBuffer(hwBlob);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/V1_0/CallForwardInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */