package android.hardware.radio.V1_2;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

public final class WcdmaSignalStrength {
  public android.hardware.radio.V1_0.WcdmaSignalStrength base = new android.hardware.radio.V1_0.WcdmaSignalStrength();
  
  public int ecno = 0;
  
  public int rscp = 0;
  
  public static final ArrayList<WcdmaSignalStrength> readVectorFromParcel(HwParcel paramHwParcel) {
    ArrayList<WcdmaSignalStrength> arrayList = new ArrayList();
    HwBlob hwBlob = paramHwParcel.readBuffer(16L);
    int i = hwBlob.getInt32(8L);
    hwBlob = paramHwParcel.readEmbeddedBuffer((i * 16), hwBlob.handle(), 0L, true);
    arrayList.clear();
    for (byte b = 0; b < i; b++) {
      WcdmaSignalStrength wcdmaSignalStrength = new WcdmaSignalStrength();
      wcdmaSignalStrength.readEmbeddedFromParcel(paramHwParcel, hwBlob, (b * 16));
      arrayList.add(wcdmaSignalStrength);
    } 
    return arrayList;
  }
  
  public static final void writeVectorToParcel(HwParcel paramHwParcel, ArrayList<WcdmaSignalStrength> paramArrayList) {
    HwBlob hwBlob1 = new HwBlob(16);
    int i = paramArrayList.size();
    hwBlob1.putInt32(8L, i);
    hwBlob1.putBool(12L, false);
    HwBlob hwBlob2 = new HwBlob(i * 16);
    for (byte b = 0; b < i; b++)
      ((WcdmaSignalStrength)paramArrayList.get(b)).writeEmbeddedToBlob(hwBlob2, (b * 16)); 
    hwBlob1.putBlob(0L, hwBlob2);
    paramHwParcel.writeBuffer(hwBlob1);
  }
  
  public final boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (paramObject == null)
      return false; 
    if (paramObject.getClass() != WcdmaSignalStrength.class)
      return false; 
    paramObject = paramObject;
    return !HidlSupport.deepEquals(this.base, ((WcdmaSignalStrength)paramObject).base) ? false : ((this.rscp != ((WcdmaSignalStrength)paramObject).rscp) ? false : (!(this.ecno != ((WcdmaSignalStrength)paramObject).ecno)));
  }
  
  public final int hashCode() {
    return Objects.hash(new Object[] { Integer.valueOf(HidlSupport.deepHashCode(this.base)), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.rscp))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.ecno))) });
  }
  
  public final void readEmbeddedFromParcel(HwParcel paramHwParcel, HwBlob paramHwBlob, long paramLong) {
    this.base.readEmbeddedFromParcel(paramHwParcel, paramHwBlob, 0L + paramLong);
    this.rscp = paramHwBlob.getInt32(8L + paramLong);
    this.ecno = paramHwBlob.getInt32(12L + paramLong);
  }
  
  public final void readFromParcel(HwParcel paramHwParcel) {
    readEmbeddedFromParcel(paramHwParcel, paramHwParcel.readBuffer(16L), 0L);
  }
  
  public final String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("{");
    stringBuilder.append(".base = ");
    stringBuilder.append(this.base);
    stringBuilder.append(", .rscp = ");
    stringBuilder.append(this.rscp);
    stringBuilder.append(", .ecno = ");
    stringBuilder.append(this.ecno);
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public final void writeEmbeddedToBlob(HwBlob paramHwBlob, long paramLong) {
    this.base.writeEmbeddedToBlob(paramHwBlob, 0L + paramLong);
    paramHwBlob.putInt32(8L + paramLong, this.rscp);
    paramHwBlob.putInt32(12L + paramLong, this.ecno);
  }
  
  public final void writeToParcel(HwParcel paramHwParcel) {
    HwBlob hwBlob = new HwBlob(16);
    writeEmbeddedToBlob(hwBlob, 0L);
    paramHwParcel.writeBuffer(hwBlob);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/V1_2/WcdmaSignalStrength.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */