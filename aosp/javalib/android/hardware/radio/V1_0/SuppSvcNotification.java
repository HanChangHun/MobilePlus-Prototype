package android.hardware.radio.V1_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

public final class SuppSvcNotification {
  public int code = 0;
  
  public int index = 0;
  
  public boolean isMT = false;
  
  public String number = new String();
  
  public int type = 0;
  
  public static final ArrayList<SuppSvcNotification> readVectorFromParcel(HwParcel paramHwParcel) {
    ArrayList<SuppSvcNotification> arrayList = new ArrayList();
    HwBlob hwBlob = paramHwParcel.readBuffer(16L);
    int i = hwBlob.getInt32(8L);
    hwBlob = paramHwParcel.readEmbeddedBuffer((i * 32), hwBlob.handle(), 0L, true);
    arrayList.clear();
    for (byte b = 0; b < i; b++) {
      SuppSvcNotification suppSvcNotification = new SuppSvcNotification();
      suppSvcNotification.readEmbeddedFromParcel(paramHwParcel, hwBlob, (b * 32));
      arrayList.add(suppSvcNotification);
    } 
    return arrayList;
  }
  
  public static final void writeVectorToParcel(HwParcel paramHwParcel, ArrayList<SuppSvcNotification> paramArrayList) {
    HwBlob hwBlob1 = new HwBlob(16);
    int i = paramArrayList.size();
    hwBlob1.putInt32(8L, i);
    hwBlob1.putBool(12L, false);
    HwBlob hwBlob2 = new HwBlob(i * 32);
    for (byte b = 0; b < i; b++)
      ((SuppSvcNotification)paramArrayList.get(b)).writeEmbeddedToBlob(hwBlob2, (b * 32)); 
    hwBlob1.putBlob(0L, hwBlob2);
    paramHwParcel.writeBuffer(hwBlob1);
  }
  
  public final boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (paramObject == null)
      return false; 
    if (paramObject.getClass() != SuppSvcNotification.class)
      return false; 
    paramObject = paramObject;
    return (this.isMT != ((SuppSvcNotification)paramObject).isMT) ? false : ((this.code != ((SuppSvcNotification)paramObject).code) ? false : ((this.index != ((SuppSvcNotification)paramObject).index) ? false : ((this.type != ((SuppSvcNotification)paramObject).type) ? false : (!!HidlSupport.deepEquals(this.number, ((SuppSvcNotification)paramObject).number)))));
  }
  
  public final int hashCode() {
    return Objects.hash(new Object[] { Integer.valueOf(HidlSupport.deepHashCode(Boolean.valueOf(this.isMT))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.code))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.index))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.type))), Integer.valueOf(HidlSupport.deepHashCode(this.number)) });
  }
  
  public final void readEmbeddedFromParcel(HwParcel paramHwParcel, HwBlob paramHwBlob, long paramLong) {
    this.isMT = paramHwBlob.getBool(paramLong + 0L);
    this.code = paramHwBlob.getInt32(paramLong + 4L);
    this.index = paramHwBlob.getInt32(paramLong + 8L);
    this.type = paramHwBlob.getInt32(paramLong + 12L);
    String str = paramHwBlob.getString(paramLong + 16L);
    this.number = str;
    paramHwParcel.readEmbeddedBuffer(((str.getBytes()).length + 1), paramHwBlob.handle(), paramLong + 16L + 0L, false);
  }
  
  public final void readFromParcel(HwParcel paramHwParcel) {
    readEmbeddedFromParcel(paramHwParcel, paramHwParcel.readBuffer(32L), 0L);
  }
  
  public final String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("{");
    stringBuilder.append(".isMT = ");
    stringBuilder.append(this.isMT);
    stringBuilder.append(", .code = ");
    stringBuilder.append(this.code);
    stringBuilder.append(", .index = ");
    stringBuilder.append(this.index);
    stringBuilder.append(", .type = ");
    stringBuilder.append(this.type);
    stringBuilder.append(", .number = ");
    stringBuilder.append(this.number);
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public final void writeEmbeddedToBlob(HwBlob paramHwBlob, long paramLong) {
    paramHwBlob.putBool(0L + paramLong, this.isMT);
    paramHwBlob.putInt32(4L + paramLong, this.code);
    paramHwBlob.putInt32(8L + paramLong, this.index);
    paramHwBlob.putInt32(12L + paramLong, this.type);
    paramHwBlob.putString(16L + paramLong, this.number);
  }
  
  public final void writeToParcel(HwParcel paramHwParcel) {
    HwBlob hwBlob = new HwBlob(32);
    writeEmbeddedToBlob(hwBlob, 0L);
    paramHwParcel.writeBuffer(hwBlob);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/V1_0/SuppSvcNotification.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */