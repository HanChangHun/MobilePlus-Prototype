package android.hardware.radio.V1_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

public final class CdmaSmsWriteArgs {
  public CdmaSmsMessage message = new CdmaSmsMessage();
  
  public int status = 0;
  
  public static final ArrayList<CdmaSmsWriteArgs> readVectorFromParcel(HwParcel paramHwParcel) {
    ArrayList<CdmaSmsWriteArgs> arrayList = new ArrayList();
    HwBlob hwBlob = paramHwParcel.readBuffer(16L);
    int i = hwBlob.getInt32(8L);
    hwBlob = paramHwParcel.readEmbeddedBuffer((i * 96), hwBlob.handle(), 0L, true);
    arrayList.clear();
    for (byte b = 0; b < i; b++) {
      CdmaSmsWriteArgs cdmaSmsWriteArgs = new CdmaSmsWriteArgs();
      cdmaSmsWriteArgs.readEmbeddedFromParcel(paramHwParcel, hwBlob, (b * 96));
      arrayList.add(cdmaSmsWriteArgs);
    } 
    return arrayList;
  }
  
  public static final void writeVectorToParcel(HwParcel paramHwParcel, ArrayList<CdmaSmsWriteArgs> paramArrayList) {
    HwBlob hwBlob1 = new HwBlob(16);
    int i = paramArrayList.size();
    hwBlob1.putInt32(8L, i);
    hwBlob1.putBool(12L, false);
    HwBlob hwBlob2 = new HwBlob(i * 96);
    for (byte b = 0; b < i; b++)
      ((CdmaSmsWriteArgs)paramArrayList.get(b)).writeEmbeddedToBlob(hwBlob2, (b * 96)); 
    hwBlob1.putBlob(0L, hwBlob2);
    paramHwParcel.writeBuffer(hwBlob1);
  }
  
  public final boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (paramObject == null)
      return false; 
    if (paramObject.getClass() != CdmaSmsWriteArgs.class)
      return false; 
    paramObject = paramObject;
    return (this.status != ((CdmaSmsWriteArgs)paramObject).status) ? false : (!!HidlSupport.deepEquals(this.message, ((CdmaSmsWriteArgs)paramObject).message));
  }
  
  public final int hashCode() {
    return Objects.hash(new Object[] { Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.status))), Integer.valueOf(HidlSupport.deepHashCode(this.message)) });
  }
  
  public final void readEmbeddedFromParcel(HwParcel paramHwParcel, HwBlob paramHwBlob, long paramLong) {
    this.status = paramHwBlob.getInt32(0L + paramLong);
    this.message.readEmbeddedFromParcel(paramHwParcel, paramHwBlob, 8L + paramLong);
  }
  
  public final void readFromParcel(HwParcel paramHwParcel) {
    readEmbeddedFromParcel(paramHwParcel, paramHwParcel.readBuffer(96L), 0L);
  }
  
  public final String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("{");
    stringBuilder.append(".status = ");
    stringBuilder.append(CdmaSmsWriteArgsStatus.toString(this.status));
    stringBuilder.append(", .message = ");
    stringBuilder.append(this.message);
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public final void writeEmbeddedToBlob(HwBlob paramHwBlob, long paramLong) {
    paramHwBlob.putInt32(0L + paramLong, this.status);
    this.message.writeEmbeddedToBlob(paramHwBlob, 8L + paramLong);
  }
  
  public final void writeToParcel(HwParcel paramHwParcel) {
    HwBlob hwBlob = new HwBlob(96);
    writeEmbeddedToBlob(hwBlob, 0L);
    paramHwParcel.writeBuffer(hwBlob);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/V1_0/CdmaSmsWriteArgs.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */