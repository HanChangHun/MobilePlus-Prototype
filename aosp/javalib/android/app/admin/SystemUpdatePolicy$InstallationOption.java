package android.app.admin;

import android.annotation.SystemApi;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@SystemApi
public class InstallationOption {
  private long mEffectiveTime;
  
  private final int mType;
  
  InstallationOption(int paramInt, long paramLong) {
    this.mType = paramInt;
    this.mEffectiveTime = paramLong;
  }
  
  public long getEffectiveTime() {
    return this.mEffectiveTime;
  }
  
  public int getType() {
    return this.mType;
  }
  
  protected void limitEffectiveTime(long paramLong) {
    this.mEffectiveTime = Long.min(this.mEffectiveTime, paramLong);
  }
  
  @Retention(RetentionPolicy.SOURCE)
  static @interface InstallationOptionType {}
}


/* Location:              /home/chun/Desktop/temp/!/android/app/admin/SystemUpdatePolicy$InstallationOption.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */