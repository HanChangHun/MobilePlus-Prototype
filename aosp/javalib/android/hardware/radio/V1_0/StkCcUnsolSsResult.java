package android.hardware.radio.V1_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

public final class StkCcUnsolSsResult {
  public ArrayList<CfData> cfData = new ArrayList<>();
  
  public int requestType = 0;
  
  public int result = 0;
  
  public int serviceClass;
  
  public int serviceType = 0;
  
  public ArrayList<SsInfoData> ssInfo = new ArrayList<>();
  
  public int teleserviceType = 0;
  
  public static final ArrayList<StkCcUnsolSsResult> readVectorFromParcel(HwParcel paramHwParcel) {
    ArrayList<StkCcUnsolSsResult> arrayList = new ArrayList();
    HwBlob hwBlob1 = paramHwParcel.readBuffer(16L);
    int i = hwBlob1.getInt32(8L);
    HwBlob hwBlob2 = paramHwParcel.readEmbeddedBuffer((i * 56), hwBlob1.handle(), 0L, true);
    arrayList.clear();
    for (byte b = 0; b < i; b++) {
      StkCcUnsolSsResult stkCcUnsolSsResult = new StkCcUnsolSsResult();
      stkCcUnsolSsResult.readEmbeddedFromParcel(paramHwParcel, hwBlob2, (b * 56));
      arrayList.add(stkCcUnsolSsResult);
    } 
    return arrayList;
  }
  
  public static final void writeVectorToParcel(HwParcel paramHwParcel, ArrayList<StkCcUnsolSsResult> paramArrayList) {
    HwBlob hwBlob1 = new HwBlob(16);
    int i = paramArrayList.size();
    hwBlob1.putInt32(8L, i);
    hwBlob1.putBool(12L, false);
    HwBlob hwBlob2 = new HwBlob(i * 56);
    for (byte b = 0; b < i; b++)
      ((StkCcUnsolSsResult)paramArrayList.get(b)).writeEmbeddedToBlob(hwBlob2, (b * 56)); 
    hwBlob1.putBlob(0L, hwBlob2);
    paramHwParcel.writeBuffer(hwBlob1);
  }
  
  public final boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (paramObject == null)
      return false; 
    if (paramObject.getClass() != StkCcUnsolSsResult.class)
      return false; 
    paramObject = paramObject;
    return (this.serviceType != ((StkCcUnsolSsResult)paramObject).serviceType) ? false : ((this.requestType != ((StkCcUnsolSsResult)paramObject).requestType) ? false : ((this.teleserviceType != ((StkCcUnsolSsResult)paramObject).teleserviceType) ? false : (!HidlSupport.deepEquals(Integer.valueOf(this.serviceClass), Integer.valueOf(((StkCcUnsolSsResult)paramObject).serviceClass)) ? false : ((this.result != ((StkCcUnsolSsResult)paramObject).result) ? false : (!HidlSupport.deepEquals(this.ssInfo, ((StkCcUnsolSsResult)paramObject).ssInfo) ? false : (!!HidlSupport.deepEquals(this.cfData, ((StkCcUnsolSsResult)paramObject).cfData)))))));
  }
  
  public final int hashCode() {
    return Objects.hash(new Object[] { Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.serviceType))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.requestType))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.teleserviceType))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.serviceClass))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.result))), Integer.valueOf(HidlSupport.deepHashCode(this.ssInfo)), Integer.valueOf(HidlSupport.deepHashCode(this.cfData)) });
  }
  
  public final void readEmbeddedFromParcel(HwParcel paramHwParcel, HwBlob paramHwBlob, long paramLong) {
    this.serviceType = paramHwBlob.getInt32(paramLong + 0L);
    this.requestType = paramHwBlob.getInt32(paramLong + 4L);
    this.teleserviceType = paramHwBlob.getInt32(paramLong + 8L);
    this.serviceClass = paramHwBlob.getInt32(paramLong + 12L);
    this.result = paramHwBlob.getInt32(paramLong + 16L);
    int i = paramHwBlob.getInt32(paramLong + 24L + 8L);
    HwBlob hwBlob1 = paramHwParcel.readEmbeddedBuffer((i * 16), paramHwBlob.handle(), paramLong + 24L + 0L, true);
    this.ssInfo.clear();
    byte b;
    for (b = 0; b < i; b++) {
      SsInfoData ssInfoData = new SsInfoData();
      ssInfoData.readEmbeddedFromParcel(paramHwParcel, hwBlob1, (b * 16));
      this.ssInfo.add(ssInfoData);
    } 
    i = paramHwBlob.getInt32(paramLong + 40L + 8L);
    HwBlob hwBlob2 = paramHwParcel.readEmbeddedBuffer((i * 16), paramHwBlob.handle(), paramLong + 40L + 0L, true);
    this.cfData.clear();
    for (b = 0; b < i; b++) {
      CfData cfData = new CfData();
      cfData.readEmbeddedFromParcel(paramHwParcel, hwBlob2, (b * 16));
      this.cfData.add(cfData);
    } 
  }
  
  public final void readFromParcel(HwParcel paramHwParcel) {
    readEmbeddedFromParcel(paramHwParcel, paramHwParcel.readBuffer(56L), 0L);
  }
  
  public final String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("{");
    stringBuilder.append(".serviceType = ");
    stringBuilder.append(SsServiceType.toString(this.serviceType));
    stringBuilder.append(", .requestType = ");
    stringBuilder.append(SsRequestType.toString(this.requestType));
    stringBuilder.append(", .teleserviceType = ");
    stringBuilder.append(SsTeleserviceType.toString(this.teleserviceType));
    stringBuilder.append(", .serviceClass = ");
    stringBuilder.append(SuppServiceClass.dumpBitfield(this.serviceClass));
    stringBuilder.append(", .result = ");
    stringBuilder.append(RadioError.toString(this.result));
    stringBuilder.append(", .ssInfo = ");
    stringBuilder.append(this.ssInfo);
    stringBuilder.append(", .cfData = ");
    stringBuilder.append(this.cfData);
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public final void writeEmbeddedToBlob(HwBlob paramHwBlob, long paramLong) {
    paramHwBlob.putInt32(paramLong + 0L, this.serviceType);
    paramHwBlob.putInt32(paramLong + 4L, this.requestType);
    paramHwBlob.putInt32(paramLong + 8L, this.teleserviceType);
    paramHwBlob.putInt32(paramLong + 12L, this.serviceClass);
    paramHwBlob.putInt32(paramLong + 16L, this.result);
    int i = this.ssInfo.size();
    paramHwBlob.putInt32(paramLong + 24L + 8L, i);
    paramHwBlob.putBool(paramLong + 24L + 12L, false);
    HwBlob hwBlob = new HwBlob(i * 16);
    byte b;
    for (b = 0; b < i; b++)
      ((SsInfoData)this.ssInfo.get(b)).writeEmbeddedToBlob(hwBlob, (b * 16)); 
    paramHwBlob.putBlob(paramLong + 24L + 0L, hwBlob);
    i = this.cfData.size();
    paramHwBlob.putInt32(paramLong + 40L + 8L, i);
    paramHwBlob.putBool(paramLong + 40L + 12L, false);
    hwBlob = new HwBlob(i * 16);
    for (b = 0; b < i; b++)
      ((CfData)this.cfData.get(b)).writeEmbeddedToBlob(hwBlob, (b * 16)); 
    paramHwBlob.putBlob(paramLong + 40L + 0L, hwBlob);
  }
  
  public final void writeToParcel(HwParcel paramHwParcel) {
    HwBlob hwBlob = new HwBlob(56);
    writeEmbeddedToBlob(hwBlob, 0L);
    paramHwParcel.writeBuffer(hwBlob);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/V1_0/StkCcUnsolSsResult.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */