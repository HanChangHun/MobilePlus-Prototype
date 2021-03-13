package android.content.pm;

import android.annotation.SystemApi;
import android.app.Activity;
import android.app.IApplicationThread;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.UserHandle;
import android.os.UserManager;
import com.android.internal.util.UserIcons;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CrossProfileApps {
  public static final String ACTION_CAN_INTERACT_ACROSS_PROFILES_CHANGED = "android.content.pm.action.CAN_INTERACT_ACROSS_PROFILES_CHANGED";
  
  private final Context mContext;
  
  private final Resources mResources;
  
  private final ICrossProfileApps mService;
  
  private final UserManager mUserManager;
  
  public CrossProfileApps(Context paramContext, ICrossProfileApps paramICrossProfileApps) {
    this.mContext = paramContext;
    this.mService = paramICrossProfileApps;
    this.mUserManager = (UserManager)paramContext.getSystemService(UserManager.class);
    this.mResources = paramContext.getResources();
  }
  
  private void verifyCanAccessUser(UserHandle paramUserHandle) {
    if (getTargetUserProfiles().contains(paramUserHandle))
      return; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Not allowed to access ");
    stringBuilder.append(paramUserHandle);
    throw new SecurityException(stringBuilder.toString());
  }
  
  public boolean canConfigureInteractAcrossProfiles(String paramString) {
    try {
      return this.mService.canConfigureInteractAcrossProfiles(paramString);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public boolean canInteractAcrossProfiles() {
    try {
      return this.mService.canInteractAcrossProfiles(this.mContext.getPackageName());
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public boolean canRequestInteractAcrossProfiles() {
    try {
      return this.mService.canRequestInteractAcrossProfiles(this.mContext.getPackageName());
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public boolean canUserAttemptToConfigureInteractAcrossProfiles(String paramString) {
    try {
      return this.mService.canUserAttemptToConfigureInteractAcrossProfiles(paramString);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void clearInteractAcrossProfilesAppOps() {
    try {
      this.mService.clearInteractAcrossProfilesAppOps();
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public Intent createRequestInteractAcrossProfilesIntent() {
    if (canRequestInteractAcrossProfiles()) {
      Intent intent = new Intent();
      intent.setAction("android.settings.MANAGE_CROSS_PROFILE_ACCESS");
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("package:");
      stringBuilder.append(this.mContext.getPackageName());
      intent.setData(Uri.parse(stringBuilder.toString()));
      return intent;
    } 
    throw new SecurityException("The calling package can not request to interact across profiles.");
  }
  
  public Drawable getProfileSwitchingIconDrawable(UserHandle paramUserHandle) {
    verifyCanAccessUser(paramUserHandle);
    return this.mUserManager.isManagedProfile(paramUserHandle.getIdentifier()) ? this.mResources.getDrawable(17302381, null) : UserIcons.getDefaultUserIcon(this.mResources, 0, true);
  }
  
  public CharSequence getProfileSwitchingLabel(UserHandle paramUserHandle) {
    int i;
    verifyCanAccessUser(paramUserHandle);
    if (this.mUserManager.isManagedProfile(paramUserHandle.getIdentifier())) {
      i = 17040495;
    } else {
      i = 17041406;
    } 
    return this.mResources.getString(i);
  }
  
  public List<UserHandle> getTargetUserProfiles() {
    try {
      return this.mService.getTargetUserProfiles(this.mContext.getPackageName());
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void resetInteractAcrossProfilesAppOps(Collection<String> paramCollection, Set<String> paramSet) {
    if (paramCollection.isEmpty())
      return; 
    paramCollection = (List)paramCollection.stream().filter(new _$$Lambda$CrossProfileApps$q8A4D5J_ieuBm118fz0XxGYzHwQ(paramSet)).collect(Collectors.toList());
    if (paramCollection.isEmpty())
      return; 
    try {
      this.mService.resetInteractAcrossProfilesAppOps((List<String>)paramCollection);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void setInteractAcrossProfilesAppOp(String paramString, int paramInt) {
    try {
      this.mService.setInteractAcrossProfilesAppOp(paramString, paramInt);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  @SystemApi
  public void startActivity(ComponentName paramComponentName, UserHandle paramUserHandle) {
    try {
      this.mService.startActivityAsUser(this.mContext.getIApplicationThread(), this.mContext.getPackageName(), this.mContext.getAttributionTag(), paramComponentName, paramUserHandle.getIdentifier(), false);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void startActivity(Intent paramIntent, UserHandle paramUserHandle, Activity paramActivity) {
    startActivity(paramIntent, paramUserHandle, paramActivity, null);
  }
  
  public void startActivity(Intent paramIntent, UserHandle paramUserHandle, Activity paramActivity, Bundle paramBundle) {
    try {
      ICrossProfileApps iCrossProfileApps = this.mService;
      IApplicationThread iApplicationThread = this.mContext.getIApplicationThread();
      String str1 = this.mContext.getPackageName();
      String str2 = this.mContext.getAttributionTag();
      int i = paramUserHandle.getIdentifier();
      if (paramActivity != null) {
        IBinder iBinder = paramActivity.getActivityToken();
      } else {
        paramUserHandle = null;
      } 
      iCrossProfileApps.startActivityAsUserByIntent(iApplicationThread, str1, str2, paramIntent, i, (IBinder)paramUserHandle, paramBundle);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void startMainActivity(ComponentName paramComponentName, UserHandle paramUserHandle) {
    try {
      this.mService.startActivityAsUser(this.mContext.getIApplicationThread(), this.mContext.getPackageName(), this.mContext.getAttributionTag(), paramComponentName, paramUserHandle.getIdentifier(), true);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/CrossProfileApps.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */