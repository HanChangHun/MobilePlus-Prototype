package android.content.pm;

import android.appwidget.AppWidgetProviderInfo;
import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.LocusId;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import java.util.List;

public abstract class ShortcutServiceInternal {
  public abstract void addListener(ShortcutChangeListener paramShortcutChangeListener);
  
  public abstract void addShortcutChangeCallback(LauncherApps.ShortcutChangeCallback paramShortcutChangeCallback);
  
  public abstract void cacheShortcuts(int paramInt1, String paramString1, String paramString2, List<String> paramList, int paramInt2, int paramInt3);
  
  public abstract Intent[] createShortcutIntents(int paramInt1, String paramString1, String paramString2, String paramString3, int paramInt2, int paramInt3, int paramInt4);
  
  public abstract List<ShortcutManager.ShareShortcutInfo> getShareTargets(String paramString, IntentFilter paramIntentFilter, int paramInt);
  
  public abstract ParcelFileDescriptor getShortcutIconFd(int paramInt1, String paramString1, String paramString2, String paramString3, int paramInt2);
  
  public abstract int getShortcutIconResId(int paramInt1, String paramString1, String paramString2, String paramString3, int paramInt2);
  
  public abstract String getShortcutIconUri(int paramInt1, String paramString1, String paramString2, String paramString3, int paramInt2);
  
  public abstract List<ShortcutInfo> getShortcuts(int paramInt1, String paramString1, long paramLong, String paramString2, List<String> paramList, List<LocusId> paramList1, ComponentName paramComponentName, int paramInt2, int paramInt3, int paramInt4, int paramInt5);
  
  public abstract boolean hasShortcutHostPermission(int paramInt1, String paramString, int paramInt2, int paramInt3);
  
  public abstract boolean isForegroundDefaultLauncher(String paramString, int paramInt);
  
  public abstract boolean isPinnedByCaller(int paramInt1, String paramString1, String paramString2, String paramString3, int paramInt2);
  
  public abstract boolean isRequestPinItemSupported(int paramInt1, int paramInt2);
  
  public abstract boolean isSharingShortcut(int paramInt1, String paramString1, String paramString2, String paramString3, int paramInt2, IntentFilter paramIntentFilter);
  
  public abstract void pinShortcuts(int paramInt1, String paramString1, String paramString2, List<String> paramList, int paramInt2);
  
  public abstract boolean requestPinAppWidget(String paramString, AppWidgetProviderInfo paramAppWidgetProviderInfo, Bundle paramBundle, IntentSender paramIntentSender, int paramInt);
  
  public abstract void setShortcutHostPackage(String paramString1, String paramString2, int paramInt);
  
  public abstract void uncacheShortcuts(int paramInt1, String paramString1, String paramString2, List<String> paramList, int paramInt2, int paramInt3);
  
  public static interface ShortcutChangeListener {
    void onShortcutChanged(String param1String, int param1Int);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/ShortcutServiceInternal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */