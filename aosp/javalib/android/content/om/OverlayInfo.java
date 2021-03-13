package android.content.om;

import android.annotation.SystemApi;
import android.os.Parcel;
import android.os.Parcelable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Objects;

@SystemApi
public final class OverlayInfo implements Parcelable {
  public static final String CATEGORY_THEME = "android.theme";
  
  public static final Parcelable.Creator<OverlayInfo> CREATOR = new Parcelable.Creator<OverlayInfo>() {
      public OverlayInfo createFromParcel(Parcel param1Parcel) {
        return new OverlayInfo(param1Parcel);
      }
      
      public OverlayInfo[] newArray(int param1Int) {
        return new OverlayInfo[param1Int];
      }
    };
  
  public static final int STATE_DISABLED = 2;
  
  public static final int STATE_ENABLED = 3;
  
  @Deprecated
  public static final int STATE_ENABLED_IMMUTABLE = 6;
  
  public static final int STATE_MISSING_TARGET = 0;
  
  public static final int STATE_NO_IDMAP = 1;
  
  public static final int STATE_OVERLAY_IS_BEING_REPLACED = 5;
  
  @Deprecated
  public static final int STATE_TARGET_IS_BEING_REPLACED = 4;
  
  public static final int STATE_UNKNOWN = -1;
  
  public final String baseCodePath;
  
  public final String category;
  
  public final boolean isMutable;
  
  public final String packageName;
  
  public final int priority;
  
  public final int state;
  
  public final String targetOverlayableName;
  
  public final String targetPackageName;
  
  public final int userId;
  
  public OverlayInfo(OverlayInfo paramOverlayInfo, int paramInt) {
    this(paramOverlayInfo.packageName, paramOverlayInfo.targetPackageName, paramOverlayInfo.targetOverlayableName, paramOverlayInfo.category, paramOverlayInfo.baseCodePath, paramInt, paramOverlayInfo.userId, paramOverlayInfo.priority, paramOverlayInfo.isMutable);
  }
  
  public OverlayInfo(Parcel paramParcel) {
    this.packageName = paramParcel.readString();
    this.targetPackageName = paramParcel.readString();
    this.targetOverlayableName = paramParcel.readString();
    this.category = paramParcel.readString();
    this.baseCodePath = paramParcel.readString();
    this.state = paramParcel.readInt();
    this.userId = paramParcel.readInt();
    this.priority = paramParcel.readInt();
    this.isMutable = paramParcel.readBoolean();
    ensureValidState();
  }
  
  public OverlayInfo(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean) {
    this.packageName = paramString1;
    this.targetPackageName = paramString2;
    this.targetOverlayableName = paramString3;
    this.category = paramString4;
    this.baseCodePath = paramString5;
    this.state = paramInt1;
    this.userId = paramInt2;
    this.priority = paramInt3;
    this.isMutable = paramBoolean;
    ensureValidState();
  }
  
  private void ensureValidState() {
    if (this.packageName != null) {
      if (this.targetPackageName != null) {
        if (this.baseCodePath != null) {
          StringBuilder stringBuilder;
          switch (this.state) {
            default:
              stringBuilder = new StringBuilder();
              stringBuilder.append("State ");
              stringBuilder.append(this.state);
              stringBuilder.append(" is not a valid state");
              throw new IllegalArgumentException(stringBuilder.toString());
            case -1:
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
              break;
          } 
          return;
        } 
        throw new IllegalArgumentException("baseCodePath must not be null");
      } 
      throw new IllegalArgumentException("targetPackageName must not be null");
    } 
    throw new IllegalArgumentException("packageName must not be null");
  }
  
  public static String stateToString(int paramInt) {
    switch (paramInt) {
      default:
        return "<unknown state>";
      case 6:
        return "STATE_ENABLED_IMMUTABLE";
      case 5:
        return "STATE_OVERLAY_IS_BEING_REPLACED";
      case 4:
        return "STATE_TARGET_IS_BEING_REPLACED";
      case 3:
        return "STATE_ENABLED";
      case 2:
        return "STATE_DISABLED";
      case 1:
        return "STATE_NO_IDMAP";
      case 0:
        return "STATE_MISSING_TARGET";
      case -1:
        break;
    } 
    return "STATE_UNKNOWN";
  }
  
  public int describeContents() {
    return 0;
  }
  
  public boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (paramObject == null)
      return false; 
    if (getClass() != paramObject.getClass())
      return false; 
    paramObject = paramObject;
    return (this.userId != ((OverlayInfo)paramObject).userId) ? false : ((this.state != ((OverlayInfo)paramObject).state) ? false : (!this.packageName.equals(((OverlayInfo)paramObject).packageName) ? false : (!this.targetPackageName.equals(((OverlayInfo)paramObject).targetPackageName) ? false : (!Objects.equals(this.targetOverlayableName, ((OverlayInfo)paramObject).targetOverlayableName) ? false : (!Objects.equals(this.category, ((OverlayInfo)paramObject).category) ? false : (!!this.baseCodePath.equals(((OverlayInfo)paramObject).baseCodePath)))))));
  }
  
  @SystemApi
  public String getCategory() {
    return this.category;
  }
  
  @SystemApi
  public String getPackageName() {
    return this.packageName;
  }
  
  @SystemApi
  public String getTargetOverlayableName() {
    return this.targetOverlayableName;
  }
  
  @SystemApi
  public String getTargetPackageName() {
    return this.targetPackageName;
  }
  
  @SystemApi
  public int getUserId() {
    return this.userId;
  }
  
  public int hashCode() {
    int m;
    int n;
    int i1;
    int i2;
    int i = this.userId;
    int j = this.state;
    String str = this.packageName;
    int k = 0;
    if (str == null) {
      m = 0;
    } else {
      m = str.hashCode();
    } 
    str = this.targetPackageName;
    if (str == null) {
      n = 0;
    } else {
      n = str.hashCode();
    } 
    str = this.targetOverlayableName;
    if (str == null) {
      i1 = 0;
    } else {
      i1 = str.hashCode();
    } 
    str = this.category;
    if (str == null) {
      i2 = 0;
    } else {
      i2 = str.hashCode();
    } 
    str = this.baseCodePath;
    if (str != null)
      k = str.hashCode(); 
    return ((((((1 * 31 + i) * 31 + j) * 31 + m) * 31 + n) * 31 + i1) * 31 + i2) * 31 + k;
  }
  
  @SystemApi
  public boolean isEnabled() {
    int i = this.state;
    return !(i != 3 && i != 6);
  }
  
  public String toString() {
    String str;
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("OverlayInfo { overlay=");
    stringBuilder.append(this.packageName);
    stringBuilder.append(", targetPackage=");
    stringBuilder.append(this.targetPackageName);
    if (this.targetOverlayableName == null) {
      str = "";
    } else {
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append(", targetOverlayable=");
      stringBuilder1.append(this.targetOverlayableName);
      str = stringBuilder1.toString();
    } 
    stringBuilder.append(str);
    stringBuilder.append(", state=");
    stringBuilder.append(this.state);
    stringBuilder.append(" (");
    stringBuilder.append(stateToString(this.state));
    stringBuilder.append("), userId=");
    stringBuilder.append(this.userId);
    stringBuilder.append(" }");
    return stringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeString(this.packageName);
    paramParcel.writeString(this.targetPackageName);
    paramParcel.writeString(this.targetOverlayableName);
    paramParcel.writeString(this.category);
    paramParcel.writeString(this.baseCodePath);
    paramParcel.writeInt(this.state);
    paramParcel.writeInt(this.userId);
    paramParcel.writeInt(this.priority);
    paramParcel.writeBoolean(this.isMutable);
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface State {}
}


/* Location:              /home/chun/Desktop/temp/!/android/content/om/OverlayInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */