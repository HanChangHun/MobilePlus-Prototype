package android.hardware.radio.V1_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

public final class ImsSmsMessage {
  public ArrayList<CdmaSmsMessage> cdmaMessage = new ArrayList<>();
  
  public ArrayList<GsmSmsMessage> gsmMessage = new ArrayList<>();
  
  public int messageRef = 0;
  
  public boolean retry = false;
  
  public int tech = 0;
  
  public static final ArrayList<ImsSmsMessage> readVectorFromParcel(HwParcel paramHwParcel) {
    ArrayList<ImsSmsMessage> arrayList = new ArrayList();
    HwBlob hwBlob = paramHwParcel.readBuffer(16L);
    int i = hwBlob.getInt32(8L);
    hwBlob = paramHwParcel.readEmbeddedBuffer((i * 48), hwBlob.handle(), 0L, true);
    arrayList.clear();
    for (byte b = 0; b < i; b++) {
      ImsSmsMessage imsSmsMessage = new ImsSmsMessage();
      imsSmsMessage.readEmbeddedFromParcel(paramHwParcel, hwBlob, (b * 48));
      arrayList.add(imsSmsMessage);
    } 
    return arrayList;
  }
  
  public static final void writeVectorToParcel(HwParcel paramHwParcel, ArrayList<ImsSmsMessage> paramArrayList) {
    HwBlob hwBlob1 = new HwBlob(16);
    int i = paramArrayList.size();
    hwBlob1.putInt32(8L, i);
    hwBlob1.putBool(12L, false);
    HwBlob hwBlob2 = new HwBlob(i * 48);
    for (byte b = 0; b < i; b++)
      ((ImsSmsMessage)paramArrayList.get(b)).writeEmbeddedToBlob(hwBlob2, (b * 48)); 
    hwBlob1.putBlob(0L, hwBlob2);
    paramHwParcel.writeBuffer(hwBlob1);
  }
  
  public final boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (paramObject == null)
      return false; 
    if (paramObject.getClass() != ImsSmsMessage.class)
      return false; 
    paramObject = paramObject;
    return (this.tech != ((ImsSmsMessage)paramObject).tech) ? false : ((this.retry != ((ImsSmsMessage)paramObject).retry) ? false : ((this.messageRef != ((ImsSmsMessage)paramObject).messageRef) ? false : (!HidlSupport.deepEquals(this.cdmaMessage, ((ImsSmsMessage)paramObject).cdmaMessage) ? false : (!!HidlSupport.deepEquals(this.gsmMessage, ((ImsSmsMessage)paramObject).gsmMessage)))));
  }
  
  public final int hashCode() {
    return Objects.hash(new Object[] { Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.tech))), Integer.valueOf(HidlSupport.deepHashCode(Boolean.valueOf(this.retry))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.messageRef))), Integer.valueOf(HidlSupport.deepHashCode(this.cdmaMessage)), Integer.valueOf(HidlSupport.deepHashCode(this.gsmMessage)) });
  }
  
  public final void readEmbeddedFromParcel(HwParcel paramHwParcel, HwBlob paramHwBlob, long paramLong) {
    this.tech = paramHwBlob.getInt32(paramLong + 0L);
    this.retry = paramHwBlob.getBool(paramLong + 4L);
    this.messageRef = paramHwBlob.getInt32(paramLong + 8L);
    int i = paramHwBlob.getInt32(paramLong + 16L + 8L);
    HwBlob hwBlob = paramHwParcel.readEmbeddedBuffer((i * 88), paramHwBlob.handle(), paramLong + 16L + 0L, true);
    this.cdmaMessage.clear();
    byte b;
    for (b = 0; b < i; b++) {
      CdmaSmsMessage cdmaSmsMessage = new CdmaSmsMessage();
      cdmaSmsMessage.readEmbeddedFromParcel(paramHwParcel, hwBlob, (b * 88));
      this.cdmaMessage.add(cdmaSmsMessage);
    } 
    i = paramHwBlob.getInt32(paramLong + 32L + 8L);
    hwBlob = paramHwParcel.readEmbeddedBuffer((i * 32), paramHwBlob.handle(), paramLong + 32L + 0L, true);
    this.gsmMessage.clear();
    for (b = 0; b < i; b++) {
      GsmSmsMessage gsmSmsMessage = new GsmSmsMessage();
      gsmSmsMessage.readEmbeddedFromParcel(paramHwParcel, hwBlob, (b * 32));
      this.gsmMessage.add(gsmSmsMessage);
    } 
  }
  
  public final void readFromParcel(HwParcel paramHwParcel) {
    readEmbeddedFromParcel(paramHwParcel, paramHwParcel.readBuffer(48L), 0L);
  }
  
  public final String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("{");
    stringBuilder.append(".tech = ");
    stringBuilder.append(RadioTechnologyFamily.toString(this.tech));
    stringBuilder.append(", .retry = ");
    stringBuilder.append(this.retry);
    stringBuilder.append(", .messageRef = ");
    stringBuilder.append(this.messageRef);
    stringBuilder.append(", .cdmaMessage = ");
    stringBuilder.append(this.cdmaMessage);
    stringBuilder.append(", .gsmMessage = ");
    stringBuilder.append(this.gsmMessage);
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public final void writeEmbeddedToBlob(HwBlob paramHwBlob, long paramLong) {
    paramHwBlob.putInt32(paramLong + 0L, this.tech);
    paramHwBlob.putBool(paramLong + 4L, this.retry);
    paramHwBlob.putInt32(paramLong + 8L, this.messageRef);
    int i = this.cdmaMessage.size();
    paramHwBlob.putInt32(paramLong + 16L + 8L, i);
    paramHwBlob.putBool(paramLong + 16L + 12L, false);
    HwBlob hwBlob = new HwBlob(i * 88);
    byte b;
    for (b = 0; b < i; b++)
      ((CdmaSmsMessage)this.cdmaMessage.get(b)).writeEmbeddedToBlob(hwBlob, (b * 88)); 
    paramHwBlob.putBlob(paramLong + 16L + 0L, hwBlob);
    i = this.gsmMessage.size();
    paramHwBlob.putInt32(paramLong + 32L + 8L, i);
    paramHwBlob.putBool(paramLong + 32L + 12L, false);
    hwBlob = new HwBlob(i * 32);
    for (b = 0; b < i; b++)
      ((GsmSmsMessage)this.gsmMessage.get(b)).writeEmbeddedToBlob(hwBlob, (b * 32)); 
    paramHwBlob.putBlob(paramLong + 32L + 0L, hwBlob);
  }
  
  public final void writeToParcel(HwParcel paramHwParcel) {
    HwBlob hwBlob = new HwBlob(48);
    writeEmbeddedToBlob(hwBlob, 0L);
    paramHwParcel.writeBuffer(hwBlob);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/V1_0/ImsSmsMessage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */