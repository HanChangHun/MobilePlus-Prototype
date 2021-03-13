package android.app.blob;

import android.os.IBinder;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;

public class Default implements IBlobStoreSession {
  public void abandon() throws RemoteException {}
  
  public void allowPackageAccess(String paramString, byte[] paramArrayOfbyte) throws RemoteException {}
  
  public void allowPublicAccess() throws RemoteException {}
  
  public void allowSameSignatureAccess() throws RemoteException {}
  
  public IBinder asBinder() {
    return null;
  }
  
  public void close() throws RemoteException {}
  
  public void commit(IBlobCommitCallback paramIBlobCommitCallback) throws RemoteException {}
  
  public long getSize() throws RemoteException {
    return 0L;
  }
  
  public boolean isPackageAccessAllowed(String paramString, byte[] paramArrayOfbyte) throws RemoteException {
    return false;
  }
  
  public boolean isPublicAccessAllowed() throws RemoteException {
    return false;
  }
  
  public boolean isSameSignatureAccessAllowed() throws RemoteException {
    return false;
  }
  
  public ParcelFileDescriptor openRead() throws RemoteException {
    return null;
  }
  
  public ParcelFileDescriptor openWrite(long paramLong1, long paramLong2) throws RemoteException {
    return null;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/blob/IBlobStoreSession$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */