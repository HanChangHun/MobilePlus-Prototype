package android.app.backup;

import android.annotation.SystemApi;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@SystemApi
public class RestoreSession {
  static final String TAG = "RestoreSession";
  
  IRestoreSession mBinder;
  
  final Context mContext;
  
  RestoreObserverWrapper mObserver = null;
  
  RestoreSession(Context paramContext, IRestoreSession paramIRestoreSession) {
    this.mContext = paramContext;
    this.mBinder = paramIRestoreSession;
  }
  
  public void endRestoreSession() {
    try {
      this.mBinder.endRestoreSession();
      this.mBinder = null;
    } catch (RemoteException remoteException) {
      Log.d("RestoreSession", "Can't contact server to get available sets");
      this.mBinder = null;
    } finally {
      Exception exception;
    } 
  }
  
  public int getAvailableRestoreSets(RestoreObserver paramRestoreObserver) {
    return getAvailableRestoreSets(paramRestoreObserver, null);
  }
  
  public int getAvailableRestoreSets(RestoreObserver paramRestoreObserver, BackupManagerMonitor paramBackupManagerMonitor) {
    BackupManagerMonitorWrapper backupManagerMonitorWrapper;
    int i = -1;
    RestoreObserverWrapper restoreObserverWrapper = new RestoreObserverWrapper(this.mContext, paramRestoreObserver);
    if (paramBackupManagerMonitor == null) {
      paramRestoreObserver = null;
    } else {
      backupManagerMonitorWrapper = new BackupManagerMonitorWrapper(paramBackupManagerMonitor);
    } 
    try {
      int j = this.mBinder.getAvailableRestoreSets(restoreObserverWrapper, backupManagerMonitorWrapper);
      i = j;
    } catch (RemoteException remoteException) {
      Log.d("RestoreSession", "Can't contact server to get available sets");
    } 
    return i;
  }
  
  public int restoreAll(long paramLong, RestoreObserver paramRestoreObserver) {
    return restoreAll(paramLong, paramRestoreObserver, null);
  }
  
  public int restoreAll(long paramLong, RestoreObserver paramRestoreObserver, BackupManagerMonitor paramBackupManagerMonitor) {
    BackupManagerMonitorWrapper backupManagerMonitorWrapper;
    int i = -1;
    if (this.mObserver != null) {
      Log.d("RestoreSession", "restoreAll() called during active restore");
      return -1;
    } 
    this.mObserver = new RestoreObserverWrapper(this.mContext, paramRestoreObserver);
    if (paramBackupManagerMonitor == null) {
      paramRestoreObserver = null;
    } else {
      backupManagerMonitorWrapper = new BackupManagerMonitorWrapper(paramBackupManagerMonitor);
    } 
    try {
      int j = this.mBinder.restoreAll(paramLong, this.mObserver, backupManagerMonitorWrapper);
      i = j;
    } catch (RemoteException remoteException) {
      Log.d("RestoreSession", "Can't contact server to restore");
    } 
    return i;
  }
  
  public int restorePackage(String paramString, RestoreObserver paramRestoreObserver) {
    return restorePackage(paramString, paramRestoreObserver, null);
  }
  
  public int restorePackage(String paramString, RestoreObserver paramRestoreObserver, BackupManagerMonitor paramBackupManagerMonitor) {
    BackupManagerMonitorWrapper backupManagerMonitorWrapper;
    byte b2;
    byte b1 = -1;
    if (this.mObserver != null) {
      Log.d("RestoreSession", "restorePackage() called during active restore");
      return -1;
    } 
    this.mObserver = new RestoreObserverWrapper(this.mContext, paramRestoreObserver);
    if (paramBackupManagerMonitor == null) {
      paramRestoreObserver = null;
    } else {
      backupManagerMonitorWrapper = new BackupManagerMonitorWrapper(paramBackupManagerMonitor);
    } 
    try {
      b2 = this.mBinder.restorePackage(paramString, this.mObserver, backupManagerMonitorWrapper);
    } catch (RemoteException remoteException) {
      Log.d("RestoreSession", "Can't contact server to restore package");
      b2 = b1;
    } 
    return b2;
  }
  
  public int restorePackages(long paramLong, RestoreObserver paramRestoreObserver, Set<String> paramSet) {
    return restorePackages(paramLong, paramRestoreObserver, paramSet, null);
  }
  
  public int restorePackages(long paramLong, RestoreObserver paramRestoreObserver, Set<String> paramSet, BackupManagerMonitor paramBackupManagerMonitor) {
    BackupManagerMonitorWrapper backupManagerMonitorWrapper;
    byte b2;
    byte b1 = -1;
    if (this.mObserver != null) {
      Log.d("RestoreSession", "restoreAll() called during active restore");
      return -1;
    } 
    this.mObserver = new RestoreObserverWrapper(this.mContext, paramRestoreObserver);
    if (paramBackupManagerMonitor == null) {
      paramRestoreObserver = null;
    } else {
      backupManagerMonitorWrapper = new BackupManagerMonitorWrapper(paramBackupManagerMonitor);
    } 
    try {
      b2 = this.mBinder.restorePackages(paramLong, this.mObserver, paramSet.<String>toArray(new String[0]), backupManagerMonitorWrapper);
    } catch (RemoteException remoteException) {
      Log.d("RestoreSession", "Can't contact server to restore packages");
      b2 = b1;
    } 
    return b2;
  }
  
  @Deprecated
  public int restoreSome(long paramLong, RestoreObserver paramRestoreObserver, BackupManagerMonitor paramBackupManagerMonitor, String[] paramArrayOfString) {
    return restorePackages(paramLong, paramRestoreObserver, new HashSet<>(Arrays.asList(paramArrayOfString)), paramBackupManagerMonitor);
  }
  
  @Deprecated
  public int restoreSome(long paramLong, RestoreObserver paramRestoreObserver, String[] paramArrayOfString) {
    return restoreSome(paramLong, paramRestoreObserver, null, paramArrayOfString);
  }
  
  private class BackupManagerMonitorWrapper extends IBackupManagerMonitor.Stub {
    final BackupManagerMonitor mMonitor;
    
    BackupManagerMonitorWrapper(BackupManagerMonitor param1BackupManagerMonitor) {
      this.mMonitor = param1BackupManagerMonitor;
    }
    
    public void onEvent(Bundle param1Bundle) throws RemoteException {
      this.mMonitor.onEvent(param1Bundle);
    }
  }
  
  private class RestoreObserverWrapper extends IRestoreObserver.Stub {
    static final int MSG_RESTORE_FINISHED = 3;
    
    static final int MSG_RESTORE_SETS_AVAILABLE = 4;
    
    static final int MSG_RESTORE_STARTING = 1;
    
    static final int MSG_UPDATE = 2;
    
    final RestoreObserver mAppObserver;
    
    final Handler mHandler;
    
    RestoreObserverWrapper(Context param1Context, RestoreObserver param1RestoreObserver) {
      this.mHandler = new Handler(param1Context.getMainLooper()) {
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
      this.mAppObserver = param1RestoreObserver;
    }
    
    public void onUpdate(int param1Int, String param1String) {
      Handler handler = this.mHandler;
      handler.sendMessage(handler.obtainMessage(2, param1Int, 0, param1String));
    }
    
    public void restoreFinished(int param1Int) {
      Handler handler = this.mHandler;
      handler.sendMessage(handler.obtainMessage(3, param1Int, 0));
    }
    
    public void restoreSetsAvailable(RestoreSet[] param1ArrayOfRestoreSet) {
      Handler handler = this.mHandler;
      handler.sendMessage(handler.obtainMessage(4, param1ArrayOfRestoreSet));
    }
    
    public void restoreStarting(int param1Int) {
      Handler handler = this.mHandler;
      handler.sendMessage(handler.obtainMessage(1, param1Int, 0));
    }
  }
  
  class null extends Handler {
    null(Looper param1Looper) {
      super(param1Looper);
    }
    
    public void handleMessage(Message param1Message) {
      int i = param1Message.what;
      if (i != 1) {
        if (i != 2) {
          if (i != 3) {
            if (i == 4)
              this.this$1.mAppObserver.restoreSetsAvailable((RestoreSet[])param1Message.obj); 
          } else {
            this.this$1.mAppObserver.restoreFinished(param1Message.arg1);
          } 
        } else {
          this.this$1.mAppObserver.onUpdate(param1Message.arg1, (String)param1Message.obj);
        } 
      } else {
        this.this$1.mAppObserver.restoreStarting(param1Message.arg1);
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/backup/RestoreSession.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */