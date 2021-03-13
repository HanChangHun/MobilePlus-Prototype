package android.appwidget;

import android.os.Binder;
import android.os.Handler;
import android.os.Process;
import android.widget.RemoteViews;
import com.android.internal.appwidget.IAppWidgetHost;
import java.lang.ref.WeakReference;

class Callbacks extends IAppWidgetHost.Stub {
  private final WeakReference<Handler> mWeakHandler;
  
  public Callbacks(Handler paramHandler) {
    this.mWeakHandler = new WeakReference<>(paramHandler);
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
  
  public void appWidgetRemoved(int paramInt) {
    Handler handler = this.mWeakHandler.get();
    if (handler == null)
      return; 
    handler.obtainMessage(5, paramInt, 0).sendToTarget();
  }
  
  public void providerChanged(int paramInt, AppWidgetProviderInfo paramAppWidgetProviderInfo) {
    AppWidgetProviderInfo appWidgetProviderInfo = paramAppWidgetProviderInfo;
    if (isLocalBinder()) {
      appWidgetProviderInfo = paramAppWidgetProviderInfo;
      if (paramAppWidgetProviderInfo != null)
        appWidgetProviderInfo = paramAppWidgetProviderInfo.clone(); 
    } 
    Handler handler = this.mWeakHandler.get();
    if (handler == null)
      return; 
    handler.obtainMessage(2, paramInt, 0, appWidgetProviderInfo).sendToTarget();
  }
  
  public void providersChanged() {
    Handler handler = this.mWeakHandler.get();
    if (handler == null)
      return; 
    handler.obtainMessage(3).sendToTarget();
  }
  
  public void updateAppWidget(int paramInt, RemoteViews paramRemoteViews) {
    RemoteViews remoteViews = paramRemoteViews;
    if (isLocalBinder()) {
      remoteViews = paramRemoteViews;
      if (paramRemoteViews != null)
        remoteViews = paramRemoteViews.clone(); 
    } 
    Handler handler = this.mWeakHandler.get();
    if (handler == null)
      return; 
    handler.obtainMessage(1, paramInt, 0, remoteViews).sendToTarget();
  }
  
  public void viewDataChanged(int paramInt1, int paramInt2) {
    Handler handler = this.mWeakHandler.get();
    if (handler == null)
      return; 
    handler.obtainMessage(4, paramInt1, paramInt2).sendToTarget();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/appwidget/AppWidgetHost$Callbacks.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */