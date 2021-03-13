package android.hardware.biometrics;

import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;

public class Default implements IBiometricService {
  public IBinder asBinder() {
    return null;
  }
  
  public void authenticate(IBinder paramIBinder, long paramLong, int paramInt1, IBiometricServiceReceiver paramIBiometricServiceReceiver, String paramString, Bundle paramBundle, int paramInt2, int paramInt3, int paramInt4) throws RemoteException {}
  
  public int canAuthenticate(String paramString, int paramInt1, int paramInt2, int paramInt3) throws RemoteException {
    return 0;
  }
  
  public void cancelAuthentication(IBinder paramIBinder, String paramString, int paramInt1, int paramInt2, int paramInt3) throws RemoteException {}
  
  public long[] getAuthenticatorIds(int paramInt) throws RemoteException {
    return null;
  }
  
  public boolean hasEnrolledBiometrics(int paramInt, String paramString) throws RemoteException {
    return false;
  }
  
  public void onReadyForAuthentication(int paramInt1, boolean paramBoolean, int paramInt2) throws RemoteException {}
  
  public void registerAuthenticator(int paramInt1, int paramInt2, int paramInt3, IBiometricAuthenticator paramIBiometricAuthenticator) throws RemoteException {}
  
  public void registerEnabledOnKeyguardCallback(IBiometricEnabledOnKeyguardCallback paramIBiometricEnabledOnKeyguardCallback, int paramInt) throws RemoteException {}
  
  public void resetLockout(byte[] paramArrayOfbyte) throws RemoteException {}
  
  public void setActiveUser(int paramInt) throws RemoteException {}
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/biometrics/IBiometricService$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */