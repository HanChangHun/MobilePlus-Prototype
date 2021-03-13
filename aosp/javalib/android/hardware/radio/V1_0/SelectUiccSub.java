package android.hardware.radio.V1_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

public final class SelectUiccSub {
  public int actStatus = 0;
  
  public int appIndex = 0;
  
  public int slot = 0;
  
  public int subType = 0;
  
  public static final ArrayList<SelectUiccSub> readVectorFromParcel(HwParcel paramHwParcel) {
    ArrayList<SelectUiccSub> arrayList = new ArrayList();
    HwBlob hwBlob1 = paramHwParcel.readBuffer(16L);
    int i = hwBlob1.getInt32(8L);
    HwBlob hwBlob2 = paramHwParcel.readEmbeddedBuffer((i * 16), hwBlob1.handle(), 0L, true);
    arrayList.clear();
    for (byte b = 0; b < i; b++) {
      SelectUiccSub selectUiccSub = new SelectUiccSub();
      selectUiccSub.readEmbeddedFromParcel(paramHwParcel, hwBlob2, (b * 16));
      arrayList.add(selectUiccSub);
    } 
    return arrayList;
  }
  
  public static final void writeVectorToParcel(HwParcel paramHwParcel, ArrayList<SelectUiccSub> paramArrayList) {
    HwBlob hwBlob1 = new HwBlob(16);
    int i = paramArrayList.size();
    hwBlob1.putInt32(8L, i);
    hwBlob1.putBool(12L, false);
    HwBlob hwBlob2 = new HwBlob(i * 16);
    for (byte b = 0; b < i; b++)
      ((SelectUiccSub)paramArrayList.get(b)).writeEmbeddedToBlob(hwBlob2, (b * 16)); 
    hwBlob1.putBlob(0L, hwBlob2);
    paramHwParcel.writeBuffer(hwBlob1);
  }
  
  public final boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (paramObject == null)
      return false; 
    if (paramObject.getClass() != SelectUiccSub.class)
      return false; 
    paramObject = paramObject;
    return (this.slot != ((SelectUiccSub)paramObject).slot) ? false : ((this.appIndex != ((SelectUiccSub)paramObject).appIndex) ? false : ((this.subType != ((SelectUiccSub)paramObject).subType) ? false : (!(this.actStatus != ((SelectUiccSub)paramObject).actStatus))));
  }
  
  public final int hashCode() {
    return Objects.hash(new Object[] { Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.slot))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.appIndex))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.subType))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.actStatus))) });
  }
  
  public final void readEmbeddedFromParcel(HwParcel paramHwParcel, HwBlob paramHwBlob, long paramLong) {
    this.slot = paramHwBlob.getInt32(0L + paramLong);
    this.appIndex = paramHwBlob.getInt32(4L + paramLong);
    this.subType = paramHwBlob.getInt32(8L + paramLong);
    this.actStatus = paramHwBlob.getInt32(12L + paramLong);
  }
  
  public final void readFromParcel(HwParcel paramHwParcel) {
    readEmbeddedFromParcel(paramHwParcel, paramHwParcel.readBuffer(16L), 0L);
  }
  
  public final String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("{");
    stringBuilder.append(".slot = ");
    stringBuilder.append(this.slot);
    stringBuilder.append(", .appIndex = ");
    stringBuilder.append(this.appIndex);
    stringBuilder.append(", .subType = ");
    stringBuilder.append(SubscriptionType.toString(this.subType));
    stringBuilder.append(", .actStatus = ");
    stringBuilder.append(UiccSubActStatus.toString(this.actStatus));
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public final void writeEmbeddedToBlob(HwBlob paramHwBlob, long paramLong) {
    paramHwBlob.putInt32(0L + paramLong, this.slot);
    paramHwBlob.putInt32(4L + paramLong, this.appIndex);
    paramHwBlob.putInt32(8L + paramLong, this.subType);
    paramHwBlob.putInt32(12L + paramLong, this.actStatus);
  }
  
  public final void writeToParcel(HwParcel paramHwParcel) {
    HwBlob hwBlob = new HwBlob(16);
    writeEmbeddedToBlob(hwBlob, 0L);
    paramHwParcel.writeBuffer(hwBlob);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/V1_0/SelectUiccSub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */