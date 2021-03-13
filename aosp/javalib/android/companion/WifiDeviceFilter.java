package android.companion;

import android.annotation.NonNull;
import android.net.MacAddress;
import android.net.wifi.ScanResult;
import android.os.Parcel;
import android.os.Parcelable;
import com.android.internal.util.AnnotationValidations;
import com.android.internal.util.Parcelling;
import java.util.Objects;
import java.util.regex.Pattern;

public final class WifiDeviceFilter implements DeviceFilter<ScanResult> {
  public static final Parcelable.Creator<WifiDeviceFilter> CREATOR;
  
  static Parcelling<Pattern> sParcellingForNamePattern;
  
  private MacAddress mBssid;
  
  private MacAddress mBssidMask;
  
  private Pattern mNamePattern;
  
  static {
    Parcelling<Pattern> parcelling = Parcelling.Cache.get(Parcelling.BuiltIn.ForPattern.class);
    sParcellingForNamePattern = parcelling;
    if (parcelling == null)
      sParcellingForNamePattern = Parcelling.Cache.put((Parcelling)new Parcelling.BuiltIn.ForPattern()); 
    CREATOR = new Parcelable.Creator<WifiDeviceFilter>() {
        public WifiDeviceFilter createFromParcel(Parcel param1Parcel) {
          return new WifiDeviceFilter(param1Parcel);
        }
        
        public WifiDeviceFilter[] newArray(int param1Int) {
          return new WifiDeviceFilter[param1Int];
        }
      };
  }
  
  WifiDeviceFilter(Parcel paramParcel) {
    MacAddress macAddress2;
    this.mNamePattern = null;
    this.mBssid = null;
    this.mBssidMask = MacAddress.BROADCAST_ADDRESS;
    byte b = paramParcel.readByte();
    Pattern pattern = (Pattern)sParcellingForNamePattern.unparcel(paramParcel);
    if ((b & 0x2) == 0) {
      macAddress2 = null;
    } else {
      macAddress2 = (MacAddress)paramParcel.readTypedObject(MacAddress.CREATOR);
    } 
    MacAddress macAddress1 = (MacAddress)paramParcel.readTypedObject(MacAddress.CREATOR);
    this.mNamePattern = pattern;
    this.mBssid = macAddress2;
    this.mBssidMask = macAddress1;
    AnnotationValidations.validate(NonNull.class, null, macAddress1);
  }
  
  WifiDeviceFilter(Pattern paramPattern, MacAddress paramMacAddress1, MacAddress paramMacAddress2) {
    this.mNamePattern = null;
    this.mBssid = null;
    this.mBssidMask = MacAddress.BROADCAST_ADDRESS;
    this.mNamePattern = paramPattern;
    this.mBssid = paramMacAddress1;
    this.mBssidMask = paramMacAddress2;
    AnnotationValidations.validate(NonNull.class, null, paramMacAddress2);
  }
  
  @Deprecated
  private void __metadata() {}
  
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
    if (!Objects.equals(this.mNamePattern, ((WifiDeviceFilter)paramObject).mNamePattern) || !Objects.equals(this.mBssid, ((WifiDeviceFilter)paramObject).mBssid) || !Objects.equals(this.mBssidMask, ((WifiDeviceFilter)paramObject).mBssidMask))
      bool = false; 
    return bool;
  }
  
  public MacAddress getBssid() {
    return this.mBssid;
  }
  
  public MacAddress getBssidMask() {
    return this.mBssidMask;
  }
  
  public String getDeviceDisplayName(ScanResult paramScanResult) {
    return BluetoothDeviceFilterUtils.getDeviceDisplayNameInternal(paramScanResult);
  }
  
  public int getMediumType() {
    return 2;
  }
  
  public Pattern getNamePattern() {
    return this.mNamePattern;
  }
  
  public int hashCode() {
    return ((1 * 31 + Objects.hashCode(this.mNamePattern)) * 31 + Objects.hashCode(this.mBssid)) * 31 + Objects.hashCode(this.mBssidMask);
  }
  
  public boolean matches(ScanResult paramScanResult) {
    boolean bool;
    if (BluetoothDeviceFilterUtils.matchesName(getNamePattern(), paramScanResult) && (this.mBssid == null || MacAddress.fromString(paramScanResult.BSSID).matches(this.mBssid, this.mBssidMask))) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    byte b1 = 0;
    if (this.mNamePattern != null)
      b1 = (byte)(false | true); 
    byte b2 = b1;
    if (this.mBssid != null) {
      b1 = (byte)(b1 | 0x2);
      b2 = b1;
    } 
    paramParcel.writeByte(b2);
    sParcellingForNamePattern.parcel(this.mNamePattern, paramParcel, paramInt);
    MacAddress macAddress = this.mBssid;
    if (macAddress != null)
      paramParcel.writeTypedObject((Parcelable)macAddress, paramInt); 
    paramParcel.writeTypedObject((Parcelable)this.mBssidMask, paramInt);
  }
  
  public static final class Builder {
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
    
    public Builder setBssid(MacAddress param1MacAddress) {
      checkNotUsed();
      this.mBuilderFieldsSet |= 0x2L;
      this.mBssid = param1MacAddress;
      return this;
    }
    
    public Builder setBssidMask(MacAddress param1MacAddress) {
      checkNotUsed();
      this.mBuilderFieldsSet |= 0x4L;
      this.mBssidMask = param1MacAddress;
      return this;
    }
    
    public Builder setNamePattern(Pattern param1Pattern) {
      checkNotUsed();
      this.mBuilderFieldsSet |= 0x1L;
      this.mNamePattern = param1Pattern;
      return this;
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/companion/WifiDeviceFilter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */