package android.hardware.radio.V1_2;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

public final class CellIdentityTdscdma {
  public android.hardware.radio.V1_0.CellIdentityTdscdma base = new android.hardware.radio.V1_0.CellIdentityTdscdma();
  
  public CellIdentityOperatorNames operatorNames = new CellIdentityOperatorNames();
  
  public int uarfcn = 0;
  
  public static final ArrayList<CellIdentityTdscdma> readVectorFromParcel(HwParcel paramHwParcel) {
    ArrayList<CellIdentityTdscdma> arrayList = new ArrayList();
    HwBlob hwBlob1 = paramHwParcel.readBuffer(16L);
    int i = hwBlob1.getInt32(8L);
    HwBlob hwBlob2 = paramHwParcel.readEmbeddedBuffer((i * 88), hwBlob1.handle(), 0L, true);
    arrayList.clear();
    for (byte b = 0; b < i; b++) {
      CellIdentityTdscdma cellIdentityTdscdma = new CellIdentityTdscdma();
      cellIdentityTdscdma.readEmbeddedFromParcel(paramHwParcel, hwBlob2, (b * 88));
      arrayList.add(cellIdentityTdscdma);
    } 
    return arrayList;
  }
  
  public static final void writeVectorToParcel(HwParcel paramHwParcel, ArrayList<CellIdentityTdscdma> paramArrayList) {
    HwBlob hwBlob1 = new HwBlob(16);
    int i = paramArrayList.size();
    hwBlob1.putInt32(8L, i);
    hwBlob1.putBool(12L, false);
    HwBlob hwBlob2 = new HwBlob(i * 88);
    for (byte b = 0; b < i; b++)
      ((CellIdentityTdscdma)paramArrayList.get(b)).writeEmbeddedToBlob(hwBlob2, (b * 88)); 
    hwBlob1.putBlob(0L, hwBlob2);
    paramHwParcel.writeBuffer(hwBlob1);
  }
  
  public final boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (paramObject == null)
      return false; 
    if (paramObject.getClass() != CellIdentityTdscdma.class)
      return false; 
    paramObject = paramObject;
    return !HidlSupport.deepEquals(this.base, ((CellIdentityTdscdma)paramObject).base) ? false : ((this.uarfcn != ((CellIdentityTdscdma)paramObject).uarfcn) ? false : (!!HidlSupport.deepEquals(this.operatorNames, ((CellIdentityTdscdma)paramObject).operatorNames)));
  }
  
  public final int hashCode() {
    return Objects.hash(new Object[] { Integer.valueOf(HidlSupport.deepHashCode(this.base)), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.uarfcn))), Integer.valueOf(HidlSupport.deepHashCode(this.operatorNames)) });
  }
  
  public final void readEmbeddedFromParcel(HwParcel paramHwParcel, HwBlob paramHwBlob, long paramLong) {
    this.base.readEmbeddedFromParcel(paramHwParcel, paramHwBlob, 0L + paramLong);
    this.uarfcn = paramHwBlob.getInt32(48L + paramLong);
    this.operatorNames.readEmbeddedFromParcel(paramHwParcel, paramHwBlob, 56L + paramLong);
  }
  
  public final void readFromParcel(HwParcel paramHwParcel) {
    readEmbeddedFromParcel(paramHwParcel, paramHwParcel.readBuffer(88L), 0L);
  }
  
  public final String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("{");
    stringBuilder.append(".base = ");
    stringBuilder.append(this.base);
    stringBuilder.append(", .uarfcn = ");
    stringBuilder.append(this.uarfcn);
    stringBuilder.append(", .operatorNames = ");
    stringBuilder.append(this.operatorNames);
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public final void writeEmbeddedToBlob(HwBlob paramHwBlob, long paramLong) {
    this.base.writeEmbeddedToBlob(paramHwBlob, 0L + paramLong);
    paramHwBlob.putInt32(48L + paramLong, this.uarfcn);
    this.operatorNames.writeEmbeddedToBlob(paramHwBlob, 56L + paramLong);
  }
  
  public final void writeToParcel(HwParcel paramHwParcel) {
    HwBlob hwBlob = new HwBlob(88);
    writeEmbeddedToBlob(hwBlob, 0L);
    paramHwParcel.writeBuffer(hwBlob);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/V1_2/CellIdentityTdscdma.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */