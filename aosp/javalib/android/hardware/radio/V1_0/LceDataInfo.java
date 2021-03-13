package android.hardware.radio.V1_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

public final class LceDataInfo {
  public byte confidenceLevel = (byte)0;
  
  public int lastHopCapacityKbps = 0;
  
  public boolean lceSuspended = false;
  
  public static final ArrayList<LceDataInfo> readVectorFromParcel(HwParcel paramHwParcel) {
    ArrayList<LceDataInfo> arrayList = new ArrayList();
    HwBlob hwBlob1 = paramHwParcel.readBuffer(16L);
    int i = hwBlob1.getInt32(8L);
    HwBlob hwBlob2 = paramHwParcel.readEmbeddedBuffer((i * 8), hwBlob1.handle(), 0L, true);
    arrayList.clear();
    for (byte b = 0; b < i; b++) {
      LceDataInfo lceDataInfo = new LceDataInfo();
      lceDataInfo.readEmbeddedFromParcel(paramHwParcel, hwBlob2, (b * 8));
      arrayList.add(lceDataInfo);
    } 
    return arrayList;
  }
  
  public static final void writeVectorToParcel(HwParcel paramHwParcel, ArrayList<LceDataInfo> paramArrayList) {
    HwBlob hwBlob1 = new HwBlob(16);
    int i = paramArrayList.size();
    hwBlob1.putInt32(8L, i);
    hwBlob1.putBool(12L, false);
    HwBlob hwBlob2 = new HwBlob(i * 8);
    for (byte b = 0; b < i; b++)
      ((LceDataInfo)paramArrayList.get(b)).writeEmbeddedToBlob(hwBlob2, (b * 8)); 
    hwBlob1.putBlob(0L, hwBlob2);
    paramHwParcel.writeBuffer(hwBlob1);
  }
  
  public final boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (paramObject == null)
      return false; 
    if (paramObject.getClass() != LceDataInfo.class)
      return false; 
    paramObject = paramObject;
    return (this.lastHopCapacityKbps != ((LceDataInfo)paramObject).lastHopCapacityKbps) ? false : ((this.confidenceLevel != ((LceDataInfo)paramObject).confidenceLevel) ? false : (!(this.lceSuspended != ((LceDataInfo)paramObject).lceSuspended)));
  }
  
  public final int hashCode() {
    return Objects.hash(new Object[] { Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.lastHopCapacityKbps))), Integer.valueOf(HidlSupport.deepHashCode(Byte.valueOf(this.confidenceLevel))), Integer.valueOf(HidlSupport.deepHashCode(Boolean.valueOf(this.lceSuspended))) });
  }
  
  public final void readEmbeddedFromParcel(HwParcel paramHwParcel, HwBlob paramHwBlob, long paramLong) {
    this.lastHopCapacityKbps = paramHwBlob.getInt32(0L + paramLong);
    this.confidenceLevel = paramHwBlob.getInt8(4L + paramLong);
    this.lceSuspended = paramHwBlob.getBool(5L + paramLong);
  }
  
  public final void readFromParcel(HwParcel paramHwParcel) {
    readEmbeddedFromParcel(paramHwParcel, paramHwParcel.readBuffer(8L), 0L);
  }
  
  public final String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("{");
    stringBuilder.append(".lastHopCapacityKbps = ");
    stringBuilder.append(this.lastHopCapacityKbps);
    stringBuilder.append(", .confidenceLevel = ");
    stringBuilder.append(this.confidenceLevel);
    stringBuilder.append(", .lceSuspended = ");
    stringBuilder.append(this.lceSuspended);
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public final void writeEmbeddedToBlob(HwBlob paramHwBlob, long paramLong) {
    paramHwBlob.putInt32(0L + paramLong, this.lastHopCapacityKbps);
    paramHwBlob.putInt8(4L + paramLong, this.confidenceLevel);
    paramHwBlob.putBool(5L + paramLong, this.lceSuspended);
  }
  
  public final void writeToParcel(HwParcel paramHwParcel) {
    HwBlob hwBlob = new HwBlob(8);
    writeEmbeddedToBlob(hwBlob, 0L);
    paramHwParcel.writeBuffer(hwBlob);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/V1_0/LceDataInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */