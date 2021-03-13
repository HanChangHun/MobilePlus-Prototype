package android.hardware.gnss.V1_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

public final class DebugData {
  public IGnssDebug.PositionDebug position = new IGnssDebug.PositionDebug();
  
  public ArrayList<IGnssDebug.SatelliteData> satelliteDataArray = new ArrayList<>();
  
  public IGnssDebug.TimeDebug time = new IGnssDebug.TimeDebug();
  
  public static final ArrayList<DebugData> readVectorFromParcel(HwParcel paramHwParcel) {
    ArrayList<DebugData> arrayList = new ArrayList();
    HwBlob hwBlob1 = paramHwParcel.readBuffer(16L);
    int i = hwBlob1.getInt32(8L);
    HwBlob hwBlob2 = paramHwParcel.readEmbeddedBuffer((i * 112), hwBlob1.handle(), 0L, true);
    arrayList.clear();
    for (byte b = 0; b < i; b++) {
      DebugData debugData = new DebugData();
      debugData.readEmbeddedFromParcel(paramHwParcel, hwBlob2, (b * 112));
      arrayList.add(debugData);
    } 
    return arrayList;
  }
  
  public static final void writeVectorToParcel(HwParcel paramHwParcel, ArrayList<DebugData> paramArrayList) {
    HwBlob hwBlob1 = new HwBlob(16);
    int i = paramArrayList.size();
    hwBlob1.putInt32(8L, i);
    hwBlob1.putBool(12L, false);
    HwBlob hwBlob2 = new HwBlob(i * 112);
    for (byte b = 0; b < i; b++)
      ((DebugData)paramArrayList.get(b)).writeEmbeddedToBlob(hwBlob2, (b * 112)); 
    hwBlob1.putBlob(0L, hwBlob2);
    paramHwParcel.writeBuffer(hwBlob1);
  }
  
  public final boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (paramObject == null)
      return false; 
    if (paramObject.getClass() != DebugData.class)
      return false; 
    paramObject = paramObject;
    return !HidlSupport.deepEquals(this.position, ((DebugData)paramObject).position) ? false : (!HidlSupport.deepEquals(this.time, ((DebugData)paramObject).time) ? false : (!!HidlSupport.deepEquals(this.satelliteDataArray, ((DebugData)paramObject).satelliteDataArray)));
  }
  
  public final int hashCode() {
    return Objects.hash(new Object[] { Integer.valueOf(HidlSupport.deepHashCode(this.position)), Integer.valueOf(HidlSupport.deepHashCode(this.time)), Integer.valueOf(HidlSupport.deepHashCode(this.satelliteDataArray)) });
  }
  
  public final void readEmbeddedFromParcel(HwParcel paramHwParcel, HwBlob paramHwBlob, long paramLong) {
    this.position.readEmbeddedFromParcel(paramHwParcel, paramHwBlob, paramLong + 0L);
    this.time.readEmbeddedFromParcel(paramHwParcel, paramHwBlob, paramLong + 80L);
    int i = paramHwBlob.getInt32(paramLong + 96L + 8L);
    HwBlob hwBlob = paramHwParcel.readEmbeddedBuffer((i * 20), paramHwBlob.handle(), paramLong + 96L + 0L, true);
    this.satelliteDataArray.clear();
    for (byte b = 0; b < i; b++) {
      IGnssDebug.SatelliteData satelliteData = new IGnssDebug.SatelliteData();
      satelliteData.readEmbeddedFromParcel(paramHwParcel, hwBlob, (b * 20));
      this.satelliteDataArray.add(satelliteData);
    } 
  }
  
  public final void readFromParcel(HwParcel paramHwParcel) {
    readEmbeddedFromParcel(paramHwParcel, paramHwParcel.readBuffer(112L), 0L);
  }
  
  public final String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("{");
    stringBuilder.append(".position = ");
    stringBuilder.append(this.position);
    stringBuilder.append(", .time = ");
    stringBuilder.append(this.time);
    stringBuilder.append(", .satelliteDataArray = ");
    stringBuilder.append(this.satelliteDataArray);
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public final void writeEmbeddedToBlob(HwBlob paramHwBlob, long paramLong) {
    this.position.writeEmbeddedToBlob(paramHwBlob, paramLong + 0L);
    this.time.writeEmbeddedToBlob(paramHwBlob, 80L + paramLong);
    int i = this.satelliteDataArray.size();
    paramHwBlob.putInt32(paramLong + 96L + 8L, i);
    paramHwBlob.putBool(paramLong + 96L + 12L, false);
    HwBlob hwBlob = new HwBlob(i * 20);
    for (byte b = 0; b < i; b++)
      ((IGnssDebug.SatelliteData)this.satelliteDataArray.get(b)).writeEmbeddedToBlob(hwBlob, (b * 20)); 
    paramHwBlob.putBlob(96L + paramLong + 0L, hwBlob);
  }
  
  public final void writeToParcel(HwParcel paramHwParcel) {
    HwBlob hwBlob = new HwBlob(112);
    writeEmbeddedToBlob(hwBlob, 0L);
    paramHwParcel.writeBuffer(hwBlob);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/gnss/V1_0/IGnssDebug$DebugData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */