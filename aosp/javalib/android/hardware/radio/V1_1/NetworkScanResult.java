package android.hardware.radio.V1_1;

import android.hardware.radio.V1_0.CellInfo;
import android.hardware.radio.V1_0.RadioError;
import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

public final class NetworkScanResult {
  public int error = 0;
  
  public ArrayList<CellInfo> networkInfos = new ArrayList<>();
  
  public int status = 0;
  
  public static final ArrayList<NetworkScanResult> readVectorFromParcel(HwParcel paramHwParcel) {
    ArrayList<NetworkScanResult> arrayList = new ArrayList();
    HwBlob hwBlob1 = paramHwParcel.readBuffer(16L);
    int i = hwBlob1.getInt32(8L);
    HwBlob hwBlob2 = paramHwParcel.readEmbeddedBuffer((i * 24), hwBlob1.handle(), 0L, true);
    arrayList.clear();
    for (byte b = 0; b < i; b++) {
      NetworkScanResult networkScanResult = new NetworkScanResult();
      networkScanResult.readEmbeddedFromParcel(paramHwParcel, hwBlob2, (b * 24));
      arrayList.add(networkScanResult);
    } 
    return arrayList;
  }
  
  public static final void writeVectorToParcel(HwParcel paramHwParcel, ArrayList<NetworkScanResult> paramArrayList) {
    HwBlob hwBlob1 = new HwBlob(16);
    int i = paramArrayList.size();
    hwBlob1.putInt32(8L, i);
    hwBlob1.putBool(12L, false);
    HwBlob hwBlob2 = new HwBlob(i * 24);
    for (byte b = 0; b < i; b++)
      ((NetworkScanResult)paramArrayList.get(b)).writeEmbeddedToBlob(hwBlob2, (b * 24)); 
    hwBlob1.putBlob(0L, hwBlob2);
    paramHwParcel.writeBuffer(hwBlob1);
  }
  
  public final boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (paramObject == null)
      return false; 
    if (paramObject.getClass() != NetworkScanResult.class)
      return false; 
    paramObject = paramObject;
    return (this.status != ((NetworkScanResult)paramObject).status) ? false : ((this.error != ((NetworkScanResult)paramObject).error) ? false : (!!HidlSupport.deepEquals(this.networkInfos, ((NetworkScanResult)paramObject).networkInfos)));
  }
  
  public final int hashCode() {
    return Objects.hash(new Object[] { Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.status))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.error))), Integer.valueOf(HidlSupport.deepHashCode(this.networkInfos)) });
  }
  
  public final void readEmbeddedFromParcel(HwParcel paramHwParcel, HwBlob paramHwBlob, long paramLong) {
    this.status = paramHwBlob.getInt32(paramLong + 0L);
    this.error = paramHwBlob.getInt32(paramLong + 4L);
    int i = paramHwBlob.getInt32(paramLong + 8L + 8L);
    paramHwBlob = paramHwParcel.readEmbeddedBuffer((i * 104), paramHwBlob.handle(), paramLong + 8L + 0L, true);
    this.networkInfos.clear();
    for (byte b = 0; b < i; b++) {
      CellInfo cellInfo = new CellInfo();
      cellInfo.readEmbeddedFromParcel(paramHwParcel, paramHwBlob, (b * 104));
      this.networkInfos.add(cellInfo);
    } 
  }
  
  public final void readFromParcel(HwParcel paramHwParcel) {
    readEmbeddedFromParcel(paramHwParcel, paramHwParcel.readBuffer(24L), 0L);
  }
  
  public final String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("{");
    stringBuilder.append(".status = ");
    stringBuilder.append(ScanStatus.toString(this.status));
    stringBuilder.append(", .error = ");
    stringBuilder.append(RadioError.toString(this.error));
    stringBuilder.append(", .networkInfos = ");
    stringBuilder.append(this.networkInfos);
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public final void writeEmbeddedToBlob(HwBlob paramHwBlob, long paramLong) {
    paramHwBlob.putInt32(paramLong + 0L, this.status);
    paramHwBlob.putInt32(4L + paramLong, this.error);
    int i = this.networkInfos.size();
    paramHwBlob.putInt32(paramLong + 8L + 8L, i);
    paramHwBlob.putBool(paramLong + 8L + 12L, false);
    HwBlob hwBlob = new HwBlob(i * 104);
    for (byte b = 0; b < i; b++)
      ((CellInfo)this.networkInfos.get(b)).writeEmbeddedToBlob(hwBlob, (b * 104)); 
    paramHwBlob.putBlob(8L + paramLong + 0L, hwBlob);
  }
  
  public final void writeToParcel(HwParcel paramHwParcel) {
    HwBlob hwBlob = new HwBlob(24);
    writeEmbeddedToBlob(hwBlob, 0L);
    paramHwParcel.writeBuffer(hwBlob);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/V1_1/NetworkScanResult.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */