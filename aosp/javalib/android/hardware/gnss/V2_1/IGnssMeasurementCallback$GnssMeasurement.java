package android.hardware.gnss.V2_1;

import android.hardware.gnss.V2_0.IGnssMeasurementCallback;
import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

public final class GnssMeasurement {
  public double basebandCN0DbHz = 0.0D;
  
  public int flags;
  
  public double fullInterSignalBiasNs = 0.0D;
  
  public double fullInterSignalBiasUncertaintyNs = 0.0D;
  
  public double satelliteInterSignalBiasNs = 0.0D;
  
  public double satelliteInterSignalBiasUncertaintyNs = 0.0D;
  
  public IGnssMeasurementCallback.GnssMeasurement v2_0 = new IGnssMeasurementCallback.GnssMeasurement();
  
  public static final ArrayList<GnssMeasurement> readVectorFromParcel(HwParcel paramHwParcel) {
    ArrayList<GnssMeasurement> arrayList = new ArrayList();
    HwBlob hwBlob = paramHwParcel.readBuffer(16L);
    int i = hwBlob.getInt32(8L);
    hwBlob = paramHwParcel.readEmbeddedBuffer((i * 224), hwBlob.handle(), 0L, true);
    arrayList.clear();
    for (byte b = 0; b < i; b++) {
      GnssMeasurement gnssMeasurement = new GnssMeasurement();
      gnssMeasurement.readEmbeddedFromParcel(paramHwParcel, hwBlob, (b * 224));
      arrayList.add(gnssMeasurement);
    } 
    return arrayList;
  }
  
  public static final void writeVectorToParcel(HwParcel paramHwParcel, ArrayList<GnssMeasurement> paramArrayList) {
    HwBlob hwBlob1 = new HwBlob(16);
    int i = paramArrayList.size();
    hwBlob1.putInt32(8L, i);
    hwBlob1.putBool(12L, false);
    HwBlob hwBlob2 = new HwBlob(i * 224);
    for (byte b = 0; b < i; b++)
      ((GnssMeasurement)paramArrayList.get(b)).writeEmbeddedToBlob(hwBlob2, (b * 224)); 
    hwBlob1.putBlob(0L, hwBlob2);
    paramHwParcel.writeBuffer(hwBlob1);
  }
  
  public final boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (paramObject == null)
      return false; 
    if (paramObject.getClass() != GnssMeasurement.class)
      return false; 
    paramObject = paramObject;
    return !HidlSupport.deepEquals(this.v2_0, ((GnssMeasurement)paramObject).v2_0) ? false : (!HidlSupport.deepEquals(Integer.valueOf(this.flags), Integer.valueOf(((GnssMeasurement)paramObject).flags)) ? false : ((this.fullInterSignalBiasNs != ((GnssMeasurement)paramObject).fullInterSignalBiasNs) ? false : ((this.fullInterSignalBiasUncertaintyNs != ((GnssMeasurement)paramObject).fullInterSignalBiasUncertaintyNs) ? false : ((this.satelliteInterSignalBiasNs != ((GnssMeasurement)paramObject).satelliteInterSignalBiasNs) ? false : ((this.satelliteInterSignalBiasUncertaintyNs != ((GnssMeasurement)paramObject).satelliteInterSignalBiasUncertaintyNs) ? false : (!(this.basebandCN0DbHz != ((GnssMeasurement)paramObject).basebandCN0DbHz)))))));
  }
  
  public final int hashCode() {
    return Objects.hash(new Object[] { Integer.valueOf(HidlSupport.deepHashCode(this.v2_0)), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.flags))), Integer.valueOf(HidlSupport.deepHashCode(Double.valueOf(this.fullInterSignalBiasNs))), Integer.valueOf(HidlSupport.deepHashCode(Double.valueOf(this.fullInterSignalBiasUncertaintyNs))), Integer.valueOf(HidlSupport.deepHashCode(Double.valueOf(this.satelliteInterSignalBiasNs))), Integer.valueOf(HidlSupport.deepHashCode(Double.valueOf(this.satelliteInterSignalBiasUncertaintyNs))), Integer.valueOf(HidlSupport.deepHashCode(Double.valueOf(this.basebandCN0DbHz))) });
  }
  
  public final void readEmbeddedFromParcel(HwParcel paramHwParcel, HwBlob paramHwBlob, long paramLong) {
    this.v2_0.readEmbeddedFromParcel(paramHwParcel, paramHwBlob, 0L + paramLong);
    this.flags = paramHwBlob.getInt32(176L + paramLong);
    this.fullInterSignalBiasNs = paramHwBlob.getDouble(184L + paramLong);
    this.fullInterSignalBiasUncertaintyNs = paramHwBlob.getDouble(192L + paramLong);
    this.satelliteInterSignalBiasNs = paramHwBlob.getDouble(200L + paramLong);
    this.satelliteInterSignalBiasUncertaintyNs = paramHwBlob.getDouble(208L + paramLong);
    this.basebandCN0DbHz = paramHwBlob.getDouble(216L + paramLong);
  }
  
  public final void readFromParcel(HwParcel paramHwParcel) {
    readEmbeddedFromParcel(paramHwParcel, paramHwParcel.readBuffer(224L), 0L);
  }
  
  public final String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("{");
    stringBuilder.append(".v2_0 = ");
    stringBuilder.append(this.v2_0);
    stringBuilder.append(", .flags = ");
    stringBuilder.append(IGnssMeasurementCallback.GnssMeasurementFlags.dumpBitfield(this.flags));
    stringBuilder.append(", .fullInterSignalBiasNs = ");
    stringBuilder.append(this.fullInterSignalBiasNs);
    stringBuilder.append(", .fullInterSignalBiasUncertaintyNs = ");
    stringBuilder.append(this.fullInterSignalBiasUncertaintyNs);
    stringBuilder.append(", .satelliteInterSignalBiasNs = ");
    stringBuilder.append(this.satelliteInterSignalBiasNs);
    stringBuilder.append(", .satelliteInterSignalBiasUncertaintyNs = ");
    stringBuilder.append(this.satelliteInterSignalBiasUncertaintyNs);
    stringBuilder.append(", .basebandCN0DbHz = ");
    stringBuilder.append(this.basebandCN0DbHz);
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public final void writeEmbeddedToBlob(HwBlob paramHwBlob, long paramLong) {
    this.v2_0.writeEmbeddedToBlob(paramHwBlob, 0L + paramLong);
    paramHwBlob.putInt32(176L + paramLong, this.flags);
    paramHwBlob.putDouble(184L + paramLong, this.fullInterSignalBiasNs);
    paramHwBlob.putDouble(192L + paramLong, this.fullInterSignalBiasUncertaintyNs);
    paramHwBlob.putDouble(200L + paramLong, this.satelliteInterSignalBiasNs);
    paramHwBlob.putDouble(208L + paramLong, this.satelliteInterSignalBiasUncertaintyNs);
    paramHwBlob.putDouble(216L + paramLong, this.basebandCN0DbHz);
  }
  
  public final void writeToParcel(HwParcel paramHwParcel) {
    HwBlob hwBlob = new HwBlob(224);
    writeEmbeddedToBlob(hwBlob, 0L);
    paramHwParcel.writeBuffer(hwBlob);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/gnss/V2_1/IGnssMeasurementCallback$GnssMeasurement.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */