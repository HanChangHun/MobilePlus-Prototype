package android.hardware.radio.V1_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

public final class CdmaNumberInfoRecord {
  public String number = new String();
  
  public byte numberPlan = (byte)0;
  
  public byte numberType = (byte)0;
  
  public byte pi = (byte)0;
  
  public byte si = (byte)0;
  
  public static final ArrayList<CdmaNumberInfoRecord> readVectorFromParcel(HwParcel paramHwParcel) {
    ArrayList<CdmaNumberInfoRecord> arrayList = new ArrayList();
    HwBlob hwBlob = paramHwParcel.readBuffer(16L);
    int i = hwBlob.getInt32(8L);
    hwBlob = paramHwParcel.readEmbeddedBuffer((i * 24), hwBlob.handle(), 0L, true);
    arrayList.clear();
    for (byte b = 0; b < i; b++) {
      CdmaNumberInfoRecord cdmaNumberInfoRecord = new CdmaNumberInfoRecord();
      cdmaNumberInfoRecord.readEmbeddedFromParcel(paramHwParcel, hwBlob, (b * 24));
      arrayList.add(cdmaNumberInfoRecord);
    } 
    return arrayList;
  }
  
  public static final void writeVectorToParcel(HwParcel paramHwParcel, ArrayList<CdmaNumberInfoRecord> paramArrayList) {
    HwBlob hwBlob1 = new HwBlob(16);
    int i = paramArrayList.size();
    hwBlob1.putInt32(8L, i);
    hwBlob1.putBool(12L, false);
    HwBlob hwBlob2 = new HwBlob(i * 24);
    for (byte b = 0; b < i; b++)
      ((CdmaNumberInfoRecord)paramArrayList.get(b)).writeEmbeddedToBlob(hwBlob2, (b * 24)); 
    hwBlob1.putBlob(0L, hwBlob2);
    paramHwParcel.writeBuffer(hwBlob1);
  }
  
  public final boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (paramObject == null)
      return false; 
    if (paramObject.getClass() != CdmaNumberInfoRecord.class)
      return false; 
    paramObject = paramObject;
    return !HidlSupport.deepEquals(this.number, ((CdmaNumberInfoRecord)paramObject).number) ? false : ((this.numberType != ((CdmaNumberInfoRecord)paramObject).numberType) ? false : ((this.numberPlan != ((CdmaNumberInfoRecord)paramObject).numberPlan) ? false : ((this.pi != ((CdmaNumberInfoRecord)paramObject).pi) ? false : (!(this.si != ((CdmaNumberInfoRecord)paramObject).si)))));
  }
  
  public final int hashCode() {
    return Objects.hash(new Object[] { Integer.valueOf(HidlSupport.deepHashCode(this.number)), Integer.valueOf(HidlSupport.deepHashCode(Byte.valueOf(this.numberType))), Integer.valueOf(HidlSupport.deepHashCode(Byte.valueOf(this.numberPlan))), Integer.valueOf(HidlSupport.deepHashCode(Byte.valueOf(this.pi))), Integer.valueOf(HidlSupport.deepHashCode(Byte.valueOf(this.si))) });
  }
  
  public final void readEmbeddedFromParcel(HwParcel paramHwParcel, HwBlob paramHwBlob, long paramLong) {
    String str = paramHwBlob.getString(paramLong + 0L);
    this.number = str;
    paramHwParcel.readEmbeddedBuffer(((str.getBytes()).length + 1), paramHwBlob.handle(), paramLong + 0L + 0L, false);
    this.numberType = paramHwBlob.getInt8(16L + paramLong);
    this.numberPlan = paramHwBlob.getInt8(17L + paramLong);
    this.pi = paramHwBlob.getInt8(18L + paramLong);
    this.si = paramHwBlob.getInt8(19L + paramLong);
  }
  
  public final void readFromParcel(HwParcel paramHwParcel) {
    readEmbeddedFromParcel(paramHwParcel, paramHwParcel.readBuffer(24L), 0L);
  }
  
  public final String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("{");
    stringBuilder.append(".number = ");
    stringBuilder.append(this.number);
    stringBuilder.append(", .numberType = ");
    stringBuilder.append(this.numberType);
    stringBuilder.append(", .numberPlan = ");
    stringBuilder.append(this.numberPlan);
    stringBuilder.append(", .pi = ");
    stringBuilder.append(this.pi);
    stringBuilder.append(", .si = ");
    stringBuilder.append(this.si);
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public final void writeEmbeddedToBlob(HwBlob paramHwBlob, long paramLong) {
    paramHwBlob.putString(0L + paramLong, this.number);
    paramHwBlob.putInt8(16L + paramLong, this.numberType);
    paramHwBlob.putInt8(17L + paramLong, this.numberPlan);
    paramHwBlob.putInt8(18L + paramLong, this.pi);
    paramHwBlob.putInt8(19L + paramLong, this.si);
  }
  
  public final void writeToParcel(HwParcel paramHwParcel) {
    HwBlob hwBlob = new HwBlob(24);
    writeEmbeddedToBlob(hwBlob, 0L);
    paramHwParcel.writeBuffer(hwBlob);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/V1_0/CdmaNumberInfoRecord.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */