package android.hardware.radio.V1_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

public final class GsmBroadcastSmsConfigInfo {
  public int fromCodeScheme = 0;
  
  public int fromServiceId = 0;
  
  public boolean selected = false;
  
  public int toCodeScheme = 0;
  
  public int toServiceId = 0;
  
  public static final ArrayList<GsmBroadcastSmsConfigInfo> readVectorFromParcel(HwParcel paramHwParcel) {
    ArrayList<GsmBroadcastSmsConfigInfo> arrayList = new ArrayList();
    HwBlob hwBlob = paramHwParcel.readBuffer(16L);
    int i = hwBlob.getInt32(8L);
    hwBlob = paramHwParcel.readEmbeddedBuffer((i * 20), hwBlob.handle(), 0L, true);
    arrayList.clear();
    for (byte b = 0; b < i; b++) {
      GsmBroadcastSmsConfigInfo gsmBroadcastSmsConfigInfo = new GsmBroadcastSmsConfigInfo();
      gsmBroadcastSmsConfigInfo.readEmbeddedFromParcel(paramHwParcel, hwBlob, (b * 20));
      arrayList.add(gsmBroadcastSmsConfigInfo);
    } 
    return arrayList;
  }
  
  public static final void writeVectorToParcel(HwParcel paramHwParcel, ArrayList<GsmBroadcastSmsConfigInfo> paramArrayList) {
    HwBlob hwBlob1 = new HwBlob(16);
    int i = paramArrayList.size();
    hwBlob1.putInt32(8L, i);
    hwBlob1.putBool(12L, false);
    HwBlob hwBlob2 = new HwBlob(i * 20);
    for (byte b = 0; b < i; b++)
      ((GsmBroadcastSmsConfigInfo)paramArrayList.get(b)).writeEmbeddedToBlob(hwBlob2, (b * 20)); 
    hwBlob1.putBlob(0L, hwBlob2);
    paramHwParcel.writeBuffer(hwBlob1);
  }
  
  public final boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (paramObject == null)
      return false; 
    if (paramObject.getClass() != GsmBroadcastSmsConfigInfo.class)
      return false; 
    paramObject = paramObject;
    return (this.fromServiceId != ((GsmBroadcastSmsConfigInfo)paramObject).fromServiceId) ? false : ((this.toServiceId != ((GsmBroadcastSmsConfigInfo)paramObject).toServiceId) ? false : ((this.fromCodeScheme != ((GsmBroadcastSmsConfigInfo)paramObject).fromCodeScheme) ? false : ((this.toCodeScheme != ((GsmBroadcastSmsConfigInfo)paramObject).toCodeScheme) ? false : (!(this.selected != ((GsmBroadcastSmsConfigInfo)paramObject).selected)))));
  }
  
  public final int hashCode() {
    return Objects.hash(new Object[] { Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.fromServiceId))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.toServiceId))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.fromCodeScheme))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.toCodeScheme))), Integer.valueOf(HidlSupport.deepHashCode(Boolean.valueOf(this.selected))) });
  }
  
  public final void readEmbeddedFromParcel(HwParcel paramHwParcel, HwBlob paramHwBlob, long paramLong) {
    this.fromServiceId = paramHwBlob.getInt32(0L + paramLong);
    this.toServiceId = paramHwBlob.getInt32(4L + paramLong);
    this.fromCodeScheme = paramHwBlob.getInt32(8L + paramLong);
    this.toCodeScheme = paramHwBlob.getInt32(12L + paramLong);
    this.selected = paramHwBlob.getBool(16L + paramLong);
  }
  
  public final void readFromParcel(HwParcel paramHwParcel) {
    readEmbeddedFromParcel(paramHwParcel, paramHwParcel.readBuffer(20L), 0L);
  }
  
  public final String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("{");
    stringBuilder.append(".fromServiceId = ");
    stringBuilder.append(this.fromServiceId);
    stringBuilder.append(", .toServiceId = ");
    stringBuilder.append(this.toServiceId);
    stringBuilder.append(", .fromCodeScheme = ");
    stringBuilder.append(this.fromCodeScheme);
    stringBuilder.append(", .toCodeScheme = ");
    stringBuilder.append(this.toCodeScheme);
    stringBuilder.append(", .selected = ");
    stringBuilder.append(this.selected);
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public final void writeEmbeddedToBlob(HwBlob paramHwBlob, long paramLong) {
    paramHwBlob.putInt32(0L + paramLong, this.fromServiceId);
    paramHwBlob.putInt32(4L + paramLong, this.toServiceId);
    paramHwBlob.putInt32(8L + paramLong, this.fromCodeScheme);
    paramHwBlob.putInt32(12L + paramLong, this.toCodeScheme);
    paramHwBlob.putBool(16L + paramLong, this.selected);
  }
  
  public final void writeToParcel(HwParcel paramHwParcel) {
    HwBlob hwBlob = new HwBlob(20);
    writeEmbeddedToBlob(hwBlob, 0L);
    paramHwParcel.writeBuffer(hwBlob);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/V1_0/GsmBroadcastSmsConfigInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */