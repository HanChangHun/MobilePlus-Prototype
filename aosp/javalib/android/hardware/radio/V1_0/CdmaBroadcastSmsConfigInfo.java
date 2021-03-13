package android.hardware.radio.V1_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

public final class CdmaBroadcastSmsConfigInfo {
  public int language = 0;
  
  public boolean selected = false;
  
  public int serviceCategory = 0;
  
  public static final ArrayList<CdmaBroadcastSmsConfigInfo> readVectorFromParcel(HwParcel paramHwParcel) {
    ArrayList<CdmaBroadcastSmsConfigInfo> arrayList = new ArrayList();
    HwBlob hwBlob = paramHwParcel.readBuffer(16L);
    int i = hwBlob.getInt32(8L);
    hwBlob = paramHwParcel.readEmbeddedBuffer((i * 12), hwBlob.handle(), 0L, true);
    arrayList.clear();
    for (byte b = 0; b < i; b++) {
      CdmaBroadcastSmsConfigInfo cdmaBroadcastSmsConfigInfo = new CdmaBroadcastSmsConfigInfo();
      cdmaBroadcastSmsConfigInfo.readEmbeddedFromParcel(paramHwParcel, hwBlob, (b * 12));
      arrayList.add(cdmaBroadcastSmsConfigInfo);
    } 
    return arrayList;
  }
  
  public static final void writeVectorToParcel(HwParcel paramHwParcel, ArrayList<CdmaBroadcastSmsConfigInfo> paramArrayList) {
    HwBlob hwBlob1 = new HwBlob(16);
    int i = paramArrayList.size();
    hwBlob1.putInt32(8L, i);
    hwBlob1.putBool(12L, false);
    HwBlob hwBlob2 = new HwBlob(i * 12);
    for (byte b = 0; b < i; b++)
      ((CdmaBroadcastSmsConfigInfo)paramArrayList.get(b)).writeEmbeddedToBlob(hwBlob2, (b * 12)); 
    hwBlob1.putBlob(0L, hwBlob2);
    paramHwParcel.writeBuffer(hwBlob1);
  }
  
  public final boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (paramObject == null)
      return false; 
    if (paramObject.getClass() != CdmaBroadcastSmsConfigInfo.class)
      return false; 
    paramObject = paramObject;
    return (this.serviceCategory != ((CdmaBroadcastSmsConfigInfo)paramObject).serviceCategory) ? false : ((this.language != ((CdmaBroadcastSmsConfigInfo)paramObject).language) ? false : (!(this.selected != ((CdmaBroadcastSmsConfigInfo)paramObject).selected)));
  }
  
  public final int hashCode() {
    return Objects.hash(new Object[] { Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.serviceCategory))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.language))), Integer.valueOf(HidlSupport.deepHashCode(Boolean.valueOf(this.selected))) });
  }
  
  public final void readEmbeddedFromParcel(HwParcel paramHwParcel, HwBlob paramHwBlob, long paramLong) {
    this.serviceCategory = paramHwBlob.getInt32(0L + paramLong);
    this.language = paramHwBlob.getInt32(4L + paramLong);
    this.selected = paramHwBlob.getBool(8L + paramLong);
  }
  
  public final void readFromParcel(HwParcel paramHwParcel) {
    readEmbeddedFromParcel(paramHwParcel, paramHwParcel.readBuffer(12L), 0L);
  }
  
  public final String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("{");
    stringBuilder.append(".serviceCategory = ");
    stringBuilder.append(this.serviceCategory);
    stringBuilder.append(", .language = ");
    stringBuilder.append(this.language);
    stringBuilder.append(", .selected = ");
    stringBuilder.append(this.selected);
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public final void writeEmbeddedToBlob(HwBlob paramHwBlob, long paramLong) {
    paramHwBlob.putInt32(0L + paramLong, this.serviceCategory);
    paramHwBlob.putInt32(4L + paramLong, this.language);
    paramHwBlob.putBool(8L + paramLong, this.selected);
  }
  
  public final void writeToParcel(HwParcel paramHwParcel) {
    HwBlob hwBlob = new HwBlob(12);
    writeEmbeddedToBlob(hwBlob, 0L);
    paramHwParcel.writeBuffer(hwBlob);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/V1_0/CdmaBroadcastSmsConfigInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */