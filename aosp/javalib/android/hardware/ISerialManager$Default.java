package android.hardware;

import android.os.IBinder;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;

public class Default implements ISerialManager {
  public IBinder asBinder() {
    return null;
  }
  
  public String[] getSerialPorts() throws RemoteException {
    return null;
  }
  
  public ParcelFileDescriptor openSerialPort(String paramString) throws RemoteException {
    return null;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/ISerialManager$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */