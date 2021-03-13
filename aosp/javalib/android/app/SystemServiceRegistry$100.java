package android.app;

import android.app.timezone.RulesManager;
import android.os.ServiceManager;

class null extends SystemServiceRegistry.CachedServiceFetcher<RulesManager> {
  public RulesManager createService(ContextImpl paramContextImpl) {
    return new RulesManager(paramContextImpl.getOuterContext());
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/SystemServiceRegistry$100.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */