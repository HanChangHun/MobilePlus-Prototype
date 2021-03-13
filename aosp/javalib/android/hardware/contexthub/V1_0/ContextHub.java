package android.hardware.contexthub.V1_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

public final class ContextHub {
  public byte chreApiMajorVersion = (byte)0;
  
  public byte chreApiMinorVersion = (byte)0;
  
  public short chrePatchVersion = (short)0;
  
  public long chrePlatformId = 0L;
  
  public ArrayList<PhysicalSensor> connectedSensors = new ArrayList<>();
  
  public int hubId = 0;
  
  public int maxSupportedMsgLen = 0;
  
  public String name = new String();
  
  public float peakMips = 0.0F;
  
  public float peakPowerDrawMw = 0.0F;
  
  public int platformVersion = 0;
  
  public float sleepPowerDrawMw = 0.0F;
  
  public float stoppedPowerDrawMw = 0.0F;
  
  public String toolchain = new String();
  
  public int toolchainVersion = 0;
  
  public String vendor = new String();
  
  public static final ArrayList<ContextHub> readVectorFromParcel(HwParcel paramHwParcel) {
    ArrayList<ContextHub> arrayList = new ArrayList();
    HwBlob hwBlob = paramHwParcel.readBuffer(16L);
    int i = hwBlob.getInt32(8L);
    hwBlob = paramHwParcel.readEmbeddedBuffer((i * 120), hwBlob.handle(), 0L, true);
    arrayList.clear();
    for (byte b = 0; b < i; b++) {
      ContextHub contextHub = new ContextHub();
      contextHub.readEmbeddedFromParcel(paramHwParcel, hwBlob, (b * 120));
      arrayList.add(contextHub);
    } 
    return arrayList;
  }
  
  public static final void writeVectorToParcel(HwParcel paramHwParcel, ArrayList<ContextHub> paramArrayList) {
    HwBlob hwBlob1 = new HwBlob(16);
    int i = paramArrayList.size();
    hwBlob1.putInt32(8L, i);
    hwBlob1.putBool(12L, false);
    HwBlob hwBlob2 = new HwBlob(i * 120);
    for (byte b = 0; b < i; b++)
      ((ContextHub)paramArrayList.get(b)).writeEmbeddedToBlob(hwBlob2, (b * 120)); 
    hwBlob1.putBlob(0L, hwBlob2);
    paramHwParcel.writeBuffer(hwBlob1);
  }
  
  public final boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (paramObject == null)
      return false; 
    if (paramObject.getClass() != ContextHub.class)
      return false; 
    paramObject = paramObject;
    return !HidlSupport.deepEquals(this.name, ((ContextHub)paramObject).name) ? false : (!HidlSupport.deepEquals(this.vendor, ((ContextHub)paramObject).vendor) ? false : (!HidlSupport.deepEquals(this.toolchain, ((ContextHub)paramObject).toolchain) ? false : ((this.platformVersion != ((ContextHub)paramObject).platformVersion) ? false : ((this.toolchainVersion != ((ContextHub)paramObject).toolchainVersion) ? false : ((this.hubId != ((ContextHub)paramObject).hubId) ? false : ((this.peakMips != ((ContextHub)paramObject).peakMips) ? false : ((this.stoppedPowerDrawMw != ((ContextHub)paramObject).stoppedPowerDrawMw) ? false : ((this.sleepPowerDrawMw != ((ContextHub)paramObject).sleepPowerDrawMw) ? false : ((this.peakPowerDrawMw != ((ContextHub)paramObject).peakPowerDrawMw) ? false : (!HidlSupport.deepEquals(this.connectedSensors, ((ContextHub)paramObject).connectedSensors) ? false : ((this.maxSupportedMsgLen != ((ContextHub)paramObject).maxSupportedMsgLen) ? false : ((this.chrePlatformId != ((ContextHub)paramObject).chrePlatformId) ? false : ((this.chreApiMajorVersion != ((ContextHub)paramObject).chreApiMajorVersion) ? false : ((this.chreApiMinorVersion != ((ContextHub)paramObject).chreApiMinorVersion) ? false : (!(this.chrePatchVersion != ((ContextHub)paramObject).chrePatchVersion))))))))))))))));
  }
  
  public final int hashCode() {
    return Objects.hash(new Object[] { 
          Integer.valueOf(HidlSupport.deepHashCode(this.name)), Integer.valueOf(HidlSupport.deepHashCode(this.vendor)), Integer.valueOf(HidlSupport.deepHashCode(this.toolchain)), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.platformVersion))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.toolchainVersion))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.hubId))), Integer.valueOf(HidlSupport.deepHashCode(Float.valueOf(this.peakMips))), Integer.valueOf(HidlSupport.deepHashCode(Float.valueOf(this.stoppedPowerDrawMw))), Integer.valueOf(HidlSupport.deepHashCode(Float.valueOf(this.sleepPowerDrawMw))), Integer.valueOf(HidlSupport.deepHashCode(Float.valueOf(this.peakPowerDrawMw))), 
          Integer.valueOf(HidlSupport.deepHashCode(this.connectedSensors)), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.maxSupportedMsgLen))), Integer.valueOf(HidlSupport.deepHashCode(Long.valueOf(this.chrePlatformId))), Integer.valueOf(HidlSupport.deepHashCode(Byte.valueOf(this.chreApiMajorVersion))), Integer.valueOf(HidlSupport.deepHashCode(Byte.valueOf(this.chreApiMinorVersion))), Integer.valueOf(HidlSupport.deepHashCode(Short.valueOf(this.chrePatchVersion))) });
  }
  
  public final void readEmbeddedFromParcel(HwParcel paramHwParcel, HwBlob paramHwBlob, long paramLong) {
    String str = paramHwBlob.getString(paramLong + 0L);
    this.name = str;
    paramHwParcel.readEmbeddedBuffer(((str.getBytes()).length + 1), paramHwBlob.handle(), paramLong + 0L + 0L, false);
    str = paramHwBlob.getString(paramLong + 16L);
    this.vendor = str;
    paramHwParcel.readEmbeddedBuffer(((str.getBytes()).length + 1), paramHwBlob.handle(), paramLong + 16L + 0L, false);
    str = paramHwBlob.getString(paramLong + 32L);
    this.toolchain = str;
    paramHwParcel.readEmbeddedBuffer(((str.getBytes()).length + 1), paramHwBlob.handle(), paramLong + 32L + 0L, false);
    this.platformVersion = paramHwBlob.getInt32(paramLong + 48L);
    this.toolchainVersion = paramHwBlob.getInt32(paramLong + 52L);
    this.hubId = paramHwBlob.getInt32(paramLong + 56L);
    this.peakMips = paramHwBlob.getFloat(paramLong + 60L);
    this.stoppedPowerDrawMw = paramHwBlob.getFloat(paramLong + 64L);
    this.sleepPowerDrawMw = paramHwBlob.getFloat(paramLong + 68L);
    this.peakPowerDrawMw = paramHwBlob.getFloat(paramLong + 72L);
    int i = paramHwBlob.getInt32(paramLong + 80L + 8L);
    HwBlob hwBlob = paramHwParcel.readEmbeddedBuffer((i * 96), paramHwBlob.handle(), paramLong + 80L + 0L, true);
    this.connectedSensors.clear();
    for (byte b = 0; b < i; b++) {
      PhysicalSensor physicalSensor = new PhysicalSensor();
      physicalSensor.readEmbeddedFromParcel(paramHwParcel, hwBlob, (b * 96));
      this.connectedSensors.add(physicalSensor);
    } 
    this.maxSupportedMsgLen = paramHwBlob.getInt32(paramLong + 96L);
    this.chrePlatformId = paramHwBlob.getInt64(paramLong + 104L);
    this.chreApiMajorVersion = paramHwBlob.getInt8(paramLong + 112L);
    this.chreApiMinorVersion = paramHwBlob.getInt8(paramLong + 113L);
    this.chrePatchVersion = paramHwBlob.getInt16(paramLong + 114L);
  }
  
  public final void readFromParcel(HwParcel paramHwParcel) {
    readEmbeddedFromParcel(paramHwParcel, paramHwParcel.readBuffer(120L), 0L);
  }
  
  public final String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("{");
    stringBuilder.append(".name = ");
    stringBuilder.append(this.name);
    stringBuilder.append(", .vendor = ");
    stringBuilder.append(this.vendor);
    stringBuilder.append(", .toolchain = ");
    stringBuilder.append(this.toolchain);
    stringBuilder.append(", .platformVersion = ");
    stringBuilder.append(this.platformVersion);
    stringBuilder.append(", .toolchainVersion = ");
    stringBuilder.append(this.toolchainVersion);
    stringBuilder.append(", .hubId = ");
    stringBuilder.append(this.hubId);
    stringBuilder.append(", .peakMips = ");
    stringBuilder.append(this.peakMips);
    stringBuilder.append(", .stoppedPowerDrawMw = ");
    stringBuilder.append(this.stoppedPowerDrawMw);
    stringBuilder.append(", .sleepPowerDrawMw = ");
    stringBuilder.append(this.sleepPowerDrawMw);
    stringBuilder.append(", .peakPowerDrawMw = ");
    stringBuilder.append(this.peakPowerDrawMw);
    stringBuilder.append(", .connectedSensors = ");
    stringBuilder.append(this.connectedSensors);
    stringBuilder.append(", .maxSupportedMsgLen = ");
    stringBuilder.append(this.maxSupportedMsgLen);
    stringBuilder.append(", .chrePlatformId = ");
    stringBuilder.append(this.chrePlatformId);
    stringBuilder.append(", .chreApiMajorVersion = ");
    stringBuilder.append(this.chreApiMajorVersion);
    stringBuilder.append(", .chreApiMinorVersion = ");
    stringBuilder.append(this.chreApiMinorVersion);
    stringBuilder.append(", .chrePatchVersion = ");
    stringBuilder.append(this.chrePatchVersion);
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public final void writeEmbeddedToBlob(HwBlob paramHwBlob, long paramLong) {
    paramHwBlob.putString(paramLong + 0L, this.name);
    paramHwBlob.putString(16L + paramLong, this.vendor);
    paramHwBlob.putString(32L + paramLong, this.toolchain);
    paramHwBlob.putInt32(48L + paramLong, this.platformVersion);
    paramHwBlob.putInt32(52L + paramLong, this.toolchainVersion);
    paramHwBlob.putInt32(56L + paramLong, this.hubId);
    paramHwBlob.putFloat(60L + paramLong, this.peakMips);
    paramHwBlob.putFloat(64L + paramLong, this.stoppedPowerDrawMw);
    paramHwBlob.putFloat(68L + paramLong, this.sleepPowerDrawMw);
    paramHwBlob.putFloat(72L + paramLong, this.peakPowerDrawMw);
    int i = this.connectedSensors.size();
    paramHwBlob.putInt32(paramLong + 80L + 8L, i);
    paramHwBlob.putBool(paramLong + 80L + 12L, false);
    HwBlob hwBlob = new HwBlob(i * 96);
    for (byte b = 0; b < i; b++)
      ((PhysicalSensor)this.connectedSensors.get(b)).writeEmbeddedToBlob(hwBlob, (b * 96)); 
    paramHwBlob.putBlob(80L + paramLong + 0L, hwBlob);
    paramHwBlob.putInt32(96L + paramLong, this.maxSupportedMsgLen);
    paramHwBlob.putInt64(104L + paramLong, this.chrePlatformId);
    paramHwBlob.putInt8(112L + paramLong, this.chreApiMajorVersion);
    paramHwBlob.putInt8(113L + paramLong, this.chreApiMinorVersion);
    paramHwBlob.putInt16(114L + paramLong, this.chrePatchVersion);
  }
  
  public final void writeToParcel(HwParcel paramHwParcel) {
    HwBlob hwBlob = new HwBlob(120);
    writeEmbeddedToBlob(hwBlob, 0L);
    paramHwParcel.writeBuffer(hwBlob);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/contexthub/V1_0/ContextHub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */