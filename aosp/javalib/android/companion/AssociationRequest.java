package android.companion;

import android.os.Parcel;
import android.os.Parcelable;
import android.provider.OneTimeUseBuilder;
import com.android.internal.util.ArrayUtils;
import com.android.internal.util.CollectionUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class AssociationRequest implements Parcelable {
  public static final Parcelable.Creator<AssociationRequest> CREATOR = new Parcelable.Creator<AssociationRequest>() {
      public AssociationRequest createFromParcel(Parcel param1Parcel) {
        return new AssociationRequest(param1Parcel);
      }
      
      public AssociationRequest[] newArray(int param1Int) {
        return new AssociationRequest[param1Int];
      }
    };
  
  private final List<DeviceFilter<?>> mDeviceFilters;
  
  private final boolean mSingleDevice;
  
  private AssociationRequest(Parcel paramParcel) {
    this(bool, paramParcel.readParcelableList(new ArrayList(), AssociationRequest.class.getClassLoader()));
  }
  
  private AssociationRequest(boolean paramBoolean, List<DeviceFilter<?>> paramList) {
    this.mSingleDevice = paramBoolean;
    this.mDeviceFilters = CollectionUtils.emptyIfNull(paramList);
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
    if (this.mSingleDevice != ((AssociationRequest)paramObject).mSingleDevice || !Objects.equals(this.mDeviceFilters, ((AssociationRequest)paramObject).mDeviceFilters))
      bool = false; 
    return bool;
  }
  
  public List<DeviceFilter<?>> getDeviceFilters() {
    return this.mDeviceFilters;
  }
  
  public int hashCode() {
    return Objects.hash(new Object[] { Boolean.valueOf(this.mSingleDevice), this.mDeviceFilters });
  }
  
  public boolean isSingleDevice() {
    return this.mSingleDevice;
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("AssociationRequest{mSingleDevice=");
    stringBuilder.append(this.mSingleDevice);
    stringBuilder.append(", mDeviceFilters=");
    stringBuilder.append(this.mDeviceFilters);
    stringBuilder.append('}');
    return stringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeByte((byte)this.mSingleDevice);
    paramParcel.writeParcelableList(this.mDeviceFilters, paramInt);
  }
  
  public static final class Builder extends OneTimeUseBuilder<AssociationRequest> {
    private ArrayList<DeviceFilter<?>> mDeviceFilters = null;
    
    private boolean mSingleDevice = false;
    
    public Builder addDeviceFilter(DeviceFilter<?> param1DeviceFilter) {
      checkNotUsed();
      if (param1DeviceFilter != null)
        this.mDeviceFilters = ArrayUtils.add(this.mDeviceFilters, param1DeviceFilter); 
      return this;
    }
    
    public AssociationRequest build() {
      markUsed();
      return new AssociationRequest(this.mSingleDevice, this.mDeviceFilters);
    }
    
    public Builder setSingleDevice(boolean param1Boolean) {
      checkNotUsed();
      this.mSingleDevice = param1Boolean;
      return this;
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/companion/AssociationRequest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */