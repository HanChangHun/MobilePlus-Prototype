package android.app;

import android.content.pm.ApplicationInfo;
import android.content.res.Resources;
import android.os.IBinder;
import android.os.ServiceManager;
import android.util.Log;

class null extends SystemServiceRegistry.CachedServiceFetcher<WallpaperManager> {
  public WallpaperManager createService(ContextImpl paramContextImpl) throws ServiceManager.ServiceNotFoundException {
    IBinder iBinder = ServiceManager.getService("wallpaper");
    if (iBinder == null) {
      ApplicationInfo applicationInfo = paramContextImpl.getApplicationInfo();
      if (applicationInfo.targetSdkVersion < 28 || !applicationInfo.isInstantApp()) {
        if (!Resources.getSystem().getBoolean(17891454))
          return DisabledWallpaperManager.getInstance(); 
        Log.e("SystemServiceRegistry", "No wallpaper service");
        return new WallpaperManager(IWallpaperManager.Stub.asInterface(iBinder), paramContextImpl.getOuterContext(), paramContextImpl.mMainThread.getHandler());
      } 
      throw new ServiceManager.ServiceNotFoundException("wallpaper");
    } 
    return new WallpaperManager(IWallpaperManager.Stub.asInterface(iBinder), paramContextImpl.getOuterContext(), paramContextImpl.mMainThread.getHandler());
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/SystemServiceRegistry$56.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */