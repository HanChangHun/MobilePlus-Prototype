package android.telephony.data;

import android.os.IBinder;
import android.os.RemoteException;

public class Default implements IQualifiedNetworksService {
  public IBinder asBinder() {
    return null;
  }
  
  public void createNetworkAvailabilityProvider(int paramInt, IQualifiedNetworksServiceCallback paramIQualifiedNetworksServiceCallback) throws RemoteException {}
  
  public void removeNetworkAvailabilityProvider(int paramInt) throws RemoteException {}
}


/* Location:              /home/chun/Desktop/temp/!/android/telephony/data/IQualifiedNetworksService$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */