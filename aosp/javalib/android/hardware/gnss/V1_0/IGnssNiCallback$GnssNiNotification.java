package android.hardware.gnss.V1_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

public final class GnssNiNotification {
  public byte defaultResponse = (byte)0;
  
  public byte niType = (byte)0;
  
  public int notificationId = 0;
  
  public int notificationIdEncoding = 0;
  
  public String notificationMessage = new String();
  
  public int notifyFlags;
  
  public String requestorId = new String();
  
  public int requestorIdEncoding = 0;
  
  public int timeoutSec = 0;
  
  public static final ArrayList<GnssNiNotification> readVectorFromParcel(HwParcel paramHwParcel) {
    ArrayList<GnssNiNotification> arrayList = new ArrayList();
    HwBlob hwBlob1 = paramHwParcel.readBuffer(16L);
    int i = hwBlob1.getInt32(8L);
    HwBlob hwBlob2 = paramHwParcel.readEmbeddedBuffer((i * 64), hwBlob1.handle(), 0L, true);
    arrayList.clear();
    for (byte b = 0; b < i; b++) {
      GnssNiNotification gnssNiNotification = new GnssNiNotification();
      gnssNiNotification.readEmbeddedFromParcel(paramHwParcel, hwBlob2, (b * 64));
      arrayList.add(gnssNiNotification);
    } 
    return arrayList;
  }
  
  public static final void writeVectorToParcel(HwParcel paramHwParcel, ArrayList<GnssNiNotification> paramArrayList) {
    HwBlob hwBlob1 = new HwBlob(16);
    int i = paramArrayList.size();
    hwBlob1.putInt32(8L, i);
    hwBlob1.putBool(12L, false);
    HwBlob hwBlob2 = new HwBlob(i * 64);
    for (byte b = 0; b < i; b++)
      ((GnssNiNotification)paramArrayList.get(b)).writeEmbeddedToBlob(hwBlob2, (b * 64)); 
    hwBlob1.putBlob(0L, hwBlob2);
    paramHwParcel.writeBuffer(hwBlob1);
  }
  
  public final boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (paramObject == null)
      return false; 
    if (paramObject.getClass() != GnssNiNotification.class)
      return false; 
    paramObject = paramObject;
    return (this.notificationId != ((GnssNiNotification)paramObject).notificationId) ? false : ((this.niType != ((GnssNiNotification)paramObject).niType) ? false : (!HidlSupport.deepEquals(Integer.valueOf(this.notifyFlags), Integer.valueOf(((GnssNiNotification)paramObject).notifyFlags)) ? false : ((this.timeoutSec != ((GnssNiNotification)paramObject).timeoutSec) ? false : ((this.defaultResponse != ((GnssNiNotification)paramObject).defaultResponse) ? false : (!HidlSupport.deepEquals(this.requestorId, ((GnssNiNotification)paramObject).requestorId) ? false : (!HidlSupport.deepEquals(this.notificationMessage, ((GnssNiNotification)paramObject).notificationMessage) ? false : ((this.requestorIdEncoding != ((GnssNiNotification)paramObject).requestorIdEncoding) ? false : (!(this.notificationIdEncoding != ((GnssNiNotification)paramObject).notificationIdEncoding)))))))));
  }
  
  public final int hashCode() {
    return Objects.hash(new Object[] { Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.notificationId))), Integer.valueOf(HidlSupport.deepHashCode(Byte.valueOf(this.niType))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.notifyFlags))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.timeoutSec))), Integer.valueOf(HidlSupport.deepHashCode(Byte.valueOf(this.defaultResponse))), Integer.valueOf(HidlSupport.deepHashCode(this.requestorId)), Integer.valueOf(HidlSupport.deepHashCode(this.notificationMessage)), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.requestorIdEncoding))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.notificationIdEncoding))) });
  }
  
  public final void readEmbeddedFromParcel(HwParcel paramHwParcel, HwBlob paramHwBlob, long paramLong) {
    this.notificationId = paramHwBlob.getInt32(paramLong + 0L);
    this.niType = paramHwBlob.getInt8(paramLong + 4L);
    this.notifyFlags = paramHwBlob.getInt32(paramLong + 8L);
    this.timeoutSec = paramHwBlob.getInt32(paramLong + 12L);
    this.defaultResponse = paramHwBlob.getInt8(paramLong + 16L);
    String str = paramHwBlob.getString(paramLong + 24L);
    this.requestorId = str;
    paramHwParcel.readEmbeddedBuffer(((str.getBytes()).length + 1), paramHwBlob.handle(), paramLong + 24L + 0L, false);
    str = paramHwBlob.getString(paramLong + 40L);
    this.notificationMessage = str;
    paramHwParcel.readEmbeddedBuffer(((str.getBytes()).length + 1), paramHwBlob.handle(), paramLong + 40L + 0L, false);
    this.requestorIdEncoding = paramHwBlob.getInt32(paramLong + 56L);
    this.notificationIdEncoding = paramHwBlob.getInt32(paramLong + 60L);
  }
  
  public final void readFromParcel(HwParcel paramHwParcel) {
    readEmbeddedFromParcel(paramHwParcel, paramHwParcel.readBuffer(64L), 0L);
  }
  
  public final String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("{");
    stringBuilder.append(".notificationId = ");
    stringBuilder.append(this.notificationId);
    stringBuilder.append(", .niType = ");
    stringBuilder.append(IGnssNiCallback.GnssNiType.toString(this.niType));
    stringBuilder.append(", .notifyFlags = ");
    stringBuilder.append(IGnssNiCallback.GnssNiNotifyFlags.dumpBitfield(this.notifyFlags));
    stringBuilder.append(", .timeoutSec = ");
    stringBuilder.append(this.timeoutSec);
    stringBuilder.append(", .defaultResponse = ");
    stringBuilder.append(IGnssNiCallback.GnssUserResponseType.toString(this.defaultResponse));
    stringBuilder.append(", .requestorId = ");
    stringBuilder.append(this.requestorId);
    stringBuilder.append(", .notificationMessage = ");
    stringBuilder.append(this.notificationMessage);
    stringBuilder.append(", .requestorIdEncoding = ");
    stringBuilder.append(IGnssNiCallback.GnssNiEncodingType.toString(this.requestorIdEncoding));
    stringBuilder.append(", .notificationIdEncoding = ");
    stringBuilder.append(IGnssNiCallback.GnssNiEncodingType.toString(this.notificationIdEncoding));
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public final void writeEmbeddedToBlob(HwBlob paramHwBlob, long paramLong) {
    paramHwBlob.putInt32(0L + paramLong, this.notificationId);
    paramHwBlob.putInt8(4L + paramLong, this.niType);
    paramHwBlob.putInt32(8L + paramLong, this.notifyFlags);
    paramHwBlob.putInt32(12L + paramLong, this.timeoutSec);
    paramHwBlob.putInt8(16L + paramLong, this.defaultResponse);
    paramHwBlob.putString(24L + paramLong, this.requestorId);
    paramHwBlob.putString(40L + paramLong, this.notificationMessage);
    paramHwBlob.putInt32(56L + paramLong, this.requestorIdEncoding);
    paramHwBlob.putInt32(60L + paramLong, this.notificationIdEncoding);
  }
  
  public final void writeToParcel(HwParcel paramHwParcel) {
    HwBlob hwBlob = new HwBlob(64);
    writeEmbeddedToBlob(hwBlob, 0L);
    paramHwParcel.writeBuffer(hwBlob);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/gnss/V1_0/IGnssNiCallback$GnssNiNotification.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */