package android.hardware.radio.V1_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

public final class SimApdu {
  public int cla = 0;
  
  public String data = new String();
  
  public int instruction = 0;
  
  public int p1 = 0;
  
  public int p2 = 0;
  
  public int p3 = 0;
  
  public int sessionId = 0;
  
  public static final ArrayList<SimApdu> readVectorFromParcel(HwParcel paramHwParcel) {
    ArrayList<SimApdu> arrayList = new ArrayList();
    HwBlob hwBlob = paramHwParcel.readBuffer(16L);
    int i = hwBlob.getInt32(8L);
    hwBlob = paramHwParcel.readEmbeddedBuffer((i * 40), hwBlob.handle(), 0L, true);
    arrayList.clear();
    for (byte b = 0; b < i; b++) {
      SimApdu simApdu = new SimApdu();
      simApdu.readEmbeddedFromParcel(paramHwParcel, hwBlob, (b * 40));
      arrayList.add(simApdu);
    } 
    return arrayList;
  }
  
  public static final void writeVectorToParcel(HwParcel paramHwParcel, ArrayList<SimApdu> paramArrayList) {
    HwBlob hwBlob1 = new HwBlob(16);
    int i = paramArrayList.size();
    hwBlob1.putInt32(8L, i);
    hwBlob1.putBool(12L, false);
    HwBlob hwBlob2 = new HwBlob(i * 40);
    for (byte b = 0; b < i; b++)
      ((SimApdu)paramArrayList.get(b)).writeEmbeddedToBlob(hwBlob2, (b * 40)); 
    hwBlob1.putBlob(0L, hwBlob2);
    paramHwParcel.writeBuffer(hwBlob1);
  }
  
  public final boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (paramObject == null)
      return false; 
    if (paramObject.getClass() != SimApdu.class)
      return false; 
    paramObject = paramObject;
    return (this.sessionId != ((SimApdu)paramObject).sessionId) ? false : ((this.cla != ((SimApdu)paramObject).cla) ? false : ((this.instruction != ((SimApdu)paramObject).instruction) ? false : ((this.p1 != ((SimApdu)paramObject).p1) ? false : ((this.p2 != ((SimApdu)paramObject).p2) ? false : ((this.p3 != ((SimApdu)paramObject).p3) ? false : (!!HidlSupport.deepEquals(this.data, ((SimApdu)paramObject).data)))))));
  }
  
  public final int hashCode() {
    return Objects.hash(new Object[] { Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.sessionId))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.cla))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.instruction))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.p1))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.p2))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.p3))), Integer.valueOf(HidlSupport.deepHashCode(this.data)) });
  }
  
  public final void readEmbeddedFromParcel(HwParcel paramHwParcel, HwBlob paramHwBlob, long paramLong) {
    this.sessionId = paramHwBlob.getInt32(paramLong + 0L);
    this.cla = paramHwBlob.getInt32(paramLong + 4L);
    this.instruction = paramHwBlob.getInt32(paramLong + 8L);
    this.p1 = paramHwBlob.getInt32(paramLong + 12L);
    this.p2 = paramHwBlob.getInt32(paramLong + 16L);
    this.p3 = paramHwBlob.getInt32(paramLong + 20L);
    String str = paramHwBlob.getString(paramLong + 24L);
    this.data = str;
    paramHwParcel.readEmbeddedBuffer(((str.getBytes()).length + 1), paramHwBlob.handle(), paramLong + 24L + 0L, false);
  }
  
  public final void readFromParcel(HwParcel paramHwParcel) {
    readEmbeddedFromParcel(paramHwParcel, paramHwParcel.readBuffer(40L), 0L);
  }
  
  public final String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("{");
    stringBuilder.append(".sessionId = ");
    stringBuilder.append(this.sessionId);
    stringBuilder.append(", .cla = ");
    stringBuilder.append(this.cla);
    stringBuilder.append(", .instruction = ");
    stringBuilder.append(this.instruction);
    stringBuilder.append(", .p1 = ");
    stringBuilder.append(this.p1);
    stringBuilder.append(", .p2 = ");
    stringBuilder.append(this.p2);
    stringBuilder.append(", .p3 = ");
    stringBuilder.append(this.p3);
    stringBuilder.append(", .data = ");
    stringBuilder.append(this.data);
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public final void writeEmbeddedToBlob(HwBlob paramHwBlob, long paramLong) {
    paramHwBlob.putInt32(0L + paramLong, this.sessionId);
    paramHwBlob.putInt32(4L + paramLong, this.cla);
    paramHwBlob.putInt32(8L + paramLong, this.instruction);
    paramHwBlob.putInt32(12L + paramLong, this.p1);
    paramHwBlob.putInt32(16L + paramLong, this.p2);
    paramHwBlob.putInt32(20L + paramLong, this.p3);
    paramHwBlob.putString(24L + paramLong, this.data);
  }
  
  public final void writeToParcel(HwParcel paramHwParcel) {
    HwBlob hwBlob = new HwBlob(40);
    writeEmbeddedToBlob(hwBlob, 0L);
    paramHwParcel.writeBuffer(hwBlob);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/V1_0/SimApdu.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */