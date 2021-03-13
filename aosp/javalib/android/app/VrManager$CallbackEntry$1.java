package android.app;

import android.service.vr.IVrStateCallbacks;

class null extends IVrStateCallbacks.Stub {
  public void onVrStateChanged(boolean paramBoolean) {
    VrManager.CallbackEntry.this.mExecutor.execute(new _$$Lambda$VrManager$CallbackEntry$1$rgUBVVG1QhelpvAp8W3UQHDHJdU(this, paramBoolean));
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/VrManager$CallbackEntry$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */