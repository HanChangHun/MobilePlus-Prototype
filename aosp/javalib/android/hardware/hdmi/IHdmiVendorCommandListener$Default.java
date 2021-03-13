package android.hardware.hdmi;

import android.os.IBinder;
import android.os.RemoteException;

public class Default implements IHdmiVendorCommandListener {
  public IBinder asBinder() {
    return null;
  }
  
  public void onControlStateChanged(boolean paramBoolean, int paramInt) throws RemoteException {}
  
  public void onReceived(int paramInt1, int paramInt2, byte[] paramArrayOfbyte, boolean paramBoolean) throws RemoteException {}
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/hdmi/IHdmiVendorCommandListener$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */