package android.hardware.radio.V1_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

public final class CdmaSmsMessage {
  public CdmaSmsAddress address = new CdmaSmsAddress();
  
  public ArrayList<Byte> bearerData = new ArrayList<>();
  
  public boolean isServicePresent = false;
  
  public int serviceCategory = 0;
  
  public CdmaSmsSubaddress subAddress = new CdmaSmsSubaddress();
  
  public int teleserviceId = 0;
  
  public static final ArrayList<CdmaSmsMessage> readVectorFromParcel(HwParcel paramHwParcel) {
    ArrayList<CdmaSmsMessage> arrayList = new ArrayList();
    HwBlob hwBlob1 = paramHwParcel.readBuffer(16L);
    int i = hwBlob1.getInt32(8L);
    HwBlob hwBlob2 = paramHwParcel.readEmbeddedBuffer((i * 88), hwBlob1.handle(), 0L, true);
    arrayList.clear();
    for (byte b = 0; b < i; b++) {
      CdmaSmsMessage cdmaSmsMessage = new CdmaSmsMessage();
      cdmaSmsMessage.readEmbeddedFromParcel(paramHwParcel, hwBlob2, (b * 88));
      arrayList.add(cdmaSmsMessage);
    } 
    return arrayList;
  }
  
  public static final void writeVectorToParcel(HwParcel paramHwParcel, ArrayList<CdmaSmsMessage> paramArrayList) {
    HwBlob hwBlob1 = new HwBlob(16);
    int i = paramArrayList.size();
    hwBlob1.putInt32(8L, i);
    hwBlob1.putBool(12L, false);
    HwBlob hwBlob2 = new HwBlob(i * 88);
    for (byte b = 0; b < i; b++)
      ((CdmaSmsMessage)paramArrayList.get(b)).writeEmbeddedToBlob(hwBlob2, (b * 88)); 
    hwBlob1.putBlob(0L, hwBlob2);
    paramHwParcel.writeBuffer(hwBlob1);
  }
  
  public final boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (paramObject == null)
      return false; 
    if (paramObject.getClass() != CdmaSmsMessage.class)
      return false; 
    paramObject = paramObject;
    return (this.teleserviceId != ((CdmaSmsMessage)paramObject).teleserviceId) ? false : ((this.isServicePresent != ((CdmaSmsMessage)paramObject).isServicePresent) ? false : ((this.serviceCategory != ((CdmaSmsMessage)paramObject).serviceCategory) ? false : (!HidlSupport.deepEquals(this.address, ((CdmaSmsMessage)paramObject).address) ? false : (!HidlSupport.deepEquals(this.subAddress, ((CdmaSmsMessage)paramObject).subAddress) ? false : (!!HidlSupport.deepEquals(this.bearerData, ((CdmaSmsMessage)paramObject).bearerData))))));
  }
  
  public final int hashCode() {
    return Objects.hash(new Object[] { Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.teleserviceId))), Integer.valueOf(HidlSupport.deepHashCode(Boolean.valueOf(this.isServicePresent))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.serviceCategory))), Integer.valueOf(HidlSupport.deepHashCode(this.address)), Integer.valueOf(HidlSupport.deepHashCode(this.subAddress)), Integer.valueOf(HidlSupport.deepHashCode(this.bearerData)) });
  }
  
  public final void readEmbeddedFromParcel(HwParcel paramHwParcel, HwBlob paramHwBlob, long paramLong) {
    this.teleserviceId = paramHwBlob.getInt32(paramLong + 0L);
    this.isServicePresent = paramHwBlob.getBool(paramLong + 4L);
    this.serviceCategory = paramHwBlob.getInt32(paramLong + 8L);
    this.address.readEmbeddedFromParcel(paramHwParcel, paramHwBlob, paramLong + 16L);
    this.subAddress.readEmbeddedFromParcel(paramHwParcel, paramHwBlob, paramLong + 48L);
    int i = paramHwBlob.getInt32(paramLong + 72L + 8L);
    HwBlob hwBlob = paramHwParcel.readEmbeddedBuffer((i * 1), paramHwBlob.handle(), paramLong + 72L + 0L, true);
    this.bearerData.clear();
    for (byte b = 0; b < i; b++) {
      byte b1 = hwBlob.getInt8((b * 1));
      this.bearerData.add(Byte.valueOf(b1));
    } 
  }
  
  public final void readFromParcel(HwParcel paramHwParcel) {
    readEmbeddedFromParcel(paramHwParcel, paramHwParcel.readBuffer(88L), 0L);
  }
  
  public final String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("{");
    stringBuilder.append(".teleserviceId = ");
    stringBuilder.append(this.teleserviceId);
    stringBuilder.append(", .isServicePresent = ");
    stringBuilder.append(this.isServicePresent);
    stringBuilder.append(", .serviceCategory = ");
    stringBuilder.append(this.serviceCategory);
    stringBuilder.append(", .address = ");
    stringBuilder.append(this.address);
    stringBuilder.append(", .subAddress = ");
    stringBuilder.append(this.subAddress);
    stringBuilder.append(", .bearerData = ");
    stringBuilder.append(this.bearerData);
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public final void writeEmbeddedToBlob(HwBlob paramHwBlob, long paramLong) {
    paramHwBlob.putInt32(paramLong + 0L, this.teleserviceId);
    paramHwBlob.putBool(4L + paramLong, this.isServicePresent);
    paramHwBlob.putInt32(paramLong + 8L, this.serviceCategory);
    this.address.writeEmbeddedToBlob(paramHwBlob, 16L + paramLong);
    this.subAddress.writeEmbeddedToBlob(paramHwBlob, 48L + paramLong);
    int i = this.bearerData.size();
    paramHwBlob.putInt32(paramLong + 72L + 8L, i);
    paramHwBlob.putBool(paramLong + 72L + 12L, false);
    HwBlob hwBlob = new HwBlob(i * 1);
    for (byte b = 0; b < i; b++)
      hwBlob.putInt8((b * 1), ((Byte)this.bearerData.get(b)).byteValue()); 
    paramHwBlob.putBlob(72L + paramLong + 0L, hwBlob);
  }
  
  public final void writeToParcel(HwParcel paramHwParcel) {
    HwBlob hwBlob = new HwBlob(88);
    writeEmbeddedToBlob(hwBlob, 0L);
    paramHwParcel.writeBuffer(hwBlob);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/V1_0/CdmaSmsMessage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */