package android.hardware.radio.V1_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

public final class GsmSmsMessage {
  public String pdu = new String();
  
  public String smscPdu = new String();
  
  public static final ArrayList<GsmSmsMessage> readVectorFromParcel(HwParcel paramHwParcel) {
    ArrayList<GsmSmsMessage> arrayList = new ArrayList();
    HwBlob hwBlob1 = paramHwParcel.readBuffer(16L);
    int i = hwBlob1.getInt32(8L);
    HwBlob hwBlob2 = paramHwParcel.readEmbeddedBuffer((i * 32), hwBlob1.handle(), 0L, true);
    arrayList.clear();
    for (byte b = 0; b < i; b++) {
      GsmSmsMessage gsmSmsMessage = new GsmSmsMessage();
      gsmSmsMessage.readEmbeddedFromParcel(paramHwParcel, hwBlob2, (b * 32));
      arrayList.add(gsmSmsMessage);
    } 
    return arrayList;
  }
  
  public static final void writeVectorToParcel(HwParcel paramHwParcel, ArrayList<GsmSmsMessage> paramArrayList) {
    HwBlob hwBlob1 = new HwBlob(16);
    int i = paramArrayList.size();
    hwBlob1.putInt32(8L, i);
    hwBlob1.putBool(12L, false);
    HwBlob hwBlob2 = new HwBlob(i * 32);
    for (byte b = 0; b < i; b++)
      ((GsmSmsMessage)paramArrayList.get(b)).writeEmbeddedToBlob(hwBlob2, (b * 32)); 
    hwBlob1.putBlob(0L, hwBlob2);
    paramHwParcel.writeBuffer(hwBlob1);
  }
  
  public final boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (paramObject == null)
      return false; 
    if (paramObject.getClass() != GsmSmsMessage.class)
      return false; 
    paramObject = paramObject;
    return !HidlSupport.deepEquals(this.smscPdu, ((GsmSmsMessage)paramObject).smscPdu) ? false : (!!HidlSupport.deepEquals(this.pdu, ((GsmSmsMessage)paramObject).pdu));
  }
  
  public final int hashCode() {
    return Objects.hash(new Object[] { Integer.valueOf(HidlSupport.deepHashCode(this.smscPdu)), Integer.valueOf(HidlSupport.deepHashCode(this.pdu)) });
  }
  
  public final void readEmbeddedFromParcel(HwParcel paramHwParcel, HwBlob paramHwBlob, long paramLong) {
    String str = paramHwBlob.getString(paramLong + 0L);
    this.smscPdu = str;
    paramHwParcel.readEmbeddedBuffer(((str.getBytes()).length + 1), paramHwBlob.handle(), paramLong + 0L + 0L, false);
    str = paramHwBlob.getString(paramLong + 16L);
    this.pdu = str;
    paramHwParcel.readEmbeddedBuffer(((str.getBytes()).length + 1), paramHwBlob.handle(), paramLong + 16L + 0L, false);
  }
  
  public final void readFromParcel(HwParcel paramHwParcel) {
    readEmbeddedFromParcel(paramHwParcel, paramHwParcel.readBuffer(32L), 0L);
  }
  
  public final String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("{");
    stringBuilder.append(".smscPdu = ");
    stringBuilder.append(this.smscPdu);
    stringBuilder.append(", .pdu = ");
    stringBuilder.append(this.pdu);
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public final void writeEmbeddedToBlob(HwBlob paramHwBlob, long paramLong) {
    paramHwBlob.putString(0L + paramLong, this.smscPdu);
    paramHwBlob.putString(16L + paramLong, this.pdu);
  }
  
  public final void writeToParcel(HwParcel paramHwParcel) {
    HwBlob hwBlob = new HwBlob(32);
    writeEmbeddedToBlob(hwBlob, 0L);
    paramHwParcel.writeBuffer(hwBlob);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/V1_0/GsmSmsMessage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */