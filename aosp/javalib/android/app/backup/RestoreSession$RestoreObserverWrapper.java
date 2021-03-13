package android.app.backup;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

class RestoreObserverWrapper extends IRestoreObserver.Stub {
  static final int MSG_RESTORE_FINISHED = 3;
  
  static final int MSG_RESTORE_SETS_AVAILABLE = 4;
  
  static final int MSG_RESTORE_STARTING = 1;
  
  static final int MSG_UPDATE = 2;
  
  final RestoreObserver mAppObserver;
  
  final Handler mHandler;
  
  RestoreObserverWrapper(Context paramContext, RestoreObserver paramRestoreObserver) {
    this.mHandler = new Handler(paramContext.getMainLooper()) {
        public void handleMessage(Message param2Message) {
          int i = param2Message.what;
          if (i != 1) {
            if (i != 2) {
              if (i != 3) {
                if (i == 4)
                  RestoreSession.RestoreObserverWrapper.this.mAppObserver.restoreSetsAvailable((RestoreSet[])param2Message.obj); 
              } else {
                RestoreSession.RestoreObserverWrapper.this.mAppObserver.restoreFinished(param2Message.arg1);
              } 
            } else {
              RestoreSession.RestoreObserverWrapper.this.mAppObserver.onUpdate(param2Message.arg1, (String)param2Message.obj);
            } 
          } else {
            RestoreSession.RestoreObserverWrapper.this.mAppObserver.restoreStarting(param2Message.arg1);
          } 
        }
      };
    this.mAppObserver = paramRestoreObserver;
  }
  
  public void onUpdate(int paramInt, String paramString) {
    Handler handler = this.mHandler;
    handler.sendMessage(handler.obtainMessage(2, paramInt, 0, paramString));
  }
  
  public void restoreFinished(int paramInt) {
    Handler handler = this.mHandler;
    handler.sendMessage(handler.obtainMessage(3, paramInt, 0));
  }
  
  public void restoreSetsAvailable(RestoreSet[] paramArrayOfRestoreSet) {
    Handler handler = this.mHandler;
    handler.sendMessage(handler.obtainMessage(4, paramArrayOfRestoreSet));
  }
  
  public void restoreStarting(int paramInt) {
    Handler handler = this.mHandler;
    handler.sendMessage(handler.obtainMessage(1, paramInt, 0));
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/backup/RestoreSession$RestoreObserverWrapper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */