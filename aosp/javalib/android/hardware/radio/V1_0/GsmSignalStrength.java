package android.hardware.radio.V1_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

public final class GsmSignalStrength {
  public int bitErrorRate = 0;
  
  public int signalStrength = 0;
  
  public int timingAdvance = 0;
  
  public static final ArrayList<GsmSignalStrength> readVectorFromParcel(HwParcel paramHwParcel) {
    ArrayList<GsmSignalStrength> arrayList = new ArrayList();
    HwBlob hwBlob = paramHwParcel.readBuffer(16L);
    int i = hwBlob.getInt32(8L);
    hwBlob = paramHwParcel.readEmbeddedBuffer((i * 12), hwBlob.handle(), 0L, true);
    arrayList.clear();
    for (byte b = 0; b < i; b++) {
      GsmSignalStrength gsmSignalStrength = new GsmSignalStrength();
      gsmSignalStrength.readEmbeddedFromParcel(paramHwParcel, hwBlob, (b * 12));
      arrayList.add(gsmSignalStrength);
    } 
    return arrayList;
  }
  
  public static final void writeVectorToParcel(HwParcel paramHwParcel, ArrayList<GsmSignalStrength> paramArrayList) {
    HwBlob hwBlob1 = new HwBlob(16);
    int i = paramArrayList.size();
    hwBlob1.putInt32(8L, i);
    hwBlob1.putBool(12L, false);
    HwBlob hwBlob2 = new HwBlob(i * 12);
    for (byte b = 0; b < i; b++)
      ((GsmSignalStrength)paramArrayList.get(b)).writeEmbeddedToBlob(hwBlob2, (b * 12)); 
    hwBlob1.putBlob(0L, hwBlob2);
    paramHwParcel.writeBuffer(hwBlob1);
  }
  
  public final boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (paramObject == null)
      return false; 
    if (paramObject.getClass() != GsmSignalStrength.class)
      return false; 
    paramObject = paramObject;
    return (this.signalStrength != ((GsmSignalStrength)paramObject).signalStrength) ? false : ((this.bitErrorRate != ((GsmSignalStrength)paramObject).bitErrorRate) ? false : (!(this.timingAdvance != ((GsmSignalStrength)paramObject).timingAdvance)));
  }
  
  public final int hashCode() {
    return Objects.hash(new Object[] { Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.signalStrength))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.bitErrorRate))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.timingAdvance))) });
  }
  
  public final void readEmbeddedFromParcel(HwParcel paramHwParcel, HwBlob paramHwBlob, long paramLong) {
    this.signalStrength = paramHwBlob.getInt32(0L + paramLong);
    this.bitErrorRate = paramHwBlob.getInt32(4L + paramLong);
    this.timingAdvance = paramHwBlob.getInt32(8L + paramLong);
  }
  
  public final void readFromParcel(HwParcel paramHwParcel) {
    readEmbeddedFromParcel(paramHwParcel, paramHwParcel.readBuffer(12L), 0L);
  }
  
  public final String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("{");
    stringBuilder.append(".signalStrength = ");
    stringBuilder.append(this.signalStrength);
    stringBuilder.append(", .bitErrorRate = ");
    stringBuilder.append(this.bitErrorRate);
    stringBuilder.append(", .timingAdvance = ");
    stringBuilder.append(this.timingAdvance);
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public final void writeEmbeddedToBlob(HwBlob paramHwBlob, long paramLong) {
    paramHwBlob.putInt32(0L + paramLong, this.signalStrength);
    paramHwBlob.putInt32(4L + paramLong, this.bitErrorRate);
    paramHwBlob.putInt32(8L + paramLong, this.timingAdvance);
  }
  
  public final void writeToParcel(HwParcel paramHwParcel) {
    HwBlob hwBlob = new HwBlob(12);
    writeEmbeddedToBlob(hwBlob, 0L);
    paramHwParcel.writeBuffer(hwBlob);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/V1_0/GsmSignalStrength.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */