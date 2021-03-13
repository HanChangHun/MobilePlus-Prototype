package android.hardware.radio.V1_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

public final class RadioCapability {
  public String logicalModemUuid = new String();
  
  public int phase = 0;
  
  public int raf;
  
  public int session = 0;
  
  public int status = 0;
  
  public static final ArrayList<RadioCapability> readVectorFromParcel(HwParcel paramHwParcel) {
    ArrayList<RadioCapability> arrayList = new ArrayList();
    HwBlob hwBlob1 = paramHwParcel.readBuffer(16L);
    int i = hwBlob1.getInt32(8L);
    HwBlob hwBlob2 = paramHwParcel.readEmbeddedBuffer((i * 40), hwBlob1.handle(), 0L, true);
    arrayList.clear();
    for (byte b = 0; b < i; b++) {
      RadioCapability radioCapability = new RadioCapability();
      radioCapability.readEmbeddedFromParcel(paramHwParcel, hwBlob2, (b * 40));
      arrayList.add(radioCapability);
    } 
    return arrayList;
  }
  
  public static final void writeVectorToParcel(HwParcel paramHwParcel, ArrayList<RadioCapability> paramArrayList) {
    HwBlob hwBlob1 = new HwBlob(16);
    int i = paramArrayList.size();
    hwBlob1.putInt32(8L, i);
    hwBlob1.putBool(12L, false);
    HwBlob hwBlob2 = new HwBlob(i * 40);
    for (byte b = 0; b < i; b++)
      ((RadioCapability)paramArrayList.get(b)).writeEmbeddedToBlob(hwBlob2, (b * 40)); 
    hwBlob1.putBlob(0L, hwBlob2);
    paramHwParcel.writeBuffer(hwBlob1);
  }
  
  public final boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (paramObject == null)
      return false; 
    if (paramObject.getClass() != RadioCapability.class)
      return false; 
    paramObject = paramObject;
    return (this.session != ((RadioCapability)paramObject).session) ? false : ((this.phase != ((RadioCapability)paramObject).phase) ? false : (!HidlSupport.deepEquals(Integer.valueOf(this.raf), Integer.valueOf(((RadioCapability)paramObject).raf)) ? false : (!HidlSupport.deepEquals(this.logicalModemUuid, ((RadioCapability)paramObject).logicalModemUuid) ? false : (!(this.status != ((RadioCapability)paramObject).status)))));
  }
  
  public final int hashCode() {
    return Objects.hash(new Object[] { Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.session))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.phase))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.raf))), Integer.valueOf(HidlSupport.deepHashCode(this.logicalModemUuid)), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.status))) });
  }
  
  public final void readEmbeddedFromParcel(HwParcel paramHwParcel, HwBlob paramHwBlob, long paramLong) {
    this.session = paramHwBlob.getInt32(paramLong + 0L);
    this.phase = paramHwBlob.getInt32(paramLong + 4L);
    this.raf = paramHwBlob.getInt32(paramLong + 8L);
    String str = paramHwBlob.getString(paramLong + 16L);
    this.logicalModemUuid = str;
    paramHwParcel.readEmbeddedBuffer(((str.getBytes()).length + 1), paramHwBlob.handle(), paramLong + 16L + 0L, false);
    this.status = paramHwBlob.getInt32(paramLong + 32L);
  }
  
  public final void readFromParcel(HwParcel paramHwParcel) {
    readEmbeddedFromParcel(paramHwParcel, paramHwParcel.readBuffer(40L), 0L);
  }
  
  public final String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("{");
    stringBuilder.append(".session = ");
    stringBuilder.append(this.session);
    stringBuilder.append(", .phase = ");
    stringBuilder.append(RadioCapabilityPhase.toString(this.phase));
    stringBuilder.append(", .raf = ");
    stringBuilder.append(RadioAccessFamily.dumpBitfield(this.raf));
    stringBuilder.append(", .logicalModemUuid = ");
    stringBuilder.append(this.logicalModemUuid);
    stringBuilder.append(", .status = ");
    stringBuilder.append(RadioCapabilityStatus.toString(this.status));
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public final void writeEmbeddedToBlob(HwBlob paramHwBlob, long paramLong) {
    paramHwBlob.putInt32(0L + paramLong, this.session);
    paramHwBlob.putInt32(4L + paramLong, this.phase);
    paramHwBlob.putInt32(8L + paramLong, this.raf);
    paramHwBlob.putString(16L + paramLong, this.logicalModemUuid);
    paramHwBlob.putInt32(32L + paramLong, this.status);
  }
  
  public final void writeToParcel(HwParcel paramHwParcel) {
    HwBlob hwBlob = new HwBlob(40);
    writeEmbeddedToBlob(hwBlob, 0L);
    paramHwParcel.writeBuffer(hwBlob);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/V1_0/RadioCapability.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */