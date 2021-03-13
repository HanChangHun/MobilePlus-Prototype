package android.content.pm;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;

public final class Activity extends PackageParser.Component<PackageParser.ActivityIntentInfo> implements Parcelable {
  public static final Parcelable.Creator CREATOR = new Parcelable.Creator<Activity>() {
      public PackageParser.Activity createFromParcel(Parcel param2Parcel) {
        return new PackageParser.Activity(param2Parcel);
      }
      
      public PackageParser.Activity[] newArray(int param2Int) {
        return new PackageParser.Activity[param2Int];
      }
    };
  
  public final ActivityInfo info;
  
  private boolean mHasMaxAspectRatio;
  
  private boolean mHasMinAspectRatio;
  
  Activity(PackageParser.Package paramPackage, String paramString, ActivityInfo paramActivityInfo) {
    super(paramPackage, new ArrayList<>(0), paramString);
    this.info = paramActivityInfo;
    paramActivityInfo.applicationInfo = paramPackage.applicationInfo;
  }
  
  public Activity(PackageParser.ParseComponentArgs paramParseComponentArgs, ActivityInfo paramActivityInfo) {
    super(paramParseComponentArgs, paramActivityInfo);
    this.info = paramActivityInfo;
    paramActivityInfo.applicationInfo = paramParseComponentArgs.owner.applicationInfo;
  }
  
  private Activity(Parcel paramParcel) {
    super(paramParcel);
    this.info = (ActivityInfo)paramParcel.readParcelable(Object.class.getClassLoader());
    this.mHasMaxAspectRatio = paramParcel.readBoolean();
    this.mHasMinAspectRatio = paramParcel.readBoolean();
    for (PackageParser.ActivityIntentInfo activityIntentInfo : this.intents) {
      activityIntentInfo.activity = this;
      this.order = Math.max(activityIntentInfo.getOrder(), this.order);
    } 
    if (this.info.permission != null) {
      ActivityInfo activityInfo = this.info;
      activityInfo.permission = activityInfo.permission.intern();
    } 
  }
  
  private boolean hasMaxAspectRatio() {
    return this.mHasMaxAspectRatio;
  }
  
  private boolean hasMinAspectRatio() {
    return this.mHasMinAspectRatio;
  }
  
  private void setMaxAspectRatio(float paramFloat) {
    if (this.info.resizeMode == 2 || this.info.resizeMode == 1)
      return; 
    if (paramFloat < 1.0F && paramFloat != 0.0F)
      return; 
    this.info.maxAspectRatio = paramFloat;
    this.mHasMaxAspectRatio = true;
  }
  
  private void setMinAspectRatio(float paramFloat) {
    if (this.info.resizeMode == 2 || this.info.resizeMode == 1)
      return; 
    if (paramFloat < 1.0F && paramFloat != 0.0F)
      return; 
    this.info.minAspectRatio = paramFloat;
    this.mHasMinAspectRatio = true;
  }
  
  public int describeContents() {
    return 0;
  }
  
  public void setPackageName(String paramString) {
    super.setPackageName(paramString);
    this.info.packageName = paramString;
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder(128);
    stringBuilder.append("Activity{");
    stringBuilder.append(Integer.toHexString(System.identityHashCode(this)));
    stringBuilder.append(' ');
    appendComponentShortName(stringBuilder);
    stringBuilder.append('}');
    return stringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    super.writeToParcel(paramParcel, paramInt);
    paramParcel.writeParcelable(this.info, paramInt | 0x2);
    paramParcel.writeBoolean(this.mHasMaxAspectRatio);
    paramParcel.writeBoolean(this.mHasMinAspectRatio);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/PackageParser$Activity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */