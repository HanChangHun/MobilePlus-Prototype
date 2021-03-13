package android.app.admin;

import android.content.ComponentName;
import android.content.Intent;
import android.os.UserHandle;
import java.util.List;

public abstract class DevicePolicyManagerInternal {
  public abstract void addOnCrossProfileWidgetProvidersChangeListener(OnCrossProfileWidgetProvidersChangeListener paramOnCrossProfileWidgetProvidersChangeListener);
  
  public abstract void broadcastIntentToCrossProfileManifestReceiversAsUser(Intent paramIntent, UserHandle paramUserHandle, boolean paramBoolean);
  
  public abstract boolean canSilentlyInstallPackage(String paramString, int paramInt);
  
  public abstract Intent createShowAdminSupportIntent(int paramInt, boolean paramBoolean);
  
  public abstract Intent createUserRestrictionSupportIntent(int paramInt, String paramString);
  
  public abstract List<String> getAllCrossProfilePackages();
  
  public abstract List<String> getCrossProfileWidgetProviders(int paramInt);
  
  public abstract List<String> getDefaultCrossProfilePackages();
  
  protected abstract DevicePolicyCache getDevicePolicyCache();
  
  protected abstract DeviceStateCache getDeviceStateCache();
  
  public abstract CharSequence getPrintingDisabledReasonForUser(int paramInt);
  
  public abstract ComponentName getProfileOwnerAsUser(int paramInt);
  
  public abstract boolean isActiveAdminWithPolicy(int paramInt1, int paramInt2);
  
  public abstract boolean isActiveSupervisionApp(int paramInt);
  
  public abstract boolean isUserAffiliatedWithDevice(int paramInt);
  
  public abstract void reportSeparateProfileChallengeChanged(int paramInt);
  
  public static interface OnCrossProfileWidgetProvidersChangeListener {
    void onCrossProfileWidgetProvidersChanged(int param1Int, List<String> param1List);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/admin/DevicePolicyManagerInternal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */