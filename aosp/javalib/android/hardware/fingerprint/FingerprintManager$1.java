package android.hardware.fingerprint;

import android.hardware.biometrics.IBiometricServiceLockoutResetCallback;
import android.os.Handler;
import android.os.IRemoteCallback;
import android.os.PowerManager;
import android.os.RemoteException;

class null extends IBiometricServiceLockoutResetCallback.Stub {
  public void onLockoutReset(long paramLong, IRemoteCallback paramIRemoteCallback) throws RemoteException {
    try {
      PowerManager.WakeLock wakeLock = powerManager.newWakeLock(1, "lockoutResetCallback");
      wakeLock.acquire();
      Handler handler = FingerprintManager.access$600(FingerprintManager.this);
      FingerprintManager.LockoutResetCallback lockoutResetCallback = callback;
      _$$Lambda$FingerprintManager$1$4i3tUU8mafgvA9HaB2UPD31L6UY _$$Lambda$FingerprintManager$1$4i3tUU8mafgvA9HaB2UPD31L6UY = new _$$Lambda$FingerprintManager$1$4i3tUU8mafgvA9HaB2UPD31L6UY();
      this(lockoutResetCallback, wakeLock);
      handler.post(_$$Lambda$FingerprintManager$1$4i3tUU8mafgvA9HaB2UPD31L6UY);
      return;
    } finally {
      paramIRemoteCallback.sendResult(null);
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/fingerprint/FingerprintManager$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */