package android.app.trust;

import android.hardware.biometrics.BiometricSourceType;
import android.os.IBinder;
import android.os.RemoteException;

public class Default implements ITrustManager {
  public IBinder asBinder() {
    return null;
  }
  
  public void clearAllBiometricRecognized(BiometricSourceType paramBiometricSourceType) throws RemoteException {}
  
  public boolean isDeviceLocked(int paramInt) throws RemoteException {
    return false;
  }
  
  public boolean isDeviceSecure(int paramInt) throws RemoteException {
    return false;
  }
  
  public boolean isTrustUsuallyManaged(int paramInt) throws RemoteException {
    return false;
  }
  
  public void registerTrustListener(ITrustListener paramITrustListener) throws RemoteException {}
  
  public void reportEnabledTrustAgentsChanged(int paramInt) throws RemoteException {}
  
  public void reportKeyguardShowingChanged() throws RemoteException {}
  
  public void reportUnlockAttempt(boolean paramBoolean, int paramInt) throws RemoteException {}
  
  public void reportUnlockLockout(int paramInt1, int paramInt2) throws RemoteException {}
  
  public void setDeviceLockedForUser(int paramInt, boolean paramBoolean) throws RemoteException {}
  
  public void unlockedByBiometricForUser(int paramInt, BiometricSourceType paramBiometricSourceType) throws RemoteException {}
  
  public void unregisterTrustListener(ITrustListener paramITrustListener) throws RemoteException {}
}


/* Location:              /home/chun/Desktop/temp/!/android/app/trust/ITrustManager$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */