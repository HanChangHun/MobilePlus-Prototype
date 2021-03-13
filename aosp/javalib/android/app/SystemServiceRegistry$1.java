package android.app;

import android.os.ServiceManager;
import android.view.accessibility.AccessibilityManager;

class null extends SystemServiceRegistry.CachedServiceFetcher<AccessibilityManager> {
  public AccessibilityManager createService(ContextImpl paramContextImpl) {
    return AccessibilityManager.getInstance(paramContextImpl);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/SystemServiceRegistry$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */