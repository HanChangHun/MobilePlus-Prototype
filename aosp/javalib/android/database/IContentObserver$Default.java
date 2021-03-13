package android.database;

import android.net.Uri;
import android.os.IBinder;
import android.os.RemoteException;

public class Default implements IContentObserver {
  public IBinder asBinder() {
    return null;
  }
  
  public void onChange(boolean paramBoolean, Uri paramUri, int paramInt) throws RemoteException {}
  
  public void onChangeEtc(boolean paramBoolean, Uri[] paramArrayOfUri, int paramInt1, int paramInt2) throws RemoteException {}
}


/* Location:              /home/chun/Desktop/temp/!/android/database/IContentObserver$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */