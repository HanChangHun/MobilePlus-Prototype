package android.hardware.biometrics;

import android.os.IBinder;
import android.os.RemoteException;

public class Default implements IBiometricEnabledOnKeyguardCallback {
  public IBinder asBinder() {
    return null;
  }
  
  public void onChanged(BiometricSourceType paramBiometricSourceType, boolean paramBoolean, int paramInt) throws RemoteException {}
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/biometrics/IBiometricEnabledOnKeyguardCallback$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */