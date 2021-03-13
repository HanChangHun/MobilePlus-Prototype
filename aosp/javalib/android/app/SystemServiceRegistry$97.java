package android.app;

import android.app.prediction.AppPredictionManager;
import android.os.ServiceManager;

class null extends SystemServiceRegistry.CachedServiceFetcher<AppPredictionManager> {
  public AppPredictionManager createService(ContextImpl paramContextImpl) throws ServiceManager.ServiceNotFoundException {
    AppPredictionManager appPredictionManager;
    if (ServiceManager.getService("app_prediction") == null) {
      paramContextImpl = null;
    } else {
      appPredictionManager = new AppPredictionManager(paramContextImpl);
    } 
    return appPredictionManager;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/SystemServiceRegistry$97.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */