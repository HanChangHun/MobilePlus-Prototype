package android.hardware.radio.V1_1;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

public final class ImsiEncryptionInfo {
  public ArrayList<Byte> carrierKey = new ArrayList<>();
  
  public long expirationTime = 0L;
  
  public String keyIdentifier = new String();
  
  public String mcc = new String();
  
  public String mnc = new String();
  
  public static final ArrayList<ImsiEncryptionInfo> readVectorFromParcel(HwParcel paramHwParcel) {
    ArrayList<ImsiEncryptionInfo> arrayList = new ArrayList();
    HwBlob hwBlob = paramHwParcel.readBuffer(16L);
    int i = hwBlob.getInt32(8L);
    hwBlob = paramHwParcel.readEmbeddedBuffer((i * 72), hwBlob.handle(), 0L, true);
    arrayList.clear();
    for (byte b = 0; b < i; b++) {
      ImsiEncryptionInfo imsiEncryptionInfo = new ImsiEncryptionInfo();
      imsiEncryptionInfo.readEmbeddedFromParcel(paramHwParcel, hwBlob, (b * 72));
      arrayList.add(imsiEncryptionInfo);
    } 
    return arrayList;
  }
  
  public static final void writeVectorToParcel(HwParcel paramHwParcel, ArrayList<ImsiEncryptionInfo> paramArrayList) {
    HwBlob hwBlob1 = new HwBlob(16);
    int i = paramArrayList.size();
    hwBlob1.putInt32(8L, i);
    hwBlob1.putBool(12L, false);
    HwBlob hwBlob2 = new HwBlob(i * 72);
    for (byte b = 0; b < i; b++)
      ((ImsiEncryptionInfo)paramArrayList.get(b)).writeEmbeddedToBlob(hwBlob2, (b * 72)); 
    hwBlob1.putBlob(0L, hwBlob2);
    paramHwParcel.writeBuffer(hwBlob1);
  }
  
  public final boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (paramObject == null)
      return false; 
    if (paramObject.getClass() != ImsiEncryptionInfo.class)
      return false; 
    paramObject = paramObject;
    return !HidlSupport.deepEquals(this.mcc, ((ImsiEncryptionInfo)paramObject).mcc) ? false : (!HidlSupport.deepEquals(this.mnc, ((ImsiEncryptionInfo)paramObject).mnc) ? false : (!HidlSupport.deepEquals(this.carrierKey, ((ImsiEncryptionInfo)paramObject).carrierKey) ? false : (!HidlSupport.deepEquals(this.keyIdentifier, ((ImsiEncryptionInfo)paramObject).keyIdentifier) ? false : (!(this.expirationTime != ((ImsiEncryptionInfo)paramObject).expirationTime)))));
  }
  
  public final int hashCode() {
    return Objects.hash(new Object[] { Integer.valueOf(HidlSupport.deepHashCode(this.mcc)), Integer.valueOf(HidlSupport.deepHashCode(this.mnc)), Integer.valueOf(HidlSupport.deepHashCode(this.carrierKey)), Integer.valueOf(HidlSupport.deepHashCode(this.keyIdentifier)), Integer.valueOf(HidlSupport.deepHashCode(Long.valueOf(this.expirationTime))) });
  }
  
  public final void readEmbeddedFromParcel(HwParcel paramHwParcel, HwBlob paramHwBlob, long paramLong) {
    String str2 = paramHwBlob.getString(paramLong + 0L);
    this.mcc = str2;
    paramHwParcel.readEmbeddedBuffer(((str2.getBytes()).length + 1), paramHwBlob.handle(), paramLong + 0L + 0L, false);
    str2 = paramHwBlob.getString(paramLong + 16L);
    this.mnc = str2;
    paramHwParcel.readEmbeddedBuffer(((str2.getBytes()).length + 1), paramHwBlob.handle(), paramLong + 16L + 0L, false);
    int i = paramHwBlob.getInt32(paramLong + 32L + 8L);
    HwBlob hwBlob = paramHwParcel.readEmbeddedBuffer((i * 1), paramHwBlob.handle(), paramLong + 32L + 0L, true);
    this.carrierKey.clear();
    for (byte b = 0; b < i; b++) {
      byte b1 = hwBlob.getInt8((b * 1));
      this.carrierKey.add(Byte.valueOf(b1));
    } 
    String str1 = paramHwBlob.getString(paramLong + 48L);
    this.keyIdentifier = str1;
    paramHwParcel.readEmbeddedBuffer(((str1.getBytes()).length + 1), paramHwBlob.handle(), paramLong + 48L + 0L, false);
    this.expirationTime = paramHwBlob.getInt64(paramLong + 64L);
  }
  
  public final void readFromParcel(HwParcel paramHwParcel) {
    readEmbeddedFromParcel(paramHwParcel, paramHwParcel.readBuffer(72L), 0L);
  }
  
  public final String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("{");
    stringBuilder.append(".mcc = ");
    stringBuilder.append(this.mcc);
    stringBuilder.append(", .mnc = ");
    stringBuilder.append(this.mnc);
    stringBuilder.append(", .carrierKey = ");
    stringBuilder.append(this.carrierKey);
    stringBuilder.append(", .keyIdentifier = ");
    stringBuilder.append(this.keyIdentifier);
    stringBuilder.append(", .expirationTime = ");
    stringBuilder.append(this.expirationTime);
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public final void writeEmbeddedToBlob(HwBlob paramHwBlob, long paramLong) {
    paramHwBlob.putString(paramLong + 0L, this.mcc);
    paramHwBlob.putString(16L + paramLong, this.mnc);
    int i = this.carrierKey.size();
    paramHwBlob.putInt32(paramLong + 32L + 8L, i);
    paramHwBlob.putBool(paramLong + 32L + 12L, false);
    HwBlob hwBlob = new HwBlob(i * 1);
    for (byte b = 0; b < i; b++)
      hwBlob.putInt8((b * 1), ((Byte)this.carrierKey.get(b)).byteValue()); 
    paramHwBlob.putBlob(32L + paramLong + 0L, hwBlob);
    paramHwBlob.putString(48L + paramLong, this.keyIdentifier);
    paramHwBlob.putInt64(64L + paramLong, this.expirationTime);
  }
  
  public final void writeToParcel(HwParcel paramHwParcel) {
    HwBlob hwBlob = new HwBlob(72);
    writeEmbeddedToBlob(hwBlob, 0L);
    paramHwParcel.writeBuffer(hwBlob);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/V1_1/ImsiEncryptionInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */