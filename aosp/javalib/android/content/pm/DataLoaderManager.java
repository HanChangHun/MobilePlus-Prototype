package android.content.pm;

import android.os.RemoteException;

public class DataLoaderManager {
  private static final String TAG = "DataLoaderManager";
  
  private final IDataLoaderManager mService;
  
  public DataLoaderManager(IDataLoaderManager paramIDataLoaderManager) {
    this.mService = paramIDataLoaderManager;
  }
  
  public boolean bindToDataLoader(int paramInt, DataLoaderParamsParcel paramDataLoaderParamsParcel, IDataLoaderStatusListener paramIDataLoaderStatusListener) {
    try {
      return this.mService.bindToDataLoader(paramInt, paramDataLoaderParamsParcel, paramIDataLoaderStatusListener);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public IDataLoader getDataLoader(int paramInt) {
    try {
      return this.mService.getDataLoader(paramInt);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void unbindFromDataLoader(int paramInt) {
    try {
      this.mService.unbindFromDataLoader(paramInt);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/DataLoaderManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */