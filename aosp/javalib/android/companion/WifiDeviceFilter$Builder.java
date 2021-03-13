package android.companion;

import android.net.MacAddress;
import java.util.regex.Pattern;

public final class Builder {
  private MacAddress mBssid;
  
  private MacAddress mBssidMask;
  
  private long mBuilderFieldsSet = 0L;
  
  private Pattern mNamePattern;
  
  private void checkNotUsed() {
    if ((this.mBuilderFieldsSet & 0x8L) == 0L)
      return; 
    throw new IllegalStateException("This Builder should not be reused. Use a new Builder instance instead");
  }
  
  public WifiDeviceFilter build() {
    checkNotUsed();
    long l = this.mBuilderFieldsSet | 0x8L;
    this.mBuilderFieldsSet = l;
    if ((l & 0x1L) == 0L)
      this.mNamePattern = null; 
    if ((this.mBuilderFieldsSet & 0x2L) == 0L)
      this.mBssid = null; 
    if ((this.mBuilderFieldsSet & 0x4L) == 0L)
      this.mBssidMask = MacAddress.BROADCAST_ADDRESS; 
    return new WifiDeviceFilter(this.mNamePattern, this.mBssid, this.mBssidMask);
  }
  
  public Builder setBssid(MacAddress paramMacAddress) {
    checkNotUsed();
    this.mBuilderFieldsSet |= 0x2L;
    this.mBssid = paramMacAddress;
    return this;
  }
  
  public Builder setBssidMask(MacAddress paramMacAddress) {
    checkNotUsed();
    this.mBuilderFieldsSet |= 0x4L;
    this.mBssidMask = paramMacAddress;
    return this;
  }
  
  public Builder setNamePattern(Pattern paramPattern) {
    checkNotUsed();
    this.mBuilderFieldsSet |= 0x1L;
    this.mNamePattern = paramPattern;
    return this;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/companion/WifiDeviceFilter$Builder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */