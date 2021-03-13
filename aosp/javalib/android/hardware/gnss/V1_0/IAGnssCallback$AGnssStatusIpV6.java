package android.hardware.gnss.V1_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public final class AGnssStatusIpV6 {
  public byte[] ipV6Addr = new byte[16];
  
  public byte status = (byte)0;
  
  public byte type = (byte)0;
  
  public static final ArrayList<AGnssStatusIpV6> readVectorFromParcel(HwParcel paramHwParcel) {
    ArrayList<AGnssStatusIpV6> arrayList = new ArrayList();
    HwBlob hwBlob = paramHwParcel.readBuffer(16L);
    int i = hwBlob.getInt32(8L);
    hwBlob = paramHwParcel.readEmbeddedBuffer((i * 18), hwBlob.handle(), 0L, true);
    arrayList.clear();
    for (byte b = 0; b < i; b++) {
      AGnssStatusIpV6 aGnssStatusIpV6 = new AGnssStatusIpV6();
      aGnssStatusIpV6.readEmbeddedFromParcel(paramHwParcel, hwBlob, (b * 18));
      arrayList.add(aGnssStatusIpV6);
    } 
    return arrayList;
  }
  
  public static final void writeVectorToParcel(HwParcel paramHwParcel, ArrayList<AGnssStatusIpV6> paramArrayList) {
    HwBlob hwBlob1 = new HwBlob(16);
    int i = paramArrayList.size();
    hwBlob1.putInt32(8L, i);
    hwBlob1.putBool(12L, false);
    HwBlob hwBlob2 = new HwBlob(i * 18);
    for (byte b = 0; b < i; b++)
      ((AGnssStatusIpV6)paramArrayList.get(b)).writeEmbeddedToBlob(hwBlob2, (b * 18)); 
    hwBlob1.putBlob(0L, hwBlob2);
    paramHwParcel.writeBuffer(hwBlob1);
  }
  
  public final boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (paramObject == null)
      return false; 
    if (paramObject.getClass() != AGnssStatusIpV6.class)
      return false; 
    paramObject = paramObject;
    return (this.type != ((AGnssStatusIpV6)paramObject).type) ? false : ((this.status != ((AGnssStatusIpV6)paramObject).status) ? false : (!!HidlSupport.deepEquals(this.ipV6Addr, ((AGnssStatusIpV6)paramObject).ipV6Addr)));
  }
  
  public final int hashCode() {
    return Objects.hash(new Object[] { Integer.valueOf(HidlSupport.deepHashCode(Byte.valueOf(this.type))), Integer.valueOf(HidlSupport.deepHashCode(Byte.valueOf(this.status))), Integer.valueOf(HidlSupport.deepHashCode(this.ipV6Addr)) });
  }
  
  public final void readEmbeddedFromParcel(HwParcel paramHwParcel, HwBlob paramHwBlob, long paramLong) {
    this.type = paramHwBlob.getInt8(0L + paramLong);
    this.status = paramHwBlob.getInt8(1L + paramLong);
    paramHwBlob.copyToInt8Array(2L + paramLong, this.ipV6Addr, 16);
  }
  
  public final void readFromParcel(HwParcel paramHwParcel) {
    readEmbeddedFromParcel(paramHwParcel, paramHwParcel.readBuffer(18L), 0L);
  }
  
  public final String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("{");
    stringBuilder.append(".type = ");
    stringBuilder.append(IAGnssCallback.AGnssType.toString(this.type));
    stringBuilder.append(", .status = ");
    stringBuilder.append(IAGnssCallback.AGnssStatusValue.toString(this.status));
    stringBuilder.append(", .ipV6Addr = ");
    stringBuilder.append(Arrays.toString(this.ipV6Addr));
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public final void writeEmbeddedToBlob(HwBlob paramHwBlob, long paramLong) {
    paramHwBlob.putInt8(0L + paramLong, this.type);
    paramHwBlob.putInt8(1L + paramLong, this.status);
    byte[] arrayOfByte = this.ipV6Addr;
    if (arrayOfByte != null && arrayOfByte.length == 16) {
      paramHwBlob.putInt8Array(2L + paramLong, arrayOfByte);
      return;
    } 
    throw new IllegalArgumentException("Array element is not of the expected length");
  }
  
  public final void writeToParcel(HwParcel paramHwParcel) {
    HwBlob hwBlob = new HwBlob(18);
    writeEmbeddedToBlob(hwBlob, 0L);
    paramHwParcel.writeBuffer(hwBlob);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/gnss/V1_0/IAGnssCallback$AGnssStatusIpV6.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */