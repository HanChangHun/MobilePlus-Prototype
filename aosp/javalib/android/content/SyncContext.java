package android.content;

import android.os.IBinder;
import android.os.RemoteException;
import android.os.SystemClock;

public class SyncContext {
  private static final long HEARTBEAT_SEND_INTERVAL_IN_MS = 1000L;
  
  private long mLastHeartbeatSendTime;
  
  private ISyncContext mSyncContext;
  
  public SyncContext(ISyncContext paramISyncContext) {
    this.mSyncContext = paramISyncContext;
    this.mLastHeartbeatSendTime = 0L;
  }
  
  private void updateHeartbeat() {
    long l = SystemClock.elapsedRealtime();
    if (l < this.mLastHeartbeatSendTime + 1000L)
      return; 
    try {
      this.mLastHeartbeatSendTime = l;
      if (this.mSyncContext != null)
        this.mSyncContext.sendHeartbeat(); 
    } catch (RemoteException remoteException) {}
  }
  
  public IBinder getSyncContextBinder() {
    IBinder iBinder;
    ISyncContext iSyncContext = this.mSyncContext;
    if (iSyncContext == null) {
      iSyncContext = null;
    } else {
      iBinder = iSyncContext.asBinder();
    } 
    return iBinder;
  }
  
  public void onFinished(SyncResult paramSyncResult) {
    try {
      if (this.mSyncContext != null)
        this.mSyncContext.onFinished(paramSyncResult); 
    } catch (RemoteException remoteException) {}
  }
  
  public void setStatusText(String paramString) {
    updateHeartbeat();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/SyncContext.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */