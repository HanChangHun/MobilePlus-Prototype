package android.companion;

import android.os.IBinder;
import android.os.RemoteException;
import com.android.internal.infra.AndroidFuture;

public class Default implements ICompanionDeviceDiscoveryService {
  public IBinder asBinder() {
    return null;
  }
  
  public void startDiscovery(AssociationRequest paramAssociationRequest, String paramString, IFindDeviceCallback paramIFindDeviceCallback, AndroidFuture<Association> paramAndroidFuture) throws RemoteException {}
}


/* Location:              /home/chun/Desktop/temp/!/android/companion/ICompanionDeviceDiscoveryService$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */