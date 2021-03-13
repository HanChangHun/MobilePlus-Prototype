package android.companion;

import android.bluetooth.BluetoothDevice;
import android.os.Parcel;
import android.os.ParcelUuid;
import android.os.Parcelable;
import android.provider.OneTimeUseBuilder;
import com.android.internal.util.ArrayUtils;
import com.android.internal.util.CollectionUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

public final class BluetoothDeviceFilter implements DeviceFilter<BluetoothDevice> {
  public static final Parcelable.Creator<BluetoothDeviceFilter> CREATOR = new Parcelable.Creator<BluetoothDeviceFilter>() {
      public BluetoothDeviceFilter createFromParcel(Parcel param1Parcel) {
        return new BluetoothDeviceFilter(param1Parcel);
      }
      
      public BluetoothDeviceFilter[] newArray(int param1Int) {
        return new BluetoothDeviceFilter[param1Int];
      }
    };
  
  private final String mAddress;
  
  private final Pattern mNamePattern;
  
  private final List<ParcelUuid> mServiceUuidMasks;
  
  private final List<ParcelUuid> mServiceUuids;
  
  private BluetoothDeviceFilter(Parcel paramParcel) {
    this(BluetoothDeviceFilterUtils.patternFromString(paramParcel.readString()), paramParcel.readString(), readUuids(paramParcel), readUuids(paramParcel));
  }
  
  private BluetoothDeviceFilter(Pattern paramPattern, String paramString, List<ParcelUuid> paramList1, List<ParcelUuid> paramList2) {
    this.mNamePattern = paramPattern;
    this.mAddress = paramString;
    this.mServiceUuids = CollectionUtils.emptyIfNull(paramList1);
    this.mServiceUuidMasks = CollectionUtils.emptyIfNull(paramList2);
  }
  
  private static List<ParcelUuid> readUuids(Parcel paramParcel) {
    return paramParcel.readParcelableList(new ArrayList(), ParcelUuid.class.getClassLoader());
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
    if (!Objects.equals(this.mNamePattern, ((BluetoothDeviceFilter)paramObject).mNamePattern) || !Objects.equals(this.mAddress, ((BluetoothDeviceFilter)paramObject).mAddress) || !Objects.equals(this.mServiceUuids, ((BluetoothDeviceFilter)paramObject).mServiceUuids) || !Objects.equals(this.mServiceUuidMasks, ((BluetoothDeviceFilter)paramObject).mServiceUuidMasks))
      bool = false; 
    return bool;
  }
  
  public String getAddress() {
    return this.mAddress;
  }
  
  public String getDeviceDisplayName(BluetoothDevice paramBluetoothDevice) {
    return BluetoothDeviceFilterUtils.getDeviceDisplayNameInternal(paramBluetoothDevice);
  }
  
  public int getMediumType() {
    return 0;
  }
  
  public Pattern getNamePattern() {
    return this.mNamePattern;
  }
  
  public List<ParcelUuid> getServiceUuidMasks() {
    return this.mServiceUuidMasks;
  }
  
  public List<ParcelUuid> getServiceUuids() {
    return this.mServiceUuids;
  }
  
  public int hashCode() {
    return Objects.hash(new Object[] { this.mNamePattern, this.mAddress, this.mServiceUuids, this.mServiceUuidMasks });
  }
  
  public boolean matches(BluetoothDevice paramBluetoothDevice) {
    boolean bool;
    if (BluetoothDeviceFilterUtils.matchesAddress(this.mAddress, paramBluetoothDevice) && BluetoothDeviceFilterUtils.matchesServiceUuids(this.mServiceUuids, this.mServiceUuidMasks, paramBluetoothDevice) && BluetoothDeviceFilterUtils.matchesName(getNamePattern(), paramBluetoothDevice)) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("BluetoothDeviceFilter{mNamePattern=");
    stringBuilder.append(this.mNamePattern);
    stringBuilder.append(", mAddress='");
    stringBuilder.append(this.mAddress);
    stringBuilder.append('\'');
    stringBuilder.append(", mServiceUuids=");
    stringBuilder.append(this.mServiceUuids);
    stringBuilder.append(", mServiceUuidMasks=");
    stringBuilder.append(this.mServiceUuidMasks);
    stringBuilder.append('}');
    return stringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeString(BluetoothDeviceFilterUtils.patternToString(getNamePattern()));
    paramParcel.writeString(this.mAddress);
    paramParcel.writeParcelableList(this.mServiceUuids, paramInt);
    paramParcel.writeParcelableList(this.mServiceUuidMasks, paramInt);
  }
  
  public static final class Builder extends OneTimeUseBuilder<BluetoothDeviceFilter> {
    private String mAddress;
    
    private Pattern mNamePattern;
    
    private ArrayList<ParcelUuid> mServiceUuid;
    
    private ArrayList<ParcelUuid> mServiceUuidMask;
    
    public Builder addServiceUuid(ParcelUuid param1ParcelUuid1, ParcelUuid param1ParcelUuid2) {
      checkNotUsed();
      this.mServiceUuid = ArrayUtils.add(this.mServiceUuid, param1ParcelUuid1);
      this.mServiceUuidMask = ArrayUtils.add(this.mServiceUuidMask, param1ParcelUuid2);
      return this;
    }
    
    public BluetoothDeviceFilter build() {
      markUsed();
      return new BluetoothDeviceFilter(this.mNamePattern, this.mAddress, this.mServiceUuid, this.mServiceUuidMask);
    }
    
    public Builder setAddress(String param1String) {
      checkNotUsed();
      this.mAddress = param1String;
      return this;
    }
    
    public Builder setNamePattern(Pattern param1Pattern) {
      checkNotUsed();
      this.mNamePattern = param1Pattern;
      return this;
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/companion/BluetoothDeviceFilter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */