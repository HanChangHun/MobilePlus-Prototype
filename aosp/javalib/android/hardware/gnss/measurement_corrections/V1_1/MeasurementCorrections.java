package android.hardware.gnss.measurement_corrections.V1_1;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

public final class MeasurementCorrections {
  public float environmentBearingDegrees = 0.0F;
  
  public float environmentBearingUncertaintyDegrees = 0.0F;
  
  public boolean hasEnvironmentBearing = false;
  
  public ArrayList<SingleSatCorrection> satCorrections = new ArrayList<>();
  
  public android.hardware.gnss.measurement_corrections.V1_0.MeasurementCorrections v1_0 = new android.hardware.gnss.measurement_corrections.V1_0.MeasurementCorrections();
  
  public static final ArrayList<MeasurementCorrections> readVectorFromParcel(HwParcel paramHwParcel) {
    ArrayList<MeasurementCorrections> arrayList = new ArrayList();
    HwBlob hwBlob = paramHwParcel.readBuffer(16L);
    int i = hwBlob.getInt32(8L);
    hwBlob = paramHwParcel.readEmbeddedBuffer((i * 96), hwBlob.handle(), 0L, true);
    arrayList.clear();
    for (byte b = 0; b < i; b++) {
      MeasurementCorrections measurementCorrections = new MeasurementCorrections();
      measurementCorrections.readEmbeddedFromParcel(paramHwParcel, hwBlob, (b * 96));
      arrayList.add(measurementCorrections);
    } 
    return arrayList;
  }
  
  public static final void writeVectorToParcel(HwParcel paramHwParcel, ArrayList<MeasurementCorrections> paramArrayList) {
    HwBlob hwBlob1 = new HwBlob(16);
    int i = paramArrayList.size();
    hwBlob1.putInt32(8L, i);
    hwBlob1.putBool(12L, false);
    HwBlob hwBlob2 = new HwBlob(i * 96);
    for (byte b = 0; b < i; b++)
      ((MeasurementCorrections)paramArrayList.get(b)).writeEmbeddedToBlob(hwBlob2, (b * 96)); 
    hwBlob1.putBlob(0L, hwBlob2);
    paramHwParcel.writeBuffer(hwBlob1);
  }
  
  public final boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (paramObject == null)
      return false; 
    if (paramObject.getClass() != MeasurementCorrections.class)
      return false; 
    paramObject = paramObject;
    return !HidlSupport.deepEquals(this.v1_0, ((MeasurementCorrections)paramObject).v1_0) ? false : ((this.hasEnvironmentBearing != ((MeasurementCorrections)paramObject).hasEnvironmentBearing) ? false : ((this.environmentBearingDegrees != ((MeasurementCorrections)paramObject).environmentBearingDegrees) ? false : ((this.environmentBearingUncertaintyDegrees != ((MeasurementCorrections)paramObject).environmentBearingUncertaintyDegrees) ? false : (!!HidlSupport.deepEquals(this.satCorrections, ((MeasurementCorrections)paramObject).satCorrections)))));
  }
  
  public final int hashCode() {
    return Objects.hash(new Object[] { Integer.valueOf(HidlSupport.deepHashCode(this.v1_0)), Integer.valueOf(HidlSupport.deepHashCode(Boolean.valueOf(this.hasEnvironmentBearing))), Integer.valueOf(HidlSupport.deepHashCode(Float.valueOf(this.environmentBearingDegrees))), Integer.valueOf(HidlSupport.deepHashCode(Float.valueOf(this.environmentBearingUncertaintyDegrees))), Integer.valueOf(HidlSupport.deepHashCode(this.satCorrections)) });
  }
  
  public final void readEmbeddedFromParcel(HwParcel paramHwParcel, HwBlob paramHwBlob, long paramLong) {
    this.v1_0.readEmbeddedFromParcel(paramHwParcel, paramHwBlob, paramLong + 0L);
    this.hasEnvironmentBearing = paramHwBlob.getBool(paramLong + 64L);
    this.environmentBearingDegrees = paramHwBlob.getFloat(paramLong + 68L);
    this.environmentBearingUncertaintyDegrees = paramHwBlob.getFloat(paramLong + 72L);
    int i = paramHwBlob.getInt32(paramLong + 80L + 8L);
    paramHwBlob = paramHwParcel.readEmbeddedBuffer((i * 64), paramHwBlob.handle(), paramLong + 80L + 0L, true);
    this.satCorrections.clear();
    for (byte b = 0; b < i; b++) {
      SingleSatCorrection singleSatCorrection = new SingleSatCorrection();
      singleSatCorrection.readEmbeddedFromParcel(paramHwParcel, paramHwBlob, (b * 64));
      this.satCorrections.add(singleSatCorrection);
    } 
  }
  
  public final void readFromParcel(HwParcel paramHwParcel) {
    readEmbeddedFromParcel(paramHwParcel, paramHwParcel.readBuffer(96L), 0L);
  }
  
  public final String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("{");
    stringBuilder.append(".v1_0 = ");
    stringBuilder.append(this.v1_0);
    stringBuilder.append(", .hasEnvironmentBearing = ");
    stringBuilder.append(this.hasEnvironmentBearing);
    stringBuilder.append(", .environmentBearingDegrees = ");
    stringBuilder.append(this.environmentBearingDegrees);
    stringBuilder.append(", .environmentBearingUncertaintyDegrees = ");
    stringBuilder.append(this.environmentBearingUncertaintyDegrees);
    stringBuilder.append(", .satCorrections = ");
    stringBuilder.append(this.satCorrections);
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public final void writeEmbeddedToBlob(HwBlob paramHwBlob, long paramLong) {
    this.v1_0.writeEmbeddedToBlob(paramHwBlob, paramLong + 0L);
    paramHwBlob.putBool(64L + paramLong, this.hasEnvironmentBearing);
    paramHwBlob.putFloat(68L + paramLong, this.environmentBearingDegrees);
    paramHwBlob.putFloat(72L + paramLong, this.environmentBearingUncertaintyDegrees);
    int i = this.satCorrections.size();
    paramHwBlob.putInt32(paramLong + 80L + 8L, i);
    paramHwBlob.putBool(paramLong + 80L + 12L, false);
    HwBlob hwBlob = new HwBlob(i * 64);
    for (byte b = 0; b < i; b++)
      ((SingleSatCorrection)this.satCorrections.get(b)).writeEmbeddedToBlob(hwBlob, (b * 64)); 
    paramHwBlob.putBlob(80L + paramLong + 0L, hwBlob);
  }
  
  public final void writeToParcel(HwParcel paramHwParcel) {
    HwBlob hwBlob = new HwBlob(96);
    writeEmbeddedToBlob(hwBlob, 0L);
    paramHwParcel.writeBuffer(hwBlob);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/gnss/measurement_corrections/V1_1/MeasurementCorrections.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */