package android.hardware.radio.V1_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

public final class SmsWriteArgs {
  public String pdu = new String();
  
  public String smsc = new String();
  
  public int status = 0;
  
  public static final ArrayList<SmsWriteArgs> readVectorFromParcel(HwParcel paramHwParcel) {
    ArrayList<SmsWriteArgs> arrayList = new ArrayList();
    HwBlob hwBlob1 = paramHwParcel.readBuffer(16L);
    int i = hwBlob1.getInt32(8L);
    HwBlob hwBlob2 = paramHwParcel.readEmbeddedBuffer((i * 40), hwBlob1.handle(), 0L, true);
    arrayList.clear();
    for (byte b = 0; b < i; b++) {
      SmsWriteArgs smsWriteArgs = new SmsWriteArgs();
      smsWriteArgs.readEmbeddedFromParcel(paramHwParcel, hwBlob2, (b * 40));
      arrayList.add(smsWriteArgs);
    } 
    return arrayList;
  }
  
  public static final void writeVectorToParcel(HwParcel paramHwParcel, ArrayList<SmsWriteArgs> paramArrayList) {
    HwBlob hwBlob1 = new HwBlob(16);
    int i = paramArrayList.size();
    hwBlob1.putInt32(8L, i);
    hwBlob1.putBool(12L, false);
    HwBlob hwBlob2 = new HwBlob(i * 40);
    for (byte b = 0; b < i; b++)
      ((SmsWriteArgs)paramArrayList.get(b)).writeEmbeddedToBlob(hwBlob2, (b * 40)); 
    hwBlob1.putBlob(0L, hwBlob2);
    paramHwParcel.writeBuffer(hwBlob1);
  }
  
  public final boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (paramObject == null)
      return false; 
    if (paramObject.getClass() != SmsWriteArgs.class)
      return false; 
    paramObject = paramObject;
    return (this.status != ((SmsWriteArgs)paramObject).status) ? false : (!HidlSupport.deepEquals(this.pdu, ((SmsWriteArgs)paramObject).pdu) ? false : (!!HidlSupport.deepEquals(this.smsc, ((SmsWriteArgs)paramObject).smsc)));
  }
  
  public final int hashCode() {
    return Objects.hash(new Object[] { Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.status))), Integer.valueOf(HidlSupport.deepHashCode(this.pdu)), Integer.valueOf(HidlSupport.deepHashCode(this.smsc)) });
  }
  
  public final void readEmbeddedFromParcel(HwParcel paramHwParcel, HwBlob paramHwBlob, long paramLong) {
    this.status = paramHwBlob.getInt32(paramLong + 0L);
    String str = paramHwBlob.getString(paramLong + 8L);
    this.pdu = str;
    paramHwParcel.readEmbeddedBuffer(((str.getBytes()).length + 1), paramHwBlob.handle(), paramLong + 8L + 0L, false);
    str = paramHwBlob.getString(paramLong + 24L);
    this.smsc = str;
    paramHwParcel.readEmbeddedBuffer(((str.getBytes()).length + 1), paramHwBlob.handle(), paramLong + 24L + 0L, false);
  }
  
  public final void readFromParcel(HwParcel paramHwParcel) {
    readEmbeddedFromParcel(paramHwParcel, paramHwParcel.readBuffer(40L), 0L);
  }
  
  public final String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("{");
    stringBuilder.append(".status = ");
    stringBuilder.append(SmsWriteArgsStatus.toString(this.status));
    stringBuilder.append(", .pdu = ");
    stringBuilder.append(this.pdu);
    stringBuilder.append(", .smsc = ");
    stringBuilder.append(this.smsc);
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public final void writeEmbeddedToBlob(HwBlob paramHwBlob, long paramLong) {
    paramHwBlob.putInt32(0L + paramLong, this.status);
    paramHwBlob.putString(8L + paramLong, this.pdu);
    paramHwBlob.putString(24L + paramLong, this.smsc);
  }
  
  public final void writeToParcel(HwParcel paramHwParcel) {
    HwBlob hwBlob = new HwBlob(40);
    writeEmbeddedToBlob(hwBlob, 0L);
    paramHwParcel.writeBuffer(hwBlob);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/V1_0/SmsWriteArgs.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */