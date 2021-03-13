package android.hardware.radio.V1_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

public final class SendSmsResult {
  public String ackPDU = new String();
  
  public int errorCode = 0;
  
  public int messageRef = 0;
  
  public static final ArrayList<SendSmsResult> readVectorFromParcel(HwParcel paramHwParcel) {
    ArrayList<SendSmsResult> arrayList = new ArrayList();
    HwBlob hwBlob = paramHwParcel.readBuffer(16L);
    int i = hwBlob.getInt32(8L);
    hwBlob = paramHwParcel.readEmbeddedBuffer((i * 32), hwBlob.handle(), 0L, true);
    arrayList.clear();
    for (byte b = 0; b < i; b++) {
      SendSmsResult sendSmsResult = new SendSmsResult();
      sendSmsResult.readEmbeddedFromParcel(paramHwParcel, hwBlob, (b * 32));
      arrayList.add(sendSmsResult);
    } 
    return arrayList;
  }
  
  public static final void writeVectorToParcel(HwParcel paramHwParcel, ArrayList<SendSmsResult> paramArrayList) {
    HwBlob hwBlob1 = new HwBlob(16);
    int i = paramArrayList.size();
    hwBlob1.putInt32(8L, i);
    hwBlob1.putBool(12L, false);
    HwBlob hwBlob2 = new HwBlob(i * 32);
    for (byte b = 0; b < i; b++)
      ((SendSmsResult)paramArrayList.get(b)).writeEmbeddedToBlob(hwBlob2, (b * 32)); 
    hwBlob1.putBlob(0L, hwBlob2);
    paramHwParcel.writeBuffer(hwBlob1);
  }
  
  public final boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (paramObject == null)
      return false; 
    if (paramObject.getClass() != SendSmsResult.class)
      return false; 
    paramObject = paramObject;
    return (this.messageRef != ((SendSmsResult)paramObject).messageRef) ? false : (!HidlSupport.deepEquals(this.ackPDU, ((SendSmsResult)paramObject).ackPDU) ? false : (!(this.errorCode != ((SendSmsResult)paramObject).errorCode)));
  }
  
  public final int hashCode() {
    return Objects.hash(new Object[] { Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.messageRef))), Integer.valueOf(HidlSupport.deepHashCode(this.ackPDU)), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.errorCode))) });
  }
  
  public final void readEmbeddedFromParcel(HwParcel paramHwParcel, HwBlob paramHwBlob, long paramLong) {
    this.messageRef = paramHwBlob.getInt32(paramLong + 0L);
    String str = paramHwBlob.getString(paramLong + 8L);
    this.ackPDU = str;
    paramHwParcel.readEmbeddedBuffer(((str.getBytes()).length + 1), paramHwBlob.handle(), paramLong + 8L + 0L, false);
    this.errorCode = paramHwBlob.getInt32(paramLong + 24L);
  }
  
  public final void readFromParcel(HwParcel paramHwParcel) {
    readEmbeddedFromParcel(paramHwParcel, paramHwParcel.readBuffer(32L), 0L);
  }
  
  public final String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("{");
    stringBuilder.append(".messageRef = ");
    stringBuilder.append(this.messageRef);
    stringBuilder.append(", .ackPDU = ");
    stringBuilder.append(this.ackPDU);
    stringBuilder.append(", .errorCode = ");
    stringBuilder.append(this.errorCode);
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public final void writeEmbeddedToBlob(HwBlob paramHwBlob, long paramLong) {
    paramHwBlob.putInt32(0L + paramLong, this.messageRef);
    paramHwBlob.putString(8L + paramLong, this.ackPDU);
    paramHwBlob.putInt32(24L + paramLong, this.errorCode);
  }
  
  public final void writeToParcel(HwParcel paramHwParcel) {
    HwBlob hwBlob = new HwBlob(32);
    writeEmbeddedToBlob(hwBlob, 0L);
    paramHwParcel.writeBuffer(hwBlob);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/V1_0/SendSmsResult.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */