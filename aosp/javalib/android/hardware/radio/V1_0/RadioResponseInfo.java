package android.hardware.radio.V1_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

public final class RadioResponseInfo {
  public int error = 0;
  
  public int serial = 0;
  
  public int type = 0;
  
  public static final ArrayList<RadioResponseInfo> readVectorFromParcel(HwParcel paramHwParcel) {
    ArrayList<RadioResponseInfo> arrayList = new ArrayList();
    HwBlob hwBlob = paramHwParcel.readBuffer(16L);
    int i = hwBlob.getInt32(8L);
    hwBlob = paramHwParcel.readEmbeddedBuffer((i * 12), hwBlob.handle(), 0L, true);
    arrayList.clear();
    for (byte b = 0; b < i; b++) {
      RadioResponseInfo radioResponseInfo = new RadioResponseInfo();
      radioResponseInfo.readEmbeddedFromParcel(paramHwParcel, hwBlob, (b * 12));
      arrayList.add(radioResponseInfo);
    } 
    return arrayList;
  }
  
  public static final void writeVectorToParcel(HwParcel paramHwParcel, ArrayList<RadioResponseInfo> paramArrayList) {
    HwBlob hwBlob1 = new HwBlob(16);
    int i = paramArrayList.size();
    hwBlob1.putInt32(8L, i);
    hwBlob1.putBool(12L, false);
    HwBlob hwBlob2 = new HwBlob(i * 12);
    for (byte b = 0; b < i; b++)
      ((RadioResponseInfo)paramArrayList.get(b)).writeEmbeddedToBlob(hwBlob2, (b * 12)); 
    hwBlob1.putBlob(0L, hwBlob2);
    paramHwParcel.writeBuffer(hwBlob1);
  }
  
  public final boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (paramObject == null)
      return false; 
    if (paramObject.getClass() != RadioResponseInfo.class)
      return false; 
    paramObject = paramObject;
    return (this.type != ((RadioResponseInfo)paramObject).type) ? false : ((this.serial != ((RadioResponseInfo)paramObject).serial) ? false : (!(this.error != ((RadioResponseInfo)paramObject).error)));
  }
  
  public final int hashCode() {
    return Objects.hash(new Object[] { Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.type))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.serial))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.error))) });
  }
  
  public final void readEmbeddedFromParcel(HwParcel paramHwParcel, HwBlob paramHwBlob, long paramLong) {
    this.type = paramHwBlob.getInt32(0L + paramLong);
    this.serial = paramHwBlob.getInt32(4L + paramLong);
    this.error = paramHwBlob.getInt32(8L + paramLong);
  }
  
  public final void readFromParcel(HwParcel paramHwParcel) {
    readEmbeddedFromParcel(paramHwParcel, paramHwParcel.readBuffer(12L), 0L);
  }
  
  public final String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("{");
    stringBuilder.append(".type = ");
    stringBuilder.append(RadioResponseType.toString(this.type));
    stringBuilder.append(", .serial = ");
    stringBuilder.append(this.serial);
    stringBuilder.append(", .error = ");
    stringBuilder.append(RadioError.toString(this.error));
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public final void writeEmbeddedToBlob(HwBlob paramHwBlob, long paramLong) {
    paramHwBlob.putInt32(0L + paramLong, this.type);
    paramHwBlob.putInt32(4L + paramLong, this.serial);
    paramHwBlob.putInt32(8L + paramLong, this.error);
  }
  
  public final void writeToParcel(HwParcel paramHwParcel) {
    HwBlob hwBlob = new HwBlob(12);
    writeEmbeddedToBlob(hwBlob, 0L);
    paramHwParcel.writeBuffer(hwBlob);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/V1_0/RadioResponseInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */