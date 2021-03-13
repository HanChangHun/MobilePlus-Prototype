package android.hardware.gnss.V1_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

public final class AGnssRefLocationCellID {
  public int cid = 0;
  
  public short lac = (short)0;
  
  public short mcc = (short)0;
  
  public short mnc = (short)0;
  
  public short pcid = (short)0;
  
  public short tac = (short)0;
  
  public byte type = (byte)0;
  
  public static final ArrayList<AGnssRefLocationCellID> readVectorFromParcel(HwParcel paramHwParcel) {
    ArrayList<AGnssRefLocationCellID> arrayList = new ArrayList();
    HwBlob hwBlob = paramHwParcel.readBuffer(16L);
    int i = hwBlob.getInt32(8L);
    hwBlob = paramHwParcel.readEmbeddedBuffer((i * 16), hwBlob.handle(), 0L, true);
    arrayList.clear();
    for (byte b = 0; b < i; b++) {
      AGnssRefLocationCellID aGnssRefLocationCellID = new AGnssRefLocationCellID();
      aGnssRefLocationCellID.readEmbeddedFromParcel(paramHwParcel, hwBlob, (b * 16));
      arrayList.add(aGnssRefLocationCellID);
    } 
    return arrayList;
  }
  
  public static final void writeVectorToParcel(HwParcel paramHwParcel, ArrayList<AGnssRefLocationCellID> paramArrayList) {
    HwBlob hwBlob1 = new HwBlob(16);
    int i = paramArrayList.size();
    hwBlob1.putInt32(8L, i);
    hwBlob1.putBool(12L, false);
    HwBlob hwBlob2 = new HwBlob(i * 16);
    for (byte b = 0; b < i; b++)
      ((AGnssRefLocationCellID)paramArrayList.get(b)).writeEmbeddedToBlob(hwBlob2, (b * 16)); 
    hwBlob1.putBlob(0L, hwBlob2);
    paramHwParcel.writeBuffer(hwBlob1);
  }
  
  public final boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (paramObject == null)
      return false; 
    if (paramObject.getClass() != AGnssRefLocationCellID.class)
      return false; 
    paramObject = paramObject;
    return (this.type != ((AGnssRefLocationCellID)paramObject).type) ? false : ((this.mcc != ((AGnssRefLocationCellID)paramObject).mcc) ? false : ((this.mnc != ((AGnssRefLocationCellID)paramObject).mnc) ? false : ((this.lac != ((AGnssRefLocationCellID)paramObject).lac) ? false : ((this.cid != ((AGnssRefLocationCellID)paramObject).cid) ? false : ((this.tac != ((AGnssRefLocationCellID)paramObject).tac) ? false : (!(this.pcid != ((AGnssRefLocationCellID)paramObject).pcid)))))));
  }
  
  public final int hashCode() {
    return Objects.hash(new Object[] { Integer.valueOf(HidlSupport.deepHashCode(Byte.valueOf(this.type))), Integer.valueOf(HidlSupport.deepHashCode(Short.valueOf(this.mcc))), Integer.valueOf(HidlSupport.deepHashCode(Short.valueOf(this.mnc))), Integer.valueOf(HidlSupport.deepHashCode(Short.valueOf(this.lac))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.cid))), Integer.valueOf(HidlSupport.deepHashCode(Short.valueOf(this.tac))), Integer.valueOf(HidlSupport.deepHashCode(Short.valueOf(this.pcid))) });
  }
  
  public final void readEmbeddedFromParcel(HwParcel paramHwParcel, HwBlob paramHwBlob, long paramLong) {
    this.type = paramHwBlob.getInt8(0L + paramLong);
    this.mcc = paramHwBlob.getInt16(2L + paramLong);
    this.mnc = paramHwBlob.getInt16(4L + paramLong);
    this.lac = paramHwBlob.getInt16(6L + paramLong);
    this.cid = paramHwBlob.getInt32(8L + paramLong);
    this.tac = paramHwBlob.getInt16(12L + paramLong);
    this.pcid = paramHwBlob.getInt16(14L + paramLong);
  }
  
  public final void readFromParcel(HwParcel paramHwParcel) {
    readEmbeddedFromParcel(paramHwParcel, paramHwParcel.readBuffer(16L), 0L);
  }
  
  public final String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("{");
    stringBuilder.append(".type = ");
    stringBuilder.append(IAGnssRil.AGnssRefLocationType.toString(this.type));
    stringBuilder.append(", .mcc = ");
    stringBuilder.append(this.mcc);
    stringBuilder.append(", .mnc = ");
    stringBuilder.append(this.mnc);
    stringBuilder.append(", .lac = ");
    stringBuilder.append(this.lac);
    stringBuilder.append(", .cid = ");
    stringBuilder.append(this.cid);
    stringBuilder.append(", .tac = ");
    stringBuilder.append(this.tac);
    stringBuilder.append(", .pcid = ");
    stringBuilder.append(this.pcid);
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public final void writeEmbeddedToBlob(HwBlob paramHwBlob, long paramLong) {
    paramHwBlob.putInt8(0L + paramLong, this.type);
    paramHwBlob.putInt16(2L + paramLong, this.mcc);
    paramHwBlob.putInt16(4L + paramLong, this.mnc);
    paramHwBlob.putInt16(6L + paramLong, this.lac);
    paramHwBlob.putInt32(8L + paramLong, this.cid);
    paramHwBlob.putInt16(12L + paramLong, this.tac);
    paramHwBlob.putInt16(14L + paramLong, this.pcid);
  }
  
  public final void writeToParcel(HwParcel paramHwParcel) {
    HwBlob hwBlob = new HwBlob(16);
    writeEmbeddedToBlob(hwBlob, 0L);
    paramHwParcel.writeBuffer(hwBlob);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/gnss/V1_0/IAGnssRil$AGnssRefLocationCellID.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */