package android.app;

import android.os.ServiceManager;
import android.view.LayoutInflater;
import com.android.internal.policy.PhoneLayoutInflater;

class null extends SystemServiceRegistry.CachedServiceFetcher<LayoutInflater> {
  public LayoutInflater createService(ContextImpl paramContextImpl) {
    return (LayoutInflater)new PhoneLayoutInflater(paramContextImpl.getOuterContext());
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/SystemServiceRegistry$33.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */