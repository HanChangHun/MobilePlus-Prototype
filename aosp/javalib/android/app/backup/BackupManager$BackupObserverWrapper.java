package android.app.backup;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.util.Pair;

class BackupObserverWrapper extends IBackupObserver.Stub {
  static final int MSG_FINISHED = 3;
  
  static final int MSG_RESULT = 2;
  
  static final int MSG_UPDATE = 1;
  
  final Handler mHandler;
  
  final BackupObserver mObserver;
  
  BackupObserverWrapper(Context paramContext, BackupObserver paramBackupObserver) {
    this.mHandler = new Handler(paramContext.getMainLooper()) {
        public void handleMessage(Message param2Message) {
          int i = param2Message.what;
          if (i != 1) {
            if (i != 2) {
              if (i != 3) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Unknown message: ");
                stringBuilder.append(param2Message);
                Log.w("BackupManager", stringBuilder.toString());
              } else {
                BackupManager.BackupObserverWrapper.this.mObserver.backupFinished(param2Message.arg1);
              } 
            } else {
              BackupManager.BackupObserverWrapper.this.mObserver.onResult((String)param2Message.obj, param2Message.arg1);
            } 
          } else {
            Pair pair = (Pair)param2Message.obj;
            BackupManager.BackupObserverWrapper.this.mObserver.onUpdate((String)pair.first, (BackupProgress)pair.second);
          } 
        }
      };
    this.mObserver = paramBackupObserver;
  }
  
  public void backupFinished(int paramInt) {
    Handler handler = this.mHandler;
    handler.sendMessage(handler.obtainMessage(3, paramInt, 0));
  }
  
  public void onResult(String paramString, int paramInt) {
    Handler handler = this.mHandler;
    handler.sendMessage(handler.obtainMessage(2, paramInt, 0, paramString));
  }
  
  public void onUpdate(String paramString, BackupProgress paramBackupProgress) {
    Handler handler = this.mHandler;
    handler.sendMessage(handler.obtainMessage(1, Pair.create(paramString, paramBackupProgress)));
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/backup/BackupManager$BackupObserverWrapper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */