package android.content.pm;

import android.annotation.SystemApi;
import android.content.ComponentName;
import android.os.Parcel;
import android.os.Parcelable;

@SystemApi
public final class ShareShortcutInfo implements Parcelable {
  public static final Parcelable.Creator<ShareShortcutInfo> CREATOR = new Parcelable.Creator<ShareShortcutInfo>() {
      public ShortcutManager.ShareShortcutInfo createFromParcel(Parcel param2Parcel) {
        return new ShortcutManager.ShareShortcutInfo(param2Parcel);
      }
      
      public ShortcutManager.ShareShortcutInfo[] newArray(int param2Int) {
        return new ShortcutManager.ShareShortcutInfo[param2Int];
      }
    };
  
  private final ShortcutInfo mShortcutInfo;
  
  private final ComponentName mTargetComponent;
  
  public ShareShortcutInfo(ShortcutInfo paramShortcutInfo, ComponentName paramComponentName) {
    if (paramShortcutInfo != null) {
      if (paramComponentName != null) {
        this.mShortcutInfo = paramShortcutInfo;
        this.mTargetComponent = paramComponentName;
        return;
      } 
      throw new NullPointerException("target component is null");
    } 
    throw new NullPointerException("shortcut info is null");
  }
  
  private ShareShortcutInfo(Parcel paramParcel) {
    this.mShortcutInfo = (ShortcutInfo)paramParcel.readParcelable(ShortcutInfo.class.getClassLoader());
    this.mTargetComponent = (ComponentName)paramParcel.readParcelable(ComponentName.class.getClassLoader());
  }
  
  public int describeContents() {
    return 0;
  }
  
  public ShortcutInfo getShortcutInfo() {
    return this.mShortcutInfo;
  }
  
  public ComponentName getTargetComponent() {
    return this.mTargetComponent;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeParcelable(this.mShortcutInfo, paramInt);
    paramParcel.writeParcelable((Parcelable)this.mTargetComponent, paramInt);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/ShortcutManager$ShareShortcutInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */