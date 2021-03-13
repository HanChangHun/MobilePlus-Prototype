package android.content.pm;

import android.os.IBinder;
import android.os.RemoteException;

public class Default implements IDataLoader {
  public IBinder asBinder() {
    return null;
  }
  
  public void create(int paramInt, DataLoaderParamsParcel paramDataLoaderParamsParcel, FileSystemControlParcel paramFileSystemControlParcel, IDataLoaderStatusListener paramIDataLoaderStatusListener) throws RemoteException {}
  
  public void destroy(int paramInt) throws RemoteException {}
  
  public void prepareImage(int paramInt, InstallationFileParcel[] paramArrayOfInstallationFileParcel, String[] paramArrayOfString) throws RemoteException {}
  
  public void start(int paramInt) throws RemoteException {}
  
  public void stop(int paramInt) throws RemoteException {}
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/IDataLoader$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */