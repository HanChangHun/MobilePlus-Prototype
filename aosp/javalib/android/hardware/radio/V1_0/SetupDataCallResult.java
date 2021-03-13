package android.hardware.radio.V1_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

public final class SetupDataCallResult {
  public int active = 0;
  
  public String addresses = new String();
  
  public int cid = 0;
  
  public String dnses = new String();
  
  public String gateways = new String();
  
  public String ifname = new String();
  
  public int mtu = 0;
  
  public String pcscf = new String();
  
  public int status = 0;
  
  public int suggestedRetryTime = 0;
  
  public String type = new String();
  
  public static final ArrayList<SetupDataCallResult> readVectorFromParcel(HwParcel paramHwParcel) {
    ArrayList<SetupDataCallResult> arrayList = new ArrayList();
    HwBlob hwBlob = paramHwParcel.readBuffer(16L);
    int i = hwBlob.getInt32(8L);
    hwBlob = paramHwParcel.readEmbeddedBuffer((i * 120), hwBlob.handle(), 0L, true);
    arrayList.clear();
    for (byte b = 0; b < i; b++) {
      SetupDataCallResult setupDataCallResult = new SetupDataCallResult();
      setupDataCallResult.readEmbeddedFromParcel(paramHwParcel, hwBlob, (b * 120));
      arrayList.add(setupDataCallResult);
    } 
    return arrayList;
  }
  
  public static final void writeVectorToParcel(HwParcel paramHwParcel, ArrayList<SetupDataCallResult> paramArrayList) {
    HwBlob hwBlob1 = new HwBlob(16);
    int i = paramArrayList.size();
    hwBlob1.putInt32(8L, i);
    hwBlob1.putBool(12L, false);
    HwBlob hwBlob2 = new HwBlob(i * 120);
    for (byte b = 0; b < i; b++)
      ((SetupDataCallResult)paramArrayList.get(b)).writeEmbeddedToBlob(hwBlob2, (b * 120)); 
    hwBlob1.putBlob(0L, hwBlob2);
    paramHwParcel.writeBuffer(hwBlob1);
  }
  
  public final boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (paramObject == null)
      return false; 
    if (paramObject.getClass() != SetupDataCallResult.class)
      return false; 
    paramObject = paramObject;
    return (this.status != ((SetupDataCallResult)paramObject).status) ? false : ((this.suggestedRetryTime != ((SetupDataCallResult)paramObject).suggestedRetryTime) ? false : ((this.cid != ((SetupDataCallResult)paramObject).cid) ? false : ((this.active != ((SetupDataCallResult)paramObject).active) ? false : (!HidlSupport.deepEquals(this.type, ((SetupDataCallResult)paramObject).type) ? false : (!HidlSupport.deepEquals(this.ifname, ((SetupDataCallResult)paramObject).ifname) ? false : (!HidlSupport.deepEquals(this.addresses, ((SetupDataCallResult)paramObject).addresses) ? false : (!HidlSupport.deepEquals(this.dnses, ((SetupDataCallResult)paramObject).dnses) ? false : (!HidlSupport.deepEquals(this.gateways, ((SetupDataCallResult)paramObject).gateways) ? false : (!HidlSupport.deepEquals(this.pcscf, ((SetupDataCallResult)paramObject).pcscf) ? false : (!(this.mtu != ((SetupDataCallResult)paramObject).mtu)))))))))));
  }
  
  public final int hashCode() {
    return Objects.hash(new Object[] { 
          Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.status))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.suggestedRetryTime))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.cid))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.active))), Integer.valueOf(HidlSupport.deepHashCode(this.type)), Integer.valueOf(HidlSupport.deepHashCode(this.ifname)), Integer.valueOf(HidlSupport.deepHashCode(this.addresses)), Integer.valueOf(HidlSupport.deepHashCode(this.dnses)), Integer.valueOf(HidlSupport.deepHashCode(this.gateways)), Integer.valueOf(HidlSupport.deepHashCode(this.pcscf)), 
          Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.mtu))) });
  }
  
  public final void readEmbeddedFromParcel(HwParcel paramHwParcel, HwBlob paramHwBlob, long paramLong) {
    this.status = paramHwBlob.getInt32(paramLong + 0L);
    this.suggestedRetryTime = paramHwBlob.getInt32(paramLong + 4L);
    this.cid = paramHwBlob.getInt32(paramLong + 8L);
    this.active = paramHwBlob.getInt32(paramLong + 12L);
    String str = paramHwBlob.getString(paramLong + 16L);
    this.type = str;
    paramHwParcel.readEmbeddedBuffer(((str.getBytes()).length + 1), paramHwBlob.handle(), paramLong + 16L + 0L, false);
    str = paramHwBlob.getString(paramLong + 32L);
    this.ifname = str;
    paramHwParcel.readEmbeddedBuffer(((str.getBytes()).length + 1), paramHwBlob.handle(), paramLong + 32L + 0L, false);
    str = paramHwBlob.getString(paramLong + 48L);
    this.addresses = str;
    paramHwParcel.readEmbeddedBuffer(((str.getBytes()).length + 1), paramHwBlob.handle(), paramLong + 48L + 0L, false);
    str = paramHwBlob.getString(paramLong + 64L);
    this.dnses = str;
    paramHwParcel.readEmbeddedBuffer(((str.getBytes()).length + 1), paramHwBlob.handle(), paramLong + 64L + 0L, false);
    str = paramHwBlob.getString(paramLong + 80L);
    this.gateways = str;
    paramHwParcel.readEmbeddedBuffer(((str.getBytes()).length + 1), paramHwBlob.handle(), paramLong + 80L + 0L, false);
    str = paramHwBlob.getString(paramLong + 96L);
    this.pcscf = str;
    paramHwParcel.readEmbeddedBuffer(((str.getBytes()).length + 1), paramHwBlob.handle(), paramLong + 96L + 0L, false);
    this.mtu = paramHwBlob.getInt32(paramLong + 112L);
  }
  
  public final void readFromParcel(HwParcel paramHwParcel) {
    readEmbeddedFromParcel(paramHwParcel, paramHwParcel.readBuffer(120L), 0L);
  }
  
  public final String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("{");
    stringBuilder.append(".status = ");
    stringBuilder.append(DataCallFailCause.toString(this.status));
    stringBuilder.append(", .suggestedRetryTime = ");
    stringBuilder.append(this.suggestedRetryTime);
    stringBuilder.append(", .cid = ");
    stringBuilder.append(this.cid);
    stringBuilder.append(", .active = ");
    stringBuilder.append(this.active);
    stringBuilder.append(", .type = ");
    stringBuilder.append(this.type);
    stringBuilder.append(", .ifname = ");
    stringBuilder.append(this.ifname);
    stringBuilder.append(", .addresses = ");
    stringBuilder.append(this.addresses);
    stringBuilder.append(", .dnses = ");
    stringBuilder.append(this.dnses);
    stringBuilder.append(", .gateways = ");
    stringBuilder.append(this.gateways);
    stringBuilder.append(", .pcscf = ");
    stringBuilder.append(this.pcscf);
    stringBuilder.append(", .mtu = ");
    stringBuilder.append(this.mtu);
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public final void writeEmbeddedToBlob(HwBlob paramHwBlob, long paramLong) {
    paramHwBlob.putInt32(0L + paramLong, this.status);
    paramHwBlob.putInt32(4L + paramLong, this.suggestedRetryTime);
    paramHwBlob.putInt32(8L + paramLong, this.cid);
    paramHwBlob.putInt32(12L + paramLong, this.active);
    paramHwBlob.putString(16L + paramLong, this.type);
    paramHwBlob.putString(32L + paramLong, this.ifname);
    paramHwBlob.putString(48L + paramLong, this.addresses);
    paramHwBlob.putString(64L + paramLong, this.dnses);
    paramHwBlob.putString(80L + paramLong, this.gateways);
    paramHwBlob.putString(96L + paramLong, this.pcscf);
    paramHwBlob.putInt32(112L + paramLong, this.mtu);
  }
  
  public final void writeToParcel(HwParcel paramHwParcel) {
    HwBlob hwBlob = new HwBlob(120);
    writeEmbeddedToBlob(hwBlob, 0L);
    paramHwParcel.writeBuffer(hwBlob);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/V1_0/SetupDataCallResult.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */