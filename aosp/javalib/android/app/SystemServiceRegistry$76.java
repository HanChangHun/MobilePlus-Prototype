package android.app;

import android.media.tv.tunerresourcemanager.ITunerResourceManager;
import android.media.tv.tunerresourcemanager.TunerResourceManager;
import android.os.ServiceManager;

class null extends SystemServiceRegistry.CachedServiceFetcher<TunerResourceManager> {
  public TunerResourceManager createService(ContextImpl paramContextImpl) throws ServiceManager.ServiceNotFoundException {
    return new TunerResourceManager(ITunerResourceManager.Stub.asInterface(ServiceManager.getServiceOrThrow("tv_tuner_resource_mgr")), paramContextImpl.getUserId());
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/SystemServiceRegistry$76.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */