package android.hardware.contexthub.V1_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

public final class PhysicalSensor {
  public int fifoMaxCount = 0;
  
  public int fifoReservedCount = 0;
  
  public long maxDelayMs = 0L;
  
  public long minDelayMs = 0L;
  
  public String name = new String();
  
  public float peakPowerMw = 0.0F;
  
  public int sensorType = 0;
  
  public String type = new String();
  
  public String vendor = new String();
  
  public int version = 0;
  
  public static final ArrayList<PhysicalSensor> readVectorFromParcel(HwParcel paramHwParcel) {
    ArrayList<PhysicalSensor> arrayList = new ArrayList();
    HwBlob hwBlob = paramHwParcel.readBuffer(16L);
    int i = hwBlob.getInt32(8L);
    hwBlob = paramHwParcel.readEmbeddedBuffer((i * 96), hwBlob.handle(), 0L, true);
    arrayList.clear();
    for (byte b = 0; b < i; b++) {
      PhysicalSensor physicalSensor = new PhysicalSensor();
      physicalSensor.readEmbeddedFromParcel(paramHwParcel, hwBlob, (b * 96));
      arrayList.add(physicalSensor);
    } 
    return arrayList;
  }
  
  public static final void writeVectorToParcel(HwParcel paramHwParcel, ArrayList<PhysicalSensor> paramArrayList) {
    HwBlob hwBlob1 = new HwBlob(16);
    int i = paramArrayList.size();
    hwBlob1.putInt32(8L, i);
    hwBlob1.putBool(12L, false);
    HwBlob hwBlob2 = new HwBlob(i * 96);
    for (byte b = 0; b < i; b++)
      ((PhysicalSensor)paramArrayList.get(b)).writeEmbeddedToBlob(hwBlob2, (b * 96)); 
    hwBlob1.putBlob(0L, hwBlob2);
    paramHwParcel.writeBuffer(hwBlob1);
  }
  
  public final boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (paramObject == null)
      return false; 
    if (paramObject.getClass() != PhysicalSensor.class)
      return false; 
    paramObject = paramObject;
    return (this.sensorType != ((PhysicalSensor)paramObject).sensorType) ? false : (!HidlSupport.deepEquals(this.type, ((PhysicalSensor)paramObject).type) ? false : (!HidlSupport.deepEquals(this.name, ((PhysicalSensor)paramObject).name) ? false : (!HidlSupport.deepEquals(this.vendor, ((PhysicalSensor)paramObject).vendor) ? false : ((this.version != ((PhysicalSensor)paramObject).version) ? false : ((this.fifoReservedCount != ((PhysicalSensor)paramObject).fifoReservedCount) ? false : ((this.fifoMaxCount != ((PhysicalSensor)paramObject).fifoMaxCount) ? false : ((this.minDelayMs != ((PhysicalSensor)paramObject).minDelayMs) ? false : ((this.maxDelayMs != ((PhysicalSensor)paramObject).maxDelayMs) ? false : (!(this.peakPowerMw != ((PhysicalSensor)paramObject).peakPowerMw))))))))));
  }
  
  public final int hashCode() {
    return Objects.hash(new Object[] { Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.sensorType))), Integer.valueOf(HidlSupport.deepHashCode(this.type)), Integer.valueOf(HidlSupport.deepHashCode(this.name)), Integer.valueOf(HidlSupport.deepHashCode(this.vendor)), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.version))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.fifoReservedCount))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.fifoMaxCount))), Integer.valueOf(HidlSupport.deepHashCode(Long.valueOf(this.minDelayMs))), Integer.valueOf(HidlSupport.deepHashCode(Long.valueOf(this.maxDelayMs))), Integer.valueOf(HidlSupport.deepHashCode(Float.valueOf(this.peakPowerMw))) });
  }
  
  public final void readEmbeddedFromParcel(HwParcel paramHwParcel, HwBlob paramHwBlob, long paramLong) {
    this.sensorType = paramHwBlob.getInt32(paramLong + 0L);
    String str = paramHwBlob.getString(paramLong + 8L);
    this.type = str;
    paramHwParcel.readEmbeddedBuffer(((str.getBytes()).length + 1), paramHwBlob.handle(), paramLong + 8L + 0L, false);
    str = paramHwBlob.getString(paramLong + 24L);
    this.name = str;
    paramHwParcel.readEmbeddedBuffer(((str.getBytes()).length + 1), paramHwBlob.handle(), paramLong + 24L + 0L, false);
    str = paramHwBlob.getString(paramLong + 40L);
    this.vendor = str;
    paramHwParcel.readEmbeddedBuffer(((str.getBytes()).length + 1), paramHwBlob.handle(), paramLong + 40L + 0L, false);
    this.version = paramHwBlob.getInt32(paramLong + 56L);
    this.fifoReservedCount = paramHwBlob.getInt32(paramLong + 60L);
    this.fifoMaxCount = paramHwBlob.getInt32(paramLong + 64L);
    this.minDelayMs = paramHwBlob.getInt64(paramLong + 72L);
    this.maxDelayMs = paramHwBlob.getInt64(paramLong + 80L);
    this.peakPowerMw = paramHwBlob.getFloat(paramLong + 88L);
  }
  
  public final void readFromParcel(HwParcel paramHwParcel) {
    readEmbeddedFromParcel(paramHwParcel, paramHwParcel.readBuffer(96L), 0L);
  }
  
  public final String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("{");
    stringBuilder.append(".sensorType = ");
    stringBuilder.append(SensorType.toString(this.sensorType));
    stringBuilder.append(", .type = ");
    stringBuilder.append(this.type);
    stringBuilder.append(", .name = ");
    stringBuilder.append(this.name);
    stringBuilder.append(", .vendor = ");
    stringBuilder.append(this.vendor);
    stringBuilder.append(", .version = ");
    stringBuilder.append(this.version);
    stringBuilder.append(", .fifoReservedCount = ");
    stringBuilder.append(this.fifoReservedCount);
    stringBuilder.append(", .fifoMaxCount = ");
    stringBuilder.append(this.fifoMaxCount);
    stringBuilder.append(", .minDelayMs = ");
    stringBuilder.append(this.minDelayMs);
    stringBuilder.append(", .maxDelayMs = ");
    stringBuilder.append(this.maxDelayMs);
    stringBuilder.append(", .peakPowerMw = ");
    stringBuilder.append(this.peakPowerMw);
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public final void writeEmbeddedToBlob(HwBlob paramHwBlob, long paramLong) {
    paramHwBlob.putInt32(0L + paramLong, this.sensorType);
    paramHwBlob.putString(8L + paramLong, this.type);
    paramHwBlob.putString(24L + paramLong, this.name);
    paramHwBlob.putString(40L + paramLong, this.vendor);
    paramHwBlob.putInt32(56L + paramLong, this.version);
    paramHwBlob.putInt32(60L + paramLong, this.fifoReservedCount);
    paramHwBlob.putInt32(64L + paramLong, this.fifoMaxCount);
    paramHwBlob.putInt64(72L + paramLong, this.minDelayMs);
    paramHwBlob.putInt64(80L + paramLong, this.maxDelayMs);
    paramHwBlob.putFloat(88L + paramLong, this.peakPowerMw);
  }
  
  public final void writeToParcel(HwParcel paramHwParcel) {
    HwBlob hwBlob = new HwBlob(96);
    writeEmbeddedToBlob(hwBlob, 0L);
    paramHwParcel.writeBuffer(hwBlob);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/contexthub/V1_0/PhysicalSensor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */