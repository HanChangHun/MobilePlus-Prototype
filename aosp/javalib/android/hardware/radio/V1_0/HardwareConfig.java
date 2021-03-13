package android.hardware.radio.V1_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

public final class HardwareConfig {
  public ArrayList<HardwareConfigModem> modem = new ArrayList<>();
  
  public ArrayList<HardwareConfigSim> sim = new ArrayList<>();
  
  public int state = 0;
  
  public int type = 0;
  
  public String uuid = new String();
  
  public static final ArrayList<HardwareConfig> readVectorFromParcel(HwParcel paramHwParcel) {
    ArrayList<HardwareConfig> arrayList = new ArrayList();
    HwBlob hwBlob1 = paramHwParcel.readBuffer(16L);
    int i = hwBlob1.getInt32(8L);
    HwBlob hwBlob2 = paramHwParcel.readEmbeddedBuffer((i * 64), hwBlob1.handle(), 0L, true);
    arrayList.clear();
    for (byte b = 0; b < i; b++) {
      HardwareConfig hardwareConfig = new HardwareConfig();
      hardwareConfig.readEmbeddedFromParcel(paramHwParcel, hwBlob2, (b * 64));
      arrayList.add(hardwareConfig);
    } 
    return arrayList;
  }
  
  public static final void writeVectorToParcel(HwParcel paramHwParcel, ArrayList<HardwareConfig> paramArrayList) {
    HwBlob hwBlob1 = new HwBlob(16);
    int i = paramArrayList.size();
    hwBlob1.putInt32(8L, i);
    hwBlob1.putBool(12L, false);
    HwBlob hwBlob2 = new HwBlob(i * 64);
    for (byte b = 0; b < i; b++)
      ((HardwareConfig)paramArrayList.get(b)).writeEmbeddedToBlob(hwBlob2, (b * 64)); 
    hwBlob1.putBlob(0L, hwBlob2);
    paramHwParcel.writeBuffer(hwBlob1);
  }
  
  public final boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (paramObject == null)
      return false; 
    if (paramObject.getClass() != HardwareConfig.class)
      return false; 
    paramObject = paramObject;
    return (this.type != ((HardwareConfig)paramObject).type) ? false : (!HidlSupport.deepEquals(this.uuid, ((HardwareConfig)paramObject).uuid) ? false : ((this.state != ((HardwareConfig)paramObject).state) ? false : (!HidlSupport.deepEquals(this.modem, ((HardwareConfig)paramObject).modem) ? false : (!!HidlSupport.deepEquals(this.sim, ((HardwareConfig)paramObject).sim)))));
  }
  
  public final int hashCode() {
    return Objects.hash(new Object[] { Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.type))), Integer.valueOf(HidlSupport.deepHashCode(this.uuid)), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.state))), Integer.valueOf(HidlSupport.deepHashCode(this.modem)), Integer.valueOf(HidlSupport.deepHashCode(this.sim)) });
  }
  
  public final void readEmbeddedFromParcel(HwParcel paramHwParcel, HwBlob paramHwBlob, long paramLong) {
    this.type = paramHwBlob.getInt32(paramLong + 0L);
    String str = paramHwBlob.getString(paramLong + 8L);
    this.uuid = str;
    paramHwParcel.readEmbeddedBuffer(((str.getBytes()).length + 1), paramHwBlob.handle(), paramLong + 8L + 0L, false);
    this.state = paramHwBlob.getInt32(paramLong + 24L);
    int i = paramHwBlob.getInt32(paramLong + 32L + 8L);
    HwBlob hwBlob = paramHwParcel.readEmbeddedBuffer((i * 20), paramHwBlob.handle(), paramLong + 32L + 0L, true);
    this.modem.clear();
    byte b;
    for (b = 0; b < i; b++) {
      HardwareConfigModem hardwareConfigModem = new HardwareConfigModem();
      hardwareConfigModem.readEmbeddedFromParcel(paramHwParcel, hwBlob, (b * 20));
      this.modem.add(hardwareConfigModem);
    } 
    i = paramHwBlob.getInt32(paramLong + 48L + 8L);
    paramHwBlob = paramHwParcel.readEmbeddedBuffer((i * 16), paramHwBlob.handle(), paramLong + 48L + 0L, true);
    this.sim.clear();
    for (b = 0; b < i; b++) {
      HardwareConfigSim hardwareConfigSim = new HardwareConfigSim();
      hardwareConfigSim.readEmbeddedFromParcel(paramHwParcel, paramHwBlob, (b * 16));
      this.sim.add(hardwareConfigSim);
    } 
  }
  
  public final void readFromParcel(HwParcel paramHwParcel) {
    readEmbeddedFromParcel(paramHwParcel, paramHwParcel.readBuffer(64L), 0L);
  }
  
  public final String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("{");
    stringBuilder.append(".type = ");
    stringBuilder.append(HardwareConfigType.toString(this.type));
    stringBuilder.append(", .uuid = ");
    stringBuilder.append(this.uuid);
    stringBuilder.append(", .state = ");
    stringBuilder.append(HardwareConfigState.toString(this.state));
    stringBuilder.append(", .modem = ");
    stringBuilder.append(this.modem);
    stringBuilder.append(", .sim = ");
    stringBuilder.append(this.sim);
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public final void writeEmbeddedToBlob(HwBlob paramHwBlob, long paramLong) {
    paramHwBlob.putInt32(paramLong + 0L, this.type);
    paramHwBlob.putString(paramLong + 8L, this.uuid);
    paramHwBlob.putInt32(paramLong + 24L, this.state);
    int i = this.modem.size();
    paramHwBlob.putInt32(paramLong + 32L + 8L, i);
    paramHwBlob.putBool(paramLong + 32L + 12L, false);
    HwBlob hwBlob = new HwBlob(i * 20);
    byte b;
    for (b = 0; b < i; b++)
      ((HardwareConfigModem)this.modem.get(b)).writeEmbeddedToBlob(hwBlob, (b * 20)); 
    paramHwBlob.putBlob(paramLong + 32L + 0L, hwBlob);
    i = this.sim.size();
    paramHwBlob.putInt32(paramLong + 48L + 8L, i);
    paramHwBlob.putBool(paramLong + 48L + 12L, false);
    hwBlob = new HwBlob(i * 16);
    for (b = 0; b < i; b++)
      ((HardwareConfigSim)this.sim.get(b)).writeEmbeddedToBlob(hwBlob, (b * 16)); 
    paramHwBlob.putBlob(paramLong + 48L + 0L, hwBlob);
  }
  
  public final void writeToParcel(HwParcel paramHwParcel) {
    HwBlob hwBlob = new HwBlob(64);
    writeEmbeddedToBlob(hwBlob, 0L);
    paramHwParcel.writeBuffer(hwBlob);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/V1_0/HardwareConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */