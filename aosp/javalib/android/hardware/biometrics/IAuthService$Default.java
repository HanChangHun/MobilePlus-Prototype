package android.hardware.biometrics;

import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;

public class Default implements IAuthService {
  public IBinder asBinder() {
    return null;
  }
  
  public void authenticate(IBinder paramIBinder, long paramLong, int paramInt, IBiometricServiceReceiver paramIBiometricServiceReceiver, String paramString, Bundle paramBundle) throws RemoteException {}
  
  public int canAuthenticate(String paramString, int paramInt1, int paramInt2) throws RemoteException {
    return 0;
  }
  
  public void cancelAuthentication(IBinder paramIBinder, String paramString) throws RemoteException {}
  
  public long[] getAuthenticatorIds() throws RemoteException {
    return null;
  }
  
  public boolean hasEnrolledBiometrics(int paramInt, String paramString) throws RemoteException {
    return false;
  }
  
  public void registerEnabledOnKeyguardCallback(IBiometricEnabledOnKeyguardCallback paramIBiometricEnabledOnKeyguardCallback) throws RemoteException {}
  
  public void resetLockout(byte[] paramArrayOfbyte) throws RemoteException {}
  
  public void setActiveUser(int paramInt) throws RemoteException {}
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/biometrics/IAuthService$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */