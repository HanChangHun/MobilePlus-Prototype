package android.hardware.radio.V1_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

public final class CarrierRestrictions {
  public ArrayList<Carrier> allowedCarriers = new ArrayList<>();
  
  public ArrayList<Carrier> excludedCarriers = new ArrayList<>();
  
  public static final ArrayList<CarrierRestrictions> readVectorFromParcel(HwParcel paramHwParcel) {
    ArrayList<CarrierRestrictions> arrayList = new ArrayList();
    HwBlob hwBlob1 = paramHwParcel.readBuffer(16L);
    int i = hwBlob1.getInt32(8L);
    HwBlob hwBlob2 = paramHwParcel.readEmbeddedBuffer((i * 32), hwBlob1.handle(), 0L, true);
    arrayList.clear();
    for (byte b = 0; b < i; b++) {
      CarrierRestrictions carrierRestrictions = new CarrierRestrictions();
      carrierRestrictions.readEmbeddedFromParcel(paramHwParcel, hwBlob2, (b * 32));
      arrayList.add(carrierRestrictions);
    } 
    return arrayList;
  }
  
  public static final void writeVectorToParcel(HwParcel paramHwParcel, ArrayList<CarrierRestrictions> paramArrayList) {
    HwBlob hwBlob1 = new HwBlob(16);
    int i = paramArrayList.size();
    hwBlob1.putInt32(8L, i);
    hwBlob1.putBool(12L, false);
    HwBlob hwBlob2 = new HwBlob(i * 32);
    for (byte b = 0; b < i; b++)
      ((CarrierRestrictions)paramArrayList.get(b)).writeEmbeddedToBlob(hwBlob2, (b * 32)); 
    hwBlob1.putBlob(0L, hwBlob2);
    paramHwParcel.writeBuffer(hwBlob1);
  }
  
  public final boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (paramObject == null)
      return false; 
    if (paramObject.getClass() != CarrierRestrictions.class)
      return false; 
    paramObject = paramObject;
    return !HidlSupport.deepEquals(this.allowedCarriers, ((CarrierRestrictions)paramObject).allowedCarriers) ? false : (!!HidlSupport.deepEquals(this.excludedCarriers, ((CarrierRestrictions)paramObject).excludedCarriers));
  }
  
  public final int hashCode() {
    return Objects.hash(new Object[] { Integer.valueOf(HidlSupport.deepHashCode(this.allowedCarriers)), Integer.valueOf(HidlSupport.deepHashCode(this.excludedCarriers)) });
  }
  
  public final void readEmbeddedFromParcel(HwParcel paramHwParcel, HwBlob paramHwBlob, long paramLong) {
    int i = paramHwBlob.getInt32(paramLong + 0L + 8L);
    HwBlob hwBlob = paramHwParcel.readEmbeddedBuffer((i * 56), paramHwBlob.handle(), paramLong + 0L + 0L, true);
    this.allowedCarriers.clear();
    byte b;
    for (b = 0; b < i; b++) {
      Carrier carrier = new Carrier();
      carrier.readEmbeddedFromParcel(paramHwParcel, hwBlob, (b * 56));
      this.allowedCarriers.add(carrier);
    } 
    i = paramHwBlob.getInt32(paramLong + 16L + 8L);
    paramHwBlob = paramHwParcel.readEmbeddedBuffer((i * 56), paramHwBlob.handle(), paramLong + 16L + 0L, true);
    this.excludedCarriers.clear();
    for (b = 0; b < i; b++) {
      Carrier carrier = new Carrier();
      carrier.readEmbeddedFromParcel(paramHwParcel, paramHwBlob, (b * 56));
      this.excludedCarriers.add(carrier);
    } 
  }
  
  public final void readFromParcel(HwParcel paramHwParcel) {
    readEmbeddedFromParcel(paramHwParcel, paramHwParcel.readBuffer(32L), 0L);
  }
  
  public final String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("{");
    stringBuilder.append(".allowedCarriers = ");
    stringBuilder.append(this.allowedCarriers);
    stringBuilder.append(", .excludedCarriers = ");
    stringBuilder.append(this.excludedCarriers);
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public final void writeEmbeddedToBlob(HwBlob paramHwBlob, long paramLong) {
    int i = this.allowedCarriers.size();
    paramHwBlob.putInt32(paramLong + 0L + 8L, i);
    paramHwBlob.putBool(paramLong + 0L + 12L, false);
    HwBlob hwBlob = new HwBlob(i * 56);
    byte b;
    for (b = 0; b < i; b++)
      ((Carrier)this.allowedCarriers.get(b)).writeEmbeddedToBlob(hwBlob, (b * 56)); 
    paramHwBlob.putBlob(paramLong + 0L + 0L, hwBlob);
    i = this.excludedCarriers.size();
    paramHwBlob.putInt32(paramLong + 16L + 8L, i);
    paramHwBlob.putBool(paramLong + 16L + 12L, false);
    hwBlob = new HwBlob(i * 56);
    for (b = 0; b < i; b++)
      ((Carrier)this.excludedCarriers.get(b)).writeEmbeddedToBlob(hwBlob, (b * 56)); 
    paramHwBlob.putBlob(paramLong + 16L + 0L, hwBlob);
  }
  
  public final void writeToParcel(HwParcel paramHwParcel) {
    HwBlob hwBlob = new HwBlob(32);
    writeEmbeddedToBlob(hwBlob, 0L);
    paramHwParcel.writeBuffer(hwBlob);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/V1_0/CarrierRestrictions.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */