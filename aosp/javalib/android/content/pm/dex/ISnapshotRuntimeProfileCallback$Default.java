package android.content.pm.dex;

import android.os.IBinder;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;

public class Default implements ISnapshotRuntimeProfileCallback {
  public IBinder asBinder() {
    return null;
  }
  
  public void onError(int paramInt) throws RemoteException {}
  
  public void onSuccess(ParcelFileDescriptor paramParcelFileDescriptor) throws RemoteException {}
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/dex/ISnapshotRuntimeProfileCallback$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */