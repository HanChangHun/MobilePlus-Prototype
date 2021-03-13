package android.app;

import android.os.ServiceManager;

class null extends SystemServiceRegistry.CachedServiceFetcher<DownloadManager> {
  public DownloadManager createService(ContextImpl paramContextImpl) {
    return new DownloadManager(paramContextImpl);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/SystemServiceRegistry$23.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */