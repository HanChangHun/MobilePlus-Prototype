package android.hardware.radio.V1_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

public final class CdmaSmsAddress {
  public int digitMode = 0;
  
  public ArrayList<Byte> digits = new ArrayList<>();
  
  public int numberMode = 0;
  
  public int numberPlan = 0;
  
  public int numberType = 0;
  
  public static final ArrayList<CdmaSmsAddress> readVectorFromParcel(HwParcel paramHwParcel) {
    ArrayList<CdmaSmsAddress> arrayList = new ArrayList();
    HwBlob hwBlob = paramHwParcel.readBuffer(16L);
    int i = hwBlob.getInt32(8L);
    hwBlob = paramHwParcel.readEmbeddedBuffer((i * 32), hwBlob.handle(), 0L, true);
    arrayList.clear();
    for (byte b = 0; b < i; b++) {
      CdmaSmsAddress cdmaSmsAddress = new CdmaSmsAddress();
      cdmaSmsAddress.readEmbeddedFromParcel(paramHwParcel, hwBlob, (b * 32));
      arrayList.add(cdmaSmsAddress);
    } 
    return arrayList;
  }
  
  public static final void writeVectorToParcel(HwParcel paramHwParcel, ArrayList<CdmaSmsAddress> paramArrayList) {
    HwBlob hwBlob1 = new HwBlob(16);
    int i = paramArrayList.size();
    hwBlob1.putInt32(8L, i);
    hwBlob1.putBool(12L, false);
    HwBlob hwBlob2 = new HwBlob(i * 32);
    for (byte b = 0; b < i; b++)
      ((CdmaSmsAddress)paramArrayList.get(b)).writeEmbeddedToBlob(hwBlob2, (b * 32)); 
    hwBlob1.putBlob(0L, hwBlob2);
    paramHwParcel.writeBuffer(hwBlob1);
  }
  
  public final boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (paramObject == null)
      return false; 
    if (paramObject.getClass() != CdmaSmsAddress.class)
      return false; 
    paramObject = paramObject;
    return (this.digitMode != ((CdmaSmsAddress)paramObject).digitMode) ? false : ((this.numberMode != ((CdmaSmsAddress)paramObject).numberMode) ? false : ((this.numberType != ((CdmaSmsAddress)paramObject).numberType) ? false : ((this.numberPlan != ((CdmaSmsAddress)paramObject).numberPlan) ? false : (!!HidlSupport.deepEquals(this.digits, ((CdmaSmsAddress)paramObject).digits)))));
  }
  
  public final int hashCode() {
    return Objects.hash(new Object[] { Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.digitMode))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.numberMode))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.numberType))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.numberPlan))), Integer.valueOf(HidlSupport.deepHashCode(this.digits)) });
  }
  
  public final void readEmbeddedFromParcel(HwParcel paramHwParcel, HwBlob paramHwBlob, long paramLong) {
    this.digitMode = paramHwBlob.getInt32(paramLong + 0L);
    this.numberMode = paramHwBlob.getInt32(paramLong + 4L);
    this.numberType = paramHwBlob.getInt32(paramLong + 8L);
    this.numberPlan = paramHwBlob.getInt32(paramLong + 12L);
    int i = paramHwBlob.getInt32(paramLong + 16L + 8L);
    HwBlob hwBlob = paramHwParcel.readEmbeddedBuffer((i * 1), paramHwBlob.handle(), paramLong + 16L + 0L, true);
    this.digits.clear();
    for (byte b = 0; b < i; b++) {
      byte b1 = hwBlob.getInt8((b * 1));
      this.digits.add(Byte.valueOf(b1));
    } 
  }
  
  public final void readFromParcel(HwParcel paramHwParcel) {
    readEmbeddedFromParcel(paramHwParcel, paramHwParcel.readBuffer(32L), 0L);
  }
  
  public final String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("{");
    stringBuilder.append(".digitMode = ");
    stringBuilder.append(CdmaSmsDigitMode.toString(this.digitMode));
    stringBuilder.append(", .numberMode = ");
    stringBuilder.append(CdmaSmsNumberMode.toString(this.numberMode));
    stringBuilder.append(", .numberType = ");
    stringBuilder.append(CdmaSmsNumberType.toString(this.numberType));
    stringBuilder.append(", .numberPlan = ");
    stringBuilder.append(CdmaSmsNumberPlan.toString(this.numberPlan));
    stringBuilder.append(", .digits = ");
    stringBuilder.append(this.digits);
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public final void writeEmbeddedToBlob(HwBlob paramHwBlob, long paramLong) {
    paramHwBlob.putInt32(paramLong + 0L, this.digitMode);
    paramHwBlob.putInt32(4L + paramLong, this.numberMode);
    paramHwBlob.putInt32(paramLong + 8L, this.numberType);
    paramHwBlob.putInt32(paramLong + 12L, this.numberPlan);
    int i = this.digits.size();
    paramHwBlob.putInt32(paramLong + 16L + 8L, i);
    paramHwBlob.putBool(paramLong + 16L + 12L, false);
    HwBlob hwBlob = new HwBlob(i * 1);
    for (byte b = 0; b < i; b++)
      hwBlob.putInt8((b * 1), ((Byte)this.digits.get(b)).byteValue()); 
    paramHwBlob.putBlob(16L + paramLong + 0L, hwBlob);
  }
  
  public final void writeToParcel(HwParcel paramHwParcel) {
    HwBlob hwBlob = new HwBlob(32);
    writeEmbeddedToBlob(hwBlob, 0L);
    paramHwParcel.writeBuffer(hwBlob);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/V1_0/CdmaSmsAddress.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */