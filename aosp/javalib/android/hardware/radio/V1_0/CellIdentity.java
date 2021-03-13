package android.hardware.radio.V1_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

public final class CellIdentity {
  public ArrayList<CellIdentityCdma> cellIdentityCdma = new ArrayList<>();
  
  public ArrayList<CellIdentityGsm> cellIdentityGsm = new ArrayList<>();
  
  public ArrayList<CellIdentityLte> cellIdentityLte = new ArrayList<>();
  
  public ArrayList<CellIdentityTdscdma> cellIdentityTdscdma = new ArrayList<>();
  
  public ArrayList<CellIdentityWcdma> cellIdentityWcdma = new ArrayList<>();
  
  public int cellInfoType = 0;
  
  public static final ArrayList<CellIdentity> readVectorFromParcel(HwParcel paramHwParcel) {
    ArrayList<CellIdentity> arrayList = new ArrayList();
    HwBlob hwBlob = paramHwParcel.readBuffer(16L);
    int i = hwBlob.getInt32(8L);
    hwBlob = paramHwParcel.readEmbeddedBuffer((i * 88), hwBlob.handle(), 0L, true);
    arrayList.clear();
    for (byte b = 0; b < i; b++) {
      CellIdentity cellIdentity = new CellIdentity();
      cellIdentity.readEmbeddedFromParcel(paramHwParcel, hwBlob, (b * 88));
      arrayList.add(cellIdentity);
    } 
    return arrayList;
  }
  
  public static final void writeVectorToParcel(HwParcel paramHwParcel, ArrayList<CellIdentity> paramArrayList) {
    HwBlob hwBlob1 = new HwBlob(16);
    int i = paramArrayList.size();
    hwBlob1.putInt32(8L, i);
    hwBlob1.putBool(12L, false);
    HwBlob hwBlob2 = new HwBlob(i * 88);
    for (byte b = 0; b < i; b++)
      ((CellIdentity)paramArrayList.get(b)).writeEmbeddedToBlob(hwBlob2, (b * 88)); 
    hwBlob1.putBlob(0L, hwBlob2);
    paramHwParcel.writeBuffer(hwBlob1);
  }
  
  public final boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (paramObject == null)
      return false; 
    if (paramObject.getClass() != CellIdentity.class)
      return false; 
    paramObject = paramObject;
    return (this.cellInfoType != ((CellIdentity)paramObject).cellInfoType) ? false : (!HidlSupport.deepEquals(this.cellIdentityGsm, ((CellIdentity)paramObject).cellIdentityGsm) ? false : (!HidlSupport.deepEquals(this.cellIdentityWcdma, ((CellIdentity)paramObject).cellIdentityWcdma) ? false : (!HidlSupport.deepEquals(this.cellIdentityCdma, ((CellIdentity)paramObject).cellIdentityCdma) ? false : (!HidlSupport.deepEquals(this.cellIdentityLte, ((CellIdentity)paramObject).cellIdentityLte) ? false : (!!HidlSupport.deepEquals(this.cellIdentityTdscdma, ((CellIdentity)paramObject).cellIdentityTdscdma))))));
  }
  
  public final int hashCode() {
    return Objects.hash(new Object[] { Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.cellInfoType))), Integer.valueOf(HidlSupport.deepHashCode(this.cellIdentityGsm)), Integer.valueOf(HidlSupport.deepHashCode(this.cellIdentityWcdma)), Integer.valueOf(HidlSupport.deepHashCode(this.cellIdentityCdma)), Integer.valueOf(HidlSupport.deepHashCode(this.cellIdentityLte)), Integer.valueOf(HidlSupport.deepHashCode(this.cellIdentityTdscdma)) });
  }
  
  public final void readEmbeddedFromParcel(HwParcel paramHwParcel, HwBlob paramHwBlob, long paramLong) {
    this.cellInfoType = paramHwBlob.getInt32(paramLong + 0L);
    int i = paramHwBlob.getInt32(paramLong + 8L + 8L);
    HwBlob hwBlob1 = paramHwParcel.readEmbeddedBuffer((i * 48), paramHwBlob.handle(), paramLong + 8L + 0L, true);
    this.cellIdentityGsm.clear();
    byte b;
    for (b = 0; b < i; b++) {
      CellIdentityGsm cellIdentityGsm = new CellIdentityGsm();
      cellIdentityGsm.readEmbeddedFromParcel(paramHwParcel, hwBlob1, (b * 48));
      this.cellIdentityGsm.add(cellIdentityGsm);
    } 
    i = paramHwBlob.getInt32(paramLong + 24L + 8L);
    hwBlob1 = paramHwParcel.readEmbeddedBuffer((i * 48), paramHwBlob.handle(), paramLong + 24L + 0L, true);
    this.cellIdentityWcdma.clear();
    for (b = 0; b < i; b++) {
      CellIdentityWcdma cellIdentityWcdma = new CellIdentityWcdma();
      cellIdentityWcdma.readEmbeddedFromParcel(paramHwParcel, hwBlob1, (b * 48));
      this.cellIdentityWcdma.add(cellIdentityWcdma);
    } 
    i = paramHwBlob.getInt32(paramLong + 40L + 8L);
    HwBlob hwBlob2 = paramHwParcel.readEmbeddedBuffer((i * 20), paramHwBlob.handle(), paramLong + 40L + 0L, true);
    this.cellIdentityCdma.clear();
    for (b = 0; b < i; b++) {
      CellIdentityCdma cellIdentityCdma = new CellIdentityCdma();
      cellIdentityCdma.readEmbeddedFromParcel(paramHwParcel, hwBlob2, (b * 20));
      this.cellIdentityCdma.add(cellIdentityCdma);
    } 
    i = paramHwBlob.getInt32(paramLong + 56L + 8L);
    hwBlob1 = paramHwParcel.readEmbeddedBuffer((i * 48), paramHwBlob.handle(), paramLong + 56L + 0L, true);
    this.cellIdentityLte.clear();
    for (b = 0; b < i; b++) {
      CellIdentityLte cellIdentityLte = new CellIdentityLte();
      cellIdentityLte.readEmbeddedFromParcel(paramHwParcel, hwBlob1, (b * 48));
      this.cellIdentityLte.add(cellIdentityLte);
    } 
    i = paramHwBlob.getInt32(paramLong + 72L + 8L);
    hwBlob1 = paramHwParcel.readEmbeddedBuffer((i * 48), paramHwBlob.handle(), paramLong + 72L + 0L, true);
    this.cellIdentityTdscdma.clear();
    for (b = 0; b < i; b++) {
      CellIdentityTdscdma cellIdentityTdscdma = new CellIdentityTdscdma();
      cellIdentityTdscdma.readEmbeddedFromParcel(paramHwParcel, hwBlob1, (b * 48));
      this.cellIdentityTdscdma.add(cellIdentityTdscdma);
    } 
  }
  
  public final void readFromParcel(HwParcel paramHwParcel) {
    readEmbeddedFromParcel(paramHwParcel, paramHwParcel.readBuffer(88L), 0L);
  }
  
  public final String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("{");
    stringBuilder.append(".cellInfoType = ");
    stringBuilder.append(CellInfoType.toString(this.cellInfoType));
    stringBuilder.append(", .cellIdentityGsm = ");
    stringBuilder.append(this.cellIdentityGsm);
    stringBuilder.append(", .cellIdentityWcdma = ");
    stringBuilder.append(this.cellIdentityWcdma);
    stringBuilder.append(", .cellIdentityCdma = ");
    stringBuilder.append(this.cellIdentityCdma);
    stringBuilder.append(", .cellIdentityLte = ");
    stringBuilder.append(this.cellIdentityLte);
    stringBuilder.append(", .cellIdentityTdscdma = ");
    stringBuilder.append(this.cellIdentityTdscdma);
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public final void writeEmbeddedToBlob(HwBlob paramHwBlob, long paramLong) {
    paramHwBlob.putInt32(paramLong + 0L, this.cellInfoType);
    int i = this.cellIdentityGsm.size();
    paramHwBlob.putInt32(paramLong + 8L + 8L, i);
    paramHwBlob.putBool(paramLong + 8L + 12L, false);
    HwBlob hwBlob = new HwBlob(i * 48);
    byte b;
    for (b = 0; b < i; b++)
      ((CellIdentityGsm)this.cellIdentityGsm.get(b)).writeEmbeddedToBlob(hwBlob, (b * 48)); 
    paramHwBlob.putBlob(paramLong + 8L + 0L, hwBlob);
    i = this.cellIdentityWcdma.size();
    paramHwBlob.putInt32(paramLong + 24L + 8L, i);
    paramHwBlob.putBool(paramLong + 24L + 12L, false);
    hwBlob = new HwBlob(i * 48);
    for (b = 0; b < i; b++)
      ((CellIdentityWcdma)this.cellIdentityWcdma.get(b)).writeEmbeddedToBlob(hwBlob, (b * 48)); 
    paramHwBlob.putBlob(paramLong + 24L + 0L, hwBlob);
    i = this.cellIdentityCdma.size();
    paramHwBlob.putInt32(paramLong + 40L + 8L, i);
    paramHwBlob.putBool(paramLong + 40L + 12L, false);
    hwBlob = new HwBlob(i * 20);
    for (b = 0; b < i; b++)
      ((CellIdentityCdma)this.cellIdentityCdma.get(b)).writeEmbeddedToBlob(hwBlob, (b * 20)); 
    paramHwBlob.putBlob(paramLong + 40L + 0L, hwBlob);
    i = this.cellIdentityLte.size();
    paramHwBlob.putInt32(paramLong + 56L + 8L, i);
    paramHwBlob.putBool(paramLong + 56L + 12L, false);
    hwBlob = new HwBlob(i * 48);
    for (b = 0; b < i; b++)
      ((CellIdentityLte)this.cellIdentityLte.get(b)).writeEmbeddedToBlob(hwBlob, (b * 48)); 
    paramHwBlob.putBlob(paramLong + 56L + 0L, hwBlob);
    i = this.cellIdentityTdscdma.size();
    paramHwBlob.putInt32(paramLong + 72L + 8L, i);
    paramHwBlob.putBool(paramLong + 72L + 12L, false);
    hwBlob = new HwBlob(i * 48);
    for (b = 0; b < i; b++)
      ((CellIdentityTdscdma)this.cellIdentityTdscdma.get(b)).writeEmbeddedToBlob(hwBlob, (b * 48)); 
    paramHwBlob.putBlob(paramLong + 72L + 0L, hwBlob);
  }
  
  public final void writeToParcel(HwParcel paramHwParcel) {
    HwBlob hwBlob = new HwBlob(88);
    writeEmbeddedToBlob(hwBlob, 0L);
    paramHwParcel.writeBuffer(hwBlob);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/V1_0/CellIdentity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */