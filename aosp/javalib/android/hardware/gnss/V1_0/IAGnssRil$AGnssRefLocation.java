package android.hardware.gnss.V1_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

public final class AGnssRefLocation {
  public IAGnssRil.AGnssRefLocationCellID cellID = new IAGnssRil.AGnssRefLocationCellID();
  
  public byte type = (byte)0;
  
  public static final ArrayList<AGnssRefLocation> readVectorFromParcel(HwParcel paramHwParcel) {
    ArrayList<AGnssRefLocation> arrayList = new ArrayList();
    HwBlob hwBlob1 = paramHwParcel.readBuffer(16L);
    int i = hwBlob1.getInt32(8L);
    HwBlob hwBlob2 = paramHwParcel.readEmbeddedBuffer((i * 20), hwBlob1.handle(), 0L, true);
    arrayList.clear();
    for (byte b = 0; b < i; b++) {
      AGnssRefLocation aGnssRefLocation = new AGnssRefLocation();
      aGnssRefLocation.readEmbeddedFromParcel(paramHwParcel, hwBlob2, (b * 20));
      arrayList.add(aGnssRefLocation);
    } 
    return arrayList;
  }
  
  public static final void writeVectorToParcel(HwParcel paramHwParcel, ArrayList<AGnssRefLocation> paramArrayList) {
    HwBlob hwBlob1 = new HwBlob(16);
    int i = paramArrayList.size();
    hwBlob1.putInt32(8L, i);
    hwBlob1.putBool(12L, false);
    HwBlob hwBlob2 = new HwBlob(i * 20);
    for (byte b = 0; b < i; b++)
      ((AGnssRefLocation)paramArrayList.get(b)).writeEmbeddedToBlob(hwBlob2, (b * 20)); 
    hwBlob1.putBlob(0L, hwBlob2);
    paramHwParcel.writeBuffer(hwBlob1);
  }
  
  public final boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (paramObject == null)
      return false; 
    if (paramObject.getClass() != AGnssRefLocation.class)
      return false; 
    paramObject = paramObject;
    return (this.type != ((AGnssRefLocation)paramObject).type) ? false : (!!HidlSupport.deepEquals(this.cellID, ((AGnssRefLocation)paramObject).cellID));
  }
  
  public final int hashCode() {
    return Objects.hash(new Object[] { Integer.valueOf(HidlSupport.deepHashCode(Byte.valueOf(this.type))), Integer.valueOf(HidlSupport.deepHashCode(this.cellID)) });
  }
  
  public final void readEmbeddedFromParcel(HwParcel paramHwParcel, HwBlob paramHwBlob, long paramLong) {
    this.type = paramHwBlob.getInt8(0L + paramLong);
    this.cellID.readEmbeddedFromParcel(paramHwParcel, paramHwBlob, 4L + paramLong);
  }
  
  public final void readFromParcel(HwParcel paramHwParcel) {
    readEmbeddedFromParcel(paramHwParcel, paramHwParcel.readBuffer(20L), 0L);
  }
  
  public final String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("{");
    stringBuilder.append(".type = ");
    stringBuilder.append(IAGnssRil.AGnssRefLocationType.toString(this.type));
    stringBuilder.append(", .cellID = ");
    stringBuilder.append(this.cellID);
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public final void writeEmbeddedToBlob(HwBlob paramHwBlob, long paramLong) {
    paramHwBlob.putInt8(0L + paramLong, this.type);
    this.cellID.writeEmbeddedToBlob(paramHwBlob, 4L + paramLong);
  }
  
  public final void writeToParcel(HwParcel paramHwParcel) {
    HwBlob hwBlob = new HwBlob(20);
    writeEmbeddedToBlob(hwBlob, 0L);
    paramHwParcel.writeBuffer(hwBlob);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/gnss/V1_0/IAGnssRil$AGnssRefLocation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */