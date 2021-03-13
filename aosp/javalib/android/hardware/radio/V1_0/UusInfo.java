package android.hardware.radio.V1_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

public final class UusInfo {
  public String uusData = new String();
  
  public int uusDcs = 0;
  
  public int uusType = 0;
  
  public static final ArrayList<UusInfo> readVectorFromParcel(HwParcel paramHwParcel) {
    ArrayList<UusInfo> arrayList = new ArrayList();
    HwBlob hwBlob = paramHwParcel.readBuffer(16L);
    int i = hwBlob.getInt32(8L);
    hwBlob = paramHwParcel.readEmbeddedBuffer((i * 24), hwBlob.handle(), 0L, true);
    arrayList.clear();
    for (byte b = 0; b < i; b++) {
      UusInfo uusInfo = new UusInfo();
      uusInfo.readEmbeddedFromParcel(paramHwParcel, hwBlob, (b * 24));
      arrayList.add(uusInfo);
    } 
    return arrayList;
  }
  
  public static final void writeVectorToParcel(HwParcel paramHwParcel, ArrayList<UusInfo> paramArrayList) {
    HwBlob hwBlob1 = new HwBlob(16);
    int i = paramArrayList.size();
    hwBlob1.putInt32(8L, i);
    hwBlob1.putBool(12L, false);
    HwBlob hwBlob2 = new HwBlob(i * 24);
    for (byte b = 0; b < i; b++)
      ((UusInfo)paramArrayList.get(b)).writeEmbeddedToBlob(hwBlob2, (b * 24)); 
    hwBlob1.putBlob(0L, hwBlob2);
    paramHwParcel.writeBuffer(hwBlob1);
  }
  
  public final boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (paramObject == null)
      return false; 
    if (paramObject.getClass() != UusInfo.class)
      return false; 
    paramObject = paramObject;
    return (this.uusType != ((UusInfo)paramObject).uusType) ? false : ((this.uusDcs != ((UusInfo)paramObject).uusDcs) ? false : (!!HidlSupport.deepEquals(this.uusData, ((UusInfo)paramObject).uusData)));
  }
  
  public final int hashCode() {
    return Objects.hash(new Object[] { Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.uusType))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.uusDcs))), Integer.valueOf(HidlSupport.deepHashCode(this.uusData)) });
  }
  
  public final void readEmbeddedFromParcel(HwParcel paramHwParcel, HwBlob paramHwBlob, long paramLong) {
    this.uusType = paramHwBlob.getInt32(paramLong + 0L);
    this.uusDcs = paramHwBlob.getInt32(paramLong + 4L);
    String str = paramHwBlob.getString(paramLong + 8L);
    this.uusData = str;
    paramHwParcel.readEmbeddedBuffer(((str.getBytes()).length + 1), paramHwBlob.handle(), paramLong + 8L + 0L, false);
  }
  
  public final void readFromParcel(HwParcel paramHwParcel) {
    readEmbeddedFromParcel(paramHwParcel, paramHwParcel.readBuffer(24L), 0L);
  }
  
  public final String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("{");
    stringBuilder.append(".uusType = ");
    stringBuilder.append(UusType.toString(this.uusType));
    stringBuilder.append(", .uusDcs = ");
    stringBuilder.append(UusDcs.toString(this.uusDcs));
    stringBuilder.append(", .uusData = ");
    stringBuilder.append(this.uusData);
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public final void writeEmbeddedToBlob(HwBlob paramHwBlob, long paramLong) {
    paramHwBlob.putInt32(0L + paramLong, this.uusType);
    paramHwBlob.putInt32(4L + paramLong, this.uusDcs);
    paramHwBlob.putString(8L + paramLong, this.uusData);
  }
  
  public final void writeToParcel(HwParcel paramHwParcel) {
    HwBlob hwBlob = new HwBlob(24);
    writeEmbeddedToBlob(hwBlob, 0L);
    paramHwParcel.writeBuffer(hwBlob);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/V1_0/UusInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */