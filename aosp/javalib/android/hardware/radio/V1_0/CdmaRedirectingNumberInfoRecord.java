package android.hardware.radio.V1_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

public final class CdmaRedirectingNumberInfoRecord {
  public CdmaNumberInfoRecord redirectingNumber = new CdmaNumberInfoRecord();
  
  public int redirectingReason = 0;
  
  public static final ArrayList<CdmaRedirectingNumberInfoRecord> readVectorFromParcel(HwParcel paramHwParcel) {
    ArrayList<CdmaRedirectingNumberInfoRecord> arrayList = new ArrayList();
    HwBlob hwBlob = paramHwParcel.readBuffer(16L);
    int i = hwBlob.getInt32(8L);
    hwBlob = paramHwParcel.readEmbeddedBuffer((i * 32), hwBlob.handle(), 0L, true);
    arrayList.clear();
    for (byte b = 0; b < i; b++) {
      CdmaRedirectingNumberInfoRecord cdmaRedirectingNumberInfoRecord = new CdmaRedirectingNumberInfoRecord();
      cdmaRedirectingNumberInfoRecord.readEmbeddedFromParcel(paramHwParcel, hwBlob, (b * 32));
      arrayList.add(cdmaRedirectingNumberInfoRecord);
    } 
    return arrayList;
  }
  
  public static final void writeVectorToParcel(HwParcel paramHwParcel, ArrayList<CdmaRedirectingNumberInfoRecord> paramArrayList) {
    HwBlob hwBlob1 = new HwBlob(16);
    int i = paramArrayList.size();
    hwBlob1.putInt32(8L, i);
    hwBlob1.putBool(12L, false);
    HwBlob hwBlob2 = new HwBlob(i * 32);
    for (byte b = 0; b < i; b++)
      ((CdmaRedirectingNumberInfoRecord)paramArrayList.get(b)).writeEmbeddedToBlob(hwBlob2, (b * 32)); 
    hwBlob1.putBlob(0L, hwBlob2);
    paramHwParcel.writeBuffer(hwBlob1);
  }
  
  public final boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (paramObject == null)
      return false; 
    if (paramObject.getClass() != CdmaRedirectingNumberInfoRecord.class)
      return false; 
    paramObject = paramObject;
    return !HidlSupport.deepEquals(this.redirectingNumber, ((CdmaRedirectingNumberInfoRecord)paramObject).redirectingNumber) ? false : (!(this.redirectingReason != ((CdmaRedirectingNumberInfoRecord)paramObject).redirectingReason));
  }
  
  public final int hashCode() {
    return Objects.hash(new Object[] { Integer.valueOf(HidlSupport.deepHashCode(this.redirectingNumber)), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.redirectingReason))) });
  }
  
  public final void readEmbeddedFromParcel(HwParcel paramHwParcel, HwBlob paramHwBlob, long paramLong) {
    this.redirectingNumber.readEmbeddedFromParcel(paramHwParcel, paramHwBlob, 0L + paramLong);
    this.redirectingReason = paramHwBlob.getInt32(24L + paramLong);
  }
  
  public final void readFromParcel(HwParcel paramHwParcel) {
    readEmbeddedFromParcel(paramHwParcel, paramHwParcel.readBuffer(32L), 0L);
  }
  
  public final String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("{");
    stringBuilder.append(".redirectingNumber = ");
    stringBuilder.append(this.redirectingNumber);
    stringBuilder.append(", .redirectingReason = ");
    stringBuilder.append(CdmaRedirectingReason.toString(this.redirectingReason));
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public final void writeEmbeddedToBlob(HwBlob paramHwBlob, long paramLong) {
    this.redirectingNumber.writeEmbeddedToBlob(paramHwBlob, 0L + paramLong);
    paramHwBlob.putInt32(24L + paramLong, this.redirectingReason);
  }
  
  public final void writeToParcel(HwParcel paramHwParcel) {
    HwBlob hwBlob = new HwBlob(32);
    writeEmbeddedToBlob(hwBlob, 0L);
    paramHwParcel.writeBuffer(hwBlob);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/V1_0/CdmaRedirectingNumberInfoRecord.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */