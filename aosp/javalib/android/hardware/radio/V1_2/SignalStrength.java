package android.hardware.radio.V1_2;

import android.hardware.radio.V1_0.CdmaSignalStrength;
import android.hardware.radio.V1_0.EvdoSignalStrength;
import android.hardware.radio.V1_0.GsmSignalStrength;
import android.hardware.radio.V1_0.LteSignalStrength;
import android.hardware.radio.V1_0.TdScdmaSignalStrength;
import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

public final class SignalStrength {
  public CdmaSignalStrength cdma = new CdmaSignalStrength();
  
  public EvdoSignalStrength evdo = new EvdoSignalStrength();
  
  public GsmSignalStrength gsm = new GsmSignalStrength();
  
  public LteSignalStrength lte = new LteSignalStrength();
  
  public TdScdmaSignalStrength tdScdma = new TdScdmaSignalStrength();
  
  public WcdmaSignalStrength wcdma = new WcdmaSignalStrength();
  
  public static final ArrayList<SignalStrength> readVectorFromParcel(HwParcel paramHwParcel) {
    ArrayList<SignalStrength> arrayList = new ArrayList();
    HwBlob hwBlob = paramHwParcel.readBuffer(16L);
    int i = hwBlob.getInt32(8L);
    hwBlob = paramHwParcel.readEmbeddedBuffer((i * 76), hwBlob.handle(), 0L, true);
    arrayList.clear();
    for (byte b = 0; b < i; b++) {
      SignalStrength signalStrength = new SignalStrength();
      signalStrength.readEmbeddedFromParcel(paramHwParcel, hwBlob, (b * 76));
      arrayList.add(signalStrength);
    } 
    return arrayList;
  }
  
  public static final void writeVectorToParcel(HwParcel paramHwParcel, ArrayList<SignalStrength> paramArrayList) {
    HwBlob hwBlob1 = new HwBlob(16);
    int i = paramArrayList.size();
    hwBlob1.putInt32(8L, i);
    hwBlob1.putBool(12L, false);
    HwBlob hwBlob2 = new HwBlob(i * 76);
    for (byte b = 0; b < i; b++)
      ((SignalStrength)paramArrayList.get(b)).writeEmbeddedToBlob(hwBlob2, (b * 76)); 
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
    return !HidlSupport.deepEquals(this.gsm, ((SignalStrength)paramObject).gsm) ? false : (!HidlSupport.deepEquals(this.cdma, ((SignalStrength)paramObject).cdma) ? false : (!HidlSupport.deepEquals(this.evdo, ((SignalStrength)paramObject).evdo) ? false : (!HidlSupport.deepEquals(this.lte, ((SignalStrength)paramObject).lte) ? false : (!HidlSupport.deepEquals(this.tdScdma, ((SignalStrength)paramObject).tdScdma) ? false : (!!HidlSupport.deepEquals(this.wcdma, ((SignalStrength)paramObject).wcdma))))));
  }
  
  public final int hashCode() {
    return Objects.hash(new Object[] { Integer.valueOf(HidlSupport.deepHashCode(this.gsm)), Integer.valueOf(HidlSupport.deepHashCode(this.cdma)), Integer.valueOf(HidlSupport.deepHashCode(this.evdo)), Integer.valueOf(HidlSupport.deepHashCode(this.lte)), Integer.valueOf(HidlSupport.deepHashCode(this.tdScdma)), Integer.valueOf(HidlSupport.deepHashCode(this.wcdma)) });
  }
  
  public final void readEmbeddedFromParcel(HwParcel paramHwParcel, HwBlob paramHwBlob, long paramLong) {
    this.gsm.readEmbeddedFromParcel(paramHwParcel, paramHwBlob, 0L + paramLong);
    this.cdma.readEmbeddedFromParcel(paramHwParcel, paramHwBlob, 12L + paramLong);
    this.evdo.readEmbeddedFromParcel(paramHwParcel, paramHwBlob, 20L + paramLong);
    this.lte.readEmbeddedFromParcel(paramHwParcel, paramHwBlob, 32L + paramLong);
    this.tdScdma.readEmbeddedFromParcel(paramHwParcel, paramHwBlob, 56L + paramLong);
    this.wcdma.readEmbeddedFromParcel(paramHwParcel, paramHwBlob, 60L + paramLong);
  }
  
  public final void readFromParcel(HwParcel paramHwParcel) {
    readEmbeddedFromParcel(paramHwParcel, paramHwParcel.readBuffer(76L), 0L);
  }
  
  public final String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("{");
    stringBuilder.append(".gsm = ");
    stringBuilder.append(this.gsm);
    stringBuilder.append(", .cdma = ");
    stringBuilder.append(this.cdma);
    stringBuilder.append(", .evdo = ");
    stringBuilder.append(this.evdo);
    stringBuilder.append(", .lte = ");
    stringBuilder.append(this.lte);
    stringBuilder.append(", .tdScdma = ");
    stringBuilder.append(this.tdScdma);
    stringBuilder.append(", .wcdma = ");
    stringBuilder.append(this.wcdma);
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public final void writeEmbeddedToBlob(HwBlob paramHwBlob, long paramLong) {
    this.gsm.writeEmbeddedToBlob(paramHwBlob, 0L + paramLong);
    this.cdma.writeEmbeddedToBlob(paramHwBlob, 12L + paramLong);
    this.evdo.writeEmbeddedToBlob(paramHwBlob, 20L + paramLong);
    this.lte.writeEmbeddedToBlob(paramHwBlob, 32L + paramLong);
    this.tdScdma.writeEmbeddedToBlob(paramHwBlob, 56L + paramLong);
    this.wcdma.writeEmbeddedToBlob(paramHwBlob, 60L + paramLong);
  }
  
  public final void writeToParcel(HwParcel paramHwParcel) {
    HwBlob hwBlob = new HwBlob(76);
    writeEmbeddedToBlob(hwBlob, 0L);
    paramHwParcel.writeBuffer(hwBlob);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/V1_2/SignalStrength.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */