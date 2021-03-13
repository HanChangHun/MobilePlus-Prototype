package android.hardware.location;

import android.os.IBinder;
import android.os.RemoteException;

public class Default implements IContextHubClient {
  public IBinder asBinder() {
    return null;
  }
  
  public void close() throws RemoteException {}
  
  public int sendMessageToNanoApp(NanoAppMessage paramNanoAppMessage) throws RemoteException {
    return 0;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/location/IContextHubClient$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */