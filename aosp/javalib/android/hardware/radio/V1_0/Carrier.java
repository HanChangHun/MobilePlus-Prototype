package android.hardware.radio.V1_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

public final class Carrier {
  public String matchData = new String();
  
  public int matchType = 0;
  
  public String mcc = new String();
  
  public String mnc = new String();
  
  public static final ArrayList<Carrier> readVectorFromParcel(HwParcel paramHwParcel) {
    ArrayList<Carrier> arrayList = new ArrayList();
    HwBlob hwBlob1 = paramHwParcel.readBuffer(16L);
    int i = hwBlob1.getInt32(8L);
    HwBlob hwBlob2 = paramHwParcel.readEmbeddedBuffer((i * 56), hwBlob1.handle(), 0L, true);
    arrayList.clear();
    for (byte b = 0; b < i; b++) {
      Carrier carrier = new Carrier();
      carrier.readEmbeddedFromParcel(paramHwParcel, hwBlob2, (b * 56));
      arrayList.add(carrier);
    } 
    return arrayList;
  }
  
  public static final void writeVectorToParcel(HwParcel paramHwParcel, ArrayList<Carrier> paramArrayList) {
    HwBlob hwBlob1 = new HwBlob(16);
    int i = paramArrayList.size();
    hwBlob1.putInt32(8L, i);
    hwBlob1.putBool(12L, false);
    HwBlob hwBlob2 = new HwBlob(i * 56);
    for (byte b = 0; b < i; b++)
      ((Carrier)paramArrayList.get(b)).writeEmbeddedToBlob(hwBlob2, (b * 56)); 
    hwBlob1.putBlob(0L, hwBlob2);
    paramHwParcel.writeBuffer(hwBlob1);
  }
  
  public final boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (paramObject == null)
      return false; 
    if (paramObject.getClass() != Carrier.class)
      return false; 
    paramObject = paramObject;
    return !HidlSupport.deepEquals(this.mcc, ((Carrier)paramObject).mcc) ? false : (!HidlSupport.deepEquals(this.mnc, ((Carrier)paramObject).mnc) ? false : ((this.matchType != ((Carrier)paramObject).matchType) ? false : (!!HidlSupport.deepEquals(this.matchData, ((Carrier)paramObject).matchData))));
  }
  
  public final int hashCode() {
    return Objects.hash(new Object[] { Integer.valueOf(HidlSupport.deepHashCode(this.mcc)), Integer.valueOf(HidlSupport.deepHashCode(this.mnc)), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.matchType))), Integer.valueOf(HidlSupport.deepHashCode(this.matchData)) });
  }
  
  public final void readEmbeddedFromParcel(HwParcel paramHwParcel, HwBlob paramHwBlob, long paramLong) {
    String str = paramHwBlob.getString(paramLong + 0L);
    this.mcc = str;
    paramHwParcel.readEmbeddedBuffer(((str.getBytes()).length + 1), paramHwBlob.handle(), paramLong + 0L + 0L, false);
    str = paramHwBlob.getString(paramLong + 16L);
    this.mnc = str;
    paramHwParcel.readEmbeddedBuffer(((str.getBytes()).length + 1), paramHwBlob.handle(), paramLong + 16L + 0L, false);
    this.matchType = paramHwBlob.getInt32(paramLong + 32L);
    str = paramHwBlob.getString(paramLong + 40L);
    this.matchData = str;
    paramHwParcel.readEmbeddedBuffer(((str.getBytes()).length + 1), paramHwBlob.handle(), paramLong + 40L + 0L, false);
  }
  
  public final void readFromParcel(HwParcel paramHwParcel) {
    readEmbeddedFromParcel(paramHwParcel, paramHwParcel.readBuffer(56L), 0L);
  }
  
  public final String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("{");
    stringBuilder.append(".mcc = ");
    stringBuilder.append(this.mcc);
    stringBuilder.append(", .mnc = ");
    stringBuilder.append(this.mnc);
    stringBuilder.append(", .matchType = ");
    stringBuilder.append(CarrierMatchType.toString(this.matchType));
    stringBuilder.append(", .matchData = ");
    stringBuilder.append(this.matchData);
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public final void writeEmbeddedToBlob(HwBlob paramHwBlob, long paramLong) {
    paramHwBlob.putString(0L + paramLong, this.mcc);
    paramHwBlob.putString(16L + paramLong, this.mnc);
    paramHwBlob.putInt32(32L + paramLong, this.matchType);
    paramHwBlob.putString(40L + paramLong, this.matchData);
  }
  
  public final void writeToParcel(HwParcel paramHwParcel) {
    HwBlob hwBlob = new HwBlob(56);
    writeEmbeddedToBlob(hwBlob, 0L);
    paramHwParcel.writeBuffer(hwBlob);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/V1_0/Carrier.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */