package android.hardware;

import android.os.IBinder;
import android.os.RemoteException;

public class Default implements IConsumerIrService {
  public IBinder asBinder() {
    return null;
  }
  
  public int[] getCarrierFrequencies() throws RemoteException {
    return null;
  }
  
  public boolean hasIrEmitter() throws RemoteException {
    return false;
  }
  
  public void transmit(String paramString, int paramInt, int[] paramArrayOfint) throws RemoteException {}
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/IConsumerIrService$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */