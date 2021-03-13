package android.app;

import android.os.Bundle;
import android.os.Handler;
import android.os.IRemoteCallback;
import android.os.RemoteException;

class null extends IRemoteCallback.Stub {
  public void sendResult(Bundle paramBundle) throws RemoteException {
    handler.post(new Runnable() {
          public void run() {
            listener.onAnimationFinished();
          }
        });
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/ActivityOptions$2.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */