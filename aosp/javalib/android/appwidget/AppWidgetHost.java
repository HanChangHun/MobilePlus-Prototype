package android.appwidget;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.IntentSender;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.util.DisplayMetrics;
import android.util.SparseArray;
import android.widget.RemoteViews;
import com.android.internal.appwidget.IAppWidgetHost;
import com.android.internal.appwidget.IAppWidgetService;
import java.lang.ref.WeakReference;
import java.util.List;

public class AppWidgetHost {
  static final int HANDLE_APP_WIDGET_REMOVED = 5;
  
  static final int HANDLE_PROVIDERS_CHANGED = 3;
  
  static final int HANDLE_PROVIDER_CHANGED = 2;
  
  static final int HANDLE_UPDATE = 1;
  
  static final int HANDLE_VIEW_DATA_CHANGED = 4;
  
  static IAppWidgetService sService;
  
  static boolean sServiceInitialized;
  
  static final Object sServiceLock = new Object();
  
  private final Callbacks mCallbacks;
  
  private String mContextOpPackageName;
  
  private DisplayMetrics mDisplayMetrics;
  
  private final Handler mHandler;
  
  private final int mHostId;
  
  private RemoteViews.OnClickHandler mOnClickHandler;
  
  private final SparseArray<AppWidgetHostView> mViews = new SparseArray();
  
  static {
    sServiceInitialized = false;
  }
  
  public AppWidgetHost(Context paramContext, int paramInt) {
    this(paramContext, paramInt, null, paramContext.getMainLooper());
  }
  
  public AppWidgetHost(Context paramContext, int paramInt, RemoteViews.OnClickHandler paramOnClickHandler, Looper paramLooper) {
    this.mContextOpPackageName = paramContext.getOpPackageName();
    this.mHostId = paramInt;
    this.mOnClickHandler = paramOnClickHandler;
    this.mHandler = new UpdateHandler(paramLooper);
    this.mCallbacks = new Callbacks(this.mHandler);
    this.mDisplayMetrics = paramContext.getResources().getDisplayMetrics();
    bindService(paramContext);
  }
  
  private static void bindService(Context paramContext) {
    synchronized (sServiceLock) {
      if (sServiceInitialized)
        return; 
      sServiceInitialized = true;
      if (!paramContext.getPackageManager().hasSystemFeature("android.software.app_widgets") && !paramContext.getResources().getBoolean(17891436))
        return; 
      sService = IAppWidgetService.Stub.asInterface(ServiceManager.getService("appwidget"));
      return;
    } 
  }
  
  public static void deleteAllHosts() {
    IAppWidgetService iAppWidgetService = sService;
    if (iAppWidgetService == null)
      return; 
    try {
      iAppWidgetService.deleteAllHosts();
      return;
    } catch (RemoteException remoteException) {
      throw new RuntimeException("system server dead?", remoteException);
    } 
  }
  
  public int allocateAppWidgetId() {
    IAppWidgetService iAppWidgetService = sService;
    if (iAppWidgetService == null)
      return -1; 
    try {
      return iAppWidgetService.allocateAppWidgetId(this.mContextOpPackageName, this.mHostId);
    } catch (RemoteException remoteException) {
      throw new RuntimeException("system server dead?", remoteException);
    } 
  }
  
  protected void clearViews() {
    synchronized (this.mViews) {
      this.mViews.clear();
      return;
    } 
  }
  
  public final AppWidgetHostView createView(Context paramContext, int paramInt, AppWidgetProviderInfo paramAppWidgetProviderInfo) {
    SparseArray<AppWidgetHostView> sparseArray;
    RemoteViews remoteViews;
    if (sService == null)
      return null; 
    null = onCreateView(paramContext, paramInt, paramAppWidgetProviderInfo);
    null.setOnClickHandler(this.mOnClickHandler);
    null.setAppWidget(paramInt, paramAppWidgetProviderInfo);
    synchronized (this.mViews) {
      this.mViews.put(paramInt, null);
      try {
        remoteViews = sService.getAppWidgetViews(this.mContextOpPackageName, paramInt);
        null.updateAppWidget(remoteViews);
        return null;
      } catch (RemoteException remoteException) {
        throw new RuntimeException("system server dead?", remoteException);
      } 
    } 
  }
  
  public void deleteAppWidgetId(int paramInt) {
    if (sService == null)
      return; 
    synchronized (this.mViews) {
      this.mViews.remove(paramInt);
      try {
        sService.deleteAppWidgetId(this.mContextOpPackageName, paramInt);
        return;
      } catch (RemoteException remoteException) {
        RuntimeException runtimeException = new RuntimeException();
        this("system server dead?", (Throwable)remoteException);
        throw runtimeException;
      } 
    } 
  }
  
  public void deleteHost() {
    IAppWidgetService iAppWidgetService = sService;
    if (iAppWidgetService == null)
      return; 
    try {
      iAppWidgetService.deleteHost(this.mContextOpPackageName, this.mHostId);
      return;
    } catch (RemoteException remoteException) {
      throw new RuntimeException("system server dead?", remoteException);
    } 
  }
  
  void dispatchOnAppWidgetRemoved(int paramInt) {
    synchronized (this.mViews) {
      this.mViews.remove(paramInt);
      onAppWidgetRemoved(paramInt);
      return;
    } 
  }
  
  public int[] getAppWidgetIds() {
    IAppWidgetService iAppWidgetService = sService;
    if (iAppWidgetService == null)
      return new int[0]; 
    try {
      return iAppWidgetService.getAppWidgetIdsForHost(this.mContextOpPackageName, this.mHostId);
    } catch (RemoteException remoteException) {
      throw new RuntimeException("system server dead?", remoteException);
    } 
  }
  
  public void onAppWidgetRemoved(int paramInt) {}
  
  protected AppWidgetHostView onCreateView(Context paramContext, int paramInt, AppWidgetProviderInfo paramAppWidgetProviderInfo) {
    return new AppWidgetHostView(paramContext, this.mOnClickHandler);
  }
  
  protected void onProviderChanged(int paramInt, AppWidgetProviderInfo paramAppWidgetProviderInfo) {
    paramAppWidgetProviderInfo.updateDimensions(this.mDisplayMetrics);
    synchronized (this.mViews) {
      AppWidgetHostView appWidgetHostView = (AppWidgetHostView)this.mViews.get(paramInt);
      if (appWidgetHostView != null)
        appWidgetHostView.resetAppWidget(paramAppWidgetProviderInfo); 
      return;
    } 
  }
  
  protected void onProvidersChanged() {}
  
  public final void startAppWidgetConfigureActivityForResult(Activity paramActivity, int paramInt1, int paramInt2, int paramInt3, Bundle paramBundle) {
    IAppWidgetService iAppWidgetService = sService;
    if (iAppWidgetService == null)
      return; 
    try {
      IntentSender intentSender = iAppWidgetService.createAppWidgetConfigIntentSender(this.mContextOpPackageName, paramInt1, paramInt2);
      if (intentSender != null) {
        paramActivity.startIntentSenderForResult(intentSender, paramInt3, null, 0, 0, 0, paramBundle);
        return;
      } 
      ActivityNotFoundException activityNotFoundException = new ActivityNotFoundException();
      this();
      throw activityNotFoundException;
    } catch (android.content.IntentSender.SendIntentException sendIntentException) {
      throw new ActivityNotFoundException();
    } catch (RemoteException remoteException) {
      throw new RuntimeException("system server dead?", remoteException);
    } 
  }
  
  public void startListening() {
    SparseArray<AppWidgetHostView> sparseArray;
    if (sService == null)
      return; 
    synchronized (this.mViews) {
      int i = this.mViews.size();
      int[] arrayOfInt = new int[i];
      byte b;
      for (b = 0; b < i; b++)
        arrayOfInt[b] = this.mViews.keyAt(b); 
      try {
        List<PendingHostUpdate> list = sService.startListening((IAppWidgetHost)this.mCallbacks, this.mContextOpPackageName, this.mHostId, arrayOfInt).getList();
        i = list.size();
        for (b = 0; b < i; b++) {
          PendingHostUpdate pendingHostUpdate = list.get(b);
          int j = pendingHostUpdate.type;
          if (j != 0) {
            if (j != 1) {
              if (j != 2) {
                if (j == 3)
                  dispatchOnAppWidgetRemoved(pendingHostUpdate.appWidgetId); 
              } else {
                viewDataChanged(pendingHostUpdate.appWidgetId, pendingHostUpdate.viewId);
              } 
            } else {
              onProviderChanged(pendingHostUpdate.appWidgetId, pendingHostUpdate.widgetInfo);
            } 
          } else {
            updateAppWidgetView(pendingHostUpdate.appWidgetId, pendingHostUpdate.views);
          } 
        } 
        return;
      } catch (RemoteException remoteException) {
        throw new RuntimeException("system server dead?", remoteException);
      } 
    } 
  }
  
  public void stopListening() {
    IAppWidgetService iAppWidgetService = sService;
    if (iAppWidgetService == null)
      return; 
    try {
      iAppWidgetService.stopListening(this.mContextOpPackageName, this.mHostId);
      return;
    } catch (RemoteException remoteException) {
      throw new RuntimeException("system server dead?", remoteException);
    } 
  }
  
  void updateAppWidgetView(int paramInt, RemoteViews paramRemoteViews) {
    synchronized (this.mViews) {
      AppWidgetHostView appWidgetHostView = (AppWidgetHostView)this.mViews.get(paramInt);
      if (appWidgetHostView != null)
        appWidgetHostView.updateAppWidget(paramRemoteViews); 
      return;
    } 
  }
  
  void viewDataChanged(int paramInt1, int paramInt2) {
    synchronized (this.mViews) {
      AppWidgetHostView appWidgetHostView = (AppWidgetHostView)this.mViews.get(paramInt1);
      if (appWidgetHostView != null)
        appWidgetHostView.viewDataChanged(paramInt2); 
      return;
    } 
  }
  
  static class Callbacks extends IAppWidgetHost.Stub {
    private final WeakReference<Handler> mWeakHandler;
    
    public Callbacks(Handler param1Handler) {
      this.mWeakHandler = new WeakReference<>(param1Handler);
    }
    
    private static boolean isLocalBinder() {
      boolean bool;
      if (Process.myPid() == Binder.getCallingPid()) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public void appWidgetRemoved(int param1Int) {
      Handler handler = this.mWeakHandler.get();
      if (handler == null)
        return; 
      handler.obtainMessage(5, param1Int, 0).sendToTarget();
    }
    
    public void providerChanged(int param1Int, AppWidgetProviderInfo param1AppWidgetProviderInfo) {
      AppWidgetProviderInfo appWidgetProviderInfo = param1AppWidgetProviderInfo;
      if (isLocalBinder()) {
        appWidgetProviderInfo = param1AppWidgetProviderInfo;
        if (param1AppWidgetProviderInfo != null)
          appWidgetProviderInfo = param1AppWidgetProviderInfo.clone(); 
      } 
      Handler handler = this.mWeakHandler.get();
      if (handler == null)
        return; 
      handler.obtainMessage(2, param1Int, 0, appWidgetProviderInfo).sendToTarget();
    }
    
    public void providersChanged() {
      Handler handler = this.mWeakHandler.get();
      if (handler == null)
        return; 
      handler.obtainMessage(3).sendToTarget();
    }
    
    public void updateAppWidget(int param1Int, RemoteViews param1RemoteViews) {
      RemoteViews remoteViews = param1RemoteViews;
      if (isLocalBinder()) {
        remoteViews = param1RemoteViews;
        if (param1RemoteViews != null)
          remoteViews = param1RemoteViews.clone(); 
      } 
      Handler handler = this.mWeakHandler.get();
      if (handler == null)
        return; 
      handler.obtainMessage(1, param1Int, 0, remoteViews).sendToTarget();
    }
    
    public void viewDataChanged(int param1Int1, int param1Int2) {
      Handler handler = this.mWeakHandler.get();
      if (handler == null)
        return; 
      handler.obtainMessage(4, param1Int1, param1Int2).sendToTarget();
    }
  }
  
  class UpdateHandler extends Handler {
    public UpdateHandler(Looper param1Looper) {
      super(param1Looper);
    }
    
    public void handleMessage(Message param1Message) {
      int i = param1Message.what;
      if (i != 1) {
        if (i != 2) {
          if (i != 3) {
            if (i != 4) {
              if (i == 5)
                AppWidgetHost.this.dispatchOnAppWidgetRemoved(param1Message.arg1); 
            } else {
              AppWidgetHost.this.viewDataChanged(param1Message.arg1, param1Message.arg2);
            } 
          } else {
            AppWidgetHost.this.onProvidersChanged();
          } 
        } else {
          AppWidgetHost.this.onProviderChanged(param1Message.arg1, (AppWidgetProviderInfo)param1Message.obj);
        } 
      } else {
        AppWidgetHost.this.updateAppWidgetView(param1Message.arg1, (RemoteViews)param1Message.obj);
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/appwidget/AppWidgetHost.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */