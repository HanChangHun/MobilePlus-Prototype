package android.telephony.data;

import android.os.IBinder;
import android.os.RemoteException;

public class Default implements IQualifiedNetworksServiceCallback {
  public IBinder asBinder() {
    return null;
  }
  
  public void onQualifiedNetworkTypesChanged(int paramInt, int[] paramArrayOfint) throws RemoteException {}
}


/* Location:              /home/chun/Desktop/temp/!/android/telephony/data/IQualifiedNetworksServiceCallback$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */