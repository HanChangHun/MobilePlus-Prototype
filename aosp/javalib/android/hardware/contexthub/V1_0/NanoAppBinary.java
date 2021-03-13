package android.hardware.contexthub.V1_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

public final class NanoAppBinary {
  public long appId = 0L;
  
  public int appVersion = 0;
  
  public ArrayList<Byte> customBinary = new ArrayList<>();
  
  public int flags;
  
  public byte targetChreApiMajorVersion = (byte)0;
  
  public byte targetChreApiMinorVersion = (byte)0;
  
  public static final ArrayList<NanoAppBinary> readVectorFromParcel(HwParcel paramHwParcel) {
    ArrayList<NanoAppBinary> arrayList = new ArrayList();
    HwBlob hwBlob = paramHwParcel.readBuffer(16L);
    int i = hwBlob.getInt32(8L);
    hwBlob = paramHwParcel.readEmbeddedBuffer((i * 40), hwBlob.handle(), 0L, true);
    arrayList.clear();
    for (byte b = 0; b < i; b++) {
      NanoAppBinary nanoAppBinary = new NanoAppBinary();
      nanoAppBinary.readEmbeddedFromParcel(paramHwParcel, hwBlob, (b * 40));
      arrayList.add(nanoAppBinary);
    } 
    return arrayList;
  }
  
  public static final void writeVectorToParcel(HwParcel paramHwParcel, ArrayList<NanoAppBinary> paramArrayList) {
    HwBlob hwBlob1 = new HwBlob(16);
    int i = paramArrayList.size();
    hwBlob1.putInt32(8L, i);
    hwBlob1.putBool(12L, false);
    HwBlob hwBlob2 = new HwBlob(i * 40);
    for (byte b = 0; b < i; b++)
      ((NanoAppBinary)paramArrayList.get(b)).writeEmbeddedToBlob(hwBlob2, (b * 40)); 
    hwBlob1.putBlob(0L, hwBlob2);
    paramHwParcel.writeBuffer(hwBlob1);
  }
  
  public final boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (paramObject == null)
      return false; 
    if (paramObject.getClass() != NanoAppBinary.class)
      return false; 
    paramObject = paramObject;
    return (this.appId != ((NanoAppBinary)paramObject).appId) ? false : ((this.appVersion != ((NanoAppBinary)paramObject).appVersion) ? false : (!HidlSupport.deepEquals(Integer.valueOf(this.flags), Integer.valueOf(((NanoAppBinary)paramObject).flags)) ? false : ((this.targetChreApiMajorVersion != ((NanoAppBinary)paramObject).targetChreApiMajorVersion) ? false : ((this.targetChreApiMinorVersion != ((NanoAppBinary)paramObject).targetChreApiMinorVersion) ? false : (!!HidlSupport.deepEquals(this.customBinary, ((NanoAppBinary)paramObject).customBinary))))));
  }
  
  public final int hashCode() {
    return Objects.hash(new Object[] { Integer.valueOf(HidlSupport.deepHashCode(Long.valueOf(this.appId))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.appVersion))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.flags))), Integer.valueOf(HidlSupport.deepHashCode(Byte.valueOf(this.targetChreApiMajorVersion))), Integer.valueOf(HidlSupport.deepHashCode(Byte.valueOf(this.targetChreApiMinorVersion))), Integer.valueOf(HidlSupport.deepHashCode(this.customBinary)) });
  }
  
  public final void readEmbeddedFromParcel(HwParcel paramHwParcel, HwBlob paramHwBlob, long paramLong) {
    this.appId = paramHwBlob.getInt64(paramLong + 0L);
    this.appVersion = paramHwBlob.getInt32(paramLong + 8L);
    this.flags = paramHwBlob.getInt32(paramLong + 12L);
    this.targetChreApiMajorVersion = paramHwBlob.getInt8(paramLong + 16L);
    this.targetChreApiMinorVersion = paramHwBlob.getInt8(paramLong + 17L);
    int i = paramHwBlob.getInt32(paramLong + 24L + 8L);
    HwBlob hwBlob = paramHwParcel.readEmbeddedBuffer((i * 1), paramHwBlob.handle(), paramLong + 24L + 0L, true);
    this.customBinary.clear();
    for (byte b = 0; b < i; b++) {
      byte b1 = hwBlob.getInt8((b * 1));
      this.customBinary.add(Byte.valueOf(b1));
    } 
  }
  
  public final void readFromParcel(HwParcel paramHwParcel) {
    readEmbeddedFromParcel(paramHwParcel, paramHwParcel.readBuffer(40L), 0L);
  }
  
  public final String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("{");
    stringBuilder.append(".appId = ");
    stringBuilder.append(this.appId);
    stringBuilder.append(", .appVersion = ");
    stringBuilder.append(this.appVersion);
    stringBuilder.append(", .flags = ");
    stringBuilder.append(NanoAppFlags.dumpBitfield(this.flags));
    stringBuilder.append(", .targetChreApiMajorVersion = ");
    stringBuilder.append(this.targetChreApiMajorVersion);
    stringBuilder.append(", .targetChreApiMinorVersion = ");
    stringBuilder.append(this.targetChreApiMinorVersion);
    stringBuilder.append(", .customBinary = ");
    stringBuilder.append(this.customBinary);
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public final void writeEmbeddedToBlob(HwBlob paramHwBlob, long paramLong) {
    paramHwBlob.putInt64(paramLong + 0L, this.appId);
    paramHwBlob.putInt32(paramLong + 8L, this.appVersion);
    paramHwBlob.putInt32(paramLong + 12L, this.flags);
    paramHwBlob.putInt8(16L + paramLong, this.targetChreApiMajorVersion);
    paramHwBlob.putInt8(17L + paramLong, this.targetChreApiMinorVersion);
    int i = this.customBinary.size();
    paramHwBlob.putInt32(paramLong + 24L + 8L, i);
    paramHwBlob.putBool(paramLong + 24L + 12L, false);
    HwBlob hwBlob = new HwBlob(i * 1);
    for (byte b = 0; b < i; b++)
      hwBlob.putInt8((b * 1), ((Byte)this.customBinary.get(b)).byteValue()); 
    paramHwBlob.putBlob(24L + paramLong + 0L, hwBlob);
  }
  
  public final void writeToParcel(HwParcel paramHwParcel) {
    HwBlob hwBlob = new HwBlob(40);
    writeEmbeddedToBlob(hwBlob, 0L);
    paramHwParcel.writeBuffer(hwBlob);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/contexthub/V1_0/NanoAppBinary.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */