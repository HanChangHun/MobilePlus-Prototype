package android.hardware.face;

import android.hardware.biometrics.IBiometricServiceLockoutResetCallback;
import android.os.Handler;
import android.os.IRemoteCallback;
import android.os.PowerManager;
import android.os.RemoteException;

class null extends IBiometricServiceLockoutResetCallback.Stub {
  public void onLockoutReset(long paramLong, IRemoteCallback paramIRemoteCallback) throws RemoteException {
    try {
      PowerManager.WakeLock wakeLock = powerManager.newWakeLock(1, "faceLockoutResetCallback");
      wakeLock.acquire();
      Handler handler = FaceManager.access$000(FaceManager.this);
      FaceManager.LockoutResetCallback lockoutResetCallback = callback;
      _$$Lambda$FaceManager$2$IVmrd2VOH7JdDdb7PFFlL5bjZ5w _$$Lambda$FaceManager$2$IVmrd2VOH7JdDdb7PFFlL5bjZ5w = new _$$Lambda$FaceManager$2$IVmrd2VOH7JdDdb7PFFlL5bjZ5w();
      this(lockoutResetCallback, wakeLock);
      handler.post(_$$Lambda$FaceManager$2$IVmrd2VOH7JdDdb7PFFlL5bjZ5w);
      return;
    } finally {
      paramIRemoteCallback.sendResult(null);
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/face/FaceManager$2.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */