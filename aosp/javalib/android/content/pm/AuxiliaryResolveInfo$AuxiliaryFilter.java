package android.content.pm;

import android.content.IntentFilter;
import android.os.Bundle;

public final class AuxiliaryFilter extends IntentFilter {
  public final Bundle extras;
  
  public final String packageName;
  
  public final InstantAppResolveInfo resolveInfo;
  
  public final String splitName;
  
  public final long versionCode;
  
  public AuxiliaryFilter(IntentFilter paramIntentFilter, InstantAppResolveInfo paramInstantAppResolveInfo, String paramString, Bundle paramBundle) {
    super(paramIntentFilter);
    this.resolveInfo = paramInstantAppResolveInfo;
    this.packageName = paramInstantAppResolveInfo.getPackageName();
    this.versionCode = paramInstantAppResolveInfo.getLongVersionCode();
    this.splitName = paramString;
    this.extras = paramBundle;
  }
  
  public AuxiliaryFilter(InstantAppResolveInfo paramInstantAppResolveInfo, String paramString, Bundle paramBundle) {
    this.resolveInfo = paramInstantAppResolveInfo;
    this.packageName = paramInstantAppResolveInfo.getPackageName();
    this.versionCode = paramInstantAppResolveInfo.getLongVersionCode();
    this.splitName = paramString;
    this.extras = paramBundle;
  }
  
  public AuxiliaryFilter(String paramString1, long paramLong, String paramString2) {
    this.resolveInfo = null;
    this.packageName = paramString1;
    this.versionCode = paramLong;
    this.splitName = paramString2;
    this.extras = null;
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("AuxiliaryFilter{packageName='");
    stringBuilder.append(this.packageName);
    stringBuilder.append('\'');
    stringBuilder.append(", versionCode=");
    stringBuilder.append(this.versionCode);
    stringBuilder.append(", splitName='");
    stringBuilder.append(this.splitName);
    stringBuilder.append('\'');
    stringBuilder.append('}');
    return stringBuilder.toString();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/AuxiliaryResolveInfo$AuxiliaryFilter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */