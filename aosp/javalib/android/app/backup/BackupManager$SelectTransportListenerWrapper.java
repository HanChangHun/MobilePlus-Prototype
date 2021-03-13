package android.app.backup;

import android.content.Context;
import android.os.Handler;

class SelectTransportListenerWrapper extends ISelectBackupTransportCallback.Stub {
  private final Handler mHandler;
  
  private final SelectBackupTransportCallback mListener;
  
  SelectTransportListenerWrapper(Context paramContext, SelectBackupTransportCallback paramSelectBackupTransportCallback) {
    this.mHandler = new Handler(paramContext.getMainLooper());
    this.mListener = paramSelectBackupTransportCallback;
  }
  
  public void onFailure(final int reason) {
    this.mHandler.post(new Runnable() {
          public void run() {
            BackupManager.SelectTransportListenerWrapper.this.mListener.onFailure(reason);
          }
        });
  }
  
  public void onSuccess(final String transportName) {
    this.mHandler.post(new Runnable() {
          public void run() {
            BackupManager.SelectTransportListenerWrapper.this.mListener.onSuccess(transportName);
          }
        });
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/backup/BackupManager$SelectTransportListenerWrapper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */