package android.hardware.radio.V1_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

public final class CfData {
  public ArrayList<CallForwardInfo> cfInfo = new ArrayList<>();
  
  public static final ArrayList<CfData> readVectorFromParcel(HwParcel paramHwParcel) {
    ArrayList<CfData> arrayList = new ArrayList();
    HwBlob hwBlob1 = paramHwParcel.readBuffer(16L);
    int i = hwBlob1.getInt32(8L);
    HwBlob hwBlob2 = paramHwParcel.readEmbeddedBuffer((i * 16), hwBlob1.handle(), 0L, true);
    arrayList.clear();
    for (byte b = 0; b < i; b++) {
      CfData cfData = new CfData();
      cfData.readEmbeddedFromParcel(paramHwParcel, hwBlob2, (b * 16));
      arrayList.add(cfData);
    } 
    return arrayList;
  }
  
  public static final void writeVectorToParcel(HwParcel paramHwParcel, ArrayList<CfData> paramArrayList) {
    HwBlob hwBlob1 = new HwBlob(16);
    int i = paramArrayList.size();
    hwBlob1.putInt32(8L, i);
    hwBlob1.putBool(12L, false);
    HwBlob hwBlob2 = new HwBlob(i * 16);
    for (byte b = 0; b < i; b++)
      ((CfData)paramArrayList.get(b)).writeEmbeddedToBlob(hwBlob2, (b * 16)); 
    hwBlob1.putBlob(0L, hwBlob2);
    paramHwParcel.writeBuffer(hwBlob1);
  }
  
  public final boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (paramObject == null)
      return false; 
    if (paramObject.getClass() != CfData.class)
      return false; 
    paramObject = paramObject;
    return !!HidlSupport.deepEquals(this.cfInfo, ((CfData)paramObject).cfInfo);
  }
  
  public final int hashCode() {
    return Objects.hash(new Object[] { Integer.valueOf(HidlSupport.deepHashCode(this.cfInfo)) });
  }
  
  public final void readEmbeddedFromParcel(HwParcel paramHwParcel, HwBlob paramHwBlob, long paramLong) {
    int i = paramHwBlob.getInt32(paramLong + 0L + 8L);
    paramHwBlob = paramHwParcel.readEmbeddedBuffer((i * 40), paramHwBlob.handle(), paramLong + 0L + 0L, true);
    this.cfInfo.clear();
    for (byte b = 0; b < i; b++) {
      CallForwardInfo callForwardInfo = new CallForwardInfo();
      callForwardInfo.readEmbeddedFromParcel(paramHwParcel, paramHwBlob, (b * 40));
      this.cfInfo.add(callForwardInfo);
    } 
  }
  
  public final void readFromParcel(HwParcel paramHwParcel) {
    readEmbeddedFromParcel(paramHwParcel, paramHwParcel.readBuffer(16L), 0L);
  }
  
  public final String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("{");
    stringBuilder.append(".cfInfo = ");
    stringBuilder.append(this.cfInfo);
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public final void writeEmbeddedToBlob(HwBlob paramHwBlob, long paramLong) {
    int i = this.cfInfo.size();
    paramHwBlob.putInt32(paramLong + 0L + 8L, i);
    paramHwBlob.putBool(paramLong + 0L + 12L, false);
    HwBlob hwBlob = new HwBlob(i * 40);
    for (byte b = 0; b < i; b++)
      ((CallForwardInfo)this.cfInfo.get(b)).writeEmbeddedToBlob(hwBlob, (b * 40)); 
    paramHwBlob.putBlob(paramLong + 0L + 0L, hwBlob);
  }
  
  public final void writeToParcel(HwParcel paramHwParcel) {
    HwBlob hwBlob = new HwBlob(16);
    writeEmbeddedToBlob(hwBlob, 0L);
    paramHwParcel.writeBuffer(hwBlob);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/V1_0/CfData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */