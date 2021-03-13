package android.hardware.location;

import android.app.PendingIntent;
import android.os.IBinder;
import android.os.RemoteException;
import java.util.List;

public class Default implements IContextHubService {
  public IBinder asBinder() {
    return null;
  }
  
  public IContextHubClient createClient(int paramInt, IContextHubClientCallback paramIContextHubClientCallback) throws RemoteException {
    return null;
  }
  
  public IContextHubClient createPendingIntentClient(int paramInt, PendingIntent paramPendingIntent, long paramLong) throws RemoteException {
    return null;
  }
  
  public void disableNanoApp(int paramInt, IContextHubTransactionCallback paramIContextHubTransactionCallback, long paramLong) throws RemoteException {}
  
  public void enableNanoApp(int paramInt, IContextHubTransactionCallback paramIContextHubTransactionCallback, long paramLong) throws RemoteException {}
  
  public int[] findNanoAppOnHub(int paramInt, NanoAppFilter paramNanoAppFilter) throws RemoteException {
    return null;
  }
  
  public int[] getContextHubHandles() throws RemoteException {
    return null;
  }
  
  public ContextHubInfo getContextHubInfo(int paramInt) throws RemoteException {
    return null;
  }
  
  public List<ContextHubInfo> getContextHubs() throws RemoteException {
    return null;
  }
  
  public NanoAppInstanceInfo getNanoAppInstanceInfo(int paramInt) throws RemoteException {
    return null;
  }
  
  public int loadNanoApp(int paramInt, NanoApp paramNanoApp) throws RemoteException {
    return 0;
  }
  
  public void loadNanoAppOnHub(int paramInt, IContextHubTransactionCallback paramIContextHubTransactionCallback, NanoAppBinary paramNanoAppBinary) throws RemoteException {}
  
  public void queryNanoApps(int paramInt, IContextHubTransactionCallback paramIContextHubTransactionCallback) throws RemoteException {}
  
  public int registerCallback(IContextHubCallback paramIContextHubCallback) throws RemoteException {
    return 0;
  }
  
  public int sendMessage(int paramInt1, int paramInt2, ContextHubMessage paramContextHubMessage) throws RemoteException {
    return 0;
  }
  
  public int unloadNanoApp(int paramInt) throws RemoteException {
    return 0;
  }
  
  public void unloadNanoAppFromHub(int paramInt, IContextHubTransactionCallback paramIContextHubTransactionCallback, long paramLong) throws RemoteException {}
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/location/IContextHubService$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */