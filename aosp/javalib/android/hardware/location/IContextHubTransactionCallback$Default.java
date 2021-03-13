package android.hardware.location;

import android.os.IBinder;
import android.os.RemoteException;
import java.util.List;

public class Default implements IContextHubTransactionCallback {
  public IBinder asBinder() {
    return null;
  }
  
  public void onQueryResponse(int paramInt, List<NanoAppState> paramList) throws RemoteException {}
  
  public void onTransactionComplete(int paramInt) throws RemoteException {}
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/location/IContextHubTransactionCallback$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */