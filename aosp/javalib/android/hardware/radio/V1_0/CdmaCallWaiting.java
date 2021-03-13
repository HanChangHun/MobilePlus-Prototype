package android.hardware.radio.V1_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

public final class CdmaCallWaiting {
  public String name = new String();
  
  public String number = new String();
  
  public int numberPlan = 0;
  
  public int numberPresentation = 0;
  
  public int numberType = 0;
  
  public CdmaSignalInfoRecord signalInfoRecord = new CdmaSignalInfoRecord();
  
  public static final ArrayList<CdmaCallWaiting> readVectorFromParcel(HwParcel paramHwParcel) {
    ArrayList<CdmaCallWaiting> arrayList = new ArrayList();
    HwBlob hwBlob = paramHwParcel.readBuffer(16L);
    int i = hwBlob.getInt32(8L);
    hwBlob = paramHwParcel.readEmbeddedBuffer((i * 56), hwBlob.handle(), 0L, true);
    arrayList.clear();
    for (byte b = 0; b < i; b++) {
      CdmaCallWaiting cdmaCallWaiting = new CdmaCallWaiting();
      cdmaCallWaiting.readEmbeddedFromParcel(paramHwParcel, hwBlob, (b * 56));
      arrayList.add(cdmaCallWaiting);
    } 
    return arrayList;
  }
  
  public static final void writeVectorToParcel(HwParcel paramHwParcel, ArrayList<CdmaCallWaiting> paramArrayList) {
    HwBlob hwBlob1 = new HwBlob(16);
    int i = paramArrayList.size();
    hwBlob1.putInt32(8L, i);
    hwBlob1.putBool(12L, false);
    HwBlob hwBlob2 = new HwBlob(i * 56);
    for (byte b = 0; b < i; b++)
      ((CdmaCallWaiting)paramArrayList.get(b)).writeEmbeddedToBlob(hwBlob2, (b * 56)); 
    hwBlob1.putBlob(0L, hwBlob2);
    paramHwParcel.writeBuffer(hwBlob1);
  }
  
  public final boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (paramObject == null)
      return false; 
    if (paramObject.getClass() != CdmaCallWaiting.class)
      return false; 
    paramObject = paramObject;
    return !HidlSupport.deepEquals(this.number, ((CdmaCallWaiting)paramObject).number) ? false : ((this.numberPresentation != ((CdmaCallWaiting)paramObject).numberPresentation) ? false : (!HidlSupport.deepEquals(this.name, ((CdmaCallWaiting)paramObject).name) ? false : (!HidlSupport.deepEquals(this.signalInfoRecord, ((CdmaCallWaiting)paramObject).signalInfoRecord) ? false : ((this.numberType != ((CdmaCallWaiting)paramObject).numberType) ? false : (!(this.numberPlan != ((CdmaCallWaiting)paramObject).numberPlan))))));
  }
  
  public final int hashCode() {
    return Objects.hash(new Object[] { Integer.valueOf(HidlSupport.deepHashCode(this.number)), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.numberPresentation))), Integer.valueOf(HidlSupport.deepHashCode(this.name)), Integer.valueOf(HidlSupport.deepHashCode(this.signalInfoRecord)), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.numberType))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.numberPlan))) });
  }
  
  public final void readEmbeddedFromParcel(HwParcel paramHwParcel, HwBlob paramHwBlob, long paramLong) {
    String str = paramHwBlob.getString(paramLong + 0L);
    this.number = str;
    paramHwParcel.readEmbeddedBuffer(((str.getBytes()).length + 1), paramHwBlob.handle(), paramLong + 0L + 0L, false);
    this.numberPresentation = paramHwBlob.getInt32(paramLong + 16L);
    str = paramHwBlob.getString(paramLong + 24L);
    this.name = str;
    paramHwParcel.readEmbeddedBuffer(((str.getBytes()).length + 1), paramHwBlob.handle(), paramLong + 24L + 0L, false);
    this.signalInfoRecord.readEmbeddedFromParcel(paramHwParcel, paramHwBlob, paramLong + 40L);
    this.numberType = paramHwBlob.getInt32(paramLong + 44L);
    this.numberPlan = paramHwBlob.getInt32(paramLong + 48L);
  }
  
  public final void readFromParcel(HwParcel paramHwParcel) {
    readEmbeddedFromParcel(paramHwParcel, paramHwParcel.readBuffer(56L), 0L);
  }
  
  public final String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("{");
    stringBuilder.append(".number = ");
    stringBuilder.append(this.number);
    stringBuilder.append(", .numberPresentation = ");
    stringBuilder.append(CdmaCallWaitingNumberPresentation.toString(this.numberPresentation));
    stringBuilder.append(", .name = ");
    stringBuilder.append(this.name);
    stringBuilder.append(", .signalInfoRecord = ");
    stringBuilder.append(this.signalInfoRecord);
    stringBuilder.append(", .numberType = ");
    stringBuilder.append(CdmaCallWaitingNumberType.toString(this.numberType));
    stringBuilder.append(", .numberPlan = ");
    stringBuilder.append(CdmaCallWaitingNumberPlan.toString(this.numberPlan));
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public final void writeEmbeddedToBlob(HwBlob paramHwBlob, long paramLong) {
    paramHwBlob.putString(0L + paramLong, this.number);
    paramHwBlob.putInt32(16L + paramLong, this.numberPresentation);
    paramHwBlob.putString(24L + paramLong, this.name);
    this.signalInfoRecord.writeEmbeddedToBlob(paramHwBlob, 40L + paramLong);
    paramHwBlob.putInt32(44L + paramLong, this.numberType);
    paramHwBlob.putInt32(48L + paramLong, this.numberPlan);
  }
  
  public final void writeToParcel(HwParcel paramHwParcel) {
    HwBlob hwBlob = new HwBlob(56);
    writeEmbeddedToBlob(hwBlob, 0L);
    paramHwParcel.writeBuffer(hwBlob);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/V1_0/CdmaCallWaiting.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */