package android.hardware.gnss.measurement_corrections.V1_1;

import android.hardware.gnss.V2_0.GnssConstellationType;
import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

public final class SingleSatCorrection {
  public byte constellation = (byte)0;
  
  public android.hardware.gnss.measurement_corrections.V1_0.SingleSatCorrection v1_0 = new android.hardware.gnss.measurement_corrections.V1_0.SingleSatCorrection();
  
  public static final ArrayList<SingleSatCorrection> readVectorFromParcel(HwParcel paramHwParcel) {
    ArrayList<SingleSatCorrection> arrayList = new ArrayList();
    HwBlob hwBlob = paramHwParcel.readBuffer(16L);
    int i = hwBlob.getInt32(8L);
    hwBlob = paramHwParcel.readEmbeddedBuffer((i * 64), hwBlob.handle(), 0L, true);
    arrayList.clear();
    for (byte b = 0; b < i; b++) {
      SingleSatCorrection singleSatCorrection = new SingleSatCorrection();
      singleSatCorrection.readEmbeddedFromParcel(paramHwParcel, hwBlob, (b * 64));
      arrayList.add(singleSatCorrection);
    } 
    return arrayList;
  }
  
  public static final void writeVectorToParcel(HwParcel paramHwParcel, ArrayList<SingleSatCorrection> paramArrayList) {
    HwBlob hwBlob1 = new HwBlob(16);
    int i = paramArrayList.size();
    hwBlob1.putInt32(8L, i);
    hwBlob1.putBool(12L, false);
    HwBlob hwBlob2 = new HwBlob(i * 64);
    for (byte b = 0; b < i; b++)
      ((SingleSatCorrection)paramArrayList.get(b)).writeEmbeddedToBlob(hwBlob2, (b * 64)); 
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
    return !HidlSupport.deepEquals(this.v1_0, ((SingleSatCorrection)paramObject).v1_0) ? false : (!(this.constellation != ((SingleSatCorrection)paramObject).constellation));
  }
  
  public final int hashCode() {
    return Objects.hash(new Object[] { Integer.valueOf(HidlSupport.deepHashCode(this.v1_0)), Integer.valueOf(HidlSupport.deepHashCode(Byte.valueOf(this.constellation))) });
  }
  
  public final void readEmbeddedFromParcel(HwParcel paramHwParcel, HwBlob paramHwBlob, long paramLong) {
    this.v1_0.readEmbeddedFromParcel(paramHwParcel, paramHwBlob, 0L + paramLong);
    this.constellation = paramHwBlob.getInt8(56L + paramLong);
  }
  
  public final void readFromParcel(HwParcel paramHwParcel) {
    readEmbeddedFromParcel(paramHwParcel, paramHwParcel.readBuffer(64L), 0L);
  }
  
  public final String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("{");
    stringBuilder.append(".v1_0 = ");
    stringBuilder.append(this.v1_0);
    stringBuilder.append(", .constellation = ");
    stringBuilder.append(GnssConstellationType.toString(this.constellation));
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public final void writeEmbeddedToBlob(HwBlob paramHwBlob, long paramLong) {
    this.v1_0.writeEmbeddedToBlob(paramHwBlob, 0L + paramLong);
    paramHwBlob.putInt8(56L + paramLong, this.constellation);
  }
  
  public final void writeToParcel(HwParcel paramHwParcel) {
    HwBlob hwBlob = new HwBlob(64);
    writeEmbeddedToBlob(hwBlob, 0L);
    paramHwParcel.writeBuffer(hwBlob);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/gnss/measurement_corrections/V1_1/SingleSatCorrection.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */