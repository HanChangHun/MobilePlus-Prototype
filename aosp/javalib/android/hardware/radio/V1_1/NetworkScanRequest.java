package android.hardware.radio.V1_1;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

public final class NetworkScanRequest {
  public int interval = 0;
  
  public ArrayList<RadioAccessSpecifier> specifiers = new ArrayList<>();
  
  public int type = 0;
  
  public static final ArrayList<NetworkScanRequest> readVectorFromParcel(HwParcel paramHwParcel) {
    ArrayList<NetworkScanRequest> arrayList = new ArrayList();
    HwBlob hwBlob = paramHwParcel.readBuffer(16L);
    int i = hwBlob.getInt32(8L);
    hwBlob = paramHwParcel.readEmbeddedBuffer((i * 24), hwBlob.handle(), 0L, true);
    arrayList.clear();
    for (byte b = 0; b < i; b++) {
      NetworkScanRequest networkScanRequest = new NetworkScanRequest();
      networkScanRequest.readEmbeddedFromParcel(paramHwParcel, hwBlob, (b * 24));
      arrayList.add(networkScanRequest);
    } 
    return arrayList;
  }
  
  public static final void writeVectorToParcel(HwParcel paramHwParcel, ArrayList<NetworkScanRequest> paramArrayList) {
    HwBlob hwBlob1 = new HwBlob(16);
    int i = paramArrayList.size();
    hwBlob1.putInt32(8L, i);
    hwBlob1.putBool(12L, false);
    HwBlob hwBlob2 = new HwBlob(i * 24);
    for (byte b = 0; b < i; b++)
      ((NetworkScanRequest)paramArrayList.get(b)).writeEmbeddedToBlob(hwBlob2, (b * 24)); 
    hwBlob1.putBlob(0L, hwBlob2);
    paramHwParcel.writeBuffer(hwBlob1);
  }
  
  public final boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (paramObject == null)
      return false; 
    if (paramObject.getClass() != NetworkScanRequest.class)
      return false; 
    paramObject = paramObject;
    return (this.type != ((NetworkScanRequest)paramObject).type) ? false : ((this.interval != ((NetworkScanRequest)paramObject).interval) ? false : (!!HidlSupport.deepEquals(this.specifiers, ((NetworkScanRequest)paramObject).specifiers)));
  }
  
  public final int hashCode() {
    return Objects.hash(new Object[] { Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.type))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.interval))), Integer.valueOf(HidlSupport.deepHashCode(this.specifiers)) });
  }
  
  public final void readEmbeddedFromParcel(HwParcel paramHwParcel, HwBlob paramHwBlob, long paramLong) {
    this.type = paramHwBlob.getInt32(paramLong + 0L);
    this.interval = paramHwBlob.getInt32(paramLong + 4L);
    int i = paramHwBlob.getInt32(paramLong + 8L + 8L);
    HwBlob hwBlob = paramHwParcel.readEmbeddedBuffer((i * 72), paramHwBlob.handle(), paramLong + 8L + 0L, true);
    this.specifiers.clear();
    for (byte b = 0; b < i; b++) {
      RadioAccessSpecifier radioAccessSpecifier = new RadioAccessSpecifier();
      radioAccessSpecifier.readEmbeddedFromParcel(paramHwParcel, hwBlob, (b * 72));
      this.specifiers.add(radioAccessSpecifier);
    } 
  }
  
  public final void readFromParcel(HwParcel paramHwParcel) {
    readEmbeddedFromParcel(paramHwParcel, paramHwParcel.readBuffer(24L), 0L);
  }
  
  public final String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("{");
    stringBuilder.append(".type = ");
    stringBuilder.append(ScanType.toString(this.type));
    stringBuilder.append(", .interval = ");
    stringBuilder.append(this.interval);
    stringBuilder.append(", .specifiers = ");
    stringBuilder.append(this.specifiers);
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public final void writeEmbeddedToBlob(HwBlob paramHwBlob, long paramLong) {
    paramHwBlob.putInt32(paramLong + 0L, this.type);
    paramHwBlob.putInt32(4L + paramLong, this.interval);
    int i = this.specifiers.size();
    paramHwBlob.putInt32(paramLong + 8L + 8L, i);
    paramHwBlob.putBool(paramLong + 8L + 12L, false);
    HwBlob hwBlob = new HwBlob(i * 72);
    for (byte b = 0; b < i; b++)
      ((RadioAccessSpecifier)this.specifiers.get(b)).writeEmbeddedToBlob(hwBlob, (b * 72)); 
    paramHwBlob.putBlob(8L + paramLong + 0L, hwBlob);
  }
  
  public final void writeToParcel(HwParcel paramHwParcel) {
    HwBlob hwBlob = new HwBlob(24);
    writeEmbeddedToBlob(hwBlob, 0L);
    paramHwParcel.writeBuffer(hwBlob);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/V1_1/NetworkScanRequest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */