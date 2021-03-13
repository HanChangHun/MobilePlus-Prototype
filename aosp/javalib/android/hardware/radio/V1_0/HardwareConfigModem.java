package android.hardware.radio.V1_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

public final class HardwareConfigModem {
  public int maxData = 0;
  
  public int maxStandby = 0;
  
  public int maxVoice = 0;
  
  public int rat = 0;
  
  public int rilModel = 0;
  
  public static final ArrayList<HardwareConfigModem> readVectorFromParcel(HwParcel paramHwParcel) {
    ArrayList<HardwareConfigModem> arrayList = new ArrayList();
    HwBlob hwBlob1 = paramHwParcel.readBuffer(16L);
    int i = hwBlob1.getInt32(8L);
    HwBlob hwBlob2 = paramHwParcel.readEmbeddedBuffer((i * 20), hwBlob1.handle(), 0L, true);
    arrayList.clear();
    for (byte b = 0; b < i; b++) {
      HardwareConfigModem hardwareConfigModem = new HardwareConfigModem();
      hardwareConfigModem.readEmbeddedFromParcel(paramHwParcel, hwBlob2, (b * 20));
      arrayList.add(hardwareConfigModem);
    } 
    return arrayList;
  }
  
  public static final void writeVectorToParcel(HwParcel paramHwParcel, ArrayList<HardwareConfigModem> paramArrayList) {
    HwBlob hwBlob1 = new HwBlob(16);
    int i = paramArrayList.size();
    hwBlob1.putInt32(8L, i);
    hwBlob1.putBool(12L, false);
    HwBlob hwBlob2 = new HwBlob(i * 20);
    for (byte b = 0; b < i; b++)
      ((HardwareConfigModem)paramArrayList.get(b)).writeEmbeddedToBlob(hwBlob2, (b * 20)); 
    hwBlob1.putBlob(0L, hwBlob2);
    paramHwParcel.writeBuffer(hwBlob1);
  }
  
  public final boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (paramObject == null)
      return false; 
    if (paramObject.getClass() != HardwareConfigModem.class)
      return false; 
    paramObject = paramObject;
    return (this.rilModel != ((HardwareConfigModem)paramObject).rilModel) ? false : ((this.rat != ((HardwareConfigModem)paramObject).rat) ? false : ((this.maxVoice != ((HardwareConfigModem)paramObject).maxVoice) ? false : ((this.maxData != ((HardwareConfigModem)paramObject).maxData) ? false : (!(this.maxStandby != ((HardwareConfigModem)paramObject).maxStandby)))));
  }
  
  public final int hashCode() {
    return Objects.hash(new Object[] { Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.rilModel))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.rat))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.maxVoice))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.maxData))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.maxStandby))) });
  }
  
  public final void readEmbeddedFromParcel(HwParcel paramHwParcel, HwBlob paramHwBlob, long paramLong) {
    this.rilModel = paramHwBlob.getInt32(0L + paramLong);
    this.rat = paramHwBlob.getInt32(4L + paramLong);
    this.maxVoice = paramHwBlob.getInt32(8L + paramLong);
    this.maxData = paramHwBlob.getInt32(12L + paramLong);
    this.maxStandby = paramHwBlob.getInt32(16L + paramLong);
  }
  
  public final void readFromParcel(HwParcel paramHwParcel) {
    readEmbeddedFromParcel(paramHwParcel, paramHwParcel.readBuffer(20L), 0L);
  }
  
  public final String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("{");
    stringBuilder.append(".rilModel = ");
    stringBuilder.append(this.rilModel);
    stringBuilder.append(", .rat = ");
    stringBuilder.append(this.rat);
    stringBuilder.append(", .maxVoice = ");
    stringBuilder.append(this.maxVoice);
    stringBuilder.append(", .maxData = ");
    stringBuilder.append(this.maxData);
    stringBuilder.append(", .maxStandby = ");
    stringBuilder.append(this.maxStandby);
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public final void writeEmbeddedToBlob(HwBlob paramHwBlob, long paramLong) {
    paramHwBlob.putInt32(0L + paramLong, this.rilModel);
    paramHwBlob.putInt32(4L + paramLong, this.rat);
    paramHwBlob.putInt32(8L + paramLong, this.maxVoice);
    paramHwBlob.putInt32(12L + paramLong, this.maxData);
    paramHwBlob.putInt32(16L + paramLong, this.maxStandby);
  }
  
  public final void writeToParcel(HwParcel paramHwParcel) {
    HwBlob hwBlob = new HwBlob(20);
    writeEmbeddedToBlob(hwBlob, 0L);
    paramHwParcel.writeBuffer(hwBlob);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/V1_0/HardwareConfigModem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */