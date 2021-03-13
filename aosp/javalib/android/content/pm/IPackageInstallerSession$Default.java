package android.content.pm;

import android.content.IntentSender;
import android.os.IBinder;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;

public class Default implements IPackageInstallerSession {
  public void abandon() throws RemoteException {}
  
  public void addChildSessionId(int paramInt) throws RemoteException {}
  
  public void addClientProgress(float paramFloat) throws RemoteException {}
  
  public void addFile(int paramInt, String paramString, long paramLong, byte[] paramArrayOfbyte1, byte[] paramArrayOfbyte2) throws RemoteException {}
  
  public IBinder asBinder() {
    return null;
  }
  
  public void close() throws RemoteException {}
  
  public void commit(IntentSender paramIntentSender, boolean paramBoolean) throws RemoteException {}
  
  public int[] getChildSessionIds() throws RemoteException {
    return null;
  }
  
  public DataLoaderParamsParcel getDataLoaderParams() throws RemoteException {
    return null;
  }
  
  public String[] getNames() throws RemoteException {
    return null;
  }
  
  public int getParentSessionId() throws RemoteException {
    return 0;
  }
  
  public boolean isMultiPackage() throws RemoteException {
    return false;
  }
  
  public boolean isStaged() throws RemoteException {
    return false;
  }
  
  public ParcelFileDescriptor openRead(String paramString) throws RemoteException {
    return null;
  }
  
  public ParcelFileDescriptor openWrite(String paramString, long paramLong1, long paramLong2) throws RemoteException {
    return null;
  }
  
  public void removeChildSessionId(int paramInt) throws RemoteException {}
  
  public void removeFile(int paramInt, String paramString) throws RemoteException {}
  
  public void removeSplit(String paramString) throws RemoteException {}
  
  public void setClientProgress(float paramFloat) throws RemoteException {}
  
  public void transfer(String paramString) throws RemoteException {}
  
  public void write(String paramString, long paramLong1, long paramLong2, ParcelFileDescriptor paramParcelFileDescriptor) throws RemoteException {}
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/IPackageInstallerSession$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */