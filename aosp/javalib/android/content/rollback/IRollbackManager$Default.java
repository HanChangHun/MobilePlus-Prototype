package android.content.rollback;

import android.content.IntentSender;
import android.content.pm.ParceledListSlice;
import android.os.IBinder;
import android.os.RemoteException;

public class Default implements IRollbackManager {
  public IBinder asBinder() {
    return null;
  }
  
  public void blockRollbackManager(long paramLong) throws RemoteException {}
  
  public void commitRollback(int paramInt, ParceledListSlice paramParceledListSlice, String paramString, IntentSender paramIntentSender) throws RemoteException {}
  
  public void expireRollbackForPackage(String paramString) throws RemoteException {}
  
  public ParceledListSlice getAvailableRollbacks() throws RemoteException {
    return null;
  }
  
  public ParceledListSlice getRecentlyCommittedRollbacks() throws RemoteException {
    return null;
  }
  
  public void notifyStagedApkSession(int paramInt1, int paramInt2) throws RemoteException {}
  
  public int notifyStagedSession(int paramInt) throws RemoteException {
    return 0;
  }
  
  public void reloadPersistedData() throws RemoteException {}
  
  public void snapshotAndRestoreUserData(String paramString1, int[] paramArrayOfint, int paramInt1, long paramLong, String paramString2, int paramInt2) throws RemoteException {}
}


/* Location:              /home/chun/Desktop/temp/!/android/content/rollback/IRollbackManager$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */