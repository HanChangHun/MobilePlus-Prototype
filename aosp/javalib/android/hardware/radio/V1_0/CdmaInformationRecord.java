package android.hardware.radio.V1_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

public final class CdmaInformationRecord {
  public ArrayList<CdmaT53AudioControlInfoRecord> audioCtrl = new ArrayList<>();
  
  public ArrayList<CdmaT53ClirInfoRecord> clir = new ArrayList<>();
  
  public ArrayList<CdmaDisplayInfoRecord> display = new ArrayList<>();
  
  public ArrayList<CdmaLineControlInfoRecord> lineCtrl = new ArrayList<>();
  
  public int name = 0;
  
  public ArrayList<CdmaNumberInfoRecord> number = new ArrayList<>();
  
  public ArrayList<CdmaRedirectingNumberInfoRecord> redir = new ArrayList<>();
  
  public ArrayList<CdmaSignalInfoRecord> signal = new ArrayList<>();
  
  public static final ArrayList<CdmaInformationRecord> readVectorFromParcel(HwParcel paramHwParcel) {
    ArrayList<CdmaInformationRecord> arrayList = new ArrayList();
    HwBlob hwBlob = paramHwParcel.readBuffer(16L);
    int i = hwBlob.getInt32(8L);
    hwBlob = paramHwParcel.readEmbeddedBuffer((i * 120), hwBlob.handle(), 0L, true);
    arrayList.clear();
    for (byte b = 0; b < i; b++) {
      CdmaInformationRecord cdmaInformationRecord = new CdmaInformationRecord();
      cdmaInformationRecord.readEmbeddedFromParcel(paramHwParcel, hwBlob, (b * 120));
      arrayList.add(cdmaInformationRecord);
    } 
    return arrayList;
  }
  
  public static final void writeVectorToParcel(HwParcel paramHwParcel, ArrayList<CdmaInformationRecord> paramArrayList) {
    HwBlob hwBlob1 = new HwBlob(16);
    int i = paramArrayList.size();
    hwBlob1.putInt32(8L, i);
    hwBlob1.putBool(12L, false);
    HwBlob hwBlob2 = new HwBlob(i * 120);
    for (byte b = 0; b < i; b++)
      ((CdmaInformationRecord)paramArrayList.get(b)).writeEmbeddedToBlob(hwBlob2, (b * 120)); 
    hwBlob1.putBlob(0L, hwBlob2);
    paramHwParcel.writeBuffer(hwBlob1);
  }
  
  public final boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (paramObject == null)
      return false; 
    if (paramObject.getClass() != CdmaInformationRecord.class)
      return false; 
    paramObject = paramObject;
    return (this.name != ((CdmaInformationRecord)paramObject).name) ? false : (!HidlSupport.deepEquals(this.display, ((CdmaInformationRecord)paramObject).display) ? false : (!HidlSupport.deepEquals(this.number, ((CdmaInformationRecord)paramObject).number) ? false : (!HidlSupport.deepEquals(this.signal, ((CdmaInformationRecord)paramObject).signal) ? false : (!HidlSupport.deepEquals(this.redir, ((CdmaInformationRecord)paramObject).redir) ? false : (!HidlSupport.deepEquals(this.lineCtrl, ((CdmaInformationRecord)paramObject).lineCtrl) ? false : (!HidlSupport.deepEquals(this.clir, ((CdmaInformationRecord)paramObject).clir) ? false : (!!HidlSupport.deepEquals(this.audioCtrl, ((CdmaInformationRecord)paramObject).audioCtrl))))))));
  }
  
  public final int hashCode() {
    return Objects.hash(new Object[] { Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.name))), Integer.valueOf(HidlSupport.deepHashCode(this.display)), Integer.valueOf(HidlSupport.deepHashCode(this.number)), Integer.valueOf(HidlSupport.deepHashCode(this.signal)), Integer.valueOf(HidlSupport.deepHashCode(this.redir)), Integer.valueOf(HidlSupport.deepHashCode(this.lineCtrl)), Integer.valueOf(HidlSupport.deepHashCode(this.clir)), Integer.valueOf(HidlSupport.deepHashCode(this.audioCtrl)) });
  }
  
  public final void readEmbeddedFromParcel(HwParcel paramHwParcel, HwBlob paramHwBlob, long paramLong) {
    this.name = paramHwBlob.getInt32(paramLong + 0L);
    int i = paramHwBlob.getInt32(paramLong + 8L + 8L);
    HwBlob hwBlob1 = paramHwParcel.readEmbeddedBuffer((i * 16), paramHwBlob.handle(), paramLong + 8L + 0L, true);
    this.display.clear();
    byte b;
    for (b = 0; b < i; b++) {
      CdmaDisplayInfoRecord cdmaDisplayInfoRecord = new CdmaDisplayInfoRecord();
      cdmaDisplayInfoRecord.readEmbeddedFromParcel(paramHwParcel, hwBlob1, (b * 16));
      this.display.add(cdmaDisplayInfoRecord);
    } 
    i = paramHwBlob.getInt32(paramLong + 24L + 8L);
    HwBlob hwBlob2 = paramHwParcel.readEmbeddedBuffer((i * 24), paramHwBlob.handle(), paramLong + 24L + 0L, true);
    this.number.clear();
    for (b = 0; b < i; b++) {
      CdmaNumberInfoRecord cdmaNumberInfoRecord = new CdmaNumberInfoRecord();
      cdmaNumberInfoRecord.readEmbeddedFromParcel(paramHwParcel, hwBlob2, (b * 24));
      this.number.add(cdmaNumberInfoRecord);
    } 
    i = paramHwBlob.getInt32(paramLong + 40L + 8L);
    hwBlob1 = paramHwParcel.readEmbeddedBuffer((i * 4), paramHwBlob.handle(), paramLong + 40L + 0L, true);
    this.signal.clear();
    for (b = 0; b < i; b++) {
      CdmaSignalInfoRecord cdmaSignalInfoRecord = new CdmaSignalInfoRecord();
      cdmaSignalInfoRecord.readEmbeddedFromParcel(paramHwParcel, hwBlob1, (b * 4));
      this.signal.add(cdmaSignalInfoRecord);
    } 
    i = paramHwBlob.getInt32(paramLong + 56L + 8L);
    hwBlob1 = paramHwParcel.readEmbeddedBuffer((i * 32), paramHwBlob.handle(), paramLong + 56L + 0L, true);
    this.redir.clear();
    for (b = 0; b < i; b++) {
      CdmaRedirectingNumberInfoRecord cdmaRedirectingNumberInfoRecord = new CdmaRedirectingNumberInfoRecord();
      cdmaRedirectingNumberInfoRecord.readEmbeddedFromParcel(paramHwParcel, hwBlob1, (b * 32));
      this.redir.add(cdmaRedirectingNumberInfoRecord);
    } 
    i = paramHwBlob.getInt32(paramLong + 72L + 8L);
    hwBlob2 = paramHwParcel.readEmbeddedBuffer((i * 4), paramHwBlob.handle(), paramLong + 72L + 0L, true);
    this.lineCtrl.clear();
    for (b = 0; b < i; b++) {
      CdmaLineControlInfoRecord cdmaLineControlInfoRecord = new CdmaLineControlInfoRecord();
      cdmaLineControlInfoRecord.readEmbeddedFromParcel(paramHwParcel, hwBlob2, (b * 4));
      this.lineCtrl.add(cdmaLineControlInfoRecord);
    } 
    i = paramHwBlob.getInt32(paramLong + 88L + 8L);
    hwBlob1 = paramHwParcel.readEmbeddedBuffer((i * 1), paramHwBlob.handle(), paramLong + 88L + 0L, true);
    this.clir.clear();
    for (b = 0; b < i; b++) {
      CdmaT53ClirInfoRecord cdmaT53ClirInfoRecord = new CdmaT53ClirInfoRecord();
      cdmaT53ClirInfoRecord.readEmbeddedFromParcel(paramHwParcel, hwBlob1, (b * 1));
      this.clir.add(cdmaT53ClirInfoRecord);
    } 
    i = paramHwBlob.getInt32(paramLong + 104L + 8L);
    hwBlob2 = paramHwParcel.readEmbeddedBuffer((i * 2), paramHwBlob.handle(), paramLong + 104L + 0L, true);
    this.audioCtrl.clear();
    for (b = 0; b < i; b++) {
      CdmaT53AudioControlInfoRecord cdmaT53AudioControlInfoRecord = new CdmaT53AudioControlInfoRecord();
      cdmaT53AudioControlInfoRecord.readEmbeddedFromParcel(paramHwParcel, hwBlob2, (b * 2));
      this.audioCtrl.add(cdmaT53AudioControlInfoRecord);
    } 
  }
  
  public final void readFromParcel(HwParcel paramHwParcel) {
    readEmbeddedFromParcel(paramHwParcel, paramHwParcel.readBuffer(120L), 0L);
  }
  
  public final String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("{");
    stringBuilder.append(".name = ");
    stringBuilder.append(CdmaInfoRecName.toString(this.name));
    stringBuilder.append(", .display = ");
    stringBuilder.append(this.display);
    stringBuilder.append(", .number = ");
    stringBuilder.append(this.number);
    stringBuilder.append(", .signal = ");
    stringBuilder.append(this.signal);
    stringBuilder.append(", .redir = ");
    stringBuilder.append(this.redir);
    stringBuilder.append(", .lineCtrl = ");
    stringBuilder.append(this.lineCtrl);
    stringBuilder.append(", .clir = ");
    stringBuilder.append(this.clir);
    stringBuilder.append(", .audioCtrl = ");
    stringBuilder.append(this.audioCtrl);
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public final void writeEmbeddedToBlob(HwBlob paramHwBlob, long paramLong) {
    paramHwBlob.putInt32(paramLong + 0L, this.name);
    int i = this.display.size();
    paramHwBlob.putInt32(paramLong + 8L + 8L, i);
    paramHwBlob.putBool(paramLong + 8L + 12L, false);
    HwBlob hwBlob = new HwBlob(i * 16);
    byte b;
    for (b = 0; b < i; b++)
      ((CdmaDisplayInfoRecord)this.display.get(b)).writeEmbeddedToBlob(hwBlob, (b * 16)); 
    paramHwBlob.putBlob(paramLong + 8L + 0L, hwBlob);
    i = this.number.size();
    paramHwBlob.putInt32(paramLong + 24L + 8L, i);
    paramHwBlob.putBool(paramLong + 24L + 12L, false);
    hwBlob = new HwBlob(i * 24);
    for (b = 0; b < i; b++)
      ((CdmaNumberInfoRecord)this.number.get(b)).writeEmbeddedToBlob(hwBlob, (b * 24)); 
    paramHwBlob.putBlob(paramLong + 24L + 0L, hwBlob);
    i = this.signal.size();
    paramHwBlob.putInt32(paramLong + 40L + 8L, i);
    paramHwBlob.putBool(paramLong + 40L + 12L, false);
    hwBlob = new HwBlob(i * 4);
    for (b = 0; b < i; b++)
      ((CdmaSignalInfoRecord)this.signal.get(b)).writeEmbeddedToBlob(hwBlob, (b * 4)); 
    paramHwBlob.putBlob(paramLong + 40L + 0L, hwBlob);
    i = this.redir.size();
    paramHwBlob.putInt32(paramLong + 56L + 8L, i);
    paramHwBlob.putBool(paramLong + 56L + 12L, false);
    hwBlob = new HwBlob(i * 32);
    for (b = 0; b < i; b++)
      ((CdmaRedirectingNumberInfoRecord)this.redir.get(b)).writeEmbeddedToBlob(hwBlob, (b * 32)); 
    paramHwBlob.putBlob(paramLong + 56L + 0L, hwBlob);
    i = this.lineCtrl.size();
    paramHwBlob.putInt32(paramLong + 72L + 8L, i);
    paramHwBlob.putBool(paramLong + 72L + 12L, false);
    hwBlob = new HwBlob(i * 4);
    for (b = 0; b < i; b++)
      ((CdmaLineControlInfoRecord)this.lineCtrl.get(b)).writeEmbeddedToBlob(hwBlob, (b * 4)); 
    paramHwBlob.putBlob(paramLong + 72L + 0L, hwBlob);
    i = this.clir.size();
    paramHwBlob.putInt32(paramLong + 88L + 8L, i);
    paramHwBlob.putBool(paramLong + 88L + 12L, false);
    hwBlob = new HwBlob(i * 1);
    for (b = 0; b < i; b++)
      ((CdmaT53ClirInfoRecord)this.clir.get(b)).writeEmbeddedToBlob(hwBlob, (b * 1)); 
    paramHwBlob.putBlob(paramLong + 88L + 0L, hwBlob);
    i = this.audioCtrl.size();
    paramHwBlob.putInt32(paramLong + 104L + 8L, i);
    paramHwBlob.putBool(paramLong + 104L + 12L, false);
    hwBlob = new HwBlob(i * 2);
    for (b = 0; b < i; b++)
      ((CdmaT53AudioControlInfoRecord)this.audioCtrl.get(b)).writeEmbeddedToBlob(hwBlob, (b * 2)); 
    paramHwBlob.putBlob(paramLong + 104L + 0L, hwBlob);
  }
  
  public final void writeToParcel(HwParcel paramHwParcel) {
    HwBlob hwBlob = new HwBlob(120);
    writeEmbeddedToBlob(hwBlob, 0L);
    paramHwParcel.writeBuffer(hwBlob);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/V1_0/CdmaInformationRecord.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */