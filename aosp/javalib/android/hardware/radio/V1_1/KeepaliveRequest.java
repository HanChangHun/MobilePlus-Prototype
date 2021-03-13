package android.hardware.radio.V1_1;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

public final class KeepaliveRequest {
  public int cid = 0;
  
  public ArrayList<Byte> destinationAddress = new ArrayList<>();
  
  public int destinationPort = 0;
  
  public int maxKeepaliveIntervalMillis = 0;
  
  public ArrayList<Byte> sourceAddress = new ArrayList<>();
  
  public int sourcePort = 0;
  
  public int type = 0;
  
  public static final ArrayList<KeepaliveRequest> readVectorFromParcel(HwParcel paramHwParcel) {
    ArrayList<KeepaliveRequest> arrayList = new ArrayList();
    HwBlob hwBlob1 = paramHwParcel.readBuffer(16L);
    int i = hwBlob1.getInt32(8L);
    HwBlob hwBlob2 = paramHwParcel.readEmbeddedBuffer((i * 64), hwBlob1.handle(), 0L, true);
    arrayList.clear();
    for (byte b = 0; b < i; b++) {
      KeepaliveRequest keepaliveRequest = new KeepaliveRequest();
      keepaliveRequest.readEmbeddedFromParcel(paramHwParcel, hwBlob2, (b * 64));
      arrayList.add(keepaliveRequest);
    } 
    return arrayList;
  }
  
  public static final void writeVectorToParcel(HwParcel paramHwParcel, ArrayList<KeepaliveRequest> paramArrayList) {
    HwBlob hwBlob1 = new HwBlob(16);
    int i = paramArrayList.size();
    hwBlob1.putInt32(8L, i);
    hwBlob1.putBool(12L, false);
    HwBlob hwBlob2 = new HwBlob(i * 64);
    for (byte b = 0; b < i; b++)
      ((KeepaliveRequest)paramArrayList.get(b)).writeEmbeddedToBlob(hwBlob2, (b * 64)); 
    hwBlob1.putBlob(0L, hwBlob2);
    paramHwParcel.writeBuffer(hwBlob1);
  }
  
  public final boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (paramObject == null)
      return false; 
    if (paramObject.getClass() != KeepaliveRequest.class)
      return false; 
    paramObject = paramObject;
    return (this.type != ((KeepaliveRequest)paramObject).type) ? false : (!HidlSupport.deepEquals(this.sourceAddress, ((KeepaliveRequest)paramObject).sourceAddress) ? false : ((this.sourcePort != ((KeepaliveRequest)paramObject).sourcePort) ? false : (!HidlSupport.deepEquals(this.destinationAddress, ((KeepaliveRequest)paramObject).destinationAddress) ? false : ((this.destinationPort != ((KeepaliveRequest)paramObject).destinationPort) ? false : ((this.maxKeepaliveIntervalMillis != ((KeepaliveRequest)paramObject).maxKeepaliveIntervalMillis) ? false : (!(this.cid != ((KeepaliveRequest)paramObject).cid)))))));
  }
  
  public final int hashCode() {
    return Objects.hash(new Object[] { Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.type))), Integer.valueOf(HidlSupport.deepHashCode(this.sourceAddress)), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.sourcePort))), Integer.valueOf(HidlSupport.deepHashCode(this.destinationAddress)), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.destinationPort))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.maxKeepaliveIntervalMillis))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.cid))) });
  }
  
  public final void readEmbeddedFromParcel(HwParcel paramHwParcel, HwBlob paramHwBlob, long paramLong) {
    this.type = paramHwBlob.getInt32(paramLong + 0L);
    int i = paramHwBlob.getInt32(paramLong + 8L + 8L);
    HwBlob hwBlob2 = paramHwParcel.readEmbeddedBuffer((i * 1), paramHwBlob.handle(), paramLong + 8L + 0L, true);
    this.sourceAddress.clear();
    byte b;
    for (b = 0; b < i; b++) {
      byte b1 = hwBlob2.getInt8((b * 1));
      this.sourceAddress.add(Byte.valueOf(b1));
    } 
    this.sourcePort = paramHwBlob.getInt32(paramLong + 24L);
    i = paramHwBlob.getInt32(paramLong + 32L + 8L);
    HwBlob hwBlob1 = paramHwParcel.readEmbeddedBuffer((i * 1), paramHwBlob.handle(), paramLong + 32L + 0L, true);
    this.destinationAddress.clear();
    for (b = 0; b < i; b++) {
      byte b1 = hwBlob1.getInt8((b * 1));
      this.destinationAddress.add(Byte.valueOf(b1));
    } 
    this.destinationPort = paramHwBlob.getInt32(paramLong + 48L);
    this.maxKeepaliveIntervalMillis = paramHwBlob.getInt32(paramLong + 52L);
    this.cid = paramHwBlob.getInt32(paramLong + 56L);
  }
  
  public final void readFromParcel(HwParcel paramHwParcel) {
    readEmbeddedFromParcel(paramHwParcel, paramHwParcel.readBuffer(64L), 0L);
  }
  
  public final String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("{");
    stringBuilder.append(".type = ");
    stringBuilder.append(KeepaliveType.toString(this.type));
    stringBuilder.append(", .sourceAddress = ");
    stringBuilder.append(this.sourceAddress);
    stringBuilder.append(", .sourcePort = ");
    stringBuilder.append(this.sourcePort);
    stringBuilder.append(", .destinationAddress = ");
    stringBuilder.append(this.destinationAddress);
    stringBuilder.append(", .destinationPort = ");
    stringBuilder.append(this.destinationPort);
    stringBuilder.append(", .maxKeepaliveIntervalMillis = ");
    stringBuilder.append(this.maxKeepaliveIntervalMillis);
    stringBuilder.append(", .cid = ");
    stringBuilder.append(this.cid);
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public final void writeEmbeddedToBlob(HwBlob paramHwBlob, long paramLong) {
    paramHwBlob.putInt32(paramLong + 0L, this.type);
    int i = this.sourceAddress.size();
    paramHwBlob.putInt32(paramLong + 8L + 8L, i);
    paramHwBlob.putBool(paramLong + 8L + 12L, false);
    HwBlob hwBlob = new HwBlob(i * 1);
    byte b;
    for (b = 0; b < i; b++)
      hwBlob.putInt8((b * 1), ((Byte)this.sourceAddress.get(b)).byteValue()); 
    paramHwBlob.putBlob(paramLong + 8L + 0L, hwBlob);
    paramHwBlob.putInt32(paramLong + 24L, this.sourcePort);
    i = this.destinationAddress.size();
    paramHwBlob.putInt32(paramLong + 32L + 8L, i);
    paramHwBlob.putBool(paramLong + 32L + 12L, false);
    hwBlob = new HwBlob(i * 1);
    for (b = 0; b < i; b++)
      hwBlob.putInt8((b * 1), ((Byte)this.destinationAddress.get(b)).byteValue()); 
    paramHwBlob.putBlob(paramLong + 32L + 0L, hwBlob);
    paramHwBlob.putInt32(paramLong + 48L, this.destinationPort);
    paramHwBlob.putInt32(paramLong + 52L, this.maxKeepaliveIntervalMillis);
    paramHwBlob.putInt32(paramLong + 56L, this.cid);
  }
  
  public final void writeToParcel(HwParcel paramHwParcel) {
    HwBlob hwBlob = new HwBlob(64);
    writeEmbeddedToBlob(hwBlob, 0L);
    paramHwParcel.writeBuffer(hwBlob);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/V1_1/KeepaliveRequest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */