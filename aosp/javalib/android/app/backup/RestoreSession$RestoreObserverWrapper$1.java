package android.app.backup;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

class null extends Handler {
  null(Looper paramLooper) {
    super(paramLooper);
  }
  
  public void handleMessage(Message paramMessage) {
    int i = paramMessage.what;
    if (i != 1) {
      if (i != 2) {
        if (i != 3) {
          if (i == 4)
            RestoreSession.RestoreObserverWrapper.this.mAppObserver.restoreSetsAvailable((RestoreSet[])paramMessage.obj); 
        } else {
          RestoreSession.RestoreObserverWrapper.this.mAppObserver.restoreFinished(paramMessage.arg1);
        } 
      } else {
        RestoreSession.RestoreObserverWrapper.this.mAppObserver.onUpdate(paramMessage.arg1, (String)paramMessage.obj);
      } 
    } else {
      RestoreSession.RestoreObserverWrapper.this.mAppObserver.restoreStarting(paramMessage.arg1);
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/backup/RestoreSession$RestoreObserverWrapper$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */