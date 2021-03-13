package android.appwidget;

import android.app.IServiceConnection;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.ParceledListSlice;
import android.os.Bundle;
import android.os.RemoteException;
import android.os.UserHandle;
import android.util.DisplayMetrics;
import android.widget.RemoteViews;
import com.android.internal.appwidget.IAppWidgetService;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class AppWidgetManager {
  public static final String ACTION_APPWIDGET_BIND = "android.appwidget.action.APPWIDGET_BIND";
  
  public static final String ACTION_APPWIDGET_CONFIGURE = "android.appwidget.action.APPWIDGET_CONFIGURE";
  
  public static final String ACTION_APPWIDGET_DELETED = "android.appwidget.action.APPWIDGET_DELETED";
  
  public static final String ACTION_APPWIDGET_DISABLED = "android.appwidget.action.APPWIDGET_DISABLED";
  
  public static final String ACTION_APPWIDGET_ENABLED = "android.appwidget.action.APPWIDGET_ENABLED";
  
  public static final String ACTION_APPWIDGET_HOST_RESTORED = "android.appwidget.action.APPWIDGET_HOST_RESTORED";
  
  public static final String ACTION_APPWIDGET_OPTIONS_CHANGED = "android.appwidget.action.APPWIDGET_UPDATE_OPTIONS";
  
  public static final String ACTION_APPWIDGET_PICK = "android.appwidget.action.APPWIDGET_PICK";
  
  public static final String ACTION_APPWIDGET_RESTORED = "android.appwidget.action.APPWIDGET_RESTORED";
  
  public static final String ACTION_APPWIDGET_UPDATE = "android.appwidget.action.APPWIDGET_UPDATE";
  
  public static final String ACTION_KEYGUARD_APPWIDGET_PICK = "android.appwidget.action.KEYGUARD_APPWIDGET_PICK";
  
  public static final String EXTRA_APPWIDGET_ID = "appWidgetId";
  
  public static final String EXTRA_APPWIDGET_IDS = "appWidgetIds";
  
  public static final String EXTRA_APPWIDGET_OLD_IDS = "appWidgetOldIds";
  
  public static final String EXTRA_APPWIDGET_OPTIONS = "appWidgetOptions";
  
  public static final String EXTRA_APPWIDGET_PREVIEW = "appWidgetPreview";
  
  public static final String EXTRA_APPWIDGET_PROVIDER = "appWidgetProvider";
  
  public static final String EXTRA_APPWIDGET_PROVIDER_PROFILE = "appWidgetProviderProfile";
  
  public static final String EXTRA_CATEGORY_FILTER = "categoryFilter";
  
  public static final String EXTRA_CUSTOM_EXTRAS = "customExtras";
  
  public static final String EXTRA_CUSTOM_INFO = "customInfo";
  
  public static final String EXTRA_CUSTOM_SORT = "customSort";
  
  public static final String EXTRA_HOST_ID = "hostId";
  
  public static final int INVALID_APPWIDGET_ID = 0;
  
  public static final String META_DATA_APPWIDGET_PROVIDER = "android.appwidget.provider";
  
  public static final String OPTION_APPWIDGET_HOST_CATEGORY = "appWidgetCategory";
  
  public static final String OPTION_APPWIDGET_MAX_HEIGHT = "appWidgetMaxHeight";
  
  public static final String OPTION_APPWIDGET_MAX_WIDTH = "appWidgetMaxWidth";
  
  public static final String OPTION_APPWIDGET_MIN_HEIGHT = "appWidgetMinHeight";
  
  public static final String OPTION_APPWIDGET_MIN_WIDTH = "appWidgetMinWidth";
  
  public static final String OPTION_APPWIDGET_RESTORE_COMPLETED = "appWidgetRestoreCompleted";
  
  private final Context mContext;
  
  private final DisplayMetrics mDisplayMetrics;
  
  private final String mPackageName;
  
  private final IAppWidgetService mService;
  
  public AppWidgetManager(Context paramContext, IAppWidgetService paramIAppWidgetService) {
    this.mContext = paramContext;
    this.mPackageName = paramContext.getOpPackageName();
    this.mService = paramIAppWidgetService;
    this.mDisplayMetrics = paramContext.getResources().getDisplayMetrics();
  }
  
  private boolean bindAppWidgetIdIfAllowed(int paramInt1, int paramInt2, ComponentName paramComponentName, Bundle paramBundle) {
    IAppWidgetService iAppWidgetService = this.mService;
    if (iAppWidgetService == null)
      return false; 
    try {
      return iAppWidgetService.bindAppWidgetId(this.mPackageName, paramInt1, paramInt2, paramComponentName, paramBundle);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public static AppWidgetManager getInstance(Context paramContext) {
    return (AppWidgetManager)paramContext.getSystemService("appwidget");
  }
  
  public void bindAppWidgetId(int paramInt, ComponentName paramComponentName) {
    if (this.mService == null)
      return; 
    bindAppWidgetId(paramInt, paramComponentName, null);
  }
  
  public void bindAppWidgetId(int paramInt, ComponentName paramComponentName, Bundle paramBundle) {
    if (this.mService == null)
      return; 
    bindAppWidgetIdIfAllowed(paramInt, this.mContext.getUser(), paramComponentName, paramBundle);
  }
  
  public boolean bindAppWidgetIdIfAllowed(int paramInt, ComponentName paramComponentName) {
    return (this.mService == null) ? false : bindAppWidgetIdIfAllowed(paramInt, this.mContext.getUserId(), paramComponentName, (Bundle)null);
  }
  
  public boolean bindAppWidgetIdIfAllowed(int paramInt, ComponentName paramComponentName, Bundle paramBundle) {
    return (this.mService == null) ? false : bindAppWidgetIdIfAllowed(paramInt, this.mContext.getUserId(), paramComponentName, paramBundle);
  }
  
  public boolean bindAppWidgetIdIfAllowed(int paramInt, UserHandle paramUserHandle, ComponentName paramComponentName, Bundle paramBundle) {
    return (this.mService == null) ? false : bindAppWidgetIdIfAllowed(paramInt, paramUserHandle.getIdentifier(), paramComponentName, paramBundle);
  }
  
  public boolean bindRemoteViewsService(Context paramContext, int paramInt1, Intent paramIntent, IServiceConnection paramIServiceConnection, int paramInt2) {
    IAppWidgetService iAppWidgetService = this.mService;
    if (iAppWidgetService == null)
      return false; 
    try {
      return iAppWidgetService.bindRemoteViewsService(paramContext.getOpPackageName(), paramInt1, paramIntent, paramContext.getIApplicationThread(), paramContext.getActivityToken(), paramIServiceConnection, paramInt2);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public int[] getAppWidgetIds(ComponentName paramComponentName) {
    IAppWidgetService iAppWidgetService = this.mService;
    if (iAppWidgetService == null)
      return new int[0]; 
    try {
      return iAppWidgetService.getAppWidgetIds(paramComponentName);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public AppWidgetProviderInfo getAppWidgetInfo(int paramInt) {
    IAppWidgetService iAppWidgetService = this.mService;
    if (iAppWidgetService == null)
      return null; 
    try {
      AppWidgetProviderInfo appWidgetProviderInfo = iAppWidgetService.getAppWidgetInfo(this.mPackageName, paramInt);
      if (appWidgetProviderInfo != null)
        appWidgetProviderInfo.updateDimensions(this.mDisplayMetrics); 
      return appWidgetProviderInfo;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public Bundle getAppWidgetOptions(int paramInt) {
    IAppWidgetService iAppWidgetService = this.mService;
    if (iAppWidgetService == null)
      return Bundle.EMPTY; 
    try {
      return iAppWidgetService.getAppWidgetOptions(this.mPackageName, paramInt);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public List<AppWidgetProviderInfo> getInstalledProviders() {
    return (this.mService == null) ? Collections.emptyList() : getInstalledProvidersForProfile(1, null, null);
  }
  
  public List<AppWidgetProviderInfo> getInstalledProviders(int paramInt) {
    return (this.mService == null) ? Collections.emptyList() : getInstalledProvidersForProfile(paramInt, null, null);
  }
  
  public List<AppWidgetProviderInfo> getInstalledProvidersForPackage(String paramString, UserHandle paramUserHandle) {
    if (paramString != null)
      return (this.mService == null) ? Collections.emptyList() : getInstalledProvidersForProfile(1, paramUserHandle, paramString); 
    throw new NullPointerException("A non-null package must be passed to this method. If you want all widgets regardless of package, see getInstalledProvidersForProfile(UserHandle)");
  }
  
  public List<AppWidgetProviderInfo> getInstalledProvidersForProfile(int paramInt, UserHandle paramUserHandle, String paramString) {
    if (this.mService == null)
      return Collections.emptyList(); 
    UserHandle userHandle = paramUserHandle;
    if (paramUserHandle == null)
      userHandle = this.mContext.getUser(); 
    try {
      ParceledListSlice parceledListSlice = this.mService.getInstalledProvidersForProfile(paramInt, userHandle.getIdentifier(), paramString);
      if (parceledListSlice == null)
        return Collections.emptyList(); 
      Iterator<AppWidgetProviderInfo> iterator = parceledListSlice.getList().iterator();
      while (iterator.hasNext())
        ((AppWidgetProviderInfo)iterator.next()).updateDimensions(this.mDisplayMetrics); 
      return parceledListSlice.getList();
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public List<AppWidgetProviderInfo> getInstalledProvidersForProfile(UserHandle paramUserHandle) {
    return (this.mService == null) ? Collections.emptyList() : getInstalledProvidersForProfile(1, paramUserHandle, null);
  }
  
  public boolean hasBindAppWidgetPermission(String paramString) {
    IAppWidgetService iAppWidgetService = this.mService;
    if (iAppWidgetService == null)
      return false; 
    try {
      return iAppWidgetService.hasBindAppWidgetPermission(paramString, this.mContext.getUserId());
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public boolean hasBindAppWidgetPermission(String paramString, int paramInt) {
    IAppWidgetService iAppWidgetService = this.mService;
    if (iAppWidgetService == null)
      return false; 
    try {
      return iAppWidgetService.hasBindAppWidgetPermission(paramString, paramInt);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public boolean isBoundWidgetPackage(String paramString, int paramInt) {
    IAppWidgetService iAppWidgetService = this.mService;
    if (iAppWidgetService == null)
      return false; 
    try {
      return iAppWidgetService.isBoundWidgetPackage(paramString, paramInt);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public boolean isRequestPinAppWidgetSupported() {
    try {
      return this.mService.isRequestPinAppWidgetSupported();
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void noteAppWidgetTapped(int paramInt) {
    try {
      this.mService.noteAppWidgetTapped(this.mPackageName, paramInt);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void notifyAppWidgetViewDataChanged(int paramInt1, int paramInt2) {
    if (this.mService == null)
      return; 
    notifyAppWidgetViewDataChanged(new int[] { paramInt1 }, paramInt2);
  }
  
  public void notifyAppWidgetViewDataChanged(int[] paramArrayOfint, int paramInt) {
    IAppWidgetService iAppWidgetService = this.mService;
    if (iAppWidgetService == null)
      return; 
    try {
      iAppWidgetService.notifyAppWidgetViewDataChanged(this.mPackageName, paramArrayOfint, paramInt);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void partiallyUpdateAppWidget(int paramInt, RemoteViews paramRemoteViews) {
    if (this.mService == null)
      return; 
    partiallyUpdateAppWidget(new int[] { paramInt }, paramRemoteViews);
  }
  
  public void partiallyUpdateAppWidget(int[] paramArrayOfint, RemoteViews paramRemoteViews) {
    IAppWidgetService iAppWidgetService = this.mService;
    if (iAppWidgetService == null)
      return; 
    try {
      iAppWidgetService.partiallyUpdateAppWidgetIds(this.mPackageName, paramArrayOfint, paramRemoteViews);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public boolean requestPinAppWidget(ComponentName paramComponentName, PendingIntent paramPendingIntent) {
    return requestPinAppWidget(paramComponentName, null, paramPendingIntent);
  }
  
  public boolean requestPinAppWidget(ComponentName paramComponentName, Bundle paramBundle, PendingIntent paramPendingIntent) {
    try {
      IntentSender intentSender;
      IAppWidgetService iAppWidgetService = this.mService;
      String str = this.mPackageName;
      if (paramPendingIntent == null) {
        paramPendingIntent = null;
      } else {
        intentSender = paramPendingIntent.getIntentSender();
      } 
      return iAppWidgetService.requestPinAppWidget(str, paramComponentName, paramBundle, intentSender);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void setBindAppWidgetPermission(String paramString, int paramInt, boolean paramBoolean) {
    IAppWidgetService iAppWidgetService = this.mService;
    if (iAppWidgetService == null)
      return; 
    try {
      iAppWidgetService.setBindAppWidgetPermission(paramString, paramInt, paramBoolean);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void setBindAppWidgetPermission(String paramString, boolean paramBoolean) {
    if (this.mService == null)
      return; 
    setBindAppWidgetPermission(paramString, this.mContext.getUserId(), paramBoolean);
  }
  
  public void updateAppWidget(int paramInt, RemoteViews paramRemoteViews) {
    if (this.mService == null)
      return; 
    updateAppWidget(new int[] { paramInt }, paramRemoteViews);
  }
  
  public void updateAppWidget(ComponentName paramComponentName, RemoteViews paramRemoteViews) {
    IAppWidgetService iAppWidgetService = this.mService;
    if (iAppWidgetService == null)
      return; 
    try {
      iAppWidgetService.updateAppWidgetProvider(paramComponentName, paramRemoteViews);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void updateAppWidget(int[] paramArrayOfint, RemoteViews paramRemoteViews) {
    IAppWidgetService iAppWidgetService = this.mService;
    if (iAppWidgetService == null)
      return; 
    try {
      iAppWidgetService.updateAppWidgetIds(this.mPackageName, paramArrayOfint, paramRemoteViews);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void updateAppWidgetOptions(int paramInt, Bundle paramBundle) {
    IAppWidgetService iAppWidgetService = this.mService;
    if (iAppWidgetService == null)
      return; 
    try {
      iAppWidgetService.updateAppWidgetOptions(this.mPackageName, paramInt, paramBundle);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void updateAppWidgetProviderInfo(ComponentName paramComponentName, String paramString) {
    IAppWidgetService iAppWidgetService = this.mService;
    if (iAppWidgetService == null)
      return; 
    try {
      iAppWidgetService.updateAppWidgetProviderInfo(paramComponentName, paramString);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/appwidget/AppWidgetManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */