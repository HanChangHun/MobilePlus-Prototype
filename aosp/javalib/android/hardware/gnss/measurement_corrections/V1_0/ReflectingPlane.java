package android.hardware.gnss.measurement_corrections.V1_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

public final class ReflectingPlane {
  public double altitudeMeters = 0.0D;
  
  public double azimuthDegrees = 0.0D;
  
  public double latitudeDegrees = 0.0D;
  
  public double longitudeDegrees = 0.0D;
  
  public static final ArrayList<ReflectingPlane> readVectorFromParcel(HwParcel paramHwParcel) {
    ArrayList<ReflectingPlane> arrayList = new ArrayList();
    HwBlob hwBlob = paramHwParcel.readBuffer(16L);
    int i = hwBlob.getInt32(8L);
    hwBlob = paramHwParcel.readEmbeddedBuffer((i * 32), hwBlob.handle(), 0L, true);
    arrayList.clear();
    for (byte b = 0; b < i; b++) {
      ReflectingPlane reflectingPlane = new ReflectingPlane();
      reflectingPlane.readEmbeddedFromParcel(paramHwParcel, hwBlob, (b * 32));
      arrayList.add(reflectingPlane);
    } 
    return arrayList;
  }
  
  public static final void writeVectorToParcel(HwParcel paramHwParcel, ArrayList<ReflectingPlane> paramArrayList) {
    HwBlob hwBlob1 = new HwBlob(16);
    int i = paramArrayList.size();
    hwBlob1.putInt32(8L, i);
    hwBlob1.putBool(12L, false);
    HwBlob hwBlob2 = new HwBlob(i * 32);
    for (byte b = 0; b < i; b++)
      ((ReflectingPlane)paramArrayList.get(b)).writeEmbeddedToBlob(hwBlob2, (b * 32)); 
    hwBlob1.putBlob(0L, hwBlob2);
    paramHwParcel.writeBuffer(hwBlob1);
  }
  
  public final boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (paramObject == null)
      return false; 
    if (paramObject.getClass() != ReflectingPlane.class)
      return false; 
    paramObject = paramObject;
    return (this.latitudeDegrees != ((ReflectingPlane)paramObject).latitudeDegrees) ? false : ((this.longitudeDegrees != ((ReflectingPlane)paramObject).longitudeDegrees) ? false : ((this.altitudeMeters != ((ReflectingPlane)paramObject).altitudeMeters) ? false : (!(this.azimuthDegrees != ((ReflectingPlane)paramObject).azimuthDegrees))));
  }
  
  public final int hashCode() {
    return Objects.hash(new Object[] { Integer.valueOf(HidlSupport.deepHashCode(Double.valueOf(this.latitudeDegrees))), Integer.valueOf(HidlSupport.deepHashCode(Double.valueOf(this.longitudeDegrees))), Integer.valueOf(HidlSupport.deepHashCode(Double.valueOf(this.altitudeMeters))), Integer.valueOf(HidlSupport.deepHashCode(Double.valueOf(this.azimuthDegrees))) });
  }
  
  public final void readEmbeddedFromParcel(HwParcel paramHwParcel, HwBlob paramHwBlob, long paramLong) {
    this.latitudeDegrees = paramHwBlob.getDouble(0L + paramLong);
    this.longitudeDegrees = paramHwBlob.getDouble(8L + paramLong);
    this.altitudeMeters = paramHwBlob.getDouble(16L + paramLong);
    this.azimuthDegrees = paramHwBlob.getDouble(24L + paramLong);
  }
  
  public final void readFromParcel(HwParcel paramHwParcel) {
    readEmbeddedFromParcel(paramHwParcel, paramHwParcel.readBuffer(32L), 0L);
  }
  
  public final String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("{");
    stringBuilder.append(".latitudeDegrees = ");
    stringBuilder.append(this.latitudeDegrees);
    stringBuilder.append(", .longitudeDegrees = ");
    stringBuilder.append(this.longitudeDegrees);
    stringBuilder.append(", .altitudeMeters = ");
    stringBuilder.append(this.altitudeMeters);
    stringBuilder.append(", .azimuthDegrees = ");
    stringBuilder.append(this.azimuthDegrees);
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public final void writeEmbeddedToBlob(HwBlob paramHwBlob, long paramLong) {
    paramHwBlob.putDouble(0L + paramLong, this.latitudeDegrees);
    paramHwBlob.putDouble(8L + paramLong, this.longitudeDegrees);
    paramHwBlob.putDouble(16L + paramLong, this.altitudeMeters);
    paramHwBlob.putDouble(24L + paramLong, this.azimuthDegrees);
  }
  
  public final void writeToParcel(HwParcel paramHwParcel) {
    HwBlob hwBlob = new HwBlob(32);
    writeEmbeddedToBlob(hwBlob, 0L);
    paramHwParcel.writeBuffer(hwBlob);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/gnss/measurement_corrections/V1_0/ReflectingPlane.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */