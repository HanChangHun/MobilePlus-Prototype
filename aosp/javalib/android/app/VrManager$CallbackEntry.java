package android.app;

import android.service.vr.IPersistentVrStateCallbacks;
import android.service.vr.IVrStateCallbacks;
import java.util.concurrent.Executor;

class CallbackEntry {
  final VrStateCallback mCallback;
  
  final Executor mExecutor;
  
  final IPersistentVrStateCallbacks mPersistentStateCallback = (IPersistentVrStateCallbacks)new IPersistentVrStateCallbacks.Stub() {
      public void onPersistentVrStateChanged(boolean param2Boolean) {
        VrManager.CallbackEntry.this.mExecutor.execute(new _$$Lambda$VrManager$CallbackEntry$2$KvHLIXm3_7igcOqTEl46YdjhHMk(this, param2Boolean));
      }
    };
  
  final IVrStateCallbacks mStateCallback = (IVrStateCallbacks)new IVrStateCallbacks.Stub() {
      public void onVrStateChanged(boolean param2Boolean) {
        VrManager.CallbackEntry.this.mExecutor.execute(new _$$Lambda$VrManager$CallbackEntry$1$rgUBVVG1QhelpvAp8W3UQHDHJdU(this, param2Boolean));
      }
    };
  
  CallbackEntry(VrStateCallback paramVrStateCallback, Executor paramExecutor) {
    this.mCallback = paramVrStateCallback;
    this.mExecutor = paramExecutor;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/VrManager$CallbackEntry.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */