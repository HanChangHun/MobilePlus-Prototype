package android.hardware.gnss.measurement_corrections.V1_0;

import android.hardware.gnss.V1_0.GnssConstellationType;
import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

public final class SingleSatCorrection {
  public float carrierFrequencyHz = 0.0F;
  
  public byte constellation = (byte)0;
  
  public float excessPathLengthMeters = 0.0F;
  
  public float excessPathLengthUncertaintyMeters = 0.0F;
  
  public float probSatIsLos = 0.0F;
  
  public ReflectingPlane reflectingPlane = new ReflectingPlane();
  
  public short singleSatCorrectionFlags;
  
  public short svid = (short)0;
  
  public static final ArrayList<SingleSatCorrection> readVectorFromParcel(HwParcel paramHwParcel) {
    ArrayList<SingleSatCorrection> arrayList = new ArrayList();
    HwBlob hwBlob = paramHwParcel.readBuffer(16L);
    int i = hwBlob.getInt32(8L);
    hwBlob = paramHwParcel.readEmbeddedBuffer((i * 56), hwBlob.handle(), 0L, true);
    arrayList.clear();
    for (byte b = 0; b < i; b++) {
      SingleSatCorrection singleSatCorrection = new SingleSatCorrection();
      singleSatCorrection.readEmbeddedFromParcel(paramHwParcel, hwBlob, (b * 56));
      arrayList.add(singleSatCorrection);
    } 
    return arrayList;
  }
  
  public static final void writeVectorToParcel(HwParcel paramHwParcel, ArrayList<SingleSatCorrection> paramArrayList) {
    HwBlob hwBlob1 = new HwBlob(16);
    int i = paramArrayList.size();
    hwBlob1.putInt32(8L, i);
    hwBlob1.putBool(12L, false);
    HwBlob hwBlob2 = new HwBlob(i * 56);
    for (byte b = 0; b < i; b++)
      ((SingleSatCorrection)paramArrayList.get(b)).writeEmbeddedToBlob(hwBlob2, (b * 56)); 
    hwBlob1.putBlob(0L, hwBlob2);
    paramHwParcel.writeBuffer(hwBlob1);
  }
  
  public final boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (paramObject == null)
      return false; 
    if (paramObject.getClass() != SingleSatCorrection.class)
      return false; 
    paramObject = paramObject;
    return !HidlSupport.deepEquals(Short.valueOf(this.singleSatCorrectionFlags), Short.valueOf(((SingleSatCorrection)paramObject).singleSatCorrectionFlags)) ? false : ((this.constellation != ((SingleSatCorrection)paramObject).constellation) ? false : ((this.svid != ((SingleSatCorrection)paramObject).svid) ? false : ((this.carrierFrequencyHz != ((SingleSatCorrection)paramObject).carrierFrequencyHz) ? false : ((this.probSatIsLos != ((SingleSatCorrection)paramObject).probSatIsLos) ? false : ((this.excessPathLengthMeters != ((SingleSatCorrection)paramObject).excessPathLengthMeters) ? false : ((this.excessPathLengthUncertaintyMeters != ((SingleSatCorrection)paramObject).excessPathLengthUncertaintyMeters) ? false : (!!HidlSupport.deepEquals(this.reflectingPlane, ((SingleSatCorrection)paramObject).reflectingPlane))))))));
  }
  
  public final int hashCode() {
    return Objects.hash(new Object[] { Integer.valueOf(HidlSupport.deepHashCode(Short.valueOf(this.singleSatCorrectionFlags))), Integer.valueOf(HidlSupport.deepHashCode(Byte.valueOf(this.constellation))), Integer.valueOf(HidlSupport.deepHashCode(Short.valueOf(this.svid))), Integer.valueOf(HidlSupport.deepHashCode(Float.valueOf(this.carrierFrequencyHz))), Integer.valueOf(HidlSupport.deepHashCode(Float.valueOf(this.probSatIsLos))), Integer.valueOf(HidlSupport.deepHashCode(Float.valueOf(this.excessPathLengthMeters))), Integer.valueOf(HidlSupport.deepHashCode(Float.valueOf(this.excessPathLengthUncertaintyMeters))), Integer.valueOf(HidlSupport.deepHashCode(this.reflectingPlane)) });
  }
  
  public final void readEmbeddedFromParcel(HwParcel paramHwParcel, HwBlob paramHwBlob, long paramLong) {
    this.singleSatCorrectionFlags = paramHwBlob.getInt16(0L + paramLong);
    this.constellation = paramHwBlob.getInt8(2L + paramLong);
    this.svid = paramHwBlob.getInt16(4L + paramLong);
    this.carrierFrequencyHz = paramHwBlob.getFloat(8L + paramLong);
    this.probSatIsLos = paramHwBlob.getFloat(12L + paramLong);
    this.excessPathLengthMeters = paramHwBlob.getFloat(16L + paramLong);
    this.excessPathLengthUncertaintyMeters = paramHwBlob.getFloat(20L + paramLong);
    this.reflectingPlane.readEmbeddedFromParcel(paramHwParcel, paramHwBlob, 24L + paramLong);
  }
  
  public final void readFromParcel(HwParcel paramHwParcel) {
    readEmbeddedFromParcel(paramHwParcel, paramHwParcel.readBuffer(56L), 0L);
  }
  
  public final String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("{");
    stringBuilder.append(".singleSatCorrectionFlags = ");
    stringBuilder.append(GnssSingleSatCorrectionFlags.dumpBitfield(this.singleSatCorrectionFlags));
    stringBuilder.append(", .constellation = ");
    stringBuilder.append(GnssConstellationType.toString(this.constellation));
    stringBuilder.append(", .svid = ");
    stringBuilder.append(this.svid);
    stringBuilder.append(", .carrierFrequencyHz = ");
    stringBuilder.append(this.carrierFrequencyHz);
    stringBuilder.append(", .probSatIsLos = ");
    stringBuilder.append(this.probSatIsLos);
    stringBuilder.append(", .excessPathLengthMeters = ");
    stringBuilder.append(this.excessPathLengthMeters);
    stringBuilder.append(", .excessPathLengthUncertaintyMeters = ");
    stringBuilder.append(this.excessPathLengthUncertaintyMeters);
    stringBuilder.append(", .reflectingPlane = ");
    stringBuilder.append(this.reflectingPlane);
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public final void writeEmbeddedToBlob(HwBlob paramHwBlob, long paramLong) {
    paramHwBlob.putInt16(0L + paramLong, this.singleSatCorrectionFlags);
    paramHwBlob.putInt8(2L + paramLong, this.constellation);
    paramHwBlob.putInt16(4L + paramLong, this.svid);
    paramHwBlob.putFloat(8L + paramLong, this.carrierFrequencyHz);
    paramHwBlob.putFloat(12L + paramLong, this.probSatIsLos);
    paramHwBlob.putFloat(16L + paramLong, this.excessPathLengthMeters);
    paramHwBlob.putFloat(20L + paramLong, this.excessPathLengthUncertaintyMeters);
    this.reflectingPlane.writeEmbeddedToBlob(paramHwBlob, 24L + paramLong);
  }
  
  public final void writeToParcel(HwParcel paramHwParcel) {
    HwBlob hwBlob = new HwBlob(56);
    writeEmbeddedToBlob(hwBlob, 0L);
    paramHwParcel.writeBuffer(hwBlob);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/gnss/measurement_corrections/V1_0/SingleSatCorrection.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */