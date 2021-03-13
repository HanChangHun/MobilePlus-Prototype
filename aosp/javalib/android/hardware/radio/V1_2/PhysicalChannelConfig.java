package android.hardware.radio.V1_2;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

public final class PhysicalChannelConfig {
  public int cellBandwidthDownlink = 0;
  
  public int status = 0;
  
  public static final ArrayList<PhysicalChannelConfig> readVectorFromParcel(HwParcel paramHwParcel) {
    ArrayList<PhysicalChannelConfig> arrayList = new ArrayList();
    HwBlob hwBlob1 = paramHwParcel.readBuffer(16L);
    int i = hwBlob1.getInt32(8L);
    HwBlob hwBlob2 = paramHwParcel.readEmbeddedBuffer((i * 8), hwBlob1.handle(), 0L, true);
    arrayList.clear();
    for (byte b = 0; b < i; b++) {
      PhysicalChannelConfig physicalChannelConfig = new PhysicalChannelConfig();
      physicalChannelConfig.readEmbeddedFromParcel(paramHwParcel, hwBlob2, (b * 8));
      arrayList.add(physicalChannelConfig);
    } 
    return arrayList;
  }
  
  public static final void writeVectorToParcel(HwParcel paramHwParcel, ArrayList<PhysicalChannelConfig> paramArrayList) {
    HwBlob hwBlob1 = new HwBlob(16);
    int i = paramArrayList.size();
    hwBlob1.putInt32(8L, i);
    hwBlob1.putBool(12L, false);
    HwBlob hwBlob2 = new HwBlob(i * 8);
    for (byte b = 0; b < i; b++)
      ((PhysicalChannelConfig)paramArrayList.get(b)).writeEmbeddedToBlob(hwBlob2, (b * 8)); 
    hwBlob1.putBlob(0L, hwBlob2);
    paramHwParcel.writeBuffer(hwBlob1);
  }
  
  public final boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (paramObject == null)
      return false; 
    if (paramObject.getClass() != PhysicalChannelConfig.class)
      return false; 
    paramObject = paramObject;
    return (this.status != ((PhysicalChannelConfig)paramObject).status) ? false : (!(this.cellBandwidthDownlink != ((PhysicalChannelConfig)paramObject).cellBandwidthDownlink));
  }
  
  public final int hashCode() {
    return Objects.hash(new Object[] { Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.status))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.cellBandwidthDownlink))) });
  }
  
  public final void readEmbeddedFromParcel(HwParcel paramHwParcel, HwBlob paramHwBlob, long paramLong) {
    this.status = paramHwBlob.getInt32(0L + paramLong);
    this.cellBandwidthDownlink = paramHwBlob.getInt32(4L + paramLong);
  }
  
  public final void readFromParcel(HwParcel paramHwParcel) {
    readEmbeddedFromParcel(paramHwParcel, paramHwParcel.readBuffer(8L), 0L);
  }
  
  public final String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("{");
    stringBuilder.append(".status = ");
    stringBuilder.append(CellConnectionStatus.toString(this.status));
    stringBuilder.append(", .cellBandwidthDownlink = ");
    stringBuilder.append(this.cellBandwidthDownlink);
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public final void writeEmbeddedToBlob(HwBlob paramHwBlob, long paramLong) {
    paramHwBlob.putInt32(0L + paramLong, this.status);
    paramHwBlob.putInt32(4L + paramLong, this.cellBandwidthDownlink);
  }
  
  public final void writeToParcel(HwParcel paramHwParcel) {
    HwBlob hwBlob = new HwBlob(8);
    writeEmbeddedToBlob(hwBlob, 0L);
    paramHwParcel.writeBuffer(hwBlob);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/V1_2/PhysicalChannelConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */