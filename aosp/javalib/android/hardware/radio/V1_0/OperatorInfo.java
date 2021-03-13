package android.hardware.radio.V1_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

public final class OperatorInfo {
  public String alphaLong = new String();
  
  public String alphaShort = new String();
  
  public String operatorNumeric = new String();
  
  public int status = 0;
  
  public static final ArrayList<OperatorInfo> readVectorFromParcel(HwParcel paramHwParcel) {
    ArrayList<OperatorInfo> arrayList = new ArrayList();
    HwBlob hwBlob1 = paramHwParcel.readBuffer(16L);
    int i = hwBlob1.getInt32(8L);
    HwBlob hwBlob2 = paramHwParcel.readEmbeddedBuffer((i * 56), hwBlob1.handle(), 0L, true);
    arrayList.clear();
    for (byte b = 0; b < i; b++) {
      OperatorInfo operatorInfo = new OperatorInfo();
      operatorInfo.readEmbeddedFromParcel(paramHwParcel, hwBlob2, (b * 56));
      arrayList.add(operatorInfo);
    } 
    return arrayList;
  }
  
  public static final void writeVectorToParcel(HwParcel paramHwParcel, ArrayList<OperatorInfo> paramArrayList) {
    HwBlob hwBlob1 = new HwBlob(16);
    int i = paramArrayList.size();
    hwBlob1.putInt32(8L, i);
    hwBlob1.putBool(12L, false);
    HwBlob hwBlob2 = new HwBlob(i * 56);
    for (byte b = 0; b < i; b++)
      ((OperatorInfo)paramArrayList.get(b)).writeEmbeddedToBlob(hwBlob2, (b * 56)); 
    hwBlob1.putBlob(0L, hwBlob2);
    paramHwParcel.writeBuffer(hwBlob1);
  }
  
  public final boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (paramObject == null)
      return false; 
    if (paramObject.getClass() != OperatorInfo.class)
      return false; 
    paramObject = paramObject;
    return !HidlSupport.deepEquals(this.alphaLong, ((OperatorInfo)paramObject).alphaLong) ? false : (!HidlSupport.deepEquals(this.alphaShort, ((OperatorInfo)paramObject).alphaShort) ? false : (!HidlSupport.deepEquals(this.operatorNumeric, ((OperatorInfo)paramObject).operatorNumeric) ? false : (!(this.status != ((OperatorInfo)paramObject).status))));
  }
  
  public final int hashCode() {
    return Objects.hash(new Object[] { Integer.valueOf(HidlSupport.deepHashCode(this.alphaLong)), Integer.valueOf(HidlSupport.deepHashCode(this.alphaShort)), Integer.valueOf(HidlSupport.deepHashCode(this.operatorNumeric)), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.status))) });
  }
  
  public final void readEmbeddedFromParcel(HwParcel paramHwParcel, HwBlob paramHwBlob, long paramLong) {
    String str = paramHwBlob.getString(paramLong + 0L);
    this.alphaLong = str;
    paramHwParcel.readEmbeddedBuffer(((str.getBytes()).length + 1), paramHwBlob.handle(), paramLong + 0L + 0L, false);
    str = paramHwBlob.getString(paramLong + 16L);
    this.alphaShort = str;
    paramHwParcel.readEmbeddedBuffer(((str.getBytes()).length + 1), paramHwBlob.handle(), paramLong + 16L + 0L, false);
    str = paramHwBlob.getString(paramLong + 32L);
    this.operatorNumeric = str;
    paramHwParcel.readEmbeddedBuffer(((str.getBytes()).length + 1), paramHwBlob.handle(), paramLong + 32L + 0L, false);
    this.status = paramHwBlob.getInt32(paramLong + 48L);
  }
  
  public final void readFromParcel(HwParcel paramHwParcel) {
    readEmbeddedFromParcel(paramHwParcel, paramHwParcel.readBuffer(56L), 0L);
  }
  
  public final String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("{");
    stringBuilder.append(".alphaLong = ");
    stringBuilder.append(this.alphaLong);
    stringBuilder.append(", .alphaShort = ");
    stringBuilder.append(this.alphaShort);
    stringBuilder.append(", .operatorNumeric = ");
    stringBuilder.append(this.operatorNumeric);
    stringBuilder.append(", .status = ");
    stringBuilder.append(OperatorStatus.toString(this.status));
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public final void writeEmbeddedToBlob(HwBlob paramHwBlob, long paramLong) {
    paramHwBlob.putString(0L + paramLong, this.alphaLong);
    paramHwBlob.putString(16L + paramLong, this.alphaShort);
    paramHwBlob.putString(32L + paramLong, this.operatorNumeric);
    paramHwBlob.putInt32(48L + paramLong, this.status);
  }
  
  public final void writeToParcel(HwParcel paramHwParcel) {
    HwBlob hwBlob = new HwBlob(56);
    writeEmbeddedToBlob(hwBlob, 0L);
    paramHwParcel.writeBuffer(hwBlob);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/V1_0/OperatorInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */