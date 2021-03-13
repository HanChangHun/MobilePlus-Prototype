package android.hardware.radio.V1_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

public final class CellInfo {
  public ArrayList<CellInfoCdma> cdma = new ArrayList<>();
  
  public int cellInfoType = 0;
  
  public ArrayList<CellInfoGsm> gsm = new ArrayList<>();
  
  public ArrayList<CellInfoLte> lte = new ArrayList<>();
  
  public boolean registered = false;
  
  public ArrayList<CellInfoTdscdma> tdscdma = new ArrayList<>();
  
  public long timeStamp = 0L;
  
  public int timeStampType = 0;
  
  public ArrayList<CellInfoWcdma> wcdma = new ArrayList<>();
  
  public static final ArrayList<CellInfo> readVectorFromParcel(HwParcel paramHwParcel) {
    ArrayList<CellInfo> arrayList = new ArrayList();
    HwBlob hwBlob = paramHwParcel.readBuffer(16L);
    int i = hwBlob.getInt32(8L);
    hwBlob = paramHwParcel.readEmbeddedBuffer((i * 104), hwBlob.handle(), 0L, true);
    arrayList.clear();
    for (byte b = 0; b < i; b++) {
      CellInfo cellInfo = new CellInfo();
      cellInfo.readEmbeddedFromParcel(paramHwParcel, hwBlob, (b * 104));
      arrayList.add(cellInfo);
    } 
    return arrayList;
  }
  
  public static final void writeVectorToParcel(HwParcel paramHwParcel, ArrayList<CellInfo> paramArrayList) {
    HwBlob hwBlob1 = new HwBlob(16);
    int i = paramArrayList.size();
    hwBlob1.putInt32(8L, i);
    hwBlob1.putBool(12L, false);
    HwBlob hwBlob2 = new HwBlob(i * 104);
    for (byte b = 0; b < i; b++)
      ((CellInfo)paramArrayList.get(b)).writeEmbeddedToBlob(hwBlob2, (b * 104)); 
    hwBlob1.putBlob(0L, hwBlob2);
    paramHwParcel.writeBuffer(hwBlob1);
  }
  
  public final boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (paramObject == null)
      return false; 
    if (paramObject.getClass() != CellInfo.class)
      return false; 
    paramObject = paramObject;
    return (this.cellInfoType != ((CellInfo)paramObject).cellInfoType) ? false : ((this.registered != ((CellInfo)paramObject).registered) ? false : ((this.timeStampType != ((CellInfo)paramObject).timeStampType) ? false : ((this.timeStamp != ((CellInfo)paramObject).timeStamp) ? false : (!HidlSupport.deepEquals(this.gsm, ((CellInfo)paramObject).gsm) ? false : (!HidlSupport.deepEquals(this.cdma, ((CellInfo)paramObject).cdma) ? false : (!HidlSupport.deepEquals(this.lte, ((CellInfo)paramObject).lte) ? false : (!HidlSupport.deepEquals(this.wcdma, ((CellInfo)paramObject).wcdma) ? false : (!!HidlSupport.deepEquals(this.tdscdma, ((CellInfo)paramObject).tdscdma)))))))));
  }
  
  public final int hashCode() {
    return Objects.hash(new Object[] { Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.cellInfoType))), Integer.valueOf(HidlSupport.deepHashCode(Boolean.valueOf(this.registered))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.timeStampType))), Integer.valueOf(HidlSupport.deepHashCode(Long.valueOf(this.timeStamp))), Integer.valueOf(HidlSupport.deepHashCode(this.gsm)), Integer.valueOf(HidlSupport.deepHashCode(this.cdma)), Integer.valueOf(HidlSupport.deepHashCode(this.lte)), Integer.valueOf(HidlSupport.deepHashCode(this.wcdma)), Integer.valueOf(HidlSupport.deepHashCode(this.tdscdma)) });
  }
  
  public final void readEmbeddedFromParcel(HwParcel paramHwParcel, HwBlob paramHwBlob, long paramLong) {
    this.cellInfoType = paramHwBlob.getInt32(paramLong + 0L);
    this.registered = paramHwBlob.getBool(paramLong + 4L);
    this.timeStampType = paramHwBlob.getInt32(paramLong + 8L);
    this.timeStamp = paramHwBlob.getInt64(paramLong + 16L);
    int i = paramHwBlob.getInt32(paramLong + 24L + 8L);
    HwBlob hwBlob1 = paramHwParcel.readEmbeddedBuffer((i * 64), paramHwBlob.handle(), paramLong + 24L + 0L, true);
    this.gsm.clear();
    byte b;
    for (b = 0; b < i; b++) {
      CellInfoGsm cellInfoGsm = new CellInfoGsm();
      cellInfoGsm.readEmbeddedFromParcel(paramHwParcel, hwBlob1, (b * 64));
      this.gsm.add(cellInfoGsm);
    } 
    i = paramHwBlob.getInt32(paramLong + 40L + 8L);
    hwBlob1 = paramHwParcel.readEmbeddedBuffer((i * 40), paramHwBlob.handle(), paramLong + 40L + 0L, true);
    this.cdma.clear();
    for (b = 0; b < i; b++) {
      CellInfoCdma cellInfoCdma = new CellInfoCdma();
      cellInfoCdma.readEmbeddedFromParcel(paramHwParcel, hwBlob1, (b * 40));
      this.cdma.add(cellInfoCdma);
    } 
    i = paramHwBlob.getInt32(paramLong + 56L + 8L);
    hwBlob1 = paramHwParcel.readEmbeddedBuffer((i * 72), paramHwBlob.handle(), paramLong + 56L + 0L, true);
    this.lte.clear();
    for (b = 0; b < i; b++) {
      CellInfoLte cellInfoLte = new CellInfoLte();
      cellInfoLte.readEmbeddedFromParcel(paramHwParcel, hwBlob1, (b * 72));
      this.lte.add(cellInfoLte);
    } 
    i = paramHwBlob.getInt32(paramLong + 72L + 8L);
    HwBlob hwBlob2 = paramHwParcel.readEmbeddedBuffer((i * 56), paramHwBlob.handle(), paramLong + 72L + 0L, true);
    this.wcdma.clear();
    for (b = 0; b < i; b++) {
      CellInfoWcdma cellInfoWcdma = new CellInfoWcdma();
      cellInfoWcdma.readEmbeddedFromParcel(paramHwParcel, hwBlob2, (b * 56));
      this.wcdma.add(cellInfoWcdma);
    } 
    i = paramHwBlob.getInt32(paramLong + 88L + 8L);
    hwBlob1 = paramHwParcel.readEmbeddedBuffer((i * 56), paramHwBlob.handle(), paramLong + 88L + 0L, true);
    this.tdscdma.clear();
    for (b = 0; b < i; b++) {
      CellInfoTdscdma cellInfoTdscdma = new CellInfoTdscdma();
      cellInfoTdscdma.readEmbeddedFromParcel(paramHwParcel, hwBlob1, (b * 56));
      this.tdscdma.add(cellInfoTdscdma);
    } 
  }
  
  public final void readFromParcel(HwParcel paramHwParcel) {
    readEmbeddedFromParcel(paramHwParcel, paramHwParcel.readBuffer(104L), 0L);
  }
  
  public final String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("{");
    stringBuilder.append(".cellInfoType = ");
    stringBuilder.append(CellInfoType.toString(this.cellInfoType));
    stringBuilder.append(", .registered = ");
    stringBuilder.append(this.registered);
    stringBuilder.append(", .timeStampType = ");
    stringBuilder.append(TimeStampType.toString(this.timeStampType));
    stringBuilder.append(", .timeStamp = ");
    stringBuilder.append(this.timeStamp);
    stringBuilder.append(", .gsm = ");
    stringBuilder.append(this.gsm);
    stringBuilder.append(", .cdma = ");
    stringBuilder.append(this.cdma);
    stringBuilder.append(", .lte = ");
    stringBuilder.append(this.lte);
    stringBuilder.append(", .wcdma = ");
    stringBuilder.append(this.wcdma);
    stringBuilder.append(", .tdscdma = ");
    stringBuilder.append(this.tdscdma);
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public final void writeEmbeddedToBlob(HwBlob paramHwBlob, long paramLong) {
    paramHwBlob.putInt32(paramLong + 0L, this.cellInfoType);
    paramHwBlob.putBool(paramLong + 4L, this.registered);
    paramHwBlob.putInt32(paramLong + 8L, this.timeStampType);
    paramHwBlob.putInt64(paramLong + 16L, this.timeStamp);
    int i = this.gsm.size();
    paramHwBlob.putInt32(paramLong + 24L + 8L, i);
    paramHwBlob.putBool(paramLong + 24L + 12L, false);
    HwBlob hwBlob = new HwBlob(i * 64);
    byte b;
    for (b = 0; b < i; b++)
      ((CellInfoGsm)this.gsm.get(b)).writeEmbeddedToBlob(hwBlob, (b * 64)); 
    paramHwBlob.putBlob(paramLong + 24L + 0L, hwBlob);
    i = this.cdma.size();
    paramHwBlob.putInt32(paramLong + 40L + 8L, i);
    paramHwBlob.putBool(paramLong + 40L + 12L, false);
    hwBlob = new HwBlob(i * 40);
    for (b = 0; b < i; b++)
      ((CellInfoCdma)this.cdma.get(b)).writeEmbeddedToBlob(hwBlob, (b * 40)); 
    paramHwBlob.putBlob(paramLong + 40L + 0L, hwBlob);
    i = this.lte.size();
    paramHwBlob.putInt32(paramLong + 56L + 8L, i);
    paramHwBlob.putBool(paramLong + 56L + 12L, false);
    hwBlob = new HwBlob(i * 72);
    for (b = 0; b < i; b++)
      ((CellInfoLte)this.lte.get(b)).writeEmbeddedToBlob(hwBlob, (b * 72)); 
    paramHwBlob.putBlob(paramLong + 56L + 0L, hwBlob);
    i = this.wcdma.size();
    paramHwBlob.putInt32(paramLong + 72L + 8L, i);
    paramHwBlob.putBool(paramLong + 72L + 12L, false);
    hwBlob = new HwBlob(i * 56);
    for (b = 0; b < i; b++)
      ((CellInfoWcdma)this.wcdma.get(b)).writeEmbeddedToBlob(hwBlob, (b * 56)); 
    paramHwBlob.putBlob(paramLong + 72L + 0L, hwBlob);
    i = this.tdscdma.size();
    paramHwBlob.putInt32(paramLong + 88L + 8L, i);
    paramHwBlob.putBool(paramLong + 88L + 12L, false);
    hwBlob = new HwBlob(i * 56);
    for (b = 0; b < i; b++)
      ((CellInfoTdscdma)this.tdscdma.get(b)).writeEmbeddedToBlob(hwBlob, (b * 56)); 
    paramHwBlob.putBlob(paramLong + 88L + 0L, hwBlob);
  }
  
  public final void writeToParcel(HwParcel paramHwParcel) {
    HwBlob hwBlob = new HwBlob(104);
    writeEmbeddedToBlob(hwBlob, 0L);
    paramHwParcel.writeBuffer(hwBlob);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/V1_0/CellInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */