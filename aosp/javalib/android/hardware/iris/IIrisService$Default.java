package android.hardware.iris;

import android.os.IBinder;
import android.os.RemoteException;

public class Default implements IIrisService {
  public IBinder asBinder() {
    return null;
  }
  
  public void initConfiguredStrength(int paramInt) throws RemoteException {}
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/iris/IIrisService$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */