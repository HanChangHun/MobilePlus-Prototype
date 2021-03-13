package android.hardware.radio.V1_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

public final class EvdoSignalStrength {
  public int dbm = 0;
  
  public int ecio = 0;
  
  public int signalNoiseRatio = 0;
  
  public static final ArrayList<EvdoSignalStrength> readVectorFromParcel(HwParcel paramHwParcel) {
    ArrayList<EvdoSignalStrength> arrayList = new ArrayList();
    HwBlob hwBlob1 = paramHwParcel.readBuffer(16L);
    int i = hwBlob1.getInt32(8L);
    HwBlob hwBlob2 = paramHwParcel.readEmbeddedBuffer((i * 12), hwBlob1.handle(), 0L, true);
    arrayList.clear();
    for (byte b = 0; b < i; b++) {
      EvdoSignalStrength evdoSignalStrength = new EvdoSignalStrength();
      evdoSignalStrength.readEmbeddedFromParcel(paramHwParcel, hwBlob2, (b * 12));
      arrayList.add(evdoSignalStrength);
    } 
    return arrayList;
  }
  
  public static final void writeVectorToParcel(HwParcel paramHwParcel, ArrayList<EvdoSignalStrength> paramArrayList) {
    HwBlob hwBlob1 = new HwBlob(16);
    int i = paramArrayList.size();
    hwBlob1.putInt32(8L, i);
    hwBlob1.putBool(12L, false);
    HwBlob hwBlob2 = new HwBlob(i * 12);
    for (byte b = 0; b < i; b++)
      ((EvdoSignalStrength)paramArrayList.get(b)).writeEmbeddedToBlob(hwBlob2, (b * 12)); 
    hwBlob1.putBlob(0L, hwBlob2);
    paramHwParcel.writeBuffer(hwBlob1);
  }
  
  public final boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (paramObject == null)
      return false; 
    if (paramObject.getClass() != EvdoSignalStrength.class)
      return false; 
    paramObject = paramObject;
    return (this.dbm != ((EvdoSignalStrength)paramObject).dbm) ? false : ((this.ecio != ((EvdoSignalStrength)paramObject).ecio) ? false : (!(this.signalNoiseRatio != ((EvdoSignalStrength)paramObject).signalNoiseRatio)));
  }
  
  public final int hashCode() {
    return Objects.hash(new Object[] { Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.dbm))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.ecio))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.signalNoiseRatio))) });
  }
  
  public final void readEmbeddedFromParcel(HwParcel paramHwParcel, HwBlob paramHwBlob, long paramLong) {
    this.dbm = paramHwBlob.getInt32(0L + paramLong);
    this.ecio = paramHwBlob.getInt32(4L + paramLong);
    this.signalNoiseRatio = paramHwBlob.getInt32(8L + paramLong);
  }
  
  public final void readFromParcel(HwParcel paramHwParcel) {
    readEmbeddedFromParcel(paramHwParcel, paramHwParcel.readBuffer(12L), 0L);
  }
  
  public final String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("{");
    stringBuilder.append(".dbm = ");
    stringBuilder.append(this.dbm);
    stringBuilder.append(", .ecio = ");
    stringBuilder.append(this.ecio);
    stringBuilder.append(", .signalNoiseRatio = ");
    stringBuilder.append(this.signalNoiseRatio);
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public final void writeEmbeddedToBlob(HwBlob paramHwBlob, long paramLong) {
    paramHwBlob.putInt32(0L + paramLong, this.dbm);
    paramHwBlob.putInt32(4L + paramLong, this.ecio);
    paramHwBlob.putInt32(8L + paramLong, this.signalNoiseRatio);
  }
  
  public final void writeToParcel(HwParcel paramHwParcel) {
    HwBlob hwBlob = new HwBlob(12);
    writeEmbeddedToBlob(hwBlob, 0L);
    paramHwParcel.writeBuffer(hwBlob);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/V1_0/EvdoSignalStrength.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */