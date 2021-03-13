package android.hardware.radio.V1_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

public final class SignalStrength {
  public CdmaSignalStrength cdma = new CdmaSignalStrength();
  
  public EvdoSignalStrength evdo = new EvdoSignalStrength();
  
  public GsmSignalStrength gw = new GsmSignalStrength();
  
  public LteSignalStrength lte = new LteSignalStrength();
  
  public TdScdmaSignalStrength tdScdma = new TdScdmaSignalStrength();
  
  public static final ArrayList<SignalStrength> readVectorFromParcel(HwParcel paramHwParcel) {
    ArrayList<SignalStrength> arrayList = new ArrayList();
    HwBlob hwBlob = paramHwParcel.readBuffer(16L);
    int i = hwBlob.getInt32(8L);
    hwBlob = paramHwParcel.readEmbeddedBuffer((i * 60), hwBlob.handle(), 0L, true);
    arrayList.clear();
    for (byte b = 0; b < i; b++) {
      SignalStrength signalStrength = new SignalStrength();
      signalStrength.readEmbeddedFromParcel(paramHwParcel, hwBlob, (b * 60));
      arrayList.add(signalStrength);
    } 
    return arrayList;
  }
  
  public static final void writeVectorToParcel(HwParcel paramHwParcel, ArrayList<SignalStrength> paramArrayList) {
    HwBlob hwBlob1 = new HwBlob(16);
    int i = paramArrayList.size();
    hwBlob1.putInt32(8L, i);
    hwBlob1.putBool(12L, false);
    HwBlob hwBlob2 = new HwBlob(i * 60);
    for (byte b = 0; b < i; b++)
      ((SignalStrength)paramArrayList.get(b)).writeEmbeddedToBlob(hwBlob2, (b * 60)); 
    hwBlob1.putBlob(0L, hwBlob2);
    paramHwParcel.writeBuffer(hwBlob1);
  }
  
  public final boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (paramObject == null)
      return false; 
    if (paramObject.getClass() != SignalStrength.class)
      return false; 
    paramObject = paramObject;
    return !HidlSupport.deepEquals(this.gw, ((SignalStrength)paramObject).gw) ? false : (!HidlSupport.deepEquals(this.cdma, ((SignalStrength)paramObject).cdma) ? false : (!HidlSupport.deepEquals(this.evdo, ((SignalStrength)paramObject).evdo) ? false : (!HidlSupport.deepEquals(this.lte, ((SignalStrength)paramObject).lte) ? false : (!!HidlSupport.deepEquals(this.tdScdma, ((SignalStrength)paramObject).tdScdma)))));
  }
  
  public final int hashCode() {
    return Objects.hash(new Object[] { Integer.valueOf(HidlSupport.deepHashCode(this.gw)), Integer.valueOf(HidlSupport.deepHashCode(this.cdma)), Integer.valueOf(HidlSupport.deepHashCode(this.evdo)), Integer.valueOf(HidlSupport.deepHashCode(this.lte)), Integer.valueOf(HidlSupport.deepHashCode(this.tdScdma)) });
  }
  
  public final void readEmbeddedFromParcel(HwParcel paramHwParcel, HwBlob paramHwBlob, long paramLong) {
    this.gw.readEmbeddedFromParcel(paramHwParcel, paramHwBlob, 0L + paramLong);
    this.cdma.readEmbeddedFromParcel(paramHwParcel, paramHwBlob, 12L + paramLong);
    this.evdo.readEmbeddedFromParcel(paramHwParcel, paramHwBlob, 20L + paramLong);
    this.lte.readEmbeddedFromParcel(paramHwParcel, paramHwBlob, 32L + paramLong);
    this.tdScdma.readEmbeddedFromParcel(paramHwParcel, paramHwBlob, 56L + paramLong);
  }
  
  public final void readFromParcel(HwParcel paramHwParcel) {
    readEmbeddedFromParcel(paramHwParcel, paramHwParcel.readBuffer(60L), 0L);
  }
  
  public final String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("{");
    stringBuilder.append(".gw = ");
    stringBuilder.append(this.gw);
    stringBuilder.append(", .cdma = ");
    stringBuilder.append(this.cdma);
    stringBuilder.append(", .evdo = ");
    stringBuilder.append(this.evdo);
    stringBuilder.append(", .lte = ");
    stringBuilder.append(this.lte);
    stringBuilder.append(", .tdScdma = ");
    stringBuilder.append(this.tdScdma);
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public final void writeEmbeddedToBlob(HwBlob paramHwBlob, long paramLong) {
    this.gw.writeEmbeddedToBlob(paramHwBlob, 0L + paramLong);
    this.cdma.writeEmbeddedToBlob(paramHwBlob, 12L + paramLong);
    this.evdo.writeEmbeddedToBlob(paramHwBlob, 20L + paramLong);
    this.lte.writeEmbeddedToBlob(paramHwBlob, 32L + paramLong);
    this.tdScdma.writeEmbeddedToBlob(paramHwBlob, 56L + paramLong);
  }
  
  public final void writeToParcel(HwParcel paramHwParcel) {
    HwBlob hwBlob = new HwBlob(60);
    writeEmbeddedToBlob(hwBlob, 0L);
    paramHwParcel.writeBuffer(hwBlob);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/V1_0/SignalStrength.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */