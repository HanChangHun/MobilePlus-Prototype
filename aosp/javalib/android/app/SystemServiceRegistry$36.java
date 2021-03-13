package android.app;

import android.content.Context;
import android.content.res.Resources;
import android.os.ServiceManager;
import android.view.ContextThemeWrapper;

class null extends SystemServiceRegistry.CachedServiceFetcher<NotificationManager> {
  public NotificationManager createService(ContextImpl paramContextImpl) {
    Context context = paramContextImpl.getOuterContext();
    return new NotificationManager((Context)new ContextThemeWrapper(context, Resources.selectSystemTheme(0, (context.getApplicationInfo()).targetSdkVersion, 16973835, 16973935, 16974126, 16974130)), paramContextImpl.mMainThread.getHandler());
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/SystemServiceRegistry$36.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */