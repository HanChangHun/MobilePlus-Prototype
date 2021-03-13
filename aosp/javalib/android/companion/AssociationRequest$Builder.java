package android.companion;

import android.provider.OneTimeUseBuilder;
import com.android.internal.util.ArrayUtils;
import java.util.ArrayList;

public final class Builder extends OneTimeUseBuilder<AssociationRequest> {
  private ArrayList<DeviceFilter<?>> mDeviceFilters = null;
  
  private boolean mSingleDevice = false;
  
  public Builder addDeviceFilter(DeviceFilter<?> paramDeviceFilter) {
    checkNotUsed();
    if (paramDeviceFilter != null)
      this.mDeviceFilters = ArrayUtils.add(this.mDeviceFilters, paramDeviceFilter); 
    return this;
  }
  
  public AssociationRequest build() {
    markUsed();
    return new AssociationRequest(this.mSingleDevice, this.mDeviceFilters, null);
  }
  
  public Builder setSingleDevice(boolean paramBoolean) {
    checkNotUsed();
    this.mSingleDevice = paramBoolean;
    return this;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/companion/AssociationRequest$Builder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */