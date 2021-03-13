package android.app.prediction;

import android.content.pm.ParceledListSlice;
import android.os.Binder;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.function.Consumer;

class CallbackWrapper extends IPredictionCallback.Stub {
  private final Consumer<List<AppTarget>> mCallback;
  
  private final Executor mExecutor;
  
  CallbackWrapper(Executor paramExecutor, Consumer<List<AppTarget>> paramConsumer) {
    this.mCallback = paramConsumer;
    this.mExecutor = paramExecutor;
  }
  
  public void onResult(ParceledListSlice paramParceledListSlice) {
    long l = Binder.clearCallingIdentity();
    try {
      Executor executor = this.mExecutor;
      _$$Lambda$AppPredictor$CallbackWrapper$gCs3O3sYRlsXAOdelds31867YXo _$$Lambda$AppPredictor$CallbackWrapper$gCs3O3sYRlsXAOdelds31867YXo = new _$$Lambda$AppPredictor$CallbackWrapper$gCs3O3sYRlsXAOdelds31867YXo();
      this(this, paramParceledListSlice);
      executor.execute(_$$Lambda$AppPredictor$CallbackWrapper$gCs3O3sYRlsXAOdelds31867YXo);
      return;
    } finally {
      Binder.restoreCallingIdentity(l);
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/prediction/AppPredictor$CallbackWrapper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */