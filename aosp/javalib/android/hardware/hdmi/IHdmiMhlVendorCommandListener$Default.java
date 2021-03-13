package android.hardware.hdmi;

import android.os.IBinder;
import android.os.RemoteException;

public class Default implements IHdmiMhlVendorCommandListener {
  public IBinder asBinder() {
    return null;
  }
  
  public void onReceived(int paramInt1, int paramInt2, int paramInt3, byte[] paramArrayOfbyte) throws RemoteException {}
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/hdmi/IHdmiMhlVendorCommandListener$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */