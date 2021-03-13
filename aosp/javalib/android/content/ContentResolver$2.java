package android.content;

import android.os.RemoteException;

class null extends ISyncStatusObserver.Stub {
  public void onStatusChanged(int paramInt) throws RemoteException {
    callback.onStatusChanged(paramInt);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/ContentResolver$2.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */