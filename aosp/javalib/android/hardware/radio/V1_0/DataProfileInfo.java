package android.hardware.radio.V1_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

public final class DataProfileInfo {
  public String apn = new String();
  
  public int authType = 0;
  
  public int bearerBitmap;
  
  public boolean enabled = false;
  
  public int maxConns = 0;
  
  public int maxConnsTime = 0;
  
  public int mtu = 0;
  
  public String mvnoMatchData = new String();
  
  public int mvnoType = 0;
  
  public String password = new String();
  
  public int profileId = 0;
  
  public String protocol = new String();
  
  public String roamingProtocol = new String();
  
  public int supportedApnTypesBitmap;
  
  public int type = 0;
  
  public String user = new String();
  
  public int waitTime = 0;
  
  public static final ArrayList<DataProfileInfo> readVectorFromParcel(HwParcel paramHwParcel) {
    ArrayList<DataProfileInfo> arrayList = new ArrayList();
    HwBlob hwBlob = paramHwParcel.readBuffer(16L);
    int i = hwBlob.getInt32(8L);
    hwBlob = paramHwParcel.readEmbeddedBuffer((i * 152), hwBlob.handle(), 0L, true);
    arrayList.clear();
    for (byte b = 0; b < i; b++) {
      DataProfileInfo dataProfileInfo = new DataProfileInfo();
      dataProfileInfo.readEmbeddedFromParcel(paramHwParcel, hwBlob, (b * 152));
      arrayList.add(dataProfileInfo);
    } 
    return arrayList;
  }
  
  public static final void writeVectorToParcel(HwParcel paramHwParcel, ArrayList<DataProfileInfo> paramArrayList) {
    HwBlob hwBlob1 = new HwBlob(16);
    int i = paramArrayList.size();
    hwBlob1.putInt32(8L, i);
    hwBlob1.putBool(12L, false);
    HwBlob hwBlob2 = new HwBlob(i * 152);
    for (byte b = 0; b < i; b++)
      ((DataProfileInfo)paramArrayList.get(b)).writeEmbeddedToBlob(hwBlob2, (b * 152)); 
    hwBlob1.putBlob(0L, hwBlob2);
    paramHwParcel.writeBuffer(hwBlob1);
  }
  
  public final boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (paramObject == null)
      return false; 
    if (paramObject.getClass() != DataProfileInfo.class)
      return false; 
    paramObject = paramObject;
    return (this.profileId != ((DataProfileInfo)paramObject).profileId) ? false : (!HidlSupport.deepEquals(this.apn, ((DataProfileInfo)paramObject).apn) ? false : (!HidlSupport.deepEquals(this.protocol, ((DataProfileInfo)paramObject).protocol) ? false : (!HidlSupport.deepEquals(this.roamingProtocol, ((DataProfileInfo)paramObject).roamingProtocol) ? false : ((this.authType != ((DataProfileInfo)paramObject).authType) ? false : (!HidlSupport.deepEquals(this.user, ((DataProfileInfo)paramObject).user) ? false : (!HidlSupport.deepEquals(this.password, ((DataProfileInfo)paramObject).password) ? false : ((this.type != ((DataProfileInfo)paramObject).type) ? false : ((this.maxConnsTime != ((DataProfileInfo)paramObject).maxConnsTime) ? false : ((this.maxConns != ((DataProfileInfo)paramObject).maxConns) ? false : ((this.waitTime != ((DataProfileInfo)paramObject).waitTime) ? false : ((this.enabled != ((DataProfileInfo)paramObject).enabled) ? false : (!HidlSupport.deepEquals(Integer.valueOf(this.supportedApnTypesBitmap), Integer.valueOf(((DataProfileInfo)paramObject).supportedApnTypesBitmap)) ? false : (!HidlSupport.deepEquals(Integer.valueOf(this.bearerBitmap), Integer.valueOf(((DataProfileInfo)paramObject).bearerBitmap)) ? false : ((this.mtu != ((DataProfileInfo)paramObject).mtu) ? false : ((this.mvnoType != ((DataProfileInfo)paramObject).mvnoType) ? false : (!!HidlSupport.deepEquals(this.mvnoMatchData, ((DataProfileInfo)paramObject).mvnoMatchData)))))))))))))))));
  }
  
  public final int hashCode() {
    return Objects.hash(new Object[] { 
          Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.profileId))), Integer.valueOf(HidlSupport.deepHashCode(this.apn)), Integer.valueOf(HidlSupport.deepHashCode(this.protocol)), Integer.valueOf(HidlSupport.deepHashCode(this.roamingProtocol)), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.authType))), Integer.valueOf(HidlSupport.deepHashCode(this.user)), Integer.valueOf(HidlSupport.deepHashCode(this.password)), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.type))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.maxConnsTime))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.maxConns))), 
          Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.waitTime))), Integer.valueOf(HidlSupport.deepHashCode(Boolean.valueOf(this.enabled))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.supportedApnTypesBitmap))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.bearerBitmap))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.mtu))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.mvnoType))), Integer.valueOf(HidlSupport.deepHashCode(this.mvnoMatchData)) });
  }
  
  public final void readEmbeddedFromParcel(HwParcel paramHwParcel, HwBlob paramHwBlob, long paramLong) {
    this.profileId = paramHwBlob.getInt32(paramLong + 0L);
    String str = paramHwBlob.getString(paramLong + 8L);
    this.apn = str;
    paramHwParcel.readEmbeddedBuffer(((str.getBytes()).length + 1), paramHwBlob.handle(), paramLong + 8L + 0L, false);
    str = paramHwBlob.getString(paramLong + 24L);
    this.protocol = str;
    paramHwParcel.readEmbeddedBuffer(((str.getBytes()).length + 1), paramHwBlob.handle(), paramLong + 24L + 0L, false);
    str = paramHwBlob.getString(paramLong + 40L);
    this.roamingProtocol = str;
    paramHwParcel.readEmbeddedBuffer(((str.getBytes()).length + 1), paramHwBlob.handle(), paramLong + 40L + 0L, false);
    this.authType = paramHwBlob.getInt32(paramLong + 56L);
    str = paramHwBlob.getString(paramLong + 64L);
    this.user = str;
    paramHwParcel.readEmbeddedBuffer(((str.getBytes()).length + 1), paramHwBlob.handle(), paramLong + 64L + 0L, false);
    str = paramHwBlob.getString(paramLong + 80L);
    this.password = str;
    paramHwParcel.readEmbeddedBuffer(((str.getBytes()).length + 1), paramHwBlob.handle(), paramLong + 80L + 0L, false);
    this.type = paramHwBlob.getInt32(paramLong + 96L);
    this.maxConnsTime = paramHwBlob.getInt32(paramLong + 100L);
    this.maxConns = paramHwBlob.getInt32(paramLong + 104L);
    this.waitTime = paramHwBlob.getInt32(paramLong + 108L);
    this.enabled = paramHwBlob.getBool(paramLong + 112L);
    this.supportedApnTypesBitmap = paramHwBlob.getInt32(paramLong + 116L);
    this.bearerBitmap = paramHwBlob.getInt32(paramLong + 120L);
    this.mtu = paramHwBlob.getInt32(paramLong + 124L);
    this.mvnoType = paramHwBlob.getInt32(paramLong + 128L);
    str = paramHwBlob.getString(paramLong + 136L);
    this.mvnoMatchData = str;
    paramHwParcel.readEmbeddedBuffer(((str.getBytes()).length + 1), paramHwBlob.handle(), paramLong + 136L + 0L, false);
  }
  
  public final void readFromParcel(HwParcel paramHwParcel) {
    readEmbeddedFromParcel(paramHwParcel, paramHwParcel.readBuffer(152L), 0L);
  }
  
  public final String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("{");
    stringBuilder.append(".profileId = ");
    stringBuilder.append(DataProfileId.toString(this.profileId));
    stringBuilder.append(", .apn = ");
    stringBuilder.append(this.apn);
    stringBuilder.append(", .protocol = ");
    stringBuilder.append(this.protocol);
    stringBuilder.append(", .roamingProtocol = ");
    stringBuilder.append(this.roamingProtocol);
    stringBuilder.append(", .authType = ");
    stringBuilder.append(ApnAuthType.toString(this.authType));
    stringBuilder.append(", .user = ");
    stringBuilder.append(this.user);
    stringBuilder.append(", .password = ");
    stringBuilder.append(this.password);
    stringBuilder.append(", .type = ");
    stringBuilder.append(DataProfileInfoType.toString(this.type));
    stringBuilder.append(", .maxConnsTime = ");
    stringBuilder.append(this.maxConnsTime);
    stringBuilder.append(", .maxConns = ");
    stringBuilder.append(this.maxConns);
    stringBuilder.append(", .waitTime = ");
    stringBuilder.append(this.waitTime);
    stringBuilder.append(", .enabled = ");
    stringBuilder.append(this.enabled);
    stringBuilder.append(", .supportedApnTypesBitmap = ");
    stringBuilder.append(ApnTypes.dumpBitfield(this.supportedApnTypesBitmap));
    stringBuilder.append(", .bearerBitmap = ");
    stringBuilder.append(RadioAccessFamily.dumpBitfield(this.bearerBitmap));
    stringBuilder.append(", .mtu = ");
    stringBuilder.append(this.mtu);
    stringBuilder.append(", .mvnoType = ");
    stringBuilder.append(MvnoType.toString(this.mvnoType));
    stringBuilder.append(", .mvnoMatchData = ");
    stringBuilder.append(this.mvnoMatchData);
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public final void writeEmbeddedToBlob(HwBlob paramHwBlob, long paramLong) {
    paramHwBlob.putInt32(0L + paramLong, this.profileId);
    paramHwBlob.putString(8L + paramLong, this.apn);
    paramHwBlob.putString(24L + paramLong, this.protocol);
    paramHwBlob.putString(40L + paramLong, this.roamingProtocol);
    paramHwBlob.putInt32(56L + paramLong, this.authType);
    paramHwBlob.putString(64L + paramLong, this.user);
    paramHwBlob.putString(80L + paramLong, this.password);
    paramHwBlob.putInt32(96L + paramLong, this.type);
    paramHwBlob.putInt32(100L + paramLong, this.maxConnsTime);
    paramHwBlob.putInt32(104L + paramLong, this.maxConns);
    paramHwBlob.putInt32(108L + paramLong, this.waitTime);
    paramHwBlob.putBool(112L + paramLong, this.enabled);
    paramHwBlob.putInt32(116L + paramLong, this.supportedApnTypesBitmap);
    paramHwBlob.putInt32(120L + paramLong, this.bearerBitmap);
    paramHwBlob.putInt32(124L + paramLong, this.mtu);
    paramHwBlob.putInt32(128L + paramLong, this.mvnoType);
    paramHwBlob.putString(136L + paramLong, this.mvnoMatchData);
  }
  
  public final void writeToParcel(HwParcel paramHwParcel) {
    HwBlob hwBlob = new HwBlob(152);
    writeEmbeddedToBlob(hwBlob, 0L);
    paramHwParcel.writeBuffer(hwBlob);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/V1_0/DataProfileInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */