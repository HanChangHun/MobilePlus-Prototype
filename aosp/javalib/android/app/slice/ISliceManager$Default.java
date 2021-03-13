package android.app.slice;

import android.net.Uri;
import android.os.IBinder;
import android.os.RemoteException;

public class Default implements ISliceManager {
  public void applyRestore(byte[] paramArrayOfbyte, int paramInt) throws RemoteException {}
  
  public IBinder asBinder() {
    return null;
  }
  
  public int checkSlicePermission(Uri paramUri, String paramString1, String paramString2, int paramInt1, int paramInt2, String[] paramArrayOfString) throws RemoteException {
    return 0;
  }
  
  public byte[] getBackupPayload(int paramInt) throws RemoteException {
    return null;
  }
  
  public Uri[] getPinnedSlices(String paramString) throws RemoteException {
    return null;
  }
  
  public SliceSpec[] getPinnedSpecs(Uri paramUri, String paramString) throws RemoteException {
    return null;
  }
  
  public void grantPermissionFromUser(Uri paramUri, String paramString1, String paramString2, boolean paramBoolean) throws RemoteException {}
  
  public void grantSlicePermission(String paramString1, String paramString2, Uri paramUri) throws RemoteException {}
  
  public boolean hasSliceAccess(String paramString) throws RemoteException {
    return false;
  }
  
  public void pinSlice(String paramString, Uri paramUri, SliceSpec[] paramArrayOfSliceSpec, IBinder paramIBinder) throws RemoteException {}
  
  public void revokeSlicePermission(String paramString1, String paramString2, Uri paramUri) throws RemoteException {}
  
  public void unpinSlice(String paramString, Uri paramUri, IBinder paramIBinder) throws RemoteException {}
}


/* Location:              /home/chun/Desktop/temp/!/android/app/slice/ISliceManager$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */