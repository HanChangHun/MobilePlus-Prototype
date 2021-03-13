package android.content.pm;

import android.content.Intent;
import android.os.Bundle;

public final class InstantAppRequest {
  public final String callingFeatureId;
  
  public final String callingPackage;
  
  public final int[] hostDigestPrefixSecure;
  
  public final boolean isRequesterInstantApp;
  
  public final Intent origIntent;
  
  public final boolean resolveForStart;
  
  public final String resolvedType;
  
  public final AuxiliaryResolveInfo responseObj;
  
  public final String token;
  
  public final int userId;
  
  public final Bundle verificationBundle;
  
  public InstantAppRequest(AuxiliaryResolveInfo paramAuxiliaryResolveInfo, Intent paramIntent, String paramString1, String paramString2, String paramString3, boolean paramBoolean1, int paramInt, Bundle paramBundle, boolean paramBoolean2, int[] paramArrayOfint, String paramString4) {
    this.responseObj = paramAuxiliaryResolveInfo;
    this.origIntent = paramIntent;
    this.resolvedType = paramString1;
    this.callingPackage = paramString2;
    this.callingFeatureId = paramString3;
    this.isRequesterInstantApp = paramBoolean1;
    this.userId = paramInt;
    this.verificationBundle = paramBundle;
    this.resolveForStart = paramBoolean2;
    this.hostDigestPrefixSecure = paramArrayOfint;
    this.token = paramString4;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/InstantAppRequest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */