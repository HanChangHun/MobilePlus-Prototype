package android.hardware.radio.V1_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

public final class CellInfoCdma {
  public CellIdentityCdma cellIdentityCdma = new CellIdentityCdma();
  
  public CdmaSignalStrength signalStrengthCdma = new CdmaSignalStrength();
  
  public EvdoSignalStrength signalStrengthEvdo = new EvdoSignalStrength();
  
  public static final ArrayList<CellInfoCdma> readVectorFromParcel(HwParcel paramHwParcel) {
    ArrayList<CellInfoCdma> arrayList = new ArrayList();
    HwBlob hwBlob1 = paramHwParcel.readBuffer(16L);
    int i = hwBlob1.getInt32(8L);
    HwBlob hwBlob2 = paramHwParcel.readEmbeddedBuffer((i * 40), hwBlob1.handle(), 0L, true);
    arrayList.clear();
    for (byte b = 0; b < i; b++) {
      CellInfoCdma cellInfoCdma = new CellInfoCdma();
      cellInfoCdma.readEmbeddedFromParcel(paramHwParcel, hwBlob2, (b * 40));
      arrayList.add(cellInfoCdma);
    } 
    return arrayList;
  }
  
  public static final void writeVectorToParcel(HwParcel paramHwParcel, ArrayList<CellInfoCdma> paramArrayList) {
    HwBlob hwBlob1 = new HwBlob(16);
    int i = paramArrayList.size();
    hwBlob1.putInt32(8L, i);
    hwBlob1.putBool(12L, false);
    HwBlob hwBlob2 = new HwBlob(i * 40);
    for (byte b = 0; b < i; b++)
      ((CellInfoCdma)paramArrayList.get(b)).writeEmbeddedToBlob(hwBlob2, (b * 40)); 
    hwBlob1.putBlob(0L, hwBlob2);
    paramHwParcel.writeBuffer(hwBlob1);
  }
  
  public final boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (paramObject == null)
      return false; 
    if (paramObject.getClass() != CellInfoCdma.class)
      return false; 
    paramObject = paramObject;
    return !HidlSupport.deepEquals(this.cellIdentityCdma, ((CellInfoCdma)paramObject).cellIdentityCdma) ? false : (!HidlSupport.deepEquals(this.signalStrengthCdma, ((CellInfoCdma)paramObject).signalStrengthCdma) ? false : (!!HidlSupport.deepEquals(this.signalStrengthEvdo, ((CellInfoCdma)paramObject).signalStrengthEvdo)));
  }
  
  public final int hashCode() {
    return Objects.hash(new Object[] { Integer.valueOf(HidlSupport.deepHashCode(this.cellIdentityCdma)), Integer.valueOf(HidlSupport.deepHashCode(this.signalStrengthCdma)), Integer.valueOf(HidlSupport.deepHashCode(this.signalStrengthEvdo)) });
  }
  
  public final void readEmbeddedFromParcel(HwParcel paramHwParcel, HwBlob paramHwBlob, long paramLong) {
    this.cellIdentityCdma.readEmbeddedFromParcel(paramHwParcel, paramHwBlob, 0L + paramLong);
    this.signalStrengthCdma.readEmbeddedFromParcel(paramHwParcel, paramHwBlob, 20L + paramLong);
    this.signalStrengthEvdo.readEmbeddedFromParcel(paramHwParcel, paramHwBlob, 28L + paramLong);
  }
  
  public final void readFromParcel(HwParcel paramHwParcel) {
    readEmbeddedFromParcel(paramHwParcel, paramHwParcel.readBuffer(40L), 0L);
  }
  
  public final String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("{");
    stringBuilder.append(".cellIdentityCdma = ");
    stringBuilder.append(this.cellIdentityCdma);
    stringBuilder.append(", .signalStrengthCdma = ");
    stringBuilder.append(this.signalStrengthCdma);
    stringBuilder.append(", .signalStrengthEvdo = ");
    stringBuilder.append(this.signalStrengthEvdo);
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public final void writeEmbeddedToBlob(HwBlob paramHwBlob, long paramLong) {
    this.cellIdentityCdma.writeEmbeddedToBlob(paramHwBlob, 0L + paramLong);
    this.signalStrengthCdma.writeEmbeddedToBlob(paramHwBlob, 20L + paramLong);
    this.signalStrengthEvdo.writeEmbeddedToBlob(paramHwBlob, 28L + paramLong);
  }
  
  public final void writeToParcel(HwParcel paramHwParcel) {
    HwBlob hwBlob = new HwBlob(40);
    writeEmbeddedToBlob(hwBlob, 0L);
    paramHwParcel.writeBuffer(hwBlob);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/V1_0/CellInfoCdma.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */