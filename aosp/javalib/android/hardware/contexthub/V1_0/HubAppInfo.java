package android.hardware.contexthub.V1_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

public final class HubAppInfo {
  public long appId = 0L;
  
  public boolean enabled = false;
  
  public ArrayList<MemRange> memUsage = new ArrayList<>();
  
  public int version = 0;
  
  public static final ArrayList<HubAppInfo> readVectorFromParcel(HwParcel paramHwParcel) {
    ArrayList<HubAppInfo> arrayList = new ArrayList();
    HwBlob hwBlob = paramHwParcel.readBuffer(16L);
    int i = hwBlob.getInt32(8L);
    hwBlob = paramHwParcel.readEmbeddedBuffer((i * 40), hwBlob.handle(), 0L, true);
    arrayList.clear();
    for (byte b = 0; b < i; b++) {
      HubAppInfo hubAppInfo = new HubAppInfo();
      hubAppInfo.readEmbeddedFromParcel(paramHwParcel, hwBlob, (b * 40));
      arrayList.add(hubAppInfo);
    } 
    return arrayList;
  }
  
  public static final void writeVectorToParcel(HwParcel paramHwParcel, ArrayList<HubAppInfo> paramArrayList) {
    HwBlob hwBlob1 = new HwBlob(16);
    int i = paramArrayList.size();
    hwBlob1.putInt32(8L, i);
    hwBlob1.putBool(12L, false);
    HwBlob hwBlob2 = new HwBlob(i * 40);
    for (byte b = 0; b < i; b++)
      ((HubAppInfo)paramArrayList.get(b)).writeEmbeddedToBlob(hwBlob2, (b * 40)); 
    hwBlob1.putBlob(0L, hwBlob2);
    paramHwParcel.writeBuffer(hwBlob1);
  }
  
  public final boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (paramObject == null)
      return false; 
    if (paramObject.getClass() != HubAppInfo.class)
      return false; 
    paramObject = paramObject;
    return (this.appId != ((HubAppInfo)paramObject).appId) ? false : ((this.version != ((HubAppInfo)paramObject).version) ? false : (!HidlSupport.deepEquals(this.memUsage, ((HubAppInfo)paramObject).memUsage) ? false : (!(this.enabled != ((HubAppInfo)paramObject).enabled))));
  }
  
  public final int hashCode() {
    return Objects.hash(new Object[] { Integer.valueOf(HidlSupport.deepHashCode(Long.valueOf(this.appId))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.version))), Integer.valueOf(HidlSupport.deepHashCode(this.memUsage)), Integer.valueOf(HidlSupport.deepHashCode(Boolean.valueOf(this.enabled))) });
  }
  
  public final void readEmbeddedFromParcel(HwParcel paramHwParcel, HwBlob paramHwBlob, long paramLong) {
    this.appId = paramHwBlob.getInt64(paramLong + 0L);
    this.version = paramHwBlob.getInt32(paramLong + 8L);
    int i = paramHwBlob.getInt32(paramLong + 16L + 8L);
    HwBlob hwBlob = paramHwParcel.readEmbeddedBuffer((i * 16), paramHwBlob.handle(), paramLong + 16L + 0L, true);
    this.memUsage.clear();
    for (byte b = 0; b < i; b++) {
      MemRange memRange = new MemRange();
      memRange.readEmbeddedFromParcel(paramHwParcel, hwBlob, (b * 16));
      this.memUsage.add(memRange);
    } 
    this.enabled = paramHwBlob.getBool(paramLong + 32L);
  }
  
  public final void readFromParcel(HwParcel paramHwParcel) {
    readEmbeddedFromParcel(paramHwParcel, paramHwParcel.readBuffer(40L), 0L);
  }
  
  public final String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("{");
    stringBuilder.append(".appId = ");
    stringBuilder.append(this.appId);
    stringBuilder.append(", .version = ");
    stringBuilder.append(this.version);
    stringBuilder.append(", .memUsage = ");
    stringBuilder.append(this.memUsage);
    stringBuilder.append(", .enabled = ");
    stringBuilder.append(this.enabled);
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public final void writeEmbeddedToBlob(HwBlob paramHwBlob, long paramLong) {
    paramHwBlob.putInt64(paramLong + 0L, this.appId);
    paramHwBlob.putInt32(paramLong + 8L, this.version);
    int i = this.memUsage.size();
    paramHwBlob.putInt32(paramLong + 16L + 8L, i);
    paramHwBlob.putBool(paramLong + 16L + 12L, false);
    HwBlob hwBlob = new HwBlob(i * 16);
    for (byte b = 0; b < i; b++)
      ((MemRange)this.memUsage.get(b)).writeEmbeddedToBlob(hwBlob, (b * 16)); 
    paramHwBlob.putBlob(16L + paramLong + 0L, hwBlob);
    paramHwBlob.putBool(32L + paramLong, this.enabled);
  }
  
  public final void writeToParcel(HwParcel paramHwParcel) {
    HwBlob hwBlob = new HwBlob(40);
    writeEmbeddedToBlob(hwBlob, 0L);
    paramHwParcel.writeBuffer(hwBlob);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/contexthub/V1_0/HubAppInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */