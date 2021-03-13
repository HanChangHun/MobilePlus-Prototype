package android.app.trust;

import android.os.IBinder;
import android.os.RemoteException;

public class Default implements IStrongAuthTracker {
  public IBinder asBinder() {
    return null;
  }
  
  public void onIsNonStrongBiometricAllowedChanged(boolean paramBoolean, int paramInt) throws RemoteException {}
  
  public void onStrongAuthRequiredChanged(int paramInt1, int paramInt2) throws RemoteException {}
}


/* Location:              /home/chun/Desktop/temp/!/android/app/trust/IStrongAuthTracker$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */