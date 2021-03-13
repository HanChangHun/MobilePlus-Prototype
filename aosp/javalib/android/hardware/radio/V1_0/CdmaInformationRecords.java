package android.hardware.radio.V1_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

public final class CdmaInformationRecords {
  public ArrayList<CdmaInformationRecord> infoRec = new ArrayList<>();
  
  public static final ArrayList<CdmaInformationRecords> readVectorFromParcel(HwParcel paramHwParcel) {
    ArrayList<CdmaInformationRecords> arrayList = new ArrayList();
    HwBlob hwBlob = paramHwParcel.readBuffer(16L);
    int i = hwBlob.getInt32(8L);
    hwBlob = paramHwParcel.readEmbeddedBuffer((i * 16), hwBlob.handle(), 0L, true);
    arrayList.clear();
    for (byte b = 0; b < i; b++) {
      CdmaInformationRecords cdmaInformationRecords = new CdmaInformationRecords();
      cdmaInformationRecords.readEmbeddedFromParcel(paramHwParcel, hwBlob, (b * 16));
      arrayList.add(cdmaInformationRecords);
    } 
    return arrayList;
  }
  
  public static final void writeVectorToParcel(HwParcel paramHwParcel, ArrayList<CdmaInformationRecords> paramArrayList) {
    HwBlob hwBlob1 = new HwBlob(16);
    int i = paramArrayList.size();
    hwBlob1.putInt32(8L, i);
    hwBlob1.putBool(12L, false);
    HwBlob hwBlob2 = new HwBlob(i * 16);
    for (byte b = 0; b < i; b++)
      ((CdmaInformationRecords)paramArrayList.get(b)).writeEmbeddedToBlob(hwBlob2, (b * 16)); 
    hwBlob1.putBlob(0L, hwBlob2);
    paramHwParcel.writeBuffer(hwBlob1);
  }
  
  public final boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (paramObject == null)
      return false; 
    if (paramObject.getClass() != CdmaInformationRecords.class)
      return false; 
    paramObject = paramObject;
    return !!HidlSupport.deepEquals(this.infoRec, ((CdmaInformationRecords)paramObject).infoRec);
  }
  
  public final int hashCode() {
    return Objects.hash(new Object[] { Integer.valueOf(HidlSupport.deepHashCode(this.infoRec)) });
  }
  
  public final void readEmbeddedFromParcel(HwParcel paramHwParcel, HwBlob paramHwBlob, long paramLong) {
    int i = paramHwBlob.getInt32(paramLong + 0L + 8L);
    HwBlob hwBlob = paramHwParcel.readEmbeddedBuffer((i * 120), paramHwBlob.handle(), paramLong + 0L + 0L, true);
    this.infoRec.clear();
    for (byte b = 0; b < i; b++) {
      CdmaInformationRecord cdmaInformationRecord = new CdmaInformationRecord();
      cdmaInformationRecord.readEmbeddedFromParcel(paramHwParcel, hwBlob, (b * 120));
      this.infoRec.add(cdmaInformationRecord);
    } 
  }
  
  public final void readFromParcel(HwParcel paramHwParcel) {
    readEmbeddedFromParcel(paramHwParcel, paramHwParcel.readBuffer(16L), 0L);
  }
  
  public final String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("{");
    stringBuilder.append(".infoRec = ");
    stringBuilder.append(this.infoRec);
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public final void writeEmbeddedToBlob(HwBlob paramHwBlob, long paramLong) {
    int i = this.infoRec.size();
    paramHwBlob.putInt32(paramLong + 0L + 8L, i);
    paramHwBlob.putBool(paramLong + 0L + 12L, false);
    HwBlob hwBlob = new HwBlob(i * 120);
    for (byte b = 0; b < i; b++)
      ((CdmaInformationRecord)this.infoRec.get(b)).writeEmbeddedToBlob(hwBlob, (b * 120)); 
    paramHwBlob.putBlob(paramLong + 0L + 0L, hwBlob);
  }
  
  public final void writeToParcel(HwParcel paramHwParcel) {
    HwBlob hwBlob = new HwBlob(16);
    writeEmbeddedToBlob(hwBlob, 0L);
    paramHwParcel.writeBuffer(hwBlob);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/V1_0/CdmaInformationRecords.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */