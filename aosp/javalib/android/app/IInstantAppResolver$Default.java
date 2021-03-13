package android.app;

import android.content.pm.InstantAppRequestInfo;
import android.os.IBinder;
import android.os.IRemoteCallback;
import android.os.RemoteException;

public class Default implements IInstantAppResolver {
  public IBinder asBinder() {
    return null;
  }
  
  public void getInstantAppIntentFilterList(InstantAppRequestInfo paramInstantAppRequestInfo, IRemoteCallback paramIRemoteCallback) throws RemoteException {}
  
  public void getInstantAppResolveInfoList(InstantAppRequestInfo paramInstantAppRequestInfo, int paramInt, IRemoteCallback paramIRemoteCallback) throws RemoteException {}
}


/* Location:              /home/chun/Desktop/temp/!/android/app/IInstantAppResolver$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */