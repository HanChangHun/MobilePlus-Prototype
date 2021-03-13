package android.content.pm;

import android.annotation.SystemApi;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;

@SystemApi
public final class InstantAppInfo implements Parcelable {
  public static final Parcelable.Creator<InstantAppInfo> CREATOR = new Parcelable.Creator<InstantAppInfo>() {
      public InstantAppInfo createFromParcel(Parcel param1Parcel) {
        return new InstantAppInfo(param1Parcel);
      }
      
      public InstantAppInfo[] newArray(int param1Int) {
        return new InstantAppInfo[0];
      }
    };
  
  private final ApplicationInfo mApplicationInfo;
  
  private final String[] mGrantedPermissions;
  
  private final CharSequence mLabelText;
  
  private final String mPackageName;
  
  private final String[] mRequestedPermissions;
  
  public InstantAppInfo(ApplicationInfo paramApplicationInfo, String[] paramArrayOfString1, String[] paramArrayOfString2) {
    this.mApplicationInfo = paramApplicationInfo;
    this.mPackageName = null;
    this.mLabelText = null;
    this.mRequestedPermissions = paramArrayOfString1;
    this.mGrantedPermissions = paramArrayOfString2;
  }
  
  private InstantAppInfo(Parcel paramParcel) {
    this.mPackageName = paramParcel.readString();
    this.mLabelText = paramParcel.readCharSequence();
    this.mRequestedPermissions = paramParcel.readStringArray();
    this.mGrantedPermissions = paramParcel.createStringArray();
    this.mApplicationInfo = (ApplicationInfo)paramParcel.readParcelable(null);
  }
  
  public InstantAppInfo(String paramString, CharSequence paramCharSequence, String[] paramArrayOfString1, String[] paramArrayOfString2) {
    this.mApplicationInfo = null;
    this.mPackageName = paramString;
    this.mLabelText = paramCharSequence;
    this.mRequestedPermissions = paramArrayOfString1;
    this.mGrantedPermissions = paramArrayOfString2;
  }
  
  public int describeContents() {
    return 0;
  }
  
  public ApplicationInfo getApplicationInfo() {
    return this.mApplicationInfo;
  }
  
  public String[] getGrantedPermissions() {
    return this.mGrantedPermissions;
  }
  
  public String getPackageName() {
    ApplicationInfo applicationInfo = this.mApplicationInfo;
    return (applicationInfo != null) ? applicationInfo.packageName : this.mPackageName;
  }
  
  public String[] getRequestedPermissions() {
    return this.mRequestedPermissions;
  }
  
  public Drawable loadIcon(PackageManager paramPackageManager) {
    ApplicationInfo applicationInfo = this.mApplicationInfo;
    return (applicationInfo != null) ? applicationInfo.loadIcon(paramPackageManager) : paramPackageManager.getInstantAppIcon(this.mPackageName);
  }
  
  public CharSequence loadLabel(PackageManager paramPackageManager) {
    ApplicationInfo applicationInfo = this.mApplicationInfo;
    return (applicationInfo != null) ? applicationInfo.loadLabel(paramPackageManager) : this.mLabelText;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeString(this.mPackageName);
    paramParcel.writeCharSequence(this.mLabelText);
    paramParcel.writeStringArray(this.mRequestedPermissions);
    paramParcel.writeStringArray(this.mGrantedPermissions);
    paramParcel.writeParcelable(this.mApplicationInfo, paramInt);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/InstantAppInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */