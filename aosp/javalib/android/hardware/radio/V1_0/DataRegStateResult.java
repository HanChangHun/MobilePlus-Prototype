package android.hardware.radio.V1_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

public final class DataRegStateResult {
  public CellIdentity cellIdentity = new CellIdentity();
  
  public int maxDataCalls = 0;
  
  public int rat = 0;
  
  public int reasonDataDenied = 0;
  
  public int regState = 0;
  
  public static final ArrayList<DataRegStateResult> readVectorFromParcel(HwParcel paramHwParcel) {
    ArrayList<DataRegStateResult> arrayList = new ArrayList();
    HwBlob hwBlob = paramHwParcel.readBuffer(16L);
    int i = hwBlob.getInt32(8L);
    hwBlob = paramHwParcel.readEmbeddedBuffer((i * 104), hwBlob.handle(), 0L, true);
    arrayList.clear();
    for (byte b = 0; b < i; b++) {
      DataRegStateResult dataRegStateResult = new DataRegStateResult();
      dataRegStateResult.readEmbeddedFromParcel(paramHwParcel, hwBlob, (b * 104));
      arrayList.add(dataRegStateResult);
    } 
    return arrayList;
  }
  
  public static final void writeVectorToParcel(HwParcel paramHwParcel, ArrayList<DataRegStateResult> paramArrayList) {
    HwBlob hwBlob1 = new HwBlob(16);
    int i = paramArrayList.size();
    hwBlob1.putInt32(8L, i);
    hwBlob1.putBool(12L, false);
    HwBlob hwBlob2 = new HwBlob(i * 104);
    for (byte b = 0; b < i; b++)
      ((DataRegStateResult)paramArrayList.get(b)).writeEmbeddedToBlob(hwBlob2, (b * 104)); 
    hwBlob1.putBlob(0L, hwBlob2);
    paramHwParcel.writeBuffer(hwBlob1);
  }
  
  public final boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (paramObject == null)
      return false; 
    if (paramObject.getClass() != DataRegStateResult.class)
      return false; 
    paramObject = paramObject;
    return (this.regState != ((DataRegStateResult)paramObject).regState) ? false : ((this.rat != ((DataRegStateResult)paramObject).rat) ? false : ((this.reasonDataDenied != ((DataRegStateResult)paramObject).reasonDataDenied) ? false : ((this.maxDataCalls != ((DataRegStateResult)paramObject).maxDataCalls) ? false : (!!HidlSupport.deepEquals(this.cellIdentity, ((DataRegStateResult)paramObject).cellIdentity)))));
  }
  
  public final int hashCode() {
    return Objects.hash(new Object[] { Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.regState))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.rat))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.reasonDataDenied))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.maxDataCalls))), Integer.valueOf(HidlSupport.deepHashCode(this.cellIdentity)) });
  }
  
  public final void readEmbeddedFromParcel(HwParcel paramHwParcel, HwBlob paramHwBlob, long paramLong) {
    this.regState = paramHwBlob.getInt32(0L + paramLong);
    this.rat = paramHwBlob.getInt32(4L + paramLong);
    this.reasonDataDenied = paramHwBlob.getInt32(8L + paramLong);
    this.maxDataCalls = paramHwBlob.getInt32(12L + paramLong);
    this.cellIdentity.readEmbeddedFromParcel(paramHwParcel, paramHwBlob, 16L + paramLong);
  }
  
  public final void readFromParcel(HwParcel paramHwParcel) {
    readEmbeddedFromParcel(paramHwParcel, paramHwParcel.readBuffer(104L), 0L);
  }
  
  public final String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("{");
    stringBuilder.append(".regState = ");
    stringBuilder.append(RegState.toString(this.regState));
    stringBuilder.append(", .rat = ");
    stringBuilder.append(this.rat);
    stringBuilder.append(", .reasonDataDenied = ");
    stringBuilder.append(this.reasonDataDenied);
    stringBuilder.append(", .maxDataCalls = ");
    stringBuilder.append(this.maxDataCalls);
    stringBuilder.append(", .cellIdentity = ");
    stringBuilder.append(this.cellIdentity);
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public final void writeEmbeddedToBlob(HwBlob paramHwBlob, long paramLong) {
    paramHwBlob.putInt32(0L + paramLong, this.regState);
    paramHwBlob.putInt32(4L + paramLong, this.rat);
    paramHwBlob.putInt32(8L + paramLong, this.reasonDataDenied);
    paramHwBlob.putInt32(12L + paramLong, this.maxDataCalls);
    this.cellIdentity.writeEmbeddedToBlob(paramHwBlob, 16L + paramLong);
  }
  
  public final void writeToParcel(HwParcel paramHwParcel) {
    HwBlob hwBlob = new HwBlob(104);
    writeEmbeddedToBlob(hwBlob, 0L);
    paramHwParcel.writeBuffer(hwBlob);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/V1_0/DataRegStateResult.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */