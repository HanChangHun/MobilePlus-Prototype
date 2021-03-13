package android.hardware.radio.V1_2;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

public final class CardStatus {
  public String atr = new String();
  
  public android.hardware.radio.V1_0.CardStatus base = new android.hardware.radio.V1_0.CardStatus();
  
  public String iccid = new String();
  
  public int physicalSlotId = 0;
  
  public static final ArrayList<CardStatus> readVectorFromParcel(HwParcel paramHwParcel) {
    ArrayList<CardStatus> arrayList = new ArrayList();
    HwBlob hwBlob = paramHwParcel.readBuffer(16L);
    int i = hwBlob.getInt32(8L);
    hwBlob = paramHwParcel.readEmbeddedBuffer((i * 80), hwBlob.handle(), 0L, true);
    arrayList.clear();
    for (byte b = 0; b < i; b++) {
      CardStatus cardStatus = new CardStatus();
      cardStatus.readEmbeddedFromParcel(paramHwParcel, hwBlob, (b * 80));
      arrayList.add(cardStatus);
    } 
    return arrayList;
  }
  
  public static final void writeVectorToParcel(HwParcel paramHwParcel, ArrayList<CardStatus> paramArrayList) {
    HwBlob hwBlob1 = new HwBlob(16);
    int i = paramArrayList.size();
    hwBlob1.putInt32(8L, i);
    hwBlob1.putBool(12L, false);
    HwBlob hwBlob2 = new HwBlob(i * 80);
    for (byte b = 0; b < i; b++)
      ((CardStatus)paramArrayList.get(b)).writeEmbeddedToBlob(hwBlob2, (b * 80)); 
    hwBlob1.putBlob(0L, hwBlob2);
    paramHwParcel.writeBuffer(hwBlob1);
  }
  
  public final boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (paramObject == null)
      return false; 
    if (paramObject.getClass() != CardStatus.class)
      return false; 
    paramObject = paramObject;
    return !HidlSupport.deepEquals(this.base, ((CardStatus)paramObject).base) ? false : ((this.physicalSlotId != ((CardStatus)paramObject).physicalSlotId) ? false : (!HidlSupport.deepEquals(this.atr, ((CardStatus)paramObject).atr) ? false : (!!HidlSupport.deepEquals(this.iccid, ((CardStatus)paramObject).iccid))));
  }
  
  public final int hashCode() {
    return Objects.hash(new Object[] { Integer.valueOf(HidlSupport.deepHashCode(this.base)), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.physicalSlotId))), Integer.valueOf(HidlSupport.deepHashCode(this.atr)), Integer.valueOf(HidlSupport.deepHashCode(this.iccid)) });
  }
  
  public final void readEmbeddedFromParcel(HwParcel paramHwParcel, HwBlob paramHwBlob, long paramLong) {
    this.base.readEmbeddedFromParcel(paramHwParcel, paramHwBlob, paramLong + 0L);
    this.physicalSlotId = paramHwBlob.getInt32(paramLong + 40L);
    String str = paramHwBlob.getString(paramLong + 48L);
    this.atr = str;
    paramHwParcel.readEmbeddedBuffer(((str.getBytes()).length + 1), paramHwBlob.handle(), paramLong + 48L + 0L, false);
    str = paramHwBlob.getString(paramLong + 64L);
    this.iccid = str;
    paramHwParcel.readEmbeddedBuffer(((str.getBytes()).length + 1), paramHwBlob.handle(), paramLong + 64L + 0L, false);
  }
  
  public final void readFromParcel(HwParcel paramHwParcel) {
    readEmbeddedFromParcel(paramHwParcel, paramHwParcel.readBuffer(80L), 0L);
  }
  
  public final String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("{");
    stringBuilder.append(".base = ");
    stringBuilder.append(this.base);
    stringBuilder.append(", .physicalSlotId = ");
    stringBuilder.append(this.physicalSlotId);
    stringBuilder.append(", .atr = ");
    stringBuilder.append(this.atr);
    stringBuilder.append(", .iccid = ");
    stringBuilder.append(this.iccid);
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public final void writeEmbeddedToBlob(HwBlob paramHwBlob, long paramLong) {
    this.base.writeEmbeddedToBlob(paramHwBlob, 0L + paramLong);
    paramHwBlob.putInt32(40L + paramLong, this.physicalSlotId);
    paramHwBlob.putString(48L + paramLong, this.atr);
    paramHwBlob.putString(64L + paramLong, this.iccid);
  }
  
  public final void writeToParcel(HwParcel paramHwParcel) {
    HwBlob hwBlob = new HwBlob(80);
    writeEmbeddedToBlob(hwBlob, 0L);
    paramHwParcel.writeBuffer(hwBlob);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/V1_2/CardStatus.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */