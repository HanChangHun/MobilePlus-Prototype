package android.hardware.radio.V1_2;

import android.hardware.radio.V1_0.LteSignalStrength;
import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

public final class CellInfoLte {
  public CellIdentityLte cellIdentityLte = new CellIdentityLte();
  
  public LteSignalStrength signalStrengthLte = new LteSignalStrength();
  
  public static final ArrayList<CellInfoLte> readVectorFromParcel(HwParcel paramHwParcel) {
    ArrayList<CellInfoLte> arrayList = new ArrayList();
    HwBlob hwBlob1 = paramHwParcel.readBuffer(16L);
    int i = hwBlob1.getInt32(8L);
    HwBlob hwBlob2 = paramHwParcel.readEmbeddedBuffer((i * 112), hwBlob1.handle(), 0L, true);
    arrayList.clear();
    for (byte b = 0; b < i; b++) {
      CellInfoLte cellInfoLte = new CellInfoLte();
      cellInfoLte.readEmbeddedFromParcel(paramHwParcel, hwBlob2, (b * 112));
      arrayList.add(cellInfoLte);
    } 
    return arrayList;
  }
  
  public static final void writeVectorToParcel(HwParcel paramHwParcel, ArrayList<CellInfoLte> paramArrayList) {
    HwBlob hwBlob1 = new HwBlob(16);
    int i = paramArrayList.size();
    hwBlob1.putInt32(8L, i);
    hwBlob1.putBool(12L, false);
    HwBlob hwBlob2 = new HwBlob(i * 112);
    for (byte b = 0; b < i; b++)
      ((CellInfoLte)paramArrayList.get(b)).writeEmbeddedToBlob(hwBlob2, (b * 112)); 
    hwBlob1.putBlob(0L, hwBlob2);
    paramHwParcel.writeBuffer(hwBlob1);
  }
  
  public final boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (paramObject == null)
      return false; 
    if (paramObject.getClass() != CellInfoLte.class)
      return false; 
    paramObject = paramObject;
    return !HidlSupport.deepEquals(this.cellIdentityLte, ((CellInfoLte)paramObject).cellIdentityLte) ? false : (!!HidlSupport.deepEquals(this.signalStrengthLte, ((CellInfoLte)paramObject).signalStrengthLte));
  }
  
  public final int hashCode() {
    return Objects.hash(new Object[] { Integer.valueOf(HidlSupport.deepHashCode(this.cellIdentityLte)), Integer.valueOf(HidlSupport.deepHashCode(this.signalStrengthLte)) });
  }
  
  public final void readEmbeddedFromParcel(HwParcel paramHwParcel, HwBlob paramHwBlob, long paramLong) {
    this.cellIdentityLte.readEmbeddedFromParcel(paramHwParcel, paramHwBlob, 0L + paramLong);
    this.signalStrengthLte.readEmbeddedFromParcel(paramHwParcel, paramHwBlob, 88L + paramLong);
  }
  
  public final void readFromParcel(HwParcel paramHwParcel) {
    readEmbeddedFromParcel(paramHwParcel, paramHwParcel.readBuffer(112L), 0L);
  }
  
  public final String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("{");
    stringBuilder.append(".cellIdentityLte = ");
    stringBuilder.append(this.cellIdentityLte);
    stringBuilder.append(", .signalStrengthLte = ");
    stringBuilder.append(this.signalStrengthLte);
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public final void writeEmbeddedToBlob(HwBlob paramHwBlob, long paramLong) {
    this.cellIdentityLte.writeEmbeddedToBlob(paramHwBlob, 0L + paramLong);
    this.signalStrengthLte.writeEmbeddedToBlob(paramHwBlob, 88L + paramLong);
  }
  
  public final void writeToParcel(HwParcel paramHwParcel) {
    HwBlob hwBlob = new HwBlob(112);
    writeEmbeddedToBlob(hwBlob, 0L);
    paramHwParcel.writeBuffer(hwBlob);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/V1_2/CellInfoLte.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */