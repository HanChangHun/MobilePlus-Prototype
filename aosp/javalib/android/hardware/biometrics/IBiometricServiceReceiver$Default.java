package android.hardware.biometrics;

import android.os.IBinder;
import android.os.RemoteException;

public class Default implements IBiometricServiceReceiver {
  public IBinder asBinder() {
    return null;
  }
  
  public void onAcquired(int paramInt, String paramString) throws RemoteException {}
  
  public void onAuthenticationFailed() throws RemoteException {}
  
  public void onAuthenticationSucceeded(int paramInt) throws RemoteException {}
  
  public void onDialogDismissed(int paramInt) throws RemoteException {}
  
  public void onError(int paramInt1, int paramInt2, int paramInt3) throws RemoteException {}
  
  public void onSystemEvent(int paramInt) throws RemoteException {}
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/biometrics/IBiometricServiceReceiver$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */