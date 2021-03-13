package android.hardware.radio.V1_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

public final class CdmaLineControlInfoRecord {
  public byte lineCtrlPolarityIncluded = (byte)0;
  
  public byte lineCtrlPowerDenial = (byte)0;
  
  public byte lineCtrlReverse = (byte)0;
  
  public byte lineCtrlToggle = (byte)0;
  
  public static final ArrayList<CdmaLineControlInfoRecord> readVectorFromParcel(HwParcel paramHwParcel) {
    ArrayList<CdmaLineControlInfoRecord> arrayList = new ArrayList();
    HwBlob hwBlob = paramHwParcel.readBuffer(16L);
    int i = hwBlob.getInt32(8L);
    hwBlob = paramHwParcel.readEmbeddedBuffer((i * 4), hwBlob.handle(), 0L, true);
    arrayList.clear();
    for (byte b = 0; b < i; b++) {
      CdmaLineControlInfoRecord cdmaLineControlInfoRecord = new CdmaLineControlInfoRecord();
      cdmaLineControlInfoRecord.readEmbeddedFromParcel(paramHwParcel, hwBlob, (b * 4));
      arrayList.add(cdmaLineControlInfoRecord);
    } 
    return arrayList;
  }
  
  public static final void writeVectorToParcel(HwParcel paramHwParcel, ArrayList<CdmaLineControlInfoRecord> paramArrayList) {
    HwBlob hwBlob1 = new HwBlob(16);
    int i = paramArrayList.size();
    hwBlob1.putInt32(8L, i);
    hwBlob1.putBool(12L, false);
    HwBlob hwBlob2 = new HwBlob(i * 4);
    for (byte b = 0; b < i; b++)
      ((CdmaLineControlInfoRecord)paramArrayList.get(b)).writeEmbeddedToBlob(hwBlob2, (b * 4)); 
    hwBlob1.putBlob(0L, hwBlob2);
    paramHwParcel.writeBuffer(hwBlob1);
  }
  
  public final boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (paramObject == null)
      return false; 
    if (paramObject.getClass() != CdmaLineControlInfoRecord.class)
      return false; 
    paramObject = paramObject;
    return (this.lineCtrlPolarityIncluded != ((CdmaLineControlInfoRecord)paramObject).lineCtrlPolarityIncluded) ? false : ((this.lineCtrlToggle != ((CdmaLineControlInfoRecord)paramObject).lineCtrlToggle) ? false : ((this.lineCtrlReverse != ((CdmaLineControlInfoRecord)paramObject).lineCtrlReverse) ? false : (!(this.lineCtrlPowerDenial != ((CdmaLineControlInfoRecord)paramObject).lineCtrlPowerDenial))));
  }
  
  public final int hashCode() {
    return Objects.hash(new Object[] { Integer.valueOf(HidlSupport.deepHashCode(Byte.valueOf(this.lineCtrlPolarityIncluded))), Integer.valueOf(HidlSupport.deepHashCode(Byte.valueOf(this.lineCtrlToggle))), Integer.valueOf(HidlSupport.deepHashCode(Byte.valueOf(this.lineCtrlReverse))), Integer.valueOf(HidlSupport.deepHashCode(Byte.valueOf(this.lineCtrlPowerDenial))) });
  }
  
  public final void readEmbeddedFromParcel(HwParcel paramHwParcel, HwBlob paramHwBlob, long paramLong) {
    this.lineCtrlPolarityIncluded = paramHwBlob.getInt8(0L + paramLong);
    this.lineCtrlToggle = paramHwBlob.getInt8(1L + paramLong);
    this.lineCtrlReverse = paramHwBlob.getInt8(2L + paramLong);
    this.lineCtrlPowerDenial = paramHwBlob.getInt8(3L + paramLong);
  }
  
  public final void readFromParcel(HwParcel paramHwParcel) {
    readEmbeddedFromParcel(paramHwParcel, paramHwParcel.readBuffer(4L), 0L);
  }
  
  public final String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("{");
    stringBuilder.append(".lineCtrlPolarityIncluded = ");
    stringBuilder.append(this.lineCtrlPolarityIncluded);
    stringBuilder.append(", .lineCtrlToggle = ");
    stringBuilder.append(this.lineCtrlToggle);
    stringBuilder.append(", .lineCtrlReverse = ");
    stringBuilder.append(this.lineCtrlReverse);
    stringBuilder.append(", .lineCtrlPowerDenial = ");
    stringBuilder.append(this.lineCtrlPowerDenial);
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public final void writeEmbeddedToBlob(HwBlob paramHwBlob, long paramLong) {
    paramHwBlob.putInt8(0L + paramLong, this.lineCtrlPolarityIncluded);
    paramHwBlob.putInt8(1L + paramLong, this.lineCtrlToggle);
    paramHwBlob.putInt8(2L + paramLong, this.lineCtrlReverse);
    paramHwBlob.putInt8(3L + paramLong, this.lineCtrlPowerDenial);
  }
  
  public final void writeToParcel(HwParcel paramHwParcel) {
    HwBlob hwBlob = new HwBlob(4);
    writeEmbeddedToBlob(hwBlob, 0L);
    paramHwParcel.writeBuffer(hwBlob);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/V1_0/CdmaLineControlInfoRecord.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */