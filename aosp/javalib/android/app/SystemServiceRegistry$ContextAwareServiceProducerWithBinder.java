package android.app;

import android.annotation.SystemApi;
import android.content.Context;
import android.os.IBinder;

@SystemApi
public interface ContextAwareServiceProducerWithBinder<TServiceClass> {
  TServiceClass createService(Context paramContext, IBinder paramIBinder);
}


/* Location:              /home/chun/Desktop/temp/!/android/app/SystemServiceRegistry$ContextAwareServiceProducerWithBinder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */