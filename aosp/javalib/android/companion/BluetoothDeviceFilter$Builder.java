package android.companion;

import android.os.ParcelUuid;
import android.provider.OneTimeUseBuilder;
import com.android.internal.util.ArrayUtils;
import java.util.ArrayList;
import java.util.regex.Pattern;

public final class Builder extends OneTimeUseBuilder<BluetoothDeviceFilter> {
  private String mAddress;
  
  private Pattern mNamePattern;
  
  private ArrayList<ParcelUuid> mServiceUuid;
  
  private ArrayList<ParcelUuid> mServiceUuidMask;
  
  public Builder addServiceUuid(ParcelUuid paramParcelUuid1, ParcelUuid paramParcelUuid2) {
    checkNotUsed();
    this.mServiceUuid = ArrayUtils.add(this.mServiceUuid, paramParcelUuid1);
    this.mServiceUuidMask = ArrayUtils.add(this.mServiceUuidMask, paramParcelUuid2);
    return this;
  }
  
  public BluetoothDeviceFilter build() {
    markUsed();
    return new BluetoothDeviceFilter(this.mNamePattern, this.mAddress, this.mServiceUuid, this.mServiceUuidMask, null);
  }
  
  public Builder setAddress(String paramString) {
    checkNotUsed();
    this.mAddress = paramString;
    return this;
  }
  
  public Builder setNamePattern(Pattern paramPattern) {
    checkNotUsed();
    this.mNamePattern = paramPattern;
    return this;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/companion/BluetoothDeviceFilter$Builder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */