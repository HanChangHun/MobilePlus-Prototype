package android.hardware.radio.V1_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

public final class IccIo {
  public String aid = new String();
  
  public int command = 0;
  
  public String data = new String();
  
  public int fileId = 0;
  
  public int p1 = 0;
  
  public int p2 = 0;
  
  public int p3 = 0;
  
  public String path = new String();
  
  public String pin2 = new String();
  
  public static final ArrayList<IccIo> readVectorFromParcel(HwParcel paramHwParcel) {
    ArrayList<IccIo> arrayList = new ArrayList();
    HwBlob hwBlob = paramHwParcel.readBuffer(16L);
    int i = hwBlob.getInt32(8L);
    hwBlob = paramHwParcel.readEmbeddedBuffer((i * 88), hwBlob.handle(), 0L, true);
    arrayList.clear();
    for (byte b = 0; b < i; b++) {
      IccIo iccIo = new IccIo();
      iccIo.readEmbeddedFromParcel(paramHwParcel, hwBlob, (b * 88));
      arrayList.add(iccIo);
    } 
    return arrayList;
  }
  
  public static final void writeVectorToParcel(HwParcel paramHwParcel, ArrayList<IccIo> paramArrayList) {
    HwBlob hwBlob1 = new HwBlob(16);
    int i = paramArrayList.size();
    hwBlob1.putInt32(8L, i);
    hwBlob1.putBool(12L, false);
    HwBlob hwBlob2 = new HwBlob(i * 88);
    for (byte b = 0; b < i; b++)
      ((IccIo)paramArrayList.get(b)).writeEmbeddedToBlob(hwBlob2, (b * 88)); 
    hwBlob1.putBlob(0L, hwBlob2);
    paramHwParcel.writeBuffer(hwBlob1);
  }
  
  public final boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (paramObject == null)
      return false; 
    if (paramObject.getClass() != IccIo.class)
      return false; 
    paramObject = paramObject;
    return (this.command != ((IccIo)paramObject).command) ? false : ((this.fileId != ((IccIo)paramObject).fileId) ? false : (!HidlSupport.deepEquals(this.path, ((IccIo)paramObject).path) ? false : ((this.p1 != ((IccIo)paramObject).p1) ? false : ((this.p2 != ((IccIo)paramObject).p2) ? false : ((this.p3 != ((IccIo)paramObject).p3) ? false : (!HidlSupport.deepEquals(this.data, ((IccIo)paramObject).data) ? false : (!HidlSupport.deepEquals(this.pin2, ((IccIo)paramObject).pin2) ? false : (!!HidlSupport.deepEquals(this.aid, ((IccIo)paramObject).aid)))))))));
  }
  
  public final int hashCode() {
    return Objects.hash(new Object[] { Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.command))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.fileId))), Integer.valueOf(HidlSupport.deepHashCode(this.path)), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.p1))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.p2))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.p3))), Integer.valueOf(HidlSupport.deepHashCode(this.data)), Integer.valueOf(HidlSupport.deepHashCode(this.pin2)), Integer.valueOf(HidlSupport.deepHashCode(this.aid)) });
  }
  
  public final void readEmbeddedFromParcel(HwParcel paramHwParcel, HwBlob paramHwBlob, long paramLong) {
    this.command = paramHwBlob.getInt32(paramLong + 0L);
    this.fileId = paramHwBlob.getInt32(paramLong + 4L);
    String str = paramHwBlob.getString(paramLong + 8L);
    this.path = str;
    paramHwParcel.readEmbeddedBuffer(((str.getBytes()).length + 1), paramHwBlob.handle(), paramLong + 8L + 0L, false);
    this.p1 = paramHwBlob.getInt32(paramLong + 24L);
    this.p2 = paramHwBlob.getInt32(paramLong + 28L);
    this.p3 = paramHwBlob.getInt32(paramLong + 32L);
    str = paramHwBlob.getString(paramLong + 40L);
    this.data = str;
    paramHwParcel.readEmbeddedBuffer(((str.getBytes()).length + 1), paramHwBlob.handle(), paramLong + 40L + 0L, false);
    str = paramHwBlob.getString(paramLong + 56L);
    this.pin2 = str;
    paramHwParcel.readEmbeddedBuffer(((str.getBytes()).length + 1), paramHwBlob.handle(), paramLong + 56L + 0L, false);
    str = paramHwBlob.getString(paramLong + 72L);
    this.aid = str;
    paramHwParcel.readEmbeddedBuffer(((str.getBytes()).length + 1), paramHwBlob.handle(), paramLong + 72L + 0L, false);
  }
  
  public final void readFromParcel(HwParcel paramHwParcel) {
    readEmbeddedFromParcel(paramHwParcel, paramHwParcel.readBuffer(88L), 0L);
  }
  
  public final String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("{");
    stringBuilder.append(".command = ");
    stringBuilder.append(this.command);
    stringBuilder.append(", .fileId = ");
    stringBuilder.append(this.fileId);
    stringBuilder.append(", .path = ");
    stringBuilder.append(this.path);
    stringBuilder.append(", .p1 = ");
    stringBuilder.append(this.p1);
    stringBuilder.append(", .p2 = ");
    stringBuilder.append(this.p2);
    stringBuilder.append(", .p3 = ");
    stringBuilder.append(this.p3);
    stringBuilder.append(", .data = ");
    stringBuilder.append(this.data);
    stringBuilder.append(", .pin2 = ");
    stringBuilder.append(this.pin2);
    stringBuilder.append(", .aid = ");
    stringBuilder.append(this.aid);
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public final void writeEmbeddedToBlob(HwBlob paramHwBlob, long paramLong) {
    paramHwBlob.putInt32(0L + paramLong, this.command);
    paramHwBlob.putInt32(4L + paramLong, this.fileId);
    paramHwBlob.putString(8L + paramLong, this.path);
    paramHwBlob.putInt32(24L + paramLong, this.p1);
    paramHwBlob.putInt32(28L + paramLong, this.p2);
    paramHwBlob.putInt32(32L + paramLong, this.p3);
    paramHwBlob.putString(40L + paramLong, this.data);
    paramHwBlob.putString(56L + paramLong, this.pin2);
    paramHwBlob.putString(72L + paramLong, this.aid);
  }
  
  public final void writeToParcel(HwParcel paramHwParcel) {
    HwBlob hwBlob = new HwBlob(88);
    writeEmbeddedToBlob(hwBlob, 0L);
    paramHwParcel.writeBuffer(hwBlob);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/V1_0/IccIo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */