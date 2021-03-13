package android.app;

import android.annotation.SystemApi;
import android.content.Context;

@SystemApi
public interface ContextAwareServiceProducerWithoutBinder<TServiceClass> {
  TServiceClass createService(Context paramContext);
}


/* Location:              /home/chun/Desktop/temp/!/android/app/SystemServiceRegistry$ContextAwareServiceProducerWithoutBinder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */