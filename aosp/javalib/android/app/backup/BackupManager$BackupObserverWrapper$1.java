package android.app.backup;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.util.Pair;

class null extends Handler {
  null(Looper paramLooper) {
    super(paramLooper);
  }
  
  public void handleMessage(Message paramMessage) {
    int i = paramMessage.what;
    if (i != 1) {
      if (i != 2) {
        if (i != 3) {
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("Unknown message: ");
          stringBuilder.append(paramMessage);
          Log.w("BackupManager", stringBuilder.toString());
        } else {
          BackupManager.BackupObserverWrapper.this.mObserver.backupFinished(paramMessage.arg1);
        } 
      } else {
        BackupManager.BackupObserverWrapper.this.mObserver.onResult((String)paramMessage.obj, paramMessage.arg1);
      } 
    } else {
      Pair pair = (Pair)paramMessage.obj;
      BackupManager.BackupObserverWrapper.this.mObserver.onUpdate((String)pair.first, (BackupProgress)pair.second);
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/backup/BackupManager$BackupObserverWrapper$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */