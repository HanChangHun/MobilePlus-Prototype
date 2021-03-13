package android.content.pm;

import android.os.Parcel;

public final class ActivityIntentInfo extends PackageParser.IntentInfo {
  public PackageParser.Activity activity;
  
  public ActivityIntentInfo(PackageParser.Activity paramActivity) {
    this.activity = paramActivity;
  }
  
  public ActivityIntentInfo(Parcel paramParcel) {
    super(paramParcel);
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder(128);
    stringBuilder.append("ActivityIntentInfo{");
    stringBuilder.append(Integer.toHexString(System.identityHashCode(this)));
    stringBuilder.append(' ');
    this.activity.appendComponentShortName(stringBuilder);
    stringBuilder.append('}');
    return stringBuilder.toString();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/PackageParser$ActivityIntentInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */