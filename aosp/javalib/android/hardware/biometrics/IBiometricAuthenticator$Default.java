package android.hardware.biometrics;

import android.os.IBinder;
import android.os.RemoteException;

public class Default implements IBiometricAuthenticator {
  public IBinder asBinder() {
    return null;
  }
  
  public void cancelAuthenticationFromService(IBinder paramIBinder, String paramString, int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean) throws RemoteException {}
  
  public long getAuthenticatorId(int paramInt) throws RemoteException {
    return 0L;
  }
  
  public boolean hasEnrolledTemplates(int paramInt, String paramString) throws RemoteException {
    return false;
  }
  
  public boolean isHardwareDetected(String paramString) throws RemoteException {
    return false;
  }
  
  public void prepareForAuthentication(boolean paramBoolean, IBinder paramIBinder, long paramLong, int paramInt1, IBiometricServiceReceiverInternal paramIBiometricServiceReceiverInternal, String paramString, int paramInt2, int paramInt3, int paramInt4, int paramInt5) throws RemoteException {}
  
  public void resetLockout(byte[] paramArrayOfbyte) throws RemoteException {}
  
  public void setActiveUser(int paramInt) throws RemoteException {}
  
  public void startPreparedClient(int paramInt) throws RemoteException {}
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/biometrics/IBiometricAuthenticator$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */