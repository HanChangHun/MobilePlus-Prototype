package android.hardware.hdmi;

import android.os.IBinder;
import android.os.RemoteException;

public class Default implements IHdmiRecordListener {
  public IBinder asBinder() {
    return null;
  }
  
  public byte[] getOneTouchRecordSource(int paramInt) throws RemoteException {
    return null;
  }
  
  public void onClearTimerRecordingResult(int paramInt1, int paramInt2) throws RemoteException {}
  
  public void onOneTouchRecordResult(int paramInt1, int paramInt2) throws RemoteException {}
  
  public void onTimerRecordingResult(int paramInt1, int paramInt2) throws RemoteException {}
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/hdmi/IHdmiRecordListener$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */