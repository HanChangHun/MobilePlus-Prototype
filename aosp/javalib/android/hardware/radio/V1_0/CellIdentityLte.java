package android.hardware.radio.V1_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

public final class CellIdentityLte {
  public int ci = 0;
  
  public int earfcn = 0;
  
  public String mcc = new String();
  
  public String mnc = new String();
  
  public int pci = 0;
  
  public int tac = 0;
  
  public static final ArrayList<CellIdentityLte> readVectorFromParcel(HwParcel paramHwParcel) {
    ArrayList<CellIdentityLte> arrayList = new ArrayList();
    HwBlob hwBlob1 = paramHwParcel.readBuffer(16L);
    int i = hwBlob1.getInt32(8L);
    HwBlob hwBlob2 = paramHwParcel.readEmbeddedBuffer((i * 48), hwBlob1.handle(), 0L, true);
    arrayList.clear();
    for (byte b = 0; b < i; b++) {
      CellIdentityLte cellIdentityLte = new CellIdentityLte();
      cellIdentityLte.readEmbeddedFromParcel(paramHwParcel, hwBlob2, (b * 48));
      arrayList.add(cellIdentityLte);
    } 
    return arrayList;
  }
  
  public static final void writeVectorToParcel(HwParcel paramHwParcel, ArrayList<CellIdentityLte> paramArrayList) {
    HwBlob hwBlob1 = new HwBlob(16);
    int i = paramArrayList.size();
    hwBlob1.putInt32(8L, i);
    hwBlob1.putBool(12L, false);
    HwBlob hwBlob2 = new HwBlob(i * 48);
    for (byte b = 0; b < i; b++)
      ((CellIdentityLte)paramArrayList.get(b)).writeEmbeddedToBlob(hwBlob2, (b * 48)); 
    hwBlob1.putBlob(0L, hwBlob2);
    paramHwParcel.writeBuffer(hwBlob1);
  }
  
  public final boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (paramObject == null)
      return false; 
    if (paramObject.getClass() != CellIdentityLte.class)
      return false; 
    paramObject = paramObject;
    return !HidlSupport.deepEquals(this.mcc, ((CellIdentityLte)paramObject).mcc) ? false : (!HidlSupport.deepEquals(this.mnc, ((CellIdentityLte)paramObject).mnc) ? false : ((this.ci != ((CellIdentityLte)paramObject).ci) ? false : ((this.pci != ((CellIdentityLte)paramObject).pci) ? false : ((this.tac != ((CellIdentityLte)paramObject).tac) ? false : (!(this.earfcn != ((CellIdentityLte)paramObject).earfcn))))));
  }
  
  public final int hashCode() {
    return Objects.hash(new Object[] { Integer.valueOf(HidlSupport.deepHashCode(this.mcc)), Integer.valueOf(HidlSupport.deepHashCode(this.mnc)), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.ci))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.pci))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.tac))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.earfcn))) });
  }
  
  public final void readEmbeddedFromParcel(HwParcel paramHwParcel, HwBlob paramHwBlob, long paramLong) {
    String str = paramHwBlob.getString(paramLong + 0L);
    this.mcc = str;
    paramHwParcel.readEmbeddedBuffer(((str.getBytes()).length + 1), paramHwBlob.handle(), paramLong + 0L + 0L, false);
    str = paramHwBlob.getString(paramLong + 16L);
    this.mnc = str;
    paramHwParcel.readEmbeddedBuffer(((str.getBytes()).length + 1), paramHwBlob.handle(), paramLong + 16L + 0L, false);
    this.ci = paramHwBlob.getInt32(paramLong + 32L);
    this.pci = paramHwBlob.getInt32(paramLong + 36L);
    this.tac = paramHwBlob.getInt32(paramLong + 40L);
    this.earfcn = paramHwBlob.getInt32(paramLong + 44L);
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
    stringBuilder.append(", .ci = ");
    stringBuilder.append(this.ci);
    stringBuilder.append(", .pci = ");
    stringBuilder.append(this.pci);
    stringBuilder.append(", .tac = ");
    stringBuilder.append(this.tac);
    stringBuilder.append(", .earfcn = ");
    stringBuilder.append(this.earfcn);
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public final void writeEmbeddedToBlob(HwBlob paramHwBlob, long paramLong) {
    paramHwBlob.putString(0L + paramLong, this.mcc);
    paramHwBlob.putString(16L + paramLong, this.mnc);
    paramHwBlob.putInt32(32L + paramLong, this.ci);
    paramHwBlob.putInt32(36L + paramLong, this.pci);
    paramHwBlob.putInt32(40L + paramLong, this.tac);
    paramHwBlob.putInt32(44L + paramLong, this.earfcn);
  }
  
  public final void writeToParcel(HwParcel paramHwParcel) {
    HwBlob hwBlob = new HwBlob(48);
    writeEmbeddedToBlob(hwBlob, 0L);
    paramHwParcel.writeBuffer(hwBlob);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/V1_0/CellIdentityLte.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */