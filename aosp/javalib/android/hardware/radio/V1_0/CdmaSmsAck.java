package android.hardware.radio.V1_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

public final class CdmaSmsAck {
  public int errorClass = 0;
  
  public int smsCauseCode = 0;
  
  public static final ArrayList<CdmaSmsAck> readVectorFromParcel(HwParcel paramHwParcel) {
    ArrayList<CdmaSmsAck> arrayList = new ArrayList();
    HwBlob hwBlob = paramHwParcel.readBuffer(16L);
    int i = hwBlob.getInt32(8L);
    hwBlob = paramHwParcel.readEmbeddedBuffer((i * 8), hwBlob.handle(), 0L, true);
    arrayList.clear();
    for (byte b = 0; b < i; b++) {
      CdmaSmsAck cdmaSmsAck = new CdmaSmsAck();
      cdmaSmsAck.readEmbeddedFromParcel(paramHwParcel, hwBlob, (b * 8));
      arrayList.add(cdmaSmsAck);
    } 
    return arrayList;
  }
  
  public static final void writeVectorToParcel(HwParcel paramHwParcel, ArrayList<CdmaSmsAck> paramArrayList) {
    HwBlob hwBlob1 = new HwBlob(16);
    int i = paramArrayList.size();
    hwBlob1.putInt32(8L, i);
    hwBlob1.putBool(12L, false);
    HwBlob hwBlob2 = new HwBlob(i * 8);
    for (byte b = 0; b < i; b++)
      ((CdmaSmsAck)paramArrayList.get(b)).writeEmbeddedToBlob(hwBlob2, (b * 8)); 
    hwBlob1.putBlob(0L, hwBlob2);
    paramHwParcel.writeBuffer(hwBlob1);
  }
  
  public final boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (paramObject == null)
      return false; 
    if (paramObject.getClass() != CdmaSmsAck.class)
      return false; 
    paramObject = paramObject;
    return (this.errorClass != ((CdmaSmsAck)paramObject).errorClass) ? false : (!(this.smsCauseCode != ((CdmaSmsAck)paramObject).smsCauseCode));
  }
  
  public final int hashCode() {
    return Objects.hash(new Object[] { Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.errorClass))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.smsCauseCode))) });
  }
  
  public final void readEmbeddedFromParcel(HwParcel paramHwParcel, HwBlob paramHwBlob, long paramLong) {
    this.errorClass = paramHwBlob.getInt32(0L + paramLong);
    this.smsCauseCode = paramHwBlob.getInt32(4L + paramLong);
  }
  
  public final void readFromParcel(HwParcel paramHwParcel) {
    readEmbeddedFromParcel(paramHwParcel, paramHwParcel.readBuffer(8L), 0L);
  }
  
  public final String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("{");
    stringBuilder.append(".errorClass = ");
    stringBuilder.append(CdmaSmsErrorClass.toString(this.errorClass));
    stringBuilder.append(", .smsCauseCode = ");
    stringBuilder.append(this.smsCauseCode);
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public final void writeEmbeddedToBlob(HwBlob paramHwBlob, long paramLong) {
    paramHwBlob.putInt32(0L + paramLong, this.errorClass);
    paramHwBlob.putInt32(4L + paramLong, this.smsCauseCode);
  }
  
  public final void writeToParcel(HwParcel paramHwParcel) {
    HwBlob hwBlob = new HwBlob(8);
    writeEmbeddedToBlob(hwBlob, 0L);
    paramHwParcel.writeBuffer(hwBlob);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/V1_0/CdmaSmsAck.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */