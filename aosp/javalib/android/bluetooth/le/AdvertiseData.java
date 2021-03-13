package android.bluetooth.le;

import android.os.Parcel;
import android.os.ParcelUuid;
import android.os.Parcelable;
import android.util.ArrayMap;
import android.util.SparseArray;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public final class AdvertiseData implements Parcelable {
  public static final Parcelable.Creator<AdvertiseData> CREATOR = new Parcelable.Creator<AdvertiseData>() {
      public AdvertiseData createFromParcel(Parcel param1Parcel) {
        AdvertiseData.Builder builder = new AdvertiseData.Builder();
        Iterator<ParcelUuid> iterator = param1Parcel.createTypedArrayList(ParcelUuid.CREATOR).iterator();
        while (iterator.hasNext())
          builder.addServiceUuid(iterator.next()); 
        int i = param1Parcel.readInt();
        byte b;
        for (b = 0; b < i; b++)
          builder.addManufacturerData(param1Parcel.readInt(), param1Parcel.createByteArray()); 
        i = param1Parcel.readInt();
        for (b = 0; b < i; b++)
          builder.addServiceData((ParcelUuid)param1Parcel.readTypedObject(ParcelUuid.CREATOR), param1Parcel.createByteArray()); 
        b = param1Parcel.readByte();
        boolean bool1 = false;
        if (b == 1) {
          bool2 = true;
        } else {
          bool2 = false;
        } 
        builder.setIncludeTxPowerLevel(bool2);
        boolean bool2 = bool1;
        if (param1Parcel.readByte() == 1)
          bool2 = true; 
        builder.setIncludeDeviceName(bool2);
        return builder.build();
      }
      
      public AdvertiseData[] newArray(int param1Int) {
        return new AdvertiseData[param1Int];
      }
    };
  
  private final boolean mIncludeDeviceName;
  
  private final boolean mIncludeTxPowerLevel;
  
  private final SparseArray<byte[]> mManufacturerSpecificData;
  
  private final Map<ParcelUuid, byte[]> mServiceData;
  
  private final List<ParcelUuid> mServiceUuids;
  
  private AdvertiseData(List<ParcelUuid> paramList, SparseArray<byte[]> paramSparseArray, Map<ParcelUuid, byte[]> paramMap, boolean paramBoolean1, boolean paramBoolean2) {
    this.mServiceUuids = paramList;
    this.mManufacturerSpecificData = paramSparseArray;
    this.mServiceData = paramMap;
    this.mIncludeTxPowerLevel = paramBoolean1;
    this.mIncludeDeviceName = paramBoolean2;
  }
  
  public int describeContents() {
    return 0;
  }
  
  public boolean equals(Object paramObject) {
    boolean bool = true;
    if (this == paramObject)
      return true; 
    if (paramObject == null || getClass() != paramObject.getClass())
      return false; 
    paramObject = paramObject;
    if (!Objects.equals(this.mServiceUuids, ((AdvertiseData)paramObject).mServiceUuids) || !BluetoothLeUtils.equals(this.mManufacturerSpecificData, ((AdvertiseData)paramObject).mManufacturerSpecificData) || !BluetoothLeUtils.equals(this.mServiceData, ((AdvertiseData)paramObject).mServiceData) || this.mIncludeDeviceName != ((AdvertiseData)paramObject).mIncludeDeviceName || this.mIncludeTxPowerLevel != ((AdvertiseData)paramObject).mIncludeTxPowerLevel)
      bool = false; 
    return bool;
  }
  
  public boolean getIncludeDeviceName() {
    return this.mIncludeDeviceName;
  }
  
  public boolean getIncludeTxPowerLevel() {
    return this.mIncludeTxPowerLevel;
  }
  
  public SparseArray<byte[]> getManufacturerSpecificData() {
    return this.mManufacturerSpecificData;
  }
  
  public Map<ParcelUuid, byte[]> getServiceData() {
    return this.mServiceData;
  }
  
  public List<ParcelUuid> getServiceUuids() {
    return this.mServiceUuids;
  }
  
  public int hashCode() {
    return Objects.hash(new Object[] { this.mServiceUuids, this.mManufacturerSpecificData, this.mServiceData, Boolean.valueOf(this.mIncludeDeviceName), Boolean.valueOf(this.mIncludeTxPowerLevel) });
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("AdvertiseData [mServiceUuids=");
    stringBuilder.append(this.mServiceUuids);
    stringBuilder.append(", mManufacturerSpecificData=");
    stringBuilder.append(BluetoothLeUtils.toString(this.mManufacturerSpecificData));
    stringBuilder.append(", mServiceData=");
    stringBuilder.append(BluetoothLeUtils.toString(this.mServiceData));
    stringBuilder.append(", mIncludeTxPowerLevel=");
    stringBuilder.append(this.mIncludeTxPowerLevel);
    stringBuilder.append(", mIncludeDeviceName=");
    stringBuilder.append(this.mIncludeDeviceName);
    stringBuilder.append("]");
    return stringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    List<ParcelUuid> list = this.mServiceUuids;
    paramParcel.writeTypedArray((Parcelable[])list.<ParcelUuid>toArray(new ParcelUuid[list.size()]), paramInt);
    paramParcel.writeInt(this.mManufacturerSpecificData.size());
    for (byte b = 0; b < this.mManufacturerSpecificData.size(); b++) {
      paramParcel.writeInt(this.mManufacturerSpecificData.keyAt(b));
      paramParcel.writeByteArray((byte[])this.mManufacturerSpecificData.valueAt(b));
    } 
    paramParcel.writeInt(this.mServiceData.size());
    for (ParcelUuid parcelUuid : this.mServiceData.keySet()) {
      paramParcel.writeTypedObject((Parcelable)parcelUuid, paramInt);
      paramParcel.writeByteArray(this.mServiceData.get(parcelUuid));
    } 
    paramParcel.writeByte((byte)getIncludeTxPowerLevel());
    paramParcel.writeByte((byte)getIncludeDeviceName());
  }
  
  public static final class Builder {
    private boolean mIncludeDeviceName;
    
    private boolean mIncludeTxPowerLevel;
    
    private SparseArray<byte[]> mManufacturerSpecificData = new SparseArray();
    
    private Map<ParcelUuid, byte[]> mServiceData = (Map<ParcelUuid, byte[]>)new ArrayMap();
    
    private List<ParcelUuid> mServiceUuids = new ArrayList<>();
    
    public Builder addManufacturerData(int param1Int, byte[] param1ArrayOfbyte) {
      if (param1Int >= 0) {
        if (param1ArrayOfbyte != null) {
          this.mManufacturerSpecificData.put(param1Int, param1ArrayOfbyte);
          return this;
        } 
        throw new IllegalArgumentException("manufacturerSpecificData is null");
      } 
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("invalid manufacturerId - ");
      stringBuilder.append(param1Int);
      throw new IllegalArgumentException(stringBuilder.toString());
    }
    
    public Builder addServiceData(ParcelUuid param1ParcelUuid, byte[] param1ArrayOfbyte) {
      if (param1ParcelUuid != null && param1ArrayOfbyte != null) {
        this.mServiceData.put(param1ParcelUuid, param1ArrayOfbyte);
        return this;
      } 
      throw new IllegalArgumentException("serviceDataUuid or serviceDataUuid is null");
    }
    
    public Builder addServiceUuid(ParcelUuid param1ParcelUuid) {
      if (param1ParcelUuid != null) {
        this.mServiceUuids.add(param1ParcelUuid);
        return this;
      } 
      throw new IllegalArgumentException("serivceUuids are null");
    }
    
    public AdvertiseData build() {
      return new AdvertiseData(this.mServiceUuids, this.mManufacturerSpecificData, this.mServiceData, this.mIncludeTxPowerLevel, this.mIncludeDeviceName);
    }
    
    public Builder setIncludeDeviceName(boolean param1Boolean) {
      this.mIncludeDeviceName = param1Boolean;
      return this;
    }
    
    public Builder setIncludeTxPowerLevel(boolean param1Boolean) {
      this.mIncludeTxPowerLevel = param1Boolean;
      return this;
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/le/AdvertiseData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */