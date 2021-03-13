package android.content.pm.dex;

import android.os.IBinder;
import android.os.RemoteException;

public class Default implements IArtManager {
  public IBinder asBinder() {
    return null;
  }
  
  public boolean isRuntimeProfilingEnabled(int paramInt, String paramString) throws RemoteException {
    return false;
  }
  
  public void snapshotRuntimeProfile(int paramInt, String paramString1, String paramString2, ISnapshotRuntimeProfileCallback paramISnapshotRuntimeProfileCallback, String paramString3) throws RemoteException {}
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/dex/IArtManager$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */