package android.hardware.display;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Objects;

public final class WifiDisplay implements Parcelable {
  public static final Parcelable.Creator<WifiDisplay> CREATOR;
  
  public static final WifiDisplay[] EMPTY_ARRAY = new WifiDisplay[0];
  
  private final boolean mCanConnect;
  
  private final String mDeviceAddress;
  
  private final String mDeviceAlias;
  
  private final String mDeviceName;
  
  private final boolean mIsAvailable;
  
  private final boolean mIsRemembered;
  
  static {
    CREATOR = new Parcelable.Creator<WifiDisplay>() {
        public WifiDisplay createFromParcel(Parcel param1Parcel) {
          boolean bool1;
          boolean bool2;
          boolean bool3;
          String str1 = param1Parcel.readString();
          String str2 = param1Parcel.readString();
          String str3 = param1Parcel.readString();
          if (param1Parcel.readInt() != 0) {
            bool1 = true;
          } else {
            bool1 = false;
          } 
          if (param1Parcel.readInt() != 0) {
            bool2 = true;
          } else {
            bool2 = false;
          } 
          if (param1Parcel.readInt() != 0) {
            bool3 = true;
          } else {
            bool3 = false;
          } 
          return new WifiDisplay(str1, str2, str3, bool1, bool2, bool3);
        }
        
        public WifiDisplay[] newArray(int param1Int) {
          WifiDisplay[] arrayOfWifiDisplay;
          if (param1Int == 0) {
            arrayOfWifiDisplay = WifiDisplay.EMPTY_ARRAY;
          } else {
            arrayOfWifiDisplay = new WifiDisplay[param1Int];
          } 
          return arrayOfWifiDisplay;
        }
      };
  }
  
  public WifiDisplay(String paramString1, String paramString2, String paramString3, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3) {
    if (paramString1 != null) {
      if (paramString2 != null) {
        this.mDeviceAddress = paramString1;
        this.mDeviceName = paramString2;
        this.mDeviceAlias = paramString3;
        this.mIsAvailable = paramBoolean1;
        this.mCanConnect = paramBoolean2;
        this.mIsRemembered = paramBoolean3;
        return;
      } 
      throw new IllegalArgumentException("deviceName must not be null");
    } 
    throw new IllegalArgumentException("deviceAddress must not be null");
  }
  
  public boolean canConnect() {
    return this.mCanConnect;
  }
  
  public int describeContents() {
    return 0;
  }
  
  public boolean equals(WifiDisplay paramWifiDisplay) {
    boolean bool;
    if (paramWifiDisplay != null && this.mDeviceAddress.equals(paramWifiDisplay.mDeviceAddress) && this.mDeviceName.equals(paramWifiDisplay.mDeviceName) && Objects.equals(this.mDeviceAlias, paramWifiDisplay.mDeviceAlias)) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean equals(Object paramObject) {
    boolean bool;
    if (paramObject instanceof WifiDisplay && equals((WifiDisplay)paramObject)) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public String getDeviceAddress() {
    return this.mDeviceAddress;
  }
  
  public String getDeviceAlias() {
    return this.mDeviceAlias;
  }
  
  public String getDeviceName() {
    return this.mDeviceName;
  }
  
  public String getFriendlyDisplayName() {
    String str = this.mDeviceAlias;
    if (str == null)
      str = this.mDeviceName; 
    return str;
  }
  
  public boolean hasSameAddress(WifiDisplay paramWifiDisplay) {
    boolean bool;
    if (paramWifiDisplay != null && this.mDeviceAddress.equals(paramWifiDisplay.mDeviceAddress)) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public int hashCode() {
    return this.mDeviceAddress.hashCode();
  }
  
  public boolean isAvailable() {
    return this.mIsAvailable;
  }
  
  public boolean isRemembered() {
    return this.mIsRemembered;
  }
  
  public String toString() {
    StringBuilder stringBuilder1 = new StringBuilder();
    stringBuilder1.append(this.mDeviceName);
    stringBuilder1.append(" (");
    stringBuilder1.append(this.mDeviceAddress);
    stringBuilder1.append(")");
    String str2 = stringBuilder1.toString();
    String str1 = str2;
    if (this.mDeviceAlias != null) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(str2);
      stringBuilder.append(", alias ");
      stringBuilder.append(this.mDeviceAlias);
      str1 = stringBuilder.toString();
    } 
    StringBuilder stringBuilder2 = new StringBuilder();
    stringBuilder2.append(str1);
    stringBuilder2.append(", isAvailable ");
    stringBuilder2.append(this.mIsAvailable);
    stringBuilder2.append(", canConnect ");
    stringBuilder2.append(this.mCanConnect);
    stringBuilder2.append(", isRemembered ");
    stringBuilder2.append(this.mIsRemembered);
    return stringBuilder2.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeString(this.mDeviceAddress);
    paramParcel.writeString(this.mDeviceName);
    paramParcel.writeString(this.mDeviceAlias);
    paramParcel.writeInt(this.mIsAvailable);
    paramParcel.writeInt(this.mCanConnect);
    paramParcel.writeInt(this.mIsRemembered);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/display/WifiDisplay.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */