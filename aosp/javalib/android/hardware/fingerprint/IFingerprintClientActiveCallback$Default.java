package android.hardware.fingerprint;

import android.os.IBinder;
import android.os.RemoteException;

public class Default implements IFingerprintClientActiveCallback {
  public IBinder asBinder() {
    return null;
  }
  
  public void onClientActiveChanged(boolean paramBoolean) throws RemoteException {}
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/fingerprint/IFingerprintClientActiveCallback$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */