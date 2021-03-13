package android.app;

import android.media.soundtrigger.SoundTriggerManager;
import android.os.ServiceManager;
import com.android.internal.app.ISoundTriggerService;

class null extends SystemServiceRegistry.CachedServiceFetcher<SoundTriggerManager> {
  public SoundTriggerManager createService(ContextImpl paramContextImpl) throws ServiceManager.ServiceNotFoundException {
    return new SoundTriggerManager(paramContextImpl, ISoundTriggerService.Stub.asInterface(ServiceManager.getServiceOrThrow("soundtrigger")));
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/SystemServiceRegistry$87.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */