package android.content.pm;

import android.os.IBinder;
import android.os.RemoteException;

public class Default implements IDataLoaderManager {
  public IBinder asBinder() {
    return null;
  }
  
  public boolean bindToDataLoader(int paramInt, DataLoaderParamsParcel paramDataLoaderParamsParcel, IDataLoaderStatusListener paramIDataLoaderStatusListener) throws RemoteException {
    return false;
  }
  
  public IDataLoader getDataLoader(int paramInt) throws RemoteException {
    return null;
  }
  
  public void unbindFromDataLoader(int paramInt) throws RemoteException {}
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/IDataLoaderManager$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */