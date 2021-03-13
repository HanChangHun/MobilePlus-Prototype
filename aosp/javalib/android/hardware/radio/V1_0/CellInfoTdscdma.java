package android.hardware.radio.V1_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

public final class CellInfoTdscdma {
  public CellIdentityTdscdma cellIdentityTdscdma = new CellIdentityTdscdma();
  
  public TdScdmaSignalStrength signalStrengthTdscdma = new TdScdmaSignalStrength();
  
  public static final ArrayList<CellInfoTdscdma> readVectorFromParcel(HwParcel paramHwParcel) {
    ArrayList<CellInfoTdscdma> arrayList = new ArrayList();
    HwBlob hwBlob1 = paramHwParcel.readBuffer(16L);
    int i = hwBlob1.getInt32(8L);
    HwBlob hwBlob2 = paramHwParcel.readEmbeddedBuffer((i * 56), hwBlob1.handle(), 0L, true);
    arrayList.clear();
    for (byte b = 0; b < i; b++) {
      CellInfoTdscdma cellInfoTdscdma = new CellInfoTdscdma();
      cellInfoTdscdma.readEmbeddedFromParcel(paramHwParcel, hwBlob2, (b * 56));
      arrayList.add(cellInfoTdscdma);
    } 
    return arrayList;
  }
  
  public static final void writeVectorToParcel(HwParcel paramHwParcel, ArrayList<CellInfoTdscdma> paramArrayList) {
    HwBlob hwBlob1 = new HwBlob(16);
    int i = paramArrayList.size();
    hwBlob1.putInt32(8L, i);
    hwBlob1.putBool(12L, false);
    HwBlob hwBlob2 = new HwBlob(i * 56);
    for (byte b = 0; b < i; b++)
      ((CellInfoTdscdma)paramArrayList.get(b)).writeEmbeddedToBlob(hwBlob2, (b * 56)); 
    hwBlob1.putBlob(0L, hwBlob2);
    paramHwParcel.writeBuffer(hwBlob1);
  }
  
  public final boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (paramObject == null)
      return false; 
    if (paramObject.getClass() != CellInfoTdscdma.class)
      return false; 
    paramObject = paramObject;
    return !HidlSupport.deepEquals(this.cellIdentityTdscdma, ((CellInfoTdscdma)paramObject).cellIdentityTdscdma) ? false : (!!HidlSupport.deepEquals(this.signalStrengthTdscdma, ((CellInfoTdscdma)paramObject).signalStrengthTdscdma));
  }
  
  public final int hashCode() {
    return Objects.hash(new Object[] { Integer.valueOf(HidlSupport.deepHashCode(this.cellIdentityTdscdma)), Integer.valueOf(HidlSupport.deepHashCode(this.signalStrengthTdscdma)) });
  }
  
  public final void readEmbeddedFromParcel(HwParcel paramHwParcel, HwBlob paramHwBlob, long paramLong) {
    this.cellIdentityTdscdma.readEmbeddedFromParcel(paramHwParcel, paramHwBlob, 0L + paramLong);
    this.signalStrengthTdscdma.readEmbeddedFromParcel(paramHwParcel, paramHwBlob, 48L + paramLong);
  }
  
  public final void readFromParcel(HwParcel paramHwParcel) {
    readEmbeddedFromParcel(paramHwParcel, paramHwParcel.readBuffer(56L), 0L);
  }
  
  public final String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("{");
    stringBuilder.append(".cellIdentityTdscdma = ");
    stringBuilder.append(this.cellIdentityTdscdma);
    stringBuilder.append(", .signalStrengthTdscdma = ");
    stringBuilder.append(this.signalStrengthTdscdma);
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public final void writeEmbeddedToBlob(HwBlob paramHwBlob, long paramLong) {
    this.cellIdentityTdscdma.writeEmbeddedToBlob(paramHwBlob, 0L + paramLong);
    this.signalStrengthTdscdma.writeEmbeddedToBlob(paramHwBlob, 48L + paramLong);
  }
  
  public final void writeToParcel(HwParcel paramHwParcel) {
    HwBlob hwBlob = new HwBlob(56);
    writeEmbeddedToBlob(hwBlob, 0L);
    paramHwParcel.writeBuffer(hwBlob);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/V1_0/CellInfoTdscdma.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */