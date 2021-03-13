package android.hardware.radio.V1_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

public final class CdmaSmsSubaddress {
  public ArrayList<Byte> digits = new ArrayList<>();
  
  public boolean odd = false;
  
  public int subaddressType = 0;
  
  public static final ArrayList<CdmaSmsSubaddress> readVectorFromParcel(HwParcel paramHwParcel) {
    ArrayList<CdmaSmsSubaddress> arrayList = new ArrayList();
    HwBlob hwBlob = paramHwParcel.readBuffer(16L);
    int i = hwBlob.getInt32(8L);
    hwBlob = paramHwParcel.readEmbeddedBuffer((i * 24), hwBlob.handle(), 0L, true);
    arrayList.clear();
    for (byte b = 0; b < i; b++) {
      CdmaSmsSubaddress cdmaSmsSubaddress = new CdmaSmsSubaddress();
      cdmaSmsSubaddress.readEmbeddedFromParcel(paramHwParcel, hwBlob, (b * 24));
      arrayList.add(cdmaSmsSubaddress);
    } 
    return arrayList;
  }
  
  public static final void writeVectorToParcel(HwParcel paramHwParcel, ArrayList<CdmaSmsSubaddress> paramArrayList) {
    HwBlob hwBlob1 = new HwBlob(16);
    int i = paramArrayList.size();
    hwBlob1.putInt32(8L, i);
    hwBlob1.putBool(12L, false);
    HwBlob hwBlob2 = new HwBlob(i * 24);
    for (byte b = 0; b < i; b++)
      ((CdmaSmsSubaddress)paramArrayList.get(b)).writeEmbeddedToBlob(hwBlob2, (b * 24)); 
    hwBlob1.putBlob(0L, hwBlob2);
    paramHwParcel.writeBuffer(hwBlob1);
  }
  
  public final boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (paramObject == null)
      return false; 
    if (paramObject.getClass() != CdmaSmsSubaddress.class)
      return false; 
    paramObject = paramObject;
    return (this.subaddressType != ((CdmaSmsSubaddress)paramObject).subaddressType) ? false : ((this.odd != ((CdmaSmsSubaddress)paramObject).odd) ? false : (!!HidlSupport.deepEquals(this.digits, ((CdmaSmsSubaddress)paramObject).digits)));
  }
  
  public final int hashCode() {
    return Objects.hash(new Object[] { Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.subaddressType))), Integer.valueOf(HidlSupport.deepHashCode(Boolean.valueOf(this.odd))), Integer.valueOf(HidlSupport.deepHashCode(this.digits)) });
  }
  
  public final void readEmbeddedFromParcel(HwParcel paramHwParcel, HwBlob paramHwBlob, long paramLong) {
    this.subaddressType = paramHwBlob.getInt32(paramLong + 0L);
    this.odd = paramHwBlob.getBool(paramLong + 4L);
    int i = paramHwBlob.getInt32(paramLong + 8L + 8L);
    HwBlob hwBlob = paramHwParcel.readEmbeddedBuffer((i * 1), paramHwBlob.handle(), paramLong + 8L + 0L, true);
    this.digits.clear();
    for (byte b = 0; b < i; b++) {
      byte b1 = hwBlob.getInt8((b * 1));
      this.digits.add(Byte.valueOf(b1));
    } 
  }
  
  public final void readFromParcel(HwParcel paramHwParcel) {
    readEmbeddedFromParcel(paramHwParcel, paramHwParcel.readBuffer(24L), 0L);
  }
  
  public final String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("{");
    stringBuilder.append(".subaddressType = ");
    stringBuilder.append(CdmaSmsSubaddressType.toString(this.subaddressType));
    stringBuilder.append(", .odd = ");
    stringBuilder.append(this.odd);
    stringBuilder.append(", .digits = ");
    stringBuilder.append(this.digits);
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public final void writeEmbeddedToBlob(HwBlob paramHwBlob, long paramLong) {
    paramHwBlob.putInt32(paramLong + 0L, this.subaddressType);
    paramHwBlob.putBool(4L + paramLong, this.odd);
    int i = this.digits.size();
    paramHwBlob.putInt32(paramLong + 8L + 8L, i);
    paramHwBlob.putBool(paramLong + 8L + 12L, false);
    HwBlob hwBlob = new HwBlob(i * 1);
    for (byte b = 0; b < i; b++)
      hwBlob.putInt8((b * 1), ((Byte)this.digits.get(b)).byteValue()); 
    paramHwBlob.putBlob(8L + paramLong + 0L, hwBlob);
  }
  
  public final void writeToParcel(HwParcel paramHwParcel) {
    HwBlob hwBlob = new HwBlob(24);
    writeEmbeddedToBlob(hwBlob, 0L);
    paramHwParcel.writeBuffer(hwBlob);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/V1_0/CdmaSmsSubaddress.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */