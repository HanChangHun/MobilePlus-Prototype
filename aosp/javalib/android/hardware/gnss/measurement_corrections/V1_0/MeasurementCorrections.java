package android.hardware.gnss.measurement_corrections.V1_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

public final class MeasurementCorrections {
  public double altitudeMeters = 0.0D;
  
  public double horizontalPositionUncertaintyMeters = 0.0D;
  
  public double latitudeDegrees = 0.0D;
  
  public double longitudeDegrees = 0.0D;
  
  public ArrayList<SingleSatCorrection> satCorrections = new ArrayList<>();
  
  public long toaGpsNanosecondsOfWeek = 0L;
  
  public double verticalPositionUncertaintyMeters = 0.0D;
  
  public static final ArrayList<MeasurementCorrections> readVectorFromParcel(HwParcel paramHwParcel) {
    ArrayList<MeasurementCorrections> arrayList = new ArrayList();
    HwBlob hwBlob1 = paramHwParcel.readBuffer(16L);
    int i = hwBlob1.getInt32(8L);
    HwBlob hwBlob2 = paramHwParcel.readEmbeddedBuffer((i * 64), hwBlob1.handle(), 0L, true);
    arrayList.clear();
    for (byte b = 0; b < i; b++) {
      MeasurementCorrections measurementCorrections = new MeasurementCorrections();
      measurementCorrections.readEmbeddedFromParcel(paramHwParcel, hwBlob2, (b * 64));
      arrayList.add(measurementCorrections);
    } 
    return arrayList;
  }
  
  public static final void writeVectorToParcel(HwParcel paramHwParcel, ArrayList<MeasurementCorrections> paramArrayList) {
    HwBlob hwBlob1 = new HwBlob(16);
    int i = paramArrayList.size();
    hwBlob1.putInt32(8L, i);
    hwBlob1.putBool(12L, false);
    HwBlob hwBlob2 = new HwBlob(i * 64);
    for (byte b = 0; b < i; b++)
      ((MeasurementCorrections)paramArrayList.get(b)).writeEmbeddedToBlob(hwBlob2, (b * 64)); 
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
    return (this.latitudeDegrees != ((MeasurementCorrections)paramObject).latitudeDegrees) ? false : ((this.longitudeDegrees != ((MeasurementCorrections)paramObject).longitudeDegrees) ? false : ((this.altitudeMeters != ((MeasurementCorrections)paramObject).altitudeMeters) ? false : ((this.horizontalPositionUncertaintyMeters != ((MeasurementCorrections)paramObject).horizontalPositionUncertaintyMeters) ? false : ((this.verticalPositionUncertaintyMeters != ((MeasurementCorrections)paramObject).verticalPositionUncertaintyMeters) ? false : ((this.toaGpsNanosecondsOfWeek != ((MeasurementCorrections)paramObject).toaGpsNanosecondsOfWeek) ? false : (!!HidlSupport.deepEquals(this.satCorrections, ((MeasurementCorrections)paramObject).satCorrections)))))));
  }
  
  public final int hashCode() {
    return Objects.hash(new Object[] { Integer.valueOf(HidlSupport.deepHashCode(Double.valueOf(this.latitudeDegrees))), Integer.valueOf(HidlSupport.deepHashCode(Double.valueOf(this.longitudeDegrees))), Integer.valueOf(HidlSupport.deepHashCode(Double.valueOf(this.altitudeMeters))), Integer.valueOf(HidlSupport.deepHashCode(Double.valueOf(this.horizontalPositionUncertaintyMeters))), Integer.valueOf(HidlSupport.deepHashCode(Double.valueOf(this.verticalPositionUncertaintyMeters))), Integer.valueOf(HidlSupport.deepHashCode(Long.valueOf(this.toaGpsNanosecondsOfWeek))), Integer.valueOf(HidlSupport.deepHashCode(this.satCorrections)) });
  }
  
  public final void readEmbeddedFromParcel(HwParcel paramHwParcel, HwBlob paramHwBlob, long paramLong) {
    this.latitudeDegrees = paramHwBlob.getDouble(paramLong + 0L);
    this.longitudeDegrees = paramHwBlob.getDouble(paramLong + 8L);
    this.altitudeMeters = paramHwBlob.getDouble(paramLong + 16L);
    this.horizontalPositionUncertaintyMeters = paramHwBlob.getDouble(paramLong + 24L);
    this.verticalPositionUncertaintyMeters = paramHwBlob.getDouble(paramLong + 32L);
    this.toaGpsNanosecondsOfWeek = paramHwBlob.getInt64(paramLong + 40L);
    int i = paramHwBlob.getInt32(paramLong + 48L + 8L);
    paramHwBlob = paramHwParcel.readEmbeddedBuffer((i * 56), paramHwBlob.handle(), paramLong + 48L + 0L, true);
    this.satCorrections.clear();
    for (byte b = 0; b < i; b++) {
      SingleSatCorrection singleSatCorrection = new SingleSatCorrection();
      singleSatCorrection.readEmbeddedFromParcel(paramHwParcel, paramHwBlob, (b * 56));
      this.satCorrections.add(singleSatCorrection);
    } 
  }
  
  public final void readFromParcel(HwParcel paramHwParcel) {
    readEmbeddedFromParcel(paramHwParcel, paramHwParcel.readBuffer(64L), 0L);
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
    stringBuilder.append(", .horizontalPositionUncertaintyMeters = ");
    stringBuilder.append(this.horizontalPositionUncertaintyMeters);
    stringBuilder.append(", .verticalPositionUncertaintyMeters = ");
    stringBuilder.append(this.verticalPositionUncertaintyMeters);
    stringBuilder.append(", .toaGpsNanosecondsOfWeek = ");
    stringBuilder.append(this.toaGpsNanosecondsOfWeek);
    stringBuilder.append(", .satCorrections = ");
    stringBuilder.append(this.satCorrections);
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public final void writeEmbeddedToBlob(HwBlob paramHwBlob, long paramLong) {
    paramHwBlob.putDouble(paramLong + 0L, this.latitudeDegrees);
    paramHwBlob.putDouble(paramLong + 8L, this.longitudeDegrees);
    paramHwBlob.putDouble(16L + paramLong, this.altitudeMeters);
    paramHwBlob.putDouble(24L + paramLong, this.horizontalPositionUncertaintyMeters);
    paramHwBlob.putDouble(32L + paramLong, this.verticalPositionUncertaintyMeters);
    paramHwBlob.putInt64(40L + paramLong, this.toaGpsNanosecondsOfWeek);
    int i = this.satCorrections.size();
    paramHwBlob.putInt32(paramLong + 48L + 8L, i);
    paramHwBlob.putBool(paramLong + 48L + 12L, false);
    HwBlob hwBlob = new HwBlob(i * 56);
    for (byte b = 0; b < i; b++)
      ((SingleSatCorrection)this.satCorrections.get(b)).writeEmbeddedToBlob(hwBlob, (b * 56)); 
    paramHwBlob.putBlob(48L + paramLong + 0L, hwBlob);
  }
  
  public final void writeToParcel(HwParcel paramHwParcel) {
    HwBlob hwBlob = new HwBlob(64);
    writeEmbeddedToBlob(hwBlob, 0L);
    paramHwParcel.writeBuffer(hwBlob);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/gnss/measurement_corrections/V1_0/MeasurementCorrections.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */