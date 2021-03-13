package android.content.pm;

import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import java.util.Collections;
import java.util.List;

public final class AuxiliaryResolveInfo {
  public final Intent failureIntent;
  
  public final List<AuxiliaryFilter> filters;
  
  public final int[] hostDigestPrefixSecure;
  
  public final ComponentName installFailureActivity;
  
  public final boolean needsPhaseTwo;
  
  public final String token;
  
  public AuxiliaryResolveInfo(ComponentName paramComponentName, Intent paramIntent, List<AuxiliaryFilter> paramList) {
    this.installFailureActivity = paramComponentName;
    this.filters = paramList;
    this.token = null;
    this.needsPhaseTwo = false;
    this.failureIntent = paramIntent;
    this.hostDigestPrefixSecure = null;
  }
  
  public AuxiliaryResolveInfo(ComponentName paramComponentName, String paramString1, long paramLong, String paramString2) {
    this(paramComponentName, null, Collections.singletonList(new AuxiliaryFilter(paramString1, paramLong, paramString2)));
  }
  
  public AuxiliaryResolveInfo(String paramString, boolean paramBoolean, Intent paramIntent, List<AuxiliaryFilter> paramList, int[] paramArrayOfint) {
    this.token = paramString;
    this.needsPhaseTwo = paramBoolean;
    this.failureIntent = paramIntent;
    this.filters = paramList;
    this.installFailureActivity = null;
    this.hostDigestPrefixSecure = paramArrayOfint;
  }
  
  public static final class AuxiliaryFilter extends IntentFilter {
    public final Bundle extras;
    
    public final String packageName;
    
    public final InstantAppResolveInfo resolveInfo;
    
    public final String splitName;
    
    public final long versionCode;
    
    public AuxiliaryFilter(IntentFilter param1IntentFilter, InstantAppResolveInfo param1InstantAppResolveInfo, String param1String, Bundle param1Bundle) {
      super(param1IntentFilter);
      this.resolveInfo = param1InstantAppResolveInfo;
      this.packageName = param1InstantAppResolveInfo.getPackageName();
      this.versionCode = param1InstantAppResolveInfo.getLongVersionCode();
      this.splitName = param1String;
      this.extras = param1Bundle;
    }
    
    public AuxiliaryFilter(InstantAppResolveInfo param1InstantAppResolveInfo, String param1String, Bundle param1Bundle) {
      this.resolveInfo = param1InstantAppResolveInfo;
      this.packageName = param1InstantAppResolveInfo.getPackageName();
      this.versionCode = param1InstantAppResolveInfo.getLongVersionCode();
      this.splitName = param1String;
      this.extras = param1Bundle;
    }
    
    public AuxiliaryFilter(String param1String1, long param1Long, String param1String2) {
      this.resolveInfo = null;
      this.packageName = param1String1;
      this.versionCode = param1Long;
      this.splitName = param1String2;
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
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/AuxiliaryResolveInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */