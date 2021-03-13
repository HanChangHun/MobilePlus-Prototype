package android.bluetooth.le;

import android.os.ParcelUuid;
import android.util.ArrayMap;
import android.util.SparseArray;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class Builder {
  private boolean mIncludeDeviceName;
  
  private boolean mIncludeTxPowerLevel;
  
  private SparseArray<byte[]> mManufacturerSpecificData = new SparseArray();
  
  private Map<ParcelUuid, byte[]> mServiceData = (Map<ParcelUuid, byte[]>)new ArrayMap();
  
  private List<ParcelUuid> mServiceUuids = new ArrayList<>();
  
  public Builder addManufacturerData(int paramInt, byte[] paramArrayOfbyte) {
    if (paramInt >= 0) {
      if (paramArrayOfbyte != null) {
        this.mManufacturerSpecificData.put(paramInt, paramArrayOfbyte);
        return this;
      } 
      throw new IllegalArgumentException("manufacturerSpecificData is null");
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("invalid manufacturerId - ");
    stringBuilder.append(paramInt);
    throw new IllegalArgumentException(stringBuilder.toString());
  }
  
  public Builder addServiceData(ParcelUuid paramParcelUuid, byte[] paramArrayOfbyte) {
    if (paramParcelUuid != null && paramArrayOfbyte != null) {
      this.mServiceData.put(paramParcelUuid, paramArrayOfbyte);
      return this;
    } 
    throw new IllegalArgumentException("serviceDataUuid or serviceDataUuid is null");
  }
  
  public Builder addServiceUuid(ParcelUuid paramParcelUuid) {
    if (paramParcelUuid != null) {
      this.mServiceUuids.add(paramParcelUuid);
      return this;
    } 
    throw new IllegalArgumentException("serivceUuids are null");
  }
  
  public AdvertiseData build() {
    return new AdvertiseData(this.mServiceUuids, this.mManufacturerSpecificData, this.mServiceData, this.mIncludeTxPowerLevel, this.mIncludeDeviceName, null);
  }
  
  public Builder setIncludeDeviceName(boolean paramBoolean) {
    this.mIncludeDeviceName = paramBoolean;
    return this;
  }
  
  public Builder setIncludeTxPowerLevel(boolean paramBoolean) {
    this.mIncludeTxPowerLevel = paramBoolean;
    return this;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/le/AdvertiseData$Builder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */