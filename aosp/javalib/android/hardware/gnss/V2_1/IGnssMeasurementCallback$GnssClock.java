package android.hardware.gnss.V2_1;

import android.hardware.gnss.V1_0.IGnssMeasurementCallback;
import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

public final class GnssClock {
  public GnssSignalType referenceSignalTypeForIsb = new GnssSignalType();
  
  public IGnssMeasurementCallback.GnssClock v1_0 = new IGnssMeasurementCallback.GnssClock();
  
  public static final ArrayList<GnssClock> readVectorFromParcel(HwParcel paramHwParcel) {
    ArrayList<GnssClock> arrayList = new ArrayList();
    HwBlob hwBlob = paramHwParcel.readBuffer(16L);
    int i = hwBlob.getInt32(8L);
    hwBlob = paramHwParcel.readEmbeddedBuffer((i * 104), hwBlob.handle(), 0L, true);
    arrayList.clear();
    for (byte b = 0; b < i; b++) {
      GnssClock gnssClock = new GnssClock();
      gnssClock.readEmbeddedFromParcel(paramHwParcel, hwBlob, (b * 104));
      arrayList.add(gnssClock);
    } 
    return arrayList;
  }
  
  public static final void writeVectorToParcel(HwParcel paramHwParcel, ArrayList<GnssClock> paramArrayList) {
    HwBlob hwBlob1 = new HwBlob(16);
    int i = paramArrayList.size();
    hwBlob1.putInt32(8L, i);
    hwBlob1.putBool(12L, false);
    HwBlob hwBlob2 = new HwBlob(i * 104);
    for (byte b = 0; b < i; b++)
      ((GnssClock)paramArrayList.get(b)).writeEmbeddedToBlob(hwBlob2, (b * 104)); 
    hwBlob1.putBlob(0L, hwBlob2);
    paramHwParcel.writeBuffer(hwBlob1);
  }
  
  public final boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (paramObject == null)
      return false; 
    if (paramObject.getClass() != GnssClock.class)
      return false; 
    paramObject = paramObject;
    return !HidlSupport.deepEquals(this.v1_0, ((GnssClock)paramObject).v1_0) ? false : (!!HidlSupport.deepEquals(this.referenceSignalTypeForIsb, ((GnssClock)paramObject).referenceSignalTypeForIsb));
  }
  
  public final int hashCode() {
    return Objects.hash(new Object[] { Integer.valueOf(HidlSupport.deepHashCode(this.v1_0)), Integer.valueOf(HidlSupport.deepHashCode(this.referenceSignalTypeForIsb)) });
  }
  
  public final void readEmbeddedFromParcel(HwParcel paramHwParcel, HwBlob paramHwBlob, long paramLong) {
    this.v1_0.readEmbeddedFromParcel(paramHwParcel, paramHwBlob, 0L + paramLong);
    this.referenceSignalTypeForIsb.readEmbeddedFromParcel(paramHwParcel, paramHwBlob, 72L + paramLong);
  }
  
  public final void readFromParcel(HwParcel paramHwParcel) {
    readEmbeddedFromParcel(paramHwParcel, paramHwParcel.readBuffer(104L), 0L);
  }
  
  public final String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("{");
    stringBuilder.append(".v1_0 = ");
    stringBuilder.append(this.v1_0);
    stringBuilder.append(", .referenceSignalTypeForIsb = ");
    stringBuilder.append(this.referenceSignalTypeForIsb);
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public final void writeEmbeddedToBlob(HwBlob paramHwBlob, long paramLong) {
    this.v1_0.writeEmbeddedToBlob(paramHwBlob, 0L + paramLong);
    this.referenceSignalTypeForIsb.writeEmbeddedToBlob(paramHwBlob, 72L + paramLong);
  }
  
  public final void writeToParcel(HwParcel paramHwParcel) {
    HwBlob hwBlob = new HwBlob(104);
    writeEmbeddedToBlob(hwBlob, 0L);
    paramHwParcel.writeBuffer(hwBlob);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/gnss/V2_1/IGnssMeasurementCallback$GnssClock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */