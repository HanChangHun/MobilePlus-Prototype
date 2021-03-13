package android.hardware.radio.V1_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

public final class AppStatus {
  public String aidPtr = new String();
  
  public String appLabelPtr = new String();
  
  public int appState = 0;
  
  public int appType = 0;
  
  public int persoSubstate = 0;
  
  public int pin1 = 0;
  
  public int pin1Replaced = 0;
  
  public int pin2 = 0;
  
  public static final ArrayList<AppStatus> readVectorFromParcel(HwParcel paramHwParcel) {
    ArrayList<AppStatus> arrayList = new ArrayList();
    HwBlob hwBlob1 = paramHwParcel.readBuffer(16L);
    int i = hwBlob1.getInt32(8L);
    HwBlob hwBlob2 = paramHwParcel.readEmbeddedBuffer((i * 64), hwBlob1.handle(), 0L, true);
    arrayList.clear();
    for (byte b = 0; b < i; b++) {
      AppStatus appStatus = new AppStatus();
      appStatus.readEmbeddedFromParcel(paramHwParcel, hwBlob2, (b * 64));
      arrayList.add(appStatus);
    } 
    return arrayList;
  }
  
  public static final void writeVectorToParcel(HwParcel paramHwParcel, ArrayList<AppStatus> paramArrayList) {
    HwBlob hwBlob1 = new HwBlob(16);
    int i = paramArrayList.size();
    hwBlob1.putInt32(8L, i);
    hwBlob1.putBool(12L, false);
    HwBlob hwBlob2 = new HwBlob(i * 64);
    for (byte b = 0; b < i; b++)
      ((AppStatus)paramArrayList.get(b)).writeEmbeddedToBlob(hwBlob2, (b * 64)); 
    hwBlob1.putBlob(0L, hwBlob2);
    paramHwParcel.writeBuffer(hwBlob1);
  }
  
  public final boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (paramObject == null)
      return false; 
    if (paramObject.getClass() != AppStatus.class)
      return false; 
    paramObject = paramObject;
    return (this.appType != ((AppStatus)paramObject).appType) ? false : ((this.appState != ((AppStatus)paramObject).appState) ? false : ((this.persoSubstate != ((AppStatus)paramObject).persoSubstate) ? false : (!HidlSupport.deepEquals(this.aidPtr, ((AppStatus)paramObject).aidPtr) ? false : (!HidlSupport.deepEquals(this.appLabelPtr, ((AppStatus)paramObject).appLabelPtr) ? false : ((this.pin1Replaced != ((AppStatus)paramObject).pin1Replaced) ? false : ((this.pin1 != ((AppStatus)paramObject).pin1) ? false : (!(this.pin2 != ((AppStatus)paramObject).pin2))))))));
  }
  
  public final int hashCode() {
    return Objects.hash(new Object[] { Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.appType))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.appState))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.persoSubstate))), Integer.valueOf(HidlSupport.deepHashCode(this.aidPtr)), Integer.valueOf(HidlSupport.deepHashCode(this.appLabelPtr)), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.pin1Replaced))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.pin1))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.pin2))) });
  }
  
  public final void readEmbeddedFromParcel(HwParcel paramHwParcel, HwBlob paramHwBlob, long paramLong) {
    this.appType = paramHwBlob.getInt32(paramLong + 0L);
    this.appState = paramHwBlob.getInt32(paramLong + 4L);
    this.persoSubstate = paramHwBlob.getInt32(paramLong + 8L);
    String str = paramHwBlob.getString(paramLong + 16L);
    this.aidPtr = str;
    paramHwParcel.readEmbeddedBuffer(((str.getBytes()).length + 1), paramHwBlob.handle(), paramLong + 16L + 0L, false);
    str = paramHwBlob.getString(paramLong + 32L);
    this.appLabelPtr = str;
    paramHwParcel.readEmbeddedBuffer(((str.getBytes()).length + 1), paramHwBlob.handle(), paramLong + 32L + 0L, false);
    this.pin1Replaced = paramHwBlob.getInt32(paramLong + 48L);
    this.pin1 = paramHwBlob.getInt32(paramLong + 52L);
    this.pin2 = paramHwBlob.getInt32(paramLong + 56L);
  }
  
  public final void readFromParcel(HwParcel paramHwParcel) {
    readEmbeddedFromParcel(paramHwParcel, paramHwParcel.readBuffer(64L), 0L);
  }
  
  public final String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("{");
    stringBuilder.append(".appType = ");
    stringBuilder.append(AppType.toString(this.appType));
    stringBuilder.append(", .appState = ");
    stringBuilder.append(AppState.toString(this.appState));
    stringBuilder.append(", .persoSubstate = ");
    stringBuilder.append(PersoSubstate.toString(this.persoSubstate));
    stringBuilder.append(", .aidPtr = ");
    stringBuilder.append(this.aidPtr);
    stringBuilder.append(", .appLabelPtr = ");
    stringBuilder.append(this.appLabelPtr);
    stringBuilder.append(", .pin1Replaced = ");
    stringBuilder.append(this.pin1Replaced);
    stringBuilder.append(", .pin1 = ");
    stringBuilder.append(PinState.toString(this.pin1));
    stringBuilder.append(", .pin2 = ");
    stringBuilder.append(PinState.toString(this.pin2));
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public final void writeEmbeddedToBlob(HwBlob paramHwBlob, long paramLong) {
    paramHwBlob.putInt32(0L + paramLong, this.appType);
    paramHwBlob.putInt32(4L + paramLong, this.appState);
    paramHwBlob.putInt32(8L + paramLong, this.persoSubstate);
    paramHwBlob.putString(16L + paramLong, this.aidPtr);
    paramHwBlob.putString(32L + paramLong, this.appLabelPtr);
    paramHwBlob.putInt32(48L + paramLong, this.pin1Replaced);
    paramHwBlob.putInt32(52L + paramLong, this.pin1);
    paramHwBlob.putInt32(56L + paramLong, this.pin2);
  }
  
  public final void writeToParcel(HwParcel paramHwParcel) {
    HwBlob hwBlob = new HwBlob(64);
    writeEmbeddedToBlob(hwBlob, 0L);
    paramHwParcel.writeBuffer(hwBlob);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/V1_0/AppStatus.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */