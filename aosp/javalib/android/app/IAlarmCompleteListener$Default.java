package android.app;

import android.os.IBinder;
import android.os.RemoteException;

public class Default implements IAlarmCompleteListener {
  public void alarmComplete(IBinder paramIBinder) throws RemoteException {}
  
  public IBinder asBinder() {
    return null;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/IAlarmCompleteListener$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */