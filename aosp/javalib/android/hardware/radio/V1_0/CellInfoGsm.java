package android.hardware.radio.V1_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

public final class CellInfoGsm {
  public CellIdentityGsm cellIdentityGsm = new CellIdentityGsm();
  
  public GsmSignalStrength signalStrengthGsm = new GsmSignalStrength();
  
  public static final ArrayList<CellInfoGsm> readVectorFromParcel(HwParcel paramHwParcel) {
    ArrayList<CellInfoGsm> arrayList = new ArrayList();
    HwBlob hwBlob1 = paramHwParcel.readBuffer(16L);
    int i = hwBlob1.getInt32(8L);
    HwBlob hwBlob2 = paramHwParcel.readEmbeddedBuffer((i * 64), hwBlob1.handle(), 0L, true);
    arrayList.clear();
    for (byte b = 0; b < i; b++) {
      CellInfoGsm cellInfoGsm = new CellInfoGsm();
      cellInfoGsm.readEmbeddedFromParcel(paramHwParcel, hwBlob2, (b * 64));
      arrayList.add(cellInfoGsm);
    } 
    return arrayList;
  }
  
  public static final void writeVectorToParcel(HwParcel paramHwParcel, ArrayList<CellInfoGsm> paramArrayList) {
    HwBlob hwBlob1 = new HwBlob(16);
    int i = paramArrayList.size();
    hwBlob1.putInt32(8L, i);
    hwBlob1.putBool(12L, false);
    HwBlob hwBlob2 = new HwBlob(i * 64);
    for (byte b = 0; b < i; b++)
      ((CellInfoGsm)paramArrayList.get(b)).writeEmbeddedToBlob(hwBlob2, (b * 64)); 
    hwBlob1.putBlob(0L, hwBlob2);
    paramHwParcel.writeBuffer(hwBlob1);
  }
  
  public final boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (paramObject == null)
      return false; 
    if (paramObject.getClass() != CellInfoGsm.class)
      return false; 
    paramObject = paramObject;
    return !HidlSupport.deepEquals(this.cellIdentityGsm, ((CellInfoGsm)paramObject).cellIdentityGsm) ? false : (!!HidlSupport.deepEquals(this.signalStrengthGsm, ((CellInfoGsm)paramObject).signalStrengthGsm));
  }
  
  public final int hashCode() {
    return Objects.hash(new Object[] { Integer.valueOf(HidlSupport.deepHashCode(this.cellIdentityGsm)), Integer.valueOf(HidlSupport.deepHashCode(this.signalStrengthGsm)) });
  }
  
  public final void readEmbeddedFromParcel(HwParcel paramHwParcel, HwBlob paramHwBlob, long paramLong) {
    this.cellIdentityGsm.readEmbeddedFromParcel(paramHwParcel, paramHwBlob, 0L + paramLong);
    this.signalStrengthGsm.readEmbeddedFromParcel(paramHwParcel, paramHwBlob, 48L + paramLong);
  }
  
  public final void readFromParcel(HwParcel paramHwParcel) {
    readEmbeddedFromParcel(paramHwParcel, paramHwParcel.readBuffer(64L), 0L);
  }
  
  public final String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("{");
    stringBuilder.append(".cellIdentityGsm = ");
    stringBuilder.append(this.cellIdentityGsm);
    stringBuilder.append(", .signalStrengthGsm = ");
    stringBuilder.append(this.signalStrengthGsm);
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public final void writeEmbeddedToBlob(HwBlob paramHwBlob, long paramLong) {
    this.cellIdentityGsm.writeEmbeddedToBlob(paramHwBlob, 0L + paramLong);
    this.signalStrengthGsm.writeEmbeddedToBlob(paramHwBlob, 48L + paramLong);
  }
  
  public final void writeToParcel(HwParcel paramHwParcel) {
    HwBlob hwBlob = new HwBlob(64);
    writeEmbeddedToBlob(hwBlob, 0L);
    paramHwParcel.writeBuffer(hwBlob);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/V1_0/CellInfoGsm.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */