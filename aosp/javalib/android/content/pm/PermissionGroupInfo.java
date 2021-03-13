package android.content.pm;

import android.annotation.SystemApi;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class PermissionGroupInfo extends PackageItemInfo implements Parcelable {
  public static final Parcelable.Creator<PermissionGroupInfo> CREATOR = new Parcelable.Creator<PermissionGroupInfo>() {
      public PermissionGroupInfo createFromParcel(Parcel param1Parcel) {
        return new PermissionGroupInfo(param1Parcel);
      }
      
      public PermissionGroupInfo[] newArray(int param1Int) {
        return new PermissionGroupInfo[param1Int];
      }
    };
  
  public static final int FLAG_PERSONAL_INFO = 1;
  
  @SystemApi
  public final int backgroundRequestDetailResourceId;
  
  @SystemApi
  public final int backgroundRequestResourceId;
  
  public int descriptionRes;
  
  public int flags;
  
  public CharSequence nonLocalizedDescription;
  
  public int priority;
  
  @SystemApi
  public final int requestDetailResourceId;
  
  @SystemApi
  public int requestRes;
  
  @Deprecated
  public PermissionGroupInfo() {
    this(0, 0, 0);
  }
  
  public PermissionGroupInfo(int paramInt1, int paramInt2, int paramInt3) {
    this.requestDetailResourceId = paramInt1;
    this.backgroundRequestResourceId = paramInt2;
    this.backgroundRequestDetailResourceId = paramInt3;
  }
  
  @Deprecated
  public PermissionGroupInfo(PermissionGroupInfo paramPermissionGroupInfo) {
    super(paramPermissionGroupInfo);
    this.descriptionRes = paramPermissionGroupInfo.descriptionRes;
    this.requestRes = paramPermissionGroupInfo.requestRes;
    this.requestDetailResourceId = paramPermissionGroupInfo.requestDetailResourceId;
    this.backgroundRequestResourceId = paramPermissionGroupInfo.backgroundRequestResourceId;
    this.backgroundRequestDetailResourceId = paramPermissionGroupInfo.backgroundRequestDetailResourceId;
    this.nonLocalizedDescription = paramPermissionGroupInfo.nonLocalizedDescription;
    this.flags = paramPermissionGroupInfo.flags;
    this.priority = paramPermissionGroupInfo.priority;
  }
  
  private PermissionGroupInfo(Parcel paramParcel) {
    super(paramParcel);
    this.descriptionRes = paramParcel.readInt();
    this.requestRes = paramParcel.readInt();
    this.requestDetailResourceId = paramParcel.readInt();
    this.backgroundRequestResourceId = paramParcel.readInt();
    this.backgroundRequestDetailResourceId = paramParcel.readInt();
    this.nonLocalizedDescription = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(paramParcel);
    this.flags = paramParcel.readInt();
    this.priority = paramParcel.readInt();
  }
  
  public int describeContents() {
    return 0;
  }
  
  public CharSequence loadDescription(PackageManager paramPackageManager) {
    CharSequence charSequence = this.nonLocalizedDescription;
    if (charSequence != null)
      return charSequence; 
    if (this.descriptionRes != 0) {
      CharSequence charSequence1 = paramPackageManager.getText(this.packageName, this.descriptionRes, null);
      if (charSequence1 != null)
        return charSequence1; 
    } 
    return null;
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("PermissionGroupInfo{");
    stringBuilder.append(Integer.toHexString(System.identityHashCode(this)));
    stringBuilder.append(" ");
    stringBuilder.append(this.name);
    stringBuilder.append(" flgs=0x");
    stringBuilder.append(Integer.toHexString(this.flags));
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    super.writeToParcel(paramParcel, paramInt);
    paramParcel.writeInt(this.descriptionRes);
    paramParcel.writeInt(this.requestRes);
    paramParcel.writeInt(this.requestDetailResourceId);
    paramParcel.writeInt(this.backgroundRequestResourceId);
    paramParcel.writeInt(this.backgroundRequestDetailResourceId);
    TextUtils.writeToParcel(this.nonLocalizedDescription, paramParcel, paramInt);
    paramParcel.writeInt(this.flags);
    paramParcel.writeInt(this.priority);
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface Flags {}
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/PermissionGroupInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */