package android.hardware.radio.V1_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

public final class CellInfoWcdma {
  public CellIdentityWcdma cellIdentityWcdma = new CellIdentityWcdma();
  
  public WcdmaSignalStrength signalStrengthWcdma = new WcdmaSignalStrength();
  
  public static final ArrayList<CellInfoWcdma> readVectorFromParcel(HwParcel paramHwParcel) {
    ArrayList<CellInfoWcdma> arrayList = new ArrayList();
    HwBlob hwBlob = paramHwParcel.readBuffer(16L);
    int i = hwBlob.getInt32(8L);
    hwBlob = paramHwParcel.readEmbeddedBuffer((i * 56), hwBlob.handle(), 0L, true);
    arrayList.clear();
    for (byte b = 0; b < i; b++) {
      CellInfoWcdma cellInfoWcdma = new CellInfoWcdma();
      cellInfoWcdma.readEmbeddedFromParcel(paramHwParcel, hwBlob, (b * 56));
      arrayList.add(cellInfoWcdma);
    } 
    return arrayList;
  }
  
  public static final void writeVectorToParcel(HwParcel paramHwParcel, ArrayList<CellInfoWcdma> paramArrayList) {
    HwBlob hwBlob1 = new HwBlob(16);
    int i = paramArrayList.size();
    hwBlob1.putInt32(8L, i);
    hwBlob1.putBool(12L, false);
    HwBlob hwBlob2 = new HwBlob(i * 56);
    for (byte b = 0; b < i; b++)
      ((CellInfoWcdma)paramArrayList.get(b)).writeEmbeddedToBlob(hwBlob2, (b * 56)); 
    hwBlob1.putBlob(0L, hwBlob2);
    paramHwParcel.writeBuffer(hwBlob1);
  }
  
  public final boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (paramObject == null)
      return false; 
    if (paramObject.getClass() != CellInfoWcdma.class)
      return false; 
    paramObject = paramObject;
    return !HidlSupport.deepEquals(this.cellIdentityWcdma, ((CellInfoWcdma)paramObject).cellIdentityWcdma) ? false : (!!HidlSupport.deepEquals(this.signalStrengthWcdma, ((CellInfoWcdma)paramObject).signalStrengthWcdma));
  }
  
  public final int hashCode() {
    return Objects.hash(new Object[] { Integer.valueOf(HidlSupport.deepHashCode(this.cellIdentityWcdma)), Integer.valueOf(HidlSupport.deepHashCode(this.signalStrengthWcdma)) });
  }
  
  public final void readEmbeddedFromParcel(HwParcel paramHwParcel, HwBlob paramHwBlob, long paramLong) {
    this.cellIdentityWcdma.readEmbeddedFromParcel(paramHwParcel, paramHwBlob, 0L + paramLong);
    this.signalStrengthWcdma.readEmbeddedFromParcel(paramHwParcel, paramHwBlob, 48L + paramLong);
  }
  
  public final void readFromParcel(HwParcel paramHwParcel) {
    readEmbeddedFromParcel(paramHwParcel, paramHwParcel.readBuffer(56L), 0L);
  }
  
  public final String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("{");
    stringBuilder.append(".cellIdentityWcdma = ");
    stringBuilder.append(this.cellIdentityWcdma);
    stringBuilder.append(", .signalStrengthWcdma = ");
    stringBuilder.append(this.signalStrengthWcdma);
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public final void writeEmbeddedToBlob(HwBlob paramHwBlob, long paramLong) {
    this.cellIdentityWcdma.writeEmbeddedToBlob(paramHwBlob, 0L + paramLong);
    this.signalStrengthWcdma.writeEmbeddedToBlob(paramHwBlob, 48L + paramLong);
  }
  
  public final void writeToParcel(HwParcel paramHwParcel) {
    HwBlob hwBlob = new HwBlob(56);
    writeEmbeddedToBlob(hwBlob, 0L);
    paramHwParcel.writeBuffer(hwBlob);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/V1_0/CellInfoWcdma.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */