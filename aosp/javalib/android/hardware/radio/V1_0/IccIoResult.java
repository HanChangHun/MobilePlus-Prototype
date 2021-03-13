package android.hardware.radio.V1_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

public final class IccIoResult {
  public String simResponse = new String();
  
  public int sw1 = 0;
  
  public int sw2 = 0;
  
  public static final ArrayList<IccIoResult> readVectorFromParcel(HwParcel paramHwParcel) {
    ArrayList<IccIoResult> arrayList = new ArrayList();
    HwBlob hwBlob1 = paramHwParcel.readBuffer(16L);
    int i = hwBlob1.getInt32(8L);
    HwBlob hwBlob2 = paramHwParcel.readEmbeddedBuffer((i * 24), hwBlob1.handle(), 0L, true);
    arrayList.clear();
    for (byte b = 0; b < i; b++) {
      IccIoResult iccIoResult = new IccIoResult();
      iccIoResult.readEmbeddedFromParcel(paramHwParcel, hwBlob2, (b * 24));
      arrayList.add(iccIoResult);
    } 
    return arrayList;
  }
  
  public static final void writeVectorToParcel(HwParcel paramHwParcel, ArrayList<IccIoResult> paramArrayList) {
    HwBlob hwBlob1 = new HwBlob(16);
    int i = paramArrayList.size();
    hwBlob1.putInt32(8L, i);
    hwBlob1.putBool(12L, false);
    HwBlob hwBlob2 = new HwBlob(i * 24);
    for (byte b = 0; b < i; b++)
      ((IccIoResult)paramArrayList.get(b)).writeEmbeddedToBlob(hwBlob2, (b * 24)); 
    hwBlob1.putBlob(0L, hwBlob2);
    paramHwParcel.writeBuffer(hwBlob1);
  }
  
  public final boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (paramObject == null)
      return false; 
    if (paramObject.getClass() != IccIoResult.class)
      return false; 
    paramObject = paramObject;
    return (this.sw1 != ((IccIoResult)paramObject).sw1) ? false : ((this.sw2 != ((IccIoResult)paramObject).sw2) ? false : (!!HidlSupport.deepEquals(this.simResponse, ((IccIoResult)paramObject).simResponse)));
  }
  
  public final int hashCode() {
    return Objects.hash(new Object[] { Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.sw1))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.sw2))), Integer.valueOf(HidlSupport.deepHashCode(this.simResponse)) });
  }
  
  public final void readEmbeddedFromParcel(HwParcel paramHwParcel, HwBlob paramHwBlob, long paramLong) {
    this.sw1 = paramHwBlob.getInt32(paramLong + 0L);
    this.sw2 = paramHwBlob.getInt32(paramLong + 4L);
    String str = paramHwBlob.getString(paramLong + 8L);
    this.simResponse = str;
    paramHwParcel.readEmbeddedBuffer(((str.getBytes()).length + 1), paramHwBlob.handle(), paramLong + 8L + 0L, false);
  }
  
  public final void readFromParcel(HwParcel paramHwParcel) {
    readEmbeddedFromParcel(paramHwParcel, paramHwParcel.readBuffer(24L), 0L);
  }
  
  public final String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("{");
    stringBuilder.append(".sw1 = ");
    stringBuilder.append(this.sw1);
    stringBuilder.append(", .sw2 = ");
    stringBuilder.append(this.sw2);
    stringBuilder.append(", .simResponse = ");
    stringBuilder.append(this.simResponse);
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public final void writeEmbeddedToBlob(HwBlob paramHwBlob, long paramLong) {
    paramHwBlob.putInt32(0L + paramLong, this.sw1);
    paramHwBlob.putInt32(4L + paramLong, this.sw2);
    paramHwBlob.putString(8L + paramLong, this.simResponse);
  }
  
  public final void writeToParcel(HwParcel paramHwParcel) {
    HwBlob hwBlob = new HwBlob(24);
    writeEmbeddedToBlob(hwBlob, 0L);
    paramHwParcel.writeBuffer(hwBlob);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/V1_0/IccIoResult.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */