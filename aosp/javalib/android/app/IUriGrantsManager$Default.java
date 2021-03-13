package android.app;

import android.content.pm.ParceledListSlice;
import android.net.Uri;
import android.os.IBinder;
import android.os.RemoteException;

public class Default implements IUriGrantsManager {
  public IBinder asBinder() {
    return null;
  }
  
  public void clearGrantedUriPermissions(String paramString, int paramInt) throws RemoteException {}
  
  public ParceledListSlice getGrantedUriPermissions(String paramString, int paramInt) throws RemoteException {
    return null;
  }
  
  public ParceledListSlice getUriPermissions(String paramString, boolean paramBoolean1, boolean paramBoolean2) throws RemoteException {
    return null;
  }
  
  public void grantUriPermissionFromOwner(IBinder paramIBinder, int paramInt1, String paramString, Uri paramUri, int paramInt2, int paramInt3, int paramInt4) throws RemoteException {}
  
  public void releasePersistableUriPermission(Uri paramUri, int paramInt1, String paramString, int paramInt2) throws RemoteException {}
  
  public void takePersistableUriPermission(Uri paramUri, int paramInt1, String paramString, int paramInt2) throws RemoteException {}
}


/* Location:              /home/chun/Desktop/temp/!/android/app/IUriGrantsManager$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */