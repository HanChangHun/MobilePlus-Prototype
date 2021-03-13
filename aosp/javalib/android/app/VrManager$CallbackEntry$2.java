package android.app;

import android.service.vr.IPersistentVrStateCallbacks;

class null extends IPersistentVrStateCallbacks.Stub {
  public void onPersistentVrStateChanged(boolean paramBoolean) {
    VrManager.CallbackEntry.this.mExecutor.execute(new _$$Lambda$VrManager$CallbackEntry$2$KvHLIXm3_7igcOqTEl46YdjhHMk(this, paramBoolean));
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/VrManager$CallbackEntry$2.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */