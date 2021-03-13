package android.app;

import android.appwidget.AppWidgetManager;
import android.os.ServiceManager;
import com.android.internal.appwidget.IAppWidgetService;

class null extends SystemServiceRegistry.CachedServiceFetcher<AppWidgetManager> {
  public AppWidgetManager createService(ContextImpl paramContextImpl) throws ServiceManager.ServiceNotFoundException {
    return new AppWidgetManager(paramContextImpl, IAppWidgetService.Stub.asInterface(ServiceManager.getServiceOrThrow("appwidget")));
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/SystemServiceRegistry$83.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */