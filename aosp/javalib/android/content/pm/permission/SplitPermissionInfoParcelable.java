package android.content.pm.permission;

import android.annotation.IntRange;
import android.annotation.NonNull;
import android.os.Parcel;
import android.os.Parcelable;
import com.android.internal.util.AnnotationValidations;
import com.android.internal.util.Preconditions;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SplitPermissionInfoParcelable implements Parcelable {
  public static final Parcelable.Creator<SplitPermissionInfoParcelable> CREATOR = new Parcelable.Creator<SplitPermissionInfoParcelable>() {
      public SplitPermissionInfoParcelable createFromParcel(Parcel param1Parcel) {
        String str = param1Parcel.readString();
        ArrayList<String> arrayList = new ArrayList();
        param1Parcel.readStringList(arrayList);
        return new SplitPermissionInfoParcelable(str, arrayList, param1Parcel.readInt());
      }
      
      public SplitPermissionInfoParcelable[] newArray(int param1Int) {
        return new SplitPermissionInfoParcelable[param1Int];
      }
    };
  
  private final List<String> mNewPermissions;
  
  private final String mSplitPermission;
  
  private final int mTargetSdk;
  
  public SplitPermissionInfoParcelable(String paramString, List<String> paramList, int paramInt) {
    this.mSplitPermission = paramString;
    AnnotationValidations.validate(NonNull.class, null, paramString);
    this.mNewPermissions = paramList;
    AnnotationValidations.validate(NonNull.class, null, paramList);
    this.mTargetSdk = paramInt;
    AnnotationValidations.validate(IntRange.class, null, paramInt, "from", 0L);
    onConstructed();
  }
  
  @Deprecated
  private void __metadata() {}
  
  private void onConstructed() {
    Preconditions.checkCollectionElementsNotNull(this.mNewPermissions, "newPermissions");
  }
  
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
    if (!Objects.equals(this.mSplitPermission, ((SplitPermissionInfoParcelable)paramObject).mSplitPermission) || !Objects.equals(this.mNewPermissions, ((SplitPermissionInfoParcelable)paramObject).mNewPermissions) || this.mTargetSdk != ((SplitPermissionInfoParcelable)paramObject).mTargetSdk)
      bool = false; 
    return bool;
  }
  
  public List<String> getNewPermissions() {
    return this.mNewPermissions;
  }
  
  public String getSplitPermission() {
    return this.mSplitPermission;
  }
  
  public int getTargetSdk() {
    return this.mTargetSdk;
  }
  
  public int hashCode() {
    return ((1 * 31 + Objects.hashCode(this.mSplitPermission)) * 31 + Objects.hashCode(this.mNewPermissions)) * 31 + this.mTargetSdk;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeString(this.mSplitPermission);
    paramParcel.writeStringList(this.mNewPermissions);
    paramParcel.writeInt(this.mTargetSdk);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/permission/SplitPermissionInfoParcelable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */