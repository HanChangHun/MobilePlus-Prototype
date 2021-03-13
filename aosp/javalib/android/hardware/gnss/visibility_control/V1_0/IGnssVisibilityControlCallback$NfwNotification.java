package android.hardware.gnss.visibility_control.V1_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

public final class NfwNotification {
  public boolean inEmergencyMode = false;
  
  public boolean isCachedLocation = false;
  
  public String otherProtocolStackName = new String();
  
  public byte protocolStack = (byte)0;
  
  public String proxyAppPackageName = new String();
  
  public byte requestor = (byte)0;
  
  public String requestorId = new String();
  
  public byte responseType = (byte)0;
  
  public static final ArrayList<NfwNotification> readVectorFromParcel(HwParcel paramHwParcel) {
    ArrayList<NfwNotification> arrayList = new ArrayList();
    HwBlob hwBlob = paramHwParcel.readBuffer(16L);
    int i = hwBlob.getInt32(8L);
    hwBlob = paramHwParcel.readEmbeddedBuffer((i * 72), hwBlob.handle(), 0L, true);
    arrayList.clear();
    for (byte b = 0; b < i; b++) {
      NfwNotification nfwNotification = new NfwNotification();
      nfwNotification.readEmbeddedFromParcel(paramHwParcel, hwBlob, (b * 72));
      arrayList.add(nfwNotification);
    } 
    return arrayList;
  }
  
  public static final void writeVectorToParcel(HwParcel paramHwParcel, ArrayList<NfwNotification> paramArrayList) {
    HwBlob hwBlob1 = new HwBlob(16);
    int i = paramArrayList.size();
    hwBlob1.putInt32(8L, i);
    hwBlob1.putBool(12L, false);
    HwBlob hwBlob2 = new HwBlob(i * 72);
    for (byte b = 0; b < i; b++)
      ((NfwNotification)paramArrayList.get(b)).writeEmbeddedToBlob(hwBlob2, (b * 72)); 
    hwBlob1.putBlob(0L, hwBlob2);
    paramHwParcel.writeBuffer(hwBlob1);
  }
  
  public final boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (paramObject == null)
      return false; 
    if (paramObject.getClass() != NfwNotification.class)
      return false; 
    paramObject = paramObject;
    return !HidlSupport.deepEquals(this.proxyAppPackageName, ((NfwNotification)paramObject).proxyAppPackageName) ? false : ((this.protocolStack != ((NfwNotification)paramObject).protocolStack) ? false : (!HidlSupport.deepEquals(this.otherProtocolStackName, ((NfwNotification)paramObject).otherProtocolStackName) ? false : ((this.requestor != ((NfwNotification)paramObject).requestor) ? false : (!HidlSupport.deepEquals(this.requestorId, ((NfwNotification)paramObject).requestorId) ? false : ((this.responseType != ((NfwNotification)paramObject).responseType) ? false : ((this.inEmergencyMode != ((NfwNotification)paramObject).inEmergencyMode) ? false : (!(this.isCachedLocation != ((NfwNotification)paramObject).isCachedLocation))))))));
  }
  
  public final int hashCode() {
    return Objects.hash(new Object[] { Integer.valueOf(HidlSupport.deepHashCode(this.proxyAppPackageName)), Integer.valueOf(HidlSupport.deepHashCode(Byte.valueOf(this.protocolStack))), Integer.valueOf(HidlSupport.deepHashCode(this.otherProtocolStackName)), Integer.valueOf(HidlSupport.deepHashCode(Byte.valueOf(this.requestor))), Integer.valueOf(HidlSupport.deepHashCode(this.requestorId)), Integer.valueOf(HidlSupport.deepHashCode(Byte.valueOf(this.responseType))), Integer.valueOf(HidlSupport.deepHashCode(Boolean.valueOf(this.inEmergencyMode))), Integer.valueOf(HidlSupport.deepHashCode(Boolean.valueOf(this.isCachedLocation))) });
  }
  
  public final void readEmbeddedFromParcel(HwParcel paramHwParcel, HwBlob paramHwBlob, long paramLong) {
    String str = paramHwBlob.getString(paramLong + 0L);
    this.proxyAppPackageName = str;
    paramHwParcel.readEmbeddedBuffer(((str.getBytes()).length + 1), paramHwBlob.handle(), paramLong + 0L + 0L, false);
    this.protocolStack = paramHwBlob.getInt8(paramLong + 16L);
    str = paramHwBlob.getString(paramLong + 24L);
    this.otherProtocolStackName = str;
    paramHwParcel.readEmbeddedBuffer(((str.getBytes()).length + 1), paramHwBlob.handle(), paramLong + 24L + 0L, false);
    this.requestor = paramHwBlob.getInt8(paramLong + 40L);
    str = paramHwBlob.getString(paramLong + 48L);
    this.requestorId = str;
    paramHwParcel.readEmbeddedBuffer(((str.getBytes()).length + 1), paramHwBlob.handle(), paramLong + 48L + 0L, false);
    this.responseType = paramHwBlob.getInt8(paramLong + 64L);
    this.inEmergencyMode = paramHwBlob.getBool(paramLong + 65L);
    this.isCachedLocation = paramHwBlob.getBool(paramLong + 66L);
  }
  
  public final void readFromParcel(HwParcel paramHwParcel) {
    readEmbeddedFromParcel(paramHwParcel, paramHwParcel.readBuffer(72L), 0L);
  }
  
  public final String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("{");
    stringBuilder.append(".proxyAppPackageName = ");
    stringBuilder.append(this.proxyAppPackageName);
    stringBuilder.append(", .protocolStack = ");
    stringBuilder.append(IGnssVisibilityControlCallback.NfwProtocolStack.toString(this.protocolStack));
    stringBuilder.append(", .otherProtocolStackName = ");
    stringBuilder.append(this.otherProtocolStackName);
    stringBuilder.append(", .requestor = ");
    stringBuilder.append(IGnssVisibilityControlCallback.NfwRequestor.toString(this.requestor));
    stringBuilder.append(", .requestorId = ");
    stringBuilder.append(this.requestorId);
    stringBuilder.append(", .responseType = ");
    stringBuilder.append(IGnssVisibilityControlCallback.NfwResponseType.toString(this.responseType));
    stringBuilder.append(", .inEmergencyMode = ");
    stringBuilder.append(this.inEmergencyMode);
    stringBuilder.append(", .isCachedLocation = ");
    stringBuilder.append(this.isCachedLocation);
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public final void writeEmbeddedToBlob(HwBlob paramHwBlob, long paramLong) {
    paramHwBlob.putString(0L + paramLong, this.proxyAppPackageName);
    paramHwBlob.putInt8(16L + paramLong, this.protocolStack);
    paramHwBlob.putString(24L + paramLong, this.otherProtocolStackName);
    paramHwBlob.putInt8(40L + paramLong, this.requestor);
    paramHwBlob.putString(48L + paramLong, this.requestorId);
    paramHwBlob.putInt8(64L + paramLong, this.responseType);
    paramHwBlob.putBool(65L + paramLong, this.inEmergencyMode);
    paramHwBlob.putBool(66L + paramLong, this.isCachedLocation);
  }
  
  public final void writeToParcel(HwParcel paramHwParcel) {
    HwBlob hwBlob = new HwBlob(72);
    writeEmbeddedToBlob(hwBlob, 0L);
    paramHwParcel.writeBuffer(hwBlob);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/gnss/visibility_control/V1_0/IGnssVisibilityControlCallback$NfwNotification.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */