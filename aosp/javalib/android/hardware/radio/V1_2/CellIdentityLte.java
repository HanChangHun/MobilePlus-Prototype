package android.hardware.radio.V1_2;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

public final class CellIdentityLte {
  public int bandwidth = 0;
  
  public android.hardware.radio.V1_0.CellIdentityLte base = new android.hardware.radio.V1_0.CellIdentityLte();
  
  public CellIdentityOperatorNames operatorNames = new CellIdentityOperatorNames();
  
  public static final ArrayList<CellIdentityLte> readVectorFromParcel(HwParcel paramHwParcel) {
    ArrayList<CellIdentityLte> arrayList = new ArrayList();
    HwBlob hwBlob = paramHwParcel.readBuffer(16L);
    int i = hwBlob.getInt32(8L);
    hwBlob = paramHwParcel.readEmbeddedBuffer((i * 88), hwBlob.handle(), 0L, true);
    arrayList.clear();
    for (byte b = 0; b < i; b++) {
      CellIdentityLte cellIdentityLte = new CellIdentityLte();
      cellIdentityLte.readEmbeddedFromParcel(paramHwParcel, hwBlob, (b * 88));
      arrayList.add(cellIdentityLte);
    } 
    return arrayList;
  }
  
  public static final void writeVectorToParcel(HwParcel paramHwParcel, ArrayList<CellIdentityLte> paramArrayList) {
    HwBlob hwBlob1 = new HwBlob(16);
    int i = paramArrayList.size();
    hwBlob1.putInt32(8L, i);
    hwBlob1.putBool(12L, false);
    HwBlob hwBlob2 = new HwBlob(i * 88);
    for (byte b = 0; b < i; b++)
      ((CellIdentityLte)paramArrayList.get(b)).writeEmbeddedToBlob(hwBlob2, (b * 88)); 
    hwBlob1.putBlob(0L, hwBlob2);
    paramHwParcel.writeBuffer(hwBlob1);
  }
  
  public final boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (paramObject == null)
      return false; 
    if (paramObject.getClass() != CellIdentityLte.class)
      return false; 
    paramObject = paramObject;
    return !HidlSupport.deepEquals(this.base, ((CellIdentityLte)paramObject).base) ? false : (!HidlSupport.deepEquals(this.operatorNames, ((CellIdentityLte)paramObject).operatorNames) ? false : (!(this.bandwidth != ((CellIdentityLte)paramObject).bandwidth)));
  }
  
  public final int hashCode() {
    return Objects.hash(new Object[] { Integer.valueOf(HidlSupport.deepHashCode(this.base)), Integer.valueOf(HidlSupport.deepHashCode(this.operatorNames)), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.bandwidth))) });
  }
  
  public final void readEmbeddedFromParcel(HwParcel paramHwParcel, HwBlob paramHwBlob, long paramLong) {
    this.base.readEmbeddedFromParcel(paramHwParcel, paramHwBlob, 0L + paramLong);
    this.operatorNames.readEmbeddedFromParcel(paramHwParcel, paramHwBlob, 48L + paramLong);
    this.bandwidth = paramHwBlob.getInt32(80L + paramLong);
  }
  
  public final void readFromParcel(HwParcel paramHwParcel) {
    readEmbeddedFromParcel(paramHwParcel, paramHwParcel.readBuffer(88L), 0L);
  }
  
  public final String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("{");
    stringBuilder.append(".base = ");
    stringBuilder.append(this.base);
    stringBuilder.append(", .operatorNames = ");
    stringBuilder.append(this.operatorNames);
    stringBuilder.append(", .bandwidth = ");
    stringBuilder.append(this.bandwidth);
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public final void writeEmbeddedToBlob(HwBlob paramHwBlob, long paramLong) {
    this.base.writeEmbeddedToBlob(paramHwBlob, 0L + paramLong);
    this.operatorNames.writeEmbeddedToBlob(paramHwBlob, 48L + paramLong);
    paramHwBlob.putInt32(80L + paramLong, this.bandwidth);
  }
  
  public final void writeToParcel(HwParcel paramHwParcel) {
    HwBlob hwBlob = new HwBlob(88);
    writeEmbeddedToBlob(hwBlob, 0L);
    paramHwParcel.writeBuffer(hwBlob);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/V1_2/CellIdentityLte.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */