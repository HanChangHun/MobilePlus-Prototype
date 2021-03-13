package android.content.pm.parsing.component;

import android.app.ActivityTaskManager;
import android.content.ComponentName;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageParser;
import android.content.pm.parsing.ParsingPackageImpl;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

public class ParsedActivity extends ParsedMainComponent {
  public static final Parcelable.Creator<ParsedActivity> CREATOR = new Parcelable.Creator<ParsedActivity>() {
      public ParsedActivity createFromParcel(Parcel param1Parcel) {
        return new ParsedActivity(param1Parcel);
      }
      
      public ParsedActivity[] newArray(int param1Int) {
        return new ParsedActivity[param1Int];
      }
    };
  
  int colorMode;
  
  int configChanges;
  
  int documentLaunchMode;
  
  int launchMode;
  
  int lockTaskLaunchMode;
  
  private Float maxAspectRatio;
  
  int maxRecents;
  
  private Float minAspectRatio;
  
  private String parentActivityName;
  
  private String permission;
  
  int persistableMode;
  
  int privateFlags;
  
  String requestedVrComponent;
  
  int resizeMode = 2;
  
  int rotationAnimation = -1;
  
  int screenOrientation = -1;
  
  int softInputMode;
  
  private boolean supportsSizeChanges;
  
  private String targetActivity;
  
  String taskAffinity;
  
  int theme;
  
  int uiOptions;
  
  ActivityInfo.WindowLayout windowLayout;
  
  public ParsedActivity() {}
  
  public ParsedActivity(ParsedActivity paramParsedActivity) {
    super(paramParsedActivity);
    this.theme = paramParsedActivity.theme;
    this.uiOptions = paramParsedActivity.uiOptions;
    this.targetActivity = paramParsedActivity.targetActivity;
    this.parentActivityName = paramParsedActivity.parentActivityName;
    this.taskAffinity = paramParsedActivity.taskAffinity;
    this.privateFlags = paramParsedActivity.privateFlags;
    this.permission = paramParsedActivity.permission;
    this.launchMode = paramParsedActivity.launchMode;
    this.documentLaunchMode = paramParsedActivity.documentLaunchMode;
    this.maxRecents = paramParsedActivity.maxRecents;
    this.configChanges = paramParsedActivity.configChanges;
    this.softInputMode = paramParsedActivity.softInputMode;
    this.persistableMode = paramParsedActivity.persistableMode;
    this.lockTaskLaunchMode = paramParsedActivity.lockTaskLaunchMode;
    this.screenOrientation = paramParsedActivity.screenOrientation;
    this.resizeMode = paramParsedActivity.resizeMode;
    this.maxAspectRatio = paramParsedActivity.maxAspectRatio;
    this.minAspectRatio = paramParsedActivity.minAspectRatio;
    this.supportsSizeChanges = paramParsedActivity.supportsSizeChanges;
    this.requestedVrComponent = paramParsedActivity.requestedVrComponent;
    this.rotationAnimation = paramParsedActivity.rotationAnimation;
    this.colorMode = paramParsedActivity.colorMode;
    this.windowLayout = paramParsedActivity.windowLayout;
  }
  
  protected ParsedActivity(Parcel paramParcel) {
    super(paramParcel);
    this.theme = paramParcel.readInt();
    this.uiOptions = paramParcel.readInt();
    this.targetActivity = paramParcel.readString();
    this.parentActivityName = paramParcel.readString();
    this.taskAffinity = paramParcel.readString();
    this.privateFlags = paramParcel.readInt();
    this.permission = ParsingPackageImpl.sForInternedString.unparcel(paramParcel);
    this.launchMode = paramParcel.readInt();
    this.documentLaunchMode = paramParcel.readInt();
    this.maxRecents = paramParcel.readInt();
    this.configChanges = paramParcel.readInt();
    this.softInputMode = paramParcel.readInt();
    this.persistableMode = paramParcel.readInt();
    this.lockTaskLaunchMode = paramParcel.readInt();
    this.screenOrientation = paramParcel.readInt();
    this.resizeMode = paramParcel.readInt();
    this.maxAspectRatio = (Float)paramParcel.readValue(Float.class.getClassLoader());
    this.minAspectRatio = (Float)paramParcel.readValue(Float.class.getClassLoader());
    this.supportsSizeChanges = paramParcel.readBoolean();
    this.requestedVrComponent = paramParcel.readString();
    this.rotationAnimation = paramParcel.readInt();
    this.colorMode = paramParcel.readInt();
    this.metaData = paramParcel.readBundle();
    if (paramParcel.readBoolean())
      this.windowLayout = new ActivityInfo.WindowLayout(paramParcel); 
  }
  
  static ParsedActivity makeAlias(String paramString, ParsedActivity paramParsedActivity) {
    ParsedActivity parsedActivity = new ParsedActivity();
    parsedActivity.setPackageName(paramParsedActivity.getPackageName());
    parsedActivity.setTargetActivity(paramString);
    parsedActivity.configChanges = paramParsedActivity.configChanges;
    parsedActivity.flags = paramParsedActivity.flags;
    parsedActivity.privateFlags = paramParsedActivity.privateFlags;
    parsedActivity.icon = paramParsedActivity.icon;
    parsedActivity.logo = paramParsedActivity.logo;
    parsedActivity.banner = paramParsedActivity.banner;
    parsedActivity.labelRes = paramParsedActivity.labelRes;
    parsedActivity.nonLocalizedLabel = paramParsedActivity.nonLocalizedLabel;
    parsedActivity.launchMode = paramParsedActivity.launchMode;
    parsedActivity.lockTaskLaunchMode = paramParsedActivity.lockTaskLaunchMode;
    parsedActivity.descriptionRes = paramParsedActivity.descriptionRes;
    parsedActivity.screenOrientation = paramParsedActivity.screenOrientation;
    parsedActivity.taskAffinity = paramParsedActivity.taskAffinity;
    parsedActivity.theme = paramParsedActivity.theme;
    parsedActivity.softInputMode = paramParsedActivity.softInputMode;
    parsedActivity.uiOptions = paramParsedActivity.uiOptions;
    parsedActivity.parentActivityName = paramParsedActivity.parentActivityName;
    parsedActivity.maxRecents = paramParsedActivity.maxRecents;
    parsedActivity.windowLayout = paramParsedActivity.windowLayout;
    parsedActivity.resizeMode = paramParsedActivity.resizeMode;
    parsedActivity.maxAspectRatio = paramParsedActivity.maxAspectRatio;
    parsedActivity.minAspectRatio = paramParsedActivity.minAspectRatio;
    parsedActivity.supportsSizeChanges = paramParsedActivity.supportsSizeChanges;
    parsedActivity.requestedVrComponent = paramParsedActivity.requestedVrComponent;
    parsedActivity.directBootAware = paramParsedActivity.directBootAware;
    parsedActivity.setProcessName(paramParsedActivity.getProcessName());
    return parsedActivity;
  }
  
  public static ParsedActivity makeAppDetailsActivity(String paramString1, String paramString2, int paramInt, String paramString3, boolean paramBoolean) {
    ParsedActivity parsedActivity = new ParsedActivity();
    parsedActivity.setPackageName(paramString1);
    parsedActivity.theme = 16973909;
    parsedActivity.exported = true;
    parsedActivity.setName(PackageManager.APP_DETAILS_ACTIVITY_CLASS_NAME);
    parsedActivity.setProcessName(paramString2);
    parsedActivity.uiOptions = paramInt;
    parsedActivity.taskAffinity = paramString3;
    parsedActivity.launchMode = 0;
    parsedActivity.documentLaunchMode = 0;
    parsedActivity.maxRecents = ActivityTaskManager.getDefaultAppRecentsLimitStatic();
    parsedActivity.configChanges = PackageParser.getActivityConfigChanges(0, 0);
    parsedActivity.softInputMode = 0;
    parsedActivity.persistableMode = 1;
    parsedActivity.screenOrientation = -1;
    parsedActivity.resizeMode = 4;
    parsedActivity.lockTaskLaunchMode = 0;
    parsedActivity.setDirectBootAware(false);
    parsedActivity.rotationAnimation = -1;
    parsedActivity.colorMode = 0;
    if (paramBoolean)
      parsedActivity.setFlags(parsedActivity.getFlags() | 0x200); 
    return parsedActivity;
  }
  
  public int describeContents() {
    return 0;
  }
  
  public int getColorMode() {
    return this.colorMode;
  }
  
  public int getConfigChanges() {
    return this.configChanges;
  }
  
  public int getDocumentLaunchMode() {
    return this.documentLaunchMode;
  }
  
  public int getLaunchMode() {
    return this.launchMode;
  }
  
  public int getLockTaskLaunchMode() {
    return this.lockTaskLaunchMode;
  }
  
  public Float getMaxAspectRatio() {
    return this.maxAspectRatio;
  }
  
  public int getMaxRecents() {
    return this.maxRecents;
  }
  
  public Float getMinAspectRatio() {
    return this.minAspectRatio;
  }
  
  public String getParentActivityName() {
    return this.parentActivityName;
  }
  
  public String getPermission() {
    return this.permission;
  }
  
  public int getPersistableMode() {
    return this.persistableMode;
  }
  
  public int getPrivateFlags() {
    return this.privateFlags;
  }
  
  public String getRequestedVrComponent() {
    return this.requestedVrComponent;
  }
  
  public int getResizeMode() {
    return this.resizeMode;
  }
  
  public int getRotationAnimation() {
    return this.rotationAnimation;
  }
  
  public int getScreenOrientation() {
    return this.screenOrientation;
  }
  
  public int getSoftInputMode() {
    return this.softInputMode;
  }
  
  public boolean getSupportsSizeChanges() {
    return this.supportsSizeChanges;
  }
  
  public String getTargetActivity() {
    return this.targetActivity;
  }
  
  public String getTaskAffinity() {
    return this.taskAffinity;
  }
  
  public int getTheme() {
    return this.theme;
  }
  
  public int getUiOptions() {
    return this.uiOptions;
  }
  
  public ActivityInfo.WindowLayout getWindowLayout() {
    return this.windowLayout;
  }
  
  public ParsedActivity setFlags(int paramInt) {
    this.flags = paramInt;
    return this;
  }
  
  public ParsedActivity setMaxAspectRatio(int paramInt, float paramFloat) {
    if (paramInt == 2 || paramInt == 1)
      return this; 
    if (paramFloat < 1.0F && paramFloat != 0.0F)
      return this; 
    this.maxAspectRatio = Float.valueOf(paramFloat);
    return this;
  }
  
  public ParsedActivity setMinAspectRatio(int paramInt, float paramFloat) {
    if (paramInt == 2 || paramInt == 1)
      return this; 
    if (paramFloat < 1.0F && paramFloat != 0.0F)
      return this; 
    this.minAspectRatio = Float.valueOf(paramFloat);
    return this;
  }
  
  public ParsedActivity setParentActivity(String paramString) {
    this.parentActivityName = TextUtils.safeIntern(paramString);
    return this;
  }
  
  public ParsedActivity setPermission(String paramString) {
    if (TextUtils.isEmpty(paramString)) {
      paramString = null;
    } else {
      paramString = paramString.intern();
    } 
    this.permission = paramString;
    return this;
  }
  
  public ParsedActivity setResizeMode(int paramInt) {
    this.resizeMode = paramInt;
    return this;
  }
  
  public ParsedActivity setSupportsSizeChanges(boolean paramBoolean) {
    this.supportsSizeChanges = paramBoolean;
    return this;
  }
  
  public ParsedActivity setTargetActivity(String paramString) {
    this.targetActivity = TextUtils.safeIntern(paramString);
    return this;
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder(128);
    stringBuilder.append("Activity{");
    stringBuilder.append(Integer.toHexString(System.identityHashCode(this)));
    stringBuilder.append(' ');
    ComponentName.appendShortString(stringBuilder, getPackageName(), getName());
    stringBuilder.append('}');
    return stringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    super.writeToParcel(paramParcel, paramInt);
    paramParcel.writeInt(this.theme);
    paramParcel.writeInt(this.uiOptions);
    paramParcel.writeString(this.targetActivity);
    paramParcel.writeString(this.parentActivityName);
    paramParcel.writeString(this.taskAffinity);
    paramParcel.writeInt(this.privateFlags);
    ParsingPackageImpl.sForInternedString.parcel(this.permission, paramParcel, paramInt);
    paramParcel.writeInt(this.launchMode);
    paramParcel.writeInt(this.documentLaunchMode);
    paramParcel.writeInt(this.maxRecents);
    paramParcel.writeInt(this.configChanges);
    paramParcel.writeInt(this.softInputMode);
    paramParcel.writeInt(this.persistableMode);
    paramParcel.writeInt(this.lockTaskLaunchMode);
    paramParcel.writeInt(this.screenOrientation);
    paramParcel.writeInt(this.resizeMode);
    paramParcel.writeValue(this.maxAspectRatio);
    paramParcel.writeValue(this.minAspectRatio);
    paramParcel.writeBoolean(this.supportsSizeChanges);
    paramParcel.writeString(this.requestedVrComponent);
    paramParcel.writeInt(this.rotationAnimation);
    paramParcel.writeInt(this.colorMode);
    paramParcel.writeBundle(this.metaData);
    if (this.windowLayout != null) {
      paramParcel.writeInt(1);
      this.windowLayout.writeToParcel(paramParcel);
    } else {
      paramParcel.writeBoolean(false);
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/parsing/component/ParsedActivity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */