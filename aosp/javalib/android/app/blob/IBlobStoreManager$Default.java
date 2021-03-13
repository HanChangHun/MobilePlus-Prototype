package android.app.blob;

import android.os.IBinder;
import android.os.ParcelFileDescriptor;
import android.os.RemoteCallback;
import android.os.RemoteException;
import java.util.List;

public class Default implements IBlobStoreManager {
  public void abandonSession(long paramLong, String paramString) throws RemoteException {}
  
  public void acquireLease(BlobHandle paramBlobHandle, int paramInt, CharSequence paramCharSequence, long paramLong, String paramString) throws RemoteException {}
  
  public IBinder asBinder() {
    return null;
  }
  
  public long createSession(BlobHandle paramBlobHandle, String paramString) throws RemoteException {
    return 0L;
  }
  
  public void deleteBlob(long paramLong) throws RemoteException {}
  
  public LeaseInfo getLeaseInfo(BlobHandle paramBlobHandle, String paramString) throws RemoteException {
    return null;
  }
  
  public List<BlobHandle> getLeasedBlobs(String paramString) throws RemoteException {
    return null;
  }
  
  public long getRemainingLeaseQuotaBytes(String paramString) throws RemoteException {
    return 0L;
  }
  
  public ParcelFileDescriptor openBlob(BlobHandle paramBlobHandle, String paramString) throws RemoteException {
    return null;
  }
  
  public IBlobStoreSession openSession(long paramLong, String paramString) throws RemoteException {
    return null;
  }
  
  public List<BlobInfo> queryBlobsForUser(int paramInt) throws RemoteException {
    return null;
  }
  
  public void releaseLease(BlobHandle paramBlobHandle, String paramString) throws RemoteException {}
  
  public void waitForIdle(RemoteCallback paramRemoteCallback) throws RemoteException {}
}


/* Location:              /home/chun/Desktop/temp/!/android/app/blob/IBlobStoreManager$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */