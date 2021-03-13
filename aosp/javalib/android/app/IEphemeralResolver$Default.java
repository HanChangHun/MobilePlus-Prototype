package android.app;

import android.os.IBinder;
import android.os.IRemoteCallback;
import android.os.RemoteException;

public class Default implements IEphemeralResolver {
  public IBinder asBinder() {
    return null;
  }
  
  public void getEphemeralIntentFilterList(IRemoteCallback paramIRemoteCallback, String paramString, int paramInt) throws RemoteException {}
  
  public void getEphemeralResolveInfoList(IRemoteCallback paramIRemoteCallback, int[] paramArrayOfint, int paramInt) throws RemoteException {}
}


/* Location:              /home/chun/Desktop/temp/!/android/app/IEphemeralResolver$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */