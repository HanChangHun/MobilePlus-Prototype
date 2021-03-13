package android.hardware.gnss.V1_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

public final class GnssNavigationMessage {
  public ArrayList<Byte> data = new ArrayList<>();
  
  public short messageId = (short)0;
  
  public short status;
  
  public short submessageId = (short)0;
  
  public short svid = (short)0;
  
  public short type = (short)0;
  
  public static final ArrayList<GnssNavigationMessage> readVectorFromParcel(HwParcel paramHwParcel) {
    ArrayList<GnssNavigationMessage> arrayList = new ArrayList();
    HwBlob hwBlob = paramHwParcel.readBuffer(16L);
    int i = hwBlob.getInt32(8L);
    hwBlob = paramHwParcel.readEmbeddedBuffer((i * 32), hwBlob.handle(), 0L, true);
    arrayList.clear();
    for (byte b = 0; b < i; b++) {
      GnssNavigationMessage gnssNavigationMessage = new GnssNavigationMessage();
      gnssNavigationMessage.readEmbeddedFromParcel(paramHwParcel, hwBlob, (b * 32));
      arrayList.add(gnssNavigationMessage);
    } 
    return arrayList;
  }
  
  public static final void writeVectorToParcel(HwParcel paramHwParcel, ArrayList<GnssNavigationMessage> paramArrayList) {
    HwBlob hwBlob1 = new HwBlob(16);
    int i = paramArrayList.size();
    hwBlob1.putInt32(8L, i);
    hwBlob1.putBool(12L, false);
    HwBlob hwBlob2 = new HwBlob(i * 32);
    for (byte b = 0; b < i; b++)
      ((GnssNavigationMessage)paramArrayList.get(b)).writeEmbeddedToBlob(hwBlob2, (b * 32)); 
    hwBlob1.putBlob(0L, hwBlob2);
    paramHwParcel.writeBuffer(hwBlob1);
  }
  
  public final boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (paramObject == null)
      return false; 
    if (paramObject.getClass() != GnssNavigationMessage.class)
      return false; 
    paramObject = paramObject;
    return (this.svid != ((GnssNavigationMessage)paramObject).svid) ? false : ((this.type != ((GnssNavigationMessage)paramObject).type) ? false : (!HidlSupport.deepEquals(Short.valueOf(this.status), Short.valueOf(((GnssNavigationMessage)paramObject).status)) ? false : ((this.messageId != ((GnssNavigationMessage)paramObject).messageId) ? false : ((this.submessageId != ((GnssNavigationMessage)paramObject).submessageId) ? false : (!!HidlSupport.deepEquals(this.data, ((GnssNavigationMessage)paramObject).data))))));
  }
  
  public final int hashCode() {
    return Objects.hash(new Object[] { Integer.valueOf(HidlSupport.deepHashCode(Short.valueOf(this.svid))), Integer.valueOf(HidlSupport.deepHashCode(Short.valueOf(this.type))), Integer.valueOf(HidlSupport.deepHashCode(Short.valueOf(this.status))), Integer.valueOf(HidlSupport.deepHashCode(Short.valueOf(this.messageId))), Integer.valueOf(HidlSupport.deepHashCode(Short.valueOf(this.submessageId))), Integer.valueOf(HidlSupport.deepHashCode(this.data)) });
  }
  
  public final void readEmbeddedFromParcel(HwParcel paramHwParcel, HwBlob paramHwBlob, long paramLong) {
    this.svid = paramHwBlob.getInt16(paramLong + 0L);
    this.type = paramHwBlob.getInt16(paramLong + 2L);
    this.status = paramHwBlob.getInt16(paramLong + 4L);
    this.messageId = paramHwBlob.getInt16(paramLong + 6L);
    this.submessageId = paramHwBlob.getInt16(paramLong + 8L);
    int i = paramHwBlob.getInt32(paramLong + 16L + 8L);
    HwBlob hwBlob = paramHwParcel.readEmbeddedBuffer((i * 1), paramHwBlob.handle(), paramLong + 16L + 0L, true);
    this.data.clear();
    for (byte b = 0; b < i; b++) {
      byte b1 = hwBlob.getInt8((b * 1));
      this.data.add(Byte.valueOf(b1));
    } 
  }
  
  public final void readFromParcel(HwParcel paramHwParcel) {
    readEmbeddedFromParcel(paramHwParcel, paramHwParcel.readBuffer(32L), 0L);
  }
  
  public final String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("{");
    stringBuilder.append(".svid = ");
    stringBuilder.append(this.svid);
    stringBuilder.append(", .type = ");
    stringBuilder.append(IGnssNavigationMessageCallback.GnssNavigationMessageType.toString(this.type));
    stringBuilder.append(", .status = ");
    stringBuilder.append(IGnssNavigationMessageCallback.NavigationMessageStatus.dumpBitfield(this.status));
    stringBuilder.append(", .messageId = ");
    stringBuilder.append(this.messageId);
    stringBuilder.append(", .submessageId = ");
    stringBuilder.append(this.submessageId);
    stringBuilder.append(", .data = ");
    stringBuilder.append(this.data);
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public final void writeEmbeddedToBlob(HwBlob paramHwBlob, long paramLong) {
    paramHwBlob.putInt16(paramLong + 0L, this.svid);
    paramHwBlob.putInt16(2L + paramLong, this.type);
    paramHwBlob.putInt16(4L + paramLong, this.status);
    paramHwBlob.putInt16(6L + paramLong, this.messageId);
    paramHwBlob.putInt16(paramLong + 8L, this.submessageId);
    int i = this.data.size();
    paramHwBlob.putInt32(paramLong + 16L + 8L, i);
    paramHwBlob.putBool(paramLong + 16L + 12L, false);
    HwBlob hwBlob = new HwBlob(i * 1);
    for (byte b = 0; b < i; b++)
      hwBlob.putInt8((b * 1), ((Byte)this.data.get(b)).byteValue()); 
    paramHwBlob.putBlob(16L + paramLong + 0L, hwBlob);
  }
  
  public final void writeToParcel(HwParcel paramHwParcel) {
    HwBlob hwBlob = new HwBlob(32);
    writeEmbeddedToBlob(hwBlob, 0L);
    paramHwParcel.writeBuffer(hwBlob);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/gnss/V1_0/IGnssNavigationMessageCallback$GnssNavigationMessage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */