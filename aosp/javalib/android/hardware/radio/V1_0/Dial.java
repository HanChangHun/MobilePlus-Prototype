package android.hardware.radio.V1_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

public final class Dial {
  public String address = new String();
  
  public int clir = 0;
  
  public ArrayList<UusInfo> uusInfo = new ArrayList<>();
  
  public static final ArrayList<Dial> readVectorFromParcel(HwParcel paramHwParcel) {
    ArrayList<Dial> arrayList = new ArrayList();
    HwBlob hwBlob1 = paramHwParcel.readBuffer(16L);
    int i = hwBlob1.getInt32(8L);
    HwBlob hwBlob2 = paramHwParcel.readEmbeddedBuffer((i * 40), hwBlob1.handle(), 0L, true);
    arrayList.clear();
    for (byte b = 0; b < i; b++) {
      Dial dial = new Dial();
      dial.readEmbeddedFromParcel(paramHwParcel, hwBlob2, (b * 40));
      arrayList.add(dial);
    } 
    return arrayList;
  }
  
  public static final void writeVectorToParcel(HwParcel paramHwParcel, ArrayList<Dial> paramArrayList) {
    HwBlob hwBlob1 = new HwBlob(16);
    int i = paramArrayList.size();
    hwBlob1.putInt32(8L, i);
    hwBlob1.putBool(12L, false);
    HwBlob hwBlob2 = new HwBlob(i * 40);
    for (byte b = 0; b < i; b++)
      ((Dial)paramArrayList.get(b)).writeEmbeddedToBlob(hwBlob2, (b * 40)); 
    hwBlob1.putBlob(0L, hwBlob2);
    paramHwParcel.writeBuffer(hwBlob1);
  }
  
  public final boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (paramObject == null)
      return false; 
    if (paramObject.getClass() != Dial.class)
      return false; 
    paramObject = paramObject;
    return !HidlSupport.deepEquals(this.address, ((Dial)paramObject).address) ? false : ((this.clir != ((Dial)paramObject).clir) ? false : (!!HidlSupport.deepEquals(this.uusInfo, ((Dial)paramObject).uusInfo)));
  }
  
  public final int hashCode() {
    return Objects.hash(new Object[] { Integer.valueOf(HidlSupport.deepHashCode(this.address)), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.clir))), Integer.valueOf(HidlSupport.deepHashCode(this.uusInfo)) });
  }
  
  public final void readEmbeddedFromParcel(HwParcel paramHwParcel, HwBlob paramHwBlob, long paramLong) {
    String str = paramHwBlob.getString(paramLong + 0L);
    this.address = str;
    paramHwParcel.readEmbeddedBuffer(((str.getBytes()).length + 1), paramHwBlob.handle(), paramLong + 0L + 0L, false);
    this.clir = paramHwBlob.getInt32(paramLong + 16L);
    int i = paramHwBlob.getInt32(paramLong + 24L + 8L);
    paramHwBlob = paramHwParcel.readEmbeddedBuffer((i * 24), paramHwBlob.handle(), paramLong + 24L + 0L, true);
    this.uusInfo.clear();
    for (byte b = 0; b < i; b++) {
      UusInfo uusInfo = new UusInfo();
      uusInfo.readEmbeddedFromParcel(paramHwParcel, paramHwBlob, (b * 24));
      this.uusInfo.add(uusInfo);
    } 
  }
  
  public final void readFromParcel(HwParcel paramHwParcel) {
    readEmbeddedFromParcel(paramHwParcel, paramHwParcel.readBuffer(40L), 0L);
  }
  
  public final String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("{");
    stringBuilder.append(".address = ");
    stringBuilder.append(this.address);
    stringBuilder.append(", .clir = ");
    stringBuilder.append(Clir.toString(this.clir));
    stringBuilder.append(", .uusInfo = ");
    stringBuilder.append(this.uusInfo);
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public final void writeEmbeddedToBlob(HwBlob paramHwBlob, long paramLong) {
    paramHwBlob.putString(paramLong + 0L, this.address);
    paramHwBlob.putInt32(16L + paramLong, this.clir);
    int i = this.uusInfo.size();
    paramHwBlob.putInt32(paramLong + 24L + 8L, i);
    paramHwBlob.putBool(paramLong + 24L + 12L, false);
    HwBlob hwBlob = new HwBlob(i * 24);
    for (byte b = 0; b < i; b++)
      ((UusInfo)this.uusInfo.get(b)).writeEmbeddedToBlob(hwBlob, (b * 24)); 
    paramHwBlob.putBlob(24L + paramLong + 0L, hwBlob);
  }
  
  public final void writeToParcel(HwParcel paramHwParcel) {
    HwBlob hwBlob = new HwBlob(40);
    writeEmbeddedToBlob(hwBlob, 0L);
    paramHwParcel.writeBuffer(hwBlob);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/V1_0/Dial.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */