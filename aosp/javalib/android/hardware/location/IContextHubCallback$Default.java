package android.hardware.location;

import android.os.IBinder;
import android.os.RemoteException;

public class Default implements IContextHubCallback {
  public IBinder asBinder() {
    return null;
  }
  
  public void onMessageReceipt(int paramInt1, int paramInt2, ContextHubMessage paramContextHubMessage) throws RemoteException {}
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/location/IContextHubCallback$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */