package android.hardware.radio.V1_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

public final class CdmaT53AudioControlInfoRecord {
  public byte downLink = (byte)0;
  
  public byte upLink = (byte)0;
  
  public static final ArrayList<CdmaT53AudioControlInfoRecord> readVectorFromParcel(HwParcel paramHwParcel) {
    ArrayList<CdmaT53AudioControlInfoRecord> arrayList = new ArrayList();
    HwBlob hwBlob1 = paramHwParcel.readBuffer(16L);
    int i = hwBlob1.getInt32(8L);
    HwBlob hwBlob2 = paramHwParcel.readEmbeddedBuffer((i * 2), hwBlob1.handle(), 0L, true);
    arrayList.clear();
    for (byte b = 0; b < i; b++) {
      CdmaT53AudioControlInfoRecord cdmaT53AudioControlInfoRecord = new CdmaT53AudioControlInfoRecord();
      cdmaT53AudioControlInfoRecord.readEmbeddedFromParcel(paramHwParcel, hwBlob2, (b * 2));
      arrayList.add(cdmaT53AudioControlInfoRecord);
    } 
    return arrayList;
  }
  
  public static final void writeVectorToParcel(HwParcel paramHwParcel, ArrayList<CdmaT53AudioControlInfoRecord> paramArrayList) {
    HwBlob hwBlob1 = new HwBlob(16);
    int i = paramArrayList.size();
    hwBlob1.putInt32(8L, i);
    hwBlob1.putBool(12L, false);
    HwBlob hwBlob2 = new HwBlob(i * 2);
    for (byte b = 0; b < i; b++)
      ((CdmaT53AudioControlInfoRecord)paramArrayList.get(b)).writeEmbeddedToBlob(hwBlob2, (b * 2)); 
    hwBlob1.putBlob(0L, hwBlob2);
    paramHwParcel.writeBuffer(hwBlob1);
  }
  
  public final boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (paramObject == null)
      return false; 
    if (paramObject.getClass() != CdmaT53AudioControlInfoRecord.class)
      return false; 
    paramObject = paramObject;
    return (this.upLink != ((CdmaT53AudioControlInfoRecord)paramObject).upLink) ? false : (!(this.downLink != ((CdmaT53AudioControlInfoRecord)paramObject).downLink));
  }
  
  public final int hashCode() {
    return Objects.hash(new Object[] { Integer.valueOf(HidlSupport.deepHashCode(Byte.valueOf(this.upLink))), Integer.valueOf(HidlSupport.deepHashCode(Byte.valueOf(this.downLink))) });
  }
  
  public final void readEmbeddedFromParcel(HwParcel paramHwParcel, HwBlob paramHwBlob, long paramLong) {
    this.upLink = paramHwBlob.getInt8(0L + paramLong);
    this.downLink = paramHwBlob.getInt8(1L + paramLong);
  }
  
  public final void readFromParcel(HwParcel paramHwParcel) {
    readEmbeddedFromParcel(paramHwParcel, paramHwParcel.readBuffer(2L), 0L);
  }
  
  public final String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("{");
    stringBuilder.append(".upLink = ");
    stringBuilder.append(this.upLink);
    stringBuilder.append(", .downLink = ");
    stringBuilder.append(this.downLink);
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public final void writeEmbeddedToBlob(HwBlob paramHwBlob, long paramLong) {
    paramHwBlob.putInt8(0L + paramLong, this.upLink);
    paramHwBlob.putInt8(1L + paramLong, this.downLink);
  }
  
  public final void writeToParcel(HwParcel paramHwParcel) {
    HwBlob hwBlob = new HwBlob(2);
    writeEmbeddedToBlob(hwBlob, 0L);
    paramHwParcel.writeBuffer(hwBlob);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/V1_0/CdmaT53AudioControlInfoRecord.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */