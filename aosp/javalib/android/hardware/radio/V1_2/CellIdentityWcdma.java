package android.hardware.radio.V1_2;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

public final class CellIdentityWcdma {
  public android.hardware.radio.V1_0.CellIdentityWcdma base = new android.hardware.radio.V1_0.CellIdentityWcdma();
  
  public CellIdentityOperatorNames operatorNames = new CellIdentityOperatorNames();
  
  public static final ArrayList<CellIdentityWcdma> readVectorFromParcel(HwParcel paramHwParcel) {
    ArrayList<CellIdentityWcdma> arrayList = new ArrayList();
    HwBlob hwBlob1 = paramHwParcel.readBuffer(16L);
    int i = hwBlob1.getInt32(8L);
    HwBlob hwBlob2 = paramHwParcel.readEmbeddedBuffer((i * 80), hwBlob1.handle(), 0L, true);
    arrayList.clear();
    for (byte b = 0; b < i; b++) {
      CellIdentityWcdma cellIdentityWcdma = new CellIdentityWcdma();
      cellIdentityWcdma.readEmbeddedFromParcel(paramHwParcel, hwBlob2, (b * 80));
      arrayList.add(cellIdentityWcdma);
    } 
    return arrayList;
  }
  
  public static final void writeVectorToParcel(HwParcel paramHwParcel, ArrayList<CellIdentityWcdma> paramArrayList) {
    HwBlob hwBlob1 = new HwBlob(16);
    int i = paramArrayList.size();
    hwBlob1.putInt32(8L, i);
    hwBlob1.putBool(12L, false);
    HwBlob hwBlob2 = new HwBlob(i * 80);
    for (byte b = 0; b < i; b++)
      ((CellIdentityWcdma)paramArrayList.get(b)).writeEmbeddedToBlob(hwBlob2, (b * 80)); 
    hwBlob1.putBlob(0L, hwBlob2);
    paramHwParcel.writeBuffer(hwBlob1);
  }
  
  public final boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (paramObject == null)
      return false; 
    if (paramObject.getClass() != CellIdentityWcdma.class)
      return false; 
    paramObject = paramObject;
    return !HidlSupport.deepEquals(this.base, ((CellIdentityWcdma)paramObject).base) ? false : (!!HidlSupport.deepEquals(this.operatorNames, ((CellIdentityWcdma)paramObject).operatorNames));
  }
  
  public final int hashCode() {
    return Objects.hash(new Object[] { Integer.valueOf(HidlSupport.deepHashCode(this.base)), Integer.valueOf(HidlSupport.deepHashCode(this.operatorNames)) });
  }
  
  public final void readEmbeddedFromParcel(HwParcel paramHwParcel, HwBlob paramHwBlob, long paramLong) {
    this.base.readEmbeddedFromParcel(paramHwParcel, paramHwBlob, 0L + paramLong);
    this.operatorNames.readEmbeddedFromParcel(paramHwParcel, paramHwBlob, 48L + paramLong);
  }
  
  public final void readFromParcel(HwParcel paramHwParcel) {
    readEmbeddedFromParcel(paramHwParcel, paramHwParcel.readBuffer(80L), 0L);
  }
  
  public final String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("{");
    stringBuilder.append(".base = ");
    stringBuilder.append(this.base);
    stringBuilder.append(", .operatorNames = ");
    stringBuilder.append(this.operatorNames);
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public final void writeEmbeddedToBlob(HwBlob paramHwBlob, long paramLong) {
    this.base.writeEmbeddedToBlob(paramHwBlob, 0L + paramLong);
    this.operatorNames.writeEmbeddedToBlob(paramHwBlob, 48L + paramLong);
  }
  
  public final void writeToParcel(HwParcel paramHwParcel) {
    HwBlob hwBlob = new HwBlob(80);
    writeEmbeddedToBlob(hwBlob, 0L);
    paramHwParcel.writeBuffer(hwBlob);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/V1_2/CellIdentityWcdma.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */