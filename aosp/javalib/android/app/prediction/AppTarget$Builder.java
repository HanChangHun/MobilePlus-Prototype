package android.app.prediction;

import android.annotation.SystemApi;
import android.content.pm.ShortcutInfo;
import android.os.UserHandle;
import java.util.Objects;

@SystemApi
public final class Builder {
  private String mClassName;
  
  private final AppTargetId mId;
  
  private String mPackageName;
  
  private int mRank;
  
  private ShortcutInfo mShortcutInfo;
  
  private UserHandle mUser;
  
  @SystemApi
  @Deprecated
  public Builder(AppTargetId paramAppTargetId) {
    this.mId = paramAppTargetId;
  }
  
  @SystemApi
  public Builder(AppTargetId paramAppTargetId, ShortcutInfo paramShortcutInfo) {
    Objects.requireNonNull(paramAppTargetId);
    this.mId = paramAppTargetId;
    Objects.requireNonNull(paramShortcutInfo);
    this.mShortcutInfo = paramShortcutInfo;
    this.mPackageName = paramShortcutInfo.getPackage();
    this.mUser = paramShortcutInfo.getUserHandle();
  }
  
  @SystemApi
  public Builder(AppTargetId paramAppTargetId, String paramString, UserHandle paramUserHandle) {
    Objects.requireNonNull(paramAppTargetId);
    this.mId = paramAppTargetId;
    Objects.requireNonNull(paramString);
    this.mPackageName = paramString;
    Objects.requireNonNull(paramUserHandle);
    this.mUser = paramUserHandle;
  }
  
  public AppTarget build() {
    if (this.mPackageName != null)
      return new AppTarget(this.mId, this.mPackageName, this.mUser, this.mShortcutInfo, this.mClassName, this.mRank, null); 
    throw new IllegalStateException("No target is set");
  }
  
  public Builder setClassName(String paramString) {
    Objects.requireNonNull(paramString);
    this.mClassName = paramString;
    return this;
  }
  
  public Builder setRank(int paramInt) {
    if (paramInt >= 0) {
      this.mRank = paramInt;
      return this;
    } 
    throw new IllegalArgumentException("rank cannot be a negative value");
  }
  
  @Deprecated
  public Builder setTarget(ShortcutInfo paramShortcutInfo) {
    setTarget(paramShortcutInfo.getPackage(), paramShortcutInfo.getUserHandle());
    Objects.requireNonNull(paramShortcutInfo);
    this.mShortcutInfo = paramShortcutInfo;
    return this;
  }
  
  @Deprecated
  public Builder setTarget(String paramString, UserHandle paramUserHandle) {
    if (this.mPackageName == null) {
      Objects.requireNonNull(paramString);
      this.mPackageName = paramString;
      Objects.requireNonNull(paramUserHandle);
      this.mUser = paramUserHandle;
      return this;
    } 
    throw new IllegalArgumentException("Target is already set");
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/prediction/AppTarget$Builder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */