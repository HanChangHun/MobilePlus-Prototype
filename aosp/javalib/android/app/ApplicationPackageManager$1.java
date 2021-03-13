package android.app;

import android.os.RemoteException;

class null extends PropertyInvalidatedCache<ApplicationPackageManager.HasSystemFeatureQuery, Boolean> {
  null(int paramInt, String paramString) {
    super(paramInt, paramString);
  }
  
  protected Boolean recompute(ApplicationPackageManager.HasSystemFeatureQuery paramHasSystemFeatureQuery) {
    try {
      ActivityThread.currentActivityThread();
      boolean bool = ActivityThread.getPackageManager().hasSystemFeature(paramHasSystemFeatureQuery.name, paramHasSystemFeatureQuery.version);
      return Boolean.valueOf(bool);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/ApplicationPackageManager$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */