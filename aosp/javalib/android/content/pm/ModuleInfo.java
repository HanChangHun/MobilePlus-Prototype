package android.content.pm;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Objects;

public final class ModuleInfo implements Parcelable {
  public static final Parcelable.Creator<ModuleInfo> CREATOR = new Parcelable.Creator<ModuleInfo>() {
      public ModuleInfo createFromParcel(Parcel param1Parcel) {
        return new ModuleInfo(param1Parcel);
      }
      
      public ModuleInfo[] newArray(int param1Int) {
        return new ModuleInfo[param1Int];
      }
    };
  
  private String mApexModuleName;
  
  private boolean mHidden;
  
  private CharSequence mName;
  
  private String mPackageName;
  
  public ModuleInfo() {}
  
  public ModuleInfo(ModuleInfo paramModuleInfo) {
    this.mName = paramModuleInfo.mName;
    this.mPackageName = paramModuleInfo.mPackageName;
    this.mHidden = paramModuleInfo.mHidden;
    this.mApexModuleName = paramModuleInfo.mApexModuleName;
  }
  
  private ModuleInfo(Parcel paramParcel) {
    this.mName = paramParcel.readCharSequence();
    this.mPackageName = paramParcel.readString();
    this.mHidden = paramParcel.readBoolean();
    this.mApexModuleName = paramParcel.readString();
  }
  
  public int describeContents() {
    return 0;
  }
  
  public boolean equals(Object paramObject) {
    boolean bool = paramObject instanceof ModuleInfo;
    boolean bool1 = false;
    if (!bool)
      return false; 
    paramObject = paramObject;
    if (Objects.equals(this.mName, ((ModuleInfo)paramObject).mName) && Objects.equals(this.mPackageName, ((ModuleInfo)paramObject).mPackageName) && Objects.equals(this.mApexModuleName, ((ModuleInfo)paramObject).mApexModuleName) && this.mHidden == ((ModuleInfo)paramObject).mHidden)
      bool1 = true; 
    return bool1;
  }
  
  public String getApexModuleName() {
    return this.mApexModuleName;
  }
  
  public CharSequence getName() {
    return this.mName;
  }
  
  public String getPackageName() {
    return this.mPackageName;
  }
  
  public int hashCode() {
    return (((0 * 31 + Objects.hashCode(this.mName)) * 31 + Objects.hashCode(this.mPackageName)) * 31 + Objects.hashCode(this.mApexModuleName)) * 31 + Boolean.hashCode(this.mHidden);
  }
  
  public boolean isHidden() {
    return this.mHidden;
  }
  
  public ModuleInfo setApexModuleName(String paramString) {
    this.mApexModuleName = paramString;
    return this;
  }
  
  public ModuleInfo setHidden(boolean paramBoolean) {
    this.mHidden = paramBoolean;
    return this;
  }
  
  public ModuleInfo setName(CharSequence paramCharSequence) {
    this.mName = paramCharSequence;
    return this;
  }
  
  public ModuleInfo setPackageName(String paramString) {
    this.mPackageName = paramString;
    return this;
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("ModuleInfo{");
    stringBuilder.append(Integer.toHexString(System.identityHashCode(this)));
    stringBuilder.append(" ");
    stringBuilder.append(this.mName);
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeCharSequence(this.mName);
    paramParcel.writeString(this.mPackageName);
    paramParcel.writeBoolean(this.mHidden);
    paramParcel.writeString(this.mApexModuleName);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/ModuleInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */