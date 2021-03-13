package android.hardware.radio.V1_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

public final class VoiceRegStateResult {
  public CellIdentity cellIdentity = new CellIdentity();
  
  public boolean cssSupported = false;
  
  public int defaultRoamingIndicator = 0;
  
  public int rat = 0;
  
  public int reasonForDenial = 0;
  
  public int regState = 0;
  
  public int roamingIndicator = 0;
  
  public int systemIsInPrl = 0;
  
  public static final ArrayList<VoiceRegStateResult> readVectorFromParcel(HwParcel paramHwParcel) {
    ArrayList<VoiceRegStateResult> arrayList = new ArrayList();
    HwBlob hwBlob = paramHwParcel.readBuffer(16L);
    int i = hwBlob.getInt32(8L);
    hwBlob = paramHwParcel.readEmbeddedBuffer((i * 120), hwBlob.handle(), 0L, true);
    arrayList.clear();
    for (byte b = 0; b < i; b++) {
      VoiceRegStateResult voiceRegStateResult = new VoiceRegStateResult();
      voiceRegStateResult.readEmbeddedFromParcel(paramHwParcel, hwBlob, (b * 120));
      arrayList.add(voiceRegStateResult);
    } 
    return arrayList;
  }
  
  public static final void writeVectorToParcel(HwParcel paramHwParcel, ArrayList<VoiceRegStateResult> paramArrayList) {
    HwBlob hwBlob1 = new HwBlob(16);
    int i = paramArrayList.size();
    hwBlob1.putInt32(8L, i);
    hwBlob1.putBool(12L, false);
    HwBlob hwBlob2 = new HwBlob(i * 120);
    for (byte b = 0; b < i; b++)
      ((VoiceRegStateResult)paramArrayList.get(b)).writeEmbeddedToBlob(hwBlob2, (b * 120)); 
    hwBlob1.putBlob(0L, hwBlob2);
    paramHwParcel.writeBuffer(hwBlob1);
  }
  
  public final boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (paramObject == null)
      return false; 
    if (paramObject.getClass() != VoiceRegStateResult.class)
      return false; 
    paramObject = paramObject;
    return (this.regState != ((VoiceRegStateResult)paramObject).regState) ? false : ((this.rat != ((VoiceRegStateResult)paramObject).rat) ? false : ((this.cssSupported != ((VoiceRegStateResult)paramObject).cssSupported) ? false : ((this.roamingIndicator != ((VoiceRegStateResult)paramObject).roamingIndicator) ? false : ((this.systemIsInPrl != ((VoiceRegStateResult)paramObject).systemIsInPrl) ? false : ((this.defaultRoamingIndicator != ((VoiceRegStateResult)paramObject).defaultRoamingIndicator) ? false : ((this.reasonForDenial != ((VoiceRegStateResult)paramObject).reasonForDenial) ? false : (!!HidlSupport.deepEquals(this.cellIdentity, ((VoiceRegStateResult)paramObject).cellIdentity))))))));
  }
  
  public final int hashCode() {
    return Objects.hash(new Object[] { Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.regState))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.rat))), Integer.valueOf(HidlSupport.deepHashCode(Boolean.valueOf(this.cssSupported))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.roamingIndicator))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.systemIsInPrl))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.defaultRoamingIndicator))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.reasonForDenial))), Integer.valueOf(HidlSupport.deepHashCode(this.cellIdentity)) });
  }
  
  public final void readEmbeddedFromParcel(HwParcel paramHwParcel, HwBlob paramHwBlob, long paramLong) {
    this.regState = paramHwBlob.getInt32(0L + paramLong);
    this.rat = paramHwBlob.getInt32(4L + paramLong);
    this.cssSupported = paramHwBlob.getBool(8L + paramLong);
    this.roamingIndicator = paramHwBlob.getInt32(12L + paramLong);
    this.systemIsInPrl = paramHwBlob.getInt32(16L + paramLong);
    this.defaultRoamingIndicator = paramHwBlob.getInt32(20L + paramLong);
    this.reasonForDenial = paramHwBlob.getInt32(24L + paramLong);
    this.cellIdentity.readEmbeddedFromParcel(paramHwParcel, paramHwBlob, 32L + paramLong);
  }
  
  public final void readFromParcel(HwParcel paramHwParcel) {
    readEmbeddedFromParcel(paramHwParcel, paramHwParcel.readBuffer(120L), 0L);
  }
  
  public final String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("{");
    stringBuilder.append(".regState = ");
    stringBuilder.append(RegState.toString(this.regState));
    stringBuilder.append(", .rat = ");
    stringBuilder.append(this.rat);
    stringBuilder.append(", .cssSupported = ");
    stringBuilder.append(this.cssSupported);
    stringBuilder.append(", .roamingIndicator = ");
    stringBuilder.append(this.roamingIndicator);
    stringBuilder.append(", .systemIsInPrl = ");
    stringBuilder.append(this.systemIsInPrl);
    stringBuilder.append(", .defaultRoamingIndicator = ");
    stringBuilder.append(this.defaultRoamingIndicator);
    stringBuilder.append(", .reasonForDenial = ");
    stringBuilder.append(this.reasonForDenial);
    stringBuilder.append(", .cellIdentity = ");
    stringBuilder.append(this.cellIdentity);
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public final void writeEmbeddedToBlob(HwBlob paramHwBlob, long paramLong) {
    paramHwBlob.putInt32(0L + paramLong, this.regState);
    paramHwBlob.putInt32(4L + paramLong, this.rat);
    paramHwBlob.putBool(8L + paramLong, this.cssSupported);
    paramHwBlob.putInt32(12L + paramLong, this.roamingIndicator);
    paramHwBlob.putInt32(16L + paramLong, this.systemIsInPrl);
    paramHwBlob.putInt32(20L + paramLong, this.defaultRoamingIndicator);
    paramHwBlob.putInt32(24L + paramLong, this.reasonForDenial);
    this.cellIdentity.writeEmbeddedToBlob(paramHwBlob, 32L + paramLong);
  }
  
  public final void writeToParcel(HwParcel paramHwParcel) {
    HwBlob hwBlob = new HwBlob(120);
    writeEmbeddedToBlob(hwBlob, 0L);
    paramHwParcel.writeBuffer(hwBlob);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/V1_0/VoiceRegStateResult.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */