package android.hardware.gnss.V2_0;

import android.hardware.gnss.V1_1.IGnssMeasurementCallback;
import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

public final class GnssMeasurement {
  public String codeType = new String();
  
  public byte constellation = (byte)0;
  
  public int state;
  
  public IGnssMeasurementCallback.GnssMeasurement v1_1 = new IGnssMeasurementCallback.GnssMeasurement();
  
  public static final ArrayList<GnssMeasurement> readVectorFromParcel(HwParcel paramHwParcel) {
    ArrayList<GnssMeasurement> arrayList = new ArrayList();
    HwBlob hwBlob1 = paramHwParcel.readBuffer(16L);
    int i = hwBlob1.getInt32(8L);
    HwBlob hwBlob2 = paramHwParcel.readEmbeddedBuffer((i * 176), hwBlob1.handle(), 0L, true);
    arrayList.clear();
    for (byte b = 0; b < i; b++) {
      GnssMeasurement gnssMeasurement = new GnssMeasurement();
      gnssMeasurement.readEmbeddedFromParcel(paramHwParcel, hwBlob2, (b * 176));
      arrayList.add(gnssMeasurement);
    } 
    return arrayList;
  }
  
  public static final void writeVectorToParcel(HwParcel paramHwParcel, ArrayList<GnssMeasurement> paramArrayList) {
    HwBlob hwBlob1 = new HwBlob(16);
    int i = paramArrayList.size();
    hwBlob1.putInt32(8L, i);
    hwBlob1.putBool(12L, false);
    HwBlob hwBlob2 = new HwBlob(i * 176);
    for (byte b = 0; b < i; b++)
      ((GnssMeasurement)paramArrayList.get(b)).writeEmbeddedToBlob(hwBlob2, (b * 176)); 
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
    return !HidlSupport.deepEquals(this.v1_1, ((GnssMeasurement)paramObject).v1_1) ? false : (!HidlSupport.deepEquals(this.codeType, ((GnssMeasurement)paramObject).codeType) ? false : (!HidlSupport.deepEquals(Integer.valueOf(this.state), Integer.valueOf(((GnssMeasurement)paramObject).state)) ? false : (!(this.constellation != ((GnssMeasurement)paramObject).constellation))));
  }
  
  public final int hashCode() {
    return Objects.hash(new Object[] { Integer.valueOf(HidlSupport.deepHashCode(this.v1_1)), Integer.valueOf(HidlSupport.deepHashCode(this.codeType)), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.state))), Integer.valueOf(HidlSupport.deepHashCode(Byte.valueOf(this.constellation))) });
  }
  
  public final void readEmbeddedFromParcel(HwParcel paramHwParcel, HwBlob paramHwBlob, long paramLong) {
    this.v1_1.readEmbeddedFromParcel(paramHwParcel, paramHwBlob, paramLong + 0L);
    String str = paramHwBlob.getString(paramLong + 152L);
    this.codeType = str;
    paramHwParcel.readEmbeddedBuffer(((str.getBytes()).length + 1), paramHwBlob.handle(), paramLong + 152L + 0L, false);
    this.state = paramHwBlob.getInt32(paramLong + 168L);
    this.constellation = paramHwBlob.getInt8(paramLong + 172L);
  }
  
  public final void readFromParcel(HwParcel paramHwParcel) {
    readEmbeddedFromParcel(paramHwParcel, paramHwParcel.readBuffer(176L), 0L);
  }
  
  public final String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("{");
    stringBuilder.append(".v1_1 = ");
    stringBuilder.append(this.v1_1);
    stringBuilder.append(", .codeType = ");
    stringBuilder.append(this.codeType);
    stringBuilder.append(", .state = ");
    stringBuilder.append(IGnssMeasurementCallback.GnssMeasurementState.dumpBitfield(this.state));
    stringBuilder.append(", .constellation = ");
    stringBuilder.append(GnssConstellationType.toString(this.constellation));
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public final void writeEmbeddedToBlob(HwBlob paramHwBlob, long paramLong) {
    this.v1_1.writeEmbeddedToBlob(paramHwBlob, 0L + paramLong);
    paramHwBlob.putString(152L + paramLong, this.codeType);
    paramHwBlob.putInt32(168L + paramLong, this.state);
    paramHwBlob.putInt8(172L + paramLong, this.constellation);
  }
  
  public final void writeToParcel(HwParcel paramHwParcel) {
    HwBlob hwBlob = new HwBlob(176);
    writeEmbeddedToBlob(hwBlob, 0L);
    paramHwParcel.writeBuffer(hwBlob);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/gnss/V2_0/IGnssMeasurementCallback$GnssMeasurement.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */