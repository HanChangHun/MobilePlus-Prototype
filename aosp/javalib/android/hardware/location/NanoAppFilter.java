package android.hardware.location;

import android.annotation.SystemApi;
import android.os.Parcel;
import android.os.Parcelable;

@SystemApi
@Deprecated
public class NanoAppFilter implements Parcelable {
  public static final int APP_ANY = -1;
  
  public static final Parcelable.Creator<NanoAppFilter> CREATOR = new Parcelable.Creator<NanoAppFilter>() {
      public NanoAppFilter createFromParcel(Parcel param1Parcel) {
        return new NanoAppFilter(param1Parcel);
      }
      
      public NanoAppFilter[] newArray(int param1Int) {
        return new NanoAppFilter[param1Int];
      }
    };
  
  public static final int FLAGS_VERSION_ANY = -1;
  
  public static final int FLAGS_VERSION_GREAT_THAN = 2;
  
  public static final int FLAGS_VERSION_LESS_THAN = 4;
  
  public static final int FLAGS_VERSION_STRICTLY_EQUAL = 8;
  
  public static final int HUB_ANY = -1;
  
  private static final String TAG = "NanoAppFilter";
  
  public static final int VENDOR_ANY = -1;
  
  private long mAppId;
  
  private long mAppIdVendorMask;
  
  private int mAppVersion;
  
  private int mContextHubId = -1;
  
  private int mVersionRestrictionMask;
  
  public NanoAppFilter(long paramLong1, int paramInt1, int paramInt2, long paramLong2) {
    this.mAppId = paramLong1;
    this.mAppVersion = paramInt1;
    this.mVersionRestrictionMask = paramInt2;
    this.mAppIdVendorMask = paramLong2;
  }
  
  private NanoAppFilter(Parcel paramParcel) {
    this.mAppId = paramParcel.readLong();
    this.mAppVersion = paramParcel.readInt();
    this.mVersionRestrictionMask = paramParcel.readInt();
    this.mAppIdVendorMask = paramParcel.readLong();
  }
  
  private boolean versionsMatch(int paramInt1, int paramInt2, int paramInt3) {
    return true;
  }
  
  public int describeContents() {
    return 0;
  }
  
  public boolean testMatch(NanoAppInstanceInfo paramNanoAppInstanceInfo) {
    boolean bool;
    if ((this.mContextHubId == -1 || paramNanoAppInstanceInfo.getContexthubId() == this.mContextHubId) && (this.mAppId == -1L || paramNanoAppInstanceInfo.getAppId() == this.mAppId) && versionsMatch(this.mVersionRestrictionMask, this.mAppVersion, paramNanoAppInstanceInfo.getAppVersion())) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("nanoAppId: 0x");
    stringBuilder.append(Long.toHexString(this.mAppId));
    stringBuilder.append(", nanoAppVersion: 0x");
    stringBuilder.append(Integer.toHexString(this.mAppVersion));
    stringBuilder.append(", versionMask: ");
    stringBuilder.append(this.mVersionRestrictionMask);
    stringBuilder.append(", vendorMask: ");
    stringBuilder.append(this.mAppIdVendorMask);
    return stringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeLong(this.mAppId);
    paramParcel.writeInt(this.mAppVersion);
    paramParcel.writeInt(this.mVersionRestrictionMask);
    paramParcel.writeLong(this.mAppIdVendorMask);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/location/NanoAppFilter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */