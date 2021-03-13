package android.app.timezone;

import android.os.IBinder;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;

public class Default implements IRulesManager {
  public IBinder asBinder() {
    return null;
  }
  
  public RulesState getRulesState() throws RemoteException {
    return null;
  }
  
  public int requestInstall(ParcelFileDescriptor paramParcelFileDescriptor, byte[] paramArrayOfbyte, ICallback paramICallback) throws RemoteException {
    return 0;
  }
  
  public void requestNothing(byte[] paramArrayOfbyte, boolean paramBoolean) throws RemoteException {}
  
  public int requestUninstall(byte[] paramArrayOfbyte, ICallback paramICallback) throws RemoteException {
    return 0;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/timezone/IRulesManager$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */