package android.hardware.contexthub.V1_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

public final class ContextHubMsg {
  public long appName = 0L;
  
  public short hostEndPoint = (short)0;
  
  public ArrayList<Byte> msg = new ArrayList<>();
  
  public int msgType = 0;
  
  public static final ArrayList<ContextHubMsg> readVectorFromParcel(HwParcel paramHwParcel) {
    ArrayList<ContextHubMsg> arrayList = new ArrayList();
    HwBlob hwBlob1 = paramHwParcel.readBuffer(16L);
    int i = hwBlob1.getInt32(8L);
    HwBlob hwBlob2 = paramHwParcel.readEmbeddedBuffer((i * 32), hwBlob1.handle(), 0L, true);
    arrayList.clear();
    for (byte b = 0; b < i; b++) {
      ContextHubMsg contextHubMsg = new ContextHubMsg();
      contextHubMsg.readEmbeddedFromParcel(paramHwParcel, hwBlob2, (b * 32));
      arrayList.add(contextHubMsg);
    } 
    return arrayList;
  }
  
  public static final void writeVectorToParcel(HwParcel paramHwParcel, ArrayList<ContextHubMsg> paramArrayList) {
    HwBlob hwBlob1 = new HwBlob(16);
    int i = paramArrayList.size();
    hwBlob1.putInt32(8L, i);
    hwBlob1.putBool(12L, false);
    HwBlob hwBlob2 = new HwBlob(i * 32);
    for (byte b = 0; b < i; b++)
      ((ContextHubMsg)paramArrayList.get(b)).writeEmbeddedToBlob(hwBlob2, (b * 32)); 
    hwBlob1.putBlob(0L, hwBlob2);
    paramHwParcel.writeBuffer(hwBlob1);
  }
  
  public final boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (paramObject == null)
      return false; 
    if (paramObject.getClass() != ContextHubMsg.class)
      return false; 
    paramObject = paramObject;
    return (this.appName != ((ContextHubMsg)paramObject).appName) ? false : ((this.hostEndPoint != ((ContextHubMsg)paramObject).hostEndPoint) ? false : ((this.msgType != ((ContextHubMsg)paramObject).msgType) ? false : (!!HidlSupport.deepEquals(this.msg, ((ContextHubMsg)paramObject).msg))));
  }
  
  public final int hashCode() {
    return Objects.hash(new Object[] { Integer.valueOf(HidlSupport.deepHashCode(Long.valueOf(this.appName))), Integer.valueOf(HidlSupport.deepHashCode(Short.valueOf(this.hostEndPoint))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.msgType))), Integer.valueOf(HidlSupport.deepHashCode(this.msg)) });
  }
  
  public final void readEmbeddedFromParcel(HwParcel paramHwParcel, HwBlob paramHwBlob, long paramLong) {
    this.appName = paramHwBlob.getInt64(paramLong + 0L);
    this.hostEndPoint = paramHwBlob.getInt16(paramLong + 8L);
    this.msgType = paramHwBlob.getInt32(paramLong + 12L);
    int i = paramHwBlob.getInt32(paramLong + 16L + 8L);
    HwBlob hwBlob = paramHwParcel.readEmbeddedBuffer((i * 1), paramHwBlob.handle(), paramLong + 16L + 0L, true);
    this.msg.clear();
    for (byte b = 0; b < i; b++) {
      byte b1 = hwBlob.getInt8((b * 1));
      this.msg.add(Byte.valueOf(b1));
    } 
  }
  
  public final void readFromParcel(HwParcel paramHwParcel) {
    readEmbeddedFromParcel(paramHwParcel, paramHwParcel.readBuffer(32L), 0L);
  }
  
  public final String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("{");
    stringBuilder.append(".appName = ");
    stringBuilder.append(this.appName);
    stringBuilder.append(", .hostEndPoint = ");
    stringBuilder.append(this.hostEndPoint);
    stringBuilder.append(", .msgType = ");
    stringBuilder.append(this.msgType);
    stringBuilder.append(", .msg = ");
    stringBuilder.append(this.msg);
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public final void writeEmbeddedToBlob(HwBlob paramHwBlob, long paramLong) {
    paramHwBlob.putInt64(paramLong + 0L, this.appName);
    paramHwBlob.putInt16(paramLong + 8L, this.hostEndPoint);
    paramHwBlob.putInt32(paramLong + 12L, this.msgType);
    int i = this.msg.size();
    paramHwBlob.putInt32(paramLong + 16L + 8L, i);
    paramHwBlob.putBool(paramLong + 16L + 12L, false);
    HwBlob hwBlob = new HwBlob(i * 1);
    for (byte b = 0; b < i; b++)
      hwBlob.putInt8((b * 1), ((Byte)this.msg.get(b)).byteValue()); 
    paramHwBlob.putBlob(16L + paramLong + 0L, hwBlob);
  }
  
  public final void writeToParcel(HwParcel paramHwParcel) {
    HwBlob hwBlob = new HwBlob(32);
    writeEmbeddedToBlob(hwBlob, 0L);
    paramHwParcel.writeBuffer(hwBlob);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/contexthub/V1_0/ContextHubMsg.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */