package android.hardware.gnss.V2_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

public final class NetworkAttributes {
  public String apn = new String();
  
  public short capabilities;
  
  public boolean isConnected = false;
  
  public long networkHandle = 0L;
  
  public static final ArrayList<NetworkAttributes> readVectorFromParcel(HwParcel paramHwParcel) {
    ArrayList<NetworkAttributes> arrayList = new ArrayList();
    HwBlob hwBlob = paramHwParcel.readBuffer(16L);
    int i = hwBlob.getInt32(8L);
    hwBlob = paramHwParcel.readEmbeddedBuffer((i * 32), hwBlob.handle(), 0L, true);
    arrayList.clear();
    for (byte b = 0; b < i; b++) {
      NetworkAttributes networkAttributes = new NetworkAttributes();
      networkAttributes.readEmbeddedFromParcel(paramHwParcel, hwBlob, (b * 32));
      arrayList.add(networkAttributes);
    } 
    return arrayList;
  }
  
  public static final void writeVectorToParcel(HwParcel paramHwParcel, ArrayList<NetworkAttributes> paramArrayList) {
    HwBlob hwBlob1 = new HwBlob(16);
    int i = paramArrayList.size();
    hwBlob1.putInt32(8L, i);
    hwBlob1.putBool(12L, false);
    HwBlob hwBlob2 = new HwBlob(i * 32);
    for (byte b = 0; b < i; b++)
      ((NetworkAttributes)paramArrayList.get(b)).writeEmbeddedToBlob(hwBlob2, (b * 32)); 
    hwBlob1.putBlob(0L, hwBlob2);
    paramHwParcel.writeBuffer(hwBlob1);
  }
  
  public final boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (paramObject == null)
      return false; 
    if (paramObject.getClass() != NetworkAttributes.class)
      return false; 
    paramObject = paramObject;
    return (this.networkHandle != ((NetworkAttributes)paramObject).networkHandle) ? false : ((this.isConnected != ((NetworkAttributes)paramObject).isConnected) ? false : (!HidlSupport.deepEquals(Short.valueOf(this.capabilities), Short.valueOf(((NetworkAttributes)paramObject).capabilities)) ? false : (!!HidlSupport.deepEquals(this.apn, ((NetworkAttributes)paramObject).apn))));
  }
  
  public final int hashCode() {
    return Objects.hash(new Object[] { Integer.valueOf(HidlSupport.deepHashCode(Long.valueOf(this.networkHandle))), Integer.valueOf(HidlSupport.deepHashCode(Boolean.valueOf(this.isConnected))), Integer.valueOf(HidlSupport.deepHashCode(Short.valueOf(this.capabilities))), Integer.valueOf(HidlSupport.deepHashCode(this.apn)) });
  }
  
  public final void readEmbeddedFromParcel(HwParcel paramHwParcel, HwBlob paramHwBlob, long paramLong) {
    this.networkHandle = paramHwBlob.getInt64(paramLong + 0L);
    this.isConnected = paramHwBlob.getBool(paramLong + 8L);
    this.capabilities = paramHwBlob.getInt16(paramLong + 10L);
    String str = paramHwBlob.getString(paramLong + 16L);
    this.apn = str;
    paramHwParcel.readEmbeddedBuffer(((str.getBytes()).length + 1), paramHwBlob.handle(), paramLong + 16L + 0L, false);
  }
  
  public final void readFromParcel(HwParcel paramHwParcel) {
    readEmbeddedFromParcel(paramHwParcel, paramHwParcel.readBuffer(32L), 0L);
  }
  
  public final String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("{");
    stringBuilder.append(".networkHandle = ");
    stringBuilder.append(this.networkHandle);
    stringBuilder.append(", .isConnected = ");
    stringBuilder.append(this.isConnected);
    stringBuilder.append(", .capabilities = ");
    stringBuilder.append(IAGnssRil.NetworkCapability.dumpBitfield(this.capabilities));
    stringBuilder.append(", .apn = ");
    stringBuilder.append(this.apn);
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public final void writeEmbeddedToBlob(HwBlob paramHwBlob, long paramLong) {
    paramHwBlob.putInt64(0L + paramLong, this.networkHandle);
    paramHwBlob.putBool(8L + paramLong, this.isConnected);
    paramHwBlob.putInt16(10L + paramLong, this.capabilities);
    paramHwBlob.putString(16L + paramLong, this.apn);
  }
  
  public final void writeToParcel(HwParcel paramHwParcel) {
    HwBlob hwBlob = new HwBlob(32);
    writeEmbeddedToBlob(hwBlob, 0L);
    paramHwParcel.writeBuffer(hwBlob);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/gnss/V2_0/IAGnssRil$NetworkAttributes.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */