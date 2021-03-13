package android.hardware.radio.V1_1;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

public final class KeepaliveStatus {
  public int code = 0;
  
  public int sessionHandle = 0;
  
  public static final ArrayList<KeepaliveStatus> readVectorFromParcel(HwParcel paramHwParcel) {
    ArrayList<KeepaliveStatus> arrayList = new ArrayList();
    HwBlob hwBlob1 = paramHwParcel.readBuffer(16L);
    int i = hwBlob1.getInt32(8L);
    HwBlob hwBlob2 = paramHwParcel.readEmbeddedBuffer((i * 8), hwBlob1.handle(), 0L, true);
    arrayList.clear();
    for (byte b = 0; b < i; b++) {
      KeepaliveStatus keepaliveStatus = new KeepaliveStatus();
      keepaliveStatus.readEmbeddedFromParcel(paramHwParcel, hwBlob2, (b * 8));
      arrayList.add(keepaliveStatus);
    } 
    return arrayList;
  }
  
  public static final void writeVectorToParcel(HwParcel paramHwParcel, ArrayList<KeepaliveStatus> paramArrayList) {
    HwBlob hwBlob1 = new HwBlob(16);
    int i = paramArrayList.size();
    hwBlob1.putInt32(8L, i);
    hwBlob1.putBool(12L, false);
    HwBlob hwBlob2 = new HwBlob(i * 8);
    for (byte b = 0; b < i; b++)
      ((KeepaliveStatus)paramArrayList.get(b)).writeEmbeddedToBlob(hwBlob2, (b * 8)); 
    hwBlob1.putBlob(0L, hwBlob2);
    paramHwParcel.writeBuffer(hwBlob1);
  }
  
  public final boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (paramObject == null)
      return false; 
    if (paramObject.getClass() != KeepaliveStatus.class)
      return false; 
    paramObject = paramObject;
    return (this.sessionHandle != ((KeepaliveStatus)paramObject).sessionHandle) ? false : (!(this.code != ((KeepaliveStatus)paramObject).code));
  }
  
  public final int hashCode() {
    return Objects.hash(new Object[] { Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.sessionHandle))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.code))) });
  }
  
  public final void readEmbeddedFromParcel(HwParcel paramHwParcel, HwBlob paramHwBlob, long paramLong) {
    this.sessionHandle = paramHwBlob.getInt32(0L + paramLong);
    this.code = paramHwBlob.getInt32(4L + paramLong);
  }
  
  public final void readFromParcel(HwParcel paramHwParcel) {
    readEmbeddedFromParcel(paramHwParcel, paramHwParcel.readBuffer(8L), 0L);
  }
  
  public final String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("{");
    stringBuilder.append(".sessionHandle = ");
    stringBuilder.append(this.sessionHandle);
    stringBuilder.append(", .code = ");
    stringBuilder.append(KeepaliveStatusCode.toString(this.code));
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public final void writeEmbeddedToBlob(HwBlob paramHwBlob, long paramLong) {
    paramHwBlob.putInt32(0L + paramLong, this.sessionHandle);
    paramHwBlob.putInt32(4L + paramLong, this.code);
  }
  
  public final void writeToParcel(HwParcel paramHwParcel) {
    HwBlob hwBlob = new HwBlob(8);
    writeEmbeddedToBlob(hwBlob, 0L);
    paramHwParcel.writeBuffer(hwBlob);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/V1_1/KeepaliveStatus.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */