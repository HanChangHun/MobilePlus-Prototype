package android.hardware.radio.V1_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

public final class LteSignalStrength {
  public int cqi = 0;
  
  public int rsrp = 0;
  
  public int rsrq = 0;
  
  public int rssnr = 0;
  
  public int signalStrength = 0;
  
  public int timingAdvance = 0;
  
  public static final ArrayList<LteSignalStrength> readVectorFromParcel(HwParcel paramHwParcel) {
    ArrayList<LteSignalStrength> arrayList = new ArrayList();
    HwBlob hwBlob1 = paramHwParcel.readBuffer(16L);
    int i = hwBlob1.getInt32(8L);
    HwBlob hwBlob2 = paramHwParcel.readEmbeddedBuffer((i * 24), hwBlob1.handle(), 0L, true);
    arrayList.clear();
    for (byte b = 0; b < i; b++) {
      LteSignalStrength lteSignalStrength = new LteSignalStrength();
      lteSignalStrength.readEmbeddedFromParcel(paramHwParcel, hwBlob2, (b * 24));
      arrayList.add(lteSignalStrength);
    } 
    return arrayList;
  }
  
  public static final void writeVectorToParcel(HwParcel paramHwParcel, ArrayList<LteSignalStrength> paramArrayList) {
    HwBlob hwBlob1 = new HwBlob(16);
    int i = paramArrayList.size();
    hwBlob1.putInt32(8L, i);
    hwBlob1.putBool(12L, false);
    HwBlob hwBlob2 = new HwBlob(i * 24);
    for (byte b = 0; b < i; b++)
      ((LteSignalStrength)paramArrayList.get(b)).writeEmbeddedToBlob(hwBlob2, (b * 24)); 
    hwBlob1.putBlob(0L, hwBlob2);
    paramHwParcel.writeBuffer(hwBlob1);
  }
  
  public final boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (paramObject == null)
      return false; 
    if (paramObject.getClass() != LteSignalStrength.class)
      return false; 
    paramObject = paramObject;
    return (this.signalStrength != ((LteSignalStrength)paramObject).signalStrength) ? false : ((this.rsrp != ((LteSignalStrength)paramObject).rsrp) ? false : ((this.rsrq != ((LteSignalStrength)paramObject).rsrq) ? false : ((this.rssnr != ((LteSignalStrength)paramObject).rssnr) ? false : ((this.cqi != ((LteSignalStrength)paramObject).cqi) ? false : (!(this.timingAdvance != ((LteSignalStrength)paramObject).timingAdvance))))));
  }
  
  public final int hashCode() {
    return Objects.hash(new Object[] { Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.signalStrength))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.rsrp))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.rsrq))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.rssnr))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.cqi))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.timingAdvance))) });
  }
  
  public final void readEmbeddedFromParcel(HwParcel paramHwParcel, HwBlob paramHwBlob, long paramLong) {
    this.signalStrength = paramHwBlob.getInt32(0L + paramLong);
    this.rsrp = paramHwBlob.getInt32(4L + paramLong);
    this.rsrq = paramHwBlob.getInt32(8L + paramLong);
    this.rssnr = paramHwBlob.getInt32(12L + paramLong);
    this.cqi = paramHwBlob.getInt32(16L + paramLong);
    this.timingAdvance = paramHwBlob.getInt32(20L + paramLong);
  }
  
  public final void readFromParcel(HwParcel paramHwParcel) {
    readEmbeddedFromParcel(paramHwParcel, paramHwParcel.readBuffer(24L), 0L);
  }
  
  public final String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("{");
    stringBuilder.append(".signalStrength = ");
    stringBuilder.append(this.signalStrength);
    stringBuilder.append(", .rsrp = ");
    stringBuilder.append(this.rsrp);
    stringBuilder.append(", .rsrq = ");
    stringBuilder.append(this.rsrq);
    stringBuilder.append(", .rssnr = ");
    stringBuilder.append(this.rssnr);
    stringBuilder.append(", .cqi = ");
    stringBuilder.append(this.cqi);
    stringBuilder.append(", .timingAdvance = ");
    stringBuilder.append(this.timingAdvance);
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public final void writeEmbeddedToBlob(HwBlob paramHwBlob, long paramLong) {
    paramHwBlob.putInt32(0L + paramLong, this.signalStrength);
    paramHwBlob.putInt32(4L + paramLong, this.rsrp);
    paramHwBlob.putInt32(8L + paramLong, this.rsrq);
    paramHwBlob.putInt32(12L + paramLong, this.rssnr);
    paramHwBlob.putInt32(16L + paramLong, this.cqi);
    paramHwBlob.putInt32(20L + paramLong, this.timingAdvance);
  }
  
  public final void writeToParcel(HwParcel paramHwParcel) {
    HwBlob hwBlob = new HwBlob(24);
    writeEmbeddedToBlob(hwBlob, 0L);
    paramHwParcel.writeBuffer(hwBlob);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/V1_0/LteSignalStrength.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */