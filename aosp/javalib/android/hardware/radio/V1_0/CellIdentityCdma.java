package android.hardware.radio.V1_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

public final class CellIdentityCdma {
  public int baseStationId = 0;
  
  public int latitude = 0;
  
  public int longitude = 0;
  
  public int networkId = 0;
  
  public int systemId = 0;
  
  public static final ArrayList<CellIdentityCdma> readVectorFromParcel(HwParcel paramHwParcel) {
    ArrayList<CellIdentityCdma> arrayList = new ArrayList();
    HwBlob hwBlob = paramHwParcel.readBuffer(16L);
    int i = hwBlob.getInt32(8L);
    hwBlob = paramHwParcel.readEmbeddedBuffer((i * 20), hwBlob.handle(), 0L, true);
    arrayList.clear();
    for (byte b = 0; b < i; b++) {
      CellIdentityCdma cellIdentityCdma = new CellIdentityCdma();
      cellIdentityCdma.readEmbeddedFromParcel(paramHwParcel, hwBlob, (b * 20));
      arrayList.add(cellIdentityCdma);
    } 
    return arrayList;
  }
  
  public static final void writeVectorToParcel(HwParcel paramHwParcel, ArrayList<CellIdentityCdma> paramArrayList) {
    HwBlob hwBlob1 = new HwBlob(16);
    int i = paramArrayList.size();
    hwBlob1.putInt32(8L, i);
    hwBlob1.putBool(12L, false);
    HwBlob hwBlob2 = new HwBlob(i * 20);
    for (byte b = 0; b < i; b++)
      ((CellIdentityCdma)paramArrayList.get(b)).writeEmbeddedToBlob(hwBlob2, (b * 20)); 
    hwBlob1.putBlob(0L, hwBlob2);
    paramHwParcel.writeBuffer(hwBlob1);
  }
  
  public final boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (paramObject == null)
      return false; 
    if (paramObject.getClass() != CellIdentityCdma.class)
      return false; 
    paramObject = paramObject;
    return (this.networkId != ((CellIdentityCdma)paramObject).networkId) ? false : ((this.systemId != ((CellIdentityCdma)paramObject).systemId) ? false : ((this.baseStationId != ((CellIdentityCdma)paramObject).baseStationId) ? false : ((this.longitude != ((CellIdentityCdma)paramObject).longitude) ? false : (!(this.latitude != ((CellIdentityCdma)paramObject).latitude)))));
  }
  
  public final int hashCode() {
    return Objects.hash(new Object[] { Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.networkId))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.systemId))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.baseStationId))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.longitude))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.latitude))) });
  }
  
  public final void readEmbeddedFromParcel(HwParcel paramHwParcel, HwBlob paramHwBlob, long paramLong) {
    this.networkId = paramHwBlob.getInt32(0L + paramLong);
    this.systemId = paramHwBlob.getInt32(4L + paramLong);
    this.baseStationId = paramHwBlob.getInt32(8L + paramLong);
    this.longitude = paramHwBlob.getInt32(12L + paramLong);
    this.latitude = paramHwBlob.getInt32(16L + paramLong);
  }
  
  public final void readFromParcel(HwParcel paramHwParcel) {
    readEmbeddedFromParcel(paramHwParcel, paramHwParcel.readBuffer(20L), 0L);
  }
  
  public final String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("{");
    stringBuilder.append(".networkId = ");
    stringBuilder.append(this.networkId);
    stringBuilder.append(", .systemId = ");
    stringBuilder.append(this.systemId);
    stringBuilder.append(", .baseStationId = ");
    stringBuilder.append(this.baseStationId);
    stringBuilder.append(", .longitude = ");
    stringBuilder.append(this.longitude);
    stringBuilder.append(", .latitude = ");
    stringBuilder.append(this.latitude);
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public final void writeEmbeddedToBlob(HwBlob paramHwBlob, long paramLong) {
    paramHwBlob.putInt32(0L + paramLong, this.networkId);
    paramHwBlob.putInt32(4L + paramLong, this.systemId);
    paramHwBlob.putInt32(8L + paramLong, this.baseStationId);
    paramHwBlob.putInt32(12L + paramLong, this.longitude);
    paramHwBlob.putInt32(16L + paramLong, this.latitude);
  }
  
  public final void writeToParcel(HwParcel paramHwParcel) {
    HwBlob hwBlob = new HwBlob(20);
    writeEmbeddedToBlob(hwBlob, 0L);
    paramHwParcel.writeBuffer(hwBlob);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/V1_0/CellIdentityCdma.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */