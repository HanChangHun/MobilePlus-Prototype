package android.hardware.radio.V1_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

public final class CdmaSignalInfoRecord {
  public byte alertPitch = (byte)0;
  
  public boolean isPresent = false;
  
  public byte signal = (byte)0;
  
  public byte signalType = (byte)0;
  
  public static final ArrayList<CdmaSignalInfoRecord> readVectorFromParcel(HwParcel paramHwParcel) {
    ArrayList<CdmaSignalInfoRecord> arrayList = new ArrayList();
    HwBlob hwBlob1 = paramHwParcel.readBuffer(16L);
    int i = hwBlob1.getInt32(8L);
    HwBlob hwBlob2 = paramHwParcel.readEmbeddedBuffer((i * 4), hwBlob1.handle(), 0L, true);
    arrayList.clear();
    for (byte b = 0; b < i; b++) {
      CdmaSignalInfoRecord cdmaSignalInfoRecord = new CdmaSignalInfoRecord();
      cdmaSignalInfoRecord.readEmbeddedFromParcel(paramHwParcel, hwBlob2, (b * 4));
      arrayList.add(cdmaSignalInfoRecord);
    } 
    return arrayList;
  }
  
  public static final void writeVectorToParcel(HwParcel paramHwParcel, ArrayList<CdmaSignalInfoRecord> paramArrayList) {
    HwBlob hwBlob1 = new HwBlob(16);
    int i = paramArrayList.size();
    hwBlob1.putInt32(8L, i);
    hwBlob1.putBool(12L, false);
    HwBlob hwBlob2 = new HwBlob(i * 4);
    for (byte b = 0; b < i; b++)
      ((CdmaSignalInfoRecord)paramArrayList.get(b)).writeEmbeddedToBlob(hwBlob2, (b * 4)); 
    hwBlob1.putBlob(0L, hwBlob2);
    paramHwParcel.writeBuffer(hwBlob1);
  }
  
  public final boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (paramObject == null)
      return false; 
    if (paramObject.getClass() != CdmaSignalInfoRecord.class)
      return false; 
    paramObject = paramObject;
    return (this.isPresent != ((CdmaSignalInfoRecord)paramObject).isPresent) ? false : ((this.signalType != ((CdmaSignalInfoRecord)paramObject).signalType) ? false : ((this.alertPitch != ((CdmaSignalInfoRecord)paramObject).alertPitch) ? false : (!(this.signal != ((CdmaSignalInfoRecord)paramObject).signal))));
  }
  
  public final int hashCode() {
    return Objects.hash(new Object[] { Integer.valueOf(HidlSupport.deepHashCode(Boolean.valueOf(this.isPresent))), Integer.valueOf(HidlSupport.deepHashCode(Byte.valueOf(this.signalType))), Integer.valueOf(HidlSupport.deepHashCode(Byte.valueOf(this.alertPitch))), Integer.valueOf(HidlSupport.deepHashCode(Byte.valueOf(this.signal))) });
  }
  
  public final void readEmbeddedFromParcel(HwParcel paramHwParcel, HwBlob paramHwBlob, long paramLong) {
    this.isPresent = paramHwBlob.getBool(0L + paramLong);
    this.signalType = paramHwBlob.getInt8(1L + paramLong);
    this.alertPitch = paramHwBlob.getInt8(2L + paramLong);
    this.signal = paramHwBlob.getInt8(3L + paramLong);
  }
  
  public final void readFromParcel(HwParcel paramHwParcel) {
    readEmbeddedFromParcel(paramHwParcel, paramHwParcel.readBuffer(4L), 0L);
  }
  
  public final String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("{");
    stringBuilder.append(".isPresent = ");
    stringBuilder.append(this.isPresent);
    stringBuilder.append(", .signalType = ");
    stringBuilder.append(this.signalType);
    stringBuilder.append(", .alertPitch = ");
    stringBuilder.append(this.alertPitch);
    stringBuilder.append(", .signal = ");
    stringBuilder.append(this.signal);
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public final void writeEmbeddedToBlob(HwBlob paramHwBlob, long paramLong) {
    paramHwBlob.putBool(0L + paramLong, this.isPresent);
    paramHwBlob.putInt8(1L + paramLong, this.signalType);
    paramHwBlob.putInt8(2L + paramLong, this.alertPitch);
    paramHwBlob.putInt8(3L + paramLong, this.signal);
  }
  
  public final void writeToParcel(HwParcel paramHwParcel) {
    HwBlob hwBlob = new HwBlob(4);
    writeEmbeddedToBlob(hwBlob, 0L);
    paramHwParcel.writeBuffer(hwBlob);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/V1_0/CdmaSignalInfoRecord.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */