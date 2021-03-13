package android.hardware.radio.V1_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

public final class CellIdentityGsm {
  public int arfcn = 0;
  
  public byte bsic = (byte)0;
  
  public int cid = 0;
  
  public int lac = 0;
  
  public String mcc = new String();
  
  public String mnc = new String();
  
  public static final ArrayList<CellIdentityGsm> readVectorFromParcel(HwParcel paramHwParcel) {
    ArrayList<CellIdentityGsm> arrayList = new ArrayList();
    HwBlob hwBlob1 = paramHwParcel.readBuffer(16L);
    int i = hwBlob1.getInt32(8L);
    HwBlob hwBlob2 = paramHwParcel.readEmbeddedBuffer((i * 48), hwBlob1.handle(), 0L, true);
    arrayList.clear();
    for (byte b = 0; b < i; b++) {
      CellIdentityGsm cellIdentityGsm = new CellIdentityGsm();
      cellIdentityGsm.readEmbeddedFromParcel(paramHwParcel, hwBlob2, (b * 48));
      arrayList.add(cellIdentityGsm);
    } 
    return arrayList;
  }
  
  public static final void writeVectorToParcel(HwParcel paramHwParcel, ArrayList<CellIdentityGsm> paramArrayList) {
    HwBlob hwBlob1 = new HwBlob(16);
    int i = paramArrayList.size();
    hwBlob1.putInt32(8L, i);
    hwBlob1.putBool(12L, false);
    HwBlob hwBlob2 = new HwBlob(i * 48);
    for (byte b = 0; b < i; b++)
      ((CellIdentityGsm)paramArrayList.get(b)).writeEmbeddedToBlob(hwBlob2, (b * 48)); 
    hwBlob1.putBlob(0L, hwBlob2);
    paramHwParcel.writeBuffer(hwBlob1);
  }
  
  public final boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (paramObject == null)
      return false; 
    if (paramObject.getClass() != CellIdentityGsm.class)
      return false; 
    paramObject = paramObject;
    return !HidlSupport.deepEquals(this.mcc, ((CellIdentityGsm)paramObject).mcc) ? false : (!HidlSupport.deepEquals(this.mnc, ((CellIdentityGsm)paramObject).mnc) ? false : ((this.lac != ((CellIdentityGsm)paramObject).lac) ? false : ((this.cid != ((CellIdentityGsm)paramObject).cid) ? false : ((this.arfcn != ((CellIdentityGsm)paramObject).arfcn) ? false : (!(this.bsic != ((CellIdentityGsm)paramObject).bsic))))));
  }
  
  public final int hashCode() {
    return Objects.hash(new Object[] { Integer.valueOf(HidlSupport.deepHashCode(this.mcc)), Integer.valueOf(HidlSupport.deepHashCode(this.mnc)), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.lac))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.cid))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.arfcn))), Integer.valueOf(HidlSupport.deepHashCode(Byte.valueOf(this.bsic))) });
  }
  
  public final void readEmbeddedFromParcel(HwParcel paramHwParcel, HwBlob paramHwBlob, long paramLong) {
    String str = paramHwBlob.getString(paramLong + 0L);
    this.mcc = str;
    paramHwParcel.readEmbeddedBuffer(((str.getBytes()).length + 1), paramHwBlob.handle(), paramLong + 0L + 0L, false);
    str = paramHwBlob.getString(paramLong + 16L);
    this.mnc = str;
    paramHwParcel.readEmbeddedBuffer(((str.getBytes()).length + 1), paramHwBlob.handle(), paramLong + 16L + 0L, false);
    this.lac = paramHwBlob.getInt32(paramLong + 32L);
    this.cid = paramHwBlob.getInt32(paramLong + 36L);
    this.arfcn = paramHwBlob.getInt32(paramLong + 40L);
    this.bsic = paramHwBlob.getInt8(paramLong + 44L);
  }
  
  public final void readFromParcel(HwParcel paramHwParcel) {
    readEmbeddedFromParcel(paramHwParcel, paramHwParcel.readBuffer(48L), 0L);
  }
  
  public final String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("{");
    stringBuilder.append(".mcc = ");
    stringBuilder.append(this.mcc);
    stringBuilder.append(", .mnc = ");
    stringBuilder.append(this.mnc);
    stringBuilder.append(", .lac = ");
    stringBuilder.append(this.lac);
    stringBuilder.append(", .cid = ");
    stringBuilder.append(this.cid);
    stringBuilder.append(", .arfcn = ");
    stringBuilder.append(this.arfcn);
    stringBuilder.append(", .bsic = ");
    stringBuilder.append(this.bsic);
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public final void writeEmbeddedToBlob(HwBlob paramHwBlob, long paramLong) {
    paramHwBlob.putString(0L + paramLong, this.mcc);
    paramHwBlob.putString(16L + paramLong, this.mnc);
    paramHwBlob.putInt32(32L + paramLong, this.lac);
    paramHwBlob.putInt32(36L + paramLong, this.cid);
    paramHwBlob.putInt32(40L + paramLong, this.arfcn);
    paramHwBlob.putInt8(44L + paramLong, this.bsic);
  }
  
  public final void writeToParcel(HwParcel paramHwParcel) {
    HwBlob hwBlob = new HwBlob(48);
    writeEmbeddedToBlob(hwBlob, 0L);
    paramHwParcel.writeBuffer(hwBlob);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/V1_0/CellIdentityGsm.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */