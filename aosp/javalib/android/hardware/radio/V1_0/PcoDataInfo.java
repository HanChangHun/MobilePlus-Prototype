package android.hardware.radio.V1_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

public final class PcoDataInfo {
  public String bearerProto = new String();
  
  public int cid = 0;
  
  public ArrayList<Byte> contents = new ArrayList<>();
  
  public int pcoId = 0;
  
  public static final ArrayList<PcoDataInfo> readVectorFromParcel(HwParcel paramHwParcel) {
    ArrayList<PcoDataInfo> arrayList = new ArrayList();
    HwBlob hwBlob = paramHwParcel.readBuffer(16L);
    int i = hwBlob.getInt32(8L);
    hwBlob = paramHwParcel.readEmbeddedBuffer((i * 48), hwBlob.handle(), 0L, true);
    arrayList.clear();
    for (byte b = 0; b < i; b++) {
      PcoDataInfo pcoDataInfo = new PcoDataInfo();
      pcoDataInfo.readEmbeddedFromParcel(paramHwParcel, hwBlob, (b * 48));
      arrayList.add(pcoDataInfo);
    } 
    return arrayList;
  }
  
  public static final void writeVectorToParcel(HwParcel paramHwParcel, ArrayList<PcoDataInfo> paramArrayList) {
    HwBlob hwBlob1 = new HwBlob(16);
    int i = paramArrayList.size();
    hwBlob1.putInt32(8L, i);
    hwBlob1.putBool(12L, false);
    HwBlob hwBlob2 = new HwBlob(i * 48);
    for (byte b = 0; b < i; b++)
      ((PcoDataInfo)paramArrayList.get(b)).writeEmbeddedToBlob(hwBlob2, (b * 48)); 
    hwBlob1.putBlob(0L, hwBlob2);
    paramHwParcel.writeBuffer(hwBlob1);
  }
  
  public final boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (paramObject == null)
      return false; 
    if (paramObject.getClass() != PcoDataInfo.class)
      return false; 
    paramObject = paramObject;
    return (this.cid != ((PcoDataInfo)paramObject).cid) ? false : (!HidlSupport.deepEquals(this.bearerProto, ((PcoDataInfo)paramObject).bearerProto) ? false : ((this.pcoId != ((PcoDataInfo)paramObject).pcoId) ? false : (!!HidlSupport.deepEquals(this.contents, ((PcoDataInfo)paramObject).contents))));
  }
  
  public final int hashCode() {
    return Objects.hash(new Object[] { Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.cid))), Integer.valueOf(HidlSupport.deepHashCode(this.bearerProto)), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.pcoId))), Integer.valueOf(HidlSupport.deepHashCode(this.contents)) });
  }
  
  public final void readEmbeddedFromParcel(HwParcel paramHwParcel, HwBlob paramHwBlob, long paramLong) {
    this.cid = paramHwBlob.getInt32(paramLong + 0L);
    String str = paramHwBlob.getString(paramLong + 8L);
    this.bearerProto = str;
    paramHwParcel.readEmbeddedBuffer(((str.getBytes()).length + 1), paramHwBlob.handle(), paramLong + 8L + 0L, false);
    this.pcoId = paramHwBlob.getInt32(paramLong + 24L);
    int i = paramHwBlob.getInt32(paramLong + 32L + 8L);
    HwBlob hwBlob = paramHwParcel.readEmbeddedBuffer((i * 1), paramHwBlob.handle(), paramLong + 32L + 0L, true);
    this.contents.clear();
    for (byte b = 0; b < i; b++) {
      byte b1 = hwBlob.getInt8((b * 1));
      this.contents.add(Byte.valueOf(b1));
    } 
  }
  
  public final void readFromParcel(HwParcel paramHwParcel) {
    readEmbeddedFromParcel(paramHwParcel, paramHwParcel.readBuffer(48L), 0L);
  }
  
  public final String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("{");
    stringBuilder.append(".cid = ");
    stringBuilder.append(this.cid);
    stringBuilder.append(", .bearerProto = ");
    stringBuilder.append(this.bearerProto);
    stringBuilder.append(", .pcoId = ");
    stringBuilder.append(this.pcoId);
    stringBuilder.append(", .contents = ");
    stringBuilder.append(this.contents);
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public final void writeEmbeddedToBlob(HwBlob paramHwBlob, long paramLong) {
    paramHwBlob.putInt32(paramLong + 0L, this.cid);
    paramHwBlob.putString(paramLong + 8L, this.bearerProto);
    paramHwBlob.putInt32(24L + paramLong, this.pcoId);
    int i = this.contents.size();
    paramHwBlob.putInt32(paramLong + 32L + 8L, i);
    paramHwBlob.putBool(paramLong + 32L + 12L, false);
    HwBlob hwBlob = new HwBlob(i * 1);
    for (byte b = 0; b < i; b++)
      hwBlob.putInt8((b * 1), ((Byte)this.contents.get(b)).byteValue()); 
    paramHwBlob.putBlob(32L + paramLong + 0L, hwBlob);
  }
  
  public final void writeToParcel(HwParcel paramHwParcel) {
    HwBlob hwBlob = new HwBlob(48);
    writeEmbeddedToBlob(hwBlob, 0L);
    paramHwParcel.writeBuffer(hwBlob);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/V1_0/PcoDataInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */