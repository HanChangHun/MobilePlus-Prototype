package android.hardware.gnss.V1_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

public final class AGnssStatusIpV4 {
  public int ipV4Addr = 0;
  
  public byte status = (byte)0;
  
  public byte type = (byte)0;
  
  public static final ArrayList<AGnssStatusIpV4> readVectorFromParcel(HwParcel paramHwParcel) {
    ArrayList<AGnssStatusIpV4> arrayList = new ArrayList();
    HwBlob hwBlob = paramHwParcel.readBuffer(16L);
    int i = hwBlob.getInt32(8L);
    hwBlob = paramHwParcel.readEmbeddedBuffer((i * 8), hwBlob.handle(), 0L, true);
    arrayList.clear();
    for (byte b = 0; b < i; b++) {
      AGnssStatusIpV4 aGnssStatusIpV4 = new AGnssStatusIpV4();
      aGnssStatusIpV4.readEmbeddedFromParcel(paramHwParcel, hwBlob, (b * 8));
      arrayList.add(aGnssStatusIpV4);
    } 
    return arrayList;
  }
  
  public static final void writeVectorToParcel(HwParcel paramHwParcel, ArrayList<AGnssStatusIpV4> paramArrayList) {
    HwBlob hwBlob1 = new HwBlob(16);
    int i = paramArrayList.size();
    hwBlob1.putInt32(8L, i);
    hwBlob1.putBool(12L, false);
    HwBlob hwBlob2 = new HwBlob(i * 8);
    for (byte b = 0; b < i; b++)
      ((AGnssStatusIpV4)paramArrayList.get(b)).writeEmbeddedToBlob(hwBlob2, (b * 8)); 
    hwBlob1.putBlob(0L, hwBlob2);
    paramHwParcel.writeBuffer(hwBlob1);
  }
  
  public final boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (paramObject == null)
      return false; 
    if (paramObject.getClass() != AGnssStatusIpV4.class)
      return false; 
    paramObject = paramObject;
    return (this.type != ((AGnssStatusIpV4)paramObject).type) ? false : ((this.status != ((AGnssStatusIpV4)paramObject).status) ? false : (!(this.ipV4Addr != ((AGnssStatusIpV4)paramObject).ipV4Addr)));
  }
  
  public final int hashCode() {
    return Objects.hash(new Object[] { Integer.valueOf(HidlSupport.deepHashCode(Byte.valueOf(this.type))), Integer.valueOf(HidlSupport.deepHashCode(Byte.valueOf(this.status))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.ipV4Addr))) });
  }
  
  public final void readEmbeddedFromParcel(HwParcel paramHwParcel, HwBlob paramHwBlob, long paramLong) {
    this.type = paramHwBlob.getInt8(0L + paramLong);
    this.status = paramHwBlob.getInt8(1L + paramLong);
    this.ipV4Addr = paramHwBlob.getInt32(4L + paramLong);
  }
  
  public final void readFromParcel(HwParcel paramHwParcel) {
    readEmbeddedFromParcel(paramHwParcel, paramHwParcel.readBuffer(8L), 0L);
  }
  
  public final String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("{");
    stringBuilder.append(".type = ");
    stringBuilder.append(IAGnssCallback.AGnssType.toString(this.type));
    stringBuilder.append(", .status = ");
    stringBuilder.append(IAGnssCallback.AGnssStatusValue.toString(this.status));
    stringBuilder.append(", .ipV4Addr = ");
    stringBuilder.append(this.ipV4Addr);
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public final void writeEmbeddedToBlob(HwBlob paramHwBlob, long paramLong) {
    paramHwBlob.putInt8(0L + paramLong, this.type);
    paramHwBlob.putInt8(1L + paramLong, this.status);
    paramHwBlob.putInt32(4L + paramLong, this.ipV4Addr);
  }
  
  public final void writeToParcel(HwParcel paramHwParcel) {
    HwBlob hwBlob = new HwBlob(8);
    writeEmbeddedToBlob(hwBlob, 0L);
    paramHwParcel.writeBuffer(hwBlob);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/gnss/V1_0/IAGnssCallback$AGnssStatusIpV4.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */